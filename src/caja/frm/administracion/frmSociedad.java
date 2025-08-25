/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.administracion;

import caja.bo.ClienteBO;
import caja.bo.ContadorBO;
import caja.bo.SeguridadBO;
import caja.bo.SociedadBO;
import caja.bo.soloMayusculas;
import caja.frm.frmPrincipal;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteAuditorIndependiente;
import caja.mapeo.entidades.ClienteSociedadMiembro;
import caja.mapeo.entidades.ClienteSociedadVigencia;
//import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoProvincia;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import units.DatePicker;

/**
 *
 * @author juan carlos
 */
public class frmSociedad extends javax.swing.JInternalFrame {

    private Cliente sociedad = new Cliente();

    private List<UbigeoDepartamento> lUbigeoDepartamento;
    List<UbigeoProvincia> lUbigeoProvincias;
    List<UbigeoDistrito> lUbigeoDistrito;
    private boolean accesoGuardar = true;

    /**
     * Creates new form frmSociedad
     */
    public frmSociedad() {
        initComponents();

        TableColumn columna = tSociedad.getColumn("NRO");
        columna.setPreferredWidth(20);
        columna = tSociedad.getColumn("ID");
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna.setPreferredWidth(0);
        columna = tSociedad.getColumn("CODIGO");
        columna.setPreferredWidth(20);
        columna = tSociedad.getColumn("RUC");
        columna.setPreferredWidth(20);
        columna = tSociedad.getColumn("NOMBRE");
        columna.setPreferredWidth(350);
        columna = tSociedad.getColumn("FECHA AFILIACION");
        columna.setPreferredWidth(60);
        columna = tSociedad.getColumn("EMAIL");
        columna.setPreferredWidth(100);

        columna = tMiembros.getColumn("OBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tMiembros.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tMiembros.getColumn("PRINCIPAL");
        columna.setPreferredWidth(140);
        columna.setMinWidth(140);
        columna.setMaxWidth(140);

        columna = tVigencia.getColumn("OBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tVigencia.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
//        columna = tVigencia.getColumn("F. HASTA");
//        columna.setPreferredWidth(140);
//        columna.setMinWidth(140);
//        columna.setMaxWidth(140);
//        columna = tVigencia.getColumn("F. DESDE");
//        columna.setPreferredWidth(140);
//        columna.setMinWidth(140);
//        columna.setMaxWidth(140);

        txtRUC.setEditable(false);
        txtCodigo.setEditable(false);
        txtFechaA.setEditable(false);
        txtNombre.setEditable(false);
        txtNombre.setDocument(new soloMayusculas());
        cbCondicion.setEnabled(false);
        txtTelefonoFijo.setEditable(false);
        txtTelefonoCelular.setEditable(false);
        txtEmail1.setEditable(false);
        txtEmail2.setEditable(false);
        txtDireccion.setEditable(false);
        txtDireccion.setDocument(new soloMayusculas());
        txtPaginaWeb.setEditable(false);
        txtPaginaWeb.setDocument(new soloMayusculas());
        cbDepartamento.setEnabled(false);
        cbProvincia.setEnabled(false);
        cbDistrito.setEnabled(false);
        cbCobrador.setEnabled(false);
        cbCiudad.setEnabled(false);
        txtObservaciones.setEditable(false);
        txtObservaciones.setDocument(new soloMayusculas());

        btnFechaA.setEnabled(false);
//        btnFechaP.setEnabled(false);

        SociedadBO socBO = SociedadBO.getInstance();

        lUbigeoDepartamento = (List<UbigeoDepartamento>) socBO.ObtenerSociedadUbigeoDepartamento();
        for (UbigeoDepartamento udep : lUbigeoDepartamento) {
            cbDepartamento.addItem(udep.getNombreDepartamento());
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

        jLabel1 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        cbFiltro = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        btnIngresarSociedad = new javax.swing.JButton();
        pbSociedad = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tSociedad = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtRUC = new javax.swing.JTextField();
        txtFechaA = new javax.swing.JTextField();
        btnFechaA = new javax.swing.JButton();
        txtEmail1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefonoFijo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTelefonoCelular = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPaginaWeb = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbDepartamento = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbProvincia = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        cbDistrito = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        cbCondicion = new javax.swing.JComboBox();
        cbCobrador = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cbCiudad = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarPariente2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tMiembros = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tVigencia = new javax.swing.JTable();
        btnAgregarPariente1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Formulario Sociedad");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setText("Búsqueda:");

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "POR CODIGO", "POR RUC", "POR NOMBRE", "POR CONDICION" }));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/buscar_mini1.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnIngresarSociedad.setText("Ingresar Sociedad");
        btnIngresarSociedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarSociedadActionPerformed(evt);
            }
        });

        tSociedad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NRO", "CODIGO", "RUC", "NOMBRE", "FECHA AFILIACION", "EMAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tSociedad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tSociedadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tSociedad);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/Guardar2.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel2.setText("Codigo:");

        jLabel3.setText("RUC:");

        jLabel4.setText("Condición:");

        jLabel5.setText("F. afiliación:");

        jLabel7.setText("Email 1:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRUCKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRUCKeyTyped(evt);
            }
        });

        btnFechaA.setText("...");
        btnFechaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaAActionPerformed(evt);
            }
        });

        jLabel8.setText("Nombre:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        jLabel10.setText("Teléfono Fijo:");

        txtTelefonoFijo.setToolTipText("(054)-256784");

        jLabel11.setText("Celular:");

        jLabel12.setText("Email 2:");

        jLabel13.setText("Dirección:");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
        });

        jLabel14.setText("Página Web:");

        jLabel15.setText("Departamento:");

        cbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDepartamentoItemStateChanged(evt);
            }
        });

        jLabel16.setText("Provincia:");

        cbProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProvinciaItemStateChanged(evt);
            }
        });
        cbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProvinciaActionPerformed(evt);
            }
        });

        jLabel17.setText("Distrito:");

        cbDistrito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDistritoItemStateChanged(evt);
            }
        });

        jLabel18.setText("Cobrador:");

        jLabel19.setText("Observaciones:");

        cbCondicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "HABIL", "INHABIL", "LICENCIA", "RETIRADO" }));

        cbCobrador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CAJA 01", "CERVANTES TAMAYO HERNAN 03", "MASIEL ERIKA CARPIO CARPIO 05", "GUSTAVO COPARA CUSI 06", "GINO QUIROZ APAZA 07", "HERNAN BELLIDO QUISPE 08", "----------" }));

        jLabel6.setText("Ciudad:");

        cbCiudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AREQUIPA", "LIMA", "-----" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel19)
                    .addComponent(jLabel7)
                    .addComponent(jLabel18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtEmail1)
                                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDireccion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txtPaginaWeb)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(cbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaA, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(btnFechaA, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefonoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefonoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(148, 148, 148))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtFechaA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFechaA)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtTelefonoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtTelefonoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(txtPaginaWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17)
                                .addComponent(cbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(cbCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(cbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        jTabbedPane1.addTab("DATOS", jPanel1);

        btnAgregarPariente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/add_mini.png"))); // NOI18N
        btnAgregarPariente2.setText("AGREGAR");
        btnAgregarPariente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPariente2ActionPerformed(evt);
            }
        });

        tMiembros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OBJ", "NRO", "MIEMBRO", "PRINCIPAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tMiembros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tMiembrosMouseClicked(evt);
            }
        });
        tMiembros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tMiembrosKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tMiembros);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarPariente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarPariente2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("MIEMBROS", jPanel2);

        tVigencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OBJ", "NRO", "F. DESDE", "F. HASTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tVigencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tVigenciaMouseClicked(evt);
            }
        });
        tVigencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tVigenciaKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tVigencia);

        btnAgregarPariente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/add_mini.png"))); // NOI18N
        btnAgregarPariente1.setText("AGREGAR");
        btnAgregarPariente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPariente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarPariente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarPariente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("VIGENCIA", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                        .addComponent(btnIngresarSociedad))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pbSociedad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnIngresarSociedad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarSociedad() {
        txtRUC.setEditable(false);
        txtCodigo.setEditable(false);
        txtFechaA.setEditable(false);
        txtNombre.setEditable(false);
        cbCondicion.setEnabled(false);
        txtTelefonoFijo.setEditable(false);
        txtTelefonoCelular.setEditable(false);
        txtEmail1.setEditable(false);
        txtEmail2.setEditable(false);
        txtDireccion.setEditable(false);
        txtPaginaWeb.setEditable(false);
        cbDepartamento.setEnabled(false);
        cbProvincia.setEnabled(false);
        cbDistrito.setEnabled(false);
        cbCobrador.setEnabled(false);
        cbCiudad.setEnabled(false);
        txtObservaciones.setEditable(false);
        btnFechaA.setEnabled(false);
        int tipoBusqueda = 0;
        if (cbFiltro.getSelectedItem() == "POR CODIGO") {
            tipoBusqueda = 1;
        } else if (cbFiltro.getSelectedItem() == "POR RUC") {
            tipoBusqueda = 2;
        } else if (cbFiltro.getSelectedItem() == "POR NOMBRE") {
            tipoBusqueda = 3;
        } else if (cbFiltro.getSelectedItem() == "POR CONDICION") {
            tipoBusqueda = 4;
        }
        SociedadBO s = SociedadBO.getInstance();
        List<Cliente> l = s.BuscarSociedad(txtBusqueda.getText(), tipoBusqueda);
        DefaultTableModel model = (DefaultTableModel) tSociedad.getModel();
        model = (DefaultTableModel) tSociedad.getModel();
        model.setRowCount(l.size());
        int cont = 0;
        for (Cliente p : l) {
            model.setNumRows(cont);
            model.addRow(new Object[cont]);
            tSociedad.setValueAt(p.getIdCliente(), cont, 0);
            tSociedad.setValueAt(cont + 1, cont, 1);
            tSociedad.setValueAt(p.getScodigoSoc(), cont, 2);
            tSociedad.setValueAt(p.getSruc(), cont, 3);
            tSociedad.setValueAt(p.getSnombreSociedad(), cont, 4);
            tSociedad.setValueAt(p.getSfechaAfiliacion(), cont, 5);
            tSociedad.setValueAt(p.getSemail(), cont, 6);
            cont++;
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Thread queryThread = new Thread() {
            public void run() {
                pbSociedad.setIndeterminate(true);
                pbSociedad.setStringPainted(true);
                pbSociedad.setString("BUSCANDO SOCIEDAD, POR FAVOR ESPERE");
                pbSociedad.setValue(100);
                pbSociedad.repaint();
                buscarSociedad();
                pbSociedad.setString("BUSQUEDA FINALIZADA");
                pbSociedad.setStringPainted(false);
                pbSociedad.setIndeterminate(false);
            }
        };
        queryThread.start();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnIngresarSociedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarSociedadActionPerformed
        btnGuardar.setEnabled(true);
        txtRUC.setEditable(true);
        txtCodigo.setEditable(true);
        txtFechaA.setEditable(true);
        txtNombre.setEditable(true);
        cbCondicion.setEnabled(true);
        txtTelefonoFijo.setEditable(true);
        txtTelefonoCelular.setEditable(true);
        txtEmail1.setEditable(true);
        txtEmail2.setEditable(true);
        txtDireccion.setEditable(true);
        txtPaginaWeb.setEditable(true);
        cbDepartamento.setEnabled(true);
        cbDepartamento.setSelectedIndex(cbDepartamento.getItemCount() - 1);
        cbProvincia.setEnabled(true);
        cbProvincia.setSelectedIndex(0);
        cbDistrito.setEnabled(true);
        cbDistrito.setSelectedIndex(0);
        cbCobrador.setEnabled(true);
        cbCiudad.setEnabled(true);
        txtObservaciones.setEditable(true);
        txtRUC.setFocusable(true);
        txtRUC.requestFocus();
        btnFechaA.setEnabled(true);
        txtRUC.setText("");
        txtCodigo.setText("");
        txtFechaA.setText("");
        txtNombre.setText("");
        txtTelefonoFijo.setText("");
        txtTelefonoCelular.setText("");
        txtEmail1.setText("");;
        txtEmail2.setText("");
        txtDireccion.setText("");
        txtPaginaWeb.setText("");
        txtObservaciones.setText("");
        this.sociedad = new Cliente();
        if (this.accesoGuardar) {
            btnGuardar.setEnabled(true);
        } else {
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_btnIngresarSociedadActionPerformed

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


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!txtRUC.getText().isEmpty() && !txtCodigo.getText().isEmpty()
                && !txtFechaA.getText().isEmpty() && !txtNombre.getText().isEmpty()) {
            ClienteBO sBO = ClienteBO.getInstance();
            List<Cliente> lClientes = sBO.ObtenerTodasSociedades();
            if (txtRUC.getText().length() != 11) {
                JOptionPane.showMessageDialog(this, "El RUC debe tener 11 digitos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtCodigo.getText().length() != 4) {
                JOptionPane.showMessageDialog(this, "El Código debe tener 4 digitos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!this.esFechaValida(txtFechaA.getText())) {
                JOptionPane.showMessageDialog(this, "La Fecha de afiliación no es Correcta", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Cliente c = this.sociedad;
                if (!txtRUC.getText().trim().isEmpty()) {
                    c.setSruc(txtRUC.getText().trim());
                } else {
                    c.setSruc(null);
                }

                c.setScodigoSoc(txtCodigo.getText());
                c.setSnombreSociedad(txtNombre.getText());

                c.setTipoCliente("S");

                //cb para guardar la ciudad a la que pertenece la sociedad
                if (cbCiudad.getSelectedItem() == "-----") {
                    c.setSciudad(null);
                } else if (cbCiudad.getSelectedItem() == "AREQUIPA") {
                    c.setSciudad("A");
                } else {
                    c.setSciudad("L");
                }

                //combo condición de la SOCIEDAD
                if (cbCondicion.getSelectedItem() == "HABIL") {
                    c.setEstado("H");
                } else if (cbCondicion.getSelectedItem() == "INHABIL") {
                    c.setEstado("I");
                } else if (cbCondicion.getSelectedItem() == "LICENCIA") {
                    c.setEstado("L");
                } else {
                    c.setEstado("R");
                }

                //combo de cobrador
                if (cbCobrador.getSelectedItem().equals("----------")) {
                    c.setScobrador(null);
                } else if (cbCobrador.getSelectedItem() == "CAJA 01") {
                    c.setScobrador("01");
                } else if (cbCobrador.getSelectedItem() == "CERVANTES TAMAYO HERNAN 03") {
                    c.setScobrador("03");
                } else if (cbCobrador.getSelectedItem() == "MASIEL ERIKA CARPIO CARPIO 05") {
                    c.setScobrador("05");
                } else if (cbCobrador.getSelectedItem() == "GUSTAVO COPARA CUSI 06") {
                    c.setScobrador("06");
                } else if (cbCobrador.getSelectedItem() == "GINO QUIROZ APAZA 07") {
                    c.setScobrador("07");
                } else {
                    c.setScobrador("08");
                }

                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                c.setSfechaAfiliacion(formatoDeFecha.parse(txtFechaA.getText()));

                if (!txtTelefonoFijo.getText().isEmpty()) {
                    c.setStelefonoFijo(txtTelefonoFijo.getText());
                } else {
                    c.setStelefonoFijo(null);
                }

                if (!txtTelefonoCelular.getText().isEmpty()) {
                    c.setStelefonoCelular(txtTelefonoCelular.getText());
                } else {
                    c.setStelefonoCelular(null);
                }

                if (!txtEmail1.getText().isEmpty()) {
                    c.setSemail(txtEmail1.getText());
                } else {
                    c.setSemail(null);
                }

                if (!txtEmail2.getText().isEmpty()) {
                    c.setSemailAlternativo(txtEmail2.getText());
                } else {
                    c.setSemailAlternativo(null);
                }

                if (!txtDireccion.getText().isEmpty()) {
                    c.setSdireccion(txtDireccion.getText());
                } else {
                    c.setSdireccion(null);
                }

                if (!txtPaginaWeb.getText().isEmpty()) {
                    c.setSpaginaWeb(txtPaginaWeb.getText());
                } else {
                    c.setSpaginaWeb(null);
                }

//                c.setSobservacion(txtObservaciones.getText());
                if (!txtObservaciones.getText().isEmpty()) {
                    c.setSobservacion(txtObservaciones.getText());
                } else {
                    c.setSobservacion(null);
                }

                //cb para guardar Ubigeo Departamento
                for (UbigeoDepartamento ud : lUbigeoDepartamento) {
                    if (cbDepartamento.getSelectedIndex() == -1) {
                        c.setSdepartamento(null);
                    } else {
                        int idDepartamento = cbDepartamento.getSelectedIndex();

                        if (ud.getIdDepartamento() == idDepartamento) {
                            c.setSdepartamento(idDepartamento + 1);
                        }
                    }
                }

                //cb para guardar Ubigeo Provincia
                for (UbigeoProvincia up : lUbigeoProvincias) {
                    if (cbProvincia.getSelectedIndex() == 0) {
                        c.setSprovincia(null);
                    } else {
                        int idProvincia = lUbigeoProvincias.get(cbProvincia.getSelectedIndex())
                                .getIdProvincia();
                        //cbProvinciaDomicilio.getSelectedIndex();

                        if (up.getIdProvincia() == idProvincia) {
                            c.setSprovincia(idProvincia);
                        }
                    }
                }

                //cb para guardar Ubigeo Distrito
                for (UbigeoDistrito udist : lUbigeoDistrito) {
                    if (cbDistrito.getSelectedIndex() == 0) {
                        c.setSdistrito(null);
                    } else {
                        int idDistrito = lUbigeoDistrito.get(cbDistrito.getSelectedIndex())
                                .getIdDistrito();

                        if (udist.getIdDistrito() == idDistrito) {
                            c.setSdistrito(idDistrito);
                        }
                    }
                }
                SociedadBO soc = SociedadBO.getInstance();
                if (c.getIdCliente() == 0) {
                    for (Cliente cl : lClientes) {
                        if (cl.getScodigoSoc().equals(txtCodigo.getText())) {
                            JOptionPane.showMessageDialog(this,
                                    "El Código ya existe, vuelva a ingresarlo", "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    soc.GuardarSociedad(c);
                    JOptionPane.showMessageDialog(this, "Se insertó Correctamente la Sociedad ", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (Cliente cl : lClientes) {
                        if (cl.getScodigoSoc().equals(txtCodigo.getText())) {
                            if (cl.getIdCliente() != c.getIdCliente()) {
                                JOptionPane.showMessageDialog(this,
                                        "El Código ya existe, vuelva a ingresarlo", "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }
                    soc.ActualizarSociedad(c);
                    JOptionPane.showMessageDialog(this, "Se actualizó Correctamente la Sociedad ", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                ContadorBO cont = ContadorBO.getInstance();
                for (int i = 0; i < tMiembros.getRowCount(); i++) {
                    ClienteSociedadMiembro cc = (ClienteSociedadMiembro) tMiembros.getValueAt(i, 0);
                    cc.setCliente(this.sociedad);
                    if (cc.getId() == 0) {
                        cont.GuardarObjeto(cc);
                    } else {
                        cont.ActualizarObjeto(cc);
                    }
                }
                for (int i = 0; i < tVigencia.getRowCount(); i++) {
                    ClienteSociedadVigencia cc = (ClienteSociedadVigencia) tVigencia.getValueAt(i, 0);
                    cc.setCliente(this.sociedad);
                    if (cc.getId() == 0) {
                        cont.GuardarObjeto(cc);
                    } else {
                        cont.ActualizarObjeto(cc);
                    }
                }
                txtRUC.setEditable(false);
                txtCodigo.setEditable(false);
                txtFechaA.setEditable(false);
                txtNombre.setEditable(false);
                cbCondicion.setEnabled(false);
                txtTelefonoFijo.setEditable(false);
                txtTelefonoCelular.setEditable(false);
                txtEmail1.setEditable(false);
                txtEmail2.setEditable(false);
                txtDireccion.setEditable(false);
                txtPaginaWeb.setEditable(false);
                cbDepartamento.setEnabled(false);
                cbProvincia.setEnabled(false);
                cbDistrito.setEnabled(false);
                cbCobrador.setEnabled(false);
                cbCiudad.setEnabled(false);
                txtObservaciones.setEditable(false);
                txtRUC.setFocusable(false);
                txtRUC.requestFocus();
                btnFechaA.setEnabled(false);

                txtRUC.setText("");
                txtCodigo.setText("");
                txtFechaA.setText("");
                txtNombre.setText("");
                txtTelefonoFijo.setText("");
                txtTelefonoCelular.setText("");
                txtEmail1.setText("");;
                txtEmail2.setText("");
                txtDireccion.setText("");
                txtPaginaWeb.setText("");
                txtObservaciones.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "Debe llenar todos los Campos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tSociedadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tSociedadMouseClicked
        if (evt.getClickCount() == 2) {
            btnGuardar.setEnabled(true);
            int fila = tSociedad.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tSociedad.getModel();
            String codigo = String.valueOf(model.getValueAt(fila, 2));
            SociedadBO soc = SociedadBO.getInstance();
            this.sociedad = soc.ObtenerSociedad(codigo);
            String condicion = this.sociedad.getEstado();
            if (this.sociedad.getEstado() == null) {
                cbCondicion.setSelectedItem("INHABIL");
            } else if (condicion.equals("H")) {
                cbCondicion.setSelectedItem("HABIL");
            } else if (condicion.equals("I")) {
                cbCondicion.setSelectedItem("INHABIL");
            } else if (condicion.equals("L")) {
                cbCondicion.setSelectedItem("LICENCIA");
            } else {
                cbCondicion.setSelectedItem(("RETIRADO"));
            }
            String cobradores = this.sociedad.getScobrador();
            if (this.sociedad.getScobrador() == null) {
                cbCobrador.setSelectedItem("----------");
            } else if (cobradores.equals("01")) {
                cbCobrador.setSelectedItem("CAJA 01");
            } else if (cobradores.equals("03")) {
                cbCobrador.setSelectedItem("CERVANTES TAMAYO HERNAN 03");
            } else if (cobradores.equals("05")) {
                cbCobrador.setSelectedItem("MASIEL ERIKA CARPIO CARPIO 05");
            } else if (cobradores.equals("06")) {
                cbCobrador.setSelectedItem("GUSTAVO COPARA CUSI 06");
            } else if (cobradores.equals("07")) {
                cbCobrador.setSelectedItem("GINO QUIROZ APAZA 07");
            } else {
                cbCobrador.setSelectedItem("HERNAN BELLIDO QUISPE 08");
            }
            String ciudad = this.sociedad.getSciudad();
            if (this.sociedad.getSciudad() == null) {
                cbCiudad.setSelectedItem("-----");
            } else if (ciudad.equals("A")) {
                cbCiudad.setSelectedItem("AREQUIPA");
            } else {
                cbCiudad.setSelectedItem("LIMA");
            }
            if (this.sociedad.getSdepartamento() == null) {
                cbDepartamento.setSelectedItem("---");
            } else {
                int idDepartamento = this.sociedad.getSdepartamento();
                String NombreDepartamento = lUbigeoDepartamento.get(idDepartamento - 1).getNombreDepartamento();
                cbDepartamento.setSelectedItem(NombreDepartamento);
            }
            //cb de ubigeo provincia mediante un for para hallar la posición del idProvincia, antes los null
            if (this.sociedad.getSprovincia() == null) {
                cbProvincia.setSelectedItem("---");
            } else {
                int idProvincia = this.sociedad.getSprovincia();
                for (UbigeoProvincia up : lUbigeoProvincias) {
                    if (up.getIdProvincia() == idProvincia) {
                        cbProvincia.setSelectedItem(up.getNombreProvincia());
                        break;
                    }
                }
            }
            //cb de ubigeo distrito
            if (this.sociedad.getSdistrito() == null) {
                cbDistrito.setSelectedItem("---");
            } else {
                int idDistrito = this.sociedad.getSdistrito();
                for (UbigeoDistrito ud : lUbigeoDistrito) {
                    if (ud.getIdDistrito() == idDistrito) {
                        cbDistrito.setSelectedItem(ud.getNombreDistrito());
                        break;
                    }
                }
            }
            txtRUC.setText(this.sociedad.getSruc());
            if (this.sociedad.getSruc() == null) {
                txtRUC.setText("");
            }
            txtCodigo.setText(this.sociedad.getScodigoSoc());
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            txtFechaA.setText(formatoDeFecha.format(this.sociedad.getSfechaAfiliacion()));
            txtNombre.setText(this.sociedad.getSnombreSociedad());
            if (this.sociedad.getSnombreSociedad() == null) {
                txtNombre.setText("");
            }
            txtTelefonoFijo.setText(this.sociedad.getStelefonoFijo());
            if (this.sociedad.getStelefonoFijo() == null) {
                txtTelefonoFijo.setText("");
            }
            txtTelefonoCelular.setText(this.sociedad.getStelefonoCelular());
            if (this.sociedad.getStelefonoCelular() == null) {
                txtTelefonoCelular.setText("");
            }
            txtEmail1.setText(this.sociedad.getSemail());
            if (this.sociedad.getSemail() == null) {
                txtEmail1.setText("");
            }
            txtEmail2.setText(this.sociedad.getSemailAlternativo());
            if (this.sociedad.getSemailAlternativo() == null) {
                txtEmail2.setText("");
            }
            txtDireccion.setText(this.sociedad.getSdireccion());
            if (this.sociedad.getSdireccion() == null) {
                txtDireccion.setText("");
            }
            txtPaginaWeb.setText(this.sociedad.getSpaginaWeb());
            if (this.sociedad.getStelefonoFijo() == null) {
                txtTelefonoFijo.setText("");
            }
            txtObservaciones.setText(this.sociedad.getSobservacion());
            if (this.sociedad.getSobservacion() == null) {
                txtObservaciones.setText("");
            }
            List<ClienteSociedadMiembro> lai = soc.ObtenerSociedadMiembro_SegunCliente(sociedad.getIdCliente());
            DefaultTableModel modelo = (DefaultTableModel) tMiembros.getModel();
            modelo.setRowCount(0);
            int contador = 0;
            for (ClienteSociedadMiembro cc : lai) {
                modelo.setNumRows(contador);
                modelo.addRow(new Object[contador]);
                tMiembros.setValueAt(cc, contador, 0);
                tMiembros.setValueAt(contador + 1, contador, 1);
                tMiembros.setValueAt(cc.getClienteMiembro().getPapePat() + " " + cc.getClienteMiembro().getPapeMat() + " " + cc.getClienteMiembro().getPnombre(), contador, 2);
                tMiembros.setValueAt(!Objects.isNull(cc.getEsPrincipal()) ? (cc.getEsPrincipal().equals("S") ? "SI" : "NO") : "NO", contador, 3);
//                tMiembros.setValueAt(cc.getFechaVigenciaHasta() != null ? format.format(cc.getFechaVigenciaHasta()) : "1900-01-01", contador, 4);
                contador++;
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            List<ClienteSociedadVigencia> lav = soc.ObtenerSociedadVigencia_SegunCliente(sociedad.getIdCliente());
            modelo = (DefaultTableModel) tVigencia.getModel();
            modelo.setRowCount(0);
            contador = 0;
            for (ClienteSociedadVigencia cc : lav) {
                modelo.setNumRows(contador);
                modelo.addRow(new Object[contador]);
                tVigencia.setValueAt(cc, contador, 0);
                tVigencia.setValueAt(contador + 1, contador, 1);
                tVigencia.setValueAt(cc.getFechaDesde() != null ? format.format(cc.getFechaDesde()) : "1900-01-01", contador, 2);
                tVigencia.setValueAt(cc.getFechaHasta() != null ? format.format(cc.getFechaHasta()) : "1900-01-01", contador, 3);
                contador++;
            }

            txtRUC.setEditable(true);
            txtCodigo.setEditable(true);
            txtFechaA.setEditable(true);
            txtNombre.setEditable(true);
            cbCondicion.setEnabled(true);
            txtTelefonoFijo.setEditable(true);
            txtTelefonoCelular.setEditable(true);
            txtEmail1.setEditable(true);
            txtEmail2.setEditable(true);
            txtDireccion.setEditable(true);
            txtPaginaWeb.setEditable(true);
            cbDepartamento.setEnabled(true);
            cbProvincia.setEnabled(true);
            cbDistrito.setEnabled(true);
            cbCobrador.setEnabled(true);
            cbCiudad.setEnabled(true);
            txtObservaciones.setEditable(true);
            btnFechaA.setEnabled(true);
            if (this.accesoGuardar) {
                btnGuardar.setEnabled(true);
            } else {
                btnGuardar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tSociedadMouseClicked

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if (evt.getKeyCode() == 10) {
            this.buscarSociedad();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void txtRUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c) == false) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRUCKeyTyped

    private void cbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProvinciaActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c) == false) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void btnFechaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaAActionPerformed
        DatePicker dtp = new DatePicker(this);
        txtFechaA.setText(dtp.setPickedDate());
    }//GEN-LAST:event_btnFechaAActionPerformed

    private void cbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartamentoItemStateChanged
        SociedadBO sBO = SociedadBO.getInstance();

        if (cbDepartamento.getSelectedIndex() >= -1) {
            cbProvincia.removeAllItems();
            final int idDepartamento = lUbigeoDepartamento.get(cbDepartamento.getSelectedIndex())
                    .getIdDepartamento();

            List<UbigeoProvincia> list = sBO.ObtenerSociedadUbigeoProvincia(idDepartamento);

            for (UbigeoProvincia u : list) {
                cbProvincia.addItem(u.getNombreProvincia());

                //cbDepartamentoDomicilio.addItem(udep.getNombreDepartamento());
            }
        }
    }//GEN-LAST:event_cbDepartamentoItemStateChanged

    private void cbProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProvinciaItemStateChanged
        SociedadBO sBO = SociedadBO.getInstance();
        int idProvincia = 0;

        if (cbProvincia.getSelectedIndex() > -1) {
            cbDistrito.removeAllItems();
            lUbigeoProvincias = (List<UbigeoProvincia>) sBO.ObtenerSociedadUbigeoProvincia(lUbigeoDepartamento.
                    get(cbDepartamento.getSelectedIndex()).getIdDepartamento());
            //distrito
            idProvincia = lUbigeoProvincias.get(cbProvincia.getSelectedIndex()).getIdProvincia();

            List<UbigeoDistrito> list = sBO.ObtenerSociedadUbigeoDistrito(idProvincia);

            for (UbigeoDistrito u : list) {
                cbDistrito.addItem(u.getNombreDistrito());

                //cbDepartamentoDomicilio.addItem(udep.getNombreDepartamento());
            }
        }
    }//GEN-LAST:event_cbProvinciaItemStateChanged

    private void txtRUCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtRUC.getText().isEmpty()) {
                txtCodigo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE PONER EL RUC",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtRUCKeyPressed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtCodigo.getText().isEmpty()) {
                txtNombre.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE PONER EL CODIGO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtNombre.getText().isEmpty()) {
                txtDireccion.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE PONER EL NOMBRE",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtDireccion.getText().isEmpty()) {
                btnGuardar.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE PONER LA DIRECCIÓN",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void cbDistritoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDistritoItemStateChanged
        SociedadBO sBO = SociedadBO.getInstance();
        int idDistrito = 0;

        if (cbDistrito.getSelectedIndex() > -1) {
//            cbDistritoDomicilio.removeAllItems(); 
            lUbigeoDistrito = (List<UbigeoDistrito>) sBO.ObtenerSociedadUbigeoDistrito(lUbigeoProvincias.
                    get(cbProvincia.getSelectedIndex()).getIdProvincia());
            //distrito
            idDistrito = lUbigeoDistrito.get(cbDistrito.getSelectedIndex()).getIdDistrito();

            List<UbigeoDistrito> list = sBO.ObtenerSociedadUbigeoDistrito(idDistrito);

            for (UbigeoDistrito u : list) {
                cbDistrito.addItem(u.getNombreDistrito());

                //cbDepartamentoDomicilio.addItem(udep.getNombreDepartamento());
            }
        }
    }//GEN-LAST:event_cbDistritoItemStateChanged

    public void agregarMiembro(Cliente cc) {
        ClienteSociedadMiembro csm = new ClienteSociedadMiembro();
        csm.setBorrado("1");
        csm.setEsPrincipal("N");
        csm.setClienteMiembro(cc);
        DefaultTableModel modelo = (DefaultTableModel) tMiembros.getModel();
        int contador = tMiembros.getRowCount();
        modelo.setNumRows(contador);
        modelo.addRow(new Object[contador]);
        tMiembros.setValueAt(csm, contador, 0);
        tMiembros.setValueAt(contador + 1, contador, 1);
        tMiembros.setValueAt(csm.getClienteMiembro().getPapePat() + " " + csm.getClienteMiembro().getPapeMat() + " " + csm.getClienteMiembro().getPnombre(), contador, 2);
        tMiembros.setValueAt("NO", contador, 3);
    }

    public void actualizarMiembro(int fila, Cliente cc) {
        DefaultTableModel modelo = (DefaultTableModel) tMiembros.getModel();
        ClienteSociedadMiembro csm = (ClienteSociedadMiembro) modelo.getValueAt(0, fila);
        csm.setClienteMiembro(cc);
        tMiembros.setValueAt(csm.getCliente().getPapePat() + " " + csm.getCliente().getPapeMat() + " " + csm.getCliente().getPnombre(), fila, 2);
    }


    private void btnAgregarPariente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPariente2ActionPerformed
        frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        f.AbrirFormularioBuscardorContador(this, 2);
//        f.AbrirFormularioBuscardorAuditorIndependiente(this, 1);
    }//GEN-LAST:event_btnAgregarPariente2ActionPerformed

    private void tMiembrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMiembrosMouseClicked
        if (evt.getClickCount() == 2) {
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            int fila = tMiembros.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tMiembros.getModel();
            if (fila >= 0) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿ Desea que sea el miembro principal ?");
                if (opcion == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < tMiembros.getRowCount(); i++) {
                        ClienteSociedadMiembro m = (ClienteSociedadMiembro) model.getValueAt(i, 0);
                        model.setValueAt("NO", i, 3);
                        m.setEsPrincipal("N");
                    }
                    ClienteSociedadMiembro ctd = (ClienteSociedadMiembro) model.getValueAt(fila, 0);
                    model.setValueAt("SI", fila, 3);
                    ctd.setEsPrincipal("S");
                } else {
                    if (opcion == JOptionPane.NO_OPTION) {
                        ClienteSociedadMiembro ctd = (ClienteSociedadMiembro) model.getValueAt(fila, 0);
                        model.setValueAt("NO", fila, 3);
                        ctd.setEsPrincipal("N");
                    }
                }
            }
        }
    }//GEN-LAST:event_tMiembrosMouseClicked

    private void tMiembrosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tMiembrosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿ Desea eliminar el miembro ?");
            if (opcion == JOptionPane.YES_OPTION) {
                int fila = tMiembros.getSelectedRow();
                if (fila >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tMiembros.getModel();
                    ClienteSociedadMiembro cc = (ClienteSociedadMiembro) model.getValueAt(fila, 0);
                    if (cc.getId() > 0) {
                        ContadorBO cont = ContadorBO.getInstance();
                        cc.setBorrado("0");
                        cont.ActualizarObjeto(cc);
                    }
                    model.removeRow(fila);
                }
            }
        }
    }//GEN-LAST:event_tMiembrosKeyPressed

    private void tVigenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tVigenciaMouseClicked
        if (evt.getClickCount() == 2) {
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            int fila = tVigencia.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tVigencia.getModel();
            ClienteSociedadVigencia ctd = (ClienteSociedadVigencia) model.getValueAt(fila, 0);
            if (fila >= 0) {
                f.AbrirFormularioSociedadVigencia(this, 2, fila, ctd);
            }
        }
    }//GEN-LAST:event_tVigenciaMouseClicked

    private void tVigenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tVigenciaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿ Desea eliminar la vigencia ?");
            if (opcion == JOptionPane.YES_OPTION) {
                int fila = tVigencia.getSelectedRow();
                if (fila >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tVigencia.getModel();
                    ClienteSociedadVigencia cc = (ClienteSociedadVigencia) model.getValueAt(fila, 0);
                    if (cc.getId() > 0) {
                        ContadorBO cont = ContadorBO.getInstance();
                        cc.setBorrado("0");
                        cont.ActualizarObjeto(cc);
                    }
                    model.removeRow(fila);
                }
            }
        }
    }//GEN-LAST:event_tVigenciaKeyPressed

    public void agregarVigencia(ClienteSociedadVigencia cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel modelo = (DefaultTableModel) tVigencia.getModel();
        int contador = tVigencia.getRowCount();
        modelo.setNumRows(contador);
        modelo.addRow(new Object[contador]);
        tVigencia.setValueAt(cc, contador, 0);
        tVigencia.setValueAt(contador + 1, contador, 1);
//        tVigencia.setValueAt(format.format(cc.getFechaAfiliacion()), contador, 2);
        tVigencia.setValueAt(format.format(cc.getFechaDesde()), contador, 2);
        tVigencia.setValueAt(format.format(cc.getFechaHasta()), contador, 3);
//        String sector = "-";
//        if (!Objects.isNull(cc.getSector())) {
//            if (cc.getSector().equals("P")) {
//                sector = "Privado";
//            } else {
//                if (cc.getSector().equals("G")) {
//                    sector = "Gubernamental";
//                }
//            }
//        }
//        tAuditorIndependiente.setValueAt(sector, contador, 5);
    }

    public void actualizarVigencia(int fila, ClienteSociedadVigencia cc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        tAuditorIndependiente.setValueAt(format.format(cc.getFechaAfiliacion()), fila, 2);
        tVigencia.setValueAt(format.format(cc.getFechaDesde()), fila, 2);
        tVigencia.setValueAt(format.format(cc.getFechaHasta()), fila, 3);
//        String sector = "-";
//        if (!Objects.isNull(cc.getSector())) {
//            if (cc.getSector().equals("P")) {
//                sector = "Privado";
//            } else {
//                if (cc.getSector().equals("G")) {
//                    sector = "Gubernamental";
//                }
//            }
//        }
//        tAuditorIndependiente.setValueAt(sector, fila, 5);
    }


    private void btnAgregarPariente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPariente1ActionPerformed
        frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        ClienteSociedadVigencia cdh = new ClienteSociedadVigencia();
        cdh.setCliente(this.sociedad);
        f.AbrirFormularioSociedadVigencia(this, 1, 0, cdh);
    }//GEN-LAST:event_btnAgregarPariente1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPariente1;
    private javax.swing.JButton btnAgregarPariente2;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFechaA;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIngresarSociedad;
    private javax.swing.JComboBox cbCiudad;
    private javax.swing.JComboBox cbCobrador;
    private javax.swing.JComboBox cbCondicion;
    private javax.swing.JComboBox cbDepartamento;
    private javax.swing.JComboBox cbDistrito;
    private javax.swing.JComboBox cbFiltro;
    private javax.swing.JComboBox cbProvincia;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JProgressBar pbSociedad;
    private javax.swing.JTable tMiembros;
    private javax.swing.JTable tSociedad;
    private javax.swing.JTable tVigencia;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtFechaA;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextField txtPaginaWeb;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtTelefonoCelular;
    private javax.swing.JTextField txtTelefonoFijo;
    // End of variables declaration//GEN-END:variables
}
