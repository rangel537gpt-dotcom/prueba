/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.caja;

import caja.bo.ClienteBO;
import caja.bo.ContadorBO;
import caja.bo.CuotasBO;
import caja.bo.DeudasBO;
import caja.bo.DocumentoPagoBO;
import caja.bo.FinanciamientoBO;
import caja.bo.MiRender;
import caja.bo.PagoDeMasBO;
import caja.bo.ReincorporacionBO;
import caja.bo.ReporteCuentaCorriente;
import caja.bo.SeguridadBO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.PagoDeMas;
import caja.mapeo.entidades.Reincorporacion;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.frm.frmPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author user
 */
public class frmHistorialContador extends javax.swing.JInternalFrame {

    private int idCliente = 0;

    /**
     * Creates new form frmHistorial
     */
    public frmHistorialContador() {
        initComponents();
        tDetalle.setAutoResizeMode(tDetalle.AUTO_RESIZE_OFF);
        TableColumn columna = tContador.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tContador.getColumn("CODIGO");
        columna.setPreferredWidth(70);
        columna.setMinWidth(50);
        columna.setMaxWidth(50);
        columna = tContador.getColumn("IDCONTADOR");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tContador.getColumn("FCH AFILI.");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tContador.getColumn("ESTADO");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);

        columna = tDetalle.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tDetalle.getColumn("FECHA");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tDetalle.getColumn("CMP");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tDetalle.getColumn("DESCRIPCION");
        columna.setPreferredWidth(340);
        columna.setMinWidth(340);
        columna.setMaxWidth(340);
        columna = tDetalle.getColumn("COBRADOR");
        columna.setPreferredWidth(200);
        columna.setMinWidth(200);
        columna.setMaxWidth(200);
        columna = tDetalle.getColumn("SERIE");
        columna.setPreferredWidth(45);
        columna.setMinWidth(45);
        columna.setMaxWidth(45);
        columna = tDetalle.getColumn("NUM");
        columna.setPreferredWidth(50);
        columna.setMinWidth(50);
        columna.setMaxWidth(50);
        columna = tDetalle.getColumn("IMPORTE");
        columna.setPreferredWidth(80);
        columna.setMinWidth(80);
        columna.setMaxWidth(80);
        columna = tDetalle.getColumn("IGV");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("ESTADO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("IDDOCUMENTO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("IDTIPODOC");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("TIPOMONEDA");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);

        columna = tFinanciamiento.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tFinanciamiento.getColumn("IDFINANCIAMIENTO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);

        columna = tDetalleFinanciamiento.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tDetalleFinanciamiento.getColumn("MONTO");
        columna.setPreferredWidth(50);
        columna.setMinWidth(50);
        columna.setMaxWidth(50);
        columna = tDetalleFinanciamiento.getColumn("CUOTA");
        columna.setPreferredWidth(50);
        columna.setMinWidth(50);
        columna.setMaxWidth(50);

        columna = tDeudas.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);

        columna = tReincorporaciones.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tReincorporaciones.getColumn("ID");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);

        columna = tCurso.getColumn("OBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tCurso.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tCurso.getColumn("FECHA");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tCurso.getColumn("COD");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tCurso.getColumn("TIP DOC");
        columna.setPreferredWidth(100);
        columna.setMinWidth(100);
        columna.setMaxWidth(100);
        columna = tCurso.getColumn("SER DOC");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tCurso.getColumn("NRO DOC");
        columna.setPreferredWidth(100);
        columna.setMinWidth(100);
        columna.setMaxWidth(100);
        columna = tCurso.getColumn("MONTO");
        columna.setPreferredWidth(100);
        columna.setMinWidth(100);
        columna.setMaxWidth(100);

        tCuotas.setDefaultRenderer(Object.class, new MiRender());

        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        int contador = 0;
        for (Cliente cli : lClientes) {
            if (cli.getTipoCliente().equals("C")) {
                model.setNumRows(contador);
                model.addRow(new Object[contador]);
                tContador.setValueAt(contador + 1, contador, 0);
                tContador.setValueAt(cli.getCcodigoCole(), contador, 1);
                tContador.setValueAt(cli.getPapePat() + " " + cli.getPapeMat() + " " + cli.getPnombre(), contador, 2);
                tContador.setValueAt(cli.getPdireccionDomicilio(), contador, 3);
                tContador.setValueAt(cli.getCuniversidad(), contador, 4);
                tContador.setValueAt(cli.getCfechaAfiliacion(), contador, 5);
                tContador.setValueAt(cli.getIdCliente(), contador, 6);
                if (cli.getEstado().equals("I")) {
                    tContador.setValueAt("INHABILITADO", contador, 7);
                } else {
                    if (cli.getEstado().equals("H")) {
                        tContador.setValueAt("HABILITADO", contador, 7);
                    } else {
                        if (cli.getEstado().equals("O")) {
                            tContador.setValueAt("HONORARIO", contador, 7);
                        } else {
                            if (cli.getEstado().equals("V")) {
                                tContador.setValueAt("ORDINARIO VITALICIO", contador, 7);
                            } else {
                                if (cli.getEstado().equals("F")) {
                                    tContador.setValueAt("FALLECIDO", contador, 7);
                                } else {
                                    if (cli.getEstado().equals("L")) {
                                        tContador.setValueAt("LICENCIA", contador, 7);
                                    } else {
                                        tContador.setValueAt("RETIRADO", contador, 7);
                                    }
                                }
                            }
                        }
                    }
                }
                contador = contador + 1;
            }
        }

        tDetalle.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
                setBackground(Color.white);//color de fondo
                table.getClass().getCanonicalName();
                table.setForeground(Color.black);//color de texto
                //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
                if (String.valueOf(table.getValueAt(row, 10)).equals("ANULADO")) {
                    setBackground(Color.decode("#ffa0a2"));
                }
                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                return this;
            }
        });
        this.btnReporteWeb.setVisible(false);
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
        txtCodigo = new javax.swing.JTextField();
        txtCodigo.addKeyListener(
            new KeyAdapter(){
                public void keyReleased (KeyEvent e){
                    FiltrarContadorCodigo();
                }} );
                jLabel2 = new javax.swing.JLabel();
                txtNombre = new javax.swing.JTextField();
                txtNombre.addKeyListener(
                    new KeyAdapter(){
                        public void keyReleased (KeyEvent e){
                            FiltrarContadorNombre();
                        }} );
                        btnBuscar = new javax.swing.JButton();
                        jScrollPane1 = new javax.swing.JScrollPane();
                        tContador = new javax.swing.JTable();
                        jLabel3 = new javax.swing.JLabel();
                        lbCodigo = new javax.swing.JLabel();
                        jLabel4 = new javax.swing.JLabel();
                        lbNombre = new javax.swing.JLabel();
                        jTabbedPane1 = new javax.swing.JTabbedPane();
                        jPanel1 = new javax.swing.JPanel();
                        jScrollPane2 = new javax.swing.JScrollPane();
                        tDetalle = new javax.swing.JTable();
                        jPanel2 = new javax.swing.JPanel();
                        jScrollPane3 = new javax.swing.JScrollPane();
                        tCuotas = new javax.swing.JTable();
                        jPanel3 = new javax.swing.JPanel();
                        jScrollPane4 = new javax.swing.JScrollPane();
                        tFinanciamiento = new javax.swing.JTable();
                        jScrollPane5 = new javax.swing.JScrollPane();
                        tDetalleFinanciamiento = new javax.swing.JTable();
                        jLabel5 = new javax.swing.JLabel();
                        jReincorporacion = new javax.swing.JPanel();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        tReincorporaciones = new javax.swing.JTable();
                        pDeudas = new javax.swing.JPanel();
                        jScrollPane7 = new javax.swing.JScrollPane();
                        tDeudas = new javax.swing.JTable();
                        jPanel4 = new javax.swing.JPanel();
                        jScrollPane9 = new javax.swing.JScrollPane();
                        tCurso = new javax.swing.JTable();
                        jPagoDeMas = new javax.swing.JPanel();
                        jScrollPane8 = new javax.swing.JScrollPane();
                        tPagoDeMas = new javax.swing.JTable();
                        btnReporteWeb = new javax.swing.JButton();
                        btnExportarParaContabilidad = new javax.swing.JButton();
                        lbCodigo1 = new javax.swing.JLabel();
                        lbCodigo2 = new javax.swing.JLabel();

                        setClosable(true);
                        setIconifiable(true);
                        setTitle("HISTORIAL CONTADOR");
                        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N

                        jLabel1.setText("CODIGO:");

                        jLabel2.setText("NOMBRE:");

                        btnBuscar.setText("RECARGAR");
                        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarActionPerformed(evt);
                            }
                        });

                        tContador.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "CODIGO", "NOMBRE", "DIRECCION", "UNIVERSIDAD", "FCH AFILI.", "IDCONTADOR", "ESTADO"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tContador.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tContadorMouseClicked(evt);
                            }
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                tContadorMouseEntered(evt);
                            }
                        });
                        jScrollPane1.setViewportView(tContador);

                        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        jLabel3.setText("CODIGO:");

                        lbCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        lbCodigo.setForeground(new java.awt.Color(0, 102, 204));
                        lbCodigo.setText("00000");

                        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        jLabel4.setText("NOMBRE:");

                        lbNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        lbNombre.setForeground(new java.awt.Color(0, 102, 204));
                        lbNombre.setText("----");

                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                        jTabbedPane1.setToolTipText("");

                        tDetalle.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "FECHA", "CMP", "SERIE", "NUM", "DESCRIPCION", "IMPORTE", "IDDOCUMENTO", "IGV", "IDTIPODOC", "ESTADO", "TIPOMONEDA", "COBRADOR"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tDetalleMouseClicked(evt);
                            }
                        });
                        jScrollPane2.setViewportView(tDetalle);

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        );

                        jTabbedPane1.addTab("PAGOS REALIZADOS", jPanel1);

                        tCuotas.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "AÑO", "MES", "IMPORTE", "DOC PAGO", "SERIE", "NRO DOC", "FCH DOC"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        jScrollPane3.setViewportView(tCuotas);

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        );

                        jTabbedPane1.addTab("CUOTAS", jPanel2);

                        tFinanciamiento.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "PRIMER PAGO", "NRO CUOTAS", "MONTO CUOTA", "DESDE", "HASTA", "IDFINANCIAMIENTO"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tFinanciamiento.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tFinanciamientoMouseClicked(evt);
                            }
                        });
                        jScrollPane4.setViewportView(tFinanciamiento);

                        tDetalleFinanciamiento.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "ESTADO", "CUOTA", "MONTO", "DOC PAGO", "SERIE", "NRO DOC", "FCH DOC", "FCH VEN", "D. PRORR."
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, true
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        jScrollPane5.setViewportView(tDetalleFinanciamiento);

                        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel5.setText("DETALLE FINANCIAMIENTO");

                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                        jPanel3.setLayout(jPanel3Layout);
                        jPanel3Layout.setHorizontalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        jPanel3Layout.setVerticalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel5)
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        jTabbedPane1.addTab("FINANCIAMIENTOS", jPanel3);

                        tReincorporaciones.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "PAGO INICIAL", "MONTO TRAMO1", "MONTO TRAMO2", "CUOTAS TRAMO 1", "CUOTAS TRAMO 2", "DESDE", "HASTA", "ID"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tReincorporaciones.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tReincorporacionesMouseClicked(evt);
                            }
                        });
                        jScrollPane6.setViewportView(tReincorporaciones);

                        javax.swing.GroupLayout jReincorporacionLayout = new javax.swing.GroupLayout(jReincorporacion);
                        jReincorporacion.setLayout(jReincorporacionLayout);
                        jReincorporacionLayout.setHorizontalGroup(
                            jReincorporacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                        );
                        jReincorporacionLayout.setVerticalGroup(
                            jReincorporacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        );

                        jTabbedPane1.addTab("REINCORPORACIÓN", jReincorporacion);

                        tDeudas.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "DESCRIPCIÓN", "MONTO", "ESTADO", "DOC PAGO"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tDeudas.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tDeudasMouseClicked(evt);
                            }
                        });
                        tDeudas.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tDeudasKeyPressed(evt);
                            }
                        });
                        jScrollPane7.setViewportView(tDeudas);

                        javax.swing.GroupLayout pDeudasLayout = new javax.swing.GroupLayout(pDeudas);
                        pDeudas.setLayout(pDeudasLayout);
                        pDeudasLayout.setHorizontalGroup(
                            pDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                        );
                        pDeudasLayout.setVerticalGroup(
                            pDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        );

                        jTabbedPane1.addTab("DEUDAS", pDeudas);

                        tCurso.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "OBJ", "NRO", "COD", "CURSO", "FECHA", "TIP DOC", "SER DOC", "NRO DOC", "MONTO"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tCurso.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tCursoMouseClicked(evt);
                            }
                        });
                        jScrollPane9.setViewportView(tCurso);

                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                        jPanel4.setLayout(jPanel4Layout);
                        jPanel4Layout.setHorizontalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9)
                        );
                        jPanel4Layout.setVerticalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        );

                        jTabbedPane1.addTab("CURSOS", jPanel4);

                        tPagoDeMas.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "MONTO", "PROCEDENCIA"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        jScrollPane8.setViewportView(tPagoDeMas);

                        javax.swing.GroupLayout jPagoDeMasLayout = new javax.swing.GroupLayout(jPagoDeMas);
                        jPagoDeMas.setLayout(jPagoDeMasLayout);
                        jPagoDeMasLayout.setHorizontalGroup(
                            jPagoDeMasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                        );
                        jPagoDeMasLayout.setVerticalGroup(
                            jPagoDeMasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        );

                        jTabbedPane1.addTab("PAGO DE MAS", jPagoDeMas);

                        btnReporteWeb.setText("WEB");
                        btnReporteWeb.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnReporteWebActionPerformed(evt);
                            }
                        });

                        btnExportarParaContabilidad.setText("PARA CONT.");
                        btnExportarParaContabilidad.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnExportarParaContabilidadActionPerformed(evt);
                            }
                        });

                        lbCodigo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        lbCodigo1.setForeground(new java.awt.Color(0, 102, 204));
                        lbCodigo1.setText("GENERAR REPORTE");
                        lbCodigo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        lbCodigo1.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbCodigo1MouseClicked(evt);
                            }
                        });

                        lbCodigo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        lbCodigo2.setForeground(new java.awt.Color(0, 102, 204));
                        lbCodigo2.setText("CUENTA CORRIENTE");
                        lbCodigo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        lbCodigo2.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbCodigo2MouseClicked(evt);
                            }
                        });

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jTabbedPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnReporteWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnExportarParaContabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbCodigo2)
                                                .addGap(18, 18, 18)
                                                .addComponent(lbCodigo1)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnExportarParaContabilidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar)
                                        .addComponent(btnReporteWeb)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(lbCodigo)
                                    .addComponent(jLabel4)
                                    .addComponent(lbNombre)
                                    .addComponent(lbCodigo1)
                                    .addComponent(lbCodigo2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        /*int contador = 1;
         AnioMesBO amBO = AnioMesBO.getInstance();
         ConceptoPagoDetalle concepto = new ConceptoPagoDetalle();
         for (int i = 1956; i <= 2020; i++) {
         AnioMes am = new AnioMes();
         am.setAnio(i);
         for (int j = 1; j <= 12; j++) {
         am.setMes(j);
         am.setNroOrden(contador);
         if (i <= 2002) {
         am.setMontoCuota(13.00);
         amBO.InsetarAnioMes(am);
         AnioMesConcepto amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("S");
         amc.setMonto(13.00);
         concepto.setIdConceptoPagoDetalle(2);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         }
         if (i >= 2003 && i <= 2006) {
         am.setMontoCuota(15.00);
         amBO.InsetarAnioMes(am);
         AnioMesConcepto amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("S");
         amc.setMonto(15.00);
         concepto.setIdConceptoPagoDetalle(2);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         }
         if (i == 2007) {
         if (j <= 9) {
         am.setMontoCuota(15.00);
         amBO.InsetarAnioMes(am);
         AnioMesConcepto amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("S");
         amc.setMonto(15.00);
         concepto.setIdConceptoPagoDetalle(2);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         } else {
         am.setMontoCuota(18.00);
         amBO.InsetarAnioMes(am);
         AnioMesConcepto amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("S");
         amc.setMonto(15.00);
         concepto.setIdConceptoPagoDetalle(2);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("N");
         amc.setMonto(1.5);
         concepto.setIdConceptoPagoDetalle(3);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("N");
         amc.setMonto(1.5);
         concepto.setIdConceptoPagoDetalle(4);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         }
         }
         if (i > 2007) {
         am.setMontoCuota(18.00);
         amBO.InsetarAnioMes(am);
         AnioMesConcepto amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("S");
         amc.setMonto(15.00);
         amBO.InsertarAnioMesConcepto(amc);
         amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("N");
         amc.setMonto(1.5);
         concepto.setIdConceptoPagoDetalle(3);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         amc = new AnioMesConcepto();
         amc.setAnioMes(am);
         amc.setEsFinanciado("N");
         amc.setMonto(1.5);
         concepto.setIdConceptoPagoDetalle(4);
         amc.setConceptoPagoDetalle(concepto);
         amBO.InsertarAnioMesConcepto(amc);
         }
         contador = contador + 1;
         }
         }*/
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = dpBO.ObtenerTodosClientesNuevamente();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (Cliente cli : lClientes) {
            if (cli.getTipoCliente().equals("C")) {
                model.setNumRows(contador);
                model.addRow(new Object[contador]);
                tContador.setValueAt(contador + 1, contador, 0);
                tContador.setValueAt(cli.getCcodigoCole(), contador, 1);
                tContador.setValueAt(cli.getPapePat() + " " + cli.getPapeMat() + " " + cli.getPnombre(), contador, 2);
                tContador.setValueAt(cli.getPdireccionDomicilio(), contador, 3);
                tContador.setValueAt(cli.getCuniversidad(), contador, 4);
                tContador.setValueAt(cli.getCfechaAfiliacion(), contador, 5);
                tContador.setValueAt(cli.getIdCliente(), contador, 6);
                if (cli.getEstado().equals("I")) {
                    tContador.setValueAt("INHABILITADO", contador, 7);
                } else {
                    if (cli.getEstado().equals("H")) {
                        tContador.setValueAt("HABILITADO", contador, 7);
                    } else {
                        if (cli.getEstado().equals("O")) {
                            tContador.setValueAt("HONORARIO", contador, 7);
                        } else {
                            if (cli.getEstado().equals("V")) {
                                tContador.setValueAt("ORDINARIO VITALICIO", contador, 7);
                            } else {
                                if (cli.getEstado().equals("F")) {
                                    tContador.setValueAt("FALLECIDO", contador, 7);
                                } else {
                                    if (cli.getEstado().equals("L")) {
                                        tContador.setValueAt("LICENCIA", contador, 7);
                                    } else {
                                        tContador.setValueAt("RETIRADO", contador, 7);
                                    }
                                }
                            }
                        }
                    }
                }
                contador = contador + 1;
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void FiltrarContadorCodigo() {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
        if (!lClientes.isEmpty()) {
            String pFiltro = txtCodigo.getText();
            pFiltro = pFiltro.toUpperCase();
            txtCodigo.setText(pFiltro);
            DefaultTableModel model = (DefaultTableModel) tContador.getModel();
            int contador = 0;
            for (Cliente cliente : lClientes) {
                if (cliente.getTipoCliente().equals("C")) {
                    if (cliente.getCcodigoCole().contains(pFiltro)) {
                        model.setNumRows(contador);
                        model.addRow(new Object[contador]);
                        tContador.setValueAt(contador + 1, contador, 0);
                        tContador.setValueAt(cliente.getCcodigoCole(), contador, 1);
                        tContador.setValueAt(cliente.getPapePat() + " " + cliente.getPapeMat() + " " + cliente.getPnombre(), contador, 2);
                        tContador.setValueAt(cliente.getPdireccionDomicilio(), contador, 3);
                        tContador.setValueAt(cliente.getCuniversidad(), contador, 4);
                        tContador.setValueAt(cliente.getCfechaAfiliacion(), contador, 5);
                        tContador.setValueAt(cliente.getIdCliente(), contador, 6);
                        if (cliente.getEstado().equals("I")) {
                            tContador.setValueAt("INHABILITADO", contador, 7);
                        } else {
                            if (cliente.getEstado().equals("H")) {
                                tContador.setValueAt("HABILITADO", contador, 7);
                            } else {
                                if (cliente.getEstado().equals("O")) {
                                    tContador.setValueAt("HONORARIO", contador, 7);
                                } else {
                                    if (cliente.getEstado().equals("V")) {
                                        tContador.setValueAt("ORDINARIO VITALICIO", contador, 7);
                                    } else {
                                        if (cliente.getEstado().equals("F")) {
                                            tContador.setValueAt("FALLECIDO", contador, 7);
                                        } else {
                                            if (cliente.getEstado().equals("L")) {
                                                tContador.setValueAt("LICENCIA", contador, 7);
                                            } else {
                                                tContador.setValueAt("RETIRADO", contador, 7);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        contador = contador + 1;
                    }
                }
            }
        }
    }

    private void FiltrarContadorNombre() {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
        if (!lClientes.isEmpty()) {
            String pFiltro = txtNombre.getText();
            pFiltro = pFiltro.toUpperCase();
            txtNombre.setText(pFiltro);
            DefaultTableModel model = (DefaultTableModel) tContador.getModel();
            int contador = 0;
            String nombre;
            for (Cliente cliente : lClientes) {
                if (cliente.getTipoCliente().equals("C")) {
                    nombre = cliente.getPapePat() + " " + cliente.getPapeMat() + " " + cliente.getPnombre();
                    if (nombre.contains(pFiltro)) {
                        model.setNumRows(contador);
                        model.addRow(new Object[contador]);
                        tContador.setValueAt(contador + 1, contador, 0);
                        tContador.setValueAt(cliente.getCcodigoCole(), contador, 1);
                        tContador.setValueAt(cliente.getPapePat() + " " + cliente.getPapeMat() + " " + cliente.getPnombre(), contador, 2);
                        tContador.setValueAt(cliente.getPdireccionDomicilio(), contador, 3);
                        tContador.setValueAt(cliente.getCuniversidad(), contador, 4);
                        tContador.setValueAt(cliente.getCfechaAfiliacion(), contador, 5);
                        tContador.setValueAt(cliente.getIdCliente(), contador, 6);
                        if (cliente.getEstado().equals("I")) {
                            tContador.setValueAt("INHABILITADO", contador, 7);
                        } else {
                            if (cliente.getEstado().equals("H")) {
                                tContador.setValueAt("HABILITADO", contador, 7);
                            } else {
                                if (cliente.getEstado().equals("O")) {
                                    tContador.setValueAt("HONORARIO", contador, 7);
                                } else {
                                    if (cliente.getEstado().equals("V")) {
                                        tContador.setValueAt("ORDINARIO VITALICIO", contador, 7);
                                    } else {
                                        if (cliente.getEstado().equals("F")) {
                                            tContador.setValueAt("FALLECIDO", contador, 7);
                                        } else {
                                            if (cliente.getEstado().equals("L")) {
                                                tContador.setValueAt("LICENCIA", contador, 7);
                                            } else {
                                                tContador.setValueAt("RETIRADO", contador, 7);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        contador = contador + 1;
                    }
                }
            }
        }
    }

    private String ObtenerMes(int mes) {
        String pmes = "";
        if (mes == 1) {
            pmes = "ENERO";
        }
        if (mes == 2) {
            pmes = "FEBRERO";
        }
        if (mes == 3) {
            pmes = "MARZO";
        }
        if (mes == 4) {
            pmes = "ABRIL";
        }
        if (mes == 5) {
            pmes = "MAYO";
        }
        if (mes == 6) {
            pmes = "JUNIO";
        }
        if (mes == 7) {
            pmes = "JULIO";
        }
        if (mes == 8) {
            pmes = "AGOSTO";
        }
        if (mes == 9) {
            pmes = "SEPTIEMBRE";
        }
        if (mes == 10) {
            pmes = "OCTUBRE";
        }
        if (mes == 11) {
            pmes = "NOVIEMBRE";
        }
        if (mes == 12) {
            pmes = "DICIEMBRE";
        }
        return pmes;
    }

    private void CargarDetalleHistorial() {
        /*frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();*/
        int fila = tContador.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        String idContador = String.valueOf(model.getValueAt(fila, 6));
        String codigo = String.valueOf(model.getValueAt(fila, 1));
        String nombre = String.valueOf(model.getValueAt(fila, 2));
        //fPrin.AbrirFormularioDetalleHistorico(Integer.valueOf(idContador), codigo, nombre);
        lbCodigo.setText(codigo);
        lbNombre.setText(nombre);
        ClienteBO cBO = ClienteBO.getInstance();
        List lPagos = cBO.ObtenerHistorialPagos(Integer.valueOf(idContador));
        model = (DefaultTableModel) tDetalle.getModel();
        int contador = 0;
        double importe = 0;
        model.setNumRows(0);
        String observacion = "";
        for (Object pobj : lPagos) {
            Object[] obj = (Object[]) pobj;
            /*importe = (int) obj[2] * (double) obj[3];
             importe = Math.round(importe * Math.pow(10, 2)) / Math.pow(10, 2);*/
            importe = (double) obj[12];
            importe = Math.round(importe * Math.pow(10, 2)) / Math.pow(10, 2);
            if (obj[4] != null) {
                observacion = " " + (String) obj[4];
            }
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tDetalle.setValueAt(contador + 1, contador, 0);
            tDetalle.setValueAt(obj[0], contador, 1);
            tDetalle.setValueAt((String) obj[5], contador, 2);
            //tDetalle.setValueAt(String.valueOf((int) obj[6]), contador, 3);
            tDetalle.setValueAt((String) obj[6], contador, 3);
            tDetalle.setValueAt(String.format("%07d", (int) obj[7]), contador, 4);
            tDetalle.setValueAt((String) obj[1] + observacion, contador, 5);
            tDetalle.setValueAt(importe, contador, 6);
            tDetalle.setValueAt((int) obj[8], contador, 7);
            tDetalle.setValueAt((String) obj[9], contador, 8);
            tDetalle.setValueAt((int) obj[10], contador, 9);
            tDetalle.setValueAt((String) obj[11], contador, 10);
            tDetalle.setValueAt((String) obj[13], contador, 11);
            if (obj[14] != null) {
                tDetalle.setValueAt((String) obj[14], contador, 12);
            }
            //tDetalleHistorial.setValueAt(c.getUniversidad(), contador, 4);
            contador = contador + 1;
            observacion = "";
        }
    }

    private void CargarCursos() {
        /*frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();*/
        int fila = tContador.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        String codigo = String.valueOf(model.getValueAt(fila, 1));
        String nombre = String.valueOf(model.getValueAt(fila, 2));
        int idContador = Integer.valueOf(String.valueOf(model.getValueAt(fila, 6)));
        //fPrin.AbrirFormularioDetalleHistorico(Integer.valueOf(idContador), codigo, nombre);
        lbCodigo.setText(codigo);
        lbNombre.setText(nombre);
        ClienteBO cBO = ClienteBO.getInstance();
        List lCursos = cBO.ObtenerCursos(idContador);
        model = (DefaultTableModel) tCurso.getModel();
        int contador = 0;
        double importe = 0;
        model.setNumRows(0);
        String observacion = "";
        for (Object pobj : lCursos) {
            Object[] obj = (Object[]) pobj;
            importe = (double) obj[7];
            importe = Math.round(importe * Math.pow(10, 2)) / Math.pow(10, 2);
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tCurso.setValueAt(obj[0], contador, 0);
            tCurso.setValueAt(contador + 1, contador, 1);
            tCurso.setValueAt((String) obj[1], contador, 2);
            tCurso.setValueAt((String) obj[2], contador, 3);
            tCurso.setValueAt((Date) obj[3], contador, 4);
            tCurso.setValueAt((String) obj[4], contador, 5);
            tCurso.setValueAt((String) obj[5], contador, 6);
            tCurso.setValueAt(String.format("%07d", (int) obj[6]), contador, 7);
            tCurso.setValueAt(importe, contador, 8);
            contador = contador + 1;
            observacion = "";
        }
    }

    public void CargarTodasCuotas() {
        CuotasBO cBO = CuotasBO.getInstance();
        int fila = tContador.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        int idContador = Integer.valueOf(String.valueOf(model.getValueAt(fila, 6)));
        this.idCliente = idContador;
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
        Cliente cliente = null;
        for (Cliente cli : lClientes) {
//            if(cli.)
            if (cli.getIdCliente() == idContador) {
                cliente = cli;
                break;
            }
        }

        List<Object> lCuotas = cBO.ObtenerTodasCuotasCanceladas(cliente);
        model = (DefaultTableModel) tCuotas.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (Object pobj : lCuotas) {
            Object[] obj = (Object[]) pobj;
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tCuotas.setValueAt(contador + 1, contador, 0);
            tCuotas.setValueAt(obj[1], contador, 1);
            tCuotas.setValueAt(this.ObtenerMes((int) obj[2]), contador, 2);
            tCuotas.setValueAt(obj[3], contador, 3);
            tCuotas.setValueAt(obj[4], contador, 4);
            tCuotas.setValueAt(obj[5], contador, 5);
            tCuotas.setValueAt(obj[6], contador, 6);
            tCuotas.setValueAt(obj[7], contador, 7);
            //tDetalleHistorial.setValueAt(c.getUniversidad(), contador, 4);
            contador = contador + 1;
        }
    }

    public void CargarFinanciamientos() {
        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
        int fila = tContador.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        String idContador = String.valueOf(model.getValueAt(fila, 6));
        List<Financiamiento> lFinanciamiento = fBO.ObtenerTodosFinanciamientos(Integer.valueOf(idContador));
        model = (DefaultTableModel) tFinanciamiento.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (Financiamiento f : lFinanciamiento) {
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tFinanciamiento.setValueAt(contador + 1, contador, 0);
            tFinanciamiento.setValueAt(f.getPrimerPago(), contador, 1);
            tFinanciamiento.setValueAt(f.getNroCuotas(), contador, 2);
            tFinanciamiento.setValueAt(f.getMontoCadaCuota(), contador, 3);
            tFinanciamiento.setValueAt(f.getAnioDesde() + " " + this.ObtenerMes(f.getMesDesde()), contador, 4);
            tFinanciamiento.setValueAt(f.getAnioHasta() + " " + this.ObtenerMes(f.getMesHasta()), contador, 5);
            tFinanciamiento.setValueAt(f.getIdFinanciamiento(), contador, 6);
            contador = contador + 1;
        }

        model = (DefaultTableModel) tDetalleFinanciamiento.getModel();
        model.setNumRows(0);
    }

    public void CargarDetalleFinanciamientos() {
        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
        int fila = tFinanciamiento.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tFinanciamiento.getModel();
        String idFinanciamiento = String.valueOf(model.getValueAt(fila, 6));
        List<Object> lDetalleFinanciamiento = fBO.ObtenerDetalleFinanciamiento(Integer.valueOf(idFinanciamiento));
        model = (DefaultTableModel) tDetalleFinanciamiento.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (Object pobj : lDetalleFinanciamiento) {
            Object[] obj = (Object[]) pobj;
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tDetalleFinanciamiento.setValueAt(contador + 1, contador, 0);
            if (((String) obj[0]).equals("CI")) {
                tDetalleFinanciamiento.setValueAt("CUOTA INICIAL", contador, 1);
            }
            if (((String) obj[0]).equals("FC")) {
                tDetalleFinanciamiento.setValueAt("F. CANCELADO", contador, 1);
            }
            if (((String) obj[0]).equals("FS")) {
                tDetalleFinanciamiento.setValueAt("F. PENDIENTE PAGO", contador, 1);
            }
            if (((String) obj[0]).equals("FR")) {
                tDetalleFinanciamiento.setValueAt("REINCORPORACIÓN", contador, 1);
            }
            tDetalleFinanciamiento.setValueAt(obj[1], contador, 2);
            tDetalleFinanciamiento.setValueAt(obj[2], contador, 3);
            tDetalleFinanciamiento.setValueAt(obj[3], contador, 4);
            tDetalleFinanciamiento.setValueAt(obj[4], contador, 5);
            tDetalleFinanciamiento.setValueAt(obj[5], contador, 6);
            tDetalleFinanciamiento.setValueAt(obj[6], contador, 7);
            tDetalleFinanciamiento.setValueAt(obj[7], contador, 8);
            tDetalleFinanciamiento.setValueAt(obj[8], contador, 9);
            contador = contador + 1;
        }
    }

    public void CargarReincorporaciones() {
        ReincorporacionBO rBO = ReincorporacionBO.getInstance();
        int fila = tContador.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        String idContador = String.valueOf(model.getValueAt(fila, 6));
        List<Reincorporacion> lReincorporacion = rBO.ObtenerTodosReincorporaciones(Integer.valueOf(idContador));
        model = (DefaultTableModel) tReincorporaciones.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (Reincorporacion r : lReincorporacion) {
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tReincorporaciones.setValueAt(contador + 1, contador, 0);
            tReincorporaciones.setValueAt(r.getMontoPagoIni(), contador, 1);
            tReincorporaciones.setValueAt(r.getMontoPagarTramo1(), contador, 2);
            tReincorporaciones.setValueAt(r.getMontoPagarTramo2(), contador, 3);
            tReincorporaciones.setValueAt(r.getNroCuotasTramo1(), contador, 4);
            tReincorporaciones.setValueAt(r.getNroCuotasTramo2(), contador, 5);
            tReincorporaciones.setValueAt(r.getAnioDesde(), contador, 6);
            tReincorporaciones.setValueAt(r.getAnioHasta(), contador, 7);
            tReincorporaciones.setValueAt(r.getId(), contador, 8);
            contador = contador + 1;
        }
    }

    public void CargarDeudas() {
        DeudasBO dBO = DeudasBO.getInstance();
        int fila = tContador.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        String idContador = String.valueOf(model.getValueAt(fila, 6));
        List<Deuda> lDeuda = dBO.ObtenerTodasDeudas(Integer.valueOf(idContador));
        model = (DefaultTableModel) tDeudas.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (Deuda d : lDeuda) {
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tDeudas.setValueAt(contador + 1, contador, 0);
            tDeudas.setValueAt(d.getConceptoPagoDetalle().getDescripcion(), contador, 1);
            tDeudas.setValueAt(d.getMonto(), contador, 2);
            if (d.getEstado().equals("DC")) {
                tDeudas.setValueAt("DEUDA CANCELADA", contador, 3);
            } else {
                if (d.getEstado().equals("DF")) {
                    tDeudas.setValueAt("DEUDA FINANCIADA", contador, 3);
                } else {
                    if (d.getEstado().equals("DP")) {
                        tDeudas.setValueAt("DEUDA PENDIENTE", contador, 3);
                    } else {
                        if (d.getEstado().equals("DA")) {
                            tDeudas.setValueAt("DEUDA ABSUELTA", contador, 3);
                        } else {
                            tDeudas.setValueAt("REINCORPORACIÓN", contador, 3);
                        }
                    }
                }
            }
            contador = contador + 1;
        }
    }

    public void CargarPagoDeMas() {
        PagoDeMasBO pdmBO = PagoDeMasBO.getInstance();
        int fila = tContador.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        String idContador = String.valueOf(model.getValueAt(fila, 6));
        List<PagoDeMas> lPagoDeMas = pdmBO.ObtenerTodosPagoDeMas(Integer.valueOf(idContador));
        model = (DefaultTableModel) tPagoDeMas.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (PagoDeMas pdm : lPagoDeMas) {
            model.setNumRows(contador);
            model.addRow(new Object[contador]);
            tPagoDeMas.setValueAt(contador + 1, contador, 0);
            tPagoDeMas.setValueAt(pdm.getMonto(), contador, 1);
            if (pdm.getProcedencia().equals("F")) {
                tPagoDeMas.setValueAt("FINANCIAMIENTO", contador, 2);
            } else {
                tPagoDeMas.setValueAt("REINCORPORACION", contador, 2);
            }
            contador = contador + 1;
        }
    }

    private void tContadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tContadorMouseClicked
        if (evt.getClickCount() == 2) {
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
                    CargarDetalleHistorial();
                    CargarTodasCuotas();
                    CargarFinanciamientos();
                    CargarReincorporaciones();
                    CargarDeudas();
                    CargarPagoDeMas();
                    CargarCursos();
                    fCargando.dispose();
                }
            };
            queryThread.start();
        }
    }//GEN-LAST:event_tContadorMouseClicked

    private void tFinanciamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFinanciamientoMouseClicked
        if (evt.getClickCount() == 2) {
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
                    CargarDetalleFinanciamientos();
                    fCargando.dispose();
                }
            };
            queryThread.start();
        }
    }//GEN-LAST:event_tFinanciamientoMouseClicked

    private void tContadorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tContadorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tContadorMouseEntered

    public void CargarDocumentoPago() {
        try {
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
//            List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
//            Cliente cliente = null;
//            for (Cliente cli : lClientes) {
//                if (cli.getTipoCliente().equals("C")) {
//                    if (cli.getCcodigoCole().equals(lbCodigo.getText())) {
//                        cliente = cli;
//                        break;
//                    }
//                }
//            }
//            DocumentoPago doc = new DocumentoPago();
            int fila = tDetalle.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
            int idDocumentoPago = Integer.valueOf(model.getValueAt(fila, 7).toString());
            SeguridadBO sBO = SeguridadBO.getINSTANCE();
            DocumentoPago doc = (DocumentoPago) sBO.CargarObjeto("DocumentoPago", idDocumentoPago);
            Cliente cliente = (Cliente) sBO.CargarObjeto("Cliente", doc.getCliente().getIdCliente());
            doc.setCliente(cliente);
            if (doc.getClienteByIdContadorEmpresa() != null) {
                Cliente clienteEmpresa = (Cliente) sBO.CargarObjeto("Cliente", doc.getClienteByIdContadorEmpresa().getIdCliente());
                doc.setClienteByIdContadorEmpresa(clienteEmpresa);
            }
            String nroSerie = String.valueOf(model.getValueAt(fila, 3));
            String nroComprobante = String.valueOf(model.getValueAt(fila, 4));
            String tipoComprobante = String.valueOf(model.getValueAt(fila, 2));
            String fechaComprobante = String.valueOf(model.getValueAt(fila, 1));
            String idTipoDoc = String.valueOf(model.getValueAt(fila, 9));
            String tieneIGV = String.valueOf(model.getValueAt(fila, 8));
            String estado = String.valueOf(model.getValueAt(fila, 10));
            String tipoMoneda = String.valueOf(model.getValueAt(fila, 11));
            TipoDocPago tipoDoc = new TipoDocPago();
            tipoDoc.setIdTipoDocPago(Integer.valueOf(idTipoDoc));
            tipoDoc.setNombreDocPago(tipoComprobante);
            doc.setIdDocumentoPago(idDocumentoPago);
            doc.setNroSerie(nroSerie);
            doc.setNroDocumentoPago(Integer.valueOf(nroComprobante));
            doc.setTieneIgv(tieneIGV);
            TipoDocSerie tds = dpBO.ObtenerTipoDocSerie(Integer.valueOf(idTipoDoc), nroSerie);
            doc.setTipoDocSerie(tds);
            doc.setEstado(estado);
            doc.setMoneda(tipoMoneda);
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formateador.parse(fechaComprobante);
            doc.setFechaPago(date);
            doc.setCliente(cliente);
            Cobrador cobrador = dpBO.ObtenerCobradorComprobantePago(doc.getIdDocumentoPago());
            if (cobrador != null) {
                doc.setCobrador(cobrador);
            }
//            dpBO.setDocumentoPago(doc);
            frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            fPrin.AbrirFormularioDetalleComprobantePago(doc, cliente.getTipoCliente(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CargarDocumentoPagoSegunCurso() {
        try {
            int fila = tCurso.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tCurso.getModel();
            int idDocumentoPago = Integer.valueOf(model.getValueAt(fila, 0).toString());
            SeguridadBO sBO = SeguridadBO.getINSTANCE();
            DocumentoPago doc = (DocumentoPago) sBO.CargarObjeto("DocumentoPago", idDocumentoPago);
            Cliente cliente = (Cliente) sBO.CargarObjeto("Cliente", doc.getCliente().getIdCliente());
            doc.setCliente(cliente);
            TipoDocSerie tds = (TipoDocSerie) sBO.CargarObjeto("TipoDocSerie", doc.getTipoDocSerie().getId());
            doc.setTipoDocSerie(tds);
            TipoDocPago td = (TipoDocPago) sBO.CargarObjeto("TipoDocPago", doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago());
            doc.getTipoDocSerie().setTipoDocPago(td);
            if (doc.getCobrador() != null) {
                Cobrador cobrador = (Cobrador) sBO.CargarObjeto("Cobrador", doc.getCobrador().getIdCobrador());
                doc.setCobrador(cobrador);
                Cliente cobradorCliente = (Cliente) sBO.CargarObjeto("Cliente", doc.getCobrador().getCliente().getIdCliente());
                doc.getCobrador().setCliente(cobradorCliente);
            }
            frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            fPrin.AbrirFormularioDetalleComprobantePago(doc, cliente.getTipoCliente(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleMouseClicked
        if (evt.getClickCount() == 2) {
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
                    CargarDocumentoPago();
                    fCargando.dispose();
                }
            };
            queryThread.start();
        }
    }//GEN-LAST:event_tDetalleMouseClicked

    private void tReincorporacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tReincorporacionesMouseClicked
        if (evt.getClickCount() == 2) {
            frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            int fila = tReincorporaciones.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tReincorporaciones.getModel();
            int idReincorporacion = Integer.valueOf(model.getValueAt(fila, 8).toString());
            fPrin.AbrirDialogoReincorporacion(idReincorporacion);
        }
    }//GEN-LAST:event_tReincorporacionesMouseClicked

    private void btnReporteWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteWebActionPerformed
        try {
            ClienteBO cBO = ClienteBO.getInstance();
            List<Cliente> lContadores = cBO.ObtenerTodosContadores();
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            SimpleDateFormat fFecha = new SimpleDateFormat("yyyy-MM-dd");
            Workbook libro = new HSSFWorkbook();
            FileOutputStream archivo = new FileOutputStream("archivos/WEB/WEB_" + formato.format(fechaSistema) + ".xls");
            Sheet hoja = libro.createSheet("WEB");
            int contador = 0;
            Row fila = hoja.createRow((short) contador);
            Cell celda = fila.createCell((short) 0);
            celda.setCellValue("AS_DNI");
            celda = fila.createCell((short) 1);
            celda.setCellValue("AS_MATRIC");
            celda = fila.createCell((short) 2);
            celda.setCellValue("AS_PASSW");
            celda = fila.createCell((short) 3);
            celda.setCellValue("AS_FECAFI");
            celda = fila.createCell((short) 4);
            celda.setCellValue("AS_APELLI");
            celda = fila.createCell((short) 5);
            celda.setCellValue("AS_NOMBRE");
            celda = fila.createCell((short) 6);
            celda.setCellValue("AS_DIRECC");
            celda = fila.createCell((short) 7);
            celda.setCellValue("AS_TRABAJ");
            celda = fila.createCell((short) 8);
            celda.setCellValue("AS_DIRTRA");
            celda = fila.createCell((short) 9);
            celda.setCellValue("AS_TELEFO");
            celda = fila.createCell((short) 10);
            celda.setCellValue("AS_TELCEL");
            celda = fila.createCell((short) 11);
            celda.setCellValue("AS_TELTRA");
            celda = fila.createCell((short) 12);
            celda.setCellValue("AS_CONDIC");
            celda = fila.createCell((short) 13);
            celda.setCellValue("AS_CUOTA");
            celda = fila.createCell((short) 14);
            celda.setCellValue("AS_MES_CO");
            celda = fila.createCell((short) 15);
            celda.setCellValue("AS_ANO_CO");
            celda = fila.createCell((short) 16);
            celda.setCellValue("AS_FONDO1");
            celda = fila.createCell((short) 17);
            celda.setCellValue("AS_MES_FO");
            celda = fila.createCell((short) 18);
            celda.setCellValue("AS_ANO_FO");
            celda = fila.createCell((short) 19);
            celda.setCellValue("AS_FONDO");
            celda = fila.createCell((short) 20);
            celda.setCellValue("AS_FECNAC");
            celda = fila.createCell((short) 21);
            celda.setCellValue("AS_EMAIL");
            celda = fila.createCell((short) 22);
            celda.setCellValue("AS_BUZON");
            celda = fila.createCell((short) 23);
            celda.setCellValue("PUBLICAR");
            celda = fila.createCell((short) 24);
            celda.setCellValue("CERTIFICAD");
            contador = contador + 1;
            for (Cliente c : lContadores) {
                //System.out.print("ID: " + c.getIdCliente());
                /*Row fila = hoja.createRow((short) contador);
                 Cell celda = fila.createCell((short) 0);*/
                fila = hoja.createRow((short) contador);

                celda = fila.createCell((short) 0);
                celda.setCellValue(c.getPnroDocumento());
                celda = fila.createCell((short) 1);
                if (c.getCcodigoCole() != null) {
                    celda.setCellValue(c.getCcodigoCole());
                }
                celda = fila.createCell((short) 2);
                celda.setCellValue(c.getCpasswd());
                celda = fila.createCell((short) 3);
                if (c.getCfechaAfiliacion() != null) {
                    celda.setCellValue(fFecha.format(c.getCfechaAfiliacion()));
                }
                celda = fila.createCell((short) 4);
                String apePat = "";
                String apeMat = "";
                if (c.getPapePat() != null) {
                    apePat = c.getPapePat();
                }
                if (c.getPapeMat() != null) {
                    apeMat = c.getPapeMat();
                }
                celda.setCellValue(apePat + " " + apeMat);
                celda = fila.createCell((short) 5);
                celda.setCellValue(c.getPnombre());
                celda = fila.createCell((short) 6);
                celda.setCellValue(c.getPdireccionDomicilio());
                celda = fila.createCell((short) 7);
                celda.setCellValue("");
                celda = fila.createCell((short) 8);
                celda.setCellValue(c.getPdireccionTrabajo());
                celda = fila.createCell((short) 9);
                celda.setCellValue(c.getPtelefonoDomicilio());
                celda = fila.createCell((short) 10);
                celda.setCellValue(c.getPtelefonoCelular());
                celda = fila.createCell((short) 11);
                celda.setCellValue(c.getPtelefonoTrabajo());
                celda = fila.createCell((short) 12);
                if (c.getEstado() != null) {
                    if (c.getEstado().equals("H")) {
                        celda.setCellValue("A");
                    } else {
                        if (c.getEstado().equals("O")) {
                            celda.setCellValue("H");
                        } else {
                            celda.setCellValue(c.getEstado());
                        }
                    }
                }
                celda = fila.createCell((short) 13);
                celda.setCellValue("");
                celda = fila.createCell((short) 14);
                celda.setCellValue("");
                celda = fila.createCell((short) 15);
                celda.setCellValue("");
                celda = fila.createCell((short) 16);
                celda.setCellValue("");
                celda = fila.createCell((short) 17);
                celda.setCellValue("");
                celda = fila.createCell((short) 18);
                celda.setCellValue("");
                celda = fila.createCell((short) 19);
                celda.setCellValue("");
                celda = fila.createCell((short) 20);
                if (c.getPfechaNac() != null) {
                    celda.setCellValue(fFecha.format(c.getPfechaNac()));
                }
                celda = fila.createCell((short) 21);
                celda.setCellValue(c.getCemail());
                celda = fila.createCell((short) 22);
                celda.setCellValue("");
                celda = fila.createCell((short) 23);
                celda.setCellValue("NO");
                celda = fila.createCell((short) 24);
                if (c.getCesCertificado() != null) {
                    if (c.getCesCertificado().equals("S")) {
                        celda.setCellValue("SI");
                    } else {
                        celda.setCellValue("NO");
                    }
                } else {
                    celda.setCellValue("NO");
                }
                contador++;
            }
            /*hoja.setColumnWidth(0, 1070);
             hoja.setColumnWidth(1, 3930);
             hoja.setColumnWidth(3, 2930);
             hoja.setColumnWidth(4, 6290);
             hoja.setColumnWidth(7, 2930);
             hoja.setColumnWidth(8, 2930);*/
            libro.write(archivo);
            archivo.close();
            JOptionPane.showMessageDialog(this,
                    "SE GENERÓ EL ARCHIVO",
                    "ERROR",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {

            e.printStackTrace();

        }

        /*try {
         ClienteBO cBO = ClienteBO.getInstance();
         List<Cliente> lContadores = cBO.ObtenerTodosContadores();
         //for (Object pobj : listadoPersonalPlanilla) {
         Date fechaSistema = new Date();
         SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
         SimpleDateFormat fFecha = new SimpleDateFormat("yyyy-MM-dd");
         Workbook libro = new HSSFWorkbook();
         FileOutputStream archivo = new FileOutputStream("archivos/WEB/WEB_" + formato.format(fechaSistema) + ".xls");
         Sheet hoja = libro.createSheet("WEB");
         int contador = 0;
         Row fila = hoja.createRow((short) contador);
         Cell celda = fila.createCell((short) 0);
         celda.setCellValue("NRO");
         celda = fila.createCell((short) 1);
         celda.setCellValue("AS_DNI");
         celda = fila.createCell((short) 2);
         celda.setCellValue("AS_MATRIC");
         celda = fila.createCell((short) 3);
         celda.setCellValue("AS_PASSW");
         celda = fila.createCell((short) 4);
         celda.setCellValue("AS_FECAFI");
         celda = fila.createCell((short) 5);
         celda.setCellValue("AS_APELLI");
         celda = fila.createCell((short) 6);
         celda.setCellValue("AS_NOMBRE");
         celda = fila.createCell((short) 7);
         celda.setCellValue("AS_DIRECC");
         celda = fila.createCell((short) 8);
         celda.setCellValue("AS_TRABAJ");
         celda = fila.createCell((short) 9);
         celda.setCellValue("AS_DIRTRA");
         celda = fila.createCell((short) 10);
         celda.setCellValue("AS_TELEFO");
         celda = fila.createCell((short) 11);
         celda.setCellValue("AS_TELCEL");
         celda = fila.createCell((short) 12);
         celda.setCellValue("AS_TELTRA");
         celda = fila.createCell((short) 13);
         celda.setCellValue("AS_CONDIC");
         celda = fila.createCell((short) 14);
         celda.setCellValue("AS_CUOTA");
         celda = fila.createCell((short) 15);
         celda.setCellValue("AS_MES_CO");
         celda = fila.createCell((short) 16);
         celda.setCellValue("AS_ANO_CO");
         celda = fila.createCell((short) 17);
         celda.setCellValue("AS_FONDO1");
         celda = fila.createCell((short) 18);
         celda.setCellValue("AS_MES_FO");
         celda = fila.createCell((short) 19);
         celda.setCellValue("AS_ANO_FO");
         celda = fila.createCell((short) 20);
         celda.setCellValue("AS_FONDO");
         celda = fila.createCell((short) 21);
         celda.setCellValue("AS_FECNAC");
         celda = fila.createCell((short) 22);
         celda.setCellValue("AS_EMAIL");
         celda = fila.createCell((short) 23);
         celda.setCellValue("AS_BUZON");
         celda = fila.createCell((short) 24);
         celda.setCellValue("PUBLICAR");
         celda = fila.createCell((short) 25);
         celda.setCellValue("CERTIFICAD");
         contador = contador + 1;
         for (Cliente c : lContadores) {
         //System.out.print("ID: " + c.getIdCliente());
         fila = hoja.createRow((short) contador);
         celda = fila.createCell((short) 0);
         celda.setCellValue(contador + 1);
         celda = fila.createCell((short) 1);
         celda.setCellValue("");
         celda = fila.createCell((short) 2);
         if (c.getCcodigoCole() != null) {
         celda.setCellValue(c.getCcodigoCole());
         }
         celda = fila.createCell((short) 3);
         celda.setCellValue(c.getCpasswd());
         celda = fila.createCell((short) 4);
         if (c.getCfechaAfiliacion() != null) {
         celda.setCellValue(fFecha.format(c.getCfechaAfiliacion()));
         }
         celda = fila.createCell((short) 5);
         String apePat = "";
         String apeMat = "";
         if (c.getPapePat() != null) {
         apePat = c.getPapePat();
         }
         if (c.getPapeMat() != null) {
         apeMat = c.getPapeMat();
         }
         celda.setCellValue(apePat + " " + apeMat);
         celda = fila.createCell((short) 6);
         celda.setCellValue(c.getPnombre());
         celda = fila.createCell((short) 7);
         celda.setCellValue(c.getPdireccionDomicilio());
         celda = fila.createCell((short) 8);
         celda.setCellValue("");
         celda = fila.createCell((short) 9);
         celda.setCellValue(c.getPdireccionTrabajo());
         celda = fila.createCell((short) 10);
         celda.setCellValue(c.getPtelefonoDomicilio());
         celda = fila.createCell((short) 11);
         celda.setCellValue(c.getPtelefonoCelular());
         celda = fila.createCell((short) 12);
         celda.setCellValue(c.getPtelefonoTrabajo());
         celda = fila.createCell((short) 13);
         if (c.getEstado() != null) {
         if (c.getEstado().equals("H")) {
         celda.setCellValue("A");
         } else {
         if (c.getEstado().equals("O")) {
         celda.setCellValue("H");
         } else {
         celda.setCellValue(c.getEstado());
         }
         }
         }
         celda = fila.createCell((short) 14);
         celda.setCellValue("");
         celda = fila.createCell((short) 15);
         celda.setCellValue("");
         celda = fila.createCell((short) 16);
         celda.setCellValue("");
         celda = fila.createCell((short) 17);
         celda.setCellValue("");
         celda = fila.createCell((short) 18);
         celda.setCellValue("");
         celda = fila.createCell((short) 19);
         celda.setCellValue("");
         celda = fila.createCell((short) 20);
         celda.setCellValue("");
         celda = fila.createCell((short) 21);
         if (c.getPfechaNac() != null) {
         celda.setCellValue(fFecha.format(c.getPfechaNac()));
         }
         celda = fila.createCell((short) 22);
         celda.setCellValue(c.getCemail());
         celda = fila.createCell((short) 23);
         celda.setCellValue("");
         celda = fila.createCell((short) 24);
         celda.setCellValue("NO");
         celda = fila.createCell((short) 25);
         if (c.getCesCertificado() != null) {
         if (c.getCesCertificado().equals("S")) {
         celda.setCellValue("SI");
         } else {
         celda.setCellValue("NO");
         }
         } else {
         celda.setCellValue("NO");
         }
         contador++;
         }
         libro.write(archivo);
         archivo.close();
         JOptionPane.showMessageDialog(this,
         "SE GENERÓ EL ARCHIVO",
         "ERROR",
         JOptionPane.INFORMATION_MESSAGE);
         } catch (IOException e) {

         e.printStackTrace();

         }*/
    }//GEN-LAST:event_btnReporteWebActionPerformed

    private void generarArchivoClientes() {
        try {
            ClienteBO cBO = ClienteBO.getInstance();
            List<Cliente> lClientes = cBO.ObtenerTodosClientes();
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            File f;
            f = new File("archivos/CONT/clientes_" + formato.format(fechaSistema) + ".txt");
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            String tipoPersona = "";
            String exportacion = "";
            String codigoPersona = "";
            String nombre = "";
            for (Cliente c : lClientes) {
//                if (c.getTipoCliente().equals("C")) {
//                    tipoPersona = "T";
//                    codigoPersona = c.getCcodigoCole();
//                    nombre = c.getPapePat() + " " + c.getPapeMat() + " " + c.getPnombre();
//                }
                if (c.getTipoCliente().equals("S")) {
                    tipoPersona = "S";
                    codigoPersona = c.getScodigoSoc();
                    nombre = c.getSnombreSociedad();
                }
                if (c.getTipoCliente().equals("E")) {
                    tipoPersona = "R";
                    codigoPersona = c.getEruc();
                    nombre = c.getEnombre();
                }
                if (c.getTipoCliente().equals("P") || c.getTipoCliente().equals("C")) {
                    tipoPersona = "E";
                    codigoPersona = c.getPnroDocumento();
                    nombre = c.getPapePat() + " " + c.getPapeMat() + " " + c.getPnombre();
                }
                exportacion = tipoPersona;
                if (codigoPersona != null) {
                    exportacion = exportacion + (!codigoPersona.isEmpty() ? String.format("%011d", Long.parseLong(codigoPersona)) : "");
                }
                exportacion = exportacion + "           ";
                exportacion = exportacion + this.llenarEspacionBlancoDerecha_Cadena(50, nombre);
                wr.write(exportacion + "\r\n");
            }
            wr.close();
            bw.close();
        } catch (IOException e) {
        };
    }

    private void generarArchivoDesdeHasta(String fechaDesde, String fechaHasta) {
        try {
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            List<DocumentoPagoDet> lista = dpBO.ObtenerComprobantesDesdeHasta(fechaDesde, fechaHasta);
            BufferedWriter bw5001 = null;
            PrintWriter wr5001 = null;
            BufferedWriter bw1001 = null;
            PrintWriter wr1001 = null;
            File f5001;
            File f1001;
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            f5001 = new File("archivos/CONT/cont5001_" + formato.format(fechaSistema) + ".txt");
            f1001 = new File("archivos/CONT/cont1001_" + formato.format(fechaSistema) + ".txt");
            FileWriter w5001 = new FileWriter(f5001);
            bw5001 = new BufferedWriter(w5001);
            wr5001 = new PrintWriter(bw5001);
            FileWriter w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            int correlativo5001 = 1;
            int correlativo1001 = 1;
            int idDocumentoPago = 0;
            int idDocumentoPago5001 = 0;
            String exportacion = "";
            for (DocumentoPagoDet dpd : lista) {
                if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() != 3) {
                    if (idDocumentoPago5001 == 0) {
                        idDocumentoPago5001 = dpd.getDocumentoPago().getIdDocumentoPago();
                    } else {
                        if (dpd.getDocumentoPago().getIdDocumentoPago() != idDocumentoPago) {
                            correlativo5001 = correlativo5001 + 1;
                        }
                    }
                    exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getCargo(), "D", dpd.getPrecioVenta());
                    //correlativo5001 = correlativo5001 + 1;
                    wr5001.write(exportacion + "\r\n");
                    if (dpd.getIgv() != null) {
                        if (dpd.getIgv() != 0) {
                            exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getAbono(), "H", dpd.getValorVenta());
                            //correlativo5001 = correlativo5001 + 1;
                            wr5001.write(exportacion + "\r\n");
                            exportacion = this.linea5001(dpd, correlativo5001, "40111", "H", dpd.getIgv());
                            //correlativo5001 = correlativo5001 + 1;
                            wr5001.write(exportacion + "\r\n");
                        } else {
                            exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getAbono(), "H", dpd.getPrecioVenta());
                            //correlativo5001 = correlativo5001 + 1;
                            wr5001.write(exportacion + "\r\n");
                        }
                    } else {
                        exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getAbono(), "H", dpd.getPrecioVenta());
                        //correlativo5001 = correlativo5001 + 1;
                        wr5001.write(exportacion + "\r\n");
                    }
                }

                if (dpd.getDocumentoPago().getTipoPago().equals("CON") && !dpd.getDocumentoPago().getEstado().equals("ANULADO")) {
                    if (idDocumentoPago == 0) {
                        idDocumentoPago = dpd.getDocumentoPago().getIdDocumentoPago();
                        double montoTotalizado = dpBO.ObtenerMontoTotalComprobante(dpd.getDocumentoPago().getIdDocumentoPago());
                        if (dpd.getDocumentoPago().getMoneda().equals("D")) {
                            exportacion = this.linea1001(dpd, correlativo1001, "10102", "D", montoTotalizado);
                        } else {
                            exportacion = this.linea1001(dpd, correlativo1001, "1011", "D", montoTotalizado);
                        }
                        wr1001.write(exportacion + "\r\n");
                    }

                    if (dpd.getDocumentoPago().getIdDocumentoPago() != idDocumentoPago) {
                        double montoTotalizado = dpBO.ObtenerMontoTotalComprobante(dpd.getDocumentoPago().getIdDocumentoPago());
                        correlativo1001 = correlativo1001 + 1;
                        if (dpd.getDocumentoPago().getMoneda().equals("D")) {
                            exportacion = this.linea1001(dpd, correlativo1001, "10102", "D", montoTotalizado);
                        } else {
                            exportacion = this.linea1001(dpd, correlativo1001, "1011", "D", montoTotalizado);
                        }

                        wr1001.write(exportacion + "\r\n");
                        idDocumentoPago = dpd.getDocumentoPago().getIdDocumentoPago();
                    }
                    if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 3) {
                        exportacion = this.linea1001(dpd, correlativo1001, "101010", "H", dpd.getPrecioVenta());
                    } else {
                        exportacion = this.linea1001(dpd, correlativo1001, dpd.getConceptoPagoDetalle().getCargo(), "H", dpd.getPrecioVenta());
                    }
                    wr1001.write(exportacion + "\r\n");
                }
            }
            wr5001.close();
            bw5001.close();
            wr1001.close();
            bw1001.close();
        } catch (IOException e) {
        };
    }

    private void generarCabecera(String fechaDesde, String fechaHasta) {
        FileOutputStream archivo = null;
        try {
            File carpeta = new File("archivos");
            carpeta.mkdirs();
            carpeta = new File("archivos/SistemaContable");
            carpeta.mkdirs();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            Workbook libro = new HSSFWorkbook();
            Date fechaSistema = new Date();
            archivo = new FileOutputStream("archivos/SistemaContable/20174327026CABECERAVENTA_" + formato.format(fechaSistema) + ".xls");
            Sheet hoja = libro.createSheet("cabecera");
            int contador = 0;
            Row fila = hoja.createRow((short) contador);
            Cell celda = fila.createCell((short) 0);
            celda.setCellValue("IdDocumento");
            celda = fila.createCell((short) 1);
            celda.setCellValue("IdSerie");
            celda = fila.createCell((short) 2);
            celda.setCellValue("Numero");
            celda = fila.createCell((short) 3);
            celda.setCellValue("Fecha");
            celda = fila.createCell((short) 4);
            celda.setCellValue("DNI");
            celda = fila.createCell((short) 5);
            celda.setCellValue("RUC");
            celda = fila.createCell((short) 6);
            celda.setCellValue("NombrePersonales");
            celda = fila.createCell((short) 7);
            celda.setCellValue("ApellidoPaterno");
            celda = fila.createCell((short) 8);
            celda.setCellValue("ApellidoMaterno");
            celda = fila.createCell((short) 9);
            celda.setCellValue("RazonSocual");
            celda = fila.createCell((short) 10);
            celda.setCellValue("Direccion");
            celda = fila.createCell((short) 11);
            celda.setCellValue("IdDivisa");
            celda = fila.createCell((short) 12);
            celda.setCellValue("ImpuestoIncluido");
            celda = fila.createCell((short) 13);
            celda.setCellValue("PorcentajeImpuesto");
            celda = fila.createCell((short) 14);
            celda.setCellValue("ValorBruto");
            celda = fila.createCell((short) 15);
            celda.setCellValue("Descuento");
            celda = fila.createCell((short) 16);
            celda.setCellValue("BaseImponible");
            celda = fila.createCell((short) 17);
            celda.setCellValue("Exonerado");
            celda = fila.createCell((short) 18);
            celda.setCellValue("Inafecto");
            celda = fila.createCell((short) 19);
            celda.setCellValue("IGV");
            celda = fila.createCell((short) 20);
            celda.setCellValue("Total");
            celda = fila.createCell((short) 21);
            celda.setCellValue("Activo");
            celda = fila.createCell((short) 22);
            celda.setCellValue("PeriodoCancelado");
            contador = contador + 1;
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            List<DocumentoPago> lista = dpBO.ObtenerDocumentoPagoDesdeHasta(fechaDesde, fechaHasta);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            for (DocumentoPago dp : lista) {
                if (!dp.getEstado().equals("ANULADO")) {
                    if (dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() != 3) {
                        fila = hoja.createRow((short) contador);
                        celda = fila.createCell((short) 0);
                        String tipoDocumento = "";
                        if (dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1 || dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 5) {
                            tipoDocumento = "11";
                        } else {
                            if (dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 2 || dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 6) {
                                tipoDocumento = "12";
                            }
                        }
                        celda.setCellValue(tipoDocumento);
                        celda = fila.createCell((short) 1);
                        celda.setCellValue(dp.getNroSerie());
                        celda = fila.createCell((short) 2);
                        celda.setCellValue(String.format("%010d", dp.getNroDocumentoPago()));
                        celda = fila.createCell((short) 3);
                        celda.setCellValue(f.format(dp.getFechaPago()));
                        celda = fila.createCell((short) 4);
                        if (dp.getCliente().getTipoCliente().equals("C") || dp.getCliente().getTipoCliente().equals("P")) {
                            celda.setCellValue(dp.getCliente().getPnroDocumento());
                        }
                        celda = fila.createCell((short) 5);
                        if (dp.getCliente().getTipoCliente().equals("S")) {
                            celda.setCellValue(dp.getCliente().getSruc());
                        } else {
                            if (dp.getCliente().getTipoCliente().equals("E")) {
                                celda.setCellValue(dp.getCliente().getEruc());
                            }
                        }
                        celda = fila.createCell((short) 6);
                        if (dp.getCliente().getTipoCliente().equals("C") || dp.getCliente().getTipoCliente().equals("P")) {
                            celda.setCellValue(dp.getCliente().getPnombre());
                        }
                        celda = fila.createCell((short) 7);
                        if (dp.getCliente().getTipoCliente().equals("C") || dp.getCliente().getTipoCliente().equals("P")) {
                            celda.setCellValue(dp.getCliente().getPapePat());
                        }
                        celda = fila.createCell((short) 8);
                        if (dp.getCliente().getTipoCliente().equals("C") || dp.getCliente().getTipoCliente().equals("P")) {
                            celda.setCellValue(dp.getCliente().getPapeMat());
                        }
                        celda = fila.createCell((short) 9);
                        if (dp.getCliente().getTipoCliente().equals("S")) {
                            celda.setCellValue(dp.getCliente().getSnombreSociedad());
                        } else {
                            if (dp.getCliente().getTipoCliente().equals("E")) {
                                celda.setCellValue(dp.getCliente().getEnombre());
                            }
                        }
                        celda = fila.createCell((short) 10);
                        if (dp.getCliente().getTipoCliente().equals("C")) {
                            celda.setCellValue(dp.getCliente().getPdireccionDomicilio());
                        } else {
                            if (dp.getCliente().getTipoCliente().equals("P")) {
                                celda.setCellValue(dp.getCliente().getPdireccionDomicilio());
                            } else {
                                if (dp.getCliente().getTipoCliente().equals("S")) {
                                    celda.setCellValue(dp.getCliente().getSdireccion());
                                } else {
                                    if (dp.getCliente().getTipoCliente().equals("E")) {
                                        celda.setCellValue(dp.getCliente().getEdireccion());
                                    }
                                }
                            }
                        }
                        celda = fila.createCell((short) 11);
                        if (dp.getMoneda().equals("S")) {
                            celda.setCellValue("01");
                        } else {
                            if (dp.getMoneda().equals("D")) {
                                celda.setCellValue("02");
                            }
                        }
                        celda = fila.createCell((short) 12);
                        celda.setCellValue(dpBO.ObtenerIgv(dp.getIdDocumentoPago()) > 0 ? true : false);
                        celda = fila.createCell((short) 13);
                        celda.setCellValue(dpBO.ObtenerIgv(dp.getIdDocumentoPago()));
                        double montoIgv = 0.0;
                        double montoValorVenta = 0.0;
                        double montoExonerado = 0.0;
                        double montoInafecto = 0.0;
                        List<Object> lTributos = dpBO.ObtenerTributosGenerales(dp.getIdDocumentoPago());
                        for (Object pobj : lTributos) {
                            Object[] obj = (Object[]) pobj;
                            double montoBase = Math.round(((double) obj[3]) * Math.pow(10, 2)) / Math.pow(10, 2);
                            double montoTributo = Math.round(((double) obj[4]) * Math.pow(10, 2)) / Math.pow(10, 2);
                            if (obj[0] != null) {
                                if (((String) obj[0]).equals("1000")) {
                                    montoValorVenta = montoBase;
                                    montoIgv = montoTributo;
                                }
                                if (((String) obj[0]).equals("9997")) {
                                    montoExonerado = montoBase;
                                }
                                if (((String) obj[0]).equals("9998")) {
                                    montoInafecto = montoBase;
                                }
                            }
                        }
                        celda = fila.createCell((short) 14);
                        celda.setCellValue(montoValorVenta + montoIgv);
                        celda = fila.createCell((short) 15);
                        celda.setCellValue("");
                        celda = fila.createCell((short) 16);
                        celda.setCellValue(montoValorVenta);
                        celda = fila.createCell((short) 17);
                        celda.setCellValue(montoExonerado);
                        celda = fila.createCell((short) 18);
                        celda.setCellValue(montoInafecto);
                        celda = fila.createCell((short) 19);
                        celda.setCellValue(montoIgv);
                        celda = fila.createCell((short) 20);
                        double montoTotal = montoValorVenta + montoIgv + montoExonerado + montoInafecto;
                        montoTotal = Math.round(montoTotal * Math.pow(10, 2)) / Math.pow(10, 2);
                        celda.setCellValue(montoTotal);
                        celda = fila.createCell((short) 21);
                        celda.setCellValue(true);
                        celda = fila.createCell((short) 22);
                        celda.setCellValue(dpBO.ObtenerPeriodoCancelado(dp.getIdDocumentoPago()));
                        contador++;
                    }
                }
            }
            libro.write(archivo);
            archivo.close();
            this.generarDetalle(lista);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmHistorialContador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmHistorialContador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(frmHistorialContador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generarDetalle(List<DocumentoPago> lista) {
        FileOutputStream archivo = null;
        try {
            File carpeta = new File("archivos");
            carpeta.mkdirs();
            carpeta = new File("archivos/SistemaContable");
            carpeta.mkdirs();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            Workbook libro = new HSSFWorkbook();
            Date fechaSistema = new Date();
            archivo = new FileOutputStream("archivos/SistemaContable/20174327026DETALLEVENTA_" + formato.format(fechaSistema) + ".xls");
            Sheet hoja = libro.createSheet("detalle");
            int contador = 0;
            Row fila = hoja.createRow((short) contador);
            Cell celda = fila.createCell((short) 0);
            celda.setCellValue("IdDocumento");
            celda = fila.createCell((short) 1);
            celda.setCellValue("IdSerie");
            celda = fila.createCell((short) 2);
            celda.setCellValue("Numero");
            celda = fila.createCell((short) 3);
            celda.setCellValue("Item");
            celda = fila.createCell((short) 4);
            celda.setCellValue("IdArticulo");
            celda = fila.createCell((short) 5);
            celda.setCellValue("Cantidad");
            celda = fila.createCell((short) 6);
            celda.setCellValue("PrecioUnitario");
            celda = fila.createCell((short) 7);
            celda.setCellValue("ValorBruto");
            celda = fila.createCell((short) 8);
            celda.setCellValue("BaseImponible");
            celda = fila.createCell((short) 9);
            celda.setCellValue("Exonerado");
            celda = fila.createCell((short) 10);
            celda.setCellValue("Inafecto");
            celda = fila.createCell((short) 11);
            celda.setCellValue("IGV");
            celda = fila.createCell((short) 12);
            celda.setCellValue("Total");
            contador = contador + 1;
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
//            List<DocumentoPago> lista = dpBO.ObtenerDocumentoPagoDesdeHasta(fechaDesde, fechaHasta);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            for (DocumentoPago dp : lista) {
                if (!dp.getEstado().equals("ANULADO")) {
                    if (dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() != 3) {
                        List<DocumentoPagoDet> l = dpBO.ObtenerDetalleComprobante(dp.getIdDocumentoPago());
                        int contadorItems = 0;
                        for (DocumentoPagoDet dpd : l) {
                            fila = hoja.createRow((short) contador);
                            celda = fila.createCell((short) 0);
                            String tipoDocumento = "";
                            if (dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1 || dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 5) {
                                tipoDocumento = "11";
                            } else {
                                if (dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 2 || dp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 6) {
                                    tipoDocumento = "12";
                                }
                            }
                            celda.setCellValue(tipoDocumento);
                            celda = fila.createCell((short) 1);
                            celda.setCellValue(dp.getNroSerie());
                            celda = fila.createCell((short) 2);
                            celda.setCellValue(String.format("%010d", dp.getNroDocumentoPago()));
                            celda = fila.createCell((short) 3);
                            celda.setCellValue(String.format("%04d", contadorItems + 1));
                            celda = fila.createCell((short) 4);
                            celda.setCellValue(dpd.getConceptoPagoDetalle().getTipoCodigo() + dpd.getConceptoPagoDetalle().getCodigo());
                            celda = fila.createCell((short) 5);
                            celda.setCellValue(dpd.getCantidad());
                            celda = fila.createCell((short) 6);
                            double precioUnitario = (dpd.getValorVenta() + dpd.getIgv()) / dpd.getCantidad();
                            precioUnitario = Math.round(precioUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
                            celda.setCellValue(precioUnitario);
                            celda = fila.createCell((short) 7);
                            celda.setCellValue(dpd.getValorVenta() + dpd.getIgv());
                            celda = fila.createCell((short) 8);
                            if (dpd.getCodigoTipoTributo().equals("1000")) {
                                celda.setCellValue(dpd.getValorVenta());
                            }
                            celda = fila.createCell((short) 9);
                            if (dpd.getCodigoTipoTributo().equals("9997")) {
                                celda.setCellValue(dpd.getValorVenta());
                            }
                            celda = fila.createCell((short) 10);
                            if (dpd.getCodigoTipoTributo().equals("9998")) {
                                celda.setCellValue(dpd.getValorVenta());
                            }
                            celda = fila.createCell((short) 11);
                            if (dpd.getCodigoTipoTributo().equals("1000")) {
                                celda.setCellValue(dpd.getIgv());
                            }
                            celda = fila.createCell((short) 12);
                            celda.setCellValue(dpd.getValorVenta() + dpd.getIgv());
                            contadorItems++;
                            contador++;
                        }
                    }
                }
            }
            libro.write(archivo);
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmHistorialContador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmHistorialContador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(frmHistorialContador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generarArchivo(String nombreArchivo, int anio, int mes) {
        try {
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            List<DocumentoPagoDet> lista = dpBO.ObtenerComprobantesAnioMes(anio, mes);
            BufferedWriter bw5001 = null;
            PrintWriter wr5001 = null;
            BufferedWriter bw1001 = null;
            PrintWriter wr1001 = null;
            File f5001;
            File f1001;
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            f5001 = new File("archivos/CONT/cont5001_" + nombreArchivo + "_" + formato.format(fechaSistema) + ".txt");
            f1001 = new File("archivos/CONT/cont1001_" + nombreArchivo + "_" + formato.format(fechaSistema) + ".txt");
            FileWriter w5001 = new FileWriter(f5001);
            bw5001 = new BufferedWriter(w5001);
            wr5001 = new PrintWriter(bw5001);
            FileWriter w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            int correlativo5001 = 1;
            int correlativo1001 = 1;
            int idDocumentoPago = 0;
            int idDocumentoPago5001 = 0;
            String exportacion = "";
            for (DocumentoPagoDet dpd : lista) {
                if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() != 3) {
                    if (idDocumentoPago5001 == 0) {
                        idDocumentoPago5001 = dpd.getDocumentoPago().getIdDocumentoPago();
                    } else {
                        if (dpd.getDocumentoPago().getIdDocumentoPago() != idDocumentoPago) {
                            correlativo5001 = correlativo5001 + 1;
                        }
                    }
                    exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getCargo(), "D", dpd.getPrecioVenta());
                    //correlativo5001 = correlativo5001 + 1;
                    wr5001.write(exportacion + "\r\n");
                    if (dpd.getIgv() != null) {
                        if (dpd.getIgv() != 0) {
                            exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getAbono(), "H", dpd.getValorVenta());
                            //correlativo5001 = correlativo5001 + 1;
                            wr5001.write(exportacion + "\r\n");
                            exportacion = this.linea5001(dpd, correlativo5001, "40111", "H", dpd.getIgv());
                            //correlativo5001 = correlativo5001 + 1;
                            wr5001.write(exportacion + "\r\n");
                        } else {
                            exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getAbono(), "H", dpd.getPrecioVenta());
                            //correlativo5001 = correlativo5001 + 1;
                            wr5001.write(exportacion + "\r\n");
                        }
                    } else {
                        exportacion = this.linea5001(dpd, correlativo5001, dpd.getConceptoPagoDetalle().getAbono(), "H", dpd.getPrecioVenta());
                        //correlativo5001 = correlativo5001 + 1;
                        wr5001.write(exportacion + "\r\n");
                    }
                }

                if (dpd.getDocumentoPago().getTipoPago().equals("CON") && !dpd.getDocumentoPago().getEstado().equals("ANULADO")) {
                    if (idDocumentoPago == 0) {
                        idDocumentoPago = dpd.getDocumentoPago().getIdDocumentoPago();
                        double montoTotalizado = dpBO.ObtenerMontoTotalComprobante(dpd.getDocumentoPago().getIdDocumentoPago());
                        if (dpd.getDocumentoPago().getMoneda().equals("D")) {
                            exportacion = this.linea1001(dpd, correlativo1001, "10102", "D", montoTotalizado);
                        } else {
                            exportacion = this.linea1001(dpd, correlativo1001, "10101", "D", montoTotalizado);
                        }
                        //correlativo1001 = correlativo1001 + 1;
                        wr1001.write(exportacion + "\r\n");
                    }

                    if (dpd.getDocumentoPago().getIdDocumentoPago() != idDocumentoPago) {
                        double montoTotalizado = dpBO.ObtenerMontoTotalComprobante(dpd.getDocumentoPago().getIdDocumentoPago());
                        correlativo1001 = correlativo1001 + 1;
                        if (dpd.getDocumentoPago().getMoneda().equals("D")) {
                            exportacion = this.linea1001(dpd, correlativo1001, "10102", "D", montoTotalizado);
                        } else {
                            exportacion = this.linea1001(dpd, correlativo1001, "10101", "D", montoTotalizado);
                        }
                        wr1001.write(exportacion + "\r\n");
                        idDocumentoPago = dpd.getDocumentoPago().getIdDocumentoPago();
                    }
                    exportacion = this.linea1001(dpd, correlativo1001, dpd.getConceptoPagoDetalle().getCargo(), "H", dpd.getPrecioVenta());
                    //correlativo1001 = correlativo1001 + 1;
                    wr1001.write(exportacion + "\r\n");
                }

//                wr.write("01;" + (String) obj[0] + ";" + fuente + ";1;0161;;" + String.valueOf(montoBruto) + "\r\n");
//                wr.write("01;" + (String) obj[0] + ";" + fuente + ";9;9999;;" + String.valueOf(montoNeto) + "\r\n");

                /*cadenaMonto = "";
                 cadenaDecimales = "";
                 cuenta = (String) obj[0];
                 cadenaMonto = this.ObtenerEntero(montoNeto);
                 cadenaDecimales = this.ObtenerDecimales(montoNeto);
                 wr.write(cuenta.concat(cadenaMonto.concat(cadenaDecimales)) + "\r\n");*/
            }
            wr5001.close();
            bw5001.close();
            wr1001.close();
            bw1001.close();
        } catch (IOException e) {
        };
    }

    private void btnExportarParaContabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarParaContabilidadActionPerformed
        frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        fPrin.AbrirFormularioElegirFechaMigracion();
    }//GEN-LAST:event_btnExportarParaContabilidadActionPerformed

    public void ImprimirCuentaCorriente() {
        try {
            if (this.idCliente > 0) {
                List<ReporteCuentaCorriente> listado = new ArrayList();
                ContadorBO cBO = ContadorBO.getInstance();
                List<Object> lCuotas = cBO.ObtenerCuotasPendientesContador(this.idCliente);
                for (Object pobj : lCuotas) {
                    Object[] obj = (Object[]) pobj;
                    ReporteCuentaCorriente r = new ReporteCuentaCorriente();
                    r.setConcepto("• CUOTA");
                    r.setDescripcion((String) obj[0]);
                    try {
                        r.setMonto((double) obj[1]);
                    } catch (Exception e) {
                        try {
                            r.setMonto((int) obj[1]);
                        } catch (Exception ex) {

                        }
                    }
                    listado.add(r);
                }
                List<Object> lFinanciamiento = cBO.ObtenerFinanciamientoPendientesContador(this.idCliente);
                for (Object pobj : lFinanciamiento) {
                    Object[] obj = (Object[]) pobj;
                    ReporteCuentaCorriente r = new ReporteCuentaCorriente();
                    r.setConcepto("• FINANCIAMIENTO");
                    r.setDescripcion((String) obj[0]);
                    r.setMonto((double) obj[1]);
                    listado.add(r);
                }
                List<Object> lReincorporacion = cBO.ObtenerReincorporacionPendientesContador(this.idCliente);
                for (Object pobj : lReincorporacion) {
                    Object[] obj = (Object[]) pobj;
                    ReporteCuentaCorriente r = new ReporteCuentaCorriente();
                    r.setConcepto("• REINCORPORACION");
                    r.setDescripcion((String) obj[0]);
                    r.setMonto((double) obj[1]);
                    listado.add(r);
                }
                List<Object> lDeuda = cBO.ObtenerDeudasContador(this.idCliente);
                for (Object pobj : lDeuda) {
                    Object[] obj = (Object[]) pobj;
                    ReporteCuentaCorriente r = new ReporteCuentaCorriente();
                    r.setConcepto("• DEUDA");
                    r.setDescripcion((String) obj[0]);
                    r.setMonto((double) obj[1]);
                    listado.add(r);
                }
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listado);
                Map parametros = new HashMap();
                parametros.put("codigo", lbCodigo.getText());
//                parametros.put("hasta", hasta);
                parametros.put("REPORT_LOCALE", new Locale("en", "EN"));
                JasperPrint JPrint = JasperFillManager.fillReport("reportes/cta_cte02.jasper", parametros, ds);
                JasperViewer.viewReport(JPrint, false);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void ImprimirMovimientosContador() {
        try {
            String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(jdbcDriver);
            SeguridadBO sBO = SeguridadBO.getInstance();
            String url = sBO.getUrlReporte();
            String user = "sa";
            String pass = "4dm1n1str4c10nGOB90570";
            Connection con = DriverManager.getConnection(url, user, pass);
            String fullPath = "";
            Map param;
            fullPath = "reportes/Historial_Contador.jasper";
            param = new HashMap();
            String CodigoContador = "";
            CodigoContador = txtCodigo.getText();
            String NombreContador = "";
            NombreContador = txtNombre.getText();
            param.put("IDCLIENTE", this.idCliente);
            param.put("Codigo_Colegiatura", CodigoContador);
            param.put("Nombre_contador", NombreContador);
            param.put("Tipo", "Colegiado:");
            if (this.idCliente > 0) {
                JasperPrint JPrint = JasperFillManager.fillReport(fullPath, param, con);
                JasperViewer.viewReport(JPrint, false);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }


    private void lbCodigo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCodigo1MouseClicked
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
                ImprimirMovimientosContador();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_lbCodigo1MouseClicked

    private void tCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCursoMouseClicked
        if (evt.getClickCount() == 2) {
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
                    CargarDocumentoPagoSegunCurso();
                    fCargando.dispose();
                }
            };
            queryThread.start();
        }
    }//GEN-LAST:event_tCursoMouseClicked

    private void lbCodigo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCodigo2MouseClicked
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
                ImprimirCuentaCorriente();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_lbCodigo2MouseClicked

    private void tDeudasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tDeudasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tDeudasKeyPressed

    private void tDeudasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDeudasMouseClicked

    }//GEN-LAST:event_tDeudasMouseClicked

    public void ExportarArchivos(String fechaDesde, String fechaHasta) {
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
                generarArchivoDesdeHasta(fechaDesde, fechaHasta);
                generarArchivoClientes();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }

    public void ExportarArchivosMigracionNuevoSistemaContable(String fechaDesde, String fechaHasta) {
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
                generarCabecera(fechaDesde, fechaHasta);
//                generarDetalle(fechaDesde, fechaHasta);
                fCargando.dispose();
            }
        };
        queryThread.start();
    }

    private String llenarEspacionBlanco(int cantidad, int pvalor) {
        String valor = String.valueOf(pvalor);
        for (int i = valor.length(); i < cantidad; i++) {
            valor = " " + valor;
        }
        return valor;
    }

    private String llenarEspacionBlancoDerecha(int cantidad, Long pvalor) {
        String valor = String.valueOf(pvalor);
        for (int i = valor.length(); i < cantidad; i++) {
            valor = valor + " ";
        }
        return valor;
    }

    private String llenarEspacionBlancoDerecha_Cadena(int cantidad, String valor) {
        for (int i = valor.length(); i < cantidad; i++) {
            valor = valor + " ";
        }
        return valor;
    }

    private String linea5001(DocumentoPagoDet dpd, int correlativo5001, String cuenta, String letra, double montoDetalle) {
        SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formato_yyMM = new SimpleDateFormat("yyMM");
        String tipo = "";
        String tipoPersona = "";
        String codigoPersona = "";
        String tipoDocSUNAT = "";
        String exportacion = "";
        String monto = "";
        if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1 || dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 5) {
            tipo = "5001";
            tipoDocSUNAT = "01";
        } else {
            if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 2 || dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 6) {
                tipo = "5001";
                tipoDocSUNAT = "03";
            } else {
                tipo = "1001";
                tipoDocSUNAT = "RI";
            }
        }

        if (dpd.getDocumentoPago().getEstado().equals("ANULADO")) {
            tipoPersona = "R";
            codigoPersona = "99999999999";
        } else {
            if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("S")) {
                tipoPersona = "S";
                codigoPersona = dpd.getDocumentoPago().getCliente().getScodigoSoc();
            }
            if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("E")) {
                tipoPersona = "R";
                codigoPersona = dpd.getDocumentoPago().getCliente().getEruc();
            }
            if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("P") || dpd.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
                tipoPersona = "E";
                codigoPersona = dpd.getDocumentoPago().getCliente().getPnroDocumento();
            }
//            if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
//                tipoPersona = "T";
//                codigoPersona = dpd.getDocumentoPago().getCliente().getCcodigoCole();
//            }
        }

        //----------------------GENERANDO EL ARCHIVO 5001--------------------
        //exportacion = "16010001";
        exportacion = exportacion + formato_yyMM.format(dpd.getDocumentoPago().getFechaPago()) + "001";
        exportacion = exportacion + "5001";
        exportacion = exportacion + String.format("%08d", correlativo5001);
        exportacion = exportacion + formato_yyyyMMdd.format(dpd.getDocumentoPago().getFechaPago());
        exportacion = exportacion + "     " + "00010     ";
        exportacion = exportacion + tipoPersona;
        if (codigoPersona != null) {
            exportacion = exportacion + String.format("%011d", Long.parseLong(codigoPersona));
        }
        exportacion = exportacion + tipoDocSUNAT;
        if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
            if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                exportacion = exportacion + dpd.getDocumentoPago().getNroSerie();
            } else {
                exportacion = exportacion + dpd.getDocumentoPago().getNroSerie();
            }
        } else {
            exportacion = exportacion + dpd.getDocumentoPago().getNroSerie();
        }
//        exportacion = exportacion + String.format("%04d", dpd.getDocumentoPago().getNroSerie());
        exportacion = exportacion + String.format("%015d", dpd.getDocumentoPago().getNroDocumentoPago());
        exportacion = exportacion + formato_yyyyMMdd.format(dpd.getDocumentoPago().getFechaPago());
        exportacion = exportacion + formato_yyyyMMdd.format(dpd.getDocumentoPago().getFechaPago());
        exportacion = exportacion + "                                 "; //REFERENCIA
        exportacion = exportacion + "                     "; //RELLENO
        exportacion = exportacion + "                       ";  //DETRACCION
        exportacion = exportacion + this.llenarEspacionBlancoDerecha(15, (cuenta == null ? 0 : Long.parseLong(cuenta)));//String.format("% 15d", Integer.parseInt(cuenta));  //CODIGO CUENTA CONTABLE -- 15 ESPACIOS A LA DERECHA
        exportacion = exportacion + "               ";  //RELLENO
        exportacion = exportacion + "          ";  //FLUJO CAJA
        exportacion = exportacion + letra;  //INDICADOR

        double precioVenta = montoDetalle;
        int valorEntero = (int) precioVenta;
        double decimales = precioVenta - (int) precioVenta;
        decimales = Math.round(decimales * Math.pow(10, 2)) / Math.pow(10, 2);
        int valorDecimales = (int) (decimales * 100);

        monto = this.llenarEspacionBlanco(13, valorEntero);//String.format("% 13d", valorEntero);  //IMPORTE_ENTERO

        //double decimales = montoNeto - (int) montoNeto;
        //decimales = Math.round(decimales * Math.pow(10, 2)) / Math.pow(10, 2);
        //int valorDecimales = (int) (decimales * 100);
//                String montoString = String.valueOf(precioVenta);
//                montoString = monto.replace(".", "");
//                montoString = monto.replace(",", "");
        if (valorDecimales == 0) {
            monto = monto + ".00";
        } else {
            if (valorDecimales < 10) {
                monto = monto + ".0" + String.valueOf(valorDecimales);
            } else {
                monto = monto + "." + String.valueOf(valorDecimales);
            }
        }

//        for (int i = monto.length(); i <= 15; i++) {
//            monto = monto + "." + String.valueOf(valorDecimales);
//        }

        /*if (String.valueOf(valorDecimales).length() == 1) {
         monto = monto + "." + String.valueOf(valorDecimales) + "0";
         } //IMPORTE_DECIMALES
         else {
         monto = monto + "." + String.valueOf(valorDecimales);
         }*/
        exportacion = exportacion + monto; //IMPORTE
        if (dpd.getDocumentoPago().getEstado().equals("ANULADO")) {
            exportacion = exportacion + "ANULADO                                 "; //String.format("%04d", dpd.getDocumentoPago().getNroSerie()) + String.format("%015d", dpd.getDocumentoPago().getNroDocumentoPago()) + "                     ";  //GLOSA
        } else {
            exportacion = exportacion + dpd.getDocumentoPago().getNroSerie() + String.format("%015d", dpd.getDocumentoPago().getNroDocumentoPago()) + "                     ";  //GLOSA        
        }
        exportacion = exportacion + "   ";  //RELLENO
        if (dpd.getDocumentoPago().getMoneda().equals("D")) // MONEDA EXTRANJERA
        {
            exportacion = exportacion + monto;
            exportacion = exportacion + "                "; //TIPO DE CAMBIO 
        } else {
            exportacion = exportacion + "                                ";
        }
        return exportacion;
    }

    public String linea1001(DocumentoPagoDet dpd, int correlativo1001, String cuenta, String letra, double precio) {
        SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formato_yyMM = new SimpleDateFormat("yyMM");
        String tipo = "";
        String tipoPersona = "";
        String codigoPersona = "";
        String tipoDocSUNAT = "";
        String exportacion = "";
        String monto = "";
        if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1 || dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 5) {
            tipo = "5001";
            tipoDocSUNAT = "01";
        } else {
            if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 2 || dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 6) {
                tipo = "5001";
                tipoDocSUNAT = "03";
            } else {
                tipo = "1001";
                tipoDocSUNAT = "RI";
            }
        }
        if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("S")) {
            tipoPersona = "S";
            codigoPersona = dpd.getDocumentoPago().getCliente().getScodigoSoc();
        }
        if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("E")) {
            tipoPersona = "R";
            codigoPersona = dpd.getDocumentoPago().getCliente().getEruc();
        }
        if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("P") || dpd.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
            tipoPersona = "E";
            codigoPersona = dpd.getDocumentoPago().getCliente().getPnroDocumento();
        }
//        if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
//            tipoPersona = "T";
//            codigoPersona = dpd.getDocumentoPago().getCliente().getCcodigoCole();
//        }

        //----------------------GENERANDO EL ARCHIVO 1001--------------------
        //exportacion = "16010001";
        exportacion = exportacion + formato_yyMM.format(dpd.getDocumentoPago().getFechaPago()) + "001";
        exportacion = exportacion + "1001";
        exportacion = exportacion + String.format("%08d", correlativo1001);
        exportacion = exportacion + formato_yyyyMMdd.format(dpd.getDocumentoPago().getFechaPago());
        exportacion = exportacion + "     " + "00010     ";
        exportacion = exportacion + tipoPersona;
        if (codigoPersona != null) {
            exportacion = exportacion + String.format("%011d", Long.parseLong(codigoPersona));
        }
        exportacion = exportacion + "RI";//tipoDocSUNAT;
        String nroSerie = "";
        //----------------------------------------------------------------------
        if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
            if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                nroSerie = dpd.getDocumentoPago().getNroSerie();
            } else {
                nroSerie = dpd.getDocumentoPago().getNroSerie();
            }
        } else {
            nroSerie = dpd.getDocumentoPago().getNroSerie();
        }
        //----------------------------------------------------------------------
        exportacion = exportacion + nroSerie;
//        exportacion = exportacion + String.format("%04d", dpd.getDocumentoPago().getNroSerie());
        exportacion = exportacion + String.format("%015d", dpd.getDocumentoPago().getNroDocumentoPago());
        exportacion = exportacion + formato_yyyyMMdd.format(dpd.getDocumentoPago().getFechaPago());
        exportacion = exportacion + formato_yyyyMMdd.format(dpd.getDocumentoPago().getFechaPago());
        if (codigoPersona != null) {
            exportacion = exportacion + tipoPersona + String.format("%011d", Long.parseLong(codigoPersona)) + tipoDocSUNAT + nroSerie + String.format("%015d", dpd.getDocumentoPago().getNroDocumentoPago());//REFERENCIA
//            exportacion = exportacion + tipoPersona + String.format("%011d", Long.parseLong(codigoPersona)) + tipoDocSUNAT + String.format("%04d", dpd.getDocumentoPago().getNroSerie()) + String.format("%015d", dpd.getDocumentoPago().getNroDocumentoPago());//REFERENCIA
        }
        exportacion = exportacion + "                     ";
        exportacion = exportacion + "                       ";  //DETRACCION
        exportacion = exportacion + this.llenarEspacionBlancoDerecha(15, (cuenta == null ? 0 : Long.parseLong(cuenta))); //String.format("%015d", Integer.parseInt(cuenta));  //CODIGO CUENTA CONTABLE
        exportacion = exportacion + "               ";  //RELLENO
        if (!cuenta.equals("1011")) { //SE AGREGA UNA REGLA PARA NO TENER EN CUENTA CUANDO SEA 1011
            exportacion = exportacion + this.llenarEspacionBlancoDerecha_Cadena(10, dpd.getConceptoPagoDetalle().getCodigoPresupuesto() != null ? dpd.getConceptoPagoDetalle().getCodigoPresupuesto() : "");  //CODIGO PPTO
        } else {
            exportacion = exportacion + this.llenarEspacionBlancoDerecha_Cadena(10, "");  //CODIGO PPTO
        }
//        exportacion = exportacion + "          ";  //FLUJO CAJA
        exportacion = exportacion + letra;  //INDICADOR

        double precioVenta = precio;
        int valorEntero = (int) precioVenta;
        double decimales = precioVenta - (int) precioVenta;
        decimales = Math.round(decimales * Math.pow(10, 2)) / Math.pow(10, 2);
        int valorDecimales = (int) (decimales * 100);

        monto = this.llenarEspacionBlanco(13, valorEntero); //String.format("%013d", valorEntero);  //IMPORTE_ENTERO

        if (valorDecimales == 0) {
            monto = monto + ".00";
        } else {
            if (valorDecimales < 10) {
                monto = monto + ".0" + String.valueOf(valorDecimales);
            } else {
                monto = monto + "." + String.valueOf(valorDecimales);
            }
        }

//        if (valorDecimales == 0) {
//            monto = monto + ".00";
//        } else {
//            monto = monto + "." + String.valueOf(valorDecimales);
//        }
//        for (int i = monto.length(); i <= 15; i++) {
//            monto = monto + "." + String.valueOf(valorDecimales);
//        }
        /*if (String.valueOf(valorDecimales).length() == 1) {
         monto = monto + "." + String.valueOf(valorDecimales) + "0";
         } //IMPORTE_DECIMALES}
         else {
         monto = monto + "." + String.valueOf(valorDecimales);
         }*/
        exportacion = exportacion + monto;
        exportacion = exportacion + dpd.getDocumentoPago().getNroSerie() + String.format("%015d", dpd.getDocumentoPago().getNroDocumentoPago()) + "                     ";  //GLOSA
        exportacion = exportacion + "   ";  //RELLENO
        if (dpd.getDocumentoPago().getMoneda().equals("D")) // MONEDA EXTRANJERA
        {
            exportacion = exportacion + monto;
            exportacion = exportacion + "                "; //TIPO DE CAMBIO 
        } else {
            exportacion = exportacion + "                                ";
        }
        return exportacion;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExportarParaContabilidad;
    private javax.swing.JButton btnReporteWeb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPagoDeMas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jReincorporacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigo1;
    private javax.swing.JLabel lbCodigo2;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JPanel pDeudas;
    private javax.swing.JTable tContador;
    private javax.swing.JTable tCuotas;
    private javax.swing.JTable tCurso;
    private javax.swing.JTable tDetalle;
    private javax.swing.JTable tDetalleFinanciamiento;
    private javax.swing.JTable tDeudas;
    private javax.swing.JTable tFinanciamiento;
    private javax.swing.JTable tPagoDeMas;
    private javax.swing.JTable tReincorporaciones;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
