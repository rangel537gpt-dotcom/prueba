/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.caja;

import caja.bo.ClienteBO;
import caja.bo.ContadorBO;
import caja.bo.CuotasBO;
import caja.bo.DocumentoPagoBO;
import caja.bo.FinanciamientoBO;
import caja.bo.MiRender;
import caja.bo.ReporteCuentaCorriente;
import caja.bo.SeguridadBO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.frm.frmPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class frmHistorialSociedad extends javax.swing.JInternalFrame {

    private int idCliente = 0;

    /**
     * Creates new form frmHistorialSociedad
     */
    public frmHistorialSociedad() {
        initComponents();
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

        tCuotas.setDefaultRenderer(Object.class, new MiRender());

        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        int contador = 0;
        for (Cliente cli : lClientes) {
            if (cli.getTipoCliente() != null) {
                if (cli.getTipoCliente().equals("S")) {
                    model.setNumRows(contador);
                    model.addRow(new Object[contador]);
                    tContador.setValueAt(contador + 1, contador, 0);
                    tContador.setValueAt(cli.getScodigoSoc(), contador, 1);
                    tContador.setValueAt(cli.getSnombreSociedad(), contador, 2);
                    tContador.setValueAt(cli.getSdireccion(), contador, 3);
                    tContador.setValueAt(cli.getSruc(), contador, 4);
                    tContador.setValueAt(cli.getSfechaAfiliacion(), contador, 5);
                    tContador.setValueAt(cli.getIdCliente(), contador, 6);
                    if (cli.getEstado() != null) {
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
                    }
                    contador = contador + 1;
                }
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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
                        lbCodigo2 = new javax.swing.JLabel();
                        lbCodigo1 = new javax.swing.JLabel();

                        setClosable(true);
                        setIconifiable(true);
                        setTitle("HISTORIAL SOCIEDADES");
                        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N
                        setPreferredSize(new java.awt.Dimension(900, 600));

                        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        jLabel3.setText("CODIGO:");

                        lbCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        lbCodigo.setForeground(new java.awt.Color(0, 102, 204));
                        lbCodigo.setText("0000");

                        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        jLabel4.setText("NOMBRE:");

                        lbNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        lbNombre.setForeground(new java.awt.Color(0, 102, 204));
                        lbNombre.setText("----");

                        jLabel1.setText("CODIGO:");

                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                        jTabbedPane1.setToolTipText("");

                        tDetalle.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "FECHA", "CMP", "SERIE", "NUM", "DESCRIPCION", "IMPORTE", "IDDOCUMENTO", "IGV", "IDTIPODOC", "ESTADO", "TIPOMONEDA"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, false, false, false
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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
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

                        lbCodigo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        lbCodigo2.setForeground(new java.awt.Color(0, 102, 204));
                        lbCodigo2.setText("CUENTA CORRIENTE");
                        lbCodigo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        lbCodigo2.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbCodigo2MouseClicked(evt);
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
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCodigo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbNombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbCodigo2)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbCodigo1)))
                                .addContainerGap())
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbCodigo1)
                                        .addComponent(lbCodigo2))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(lbCodigo)
                                        .addComponent(jLabel4)
                                        .addComponent(lbNombre)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

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
                if (cliente.getTipoCliente().equals("S")) {
                    if (cliente.getScodigoSoc().contains(pFiltro)) {
                        model.setNumRows(contador);
                        model.addRow(new Object[contador]);
                        tContador.setValueAt(contador + 1, contador, 0);
                        tContador.setValueAt(cliente.getScodigoSoc(), contador, 1);
                        tContador.setValueAt(cliente.getSnombreSociedad(), contador, 2);
                        tContador.setValueAt(cliente.getSdireccion(), contador, 3);
                        tContador.setValueAt(cliente.getSruc(), contador, 4);
                        tContador.setValueAt(cliente.getSfechaAfiliacion(), contador, 5);
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
                if (cliente.getTipoCliente().equals("S")) {
                    nombre = cliente.getSnombreSociedad();
                    if (nombre.contains(pFiltro)) {
                        model.setNumRows(contador);
                        model.addRow(new Object[contador]);
                        tContador.setValueAt(contador + 1, contador, 0);
                        tContador.setValueAt(cliente.getScodigoSoc(), contador, 1);
                        tContador.setValueAt(cliente.getSnombreSociedad(), contador, 2);
                        tContador.setValueAt(cliente.getSdireccion(), contador, 3);
                        tContador.setValueAt(cliente.getSruc(), contador, 4);
                        tContador.setValueAt(cliente.getSfechaAfiliacion(), contador, 5);
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
            //tDetalleHistorial.setValueAt(c.getUniversidad(), contador, 4);
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

        List<Object> lCuotas = cBO.ObtenerTodasCuotasCanceladasSociedades(cliente);
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

    public void CargarDocumentoPago() {
        try {
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
            Cliente cliente = null;
            for (Cliente cli : lClientes) {
                if (cli.getTipoCliente().equals("S")) {
                    if (cli.getScodigoSoc().equals(lbCodigo.getText())) {
                        cliente = cli;
                        break;
                    }
                }
            }

            DocumentoPago doc = new DocumentoPago();
            int fila = tDetalle.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
            int idDocumentoPago = Integer.valueOf(model.getValueAt(fila, 7).toString());
            String nroSerie = String.valueOf(model.getValueAt(fila, 3));
            String nroComprobante = String.valueOf(model.getValueAt(fila, 4));
            String tipoComprobante = String.valueOf(model.getValueAt(fila, 2));
            String fechaComprobante = String.valueOf(model.getValueAt(fila, 1));
            String codigo = lbCodigo.getText();
            String nombrePagador = lbNombre.getText();
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
            CodigoContador = lbCodigo.getText();
            String NombreContador = "";
            NombreContador = lbNombre.getText();
            param.put("IDCLIENTE", this.idCliente);
            param.put("Codigo_Colegiatura", CodigoContador);
            param.put("Nombre_contador", NombreContador);
            param.put("Tipo", "Sociedad:");
            if (this.idCliente > 0) {
                JasperPrint JPrint = JasperFillManager.fillReport(fullPath, param, con);
                JasperViewer.viewReport(JPrint, false);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }


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
        List<Cliente> lClientes = dpBO.ObtenerTodosClientes();
        DefaultTableModel model = (DefaultTableModel) tContador.getModel();
        model.setNumRows(0);
        int contador = 0;
        for (Cliente cli : lClientes) {
            if (cli.getTipoCliente().equals("S")) {
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
                    fCargando.dispose();
                }
            };
            queryThread.start();
        }
    }//GEN-LAST:event_tContadorMouseClicked

    private void tContadorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tContadorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tContadorMouseEntered

    public void ImprimirCuentaCorriente() {
        try {
            if (this.idCliente > 0) {
                List<ReporteCuentaCorriente> listado = new ArrayList();
                ContadorBO cBO = ContadorBO.getInstance();
                List<Object> lCuotas = cBO.ObtenerCuotasPendientesSociedad(this.idCliente);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigo1;
    private javax.swing.JLabel lbCodigo2;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JTable tContador;
    private javax.swing.JTable tCuotas;
    private javax.swing.JTable tDetalle;
    private javax.swing.JTable tDetalleFinanciamiento;
    private javax.swing.JTable tFinanciamiento;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
