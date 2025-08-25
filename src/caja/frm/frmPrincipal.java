/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.frm;

import caja.bo.ClienteBO;
import caja.bo.SeguridadBO;
import caja.frm.administracion.dAuditorIndependiente;
import caja.frm.administracion.dContador;
import caja.frm.administracion.dSeleccionarPersona;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.Usuario;
import caja.frm.administracion.frmAlumno;
import caja.frm.administracion.frmAsignarConceptoEvento;
import caja.frm.administracion.frmAsignarConceptoEvento_Dialog;
import caja.frm.administracion.frmAsignarTipoDocumentoSerieUsuario;
import caja.frm.administracion.frmCargando1;
import caja.frm.administracion.frmClienteAuditorIndependiente;
import caja.frm.administracion.frmClienteComitePerito;
import caja.frm.administracion.frmClienteDerechoHabiente;
import caja.frm.administracion.frmClienteEstudioContableEspecialidad;
import caja.frm.administracion.frmClienteSociedadVigencia;
import caja.frm.administracion.frmCobrador;
import caja.frm.administracion.frmConceptoPagoDetalle;
import caja.frm.administracion.frmCongreso;
import caja.frm.administracion.frmCongresoConcepto;
import caja.frm.administracion.frmContador;
import caja.frm.administracion.frmContadorDetalle;
import caja.frm.administracion.frmEmpresa;
import caja.frm.administracion.frmFichaInscripcion;
import caja.frm.administracion.frmPersona;
import caja.frm.administracion.frmPersonaDatosContador;
import caja.frm.administracion.frmSociedad;
import caja.frm.administracion.frmTipoDocIdentidad;
import caja.frm.administracion.frmTipoDocumentoPago;
import caja.frm.administracion.frmUniversidad;
import caja.frm.administracion.frmUsuarios;
import caja.frm.caja.dBbvaOpciones;
import caja.frm.caja.dElegirFechaMigracion;
import caja.frm.caja.dElegirFinanciamiento;
import caja.frm.caja.dElegirPagadorComprobante;
import caja.frm.caja.dEntregaMaterial;
import caja.frm.caja.dGeneracionComprobanteBbva;
import caja.frm.caja.dGeneracionComprobanteScotiabank;
import caja.frm.caja.dGlosaPagoCredito;
import caja.frm.caja.dHistorialDetalladoContador;
import caja.frm.caja.dIncripcionCongreso1BK;
import caja.frm.caja.dIncripcionCongresoIndividual;
import caja.frm.caja.dIngresarOperacion;
import caja.frm.caja.dPeriodoBancoBBVA;
import caja.frm.caja.dPeriodoBancoScotiabank;
import caja.frm.caja.dPreguntaFinanciamiento;
import caja.frm.caja.dReincorporacionDetalle;
import caja.frm.caja.dScotiabankOpciones;
import caja.frm.caja.dVentasTipoEvento;
import caja.frm.caja.frmAsignarPartcipanteEvento_Dialog;
import caja.frm.caja.frmBBVA;
import caja.frm.caja.frmBBVAContador;
import caja.frm.caja.frmBBVARetorno;
import caja.frm.caja.frmBBVARetornoSubir;
import caja.frm.caja.frmBuscarComprobante;
import caja.frm.caja.frmCabeceraFinanciamiento;
import caja.frm.caja.frmCargando;
import caja.frm.caja.frmCerrarDia;
//import caja.frm.caja.frmComprobante;
import caja.frm.caja.frmConceptosDiversos;
import caja.frm.caja.frmCongresoListado;
import caja.frm.caja.frmCongresoRegistrarPersona;
import caja.frm.caja.frmCtaCte;
import caja.frm.caja.frmComprobantePagoDetalle;
import caja.frm.caja.frmFinanciamientos;
import caja.frm.caja.frmHistorialContador;
import caja.frm.caja.frmHistorialSociedad;
import caja.frm.caja.frmListaAsistencia;
import caja.frm.caja.frmMovimientosContador;
import caja.frm.caja.frmMovimientosEmpresa;
import caja.frm.caja.frmMovimientosPersona;
import caja.frm.caja.frmMovimientosSociedad;
import caja.frm.caja.frmNota;
import caja.frm.caja.frmNotaDetalle;
import caja.frm.caja.frmComprobantePagoCabecera;
import caja.frm.caja.frmEnvioCorreoElectronico;
import caja.frm.caja.frmModificarArticulo_NotaCredito;
import caja.frm.caja.frmMotivoAnulacion;
import caja.frm.caja.frmReporteAlumnos;
import caja.frm.caja.frmReporteCobrador;
import caja.frm.caja.frmReporteCursos;
import caja.frm.caja.frmReporteDeudaColegiados;
import caja.frm.caja.frmReporteDeudaSociedades;
import caja.frm.caja.frmReporteDiario;
import caja.frm.caja.frmReporteParticipantes;
import caja.frm.caja.frmResumenDiario;
import caja.frm.caja.frmResumenDiarioDetalle;
import caja.frm.caja.frmScotiabank;
import caja.frm.caja.frmScotiabankContador;
import caja.frm.caja.frmScotiabankRetorno;
import caja.frm.caja.frmScotiabankRetornoSubir;
import caja.frm.caja.frmSeleccionarComprobante;
import caja.frm.caja.frmSeleccionarComprobanteDetalle;
import caja.frm.caja.frmValeAcademico;
import caja.frm.caja.frmValeAcademicoDetalle;
import caja.mapeo.entidades.Bbva;
import caja.mapeo.entidades.BbvaRetorno;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteAuditorIndependiente;
import caja.mapeo.entidades.ClienteComitePerito;
import caja.mapeo.entidades.ClienteDerechoHabiente;
import caja.mapeo.entidades.ClienteEstudioContableEspecialidad;
import caja.mapeo.entidades.ClienteSociedadVigencia;
import caja.mapeo.entidades.Congreso;
import caja.mapeo.entidades.CongresoParticipantes;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.EventoConceptoPago;
import caja.mapeo.entidades.FichaDatos;
import caja.mapeo.entidades.Nota;
import caja.mapeo.entidades.ResumenDiario;
import caja.mapeo.entidades.Scotiabank;
import caja.mapeo.entidades.ScotiabankRetorno;
import caja.mapeo.entidades.ValeAcademico;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class frmPrincipal extends javax.swing.JFrame {

    private frmPersona fPersona;
    private frmContador fContador;
    private frmCobrador fCobrador;
    private frmSociedad fSociedad;
    private frmEmpresa fEmpresa;

    private frmConceptoPagoDetalle fConceptoPago;
    private frmTipoDocumentoPago fTipoDocumentoPago;
    private frmTipoDocIdentidad fTipoDocumentoIdentidad;

//    private frmComprobante fComprobante;
    private frmCargando fCargando;
    private frmCargando1 fcargando;
    private frmComprobantePagoCabecera fNuevoComprobante;
    private frmComprobantePagoDetalle fDetalleComprobante;
    private frmBuscarComprobante fBuscarComprobante;
    private frmCabeceraFinanciamiento fCabeceraFinanciamiento;
    private frmHistorialContador fHistoriaContador;
    private frmHistorialSociedad fHistoriaSociedad;
    private frmCerrarDia fCerrarDia;

    private frmReporteDiario fReporteDiario;
    private frmAlumno fAlumno;
    private frmReporteCursos fAlumnoCurso;

    private frmMovimientosContador fMovimientoContador;
    private frmMovimientosPersona fMovimientoPersona;
    private frmMovimientosSociedad fMovimientoSociedad;
    private frmMovimientosEmpresa fMovimientoEmpresa;

    private frmCtaCte fCtaCte;
    private frmReporteCobrador fReporteCobrador;
    private frmConceptosDiversos fConceptosDiversos;
    private frmReporteAlumnos fReporteAlumnos;
    private frmListaAsistencia fListaAsistencia;
    private frmFinanciamientos fFinanciamientos;
    private frmCongreso fCongreso;
    private frmCongresoConcepto fCongresoConcepto;
    private frmCongresoListado fCongresoListado;
    private frmReporteDeudaColegiados fDeudaColegiados;
    private frmBBVARetornoSubir fBBVARetornoSubir;
    private frmBBVARetorno fBBVARetorno;
    private frmBBVAContador fBbvaContador;
    private frmBBVA fBbva;
    private frmScotiabank fScotiabank;
    private frmScotiabankContador fScotiabankContador;
    private frmScotiabankRetornoSubir fScotiabankRetornoSubir;
    private frmScotiabankRetorno fScotiabankRetorno;
    private frmNotaDetalle fNotaDetalle;
    private frmResumenDiarioDetalle fResumenDiarioDetalle;
    private frmResumenDiario fResumenDiario;
    private frmNota fNota;
    private frmReporteDeudaSociedades fDeudaSociedades;
    private frmAsignarConceptoEvento fAsignarConceptoEvento;
    private frmReporteParticipantes fReporteParticipantes;
    private frmValeAcademico fValeAcademico;
    private frmValeAcademicoDetalle fValeAcademicoDetalle;
    private frmUniversidad fUniversidad;

    private frmUsuarios fUsuarios;
    private frmFichaInscripcion fFichaInscripcion;

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/caja/images/icono.png")).getImage());
        this.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
        int x = (dpPrincipal.getWidth() - lbLogo.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - lbLogo.getHeight()) / 2;
        lbLogo.setLocation(x, y);
        lbLogo.setVisible(true);
        jmAdministracion.setMnemonic(KeyEvent.VK_A);
        jmReportes.setMnemonic(KeyEvent.VK_R);
        jmTransacciones.setMnemonic(KeyEvent.VK_T);
        jmCursos.setMnemonic(KeyEvent.VK_C);
        SeguridadBO sBO = SeguridadBO.getInstance();
        Usuario u = sBO.InicializarDatosUsuario();
        lbNombreUsuario.setText("USUARIO: " + u.getCliente().getPapePat() + " " + u.getCliente().getPapeMat() + " " + u.getCliente().getPnombre());
        jMenuItem4.setEnabled(false);
        jmCongreso.setVisible(false);
        String rol = sBO.getUsuario().getRolUsuario();
        if (rol.equals("CAJA")) {
            jmAdministracion.setEnabled(false);
            jmCongreso.setEnabled(false);
            jmCursos.setEnabled(false);
            btnParticipantesCongreso.setEnabled(false);
            jmFinanciamiento.setEnabled(false);
            jmCerrarDia.setEnabled(false);
        }
        if (rol.equals("CONTROL")) {
            jmAdministracion.setEnabled(false);
            jmReportes.setEnabled(false);
            jmTransacciones.setEnabled(false);
            jmCursos.setEnabled(false);
            btnNuevoComprobante.setEnabled(false);
        }
        if (rol.equals("USUARIO")) {
            jmAdministracion.setEnabled(true);
            jMenuItem9.setEnabled(false);
            jMenuItem7.setEnabled(false);
            jMenuItem4.setEnabled(false);
            jMenuItem1.setEnabled(false);
            jmUsuarios.setEnabled(false);
            jmCobrador.setEnabled(false);
//            jmReportes.setEnabled(false);
//            jmTransacciones.setEnabled(false);
            jMenuItem6.setEnabled(true);
            jmCerrarDia.setEnabled(false);
            jmFinanciamiento.setEnabled(false);
            jmComprobantes.setEnabled(false);
            jmValidarHabilitacion.setEnabled(false);
            jMenu1.setEnabled(false);
            jmCursos.setEnabled(true);
            jmAlumnos.setEnabled(false);
            btnNuevoComprobante.setEnabled(false);
            btnParticipantesCongreso.setEnabled(false);
        }
    }

    public void AgregarFormulario(frmCargando fCargando) {
        this.dpPrincipal.add(fCargando);
    }

    public void AgregarFormulario1(frmCargando1 fCargando) {
        this.dpPrincipal.add(fCargando);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        dpPrincipal = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lbNombreUsuario = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        tbAccesoDirecto = new javax.swing.JToolBar();
        btnNuevoComprobante = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnParticipantesCongreso = new javax.swing.JButton();
        jmPrincipal = new javax.swing.JMenuBar();
        jmAdministracion = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jmPersona = new javax.swing.JMenuItem();
        jmContador = new javax.swing.JMenuItem();
        jmCobrador = new javax.swing.JMenuItem();
        jmEmpresa = new javax.swing.JMenuItem();
        jmSociedad = new javax.swing.JMenuItem();
        jmUniversidad = new javax.swing.JMenuItem();
        jmUsuarios = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jmReportes = new javax.swing.JMenu();
        jmMovimientosContador = new javax.swing.JMenuItem();
        jmMovimientosPersona = new javax.swing.JMenuItem();
        jmMovimientosSociedad = new javax.swing.JMenuItem();
        jmMovimientosEmpresa = new javax.swing.JMenuItem();
        jmReporteCobrador = new javax.swing.JMenuItem();
        jmConceptosDiversos = new javax.swing.JMenuItem();
        jmVentasTipoEvento = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jmTransacciones = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jmCerrarDia = new javax.swing.JMenuItem();
        jmFinanciamiento = new javax.swing.JMenuItem();
        jmComprobantes = new javax.swing.JMenuItem();
        jmBuscarComprobantes = new javax.swing.JMenuItem();
        jmHistorial = new javax.swing.JMenuItem();
        jmHistorialSociedad = new javax.swing.JMenuItem();
        jmValidarHabilitacion = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmEstructuraDatosCajaArequipa = new javax.swing.JMenuItem();
        jmEstructuraDatosBBVA = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jmCursos = new javax.swing.JMenu();
        jmAlumnos = new javax.swing.JMenuItem();
        jmBuscarAlumnos = new javax.swing.JMenuItem();
        jmReporteAlumnos = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jmCongreso = new javax.swing.JMenu();
        jmParticipantes = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenu8.setText("jMenu8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MODULO DE CAJA");

        dpPrincipal.setBackground(new java.awt.Color(0, 102, 153));

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        jLabel1.setText("COLEGIO DE CONTADORES PUBLICO DE AREQUIPA ");
        jToolBar1.add(jLabel1);
        jToolBar1.add(jSeparator1);

        lbNombreUsuario.setText(" USUARIO: VÍCTOR ZAMATA GARCÍA");
        jToolBar1.add(lbNombreUsuario);

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/logo1.png"))); // NOI18N
        lbLogo.setAlignmentY(0.0F);

        tbAccesoDirecto.setRollover(true);

        btnNuevoComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/Nuevo Comprobante.png"))); // NOI18N
        btnNuevoComprobante.setFocusable(false);
        btnNuevoComprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoComprobante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoComprobanteActionPerformed(evt);
            }
        });
        tbAccesoDirecto.add(btnNuevoComprobante);
        tbAccesoDirecto.add(jSeparator2);

        btnParticipantesCongreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/check.png"))); // NOI18N
        btnParticipantesCongreso.setFocusable(false);
        btnParticipantesCongreso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnParticipantesCongreso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnParticipantesCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParticipantesCongresoActionPerformed(evt);
            }
        });
        tbAccesoDirecto.add(btnParticipantesCongreso);

        dpPrincipal.setLayer(jToolBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(lbLogo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(tbAccesoDirecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpPrincipalLayout = new javax.swing.GroupLayout(dpPrincipal);
        dpPrincipal.setLayout(dpPrincipalLayout);
        dpPrincipalLayout.setHorizontalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1169, Short.MAX_VALUE)
            .addComponent(lbLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbAccesoDirecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dpPrincipalLayout.setVerticalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                .addComponent(tbAccesoDirecto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jmAdministracion.setText("ADMINISTRACION");

        jMenuItem17.setText("FICHA PRE-INSCRIPCION");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jmAdministracion.add(jMenuItem17);

        jmPersona.setText("PERSONA");
        jmPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPersonaActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmPersona);

        jmContador.setText("CONTADOR");
        jmContador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmContadorActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmContador);

        jmCobrador.setText("COBRADOR");
        jmCobrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCobradorActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmCobrador);

        jmEmpresa.setText("EMPRESA");
        jmEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEmpresaActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmEmpresa);

        jmSociedad.setText("SOCIEDAD");
        jmSociedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSociedadActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmSociedad);

        jmUniversidad.setText("UNIVERSIDAD");
        jmUniversidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUniversidadActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmUniversidad);

        jmUsuarios.setText("USUARIOS DEL SISTEMA");
        jmUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUsuariosActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmUsuarios);

        jMenuItem1.setText("TIPO DE COMPROBANTE");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmAdministracion.add(jMenuItem1);

        jMenuItem3.setText("CONCEPTO PAGO");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jmAdministracion.add(jMenuItem3);

        jMenuItem4.setText("DOCUMENTO DE IDENTIDAD");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jmAdministracion.add(jMenuItem4);

        jMenuItem7.setText("CONGRESO");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jmAdministracion.add(jMenuItem7);

        jMenuItem9.setText("ASIGNAR CONCEPTOS - CONGRESO");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jmAdministracion.add(jMenuItem9);

        jMenuItem13.setText("ASIGNAR CONCEPTOS - EVENTO");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jmAdministracion.add(jMenuItem13);

        jmPrincipal.add(jmAdministracion);

        jmReportes.setText("REPORTES");

        jmMovimientosContador.setText("MOVIMIENTOS CONTADOR");
        jmMovimientosContador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMovimientosContadorActionPerformed(evt);
            }
        });
        jmReportes.add(jmMovimientosContador);

        jmMovimientosPersona.setText("MOVIMIENTOS PERSONA");
        jmMovimientosPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMovimientosPersonaActionPerformed(evt);
            }
        });
        jmReportes.add(jmMovimientosPersona);

        jmMovimientosSociedad.setText("MOVIMIENTOS SOCIEDAD");
        jmMovimientosSociedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMovimientosSociedadActionPerformed(evt);
            }
        });
        jmReportes.add(jmMovimientosSociedad);

        jmMovimientosEmpresa.setText("MOVIMIENTOS EMPRESA");
        jmMovimientosEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMovimientosEmpresaActionPerformed(evt);
            }
        });
        jmReportes.add(jmMovimientosEmpresa);

        jmReporteCobrador.setText("REPORTE PARA COBRADORES");
        jmReporteCobrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmReporteCobradorActionPerformed(evt);
            }
        });
        jmReportes.add(jmReporteCobrador);

        jmConceptosDiversos.setText("REPORTE CONCEPTOS VARIOS");
        jmConceptosDiversos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmConceptosDiversosActionPerformed(evt);
            }
        });
        jmReportes.add(jmConceptosDiversos);

        jmVentasTipoEvento.setText("VENTAS TIPO EVENTO");
        jmVentasTipoEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVentasTipoEventoActionPerformed(evt);
            }
        });
        jmReportes.add(jmVentasTipoEvento);

        jMenuItem5.setText("REPORTE DE PAGOS");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jmReportes.add(jMenuItem5);

        jMenuItem10.setText("REPORTE DEUDA COLEGIADOS");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jmReportes.add(jMenuItem10);

        jMenuItem12.setText("REPORTE DEUDA SOCIEDADES");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jmReportes.add(jMenuItem12);

        jmPrincipal.add(jmReportes);

        jmTransacciones.setText("TRANSACCIONES");
        jmTransacciones.setToolTipText("");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/iconFinanciamiento.png"))); // NOI18N
        jMenuItem6.setText("LISTADO FINANCIAMIENTOS");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jmTransacciones.add(jMenuItem6);

        jmCerrarDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/iconCandado.png"))); // NOI18N
        jmCerrarDia.setText("CERRAR CAJA");
        jmCerrarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCerrarDiaActionPerformed(evt);
            }
        });
        jmTransacciones.add(jmCerrarDia);

        jmFinanciamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/iconFinanciamiento.png"))); // NOI18N
        jmFinanciamiento.setText("NUEVO FINANCIAMIENTO");
        jmFinanciamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFinanciamientoActionPerformed(evt);
            }
        });
        jmTransacciones.add(jmFinanciamiento);

        jmComprobantes.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jmComprobantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/Nuevo Comprobante.png"))); // NOI18N
        jmComprobantes.setText("COMPROBANTE DE PAGO");
        jmComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmComprobantesActionPerformed(evt);
            }
        });
        jmTransacciones.add(jmComprobantes);

        jmBuscarComprobantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/iconBuscar.png"))); // NOI18N
        jmBuscarComprobantes.setText("BUSCAR COMPROBANTES");
        jmBuscarComprobantes.setToolTipText("");
        jmBuscarComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBuscarComprobantesActionPerformed(evt);
            }
        });
        jmTransacciones.add(jmBuscarComprobantes);

        jmHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/iconHistorial.png"))); // NOI18N
        jmHistorial.setText("HISTORIAL CONTADOR");
        jmHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmHistorialActionPerformed(evt);
            }
        });
        jmTransacciones.add(jmHistorial);

        jmHistorialSociedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/iconHistorial.png"))); // NOI18N
        jmHistorialSociedad.setText("HISTORIAL SOCIEDAD");
        jmHistorialSociedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmHistorialSociedadActionPerformed(evt);
            }
        });
        jmTransacciones.add(jmHistorialSociedad);

        jmValidarHabilitacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/Validacion.png"))); // NOI18N
        jmValidarHabilitacion.setText("VALIDAR HABILITACIÓN");
        jmValidarHabilitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmValidarHabilitacionActionPerformed(evt);
            }
        });
        jmTransacciones.add(jmValidarHabilitacion);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/txt.png"))); // NOI18N
        jMenu1.setText("ESTRUCTURA DATOS COBRANZA INSTITUCIONAL");

        jmEstructuraDatosCajaArequipa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/caja_arequipa.png"))); // NOI18N
        jmEstructuraDatosCajaArequipa.setText("CAJA AREQUIPA");
        jmEstructuraDatosCajaArequipa.setEnabled(false);
        jmEstructuraDatosCajaArequipa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEstructuraDatosCajaArequipaActionPerformed(evt);
            }
        });
        jMenu1.add(jmEstructuraDatosCajaArequipa);

        jmEstructuraDatosBBVA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/bbva.png"))); // NOI18N
        jmEstructuraDatosBBVA.setText("BBVA");
        jmEstructuraDatosBBVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEstructuraDatosBBVAActionPerformed(evt);
            }
        });
        jMenu1.add(jmEstructuraDatosBBVA);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/scotiabank.png"))); // NOI18N
        jMenuItem11.setText("SCOTIABANK");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jmTransacciones.add(jMenu1);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/nota01.png"))); // NOI18N
        jMenuItem8.setText("NOTA CREDITO / DEBITO");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jmTransacciones.add(jMenuItem8);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/resumenDiario.png"))); // NOI18N
        jMenuItem15.setText("RESUMEN DIARIO");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jmTransacciones.add(jMenuItem15);

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/vale_academico.png"))); // NOI18N
        jMenuItem16.setText("VALE ACADEMICO");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jmTransacciones.add(jMenuItem16);

        jmPrincipal.add(jmTransacciones);

        jmCursos.setText("CURSOS");

        jmAlumnos.setText("NUEVOS ALUMNOS");
        jmAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAlumnosActionPerformed(evt);
            }
        });
        jmCursos.add(jmAlumnos);

        jmBuscarAlumnos.setText("BUSCAR ALUMNOS");
        jmBuscarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBuscarAlumnosActionPerformed(evt);
            }
        });
        jmCursos.add(jmBuscarAlumnos);

        jmReporteAlumnos.setText("REPORTE ALUMNOS");
        jmReporteAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmReporteAlumnosActionPerformed(evt);
            }
        });
        jmCursos.add(jmReporteAlumnos);

        jMenuItem14.setText("REPORTE EVENTOS");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jmCursos.add(jMenuItem14);

        jmPrincipal.add(jmCursos);

        jmCongreso.setText("CONGRESO");

        jmParticipantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja/images/check.png"))); // NOI18N
        jmParticipantes.setText("PARTICIPANTES");
        jmParticipantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmParticipantesActionPerformed(evt);
            }
        });
        jmCongreso.add(jmParticipantes);

        jmPrincipal.add(jmCongreso);

        setJMenuBar(jmPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void AbrirFormularioPreguntaFinanciamiento() {
        dPreguntaFinanciamiento dialogPreguntaFinanciamiento = new dPreguntaFinanciamiento(this, true);
        int x = (this.dpPrincipal.getWidth() - dialogPreguntaFinanciamiento.getWidth()) / 2;
        int y = (this.dpPrincipal.getHeight() - dialogPreguntaFinanciamiento.getHeight()) / 2;
        dialogPreguntaFinanciamiento.setLocation(x, y + 43);
        dialogPreguntaFinanciamiento.setVisible(true);
    }

    public void LlenarDatosFinanciamiento(int idTipoDoc, String nroSerie, int diasProrroga, int nroDocu, String fecha) {
        fCabeceraFinanciamiento.setIdTipoDocPag(idTipoDoc);
        fCabeceraFinanciamiento.setNroDocumento(nroDocu);
        fCabeceraFinanciamiento.setFechaDocumento(fecha);
        fCabeceraFinanciamiento.setNroSerie(nroSerie);
        fCabeceraFinanciamiento.setDiasProrroga(diasProrroga);
    }

    public void abrirFormularioSeleccionarComprobante(Object frm, int tc) {
        frmSeleccionarComprobante d = new frmSeleccionarComprobante(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 55);
        d.CargarDatos(frm, tc);
        d.setVisible(true);
    }

    public void abrirFormularioSeleccionarComprobanteDetalle(Object frm, int tc, DocumentoPago dp) {
        frmSeleccionarComprobanteDetalle d = new frmSeleccionarComprobanteDetalle(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 55);
        d.cargarDatos(frm, tc, dp);
        d.setVisible(true);
    }

    private void CargarFormularioPagos() {
        /*JInternalFrame[] activos = dpPrincipal.getAllFrames();
         boolean cerrado = true;
         int i = 0;
         while (i < activos.length && cerrado) {
         if (activos[i] == fPagos) {
         cerrado = false;
         }
         i++;
         }

         if (cerrado) {
         fPagos = new frmPagos();
         dpPrincipal.add(fPagos);
         int x = (dpPrincipal.getWidth() - fPagos.getWidth()) / 2;
         int y = (dpPrincipal.getHeight() - fPagos.getHeight()) / 2;
         fPagos.setLocation(x, y);
         fPagos.setVisible(true);
         }
         fPagos.toFront();*/
    }

    public void CargarFormularioListadoFinanciamiento() {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fFinanciamientos) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fFinanciamientos = new frmFinanciamientos();
            dpPrincipal.add(fFinanciamientos);
            int x = (dpPrincipal.getWidth() - fFinanciamientos.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fFinanciamientos.getHeight()) / 2;
            fFinanciamientos.setLocation(x, y);
            fFinanciamientos.setVisible(true);
        }
        fFinanciamientos.toFront();
    }

    public void AbrirFormularioNotaDetalle(Nota nota) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fNotaDetalle) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fNotaDetalle = new frmNotaDetalle();
            dpPrincipal.add(fNotaDetalle);
            int x = (dpPrincipal.getWidth() - fNotaDetalle.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fNotaDetalle.getHeight()) / 2;
            fNotaDetalle.setLocation(x, y);
            fNotaDetalle.setVisible(true);
        }
        fNotaDetalle.CargarDatos(nota);
        fNotaDetalle.toFront();
    }

    public void AbrirFormularioValeAcademicoDetalle(ValeAcademico va) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fValeAcademicoDetalle) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fValeAcademicoDetalle = new frmValeAcademicoDetalle();
            dpPrincipal.add(fNotaDetalle);
            int x = (dpPrincipal.getWidth() - fValeAcademicoDetalle.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fValeAcademicoDetalle.getHeight()) / 2;
            fValeAcademicoDetalle.setLocation(x, y);
            fValeAcademicoDetalle.setVisible(true);
        }
        fValeAcademicoDetalle.cargarDatos(va);
        fValeAcademicoDetalle.toFront();
    }

    public void AbrirFormularioValeAcademico(ValeAcademico va) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fValeAcademicoDetalle) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fValeAcademicoDetalle = new frmValeAcademicoDetalle();
            dpPrincipal.add(fValeAcademicoDetalle);
            int x = (dpPrincipal.getWidth() - fValeAcademicoDetalle.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fValeAcademicoDetalle.getHeight()) / 2;
            fValeAcademicoDetalle.setLocation(x, y);
            fValeAcademicoDetalle.setVisible(true);
        }
        fValeAcademicoDetalle.cargarDatos(va);
        fValeAcademicoDetalle.toFront();
    }

    public void AbrirFormularioResumenDiarioDetalle(ResumenDiario r) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fResumenDiarioDetalle) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fResumenDiarioDetalle = new frmResumenDiarioDetalle();
            dpPrincipal.add(fResumenDiarioDetalle);
            int x = (dpPrincipal.getWidth() - fResumenDiarioDetalle.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fResumenDiarioDetalle.getHeight()) / 2;
            fResumenDiarioDetalle.setLocation(x, y);
            fResumenDiarioDetalle.setVisible(true);
        }
        fResumenDiarioDetalle.CargarDatos(r);
        fResumenDiarioDetalle.toFront();
    }

    public void CargarFormularioHistorico() {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fHistoriaContador) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fHistoriaContador = new frmHistorialContador();
            dpPrincipal.add(fHistoriaContador);
            int x = (dpPrincipal.getWidth() - fHistoriaContador.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fHistoriaContador.getHeight()) / 2;
            fHistoriaContador.setLocation(x, y);
            fHistoriaContador.setVisible(true);
        }
        fHistoriaContador.toFront();
    }

    private void jmPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPersonaActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fPersona) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fPersona = new frmPersona();
            dpPrincipal.add(fPersona);
            int x = (dpPrincipal.getWidth() - fPersona.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fPersona.getHeight()) / 2;
            fPersona.setLocation(x, y);
            fPersona.setVisible(true);

        }
    }//GEN-LAST:event_jmPersonaActionPerformed

    private void jmContadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmContadorActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fContador) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fContador = new frmContador();
            dpPrincipal.add(fContador);
            int x = (dpPrincipal.getWidth() - fContador.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fContador.getHeight()) / 2;
            fContador.setLocation(x, y);
            fContador.setVisible(true);

        }
    }//GEN-LAST:event_jmContadorActionPerformed

    private void jmCobradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCobradorActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fCobrador) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fCobrador = new frmCobrador();
            dpPrincipal.add(fCobrador);
            int x = (dpPrincipal.getWidth() - fCobrador.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fCobrador.getHeight()) / 2;
            fCobrador.setLocation(x, y);
            fCobrador.setVisible(true);

        }
    }//GEN-LAST:event_jmCobradorActionPerformed

    private void jmEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEmpresaActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fEmpresa) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fEmpresa = new frmEmpresa();
            dpPrincipal.add(fEmpresa);
            int x = (dpPrincipal.getWidth() - fEmpresa.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fEmpresa.getHeight()) / 2;
            fEmpresa.setLocation(x, y);
            fEmpresa.setVisible(true);

        }
    }//GEN-LAST:event_jmEmpresaActionPerformed

    private void jmSociedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSociedadActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fSociedad) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fSociedad = new frmSociedad();
            dpPrincipal.add(fSociedad);
            int x = (dpPrincipal.getWidth() - fSociedad.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fSociedad.getHeight()) / 2;
            fSociedad.setLocation(x, y);
            fSociedad.setVisible(true);

        }
    }//GEN-LAST:event_jmSociedadActionPerformed

    private void jmCerrarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCerrarDiaActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fCerrarDia) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fCerrarDia = new frmCerrarDia();
            dpPrincipal.add(fCerrarDia);
            int x = (dpPrincipal.getWidth() - fCerrarDia.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fCerrarDia.getHeight()) / 2;
            fCerrarDia.setLocation(x, y);
            fCerrarDia.setVisible(true);
        }
        fCerrarDia.toFront();
    }//GEN-LAST:event_jmCerrarDiaActionPerformed

    private void jmFinanciamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFinanciamientoActionPerformed
        dElegirFinanciamiento dialogFinanciamiento = new dElegirFinanciamiento(this, true);
        int x = (dpPrincipal.getWidth() - dialogFinanciamiento.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogFinanciamiento.getHeight()) / 2;
        dialogFinanciamiento.setLocation(x, y);
        dialogFinanciamiento.setVisible(true);
    }//GEN-LAST:event_jmFinanciamientoActionPerformed

    public void AbrirFormularioNuevoComprobante() {
        dElegirPagadorComprobante dialogPagador = new dElegirPagadorComprobante(this, true);
        int x = (dpPrincipal.getWidth() - dialogPagador.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogPagador.getHeight()) / 2;
        dialogPagador.setLocation(x, y);
        dialogPagador.setVisible(true);
    }

    public void AbriDialogoGlosa() {
        dGlosaPagoCredito dialogGlosa = new dGlosaPagoCredito(this, true);
        int x = (dpPrincipal.getWidth() - dialogGlosa.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogGlosa.getHeight()) / 2;
        dialogGlosa.setLocation(x, y);
        dialogGlosa.setVisible(true);
    }

    public void AbriFormularioCorreoElectronico(DocumentoPago dp) {
        frmEnvioCorreoElectronico d = new frmEnvioCorreoElectronico(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y);
        d.CargarDatos(dp);
        d.setVisible(true);
    }

    public void AbriFormularioMotivoAnulacion(Object frm, DocumentoPago dp) {
        frmMotivoAnulacion d = new frmMotivoAnulacion(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.CargarDatos(frm, dp);
        d.setLocation(x, y);
        d.setVisible(true);
    }

    public void AbriFormularioAsignarConceptoEvento_Dialog(int tc, Object frm, Cliente c) {
        frmAsignarConceptoEvento_Dialog d = new frmAsignarConceptoEvento_Dialog(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.cargarDatos(tc, frm, c);
        d.setLocation(x, y);
        d.setVisible(true);
    }

    public void AgregarGlosa(String nroDoc) {
        fDetalleComprobante.AgregarGlosa(nroDoc);
    }

    private void jmComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmComprobantesActionPerformed
        dElegirPagadorComprobante dialogPagador = new dElegirPagadorComprobante(this, true);
        int x = (dpPrincipal.getWidth() - dialogPagador.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogPagador.getHeight()) / 2;
        dialogPagador.setLocation(x, y);
        dialogPagador.setVisible(true);
    }//GEN-LAST:event_jmComprobantesActionPerformed

    private void jmBuscarComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmBuscarComprobantesActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fBuscarComprobante) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fBuscarComprobante = new frmBuscarComprobante();
            dpPrincipal.add(fBuscarComprobante);
            int x = (dpPrincipal.getWidth() - fBuscarComprobante.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fBuscarComprobante.getHeight()) / 2;
            fBuscarComprobante.setLocation(x, y);
            fBuscarComprobante.setVisible(true);
        }
        fBuscarComprobante.toFront();
    }//GEN-LAST:event_jmBuscarComprobantesActionPerformed

    public void CargarFormularioHistoricoSociedad() {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fHistoriaSociedad) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fHistoriaSociedad = new frmHistorialSociedad();
            dpPrincipal.add(fHistoriaSociedad);
            int x = (dpPrincipal.getWidth() - fHistoriaSociedad.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fHistoriaSociedad.getHeight()) / 2;
            fHistoriaSociedad.setLocation(x, y);
            fHistoriaSociedad.setVisible(true);
        }
        fHistoriaSociedad.toFront();
    }


    private void jmHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmHistorialActionPerformed
        Thread queryThread = new Thread() {
            public void run() {
                frmCargando fCargando = new frmCargando();
                AgregarFormulario(fCargando);
                int x = (getWidth() - fCargando.getWidth()) / 2;
                int y = (getHeight() - fCargando.getHeight()) / 2;
                fCargando.setLocation(x, y);
                fCargando.setVisible(true);
                fCargando.toFront();
                CargarFormularioHistorico();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_jmHistorialActionPerformed

    private void jmHistorialSociedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmHistorialSociedadActionPerformed
        Thread queryThread = new Thread() {
            public void run() {
                frmCargando fCargando = new frmCargando();
                AgregarFormulario(fCargando);
                int x = (getWidth() - fCargando.getWidth()) / 2;
                int y = (getHeight() - fCargando.getHeight()) / 2;
                fCargando.setLocation(x, y);
                fCargando.setVisible(true);
                fCargando.toFront();
                CargarFormularioHistoricoSociedad();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_jmHistorialSociedadActionPerformed

    private void jmValidarHabilitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmValidarHabilitacionActionPerformed
        Thread queryThread = new Thread() {
            public void run() {
                frmCargando fCargando = new frmCargando();
                AgregarFormulario(fCargando);
                int x = (getWidth() - fCargando.getWidth()) / 2;
                int y = (getHeight() - fCargando.getHeight()) / 2;
                fCargando.setLocation(x, y);
                fCargando.setVisible(true);
                fCargando.toFront();
                ClienteBO cBO = ClienteBO.getInstance();
                cBO.ValidarHabilitacionTodosMiembros();
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_jmValidarHabilitacionActionPerformed

    private void jmAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlumnosActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fAlumno) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fAlumno = new frmAlumno();
            dpPrincipal.add(fAlumno);
            int x = (dpPrincipal.getWidth() - fAlumno.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fAlumno.getHeight()) / 2;
            fAlumno.setLocation(x, y);
            fAlumno.setVisible(true);
        }
        fAlumno.toFront();
    }//GEN-LAST:event_jmAlumnosActionPerformed

    private void jmBuscarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmBuscarAlumnosActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fAlumnoCurso) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fAlumnoCurso = new frmReporteCursos();
            dpPrincipal.add(fAlumnoCurso);
            int x = (dpPrincipal.getWidth() - fAlumnoCurso.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fAlumnoCurso.getHeight()) / 2;
            fAlumnoCurso.setLocation(x, y);
            fAlumnoCurso.setVisible(true);
        }
        fAlumnoCurso.toFront();
    }//GEN-LAST:event_jmBuscarAlumnosActionPerformed

    private void jmMovimientosContadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMovimientosContadorActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fMovimientoContador) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fMovimientoContador = new frmMovimientosContador();
            dpPrincipal.add(fMovimientoContador);
            int x = (dpPrincipal.getWidth() - fMovimientoContador.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fMovimientoContador.getHeight()) / 2;
            fMovimientoContador.setLocation(x, y);
            fMovimientoContador.setVisible(true);
        }
        fMovimientoContador.toFront();
    }//GEN-LAST:event_jmMovimientosContadorActionPerformed

    private void jmMovimientosPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMovimientosPersonaActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fMovimientoPersona) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fMovimientoPersona = new frmMovimientosPersona();
            dpPrincipal.add(fMovimientoPersona);
            int x = (dpPrincipal.getWidth() - fMovimientoPersona.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fMovimientoPersona.getHeight()) / 2;
            fMovimientoPersona.setLocation(x, y);
            fMovimientoPersona.setVisible(true);
        }
        fMovimientoPersona.toFront();
    }//GEN-LAST:event_jmMovimientosPersonaActionPerformed

    private void jmMovimientosSociedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMovimientosSociedadActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fMovimientoSociedad) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fMovimientoSociedad = new frmMovimientosSociedad();
            dpPrincipal.add(fMovimientoSociedad);
            int x = (dpPrincipal.getWidth() - fMovimientoSociedad.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fMovimientoSociedad.getHeight()) / 2;
            fMovimientoSociedad.setLocation(x, y);
            fMovimientoSociedad.setVisible(true);
        }
        fMovimientoSociedad.toFront();
    }//GEN-LAST:event_jmMovimientosSociedadActionPerformed

    private void jmMovimientosEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMovimientosEmpresaActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fMovimientoEmpresa) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fMovimientoEmpresa = new frmMovimientosEmpresa();
            dpPrincipal.add(fMovimientoEmpresa);
            int x = (dpPrincipal.getWidth() - fMovimientoEmpresa.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fMovimientoEmpresa.getHeight()) / 2;
            fMovimientoEmpresa.setLocation(x, y);
            fMovimientoEmpresa.setVisible(true);
        }
        fMovimientoEmpresa.toFront();
    }//GEN-LAST:event_jmMovimientosEmpresaActionPerformed

    private void jmUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUsuariosActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fUsuarios) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fUsuarios = new frmUsuarios();
            dpPrincipal.add(fUsuarios);
            int x = (dpPrincipal.getWidth() - fUsuarios.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fUsuarios.getHeight()) / 2;
            fUsuarios.setLocation(x, y);
            fUsuarios.setVisible(true);
        }
        fUsuarios.toFront();
    }//GEN-LAST:event_jmUsuariosActionPerformed

    private void jmReporteCobradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmReporteCobradorActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fReporteCobrador) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fReporteCobrador = new frmReporteCobrador();
            dpPrincipal.add(fReporteCobrador);
            int x = (dpPrincipal.getWidth() - fReporteCobrador.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fReporteCobrador.getHeight()) / 2;
            fReporteCobrador.setLocation(x, y);
            fReporteCobrador.setVisible(true);
        }
        fReporteCobrador.toFront();
    }//GEN-LAST:event_jmReporteCobradorActionPerformed

    private void jmConceptosDiversosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmConceptosDiversosActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fConceptosDiversos) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fConceptosDiversos = new frmConceptosDiversos();
            dpPrincipal.add(fConceptosDiversos);
            int x = (dpPrincipal.getWidth() - fConceptosDiversos.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fConceptosDiversos.getHeight()) / 2;
            fConceptosDiversos.setLocation(x, y);
            fConceptosDiversos.setVisible(true);
        }
        fConceptosDiversos.toFront();
    }//GEN-LAST:event_jmConceptosDiversosActionPerformed

    private void jmReporteAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmReporteAlumnosActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fReporteAlumnos) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fReporteAlumnos = new frmReporteAlumnos();
            dpPrincipal.add(fReporteAlumnos);
            int x = (dpPrincipal.getWidth() - fReporteAlumnos.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fReporteAlumnos.getHeight()) / 2;
            fReporteAlumnos.setLocation(x, y);
            fReporteAlumnos.setVisible(true);
        }
        fReporteAlumnos.toFront();
    }//GEN-LAST:event_jmReporteAlumnosActionPerformed

    private void jmVentasTipoEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVentasTipoEventoActionPerformed
        dVentasTipoEvento dialogVentas = new dVentasTipoEvento(this, true);
        int x = (dpPrincipal.getWidth() - dialogVentas.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogVentas.getHeight()) / 2;
        dialogVentas.setLocation(x, y + 43);
        dialogVentas.setVisible(true);
    }//GEN-LAST:event_jmVentasTipoEventoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fTipoDocumentoPago) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fTipoDocumentoPago = new frmTipoDocumentoPago();
            dpPrincipal.add(fTipoDocumentoPago);
            int x = (dpPrincipal.getWidth() - fTipoDocumentoPago.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fTipoDocumentoPago.getHeight()) / 2;
            fTipoDocumentoPago.setLocation(x, y);
            fTipoDocumentoPago.setVisible(true);
            fTipoDocumentoPago.toFront();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fConceptoPago) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fConceptoPago = new frmConceptoPagoDetalle();
            dpPrincipal.add(fConceptoPago);
            int x = (dpPrincipal.getWidth() - fConceptoPago.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fConceptoPago.getHeight()) / 2;
            fConceptoPago.setLocation(x, y);
            fConceptoPago.setVisible(true);
            fConceptoPago.toFront();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fTipoDocumentoIdentidad) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fTipoDocumentoIdentidad = new frmTipoDocIdentidad();
            dpPrincipal.add(fTipoDocumentoIdentidad);
            int x = (dpPrincipal.getWidth() - fTipoDocumentoIdentidad.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fTipoDocumentoIdentidad.getHeight()) / 2;
            fTipoDocumentoIdentidad.setLocation(x, y);
            fTipoDocumentoIdentidad.setVisible(true);
            fTipoDocumentoIdentidad.toFront();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fReporteDiario) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fReporteDiario = new frmReporteDiario();
            dpPrincipal.add(fReporteDiario);
            int x = (dpPrincipal.getWidth() - fReporteDiario.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fReporteDiario.getHeight()) / 2;
            fReporteDiario.setLocation(x, y);
            fReporteDiario.setVisible(true);
        }
        fReporteDiario.toFront();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fFinanciamientos) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fFinanciamientos = new frmFinanciamientos();
            dpPrincipal.add(fFinanciamientos);
            int x = (dpPrincipal.getWidth() - fFinanciamientos.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fFinanciamientos.getHeight()) / 2;
            fFinanciamientos.setLocation(x, y);
            fFinanciamientos.setVisible(true);
        }
        fFinanciamientos.toFront();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fCongreso) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fCongreso = new frmCongreso();
            dpPrincipal.add(fCongreso);
            int x = (dpPrincipal.getWidth() - fCongreso.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fCongreso.getHeight()) / 2;
            fCongreso.setLocation(x, y);
            fCongreso.setVisible(true);
        }
        fCongreso.toFront();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fCongresoConcepto) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fCongresoConcepto = new frmCongresoConcepto();
            dpPrincipal.add(fCongresoConcepto);
            int x = (dpPrincipal.getWidth() - fCongresoConcepto.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fCongresoConcepto.getHeight()) / 2;
            fCongresoConcepto.setLocation(x, y);
            fCongresoConcepto.setVisible(true);
        }
        fCongresoConcepto.toFront();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jmParticipantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmParticipantesActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fCongresoListado) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fCongresoListado = new frmCongresoListado();
            dpPrincipal.add(fCongresoListado);
            int x = (dpPrincipal.getWidth() - fCongresoListado.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fCongresoListado.getHeight()) / 2;
            fCongresoListado.setLocation(x, y);
            fCongresoListado.setVisible(true);
        }
        fCongresoListado.toFront();
    }//GEN-LAST:event_jmParticipantesActionPerformed

    private void btnNuevoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoComprobanteActionPerformed
        dElegirPagadorComprobante dialogPagador = new dElegirPagadorComprobante(this, true);
        int x = (dpPrincipal.getWidth() - dialogPagador.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogPagador.getHeight()) / 2;
        dialogPagador.setLocation(x, y);
        dialogPagador.setVisible(true);
    }//GEN-LAST:event_btnNuevoComprobanteActionPerformed

    private void btnParticipantesCongresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParticipantesCongresoActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fCongresoListado) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fCongresoListado = new frmCongresoListado();
            dpPrincipal.add(fCongresoListado);
            int x = (dpPrincipal.getWidth() - fCongresoListado.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fCongresoListado.getHeight()) / 2;
            fCongresoListado.setLocation(x, y);
            fCongresoListado.setVisible(true);
        }
        fCongresoListado.toFront();
    }//GEN-LAST:event_btnParticipantesCongresoActionPerformed

    public JDialog obtenerProgressBar() {
        JDialog dlgProgress = new JDialog(this, "Por favor espere ...", true);//true means that the dialog created is modal
        JLabel lblStatus = new JLabel("Procesando..."); // this is just a label in which you can indicate the state of the processing
        JProgressBar pbProgress = new JProgressBar(0, 100);
        pbProgress.setIndeterminate(true); //we'll use an indeterminate progress bar
        dlgProgress.add(BorderLayout.NORTH, lblStatus);
        dlgProgress.add(BorderLayout.CENTER, pbProgress);
        dlgProgress.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // prevent the user from closing the dialog
        dlgProgress.setSize(300, 90);
        int x = (this.getWidth() - dlgProgress.getWidth()) / 2;
        int y = (this.getHeight() - dlgProgress.getHeight()) / 2;
        dlgProgress.setLocation(x, y);
        return dlgProgress;
    }


    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fDeudaColegiados) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fDeudaColegiados = new frmReporteDeudaColegiados();
            dpPrincipal.add(fDeudaColegiados);
            int x = (dpPrincipal.getWidth() - fDeudaColegiados.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fDeudaColegiados.getHeight()) / 2;
            fDeudaColegiados.setLocation(x, y);
            fDeudaColegiados.setVisible(true);
        }
        fDeudaColegiados.toFront();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jmEstructuraDatosCajaArequipaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEstructuraDatosCajaArequipaActionPerformed
        Thread queryThread = new Thread() {
            public void run() {
                frmCargando fCargando = new frmCargando();
                AgregarFormulario(fCargando);
                int x = (getWidth() - fCargando.getWidth()) / 2;
                int y = (getHeight() - fCargando.getHeight()) / 2;
                fCargando.setLocation(x, y);
                fCargando.setVisible(true);
                fCargando.toFront();
                ClienteBO cBO = ClienteBO.getInstance();
                String[] Movimientos = {"Para Reemplazar", "Para Adicionar",};
                String TipoMovimiento = (String) JOptionPane.showInputDialog(null,
                        "Cual es el tipo de Movimiento?",
                        "Elegir",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Movimientos,
                        Movimientos[0]);
                if (TipoMovimiento.equals("Para Reemplazar")) {
                    TipoMovimiento = "R";
                } else {
                    TipoMovimiento = "A";
                }
                cBO.GenerarEstructuraDatosCobranzaInstitucional(TipoMovimiento);
                fCargando.dispose();
            }
        };
        queryThread.start();
    }//GEN-LAST:event_jmEstructuraDatosCajaArequipaActionPerformed

    public void AbrirFormularioDerechoHabiente(frmContadorDetalle f, int tc, int fila, ClienteDerechoHabiente ctd) {
        frmClienteDerechoHabiente d = new frmClienteDerechoHabiente(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(f, tc, fila, ctd);
        d.setVisible(true);
    }

    public void AbrirFormularioAuditorIndependiente(frmContadorDetalle f, int tc, int fila, ClienteAuditorIndependiente ctd) {
        frmClienteAuditorIndependiente d = new frmClienteAuditorIndependiente(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(f, tc, fila, ctd);
        d.setVisible(true);
    }

    public void AbrirFormularioSociedadVigencia(frmSociedad f, int tc, int fila, ClienteSociedadVigencia ctd) {
        frmClienteSociedadVigencia d = new frmClienteSociedadVigencia(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(f, tc, fila, ctd);
        d.setVisible(true);
    }

    public void AbrirFormularioComitePerito(frmContadorDetalle f, int tc, int fila, ClienteComitePerito ctd) {
        frmClienteComitePerito d = new frmClienteComitePerito(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(f, tc, fila, ctd);
        d.setVisible(true);
    }

    public void AbrirFormularioEstudioContableEspecialidad(frmEmpresa f, int tc, int fila, ClienteEstudioContableEspecialidad ctd) {
        frmClienteEstudioContableEspecialidad d = new frmClienteEstudioContableEspecialidad(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(f, tc, fila, ctd);
        d.setVisible(true);
    }

    public void AbrirFormularioBuscardorContador(Object f, Integer tipo) {
        dContador d = new dContador(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(f, tipo);
        d.setVisible(true);
    }

    public void AbrirFormularioBuscardorAuditorIndependiente(Object f, Integer tipo) {
        dAuditorIndependiente d = new dAuditorIndependiente(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(f, tipo);
        d.setVisible(true);
    }

    public void AbrirFormularioGenerarOperacionBbva(frmBBVA f) {
        dPeriodoBancoBBVA dialogFecha = new dPeriodoBancoBBVA(this, true);
        int x = (dpPrincipal.getWidth() - dialogFecha.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogFecha.getHeight()) / 2;
        dialogFecha.setLocation(x, y + 43);
        dialogFecha.CargarDatos(f);
        dialogFecha.setVisible(true);
    }

    public void AbrirFormularioGenerarOperacionBbva(Object f, Bbva b, BbvaRetorno r, int tc) {
        dGeneracionComprobanteBbva d = new dGeneracionComprobanteBbva(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, b, r, tc);
        d.setVisible(true);
    }

    public void AbrirFormularioGenerarOperacionScotiabank(Object f, Scotiabank s, ScotiabankRetorno r, int tc) {
        dGeneracionComprobanteScotiabank d = new dGeneracionComprobanteScotiabank(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, s, r, tc);
        d.setVisible(true);
    }

    public void AbrirFormularioGenerarOperacionScotiabank(frmScotiabank f) {
        dPeriodoBancoScotiabank dialogFecha = new dPeriodoBancoScotiabank(this, true);
        int x = (dpPrincipal.getWidth() - dialogFecha.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogFecha.getHeight()) / 2;
        dialogFecha.setLocation(x, y + 43);
        dialogFecha.CargarDatos(f);
        dialogFecha.setVisible(true);
    }

    public void AbrirFormularioBbvaContador(Bbva b) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fBbvaContador) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fBbvaContador = new frmBBVAContador();
            dpPrincipal.add(fBbvaContador);
            int x = (dpPrincipal.getWidth() - fBbvaContador.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fBbvaContador.getHeight()) / 2;
            fBbvaContador.setLocation(x, y);
            fBbvaContador.setVisible(true);
        }
        fBbvaContador.CargarDatos(b);
        fBbvaContador.toFront();
    }

    public void abrirFormularioModificarArticulo_NotaCredito(Object frm, Object detalle, JTable t, int tc) {
        frmModificarArticulo_NotaCredito d = new frmModificarArticulo_NotaCredito(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 45);
        d.CargarDatos(frm, detalle, t, tc);
        d.setVisible(true);
    }

    public void abrirFormularioAsignarTipoDocumentoSerieUsuario(int idUsuario) {
        frmAsignarTipoDocumentoSerieUsuario d = new frmAsignarTipoDocumentoSerieUsuario(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 45);
        d.CargarDatos(idUsuario);
        d.setVisible(true);
    }

    public void abrirFormularioAsignarPArticipanteEvento(EventoConceptoPago ec, Object f, Object f02, Cliente c) {
        frmAsignarPartcipanteEvento_Dialog d = new frmAsignarPartcipanteEvento_Dialog(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 45);
        d.cargarDatos(ec, f, f02, c);
        d.setVisible(true);
    }

    public void AbrirFormularioBbvaRetorno(Bbva b) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fBBVARetorno) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fBBVARetorno = new frmBBVARetorno();
            dpPrincipal.add(fBBVARetorno);
            int x = (dpPrincipal.getWidth() - fBBVARetorno.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fBBVARetorno.getHeight()) / 2;
            fBBVARetorno.setLocation(x, y);
            fBBVARetorno.setVisible(true);
        }
        fBBVARetorno.CargarDatos(b);
        fBBVARetorno.toFront();
    }

    public void AbrirFormularioScotiabankRetorno(Scotiabank b) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fScotiabankRetorno) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fScotiabankRetorno = new frmScotiabankRetorno();
            dpPrincipal.add(fScotiabankRetorno);
            int x = (dpPrincipal.getWidth() - fScotiabankRetorno.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fScotiabankRetorno.getHeight()) / 2;
            fScotiabankRetorno.setLocation(x, y);
            fScotiabankRetorno.setVisible(true);
        }
        fScotiabankRetorno.CargarDatos(b);
        fScotiabankRetorno.toFront();
    }

    public void AbrirFormularioBbvaRetorno_SubirArchivo(Bbva b) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fBBVARetornoSubir) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fBBVARetornoSubir = new frmBBVARetornoSubir();
            dpPrincipal.add(fBBVARetornoSubir);
            int x = (dpPrincipal.getWidth() - fBBVARetornoSubir.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fBBVARetornoSubir.getHeight()) / 2;
            fBBVARetornoSubir.setLocation(x, y);
            fBBVARetornoSubir.setVisible(true);
        }
        fBBVARetornoSubir.CargarDatos(b);
        fBBVARetornoSubir.toFront();
    }

    public void AbrirFormularioScotiabankRetorno_SubirArchivo(Scotiabank b) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fScotiabankRetornoSubir) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fScotiabankRetornoSubir = new frmScotiabankRetornoSubir();
            dpPrincipal.add(fScotiabankRetornoSubir);
            int x = (dpPrincipal.getWidth() - fScotiabankRetornoSubir.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fScotiabankRetornoSubir.getHeight()) / 2;
            fScotiabankRetornoSubir.setLocation(x, y);
            fScotiabankRetornoSubir.setVisible(true);
        }
        fScotiabankRetornoSubir.CargarDatos(b);
        fScotiabankRetornoSubir.toFront();
    }

    public void AbrirFormularioScotiabankContador(Scotiabank b) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fScotiabankContador) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fScotiabankContador = new frmScotiabankContador();
            dpPrincipal.add(fScotiabankContador);
            int x = (dpPrincipal.getWidth() - fScotiabankContador.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fScotiabankContador.getHeight()) / 2;
            fScotiabankContador.setLocation(x, y);
            fScotiabankContador.setVisible(true);
        }
        fScotiabankContador.CargarDatos(b);
        fScotiabankContador.toFront();
    }


    private void jmEstructuraDatosBBVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEstructuraDatosBBVAActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fBbva) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fBbva = new frmBBVA();
            dpPrincipal.add(fBbva);
            int x = (dpPrincipal.getWidth() - fBbva.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fBbva.getHeight()) / 2;
            fBbva.setLocation(x, y);
            fBbva.setVisible(true);
        }
        fBbva.toFront();
    }//GEN-LAST:event_jmEstructuraDatosBBVAActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fScotiabank) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fScotiabank = new frmScotiabank();
            dpPrincipal.add(fScotiabank);
            int x = (dpPrincipal.getWidth() - fScotiabank.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fScotiabank.getHeight()) / 2;
            fScotiabank.setLocation(x, y);
            fScotiabank.setVisible(true);
        }
        fScotiabank.toFront();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fNota) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fNota = new frmNota();
            dpPrincipal.add(fNota);
            int x = (dpPrincipal.getWidth() - fNota.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fNota.getHeight()) / 2;
            fNota.setLocation(x, y);
            fNota.setVisible(true);
        }
        fNota.toFront();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fDeudaSociedades) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fDeudaSociedades = new frmReporteDeudaSociedades();
            dpPrincipal.add(fDeudaSociedades);
            int x = (dpPrincipal.getWidth() - fDeudaSociedades.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fDeudaSociedades.getHeight()) / 2;
            fDeudaSociedades.setLocation(x, y);
            fDeudaSociedades.setVisible(true);
        }
        fDeudaSociedades.toFront();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fAsignarConceptoEvento) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fAsignarConceptoEvento = new frmAsignarConceptoEvento();
            dpPrincipal.add(fAsignarConceptoEvento);
            int x = (dpPrincipal.getWidth() - fAsignarConceptoEvento.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fAsignarConceptoEvento.getHeight()) / 2;
            fAsignarConceptoEvento.setLocation(x, y);
            fAsignarConceptoEvento.setVisible(true);
        }
        fAsignarConceptoEvento.toFront();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fReporteParticipantes) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fReporteParticipantes = new frmReporteParticipantes();
            dpPrincipal.add(fReporteParticipantes);
            int x = (dpPrincipal.getWidth() - fReporteParticipantes.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fReporteParticipantes.getHeight()) / 2;
            fReporteParticipantes.setLocation(x, y);
            fReporteParticipantes.setVisible(true);
        }
        fReporteParticipantes.toFront();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    public void AbrirFormularioContadorDetalle(Cliente c, FichaDatos f) {
        frmContadorDetalle d = new frmContadorDetalle(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(c, f);
        d.setVisible(true);
    }

    public void AbrirFormularioPersonaDatosContador(Cliente c, Object o) {
        frmPersonaDatosContador d = new frmPersonaDatosContador(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.cargarDatos(c, o);
        d.setVisible(true);
    }


    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fResumenDiario) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fResumenDiario = new frmResumenDiario();
            dpPrincipal.add(fResumenDiario);
            int x = (dpPrincipal.getWidth() - fResumenDiario.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fResumenDiario.getHeight()) / 2;
            fResumenDiario.setLocation(x, y);
            fResumenDiario.setVisible(true);
        }
        fResumenDiario.toFront();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fValeAcademico) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fValeAcademico = new frmValeAcademico();
            dpPrincipal.add(fValeAcademico);
            int x = (dpPrincipal.getWidth() - fValeAcademico.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fValeAcademico.getHeight()) / 2;
            fValeAcademico.setLocation(x, y);
            fValeAcademico.setVisible(true);
        }
        fValeAcademico.toFront();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jmUniversidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUniversidadActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fUniversidad) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fUniversidad = new frmUniversidad();
            dpPrincipal.add(fUniversidad);
            int x = (dpPrincipal.getWidth() - fUniversidad.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fUniversidad.getHeight()) / 2;
            fUniversidad.setLocation(x, y);
            fUniversidad.setVisible(true);

        }
    }//GEN-LAST:event_jmUniversidadActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fFichaInscripcion) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fFichaInscripcion = new frmFichaInscripcion();
            dpPrincipal.add(fFichaInscripcion);
            int x = (dpPrincipal.getWidth() - fFichaInscripcion.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fFichaInscripcion.getHeight()) / 2;
            fFichaInscripcion.setLocation(x, y);
            fFichaInscripcion.setVisible(true);

        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void ExportarArchivosMigracionHalCont(dElegirFechaMigracion dialog, String fechaDesde, String fechaHasta) {
        dialog.dispose();
        fHistoriaContador.ExportarArchivos(fechaDesde, fechaHasta);
    }

    public void ExportarArchivosMigracionNuevoSistemaContable(dElegirFechaMigracion dialog, String fechaDesde, String fechaHasta) {
        dialog.dispose();
        fHistoriaContador.ExportarArchivosMigracionNuevoSistemaContable(fechaDesde, fechaHasta);
    }

    public void AbrirFormularioElegirFechaMigracion() {
        dElegirFechaMigracion dialogFecha = new dElegirFechaMigracion(this, true);
        int x = (dpPrincipal.getWidth() - dialogFecha.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogFecha.getHeight()) / 2;
        dialogFecha.setLocation(x, y + 43);
        dialogFecha.setVisible(true);
    }

    public void AbrirFormularioBbvaOpciones(Object f, int tc, BbvaRetorno br) {
        dBbvaOpciones d = new dBbvaOpciones(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, tc, br);
        d.setVisible(true);
    }

    public void AbrirFormularioSeleccionarPersona(Object f, int tc) {
        dSeleccionarPersona d = new dSeleccionarPersona(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, tc);
        d.setVisible(true);
    }

    public void AbrirFormularioScotiabankOpciones(Object f, int tc, ScotiabankRetorno br) {
        dScotiabankOpciones d = new dScotiabankOpciones(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, tc, br);
        d.setVisible(true);
    }

    public void AbrirFormularioCrearPersonaCongreso(dIncripcionCongreso1BK f) {
        frmCongresoRegistrarPersona d = new frmCongresoRegistrarPersona(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f);
        d.setVisible(true);
    }

    public void AbrirFormularioDetalleHistorico(int idContador, String codigo, String nombre) {
        dHistorialDetalladoContador dialogHistorico = new dHistorialDetalladoContador(this, true);
        int x = (dpPrincipal.getWidth() - dialogHistorico.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogHistorico.getHeight()) / 2;
        dialogHistorico.setLocation(x, y + 43);
        dialogHistorico.CargarDatos(idContador, codigo, nombre);
        dialogHistorico.setVisible(true);
    }

    public void AbrirFormularioNroOperacion(frmComprobantePagoCabecera f, DocumentoPago dp) {
        dIngresarOperacion dialogOperacion = new dIngresarOperacion(this, true);
        int x = (dpPrincipal.getWidth() - dialogOperacion.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogOperacion.getHeight()) / 2;
        dialogOperacion.setLocation(x, y + 43);
        dialogOperacion.CargarDatos(f, dp);
        dialogOperacion.setVisible(true);
    }

    public void AbrirFormularioCongresoIndividual(frmComprobantePagoDetalle f, DocumentoPagoDet dpd, List<Congreso> listado) {
        dIncripcionCongresoIndividual d = new dIncripcionCongresoIndividual(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, dpd, listado);
        d.setVisible(true);
    }

    public void AbrirFormularioEntregaMaterial(frmCongresoListado f, CongresoParticipantes cp) {
        dEntregaMaterial d = new dEntregaMaterial(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, cp);
        d.setVisible(true);
    }

    public void AbrirFormularioCongresoVariasPersonas(frmComprobantePagoDetalle f, DocumentoPagoDet dpd, List<Congreso> listado) {
        dIncripcionCongreso1BK d = new dIncripcionCongreso1BK(this, true);
        int x = (dpPrincipal.getWidth() - d.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - d.getHeight()) / 2;
        d.setLocation(x, y + 43);
        d.CargarDatos(f, dpd, listado);
        d.setVisible(true);
    }

    public void AbrirFormularioComprobante(String tipoPagador) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fNuevoComprobante) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fNuevoComprobante = new frmComprobantePagoCabecera();
            dpPrincipal.add(fNuevoComprobante);
            int x = (dpPrincipal.getWidth() - fNuevoComprobante.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fNuevoComprobante.getHeight()) / 2;
            fNuevoComprobante.setLocation(x, y);
            fNuevoComprobante.setVisible(true);
        }
        fNuevoComprobante.CargarDatos(tipoPagador);
        fNuevoComprobante.toFront();
    }

    public void AbrirFormularioFinanciamiento(String tipoCliente) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fCabeceraFinanciamiento) {;
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fCabeceraFinanciamiento = new frmCabeceraFinanciamiento();
            dpPrincipal.add(fCabeceraFinanciamiento);
            int x = (dpPrincipal.getWidth() - fCabeceraFinanciamiento.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fCabeceraFinanciamiento.getHeight()) / 2;
            fCabeceraFinanciamiento.setLocation(x, y);
            fCabeceraFinanciamiento.setVisible(true);
        }
        fCabeceraFinanciamiento.CargarDatos(tipoCliente);
        fCabeceraFinanciamiento.toFront();
    }

    public void AbrirDialogoReincorporacion(int idReincorporacion) {
        dReincorporacionDetalle dialogReincorporacion = new dReincorporacionDetalle(this, true);
        int x = (dpPrincipal.getWidth() - dialogReincorporacion.getWidth()) / 2;
        int y = (dpPrincipal.getHeight() - dialogReincorporacion.getHeight()) / 2;
        dialogReincorporacion.CargarDatos(idReincorporacion);
        dialogReincorporacion.setLocation(x, y + 43);
        dialogReincorporacion.setVisible(true);
    }

    public void AbrirFormularioDetalleComprobantePago(DocumentoPago doc, String tipoPagador, int tipoCargaDatos) {
        JInternalFrame[] activos = dpPrincipal.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == fDetalleComprobante) {
                cerrado = false;
            }
            i++;
        }

        if (cerrado) {
            fDetalleComprobante = new frmComprobantePagoDetalle();
            dpPrincipal.add(fDetalleComprobante);
            int x = (dpPrincipal.getWidth() - fDetalleComprobante.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fDetalleComprobante.getHeight()) / 2;
            fDetalleComprobante.setLocation(x, y);
            fDetalleComprobante.setVisible(true);
        } else {
            fDetalleComprobante.dispose();
            fDetalleComprobante = new frmComprobantePagoDetalle();
            dpPrincipal.add(fDetalleComprobante);
            int x = (dpPrincipal.getWidth() - fDetalleComprobante.getWidth()) / 2;
            int y = (dpPrincipal.getHeight() - fDetalleComprobante.getHeight()) / 2;
            fDetalleComprobante.setLocation(x, y);
            fDetalleComprobante.setVisible(true);
        }
        fDetalleComprobante.CargarDatos(doc, tipoPagador, tipoCargaDatos);
        fDetalleComprobante.toFront();
    }

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
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //WebLookAndFeel.install(true);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.err.println("Error loading L&F: " + e.getMessage());
                }
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevoComprobante;
    private javax.swing.JButton btnParticipantesCongreso;
    private javax.swing.JDesktopPane dpPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu jmAdministracion;
    private javax.swing.JMenuItem jmAlumnos;
    private javax.swing.JMenuItem jmBuscarAlumnos;
    private javax.swing.JMenuItem jmBuscarComprobantes;
    private javax.swing.JMenuItem jmCerrarDia;
    private javax.swing.JMenuItem jmCobrador;
    private javax.swing.JMenuItem jmComprobantes;
    private javax.swing.JMenuItem jmConceptosDiversos;
    private javax.swing.JMenu jmCongreso;
    private javax.swing.JMenuItem jmContador;
    private javax.swing.JMenu jmCursos;
    private javax.swing.JMenuItem jmEmpresa;
    private javax.swing.JMenuItem jmEstructuraDatosBBVA;
    private javax.swing.JMenuItem jmEstructuraDatosCajaArequipa;
    private javax.swing.JMenuItem jmFinanciamiento;
    private javax.swing.JMenuItem jmHistorial;
    private javax.swing.JMenuItem jmHistorialSociedad;
    private javax.swing.JMenuItem jmMovimientosContador;
    private javax.swing.JMenuItem jmMovimientosEmpresa;
    private javax.swing.JMenuItem jmMovimientosPersona;
    private javax.swing.JMenuItem jmMovimientosSociedad;
    private javax.swing.JMenuItem jmParticipantes;
    private javax.swing.JMenuItem jmPersona;
    private javax.swing.JMenuBar jmPrincipal;
    private javax.swing.JMenuItem jmReporteAlumnos;
    private javax.swing.JMenuItem jmReporteCobrador;
    private javax.swing.JMenu jmReportes;
    private javax.swing.JMenuItem jmSociedad;
    private javax.swing.JMenu jmTransacciones;
    private javax.swing.JMenuItem jmUniversidad;
    private javax.swing.JMenuItem jmUsuarios;
    private javax.swing.JMenuItem jmValidarHabilitacion;
    private javax.swing.JMenuItem jmVentasTipoEvento;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbNombreUsuario;
    private javax.swing.JToolBar tbAccesoDirecto;
    // End of variables declaration//GEN-END:variables
}
