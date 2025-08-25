/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.caja;

//import capa_negocio.bo.ComprobanteBO;
//import capa_negocio.bo.DatePicker;
//import capa_negocio.bo.NotaBO;
//import capa_negocio.bo.NumeroLetras;
//import capa_negocio.bo.SeguridadBO;
//import capa_negocio.bo.TipoNotaBO;
//import capa_negocio.dao.ComprobanteDAO;
//import capa_negocio.mapeo.ClienteProveedor;
//import capa_negocio.mapeo.ComprobantePago;
//import capa_negocio.mapeo.ComprobantePagoDetalle;;
//import capa_negocio.mapeo.MotivoNota;
//import capa_negocio.mapeo.Nota;
//import capa_negocio.mapeo.NotaDetalle;
//import capa_negocio.mapeo.TipoDocumento;
import caja.bo.DocumentoPagoBO;
import caja.bo.NotaBO;
import caja.bo.NumeroLetras;
import caja.bo.SeguridadBO;
import caja.bo.TipoNotaBO;
import caja.frm.frmPrincipal;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Nota;
import caja.mapeo.entidades.NotaDetalle;
import caja.mapeo.entidades.TipoNota;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import units.DatePicker;

/**
 *
 * @author user
 */
public class frmNotaDetalle extends javax.swing.JInternalFrame {

    private Nota notaCredito = null;
    private boolean accesoGuardar = false;

    /**
     * Creates new form frmNota
     */
    public frmNotaDetalle() {
        initComponents();
        Date fechaActual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtFechaEmision.setText(format.format(fechaActual));
        TipoNotaBO tnBO = TipoNotaBO.getInstance();
        List<TipoNota> listado = tnBO.BuscarTodosTipoNotaCredito();
        for (TipoNota tn : listado) {
            cbMotivoNota.addItem(tn);
        }

        TableColumn columna = tDetalle.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tDetalle.getColumn("NOTADETALLEOBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("COMPROBANTEDETALLEOBJ");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);;
        columna = tDetalle.getColumn("CODIGO");
        columna.setPreferredWidth(55);
        columna.setMinWidth(55);
        columna.setMaxWidth(55);
//        columna = tDetalle.getColumn("ARTICULO");
//        columna.setPreferredWidth(300);
//        columna.setMinWidth(300);
//        columna.setMaxWidth(300);
        columna = tDetalle.getColumn("UNIDAD");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("CANT");
        columna.setPreferredWidth(50);
        columna.setMinWidth(50);
        columna.setMaxWidth(50);
        columna = tDetalle.getColumn("V. VENTA");
        columna.setPreferredWidth(50);
        columna.setMinWidth(50);
        columna.setMaxWidth(50);
        columna = tDetalle.getColumn("IGV");
        columna.setPreferredWidth(50);
        columna.setMinWidth(50);
        columna.setMaxWidth(50);
        columna = tDetalle.getColumn("TOTAL");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tDetalle.getColumn("P. VENTA");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("DETRACCION");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("T. AFEC.");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tDetalle = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        btnFecha = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtComprobante = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtDirección = new javax.swing.JTextField();
        btnSeleccionarComprobante = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbMotivoNota = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbSerie = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtNroNota = new javax.swing.JTextField();
        lbMontoTotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtMoneda = new javax.swing.JTextField();
        lbTipoMoneda = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTipoOperacion = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnMail = new javax.swing.JButton();
        btnEliminar1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cbTipoNota = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        lbMontoDetraccion = new javax.swing.JLabel();
        lbTipoMoneda1 = new javax.swing.JLabel();
        btnMail1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setClosable(true);
        setTitle("NOTA CREDITO ELÉCTRONICO");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 600));

        tDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOTADETALLEOBJ", "NRO", "CODIGO", "ARTICULO", "UNIDAD", "CANT", "V. VENTA", "IGV", "TOTAL", "P. VENTA", "DETRACCION", "T. AFEC.", "COMPROBANTEDETALLEOBJ"
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
        tDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tDetalleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tDetalle);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/ok_mediano.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/IMPRESORA.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("FECHA:");

        txtFechaEmision.setEditable(false);
        txtFechaEmision.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFechaEmision.setForeground(new java.awt.Color(0, 102, 204));

        btnFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/calendario.png"))); // NOI18N
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("COMPROBANTE:");

        txtComprobante.setEditable(false);
        txtComprobante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtComprobante.setForeground(new java.awt.Color(0, 102, 204));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("CLIENTE:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("DIRECCIÓN:");

        txtCliente.setEditable(false);
        txtCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCliente.setForeground(new java.awt.Color(0, 102, 204));

        txtDirección.setEditable(false);
        txtDirección.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDirección.setForeground(new java.awt.Color(0, 102, 204));

        btnSeleccionarComprobante.setText("...");
        btnSeleccionarComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarComprobanteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("MOTIVO NOTA:");

        cbMotivoNota.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbMotivoNota.setForeground(new java.awt.Color(0, 102, 204));
        cbMotivoNota.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("MOTIVO:");

        txtMotivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMotivo.setForeground(new java.awt.Color(0, 102, 204));
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMotivoKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("SERIE:");

        cbSerie.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbSerie.setForeground(new java.awt.Color(0, 102, 204));
        cbSerie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F001", "B001", "9999", "0001" }));
        cbSerie.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("NRO:");

        txtNroNota.setEditable(false);
        txtNroNota.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNroNota.setForeground(new java.awt.Color(0, 102, 204));
        txtNroNota.setText("000000");
        txtNroNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroNotaKeyTyped(evt);
            }
        });

        lbMontoTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbMontoTotal.setForeground(new java.awt.Color(0, 102, 204));
        lbMontoTotal.setText("0.00");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("TOTAL:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MONEDA:");

        txtMoneda.setEditable(false);
        txtMoneda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMoneda.setForeground(new java.awt.Color(0, 102, 204));

        lbTipoMoneda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTipoMoneda.setText("SOLES");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("TIPO OPERACIÓN:");

        txtTipoOperacion.setEditable(false);
        txtTipoOperacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTipoOperacion.setForeground(new java.awt.Color(0, 102, 204));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/eliminar1.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/mail03.png"))); // NOI18N
        btnMail.setToolTipText("ENVIAR E-MAIL");
        btnMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMailActionPerformed(evt);
            }
        });

        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/cambiar.png"))); // NOI18N
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("TIPO DE NOTA:");

        cbTipoNota.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbTipoNota.setForeground(new java.awt.Color(0, 102, 204));
        cbTipoNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOTA CRÉDITO ELECTRÓNICA", "NOTA DÉBITO ELECTRÓNICA" }));
        cbTipoNota.setEnabled(false);
        cbTipoNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoNotaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("DETRACCIÓN:");

        lbMontoDetraccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbMontoDetraccion.setForeground(new java.awt.Color(0, 102, 204));
        lbMontoDetraccion.setText("0.00");

        lbTipoMoneda1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTipoMoneda1.setText("SOLES");

        btnMail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/generar.png"))); // NOI18N
        btnMail1.setToolTipText("GENERAR ARCHIVO ELECTRONICO");
        btnMail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMail1ActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbMontoTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTipoMoneda))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbMontoDetraccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTipoMoneda1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMail1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMail, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDirección)
                            .addComponent(txtCliente)
                            .addComponent(txtMotivo)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbTipoNota, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFecha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtComprobante))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSeleccionarComprobante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbMotivoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtNroNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbTipoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbMotivoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtNroNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarComprobante)
                    .addComponent(jLabel1)
                    .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDirección, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(lbMontoTotal))
                            .addComponent(lbTipoMoneda))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(lbMontoDetraccion))
                            .addComponent(lbTipoMoneda1)))
                    .addComponent(btnMail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        DatePicker dtp = new DatePicker(this);
        if (!dtp.setPickedDate().isEmpty()) {
            txtFechaEmision.setText(dtp.setPickedDate());
        }
    }//GEN-LAST:event_btnFechaActionPerformed

    private void btnSeleccionarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarComprobanteActionPerformed
        frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        f.abrirFormularioSeleccionarComprobante(this, 1);
    }//GEN-LAST:event_btnSeleccionarComprobanteActionPerformed

    private void txtMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyReleased
        int p = txtMotivo.getCaretPosition();
        txtMotivo.setText(txtMotivo.getText().toUpperCase());
        txtMotivo.setCaretPosition(p);
    }//GEN-LAST:event_txtMotivoKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtMotivo.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "DEBE INGRESAR UN MOTIVO", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.notaCredito.getDocumentoPago() == null) {
            JOptionPane.showMessageDialog(this,
                    "DEBE ELEGIR UN COMPROBANTE DE PAGO",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        NotaBO ncBO = NotaBO.getInstance();
        boolean seElimino = ncBO.eliminarCuotaFinanciamientoReincorporacion(this.notaCredito.getDocumentoPago());
        if (!seElimino) {
            JOptionPane.showMessageDialog(this,
                    "NO SE PUDO ELIMINAR LA CUOTA/FINANCIMIENTO/REINCORPORACION",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fecha = sBO.ObtenerFechaHoraServidor();
        this.notaCredito.setFecha(fecha);
        this.notaCredito.setMotivo(txtMotivo.getText());
        this.notaCredito.setTipoNota((TipoNota) cbMotivoNota.getSelectedItem());
        if (cbTipoNota.getSelectedItem().toString().equals("NOTA CRÉDITO ELECTRÓNICA")) {
            this.notaCredito.setTipo(1);
        } else {
            this.notaCredito.setTipo(-1);
        }
        if (this.notaCredito.getId() == 0) {
            this.notaCredito.setEstado("C");
            this.notaCredito.setNroSerie(cbSerie.getSelectedItem().toString());
            boolean guardo = ncBO.GuardarNotaElectronica(this.notaCredito);
            if (!guardo) {
                JOptionPane.showMessageDialog(this,
                        "NO SE PUDO GUARDAR LOS DATOS",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this,
                        "SE GUARDÓ LOS DATOS CORRECTAMENTE",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            boolean guardo = ncBO.ActualizarNota(this.notaCredito);
            if (!guardo) {
                JOptionPane.showMessageDialog(this,
                        "NO SE PUDO GUARDAR LOS DATOS",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this,
                        "SE GUARDÓ LOS DATOS CORRECTAMENTE",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        DefaultTableModel modelDetalle = (DefaultTableModel) tDetalle.getModel();
        if (modelDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "LA NOTA DE CRÉDITO DEBE CONTENER DETALLE",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numFilas = modelDetalle.getRowCount();
        for (int i = 0; i < numFilas; i++) {
            NotaDetalle ncd = (NotaDetalle) modelDetalle.getValueAt(i, 0);
            int cantidad = (int) modelDetalle.getValueAt(i, 5);
            double vVenta = (double) modelDetalle.getValueAt(i, 6);
            double igv = (double) modelDetalle.getValueAt(i, 7);
            ncd.setNota(this.notaCredito);
            ncd.setCantidad(cantidad);
            ncd.setValorVenta(vVenta);
            ncd.setIgv(igv);
            if (ncd.getId() == 0) {
                boolean seGuardo = ncBO.GuardarNotaDetalle(ncd);
                if (!seGuardo) {
                    JOptionPane.showMessageDialog(this,
                            "NO SE GUARDO EL DETALLE",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                ncBO.ActualizarNotaDetalle(ncd);
            }
        }
        if (this.notaCredito.getTipoNota().getTipoNota() == 1) {
            if (!this.notaCredito.getTipoNota().getCodigo().equals("05")) {
                if (!this.notaCredito.getTipoNota().getCodigo().equals("07")) {
                    this.borrarParticipantesEvento(this.notaCredito.getId());
                    this.borrarConstancias(this.notaCredito.getId());
                }
            }
        }
        this.GenerarArchivoElectronicoSUNAT_TXT();
        this.CargarDatos(this.notaCredito);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void borrarParticipantesEvento(int idNota) {
        NotaBO ncBO = NotaBO.getInstance();
        List<Object> l = ncBO.ObtenerIdParticipantes_SegunNotaCredito(idNota);
        for (Object obj : l) {
            int idParticipante = (int) obj;
            ncBO.actualizarBorradoPartipantes(idParticipante, "0");
        }
    }

    private void borrarConstancias(int idNota) {
        NotaBO ncBO = NotaBO.getInstance();
        List<Object> l = ncBO.ObtenerIdConstancia_SegunNotaCredito(idNota);
        for (Object obj : l) {
            int id = (int) obj;
            ncBO.actualizarBorradoConstancias(id, "0");
        }
    }

    private void habilitarParticipantesEvento(List<Object> l) {
        NotaBO ncBO = NotaBO.getInstance();
        for (Object obj : l) {
            int idParticipante = (int) obj;
            ncBO.actualizarBorradoPartipantes(idParticipante, "1");
        }
    }

    private void generacionArchivoFormaPago(FileWriter w1001, BufferedWriter bw1001, PrintWriter wr1001, File f1001, String nombreArchivo, String formaPago, Double totalPrecioVenta) {
        try {
            f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".PAG");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            String cadena = "";
            cadena = cadena + formaPago + "|" + (formaPago.equals("CREDITO") ? String.valueOf(totalPrecioVenta) : "0.0") + "|PEN";
            wr1001.write(cadena + "\r\n");
            wr1001.close();
            bw1001.close();
        } catch (IOException ex) {
            Logger.getLogger(DocumentoPagoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generacionArchivoDetalleFormaPagoCredito(FileWriter w1001, BufferedWriter bw1001, PrintWriter wr1001, File f1001, String nombreArchivo, String formaPago, Date fechaVencimiento, Double totalPrecioVenta) {
        try {
            if (formaPago.equals("CREDITO")) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".DPA");
                w1001 = new FileWriter(f1001);
                bw1001 = new BufferedWriter(w1001);
                wr1001 = new PrintWriter(bw1001);
                String cadena = "";
                cadena = cadena + String.valueOf(totalPrecioVenta) + "|" + f.format(fechaVencimiento) + "|PEN";
                wr1001.write(cadena + "\r\n");
                wr1001.close();
                bw1001.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(DocumentoPagoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarArchivoACA(String nombreArchivo, DocumentoPago doc, List<NotaDetalle> lDetalle) {
        try {
            if (!Objects.isNull(doc.getTieneDetraccion())) {
                if (doc.getTieneDetraccion().equals("S")) {
//                    Double montoComprobante = 0.0;
                    SeguridadBO sBO = SeguridadBO.getINSTANCE();
                    File f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".ACA");
                    FileWriter w1001 = new FileWriter(f1001);
                    BufferedWriter bw1001 = new BufferedWriter(w1001);
                    PrintWriter wr1001 = new PrintWriter(bw1001);
                    String detraccion = "";
                    String nroCuenta = sBO.getNRO_CUENTA_DETRACCION();
                    Double montoDetraccion = 0.0;
                    for (NotaDetalle dpd : lDetalle) {
                        montoDetraccion = montoDetraccion + (!Objects.isNull(dpd.getMontoDetraccion()) ? dpd.getMontoDetraccion() : 0.0);
                    }
                    if (lDetalle.size() > 0) {
                        NotaDetalle dpd = (NotaDetalle) lDetalle.get(0);
                        detraccion = detraccion + nroCuenta + "|";
                        detraccion = detraccion + dpd.getDocumentoPagoDet().getCodigoBienServicioDetraccion() + "|";
                        detraccion = detraccion + dpd.getDocumentoPagoDet().getPorcentajeDetraccion() + "|";
                        detraccion = detraccion + montoDetraccion + "|";
                        detraccion = detraccion + doc.getCodigoMedioPago() + "|";
                        detraccion = detraccion + "PE" + "|";
                        detraccion = detraccion + doc.getCodigoUbigeo() + "|";
                        if (doc.getCliente().getTipoCliente().equals("C")) {
                            detraccion = detraccion + doc.getCliente().getPdireccionDomicilio() + "|";
                        } else {
                            if (doc.getCliente().getTipoCliente().equals("S")) {
                                detraccion = detraccion + doc.getCliente().getSdireccion() + "|";
                            } else {
                                if (doc.getCliente().getTipoCliente().equals("E")) {
                                    detraccion = detraccion + doc.getCliente().getEdireccion() + "|";
                                } else {
                                    if (doc.getCliente().getTipoCliente().equals("P")) {
                                    } else {
                                        detraccion = detraccion + doc.getCliente().getPdireccionDomicilio() + "|";
                                    }
                                }
                            }
                        }
                        detraccion = detraccion + "-|-|-";
                        wr1001.write(detraccion + "\r\n");
                    }
                    wr1001.close();
                    bw1001.close();
                }
            }
        } catch (IOException e) {
        }
    }

    private void GenerarArchivoElectronicoSUNAT_TXT() {
        NotaBO ncBO = NotaBO.getInstance();
        FileWriter w1001 = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat formatoJson = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat fAnio = new SimpleDateFormat("yyyy");
            SimpleDateFormat fMes = new SimpleDateFormat("MM");
            SimpleDateFormat fDia = new SimpleDateFormat("dd");
            BufferedWriter bw1001 = null;
            PrintWriter wr1001 = null;
            File f1001;
            String tipoNota = "";
            if (this.notaCredito.getTipo() == 1) {
                tipoNota = "07";
            } else {
                tipoNota = "08";
            }
            String nombreArchivo = "20174327026-" + tipoNota + "-" + this.notaCredito.getNroSerie() + "-" + String.format("%08d", this.notaCredito.getNro());
//            FileWriter archivo = new FileWriter("\\\\192.168.1.67\\DATA\\" + nombreArchivo);
//------------------------------------------------------------------
            f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".NOT");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            String cabecera = "";

            String tipoOperacion = "0101";
            if (!Objects.isNull(this.notaCredito.getDocumentoPago().getTieneDetraccion())) {
                if (this.notaCredito.getDocumentoPago().getTieneDetraccion().equals("S")) {
                    tipoOperacion = "1001";
                }
            }

            cabecera = cabecera + tipoOperacion + "|";
            cabecera = cabecera + formato.format(this.notaCredito.getFecha()) + "|";
            cabecera = cabecera + formatoHora.format(this.notaCredito.getFecha()) + "|";
            cabecera = cabecera + "0000" + "|";
            String rzSocial = "";
            if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
                cabecera = cabecera + "1" + "|";
                cabecera = cabecera + this.notaCredito.getDocumentoPago().getCliente().getPnroDocumento() + "|";
                rzSocial = this.notaCredito.getDocumentoPago().getCliente().getPapePat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPapeMat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPnombre();
                rzSocial = rzSocial.replace("&", "&amp;");
                cabecera = cabecera + rzSocial + "|";
            } else {
                if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("S")) {
                    cabecera = cabecera + "6" + "|";
                    cabecera = cabecera + this.notaCredito.getDocumentoPago().getCliente().getSruc() + "|";
                    rzSocial = this.notaCredito.getDocumentoPago().getCliente().getSnombreSociedad();
                    rzSocial = rzSocial.replace("&", "&amp;");
                    cabecera = cabecera + rzSocial + "|";
                } else {
                    if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("E")) {
                        cabecera = cabecera + "6" + "|";
                        cabecera = cabecera + this.notaCredito.getDocumentoPago().getCliente().getEruc() + "|";
                        rzSocial = this.notaCredito.getDocumentoPago().getCliente().getEnombre();
                        rzSocial = rzSocial.replace("&", "&amp;");
                        cabecera = cabecera + rzSocial + "|";
                    } else {
                        if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("P")) {
                            cabecera = cabecera + "1" + "|";
                            cabecera = cabecera + this.notaCredito.getDocumentoPago().getCliente().getPnroDocumento() + "|";
                            rzSocial = this.notaCredito.getDocumentoPago().getCliente().getPapePat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPapeMat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPnombre();
                            rzSocial = rzSocial.replace("&", "&amp;");
                            cabecera = cabecera + rzSocial + "|";
                        }
                    }
                }
            }
            if (this.notaCredito.getDocumentoPago().getMoneda().equals("S")) {
                cabecera = cabecera + "PEN" + "|"; //CATALOGO 02
            } else {
                cabecera = cabecera + "USD" + "|"; //CATALOGO 02
            }
            cabecera = cabecera + this.notaCredito.getTipoNota().getCodigo() + "|";
            cabecera = cabecera + this.notaCredito.getMotivo() + "|";
            cabecera = cabecera + this.notaCredito.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "|";
            if (this.notaCredito.getDocumentoPago().getElectronico() != null) {
                if (this.notaCredito.getDocumentoPago().getElectronico().equals("S")) {
                    cabecera = cabecera + this.notaCredito.getDocumentoPago().getNroSerie() + "-" + String.format("%08d", this.notaCredito.getDocumentoPago().getNroDocumentoPago()) + "|";
                } else {
                    cabecera = cabecera + this.notaCredito.getDocumentoPago().getNroSerie() + "-" + String.format("%08d", this.notaCredito.getDocumentoPago().getNroDocumentoPago()) + "|";
                }
            } else {
                cabecera = cabecera + this.notaCredito.getDocumentoPago().getNroSerie() + "-" + String.format("%08d", this.notaCredito.getDocumentoPago().getNroDocumentoPago()) + "|";
            }
            double igvTotal = ncBO.ObtenerSumaTotalIGV(this.notaCredito.getId());
            double vvTotal = ncBO.ObtenerSumaTotalValorVenta(this.notaCredito.getId());
            double pvTotal = vvTotal + igvTotal;
            pvTotal = Math.round(pvTotal * Math.pow(10, 2)) / Math.pow(10, 2);
            cabecera = cabecera + String.valueOf(igvTotal) + "|";
            cabecera = cabecera + String.valueOf(vvTotal) + "|";
            cabecera = cabecera + String.valueOf(pvTotal) + "|";
            cabecera = cabecera + "0.0" + "|";
            cabecera = cabecera + "0.0" + "|";
            cabecera = cabecera + "0.0" + "|";
            cabecera = cabecera + String.valueOf(pvTotal) + "|";
            cabecera = cabecera + "2.1" + "|";
            cabecera = cabecera + "2.0" + "|";
            wr1001.write(cabecera + "\r\n");
            wr1001.close();
            bw1001.close();
//---------------------------------------------DETALLE----------------------------------------
            List<NotaDetalle> lDetalle = ncBO.ObtenerDetalleNota(this.notaCredito.getId());
            f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".DET");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            for (NotaDetalle nd : lDetalle) {
                String detalle = "";
                detalle = detalle + "NIU" + "|";
                detalle = detalle + String.valueOf(nd.getCantidad()) + "|";
                detalle = detalle + nd.getDocumentoPagoDet().getConceptoPagoDetalle().getTipoCodigo() + nd.getDocumentoPagoDet().getConceptoPagoDetalle().getCodigo() + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + nd.getDocumentoPagoDet().getConceptoPagoDetalle().getDescripcion() + "|";
                double valorUnitario = nd.getValorVenta() / nd.getCantidad();
                valorUnitario = Math.round(valorUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
                detalle = detalle + String.valueOf(valorUnitario) + "|";
                detalle = detalle + String.valueOf(nd.getIgv()) + "|";
                detalle = detalle + nd.getCodigoTipoTributo() + "|";
                detalle = detalle + String.valueOf(nd.getIgv()) + "|";
                detalle = detalle + String.valueOf(nd.getValorVenta()) + "|";
                detalle = detalle + nd.getNombreTipoTributo() + "|";
                detalle = detalle + nd.getCodigoInternacionalTipoTributo() + "|";
                detalle = detalle + nd.getCodigoTipoAfectacion() + "|";
                detalle = detalle + String.valueOf(nd.getIgvPorcentaje()) + "|";
                //---------------------------------ISC------------------------------
                detalle = detalle + "" + "|";
                detalle = detalle + "0.0" + "|";
                detalle = detalle + "0.0" + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + "0" + "|";
                //--------------------------OTROS TRIBUTOS--------------------------
                detalle = detalle + "" + "|";
                detalle = detalle + "0.0" + "|";
                detalle = detalle + "0.0" + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + "0" + "|";
                //--------------------------TRIBUTO ICBPER 7152--------------------------
                detalle = detalle + "" + "|";
                detalle = detalle + "0.0" + "|";
                detalle = detalle + "0" + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + "" + "|";
                detalle = detalle + "0.0" + "|";
                //------------------------------------------------------------------
                double precioUnitario = (nd.getValorVenta() + nd.getIgv()) / nd.getCantidad();
                precioUnitario = Math.round(precioUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
                detalle = detalle + String.valueOf(precioUnitario) + "|";
                detalle = detalle + String.valueOf(nd.getValorVenta()) + "|";
                detalle = detalle + "0.0" + "|";
                wr1001.write(detalle + "\r\n");
            }
            wr1001.close();
            bw1001.close();
//----------------------------------TRIBUTOS GENERALES--------------------------
            List<Object> lTributos = ncBO.ObtenerTributosGenerales(this.notaCredito.getId());
            f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".TRI");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            for (Object nd : lTributos) {
                Object[] obj = (Object[]) nd;
                String tributos = "";
                tributos = tributos + obj[0] + "|";
                tributos = tributos + obj[1] + "|";
                tributos = tributos + obj[2] + "|";
                Double valorVenta = (double) obj[3];
                valorVenta = Math.round(valorVenta * Math.pow(10, 2)) / Math.pow(10, 2);
                Double igv = (double) obj[4];
                igv = Math.round(igv * Math.pow(10, 2)) / Math.pow(10, 2);
                tributos = tributos + String.valueOf(valorVenta) + "|";
                tributos = tributos + String.valueOf(igv) + "|";
                wr1001.write(tributos + "\r\n");
            }
            wr1001.close();
            bw1001.close();
//---------------------------------LEYENDA------------------------------.                
            f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".LEY");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            String leyenda = "";
            leyenda = leyenda + "1000" + "|";
            leyenda = leyenda + NumeroLetras.convertirNumerosALetras(lbMontoTotal.getText(), this.notaCredito.getMoneda()).replace("/", "'/'") + "|";
            wr1001.write(leyenda + "\r\n");
            wr1001.close();
            bw1001.close();
            if (tipoNota.equals("07")) {
                if (this.notaCredito.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getCodigoDocPago() != null) {
                    if (this.notaCredito.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
                        if (this.notaCredito.getTipoNota().getCodigo().equals("13")) {
                            this.generacionArchivoFormaPago(w1001, bw1001, wr1001, f1001, nombreArchivo, this.notaCredito.getDocumentoPago().getFormaPagoSunat(), pvTotal);
                            this.generacionArchivoDetalleFormaPagoCredito(w1001, bw1001, wr1001, f1001, nombreArchivo, this.notaCredito.getDocumentoPago().getFormaPagoSunat(), this.notaCredito.getDocumentoPago().getFechaVencimientoSunat(), pvTotal);
                        }
                    }
                }
            }
            this.generarArchivoACA(nombreArchivo, this.notaCredito.getDocumentoPago(), lDetalle);
        } catch (IOException ex) {
            Logger.getLogger(frmNotaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            //manejar error
        }
    }

    private void GenerarArchivoElectronicoSUNAT() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        JSONObject objPrincipal = new JSONObject();
        NotaBO ncBO = NotaBO.getInstance();
        JSONObject objCabecera = new JSONObject();
        String rzSocial = "";
        objCabecera.put("tipOperacion", "0101");
        objCabecera.put("fecEmision", formato.format(this.notaCredito.getFecha()));
        objCabecera.put("horEmision", formatoHora.format(this.notaCredito.getFecha()));
        objCabecera.put("codLocalEmisor", "000");
        if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
            objCabecera.put("tipDocUsuario", "1"); //CATALOGO 06
            objCabecera.put("numDocUsuario", this.notaCredito.getDocumentoPago().getCliente().getPnroDocumento());
            rzSocial = this.notaCredito.getDocumentoPago().getCliente().getPapePat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPapeMat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPnombre();
            rzSocial = rzSocial.replace("&", "&amp;");
            objCabecera.put("rznSocialUsuario", rzSocial);
        } else {
            if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("S")) {
                objCabecera.put("tipDocUsuario", "6"); //CATALOGO 06
                objCabecera.put("numDocUsuario", this.notaCredito.getDocumentoPago().getCliente().getSruc());
                rzSocial = this.notaCredito.getDocumentoPago().getCliente().getSnombreSociedad();
                rzSocial = rzSocial.replace("&", "&amp;");
                objCabecera.put("rznSocialUsuario", rzSocial);
            } else {
                if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("E")) {
                    objCabecera.put("tipDocUsuario", "6"); //CATALOGO 06
                    objCabecera.put("numDocUsuario", this.notaCredito.getDocumentoPago().getCliente().getEruc());
                    rzSocial = this.notaCredito.getDocumentoPago().getCliente().getEnombre();
                    rzSocial = rzSocial.replace("&", "&amp;");
                    objCabecera.put("rznSocialUsuario", rzSocial);
                } else {
                    if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("P")) {
                        objCabecera.put("tipDocUsuario", "1"); //CATALOGO 06
                        objCabecera.put("numDocUsuario", this.notaCredito.getDocumentoPago().getCliente().getPnroDocumento());
                        rzSocial = this.notaCredito.getDocumentoPago().getCliente().getPapePat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPapeMat() + " " + this.notaCredito.getDocumentoPago().getCliente().getPnombre();
                        rzSocial = rzSocial.replace("&", "&amp;");
                        objCabecera.put("rznSocialUsuario", rzSocial);
                    }
                }
            }
        }
        if (this.notaCredito.getDocumentoPago().getMoneda().equals("S")) {
            objCabecera.put("tipMoneda", "PEN"); //CATALOGO 02
        } else {
            objCabecera.put("tipMoneda", "USD"); //CATALOGO 02
        }
        objCabecera.put("codMotivo", this.notaCredito.getTipoNota().getCodigo());
        objCabecera.put("desMotivo", this.notaCredito.getMotivo());
        objCabecera.put("tipDocAfectado", this.notaCredito.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getCodigoDocPago());
        if (this.notaCredito.getDocumentoPago().getElectronico() != null) {
            if (this.notaCredito.getDocumentoPago().getElectronico().equals("S")) {
                objCabecera.put("numDocAfectado", this.notaCredito.getDocumentoPago().getNroSerie() + "-" + String.format("%08d", this.notaCredito.getDocumentoPago().getNroDocumentoPago()));
            } else {
                objCabecera.put("numDocAfectado", this.notaCredito.getDocumentoPago().getNroSerie() + "-" + String.format("%08d", this.notaCredito.getDocumentoPago().getNroDocumentoPago()));
            }
        } else {
            objCabecera.put("numDocAfectado", this.notaCredito.getDocumentoPago().getNroSerie() + "-" + String.format("%08d", this.notaCredito.getDocumentoPago().getNroDocumentoPago()));
        }
        double igvTotal = ncBO.ObtenerSumaTotalIGV(this.notaCredito.getId());
        double vvTotal = ncBO.ObtenerSumaTotalValorVenta(this.notaCredito.getId());
        double pvTotal = vvTotal + igvTotal;
        pvTotal = Math.round(pvTotal * Math.pow(10, 2)) / Math.pow(10, 2);
        objCabecera.put("sumTotTributos", String.valueOf(igvTotal));//TRIBUTO - IGV
        objCabecera.put("sumTotValVenta", String.valueOf(vvTotal));//?
        objCabecera.put("sumPrecioVenta", String.valueOf(pvTotal));//?
        objCabecera.put("sumDescTotal", "0.0");//?
        objCabecera.put("sumOtrosCargos", "0.0");//?
        objCabecera.put("sumTotalAnticipos", "0.0");//?
        objCabecera.put("sumImpVenta", String.valueOf(pvTotal));//?
        objCabecera.put("ublVersionId", "2,1");
        objCabecera.put("customizationId", "2.0");
        objPrincipal.put("cabecera", objCabecera);
        //--------------------------------FIN ADICIONALES DE CABECERA-------------------------
        //---------------------------------------------DETALLE----------------------------------------
        //---------------------------------------------DETALLE----------------------------------------
//        ComprobanteBO cpBO = ComprobanteBO.getInstance();
        List<NotaDetalle> lDetalle = ncBO.ObtenerDetalleNota(this.notaCredito.getId());
        JSONArray objDetalle = new JSONArray();
//        SeguridadBO sBO = SeguridadBO.getInstance();
        for (NotaDetalle nd : lDetalle) {
            JSONObject det = new JSONObject();
            det.put("codUnidadMedida", "NIU");
            det.put("ctdUnidadItem", String.valueOf(nd.getCantidad()));
            det.put("codProducto", nd.getDocumentoPagoDet().getConceptoPagoDetalle().getTipoCodigo() + nd.getDocumentoPagoDet().getConceptoPagoDetalle().getCodigo());
            det.put("codProductoSUNAT", ""); //ENLAZAR CON LAS TABLAS DE LA SUNAT - ESTA CON "C" - OPCIONAL ENVIAR
            det.put("desItem", nd.getDocumentoPagoDet().getConceptoPagoDetalle().getDescripcion());
            double valorUnitario = nd.getValorVenta() / nd.getCantidad();
            valorUnitario = Math.round(valorUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
            det.put("mtoValorUnitario", String.valueOf(valorUnitario));
            det.put("sumTotTributosItem", String.valueOf(nd.getIgv())); //Tributo: Monto de IGV por ítem + Tributo ISC: Monto de ISC por ítem + Tributo Otro: Código de tipo de tributo OTRO por Item
            det.put("codTriIGV", nd.getCodigoTipoTributo()); //ENLAZAR TABLA CONCEPTO CON EL CATALOGO 05 - SUNAT
            det.put("mtoIgvItem", String.valueOf(nd.getIgv()));
            det.put("mtoBaseIgvItem", String.valueOf(nd.getValorVenta()));
            det.put("nomTributoIgvItem", nd.getNombreTipoTributo()); //ENLAZAR TABLA CONCEPTO CON EL CATALOGO 05 - SUNAT - CAMPO NAME
            det.put("codTipTributoIgvItem", nd.getCodigoInternacionalTipoTributo()); //ENLAZAR TABLA CONCEPTO CON EL CATALOGO 05 - SUNAT - CODIGO -- LLAMAR SUNAT
            det.put("tipAfeIGV", nd.getCodigoTipoAfectacion()); //PREGUNTAR SI SIEMPRE SE USA ESE CODIGO - CATALOGO 07
            det.put("porIgvItem", String.valueOf(nd.getIgvPorcentaje())); //Colocar 18.00 para expresar 18% - AGREGAR EL CAMPO PORCENTAJE EN EL DETALLE
            //---------------------------------ISC------------------------------
            det.put("codTriISC", "");
            det.put("mtoIscItem", "0.0");
            det.put("mtoBaseIscItem", "0.0");
            det.put("nomTributoIscItem", "");
            det.put("codTipTributoIscItem", "");
            det.put("tipSisISC", "");
            det.put("porIscItem", "0");
            //--------------------------OTROS TRIBUTOS--------------------------
            det.put("codTriOtroItem", "");
            det.put("mtoTriOtroItem", "0.0");
            det.put("mtoBaseTriOtroItem", "0.0");
            det.put("nomTributoIOtroItem", "");
            det.put("codTipTributoIOtroItem", "");
            det.put("porTriOtroItem", "0");
            //------------------------------------------------------------------
            double precioUnitario = (nd.getValorVenta() + nd.getIgv()) / nd.getCantidad();
            precioUnitario = Math.round(precioUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
            det.put("mtoPrecioVentaUnitario", String.valueOf(precioUnitario));
            det.put("mtoValorVentaItem", String.valueOf(nd.getValorVenta()));
            det.put("mtoValorReferencialUnitario", "0.0");
            //-----------------------TRIBUTOS GENERALES-------------------------
            det.put("ideTributo", nd.getCodigoTipoTributo());
            det.put("nomTributo", nd.getNombreTipoTributo());
            det.put("codTipTributo", nd.getCodigoInternacionalTipoTributo());
            det.put("mtoBaseImponible", String.valueOf(nd.getValorVenta()));
            det.put("mtoTributo", String.valueOf(nd.getIgv()));
            objDetalle.add(det);
        }
        objPrincipal.put("detalle", objDetalle);
        //---------------------------------LEYENDA------------------------------
        JSONObject objLeyenda = new JSONObject();
        objLeyenda.put("codLeyenda", "1000");
        objLeyenda.put("desLeyenda", NumeroLetras.convertirNumerosALetras(lbMontoTotal.getText(), this.notaCredito.getMoneda()).replace("/", "'/'"));
        objPrincipal.put("leyenda", objLeyenda);
        String nombreArchivo = "";
        String tipoNota = "";
        if (this.notaCredito.getTipo() == 1) {
            tipoNota = "07";
        } else {
            tipoNota = "08";
        }
        try {

            nombreArchivo = "20174327026-" + tipoNota + "-" + this.notaCredito.getNroSerie() + "-" + String.format("%08d", this.notaCredito.getNro()) + ".json";
            FileWriter archivo = new FileWriter("\\\\192.168.1.67\\data\\" + nombreArchivo);
//            FileWriter archivo = new FileWriter("Factura_Electronica/" + nombreArchivo);
//            FileWriter archivo = new FileWriter("\\\\192.168.1.100\\d\\data0\\facturador\\DATA\\" + nombreArchivo);
            archivo.write(objPrincipal.toString());
            archivo.flush();
            archivo.close();

        } catch (IOException e) {
            //manejar error
        }
    }

    private void ObtenerMontoTotal() {
        double montoTotal = 0;
        double montoDetraccion = 0;
        for (int i = 0; i < tDetalle.getRowCount(); i++) {
            if (tDetalle.getValueAt(i, 8) != null) {
                double monto = Double.valueOf(tDetalle.getValueAt(i, 8).toString());
                double montoDetraccionTemp = Double.valueOf(tDetalle.getValueAt(i, 10).toString());
                montoTotal = montoTotal + monto;
                montoDetraccion = montoDetraccion + montoDetraccionTemp;
            }
        }
        montoTotal = Math.round(montoTotal * Math.pow(10, 2)) / Math.pow(10, 2);
        montoDetraccion = Math.round(montoDetraccion * Math.pow(10, 2)) / Math.pow(10, 2);
        lbMontoTotal.setText(String.valueOf(montoTotal));
        lbMontoDetraccion.setText(String.valueOf(montoDetraccion));
    }


    private void tDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE && this.notaCredito.getEstado().equals("A")) {
            int fila = tDetalle.getSelectedRow();
            if (fila >= 0) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿ DESEA ELIMINAR EL ÍTEM ?");
                if (opcion == JOptionPane.YES_OPTION) {
//                    NotaDetalle ncd = (NotaDetalle) tDetalle.getValueAt(fila, 0);
//                    if (ncd.getId() != 0) {
//                        NotaBO npBO = NotaBO.getInstance();
//                        npBO.EliminarNotaDetalle(ncd);
//                    }
                    DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
                    model.removeRow(fila);
                    this.ObtenerMontoTotal();
                }
            }
        }
    }//GEN-LAST:event_tDetalleKeyPressed

    public void ObtenerIGV(NotaDetalle ncd) {
//        ArticuloBO aBO = ArticuloBO.getInstance();
//        ArticuloUnidad au = aBO.ObtenerArticuloUnidad(ncd.getComprobantePagoDetalle().getArticulo().getId(), ncd.getComprobantePagoDetalle().getUnidadMedida().getId());
//        if (ncd.getComprobantePagoDetalle().getTipoAfectacion().getTipo().equals("G")) {
//            if (au.getTieneIgv().equals("S")) {
//                ncd.setIgv(au.getIgv());
//            } else {
//                ncd.setPercepcion(0.0);
//                ncd.setIgv(0.0);
//            }
//        } else {
//            if (ncd.getComprobantePagoDetalle().getTipoAfectacion().getTipo().equals("I")) {
//                ncd.setPercepcion(0.0);
//                ncd.setIgv(0.0);
//            } else {
//                if (ncd.getComprobantePagoDetalle().getTipoAfectacion().getTipo().equals("E")) {
//                    ncd.setPercepcion(0.0);
//                    ncd.setIgv(0.0);
//                }
//            }
//        }
    }

    public void ModificarDetalle(NotaDetalle ncd) {
        int fila = tDetalle.getSelectedRow();
        if (fila >= 0) {
            double total = ncd.getValorVenta() + ncd.getIgv();
            total = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
            tDetalle.setValueAt(ncd.getCantidad(), fila, 5);
            tDetalle.setValueAt(ncd.getValorVenta(), fila, 6);
            tDetalle.setValueAt(ncd.getIgv(), fila, 7);
            tDetalle.setValueAt(total, fila, 8);
            tDetalle.setValueAt(ncd.getMontoDetraccion(), fila, 10);
            this.ObtenerMontoTotal();
        }
    }

    private void tDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleMouseClicked
        if (evt.getClickCount() == 2) {
            if (this.notaCredito.getEstado().equals("A")) {
                int fila = tDetalle.getSelectedRow();
                if (fila > -1) {
                    NotaDetalle ncd = (NotaDetalle) tDetalle.getValueAt(fila, 0);
                    frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
                    f.abrirFormularioModificarArticulo_NotaCredito(this, ncd, tDetalle, 1);
                }
            }
        }
    }//GEN-LAST:event_tDetalleMouseClicked

    private String ObtenerValorResumen() {
        try {
            String tipoNota = "";
            if (this.notaCredito.getTipo() == 1) {
                tipoNota = "07";
            } else {
                tipoNota = "08";
            }
            String nombreArchivo = "20174327026-" + tipoNota + "-" + this.notaCredito.getDocumentoPago().getTipoDocSerie().getSerie() + "C01-" + String.format("%08d", this.notaCredito.getNro()) + ".xml";
            String rutaArchivo = "\\\\192.168.1.67\\firma\\" + nombreArchivo;
            InputStream inputStream = new FileInputStream(rutaArchivo);
            Reader reader = new InputStreamReader(inputStream, "ISO-8859-1");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            File fXmlFile = new File(rutaArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
//            Document doc = dBuilder.parse(fXmlFile);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
            output = output.substring(output.indexOf("<ds:DigestValue>") + 16);
            output = output.substring(0, output.indexOf("</ds:DigestValue>"));

            doc.getDocumentElement().normalize();
            System.out.println("ValoResumen:" + doc.getElementsByTagName("DigestValue"));
            NodeList nodo = doc.getElementsByTagName("ds:DigestValue");
            System.out.println("ValoResumen:" + nodo.item(0).getNodeValue());
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            System.out.println("ValoResumen:" + doc.getElementById("ds:digestmethod"));
            System.out.println("ValoResumen:" + doc.getElementById("ds:digestvalue"));
            NodeList nList = doc.getElementsByTagName("staff");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Staff id : " + eElement.getAttribute("id"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
                }
            }
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void ImprimirNotaElectronico() {
        try {
            String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(jdbcDriver);
            SeguridadBO sBO = SeguridadBO.getInstance();
            String url = sBO.getUrlReporte();
            String user = "sa";
            String pass = "4dm1n1str4c10nGOB90570";
            Connection con = DriverManager.getConnection(url, user, pass);
            String fullPath = "reportes/nota_electronica.jasper";
            Map param = new HashMap();
            String valorResumen = "";
//            String valorResumen = this.ObtenerValorResumen();
            String tipoDocumentoAdquiriente = "";
            String nrodocumentoAdquiriente = "";
            String nombreTipoDocumento = "";
            if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
                tipoDocumentoAdquiriente = "1";
                nrodocumentoAdquiriente = this.notaCredito.getDocumentoPago().getCliente().getPnroDocumento();
                nombreTipoDocumento = "NRO DOC:";
            } else {
                if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("P")) {
                    tipoDocumentoAdquiriente = "1";
                    nrodocumentoAdquiriente = this.notaCredito.getDocumentoPago().getCliente().getPnroDocumento();
                    nombreTipoDocumento = "NRO DOC:";
                } else {
                    if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("E")) {
                        tipoDocumentoAdquiriente = "6";
                        nrodocumentoAdquiriente = this.notaCredito.getDocumentoPago().getCliente().getEruc();
                        nombreTipoDocumento = "NRO RUC:";
                    } else {
                        if (this.notaCredito.getDocumentoPago().getCliente().getTipoCliente().equals("S")) {
                            tipoDocumentoAdquiriente = "6";
                            nrodocumentoAdquiriente = this.notaCredito.getDocumentoPago().getCliente().getSruc();
                            nombreTipoDocumento = "NRO RUC:";
                        }
                    }
                }
            }
            NotaBO ncBO = NotaBO.getInstance();
            double montoTotalIgv = ncBO.ObtenerSumaTotalIGV(this.notaCredito.getId());
            double montoTotalComprobante = ncBO.ObtenerSumaTotalPrecioVenta(this.notaCredito.getId());
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            String fechaEmision = f.format(this.notaCredito.getFecha());
            String tipoNota = "";
            String nombreTipoNota = "";
            String representacionImpresa = "";
            if (this.notaCredito.getTipo() == 1) {
                tipoNota = "07";
                nombreTipoNota = "NOTA DE CRÉDITO ELECTRÓNICA";
                representacionImpresa = "Representación impresa de la " + nombreTipoNota;
            } else {
                tipoNota = "08";
                nombreTipoNota = "NOTA DE DÉBITO ELECTRÓNICA";
                representacionImpresa = "Representación impresa de la " + nombreTipoNota;
            }
            String valorQR = "20174327026|" + tipoNota + "|" + this.notaCredito.getNroSerie() + "|" + String.format("%06d", notaCredito.getNro()) + "|";
            valorQR = valorQR + String.valueOf(montoTotalIgv) + "|" + String.valueOf(montoTotalComprobante) + "|" + fechaEmision + "|" + tipoDocumentoAdquiriente + "|" + nrodocumentoAdquiriente + "|" + valorResumen;
            String letras = NumeroLetras.convertirNumerosALetras(lbMontoTotal.getText(), this.notaCredito.getMoneda());
            param.put("IDNOTA", this.notaCredito.getId());
            param.put("TIPONOTA", nombreTipoNota);
            param.put("TIPODOCUMENTO", nombreTipoDocumento);
            param.put("REPRESENTACIONIMPRESA", representacionImpresa);
            param.put("VALOR_RESUMEN", valorResumen);
            param.put("TOTALENLETRAS", letras);
            param.put("VALORQR", valorQR);
            param.put("REPORT_LOCALE", new Locale("en", "EN"));
            JasperPrint JPrint = JasperFillManager.fillReport(fullPath, param, con);
            // VIEW THE REPORT
//            JasperViewer.viewReport(JPrint, false);
            JasperPrintManager.printPage(JPrint, 0, true);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        final frmPrincipal fPrin = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        Thread queryThread = new Thread() {
            public void run() {
                frmCargando fCargando = new frmCargando();
                fPrin.AgregarFormulario(fCargando);
                int x = (fPrin.getWidth() - fCargando.getWidth()) / 2;
                int y = (fPrin.getHeight() - fCargando.getHeight()) / 2;
                fCargando.setLocation(x, y);
                fCargando.setVisible(true);
                fCargando.toFront();
//                ImprimirComprobante();
                ImprimirNotaElectronico();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void EliminarArchivoJSON(Nota nota) {
        String nombreArchivo = "\\\\192.168.1.67\\DATA\\" + "20174327026-07-" + nota.getNroSerie() + "-" + String.format("%08d", nota.getNro());
        File fichero = new File(nombreArchivo + ".DET");
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        fichero = new File(nombreArchivo + ".LEY");
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        fichero = new File(nombreArchivo + ".NOT");
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        fichero = new File(nombreArchivo + ".TRI");
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
    }

    private void EliminarArchivoXML(Nota nota) {
        String nombreArchivo = "\\\\192.168.1.67\\firma\\" + "20174327026-07-" + nota.getNroSerie() + "-" + String.format("%08d", nota.getNro()) + ".xml";;
        File fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo jsonno existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
    }


    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿ DESEA ELIMINAR LA NOTA ?");
        if (opcion == JOptionPane.YES_OPTION) {
            NotaBO nBO = NotaBO.getInstance();
            List<Object> l = nBO.ObtenerIdParticipantes_SegunNotaCredito(this.notaCredito.getId());
            boolean seElimino = nBO.EliminarNota(this.notaCredito);
            if (seElimino) {
                this.habilitarParticipantesEvento(l);
                this.EliminarArchivoJSON(this.notaCredito);
                this.EliminarArchivoXML(this.notaCredito);
                JOptionPane.showMessageDialog(this,
                        "SE ELIMINÓ EL COMPROBANTE",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "NO SE PUDO ELIMINAR EL COMPROBANTE",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
//            JOptionPane.showMessageDialog(this,
//                    "SE ELIMINÓ LA NOTA DE CRÉDITO",
//                    "OK",
//                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNroNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroNotaKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNroNotaKeyTyped

    private String GenerarPdfEnvio(String nombreComprobante) {
        try {
            File carpeta = new File("DOCUMENTOS_ELECTRONICOS/");
            carpeta.mkdirs();
            String jdbcDriver = "org.postgresql.Driver";
            Class.forName(jdbcDriver);
            SeguridadBO sBO = SeguridadBO.getInstance();
            String url = sBO.getUrlReporte();
            String user = "postgres";
            String pass = "4dm1n1str4c10nSQL2015";
            Connection con = DriverManager.getConnection(url, user, pass);
            String fullPath = "reportes/v_nota_credito_electronico_envio.jasper";
            Map param = new HashMap();
            String valorResumen = this.ObtenerValorResumen();
//            String letras = NumeroLetras.convertirNumerosALetras(lbMontoTotal.getText(), this.notaCredito.getComprobantePago().getTipoMoneda().getNombre());
            param.put("ID_COMPROBANTE", this.notaCredito.getId());
            param.put("TIPO_COMPROBANTE", "NOTA DE CRÉDITO ELECTRÓNICA");
            param.put("NRO_COMPROBANTE", this.notaCredito.getNroSerie() + " - " + String.format("%08d", this.notaCredito.getNro()));
            param.put("VALOR_RESUMEN", valorResumen);
//            param.put("MONTO_LETRAS", letras);
            param.put("REPORT_LOCALE", new Locale("en", "EN"));
            JasperPrint JPrint = JasperFillManager.fillReport(fullPath, param, con);
            // VIEW THE REPORT
//            JasperViewer.viewReport(JPrint, false);
            //JasperPrintManager.printPage(JPrint, 0, true);
//            File pdf = File.createTempFile("DOCUMENTOS_ELECTRONICOS/", ".pdf");
            JasperExportManager.exportReportToPdfFile(JPrint, "DOCUMENTOS_ELECTRONICOS/" + nombreComprobante);
            String rutaDocumento = "DOCUMENTOS_ELECTRONICOS/" + nombreComprobante;
            return rutaDocumento;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "";
        }
    }

    private void btnMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMailActionPerformed
        //        try {
        //            Properties props = new Properties();
        //            props.setProperty("mail.smtp.host", "smtp.gmail.com");
        //            props.setProperty("mail.smtp.starttls.enable", "true");
        //            props.setProperty("mail.smtp.port", "587");
        //            props.setProperty("mail.smtp.user", "pacificodistribucionessac@gmail.com");
        //            props.setProperty("mail.smtp.auth", "true");
        //            Session session = Session.getDefaultInstance(props);
        //            session.setDebug(true);
        //            BodyPart texto = new MimeBodyPart();
        //            texto.setText("Le remito el comprobante electronico \n PACIFICO DISTRIBUCIONES");
        //            BodyPart adjunto = new MimeBodyPart();
        //            String nombreComprobante = "20100194601-" + this.comprobantePago.getTipoDocumento().getCodigo() + "-" + this.comprobantePago.getTipoDocumento().getCodigoSerieSunat() + "001-" + String.format("%08d", this.comprobantePago.getNro()) + ".pdf";
        //            String nombreArchivoElectronico = "20100194601-" + this.comprobantePago.getTipoDocumento().getCodigo() + "-" + this.comprobantePago.getTipoDocumento().getCodigoSerieSunat() + "001-" + String.format("%08d", this.comprobantePago.getNro()) + ".zip";
        //            adjunto.setDataHandler(new DataHandler(new FileDataSource("\\\\192.168.1.100\\d\\data0\\facturador\\FIRMA\\" + nombreArchivoElectronico)));
        //            adjunto.setFileName("archivo.sql");
        //            BodyPart comprobante = new MimeBodyPart();
        //            String rutaComprobante = this.GenerarPdfEnvio(nombreComprobante);
        //            comprobante.setDataHandler(new DataHandler(new FileDataSource(rutaComprobante)));
        //            comprobante.setFileName(nombreComprobante);
        //            MimeMultipart multiParte = new MimeMultipart();
        //            multiParte.addBodyPart(texto);
        //            multiParte.addBodyPart(comprobante);
        //            MimeMessage message = new MimeMessage(session);
        //
        //            message.setFrom(new InternetAddress("pacificodistribucionessac@gmail.com"));
        //            message.addRecipient(Message.RecipientType.TO, new InternetAddress("rangel537@gmail.com"));
        //            if (this.comprobantePago.getClienteProveedor() != null) {
        //                if (this.comprobantePago.getClienteProveedor().getEmail() != null) {
        //                    if (!this.comprobantePago.getClienteProveedor().getEmail().isEmpty()) {
        //                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.comprobantePago.getClienteProveedor().getEmail()));
        //                    } else {
        //                        JOptionPane.showMessageDialog(this, "EL CLIENTE NO TIENE CORREO ELECTRÓNICO", "ERROR", JOptionPane.ERROR_MESSAGE);
        //                        return;
        //                    }
        //                } else {
        //                    JOptionPane.showMessageDialog(this, "EL CLIENTE NO TIENE CORREO ELECTRÓNICO", "ERROR", JOptionPane.ERROR_MESSAGE);
        //                    return;
        //                }
        //            } else {
        //                JOptionPane.showMessageDialog(this, "DEBE ELEGIR UN CLIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        //                return;
        //            }
        //            message.setSubject("COMPROBANTE ELECTRÓNICO - " + nombreComprobante);
        //            message.setContent(multiParte);
        //            Transport t = session.getTransport("smtp");
        //            t.connect("pacificodistribucionessac@gmail.com", "sgnmsimrilpzjoly");
        //            t.sendMessage(message, message.getAllRecipients());
        //            t.close();
        //            JOptionPane.showMessageDialog(this, "SE ENVIÓ EL CORREO ELECTRÓNICO", "OK", JOptionPane.INFORMATION_MESSAGE);
        //        } catch (MessagingException ex) {
        //            Logger.getLogger(frmVentaDetalleElectronico.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        //----------------------------------------------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------PRUEBA---------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        try {
            Properties props = new Properties();

            // Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", "smtp.gmail.com");

            // TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", "true");

            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", "587");

            // Nombre del usuario
            props.setProperty("mail.smtp.user", "pacificodistribucionessac@gmail.com");

            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");

            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getDefaultInstance(props);

            // Para obtener un log de salida más extenso
            session.setDebug(true);

            //            MimeMessage message = new MimeMessage(session);
            //
            //// Quien envia el correo
            //            message.setFrom(new InternetAddress("rangel537@gmail.com"));
            //
            //// A quien va dirigido
            //            message.addRecipient(Message.RecipientType.TO, new InternetAddress("rangel537@hotmail.com.com"));
            //
            //            message.setSubject("COMPROBANTE ELECTRONICO - PACIFICO DISTRIBUCIONES");
            //            message.setText("Le remito el comprobante electronico");
            BodyPart texto = new MimeBodyPart();

            // Texto del mensaje
            texto.setText("Le remito la nota de credito electronica \n PACIFICO DISTRIBUCIONES");
            //            BodyPart adjunto = new MimeBodyPart();
            // Cargamos la imagen
            //            adjunto.setDataHandler(new DataHandler(new FileDataSource("D:\\carlota20160309.backup")));
            // Opcional. De esta forma transmitimos al receptor el nombre original del
            // fichero de imagen.
            //            String nombreComprobante = "20100194601-" + this.comprobantePago.getTipoDocumento().getCodigo() + "-" + this.comprobantePago.getTipoDocumento().getCodigoSerieSunat() + "001-" + String.format("%08d", this.comprobantePago.getNro()) + ".pdf";
            //            String nombreComprobante = "";
            //            adjunto.setFileName("archivo.sql");
            //            BodyPart comprobante = new MimeBodyPart();
            //            String rutaComprobante = this.GenerarPdfEnvio(nombreComprobante);
            //            comprobante.setDataHandler(new DataHandler(new FileDataSource(rutaComprobante)));
            //            comprobante.setFileName(nombreComprobante);
            //---------------------INICIO COMPROBANTE ELECTRONICO IMPRESO----------------------
            BodyPart comprobante = new MimeBodyPart();
            String nombreComprobante = "20100194601-" + this.notaCredito.getNroSerie() + String.format("%08d", this.notaCredito.getNro()) + ".pdf";
            String rutaComprobante = this.GenerarPdfEnvio(nombreComprobante);
            comprobante.setDataHandler(new DataHandler(new FileDataSource(rutaComprobante)));
            comprobante.setFileName(nombreComprobante);
            //---------------------FIN COMPROBANTE ELECTRONICO IMPRESO----------------------
            //---------------------INICIO ARCHIVO COMPROBANTE ELECTRONICO----------------------
            BodyPart adjunto = new MimeBodyPart();
            String codigo = "";
            if (this.notaCredito.getTipo() == 1) {
                codigo = "07";
            } else {
                codigo = "08";
            }
            String nombreArchivoElectronico = "20100194601-" + codigo + "-" + this.notaCredito.getNroSerie() + "-" + String.format("%08d", this.notaCredito.getNro()) + ".zip";
            String rutaArchivoElectronico = "\\\\192.168.1.100\\d\\data0\\facturador\\RPTA\\R" + nombreArchivoElectronico;
            adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaArchivoElectronico)));
            adjunto.setFileName(nombreArchivoElectronico);
            //            adjunto.setFileName(nombreArchivoElectronico);
            //---------------------FIN ARCHIVO COMPROBANTE ELECTRONICO----------------------

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            multiParte.addBodyPart(comprobante);
            MimeMessage message = new MimeMessage(session);

            // Se rellena el From
            message.setFrom(new InternetAddress("pacificodistribucionessac@gmail.com"));

            // Se rellenan los destinatarios
//            if (this.notaCredito.getDocumentoPago().getCliente() != null) {
//                SeguridadBO sBO = SeguridadBO.getInstance();
//                Cliente cpDB = (Cliente) sBO.CargarObjeto("Cliente", this.notaCredito.getComprobantePago().getClienteProveedor().getId());
//                if (cpDB.getEmail() != null) {
//                    this.notaCredito.getComprobantePago().getClienteProveedor().setEmail(cpDB.getEmail());
//                }
//                if (this.notaCredito.getComprobantePago().getClienteProveedor().getEmail() != null) {
//                    if (!this.notaCredito.getComprobantePago().getClienteProveedor().getEmail().isEmpty()) {
//                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.notaCredito.getComprobantePago().getClienteProveedor().getEmail()));
//                    } else {
//                        JOptionPane.showMessageDialog(this, "EL CLIENTE NO TIENE CORREO ELECTRÓNICO", "ERROR", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "EL CLIENTE NO TIENE CORREO ELECTRÓNICO", "ERROR", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "DEBE ELEGIR UN CLIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            // Se rellena el subject
            message.setSubject("NOTA DE CRÉDITO ELECTRÓNICA - " + nombreComprobante);

            // Se mete el texto y la foto adjunta.
            message.setContent(multiParte);
            Transport t = session.getTransport("smtp");

            // Aqui usuario y password de gmail
            t.connect("pacificodistribucionessac@gmail.com", "sgnmsimrilpzjoly");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            JOptionPane.showMessageDialog(this, "SE ENVIÓ EL CORREO ELECTRÓNICO", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException ex) {
            Logger.getLogger(frmNotaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "NO SE PUDO ENVIAR EL CORREO ELECTRÓNICO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnMailActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
//        if (this.notaCredito.getId() > 0) {
//            String[] Movimientos = {"APLICACIÓN FACTURA", "DEVOLUCIÓN CHEQUE"};
//            String TipoMovimiento = (String) JOptionPane.showInputDialog(null,
//                    "APLICACIÓN:",
//                    "ELEGIR",
//                    JOptionPane.QUESTION_MESSAGE,
//                    null,
//                    Movimientos,
//                    Movimientos[0]);
//            if (TipoMovimiento.equals("APLICACIÓN FACTURA")) {
//                frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
//                f.abrirFormularioSeleccionarComprobante(this, 6);
//
//            } else {
//                if (TipoMovimiento.equals("DEVOLUCIÓN CHEQUE")) {
//                    this.notaCredito.setTipoAplicacion(1);
//                    this.notaCredito.setComprobantePagoByIdComprobanteAplicacion(null);
//                    txtAplicacionFactura.setText("");
//                    NotaBO nBO = NotaBO.getInstance();
//                    nBO.ActualizarNota(this.notaCredito);
//                    this.CargarDatos(this.notaCredito);
//                }
//            }
//        }
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void cbTipoNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoNotaActionPerformed
        NotaBO nBO = NotaBO.getInstance();
        cbMotivoNota.removeAllItems();
        int tipoNota = 0;
//        String tipoN = "";
        if (cbTipoNota.getSelectedItem().toString().equals("NOTA CRÉDITO ELECTRÓNICA")) {
//            tipoN = "C";
            tipoNota = 1;
        } else {
//            tipoN = "D";
            tipoNota = -1;
        }
        if (this.notaCredito.getDocumentoPago() != null) {
            if (this.notaCredito.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
                String serie = "F001";
                cbSerie.setSelectedItem(serie);
            } else {
                if (this.notaCredito.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("03")) {
                    String serie = "B001";
                    cbSerie.setSelectedItem(serie);
                }
            }
        }
        List<TipoNota> l = nBO.ObtenerTodosTipoNota(tipoNota);
        for (TipoNota tn : l) {
            cbMotivoNota.addItem(tn);
        }
    }//GEN-LAST:event_cbTipoNotaActionPerformed

    private void btnMail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMail1ActionPerformed
        this.GenerarArchivoElectronicoSUNAT_TXT();
    }//GEN-LAST:event_btnMail1ActionPerformed

    private void MostrarPantallaDocumentoPago(DocumentoPago dp) {
        String nroSerie = "";
        if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
            if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                nroSerie = dp.getNroSerie();
            } else {
                nroSerie = dp.getNroSerie();
            }
        } else {
            nroSerie = dp.getNroSerie();
        }
        this.notaCredito.setDocumentoPago(dp);
        String comprobante = dp.getTipoDocSerie().getTipoDocPago().getAbreviatura() + " " + nroSerie + " - " + String.format("%06d", dp.getNroDocumentoPago());
        txtComprobante.setText(comprobante);
//        String tipoN = "";
//        if (cbTipoNota.getSelectedItem().toString().equals("NOTA CRÉDITO ELECTRÓNICA")) {
//            tipoN = "C";
//        } else {
//            tipoN = "D";
//        }
        if (dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
            String serie = "F001";
            cbSerie.setSelectedItem(serie);
        } else {
            if (dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("03")) {
                String serie = "B001";
                cbSerie.setSelectedItem(serie);
            }
        }
        String nombreCliente = "";
        String direccionCliente = "";
        if (dp.getCliente().getTipoCliente().equals("C")) {
            nombreCliente = dp.getCliente().getPapePat() + " " + dp.getCliente().getPapeMat() + " " + dp.getCliente().getPnombre();
            direccionCliente = dp.getCliente().getPdireccionDomicilio();
        } else {
            if (dp.getCliente().getTipoCliente().equals("P")) {
                nombreCliente = dp.getCliente().getPapePat() + " " + dp.getCliente().getPapeMat() + " " + dp.getCliente().getPnombre();
                direccionCliente = dp.getCliente().getPdireccionDomicilio();
            } else {
                if (dp.getCliente().getTipoCliente().equals("S")) {
                    nombreCliente = dp.getCliente().getSnombreSociedad();
                    direccionCliente = dp.getCliente().getSdireccion();
                } else {
                    if (dp.getCliente().getTipoCliente().equals("E")) {
                        nombreCliente = dp.getCliente().getEnombre();
                        direccionCliente = dp.getCliente().getEdireccion();
                    }
                }
            }
        }
        txtCliente.setText(nombreCliente);
        txtDirección.setText(direccionCliente);
    }

    public void AgregarComprobante(DocumentoPago dp) {
        String nroSerie = "";
        if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
            if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                nroSerie = dp.getNroSerie();
            } else {
                nroSerie = dp.getNroSerie();
            }
        } else {
            nroSerie = dp.getNroSerie();
        }
        this.notaCredito.setDocumentoPago(dp);
        String comprobante = dp.getTipoDocSerie().getTipoDocPago().getAbreviatura() + " " + nroSerie + " - " + String.format("%06d", dp.getNroDocumentoPago());
        txtComprobante.setText(comprobante);
//        String tipoN = "";
//        if (cbTipoNota.getSelectedItem().toString().equals("NOTA CRÉDITO ELECTRÓNICA")) {
//            tipoN = "C";
//        } else {
//            tipoN = "D";
//        }
        if (dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
            String serie = "F001";
            cbSerie.setSelectedItem(serie);
        } else {
            if (dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("03")) {
                String serie = "B001";
                cbSerie.setSelectedItem(serie);
            }
        }
        String nombreCliente = "";
        String direccionCliente = "";
        if (dp.getCliente().getTipoCliente().equals("C")) {
            nombreCliente = dp.getCliente().getPapePat() + " " + dp.getCliente().getPapeMat() + " " + dp.getCliente().getPnombre();
            direccionCliente = dp.getCliente().getPdireccionDomicilio();
        } else {
            if (dp.getCliente().getTipoCliente().equals("P")) {
                nombreCliente = dp.getCliente().getPapePat() + " " + dp.getCliente().getPapeMat() + " " + dp.getCliente().getPnombre();
                direccionCliente = dp.getCliente().getPdireccionDomicilio();
            } else {
                if (dp.getCliente().getTipoCliente().equals("S")) {
                    nombreCliente = dp.getCliente().getSnombreSociedad();
                    direccionCliente = dp.getCliente().getSdireccion();
                } else {
                    if (dp.getCliente().getTipoCliente().equals("E")) {
                        nombreCliente = dp.getCliente().getEnombre();
                        direccionCliente = dp.getCliente().getEdireccion();
                    }
                }
            }
        }
        txtCliente.setText(nombreCliente);
        txtDirección.setText(direccionCliente);
        this.notaCredito.setMoneda(dp.getMoneda());
        if (dp.getMoneda().equals("S")) {
            txtMoneda.setText("SOLES");
            lbTipoMoneda.setText("SOLES");
        } else {
            txtMoneda.setText("DOLARES");
            lbTipoMoneda.setText("DOLARES");
        }
//        if (cp.getTipoOperacion() != null) {
//            txtTipoOperacion.setText(cp.getTipoOperacion().getCodigo() + "-" + cp.getTipoOperacion().getDescripcion());
//        }
//        List<DocumentoPagoDet> ldetalle = new ArrayList();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<DocumentoPagoDet> ldetalle = dpBO.ObtenerDetalleComprobante(dp.getIdDocumentoPago());
        DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
        int contador = 0;
        model.setNumRows(0);
        for (DocumentoPagoDet dpd : ldetalle) {
            NotaDetalle ncd = new NotaDetalle();
            ncd.setDocumentoPagoDet(dpd);
            ncd.setCantidad(dpd.getCantidad());
            ncd.setMontoDetraccion(!Objects.isNull(dpd.getMontoDetraccion()) ? dpd.getMontoDetraccion() : 0.0);
//            ncd.setDescuento(cpd.getDescuento());
            ncd.setIgv(dpd.getIgv());
            ncd.setIgvPorcentaje((dpd.getIgvPorcentaje() != null ? dpd.getIgvPorcentaje() : 0.0));
            ncd.setValorVenta(dpd.getValorVenta());
            double total = ncd.getValorVenta() + ncd.getIgv();
//            double Descuento = total * ncd.getDescuento() / 100;
//            Descuento = Math.round(Descuento * Math.pow(10, 2)) / Math.pow(10, 2);
//            total = total - Descuento;
            total = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
//            double MontoConIgv = total * (ncd.getIgv() + 100) / 100;
//            MontoConIgv = Math.round(MontoConIgv * Math.pow(10, 2)) / Math.pow(10, 2);
            ncd.setCodigoTipoTributo(dpd.getCodigoTipoTributo());
            ncd.setNombreTipoTributo(dpd.getNombreTipoTributo());
            ncd.setCodigoInternacionalTipoTributo(dpd.getCodigoInternacionalTipoTributo());
            ncd.setCodigoTipoAfectacion(dpd.getCodigoTipoAfectacion());
            model.addRow(new Object[contador]);
            model.setValueAt(ncd, contador, 0);
            model.setValueAt(contador + 1, contador, 1);
            model.setValueAt(ncd.getDocumentoPagoDet().getConceptoPagoDetalle().getTipoCodigo() + ncd.getDocumentoPagoDet().getConceptoPagoDetalle().getCodigo(), contador, 2);
            model.setValueAt(ncd.getDocumentoPagoDet().getConceptoPagoDetalle().getDescripcion(), contador, 3);
//            model.setValueAt(ncd.getComprobantePagoDetalle().getUnidadMedida(), contador, 4);
            model.setValueAt(ncd.getCantidad(), contador, 5);
            model.setValueAt(ncd.getValorVenta(), contador, 6);
            model.setValueAt(ncd.getIgv(), contador, 7);
            model.setValueAt(total, contador, 8);
            model.setValueAt(dpd.getValorVenta() + dpd.getIgv(), contador, 9);
            model.setValueAt(!Objects.isNull(ncd.getMontoDetraccion()) ? ncd.getMontoDetraccion() : 0.0, contador, 10);
//            model.setValueAt(ncd.getComprobantePagoDetalle(), contador, 12);
            contador = contador + 1;
        }
        this.ObtenerMontoTotal();
    }

//    public void AgregarAplicacionComprobante(ComprobantePago cp) {
//        this.notaCredito.setComprobantePagoByIdComprobanteAplicacion(cp);
//        String comprobante = cp.getTipoDocumento().getNombre() + " " + cp.getNroSerie() + " - " + String.format("%06d", cp.getNro());
//        txtAplicacionFactura.setText(comprobante);
//    }
//
//    public void CambiarAplicacionComprobante(ComprobantePago cp) {
//        if (this.notaCredito.getId() > 0) {
//            this.notaCredito.setTipoAplicacion(2);
//            this.notaCredito.setComprobantePagoByIdComprobanteAplicacion(cp);
//            String comprobante = cp.getTipoDocumento().getNombre() + " " + cp.getNroSerie() + " - " + String.format("%06d", cp.getNro());
//            txtAplicacionFactura.setText(comprobante);
//            NotaBO nBO = NotaBO.getInstance();
//            nBO.ActualizarNota(this.notaCredito);
//            this.CargarDatos(this.notaCredito);
//        }
//    }
    public void CargarDatos(Nota nc) {
        this.notaCredito = nc;
        if (nc.getEstado().equals("A")) {
            btnGuardar.setEnabled(true);
//            btnAprobar.setEnabled(true);
            btnImprimir.setEnabled(true);
            btnFecha.setEnabled(true);
            cbTipoNota.setEnabled(true);
            cbMotivoNota.setEnabled(true);
            txtNroNota.setEnabled(true);
            btnSeleccionarComprobante.setEnabled(true);
        } else {
            if (nc.getEstado().equals("C")) {
                btnGuardar.setEnabled(false);
//                btnAprobar.setEnabled(false);
                btnImprimir.setEnabled(true);
                btnFecha.setEnabled(false);
                cbTipoNota.setEnabled(false);
                cbMotivoNota.setEnabled(false);
                txtNroNota.setEnabled(false);
                btnSeleccionarComprobante.setEnabled(false);
            } else {
                btnGuardar.setEnabled(false);
//                btnAprobar.setEnabled(false);
                btnImprimir.setEnabled(false);
                btnFecha.setEnabled(false);
                txtNroNota.setEnabled(false);
                cbTipoNota.setEnabled(false);
                cbMotivoNota.setEnabled(false);
                btnSeleccionarComprobante.setEnabled(false);
            }
        }
        if (nc.getId() != 0) {
//            ComprobantePago cp = nc.getComprobantePago();
//            String comprobante = cp.getTipoDocumento().getNombre() + " " + cp.getNroSerie() + " - " + String.format("%06d", cp.getNro());
            txtNroNota.setText(String.format("%06d", nc.getNro()));
            cbSerie.setSelectedItem(nc.getNroSerie());
//            txtComprobante.setText(comprobante);
//            txtCliente.setText(cp.getClienteProveedor().getNombreCompleto());
//            txtDirección.setText(cp.getClienteProveedor().getDireccion());
//            txtMoneda.setText(cp.getTipoMoneda().getNombre());
//            txtTipoOperacion.setText(cp.getTipoOperacion().getCodigo() + "-" + cp.getTipoOperacion().getDescripcion());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            txtFechaEmision.setText(format.format(nc.getFecha()));
            for (int i = 0; i < cbMotivoNota.getItemCount(); i++) {
                TipoNota tn = (TipoNota) cbMotivoNota.getItemAt(i);
                if (tn.getId() == this.notaCredito.getTipoNota().getId()) {
                    cbMotivoNota.setSelectedItem(tn);
                    break;
                }
            }
            if (this.notaCredito.getMoneda().equals("S")) {
                txtMoneda.setText("SOLES");
                lbTipoMoneda.setText("SOLES");
            } else {
                txtMoneda.setText("DOLARES");
                lbTipoMoneda.setText("DOLARES");
            }
            this.MostrarPantallaDocumentoPago(this.notaCredito.getDocumentoPago());
            txtMotivo.setText(nc.getMotivo());
            btnSeleccionarComprobante.setEnabled(false);
            btnImprimir.setEnabled(true);
//            if (nc.getTipoAplicacion() == 1) {
//                cbTipoAplicacion.setSelectedItem("DEVOLUCIÓN CHEQUE");
//            } else {
//                SeguridadBO sBO = SeguridadBO.getInstance();
//                ComprobantePago cpa = (ComprobantePago) sBO.CargarObjeto("ComprobantePago", nc.getComprobantePagoByIdComprobanteAplicacion().getId());
//                nc.setComprobantePagoByIdComprobanteAplicacion(cpa);
//                TipoDocumento td = (TipoDocumento) sBO.CargarObjeto("TipoDocumento", nc.getComprobantePagoByIdComprobanteAplicacion().getTipoDocumento().getId());
//                nc.getComprobantePagoByIdComprobanteAplicacion().setTipoDocumento(td);
//                String c = nc.getComprobantePagoByIdComprobanteAplicacion().getTipoDocumento().getNombre() + " " + nc.getComprobantePagoByIdComprobanteAplicacion().getNroSerie() + " - " + String.format("%06d", nc.getComprobantePagoByIdComprobanteAplicacion().getNro());
//                txtAplicacionFactura.setText(c);
//                cbTipoAplicacion.setSelectedItem("APLICACIÓN FACTURA");
//            }
//            for (int i = 0; i < cbMotivoNota.getItemCount(); i++) {
//                if (((MotivoNota) cbMotivoNota.getItemAt(i)).getId() == nc.getMotivoNota().getId()) {
//                    cbMotivoNota.setSelectedIndex(i);
//                    break;
//                }
//            }
            NotaBO ncBO = NotaBO.getInstance();
            List<NotaDetalle> ldetalle = ncBO.ObtenerDetalleNota(nc.getId());
            DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
            int contador = 0;
            model.setNumRows(0);
            for (NotaDetalle ncd : ldetalle) {
//                double total = ncd.getCantidad() * ncd.getValorVentaUnitario();
//                total = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
//                double Descuento = total * ncd.getDescuento() / 100;
//                Descuento = Math.round(Descuento * Math.pow(10, 2)) / Math.pow(10, 2);
//                total = total - Descuento;
//                total = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
//                double MontoConIgv = total * (ncd.getIgv() + 100) / 100;
//                MontoConIgv = Math.round(MontoConIgv * Math.pow(10, 2)) / Math.pow(10, 2);
//                double MontConPercepcion = MontoConIgv * ncd.getPercepcion() / 100;
//                MontConPercepcion = Math.round(MontConPercepcion * Math.pow(10, 2)) / Math.pow(10, 2);
                model.addRow(new Object[contador]);
                model.setValueAt(ncd, contador, 0);
                model.setValueAt(contador + 1, contador, 1);
                model.setValueAt(ncd.getDocumentoPagoDet().getConceptoPagoDetalle().getTipoCodigo() + ncd.getDocumentoPagoDet().getConceptoPagoDetalle().getCodigo(), contador, 2);
                model.setValueAt(ncd.getDocumentoPagoDet().getConceptoPagoDetalle(), contador, 3);
//                model.setValueAt(ncd.getComprobantePagoDetalle().getUnidadMedida(), contador, 4);
                model.setValueAt(ncd.getCantidad(), contador, 5);
                model.setValueAt(ncd.getValorVenta(), contador, 6);
                model.setValueAt(ncd.getIgv(), contador, 7);
                model.setValueAt(ncd.getValorVenta() + ncd.getIgv(), contador, 8);
//                model.setValueAt(MontoConIgv + ((ncd.getDescuentoDinero() != null) ? ncd.getDescuentoDinero() : 0.0), contador, 9);
                model.setValueAt(!Objects.isNull(ncd.getMontoDetraccion()) ? ncd.getMontoDetraccion() : 0.0, contador, 10);
//                model.setValueAt(ncd.getComprobantePagoDetalle().getTipoAfectacion(), contador, 11);
//                model.setValueAt(ncd.getComprobantePagoDetalle(), contador, 12);
                contador = contador + 1;
            }
            this.ObtenerMontoTotal();
        }
        if (this.accesoGuardar) {
            btnEliminar.setEnabled(true);
        } else {
            btnEliminar.setEnabled(false);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnMail;
    private javax.swing.JButton btnMail1;
    private javax.swing.JButton btnSeleccionarComprobante;
    private javax.swing.JComboBox cbMotivoNota;
    private javax.swing.JComboBox<String> cbSerie;
    private javax.swing.JComboBox cbTipoNota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMontoDetraccion;
    private javax.swing.JLabel lbMontoTotal;
    private javax.swing.JLabel lbTipoMoneda;
    private javax.swing.JLabel lbTipoMoneda1;
    private javax.swing.JTable tDetalle;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtComprobante;
    private javax.swing.JTextField txtDirección;
    private javax.swing.JTextField txtFechaEmision;
    private javax.swing.JTextField txtMoneda;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNroNota;
    private javax.swing.JTextField txtTipoOperacion;
    // End of variables declaration//GEN-END:variables
}
