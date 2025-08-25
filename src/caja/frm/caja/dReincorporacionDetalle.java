/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.caja;

import caja.bo.CuotasBO;
import caja.bo.ReincorporacionBO;
import caja.mapeo.entidades.Reincorporacion;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author user
 */
public class dReincorporacionDetalle extends javax.swing.JDialog {

    /**
     * Creates new form dReincorporacionDetalle
     */
    public dReincorporacionDetalle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        tDetalleReincorporacion.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tDetalleReincorporacion.getColumnModel().getColumn(2).setCellRenderer(Alinear);
        tDetalleReincorporacion.getColumnModel().getColumn(3).setCellRenderer(Alinear);
        tDetalleReincorporacion.getColumnModel().getColumn(4).setCellRenderer(Alinear);

        TableColumn columna = tDetalle.getColumn("");
        columna.setPreferredWidth(20);
        columna.setMinWidth(20);
        columna.setMaxWidth(20);
        columna = tDetalle.getColumn(" ");
        columna.setPreferredWidth(20);
        columna.setMinWidth(20);
        columna.setMaxWidth(20);
        columna = tDetalle.getColumn("  ");
        columna.setPreferredWidth(20);
        columna.setMinWidth(20);
        columna.setMaxWidth(20);
        columna = tDetalle.getColumn("   ");
        columna.setPreferredWidth(20);
        columna.setMinWidth(20);
        columna.setMaxWidth(20);
        columna = tDetalle.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tDetalle.getColumn("FONDO");
        columna.setPreferredWidth(60);
        columna.setMinWidth(60);
        columna.setMaxWidth(60);
        columna = tDetalle.getColumn("OTROS");
        columna.setPreferredWidth(60);
        columna.setMinWidth(60);
        columna.setMaxWidth(60);
        columna = tDetalle.getColumn("LETRA");
        columna.setPreferredWidth(60);
        columna.setMinWidth(60);
        columna.setMaxWidth(60);
        columna = tDetalle.getColumn("CUOTA");
        columna.setPreferredWidth(60);
        columna.setMinWidth(60);
        columna.setMaxWidth(60);
        columna = tDetalle.getColumn("TOTAL");
        columna.setPreferredWidth(60);
        columna.setMinWidth(60);
        columna.setMaxWidth(60);
        columna = tDetalle.getColumn("CRONOGRAMA");
        columna.setPreferredWidth(80);
        columna.setMinWidth(80);
        columna.setMaxWidth(80);
        columna = tDetalle.getColumn("ESTADO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);

        tDetalle.getColumnModel().getColumn(2).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(3).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(4).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(5).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(6).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(7).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(8).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(9).setCellRenderer(Alinear);
        tDetalle.getColumnModel().getColumn(10).setCellRenderer(Alinear);
    }

    public String ObtenerMes(int pmes) {
        String mes = "";
        if (pmes == 1) {
            mes = "ENE";
        }
        if (pmes == 2) {
            mes = "FEB";
        }
        if (pmes == 3) {
            mes = "MAR";
        }
        if (pmes == 4) {
            mes = "ABR";
        }
        if (pmes == 5) {
            mes = "MAY";
        }
        if (pmes == 6) {
            mes = "JUN";
        }
        if (pmes == 7) {
            mes = "JUL";
        }
        if (pmes == 8) {
            mes = "AGO";
        }
        if (pmes == 9) {
            mes = "SEP";
        }
        if (pmes == 10) {
            mes = "OCT";
        }
        if (pmes == 11) {
            mes = "NOV";
        }
        if (pmes == 12) {
            mes = "DIC";
        }
        return mes;
    }

    public void CargarDatos(int idReincorporacion) {
        TableColumn columna = tDetalleReincorporacion.getColumn("DESCRIPCIÓN");
        columna.setPreferredWidth(300);
        columna.setMinWidth(300);
        columna.setMaxWidth(300);
        /*tDetalleReincorporacion.getColumn("IMPORTE");
         columna.setPreferredWidth(200);
         columna.setMinWidth(200);
         columna.setMaxWidth(200);
         tDetalleReincorporacion.getColumn("SIN DESC.");
         columna.setPreferredWidth(40);
         columna.setMinWidth(40);
         columna.setMaxWidth(40);*/

        double totalDeudaImporte = 0.0;
        double totalDeudaSinDescuento = 0.0;
        double totalDeudaDescuento = 0.0;

        ReincorporacionBO rBO = ReincorporacionBO.getInstance();
        Reincorporacion reincorporacion = rBO.ObtenerReincorporacion(idReincorporacion);
        double descuento = 0;
        lbCodColegiado.setText(reincorporacion.getCliente().getCcodigoCole());
        lbNombreColegiado.setText(reincorporacion.getCliente().getPapePat() + " " + reincorporacion.getCliente().getPapeMat() + " " + reincorporacion.getCliente().getPnombre());
        DefaultTableModel modelDetalle = (DefaultTableModel) tDetalleReincorporacion.getModel();
        modelDetalle.setNumRows(0);
        modelDetalle.addRow(new Object[0]);
        tDetalleReincorporacion.setValueAt("NRO CUOTAS :" + reincorporacion.getCuotas(), 0, 0);
        tDetalleReincorporacion.setValueAt(reincorporacion.getDeuda(), 0, 1);
        tDetalleReincorporacion.setValueAt("", 0, 2);
        descuento = reincorporacion.getDeuda() / 2;
        tDetalleReincorporacion.setValueAt("-" + String.valueOf(descuento), 0, 3);
        tDetalleReincorporacion.setValueAt(String.valueOf(reincorporacion.getDeuda() - descuento), 0, 4);
        totalDeudaImporte = totalDeudaImporte + reincorporacion.getDeuda();
        totalDeudaDescuento = totalDeudaDescuento + descuento;

        modelDetalle.addRow(new Object[1]);
        tDetalleReincorporacion.setValueAt("FONDO MUTUAL " + this.ObtenerMes(reincorporacion.getMesFondoInicial()) + " " + reincorporacion.getAnioFondoInicial() + " A " + this.ObtenerMes(reincorporacion.getMesFondoFinal()) + " " + reincorporacion.getAnioFondoFinal(), 1, 0);
        tDetalleReincorporacion.setValueAt("", 1, 1);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoFmutual(), 1, 2);
        tDetalleReincorporacion.setValueAt("", 1, 3);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoFmutual(), 1, 4);
        totalDeudaSinDescuento = totalDeudaSinDescuento + reincorporacion.getMontoFmutual();

        modelDetalle.addRow(new Object[2]);
        tDetalleReincorporacion.setValueAt("FONDO CLUB " + this.ObtenerMes(reincorporacion.getMesFondoInicial()) + " " + reincorporacion.getAnioFondoInicial() + " A " + this.ObtenerMes(reincorporacion.getMesFondoFinal()) + " " + reincorporacion.getAnioFondoFinal(), 2, 0);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoFclub(), 2, 1);
        tDetalleReincorporacion.setValueAt("", 2, 2);
        descuento = reincorporacion.getMontoFclub() / 2;
        tDetalleReincorporacion.setValueAt("-" + String.valueOf(descuento), 2, 3);
        tDetalleReincorporacion.setValueAt(String.valueOf(descuento), 2, 4);
        totalDeudaImporte = totalDeudaImporte + reincorporacion.getMontoFclub();
        totalDeudaDescuento = totalDeudaDescuento + descuento;

        modelDetalle.addRow(new Object[3]);
        tDetalleReincorporacion.setValueAt("REINSERCIÓN 2005", 3, 0);
        tDetalleReincorporacion.setValueAt("", 3, 1);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoReinsercion2005(), 3, 2);
        tDetalleReincorporacion.setValueAt("", 3, 3);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoReinsercion2005(), 3, 4);
        totalDeudaSinDescuento = totalDeudaSinDescuento + reincorporacion.getMontoReinsercion2005();

        modelDetalle.addRow(new Object[4]);
        tDetalleReincorporacion.setValueAt("REINCORPORACIÓN 2006", 4, 0);
        tDetalleReincorporacion.setValueAt("", 4, 1);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoReincorporacion2006(), 4, 2);
        tDetalleReincorporacion.setValueAt("", 4, 3);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoReincorporacion2006(), 4, 4);
        totalDeudaSinDescuento = totalDeudaSinDescuento + reincorporacion.getMontoReincorporacion2006();

        modelDetalle.addRow(new Object[5]);
        tDetalleReincorporacion.setValueAt("FINANCIAMIENTOS", 5, 0);
        tDetalleReincorporacion.setValueAt(reincorporacion.getFinanciamiento(), 5, 1);
        tDetalleReincorporacion.setValueAt("", 5, 2);
        tDetalleReincorporacion.setValueAt("-" + String.valueOf(reincorporacion.getFinanciamiento() / 2), 5, 3);
        tDetalleReincorporacion.setValueAt(reincorporacion.getFinanciamiento() / 2, 5, 4);
        totalDeudaImporte = totalDeudaImporte + reincorporacion.getFinanciamiento();
        totalDeudaDescuento = totalDeudaDescuento + reincorporacion.getFinanciamiento() / 2;

        modelDetalle.addRow(new Object[6]);
        tDetalleReincorporacion.setValueAt("OTROS SALDOS PENDIENTE", 6, 0);
        tDetalleReincorporacion.setValueAt(reincorporacion.getOtrosSaldosPendientes(), 6, 1);
        tDetalleReincorporacion.setValueAt("", 6, 2);
        tDetalleReincorporacion.setValueAt(reincorporacion.getOtrosSaldosPendientes() / 2, 6, 3);
        tDetalleReincorporacion.setValueAt(reincorporacion.getOtrosSaldosPendientes() / 2, 6, 4);
        totalDeudaImporte = totalDeudaImporte + reincorporacion.getOtrosSaldosPendientes();
        totalDeudaDescuento = totalDeudaDescuento + reincorporacion.getOtrosSaldosPendientes() / 2;

        modelDetalle.addRow(new Object[7]);
        tDetalleReincorporacion.setValueAt("TOTAL DEUDA", 7, 0);
        tDetalleReincorporacion.setValueAt(totalDeudaImporte, 7, 1);
        tDetalleReincorporacion.setValueAt(totalDeudaSinDescuento, 7, 2);
        tDetalleReincorporacion.setValueAt(totalDeudaDescuento, 7, 3);
        double total = Math.round((totalDeudaImporte + totalDeudaSinDescuento - totalDeudaDescuento) * Math.pow(10, 2)) / Math.pow(10, 2);
        tDetalleReincorporacion.setValueAt(total, 7, 4);

        double montoCuotaTramo1 = 0.0;
        if (reincorporacion.getNroCuotasTramo1() > 0) {
            montoCuotaTramo1 = reincorporacion.getMontoPagarTramo1() / reincorporacion.getNroCuotasTramo1();
        }
        double montoCuotaTramo2 = 0.0;
        if (reincorporacion.getNroCuotasTramo2() > 0) {
            montoCuotaTramo2 = reincorporacion.getMontoPagarTramo2() / reincorporacion.getNroCuotasTramo2();
        }
        double montoCuotaFMutual = montoCuotaTramo1 - montoCuotaTramo2;
        double montoCuotaInicialFMutual = reincorporacion.getMontoFmutual() - montoCuotaFMutual * reincorporacion.getNroCuotasTramo1();

        modelDetalle.addRow(new Object[8]);
        tDetalleReincorporacion.setValueAt("PAGO INICIAL", 8, 0);
        tDetalleReincorporacion.setValueAt("-" + String.valueOf(reincorporacion.getMontoPagoIni() - montoCuotaInicialFMutual), 8, 1);
        tDetalleReincorporacion.setValueAt("-" + String.valueOf(montoCuotaInicialFMutual), 8, 2);
        tDetalleReincorporacion.setValueAt("", 8, 3);
        tDetalleReincorporacion.setValueAt("-" + String.valueOf(reincorporacion.getMontoPagoIni()), 8, 4);

        modelDetalle.addRow(new Object[9]);
        tDetalleReincorporacion.setValueAt("FRACCIONAMIENTO", 9, 0);
        tDetalleReincorporacion.setValueAt((reincorporacion.getMontoPagarTramo1() + reincorporacion.getMontoPagarTramo2()) - montoCuotaFMutual * 3, 9, 1);
        tDetalleReincorporacion.setValueAt(montoCuotaFMutual * 3, 9, 2);
        tDetalleReincorporacion.setValueAt("", 9, 3);
        tDetalleReincorporacion.setValueAt(reincorporacion.getMontoPagarTramo1() + reincorporacion.getMontoPagarTramo2(), 9, 4);

        if (reincorporacion.getNroCuotasTramo1() > 0) {
            lbTramo1.setText("TRAMO 1 = " + reincorporacion.getNroCuotasTramo1() + " CUOTAS DE " + montoCuotaTramo1 + " = " + reincorporacion.getNroCuotasTramo1() * montoCuotaTramo1);
        } else {
            lbTramo1.setText("TRAMO 1 = " + reincorporacion.getNroCuotasTramo1() + " CUOTAS DE 0.0 = 0.0");
        }
        lbTramo2.setText("TRAMO 2 = " + reincorporacion.getNroCuotasTramo2() + " CUOTAS DE " + montoCuotaTramo2 + " = " + reincorporacion.getNroCuotasTramo2() * montoCuotaTramo2);

        List<ReincorporacionDocumentoPago> lReincorporacionDetalle = rBO.ObtenerDetalleReincorporacion(reincorporacion.getId());
        DefaultTableModel modelDetalleReincorporacion = (DefaultTableModel) tDetalle.getModel();
        modelDetalleReincorporacion.setNumRows(0);
        int contador = 0;
        CuotasBO cBO = CuotasBO.getInstance();
        int anio = 0;
        int mes = 0;
        double montoCuota = 0.0;
        double montoFondoTotal = 0.0;
        SimpleDateFormat fAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat fMes = new SimpleDateFormat("MM");
        for (ReincorporacionDocumentoPago rdp : lReincorporacionDetalle) {
            modelDetalleReincorporacion.addRow(new Object[0]);
            tDetalle.setValueAt(contador + 1, contador, 0);
            tDetalle.setValueAt(rdp.getFechaVencimiento(), contador, 1);
            montoCuota = cBO.ObtenerMontoCuotaAnioMes(Integer.valueOf(fAnio.format(rdp.getFechaVencimiento())), Integer.valueOf(fMes.format(rdp.getFechaVencimiento())));
            if (contador < reincorporacion.getNroCuotasTramo1()) {
                tDetalle.setValueAt(rdp.getMontoFondo(), contador, 2);
                montoFondoTotal = rdp.getMontoFondo();
            } else {
                tDetalle.setValueAt("0.0", contador, 2);
                montoFondoTotal = 0.0;
            }
            tDetalle.setValueAt("+", contador, 3);
            tDetalle.setValueAt(rdp.getMontoOtros(), contador, 4);
            tDetalle.setValueAt("=", contador, 5);
            tDetalle.setValueAt(montoFondoTotal + rdp.getMontoOtros(), contador, 6);
            tDetalle.setValueAt("+", contador, 7);
            tDetalle.setValueAt(montoCuota, contador, 8);
            tDetalle.setValueAt("=", contador, 9);
            tDetalle.setValueAt(montoFondoTotal + rdp.getMontoOtros() + montoCuota, contador, 10);
            if (rdp.getDocumentoPagoDet() != null) {
                tDetalle.setValueAt(rdp.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getTipoDocPago().getNombreDocPago() + " SERIE:" + rdp.getDocumentoPagoDet().getDocumentoPago().getNroSerie() + " NRO:" + rdp.getDocumentoPagoDet().getDocumentoPago().getNroDocumentoPago(), contador, 11);
            }
            tDetalle.setValueAt(rdp.getEstado(), contador, 12);
            contador = contador + 1;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tDetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lbNombreColegiado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbDesde = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbHasta = new javax.swing.JLabel();
        lbCodColegiado = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tDetalleReincorporacion = new javax.swing.JTable();
        lbTramo1 = new javax.swing.JLabel();
        lbTramo2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REINCORPORACIÓN DETALLE");

        tDetalle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO", "CRONOGRAMA", "FONDO", "", "OTROS", " ", "LETRA", "  ", "CUOTA", "   ", "TOTAL", "DOC PAGO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tDetalle);

        jLabel1.setText("CODIGO:");

        lbNombreColegiado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNombreColegiado.setText("ROLANDO ANGEL CACERES LAURA");

        jLabel3.setText("DESDE:");

        lbDesde.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbDesde.setText("ENERO 2001");

        jLabel5.setText("HASTA:");

        lbHasta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbHasta.setText("SEPTIEMBRE 2012");

        lbCodColegiado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbCodColegiado.setText("01077");

        jLabel8.setText("NOMBRE:");

        tDetalleReincorporacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tDetalleReincorporacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN", "IMPORTE", "SIN DESC.", "DESC.", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tDetalleReincorporacion);

        lbTramo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTramo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTramo1.setText("TRAMO 1 = 3 CUOTAS DE 55.00 = 165.00");

        lbTramo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTramo2.setText("TRAMO 2 = 36 CUOTASDE 21.00 = 756.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDesde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbHasta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCodColegiado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNombreColegiado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTramo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTramo2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbNombreColegiado)
                    .addComponent(lbCodColegiado)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDesde)
                    .addComponent(jLabel5)
                    .addComponent(lbHasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTramo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTramo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dReincorporacionDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dReincorporacionDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dReincorporacionDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dReincorporacionDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dReincorporacionDetalle dialog = new dReincorporacionDetalle(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCodColegiado;
    private javax.swing.JLabel lbDesde;
    private javax.swing.JLabel lbHasta;
    private javax.swing.JLabel lbNombreColegiado;
    private javax.swing.JLabel lbTramo1;
    private javax.swing.JLabel lbTramo2;
    private javax.swing.JTable tDetalle;
    private javax.swing.JTable tDetalleReincorporacion;
    // End of variables declaration//GEN-END:variables
}
