/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.caja;

//import java.awt.Color;
//import java.awt.Component;
import caja.bo.CongresoBO;
import caja.frm.frmPrincipal;
import caja.mapeo.entidades.Congreso;
import caja.mapeo.entidades.CongresoParticipantes;
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
import java.util.List;
//import java.util.Locale;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import javax.swing.JOptionPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author user
 */
public class frmCongresoListado extends javax.swing.JInternalFrame {

    private List<CongresoParticipantes> lParticipantes;

    /**
     * Creates new form frmRequerimientos
     */
    public frmCongresoListado() {
        initComponents();

        TableColumn columna = tParticipantes.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tParticipantes.getColumn("DNI");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tParticipantes.getColumn("APELLIDOS");
        columna.setPreferredWidth(260);
        columna.setMinWidth(260);
        columna.setMaxWidth(260);
        columna = tParticipantes.getColumn("APE MAT");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tParticipantes.getColumn("NOMBRE");
        columna.setPreferredWidth(130);
        columna.setMinWidth(130);
        columna.setMaxWidth(130);
        columna = tParticipantes.getColumn("TIPO");
        columna.setPreferredWidth(80);
        columna.setMinWidth(80);
        columna.setMaxWidth(80);
        columna = tParticipantes.getColumn("NRO CMP");
        columna.setPreferredWidth(140);
        columna.setMinWidth(140);
        columna.setMaxWidth(140);
        columna = tParticipantes.getColumn("NOMBRE CONGRESO");
        columna.setPreferredWidth(200);
        columna.setMinWidth(200);
        columna.setMaxWidth(200);
//        columna = tParticipantes.getColumn("FOLIO");
//        columna.setPreferredWidth(50);
//        columna.setMinWidth(50);
//        columna.setMaxWidth(50);
//        columna = tParticipantes.getColumn("F. DIPLOMA");
//        columna.setPreferredWidth(70);
//        columna.setMinWidth(70);
//        columna.setMaxWidth(70);
//        columna = tParticipantes.getColumn("NRO DIPLOMA");
//        columna.setPreferredWidth(80);
//        columna.setMinWidth(80);
//        columna.setMaxWidth(80);
//        columna = tGradosTitulos.getColumn("DOCUMENTO");
//        columna.setPreferredWidth(140);
//        columna.setMinWidth(140);
//        columna.setMaxWidth(140);
//        columna = tGradosTitulos.getColumn("FECHA");
//        columna.setPreferredWidth(80);
//        columna.setMinWidth(80);
//        columna.setMaxWidth(80);
//        columna = tGradosTitulos.getColumn("MONTO");
//        columna.setPreferredWidth(65);
//        columna.setMinWidth(65);
//        columna.setMaxWidth(65);
//        columna = tGradosTitulos.getColumn("M APROB.");
//        columna.setPreferredWidth(65);
//        columna.setMinWidth(65);
//        columna.setMaxWidth(65);
        columna = tParticipantes.getColumn("CONGRESOPARTICIPANTEOBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);

//        Date ahora = new Date();
//        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
//        this.txtDesde.setText(formateador.format(ahora));
//        this.txtHasta.setText(formateador.format(ahora));
//        EstadoBO eBO = EstadoBO.getInstance();
//        List<Estado> lEstados = eBO.ObtenerTodosEstados();
//        lEstados.stream().forEach((e) -> {
//            cbEstado.addItem(e);
//        });
//
        CongresoBO cBO = CongresoBO.getInstance();
        List<Congreso> lCongreso = cBO.ObtenerTodasCongresos();
        for (Congreso c : lCongreso) {
            cbCongreso.addItem(c);
        }
//
//        tDocumentos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
//                setBackground(Color.white);//color de fondo
//                table.getClass().getCanonicalName();
//                table.setForeground(Color.black);//color de texto
//                //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
//                if (table.getValueAt(row, 9) != null) {
//                    if (((Estado) table.getValueAt(row, 9)).getId() == 4) { //DENEGADO
//                        setBackground(Color.decode("#ffa0a2"));
//                    }
//                    if (((Estado) table.getValueAt(row, 9)).getId() == 2) { //POR APROBAR
//                        setBackground(Color.decode("#f9ff47"));
//                    }
//                    if (((Estado) table.getValueAt(row, 9)).getId() == 3) { //APROBADO
//                        setBackground(Color.decode("#99e712"));
//                    }
//                }
//                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
//                return this;
//            }
//        });

        tParticipantes.setAutoResizeMode(tParticipantes.AUTO_RESIZE_OFF);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tParticipantes = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbCongreso = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        btnExportar = new javax.swing.JButton();
        txtFiltroNombre = new javax.swing.JTextField();
        txtFiltroNombre.addKeyListener(
            new KeyAdapter(){
                public void keyReleased (KeyEvent e){
                    FiltrarNombre();
                }} );
                jLabel2 = new javax.swing.JLabel();

                jTextField1.setText("jTextField1");

                setClosable(true);
                setTitle("LISTADO GRADOS Y TITULOS");
                setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N
                setPreferredSize(new java.awt.Dimension(900, 600));

                tParticipantes.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "CONGRESOPARTICIPANTEOBJ", "NRO", "DNI", "APELLIDOS", "APE MAT", "NOMBRE", "TIPO", "CODIGO", "NOMBRE CONGRESO", "NRO CMP", "FECHA CMP", "E. MAT."
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tParticipantes.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tParticipantesMouseClicked(evt);
                    }
                });
                jScrollPane1.setViewportView(tParticipantes);

                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/buscar10.png"))); // NOI18N
                btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarActionPerformed(evt);
                    }
                });

                jLabel1.setText("CONGRESO:");

                btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/excel10.png"))); // NOI18N
                btnExportar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnExportarActionPerformed(evt);
                    }
                });

                jLabel2.setText("NOMBRE:");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1)
                                    .addComponent(jLabel1)
                                    .addComponent(cbCongreso, 0, 688, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                    .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(9, 9, 9)
                                .addComponent(cbCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

//    private List BuscarDocumentos1Opcion() throws ParseException {
//        DocumentoBO dBO = DocumentoBO.getInstance();
//        if (chkPersona.isSelected()) {
//            return dBO.BuscarDocumentosPorPersona(txtPersona.getText());
//        } else {
//            if (chkEstado.isSelected()) {
//                return dBO.BuscarDocumentosPorEstado(((Estado) cbEstado.getSelectedItem()).getId());
//            } else {
//                return dBO.BuscarDocumentosPorDependencia(((Dependencia) cbDependencia.getSelectedItem()).getId());
//            }
//        }
//    }
//    private List BuscarDocumentos2Opciones() throws ParseException {
//        DocumentoBO dBO = DocumentoBO.getInstance();
//        if (chkPersona.isSelected() && chkEstado.isSelected()) {
//            return dBO.BuscarDocumentosPorPersona_Estado(txtPersona.getText(), ((Estado) cbEstado.getSelectedItem()).getId());
//        }
//        if (chkPersona.isSelected() && chkDependencia.isSelected()) {
//            return dBO.BuscarDocumentosPorPersona_Dependencia(txtPersona.getText(), ((Dependencia) cbDependencia.getSelectedItem()).getId());
//        }
//        if (chkDependencia.isSelected() && chkEstado.isSelected()) {
//            return dBO.BuscarDocumentosPorDependencia_Estado(((Dependencia) cbDependencia.getSelectedItem()).getId(), ((Estado) cbEstado.getSelectedItem()).getId());
//        }
//        return null;
//    }
//    private List BuscarDocumentos3Opciones() throws ParseException {
//        DocumentoBO dBO = DocumentoBO.getInstance();
//        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
//        return dBO.BuscarDocumentosPorEstado_Dependencia_Persona(((Estado) cbEstado.getSelectedItem()).getId(), ((Dependencia) cbDependencia.getSelectedItem()).getId(), txtPersona.getText());
//    }
    private void BuscarTodosCongresos() {
        CongresoBO cBO = CongresoBO.getInstance();
        lParticipantes = cBO.ObtenerTodosParticipantesCongreso(((Congreso) cbCongreso.getSelectedItem()).getId());
        DefaultTableModel modelDetalle = (DefaultTableModel) tParticipantes.getModel();
        modelDetalle.setNumRows(0);
        int contador = 0;
        for (CongresoParticipantes cp : lParticipantes) {
            modelDetalle.setNumRows(contador);
            modelDetalle.addRow(new Object[contador]);
            tParticipantes.setValueAt(cp, contador, 0);
            tParticipantes.setValueAt(contador + 1, contador, 1);
            tParticipantes.setValueAt(cp.getCliente().getPnroDocumento(), contador, 2);
            tParticipantes.setValueAt(cp.getCliente().getPapePat() + " " + cp.getCliente().getPapeMat(), contador, 3);
            tParticipantes.setValueAt(cp.getCliente().getPapeMat(), contador, 4);
            tParticipantes.setValueAt(cp.getCliente().getPnombre(), contador, 5);
            if (cp.getTipoParticipante() == 1) {
                tParticipantes.setValueAt("ORDINARIO", contador, 6);
            }
            if (cp.getTipoParticipante() == 2) {
                tParticipantes.setValueAt("OBSERVADOR", contador, 6);
            }
            if (cp.getTipoParticipante() == 3) {
                tParticipantes.setValueAt("ACOMPAÑANTE", contador, 6);
            }
            if (cp.getTipoParticipante() == 4) {
                tParticipantes.setValueAt("ESTUDIANTE", contador, 6);
            }
            tParticipantes.setValueAt(cp.getDocumentoPagoDet().getConceptoPagoDetalle().getTipoCodigo() + cp.getDocumentoPagoDet().getConceptoPagoDetalle().getCodigo(), contador, 7);
            tParticipantes.setValueAt(cp.getDocumentoPagoDet().getConceptoPagoDetalle().getDescripcion(), contador, 8);
            tParticipantes.setValueAt(cp.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getTipoDocPago().getNombreDocPago() + " " + cp.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getSerie().getSerie() + "-" + cp.getDocumentoPagoDet().getDocumentoPago().getNroDocumentoPago(), contador, 9);
            tParticipantes.setValueAt(cp.getDocumentoPagoDet().getDocumentoPago().getFechaPago(), contador, 10);
            if (cp.getEntregoMaterial().equals("S")) {
                tParticipantes.setValueAt("SI", contador, 11);
            } else {
                tParticipantes.setValueAt("NO", contador, 11);
            }
//            tParticipantes.setValueAt(gt, contador, 9);
            contador++;
        }
    }

    private void FiltrarNombre() {
        if (this.lParticipantes != null) {
            if (!lParticipantes.isEmpty()) {
                String pFiltro = txtFiltroNombre.getText();
                pFiltro = pFiltro.toUpperCase();
                txtFiltroNombre.setText(pFiltro);
                DefaultTableModel model = (DefaultTableModel) tParticipantes.getModel();
                int contador = 0;
                String nombre;
                for (CongresoParticipantes cp : lParticipantes) {
                    nombre = cp.getCliente().getPapePat() + " " + cp.getCliente().getPapeMat() + " " + cp.getCliente(). getPnombre();
                    if (nombre.contains(pFiltro)) {
                        model.setNumRows(contador);
                        model.addRow(new Object[contador]);
                        tParticipantes.setValueAt(cp, contador, 0);
                        tParticipantes.setValueAt(contador + 1, contador, 1);
                        tParticipantes.setValueAt(cp.getCliente().getPnroDocumento(), contador, 2);
                        tParticipantes.setValueAt(cp.getCliente().getPapePat() + " " + cp.getCliente().getPapeMat(), contador, 3);
                        tParticipantes.setValueAt(cp.getApeMat(), contador, 4);
                        tParticipantes.setValueAt(cp.getCliente().getPnombre(), contador, 5);
                        if (cp.getTipoParticipante() == 1) {
                            tParticipantes.setValueAt("ORDINARIO", contador, 6);
                        }
                        if (cp.getTipoParticipante() == 2) {
                            tParticipantes.setValueAt("OBSERVADOR", contador, 6);
                        }
                        if (cp.getTipoParticipante() == 3) {
                            tParticipantes.setValueAt("ACOMPAÑANTE", contador, 6);
                        }
                        if (cp.getTipoParticipante() == 4) {
                            tParticipantes.setValueAt("ESTUDIANTE", contador, 6);
                        }
                        tParticipantes.setValueAt(cp.getDocumentoPagoDet().getConceptoPagoDetalle().getTipoCodigo() + cp.getDocumentoPagoDet().getConceptoPagoDetalle().getCodigo(), contador, 7);
                        tParticipantes.setValueAt(cp.getDocumentoPagoDet().getConceptoPagoDetalle().getDescripcion(), contador, 8);
                        tParticipantes.setValueAt(cp.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getTipoDocPago().getNombreDocPago() + " " + cp.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getSerie().getSerie() + "-" + cp.getDocumentoPagoDet().getDocumentoPago().getNroDocumentoPago(), contador, 9);
                        tParticipantes.setValueAt(cp.getDocumentoPagoDet().getDocumentoPago().getFechaPago(), contador, 10);
                        if (cp.getEntregoMaterial().equals("S")) {
                            tParticipantes.setValueAt("SI", contador, 11);
                        } else {
                            tParticipantes.setValueAt("NO", contador, 11);
                        }
                        contador++;
                    }
                }
            }
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
                BuscarTodosCongresos();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_btnBuscarActionPerformed

//    private void CargarGradoTitulo() {
//        int fila = tParticipantes.getSelectedRow();
//        if (fila >= 0) {
//            CongresoParticipantes cp = (CongresoParticipantes) tParticipantes.getValueAt(fila, 0);
//            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
//            f.AbrirFormularioEntregaMaterial(this, cp);
//        }
//    }
    public void EntregaMaterial(CongresoParticipantes cp) {
        int fila = tParticipantes.getSelectedRow();
        tParticipantes.setValueAt(cp, fila, 0);
        if (cp.getEntregoMaterial().equals("S")) {
            tParticipantes.setValueAt("SI", fila, 11);
        } else {
            tParticipantes.setValueAt("NO", fila, 11);
        }
    }


    private void tParticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tParticipantesMouseClicked
        int fila = tParticipantes.getSelectedRow();
        if (fila >= 0) {
            CongresoParticipantes cp = (CongresoParticipantes) tParticipantes.getValueAt(fila, 0);
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            f.AbrirFormularioEntregaMaterial(this, cp);
        }
    }//GEN-LAST:event_tParticipantesMouseClicked

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        CongresoBO cBO = CongresoBO.getInstance();
        cBO.ExportarArchivoExcelParticipantesCongreso(((Congreso) cbCongreso.getSelectedItem()).getId());
    }//GEN-LAST:event_btnExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JComboBox cbCongreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tParticipantes;
    private javax.swing.JTextField txtFiltroNombre;
    // End of variables declaration//GEN-END:variables
}
