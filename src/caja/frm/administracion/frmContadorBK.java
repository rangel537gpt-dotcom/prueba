/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.administracion;

import caja.bo.ClienteBO;
import caja.bo.ContadorBO;
import caja.bo.DocumentoPagoBO;
import caja.bo.SeguridadBO;
import caja.bo.soloMayusculas;
import caja.dto.FichaDatosDTO;
import caja.frm.caja.frmCargando;
import caja.frm.frmPrincipal;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteAuditorIndependiente;
import caja.mapeo.entidades.ClienteCertificado;
import caja.mapeo.entidades.ClienteComitePerito;
import caja.mapeo.entidades.ClienteDerechoHabiente;
import caja.mapeo.entidades.TipoDocIdentidad;
//import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoLocalidad;
import caja.mapeo.entidades.UbigeoProvincia;
import java.awt.Desktop;
//import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
//import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import units.DatePicker;

/**
 *
 * @author juan carlos
 */
public class frmContadorBK extends javax.swing.JInternalFrame {

    private Cliente cliActual = new Cliente();
    private boolean accesoGuardar = true;

//    List<Cliente> listaCliente;
    /**
     * Creates new form frmContador
     */
    public frmContadorBK() {
        initComponents();

        TableColumn columna = tContador.getColumn("NRO");
        columna.setPreferredWidth(10);
        columna = tContador.getColumn("ID");
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna.setPreferredWidth(0);
        columna = tContador.getColumn("CODIGO");
        columna.setPreferredWidth(15);
        columna = tContador.getColumn("DNI");
        columna.setPreferredWidth(20);
        columna = tContador.getColumn("APELLIDO PATERNO");
        columna.setPreferredWidth(100);
        columna = tContador.getColumn("APELLIDO MATERNO");
        columna.setPreferredWidth(100);
        columna = tContador.getColumn("NOMBRES");
        columna.setPreferredWidth(150);
        columna = tContador.getColumn("FECHA NACIMIENTO");
        columna.setPreferredWidth(80);
        columna = tContador.getColumn("SEXO");
        columna.setPreferredWidth(30);

        columna = tCertificados.getColumn("OBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tCertificados.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);

        columna = tDerechoHabiente.getColumn("OBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDerechoHabiente.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tDerechoHabiente.getColumn("DNI");
        columna.setPreferredWidth(100);
        columna.setMinWidth(100);
        columna.setMaxWidth(100);
        columna = tDerechoHabiente.getColumn("F. NAC");
        columna.setPreferredWidth(80);
        columna.setMinWidth(80);
        columna.setMaxWidth(80);
        columna = tDerechoHabiente.getColumn("CELULAR");
        columna.setPreferredWidth(90);
        columna.setMinWidth(90);
        columna.setMaxWidth(90);

        columna = tAuditorIndependiente.getColumn("OBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tAuditorIndependiente.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tAuditorIndependiente.getColumn("F. VIGENCIA");
        columna.setPreferredWidth(140);
        columna.setMinWidth(140);
        columna.setMaxWidth(140);
        columna = tAuditorIndependiente.getColumn("F. V. DESDE");
        columna.setPreferredWidth(140);
        columna.setMinWidth(140);
        columna.setMaxWidth(140);
        columna = tAuditorIndependiente.getColumn("F. V. HASTA");
        columna.setPreferredWidth(140);
        columna.setMinWidth(140);
        columna.setMaxWidth(140);

        columna = tComitePerito.getColumn("OBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComitePerito.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tComitePerito.getColumn("F. HASTA");
        columna.setPreferredWidth(140);
        columna.setMinWidth(140);
        columna.setMaxWidth(140);
        columna = tComitePerito.getColumn("F. DESDE");
        columna.setPreferredWidth(140);
        columna.setMinWidth(140);
        columna.setMaxWidth(140);

        txtDNI.setEditable(false);
        txtCodigo.setEditable(false);
        txtPassword.setEditable(false);
        cbCondicion.setEnabled(false);
        txtApePat.setEditable(false);
        txtApePat.setDocument(new soloMayusculas());
        txtFechaNac.setEditable(false);
        cbSexo.setEnabled(false);
        txtApeMat.setEditable(false);
        txtApeMat.setDocument(new soloMayusculas());
        txtFechaAfiliacion.setEditable(false);
        txtTelefonoDomicilio.setEditable(false);
        txtNombres.setEditable(false);
        txtNombres.setDocument(new soloMayusculas());
        txtCentroTrabajo.setEditable(false);
        txtMatriculaAuditor.setEditable(false);
        cbActivo.setEnabled(false);
        txtCentroTrabajo.setDocument(new soloMayusculas());
        txtTelefonoCelular.setEditable(false);
        txtDireccionDomicilio.setEditable(false);
        txtDireccionDomicilio.setDocument(new soloMayusculas());
        cbCertificacion.setEnabled(false);
        txtNumeroCertificado.setEditable(false);
        txtTelefonoCelular1.setEditable(false);
        cbDepartamentoDomicilio.setEnabled(false);
        cbProvinciaDomicilio.setEnabled(false);
        cbDistritoDomicilio.setEnabled(false);
        cbLocalidadDomicilio.setEnabled(false);
        txtTelefonoTrabajo.setEditable(false);
        txtDireccionTrabajo.setEditable(false);
        txtDireccionTrabajo.setDocument(new soloMayusculas());
        txtTelefonoTrabajo1.setEditable(false);
        cbDepartamentoTrabajo.setEnabled(false);
        cbProvinciaTrabajo.setEnabled(false);
        cbDistritoTrabajo.setEnabled(false);
        cbLocalidadTrabajo.setEnabled(false);
        txtEmail.setEditable(false);
        txtEmailAlternativo.setEditable(false);
        cbUniversidad.setEnabled(false);
        cbBuzon.setEnabled(false);
        cbCobrador.setEnabled(false);
        cbLugarCobranza.setEnabled(false);
        txtObservaciones.setEditable(false);
        txtObservaciones.setDocument(new soloMayusculas());
        btnFecha.setEnabled(false);
        btnFechaA.setEnabled(false);
        txtFechaNac.setText("1900-01-01");
        txtFechaAfiliacion.setText("1900-01-01");
        txtFechaCertificacionDesde.setText("1900-01-01");
        txtFechaCertificacionHasta.setText("1900-01-01");

        ContadorBO contBO = ContadorBO.getInstance();

        List<UbigeoDepartamento> lp = contBO.ObtenerContadorUbigeoDepartamento();
        for (UbigeoDepartamento udep : lp) {
            cbDepartamentoDomicilio.addItem(udep);
        }

        List<UbigeoDepartamento> lt = contBO.ObtenerContadorUbigeoDepartamento();
        for (UbigeoDepartamento udep : lt) {
            cbDepartamentoTrabajo.addItem(udep);
        }
        SeguridadBO sBO = SeguridadBO.getInstance();
        if (sBO.getUsuario().getRolUsuario().equals("SA")) {
            accesoGuardar = true;
        } else {
            if (sBO.getUsuario().getRolUsuario().equals("A")) {
                accesoGuardar = true;
            } else {
                accesoGuardar = false;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBusqueda = new javax.swing.JTextField();
        cbFiltro = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        btnIngresarContador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tContador = new javax.swing.JTable();
        pbContador = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtApePat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApeMat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnFecha = new javax.swing.JButton();
        btnFechaA = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtDireccionDomicilio = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbDepartamentoDomicilio = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        cbProvinciaDomicilio = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        cbDistritoDomicilio = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        cbLocalidadDomicilio = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        cbCondicion = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTelefonoDomicilio = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTelefonoCelular = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtTelefonoCelular1 = new javax.swing.JTextField();
        txtFechaNac = new javax.swing.JFormattedTextField();
        txtFechaAfiliacion = new javax.swing.JFormattedTextField();
        jLabel41 = new javax.swing.JLabel();
        cbEstadoCivil = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtDireccionTrabajo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbDepartamentoTrabajo = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        cbProvinciaTrabajo = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        cbDistritoTrabajo = new javax.swing.JComboBox();
        jLabel38 = new javax.swing.JLabel();
        cbLocalidadTrabajo = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        txtTelefonoTrabajo = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtTelefonoTrabajo1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtEmailAlternativo = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbUniversidad = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        txtMatriculaAuditor = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        cbActivo = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        cbCertificacion = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        txtNumeroCertificado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCentroTrabajo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbCobrador = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cbLugarCobranza = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        cbBuzon = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tCertificados = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtFechaCertificacionDesde = new javax.swing.JFormattedTextField();
        btnFecha1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtFechaCertificacionHasta = new javax.swing.JFormattedTextField();
        btnFecha2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        cbTipoCertificacion = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tDerechoHabiente = new javax.swing.JTable();
        btnAgregarPariente = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tAuditorIndependiente = new javax.swing.JTable();
        btnAgregarPariente1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnAgregarPariente2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tComitePerito = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FORMULARIO DEL CONTADOR");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 600));

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "POR CODIGO", "POR DNI", "POR NOMBRES" }));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/buscar_mini1.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnIngresarContador.setText("Ingresar Contador");
        btnIngresarContador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarContadorActionPerformed(evt);
            }
        });

        tContador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NRO", "CODIGO", "DNI", "APELLIDO PATERNO", "APELLIDO MATERNO", "NOMBRES", "FECHA NACIMIENTO", "SEXO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tContador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tContadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tContador);

        jButton1.setText("Exportar Padron");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("DNI:");

        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDNIKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        jLabel7.setText("Código del Colegiado:");

        jLabel3.setText("Apellido Paterno:");

        txtApePat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApePatKeyPressed(evt);
            }
        });

        jLabel4.setText("Apellido Materno:");

        txtApeMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApeMatKeyPressed(evt);
            }
        });

        jLabel5.setText("Nombres:");

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombresKeyPressed(evt);
            }
        });

        jLabel9.setText("Fecha de Nacimiento:");

        jLabel13.setText("Fecha de Afiliación:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        btnFecha.setText("...");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });

        btnFechaA.setText("...");
        btnFechaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaAActionPerformed(evt);
            }
        });

        jLabel6.setText("Domicilio:");

        txtDireccionDomicilio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionDomicilioKeyPressed(evt);
            }
        });

        jLabel16.setText("Departamento:");

        cbDepartamentoDomicilio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDepartamentoDomicilioItemStateChanged(evt);
            }
        });
        cbDepartamentoDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDepartamentoDomicilioActionPerformed(evt);
            }
        });

        jLabel17.setText("Provincia:");

        cbProvinciaDomicilio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProvinciaDomicilioItemStateChanged(evt);
            }
        });
        cbProvinciaDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProvinciaDomicilioActionPerformed(evt);
            }
        });

        jLabel18.setText("Distrito:");

        cbDistritoDomicilio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDistritoDomicilioItemStateChanged(evt);
            }
        });
        cbDistritoDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDistritoDomicilioActionPerformed(evt);
            }
        });

        jLabel37.setText("Local Dom:");

        cbLocalidadDomicilio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLocalidadDomicilioItemStateChanged(evt);
            }
        });

        jLabel14.setText("Condición:");

        cbCondicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "HABIL", "HONORARIO", "VITALICIO", "INHABILITADO", "FALLECIDO", "LICENCIA", "RETIRADO" }));

        jLabel8.setText("Sexo:");

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MASCULINO", "FEMENINO" }));

        jLabel35.setText("Password:");

        jLabel24.setText("Teléf Domicilio:");

        txtTelefonoDomicilio.setToolTipText("(054-456788)");

        jLabel25.setText("Celular:");

        txtTelefonoCelular.setToolTipText("(054)-957-453322");

        jLabel32.setText("Celular 1:");

        txtTelefonoCelular1.setToolTipText("(054)-958-774566");
        txtTelefonoCelular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoCelular1ActionPerformed(evt);
            }
        });

        txtFechaNac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        txtFechaAfiliacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jLabel41.setText("Estado Civil:");

        cbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "SOLTERO(A)", "CASADO(A)", "CONVIVIENTE", "DIVORCIADO(A) / SEPARADO(A)", "VIUDO(A)" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtApePat, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtFechaAfiliacion, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                    .addComponent(txtFechaNac))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnFecha)
                                    .addComponent(btnFechaA))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefonoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbDepartamentoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbProvinciaDomicilio, 0, 106, Short.MAX_VALUE)
                                    .addComponent(txtTelefonoCelular))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbDistritoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17)
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbLocalidadDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTelefonoCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApeMat, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDireccionDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14)
                    .addComponent(cbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtApePat, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(btnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApeMat, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(btnFechaA)
                    .addComponent(txtFechaAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(cbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDireccionDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbDepartamentoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cbProvinciaDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cbDistritoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(cbLocalidadDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(txtTelefonoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(66, 66, 66))
        );

        jTabbedPane1.addTab("DATOS PERSONALES", jPanel1);

        jLabel19.setText("Direciión Trabajo:");

        txtDireccionTrabajo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionTrabajoKeyPressed(evt);
            }
        });

        jLabel21.setText("Departamento:");

        cbDepartamentoTrabajo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDepartamentoTrabajoItemStateChanged(evt);
            }
        });
        cbDepartamentoTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDepartamentoTrabajoActionPerformed(evt);
            }
        });

        jLabel22.setText("Provincia:");

        cbProvinciaTrabajo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProvinciaTrabajoItemStateChanged(evt);
            }
        });
        cbProvinciaTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProvinciaTrabajoActionPerformed(evt);
            }
        });

        jLabel23.setText("Distrito:");

        cbDistritoTrabajo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDistritoTrabajoItemStateChanged(evt);
            }
        });
        cbDistritoTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDistritoTrabajoActionPerformed(evt);
            }
        });

        jLabel38.setText("Local. Trab:");

        cbLocalidadTrabajo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLocalidadTrabajoItemStateChanged(evt);
            }
        });

        jLabel26.setText("Teléf Trabajo:");

        txtTelefonoTrabajo.setToolTipText("(054)-283860");

        jLabel33.setText("Teléf Trabajo 1:");

        txtTelefonoTrabajo1.setToolTipText("(054)-293890");

        jLabel27.setText("E-mail:");

        jLabel28.setText("E-mai alternativo:");

        jLabel29.setText("Universidad:");

        cbUniversidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 SAN AGUSTIN", "02 CATOLICA", "03 SAN PABLO", "04 ALAS PERUANAS", "99 OTRAS", "-----" }));

        jLabel39.setText("Matríc. Auditor:");

        jLabel40.setText("Está Activo:");

        cbActivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SI", "NO", "--" }));

        jLabel34.setText("Es Certificado:");

        cbCertificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SI", "NO", "--" }));

        jLabel36.setText("N° Cert:");

        jLabel12.setText("Centro de Trabajo:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCentroTrabajo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmailAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbUniversidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMatriculaAuditor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCertificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumeroCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDireccionTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTelefonoTrabajo)
                                    .addComponent(cbDepartamentoTrabajo, 0, 89, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelefonoTrabajo1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbProvinciaTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbDistritoTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbLocalidadTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(158, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtDireccionTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocalidadTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(cbDistritoTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cbProvinciaTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cbDepartamentoTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTelefonoTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(txtTelefonoTrabajo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCentroTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatriculaAuditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(cbActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(cbCertificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(txtNumeroCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DATOS LABORALES", jPanel2);

        jLabel10.setText("Cobrador:");

        cbCobrador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CAJA 01", "CERVANTES TAMAYO HERNAN 03", "MASIEL ERIKA CARPIO CARPIO 05", "GUSTAVO COPARA CUSI 06", "GINO QUIROZ APAZA 07", "HERNAN BELLIDO QUISPE 08", "----------" }));

        jLabel11.setText("Cobra en:");

        cbLugarCobranza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DOMICILIO", "TRABAJO", "NINGUNO", "-----" }));

        jLabel30.setText("Buzón:");

        cbBuzon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DOMICILIO", "TRABAJO", "NINGUNO", "-----" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLugarCobranza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBuzon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(598, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(cbLugarCobranza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBuzon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("COBRADOR", jPanel3);

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("OBSERVACIONES", jPanel4);

        tCertificados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OBJ", "NRO", "DESDE", "HASTA", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tCertificados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tCertificadosKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tCertificados);

        jLabel15.setText("DESDE:");

        txtFechaCertificacionDesde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        btnFecha1.setText("...");
        btnFecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecha1ActionPerformed(evt);
            }
        });

        jLabel20.setText("DESDE:");

        txtFechaCertificacionHasta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        btnFecha2.setText("...");
        btnFecha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecha2ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/add_mini.png"))); // NOI18N
        jButton2.setText("AGREGAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel31.setText("TIPO:");

        cbTipoCertificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PUBLICO", "PRIVADO" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaCertificacionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFecha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaCertificacionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFecha2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoCertificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(btnFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaCertificacionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(jLabel31)
                        .addComponent(cbTipoCertificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaCertificacionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15))
                .addContainerGap(171, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(43, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("CERTIFICACION", jPanel5);

        tDerechoHabiente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OBJ", "NRO", "DNI", "NOMBRES", "PARENTESCO", "F. NAC", "CELULAR", "EMAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tDerechoHabiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDerechoHabienteMouseClicked(evt);
            }
        });
        tDerechoHabiente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tDerechoHabienteKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tDerechoHabiente);

        btnAgregarPariente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/add_mini.png"))); // NOI18N
        btnAgregarPariente.setText("AGREGAR");
        btnAgregarPariente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarParienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnAgregarPariente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnAgregarPariente)
                    .addContainerGap(171, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("DERECHO HABIENTE", jPanel6);

        tAuditorIndependiente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OBJ", "NRO", "F. VIGENCIA", "F. V. DESDE", "F. V. HASTA", "SECTOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tAuditorIndependiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tAuditorIndependienteMouseClicked(evt);
            }
        });
        tAuditorIndependiente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tAuditorIndependienteKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tAuditorIndependiente);

        btnAgregarPariente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/add_mini.png"))); // NOI18N
        btnAgregarPariente1.setText("AGREGAR");
        btnAgregarPariente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPariente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarPariente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarPariente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("AUDITOR INDEPENDIENTE", jPanel7);

        btnAgregarPariente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/add_mini.png"))); // NOI18N
        btnAgregarPariente2.setText("AGREGAR");
        btnAgregarPariente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPariente2ActionPerformed(evt);
            }
        });

        tComitePerito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OBJ", "NRO", "F. DESDE", "F. HASTA", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tComitePerito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tComitePeritoMouseClicked(evt);
            }
        });
        tComitePerito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tComitePeritoKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tComitePerito);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarPariente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarPariente2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("C. PERITO", jPanel8);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/Guardar2.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton3.setText("Exportar D. Habiente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Búsqueda:");

        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/adicionar.png"))); // NOI18N
        btnGuardar1.setText("OBTENER FICHA");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbContador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIngresarContador, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnGuardar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnIngresarContador)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbContador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarContador() {
        txtDNI.setEditable(false);
        txtCodigo.setEditable(false);
        txtPassword.setEditable(false);
        cbCondicion.setEnabled(false);
        txtApePat.setEditable(false);
        txtFechaNac.setEditable(false);
        cbSexo.setEnabled(false);
        txtApeMat.setEditable(false);
        txtFechaAfiliacion.setEditable(false);
        txtTelefonoDomicilio.setEditable(false);
        txtNombres.setEditable(false);
        txtCentroTrabajo.setEditable(false);
        txtMatriculaAuditor.setEditable(false);
        cbActivo.setEnabled(false);
        txtTelefonoCelular.setEditable(false);
        txtDireccionDomicilio.setEditable(false);
        cbCertificacion.setEnabled(false);
        txtNumeroCertificado.setEditable(false);
        txtTelefonoCelular1.setEditable(false);
        cbDepartamentoDomicilio.setEnabled(false);
        cbProvinciaDomicilio.setEnabled(false);
        cbDistritoDomicilio.setEnabled(false);
        cbLocalidadDomicilio.setEnabled(false);
        txtTelefonoTrabajo.setEditable(false);
        txtDireccionTrabajo.setEditable(false);
        txtTelefonoTrabajo1.setEditable(false);
        cbDepartamentoTrabajo.setEnabled(false);
        cbProvinciaTrabajo.setEnabled(false);
        cbDistritoTrabajo.setEnabled(false);
        cbLocalidadTrabajo.setEnabled(false);
        txtEmail.setEditable(false);
        txtEmailAlternativo.setEditable(false);
        cbUniversidad.setEnabled(false);
        cbBuzon.setEnabled(false);
        cbCobrador.setEnabled(false);
        cbLugarCobranza.setEnabled(false);
        txtObservaciones.setEditable(false);

        btnFecha.setEnabled(false);
        btnFechaA.setEnabled(false);

        int tipoBusqueda = 0;
        if (cbFiltro.getSelectedItem() == "POR APELLIDOS") {
            tipoBusqueda = 1;
        } else if (cbFiltro.getSelectedItem() == "POR CODIGO") {
            tipoBusqueda = 2;
        } else if (cbFiltro.getSelectedItem() == "POR DNI") {
            tipoBusqueda = 3;
        } else if (cbFiltro.getSelectedItem() == "POR NOMBRES") {
            tipoBusqueda = 4;
        } else if (cbFiltro.getSelectedItem() == "POR APELLIDO PATERNO") {
            tipoBusqueda = 5;
        } else if (cbFiltro.getSelectedItem() == "POR APELLIDO MATERNO") {
            tipoBusqueda = 6;
        }

        ContadorBO c = ContadorBO.getInstance();
        List<Cliente> l = c.BuscarContador(txtBusqueda.getText(), tipoBusqueda);
        DefaultTableModel modelo = (DefaultTableModel) tContador.getModel();
        modelo = (DefaultTableModel) tContador.getModel();
        modelo.setRowCount(l.size());
        int contador = 0;
        for (Cliente cli : l) {
            modelo.setNumRows(contador);
            modelo.addRow(new Object[contador]);
            tContador.setValueAt(cli.getIdCliente(), contador, 0);
            tContador.setValueAt(contador + 1, contador, 1);
            tContador.setValueAt(cli.getCcodigoCole(), contador, 2);
            tContador.setValueAt(cli.getPnroDocumento(), contador, 3);
            tContador.setValueAt(cli.getPapePat(), contador, 4);
            tContador.setValueAt(cli.getPapeMat(), contador, 5);
            tContador.setValueAt(cli.getPnombre(), contador, 6);
            tContador.setValueAt(cli.getPfechaNac(), contador, 7);
            tContador.setValueAt(cli.getPsexo(), contador, 8);
            contador++;
        }
    }

    //implementar
    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if (evt.getKeyCode() == 10) {
            this.buscarContador();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Thread queryThread = new Thread() {
            public void run() {
                pbContador.setIndeterminate(true);
                pbContador.setStringPainted(true);
                pbContador.setString("BUSCANDO CONTADOR, POR FAVOR ESPERE");
                pbContador.setValue(100);
                pbContador.repaint();
                buscarContador();
                pbContador.setString("BUSQUEDA FINALIZADA");
                pbContador.setStringPainted(false);
                pbContador.setIndeterminate(false);
            }
        };
        queryThread.start();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnIngresarContadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarContadorActionPerformed
        btnIngresarContador.setEnabled(true);
        btnGuardar.setEnabled(true);
        txtDNI.setEditable(true);
        txtCodigo.setEditable(true);
        txtPassword.setEditable(true);
        cbCondicion.setEnabled(true);
        txtApePat.setEditable(true);
        txtFechaNac.setEditable(true);
        cbSexo.setEnabled(true);
        txtApeMat.setEditable(true);
        txtFechaAfiliacion.setEditable(true);
        txtTelefonoDomicilio.setEditable(true);
        txtNombres.setEditable(true);
        txtCentroTrabajo.setEditable(true);
        txtMatriculaAuditor.setEditable(true);
        cbActivo.setEnabled(true);
        cbCertificacion.setEnabled(true);
        txtNumeroCertificado.setEditable(true);
        txtTelefonoCelular.setEditable(true);
        txtDireccionDomicilio.setEditable(true);
        txtTelefonoCelular1.setEditable(true);
        cbDepartamentoDomicilio.setEnabled(true);
        cbDepartamentoDomicilio.setSelectedIndex(cbDepartamentoDomicilio.getItemCount() - 1);
        cbProvinciaDomicilio.setEnabled(true);
        cbProvinciaDomicilio.setSelectedIndex(0);
        cbDistritoDomicilio.setEnabled(true);
        cbDistritoDomicilio.setSelectedIndex(0);
        cbDepartamentoTrabajo.setEnabled(true);
        cbDepartamentoTrabajo.setSelectedIndex(cbDepartamentoTrabajo.getItemCount() - 1);
        cbProvinciaTrabajo.setEnabled(true);
        cbProvinciaTrabajo.setSelectedIndex(0);
        cbDistritoTrabajo.setEnabled(true);
        cbDistritoTrabajo.setSelectedIndex(0);
        txtTelefonoTrabajo.setEditable(true);
        txtDireccionTrabajo.setEditable(true);
        txtTelefonoTrabajo1.setEditable(true);
        txtEmail.setEditable(true);
        txtEmailAlternativo.setEditable(true);
        cbUniversidad.setEnabled(true);
        cbBuzon.setEnabled(true);
        cbCobrador.setEnabled(true);
        cbLugarCobranza.setEnabled(true);
        txtObservaciones.setEditable(true);
        txtDNI.setFocusable(true);
        txtDNI.requestFocus();
        btnFecha.setEnabled(true);
        btnFechaA.setEnabled(true);
        txtDNI.setText("");
        txtCodigo.setText("");
        txtPassword.setText("");
        txtApePat.setText("");
        txtFechaNac.setText("1900-01-01");
        txtApeMat.setText("");
        txtFechaAfiliacion.setText("1900-01-01");
        txtTelefonoDomicilio.setText("");
        txtNombres.setText("");
        txtCentroTrabajo.setText("");
        txtMatriculaAuditor.setText("");
        txtTelefonoCelular.setText("");
        txtDireccionDomicilio.setText("");
        txtNumeroCertificado.setText("");
        txtTelefonoCelular1.setText("");
        txtTelefonoTrabajo.setText("");
        txtDireccionTrabajo.setText("");
        txtTelefonoTrabajo1.setText("");
        txtEmail.setText("");
        txtEmailAlternativo.setText("");
        txtObservaciones.setText("");
        this.cliActual = new Cliente();
        if (this.accesoGuardar) {
            btnGuardar.setEnabled(true);
        } else {
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_btnIngresarContadorActionPerformed

    private static boolean esFechaValida(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


    private void tContadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tContadorMouseClicked
        if (evt.getClickCount() == 2) {
            btnGuardar.setEnabled(false);

            int fila = tContador.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tContador.getModel();

            String idCliente = String.valueOf(model.getValueAt(fila, 0));
            btnGuardar.setEnabled(true);

            SeguridadBO sBO = SeguridadBO.getINSTANCE();
            Cliente cli = (Cliente) sBO.CargarObjeto("Cliente", Integer.valueOf(idCliente));

            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            f.AbrirFormularioContadorDetalle(cli, null);

//            cliActual = cli;
//
//            if (cliActual.getCcodigoCole() == null) {
//                txtCodigo.setText("");
//            }
//
//            if (cliActual.getPnroDocumento() == null) {
//                txtDNI.setText("");
//            }
//
//            //cb de Condición
//            String condicion = cliActual.getEstado();
//            if (condicion.equals("H")) {
//                cbCondicion.setSelectedItem("HABIL");
//            } else if (condicion.equals("O")) {
//                cbCondicion.setSelectedItem("HONORARIO");
//            } else if (condicion.equals("V")) {
//                cbCondicion.setSelectedItem("VITALICIO");
//            } else if (condicion.equals("I")) {
//                cbCondicion.setSelectedItem("INHABILITADO");
//            } else if (condicion.equals("F")) {
//                cbCondicion.setSelectedItem("FALLECIDO");
//            } else if (condicion.equals("L")) {
//                cbCondicion.setSelectedItem("LICENCIA");
//            } else {
//                cbCondicion.setSelectedItem(("RETIRADO"));
//            }
//
//            //cb de sexo
//            String sexo = cliActual.getPsexo(); //capturando el valor del campo sexo en la BD
//            if (sexo.equals("M")) //sexo == "M"
//            {
//                cbSexo.setSelectedItem("MASCULINO"); //equals("MASCULINO");//getSelectedItem() == "MASCULINO";
//            } else {
//                cbSexo.setSelectedItem("FEMENINO");
//            }
//
//            //cb de Si es Activo o No
//            String esActivo = cliActual.getCestaActivo();
//
//            if (cliActual.getCestaActivo() == null) {
//                cbActivo.setSelectedItem("--");
//            } else if (esActivo.equals("S")) {
//                cbActivo.setSelectedItem("SI");
//            } else {
//                cbActivo.setSelectedItem("NO");
//            }
//
//            //cb de SI es o NO Certificado
//            String esCertificado = cliActual.getCesCertificado();
//
//            if (cliActual.getCesCertificado() == null) {
//                cbCertificacion.setSelectedItem("--");
//            } else if (esCertificado.equals("S")) {
//                cbCertificacion.setSelectedItem("SI");
//            } else {
//                cbCertificacion.setSelectedItem("NO");
//            }
//
//            //cb de Universidades
//            String universidad = cliActual.getCuniversidad();
//
//            if (cliActual.getCuniversidad() == null) {
//                cbUniversidad.setSelectedItem("-----");
//            } else if (universidad.equals("01")) {
//                cbUniversidad.setSelectedItem("01 SAN AGUSTIN");
//            } else if (universidad.equals("02")) {
//                cbUniversidad.setSelectedItem("02 CATOLICA");
//            } else if (universidad.equals("03")) {
//                cbUniversidad.setSelectedItem("03 SAN PABLO");
//            } else if (universidad.equals("04")) {
//                cbUniversidad.setSelectedItem("04 ALAS PERUANAS");
//            } else {
//                cbUniversidad.setSelectedItem("99 OTRAS");
//            }
//
//            //cb de Cobradores
//            String cobradores = cliActual.getCobcodigoCobrador();
//
//            if (cliActual.getCobcodigoCobrador() == null) {
//                cbCobrador.setSelectedItem("----------");
//            } else if (cobradores.equals("01")) {
//                cbCobrador.setSelectedItem("CAJA 01");
//            } else if (cobradores.equals("03")) {
//                cbCobrador.setSelectedItem("CERVANTES TAMAYO HERNAN 03");
//            } else if (cobradores.equals("05")) {
//                cbCobrador.setSelectedItem("MASIEL ERIKA CARPIO CARPIO 05");
//            } else if (cobradores.equals("06")) {
//                cbCobrador.setSelectedItem("GUSTAVO COPARA CUSI 06");
//            } else if (cobradores.equals("07")) {
//                cbCobrador.setSelectedItem("GINO QUIROZ APAZA 07");
//            } else {
//                cbCobrador.setSelectedItem("HERNAN BELLIDO QUISPE 08");
//            }
//
////            cb Lugar de cobranza
//            String lugarCobranza = cliActual.getClugarCobranza();
//
//            if (cliActual.getClugarCobranza() == null) {
//                cbLugarCobranza.setSelectedItem("-----");
//            } else if (lugarCobranza.equals("D")) {
//                cbLugarCobranza.setSelectedItem("DOMICILIO");
//            } else if (lugarCobranza.equals("T")) {
//                cbLugarCobranza.setSelectedItem("TRABAJO");
//            } else {
//                cbLugarCobranza.setSelectedItem("NINGUNO");
//            }
//
//            //cb de Buzón
//            String buzon = cliActual.getCbuzon();
//
//            if (cliActual.getCbuzon() == null) {
//                cbBuzon.setSelectedItem("-----");
//            } else if (buzon.equals("D")) {
//                cbBuzon.setSelectedItem("DOMICILIO");
//            } else if (buzon.equals("T")) {
//                cbBuzon.setSelectedItem("TRABAJO");
//            } else {
//                cbBuzon.setSelectedItem("NINGUNO");
//            }
//            //cb de ubigeo departamento domicilio   -- otra opción un for del ubigeo departamento y capturar el nombre
//            if (cliActual.getCdepDomicilio() == null) {
//                cbDepartamentoDomicilio.setSelectedIndex(cbDepartamentoDomicilio.getItemCount() - 1);  //removeAll();
//            } else {
//                for (int i = 0; i < cbDepartamentoDomicilio.getItemCount(); i++) {
//                    UbigeoDepartamento dep = (UbigeoDepartamento) cbDepartamentoDomicilio.getItemAt(i);
//                    if (dep.getIdDepartamento() == cliActual.getCdepDomicilio()) {
//                        cbDepartamentoDomicilio.setSelectedItem(dep);
//                    }
//                }
//            }
//
//            //cb de ubigeo departamento trabajo
//            if (cliActual.getCdepTrabajo() == null) {
////                cbDepartamentoTrabajo.removeAll();
//                cbDepartamentoTrabajo.setSelectedIndex(cbDepartamentoTrabajo.getItemCount() - 1);
//            } else {
//                for (int i = 0; i < cbDepartamentoTrabajo.getItemCount(); i++) {
//                    UbigeoDepartamento dep = (UbigeoDepartamento) cbDepartamentoTrabajo.getItemAt(i);
//                    if (dep.getIdDepartamento() == cliActual.getCdepTrabajo()) {
//                        cbDepartamentoTrabajo.setSelectedItem(dep);
//                    }
//                }
//            }
//
//            //cb de ubigeo provincia domicilio mediante un for para hallar la posición del idProvincia, antes los null
//            if (cliActual.getCproDomicilio() == null) {
////                cbProvinciaDomicilio.removeAll();
//                cbProvinciaDomicilio.setSelectedIndex(0);
//            } else {
//                for (int i = 0; i < cbProvinciaDomicilio.getItemCount(); i++) {
//                    UbigeoProvincia pro = (UbigeoProvincia) cbProvinciaDomicilio.getItemAt(i);
//                    if (pro.getIdProvincia() == cliActual.getCproDomicilio()) {
//                        cbProvinciaDomicilio.setSelectedItem(pro);
//                    }
//                }
//            }
//            if (cliActual.getCproTrabajo() == null) {
//                cbProvinciaTrabajo.setSelectedIndex(0);    //removeAll();
//            } else {
//                for (int i = 0; i < cbProvinciaTrabajo.getItemCount(); i++) {
//                    UbigeoProvincia pro = (UbigeoProvincia) cbProvinciaTrabajo.getItemAt(i);
//                    if (pro.getIdProvincia() == cliActual.getCproTrabajo()) {
//                        cbProvinciaTrabajo.setSelectedItem(pro);
//                    }
//                }
//            }
//            if (cliActual.getCdisDomicilio() == null) {
//                cbDistritoDomicilio.setSelectedIndex(0);                //removeAll();
//            } else {
//                for (int i = 0; i < cbDistritoDomicilio.getItemCount(); i++) {
//                    UbigeoDistrito dis = (UbigeoDistrito) cbDistritoDomicilio.getItemAt(i);
//                    if (dis.getIdDistrito() == cliActual.getCdisDomicilio()) {
//                        cbDistritoDomicilio.setSelectedItem(dis);
//                    }
//                }
//            }
//            if (cliActual.getCdisTrabajo() == null) {
//                cbDistritoTrabajo.setSelectedIndex(0);          //removeAll();
//            } else {
//                for (int i = 0; i < cbDistritoTrabajo.getItemCount(); i++) {
//                    UbigeoDistrito dis = (UbigeoDistrito) cbDistritoTrabajo.getItemAt(i);
//                    if (dis.getIdDistrito() == cliActual.getCdisTrabajo()) {
//                        cbDistritoTrabajo.setSelectedItem(dis);
//                    }
//                }
//            }
//            if (cliActual.getClocaDomicilio() == null) {
////                cbLocalidadDomicilio.setSelectedIndex(0);          //removeAll();
//            } else {
//                for (int i = 0; i < cbLocalidadDomicilio.getItemCount(); i++) {
//                    UbigeoLocalidad loc = (UbigeoLocalidad) cbLocalidadDomicilio.getItemAt(i);
//                    if (loc.getIdLocalidad() == cliActual.getClocaDomicilio()) {
//                        cbLocalidadDomicilio.setSelectedItem(loc);
//                    }
//                }
//            }
//            if (cliActual.getClocaTrabajo() == null) {
////                cbLocalidadTrabajo.setSelectedIndex(0);          //removeAll();
//            } else {
//                for (int i = 0; i < cbLocalidadTrabajo.getItemCount(); i++) {
//                    UbigeoLocalidad loc = (UbigeoLocalidad) cbLocalidadTrabajo.getItemAt(i);
//                    if (loc.getIdLocalidad() == cliActual.getClocaTrabajo()) {
//                        cbLocalidadTrabajo.setSelectedItem(loc);
//                    }
//                }
//            }
//            ClienteBO cBO = ClienteBO.getInstance();
//            List<ClienteCertificado> l = cBO.ObtenerClienteCertificado_SegunCliente(cliActual.getIdCliente());
//            DefaultTableModel modelo = (DefaultTableModel) tCertificados.getModel();
//            modelo.setRowCount(0);
//            int contador = 0;
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            for (ClienteCertificado cc : l) {
//                modelo.setNumRows(contador);
//                modelo.addRow(new Object[contador]);
//                tCertificados.setValueAt(cc, contador, 0);
//                tCertificados.setValueAt(contador + 1, contador, 1);
//                tCertificados.setValueAt(format.format(cc.getDesde()), contador, 2);
//                tCertificados.setValueAt(format.format(cc.getHasta()), contador, 3);
//                tCertificados.setValueAt(cc.getTipo(), contador, 4);
//                contador++;
//            }
//            List<ClienteDerechoHabiente> ldh = cBO.ObtenerClienteDerechoHabiente_SegunCliente(cliActual.getIdCliente());
//            modelo = (DefaultTableModel) tDerechoHabiente.getModel();
//            modelo.setRowCount(0);
//            contador = 0;
//            for (ClienteDerechoHabiente cc : ldh) {
//                modelo.setNumRows(contador);
//                modelo.addRow(new Object[contador]);
//                tDerechoHabiente.setValueAt(cc, contador, 0);
//                tDerechoHabiente.setValueAt(contador + 1, contador, 1);
//                tDerechoHabiente.setValueAt(cc.getDni(), contador, 2);
//                tDerechoHabiente.setValueAt(cc.getApePat() + " " + cc.getApeMat() + " " + cc.getNombre(), contador, 3);
//                tDerechoHabiente.setValueAt(cc.getParentesco(), contador, 4);
//                tDerechoHabiente.setValueAt(cc.getFechaNacimiento() != null ? format.format(cc.getFechaNacimiento()) : "1900-01-01", contador, 5);
//                tDerechoHabiente.setValueAt(cc.getCelular(), contador, 6);
//                tDerechoHabiente.setValueAt(cc.getEmail(), contador, 7);
//                contador++;
//            }
//            List<ClienteAuditorIndependiente> lai = cBO.ObtenerClienteAuditorIndependiente_SegunCliente(cliActual.getIdCliente());
//            modelo = (DefaultTableModel) tAuditorIndependiente.getModel();
//            modelo.setRowCount(0);
//            contador = 0;
//            for (ClienteAuditorIndependiente cc : lai) {
//                modelo.setNumRows(contador);
//                modelo.addRow(new Object[contador]);
//                tAuditorIndependiente.setValueAt(cc, contador, 0);
//                tAuditorIndependiente.setValueAt(contador + 1, contador, 1);
//                tAuditorIndependiente.setValueAt(cc.getFechaAfiliacion() != null ? format.format(cc.getFechaAfiliacion()) : "1900-01-01", contador, 2);
//                tAuditorIndependiente.setValueAt(cc.getFechaVigenciaDesde() != null ? format.format(cc.getFechaVigenciaDesde()) : "1900-01-01", contador, 3);
//                tAuditorIndependiente.setValueAt(cc.getFechaVigenciaHasta() != null ? format.format(cc.getFechaVigenciaHasta()) : "1900-01-01", contador, 4);
//                String sector = "-";
//                if (!Objects.isNull(cc.getSector())) {
//                    if (cc.getSector().equals("P")) {
//                        sector = "Privado";
//                    } else {
//                        if (cc.getSector().equals("G")) {
//                            sector = "Gubernamental";
//                        }
//                    }
//                }
//                tAuditorIndependiente.setValueAt(sector, contador, 5);
//                contador++;
//            }
//            List<ClienteComitePerito> lcp = cBO.ObtenerClienteComitePerito_SegunCliente(cliActual.getIdCliente());
//            modelo = (DefaultTableModel) tComitePerito.getModel();
//            modelo.setRowCount(0);
//            contador = 0;
//            for (ClienteComitePerito cc : lcp) {
//                modelo.setNumRows(contador);
//                modelo.addRow(new Object[contador]);
//                tComitePerito.setValueAt(cc, contador, 0);
//                tComitePerito.setValueAt(contador + 1, contador, 1);
//                tComitePerito.setValueAt(cc.getDesde() != null ? format.format(cc.getDesde()) : "1900-01-01", contador, 2);
//                tComitePerito.setValueAt(cc.getHasta() != null ? format.format(cc.getHasta()) : "1900-01-01", contador, 3);
//                String tipo = "-";
//                if (!Objects.isNull(cc.getTipo())) {
//                    if (cc.getTipo().equals("J")) {
//                        tipo = "REPEJ";
//                    } else {
//                        if (cc.getTipo().equals("F")) {
//                            tipo = "REPEF";
//                        }
//                    }
//                }
//                tComitePerito.setValueAt(tipo, contador, 4);
//                contador++;
//            }
//            txtDNI.setText(cliActual.getPnroDocumento());
//            txtCodigo.setText(cliActual.getCcodigoCole());
//            txtPassword.setText(cliActual.getCpasswd());
//            txtApePat.setText(cliActual.getPapePat());
//            txtApeMat.setText(cliActual.getPapeMat());
//            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
//            if (cliActual.getPfechaNac() != null) {
//                txtFechaNac.setText(formatoDeFecha.format(cliActual.getPfechaNac()));
//            }
//            if (cliActual.getCfechaAfiliacion() != null) {
//                txtFechaAfiliacion.setText(formatoDeFecha.format(cliActual.getCfechaAfiliacion()));
//            }
//            txtNombres.setText(cliActual.getPnombre());
//            txtCentroTrabajo.setText(cliActual.getCcentroTrabajo());
//            txtMatriculaAuditor.setText(cliActual.getCmatAuditor());
//            txtNumeroCertificado.setText(cliActual.getCnroCertificacion());
//            txtTelefonoDomicilio.setText(cliActual.getPtelefonoDomicilio());
//            txtTelefonoCelular.setText(cliActual.getPtelefonoCelular());
//            txtTelefonoCelular1.setText(cliActual.getPtelefonoCelular1());
//            txtTelefonoTrabajo.setText(cliActual.getPtelefonoTrabajo());
//            txtTelefonoTrabajo1.setText(cliActual.getPtelefonoTrabajo1());
//            txtDireccionDomicilio.setText(cliActual.getPdireccionDomicilio());
//            txtDireccionTrabajo.setText(cliActual.getPdireccionTrabajo());
//            txtEmail.setText(cliActual.getCemail());
//            txtEmailAlternativo.setText(cliActual.getCemailAlternativo());
//            //txtCobrador.setText(cliActual.getc);
//            txtObservaciones.setText(cliActual.getCobservacion());
//            if (cliActual.getEstadoCivil() != null) {
//                cbEstadoCivil.setSelectedItem(cliActual.getEstadoCivil());
//            } else {
//                cbEstadoCivil.setSelectedItem("-");
//            }
//            txtDNI.setEditable(true);
//            txtCodigo.setEditable(true);
//            txtPassword.setEditable(true);
//            cbCondicion.setEnabled(true);
//            txtApePat.setEditable(true);
//            txtFechaNac.setEditable(true);
//            cbSexo.setEnabled(true);
//            txtApeMat.setEditable(true);
//            txtFechaAfiliacion.setEditable(true);
//            txtNombres.setEditable(true);
//            txtCentroTrabajo.setEditable(true);
//            txtMatriculaAuditor.setEditable(true);
//            cbActivo.setEnabled(true);
//            cbCertificacion.setEnabled(true);
//            txtNumeroCertificado.setEditable(true);
//            txtTelefonoDomicilio.setEditable(true);
//            txtTelefonoCelular.setEditable(true);
//            txtDireccionDomicilio.setEditable(true);
//            txtTelefonoCelular1.setEditable(true);
//            cbDepartamentoDomicilio.setEnabled(true);
//            cbProvinciaDomicilio.setEnabled(true);
//            cbDistritoDomicilio.setEnabled(true);
//            txtTelefonoTrabajo.setEditable(true);
//            txtDireccionTrabajo.setEditable(true);
//            txtTelefonoTrabajo1.setEditable(true);
//            cbDepartamentoTrabajo.setEnabled(true);
//            cbProvinciaTrabajo.setEnabled(true);
//            cbDistritoTrabajo.setEnabled(true);
//            txtEmail.setEditable(true);
//            txtEmailAlternativo.setEditable(true);
//            cbUniversidad.setEnabled(true);
//            cbBuzon.setEnabled(true);
//            cbCobrador.setEnabled(true);
//            cbLugarCobranza.setEnabled(true);
//            txtObservaciones.setEditable(true);
//            btnFecha.setEnabled(true);
//            btnFechaA.setEnabled(true);
//            if (this.accesoGuardar) {
//                btnGuardar.setEnabled(true);
//            } else {
//                btnGuardar.setEnabled(false);
//            }
        }
    }//GEN-LAST:event_tContadorMouseClicked

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    public String RellenarEspaciosDerecha(String p, int cant) {
        int cantidad = p.length();
        while (cantidad < cant) {
            p = p + " ";
            cantidad++;
        }
        return p;
    }

    private void GenerarArchivo() {
        try {
            File carpeta = new File("archivos");
            carpeta.mkdirs();
            carpeta = new File("archivos/PADRON");
            carpeta.mkdirs();
            BufferedWriter bw = null;
            PrintWriter wr = null;
            File f;
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
//            SimpleDateFormat fechaEnvio = new SimpleDateFormat("yyyyMMdd");
            String nombreArchivo = "archivos/PADRON/PADRON" + formato.format(fechaSistema) + ".txt";
            f = new File(nombreArchivo);
            FileWriter w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            ClienteBO cBO = ClienteBO.getInstance();
            List<Cliente> listado = cBO.ObtenerTodosContadores();
//                Calendar calendario = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            for (Cliente c : listado) {
                String rucImformante = "20174327026";
                String tipoDocumento = "01";
                String nroDocIdentidad = this.RellenarEspaciosDerecha(c.getPnroDocumento(), 15);
                String apePat = "";
                if (c.getPapePat() != null) {
                    apePat = this.RellenarEspaciosDerecha(c.getPapePat(), 50);
                } else {
                    apePat = this.RellenarEspaciosDerecha("", 50);
                }
                String apeMat = "";
                if (c.getPapeMat() != null) {
                    apeMat = this.RellenarEspaciosDerecha(c.getPapeMat(), 50);
                } else {
                    apeMat = this.RellenarEspaciosDerecha("", 50);
                }
                String nombre = "";
                if (c.getPnombre() != null) {
                    nombre = this.RellenarEspaciosDerecha(c.getPnombre(), 80);
                } else {
                    nombre = this.RellenarEspaciosDerecha("", 80);
                }
                String estado = c.getEstado();
                if (c.getEstado().equals("H")) {
                    estado = "HABIL";
                } else {
                    if (c.getEstado().equals("I")) {
                        estado = "INHABIL";
                    } else {
                        if (c.getEstado().equals("V")) {
                            estado = "HABIL";
                        } else {
                            if (c.getEstado().equals("O")) {
                                estado = "HABIL";
                            } else {
                                if (c.getEstado().equals("L")) {
                                    estado = "LICENCIA";
                                } else {
                                    if (c.getEstado().equals("F")) {
                                        estado = "FALLECIDO";
                                    } else {
                                        if (c.getEstado().equals("R")) {
                                            estado = "RETIRADO";
                                        } else {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                estado = this.RellenarEspaciosDerecha(estado, 15);
                String nroColegiado = this.RellenarEspaciosDerecha(c.getCcodigoCole(), 50);
                String fechaColegiado = "";
                if (c.getCfechaAfiliacion() != null) {
                    fechaColegiado = format.format(c.getCfechaAfiliacion());
                }
                String detalle = rucImformante + tipoDocumento + nroDocIdentidad + apePat + apeMat + nombre + estado + nroColegiado + fechaColegiado;
                wr.write(detalle + "\r\n");
//                if (c.getPnroDocumento().equals("45008182")) {
//                    boolean a = true;
//                }
//                System.out.println(detalle);
            }
            wr.close();
            bw.close();
            try {
                File objetofile = new File(nombreArchivo);
                Desktop.getDesktop().open(objetofile);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (IOException e) {
        };
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        Thread queryThread = new Thread() {
            public void run() {
                frmCargando fCargando = new frmCargando();
                fPrin.AgregarFormulario(fCargando);
                int x = (fPrin.getWidth() - fCargando.getWidth()) / 2;
                int y = (fPrin.getHeight() - fCargando.getHeight()) / 2;
                fCargando.setLocation(x, y);
                fCargando.setVisible(true);
                fCargando.toFront();
                GenerarArchivo();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDNIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtDNI.getText().isEmpty()) {
                txtCodigo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR EL DNI",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtDNIKeyPressed

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c) == false) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtApePatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePatKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtApePat.getText().isEmpty()) {
                txtFechaNac.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR EL APELLDIO PATERNO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtApePatKeyPressed

    private void txtApeMatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMatKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtApeMat.getText().isEmpty()) {
                txtFechaAfiliacion.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR EL APELLIDO MATERNO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtApeMatKeyPressed

    private void txtNombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtNombres.getText().isEmpty()) {
                txtDireccionDomicilio.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR EL NOMBRE",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtNombresKeyPressed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtCodigo.getText().isEmpty()) {
                txtApePat.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR EL CODIGO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c) == false) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        DatePicker dtp = new DatePicker(this);
        txtFechaNac.setText(dtp.setPickedDate());
    }//GEN-LAST:event_btnFechaActionPerformed

    private void btnFechaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaAActionPerformed
        DatePicker dtp = new DatePicker(this);
        txtFechaAfiliacion.setText(dtp.setPickedDate());
    }//GEN-LAST:event_btnFechaAActionPerformed

    private void txtDireccionDomicilioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionDomicilioKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtDireccionDomicilio.getText().isEmpty()) {
                txtDireccionTrabajo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR LA DIRECCIÓN DE DOMICILIO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtDireccionDomicilioKeyPressed

    private void cbDepartamentoDomicilioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartamentoDomicilioItemStateChanged

    }//GEN-LAST:event_cbDepartamentoDomicilioItemStateChanged

    private void cbDepartamentoDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDepartamentoDomicilioActionPerformed
        ContadorBO cBO = ContadorBO.getInstance();
        if (cbDepartamentoDomicilio.getSelectedIndex() >= -1) {
            cbProvinciaDomicilio.removeAllItems();
            List<UbigeoProvincia> list = cBO.ObtenerContadorUbigeoProvincia(((UbigeoDepartamento) cbDepartamentoDomicilio.getSelectedItem()).getIdDepartamento());
            for (UbigeoProvincia u : list) {
                cbProvinciaDomicilio.addItem(u);
            }
        }
    }//GEN-LAST:event_cbDepartamentoDomicilioActionPerformed

    private void cbProvinciaDomicilioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProvinciaDomicilioItemStateChanged

    }//GEN-LAST:event_cbProvinciaDomicilioItemStateChanged

    private void cbDistritoDomicilioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDistritoDomicilioItemStateChanged

    }//GEN-LAST:event_cbDistritoDomicilioItemStateChanged

    private void cbDistritoDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDistritoDomicilioActionPerformed
        ContadorBO cBO = ContadorBO.getInstance();
        if (cbDistritoDomicilio.getSelectedIndex() > -1) {
            if (cbDistritoDomicilio.getSelectedItem().toString().equals("AREQUIPA")) {
                cbLocalidadDomicilio.setEnabled(true);
                cbLocalidadDomicilio.removeAllItems();//cbDistritoDomicilio.removeAllItems();  //cbLocalidadDomicilio.removeAllItems();
                List<UbigeoLocalidad> list = cBO.ObtenerContadorUbigeoLocalidad(((UbigeoDistrito) cbDistritoDomicilio.getSelectedItem()).getIdDistrito());
                for (UbigeoLocalidad u : list) {
                    cbLocalidadDomicilio.addItem(u);
                }
            } else {
                cbLocalidadDomicilio.setEnabled(false);
                cbLocalidadDomicilio.removeAllItems();
            }
        }
    }//GEN-LAST:event_cbDistritoDomicilioActionPerformed

    private void cbLocalidadDomicilioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLocalidadDomicilioItemStateChanged
        //        ContadorBO cBO = ContadorBO.getInstance();
        //        int idDistrito = 0;
        ////
        ////        if(cbLocalidadDomicilio.getSelectedIndex() > -1){
        //////            cbLocalidadDomicilio.removeAllItems();
        ////            lUbigeoLocalidad = (List<UbigeoLocalidad>) cBO.ObtenerContadorUbigeoLocalidad(lUbigeoDistrito.
        ////                    get(cbDistritoDomicilio.getSelectedIndex()).getIdDistrito());
        ////
        ////            idLocalidadDomicilio = lUbigeoLocalidad.get(cbLocalidadDomicilio.getSelectedIndex()).getIdLocalidad();
        //         List<UbigeoLocalidad> list = cBO.ObtenerContadorUbigeoLocalidad(idDistrito);
        //
        //
        //         for(UbigeoLocalidad u : list){
        //             cbLocalidadDomicilio.addItem(u.getNombreLocalidad());
        //         }
    }//GEN-LAST:event_cbLocalidadDomicilioItemStateChanged

    private void txtDireccionTrabajoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionTrabajoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtDireccionTrabajo.getText().isEmpty()) {
                btnGuardar.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR LA DIRECCIÓN DE TRABAJO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtDireccionTrabajoKeyPressed

    private void cbDepartamentoTrabajoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartamentoTrabajoItemStateChanged

    }//GEN-LAST:event_cbDepartamentoTrabajoItemStateChanged

    private void cbProvinciaTrabajoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProvinciaTrabajoItemStateChanged

    }//GEN-LAST:event_cbProvinciaTrabajoItemStateChanged

    private void cbProvinciaTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProvinciaTrabajoActionPerformed
        ContadorBO cBO = ContadorBO.getInstance();
        if (cbProvinciaTrabajo.getSelectedIndex() > -1) {
            cbDistritoTrabajo.removeAllItems();
            List<UbigeoDistrito> list = cBO.ObtenerContadorUbigeoDistrito(((UbigeoProvincia) cbProvinciaTrabajo.getSelectedItem()).getIdProvincia());
            for (UbigeoDistrito u : list) {
                cbDistritoTrabajo.addItem(u);
            }
        }
    }//GEN-LAST:event_cbProvinciaTrabajoActionPerformed

    private void cbDistritoTrabajoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDistritoTrabajoItemStateChanged

    }//GEN-LAST:event_cbDistritoTrabajoItemStateChanged

    private void cbLocalidadTrabajoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLocalidadTrabajoItemStateChanged
        //        ContadorBO cBO = ContadorBO.getInstance();
        //        int idDistritoTrabajo = 0;
        ////
        ////        if(cbLocalidadDomicilio.getSelectedIndex() > -1){
        //////            cbLocalidadDomicilio.removeAllItems();
        ////            lUbigeoLocalidad = (List<UbigeoLocalidad>) cBO.ObtenerContadorUbigeoLocalidad(lUbigeoDistrito.
        ////                    get(cbDistritoDomicilio.getSelectedIndex()).getIdDistrito());
        ////
        ////            idLocalidadDomicilio = lUbigeoLocalidad.get(cbLocalidadDomicilio.getSelectedIndex()).getIdLocalidad();
        //         List<UbigeoLocalidad> list = cBO.ObtenerContadorUbigeoLocalidad(idDistritoTrabajo);
        //
        //
        //         for(UbigeoLocalidad u : list){
        //             cbLocalidadTrabajo.addItem(u.getNombreLocalidad());
        //         }
    }//GEN-LAST:event_cbLocalidadTrabajoItemStateChanged

    private void txtTelefonoCelular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoCelular1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoCelular1ActionPerformed

    private boolean validarDerechoHabiente() {
        boolean esValido = true;
        ContadorBO cBO = ContadorBO.getInstance();
        DefaultTableModel model = (DefaultTableModel) tDerechoHabiente.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean esRegistroValido = true;
            ClienteDerechoHabiente cdh = (ClienteDerechoHabiente) model.getValueAt(i, 0);
            List<ClienteDerechoHabiente> l = cBO.obtenerDerechoHabiente(cdh.getDni());
            if (cdh.getId() == 0) {
                if (l.size() >= 1) {
                    esValido = false;
                    esRegistroValido = false;
                    String colegiados = "";
                    for (ClienteDerechoHabiente c : l) {
                        colegiados = c.getCliente().getCcodigoCole() + " - " + c.getCliente().getPapePat() + " " + c.getCliente().getPapeMat() + " " + c.getCliente().getPnombre() + "\n\r";
                    }
                    String nombreDerechoHabiente = cdh.getDni() + " - " + cdh.getApePat() + " " + cdh.getApeMat() + " " + cdh.getNombre();
                    JOptionPane.showMessageDialog(this,
                            "El Derecho Habiente: \n\r" + nombreDerechoHabiente + "\n\r ya fue registrado con otro colegiado\n\r" + colegiados, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String colegiados = "";
                String nombreDerechoHabiente = cdh.getDni() + " - " + cdh.getApePat() + " " + cdh.getApeMat() + " " + cdh.getNombre();
                for (ClienteDerechoHabiente c : l) {
                    if (c.getId() != cdh.getId()) {
                        colegiados = c.getCliente().getCcodigoCole() + " - " + c.getCliente().getPapePat() + " " + c.getCliente().getPapeMat() + " " + c.getCliente().getPnombre() + "\n\r";
                        esValido = false;
                        esRegistroValido = false;
                    }
                }
                if (!esRegistroValido) {
                    JOptionPane.showMessageDialog(this,
                            "El Derecho Habiente: \n\r " + nombreDerechoHabiente + "\n\r ya fue registrado con otro colegiado\n\r" + colegiados, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return esValido;
    }


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!txtDNI.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !txtApePat.getText().isEmpty()
                && !txtFechaNac.getText().isEmpty() && !txtApeMat.getText().isEmpty()
                && !txtFechaAfiliacion.getText().isEmpty() && !txtNombres.getText().isEmpty()) {

            txtDireccionDomicilio.setText(txtDireccionDomicilio.getText().trim());
            if (!cbDepartamentoDomicilio.getSelectedItem().toString().equals("NINGUNO")) {
                if (txtDireccionDomicilio.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this,
                            "DEBE INGRESAR UNA DIRECCION DE DOMICILIO", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (txtDireccionDomicilio.getText().length() > 0) {
                if (cbDepartamentoDomicilio.getSelectedItem().toString().equals("NINGUNO")) {
                    JOptionPane.showMessageDialog(this,
                            "DEBE INGRESAR UN UBIGEO DE DOMICILIO", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            //------------------------------------------------------------------
            txtDireccionTrabajo.setText(txtDireccionTrabajo.getText().trim());
            txtCentroTrabajo.setText(txtCentroTrabajo.getText().trim());
            if (cbDepartamentoTrabajo.getSelectedItem().toString().equals("NINGUNO")) {
                if (txtDireccionTrabajo.getText().length() > 0) {
                    JOptionPane.showMessageDialog(this,
                            "DEBE INGRESAR UN UBIGEO DE TRABAJO", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (txtDireccionTrabajo.getText().length() == 0) {
                if (!cbDepartamentoTrabajo.getSelectedItem().toString().equals("NINGUNO")) {
                    JOptionPane.showMessageDialog(this,
                            "DEBE INGRESAR UNA DIRECCION DE CENTRO DE TRABAJO", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
//            ClienteBO cliBO = ClienteBO.getInstance();
//            List<Cliente> lClientes = cliBO.ObtenerTodosContadores();
            if (txtDNI.getText().trim().length() != 8) {
                JOptionPane.showMessageDialog(this,
                        "El DNI debe tener 8 digitos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtCodigo.getText().length() != 5) {
                JOptionPane.showMessageDialog(this,
                        "El Código del colegiado debe tener 5 dígitos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!this.esFechaValida(txtFechaNac.getText())) {
                JOptionPane.showMessageDialog(this, "La Fecha no es Correcta", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!this.esFechaValida(txtFechaAfiliacion.getText())) {
                JOptionPane.showMessageDialog(this, "La Fecha no es correcta", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (tDerechoHabiente.getRowCount() > 0) {
                if (cbEstadoCivil.getSelectedItem().equals("-")) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar el estado civil del colegiado", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            boolean esValido = this.validarDerechoHabiente();
            if (!esValido) {
                return;
            }

            try {
                Cliente c = this.cliActual;
                c.setPnroDocumento(txtDNI.getText().trim());
                c.setCcodigoCole(txtCodigo.getText().trim());

                //password
                if (!txtPassword.getText().isEmpty()) {
                    c.setCpasswd(txtPassword.getText());
                } else {
                    c.setCpasswd(null);
                }

                //centro de trabajo
                if (!txtCentroTrabajo.getText().isEmpty()) {
                    c.setCcentroTrabajo(txtCentroTrabajo.getText());
                } else {
                    c.setCcentroTrabajo(null);
                }

                //matrícula del auditor
                if (!txtMatriculaAuditor.getText().isEmpty()) {
                    c.setCmatAuditor(txtMatriculaAuditor.getText());
                } else {
                    c.setCmatAuditor(null);
                }

                //Nro de certificado
                if (!txtNumeroCertificado.getText().isEmpty()) {
                    c.setCnroCertificacion(txtNumeroCertificado.getText());
                } else {
                    c.setCnroCertificacion(null);
                }

                //Teléfono domicilio
                if (!txtTelefonoDomicilio.getText().isEmpty()) {
                    c.setPtelefonoDomicilio(txtTelefonoDomicilio.getText());
                } else {
                    c.setPtelefonoDomicilio(null);
                }

                //Teléfono celular
                if (!txtTelefonoCelular.getText().isEmpty()) {
                    c.setPtelefonoCelular(txtTelefonoCelular.getText());
                } else {
                    c.setPtelefonoCelular(null);
                }

                //Teléfono celular 1
                if (!txtTelefonoCelular1.getText().isEmpty()) {
                    c.setPtelefonoCelular1(txtTelefonoCelular1.getText());
                } else {
                    c.setPtelefonoCelular1(null);
                }

                //Teléfono trabajo
                if (!txtTelefonoTrabajo.getText().isEmpty()) {
                    c.setPtelefonoTrabajo(txtTelefonoTrabajo.getText());
                } else {
                    c.setPtelefonoTrabajo(null);
                }

                //Teléfono trabajo 1
                if (!txtTelefonoTrabajo1.getText().isEmpty()) {
                    c.setPtelefonoTrabajo1(txtTelefonoTrabajo1.getText());
                } else {
                    c.setPtelefonoTrabajo1(null);
                }

                //Dirección domicilio
                if (!txtDireccionDomicilio.getText().isEmpty()) {
                    c.setPdireccionDomicilio(txtDireccionDomicilio.getText());
                } else {
                    c.setPdireccionDomicilio(null);
                }

                //Dirección trabajo
                if (!txtDireccionTrabajo.getText().isEmpty()) {
                    c.setPdireccionTrabajo(txtDireccionTrabajo.getText());
                } else {
                    c.setPdireccionTrabajo(null);
                }

                //EMAIL
                if (!txtEmail.getText().isEmpty()) {
                    c.setCemail(txtEmail.getText());
                } else {
                    c.setCemail(null);
                }

                //EMAIL ALTERNATIVO
                if (!txtEmailAlternativo.getText().isEmpty()) {
                    c.setCemailAlternativo(txtEmailAlternativo.getText());
                } else {
                    c.setCemailAlternativo(null);
                }

                //Observaciones
                if (!txtObservaciones.getText().isEmpty()) {
                    c.setCobservacion(txtObservaciones.getText());
                } else {
                    c.setCobservacion(null);
                }

                //Observaciones
                c.setEstadoCivil(cbEstadoCivil.getSelectedItem().toString());
                c.setTipoCliente("C");

                //combo condición del cliente
                if (cbCondicion.getSelectedItem() == "HABIL") {
                    c.setEstado("H");
                } else if (cbCondicion.getSelectedItem() == "HONORARIO") {
                    c.setEstado("O");
                } else if (cbCondicion.getSelectedItem() == "VITALICIO") {
                    c.setEstado("V");
                } else if (cbCondicion.getSelectedItem() == "INHABILITADO") {
                    c.setEstado("I");
                } else if (cbCondicion.getSelectedItem() == "FALLECIDO") {
                    c.setEstado("F");
                } else if (cbCondicion.getSelectedItem() == "LICENCIA") {
                    c.setEstado("L");
                } else {
                    c.setEstado("R");
                }

                c.setPnombre(txtNombres.getText());
                c.setPapeMat(txtApeMat.getText());
                c.setPapePat(txtApePat.getText());
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                c.setPfechaNac(formatoDeFecha.parse(txtFechaNac.getText()));
                c.setCfechaAfiliacion(formatoDeFecha.parse(txtFechaAfiliacion.getText()));
                //combo del campo sexo
                if (cbSexo.getSelectedItem() == "MASCULINO") {
                    c.setPsexo("M");
                } else {
                    c.setPsexo("F");
                }
                //combo si es certificado
                if (cbCertificacion.getSelectedItem().equals("--")) {
                    c.setCesCertificado(null);
                } else if (cbCertificacion.getSelectedItem() == "SI") { //.equals(null)
                    c.setCesCertificado("S");
                } else {
                    c.setCesCertificado("N");
                }
                //cb si está activo
                if (cbActivo.getSelectedItem().equals("--")) {
                    c.setCestaActivo(null);
                } else if (cbActivo.getSelectedItem() == "SI") { //.equals(null)
                    c.setCestaActivo("S");
                } else {
                    c.setCestaActivo("N");
                }
                //cb de Universidad
                if (cbUniversidad.getSelectedItem() == "01 SAN AGUSTIN") {
                    c.setCuniversidad("01");
                } else if (cbUniversidad.getSelectedItem() == "02 CATOLICA") {
                    c.setCuniversidad("02");
                } else if (cbUniversidad.getSelectedItem() == "03 SAN PABLO") {
                    c.setCuniversidad("03");
                } else if (cbUniversidad.getSelectedItem() == "04 ALAS PERUANAS") {
                    c.setCuniversidad("04");
                } else {
                    c.setCuniversidad("99");
                }
                //combo de cobrador
                if (cbCobrador.getSelectedItem().equals("----------")) {
                    c.setCobcodigoCobrador(null);
                } else if (cbCobrador.getSelectedItem() == "CAJA 01") {
                    c.setCobcodigoCobrador("01");
                } else if (cbCobrador.getSelectedItem() == "CERVANTES TAMAYO HERNAN 03") {
                    c.setCobcodigoCobrador("03");
                } else if (cbCobrador.getSelectedItem() == "MASIEL ERIKA CARPIO CARPIO 05") {
                    c.setCobcodigoCobrador("05");
                } else if (cbCobrador.getSelectedItem() == "GUSTAVO COPARA CUSI 06") {
                    c.setCobcodigoCobrador("06");
                } else if (cbCobrador.getSelectedItem() == "GINO QUIROZ APAZA 07") {
                    c.setCobcodigoCobrador("07");
                } else {
                    c.setCobcodigoCobrador("08");
                }
                //lugar de cobranza
                if (cbLugarCobranza.getSelectedItem().equals("-----")) {
                    c.setClugarCobranza(null);
                } else if (cbLugarCobranza.getSelectedItem() == "DOMICILIO") {
                    c.setClugarCobranza("D");
                } else if (cbLugarCobranza.getSelectedItem() == "TRABAJO") {
                    c.setClugarCobranza("T");
                } else {
                    c.setClugarCobranza("N");
                }
                //buzón de entrega del contador
                if (cbBuzon.getSelectedItem().equals("-----")) {
                    c.setCbuzon(null);
                } else if (cbBuzon.getSelectedItem() == "DOMICILIO") {
                    c.setCbuzon("D");
                } else if (cbBuzon.getSelectedItem() == "TRABAJO") {
                    c.setCbuzon("T");
                } else {
                    c.setCbuzon("N");
                }
                c.setCdepDomicilio(((UbigeoDepartamento) cbDepartamentoDomicilio.getSelectedItem()).getIdDepartamento());
//                for (UbigeoDepartamento ud : lUbigeoDepartamento) {
//                    if (cbDepartamentoDomicilio.getSelectedIndex() == -1) {
//                        c.setCdepDomicilio(null);
//                    } else {
//                        int idDepartamento = cbDepartamentoDomicilio.getSelectedIndex();
//                        if (ud.getIdDepartamento() == idDepartamento) {
////                            c.setCdepDomicilio(idDepartamento);
//                            c.setCdepDomicilio(idDepartamento); //(idDepartamento +1)
//                        }
//                    }
//                }
                //cb para guardar Ubigeo Departamento Trabajo
                c.setCdepTrabajo(((UbigeoDepartamento) cbDepartamentoTrabajo.getSelectedItem()).getIdDepartamento());
//                for (UbigeoDepartamento udt : lUbigeoDepartamentoTrabajo) {
//                    if (cbDepartamentoTrabajo.getSelectedIndex() == -1) {
//                        c.setCdepTrabajo(null);
//                    } else {
//                        int idDepartamentoTrabajo = cbDepartamentoTrabajo.getSelectedIndex();
//
//                        if (udt.getIdDepartamento() == idDepartamentoTrabajo) {
////                            c.setCdepTrabajo(idDepartamentoTrabajo);
//                            c.setCdepTrabajo(idDepartamentoTrabajo);     //(idDepartamentoTrabajo +1)
//                        }
//                    }
//                }
                c.setCproDomicilio(((UbigeoProvincia) cbProvinciaDomicilio.getSelectedItem()).getIdProvincia());
                //cb para guardar Ubigeo Provincia Domicilio
//                for (UbigeoProvincia up : lUbigeoProvincias) {
//                    if (cbProvinciaDomicilio.getSelectedIndex() == -1) {
//                        c.setCproDomicilio(null);
//                    } else {
//                        int idProvincia = lUbigeoProvincias.get(cbProvinciaDomicilio.getSelectedIndex())
//                                .getIdProvincia();
//                        //cbProvinciaDomicilio.getSelectedIndex();
//
//                        if (up.getIdProvincia() == idProvincia) {
//                            c.setCproDomicilio(idProvincia + 1);
//                        }
//                    }
//                }
                c.setCproTrabajo(((UbigeoProvincia) cbProvinciaTrabajo.getSelectedItem()).getIdProvincia());
//                //cb para guardar Ubigeo Provincia Trabajo
//                for (UbigeoProvincia upt : lUbigeoProvinciasTrabajo) {
//                    if (cbProvinciaTrabajo.getSelectedIndex() == -1) {
//                        c.setCproTrabajo(null);
//                    } else {
//                        int idProvinciaTrabajo = lUbigeoProvinciasTrabajo.get(cbProvinciaTrabajo.getSelectedIndex())
//                                .getIdProvincia();
//
//                        if (upt.getIdProvincia() == idProvinciaTrabajo) {
//                            c.setCproTrabajo(idProvinciaTrabajo + 1);
//                        }
//                    }
//                }
                c.setCdisDomicilio(((UbigeoDistrito) cbDistritoDomicilio.getSelectedItem()).getIdDistrito());
//                //cb para guardar Ubigeo Distrito Domicilio
//                for (UbigeoDistrito udist : lUbigeoDistrito) {
//                    if (cbDistritoDomicilio.getSelectedIndex() == -1) {
//                        c.setCdisDomicilio(null);
//                    } else {
//                        int idDistrito = lUbigeoDistrito.get(cbDistritoDomicilio.getSelectedIndex())
//                                .getIdDistrito();
//
//                        if (udist.getIdDistrito() == idDistrito) {
//                            c.setCdisDomicilio(idDistrito);
//                        }
//                    }
//                }
                c.setCdisTrabajo(((UbigeoDistrito) cbDistritoTrabajo.getSelectedItem()).getIdDistrito());
//                //cb para guardar Ubigeo Distrito Trabajo
//                for (UbigeoDistrito udistT : lUbigeoDistritoTrabajo) {
//                    if (cbDistritoTrabajo.getSelectedIndex() == -1) {
//                        c.setCdisTrabajo(null);
//                    } else {
//                        int idDistritoTrabajo = lUbigeoDistritoTrabajo.get(cbDistritoTrabajo.getSelectedIndex())
//                                .getIdDistrito();
//
//                        if (udistT.getIdDistrito() == idDistritoTrabajo) {
//                            c.setCdisTrabajo(idDistritoTrabajo);
//                        }
//                    }
//                }
//                ClienteBO cBO = ClienteBO.getInstance();
                if (cbLocalidadDomicilio.getSelectedItem() != null) {
                    c.setClocaDomicilio(((UbigeoLocalidad) cbLocalidadDomicilio.getSelectedItem()).getIdLocalidad());
                }
                if (cbLocalidadTrabajo.getSelectedItem() != null) {
                    c.setClocaTrabajo(((UbigeoLocalidad) cbLocalidadTrabajo.getSelectedItem()).getIdLocalidad());
                }
                ContadorBO cont = ContadorBO.getInstance();
                if (this.cliActual.getIdCliente() == 0) {
                    if (cont.ObtenerContadorSegunDNI(this.cliActual.getPdni()) != null) {
                        JOptionPane.showMessageDialog(this, "El dni ya existe", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (cont.ObtenerContador_SegunCodigoColegiado(this.cliActual.getCcodigoCole()) != null) {
                        JOptionPane.showMessageDialog(this, "El codigo ya existe", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    cont.GuardarContador(c);
                    JOptionPane.showMessageDialog(this, "Se inserto el Contador Correctamente", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (cont.ObtenerContadorSegunDNI_Distinto(this.cliActual.getPdni(), this.cliActual.getIdCliente()) != null) {
                        JOptionPane.showMessageDialog(this, "El dni ya existe", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (cont.ObtenerContador_SegunCodigoColegiado_Distinto(this.cliActual.getCcodigoCole(), this.cliActual.getIdCliente()) != null) {
                        JOptionPane.showMessageDialog(this, "El codigo ya existe", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    cont.ActualizarContador(c);
                    JOptionPane.showMessageDialog(this, "Se actualizo el Contador Correctamente", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                for (int i = 0; i < tCertificados.getRowCount(); i++) {
                    ClienteCertificado cc = (ClienteCertificado) tCertificados.getValueAt(i, 0);
                    if (cc.getId() == 0) {
                        cont.GuardarClienteCertificado(cc);
                    } else {
                        cont.ActualizarClienteCertificado(cc);
                    }
                }
                for (int i = 0; i < tDerechoHabiente.getRowCount(); i++) {
                    ClienteDerechoHabiente cc = (ClienteDerechoHabiente) tDerechoHabiente.getValueAt(i, 0);
                    if (cc.getId() == 0) {
                        cont.GuardarObjeto(cc);
                    } else {
                        cont.ActualizarObjeto(cc);
                    }
                }
                for (int i = 0; i < tAuditorIndependiente.getRowCount(); i++) {
                    ClienteAuditorIndependiente cc = (ClienteAuditorIndependiente) tAuditorIndependiente.getValueAt(i, 0);
                    if (cc.getId() == 0) {
                        cont.GuardarObjeto(cc);
                    } else {
                        cont.ActualizarObjeto(cc);
                    }
                }
                for (int i = 0; i < tComitePerito.getRowCount(); i++) {
                    ClienteComitePerito cc = (ClienteComitePerito) tComitePerito.getValueAt(i, 0);
                    if (cc.getId() == 0) {
                        cont.GuardarObjeto(cc);
                    } else {
                        cont.ActualizarObjeto(cc);
                    }
                }
                txtDNI.setEditable(false);
                txtCodigo.setEditable(false);
                txtPassword.setEditable(false);
                cbCondicion.setEnabled(false);
                txtApePat.setEditable(false);
                txtFechaNac.setEditable(false);
                cbSexo.setEnabled(false);
                txtApeMat.setEditable(false);
                txtFechaAfiliacion.setEditable(false);
                txtTelefonoDomicilio.setEditable(false);
                txtNombres.setEditable(false);
                txtCentroTrabajo.setEditable(false);
                txtMatriculaAuditor.setEditable(false);
                cbActivo.setEnabled(false);
                cbCertificacion.setEnabled(false);
                txtNumeroCertificado.setEditable(false);
                txtTelefonoCelular.setEditable(false);
                txtDireccionDomicilio.setEditable(false);
                txtTelefonoCelular1.setEditable(false);
                cbDepartamentoDomicilio.setEnabled(false);
                cbProvinciaDomicilio.setEnabled(false);
                cbDistritoDomicilio.setEnabled(false);
                txtTelefonoTrabajo.setEditable(false);
                txtDireccionTrabajo.setEditable(false);
                txtTelefonoTrabajo1.setEditable(false);
                cbDepartamentoTrabajo.setEnabled(false);
                cbProvinciaTrabajo.setEnabled(false);
                cbDistritoTrabajo.setEnabled(false);
                txtEmail.setEditable(false);
                txtEmailAlternativo.setEditable(false);
                cbUniversidad.setEnabled(false);
                cbBuzon.setEnabled(false);
                cbCobrador.setEnabled(false);
                cbLugarCobranza.setEnabled(false);
                txtObservaciones.setEditable(false);
                txtDNI.setFocusable(false);
                txtDNI.requestFocus();
                btnFecha.setEnabled(false);
                btnFechaA.setEnabled(false);
                txtDNI.setText("");
                txtCodigo.setText("");
                txtPassword.setText("");
                txtApePat.setText("");
                txtFechaNac.setText("");
                txtApeMat.setText("");
                txtFechaAfiliacion.setText("");
                txtTelefonoDomicilio.setText("");
                txtNombres.setText("");
                txtCentroTrabajo.setText("");
                txtMatriculaAuditor.setText("");
                txtNumeroCertificado.setText("");
                txtTelefonoCelular.setText("");
                txtDireccionDomicilio.setText("");
                txtTelefonoCelular1.setText("");
                txtTelefonoTrabajo.setText("");
                txtDireccionTrabajo.setText("");
                txtTelefonoTrabajo1.setText("");
                txtEmail.setText("");
                txtEmailAlternativo.setText("");
                txtObservaciones.setText("");
                cbEstadoCivil.setSelectedItem("-");
                DefaultTableModel modelo = (DefaultTableModel) tCertificados.getModel();
                modelo.setRowCount(0);
                modelo = (DefaultTableModel) tDerechoHabiente.getModel();
                modelo.setRowCount(0);
                modelo = (DefaultTableModel) tAuditorIndependiente.getModel();
                modelo.setRowCount(0);
                modelo = (DefaultTableModel) tComitePerito.getModel();
                modelo.setRowCount(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Por favor llene todos los Campos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbProvinciaDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProvinciaDomicilioActionPerformed
        ContadorBO cBO = ContadorBO.getInstance();
        if (cbProvinciaDomicilio.getSelectedIndex() > -1) {
            cbDistritoDomicilio.removeAllItems();
            List<UbigeoDistrito> list = cBO.ObtenerContadorUbigeoDistrito(((UbigeoProvincia) cbProvinciaDomicilio.getSelectedItem()).getIdProvincia());
            for (UbigeoDistrito u : list) {
                cbDistritoDomicilio.addItem(u);
            }
        }
    }//GEN-LAST:event_cbProvinciaDomicilioActionPerformed

    private void cbDepartamentoTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDepartamentoTrabajoActionPerformed
        ContadorBO cBO = ContadorBO.getInstance();
        if (cbDepartamentoTrabajo.getSelectedIndex() >= -1) {
            cbProvinciaTrabajo.removeAllItems();
            List<UbigeoProvincia> list = cBO.ObtenerContadorUbigeoProvincia(((UbigeoDepartamento) cbDepartamentoTrabajo.getSelectedItem()).getIdDepartamento());
            for (UbigeoProvincia u : list) {
                cbProvinciaTrabajo.addItem(u);
            }
        }
    }//GEN-LAST:event_cbDepartamentoTrabajoActionPerformed

    private void cbDistritoTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDistritoTrabajoActionPerformed
        ContadorBO cBO = ContadorBO.getInstance();
        if (cbDistritoTrabajo.getSelectedIndex() > -1) {
            if (cbDistritoTrabajo.getSelectedItem().toString().equals("AREQUIPA")) {
                cbLocalidadTrabajo.setEnabled(true);
                cbLocalidadTrabajo.removeAllItems();
                List<UbigeoLocalidad> list = cBO.ObtenerContadorUbigeoLocalidad(((UbigeoDistrito) cbDistritoTrabajo.getSelectedItem()).getIdDistrito());
                for (UbigeoLocalidad u : list) {
                    cbLocalidadTrabajo.addItem(u);
                }
            } else {
                cbLocalidadTrabajo.setEnabled(false);
                cbLocalidadTrabajo.removeAllItems();
            }
        }
    }//GEN-LAST:event_cbDistritoTrabajoActionPerformed

    private void btnFecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecha1ActionPerformed
        DatePicker dtp = new DatePicker(this);
        txtFechaCertificacionDesde.setText(dtp.setPickedDate());
    }//GEN-LAST:event_btnFecha1ActionPerformed

    private void btnFecha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecha2ActionPerformed
        DatePicker dtp = new DatePicker(this);
        txtFechaCertificacionHasta.setText(dtp.setPickedDate());
    }//GEN-LAST:event_btnFecha2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (this.cliActual != null) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                ClienteCertificado cc = new ClienteCertificado();
                cc.setTipo(cbTipoCertificacion.getSelectedItem().toString());
                cc.setDesde(f.parse(txtFechaCertificacionDesde.getText()));
                cc.setHasta(f.parse(txtFechaCertificacionHasta.getText()));
                cc.setCliente(this.cliActual);
                DefaultTableModel modelo = (DefaultTableModel) tCertificados.getModel();
                int contador = tCertificados.getRowCount();
                modelo.setNumRows(contador);
                modelo.addRow(new Object[contador]);
                tCertificados.setValueAt(cc, contador, 0);
                tCertificados.setValueAt(contador + 1, contador, 1);
                tCertificados.setValueAt(f.format(cc.getDesde()), contador, 2);
                tCertificados.setValueAt(f.format(cc.getHasta()), contador, 3);
                tCertificados.setValueAt(cc.getTipo(), contador, 4);
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmContadorBK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tCertificadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCertificadosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿ DESEA ELIMINAR EL CERTIFICADO ?");
            if (opcion == JOptionPane.YES_OPTION) {
                int fila = tCertificados.getSelectedRow();
                if (fila >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tCertificados.getModel();
                    ClienteCertificado cc = (ClienteCertificado) model.getValueAt(fila, 0);
                    if (cc.getId() > 0) {
                        ContadorBO cont = ContadorBO.getInstance();
                        cont.EliminarClienteCertificado(cc);
                    }
                    model.removeRow(fila);
                }
            }
        }
    }//GEN-LAST:event_tCertificadosKeyPressed

    private void tDerechoHabienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tDerechoHabienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿ DESEA ELIMINAR EL DERECHO HABIENTE ?");
            if (opcion == JOptionPane.YES_OPTION) {
                int fila = tDerechoHabiente.getSelectedRow();
                if (fila >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tDerechoHabiente.getModel();
                    ClienteDerechoHabiente cc = (ClienteDerechoHabiente) model.getValueAt(fila, 0);
                    if (cc.getId() > 0) {
                        ContadorBO cont = ContadorBO.getInstance();
                        cont.EliminarClienteDerechoHabiente(cc);
                    }
                    model.removeRow(fila);
                }
            }
        }
    }//GEN-LAST:event_tDerechoHabienteKeyPressed

    public void agregarDerechoHabiente(ClienteDerechoHabiente cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel modelo = (DefaultTableModel) tDerechoHabiente.getModel();
        int contador = tDerechoHabiente.getRowCount();
        modelo.setNumRows(contador);
        modelo.addRow(new Object[contador]);
        tDerechoHabiente.setValueAt(cc, contador, 0);
        tDerechoHabiente.setValueAt(contador + 1, contador, 1);
        tDerechoHabiente.setValueAt(cc.getDni(), contador, 2);
        tDerechoHabiente.setValueAt(cc.getApePat() + " " + cc.getApeMat() + " " + cc.getNombre(), contador, 3);
        tDerechoHabiente.setValueAt(cc.getParentesco(), contador, 4);
        tDerechoHabiente.setValueAt(format.format(cc.getFechaNacimiento()), contador, 5);
        tDerechoHabiente.setValueAt(cc.getCelular(), contador, 6);
        tDerechoHabiente.setValueAt(cc.getEmail(), contador, 7);
    }

    public void actualizarDerechoHabiente(int fila, ClienteDerechoHabiente cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tDerechoHabiente.setValueAt(cc.getDni(), fila, 2);
        tDerechoHabiente.setValueAt(cc.getApePat() + " " + cc.getApeMat() + " " + cc.getNombre(), fila, 3);
        tDerechoHabiente.setValueAt(cc.getParentesco(), fila, 4);
        tDerechoHabiente.setValueAt(format.format(cc.getFechaNacimiento()), fila, 5);
        tDerechoHabiente.setValueAt(cc.getCelular(), fila, 6);
        tDerechoHabiente.setValueAt(cc.getEmail(), fila, 7);
    }

    public void agregarAuditorIndependiente(ClienteAuditorIndependiente cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel modelo = (DefaultTableModel) tAuditorIndependiente.getModel();
        int contador = tAuditorIndependiente.getRowCount();
        modelo.setNumRows(contador);
        modelo.addRow(new Object[contador]);
        tAuditorIndependiente.setValueAt(cc, contador, 0);
        tAuditorIndependiente.setValueAt(contador + 1, contador, 1);
        tAuditorIndependiente.setValueAt(format.format(cc.getFechaAfiliacion()), contador, 2);
        tAuditorIndependiente.setValueAt(format.format(cc.getFechaVigenciaDesde()), contador, 3);
        tAuditorIndependiente.setValueAt(format.format(cc.getFechaVigenciaHasta()), contador, 4);
        String sector = "-";
        if (!Objects.isNull(cc.getSector())) {
            if (cc.getSector().equals("P")) {
                sector = "Privado";
            } else {
                if (cc.getSector().equals("G")) {
                    sector = "Gubernamental";
                }
            }
        }
        tAuditorIndependiente.setValueAt(sector, contador, 5);
    }

    public void actualizarAuditorIndependiente(int fila, ClienteAuditorIndependiente cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tAuditorIndependiente.setValueAt(format.format(cc.getFechaAfiliacion()), fila, 2);
        tAuditorIndependiente.setValueAt(format.format(cc.getFechaVigenciaDesde()), fila, 3);
        tAuditorIndependiente.setValueAt(format.format(cc.getFechaVigenciaHasta()), fila, 4);
        String sector = "-";
        if (!Objects.isNull(cc.getSector())) {
            if (cc.getSector().equals("P")) {
                sector = "Privado";
            } else {
                if (cc.getSector().equals("G")) {
                    sector = "Gubernamental";
                }
            }
        }
        tAuditorIndependiente.setValueAt(sector, fila, 5);
    }

    public void agregarComitePerito(ClienteComitePerito cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel modelo = (DefaultTableModel) tComitePerito.getModel();
        int contador = tComitePerito.getRowCount();
        modelo.setNumRows(contador);
        modelo.addRow(new Object[contador]);
        tComitePerito.setValueAt(cc, contador, 0);
        tComitePerito.setValueAt(contador + 1, contador, 1);
//        tComitePerito.setValueAt(format.format(cc.getFechaAfiliacion()), contador, 2);
        tComitePerito.setValueAt(format.format(cc.getDesde()), contador, 2);
        tComitePerito.setValueAt(!Objects.isNull(cc.getHasta()) ? format.format(cc.getHasta()) : "", contador, 3);
        String tipo = "-";
        if (!Objects.isNull(cc.getTipo())) {
            if (cc.getTipo().equals("J")) {
                tipo = "REPEJ";
            } else {
                if (cc.getTipo().equals("F")) {
                    tipo = "REPEF";
                }
            }
        }
        tComitePerito.setValueAt(tipo, contador, 4);
    }

    public void actualizarComitePerito(int fila, ClienteComitePerito cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        tComitePerito.setValueAt(format.format(cc.getFechaAfiliacion()), fila, 2);
        tComitePerito.setValueAt(format.format(cc.getDesde()), fila, 2);
        tComitePerito.setValueAt(!Objects.isNull(cc.getHasta()) ? format.format(cc.getHasta()) : "", fila, 3);
        String tipo = "-";
        if (!Objects.isNull(cc.getTipo())) {
            if (cc.getTipo().equals("J")) {
                tipo = "REPEJ";
            } else {
                if (cc.getTipo().equals("F")) {
                    tipo = "REPEF";
                }
            }
        }
        tComitePerito.setValueAt(tipo, fila, 4);
    }


    private void btnAgregarParienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarParienteActionPerformed
        frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        ClienteDerechoHabiente cdh = new ClienteDerechoHabiente();
        cdh.setCliente(this.cliActual);
//        f.AbrirFormularioDerechoHabiente(this, 1, 0, cdh);
    }//GEN-LAST:event_btnAgregarParienteActionPerformed

    private void tDerechoHabienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDerechoHabienteMouseClicked
        if (evt.getClickCount() == 2) {
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            int fila = tDerechoHabiente.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tDerechoHabiente.getModel();
            ClienteDerechoHabiente ctd = (ClienteDerechoHabiente) model.getValueAt(fila, 0);
            if (fila >= 0) {
//                f.AbrirFormularioDerechoHabiente(this, 2, fila, ctd);
            }
        }
    }//GEN-LAST:event_tDerechoHabienteMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        dpBO.exportarDerechoHabientes();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tAuditorIndependienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tAuditorIndependienteMouseClicked
        if (evt.getClickCount() == 2) {
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            int fila = tAuditorIndependiente.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tAuditorIndependiente.getModel();
            ClienteAuditorIndependiente ctd = (ClienteAuditorIndependiente) model.getValueAt(fila, 0);
            if (fila >= 0) {
//                f.AbrirFormularioAuditorIndependiente(this, 2, fila, ctd);
            }
        }
    }//GEN-LAST:event_tAuditorIndependienteMouseClicked

    private void tAuditorIndependienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tAuditorIndependienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿ Desea eliminar el auditor independiente ?");
            if (opcion == JOptionPane.YES_OPTION) {
                int fila = tAuditorIndependiente.getSelectedRow();
                if (fila >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tAuditorIndependiente.getModel();
                    ClienteAuditorIndependiente cc = (ClienteAuditorIndependiente) model.getValueAt(fila, 0);
                    if (cc.getId() > 0) {
                        ContadorBO cont = ContadorBO.getInstance();
                        cc.setBorrado("0");
                        cont.ActualizarObjeto(cc);
                    }
                    model.removeRow(fila);
                }
            }
        }
    }//GEN-LAST:event_tAuditorIndependienteKeyPressed

    private void btnAgregarPariente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPariente1ActionPerformed
        frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        ClienteAuditorIndependiente cdh = new ClienteAuditorIndependiente();
        cdh.setCliente(this.cliActual);
//        f.AbrirFormularioAuditorIndependiente(this, 1, 0, cdh);
    }//GEN-LAST:event_btnAgregarPariente1ActionPerformed

    private void btnAgregarPariente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPariente2ActionPerformed
        frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        ClienteComitePerito cdh = new ClienteComitePerito();
        cdh.setCliente(this.cliActual);
//        f.AbrirFormularioComitePerito(this, 1, 0, cdh);
    }//GEN-LAST:event_btnAgregarPariente2ActionPerformed

    private void tComitePeritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tComitePeritoMouseClicked
        if (evt.getClickCount() == 2) {
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            int fila = tComitePerito.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tComitePerito.getModel();
            ClienteComitePerito ctd = (ClienteComitePerito) model.getValueAt(fila, 0);
            if (fila >= 0) {
//                f.AbrirFormularioComitePerito(this, 2, fila, ctd);
            }
        }
    }//GEN-LAST:event_tComitePeritoMouseClicked

    private void tComitePeritoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tComitePeritoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿ Desea eliminar el comite de perito ?");
            if (opcion == JOptionPane.YES_OPTION) {
                int fila = tComitePerito.getSelectedRow();
                if (fila >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tComitePerito.getModel();
                    ClienteComitePerito cc = (ClienteComitePerito) model.getValueAt(fila, 0);
                    if (cc.getId() > 0) {
                        ContadorBO cont = ContadorBO.getInstance();
                        cc.setBorrado("0");
                        cont.ActualizarObjeto(cc);
                    }
                    model.removeRow(fila);
                }
            }
        }
    }//GEN-LAST:event_tComitePeritoKeyPressed

//    public void obtenerFicha(Integer nroFicha) {
//        SeguridadBO sBO = SeguridadBO.getINSTANCE();
//        String url = sBO.getUriBaseApiServer() + UtilConstantes.URI_APLICATIVO_WEB + "ficha/obtenerFicha?nro=" + nroFicha;
//        try {
//            Response response = Request.get(url).addHeader("Authorization", UtilConstantes.TOKEN_APLICATIVO_WEB).addHeader("Keyword", UtilConstantes.KEYWORD_APLICATIVO_WEB).execute();
//            String result = response.returnContent().asString();
//            if (!result.isEmpty()) {
//                Gson gson = new Gson();
//                FichaDatosDTO data = gson.fromJson(result, FichaDatosDTO.class);
//                if (data.getTieneError()) {
//                    JOptionPane.showMessageDialog(null, data.getMensajeError(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//                Cliente cliente = new Cliente();
//                cliente.setPapePat(data.getApePat());
//                cliente.setPapeMat(data.getApeMat());
//                cliente.setPnombre(data.getNombre());
//                cliente.setCemail(data.getEmail());
//                cliente.setPtelefonoDomicilio(data.getTelefonoFijo());
//                cliente.setPtelefonoCelular(data.getTelefonoCelular());
//                cliente.setPfechaNac(f.parse(data.getFechaNacimiento()));
//                cliente.setPfechaNac(f.parse(data.getFechaNacimiento()));
//                cliente.set(data.getLugarNacimiento());
//                cliente.setPdireccionDomicilio(data.getDomicilio());
//                cliente.setCcentroTrabajo(data.getCentroTrabajo());
//                cliente.setPdireccionTrabajo(data.getCentroTrabajoDireccion());
//                cliente.setPtelefonoCelular1(data.getTelefonoCelular2());
//                cliente.setPagina(data.getPaginaWeb());
//                cliente.setPtelefonoTrabajo(data.getTelefonoTrabajo());
//                cliente.setCemailAlternativo(data.getEmailTrabajo());
//                cliente.setpFecha(data.getFechaOptoTituloContador());
//                cliente.setpFecha(data.getUniversidadRealizoEstudios());
//                cliente.setpFecha(data.getUniversidadOtorgoTitulo());
//                cliente.setpFecha(data.getModalidadObtencionTitulo());
//                cliente.setpFecha(data.getFechaOptoGradoBachiller());
//                cliente.setpFecha(data.getEstudiosEspecializacion());
//                cliente.setpFecha(data.getOtrosEstudios());
//                cliente.setpFecha(data.getBecasObtenidas());
//                cliente.setpFecha(data.getCorrespondenciaFisica());
//                cliente.setClugarCobranza(data.getLugarCobranza());
//                cliente.setCobservacion(data.getComentarios());
//                cliente.setCActividades(data.getActividadesCulturales());
//                cliente.setClugarCobranza(data.getTemasGustariaRecibirCapacitacion());
//                for (int i = 0; i < cbDepartamentoDomicilio.getItemCount(); i++) {
//                    UbigeoDepartamento dep = (UbigeoDepartamento) cbDepartamentoDomicilio.getItemAt(i);
//                    if (Objects.equals(dep.getCodigoDepartamento(), data.getDireccionDepartamento())) {
//                        cliente.setCdepDomicilio(dep.getIdDepartamento());
//                    }
//                }
//                for (int i = 0; i < cbProvinciaDomicilio.getItemCount(); i++) {
//                    UbigeoProvincia pro = (UbigeoProvincia) cbProvinciaDomicilio.getItemAt(i);
//                    if (Objects.equals(pro.getCodigoProvincia(), data.getDireccionProvincia())) {
//                        cliente.setCproDomicilio(pro.getIdProvincia());
//                    }
//                }
//                for (int i = 0; i < cbDistritoDomicilio.getItemCount(); i++) {
//                    UbigeoDistrito dis = (UbigeoDistrito) cbDistritoDomicilio.getItemAt(i);
//                    if (Objects.equals(dis.getCodigoDistrito(), data.getDireccionDistrito())) {
//                        cliente.setCdisDomicilio(dis.getIdDistrito());
//                    }
//                }
//                for (int i = 0; i < cbDepartamentoTrabajo.getItemCount(); i++) {
//                    UbigeoDepartamento dep = (UbigeoDepartamento) cbDepartamentoTrabajo.getItemAt(i);
//                    if (Objects.equals(dep.getCodigoDepartamento(), data.getTrabajoDepartamento())) {
//                        cliente.setCdepTrabajo(dep.getIdDepartamento());
//                    }
//                }
//                for (int i = 0; i < cbProvinciaTrabajo.getItemCount(); i++) {
//                    UbigeoProvincia pro = (UbigeoProvincia) cbProvinciaTrabajo.getItemAt(i);
//                    if (Objects.equals(pro.getCodigoProvincia(), data.getTrabajoProvincia())) {
//                        cliente.setCproTrabajo(pro.getIdProvincia());
//                    }
//                }
//                for (int i = 0; i < cbDistritoTrabajo.getItemCount(); i++) {
//                    UbigeoDistrito dis = (UbigeoDistrito) cbDistritoTrabajo.getItemAt(i);
//                    if (Objects.equals(dis.getCodigoDistrito(), data.getTrabajoDistrito())) {
//                        cliente.setCdisTrabajo(dis.getIdDistrito());
//                    }
//                }
//                this.cargarDatos(ctd, data);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException ex) {
//            Logger.getLogger(frmCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        String nro = JOptionPane.showInputDialog("Ingrese el nro de ficha");
        try {
            Integer nroFicha = Integer.valueOf(nro);
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            JDialog dlgProgress = f.obtenerProgressBar();
            SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
//                    obtenerFicha(nroFicha);
                    return null;
                }

                @Override
                protected void done() {
                    dlgProgress.dispose();//close the modal dialog
                }
            };
            sw.execute(); // this will start the processing on a separate thread
            dlgProgress.setVisible(true); //this will block user input as long as the processing task is working
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nro de ficha valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPariente;
    private javax.swing.JButton btnAgregarPariente1;
    private javax.swing.JButton btnAgregarPariente2;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnFecha1;
    private javax.swing.JButton btnFecha2;
    private javax.swing.JButton btnFechaA;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnIngresarContador;
    private javax.swing.JComboBox cbActivo;
    private javax.swing.JComboBox cbBuzon;
    private javax.swing.JComboBox cbCertificacion;
    private javax.swing.JComboBox cbCobrador;
    private javax.swing.JComboBox cbCondicion;
    private javax.swing.JComboBox cbDepartamentoDomicilio;
    private javax.swing.JComboBox cbDepartamentoTrabajo;
    private javax.swing.JComboBox cbDistritoDomicilio;
    private javax.swing.JComboBox cbDistritoTrabajo;
    private javax.swing.JComboBox<String> cbEstadoCivil;
    private javax.swing.JComboBox cbFiltro;
    private javax.swing.JComboBox cbLocalidadDomicilio;
    private javax.swing.JComboBox cbLocalidadTrabajo;
    private javax.swing.JComboBox cbLugarCobranza;
    private javax.swing.JComboBox cbProvinciaDomicilio;
    private javax.swing.JComboBox cbProvinciaTrabajo;
    private javax.swing.JComboBox cbSexo;
    private javax.swing.JComboBox<String> cbTipoCertificacion;
    private javax.swing.JComboBox cbUniversidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JProgressBar pbContador;
    private javax.swing.JTable tAuditorIndependiente;
    private javax.swing.JTable tCertificados;
    private javax.swing.JTable tComitePerito;
    private javax.swing.JTable tContador;
    private javax.swing.JTable tDerechoHabiente;
    private javax.swing.JTextField txtApeMat;
    private javax.swing.JTextField txtApePat;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCentroTrabajo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccionDomicilio;
    private javax.swing.JTextField txtDireccionTrabajo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailAlternativo;
    private javax.swing.JFormattedTextField txtFechaAfiliacion;
    private javax.swing.JFormattedTextField txtFechaCertificacionDesde;
    private javax.swing.JFormattedTextField txtFechaCertificacionHasta;
    private javax.swing.JFormattedTextField txtFechaNac;
    private javax.swing.JTextField txtMatriculaAuditor;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumeroCertificado;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtTelefonoCelular;
    private javax.swing.JTextField txtTelefonoCelular1;
    private javax.swing.JTextField txtTelefonoDomicilio;
    private javax.swing.JTextField txtTelefonoTrabajo;
    private javax.swing.JTextField txtTelefonoTrabajo1;
    // End of variables declaration//GEN-END:variables
}
