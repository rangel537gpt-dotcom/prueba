/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.ClienteDAO;
//import caja.frm.caja.frmCargando;
//import caja.frm.frmPrincipal;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Bbva;
import caja.mapeo.entidades.BbvaContador;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.CuentaCorriente;
import caja.mapeo.entidades.Cuotas;
import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.BbvaContadorDetalle;
import caja.mapeo.entidades.BbvaRetorno;
import caja.mapeo.entidades.BbvaRetornoDetalle;
import caja.mapeo.entidades.BbvaRetornoDetalleDocumento;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.EstructuraPagoCajaArequipa;
import caja.mapeo.entidades.FichaDatos;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import caja.mapeo.entidades.Scotiabank;
import caja.mapeo.entidades.ScotiabankContador;
import caja.mapeo.entidades.ScotiabankContadorDetalle;
import caja.mapeo.entidades.ScotiabankRetorno;
import caja.mapeo.entidades.ScotiabankRetornoDetalle;
import caja.mapeo.entidades.ScotiabankRetornoDetalleDocumento;
import caja.mapeo.entidades.TipoAfectacion;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.mapeo.entidades.TipoTributo;
//import com.alee.extended.tree.FileTreeRootType;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
//import javax.swing.JOptionPane;

/**
 *
 * @author juan carlos
 */
public class ClienteBO implements ClienteBOIFace {

    private static ClienteBO INSTANCE = new ClienteBO();
    private Cliente cliente;
    //private List<Persona> resultadoBusqueda;
    //private List<Contador> listaContadores;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ClienteBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClienteBO();
                }
            }
        }
    }

    public static ClienteBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente pcliente) {
        cliente = pcliente;
    }

    public ClienteBO() {
        //resultadoBusqueda = new ArrayList();
    }

    /*public List<Persona> getResultadoBusqueda() {
     return resultadoBusqueda;
     }

     public void setResultadoBusqueda(List<Persona> resultadoBusqueda) {
     this.resultadoBusqueda = resultadoBusqueda;
     }

     @Override
     public List BuscarContador(String busqueda, int tipoBusqueda) {
     resultadoBusqueda.clear();

     ClienteDAO cont = ClienteDAO.getInstance();
     if (tipoBusqueda == 1) {
     resultadoBusqueda = cont.BuscarContadorPorApePat(busqueda);
     return resultadoBusqueda;
     } else if (tipoBusqueda == 2) {
     resultadoBusqueda = cont.BuscarContadorPorApeMat(busqueda);
     return resultadoBusqueda;
     } else if (tipoBusqueda == 3) {
     resultadoBusqueda = cont.BuscarContadorPorNombre(busqueda);
     return resultadoBusqueda;
     } else if (tipoBusqueda == 4) {
     resultadoBusqueda = cont.BuscarContadorPorDNI(busqueda);
     return resultadoBusqueda;
     } else {
     resultadoBusqueda = cont.BuscarContadorPorCodigoCole(busqueda);
     return resultadoBusqueda;
     }

     }

     @Override
     public void GuardarContador(Persona p, String codigoCole) {
     }

     @Override
     public Persona ObtenerCobrador(String dni) {
     for (Persona p : resultadoBusqueda) {
     if (p.getDni().equals(dni)) {
     return p;
     }
     }
     return null;
     }*/

 /*@Override
     public List ObtenerTodosContadores() {
     ContadorDAO cDAO = ContadorDAO.getInstance();
     if (this.listaContadores == null) {
     this.listaContadores = cDAO.ObtenerTodosContadores();
     }
     return this.listaContadores;
     return null;
     }*/
    @Override
    public List ObtenerHistorialPagos(int idContador) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerHistorialPagos(idContador);
    }

    @Override
    public List ObtenerCursos(Integer idColegiado) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerCursos(idColegiado);
    }

    @Override
    public FichaDatos obtenerFicha(Integer nro) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.obtenerFicha(nro);
    }

    @Override
    public Bbva ObtenerBbva(int nroOp) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbva(nroOp);
    }

    @Override
    public Bbva ObtenerUltimoBbvaGenerado() {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerUltimoBbvaGenerado();
    }

    @Override
    public Scotiabank ObtenerUltimoScotiabankGenerado() {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerUltimoScotiabankGenerado();
    }

    @Override
    public Scotiabank ObtenerScotiabank(int nroOp) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerScotiabank(nroOp);
    }

    @Override
    public List ObtenerBbvaContadorDetalle(int idBbvaContador) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaContadorDetalle(idBbvaContador);
    }

    @Override
    public List ObtenerBbvaContadorDetalle_SegunIdentificadoraPagoIdBbva(int idBbva, String identificador) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaContadorDetalle_SegunIdentificadoraPagoIdBbva(idBbva, identificador);
    }

    @Override
    public List ObtenerScotiabankContadorDetalle(int idBbvaContador) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerScotiabankContadorDetalle(idBbvaContador);
    }

    @Override
    public List ObtenerBbvaContador_SegunOperacion(int nroOp) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaContador_SegunOperacion(nroOp);
    }

    @Override
    public List ObtenerScotiabankContador_SegunOperacion(int nroOp) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerScotiabankContador_SegunOperacion(nroOp);
    }

    private void ValidarHabilitacion(Cliente cliente) {
        System.out.println("ID: " + cliente.getIdCliente());
        ClienteDAO cDAO = ClienteDAO.getInstance();
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List listado = dpBO.ObtenerDeudaNoFinanciadas(cliente.getIdCliente());
        if (listado.size() > 0) {
            cDAO.InhabilitarCliente(cliente.getIdCliente());
            dpBO.InhabilitarCliente(cliente.getIdCliente());
            return;
        }
        SimpleDateFormat fechaAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat fechaMes = new SimpleDateFormat("MM");
        Date fechaLimite;
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        //----------------------------VALIDAMOS LAS REINCORPORACIONES DEL CLIENTE----------------------------------//
        ReincorporacionBO rBO = ReincorporacionBO.getInstance();
        List<ReincorporacionDocumentoPago> lReincorporacionPendiente = rBO.ObtenerTodasReincoporacionesPendientes(cliente.getIdCliente());
        for (ReincorporacionDocumentoPago rpd : lReincorporacionPendiente) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(rpd.getFechaVencimiento());
            cal.add(Calendar.DATE, rpd.getDiasProrroga());
            fechaLimite = new java.sql.Date(cal.getTimeInMillis());
            if (fechaLimite.before(fechaServidor)) {
                cDAO.InhabilitarCliente(cliente.getIdCliente());
                dpBO.InhabilitarCliente(cliente.getIdCliente());
                return;
            }
        }
        //----------------------------VALIDAMOS LAS CUOTAS DEL CLIENTE----------------------------------//
        int anioServidor = Integer.valueOf(fechaAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(fechaMes.format(fechaServidor));
        AnioMesBO amBO = AnioMesBO.getInstance();
        AnioMes amServidor = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasBO cBO = CuotasBO.getInstance();
        Cuotas ultimaCuota = cBO.ObtenerUltimaCuotaOrdinaria(cliente.getIdCliente());
        if (ultimaCuota == null)/*NO TIENE HECHO NINGUN PAGO*/ {
            AnioMes amAfiliacion;
            int anio = 0;
            int mes = 0;
            if (cliente.getTipoCliente().equals("C")) {
                anio = Integer.valueOf(fechaAnio.format(cliente.getCfechaAfiliacion()));
                mes = Integer.valueOf(fechaMes.format(cliente.getCfechaAfiliacion()));
            }
            if (cliente.getTipoCliente().equals("S")) {
                anio = Integer.valueOf(fechaAnio.format(cliente.getSfechaAfiliacion()));
                mes = Integer.valueOf(fechaMes.format(cliente.getSfechaAfiliacion()));
            }
            amAfiliacion = amBO.ObtenerAnioMes(anio, mes);
            if ((amServidor.getNroOrden() - (amAfiliacion.getNroOrden() - 1)) > 3) {
                cDAO.InhabilitarCliente(cliente.getIdCliente());
                dpBO.InhabilitarCliente(cliente.getIdCliente());
                return;
            } else {
                cDAO.HabilitarCliente(cliente.getIdCliente());
                dpBO.HabilitarCliente(cliente.getIdCliente());
            }
        } else {
            if (amServidor.getNroOrden() > ultimaCuota.getAnioMes().getNroOrden()) {
                if ((amServidor.getNroOrden() - (ultimaCuota.getAnioMes().getNroOrden() + 1)) >= 3) {
                    cDAO.InhabilitarCliente(cliente.getIdCliente());
                    dpBO.InhabilitarCliente(cliente.getIdCliente());
                    return;
                } else {
                    cDAO.HabilitarCliente(cliente.getIdCliente());
                    dpBO.HabilitarCliente(cliente.getIdCliente());
                    return;
                }
            } else {
                cDAO.HabilitarCliente(cliente.getIdCliente());
                dpBO.HabilitarCliente(cliente.getIdCliente());
                return;
            }
        }
    }

    @Override
    public void ValidarHabilitacionTodosMiembros() {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<Cliente> lCliente = dpBO.ObtenerTodosClientes();
        for (Cliente c : lCliente) {
            this.ValidarHabilitacionColegiado(c);
//            if (c.getTipoCliente() != null) {
//                if (c.getTipoCliente().equals("C") || c.getTipoCliente().equals("S")) {
//                    if (c.getEstado() != null) {
//                        if (c.getEstado().equals("H") || c.getEstado().equals("I")) {
//                            this.ValidarHabilitacion(c);
//                        }
//                    }
//                }
//            }
        }
    }

    @Override
    public void ValidarHabilitacionColegiado(Cliente c) {
        if (c.getTipoCliente() != null) {
            if (c.getTipoCliente().equals("C") || c.getTipoCliente().equals("S")) {
                if (c.getEstado() != null) {
                    if (c.getEstado().equals("H") || c.getEstado().equals("I")) {
                        this.ValidarHabilitacion(c);
                        //--------------------ESTADO TEMPORAL-------------------
                        ClienteDAO clienteDAO = ClienteDAO.getInstance();
                        clienteDAO.ejecutarProcedimientoEstadoTemporal();
                        //------------------------------------------------------
                    }
                }
            }
        }
    }

    private String ObtenerMesExtenso(int m) {
        String mes = "";
        if (m == 1) {
            mes = "ENERO";
        }
        if (m == 2) {
            mes = "FEBRERO";
        }
        if (m == 3) {
            mes = "MARZO";
        }
        if (m == 4) {
            mes = "ABRIL";
        }
        if (m == 5) {
            mes = "MAYO";
        }
        if (m == 6) {
            mes = "JUNIO";
        }
        if (m == 7) {
            mes = "JULIO";
        }
        if (m == 8) {
            mes = "AGOSTO";
        }
        if (m == 9) {
            mes = "SEPTIEMBRE";
        }
        if (m == 10) {
            mes = "OCTUBRE";
        }
        if (m == 11) {
            mes = "NOVIEMBRE";
        }
        if (m == 12) {
            mes = "DICIEMBRE";
        }
        return mes;
    }

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

    private List ObtenerMontoFinanciamientoAPagarCliente(Cliente c, int cantCuotas) {
        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
        List<FinanciamientoDocumentoPago> listaFinanciamiento = fBO.ObtenerTodosFinanciamientoActivosCliente(c.getIdCliente());
        List<FinanciamientoDocumentoPago> financiamientoAPagar = new ArrayList();
        double montoTotalAPagar = 0;
        /*double monto = 0;
         double montoCuota = 0;*/
        int contador = 0;
        for (FinanciamientoDocumentoPago fdp : listaFinanciamiento) {
            montoTotalAPagar = montoTotalAPagar + fdp.getMonto();
            //monto = monto + fdp.getMonto();
            financiamientoAPagar.add(fdp);
            contador = contador + 1;
            if (contador == cantCuotas) {
                break;
            }
        }
        return financiamientoAPagar;
    }

    @Override
    public List ObtenerBbvaOperaciones(int anio, int mes) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.ObtenerBbvaOperaciones(anio, mes);
    }

    @Override
    public List ObtenerBbvaTodasOperaciones() {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.ObtenerBbvaTodasOperaciones();
    }

    @Override
    public List ObtenerScotiabankOperaciones(int anio, int mes) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.ObtenerScotiabankOperaciones(anio, mes);
    }

    @Override
    public List ObtenerScotiabankOperaciones() {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.ObtenerScotiabankOperaciones();
    }

    @Override
    public boolean GuardarEstructuraCajaArequipa(EstructuraPagoCajaArequipa e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarEstructuraCajaArequipa(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarBbvaContadorDetalle(BbvaContadorDetalle e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarBbvaContadorDetalle(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarScotiabankContadorDetalle(ScotiabankContadorDetalle e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarScotiabankContadorDetalle(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarBbva(Bbva e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarBbva(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarObjeto(Object e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarObjeto(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ActualizarObjeto(Object e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.ActualizarObjeto(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarBbvaRetorno(BbvaRetorno e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarBbvaRetorno(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarScotiabankRetorno(ScotiabankRetorno e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarScotiabankRetorno(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarBbvaRetornoDetalle(BbvaRetornoDetalle e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarBbvaRetornoDetalle(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarScotiabankRetornoDetalle(ScotiabankRetornoDetalle e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarScotiabankRetornoDetalle(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarBbvaRetornoDetalleDocumento(BbvaRetornoDetalleDocumento e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarBbvaRetornoDetalleDocumento(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarScotiabankRetornoDetalleDocumento(ScotiabankRetornoDetalleDocumento e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarScotiabankRetornoDetalleDocumento(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private BbvaRetornoDetalleDTO ExisteConcepto_ListaBbvaRetornoDTO(List<BbvaRetornoDetalleDTO> listadoPagar, int idConcepto) {
//        BbvaRetornoDetalleDTO existe = null;
        for (BbvaRetornoDetalleDTO r : listadoPagar) {
            if (r.getIdConcepto() == idConcepto) {
                return r;
//                existe = r;
//                return r;
            }
        }
        return null;
    }

    private ScotiabankRetornoDetalleDTO ExisteConcepto_ListaScotiabankRetornoDTO(List<ScotiabankRetornoDetalleDTO> listadoPagar, int idConcepto) {
        for (ScotiabankRetornoDetalleDTO r : listadoPagar) {
            if (r.getIdConcepto() == idConcepto) {
                return r;
            }
        }
        return null;
    }

    private void Actualizar_ListaBbvaRetornoDTO(List<BbvaRetornoDetalleDTO> listadoPagar, BbvaRetornoDetalleDTO retorno) {
        for (BbvaRetornoDetalleDTO r : listadoPagar) {
            if (r.getIdConcepto() == retorno.getIdConcepto()) {
                r = retorno;
            }
        }
    }

    private void Actualizar_ListaScotiabankRetornoDTO(List<ScotiabankRetornoDetalleDTO> listadoPagar, ScotiabankRetornoDetalleDTO retorno) {
        for (ScotiabankRetornoDetalleDTO r : listadoPagar) {
            if (r.getIdConcepto() == retorno.getIdConcepto()) {
                r = retorno;
            }
        }
    }

    private void ObtenerListaDocumentoPagoDetalle_Bbva(List listadoPagar, int idBbva, String referencia, int idRetornoDetalle) {
        SeguridadBO sBO = SeguridadBO.getInstance();
        ClienteDAO cDAO = ClienteDAO.getInstance();
        List l = cDAO.ObtenerBbvaContadorDetalle_SegunIdentificadoraPagoIdBbva(idBbva, referencia);
        for (Object po : l) {
            Object[] o = (Object[]) po;
            int idCliente = (int) o[0];
            int idConcepto = (int) o[1];
            double monto = (double) o[2];
            String concepto = (String) o[3];
            String conceptoAgrupar = (String) o[4];
            int idAnioMes = (int) o[5];
            int idFinanciamientoDetalle = (int) o[6];
            int idReincorporacionDetalle = (int) o[7];
            int idDeudaDetalle = (int) o[8];
            AnioMes am = (AnioMes) sBO.CargarObjeto("AnioMes", idAnioMes);
            BbvaRetornoDetalleDTO existe = this.ExisteConcepto_ListaBbvaRetornoDTO(listadoPagar, idConcepto);
            if (concepto.equals("CUOTA")) {
                if (existe == null) {
                    BbvaRetornoDetalleDTO bDTO = new BbvaRetornoDetalleDTO();
                    bDTO.setConcepto(concepto);
                    bDTO.setAnioDesde(am.getAnio());
                    bDTO.setMesDesde(am.getMes());
                    bDTO.setAnioHasta(am.getAnio());
                    bDTO.setMesHasta(am.getMes());
                    bDTO.setIdConcepto(idConcepto);
                    bDTO.setObservacion(am.getAnio() + " " + this.ObtenerMesExtenso(am.getMes()) + " A " + am.getAnio() + " " + this.ObtenerMesExtenso(am.getMes()));
                    bDTO.setMonto(monto);
                    bDTO.setCantidad(1);
                    listadoPagar.add(bDTO);
                    //----------------------------------------------------------
                    BbvaRetornoCuotaDTO c = new BbvaRetornoCuotaDTO();
                    c.setAnioMes(am);
                    c.setCliente(new Cliente(idCliente));
                    c.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                    c.setMonto(monto);
                    c.setReferencia(referencia);
                    c.setIdRetornoDetalle(idRetornoDetalle);
                    bDTO.agregarCuota(c);
                } else {
                    existe.setObservacion(existe.getAnioDesde() + " " + this.ObtenerMesExtenso(existe.getMesDesde()) + " A " + am.getAnio() + " " + this.ObtenerMesExtenso(am.getMes()));
                    existe.setAnioHasta(am.getAnio());
                    existe.setMesHasta(am.getMes());
                    existe.setMonto(existe.getMonto() + monto);
                    existe.setCantidad(existe.getCantidad() + 1);
                    this.Actualizar_ListaBbvaRetornoDTO(listadoPagar, existe);
                    //----------------------------------------------------------
                    BbvaRetornoCuotaDTO c = new BbvaRetornoCuotaDTO();
                    c.setAnioMes(am);
                    c.setCliente(new Cliente(idCliente));
                    c.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                    c.setMonto(monto);
                    c.setReferencia(referencia);
                    c.setIdRetornoDetalle(idRetornoDetalle);
                    existe.agregarCuota(c);
                }
            } else {
                if (concepto.equals("FINAN")) {
                    FinanciamientoDocumentoPago fdp = (FinanciamientoDocumentoPago) sBO.CargarObjeto("FinanciamientoDocumentoPago", idFinanciamientoDetalle);
                    if (existe == null) {
//                        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
//                        fBO.obtener
                        BbvaRetornoDetalleDTO bDTO = new BbvaRetornoDetalleDTO();
                        bDTO.setConcepto(concepto);
                        bDTO.setAnioDesde(am.getAnio());
                        bDTO.setMesDesde(am.getMes());
                        bDTO.setAnioHasta(am.getAnio());
                        bDTO.setMesHasta(am.getMes());
                        bDTO.setIdConcepto(idConcepto);
                        bDTO.setObservacion("CUOTA NRO: " + fdp.getNroCuota());
                        bDTO.setMonto(monto);
                        bDTO.setCantidad(1);
                        listadoPagar.add(bDTO);
                        //----------------------------------------------------------
                        BbvaRetornoFinanciamientoDTO f = new BbvaRetornoFinanciamientoDTO();
                        f.setAnioMes(am);
                        f.setCliente(new Cliente(idCliente));
                        f.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                        f.setIdFinanciamientoDetalle(idFinanciamientoDetalle);
                        f.setMonto(monto);
                        f.setReferencia(referencia);
                        f.setIdRetornoDetalle(idRetornoDetalle);
                        bDTO.agregarFinanciamiento(f);
                    } else {
                        existe.setObservacion(existe.getObservacion() + " " + fdp.getNroCuota());
                        existe.setAnioHasta(am.getAnio());
                        existe.setMesHasta(am.getMes());
                        existe.setMonto(existe.getMonto() + monto);
                        existe.setCantidad(existe.getCantidad() + 1);
                        this.Actualizar_ListaBbvaRetornoDTO(listadoPagar, existe);
                        //----------------------------------------------------------
                        BbvaRetornoFinanciamientoDTO f = new BbvaRetornoFinanciamientoDTO();
                        f.setAnioMes(am);
                        f.setCliente(new Cliente(idCliente));
                        f.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                        f.setIdFinanciamientoDetalle(idFinanciamientoDetalle);
                        f.setMonto(monto);
                        f.setReferencia(referencia);
                        f.setIdRetornoDetalle(idRetornoDetalle);
                        existe.agregarFinanciamiento(f);
                    }
                } else {
                    if (concepto.equals("REINC")) {
                        ReincorporacionDocumentoPago rdp = (ReincorporacionDocumentoPago) sBO.CargarObjeto("ReincorporacionDocumentoPago", idReincorporacionDetalle);
                        if (existe == null) {
                            BbvaRetornoDetalleDTO bDTO = new BbvaRetornoDetalleDTO();
                            bDTO.setConcepto(concepto);
                            bDTO.setAnioDesde(am.getAnio());
                            bDTO.setMesDesde(am.getMes());
                            bDTO.setAnioHasta(am.getAnio());
                            bDTO.setMesHasta(am.getMes());
                            bDTO.setIdConcepto(idConcepto);
                            bDTO.setObservacion("CUOTA NRO: " + rdp.getNroCuota());
                            bDTO.setMonto(monto);
                            bDTO.setCantidad(1);
                            listadoPagar.add(bDTO);
                            //----------------------------------------------------------
                            BbvaRetornoReincorporacionDTO r = new BbvaRetornoReincorporacionDTO();
                            r.setAnioMes(am);
                            r.setCliente(new Cliente(idCliente));
                            r.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            r.setIdReincorporacionDetalle(idReincorporacionDetalle);
                            r.setMonto(monto);
                            r.setReferencia(referencia);
                            r.setIdRetornoDetalle(idRetornoDetalle);
                            bDTO.agregarReincorporacion(r);
                        } else {
                            existe.setObservacion(existe.getObservacion() + " " + rdp.getNroCuota());
                            existe.setAnioHasta(am.getAnio());
                            existe.setMesHasta(am.getMes());
                            existe.setMonto(existe.getMonto() + monto);
                            existe.setCantidad(existe.getCantidad() + 1);
                            this.Actualizar_ListaBbvaRetornoDTO(listadoPagar, existe);
                            //----------------------------------------------------------
                            BbvaRetornoReincorporacionDTO r = new BbvaRetornoReincorporacionDTO();
                            r.setAnioMes(am);
                            r.setCliente(new Cliente(idCliente));
                            r.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            r.setIdReincorporacionDetalle(idReincorporacionDetalle);
                            r.setMonto(monto);
                            r.setReferencia(referencia);
                            r.setIdRetornoDetalle(idRetornoDetalle);
                            existe.agregarReincorporacion(r);
                        }
                    } else {
                        Deuda dd = (Deuda) sBO.CargarObjeto("Deuda", idDeudaDetalle);
                        if (existe == null) {
                            BbvaRetornoDetalleDTO bDTO = new BbvaRetornoDetalleDTO();
                            bDTO.setConcepto(concepto);
                            bDTO.setAnioDesde(am.getAnio());
                            bDTO.setMesDesde(am.getMes());
                            bDTO.setAnioHasta(am.getAnio());
                            bDTO.setMesHasta(am.getMes());
                            bDTO.setIdConcepto(idConcepto);
                            bDTO.setMonto(monto);
                            bDTO.setCantidad(1);
                            listadoPagar.add(bDTO);
                            //----------------------------------------------------------
                            BbvaRetornoDeudaDTO d = new BbvaRetornoDeudaDTO();
                            d.setCliente(new Cliente(idCliente));
                            d.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            d.setIdDeuda(idDeudaDetalle);
                            d.setMonto(monto);
                            d.setReferencia(referencia);
                            d.setIdRetornoDetalle(idRetornoDetalle);
                            bDTO.agregarDeuda(d);
                        } else {
                            existe.setAnioHasta(am.getAnio());
                            existe.setMesHasta(am.getMes());
                            existe.setMonto(existe.getMonto() + monto);
                            existe.setCantidad(existe.getCantidad() + 1);
                            this.Actualizar_ListaBbvaRetornoDTO(listadoPagar, existe);
                            //----------------------------------------------------------
                            BbvaRetornoDeudaDTO d = new BbvaRetornoDeudaDTO();
                            d.setCliente(new Cliente(idCliente));
                            d.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            d.setIdDeuda(idDeudaDetalle);
                            d.setMonto(monto);
                            d.setReferencia(referencia);
                            d.setIdRetornoDetalle(idRetornoDetalle);
                            existe.agregarDeuda(d);
                        }
                    }
                }
            }
        }
    }

    private void ObtenerListaDocumentoPagoDetalle_Scotiabank(List listadoPagar, int idScotiabank, String referencia, int idRetornoDetalle) {
        SeguridadBO sBO = SeguridadBO.getInstance();
        ClienteDAO cDAO = ClienteDAO.getInstance();
        List l = cDAO.ObtenerScotiabankContadorDetalle_SegunIdentificadoraPagoIdScotiabank(idScotiabank, referencia);
        for (Object po : l) {
            Object[] o = (Object[]) po;
            int idCliente = (int) o[0];
            int idConcepto = (int) o[1];
            double monto = (double) o[2];
            String concepto = (String) o[3];
            String conceptoAgrupar = (String) o[4];
            int idAnioMes = (int) o[5];
            int idFinanciamientoDetalle = (int) o[6];
            int idReincorporacionDetalle = (int) o[7];
            int idDeudaDetalle = (int) o[8];
            AnioMes am = (AnioMes) sBO.CargarObjeto("AnioMes", idAnioMes);
            ScotiabankRetornoDetalleDTO existe = this.ExisteConcepto_ListaScotiabankRetornoDTO(listadoPagar, idConcepto);
            if (concepto.equals("CUOTA")) {
                if (existe == null) {
                    ScotiabankRetornoDetalleDTO bDTO = new ScotiabankRetornoDetalleDTO();
                    bDTO.setConcepto(concepto);
                    bDTO.setAnioDesde(am.getAnio());
                    bDTO.setMesDesde(am.getMes());
                    bDTO.setAnioHasta(am.getAnio());
                    bDTO.setMesHasta(am.getMes());
                    bDTO.setIdConcepto(idConcepto);
                    bDTO.setObservacion(am.getAnio() + " " + this.ObtenerMesExtenso(am.getMes()) + " A " + am.getAnio() + " " + this.ObtenerMesExtenso(am.getMes()));
                    bDTO.setMonto(monto);
                    bDTO.setCantidad(1);
                    listadoPagar.add(bDTO);
                    //----------------------------------------------------------
                    ScotiabankRetornoCuotaDTO c = new ScotiabankRetornoCuotaDTO();
                    c.setAnioMes(am);
                    c.setCliente(new Cliente(idCliente));
                    c.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                    c.setMonto(monto);
                    c.setReferencia(referencia);
                    c.setIdRetornoDetalle(idRetornoDetalle);
                    bDTO.agregarCuota(c);
                } else {
                    existe.setObservacion(existe.getAnioDesde() + " " + this.ObtenerMesExtenso(existe.getMesDesde()) + " A " + am.getAnio() + " " + this.ObtenerMesExtenso(am.getMes()));
                    existe.setAnioHasta(am.getAnio());
                    existe.setMesHasta(am.getMes());
                    existe.setMonto(existe.getMonto() + monto);
                    existe.setCantidad(existe.getCantidad() + 1);
                    this.Actualizar_ListaScotiabankRetornoDTO(listadoPagar, existe);
                    //----------------------------------------------------------
                    ScotiabankRetornoCuotaDTO c = new ScotiabankRetornoCuotaDTO();
                    c.setAnioMes(am);
                    c.setCliente(new Cliente(idCliente));
                    c.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                    c.setMonto(monto);
                    c.setReferencia(referencia);
                    c.setIdRetornoDetalle(idRetornoDetalle);
                    existe.agregarCuota(c);
                }
            } else {
                if (concepto.equals("FINAN")) {
                    FinanciamientoDocumentoPago fdp = (FinanciamientoDocumentoPago) sBO.CargarObjeto("FinanciamientoDocumentoPago", idFinanciamientoDetalle);
                    if (existe == null) {
//                        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
//                        fBO.obtener
                        ScotiabankRetornoDetalleDTO bDTO = new ScotiabankRetornoDetalleDTO();
                        bDTO.setConcepto(concepto);
                        bDTO.setAnioDesde(am.getAnio());
                        bDTO.setMesDesde(am.getMes());
                        bDTO.setAnioHasta(am.getAnio());
                        bDTO.setMesHasta(am.getMes());
                        bDTO.setIdConcepto(idConcepto);
                        bDTO.setObservacion("CUOTA NRO: " + fdp.getNroCuota());
                        bDTO.setMonto(monto);
                        bDTO.setCantidad(1);
                        listadoPagar.add(bDTO);
                        //----------------------------------------------------------
                        ScotiabankRetornoFinanciamientoDTO f = new ScotiabankRetornoFinanciamientoDTO();
                        f.setAnioMes(am);
                        f.setCliente(new Cliente(idCliente));
                        f.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                        f.setIdFinanciamientoDetalle(idFinanciamientoDetalle);
                        f.setMonto(monto);
                        f.setReferencia(referencia);
                        f.setIdRetornoDetalle(idRetornoDetalle);
                        bDTO.agregarFinanciamiento(f);
                    } else {
                        existe.setObservacion(existe.getObservacion() + " " + fdp.getNroCuota());
                        existe.setAnioHasta(am.getAnio());
                        existe.setMesHasta(am.getMes());
                        existe.setMonto(existe.getMonto() + monto);
                        existe.setCantidad(existe.getCantidad() + 1);
                        this.Actualizar_ListaScotiabankRetornoDTO(listadoPagar, existe);
                        //----------------------------------------------------------
                        ScotiabankRetornoFinanciamientoDTO f = new ScotiabankRetornoFinanciamientoDTO();
                        f.setAnioMes(am);
                        f.setCliente(new Cliente(idCliente));
                        f.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                        f.setIdFinanciamientoDetalle(idFinanciamientoDetalle);
                        f.setMonto(monto);
                        f.setReferencia(referencia);
                        f.setIdRetornoDetalle(idRetornoDetalle);
                        existe.agregarFinanciamiento(f);
                    }
                } else {
                    if (concepto.equals("REINC")) {
                        ReincorporacionDocumentoPago rdp = (ReincorporacionDocumentoPago) sBO.CargarObjeto("ReincorporacionDocumentoPago", idReincorporacionDetalle);
                        if (existe == null) {
                            ScotiabankRetornoDetalleDTO bDTO = new ScotiabankRetornoDetalleDTO();
                            bDTO.setConcepto(concepto);
                            bDTO.setAnioDesde(am.getAnio());
                            bDTO.setMesDesde(am.getMes());
                            bDTO.setAnioHasta(am.getAnio());
                            bDTO.setMesHasta(am.getMes());
                            bDTO.setIdConcepto(idConcepto);
                            bDTO.setObservacion("CUOTA NRO: " + rdp.getNroCuota());
                            bDTO.setMonto(monto);
                            bDTO.setCantidad(1);
                            listadoPagar.add(bDTO);
                            //----------------------------------------------------------
                            ScotiabankRetornoReincorporacionDTO r = new ScotiabankRetornoReincorporacionDTO();
                            r.setAnioMes(am);
                            r.setCliente(new Cliente(idCliente));
                            r.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            r.setIdReincorporacionDetalle(idReincorporacionDetalle);
                            r.setMonto(monto);
                            r.setReferencia(referencia);
                            r.setIdRetornoDetalle(idRetornoDetalle);
                            bDTO.agregarReincorporacion(r);
                        } else {
                            existe.setObservacion(existe.getObservacion() + " " + rdp.getNroCuota());
                            existe.setAnioHasta(am.getAnio());
                            existe.setMesHasta(am.getMes());
                            existe.setMonto(existe.getMonto() + monto);
                            existe.setCantidad(existe.getCantidad() + 1);
                            this.Actualizar_ListaScotiabankRetornoDTO(listadoPagar, existe);
                            //----------------------------------------------------------
                            ScotiabankRetornoReincorporacionDTO r = new ScotiabankRetornoReincorporacionDTO();
                            r.setAnioMes(am);
                            r.setCliente(new Cliente(idCliente));
                            r.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            r.setIdReincorporacionDetalle(idReincorporacionDetalle);
                            r.setMonto(monto);
                            r.setReferencia(referencia);
                            r.setIdRetornoDetalle(idRetornoDetalle);
                            existe.agregarReincorporacion(r);
                        }
                    } else {
                        Deuda dd = (Deuda) sBO.CargarObjeto("Deuda", idDeudaDetalle);
                        if (existe == null) {
                            ScotiabankRetornoDetalleDTO bDTO = new ScotiabankRetornoDetalleDTO();
                            bDTO.setConcepto(concepto);
                            bDTO.setAnioDesde(am.getAnio());
                            bDTO.setMesDesde(am.getMes());
                            bDTO.setAnioHasta(am.getAnio());
                            bDTO.setMesHasta(am.getMes());
                            bDTO.setIdConcepto(idConcepto);
                            bDTO.setMonto(monto);
                            bDTO.setCantidad(1);
                            listadoPagar.add(bDTO);
                            //----------------------------------------------------------
                            ScotiabankRetornoDeudaDTO d = new ScotiabankRetornoDeudaDTO();
                            d.setCliente(new Cliente(idCliente));
                            d.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            d.setIdDeuda(idDeudaDetalle);
                            d.setMonto(monto);
                            d.setReferencia(referencia);
                            d.setIdRetornoDetalle(idRetornoDetalle);
                            bDTO.agregarDeuda(d);
                        } else {
                            existe.setAnioHasta(am.getAnio());
                            existe.setMesHasta(am.getMes());
                            existe.setMonto(existe.getMonto() + monto);
                            existe.setCantidad(existe.getCantidad() + 1);
                            this.Actualizar_ListaScotiabankRetornoDTO(listadoPagar, existe);
                            //----------------------------------------------------------
                            ScotiabankRetornoDeudaDTO d = new ScotiabankRetornoDeudaDTO();
                            d.setCliente(new Cliente(idCliente));
                            d.setConcepto(new ConceptoPagoDetalle(idConcepto, ""));
                            d.setIdDeuda(idDeudaDetalle);
                            d.setMonto(monto);
                            d.setReferencia(referencia);
                            d.setIdRetornoDetalle(idRetornoDetalle);
                            existe.agregarDeuda(d);
                        }
                    }
                }
            }
        }
    }

    private void CancelarCuotasBbvaRetorno_SegunDocumentoPagoDet(BbvaRetornoDetalleDTO dto) {
        CuotasBO cBO = CuotasBO.getInstance();
        if (dto.getConcepto().equals("CUOTA")) {
            for (BbvaRetornoCuotaDTO c : dto.getlCuotas()) {
                Cuotas cuota = new Cuotas();
                cuota.setAnioMes(c.getAnioMes());
                cuota.setCliente(dto.getDocDetalle().getDocumentoPago().getCliente());
                cuota.setDocumentoPagoDet(dto.getDocDetalle());
                cuota.setEstado("C");
                cuota.setConceptoPagoDetalle(c.getConcepto());
                cuota.setMonto(c.getMonto());
                cBO.GuardarCuota(cuota);
                //--------------------------------------------------------------
                BbvaRetornoDetalleDocumento rdd = new BbvaRetornoDetalleDocumento();
                rdd.setIdBbvaRetornoDetalle(c.getIdRetornoDetalle());
                rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                rdd.setConcepto(dto.getConcepto());
                rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                rdd.setMonto(c.getMonto());
                this.GuardarBbvaRetornoDetalleDocumento(rdd);
            }
        } else {
            if (dto.getConcepto().equals("FINAN")) {
                FinanciamientoBO fBO = FinanciamientoBO.getInstance();
                SeguridadBO sBO = SeguridadBO.getInstance();
                for (BbvaRetornoFinanciamientoDTO c : dto.getlFinanciamiento()) {
                    FinanciamientoDocumentoPago fdp = (FinanciamientoDocumentoPago) sBO.CargarObjeto("FinanciamientoDocumentoPago", c.getIdFinanciamientoDetalle());
                    fdp.setDocumentoPagoDet(dto.getDocDetalle());
                    fdp.setEstado("FC");
                    fdp.setMonto(c.getMonto());
                    fBO.ActualizarFinanciamiento(fdp);
                    //--------------------------------------------------------------
                    BbvaRetornoDetalleDocumento rdd = new BbvaRetornoDetalleDocumento();
                    rdd.setIdBbvaRetornoDetalle(c.getIdRetornoDetalle());
                    rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                    rdd.setConcepto(dto.getConcepto());
                    rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                    rdd.setMonto(c.getMonto());
                    this.GuardarBbvaRetornoDetalleDocumento(rdd);
                }
            } else {
                if (dto.getConcepto().equals("REINC")) {
                    FinanciamientoBO fBO = FinanciamientoBO.getInstance();
                    SeguridadBO sBO = SeguridadBO.getInstance();
                    for (BbvaRetornoReincorporacionDTO c : dto.getlReincorporacion()) {
                        ReincorporacionDocumentoPago rdp = (ReincorporacionDocumentoPago) sBO.CargarObjeto("ReincorporacionDocumentoPago", c.getIdReincorporacionDetalle());
                        rdp.setDocumentoPagoDet(dto.getDocDetalle());
                        rdp.setEstado("FC");
                        rdp.setMonto(c.getMonto());
                        fBO.ActualizarReincorporacion(rdp);
                        //--------------------------------------------------------------
                        BbvaRetornoDetalleDocumento rdd = new BbvaRetornoDetalleDocumento();
                        rdd.setIdBbvaRetornoDetalle(c.getIdRetornoDetalle());
                        rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                        rdd.setConcepto(dto.getConcepto());
                        rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                        rdd.setMonto(c.getMonto());
                        this.GuardarBbvaRetornoDetalleDocumento(rdd);
                    }
                } else {
                    SeguridadBO sBO = SeguridadBO.getInstance();
                    DeudasBO dBO = DeudasBO.getInstance();
                    for (BbvaRetornoDeudaDTO c : dto.getlDeuda()) {
                        Deuda d = (Deuda) sBO.CargarObjeto("Deuda", c.getIdDeuda());
                        d.setDocumentoPagoDet(dto.getDocDetalle());
                        d.setEstado("DP");
                        d.setDocumentoPagoDet(dto.getDocDetalle());
                        dBO.ActualizarDeuda(d);
                        //--------------------------------------------------------------
                        BbvaRetornoDetalleDocumento rdd = new BbvaRetornoDetalleDocumento();
                        rdd.setIdBbvaRetornoDetalle(c.getIdRetornoDetalle());
                        rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                        rdd.setConcepto(dto.getConcepto());
                        rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                        rdd.setMonto(c.getMonto());
                        this.GuardarBbvaRetornoDetalleDocumento(rdd);
                    }
                }
            }
        }
    }

    private void CancelarCuotasScotiabankRetorno_SegunDocumentoPagoDet(ScotiabankRetornoDetalleDTO dto) {
        CuotasBO cBO = CuotasBO.getInstance();
        if (dto.getConcepto().equals("CUOTA")) {
            for (ScotiabankRetornoCuotaDTO c : dto.getlCuotas()) {
                Cuotas cuota = new Cuotas();
                cuota.setAnioMes(c.getAnioMes());
                cuota.setCliente(dto.getDocDetalle().getDocumentoPago().getCliente());
                cuota.setDocumentoPagoDet(dto.getDocDetalle());
                cuota.setEstado("C");
                cuota.setConceptoPagoDetalle(c.getConcepto());
                cuota.setMonto(c.getMonto());
                cBO.GuardarCuota(cuota);
                //--------------------------------------------------------------
                ScotiabankRetornoDetalleDocumento rdd = new ScotiabankRetornoDetalleDocumento();
                rdd.setIdScotiabankRetornoDetalle(c.getIdRetornoDetalle());
                rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                rdd.setConcepto(dto.getConcepto());
                rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                rdd.setMonto(c.getMonto());
                this.GuardarScotiabankRetornoDetalleDocumento(rdd);
            }
        } else {
            if (dto.getConcepto().equals("FINAN")) {
                FinanciamientoBO fBO = FinanciamientoBO.getInstance();
                SeguridadBO sBO = SeguridadBO.getInstance();
                for (ScotiabankRetornoFinanciamientoDTO c : dto.getlFinanciamiento()) {
                    FinanciamientoDocumentoPago fdp = (FinanciamientoDocumentoPago) sBO.CargarObjeto("FinanciamientoDocumentoPago", c.getIdFinanciamientoDetalle());
                    fdp.setDocumentoPagoDet(dto.getDocDetalle());
                    fdp.setEstado("FC");
                    fdp.setMonto(c.getMonto());
                    fBO.ActualizarFinanciamiento(fdp);
                    //--------------------------------------------------------------
                    BbvaRetornoDetalleDocumento rdd = new BbvaRetornoDetalleDocumento();
                    rdd.setIdBbvaRetornoDetalle(c.getIdRetornoDetalle());
                    rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                    rdd.setConcepto(dto.getConcepto());
                    rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                    rdd.setMonto(c.getMonto());
                    this.GuardarBbvaRetornoDetalleDocumento(rdd);
                }
            } else {
                if (dto.getConcepto().equals("REINC")) {
                    FinanciamientoBO fBO = FinanciamientoBO.getInstance();
                    SeguridadBO sBO = SeguridadBO.getInstance();
                    for (ScotiabankRetornoReincorporacionDTO c : dto.getlReincorporacion()) {
                        ReincorporacionDocumentoPago rdp = (ReincorporacionDocumentoPago) sBO.CargarObjeto("ReincorporacionDocumentoPago", c.getIdReincorporacionDetalle());
                        rdp.setDocumentoPagoDet(dto.getDocDetalle());
                        rdp.setEstado("FC");
                        rdp.setMonto(c.getMonto());
                        fBO.ActualizarReincorporacion(rdp);
                        //--------------------------------------------------------------
                        BbvaRetornoDetalleDocumento rdd = new BbvaRetornoDetalleDocumento();
                        rdd.setIdBbvaRetornoDetalle(c.getIdRetornoDetalle());
                        rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                        rdd.setConcepto(dto.getConcepto());
                        rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                        rdd.setMonto(c.getMonto());
                        this.GuardarBbvaRetornoDetalleDocumento(rdd);
                    }
                } else {
                    SeguridadBO sBO = SeguridadBO.getInstance();
                    DeudasBO dBO = DeudasBO.getInstance();
                    for (ScotiabankRetornoDeudaDTO c : dto.getlDeuda()) {
                        Deuda d = (Deuda) sBO.CargarObjeto("Deuda", c.getIdDeuda());
                        d.setDocumentoPagoDet(dto.getDocDetalle());
                        d.setEstado("DP");
                        d.setDocumentoPagoDet(dto.getDocDetalle());
                        dBO.ActualizarDeuda(d);
                        //--------------------------------------------------------------
                        BbvaRetornoDetalleDocumento rdd = new BbvaRetornoDetalleDocumento();
                        rdd.setIdBbvaRetornoDetalle(c.getIdRetornoDetalle());
                        rdd.setIdDocumentoPagoDet(dto.getDocDetalle().getIdDocumentoPagoDet());
                        rdd.setConcepto(dto.getConcepto());
                        rdd.setIdConceptoPagoDetalle(dto.getIdConcepto());
                        rdd.setMonto(c.getMonto());
                        this.GuardarBbvaRetornoDetalleDocumento(rdd);
                    }
                }
            }
        }
    }

    @Override
    public void GenerarComprobantePago_SegunBbvaRetorno(Date fecha, int idTipoDocPago, String nroSerie, int bbvaRetorno, Cliente cliente) {
        DocumentoPagoBO dBO = DocumentoPagoBO.getInstance();
        SeguridadBO sBO = SeguridadBO.getInstance();
        BbvaRetorno br = (BbvaRetorno) sBO.CargarObjeto("BbvaRetorno", bbvaRetorno);
        if (cliente != null) {
            int nro = dBO.ObtenerUltimoNroComprobante(idTipoDocPago, nroSerie);
            ClienteDAO cDAO = ClienteDAO.getInstance();
            DocumentoPago dp = new DocumentoPago();
            dp.setCliente(cliente);
            dp.setFechaPago(fecha);
            dp.setEstado("C");
            dp.setTieneIgv("N");
            dp.setMoneda("S");
            dp.setTipoPago("CON");
            dp.setFormaPagoSunat("Contado");
            Date ahora = new Date();
            dp.setFechaVencimientoSunat(ahora);
            dp.setNroSerie(nroSerie);
            TipoDocSerie tds = dBO.ObtenerTipoDocSerie(idTipoDocPago, nroSerie);
            dp.setTipoDocSerie(tds);
            dp.setNroDocumentoPago(nro + 1);
            dp.setCobrador(new Cobrador(26));
            dp.setNombreCliente(cliente.getPapePat() + " " + cliente.getPapeMat() + " " + cliente.getPnombre());
            Date fechaServer = sBO.ObtenerFechaHoraServidor();
            dp.setFechaSunat(fechaServer);
            dBO.GuardarDocumentoPago_SinValidacion(dp);
            List l = cDAO.ObtenerBbvaRetornoDetalle_SegunCliente(bbvaRetorno, cliente.getIdCliente());
            List<BbvaRetornoDetalleDTO> listadoPagar = new ArrayList();
            for (Object p : l) {
                Object[] o = (Object[]) p;
                String referencia = (String) o[2];
                int idRetornoDetalle = (int) o[3];
                this.ObtenerListaDocumentoPagoDetalle_Bbva(listadoPagar, br.getIdBbva(), referencia, idRetornoDetalle);
            }
            for (BbvaRetornoDetalleDTO dto : listadoPagar) {
                DocumentoPagoDet dpd = new DocumentoPagoDet();
                dpd.setDocumentoPago(dp);
                dpd.setCantidad(dto.getCantidad());
                dpd.setAnioDesde(dto.getAnioDesde());
                dpd.setMesDesde(dto.getMesDesde());
                dpd.setAnioHasta(dto.getAnioHasta());
                dpd.setMesHasta(dto.getMesHasta());
                dpd.setObservacion(dto.getObservacion());
                dpd.setValorVenta(dto.getMonto());
                dpd.setPrecioVenta(dto.getMonto());
                dpd.setIgv(0.0);
                dpd.setTieneDescuento("N");
                //-------------------CONCEPTOS ELECTRONICOS---------------------
                ConceptoPagoDetalle conceptoDetalle = (ConceptoPagoDetalle) sBO.CargarObjeto("ConceptoPagoDetalle", dto.getIdConcepto());
                TipoAfectacion tipoAfectacion = (TipoAfectacion) sBO.CargarObjeto("TipoAfectacion", conceptoDetalle.getTipoAfectacion().getId());
                conceptoDetalle.setTipoAfectacion(tipoAfectacion);
                TipoTributo tipoTributo = (TipoTributo) sBO.CargarObjeto("TipoTributo", conceptoDetalle.getTipoAfectacion().getTipoTributo().getId());
                conceptoDetalle.getTipoAfectacion().setTipoTributo(tipoTributo);
                dpd.setConceptoPagoDetalle(conceptoDetalle);
                dpd.setCodigoTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getCodigo());
                dpd.setCodigoInternacionalTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getCodigoInternacional());
                dpd.setNombreTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getNombre());
                dpd.setCodigoTipoAfectacion(conceptoDetalle.getTipoAfectacion().getCodigo());
                dpd.setIgvPorcentaje((conceptoDetalle.getPorcentajeIgv() != null ? conceptoDetalle.getPorcentajeIgv() : 0.0));
                //-------------------CONCEPTOS ELECTRONICOS---------------------
                dBO.GuardarDocumentoPagoDet_SinValidadcion(dpd);
                dto.setDocDetalle(dpd);
                this.CancelarCuotasBbvaRetorno_SegunDocumentoPagoDet(dto);
            }
            dBO.GenerarArchivosElectronico_ComprobantePago(dp);
        }
    }

    @Override
    public void GenerarComprobantePago_SegunScotiabankRetorno(Date fecha, int idTipoDocPago, String nroSerie, int scotiabankRetorno, Cliente cliente) {
        DocumentoPagoBO dBO = DocumentoPagoBO.getInstance();
        SeguridadBO sBO = SeguridadBO.getInstance();
        ScotiabankRetorno br = (ScotiabankRetorno) sBO.CargarObjeto("ScotiabankRetorno", scotiabankRetorno);
        if (cliente != null) {
            int nro = dBO.ObtenerUltimoNroComprobante(idTipoDocPago, nroSerie);
            ClienteDAO cDAO = ClienteDAO.getInstance();
            DocumentoPago dp = new DocumentoPago();
            dp.setCliente(cliente);
            dp.setFechaPago(fecha);
            dp.setEstado("C");
            dp.setTieneIgv("N");
            dp.setMoneda("S");
            dp.setTipoPago("CON");
            dp.setFormaPagoSunat("Contado");
            Date ahora = new Date();
            dp.setFechaVencimientoSunat(ahora);
            dp.setNroSerie(nroSerie);
            TipoDocSerie tds = dBO.ObtenerTipoDocSerie(idTipoDocPago, nroSerie);
            dp.setTipoDocSerie(tds);
            dp.setNroDocumentoPago(nro + 1);
            dp.setCobrador(new Cobrador(25));
            dp.setNombreCliente(cliente.getPapePat() + " " + cliente.getPapeMat() + " " + cliente.getPnombre());
            Date fechaServer = sBO.ObtenerFechaHoraServidor();
            dp.setFechaSunat(fechaServer);
            dBO.GuardarDocumentoPago_SinValidacion(dp);
//            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            List l = cDAO.ObtenerScotiabankRetornoDetalle_SegunCliente(scotiabankRetorno, cliente.getIdCliente());
//            List l = cDAO.ObtenerBbvaRetornoDetalle_SegunClienteFecha(bbvaRetorno, cliente.getIdCliente(), f.format(fechaPago));
            List<ScotiabankRetornoDetalleDTO> listadoPagar = new ArrayList();
            for (Object p : l) {
                Object[] o = (Object[]) p;
                String referencia = (String) o[2];
                int idRetornoDetalle = (int) o[3];
                this.ObtenerListaDocumentoPagoDetalle_Scotiabank(listadoPagar, br.getIdScotiabank(), referencia, idRetornoDetalle);
            }
            for (ScotiabankRetornoDetalleDTO dto : listadoPagar) {
                DocumentoPagoDet dpd = new DocumentoPagoDet();
                dpd.setDocumentoPago(dp);
                dpd.setCantidad(dto.getCantidad());
                dpd.setAnioDesde(dto.getAnioDesde());
                dpd.setMesDesde(dto.getMesDesde());
                dpd.setAnioHasta(dto.getAnioHasta());
                dpd.setMesHasta(dto.getMesHasta());
                dpd.setObservacion(dto.getObservacion());
                dpd.setValorVenta(dto.getMonto());
                dpd.setPrecioVenta(dto.getMonto());
                dpd.setIgv(0.0);
                dpd.setTieneDescuento("N");
                //-------------------CONCEPTOS ELECTRONICOS---------------------
                ConceptoPagoDetalle conceptoDetalle = (ConceptoPagoDetalle) sBO.CargarObjeto("ConceptoPagoDetalle", dto.getIdConcepto());
                TipoAfectacion tipoAfectacion = (TipoAfectacion) sBO.CargarObjeto("TipoAfectacion", conceptoDetalle.getTipoAfectacion().getId());
                conceptoDetalle.setTipoAfectacion(tipoAfectacion);
                TipoTributo tipoTributo = (TipoTributo) sBO.CargarObjeto("TipoTributo", conceptoDetalle.getTipoAfectacion().getTipoTributo().getId());
                conceptoDetalle.getTipoAfectacion().setTipoTributo(tipoTributo);
                dpd.setConceptoPagoDetalle(conceptoDetalle);
                dpd.setCodigoTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getCodigo());
                dpd.setCodigoInternacionalTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getCodigoInternacional());
                dpd.setNombreTipoTributo(conceptoDetalle.getTipoAfectacion().getTipoTributo().getNombre());
                dpd.setCodigoTipoAfectacion(conceptoDetalle.getTipoAfectacion().getCodigo());
                dpd.setIgvPorcentaje((conceptoDetalle.getPorcentajeIgv() != null ? conceptoDetalle.getPorcentajeIgv() : 0.0));
                //-------------------CONCEPTOS ELECTRONICOS---------------------
                dBO.GuardarDocumentoPagoDet_SinValidadcion(dpd);
                dto.setDocDetalle(dpd);
                this.CancelarCuotasScotiabankRetorno_SegunDocumentoPagoDet(dto);
            }
            dBO.GenerarArchivosElectronico_ComprobantePago(dp);
        }
    }

    @Override
    public boolean GuardarScotiabank(Scotiabank e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarScotiabank(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarBbvaContador(BbvaContador e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarBbvaContador(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ActualizarBbvaRetorno(BbvaRetorno e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.ActualizarBbvaRetorno(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ActualizarScotiabankRetorno(ScotiabankRetorno e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.ActualizarScotiabankRetorno(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarScotiabankContador(ScotiabankContador e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarScotiabankContador(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private int ObtenerMontoCuotasAPagarCliente_CajaArequipa(int idOperacion, Cliente cliente, int cantCuotas) {
        CuotasBO cBO = CuotasBO.getInstance();
        List<Object> listaCuotas = null;
        int idAnioMesInicio = 0;
        if (cliente.getTipoCliente().equals("C")) {
            if (cliente.getEstado().equals("V") || cliente.getEstado().equals("O")) {
                listaCuotas = cBO.ObtenerTodasCuotasClienteVitalicio_CajaArequipa(cliente, cantCuotas);
            } else {
                listaCuotas = cBO.ObtenerTodasCuotasCliente30Anios_CajaArequipa(cliente, cantCuotas);
            }
            int contador = 0;
            int idAnioMesPrevio = 0;
            boolean inicioCiclo = true;
            for (Object pobj : listaCuotas) {
                Object[] obj = (Object[]) pobj;
                if (contador < cantCuotas) {
                    if (obj[4] == null) {
                        int idAnioMes = (int) obj[1];
                        int idConcepto = (int) obj[6];
                        double montoConcepto = (double) obj[5];
                        EstructuraPagoCajaArequipa est = new EstructuraPagoCajaArequipa();
                        est.setIdOperacion(idOperacion);
                        est.setIdAnioMesCuota(idAnioMes);
                        est.setIdCliente(cliente.getIdCliente());
                        est.setIdConcepto(idConcepto);
                        est.setMonto(montoConcepto);
                        est.setTipoConceptoAgrupar("CUOTA");
                        if (inicioCiclo) {
                            idAnioMesInicio = idAnioMes;
                            idAnioMesPrevio = idAnioMes;
                            inicioCiclo = false;
                        }
                        if (idAnioMes != idAnioMesPrevio) {
                            idAnioMesPrevio = idAnioMes;
                            contador = contador + 1;
                        }
                        est.setNroConcepto(contador + 1);
                        if (contador < cantCuotas) {
                            this.GuardarEstructuraCajaArequipa(est);
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return idAnioMesInicio;
    }

    private int ObtenerMontoCuotasAPagarCliente_BBVA(int idBC, Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
//        SeguridadBO sBO = SeguridadBO.getInstance();
        CuotasBO cBO = CuotasBO.getInstance();
        List<Object> listaCuotas = null;
        int idAnioMesInicio = 0;
        int nroOrdenInicial = 0;
        if (cliente.getTipoCliente().equals("C")) {
            if (cliente.getEstado().equals("V") || cliente.getEstado().equals("O")) {
                listaCuotas = cBO.ObtenerTodasCuotasClienteVitalicio_BBVA(cliente, cantCuotas);
            } else {
                listaCuotas = cBO.ObtenerTodasCuotasCliente30Anios_BBVA(cliente, cantCuotas);
            }
            int contador = 0;
            int idAnioMesPrevio = 0;
            boolean inicioCiclo = true;
            for (Object pobj : listaCuotas) {
                Object[] obj = (Object[]) pobj;
                if (contador < cantCuotas) {
                    if (obj[4] == null) {
                        int nroOrden = (int) obj[0];
                        int idAnioMes = (int) obj[1];
                        int idConcepto = (int) obj[6];
                        double montoConcepto = (double) obj[5];
                        BbvaContadorDetalle est = new BbvaContadorDetalle();
                        est.setIdAnioMesCuota(idAnioMes);
                        est.setIdConcepto(idConcepto);
                        est.setMonto(montoConcepto);
                        est.setTipoConceptoAgrupar("CUOTA");
                        est.setConcepto("CUOTA");
                        est.setIdBbvaContador(idBC);
                        if (inicioCiclo) {
                            nroOrdenInicial = nroOrden;
                            idAnioMesInicio = idAnioMes;
                            idAnioMesPrevio = idAnioMes;
                            inicioCiclo = false;
                        }
                        if (idAnioMes != idAnioMesPrevio) {
                            idAnioMesPrevio = idAnioMes;
                            contador = contador + 1;
                        }
                        AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(nroOrden);
//                        AnioMes am = (AnioMes) sBO.CargarObjeto("AnioMes", idAnioMes);
                        int codigoColegiado = Integer.valueOf(cliente.getCcodigoCole());
                        String codFinal = RellenarEspacioBlancoIzquieda(codigoColegiado);
                        String identificacionPago = codFinal + "CUOTA-" + this.ObtenerMes(am.getMes()) + am.getAnio();
                        est.setIdentificacionPago(identificacionPago);
                        est.setNroConcepto(contador + 1);
                        if (contador < cantCuotas) {
                            this.GuardarBbvaContadorDetalle(est);
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return nroOrdenInicial;
    }

    private int ObtenerCorrelativoScotiabank_SegunIdScotiabank(int idScotiabank) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        int correlativo = cDAO.ObtenerCorrelativoScotiabank_SegunIdScotiabank(idScotiabank);
//        correlativo = correlativo;
        return correlativo;
    }

    private int ObtenerMontoCuotasAPagarCliente_Scotiabank(int idScotiabank, int idBC, Cliente cliente, int cantCuotas) {
//        int correlativo = this.ObtenerCorrelativoScotiabank_SegunIdScotiabank(idScotiabank);
        AnioMesBO amBO = AnioMesBO.getInstance();
        SeguridadBO sBO = SeguridadBO.getInstance();
        CuotasBO cBO = CuotasBO.getInstance();
        List<Object> listaCuotas = null;
        int idAnioMesInicio = 0;
        int nroOrdenInicial = 0;
        if (cliente.getTipoCliente().equals("C")) {
            if (cliente.getEstado().equals("V") || cliente.getEstado().equals("O")) {
                listaCuotas = cBO.ObtenerTodasCuotasClienteVitalicio_BBVA(cliente, cantCuotas);
            } else {
                listaCuotas = cBO.ObtenerTodasCuotasCliente30Anios_BBVA(cliente, cantCuotas);
            }
            int contador = 0;
            int idAnioMesPrevio = 0;
            boolean inicioCiclo = true;
            for (Object pobj : listaCuotas) {
                Object[] obj = (Object[]) pobj;
                if (contador < cantCuotas) {
                    if (obj[4] == null) {
                        int nroOrden = (int) obj[0];
                        int idAnioMes = (int) obj[1];
                        int idConcepto = (int) obj[6];
                        double montoConcepto = (double) obj[5];
                        ScotiabankContadorDetalle est = new ScotiabankContadorDetalle();
                        est.setIdAnioMesCuota(idAnioMes);
                        est.setIdConcepto(idConcepto);
                        est.setMonto(montoConcepto);
                        est.setTipoConceptoAgrupar("CUOTA");
                        est.setConcepto("CUOTA");
                        est.setIdScotiabankContador(idBC);
//                        est.setCorrelativo(correlativo + 1);
                        if (inicioCiclo) {
                            nroOrdenInicial = nroOrden;
                            idAnioMesInicio = idAnioMes;
                            idAnioMesPrevio = idAnioMes;
                            inicioCiclo = false;
                        }
                        if (idAnioMes != idAnioMesPrevio) {
                            idAnioMesPrevio = idAnioMes;
                            contador = contador + 1;
//                            correlativo = correlativo + 1;
                        }
                        AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(nroOrden);
//                        AnioMes am = (AnioMes) sBO.CargarObjeto("AnioMes", idAnioMes);
                        String identificacionPago = cliente.getCcodigoCole() + "CUOTA-" + this.ObtenerMes(am.getMes()) + am.getAnio();
                        est.setIdentificacionPago(identificacionPago);
                        est.setNroConcepto(contador + 1);
                        if (contador < cantCuotas) {
                            this.GuardarScotiabankContadorDetalle(est);
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return nroOrdenInicial;
    }

    private List ObtenerMontoReincorporacionAPagarCliente(Cliente c, int cantCuotas) {
        ReincorporacionBO rBO = ReincorporacionBO.getInstance();
        List<ReincorporacionDocumentoPago> listaReincorporacion = rBO.ObtenerTodosReincorporacionesActivosCliente(c.getIdCliente());
        List<ReincorporacionDocumentoPago> reincorporacionAPagar = new ArrayList();
        double montoTotalAPagar = 0;
        /*double monto = 0;
         double montoCuota = 0;*/
        int contador = 0;
        for (ReincorporacionDocumentoPago rdp : listaReincorporacion) {
            montoTotalAPagar = montoTotalAPagar + rdp.getMontoFondo() + rdp.getMontoOtros();
            rdp.setMonto(rdp.getMontoFondo() + rdp.getMontoOtros());
            reincorporacionAPagar.add(rdp);
            contador = contador + 1;
            if (contador == cantCuotas) {
                break;
            }
        }
        return reincorporacionAPagar;
    }

    public double ObtenerMontoConceptoDetallado(int idAnioMes, int idConcepto) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        Object objMonto = amBO.ObtenerMontoConceptoDetallado(idAnioMes, idConcepto);
        if (objMonto == null) {
            return 0;
        } else {
            return (double) objMonto;
        }
    }

    public int obtenerNroOperacion_CajaArequipa() {
        int nro = 0;
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        nro = clienteDAO.obtenerNroOperacion_CajaArequipa();
        return nro + 1;
    }

    public int obtenerNroOperacion_BBVA() {
        int nro = 0;
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        nro = clienteDAO.obtenerNroOperacion_BBVA();
        return nro + 1;
    }

    public int obtenerNroOperacion_SCOTIABANK() {
        int nro = 0;
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        nro = clienteDAO.obtenerNroOperacion_SCOTIABANK();
        return nro + 1;
    }

    public int obtenerVersion_BBVA(String fecha) {
        int nro = 0;
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        nro = clienteDAO.obtenerVersion_BBVA(fecha);
        return nro + 1;
    }

    public List ObtenerListado_CajaArequipa(int nroOperacion) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.ObtenerListado_CajaArequipa(nroOperacion);
    }

    public List ObtenerListado_BBVA(int nroOperacion) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.ObtenerListado_BBVA(nroOperacion);
    }

    public List ObtenerListado_Scotiabank(int nroOperacion) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.ObtenerListado_Scotiabank(nroOperacion);
    }

    public double obtenerMontoTotal_SegunNroOperacionScotiabank(int nroOperacion) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        return clienteDAO.obtenerMontoTotal_SegunNroOperacionScotiabank(nroOperacion);
    }

    private void CuotasAPagar_CajaArequipa(int idOperacion, Cliente c, int cantidadCuotas) {
        double monto = 0;
//        List<EstructuraPagoCajaArequipa> lEstructura = new ArrayList();
        int idAnioMesInicial = ObtenerMontoCuotasAPagarCliente_CajaArequipa(idOperacion, c, cantidadCuotas);
        List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = this.ObtenerMontoFinanciamientoAPagarCliente(c, cantidadCuotas);
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = this.ObtenerMontoReincorporacionAPagarCliente(c, cantidadCuotas);
//        String mensaje = "• CUOTAS A PAGAR:\r\n";
//        String mes = "";
//        for (Object pobj : listaCuotasAPagar) {
//            Object[] obj = (Object[]) pobj;
//            mes = this.ObtenerMes((int) obj[3]);
//            double montoCuota = (double) obj[4];
//            mensaje = mensaje + String.valueOf((int) obj[2]) + " " + mes + " : S/. " + String.valueOf(montoCuota) + "\r\n";
//        }
        /*INICIO ---------- SABER SI TIENE FINANCIAMIENTO A PAGAR*/
        int contadorFinanciamiento = 1;
        if (listaFinanciamientoAPagar != null) {
            if (listaFinanciamientoAPagar.size() > 0) {
                for (FinanciamientoDocumentoPago fdp : listaFinanciamientoAPagar) {
                    EstructuraPagoCajaArequipa est = new EstructuraPagoCajaArequipa();
                    est.setIdOperacion(idOperacion);
                    est.setIdFinanciamientoDetalle(cantidadCuotas);
                    est.setTipoConceptoAgrupar("FINAN");
                    est.setIdConcepto(10);
                    est.setIdFinanciamientoDetalle(fdp.getId());
                    est.setIdCliente(c.getIdCliente());
                    est.setMonto(fdp.getMonto());
                    est.setNroConcepto(contadorFinanciamiento);
                    est.setIdAnioMesCuota(idAnioMesInicial - 1 + contadorFinanciamiento);
                    contadorFinanciamiento = contadorFinanciamiento + 1;
                    this.GuardarEstructuraCajaArequipa(est);
                }
            }
        }
        /*INICIO ---------- SABER SI TIENE REINCORPORACION A PAGAR*/
        int contadorReincorporacion = 1;
        if (listaReincorporacionAPagar != null) {
            if (listaReincorporacionAPagar.size() > 0) {
                for (ReincorporacionDocumentoPago rdp : listaReincorporacionAPagar) {
                    EstructuraPagoCajaArequipa est = new EstructuraPagoCajaArequipa();
                    est.setIdOperacion(idOperacion);
                    est.setTipoConceptoAgrupar("REINC");
                    est.setIdConcepto(10);
                    est.setIdReincorporacionDetalle(rdp.getId());
                    est.setIdCliente(c.getIdCliente());
                    est.setMonto(rdp.getMonto());
                    est.setNroConcepto(contadorReincorporacion);
                    est.setIdAnioMesCuota(idAnioMesInicial - 1 + contadorReincorporacion);
                    contadorReincorporacion = contadorReincorporacion + 1;
                    this.GuardarEstructuraCajaArequipa(est);
                }
            }
        }

        DeudasBO dBO = DeudasBO.getInstance();
        List<Deuda> listadoDeuda = dBO.ObtenerTodasDeudasPendientes(c.getIdCliente());
        if (listadoDeuda != null) {
            for (Deuda d : listadoDeuda) {
                EstructuraPagoCajaArequipa est = new EstructuraPagoCajaArequipa();
                est.setIdOperacion(idOperacion);
//                est.setIdFinanciamientoDetalle(cantidadCuotas);
                est.setIdConcepto(d.getConceptoPagoDetalle().getIdConceptoPagoDetalle());
//                est.setIdReincorporacionDetalle(d.getId());
                est.setIdCliente(c.getIdCliente());
                est.setMonto(d.getMonto());
                est.setNroConcepto(1);
                if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4603) { //MULTA 29-01-2008
                    est.setIdAnioMesCuota(1414);
                    est.setTipoConceptoAgrupar("MULT_01_2008");
//                    wr.write("D," + codigoColegiado + "," + apePat + "," + apeMat + "," + nombre + ",1," + codigoConcepto + ",200801,2008/01/29," + String.format(Locale.ROOT, "%.2f", importe) + "\r\n");
                } else {
                    if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4604) { //MULTA 28-02-2008
                        est.setIdAnioMesCuota(1414);
                        est.setTipoConceptoAgrupar("MULT_02_2008");
//                        wr.write("D," + codigoColegiado + "," + apePat + "," + apeMat + "," + nombre + ",1," + codigoConcepto + ",200802,2008/02/28," + String.format(Locale.ROOT, "%.2f", importe) + "\r\n");
                    } else {
                        if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4225) { //REINSERCION 2005
                            est.setIdAnioMesCuota(1386);
                            est.setTipoConceptoAgrupar("REINS_2005");
//                            wr.write("D," + codigoColegiado + "," + apePat + "," + apeMat + "," + nombre + ",1," + codigoConcepto + ",200512,2005/12/01," + String.format(Locale.ROOT, "%.2f", importe) + "\r\n");
                        } else {
                            if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4307) { //REINCORPORACION 2006
                                est.setIdAnioMesCuota(1398);
                                est.setTipoConceptoAgrupar("REINC_2006");
//                                wr.write("D," + codigoColegiado + "," + apePat + "," + apeMat + "," + nombre + ",1," + codigoConcepto + ",200612,2006/12/01," + String.format(Locale.ROOT, "%.2f", importe) + "\r\n");
                            } else {
//                                                wr.write("D," + codigoColegiado + "," + apePat + "," + apeMat + "," + nombre + ",1," + codigoConcepto + ",200612,2006/12/01," + String.format(Locale.ROOT, "%.2f", importe) + "\r\n");
                            }
                        }
                    }
                }
                this.GuardarEstructuraCajaArequipa(est);
            }
        }
    }

    private String RellenarEspacioBlancoIzquieda(int cod) {
        String codigo = String.valueOf(cod);
        int cantidad = codigo.length();
        while (cantidad < 5) {
            codigo = codigo + " ";
            cantidad++;
        }
        return codigo;
    }

    private void CuotasAPagar_BBVA(int idBC, Cliente c, int cantidadCuotas) {
//        double monto = 0;
        AnioMesBO amBO = AnioMesBO.getInstance();
        int nroOrdenAnioMesInicial = ObtenerMontoCuotasAPagarCliente_BBVA(idBC, c, cantidadCuotas);
        List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = this.ObtenerMontoFinanciamientoAPagarCliente(c, cantidadCuotas);
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = this.ObtenerMontoReincorporacionAPagarCliente(c, cantidadCuotas);
        /*INICIO ---------- SABER SI TIENE FINANCIAMIENTO A PAGAR*/
        int contadorFinanciamiento = 1;
        if (listaFinanciamientoAPagar != null) {
            if (listaFinanciamientoAPagar.size() > 0) {
                for (FinanciamientoDocumentoPago fdp : listaFinanciamientoAPagar) {
                    BbvaContadorDetalle est = new BbvaContadorDetalle();
                    est.setIdBbvaContador(idBC);
                    est.setTipoConceptoAgrupar("CUOTA");
                    est.setConcepto("FINAN");
                    est.setIdConcepto(10);
                    est.setIdFinanciamientoDetalle(fdp.getId());
                    est.setMonto(fdp.getMonto());
                    est.setNroConcepto(contadorFinanciamiento);
                    int contadorNroOrden = nroOrdenAnioMesInicial - 1 + contadorFinanciamiento;
                    AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(contadorNroOrden);
                    est.setIdAnioMesCuota(am.getId());
                    int codigoColegiado = Integer.valueOf(c.getCcodigoCole());
                    String codFinal = RellenarEspacioBlancoIzquieda(codigoColegiado);
                    String identificacionPago = codFinal + "CUOTA-" + this.ObtenerMes(am.getMes()) + am.getAnio();
                    est.setIdentificacionPago(identificacionPago);
                    contadorFinanciamiento = contadorFinanciamiento + 1;
                    this.GuardarBbvaContadorDetalle(est);
                }
            }
        }
        /*INICIO ---------- SABER SI TIENE REINCORPORACION A PAGAR*/
        int contadorReincorporacion = 1;
        if (listaReincorporacionAPagar != null) {
            if (listaReincorporacionAPagar.size() > 0) {
                for (ReincorporacionDocumentoPago rdp : listaReincorporacionAPagar) {
                    BbvaContadorDetalle est = new BbvaContadorDetalle();
                    est.setIdBbvaContador(idBC);
                    est.setTipoConceptoAgrupar("CUOTA");
                    est.setConcepto("REINC");
                    est.setIdConcepto(5850);
                    est.setIdReincorporacionDetalle(rdp.getId());
                    est.setMonto(rdp.getMonto());
                    est.setNroConcepto(contadorReincorporacion);
//                    est.setIdAnioMesCuota(idAnioMesInicial - 1 + contadorReincorporacion);
                    int contadorNroOrden = nroOrdenAnioMesInicial - 1 + contadorReincorporacion;
                    AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(contadorNroOrden);
                    est.setIdAnioMesCuota(am.getId());
                    int codigoColegiado = Integer.valueOf(c.getCcodigoCole());
                    String codFinal = RellenarEspacioBlancoIzquieda(codigoColegiado);
                    String identificacionPago = codFinal + "CUOTA-" + this.ObtenerMes(am.getMes()) + am.getAnio();
                    est.setIdentificacionPago(identificacionPago);
                    contadorReincorporacion = contadorReincorporacion + 1;
                    this.GuardarBbvaContadorDetalle(est);
                }
            }
        }

        DeudasBO dBO = DeudasBO.getInstance();
        List<Deuda> listadoDeuda = dBO.ObtenerTodasDeudasPendientes(c.getIdCliente());
        if (listadoDeuda != null) {
            for (Deuda d : listadoDeuda) {
                BbvaContadorDetalle est = new BbvaContadorDetalle();
                est.setIdBbvaContador(idBC);
                est.setIdConcepto(d.getConceptoPagoDetalle().getIdConceptoPagoDetalle());
                est.setIdDeudaDetalle(d.getId());
                est.setMonto(d.getMonto());
                est.setNroConcepto(1);
                int codigoColegiado = Integer.valueOf(c.getCcodigoCole());
                String codFinal = RellenarEspacioBlancoIzquieda(codigoColegiado);
                if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4603) { //MULTA 29-01-2008
                    est.setIdAnioMesCuota(1412);
                    est.setConcepto("MULTA-ENE2008");
                    est.setTipoConceptoAgrupar("MULTA-ENE2008");
                    est.setIdentificacionPago(codFinal + "MULTA-ENE2008");
                } else {
                    if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4604) { //MULTA 28-02-2008
                        est.setIdAnioMesCuota(1413);
                        est.setConcepto("MULTA-FEB2008");
                        est.setTipoConceptoAgrupar("MULTA-FEB2008");
                        est.setIdentificacionPago(codFinal + "MULTA-FEB2008");
                    } else {
                        if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4225) { //REINSERCION 2005
                            est.setIdAnioMesCuota(1386);
                            est.setConcepto("REINSERCION-2005");
                            est.setTipoConceptoAgrupar("REINSERCI2005");
                            est.setIdentificacionPago(codFinal + "REINSERCI2005");
                        } else {
                            if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4307) { //REINCORPORACION 2006
                                est.setIdAnioMesCuota(1398);
                                est.setConcepto("REINCORPORACION-2006");
                                est.setTipoConceptoAgrupar("REINCORPO2006");
                                est.setIdentificacionPago(codFinal + "REINCORPO2006");
                            } else {
//                                                wr.write("D," + codigoColegiado + "," + apePat + "," + apeMat + "," + nombre + ",1," + codigoConcepto + ",200612,2006/12/01," + String.format(Locale.ROOT, "%.2f", importe) + "\r\n");
                            }
                        }
                    }
                }
                this.GuardarBbvaContadorDetalle(est);
            }
        }
    }

    private void CuotasAPagar_Scotiabank(int idScotiabank, int idBC, Cliente c, int cantidadCuotas) {
//        ClienteDAO cDAO = ClienteDAO.getInstance();
        AnioMesBO amBO = AnioMesBO.getInstance();
        int nroOrdenAnioMesInicial = ObtenerMontoCuotasAPagarCliente_Scotiabank(idScotiabank, idBC, c, cantidadCuotas);
//        int correlativo = this.ObtenerCorrelativoScotiabank_SegunIdScotiabank(idScotiabank);
        List<FinanciamientoDocumentoPago> listaFinanciamientoAPagar = this.ObtenerMontoFinanciamientoAPagarCliente(c, cantidadCuotas);
        List<ReincorporacionDocumentoPago> listaReincorporacionAPagar = this.ObtenerMontoReincorporacionAPagarCliente(c, cantidadCuotas);
        /*INICIO ---------- SABER SI TIENE FINANCIAMIENTO A PAGAR*/
        int contadorFinanciamiento = 1;
        if (listaFinanciamientoAPagar != null) {
            if (listaFinanciamientoAPagar.size() > 0) {
                for (FinanciamientoDocumentoPago fdp : listaFinanciamientoAPagar) {
                    ScotiabankContadorDetalle est = new ScotiabankContadorDetalle();
                    est.setIdScotiabankContador(idBC);
                    est.setTipoConceptoAgrupar("CUOTA");
                    est.setConcepto("FINAN");
                    est.setIdConcepto(10);
                    est.setIdFinanciamientoDetalle(fdp.getId());
                    est.setMonto(fdp.getMonto());
                    est.setNroConcepto(contadorFinanciamiento);
                    int contadorNroOrden = nroOrdenAnioMesInicial - 1 + contadorFinanciamiento;
                    AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(contadorNroOrden);
                    est.setIdAnioMesCuota(am.getId());
//                    int correlativo = cDAO.ObtenerCorrelativoScotiabank_SegunIdScotiabank_AnioMes(idBC, am.getId());
//                    est.setCorrelativo(correlativo + 1);
                    String identificacionPago = c.getCcodigoCole() + "CUOTA-" + this.ObtenerMes(am.getMes()) + am.getAnio();
                    est.setIdentificacionPago(identificacionPago);
                    contadorFinanciamiento = contadorFinanciamiento + 1;
                    this.GuardarScotiabankContadorDetalle(est);
                }
            }
        }
        /*INICIO ---------- SABER SI TIENE REINCORPORACION A PAGAR*/
        int contadorReincorporacion = 1;
        if (listaReincorporacionAPagar != null) {
            if (listaReincorporacionAPagar.size() > 0) {
                for (ReincorporacionDocumentoPago rdp : listaReincorporacionAPagar) {
                    ScotiabankContadorDetalle est = new ScotiabankContadorDetalle();
                    est.setIdScotiabankContador(idBC);
                    est.setTipoConceptoAgrupar("CUOTA");
                    est.setConcepto("REINC");
                    est.setIdConcepto(5850);
                    est.setIdReincorporacionDetalle(rdp.getId());
                    est.setMonto(rdp.getMonto());
                    est.setNroConcepto(contadorReincorporacion);
//                    est.setIdAnioMesCuota(idAnioMesInicial - 1 + contadorReincorporacion);
                    int contadorNroOrden = nroOrdenAnioMesInicial - 1 + contadorReincorporacion;
                    AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(contadorNroOrden);
                    est.setIdAnioMesCuota(am.getId());
//                    int correlativo = cDAO.ObtenerCorrelativoScotiabank_SegunIdScotiabank_AnioMes(idBC, am.getId());
//                    est.setCorrelativo(correlativo + 1);
                    String identificacionPago = c.getCcodigoCole() + "CUOTA-" + this.ObtenerMes(am.getMes()) + am.getAnio();
                    est.setIdentificacionPago(identificacionPago);
                    contadorReincorporacion = contadorReincorporacion + 1;
                    this.GuardarScotiabankContadorDetalle(est);
                }
            }
        }

        DeudasBO dBO = DeudasBO.getInstance();
        List<Deuda> listadoDeuda = dBO.ObtenerTodasDeudasPendientes(c.getIdCliente());
        if (listadoDeuda != null) {
//            int correlativo = this.ObtenerCorrelativoScotiabank_SegunIdScotiabank(idScotiabank);
            for (Deuda d : listadoDeuda) {
                ScotiabankContadorDetalle est = new ScotiabankContadorDetalle();
                est.setIdScotiabankContador(idBC);
                est.setIdConcepto(d.getConceptoPagoDetalle().getIdConceptoPagoDetalle());
                est.setIdDeudaDetalle(d.getId());
                est.setMonto(d.getMonto());
                est.setNroConcepto(1);
//                est.setCorrelativo(correlativo + 1);
                if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4603) { //MULTA 29-01-2008
                    est.setIdAnioMesCuota(1412);
                    est.setConcepto("MULTA-ENE2008");
                    est.setTipoConceptoAgrupar("MULTA-ENE2008");
                    est.setIdentificacionPago(c.getCcodigoCole() + "MULTA-ENE2008");
                } else {
                    if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4604) { //MULTA 28-02-2008
                        est.setIdAnioMesCuota(1413);
                        est.setConcepto("MULTA-FEB2008");
                        est.setTipoConceptoAgrupar("MULTA-FEB2008");
                        est.setIdentificacionPago(c.getCcodigoCole() + "MULTA-FEB2008");
                    } else {
                        if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4225) { //REINSERCION 2005
                            est.setIdAnioMesCuota(1386);
                            est.setConcepto("REINSERCION-2005");
                            est.setTipoConceptoAgrupar("REINSERCI2005");
                            est.setIdentificacionPago(c.getCcodigoCole() + "REINSERCI2005");
                        } else {
                            if (d.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 4307) { //REINCORPORACION 2006
                                est.setIdAnioMesCuota(1398);
                                est.setConcepto("REINCORPORACION-2006");
                                est.setTipoConceptoAgrupar("REINCORPO2006");
                                est.setIdentificacionPago(c.getCcodigoCole() + "REINCORPO2006");
                            } else {
//                                                wr.write("D," + codigoColegiado + "," + apePat + "," + apeMat + "," + nombre + ",1," + codigoConcepto + ",200612,2006/12/01," + String.format(Locale.ROOT, "%.2f", importe) + "\r\n");
                            }
                        }
                    }
                }
                this.GuardarScotiabankContadorDetalle(est);
//                correlativo = correlativo + 1;
            }
        }
    }

    @Override
    public void GenerarEstructuraDatosCobranzaInstitucional(String tipoMovimiento) {
        try {
            File carpeta = new File("archivos");
            carpeta.mkdirs();
            carpeta = new File("archivos/CAJA_AREQUIPA");
            carpeta.mkdirs();
            BufferedWriter bw = null;
            PrintWriter wr = null;
            File f;
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            String nombreArchivo = "archivos/CAJA_AREQUIPA/TXT" + formato.format(fechaSistema) + ".txt";
            f = new File(nombreArchivo);
            FileWriter w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
//-----------------------------------------
            CuotasBO cuBO = CuotasBO.getInstance();
            List<Cliente> listado = this.ObtenerTodosClientes();
            int nroOperacion = this.obtenerNroOperacion_CajaArequipa();
            wr.write("T,CODIGO,COLEGIO DE CONTADORES PUBLICOS DE AREQUIPA,XX," + tipoMovimiento + "\r\n");
            for (Cliente c : listado) {
                if (c.getTipoCliente().equals("C")) {
                    if (c.getEstado() != null) {
                        if (c.getEstado().equals("H") || c.getEstado().equals("I") || c.getEstado().equals("O") || c.getEstado().equals("V")) {
                            if (c.getCfechaAfiliacion() != null) {
                                this.CuotasAPagar_CajaArequipa(nroOperacion, c, 12);
                            }
                        }
                    }
                }
            }
            List<Object> listadoCajaArequipa = this.ObtenerListado_CajaArequipa(nroOperacion);
            for (Object pobj : listadoCajaArequipa) {
                Object[] obj = (Object[]) pobj;
                String codColegiado = (String) obj[0];
                String apellPat = (String) obj[1];
                String apellMat = (String) obj[2];
                String nomb = (String) obj[3];
                String tipConcepto = (String) obj[4];
                int anio = (int) obj[5];
                int mes = (int) obj[6];
                double monto = (double) obj[7];
                String codConcepto = tipConcepto + "-" + String.valueOf(anio) + "-" + String.valueOf(mes);
                String fechaEmision = "01/" + String.format("%02d", mes) + "/" + anio;
                wr.write("D," + codColegiado + "," + apellPat + "," + apellMat + "," + nomb + ",1," + codConcepto + "," + tipConcepto + "," + fechaEmision + "," + String.format(Locale.ROOT, "%.2f", monto) + "\r\n");
            }
            //-------------------------------------            
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

    @Override
    public void GenerarEstructuraDatosCobranzaInstitucional_BBVA(JProgressBar pb, String tipoMovimiento, int nroOrdenDesde, int nroOrdenHasta, String tipoIdentificacion) {
        try {
            AnioMesBO amBO = AnioMesBO.getInstance();
            CuotasBO cBO = CuotasBO.getInstance();
            SeguridadBO sBO = SeguridadBO.getInstance();
            Date fechaOperacion = sBO.ObtenerFechaServidor();
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat fa = new SimpleDateFormat("yyyy");
            SimpleDateFormat fm = new SimpleDateFormat("MM");
//-----------------------------------------
            List listado = this.ObtenerContadores_BBVA(nroOrdenDesde, nroOrdenHasta);
            int nroOperacion = this.obtenerNroOperacion_BBVA();
            int version = this.obtenerVersion_BBVA(f.format(fechaOperacion));
            Bbva b = new Bbva();
            b.setFechaOperacion(fechaOperacion);
            b.setNroOperacion(nroOperacion);
            b.setNroOrdenDesde(nroOrdenDesde);
            b.setNroOrdenHasta(nroOrdenHasta);
            b.setTipoIdentificacion(tipoIdentificacion);
            b.setVersion(version);
            this.GuardarBbva(b);
            pb.setMaximum(listado.size());
            int nroCuotas = nroOrdenHasta - nroOrdenDesde + 1;
            int contador = 0;
            for (Object p : listado) {
                Object[] o = (Object[]) p;
                int id = (int) o[0];
                String estado = (String) o[1];
                Date fechaAfiliacion = (Date) o[2];
                String codigoColegiado = (String) o[3];
                Cliente c = new Cliente();
                c.setIdCliente(id);
                c.setEstado(estado);
                c.setCcodigoCole(codigoColegiado);
                c.setCfechaAfiliacion(fechaAfiliacion);
                c.setTipoCliente("C");
                if (c.getIdCliente() == 38) {
                    System.out.println(c.getIdCliente());
                }
                if (c.getEstado().equals("H") || c.getEstado().equals("I") || c.getEstado().equals("O") || c.getEstado().equals("V")) {
                    if (c.getCfechaAfiliacion() != null) {
                        Cuotas cuota = cBO.ObtenerUltimaCuota(c.getIdCliente());
                        if (cuota == null) {
                            AnioMes amAfiliacion = amBO.ObtenerAnioMes(Integer.valueOf(fa.format(c.getCfechaAfiliacion())), Integer.valueOf(fm.format(c.getCfechaAfiliacion())));
                            nroCuotas = nroOrdenHasta - amAfiliacion.getNroOrden() + 1;
                        } else {
                            nroCuotas = nroOrdenHasta - cuota.getAnioMes().getNroOrden();
                        }
                        BbvaContador bc = new BbvaContador();
                        bc.setIdBbva(b.getId());
                        bc.setIdCliente(id);
                        this.GuardarBbvaContador(bc);
                        this.CuotasAPagar_BBVA(bc.getId(), c, nroCuotas);
                    }
                }
                pb.setValue(contador);
                contador = contador + 1;
            }
            JOptionPane.showMessageDialog(null,
                    "SE GENERÓ LA OPERACION NRO " + nroOperacion,
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, NO SE PUDO GENERAR LA OPERACIÓN",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void GenerarEstructuraDatosCobranzaInstitucional_Scotiabank(JProgressBar pb, int nroOrdenDesde, int nroOrdenHasta) {
        try {
            AnioMesBO amBO = AnioMesBO.getInstance();
            CuotasBO cBO = CuotasBO.getInstance();
            SeguridadBO sBO = SeguridadBO.getInstance();
            Date fechaOperacion = sBO.ObtenerFechaServidor();
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat fa = new SimpleDateFormat("yyyy");
            SimpleDateFormat fm = new SimpleDateFormat("MM");
//-----------------------------------------
            List listado = this.ObtenerContadores_BBVA(nroOrdenDesde, nroOrdenHasta);
            int nroOperacion = this.obtenerNroOperacion_SCOTIABANK();
//            int version = this.obtenerVersion_BBVA(f.format(fechaOperacion));
            Scotiabank s = new Scotiabank();
            s.setFechaOperacion(fechaOperacion);
            s.setNroOperacion(nroOperacion);
            s.setNroOrdenDesde(nroOrdenDesde);
            s.setNroOrdenHasta(nroOrdenHasta);
//            s.setTipoIdentificacion(tipoIdentificacion);
//            b.set(version);
            this.GuardarScotiabank(s);
            pb.setMaximum(listado.size());
            int nroCuotas = nroOrdenHasta - nroOrdenDesde + 1;
            int contador = 0;
            for (Object p : listado) {
                Object[] o = (Object[]) p;
                int id = (int) o[0];
                String estado = (String) o[1];
                Date fechaAfiliacion = (Date) o[2];
                String codigoColegiado = (String) o[3];
                Cliente c = new Cliente();
                c.setIdCliente(id);
                c.setEstado(estado);
                c.setCcodigoCole(codigoColegiado);
                c.setCfechaAfiliacion(fechaAfiliacion);
                c.setTipoCliente("C");
                if (c.getIdCliente() == 38) {
                    System.out.println(c.getIdCliente());
                }
                if (c.getEstado().equals("H") || c.getEstado().equals("I") || c.getEstado().equals("O") || c.getEstado().equals("V")) {
                    if (c.getCfechaAfiliacion() != null) {
                        Cuotas cuota = cBO.ObtenerUltimaCuota(c.getIdCliente());
                        if (cuota == null) {
                            AnioMes amAfiliacion = amBO.ObtenerAnioMes(Integer.valueOf(fa.format(c.getCfechaAfiliacion())), Integer.valueOf(fm.format(c.getCfechaAfiliacion())));
                            nroCuotas = nroOrdenHasta - amAfiliacion.getNroOrden() + 1;
                        } else {
                            nroCuotas = nroOrdenHasta - cuota.getAnioMes().getNroOrden();
                        }
                        ScotiabankContador bc = new ScotiabankContador();
                        bc.setIdScotiabank(s.getId());
                        bc.setIdCliente(id);
                        this.GuardarScotiabankContador(bc);
                        this.CuotasAPagar_Scotiabank(s.getId(), bc.getId(), c, nroCuotas);
                    }
                }
                pb.setValue(contador);
                contador = contador + 1;
            }
            ClienteDAO cDAO = ClienteDAO.getInstance();
            cDAO.actualizarCorrelativoScotiabank(s.getNroOperacion());
            JOptionPane.showMessageDialog(null,
                    "SE GENERÓ LA OPERACION NRO " + nroOperacion,
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,
                    "ERROR, NO SE PUDO GENERAR LA OPERACIÓN",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void InsertarCuentaCorrienteCliente(CuentaCorriente cuentaCorriente) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        cDAO.InsertarCuentaCorrienteCliente(cuentaCorriente);
    }

    @Override
    public List ObtenerTodosClientes() {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerTodosClientes();
    }

    @Override
    public List ObtenerTodosContadores() {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerTodosContadores();
    }

    @Override
    public List ObtenerContadores_BBVA(int nroOrdenDesde, int nroOrdenHasta) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerContadores_BBVA(nroOrdenDesde, nroOrdenHasta);
    }

    @Override
    public List ObtenerContadoresSegunCobrador(String codContador) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerContadoresSegunCobrador(codContador);
    }

    @Override
    public Cliente ObtenerClienteSegunCodigoContador(String codigo) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerClienteSegunCodigoContador(codigo);
    }

    @Override
    public List ObtenerBbvaRetornoDetalle_SegunIdBbvaRetorno(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaRetornoDetalle_SegunIdBbvaRetorno(idRetorno);
    }

    @Override
    public List ObtenerBbvaRetornoSoloClientes_SegunIdBbvaRetorno(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaRetornoSoloClientes_SegunIdBbvaRetorno(idRetorno);
    }

    @Override
    public List ObtenerScotiabankRetornoSoloClientes_SegunIdScotiabankRetorno(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerScotiabankRetornoSoloClientes_SegunIdScotiabankRetorno(idRetorno);
    }

    private boolean ValidarCobranzaBbva(BbvaRetorno br) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        List l = cDAO.ObtenerPagosNoPertenecen_Bbva(br.getId());
        if (l != null) {
            if (l.size() > 0) {
                String mensaje = "LOS SIGUIENTES PAGOS NO EXISTEN EN EL ARCHIVO ENVIADO AL BANCO:\n";
                for (Object po : l) {
//                    Object[] o = (Object[]) po;
                    mensaje = mensaje + po + "\n";
                }
                JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        List lMontos = cDAO.ObtenerMontosDiferentes_Bbva(br.getIdBbva(), br.getId());
        if (lMontos != null) {
            if (lMontos.size() > 0) {
                String mensaje = "LOS SIGUIENTES PAGOS NO COINCIDEN EN LOS MONTOS:\n";
                for (Object po : lMontos) {
                    Object[] o = (Object[]) po;
                    mensaje = mensaje + (String) o[0] + "\n";
                }
                JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        List<Object> lRetorno = this.ObtenerBbvaRetornoDetalle_ParaValidacion(br.getId());
        for (Object o : lRetorno) {
            Object[] brd = (Object[]) o;
            int idCliente = (int) brd[0];
            int idAnioMesCuota = (int) brd[1];
            int idConcepto = (int) brd[2];
            String concepto = (String) brd[3];
            int idFinanDetalle = (int) brd[4];
            int idReincDetalle = (int) brd[5];
            int idDeudaDetalle = (int) brd[6];
            String referencia = (String) brd[7];
            SeguridadBO sBO = SeguridadBO.getInstance();
            if (concepto.equals("CUOTA")) {
                CuotasBO cBO = CuotasBO.getInstance();
                Cuotas cc = cBO.ObtenerCuota(idCliente, idAnioMesCuota, idConcepto);
                ConceptoPagoDetalle con = (ConceptoPagoDetalle) sBO.CargarObjeto("ConceptoPagoDetalle", idConcepto);
                if (cc != null) {
                    JOptionPane.showMessageDialog(null, "EL CONCEPTO " + con.getCodigo() + " CORRESPONDIENTE A LA CUOTA " + referencia + " YA FUE CANCELADO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                if (concepto.equals("FINAN")) {
                    FinanciamientoDocumentoPago fdp = (FinanciamientoDocumentoPago) sBO.CargarObjeto("FinanciamientoDocumentoPago", idFinanDetalle);
                    if (!fdp.getEstado().equals("FS")) {
                        JOptionPane.showMessageDialog(null, "EL FINACIAMIENTO DE LA CUOTA " + referencia + " NO ESTA DISPONIBLE PARA PAGAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    if (concepto.equals("REINC")) {
                        ReincorporacionDocumentoPago rdp = (ReincorporacionDocumentoPago) sBO.CargarObjeto("ReincorporacionDocumentoPago", idReincDetalle);
                        if (!rdp.getEstado().equals("RS")) {
                            JOptionPane.showMessageDialog(null, "LA REINCORPORACION DE LA CUOTA " + referencia + " NO ESTA DISPONIBLE PARA PAGAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    } else {
                        Deuda d = (Deuda) sBO.CargarObjeto("Deuda", idDeudaDetalle);
                        if (!d.getEstado().equals("DP")) {
                            JOptionPane.showMessageDialog(null, "LA DEUDA DE LA CUOTA " + referencia + " NO ESTA DISPONIBLE PARA PAGAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean ValidarCobranzaScotiabank(ScotiabankRetorno br) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        List l = cDAO.ObtenerPagosNoPertenecen_Scotiabank(br.getId());
        if (l != null) {
            if (l.size() > 0) {
                String mensaje = "LOS SIGUIENTES PAGOS NO EXISTEN EN EL ARCHIVO ENVIADO AL BANCO:\n";
                for (Object po : l) {
//                    Object[] o = (Object[]) po;
                    mensaje = mensaje + po + "\n";
                }
                JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        List lMontos = cDAO.ObtenerMontosDiferentes_Scotiabank(br.getIdScotiabank(), br.getId());
        if (lMontos != null) {
            if (lMontos.size() > 0) {
                String mensaje = "LOS SIGUIENTES PAGOS NO COINCIDEN EN LOS MONTOS:\n";
                for (Object po : lMontos) {
                    Object[] o = (Object[]) po;
                    mensaje = mensaje + (String) o[0] + "\n";
                }
                JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        List<Object> lRetorno = this.ObtenerScotiabankRetornoDetalle_ParaValidacion(br.getId());
        for (Object o : lRetorno) {
            Object[] brd = (Object[]) o;
            int idCliente = (int) brd[0];
            int idAnioMesCuota = (int) brd[1];
            int idConcepto = (int) brd[2];
            String concepto = (String) brd[3];
            int idFinanDetalle = (int) brd[4];
            int idReincDetalle = (int) brd[5];
            int idDeudaDetalle = (int) brd[6];
            String referencia = (String) brd[7];
            SeguridadBO sBO = SeguridadBO.getInstance();
            if (concepto.equals("CUOTA")) {
                CuotasBO cBO = CuotasBO.getInstance();
                Cuotas cc = cBO.ObtenerCuota(idCliente, idAnioMesCuota, idConcepto);
                ConceptoPagoDetalle con = (ConceptoPagoDetalle) sBO.CargarObjeto("ConceptoPagoDetalle", idConcepto);
                if (cc != null) {
                    JOptionPane.showMessageDialog(null, "EL CONCEPTO " + con.getCodigo() + " CORRESPONDIENTE A LA CUOTA " + referencia + " YA FUE CANCELADO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                if (concepto.equals("FINAN")) {
                    FinanciamientoDocumentoPago fdp = (FinanciamientoDocumentoPago) sBO.CargarObjeto("FinanciamientoDocumentoPago", idFinanDetalle);
                    if (!fdp.getEstado().equals("FS")) {
                        JOptionPane.showMessageDialog(null, "EL FINACIAMIENTO DE LA CUOTA " + referencia + " NO ESTA DISPONIBLE PARA PAGAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    if (concepto.equals("REINC")) {
                        ReincorporacionDocumentoPago rdp = (ReincorporacionDocumentoPago) sBO.CargarObjeto("ReincorporacionDocumentoPago", idReincDetalle);
                        if (!rdp.getEstado().equals("RS")) {
                            JOptionPane.showMessageDialog(null, "LA REINCORPORACION DE LA CUOTA " + referencia + " NO ESTA DISPONIBLE PARA PAGAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    } else {
                        Deuda d = (Deuda) sBO.CargarObjeto("Deuda", idDeudaDetalle);
                        if (!d.getEstado().equals("DP")) {
                            JOptionPane.showMessageDialog(null, "LA DEUDA DE LA CUOTA " + referencia + " NO ESTA DISPONIBLE PARA PAGAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void GenerarComprobantePagoBbva(Date fecha, int idTipoDoc, String nroSerie, int idBbvaRetorno) {
        SeguridadBO sBO = SeguridadBO.getInstance();
        BbvaRetorno br = (BbvaRetorno) sBO.CargarObjeto("BbvaRetorno", idBbvaRetorno);
        boolean esValido = this.ValidarCobranzaBbva(br);
        if (!esValido) {
            return;
        }
        ClienteBO cBO = ClienteBO.getInstance();
//        List l = this.ObtenerBbvaRetornoDetalle_SegunIdBbvaRetorno(idBbvaRetorno);
        List l = this.ObtenerBbvaRetornoSoloClientes_SegunIdBbvaRetorno(idBbvaRetorno);
        for (Object obj : l) {
//            Object[] obj = (Object[]) pobj;
            int idCliente = (int) obj;
//            Date fechaPago = (Date) obj[1];
            Cliente cliente = (Cliente) sBO.CargarObjeto("Cliente", idCliente);
            cBO.GenerarComprobantePago_SegunBbvaRetorno(fecha, idTipoDoc, nroSerie, idBbvaRetorno, cliente);
            if (cliente.getEstado().equals("H") || cliente.getEstado().equals("I")) {
                cBO.ValidarHabilitacion(cliente);
            }
        }
        br.setSeGeneroComprobantes("S");
        cBO.ActualizarBbvaRetorno(br);
        JOptionPane.showMessageDialog(null, "SE GENERO LOS COMPROBANTES", "OK", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void GenerarComprobantePagoScotiabank(Date fecha, int idTipoDoc, String nroSerie, int idScotiabankRetorno) {
        SeguridadBO sBO = SeguridadBO.getInstance();
        ScotiabankRetorno br = (ScotiabankRetorno) sBO.CargarObjeto("ScotiabankRetorno", idScotiabankRetorno);
        boolean esValido = this.ValidarCobranzaScotiabank(br);
        if (!esValido) {
            return;
        }
        ClienteBO cBO = ClienteBO.getInstance();
//        List l = this.ObtenerBbvaRetornoDetalle_SegunIdBbvaRetorno(idBbvaRetorno);
        List l = this.ObtenerScotiabankRetornoSoloClientes_SegunIdScotiabankRetorno(idScotiabankRetorno);
        for (Object obj : l) {
//            Object[] obj = (Object[]) pobj;
            int idCliente = (int) obj;
//            Date fechaPago = (Date) obj[1];
            Cliente cliente = (Cliente) sBO.CargarObjeto("Cliente", idCliente);
            cBO.GenerarComprobantePago_SegunScotiabankRetorno(fecha, idTipoDoc, nroSerie, idScotiabankRetorno, cliente);
            if (cliente.getEstado().equals("H") || cliente.getEstado().equals("I")) {
                cBO.ValidarHabilitacion(cliente);
            }
        }
        br.setSeGeneroComprobantes("S");
        cBO.ActualizarScotiabankRetorno(br);
        JOptionPane.showMessageDialog(null, "SE GENERO LOS COMPROBANTES", "OK", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ImprimirDocumentoPago_SegunBbva(int idBbvaRetorno) {
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
            List l = this.ObtenerComprobantePago_SegunBbvaRetorno(idBbvaRetorno);
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            List<DocumentoPago> listado = dpBO.ObtenerDocumentosPagos_SegunListado(l);
            for (DocumentoPago doc : listado) {
                if (doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1) {
                    fullPath = "reportes/Factura.jasper";
                } else {
                    fullPath = "reportes/Boleta.jasper";
                }
                param = new HashMap();
                param.put("IDDOCUMENTOPAGO", doc.getIdDocumentoPago());
                double montoTotal = dpBO.ObtenerMontoTotalComprobante(doc.getIdDocumentoPago());
                montoTotal = Math.round(montoTotal * Math.pow(10, 2)) / Math.pow(10, 2);
                param.put("TOTALENLETRAS", "SON: " + NumeroLetras.convertirNumerosALetras(montoTotal, doc.getMoneda()));
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
                JasperPrintManager.printPage(JPrint, 0, false);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void ImprimirDocumentoPago_SegunScotiabank(int idScotiabankRetorno) {
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
            List l = this.ObtenerComprobantePago_SegunScotiabankRetorno(idScotiabankRetorno);
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            List<DocumentoPago> listado = dpBO.ObtenerDocumentosPagos_SegunListado(l);
            for (DocumentoPago doc : listado) {
                if (doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() == 1) {
                    fullPath = "reportes/Factura.jasper";
                } else {
                    fullPath = "reportes/Boleta.jasper";
                }
                param = new HashMap();
                param.put("IDDOCUMENTOPAGO", doc.getIdDocumentoPago());
                double montoTotal = dpBO.ObtenerMontoTotalComprobante(doc.getIdDocumentoPago());
                montoTotal = Math.round(montoTotal * Math.pow(10, 2)) / Math.pow(10, 2);
                param.put("TOTALENLETRAS", "SON: " + NumeroLetras.convertirNumerosALetras(montoTotal, doc.getMoneda()));
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
                JasperPrintManager.printPage(JPrint, 0, false);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public List ObtenerBbvaRetornoDetalle(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaRetornoDetalle(idRetorno);
    }

    @Override
    public List ObtenerScotiabankRetornoDetalle(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerScotiabankRetornoDetalle(idRetorno);
    }

    @Override
    public List ObtenerComprobantePago_SegunBbvaRetorno(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerComprobantePago_SegunBbvaRetorno(idRetorno);
    }

    @Override
    public List ObtenerClienteCertificado_SegunCliente(int idCliente) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerClienteCertificado_SegunCliente(idCliente);
    }

    @Override
    public List ObtenerClienteDerechoHabiente_SegunCliente(int idCliente) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerClienteDerechoHabiente_SegunCliente(idCliente);
    }

    @Override
    public List ObtenerClienteAuditorIndependiente_SegunCliente(int idCliente) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerClienteAuditorIndependiente_SegunCliente(idCliente);
    }

    @Override
    public List ObtenerClienteComitePerito_SegunCliente(int idCliente) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerClienteComitePerito_SegunCliente(idCliente);
    }

    @Override
    public List ObtenerFichaComiteDesearia_SegunFicha(int idFicha) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerFichaComiteDesearia_SegunFicha(idFicha);
    }

    @Override
    public List ObtenerFichaDeporte_SegunFicha(int idFicha) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerFichaDeporte_SegunFicha(idFicha);
    }

    @Override
    public List ObtenerFichaInstitucion_SegunFicha(int idFicha) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerFichaInstitucion_SegunFicha(idFicha);
    }

    @Override
    public List ObtenerComprobantePago_SegunScotiabankRetorno(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerComprobantePago_SegunScotiabankRetorno(idRetorno);
    }

    @Override
    public List ObtenerBbvaRetornoDetalle_ParaValidacion(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaRetornoDetalle_ParaValidacion(idRetorno);
    }

    @Override
    public List ObtenerScotiabankRetornoDetalle_ParaValidacion(int idRetorno) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerScotiabankRetornoDetalle_ParaValidacion(idRetorno);
    }

    @Override
    public List ObtenerBbvaRetorno_SegunIdBbva(int idBbva) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerBbvaRetorno_SegunIdBbva(idBbva);
    }

    @Override
    public List ObtenerScotiabankRetorno_SegunIdScotiabank(int idBbva) {
        ClienteDAO cDAO = ClienteDAO.getInstance();
        return cDAO.ObtenerScotiabankRetorno_SegunIdScotiabank(idBbva);
    }

    @Override
    public List ObtenerTodasSociedades() {
        ClienteDAO sDAO = ClienteDAO.getInstance();
        return sDAO.ObtenerTodasSociedas();
    }

    @Override
    public List obtenerUniversidades() {
        ClienteDAO sDAO = ClienteDAO.getInstance();
        return sDAO.obtenerUniversidades();
    }

}
