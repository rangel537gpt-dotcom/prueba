/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.caja;

import caja.bo.DocumentoPagoBO;
import caja.bo.SeguridadBO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.frm.frmPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author user
 */
public class frmBuscarComprobante extends javax.swing.JInternalFrame {
    
    private List<Object> lComprobantes;

    /**
     * Creates new form frmBuscarComprobn
     */
    public frmBuscarComprobante() {
        initComponents();
        TableColumn columna = tComprobantes.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tComprobantes.getColumn("CMP");
        columna.setPreferredWidth(100);
        columna.setMinWidth(100);
        columna.setMaxWidth(100);
        columna = tComprobantes.getColumn("SERIE");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tComprobantes.getColumn("NRO CMP");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tComprobantes.getColumn("FECHA CMP");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tComprobantes.getColumn("TIPO PAGADOR");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tComprobantes.getColumn("MONTO");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tComprobantes.getColumn("IDDOCUMENTO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("CODIGOPAGADOR");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("DIRECCIONPAGADOR");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("ESTADO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("IDCLIENTE");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("FCHAFIL");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("IDTIPODOC");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("TIENEIGV");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tComprobantes.getColumn("MONEDA");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
        java.util.Date ahora = new java.util.Date();
        int nroMes = Integer.valueOf(formatoMes.format(ahora));
        String mes = this.ObtenerMes(nroMes);
        cbMes.setSelectedItem(mes);
        
        SimpleDateFormat fAnio = new SimpleDateFormat("yyyy");
        int anioActual = Integer.valueOf(fAnio.format(ahora));
        for (int i = anioActual + 1; i >= 2000; i--) {
            cbAnio.addItem(String.valueOf(i));
        }
        cbAnio.setSelectedItem(String.valueOf(anioActual));
        
        SimpleDateFormat formatoDia = new SimpleDateFormat("dd");
        txtDia.setText(formatoDia.format(ahora));
        
        tComprobantes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
                setBackground(Color.white);//color de fondo
                table.getClass().getCanonicalName();
                table.setForeground(Color.black);//color de texto
                //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
                if (String.valueOf(table.getValueAt(row, 11)).equals("ANULADO")) {
                    setBackground(Color.decode("#ffa0a2"));
                }
                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                return this;
            }
        });
//        Date ahora = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy");
        cbAnio.setSelectedItem(f.format(ahora));
    }
    
    private String ObtenerMes(int nroMes) {
        String mes = "";
        if (nroMes == 1) {
            mes = "ENERO";
        }
        if (nroMes == 2) {
            mes = "FEBRERO";
        }
        if (nroMes == 3) {
            mes = "MARZO";
        }
        if (nroMes == 4) {
            mes = "ABRIL";
        }
        if (nroMes == 5) {
            mes = "MAYO";
        }
        if (nroMes == 6) {
            mes = "JUNIO";
        }
        if (nroMes == 7) {
            mes = "JULIO";
        }
        if (nroMes == 8) {
            mes = "AGOSTO";
        }
        if (nroMes == 9) {
            mes = "SEPTIEMBRE";
        }
        if (nroMes == 10) {
            mes = "OCTUBRE";
        }
        if (nroMes == 11) {
            mes = "NOVIEMBRE";
        }
        if (nroMes == 12) {
            mes = "DICIEMBRE";
        }
        return mes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        cbAnio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbMes = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtDia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tComprobantes = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtNroCmp = new javax.swing.JTextField();
        txtNroCmp.addKeyListener(
            new KeyAdapter(){
                //@Override
                public void keyReleased (KeyEvent e){
                    FiltrarNroCmp();
                }} );
                txtNombre = new javax.swing.JTextField();
                txtNombre.addKeyListener(
                    new KeyAdapter(){
                        //@Override
                        public void keyReleased (KeyEvent e){
                            FiltrarNombre();
                        }} );
                        txtSerie = new javax.swing.JTextField();
                        txtComprobante = new javax.swing.JTextField();
                        rbAnio = new javax.swing.JRadioButton();
                        jLabel5 = new javax.swing.JLabel();
                        txtNroComprobante = new javax.swing.JTextField();
                        rbNroComprobante = new javax.swing.JRadioButton();

                        setClosable(true);
                        setIconifiable(true);
                        setTitle("BUSCAR COMPROBANTE");
                        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N
                        setPreferredSize(new java.awt.Dimension(900, 600));

                        jLabel1.setText("AÑO:");

                        jLabel2.setText("MES:");

                        cbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
                        cbMes.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbMesItemStateChanged(evt);
                            }
                        });

                        jLabel3.setText("DÍA:");

                        txtDia.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtDiaKeyTyped(evt);
                            }
                        });

                        tComprobantes.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "CMP", "SERIE", "NRO CMP", "FECHA CMP", "TIPO PAGADOR", "NOMBRE", "MONTO", "IDDOCUMENTO", "CODIGOPAGADOR", "DIRECCIONPAGADOR", "ESTADO", "IDCLIENTE", "FCHAFIL", "IDTIPODOC", "TIENEIGV", "MONEDA"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tComprobantes.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tComprobantesMouseClicked(evt);
                            }
                        });
                        jScrollPane1.setViewportView(tComprobantes);

                        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/iconBuscar.png"))); // NOI18N
                        btnBuscar.setText("BUSCAR");
                        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarActionPerformed(evt);
                            }
                        });

                        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 47, Short.MAX_VALUE)
                        );

                        jLabel4.setText("FILTRAR");

                        txtNroCmp.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtNroCmpKeyTyped(evt);
                            }
                        });

                        buttonGroup1.add(rbAnio);
                        rbAnio.setSelected(true);

                        jLabel5.setText("NRO COMPROBANTE:");

                        txtNroComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtNroComprobanteKeyTyped(evt);
                            }
                        });

                        buttonGroup1.add(rbNroComprobante);

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(7, 7, 7)
                                        .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNroCmp, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3))
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNroComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                            .addComponent(txtDia))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbNroComprobante, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(rbAnio, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(cbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2)
                                            .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)
                                            .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(rbAnio)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtNroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5))
                                                .addComponent(rbNroComprobante))))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNroCmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void FiltrarNombre() {
        if (!this.lComprobantes.isEmpty()) {
            String pFiltro = txtNombre.getText();
            pFiltro = pFiltro.toUpperCase();
            txtNombre.setText(pFiltro);
            DefaultTableModel model = (DefaultTableModel) tComprobantes.getModel();
            int contador = 0;
            for (Object pobj : lComprobantes) {
                Object[] obj = (Object[]) pobj;
                if (((String) obj[6]).contains(pFiltro)) {
                    model.setNumRows(contador);
                    model.addRow(new Object[contador]);
                    tComprobantes.setValueAt(contador + 1, contador, 0);
                    tComprobantes.setValueAt(obj[1], contador, 1);
                    tComprobantes.setValueAt(String.format("%04d", obj[2]), contador, 2);
                    tComprobantes.setValueAt(String.format("%07d", obj[3]), contador, 3);
                    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                    tComprobantes.setValueAt(formateador.format(obj[4]), contador, 4);
                    if (obj[5].equals("C")) {
                        tComprobantes.setValueAt("CONTADOR", contador, 5);
                    }
                    if (obj[5].equals("S")) {
                        tComprobantes.setValueAt("SOCIEDAD", contador, 5);
                    }
                    if (obj[5].equals("E")) {
                        tComprobantes.setValueAt("EMPRESA", contador, 5);
                    }
                    if (obj[5].equals("P")) {
                        tComprobantes.setValueAt("PERSONA", contador, 5);
                    }
                    tComprobantes.setValueAt(obj[6], contador, 6);
                    tComprobantes.setValueAt(obj[7], contador, 7);
                    tComprobantes.setValueAt(obj[0], contador, 8);
                    tComprobantes.setValueAt(obj[8], contador, 9);
                    tComprobantes.setValueAt(obj[9], contador, 10);
                    tComprobantes.setValueAt(obj[10], contador, 11);
                    tComprobantes.setValueAt(obj[11], contador, 12);
                    tComprobantes.setValueAt(obj[12], contador, 13);
                    tComprobantes.setValueAt(obj[13], contador, 14);
                    tComprobantes.setValueAt(obj[14], contador, 15);
                    contador++;
                }
            }
        }
    }
    
    private void FiltrarNroCmp() {
        if (!this.lComprobantes.isEmpty()) {
            if (!txtNroCmp.getText().isEmpty()) {
                int nro = Integer.valueOf(txtNroCmp.getText());
                DefaultTableModel model = (DefaultTableModel) tComprobantes.getModel();
                int contador = 0;
                model.setNumRows(0);
                for (Object pobj : lComprobantes) {
                    Object[] obj = (Object[]) pobj;
                    if (((int) obj[3]) == nro) {
                        model.setNumRows(contador);
                        model.addRow(new Object[contador]);
                        tComprobantes.setValueAt(contador + 1, contador, 0);
                        tComprobantes.setValueAt(obj[1], contador, 1);
                        tComprobantes.setValueAt(String.format("%04d", obj[2]), contador, 2);
                        tComprobantes.setValueAt(String.format("%07d", obj[3]), contador, 3);
                        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                        tComprobantes.setValueAt(formateador.format(obj[4]), contador, 4);
                        if (obj[5].equals("C")) {
                            tComprobantes.setValueAt("CONTADOR", contador, 5);
                        }
                        if (obj[5].equals("S")) {
                            tComprobantes.setValueAt("SOCIEDAD", contador, 5);
                        }
                        if (obj[5].equals("E")) {
                            tComprobantes.setValueAt("EMPRESA", contador, 5);
                        }
                        if (obj[5].equals("P")) {
                            tComprobantes.setValueAt("PERSONA", contador, 5);
                        }
                        tComprobantes.setValueAt(obj[6], contador, 6);
                        tComprobantes.setValueAt(obj[7], contador, 7);
                        tComprobantes.setValueAt(obj[0], contador, 8);
                        tComprobantes.setValueAt(obj[8], contador, 9);
                        tComprobantes.setValueAt(obj[9], contador, 10);
                        tComprobantes.setValueAt(obj[10], contador, 11);
                        tComprobantes.setValueAt(obj[11], contador, 12);
                        tComprobantes.setValueAt(obj[12], contador, 13);
                        tComprobantes.setValueAt(obj[13], contador, 14);
                        tComprobantes.setValueAt(obj[14], contador, 15);
                        contador++;
                    }
                }
            } else {
                int contador = 0;
                for (Object pobj : lComprobantes) {
                    Object[] obj = (Object[]) pobj;
                    DefaultTableModel model = (DefaultTableModel) tComprobantes.getModel();
                    model.setNumRows(contador);
                    model.addRow(new Object[contador]);
                    tComprobantes.setValueAt(contador + 1, contador, 0);
                    tComprobantes.setValueAt(obj[1], contador, 1);
                    tComprobantes.setValueAt(String.format("%04d", obj[2]), contador, 2);
                    tComprobantes.setValueAt(String.format("%07d", obj[3]), contador, 3);
                    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                    tComprobantes.setValueAt(formateador.format(obj[4]), contador, 4);
                    if (obj[5].equals("C")) {
                        tComprobantes.setValueAt("CONTADOR", contador, 5);
                    }
                    if (obj[5].equals("S")) {
                        tComprobantes.setValueAt("SOCIEDAD", contador, 5);
                    }
                    if (obj[5].equals("E")) {
                        tComprobantes.setValueAt("EMPRESA", contador, 5);
                    }
                    if (obj[5].equals("P")) {
                        tComprobantes.setValueAt("PERSONA", contador, 5);
                    }
                    tComprobantes.setValueAt(obj[6], contador, 6);
                    tComprobantes.setValueAt(obj[7], contador, 7);
                    tComprobantes.setValueAt(obj[0], contador, 8);
                    tComprobantes.setValueAt(obj[8], contador, 9);
                    tComprobantes.setValueAt(obj[9], contador, 10);
                    tComprobantes.setValueAt(obj[10], contador, 11);
                    tComprobantes.setValueAt(obj[11], contador, 12);
                    tComprobantes.setValueAt(obj[12], contador, 13);
                    tComprobantes.setValueAt(obj[13], contador, 14);
                    tComprobantes.setValueAt(obj[14], contador, 15);
                    contador++;
                }
            }
        }
    }
    
    private void Buscar() {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        String anio = cbAnio.getSelectedItem().toString();
        String mes = cbMes.getSelectedItem().toString();
        String dia = txtDia.getText();
        DefaultTableModel modelDetalle = (DefaultTableModel) tComprobantes.getModel();
        //List<DocumentoPago> lComprobantes = dpBO.BuscarComprobantePago(anio, mes, dia);
        this.lComprobantes = dpBO.BuscarComprobantePago(anio, mes, dia);
        int contador = 0;
        modelDetalle.setNumRows(0);
        for (Object dp : lComprobantes) {
            Object[] objDp = (Object[]) dp;
            modelDetalle.setNumRows(contador);
            modelDetalle.addRow(new Object[contador]);
            tComprobantes.setValueAt(contador + 1, contador, 0);
            tComprobantes.setValueAt(objDp[1], contador, 1);
            tComprobantes.setValueAt(objDp[2], contador, 2);
            tComprobantes.setValueAt(String.format("%07d", objDp[3]), contador, 3);
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            tComprobantes.setValueAt(formateador.format(objDp[4]), contador, 4);
            if (objDp[5].equals("C")) {
                tComprobantes.setValueAt("CONTADOR", contador, 5);
            }
            if (objDp[5].equals("S")) {
                tComprobantes.setValueAt("SOCIEDAD", contador, 5);
            }
            if (objDp[5].equals("E")) {
                tComprobantes.setValueAt("EMPRESA", contador, 5);
            }
            if (objDp[5].equals("P")) {
                tComprobantes.setValueAt("PERSONA", contador, 5);
            }
            tComprobantes.setValueAt(objDp[6], contador, 6);
            tComprobantes.setValueAt(objDp[7], contador, 7);
            tComprobantes.setValueAt(objDp[0], contador, 8);
            tComprobantes.setValueAt(objDp[8], contador, 9);
            tComprobantes.setValueAt(objDp[9], contador, 10);
            tComprobantes.setValueAt(objDp[10], contador, 11);
            tComprobantes.setValueAt(objDp[11], contador, 12);
            tComprobantes.setValueAt(objDp[12], contador, 13);
            tComprobantes.setValueAt(objDp[13], contador, 14);
            tComprobantes.setValueAt(objDp[14], contador, 15);
            tComprobantes.setValueAt(objDp[15], contador, 16);
            contador++;
        }
        txtNombre.setText("");
        txtSerie.setText("");
        txtComprobante.setText("");
        txtNroCmp.setText("");
    }
    
    private void BuscarNroComprobante() {
        if (!txtNroComprobante.getText().isEmpty()) {
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            String nro = txtNroComprobante.getText();
            DefaultTableModel modelDetalle = (DefaultTableModel) tComprobantes.getModel();
            this.lComprobantes = dpBO.BuscarNroComprobantePago(Integer.valueOf(nro));
            int contador = 0;
            modelDetalle.setNumRows(0);
            for (Object dp : lComprobantes) {
                Object[] objDp = (Object[]) dp;
                modelDetalle.setNumRows(contador);
                modelDetalle.addRow(new Object[contador]);
                tComprobantes.setValueAt(contador + 1, contador, 0);
                tComprobantes.setValueAt(objDp[1], contador, 1);
                tComprobantes.setValueAt(objDp[2], contador, 2);
                tComprobantes.setValueAt(String.format("%07d", objDp[3]), contador, 3);
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                tComprobantes.setValueAt(formateador.format(objDp[4]), contador, 4);
                if (objDp[5].equals("C")) {
                    tComprobantes.setValueAt("CONTADOR", contador, 5);
                }
                if (objDp[5].equals("S")) {
                    tComprobantes.setValueAt("SOCIEDAD", contador, 5);
                }
                if (objDp[5].equals("E")) {
                    tComprobantes.setValueAt("EMPRESA", contador, 5);
                }
                if (objDp[5].equals("P")) {
                    tComprobantes.setValueAt("PERSONA", contador, 5);
                }
                tComprobantes.setValueAt(objDp[6], contador, 6);
                tComprobantes.setValueAt(objDp[7], contador, 7);
                tComprobantes.setValueAt(objDp[0], contador, 8);
                tComprobantes.setValueAt(objDp[8], contador, 9);
                tComprobantes.setValueAt(objDp[9], contador, 10);
                tComprobantes.setValueAt(objDp[10], contador, 11);
                tComprobantes.setValueAt(objDp[11], contador, 12);
                tComprobantes.setValueAt(objDp[12], contador, 13);
                tComprobantes.setValueAt(objDp[13], contador, 14);
                tComprobantes.setValueAt(objDp[14], contador, 15);
                tComprobantes.setValueAt(objDp[15], contador, 16);
                contador++;
            }
            txtNombre.setText("");
            txtSerie.setText("");
            txtComprobante.setText("");
            txtNroCmp.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "DEBE PONER UN NÚMERO DE COMPROBANTE",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
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
                if (rbAnio.isSelected()) {
                    Buscar();
                } else {
                    BuscarNroComprobante();
                }
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMesItemStateChanged
        String mes = (String) evt.getItem();
        if (mes.equals("--------------")) {
            txtDia.setText("");
        }
    }//GEN-LAST:event_cbMesItemStateChanged

    private void txtDiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
            //Error.setText("Ingresa Solo Numeros";
        }
    }//GEN-LAST:event_txtDiaKeyTyped
    
    public void CargarDocumentoPago() {
//        DocumentoPago doc = new DocumentoPago();
        int fila = tComprobantes.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tComprobantes.getModel();
        String idDocumentoPago = String.valueOf(model.getValueAt(fila, 8));
        String tipoPagador = String.valueOf(model.getValueAt(fila, 5));
        String nroSerie = String.valueOf(model.getValueAt(fila, 2));
        String nroComprobante = String.valueOf(model.getValueAt(fila, 3));
        String tipoComprobante = String.valueOf(model.getValueAt(fila, 1));
        String fechaComprobante = String.valueOf(model.getValueAt(fila, 4));
        String codigo = String.valueOf(model.getValueAt(fila, 9));
        String direccion = String.valueOf(model.getValueAt(fila, 10));
        String estado = String.valueOf(model.getValueAt(fila, 11));
        int idCliente = Integer.valueOf(String.valueOf(model.getValueAt(fila, 12)));
        Date fechaAfiliacion = null;
        if (tipoPagador.equals("CONTADOR") || tipoPagador.equals("SOCIEDAD")) {
            fechaAfiliacion = Date.valueOf(String.valueOf(model.getValueAt(fila, 13)));
        }
        String nombrePagador = String.valueOf(model.getValueAt(fila, 6));
        String idTipoDoc = String.valueOf(model.getValueAt(fila, 14));
        String tieneIGV = String.valueOf(model.getValueAt(fila, 15));
        String moneda = String.valueOf(model.getValueAt(fila, 16));
        
        DocumentoPagoBO docBO = DocumentoPagoBO.getInstance();
        TipoDocPago tipoDoc = new TipoDocPago();
        tipoDoc.setIdTipoDocPago(Integer.valueOf(idTipoDoc));
        tipoDoc.setNombreDocPago(tipoComprobante);
        TipoDocSerie tds = docBO.ObtenerTipoDocSerie(Integer.valueOf(idTipoDoc), nroSerie);
        SeguridadBO sBO = SeguridadBO.getINSTANCE();
        DocumentoPago doc = (DocumentoPago) sBO.CargarObjeto("DocumentoPago", Integer.valueOf(idDocumentoPago));
        doc.setTipoDocSerie(tds);
        String idTipoPagador = "";
        List<Cliente> lClientes = docBO.ObtenerTodosClientes();
        Cliente cliente = null;
        for (Cliente c : lClientes) {
            if (c.getIdCliente() == idCliente) {
                cliente = c;
            }
        }
        doc.setCliente(cliente);

        /*-----------ARREGLAR ESTA PARTE*----------------*/
        DocumentoPago docDATA = docBO.ObtenerDocumentoPago(Integer.valueOf(idDocumentoPago));
        /*-----------ARREGLAR ESTA PARTE*----------------*/
        
        doc.setClienteByIdContadorEmpresa(docDATA.getClienteByIdContadorEmpresa());
        
        if (tipoPagador.equals("CONTADOR")) {
            idTipoPagador = "C";
        }
        if (tipoPagador.equals("SOCIEDAD")) {
            idTipoPagador = "S";
        }
        if (tipoPagador.equals("EMPRESA")) {
            idTipoPagador = "E";
            
        }
        if (tipoPagador.equals("PERSONA")) {
            idTipoPagador = "P";
        }
        Cobrador cobrador = docBO.ObtenerCobradorComprobantePago(doc.getIdDocumentoPago());
        if (cobrador != null) {
            doc.setCobrador(cobrador);
        }
//        docBO.setDocumentoPago(doc);
        frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        fPrin.AbrirFormularioDetalleComprobantePago(doc, idTipoPagador, 2);
    }

    private void tComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tComprobantesMouseClicked
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
    }//GEN-LAST:event_tComprobantesMouseClicked

    private void txtNroCmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroCmpKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
            //Error.setText("Ingresa Solo Numeros";
        }
    }//GEN-LAST:event_txtNroCmpKeyTyped

    private void txtNroComprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroComprobanteKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
            //Error.setText("Ingresa Solo Numeros";
        }
    }//GEN-LAST:event_txtNroComprobanteKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbAnio;
    private javax.swing.JComboBox cbMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbAnio;
    private javax.swing.JRadioButton rbNroComprobante;
    private javax.swing.JTable tComprobantes;
    private javax.swing.JTextField txtComprobante;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroCmp;
    private javax.swing.JTextField txtNroComprobante;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
