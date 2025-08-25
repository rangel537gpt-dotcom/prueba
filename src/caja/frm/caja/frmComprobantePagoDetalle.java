/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm.caja;

import caja.bo.AlumnoBO;
import caja.bo.ClienteBO;
import caja.bo.CongresoBO;
import caja.bo.CuotasBO;
import caja.bo.DeudasBO;
import caja.bo.DocumentoPagoBO;
import caja.bo.EventoBO;
import caja.bo.FinanciamientoBO;
import caja.bo.ManagerBO;
import caja.bo.NumeroLetras;
import caja.bo.ReincorporacionBO;
import caja.bo.SeguridadBO;
import caja.mapeo.entidades.Alumnos;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import caja.frm.frmPrincipal;
import caja.mapeo.entidades.AsignacionEvento;
import caja.mapeo.entidades.CodigoBienServicioDetraccion;
import caja.mapeo.entidades.CodigoMedioPago;
import caja.mapeo.entidades.Congreso;
import caja.mapeo.entidades.EventoAsignacionEventoParticipante;
import caja.mapeo.entidades.EventoConceptoPago;
import caja.mapeo.entidades.Participante;
//import caja.mapeo.entidades.Serie;
import caja.mapeo.entidades.TipoAfectacion;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.mapeo.entidades.TipoTributo;
import caja.mapeo.entidades.Usuario;
import caja.mapeo.entidades.ValeAcademico;
//import caja.mapeo.entidades.CongresoConcepto;
import java.util.List;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileWriter;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
//import net.sf.jasperreports.view.JasperViewer;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author user
 */
public class frmComprobantePagoDetalle extends javax.swing.JInternalFrame {

    private boolean tieneIGV;
    private DocumentoPago documentoPago;
    private String tipoCliente;
    private String glosa;
    private String ruc = "20174327026";
    private int numCuotasFinanciamientoAPagar; //ESTO LO USO PARA SABER ENGANCHAR LAS CUOTAS ORDINARIAS CON LAS CUOTAS DE FINANCIAMIENTO
    private int numCuotasOrdinariasAPagar; //ESTO LO USO PARA SABER ENGANCHAR LAS CUOTAS ORDINARIAS CON LAS CUOTAS DE FINANCIAMIENTO
    private boolean agregarParticipantes = false;
    private EventoConceptoPago eventoConceptoPago;
    private List<Participante> listaParticipantes;
    private List<ValeAcademico> listaVales;

    /**
     * Creates new form frmDetalleComprobantePago
     */
    public frmComprobantePagoDetalle() {
        initComponents();
        TableColumn columna = tDetalle.getColumn("NRO");
        columna.setPreferredWidth(40);
        columna.setMinWidth(40);
        columna.setMaxWidth(40);
        columna = tDetalle.getColumn("PAGO");
        columna.setPreferredWidth(700);
        columna.setMinWidth(700);
        columna.setMaxWidth(700);
        columna = tDetalle.getColumn("CANTIDAD");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tDetalle.getColumn("P. UNITARIO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("TOTAL");
        columna.setPreferredWidth(70);
        columna.setMinWidth(70);
        columna.setMaxWidth(70);
        columna = tDetalle.getColumn("NOMCONCEPTOPAGO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("VALORVENTA");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("IGV");
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna.setPreferredWidth(0);
        columna = tDetalle.getColumn("IDINTERNO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("GLOSA");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("PARTICIPANTES");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("EC");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("VALE_ACADEMICO");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("DETRACCION");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("PORCENTAJE_DETRACCION");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna = tDetalle.getColumn("CODIGO_DETRACCION");
        columna.setPreferredWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
    }

    public void CargarDatos(DocumentoPago doc, String pTipoPagador, int tipoCargarDatos) {
        SeguridadBO sBO = SeguridadBO.getInstance();
        this.tipoCliente = pTipoPagador;
        this.glosa = "";
        this.documentoPago = doc;
        lbCodColegiado.setText("");
        lbNroComprobante.setText("");
        String tituloComprobante = "Comprobante de Pago ";
        if (!Objects.isNull(doc.getTipoPago())) {
            if (doc.getTipoPago().equals("CON")) {
                tituloComprobante = tituloComprobante + " - Tipo Pago (Contado)";
            } else {
                if (doc.getTipoPago().equals("CRE")) {
                    tituloComprobante = tituloComprobante + " - Tipo Pago (Credito)";
                } else {
                }
            }
        }
        if (!Objects.isNull(doc.getCodigoMedioPago())) {
            ManagerBO mgBO = ManagerBO.getInstance();
            CodigoMedioPago cmp = mgBO.obtenerCodigoMedioPago(doc.getCodigoMedioPago());
            tituloComprobante = tituloComprobante + (!Objects.isNull(cmp) ? " - Medio Pago (" + cmp.getCodigo() + " - " + cmp.getDescripcion() + ")" : "");
        }
        this.title = tituloComprobante;
        if (tipoCliente.equals("C")) {
            lbNombre.setText(doc.getCliente().getPapePat() + " " + doc.getCliente().getPapeMat() + " " + doc.getCliente().getPnombre());
            lbCodigo.setText("COD.:");
            if (doc.getCliente().getCcodigoCole() != null) {
                lbNroCodigo.setText(doc.getCliente().getCcodigoCole());
            }
            if (doc.getCliente().getPdireccionDomicilio() != null) {
                if (!doc.getCliente().getPdireccionDomicilio().equals("null")) {
                    lbDireccion.setText(doc.getCliente().getPdireccionDomicilio());
                } else {
                    lbDireccion.setText("");
                }
            } else {
                lbDireccion.setText("");
            }
        }
        if (tipoCliente.equals("S")) {
            lbNombre.setText(doc.getCliente().getSnombreSociedad());
            lbCodigo.setText("COD.:");
            if (doc.getCliente().getScodigoSoc() != null) {
                lbNroCodigo.setText(doc.getCliente().getScodigoSoc());
            }
            if (doc.getCliente().getSdireccion() != null) {
                if (!doc.getCliente().getSdireccion().equals("null")) {
                    lbDireccion.setText(doc.getCliente().getSdireccion());
                } else {
                    lbDireccion.setText("");
                }
            } else {
                lbDireccion.setText("");
            }
        }
        if (tipoCliente.equals("E")) {
            lbNombre.setText(doc.getNombreCliente());
            lbCodigo.setText("RUC:");
            if (doc.getCliente().getEruc() != null) {
                lbNroCodigo.setText(doc.getCliente().getEruc());
            }
            if (doc.getCliente() != null) {
                if (doc.getCliente().getEdireccion() != null) {
                    if (!doc.getCliente().getEdireccion().equals("null")) {
                        lbDireccion.setText(doc.getCliente().getEdireccion());
                    } else {
                        lbDireccion.setText("");
                    }
                } else {
                    lbDireccion.setText("");
                }
            } else {
                lbDireccion.setText("");
            }
            if (doc.getClienteByIdContadorEmpresa() != null) {
//                SeguridadBO sBO = SeguridadBO.getInstance();
                Cliente c = (Cliente) sBO.CargarObjeto("Cliente", doc.getClienteByIdContadorEmpresa().getIdCliente());
                lbCodColegiado.setText("COLEGIADO: " + c.getCcodigoCole() + " - " + c.getPapePat() + " " + c.getPapeMat() + " " + c.getPnombre());
            }
        }
        if (tipoCliente.equals("P")) {
            //lbNombre.setText(doc.getCliente().getPapePat() + " " + doc.getCliente().getPapeMat() + " " + doc.getCliente().getPnombre());
            lbNombre.setText(doc.getNombreCliente());
            lbCodigo.setText("DNI:");
            if (doc.getCliente().getPnroDocumento() != null) {
                lbNroCodigo.setText(doc.getCliente().getPnroDocumento());
            }
            if (doc.getCliente() != null) {
                if (doc.getCliente().getPdireccionDomicilio() != null) {
                    if (!doc.getCliente().getPdireccionDomicilio().equals("null")) {
                        lbDireccion.setText(doc.getCliente().getPdireccionDomicilio());
                    } else {
                        lbDireccion.setText("");
                    }
                } else {
                    lbDireccion.setText("");
                }
            } else {
                lbDireccion.setText("");
            }
        }
        if (doc.getCobrador() != null) {
            lbCobrador.setText(doc.getCobrador().getCliente().getPapePat() + " " + doc.getCobrador().getCliente().getPapeMat() + " " + doc.getCobrador().getCliente().getPnombre());
        } else {
            lbCobrador.setText("");
        }
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        lbFecha.setText(formateador.format(doc.getFechaPago()));
        if (doc.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
            if (doc.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                lbNroComprobante.setText(doc.getTipoDocSerie().getTipoDocPago().getAbreviatura() + " NRO: " + doc.getNroSerie() + " - " + String.format("%07d", doc.getNroDocumentoPago()));
            } else {
                lbNroComprobante.setText(doc.getTipoDocSerie().getTipoDocPago().getAbreviatura() + " NRO: " + doc.getNroSerie() + " - " + String.format("%07d", doc.getNroDocumentoPago()));
            }
        } else {
            lbNroComprobante.setText(doc.getTipoDocSerie().getTipoDocPago().getAbreviatura() + " NRO: " + doc.getNroSerie() + " - " + String.format("%07d", doc.getNroDocumentoPago()));
        }
        if (doc.getMotivoAnulacion() != null) {
            lbMotivoAnulacion.setText(doc.getMotivoAnulacion());
        } else {
            lbMotivoAnulacion.setText("");
        }
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        if (doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1) {
            this.tieneIGV = true;
        } else {
            this.tieneIGV = false;
        }
        this.listaVales = new ArrayList<>();
        if (tipoCargarDatos == 1) //CARGAR NUEVO COMPROBANTE
        {
            btnAprobarComprobante.setEnabled(true);
            btnImprimir.setEnabled(false);
        }
        if (tipoCargarDatos == 2) //CARGAR COMPROBANTE
        {
            List<DocumentoPagoDet> lDetalle = dpBO.ObtenerDetalleComprobante(doc.getIdDocumentoPago());
            DefaultTableModel modelDetalle = (DefaultTableModel) tDetalle.getModel();
            int contador = 0;
            double precioVenta = 0;
            double igv = 0;
            double valorVenta = 0;
            double detraccion = 0;
            for (DocumentoPagoDet dpd : lDetalle) {
                modelDetalle.setNumRows(contador);
                modelDetalle.addRow(new Object[contador]);
                tDetalle.setValueAt(contador + 1, contador, 0);
                if (dpd.getObservacion() == null) {
                    tDetalle.setValueAt(dpd.getConceptoPagoDetalle().getDescripcion(), contador, 1);
                } else {
                    if (dpd.getObservacion().isEmpty() || dpd.getObservacion().equals("null")) {
                        tDetalle.setValueAt(dpd.getConceptoPagoDetalle().getDescripcion(), contador, 1);
                    } else {
                        tDetalle.setValueAt(dpd.getConceptoPagoDetalle().getDescripcion() + " (" + dpd.getObservacion() + ")", contador, 1);
                    }
                }
                tDetalle.setValueAt(dpd.getCantidad(), contador, 2);
                tDetalle.setValueAt(dpd.getPrecioUnitario(), contador, 3);
                tDetalle.setValueAt(dpd.getValorVenta() + dpd.getIgv(), contador, 4);
                precioVenta = precioVenta + dpd.getValorVenta() + dpd.getIgv();
                igv = igv + dpd.getIgv();
                valorVenta = valorVenta + dpd.getValorVenta();
                detraccion = detraccion + (!Objects.isNull(dpd.getMontoDetraccion()) ? dpd.getMontoDetraccion() : 0.0);
                contador = contador + 1;
            }
            precioVenta = Math.round(precioVenta * Math.pow(10, 2)) / Math.pow(10, 2);
            valorVenta = Math.round(valorVenta * Math.pow(10, 2)) / Math.pow(10, 2);
            igv = Math.round(igv * Math.pow(10, 2)) / Math.pow(10, 2);
            lbSubTotal.setText(String.valueOf(valorVenta));
            lbIgv.setText(String.valueOf(igv));
            lbTotal.setText(String.valueOf(precioVenta));
            lbDetraccion.setText(String.valueOf(detraccion));

            if (doc.getEstado().equals("C")) {
                btnAgregar.setEnabled(false);
                btnAprobarComprobante.setEnabled(false);
                btnImprimir.setEnabled(true);
                lbEstado.setText("");
                lbEstado1.setText("");
                btnAnular.setEnabled(true);
            }
            if (doc.getEstado().equals("A")) {
                btnAgregar.setEnabled(true);
                btnAprobarComprobante.setEnabled(true);
                btnImprimir.setEnabled(false);
                lbEstado.setText("");
                lbEstado1.setText("");
                btnAnular.setEnabled(true);
            }
            if (doc.getEstado().equals("ANULADO")) {
                btnAgregar.setEnabled(false);
                btnAprobarComprobante.setEnabled(false);
                btnImprimir.setEnabled(false);
                //lbEstado.setText("ANULADO");
                lbEstado1.setText("ANULADO");
                btnAnular.setEnabled(false);
            }
        }
        if (doc.getMoneda().toString().equals("D")) {
            jLabel7.setText("TOTAL: $/.");
            jLabel2.setText("IGV: $/.");
            jLabel5.setText("SUBTOTAL: $/.");
        } else {
            jLabel7.setText("TOTAL: S/.");
            jLabel2.setText("IGV: S/.");
            jLabel5.setText("SUBTOTAL: S/.");
        }
        cbCodigo.requestFocus();
        Usuario u = sBO.getUsuario();
        if (u.getRolUsuario().equals("USUARIO")) {
            btnNuevoComprobante.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnAnular.setEnabled(false);
            btnAprobarComprobante.setEnabled(false);
            tDetalle.setEnabled(false);
            btnAgregar.setEnabled(false);
        }
    }

    public void AgregarGlosa(String nroDocumento) {
        this.glosa = nroDocumento;
    }

    public void agregarParticipantes(EventoConceptoPago ec, List<Participante> l) {
        ((JTextField) cbCodigo.getEditor().getEditorComponent()).setText(ec.getConceptoPagoDetalle().getTipoCodigo() + ec.getConceptoPagoDetalle().getCodigo());
        ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).setText(ec.getConceptoPagoDetalle().getDescripcion());
        txtCantidad.setText(String.valueOf(l.size()));
        txtTotal.setText(String.valueOf(l.size() * ec.getMonto()));
        this.agregarParticipantes = true;
        this.eventoConceptoPago = ec;
        this.listaParticipantes = l;
//        this.InsertarDetalle();
//        tDetalle.setValueAt(l, tDetalle.getRowCount() - 1, 10);
//        tDetalle.setValueAt(ec, tDetalle.getRowCount() - 1, 11);
    }

    private void BuscarConceptoPago() {
        String txt = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
        txt = txt.toUpperCase();
        cbConceptoPago.removeAllItems();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lConceptos = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle concepto : lConceptos) {
            if (concepto.getDescripcion().contains(txt)) {
                cbConceptoPago.addItem(concepto.getDescripcion());
            }
        }
        cbConceptoPago.setSelectedIndex(-1);
        cbConceptoPago.hidePopup();
        cbConceptoPago.showPopup();
        ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).setText(txt);
    }

    private void BuscarConceptoPagoCodigo() {
        String txt = ((JTextField) cbCodigo.getEditor().getEditorComponent()).getText();
        txt = txt.toUpperCase();
        cbCodigo.removeAllItems();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lConceptos = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle concepto : lConceptos) {
            String codigo = concepto.getTipoCodigo() + concepto.getCodigo();
            if (codigo.contains(txt)) {
                cbCodigo.addItem(codigo);
            }
        }
        cbCodigo.setSelectedIndex(-1);
        cbCodigo.hidePopup();
        cbCodigo.showPopup();
        ((JTextField) cbCodigo.getEditor().getEditorComponent()).setText(txt);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNroComprobante = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbDireccion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbCobrador = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbConceptoPago = new javax.swing.JComboBox();
        cbConceptoPago.getEditor().getEditorComponent().addKeyListener(
            new KeyAdapter(){
                //@Override
                public void keyReleased (KeyEvent e){
                    if (e.getKeyCode() < 37 || e.getKeyCode() > 40 ) {
                        if( e.getKeyCode() !=KeyEvent.VK_ENTER )
                        {
                            BuscarConceptoPago();
                        }
                        else
                        {
                            String txt = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
                            if( !txt.isEmpty() ){
                                ValidarConcepto();
                            }
                        }
                    }
                }} );
                jLabel11 = new javax.swing.JLabel();
                txtCantidad = new javax.swing.JTextField();
                jLabel12 = new javax.swing.JLabel();
                txtTotal = new javax.swing.JTextField();
                btnAgregar = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                tDetalle = new javax.swing.JTable();
                lbTotal = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                lbIgv = new javax.swing.JLabel();
                lbSubTotal = new javax.swing.JLabel();
                btnAprobarComprobante = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                btnEliminar = new javax.swing.JButton();
                cbCodigo = new javax.swing.JComboBox();
                cbCodigo.getEditor().getEditorComponent().addKeyListener(
                    new KeyAdapter(){
                        //@Override
                        public void keyReleased (KeyEvent e){
                            if (e.getKeyCode() < 37 || e.getKeyCode() > 40 ) {
                                if( e.getKeyCode() !=KeyEvent.VK_ENTER )
                                {
                                    BuscarConceptoPagoCodigo();
                                }
                                else
                                {
                                    String txt = ((JTextField) cbCodigo.getEditor().getEditorComponent()).getText();
                                    if( !txt.isEmpty() ){
                                        cbConceptoPago.requestFocus();
                                        //ValidarConcepto();
                                    }
                                }
                            }
                        }} );
                        btnAnular = new javax.swing.JButton();
                        lbEstado = new javax.swing.JLabel();
                        lbCodigo = new javax.swing.JLabel();
                        lbNroCodigo = new javax.swing.JLabel();
                        btnGlosa = new javax.swing.JButton();
                        btnNuevoComprobante = new javax.swing.JButton();
                        lbEstado1 = new javax.swing.JLabel();
                        lbCodColegiado = new javax.swing.JLabel();
                        btnMail = new javax.swing.JButton();
                        lbMotivoAnulacion = new javax.swing.JLabel();
                        jButton1 = new javax.swing.JButton();
                        btnMail1 = new javax.swing.JButton();
                        lbDetraccion = new javax.swing.JLabel();
                        jLabel9 = new javax.swing.JLabel();

                        setClosable(true);
                        setIconifiable(true);
                        setTitle("COMPROBANTE DE PAGO");
                        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/icono.png"))); // NOI18N

                        lbNroComprobante.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
                        lbNroComprobante.setText("BOLETA DE VENTA - NRO. 0006");

                        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
                        jLabel3.setText("FECHA:");

                        lbFecha.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
                        lbFecha.setForeground(new java.awt.Color(0, 102, 204));
                        lbFecha.setText("2015-07-17");

                        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        jLabel4.setText("NOMBRE:");

                        lbNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        lbNombre.setForeground(new java.awt.Color(0, 102, 204));
                        lbNombre.setText("ROLANDO ANGEL CACERES LAURA - COD 1077");
                        lbNombre.setPreferredSize(new java.awt.Dimension(504, 17));

                        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        jLabel6.setText("DIRECCIÓN:");

                        lbDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbDireccion.setForeground(new java.awt.Color(0, 102, 204));
                        lbDireccion.setText("URB LAS ORQUIDEAS 153");

                        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        jLabel8.setText("COBRADOR:");

                        lbCobrador.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbCobrador.setForeground(new java.awt.Color(0, 102, 204));
                        lbCobrador.setText("ROBERTO ANDRES TORRES CARBAJAL");

                        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/IMPRESORA.png"))); // NOI18N
                        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnImprimirActionPerformed(evt);
                            }
                        });

                        jLabel10.setText("PAGO:");

                        cbConceptoPago.setEditable(true);
                        cbConceptoPago.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbConceptoPagoItemStateChanged(evt);
                            }
                        });

                        jLabel11.setText("CANT:");

                        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtCantidadKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtCantidadKeyTyped(evt);
                            }
                        });

                        jLabel12.setText("TOTAL:");

                        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtTotalKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtTotalKeyTyped(evt);
                            }
                        });

                        btnAgregar.setText("AGREGAR");
                        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarActionPerformed(evt);
                            }
                        });

                        tDetalle.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "NRO", "PAGO", "CANTIDAD", "P. UNITARIO", "TOTAL", "NOMCONCEPTOPAGO", "VALORVENTA", "IGV", "IDINTERNO", "GLOSA", "PARTICIPANTES", "EC", "VALE_ACADEMICO", "DETRACCION", "PORCENTAJE_DETRACCION", "CODIGO_DETRACCION"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tDetalleKeyPressed(evt);
                            }
                        });
                        jScrollPane1.setViewportView(tDetalle);

                        lbTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbTotal.setForeground(new java.awt.Color(0, 102, 204));
                        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        lbTotal.setText("0.00");

                        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        jLabel2.setText("IGV: S/.");

                        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        jLabel5.setText("SUBTOTAL: S/.");

                        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        jLabel7.setText("TOTAL: S/.");

                        lbIgv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbIgv.setForeground(new java.awt.Color(0, 102, 204));
                        lbIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        lbIgv.setText("0.00");

                        lbSubTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbSubTotal.setForeground(new java.awt.Color(0, 102, 204));
                        lbSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        lbSubTotal.setText("0.00");

                        btnAprobarComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/aceptar.PNG"))); // NOI18N
                        btnAprobarComprobante.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAprobarComprobanteActionPerformed(evt);
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
                            .addGap(0, 57, Short.MAX_VALUE)
                        );

                        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/eliminar1.png"))); // NOI18N
                        btnEliminar.setToolTipText("ELIMINAR");
                        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEliminarActionPerformed(evt);
                            }
                        });

                        cbCodigo.setEditable(true);
                        cbCodigo.addContainerListener(new java.awt.event.ContainerAdapter() {
                            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                                cbCodigoComponentRemoved(evt);
                            }
                        });
                        cbCodigo.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbCodigoItemStateChanged(evt);
                            }
                        });

                        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/anular.png"))); // NOI18N
                        btnAnular.setToolTipText("ANULAR");
                        btnAnular.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAnularActionPerformed(evt);
                            }
                        });

                        lbEstado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        lbEstado.setForeground(new java.awt.Color(204, 0, 0));

                        lbCodigo.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
                        lbCodigo.setText("RUC:");

                        lbNroCodigo.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
                        lbNroCodigo.setForeground(new java.awt.Color(0, 102, 204));
                        lbNroCodigo.setText("...");

                        btnGlosa.setText("...");
                        btnGlosa.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGlosaActionPerformed(evt);
                            }
                        });

                        btnNuevoComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/Nuevo Comprobante 2.png"))); // NOI18N
                        btnNuevoComprobante.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnNuevoComprobanteActionPerformed(evt);
                            }
                        });

                        lbEstado1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        lbEstado1.setForeground(new java.awt.Color(204, 0, 0));
                        lbEstado1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

                        lbCodColegiado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbCodColegiado.setForeground(new java.awt.Color(0, 102, 204));
                        lbCodColegiado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        lbCodColegiado.setText("ROBERTO ANDRES TORRES CARBAJAL");

                        btnMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/mail01.png"))); // NOI18N
                        btnMail.setToolTipText("ENVIAR E-MAIL");
                        btnMail.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnMailActionPerformed(evt);
                            }
                        });

                        lbMotivoAnulacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbMotivoAnulacion.setForeground(new java.awt.Color(255, 51, 102));
                        lbMotivoAnulacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        lbMotivoAnulacion.setText("ROBERTO ANDRES TORRES CARBAJAL");

                        jButton1.setText("...");
                        jButton1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton1ActionPerformed(evt);
                            }
                        });

                        btnMail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/generar.png"))); // NOI18N
                        btnMail1.setToolTipText("GENERAR ARCHIVO ELECTRONICO");
                        btnMail1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnMail1ActionPerformed(evt);
                            }
                        });

                        lbDetraccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lbDetraccion.setForeground(new java.awt.Color(0, 102, 204));
                        lbDetraccion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        lbDetraccion.setText("0.00");

                        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        jLabel9.setText("DETRACCIÓN: S/.");

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbNroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbFecha)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbCodigo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbNroCodigo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbEstado))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(83, 83, 83))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbConceptoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGlosa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAgregar)
                                        .addGap(12, 12, 12))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(lbDetraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lbSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lbIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnMail1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnMail, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnNuevoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAprobarComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lbCodColegiado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addContainerGap())
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbMotivoAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lbNroComprobante)
                                                .addComponent(jLabel3)
                                                .addComponent(lbFecha)
                                                .addComponent(lbCodigo)
                                                .addComponent(lbNroCodigo))
                                            .addComponent(lbEstado))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(lbDireccion))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(lbCobrador)
                                            .addComponent(lbEstado1))
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(cbConceptoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12)
                                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAgregar)
                                            .addComponent(cbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnGlosa))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbSubTotal)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(lbIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbTotal)
                                                    .addComponent(jLabel7))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lbDetraccion)
                                                    .addComponent(jLabel9)))
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnMail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnNuevoComprobante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAnular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAprobarComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnImprimir)
                                                .addComponent(btnMail1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbCodColegiado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                        .addComponent(lbMotivoAnulacion))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton1))))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private String ObtenerMes(int mes) {
        String pmes = "";
        if (mes == 1) {
            pmes = "ENE";
        }
        if (mes == 2) {
            pmes = "FEB";
        }
        if (mes == 3) {
            pmes = "MAR";
        }
        if (mes == 4) {
            pmes = "ABR";
        }
        if (mes == 5) {
            pmes = "MAY";
        }
        if (mes == 6) {
            pmes = "JUN";
        }
        if (mes == 7) {
            pmes = "JUL";
        }
        if (mes == 8) {
            pmes = "AGO";
        }
        if (mes == 9) {
            pmes = "SEP";
        }
        if (mes == 10) {
            pmes = "OCT";
        }
        if (mes == 11) {
            pmes = "NOV";
        }
        if (mes == 12) {
            pmes = "DIC";
        }
        return pmes;
    }

    private String ObtenerRangoFecha(ConceptoPagoDetalle conceptoDetalle) {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        //int cantidad = Integer.valueOf(txtCantidad.getText());
        String rangoFecha = "";
        if ((conceptoDetalle.getIdConceptoPagoDetalle() == 1 || conceptoDetalle.getIdConceptoPagoDetalle() == 7220) && (this.tipoCliente.equals("C") || this.tipoCliente.equals("S") || this.tipoCliente.equals("E"))) {
            List<Object> listaCuotasAPagar = dpBO.getCuotasAPagar();
            if (listaCuotasAPagar.size() > 0) {
                Object[] cuotaDesde = (Object[]) listaCuotasAPagar.get(0);
                Object[] cuotaHasta = (Object[]) listaCuotasAPagar.get(listaCuotasAPagar.size() - 1);
                rangoFecha = " " + ObtenerMes((int) cuotaDesde[3]) + " " + (int) cuotaDesde[2] + " A " + ObtenerMes((int) cuotaHasta[3]) + " " + (int) cuotaHasta[2];
            }
        }
        return rangoFecha;
    }

    private void InsertarDetalle() {
        if (!cbConceptoPago.getSelectedItem().toString().isEmpty() && !txtCantidad.getText().isEmpty() && !txtTotal.getText().isEmpty()) {
            int cantidad = Integer.valueOf(txtCantidad.getText());
            double total = Double.valueOf(txtTotal.getText());
            if (cantidad == 0 || total == 0) {
                JOptionPane.showMessageDialog(this,
                        "LA CANTIDAD Y/O PRECIO TIENE QUE SER MAYOR QUE CERO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nomDetalle = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
            DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 5).toString().equals(nomDetalle)) {
                    JOptionPane.showMessageDialog(this,
                            "EL CONCEPTO YA FUE INGRESADO ANTERIORMENTE",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            ConceptoPagoDetalle conceptoDetalle = null;
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            List<ConceptoPagoDetalle> lconceptoDetalle = dpBO.ObtenerTodosConceptoPago();
            for (ConceptoPagoDetalle cDetalle : lconceptoDetalle) {
                if (cDetalle.getDescripcion().equals(nomDetalle)) {
                    conceptoDetalle = cDetalle;
                    break;
                }
            }
            if (conceptoDetalle == null) {
                JOptionPane.showMessageDialog(this,
                        "DEBE ELEGIR UN CONCEPTO DE PAGO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (conceptoDetalle.getIdConceptoPagoDetalle() == 3939) {
                if (!this.documentoPago.getCliente().getEstado().equals("H")) {
                    JOptionPane.showMessageDialog(this,
                            "EL COLEGIADO NO SE ENCUETRA HABILITADO PARA GENERAR LA CONSTANCIA DE HABILITACIÓN",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (this.documentoPago.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 3) {
                if (conceptoDetalle.getTieneIgv() != null) {
                    if (conceptoDetalle.getTieneIgv().equals("S")) {
                        JOptionPane.showMessageDialog(this,
                                "LOS RECIBOS DE INGRESO NO PUEDEN TENER CONCEPTOS CON IGV",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            if (this.documentoPago.getTieneDetraccion().equals("S")) {
                SeguridadBO sBO = SeguridadBO.getINSTANCE();
                CodigoBienServicioDetraccion cbsd = !Objects.isNull(conceptoDetalle.getCodigoBienServicioDetraccion()) ? (CodigoBienServicioDetraccion) sBO.CargarObjeto("CodigoBienServicioDetraccion", conceptoDetalle.getCodigoBienServicioDetraccion().getId()) : null;
                if (Objects.isNull(cbsd)) {
                    JOptionPane.showMessageDialog(this,
                            "El concepto no tiene codigo de detracción",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                conceptoDetalle.setCodigoBienServicioDetraccion(cbsd);
                if (!Objects.isNull(conceptoDetalle.getPorcentajeDetraccion())) {
                    if (conceptoDetalle.getPorcentajeDetraccion() > 0) {
                        //Validar si tiene el mismo porcentaje
                        for (int i = 0; i < model.getRowCount(); i++) {
                            if (!Objects.equals((Double) model.getValueAt(i, 14), conceptoDetalle.getPorcentajeDetraccion())) {
                                JOptionPane.showMessageDialog(this,
                                        "EL CONCEPTO TIENE PORCENTAJE (" + conceptoDetalle.getPorcentajeDetraccion() + " %) DISTINTO A LOS CONCEPTOS INGRESADOS (" + (Double) model.getValueAt(i, 14) + " %)",
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "EL CONCEPTO TIENE QUE TENER DETRACCION PARA PODER FIGURAR EN LA PRESENTE FACTURA",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "EL CONCEPTO TIENEN QUE TENER DETRACCION PARA PODER FIGURAR EN LA PRESENTE FACTURA",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!Objects.isNull(conceptoDetalle.getCodigoBienServicioDetraccion())) {
                    if (!conceptoDetalle.getCodigoBienServicioDetraccion().getCodigo().isEmpty()) {
                        //Validar si tiene el mismo codigo
                        for (int i = 0; i < model.getRowCount(); i++) {
                            if (!Objects.equals((String) model.getValueAt(i, 15), conceptoDetalle.getCodigoBienServicioDetraccion().getCodigo())) {
                                JOptionPane.showMessageDialog(this,
                                        "EL CONCEPTO TIENE CODIGO DETRACCIÓN (" + conceptoDetalle.getCodigoBienServicioDetraccion().getCodigo() + " ) DISTINTO A LOS CONCEPTOS INGRESADOS (" + (String) model.getValueAt(i, 15) + " )",
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "EL CONCEPTO TIENE QUE TENER DETRACCION PARA PODER FIGURAR EN LA PRESENTE FACTURA",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "EL CONCEPTO TIENEN QUE TENER DETRACCION PARA PODER FIGURAR EN LA PRESENTE FACTURA",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String rangoFecha = "";
            int idInterno = this.ObtenerIdInterno();
            if (conceptoDetalle.getIdConceptoPagoDetalle() == 1 || conceptoDetalle.getIdConceptoPagoDetalle() == 7220) {
                this.VisualizarIngresoCuota(conceptoDetalle, cantidad, total, rangoFecha);
            } else {
                if (conceptoDetalle.getIdConceptoPagoDetalle() == 10) {
                    this.VisualizarIngresoFinanciamiento(conceptoDetalle, cantidad, total, rangoFecha);
                } else {
                    if (conceptoDetalle.getIdConceptoPagoDetalle() == 5850) {
                        this.VisualizarIngresoReincorporacion(conceptoDetalle, cantidad, total, rangoFecha);
                    } else {
                        this.VizualizarIngresoDetalle(conceptoDetalle, cantidad, total, rangoFecha, idInterno);
                    }
                }
            }
            ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).setText("");
            txtCantidad.setEnabled(true);
            txtTotal.setEnabled(true);
            txtCantidad.setText("");
            txtTotal.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "DEBE LLENAR LOS CAMPOS",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void VerificarIngresoCongreso(DocumentoPagoDet detalle, EventoConceptoPago ec, List<Participante> l) {
        CongresoBO cBO = CongresoBO.getInstance();
        List<Congreso> lCongreso = cBO.VerificarSiConceptoPerteneceCongreso(detalle.getConceptoPagoDetalle().getIdConceptoPagoDetalle());
        if (!lCongreso.isEmpty()) {
            frmPrincipal f = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            if (detalle.getDocumentoPago().getCliente().getTipoCliente().equals("C") || detalle.getDocumentoPago().getCliente().getTipoCliente().equals("P")) {
                f.AbrirFormularioCongresoIndividual(this, detalle, lCongreso);
            } else {
                f.AbrirFormularioCongresoVariasPersonas(this, detalle, lCongreso);
            }
        } else {
            if (detalle.getConceptoPagoDetalle().getTipoCodigo().equals("04")) {
                if (l.size() > 0) {
                    EventoBO eBO = EventoBO.getInstance();
                    for (Participante p : l) {
                        p.setDocumentoPagoDet(detalle);
                        p.setInversion(ec.getInversion());
                        p.setEvento(ec.getInversion().getEvento());
                        p.setObservacion("");
                        p.setBorrado("1");//se pone de prueba
                        eBO.GuardarParticipante(p);
                        List<AsignacionEvento> lAE = eBO.obtenerAsignacionEvento_SegunIdEvento(ec.getInversion().getEvento().getId());
                        for (AsignacionEvento ae : lAE) {
                            EventoAsignacionEventoParticipante eaep = new EventoAsignacionEventoParticipante();
                            eaep.setBorrado("1");
                            eaep.setAsignacionEvento(ae);
                            eaep.setEvento(ec.getInversion().getEvento());
                            eaep.setNota(0);
                            eaep.setParticipante(p);
                            eBO.GuardarObjeto(eaep);
                        }
                    }
                } else {
                    this.ingresarAlumno(this.documentoPago, detalle);
                }
            }
        }

    }

    public int ObtenerIdInterno() {
        /*ESTE ID ES PARA PODER ELIMINAR LOS ELEMENTOS DE LA TABLA, EN CASO DE FINANCIAMIENTO Y REINCORPORACIONES*/
        int idInterno = 0;
        DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
        idInterno = model.getRowCount() + 1;
        return idInterno;
    }

    public void VisualizarIngresoCuota(ConceptoPagoDetalle conceptoDetalle, int cantidad, double precio, String rangoFecha) {
        int idInterno = this.ObtenerIdInterno();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        rangoFecha = this.ObtenerRangoFecha(conceptoDetalle);
        if (rangoFecha.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "NO SE GUARDO EL DETALLE",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        precio = 0;
        List<Object> listaCuotasAPagar = dpBO.getCuotasAPagar();
        for (Object pobj : listaCuotasAPagar) {
            Object[] obj = (Object[]) pobj;
            precio = precio + (double) obj[4];
        }
        this.VizualizarIngresoDetalle(conceptoDetalle, cantidad, precio, rangoFecha, idInterno);

        ConceptoPagoDetalle concepto = null;
        List<ConceptoPagoDetalle> lconceptoDetalle = dpBO.ObtenerTodosConceptoPago();
        precio = 0;
        List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = dpBO.getFinanciamientoAPagar();
        rangoFecha = "(";
        int nroCuotas = 1;
        if (listaFinanciamientoAPagar.size() > 0) {
            for (ConceptoPagoDetalle cDetalle : lconceptoDetalle) {
                if (cDetalle.getIdConceptoPagoDetalle() == 10) {
                    concepto = cDetalle;
                    break;
                }
            }
            for (FinanciamientoDocumentoPago fpd : listaFinanciamientoAPagar) {
                precio = precio + fpd.getMonto();
                if (nroCuotas == 1) {
                    rangoFecha = rangoFecha + " CUOTA " + fpd.getNroCuota();
                } else {
                    rangoFecha = rangoFecha + "," + fpd.getNroCuota();
                }
                nroCuotas = nroCuotas + 1;
            }
            rangoFecha = rangoFecha + ")";
            if (listaFinanciamientoAPagar != null) {
                if (!listaFinanciamientoAPagar.isEmpty()) {
                    this.VizualizarIngresoDetalle(concepto, cantidad, precio, rangoFecha, idInterno);
                }
            }
        }
        /*-------------------INGRESANDO REINCORPORACIÓN (INICIO)----------------*/
        precio = 0;
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = dpBO.getReincorporacionAPagar();
        rangoFecha = "(";
        nroCuotas = 1;
        if (listaReincorporacionAPagar.size() > 0) {
            for (ConceptoPagoDetalle cDetalle : lconceptoDetalle) {
                if (cDetalle.getIdConceptoPagoDetalle() == 5850) {
                    concepto = cDetalle;
                    break;
                }
            }
            for (ReincorporacionDocumentoPago rpd : listaReincorporacionAPagar) {
                precio = precio + rpd.getMontoFondo() + rpd.getMontoOtros();
                if (nroCuotas == 1) {
                    rangoFecha = rangoFecha + " CUOTA " + rpd.getNroCuota();
                } else {
                    rangoFecha = rangoFecha + "," + rpd.getNroCuota();
                }
                nroCuotas = nroCuotas + 1;
            }
            rangoFecha = rangoFecha + ")";
            if (listaReincorporacionAPagar != null) {
                if (!listaReincorporacionAPagar.isEmpty()) {
                    this.VizualizarIngresoDetalle(concepto, cantidad, precio, rangoFecha, idInterno);
                }
            }
        }
        /*-------------------INGRESANDO REINCORPORACIÓN (FIN)----------------*/
    }

    public void VisualizarIngresoFinanciamiento(ConceptoPagoDetalle conceptoDetalle, int cantidad, double precio, String rangoFecha) {
        int idInterno = this.ObtenerIdInterno();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Object> listaCuotasAPagar = dpBO.getCuotasAPagar();
        precio = 0;
        rangoFecha = "(";
        List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = dpBO.getFinanciamientoAPagar();
        int nroCuotas = 1;
        for (FinanciamientoDocumentoPago fpd : listaFinanciamientoAPagar) {
            precio = precio + fpd.getMonto();
            if (nroCuotas == 1) {
                rangoFecha = rangoFecha + " CUOTA " + fpd.getNroCuota();
            } else {
                rangoFecha = rangoFecha + "," + fpd.getNroCuota();
            }
            nroCuotas = nroCuotas + 1;
        }
        rangoFecha = rangoFecha + ")";
        this.VizualizarIngresoDetalle(conceptoDetalle, cantidad, precio, rangoFecha, idInterno);
    }

    public void VisualizarIngresoReincorporacion(ConceptoPagoDetalle conceptoDetalle, int cantidad, double precio, String rangoFecha) {
        int idInterno = this.ObtenerIdInterno();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Object> listaCuotasAPagar = dpBO.getCuotasAPagar();
        precio = 0;
        rangoFecha = "(";
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = dpBO.getReincorporacionAPagar();
        int nroCuotas = 1;
        for (ReincorporacionDocumentoPago rpd : listaReincorporacionAPagar) {
            precio = precio + rpd.getMontoFondo() + rpd.getMontoOtros();
            if (nroCuotas == 1) {
                rangoFecha = rangoFecha + " CUOTA " + rpd.getNroCuota();
            } else {
                rangoFecha = rangoFecha + "," + rpd.getNroCuota();
            }
            nroCuotas = nroCuotas + 1;
        }
        rangoFecha = rangoFecha + ")";
        this.VizualizarIngresoDetalle(conceptoDetalle, cantidad, precio, rangoFecha, idInterno);
        ConceptoPagoDetalle concepto = null;
        List<ConceptoPagoDetalle> lconceptoDetalle = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle cDetalle : lconceptoDetalle) {
            if (cDetalle.getIdConceptoPagoDetalle() == 1) {
                concepto = cDetalle;
                break;
            }
        }
        rangoFecha = "";
        rangoFecha = this.ObtenerRangoFecha(concepto);
        if (rangoFecha.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "NO SE GUARDO EL DETALLE",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        precio = 0;
        for (Object pobj : listaCuotasAPagar) {
            Object[] obj = (Object[]) pobj;
            precio = precio + (double) obj[4];
        }
        this.VizualizarIngresoDetalle(concepto, cantidad, precio, rangoFecha, idInterno);
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        this.InsertarDetalle();
        if (this.agregarParticipantes) {
            tDetalle.setValueAt(this.listaParticipantes, tDetalle.getRowCount() - 1, 10);
            tDetalle.setValueAt(this.eventoConceptoPago, tDetalle.getRowCount() - 1, 11);
            this.agregarParticipantes = false;
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void VizualizarIngresoDetalle(ConceptoPagoDetalle c, int cantidad, double montoTotal, String rangoFecha, int idInterno) {
        if (montoTotal > 0) {
            DefaultTableModel modelDetalle = (DefaultTableModel) tDetalle.getModel();
            int numFilas = modelDetalle.getRowCount();
            modelDetalle.setNumRows(numFilas);
            modelDetalle.addRow(new Object[numFilas]);
            tDetalle.setValueAt(numFilas + 1, numFilas, 0);
            tDetalle.setValueAt(c.getDescripcion() + rangoFecha, numFilas, 1);
            tDetalle.setValueAt(cantidad, numFilas, 2);
            montoTotal = Math.round(montoTotal * Math.pow(10, 2)) / Math.pow(10, 2);
            tDetalle.setValueAt(montoTotal, numFilas, 4);
            tDetalle.setValueAt(c.getDescripcion(), numFilas, 5);
            tDetalle.setValueAt(this.glosa, numFilas, 9);
//            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            DocumentoPago docPago = this.documentoPago;
            double igv = 0;
            double valorVenta = 0;
            if (c.getTieneIgv().equals("S")) {
                valorVenta = montoTotal / ((c.getPorcentajeIgv() + 100) / 100);
                valorVenta = Math.round(valorVenta * Math.pow(10, 2)) / Math.pow(10, 2);
                igv = montoTotal - valorVenta;
                igv = Math.round(igv * Math.pow(10, 2)) / Math.pow(10, 2);
                tDetalle.setValueAt(valorVenta, numFilas, 6);
                tDetalle.setValueAt(igv, numFilas, 7);
                valorVenta = valorVenta + Double.valueOf(lbSubTotal.getText());
                valorVenta = Math.round(valorVenta * Math.pow(10, 2)) / Math.pow(10, 2);
                igv = igv + Double.valueOf(lbIgv.getText());
                igv = Math.round(igv * Math.pow(10, 2)) / Math.pow(10, 2);
                lbSubTotal.setText(String.valueOf(valorVenta));
                lbIgv.setText(String.valueOf(igv));
                lbTotal.setText(String.valueOf(montoTotal + Double.valueOf(lbTotal.getText())));
            } else {
                montoTotal = Math.round(montoTotal * Math.pow(10, 2)) / Math.pow(10, 2);
                tDetalle.setValueAt(montoTotal, numFilas, 6);
                tDetalle.setValueAt(0.0, numFilas, 7);
                lbSubTotal.setText(String.valueOf(montoTotal + Double.valueOf(lbSubTotal.getText())));
                lbIgv.setText(String.valueOf(0.0 + Double.valueOf(lbIgv.getText())));
                lbTotal.setText(String.valueOf(montoTotal + Double.valueOf(lbTotal.getText())));
            }
            double montoDetraccion = 0;
            if (!Objects.isNull(this.documentoPago.getTieneDetraccion())) {
                if (this.documentoPago.getTieneDetraccion().equals("S")) {
                    montoDetraccion = montoTotal * (c.getPorcentajeDetraccion() / 100);
                    montoDetraccion = Math.round(montoDetraccion * Math.pow(10, 2)) / Math.pow(10, 2);
                    tDetalle.setValueAt(montoDetraccion, numFilas, 13);
                    tDetalle.setValueAt(c.getPorcentajeDetraccion(), numFilas, 14);
                    tDetalle.setValueAt(c.getCodigoBienServicioDetraccion().getCodigo(), numFilas, 15);
                    lbDetraccion.setText(String.valueOf(montoDetraccion + Double.valueOf(lbDetraccion.getText())));
                }
            }
            tDetalle.setValueAt(idInterno, numFilas, 8);
            tDetalle.setValueAt(this.listaVales, numFilas, 12);
            cbCodigo.requestFocus();
            txtCantidad.setEditable(true);
            txtTotal.setEditable(true);
            txtCantidad.setText("");
            txtTotal.setText("");
            this.glosa = "";
            this.listaVales = new ArrayList<>();
            ((JTextField) cbCodigo.getEditor().getEditorComponent()).setText("");
            ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).setText("");
        }
    }

    private void ValidarHabilitacion() {
        if (tipoCliente.equals("C") || tipoCliente.equals("S")) {
            Cliente cliente = this.documentoPago.getCliente();
            ClienteBO cBO = ClienteBO.getInstance();
            if (cliente.getEstado().equals("H") || cliente.getEstado().equals("I")) {
                cBO.ValidarHabilitacionColegiado(cliente);
            }
        } else {
            if (this.documentoPago.getCliente().getTipoCliente().equals("E")) {
                if (this.documentoPago.getClienteByIdContadorEmpresa() != null) {
                    ClienteBO cBO = ClienteBO.getInstance();
                    cBO.ValidarHabilitacionColegiado(this.documentoPago.getClienteByIdContadorEmpresa());
                }
            }
        }
    }

    private void cbConceptoPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbConceptoPagoItemStateChanged
        String detPago = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
        this.ObtenerDatosConceptoDetalle(detPago);
    }//GEN-LAST:event_cbConceptoPagoItemStateChanged

    public String VerificarExisteFinanciamiento(/*int idCliente, */List lista) {
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        /*FinanciamientoBO fBO = FinanciamientoBO.getInstance();
         List<FinanciamientoDocumentoPago> listaFinanciamiento = fBO.ObtenerTodosFinanciamientoActivosCliente(idCliente);*/
        List<FinanciamientoDocumentoPago> listaFinanciamiento = lista;
        String mensaje = "";
        int nroCuota = 0;
        int contador = 0;
        double montoFinanciamiento = 0;
        mensaje = "";
        for (FinanciamientoDocumentoPago fdp : listaFinanciamiento) {
            //fechaServidor < fdp.getFechaVencimiento(), returns less than 0
            //fechaServidor > fdp.getFechaVencimiento(), returns greater than 0
            //fechaServidor = fdp.getFechaVencimiento(), so will print 0 to console
            if (contador == 0) {
                mensaje = "• FINANCIAMIENTO PENDIENTE A PAGAR:\r\n";
                mensaje = mensaje + "FECHA VENCIMIENTO " + fdp.getFechaVencimiento().toString() + " CUOTA NRO " + fdp.getNroCuota().toString();
                nroCuota = fdp.getNroCuota();
                contador = contador + 1;
            }
            int difFecha = fechaServidor.compareTo(fdp.getFechaVencimiento());
            if (difFecha <= 0 && nroCuota == fdp.getNroCuota()) {
                montoFinanciamiento = montoFinanciamiento + fdp.getMonto();
            } else {
                mensaje = mensaje + " MONTO: S/." + String.valueOf(montoFinanciamiento) + "\r\n";
                break;
            }
        }
        return mensaje;
    }

    private boolean validarNoTengaDeudas(Cliente c) {
        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
        List l = fBO.ObtenerTodosFinanciamientoActivosCliente(c.getIdCliente());
        if (l.size() > 0) {
            return false;
        }
        ReincorporacionBO rBO = ReincorporacionBO.getInstance();
        List lr = rBO.ObtenerTodasReincoporacionesPendientes(c.getIdCliente());
        if (lr.size() > 0) {
            return false;
        }
        DeudasBO dBO = DeudasBO.getInstance();
        List ld = dBO.ObtenerTodasDeudasPendientes(c.getIdCliente());
        if (ld.size() > 0) {
            return false;
        }
        return true;
    }

    public void CuotasAPagarEmpresaContador(Cliente cliente, int cantidadCuotas) {
        boolean descuentoCuotas = false;
        boolean generarVale = false;
        double monto = 0;
        double montoDescuento = 0;
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        monto = dpBO.ObtenerMontoCuotasAPagarClienteEmpresa(cliente, cantidadCuotas);
        List<Object> listaCuotasAPagar = dpBO.getCuotasAPagar();
        if (!cliente.getEstado().equals("V")) {
            if (cantidadCuotas % 12 == 0 && cantidadCuotas > 0) {
                String[] opciones = {"Realizar Descuento", "Generar Vale Académico", "Ninguno"};
                String opcionSeleccionada = (String) JOptionPane.showInputDialog(this,
                        "¿ Que desea hacer ?",
                        "Elegir",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);
                if (opcionSeleccionada == null) {
                    return;
                }
                this.listaVales = new ArrayList<>();
                if (opcionSeleccionada.equals("Realizar Descuento")) {
                    descuentoCuotas = true;
                } else {
                    if (opcionSeleccionada.equals("Generar Vale Académico")) {
                        boolean valido = this.validarNoTengaDeudas(this.documentoPago.getCliente());
                        if (!valido) {
                            JOptionPane.showMessageDialog(this,
                                    "EL COLEGIADO TIENE DEUDAS PENDIENTES",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Integer cantidadVales = cantidadCuotas / 12;
                        for (int i = 0; i < cantidadVales; i++) {
                            ValeAcademico va  = new ValeAcademico();
                            va.setBorrado("1");
                            va.setFechaInicio(this.documentoPago.getFechaPago());
                            Calendar c = Calendar.getInstance();
                            c.setTime(this.documentoPago.getFechaPago());
                            c.add(Calendar.YEAR, i + 1);
//                            va.setFechaFin(c.getTime());
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                va.setFechaFin(format.parse("31/12/2023"));
                            } catch (Exception e) {
                                System.out.println("error");
                            }
                            va.setMonto(100.00);
                            va.setNro(i);
                            SeguridadBO sBO = SeguridadBO.getINSTANCE();
                            va.setUsuario(sBO.getUsuario());
                            this.listaVales.add(va);
                        }
                    }
                }
            }
//            int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA REALIZAR DESCUENTO?");
//            if (opcion == JOptionPane.YES_OPTION) {
//                descuentoCuotas = true;
//            }
        }
        String mensaje = "• CUOTAS A PAGAR:\r\n";
        String mes = "";
        int contador = 0;
        for (Object pobj : listaCuotasAPagar) {
            Object[] obj = (Object[]) pobj;
            mes = this.ObtenerMes((int) obj[3]);
            double montoCuota = (double) obj[4];
            if (descuentoCuotas == true) {
                int id = (int) obj[1]; //IDANIOMES
                CuotasBO cBO = CuotasBO.getInstance();
                montoDescuento = cBO.ObtenerDescuentoConcepto(id);
                if (cliente.getTipoCliente().equals("C")) {
                    montoCuota = montoCuota - montoDescuento;
                    if (contador < 8) {
                        montoCuota = montoCuota - 0.1;
                        montoDescuento = montoDescuento + 0.1;
                        contador = contador + 1;
                        montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                        montoDescuento = Math.round(montoDescuento * Math.pow(10, 2)) / Math.pow(10, 2);
                    }
                    monto = monto - montoDescuento;
                    montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                    monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
                } else {
                    if (cliente.getSciudad().equals("A")) {
                        montoCuota = montoCuota - montoDescuento * 2;
                        monto = monto - montoDescuento * 2;
                        montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                        monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
                    } else {
                        montoCuota = montoCuota - montoDescuento * 4;
                        monto = monto - montoDescuento * 4;
                        montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                        monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
                    }
                }
                obj[4] = montoCuota;
                obj[6] = true;
            }
            mensaje = mensaje + String.valueOf((int) obj[2]) + " " + mes + " : S/. " + String.valueOf(montoCuota) + "\r\n";
        }
        /*INICIO ---------- SABER SI TIENE FINANCIAMIENTO A PAGAR*/
        double montoCuotasFinanciamiento = dpBO.ObtenerMontoFinanciamientoAPagarClienteEmpresa(cliente, cantidadCuotas);
        List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = dpBO.getFinanciamientoAPagar();
        if (listaFinanciamientoAPagar != null) {
            if (listaFinanciamientoAPagar.size() > 0) {
                mensaje = mensaje + "• FINANCIAMIENTO A PAGAR:\r\n";
                for (FinanciamientoDocumentoPago fdp : listaFinanciamientoAPagar) {
                    mensaje = mensaje + "NRO CUOTA " + fdp.getNroCuota() + " MONTO: S/. " + fdp.getMonto().toString() + " FCH VENCIMIENTO:" + fdp.getFechaVencimiento().toString() + "\r\n";
                }
            }
        }
        /*FIN -------------- AÑADIENDO PROGRAMACION*/
 /*INICIO ---------- SABER SI TIENE REINCORPORACION A PAGAR*/
        double montoCuotasReincorporacion = dpBO.ObtenerMontoReincorporacionAPagarClienteEmpresa(cliente, cantidadCuotas);
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = dpBO.getReincorporacionAPagar();
        if (listaReincorporacionAPagar != null) {
            if (listaReincorporacionAPagar.size() > 0) {
                mensaje = mensaje + "• REINCORPORACIÓN A PAGAR:\r\n";
                for (ReincorporacionDocumentoPago rdp : listaReincorporacionAPagar) {
                    mensaje = mensaje + "NRO CUOTA " + rdp.getNroCuota() + " MONTO: S/. " + String.valueOf(rdp.getMontoFondo() + rdp.getMontoOtros()) + " FCH VENCIMIENTO:" + rdp.getFechaVencimiento().toString() + "\r\n";
                }
            }
        }
        /*FIN -------------- AÑADIENDO PROGRAMACION*/
        txtCantidad.setText(String.valueOf(cantidadCuotas));
        txtTotal.setText(String.valueOf(monto + montoCuotasFinanciamiento + montoCuotasReincorporacion));
        JOptionPane.showMessageDialog(this,
                mensaje,
                "INFORMACIÓN",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void CuotasAPagar(int cantidadCuotas) {
        boolean descuentoCuotas = false;
        double monto = 0;
        double montoDescuento = 0;
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        monto = dpBO.ObtenerMontoCuotasAPagarCliente(cantidadCuotas, this.documentoPago);
        List<Object> listaCuotasAPagar = dpBO.getCuotasAPagar();
        if (!this.documentoPago.getCliente().getEstado().equals("V")) {
            if (cantidadCuotas % 12 == 0 && cantidadCuotas > 0) {
                String[] opciones = {"Realizar Descuento", "Generar Vale Académico", "Ninguno"};
                String opcionSeleccionada = (String) JOptionPane.showInputDialog(this,
                        "¿ Que desea hacer ?",
                        "Elegir",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);
                if (opcionSeleccionada == null) {
                    return;
                }
                this.listaVales = new ArrayList<>();
                if (opcionSeleccionada.equals("Realizar Descuento")) {
                    descuentoCuotas = true;
                } else {
                    if (opcionSeleccionada.equals("Generar Vale Académico")) {
                        boolean valido = this.validarNoTengaDeudas(this.documentoPago.getCliente());
                        if (!valido) {
                            JOptionPane.showMessageDialog(this,
                                    "EL COLEGIADO TIENE DEUDAS PENDIENTES",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Integer cantidadVales = cantidadCuotas / 12;
                        for (int i = 0; i < cantidadVales; i++) {
                            ValeAcademico va  = new ValeAcademico();
                            va.setBorrado("1");
                            va.setFechaInicio(this.documentoPago.getFechaPago());
                            Calendar c = Calendar.getInstance();
                            c.setTime(this.documentoPago.getFechaPago());
                            c.add(Calendar.YEAR, i + 1);
//                            va.setFechaFin(c.getTime());
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                va.setFechaFin(format.parse("31/12/2023"));
                            } catch (Exception e) {
                                System.out.println("error");
                            }
                            va.setMonto(100.00);
                            va.setNro(i);
                            SeguridadBO sBO = SeguridadBO.getINSTANCE();
                            va.setUsuario(sBO.getUsuario());
                            this.listaVales.add(va);
                        }
                    }
                }
            }
        }
        String mensaje = "• CUOTAS A PAGAR:\r\n";
        String mes = "";
        int contador = 0;
        for (Object pobj : listaCuotasAPagar) {
            Object[] obj = (Object[]) pobj;
            mes = this.ObtenerMes((int) obj[3]);
            double montoCuota = (double) obj[4];
            if (descuentoCuotas == true) {
                int id = (int) obj[1]; //IDANIOMES
                CuotasBO cBO = CuotasBO.getInstance();
                montoDescuento = cBO.ObtenerDescuentoConcepto(id);
                if (this.documentoPago.getCliente().getTipoCliente().equals("C")) {
                    montoCuota = montoCuota - montoDescuento;
                    if (contador < 4) {
                        montoCuota = montoCuota - 0.1;
                        montoDescuento = montoDescuento + 0.1;
                        contador = contador + 1;
                        montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                        montoDescuento = Math.round(montoDescuento * Math.pow(10, 2)) / Math.pow(10, 2);
                    } else {
                        montoCuota = montoCuota - 0.2;
                        montoDescuento = montoDescuento + 0.2;
                        contador = contador + 1;
                        montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                        montoDescuento = Math.round(montoDescuento * Math.pow(10, 2)) / Math.pow(10, 2);
                    }
                    monto = monto - montoDescuento;
                    montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                    monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
                } else {
                    if (this.documentoPago.getCliente().getSciudad().equals("A")) {
                        montoCuota = montoCuota - montoDescuento * 2;
                        monto = monto - montoDescuento * 2;
                        montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                        monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
                    } else {
                        montoCuota = montoCuota - montoDescuento * 4;
                        monto = monto - montoDescuento * 4;
                        montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                        monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
                    }
                }
                obj[4] = montoCuota;
                obj[6] = true;
            }
            mensaje = mensaje + String.valueOf((int) obj[2]) + " " + mes + " : S/. " + String.valueOf(montoCuota) + "\r\n";
        }
        /*INICIO ---------- SABER SI TIENE FINANCIAMIENTO A PAGAR*/
        double montoCuotasFinanciamiento = 0;
        if (!this.documentoPago.getCliente().getTipoCliente().equals("S")) {
            montoCuotasFinanciamiento = dpBO.ObtenerMontoFinanciamientoAPagarCliente(cantidadCuotas, this.documentoPago);
            List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = dpBO.getFinanciamientoAPagar();
            if (listaFinanciamientoAPagar != null) {
                if (listaFinanciamientoAPagar.size() > 0) {
                    mensaje = mensaje + "• FINANCIAMIENTO A PAGAR:\r\n";
                    for (FinanciamientoDocumentoPago fdp : listaFinanciamientoAPagar) {
                        mensaje = mensaje + "NRO CUOTA " + fdp.getNroCuota() + " MONTO: S/. " + fdp.getMonto().toString() + " FCH VENCIMIENTO:" + fdp.getFechaVencimiento().toString() + "\r\n";
                    }
                }
            }
        }
        /*FIN -------------- AÑADIENDO PROGRAMACION*/
 /*INICIO ---------- SABER SI TIENE REINCORPORACION A PAGAR*/
        double montoCuotasReincorporacion = dpBO.ObtenerMontoReincorporacionAPagarCliente(cantidadCuotas, this.documentoPago);
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = dpBO.getReincorporacionAPagar();
        if (listaReincorporacionAPagar != null) {
            if (listaReincorporacionAPagar.size() > 0) {
                mensaje = mensaje + "• REINCORPORACIÓN A PAGAR:\r\n";
                for (ReincorporacionDocumentoPago rdp : listaReincorporacionAPagar) {
                    mensaje = mensaje + "NRO CUOTA " + rdp.getNroCuota() + " MONTO: S/. " + String.valueOf(rdp.getMontoFondo() + rdp.getMontoOtros()) + " FCH VENCIMIENTO:" + rdp.getFechaVencimiento().toString() + "\r\n";
                }
            }
        }
        /*FIN -------------- AÑADIENDO PROGRAMACION*/
        txtCantidad.setText(String.valueOf(cantidadCuotas));
        txtTotal.setText(String.valueOf(monto + montoCuotasFinanciamiento + montoCuotasReincorporacion));
        JOptionPane.showMessageDialog(this,
                mensaje,
                "INFORMACIÓN",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void FinanciamientoAPagar(int cantidadCuotas/*, List listaCuotasFinanciadas, String mensaje*/) {
        double monto = 0;
        double montoCuotas = 0;
        double montoDescuento = 0;
        boolean descuentoCuotas = false;
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        monto = dpBO.ObtenerMontoFinanciamientoAPagarCliente(cantidadCuotas, this.documentoPago);
        List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = dpBO.getFinanciamientoAPagar();
        String mensaje = "• FINANCIAMIENTO A PAGAR:\r\n";
        for (FinanciamientoDocumentoPago fdp : listaFinanciamientoAPagar) {
            mensaje = mensaje + "NRO CUOTA " + fdp.getNroCuota() + " MONTO: S/. " + fdp.getMonto().toString() + " FCH VENCIMIENTO:" + fdp.getFechaVencimiento().toString() + "\r\n";
        }
        txtCantidad.setText(String.valueOf(cantidadCuotas));
        txtTotal.setText(String.valueOf(monto + montoCuotas));
        JOptionPane.showMessageDialog(this,
                mensaje,
                "INFORMACIÓN",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void ReincorporacionAPagar(int cantidadCuotas) {
        double monto = 0;
        double montoCuotas = 0;
        double montoDescuento = 0;
        boolean descuentoCuotas = false;
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        monto = dpBO.ObtenerMontoReincorporacionAPagarCliente(cantidadCuotas, this.documentoPago);
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = dpBO.getReincorporacionAPagar();
        //String mensaje = "CUOTAS A PAGAR:\r\n";
        String mensaje = "• REINCORPORACIÓN A PAGAR:\r\n";
        for (ReincorporacionDocumentoPago rdp : listaReincorporacionAPagar) {
            //mes = this.ObtenerMes(fdp.getNroCuota());
            mensaje = mensaje + "NRO CUOTA " + rdp.getNroCuota() + " MONTO: S/. " + rdp.getMonto().toString() + " FCH VENCIMIENTO:" + rdp.getFechaVencimiento().toString() + "\r\n";
        }
        /*INICIO ---------- AÑADIENDO PROGRAMACION*/
        monto = monto + dpBO.ObtenerMontoCuotasAPagarCliente(cantidadCuotas, this.documentoPago);
        List<Object> listaCuotasAPagar = dpBO.getCuotasAPagar();
        if (cantidadCuotas == 12) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA REALIZAR DESCUENTO?");
            if (opcion == JOptionPane.YES_OPTION) {
                descuentoCuotas = true;
            }
        }
        mensaje = mensaje + "• CUOTAS A PAGAR:\r\n";
        String mes = "";
        for (Object pobj : listaCuotasAPagar) {
            Object[] obj = (Object[]) pobj;
            mes = this.ObtenerMes((int) obj[3]);
            double montoCuota = (double) obj[4];
            if (descuentoCuotas == true) {
                int id = (int) obj[1]; //IDANIOMES
                CuotasBO cBO = CuotasBO.getInstance();
                montoDescuento = cBO.ObtenerDescuentoConcepto(id);
                montoCuota = montoCuota - montoDescuento;
                monto = monto - montoDescuento;
                montoCuota = Math.round(montoCuota * Math.pow(10, 2)) / Math.pow(10, 2);
                monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
                obj[4] = montoCuota;
                obj[6] = true;
            }
            mensaje = mensaje + String.valueOf((int) obj[2]) + " " + mes + " : S/. " + String.valueOf(montoCuota) + "\r\n";
        }
        /*FIN -------------- AÑADIENDO PROGRAMACION*/
        txtCantidad.setText(String.valueOf(cantidadCuotas));
        txtTotal.setText(String.valueOf(monto + montoCuotas));
        JOptionPane.showMessageDialog(this,
                mensaje,
                "INFORMACIÓN",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public String VerificarExisteCuotaPendiente() {
        String mensaje = "";
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        CuotasBO cBO = CuotasBO.getInstance();
        Object[] cuota = cBO.ObtenerCuotaMesAcutal(this.documentoPago.getCliente().getIdCliente());
        if (cuota[4] == null) {
            mensaje = "• CUOTA PENDIENTE:\r\n";
            mensaje = mensaje + String.valueOf((int) cuota[2]) + " " + this.ObtenerMes((int) cuota[3]) + " MONTO: S/." + String.valueOf((double) cuota[5]) + "\r\n";
        } else {
            if ((double) cuota[4] < (double) cuota[5]) {
                mensaje = "• CUOTA PENDIENTE:\r\n";
            }
            mensaje = mensaje + String.valueOf((int) cuota[2]) + " " + this.ObtenerMes((int) cuota[3]) + " MONTO: S/." + String.valueOf((double) cuota[5] - (double) cuota[4]) + "\r\n";
        }
        return mensaje;
    }

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtCantidad.getText().isEmpty()) {
                txtTotal.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE PONER UNA CANTIDAD",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!txtTotal.getText().isEmpty()) {
                btnAgregar.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE PONER UN PRECIO",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtTotalKeyPressed

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtTotalKeyTyped

    private void imprimirReciboIngreso() {
        try {
            String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(jdbcDriver);
            SeguridadBO sBO = SeguridadBO.getInstance();
            String url = sBO.getUrlReporte();
            //String url = "jdbc:sqlserver://localhost;databaseName=caja";
            String user = "sa";
            String pass = "4dm1n1str4c10nGOB90570";
            Connection con = DriverManager.getConnection(url, user, pass);
            String fullPath = "";
            Map param;
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            DocumentoPago doc = this.documentoPago;
            fullPath = "reportes/recibo_ingreso.jasper";
            String nrodocumentoAdquiriente = "";
            String tipoDocumentoAdquiriente = "";
            String nombreTipoDocumento = "";
            if (doc.getCliente().getTipoCliente().equals("C")) {
                tipoDocumentoAdquiriente = "1";
                nrodocumentoAdquiriente = doc.getCliente().getPnroDocumento();
                nombreTipoDocumento = "NRO DOC:";
            } else {
                if (doc.getCliente().getTipoCliente().equals("P")) {
                    tipoDocumentoAdquiriente = "1";
                    nrodocumentoAdquiriente = doc.getCliente().getPnroDocumento();
                    nombreTipoDocumento = "NRO DOC:";
                } else {
                    if (doc.getCliente().getTipoCliente().equals("E")) {
                        tipoDocumentoAdquiriente = "6";
                        nrodocumentoAdquiriente = doc.getCliente().getEruc();
                        nombreTipoDocumento = "NRO RUC:";
                    } else {
                        if (doc.getCliente().getTipoCliente().equals("S")) {
                            tipoDocumentoAdquiriente = "6";
                            nrodocumentoAdquiriente = doc.getCliente().getSruc();
                            nombreTipoDocumento = "NRO RUC:";
                        }
                    }
                }
            }
            double montoTotalIgv = dpBO.ObtenerSumaTotalIGV(doc.getIdDocumentoPago());
            double montoTotalComprobante = dpBO.ObtenerMontoTotalComprobante(doc.getIdDocumentoPago());
            String valorQR = "20174327026|" + doc.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "|" + doc.getNroSerie() + "|" + String.format("%06d", doc.getNroDocumentoPago()) + "|";
            valorQR = valorQR + String.valueOf(montoTotalIgv) + "|" + String.valueOf(montoTotalComprobante) + "|" + tipoDocumentoAdquiriente + "|" + nrodocumentoAdquiriente;
            param = new HashMap();
            param.put("IDDOCUMENTOPAGO", Integer.valueOf(doc.getIdDocumentoPago()));
            param.put("NOMBRECOMPROBANTE", "RECIBO DE INGRESO");
            param.put("NOMBRETIPODOCUMENTO", nombreTipoDocumento);
            param.put("VALORQR", valorQR);
            param.put("TOTALENLETRAS", "SON: " + NumeroLetras.convertirNumerosALetras(lbTotal.getText(), doc.getMoneda()));
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy.MM.dd");
            param.put("FECHACMP", formateador.format(doc.getFechaPago()));
            param.put("NROCMP", String.format("%07d", doc.getNroDocumentoPago()));
            if (doc.getCliente().getTipoCliente().equals("C")) {
                param.put("CODPAGADOR", doc.getCliente().getCcodigoCole());
                param.put("DIRECCIONPAGADOR", doc.getCliente().getPdireccionDomicilio());
                param.put("NOMBREPAGADOR", doc.getCliente().getPapePat() + ' ' + doc.getCliente().getPapeMat() + ' ' + doc.getCliente().getPnombre());
            }
            if (doc.getCliente().getTipoCliente().equals("S")) {
                param.put("CODPAGADOR", doc.getCliente().getSruc());
                param.put("DIRECCIONPAGADOR", doc.getCliente().getSdireccion());
                param.put("NOMBREPAGADOR", doc.getCliente().getSnombreSociedad());
            }
            if (doc.getCliente().getTipoCliente().equals("E")) {
                param.put("CODPAGADOR", doc.getCliente().getEruc());
                if (doc.getCliente().getEdireccion() != null) {
                    if (!doc.getCliente().getEdireccion().equals("null")) {
                        param.put("DIRECCIONPAGADOR", doc.getCliente().getEdireccion());
                    } else {
                        param.put("DIRECCIONPAGADOR", "");
                    }
                } else {
                    param.put("DIRECCIONPAGADOR", "");
                }
                param.put("NOMBREPAGADOR", doc.getCliente().getEnombre());
            }
            if (doc.getCliente().getTipoCliente().equals("P")) {
                param.put("CODPAGADOR", doc.getCliente().getPnroDocumento());
                param.put("DIRECCIONPAGADOR", doc.getCliente().getPdireccionDomicilio());
                param.put("NOMBREPAGADOR", doc.getCliente().getPapePat() + ' ' + doc.getCliente().getPapeMat() + ' ' + doc.getCliente().getPnombre());
            }
            param.put("REPORT_LOCALE", new Locale("en", "EN"));
            JasperPrint JPrint = JasperFillManager.fillReport(fullPath, param, con);
            // VIEW THE REPORT
            //JasperViewer.viewReport(JPrint, false);
            JasperPrintManager.printPage(JPrint, 0, true);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void ImprimirDocumentoPago() {
        try {
            String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(jdbcDriver);
            SeguridadBO sBO = SeguridadBO.getInstance();
            String url = sBO.getUrlReporte();
            //String url = "jdbc:sqlserver://localhost;databaseName=caja";
            String user = "sa";
            String pass = "4dm1n1str4c10nGOB90570";
            Connection con = DriverManager.getConnection(url, user, pass);
            String fullPath = "";
            Map param;
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            DocumentoPago doc = this.documentoPago;
            if (doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1) {
                fullPath = "reportes/Factura.jasper";
            } else {
                if (doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 3) {
                    this.imprimirReciboIngreso();
                    return;
                } else {
                    fullPath = "reportes/Boleta.jasper";
                }
            }
            param = new HashMap();
            param.put("IDDOCUMENTOPAGO", Integer.valueOf(doc.getIdDocumentoPago()));
            param.put("TOTALENLETRAS", "SON: " + NumeroLetras.convertirNumerosALetras(lbTotal.getText(), doc.getMoneda()));
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy.MM.dd");
            param.put("FECHACMP", formateador.format(doc.getFechaPago()));
            param.put("NROCMP", String.format("%07d", doc.getNroDocumentoPago()));
            if (doc.getCliente().getTipoCliente().equals("C")) {
                param.put("CODPAGADOR", doc.getCliente().getCcodigoCole());
                param.put("DIRECCIONPAGADOR", doc.getCliente().getPdireccionDomicilio());
                param.put("NOMBREPAGADOR", doc.getCliente().getPapePat() + ' ' + doc.getCliente().getPapeMat() + ' ' + doc.getCliente().getPnombre());
            }
            if (doc.getCliente().getTipoCliente().equals("S")) {
                param.put("CODPAGADOR", doc.getCliente().getSruc());
                param.put("DIRECCIONPAGADOR", doc.getCliente().getSdireccion());
                param.put("NOMBREPAGADOR", doc.getCliente().getSnombreSociedad());
            }
            if (doc.getCliente().getTipoCliente().equals("E")) {
                param.put("CODPAGADOR", doc.getCliente().getEruc());
                if (doc.getCliente().getEdireccion() != null) {
                    if (!doc.getCliente().getEdireccion().equals("null")) {
                        param.put("DIRECCIONPAGADOR", doc.getCliente().getEdireccion());
                    } else {
                        param.put("DIRECCIONPAGADOR", "");
                    }
                } else {
                    param.put("DIRECCIONPAGADOR", "");
                }
                param.put("NOMBREPAGADOR", doc.getCliente().getEnombre());
            }
            if (doc.getCliente().getTipoCliente().equals("P")) {
                param.put("CODPAGADOR", doc.getCliente().getPnroDocumento());
                param.put("DIRECCIONPAGADOR", doc.getCliente().getPdireccionDomicilio());
                param.put("NOMBREPAGADOR", doc.getCliente().getPapePat() + ' ' + doc.getCliente().getPapeMat() + ' ' + doc.getCliente().getPnombre());
            }
            param.put("REPORT_LOCALE", new Locale("en", "EN"));
            JasperPrint JPrint = JasperFillManager.fillReport(fullPath, param, con);
            // VIEW THE REPORT
            //JasperViewer.viewReport(JPrint, false);
            JasperPrintManager.printPage(JPrint, 0, true);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private String ObtenerValorResumen(DocumentoPago dp) {
        try {
//            InputStream inputStream = new FileInputStream("CDR/20100194601-01-F001-000000031.xml");
            String nombreArchivo = "\\\\192.168.1.67\\firma\\" + "20174327026-" + dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + dp.getNroSerie() + "-" + String.format("%08d", dp.getNroDocumentoPago()) + ".xml";;
//            String nombreArchivo = "factura_electronica/prueba.xml";
//            String nombreArchivo = "\\\\192.168.1.100\\d\\data0\\facturador\\FIRMA\\" + "20174327026-" + dp.getTipoDocSerie().getTipoDocPago().getCodigoSunat() + "-" + dp.getTipoDocSerie().getTipoDocPago().getCodigoSunat() + "001-" + String.format("%08d", dp.getNroDocumentoPago()) + ".xml";
            File archivo = new File(nombreArchivo);
            if (!archivo.exists()) {
                nombreArchivo = "\\\\192.168.1.67\\firma2\\" + "20174327026-" + dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + dp.getNroSerie() + "-" + String.format("%08d", dp.getNroDocumentoPago()) + ".xml";;
                archivo = new File(nombreArchivo);
                if (!archivo.exists()) {
                    return "";
                }
            }
            InputStream inputStream = new FileInputStream(nombreArchivo);
            Reader reader = new InputStreamReader(inputStream, "ISO-8859-1");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

//            File fXmlFile = new File(nombreArchivo);
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

    public void ImprimirDocumentoPagoElectonico() {
        try {
            String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(jdbcDriver);
            SeguridadBO sBO = SeguridadBO.getInstance();
            String url = sBO.getUrlReporte();
            //String url = "jdbc:sqlserver://localhost;databaseName=caja";
            String user = "sa";
            String pass = "4dm1n1str4c10nGOB90570";
            Connection con = DriverManager.getConnection(url, user, pass);
            String fullPath = "reportes/comprobante_electronico.jasper";
            Map param;
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            DocumentoPago doc = this.documentoPago;
            doc = (DocumentoPago) sBO.CargarObjeto("DocumentoPago", doc.getIdDocumentoPago());
            Cliente cliente = (Cliente) sBO.CargarObjeto("Cliente", doc.getCliente().getIdCliente());
            doc.setCliente(cliente);
            TipoDocSerie tds = (TipoDocSerie) sBO.CargarObjeto("TipoDocSerie", doc.getTipoDocSerie().getId());
            doc.setTipoDocSerie(tds);
            TipoDocPago td = (TipoDocPago) sBO.CargarObjeto("TipoDocPago", doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago());
            doc.getTipoDocSerie().setTipoDocPago(td);
            param = new HashMap();
            double montoTotalIgv = dpBO.ObtenerSumaTotalIGV(doc.getIdDocumentoPago());
            double montoTotalComprobante = dpBO.ObtenerMontoTotalComprobante(doc.getIdDocumentoPago());
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            String fechaEmision = f.format(doc.getFechaSunat());
            String tipoDocumentoAdquiriente = "";
            String nrodocumentoAdquiriente = "";
            String nombreTipoDocumento = "";
            if (doc.getCliente().getTipoCliente().equals("C")) {
                tipoDocumentoAdquiriente = "1";
                nrodocumentoAdquiriente = doc.getCliente().getPnroDocumento();
                nombreTipoDocumento = "NRO DOC:";
            } else {
                if (doc.getCliente().getTipoCliente().equals("P")) {
                    tipoDocumentoAdquiriente = "1";
                    nrodocumentoAdquiriente = doc.getCliente().getPnroDocumento();
                    nombreTipoDocumento = "NRO DOC:";
                } else {
                    if (doc.getCliente().getTipoCliente().equals("E")) {
                        tipoDocumentoAdquiriente = "6";
                        nrodocumentoAdquiriente = doc.getCliente().getEruc();
                        nombreTipoDocumento = "NRO RUC:";
                    } else {
                        if (doc.getCliente().getTipoCliente().equals("S")) {
                            tipoDocumentoAdquiriente = "6";
                            nrodocumentoAdquiriente = doc.getCliente().getSruc();
                            nombreTipoDocumento = "NRO RUC:";
                        }
                    }
                }
            }
            String valorResumen = this.ObtenerValorResumen(doc);
            if (valorResumen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "NO SE ENCUENTRA VALOR RESUMEN", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombreComprobante = "";
            String representacionImpresa = "";
            if (doc.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() != null) {
                if (doc.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
                    nombreComprobante = "FACTURA ELECTRÓNICA";
                    representacionImpresa = "Representación impresa de la " + nombreComprobante;
                } else {
                    if (doc.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("03")) {
                        nombreComprobante = "BOLETA ELECTRÓNICA";
                        representacionImpresa = "Representación impresa de la " + nombreComprobante;
                    }
                }
            }
            String valorQR = "20174327026|" + doc.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "|" + doc.getNroSerie() + "|" + String.format("%06d", doc.getNroDocumentoPago()) + "|";
            valorQR = valorQR + String.valueOf(montoTotalIgv) + "|" + String.valueOf(montoTotalComprobante) + "|" + fechaEmision + "|" + tipoDocumentoAdquiriente + "|" + nrodocumentoAdquiriente + "|" + valorResumen;
            param.put("IDDOCUMENTOPAGO", doc.getIdDocumentoPago());
            param.put("TOTALENLETRAS", NumeroLetras.convertirNumerosALetras(lbTotal.getText(), doc.getMoneda()));
            param.put("REPRESENTACIONIMPRESA", representacionImpresa);
            param.put("VALORQR", valorQR);
            param.put("VALORRESUMEN", valorResumen);
            param.put("NOMBRETIPODOCUMENTO", nombreTipoDocumento);
            param.put("NOMBRECOMPROBANTE", nombreComprobante);
            if (!Objects.isNull(doc.getTieneDetraccion())) {
                if (doc.getTieneDetraccion().equals("S")) {
                    ManagerBO mgBO = ManagerBO.getInstance();
                    Double montoDetraccion = 0.0;
                    List<DocumentoPagoDet> detalle = dpBO.ObtenerDetalleComprobante(doc.getIdDocumentoPago());
                    if (detalle.size() > 0) {
                        for (DocumentoPagoDet dpd : detalle) {
                            montoDetraccion = montoDetraccion + dpd.getMontoDetraccion();
                        }
                        DocumentoPagoDet dpd = (DocumentoPagoDet) detalle.get(0);
                        param.put("detraccion", "Detracción S/." + montoDetraccion + (!Objects.isNull(dpd.getPorcentajeDetraccion()) ? " ( " + dpd.getPorcentajeDetraccion() + "% )" : ""));
                        if (!Objects.isNull(dpd.getCodigoBienServicioDetraccion())) {
                            CodigoMedioPago codigoMP = mgBO.obtenerCodigoMedioPago(doc.getCodigoMedioPago());
                            CodigoBienServicioDetraccion codigoBSD = mgBO.obtenerCodigoBienServicioDetraccion(dpd.getCodigoBienServicioDetraccion());
                            param.put("codigoBien", (!Objects.isNull(codigoBSD) ? codigoBSD.getDescripcion() + " (" + codigoBSD.getCodigo() + ")" : "") + ", " + (!Objects.isNull(codigoMP) ? codigoMP.getDescripcion() + " (" + codigoMP.getCodigo() + ")" : ""));
                        }
                    }
//                    CodigoMedioPago codigoMP = mgBO.obtenerCodigoMedioPago(doc.getCodigoMedioPago());
                    param.put("cuentaBancoNacion", "Cta Banco de la Nación: 00101150550");
                    Double montoTotalMenosDetraccion = montoTotalComprobante - montoDetraccion;
                    montoTotalMenosDetraccion = Math.round(montoTotalMenosDetraccion * Math.pow(10, 2)) / Math.pow(10, 2);
                    param.put("montoDetraccion", "Importe a Cobrar: S/. " + montoTotalMenosDetraccion);
                    fullPath = "reportes/comprobante_electronico_detraccion.jasper";
                }
            }
            if (doc.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
                if (doc.getFormaPagoSunat() != null) {
                    param.put("FORMAPAGO", (doc.getFormaPagoSunat().equals("Credito") ? "Crédito" : doc.getFormaPagoSunat()));
                } else {
                    param.put("FORMAPAGO", "");
                }
            } else {
                param.put("FORMAPAGO", "");
            }
            boolean tieneCurso = dpBO.ComprobanteTieneCurso(doc.getIdDocumentoPago());
            if (tieneCurso) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Desea imprimir el(los) participante(s)?", "Pregunta", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    param.put("PARTICIPANTES", 1);
                } else {
                    param.put("PARTICIPANTES", 0);
                }
            } else {
                param.put("PARTICIPANTES", 0);
            }
            param.put("REPORT_LOCALE", new Locale("en", "EN"));
            JasperPrint JPrint = JasperFillManager.fillReport(fullPath, param, con);
            JasperPrintManager.printPage(JPrint, 0, true);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
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
                DocumentoPago doc = documentoPago;
                if (doc.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
                    if (doc.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                        ImprimirDocumentoPagoElectonico();
                    } else {
                        ImprimirDocumentoPago();
                    }
                } else {
                    ImprimirDocumentoPago();
                }
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void ingresarAlumno(DocumentoPago d, DocumentoPagoDet dpd) {
        Cliente c = d.getCliente();
        if (c.getTipoCliente().equals("C") || c.getTipoCliente().equals("P")) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿ DESEA AGREGARLO COMO ALUMNO ?");
            if (opcion == JOptionPane.YES_OPTION) {
                AlumnoBO aluBO = AlumnoBO.getInstance();
                Alumnos alu = new Alumnos();
                alu.setCliente(c);
                alu.setDocumentoPagoDet(dpd);
                if (c.getTipoCliente().equals("C")) {
                    alu.setCodigo(c.getCcodigoCole());
                } else {
                    alu.setDni(c.getPnroDocumento());
                }
                alu.setApellidoPaterno(c.getPapePat());
                alu.setApellidoMaterno(c.getPapeMat());
                alu.setNombres(c.getPnombre());
                alu.setConceptoCurso(dpd.getConceptoPagoDetalle().getDescripcion());
                alu.setMonto(dpd.getPrecioVenta());
                alu.setCodigoCurso(dpd.getConceptoPagoDetalle().getTipoCodigo() + dpd.getConceptoPagoDetalle().getCodigo());
                alu.setTipoPago(d.getTipoPago());

                boolean seGuardo = aluBO.GuardarAlumnos(alu);
                if (!seGuardo) {
                    String msg = "NO SE PUDO INSCRIBIR AL ALUMNO:" + alu.getApellidoPaterno() + " " + alu.getApellidoMaterno() + " " + alu.getNombres();
                    JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
                    //tAlumnos.setRowSelectionInterval(i, i);
                    return;
                }
            }
        }
    }


    private void btnAprobarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobarComprobanteActionPerformed
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        DefaultTableModel modelDetalle = (DefaultTableModel) tDetalle.getModel();
        int numFilas = modelDetalle.getRowCount();
        if (numFilas > 0) {
            for (int i = 0; i < numFilas; i++) {
                String conceptoPago = String.valueOf(modelDetalle.getValueAt(i, 5));
                int cantidad = (int) modelDetalle.getValueAt(i, 2);
                double montoTotal = (double) modelDetalle.getValueAt(i, 4);
                double montoDetraccion = (!Objects.isNull(modelDetalle.getValueAt(i, 13)) ? (double) modelDetalle.getValueAt(i, 13) : 0.0);
                double porcentajeDetraccion = (!Objects.isNull(modelDetalle.getValueAt(i, 14)) ? (double) modelDetalle.getValueAt(i, 14) : 0.0);
                String glosa = (String) modelDetalle.getValueAt(i, 9);
                List lParticipantes = new ArrayList();
                if (modelDetalle.getValueAt(i, 10) != null) {
                    lParticipantes = (List) modelDetalle.getValueAt(i, 10);
                }
                EventoConceptoPago ec = new EventoConceptoPago();
                if (modelDetalle.getValueAt(i, 11) != null) {
                    ec = (EventoConceptoPago) modelDetalle.getValueAt(i, 11);
                }
                List<ValeAcademico> lva = null;
                if (modelDetalle.getValueAt(i, 12) != null) {
                    lva = (List<ValeAcademico>) modelDetalle.getValueAt(i, 12);
                }
                if (montoTotal > 0) {
                    ConceptoPagoDetalle conceptoDetalle = null;
                    List<ConceptoPagoDetalle> lconceptoDetalle = dpBO.ObtenerTodosConceptoPago();
                    for (ConceptoPagoDetalle cDetalle : lconceptoDetalle) {
                        if (cDetalle.getDescripcion().equals(conceptoPago)) {
                            conceptoDetalle = cDetalle;
                            break;
                        }
                    }
                    if (conceptoDetalle != null) {
                        SeguridadBO sBO = SeguridadBO.getInstance();
                        TipoAfectacion ta = (TipoAfectacion) sBO.CargarObjeto("TipoAfectacion", conceptoDetalle.getTipoAfectacion().getId());
                        conceptoDetalle.setTipoAfectacion(ta);
                        TipoTributo tt = (TipoTributo) sBO.CargarObjeto("TipoTributo", conceptoDetalle.getTipoAfectacion().getTipoTributo().getId());
                        conceptoDetalle.getTipoAfectacion().setTipoTributo(tt);
                        CodigoBienServicioDetraccion cbsd = (CodigoBienServicioDetraccion) sBO.CargarObjeto("CodigoBienServicioDetraccion", (!Objects.isNull(conceptoDetalle.getCodigoBienServicioDetraccion()) ? conceptoDetalle.getCodigoBienServicioDetraccion().getId() : 0));
                        conceptoDetalle.setCodigoBienServicioDetraccion(cbsd);
                    }
                    DocumentoPagoDet dpd = new DocumentoPagoDet();
                    dpd.setDocumentoPago(this.documentoPago);
                    dpd.setCantidad(cantidad);
                    dpd.setValorVenta(montoTotal);
                    dpd.setIgv(0.0);
                    dpd.setIgvPorcentaje(0.0);
                    dpd.setPrecioVenta(montoTotal);
                    dpd.setPorcentajeDetraccion(porcentajeDetraccion);
                    dpd.setMontoDetraccion(montoDetraccion);
                    double valorVenta = 0;
                    double valorIgv = 0;
                    if (conceptoDetalle.getTieneIgv() != null) {
                        if (conceptoDetalle.getTieneIgv().equals("S")) {
                            valorVenta = (double) modelDetalle.getValueAt(i, 6);
                            valorIgv = (double) modelDetalle.getValueAt(i, 7);
                            dpd.setValorVenta(valorVenta);
                            dpd.setIgv(valorIgv);
                            dpd.setPrecioVenta(valorIgv + valorVenta);
                            if (conceptoDetalle.getPorcentajeIgv() != null) {
                                dpd.setIgvPorcentaje(conceptoDetalle.getPorcentajeIgv());
                            } else {
                                dpd.setIgvPorcentaje(0.0);
                            }
                        }
                    }
                    dpd.setConceptoPagoDetalle(conceptoDetalle);
                    dpd.setCodigoTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getCodigo());
                    dpd.setCodigoInternacionalTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getCodigoInternacional());
                    dpd.setNombreTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getNombre());
                    dpd.setCodigoTipoAfectacion(conceptoDetalle.getTipoAfectacion().getCodigo());
                    dpd.setCodigoBienServicioDetraccion((!Objects.isNull(conceptoDetalle.getCodigoBienServicioDetraccion()) ? conceptoDetalle.getCodigoBienServicioDetraccion().getCodigo() : ""));
                    if (dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 6987) {
                        dpd.setObservacion(glosa);
                    } else {
                        dpd.setGlosa(glosa);
                    }
                    boolean seGuardo = dpBO.GuardarDetalleDocumentoPago(dpd, lva);
                    if (!seGuardo) {
                        JOptionPane.showMessageDialog(this,
                                "NO SE GUARDO EL DETALLE",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        this.VerificarIngresoCongreso(dpd, ec, lParticipantes);
                    }
                }
            }
            btnAprobarComprobante.setEnabled(false);
            btnImprimir.setEnabled(true);
            this.ValidarHabilitacion();
            this.LimpiarDatos();
            DocumentoPago documento = this.documentoPago;
            dpBO.GenerarArchivosElectronico_ComprobantePago(documento);
            this.CargarDatos(documento, this.tipoCliente, 2);
        } else {
            JOptionPane.showMessageDialog(this,
                    "DEBE INGRESAR DETALLE AL COMPROBANTE",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAprobarComprobanteActionPerformed

    private boolean VerificarCierreDiario(DocumentoPagoBO dpBO) {
        try {
            boolean estaCerrado = dpBO.EstaCerradoElDia(lbFecha.getText());
            return estaCerrado;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private void EliminarArchivoTXT(DocumentoPago documento) {
        String nombreArchivo = "\\\\192.168.1.67\\DATA\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".CAB";
        File fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        nombreArchivo = "\\\\192.168.1.67\\DATA\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".DET";
        fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        nombreArchivo = "\\\\192.168.1.67\\DATA\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".LEY";
        fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        nombreArchivo = "\\\\192.168.1.67\\DATA\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".TRI";
        fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        nombreArchivo = "\\\\192.168.1.67\\DATA\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".ACA";
        fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
    }

    private void EliminarArchivoJSON(DocumentoPago documento) {
        String nombreArchivo = "\\\\192.168.1.67\\DATA\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".json";
        File fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        nombreArchivo = "\\\\192.168.1.67\\DATA2\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".json";
        fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo json no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
    }

    private void EliminarArchivoXML(DocumentoPago documento) {
        String nombreArchivo = "\\\\192.168.1.67\\firma\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".xml";;
        File fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo jsonno existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
        nombreArchivo = "\\\\192.168.1.67\\firma2\\" + "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".xml";;
        fichero = new File(nombreArchivo);
        if (!fichero.exists()) {
            System.out.println("El archivo jsonno existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo json fue eliminado.");
        }
    }


    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        boolean estaCerradoElDia = this.VerificarCierreDiario(dpBO);
        if (estaCerradoElDia) {
            JOptionPane.showMessageDialog(this,
                    "NO SE PUEDE ELIMINAR EL COMPROBANTE, EL DÍA " + lbFecha.getText() + " ESTÁ CERRADO",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(null, "¿ DESEA ELIMINAR EL COMPROBANTE ?");
        if (opcion == JOptionPane.YES_OPTION) {
            boolean seElimino = dpBO.EliminarComprobante(this.documentoPago);
            if (seElimino) {
                if (this.documentoPago.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
                    if (this.documentoPago.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                        this.EliminarArchivoTXT(this.documentoPago);
//                        this.EliminarArchivoJSON(this.documentoPago);
                        this.EliminarArchivoXML(this.documentoPago);
                    }
                }
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
            this.dispose();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void ObtenerDatosSegunCodigo(String codigo) {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lConcepto = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle c : lConcepto) {
            String codigoConcepto = c.getTipoCodigo() + c.getCodigo();
            if (codigo.equals(codigoConcepto)) {
                ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).setText(c.getDescripcion());
                return;
            }
        }
        ((JTextField) cbCodigo.getEditor().getEditorComponent()).setText("");
        ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).setText("");
    }

    private void cbCodigoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCodigoItemStateChanged
        String codigo = ((JTextField) cbCodigo.getEditor().getEditorComponent()).getText();
        this.ObtenerDatosSegunCodigo(codigo);
    }//GEN-LAST:event_cbCodigoItemStateChanged

    public void AgregarMotivoAnulacion(String motivo) {
        lbMotivoAnulacion.setText(motivo);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        this.documentoPago.setMotivoAnulacion(motivo);
    }


    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        boolean estaCerradoElDia = this.VerificarCierreDiario(dpBO);
        if (estaCerradoElDia) {
            JOptionPane.showMessageDialog(this,
                    "NO SE PUEDE ANULAR EL COMPROBANTE, EL DÍA " + lbFecha.getText() + " ESTÁ CERRADO",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(null, "¿ DESEA ANULAR EL COMPROBANTE ?");
        if (opcion == JOptionPane.YES_OPTION) {
            DocumentoPago documento = this.documentoPago;
            if (documento.getMotivoAnulacion() != null) {
                if (documento.getMotivoAnulacion().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "DEBE INGRESAR UN MOTIVO DE ANULACIÓN",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "DEBE INGRESAR UN MOTIVO DE ANULACIÓN",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean seElimino = dpBO.AnularComprobante(this.documentoPago);
            if (seElimino) {
                documento.setEstado("ANULADO");
                dpBO.ActualizarCabeceraDocumento(documento);
                if (this.documentoPago.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
                    if (this.documentoPago.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                        this.EliminarArchivoXML(this.documentoPago);
                        this.EliminarArchivoJSON(this.documentoPago);
                    }
                }
                JOptionPane.showMessageDialog(this,
                        "SE ANULÓ EL COMPROBANTE",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                //if (mensaje.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "NO SE PUDO ANULAR EL COMPROBANTE",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void tDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tDetalleKeyPressed
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        if (evt.getKeyCode() == KeyEvent.VK_DELETE && this.documentoPago.getEstado().equals("A")) {
            int fila = tDetalle.getSelectedRow();
            if (fila >= 0) {
                DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
                double valorVenta = 0;
                double igv = 0;
                double detraccion = 0;
                int idInterno = Integer.valueOf(model.getValueAt(fila, 8).toString());
                int idTabla = 0;
                for (int i = 0; i < tDetalle.getRowCount(); i++) {
                    idTabla = Integer.valueOf(model.getValueAt(i, 8).toString());
                    valorVenta = Double.valueOf(model.getValueAt(i, 6).toString());
                    igv = Double.valueOf(model.getValueAt(i, 7).toString());
                    detraccion = !Objects.isNull(model.getValueAt(i, 13)) ? Double.valueOf(model.getValueAt(i, 13).toString()) : 0.0;
                    if (idInterno == idTabla) {
                        double valorSubTotal = Double.valueOf(lbSubTotal.getText()) - valorVenta;
                        double valorIgv = Double.valueOf(lbIgv.getText()) - igv;
                        double valorDetraccion = Double.valueOf(lbDetraccion.getText()) - detraccion;
                        lbSubTotal.setText(String.valueOf(valorSubTotal));
                        lbIgv.setText(String.valueOf(valorIgv));
                        lbTotal.setText(String.valueOf(valorSubTotal + valorIgv));
                        lbDetraccion.setText(String.valueOf(detraccion));
                        model.removeRow(i);
                        i = i - 1;
                    }
                }
            }
        }
    }//GEN-LAST:event_tDetalleKeyPressed

    private void cbCodigoComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_cbCodigoComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCodigoComponentRemoved

    private void btnGlosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGlosaActionPerformed
        String conceptoPago = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
        if (!conceptoPago.equals("PAGO CREDITO")) {
            String pGlosa = JOptionPane.showInputDialog(
                    this,
                    "INGRESE LA GLOSA",
                    this.glosa);  // el icono sera un iterrogante
            this.glosa = pGlosa;
        } else {
            frmPrincipal fPrincipal = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
            fPrincipal.AbriDialogoGlosa();
        }
    }//GEN-LAST:event_btnGlosaActionPerformed

    private void btnNuevoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoComprobanteActionPerformed
        frmPrincipal fPrincipal = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        fPrincipal.AbrirFormularioNuevoComprobante();
    }//GEN-LAST:event_btnNuevoComprobanteActionPerformed

    private void btnMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMailActionPerformed
        frmPrincipal fPrincipal = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        fPrincipal.AbriFormularioCorreoElectronico(this.documentoPago);
    }//GEN-LAST:event_btnMailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        frmPrincipal fPrincipal = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        fPrincipal.AbriFormularioMotivoAnulacion(this, this.documentoPago);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnMail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMail1ActionPerformed
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        DocumentoPago documento = this.documentoPago;
        dpBO.GenerarArchivosElectronico_ComprobantePago(documento);
    }//GEN-LAST:event_btnMail1ActionPerformed

    private void LimpiarDatos() {
        btnAgregar.setEnabled(false);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        dpBO.LimpiarCuotasAPagar();
        dpBO.LimpiarFinanciamientoAPagar();
        Cliente cEmpresa = new Cliente();
        dpBO.setClienteEmpresa(cEmpresa);
    }

    private void ObtenerDatosConceptoDetalle(String detPago) {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lConceptoPago = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle cd : lConceptoPago) {
            if (cd.getDescripcion().equals(detPago)) {
                if (cd.getIdConceptoPagoDetalle() == 1 || cd.getIdConceptoPagoDetalle() == 7220 || cd.getIdConceptoPagoDetalle() == 10 || cd.getIdConceptoPagoDetalle() == 5850) {
                    txtCantidad.setEditable(false);
                    txtTotal.setEditable(false);
                } else {
                    txtCantidad.setEditable(true);
                    txtTotal.setEditable(true);
                }
                if (!cd.getTipoCodigo().equals("04") && !cd.getCodigo().equals("0000")) {
                    txtCantidad.setText("");
                    txtTotal.setText("");
                    this.agregarParticipantes = false;
                }
                ((JTextField) cbCodigo.getEditor().getEditorComponent()).setText(cd.getTipoCodigo() + cd.getCodigo());
                return;
            }
        }
        ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).setText("");
    }

    private void ObtenerCuotas(int cantidad) {
        //-------------------SABER SI ES CUOTA O FINANCIAMIENTO-------------------
        String nomDetalle = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
        if (nomDetalle.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "DEBE ELEGIR UN CONCEPTO",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 5).toString().equals(nomDetalle)) {
                JOptionPane.showMessageDialog(this,
                        "EL CONCEPTO YA FUE INGRESADO ANTERIORMENTE",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        ConceptoPagoDetalle conceptoDetalle = null;
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lconceptoDetalle = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle cDetalle : lconceptoDetalle) {
            if (cDetalle.getDescripcion().equals(nomDetalle)) {
                conceptoDetalle = cDetalle;
                break;
            }
        }
        if (conceptoDetalle.getIdConceptoPagoDetalle() == 1 || conceptoDetalle.getIdConceptoPagoDetalle() == 7220 || conceptoDetalle.getIdConceptoPagoDetalle() == 10 || conceptoDetalle.getIdConceptoPagoDetalle() == 5850) {
            dpBO.ResetearListaCuotas();
            if (conceptoDetalle.getIdConceptoPagoDetalle() == 1 || conceptoDetalle.getIdConceptoPagoDetalle() == 7220) {
                this.CuotasAPagar(cantidad);
            } else {
                if (conceptoDetalle.getIdConceptoPagoDetalle() == 10) {
                    this.FinanciamientoAPagar(cantidad);
                } else {
                    if (conceptoDetalle.getIdConceptoPagoDetalle() == 5850) {
                        this.ReincorporacionAPagar(cantidad);
                    }
                }
            }
            btnAgregar.requestFocus();
        } else {
            txtTotal.requestFocus();
        }
    }

    private void ObtenerCuotasEmpresaContador(Cliente cliente, int cantidad) {
        //-------------------SABER SI ES CUOTA O FINANCIAMIENTO-------------------
        String nomDetalle = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
        if (nomDetalle.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "DEBE ELEGIR UN CONCEPTO",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tDetalle.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 5).toString().equals(nomDetalle)) {
                JOptionPane.showMessageDialog(this,
                        "EL CONCEPTO YA FUE INGRESADO ANTERIORMENTE",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        ConceptoPagoDetalle conceptoDetalle = null;
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lconceptoDetalle = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle cDetalle : lconceptoDetalle) {
            if (cDetalle.getDescripcion().equals(nomDetalle)) {
                conceptoDetalle = cDetalle;
                break;
            }
        }
        if (conceptoDetalle.getIdConceptoPagoDetalle() == 1) {
            dpBO.ResetearListaCuotas();
            this.CuotasAPagarEmpresaContador(cliente, cantidad);
            btnAgregar.requestFocus();
        } else {
            txtTotal.requestFocus();
        }
    }

    private void mostrarFormularioCursos() {
        frmPrincipal fPrincipal = (frmPrincipal) this.getParent().getParent().getParent().getParent().getParent();
        fPrincipal.AbriFormularioAsignarConceptoEvento_Dialog(2, this, this.documentoPago.getCliente());
    }

    private void ValidarConcepto() {
        txtCantidad.setText("");
        txtTotal.setText("");
        String detPago = ((JTextField) cbConceptoPago.getEditor().getEditorComponent()).getText();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lConceptoPago = dpBO.ObtenerTodosConceptoPago();
        for (ConceptoPagoDetalle cd : lConceptoPago) {
            if (cd.getDescripcion().equals(detPago)) {
                if (cd.getIdConceptoPagoDetalle() == 1 || cd.getIdConceptoPagoDetalle() == 7220 || cd.getIdConceptoPagoDetalle() == 10 || cd.getIdConceptoPagoDetalle() == 5850) {
                    if ((this.documentoPago.getCliente().getTipoCliente().equals("C") && cd.getIdConceptoPagoDetalle() == 10) || (this.documentoPago.getCliente().getTipoCliente().equals("C") && cd.getIdConceptoPagoDetalle() == 5850) || (this.documentoPago.getCliente().getTipoCliente().equals("C") && cd.getIdConceptoPagoDetalle() == 1) || (this.documentoPago.getCliente().getTipoCliente().equals("S") && cd.getIdConceptoPagoDetalle() == 7220) || (this.documentoPago.getCliente().getTipoCliente().equals("S") && cd.getIdConceptoPagoDetalle() == 10)) {
                        if (this.documentoPago.getCliente().getTipoCliente().equals("C")) {
                            if (this.documentoPago.getCliente().getEstado().equals("F") || this.documentoPago.getCliente().getEstado().equals("R") || this.documentoPago.getCliente().getEstado().equals("L")) {
                                String estado = "";
                                if (this.documentoPago.getCliente().getEstado().equals("F")) {
                                    estado = "FALLECIDO";
                                }
                                if (this.documentoPago.getCliente().getEstado().equals("R")) {
                                    estado = "RETIRADO";
                                }
                                if (this.documentoPago.getCliente().getEstado().equals("L")) {
                                    estado = "LICENCIA";
                                }
                                JOptionPane.showMessageDialog(this,
                                        "EL MIEMBRO NO PUEDE REALIZAR PAGO DE CUOTAS \n SU ESTADO ACTUAL ES: " + estado,
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        if (this.documentoPago.getCliente().getTipoCliente().equals("S")) {
                            if (this.documentoPago.getCliente().getEstado().equals("R") || this.documentoPago.getCliente().getEstado().equals("L")) {
                                String estado = "";
                                if (this.documentoPago.getCliente().getEstado().equals("R")) {
                                    estado = "RETIRADO";
                                }
                                if (this.documentoPago.getCliente().getEstado().equals("L")) {
                                    estado = "LICENCIA";
                                }
                                JOptionPane.showMessageDialog(this,
                                        "LA SOCIEDAD NO PUEDE REALIZAR PAGO DE CUOTAS \n SU ESTADO ACTUAL ES: " + estado,
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        int cantidad = 0;
                        String seleccion = JOptionPane.showInputDialog("CUANTAS CUOTAS DESEA CANCELAR");
                        if (seleccion != null) {
                            try {
                                cantidad = Integer.valueOf(seleccion);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(this,
                                        "DEBE PONER UNA CANTIDAD",
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            btnAgregar.requestFocus();
                            this.ObtenerCuotas(cantidad);
                        } else {
                            txtCantidad.setText("");
                            txtTotal.setText("");
                        }
                    }

                    /*----------------------------CODIGO ADICIONAL EMPRESA PAGA COLEGIATURA CONTADOR-----------------------*/
                    if (cd.getIdConceptoPagoDetalle() == 1 && this.documentoPago.getCliente().getTipoCliente().equals("E")) {
                        String codContador = JOptionPane.showInputDialog("DIGITE EL CÓDIGO DEL MIEMBRO");
                        codContador = String.format("%05d", Integer.valueOf(codContador));
                        ClienteBO cBO = ClienteBO.getInstance();
                        Cliente cliente = cBO.ObtenerClienteSegunCodigoContador(codContador);
                        if (cliente == null) {
                            JOptionPane.showMessageDialog(this,
                                    "EL CÓDIGO NO ESTA ASOCIADO A NINGUN MIEMBRO",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            String nroCuotas = JOptionPane.showInputDialog("CUANTAS CUOTAS DESEA CANCELAR");
                            int cantidad = 0;
                            if (nroCuotas != null) {
                                try {
                                    cantidad = Integer.valueOf(nroCuotas);
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(this,
                                            "DEBE PONER UNA CANTIDAD",
                                            "ERROR",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                this.ObtenerCuotasEmpresaContador(cliente, cantidad);
                                this.documentoPago.setClienteByIdContadorEmpresa(cliente);
                                dpBO.setClienteEmpresa(cliente);
                                DocumentoPago docEmpresa = this.documentoPago;
                                docEmpresa.setClienteByIdContadorEmpresa(cliente);
                                dpBO.ActualizarCabeceraDocumento(docEmpresa);
                                btnAgregar.requestFocus();
                            }
                        }
                    }
                    /*----------------------------CODIGO ADICIONAL-----------------------*/
                } else {
                    if ((cd.getTipoCodigo() + cd.getCodigo()).equals("040000")) {
                        this.mostrarFormularioCursos();
                    } else {
                        txtCantidad.requestFocus();
                    }
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnAprobarComprobante;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGlosa;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnMail;
    private javax.swing.JButton btnMail1;
    private javax.swing.JButton btnNuevoComprobante;
    private javax.swing.JComboBox cbCodigo;
    private javax.swing.JComboBox cbConceptoPago;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCobrador;
    private javax.swing.JLabel lbCodColegiado;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDetraccion;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbEstado1;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbIgv;
    private javax.swing.JLabel lbMotivoAnulacion;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNroCodigo;
    private javax.swing.JLabel lbNroComprobante;
    private javax.swing.JLabel lbSubTotal;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tDetalle;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
