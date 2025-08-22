/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.DocumentoPagoDAO;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteCertificado;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.mapeo.entidades.ConstanciaHabilitacion;
import caja.mapeo.entidades.CuentaCorriente;
import caja.mapeo.entidades.Cuotas;
import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.DocPagoAnulado;
import caja.mapeo.entidades.DocPagoAnuladoDetalle;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.Operacion;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import caja.mapeo.entidades.ResumenDiario;
import caja.mapeo.entidades.ResumenDiarioDetalle;
import caja.mapeo.entidades.Serie;
import caja.mapeo.entidades.TipoAfectacion;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.mapeo.entidades.TipoTributo;
import caja.mapeo.entidades.ValeAcademico;
import caja.mapeo.entidades.ValeAcademicoConsumo;
import caja.utils.GenerarExcel;
import caja.utils.UtilConstante;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author user
 */
public class DocumentoPagoBO implements DocumentoPagoBOIFace {

    private static DocumentoPagoBO INSTANCE = new DocumentoPagoBO();
//    private DocumentoPago documentoPago;
    private List<Object> cuotasAPagar;
    private List<FinanciamientoDocumentoPago> financiamientoAPagar;
    private List<ReincorporacionDocumentoPago> reincorporacionAPagar;
    private List<TipoDocPago> listaTipoDocPago;
    private List<ConceptoPagoDetalle> listaConceptoPago;
    private List<Cliente> listaClientes;
    private List<Cobrador> listaCobradores;
    private boolean descuentoCuotas = false;
    private Cliente clienteEmpresa = null; //USADO PARA EL CASO EN QUE LA EMPRESA PAGA LA CUOTA DEL CONTADOR

    private List<DocumentoPago> resultadoBusqueda;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (DocumentoPagoBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DocumentoPagoBO();
                }
            }
        }
    }

    /**
     * @return the resultadoBusqueda
     */
    public List<DocumentoPago> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    /**
     * @param resultadoBusqueda the resultadoBusqueda to set
     */
    public void setResultadoBusqueda(List<DocumentoPago> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    public Cliente getClienteEmpresa() {
        return clienteEmpresa;
    }

    public void setClienteEmpresa(Cliente clienteEmpresa) {
        this.clienteEmpresa = clienteEmpresa;
    }

    public boolean isDescuentoCuotas() {
        return descuentoCuotas;
    }

    public void setDescuentoCuotas(boolean descuentoCuotas) {
        this.descuentoCuotas = descuentoCuotas;
    }

    public List<FinanciamientoDocumentoPago> getFinanciamientoAPagar() {
        return financiamientoAPagar;
    }

    public List<ReincorporacionDocumentoPago> getReincorporacionAPagar() {
        return reincorporacionAPagar;
    }

    public void setReincorporacionAPagar(List<ReincorporacionDocumentoPago> reincorporacionAPagar) {
        this.reincorporacionAPagar = reincorporacionAPagar;
    }

    public List<Object> getCuotasAPagar() {
        return cuotasAPagar;
    }

    public static DocumentoPagoBO getInstance() {
        createInstance();
        return INSTANCE;
    }

//    public DocumentoPago getDocumentoPago() {
//        return documentoPago;
//    }
//
//    public void setDocumentoPago(DocumentoPago pdocPago) {
//        documentoPago = pdocPago;
//    }
    @Override
    public List ObtenerTodosConceptoPago() {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        if (listaConceptoPago == null) {
            this.listaConceptoPago = dpDAO.ObtenerTodosConceptoPago();
        }
        return this.listaConceptoPago;
    }

    @Override
    public List ObtenerTodosClientes() {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        if (this.listaClientes == null) {
            this.listaClientes = dpDAO.ObtenerTodosClientes();
        }
        return this.listaClientes;
    }

    @Override
    public List ObtenerTodosClientesNuevamente() {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        this.listaClientes = dpDAO.ObtenerTodosClientes();
        return this.listaClientes;
    }

    @Override
    public void AgregarClientesListadoLocal(Cliente c) {
//        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        if (this.listaClientes != null) {
            this.listaClientes.add(c);
        }
    }

    @Override
    public List ObtenerTodosClientes_Recarga() {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        this.listaClientes = dpDAO.ObtenerTodosClientes();
        return this.listaClientes;
    }

    @Override
    public List ObtenerDeudaNoFinanciadas(int idCliente) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerDeudaNoFinanciadas(idCliente);
    }

    /*@Override
     public List ObtenerTodasSociedades() {
     DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
     if (this.listaSociedades == null) {
     this.listaSociedades = dpDAO.ObtenerTodasSociedades();
     }
     return this.listaSociedades;
     }*/
    @Override
    public List ObtenerTodosCobradores() {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        if (this.listaCobradores == null) {
            this.listaCobradores = dpDAO.ObtenerTodosCobradores();
        }
        return this.listaCobradores;
    }

    @Override
    public List ObtenerTodosTipoDocPago() {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        if (this.listaTipoDocPago == null) {
            this.listaTipoDocPago = dpDAO.ObtenerTodosTipoDocPago();
        }
        return this.listaTipoDocPago;
    }

    @Override
    public List ObtenerTodasSeries_SegunTipoDocumento(int id) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerTodasSeries_SegunTipoDocumento(id);
    }

    @Override
    public double ObtenerSumaTotalValorVenta(int id) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        double total = dpDAO.ObtenerSumaTotalValorVenta(id);
        total = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
        return total;
    }

    @Override
    public List ObtenerTributos_TotalesValorVenta(int id) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerTributos_TotalesValorVenta(id);
    }

    @Override
    public double ObtenerSumaTotalIGV(int id) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        double total = dpDAO.ObtenerSumaTotalIGV(id);
        total = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
        return total;
    }

    @Override
    public double ObtenerSumaTotalPrecioVenta(int id) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        double total = dpDAO.ObtenerSumaTotalPrecioVenta(id);
        total = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
        return total;
    }

    @Override
    public boolean GuardarDocumentoPago(DocumentoPago doc) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            int num = 0;
            if (doc.getNroDocumentoPago() > 0) {
                boolean estaOcupado = dpDAO.VerificarSiNroDocEstaOcupado(doc);
                if (estaOcupado == true) {
                    SeguridadBO sBO = SeguridadBO.getInstance();
                    sBO.GenerarLog("EL NRO DE COMPROBANTE YA SE ENCUENTRA OCUPADO: NO SE PUEDE GENERAR COMPROBANTE(DocumentoPagoBO.GuardarDocumentoPago)");
                    return false;
                }
                boolean excede = dpDAO.VerificarSiNroExcedeCorrelativo(doc);
                if (excede == true) {
                    SeguridadBO sBO = SeguridadBO.getInstance();
                    sBO.GenerarLog("EL NRO DE COMPROBANTE SUPERA AL NRO CORRELATIVO PROXIMO(DocumentoPagoBO.GuardarDocumentoPago)");
                    return false;
                }
            } else {
                num = dpDAO.ObtenerCorrelativo(doc.getTipoDocSerie().getId());
                doc.setNroDocumentoPago(num);
            }
            dpDAO.GuardarDocumentoPago(doc);
//            this.documentoPago = doc;
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return false;
        }
    }

    private void guardarResumenDiarioDetalle(List<ResumenDiarioDetalle> l) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        for (ResumenDiarioDetalle d : l) {
            if (d.getId() == 0) {
                dpDAO.GuardarObjeto(d);
            } else {
                dpDAO.ActualizarObjeto(d);
            }
        }
    }

    @Override
    public boolean GuardarResumenDiario(ResumenDiario r, List<ResumenDiarioDetalle> l) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            int num = 0;
            if (r.getId() == 0) {
                SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
                num = dpDAO.ObtenerCorrelativoResumenDiario(f.format(r.getFecha()));
                r.setNro(num + 1);
                dpDAO.GuardarObjeto(r);
            } else {
                dpDAO.ActualizarObjeto(r);
            }
            this.guardarResumenDiarioDetalle(l);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return false;
        }
    }

    @Override
    public boolean GuardarDocumentoPago_SinValidacion(DocumentoPago doc) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            dpDAO.GuardarDocumentoPago(doc);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return false;
        }
    }

    @Override
    public boolean GuardarDocumentoPagoDet_SinValidadcion(DocumentoPagoDet det) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            dpDAO.GuardarDocumentoPagoDet(det);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return false;
        }
    }

    @Override
    public boolean VerificarNroComprobanteExiste(DocumentoPago doc) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        boolean estaOcupado = dpDAO.VerificarSiNroDocEstaOcupado(doc);
        return estaOcupado;
    }

    @Override
    public boolean VerificarNroComprobanteExiste(int idTipoDoc, int nroDoc, String nroSerie) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        boolean estaOcupado = dpDAO.VerificarSiNroDocEstaOcupado(idTipoDoc, nroDoc, nroSerie);
        return estaOcupado;
    }

    @Override
    public boolean GuardarDocumentoPago_CualquierNro(DocumentoPago doc) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            dpDAO.GuardarDocumentoPago(doc);
            List<Operacion> mainList = new ArrayList<Operacion>();
            mainList.addAll(doc.getOperacions());
            mainList.forEach((Operacion o) -> {
                o.setDocumentoPago(doc);
                dpDAO.GuardarOperacion(o);
            });
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return false;
        }
    }

    @Override
    public int ObtenerCorrelativoComprobante(DocumentoPago doc) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        int num = 0;
        if (doc.getNroDocumentoPago() == 0) {
            num = dpDAO.ObtenerCorrelativo(doc.getTipoDocSerie().getId());
            num = num + 1;
            doc.setNroDocumentoPago(num);
        }
        return num;
    }

    @Override
    public int ObtenerCorrelativoAnulacion(DocumentoPago doc, int anio, int mes, int dia) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        int num = dpDAO.ObtenerCorrelativoAnulacion(doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago(), anio, mes, dia);
        num = num + 1;
        doc.setCorrelativoAnulacion(num);
        return num;
    }

    @Override
    public int ObtenerSolamenteCorrelativo(int idTipoDocSerie) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            int num = dpDAO.ObtenerSolomenteCorrelativo(idTipoDocSerie);
            return num;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return 0;
        }
    }

    @Override
    public int ObtenerSolamenteCorrelativo_Metodo2(int idTipoDocSerie) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            int num = dpDAO.ObtenerSolamenteCorrelativo_Metodo2(idTipoDocSerie);
            return num + 1;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return 0;
        }
    }

    public List ObtenerConceptosCuota(int desde, int hasta) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        return amBO.ObtenerConceptosCuota(desde, hasta);
    }

    public List ObtenerConceptosCuotaSociedadArequipa(int desde, int hasta) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        return amBO.ObtenerConceptosCuotaSociedadArequipa(desde, hasta);
    }

    public List ObtenerConceptosCuotaSociedadLima(int desde, int hasta) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        return amBO.ObtenerConceptosCuotaSociedadLima(desde, hasta);
    }

    public List ObtenerConceptosCuotaVitalicio(int desde, int hasta) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        return amBO.ObtenerConceptosCuotaVitalicio(desde, hasta);
    }

    public Object[] ObtenerPrimerConceptosCuota(int idConcepto, int desde, int hasta) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        Object[] primer = (Object[]) amBO.ObtenerPrimerConceptosCuota(idConcepto, desde, hasta);
        return primer;
    }

    public Object[] ObtenerUltimoConceptosCuota(int idConcepto, int desde, int hasta) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        Object[] ultimo = (Object[]) amBO.ObtenerUltimoConceptosCuota(idConcepto, desde, hasta);
        return ultimo;
    }

    public int ObtenerUltimoNroComprobante(int idTipoDoc, String nroSerie) {
        DocumentoPagoDAO dDAO = DocumentoPagoDAO.getInstance();
        return dDAO.ObtenerUltimoNroComprobante(idTipoDoc, nroSerie);
    }

    public int ObtenerUltimoNroValeAcademico() {
        DocumentoPagoDAO dDAO = DocumentoPagoDAO.getInstance();
        return dDAO.ObtenerUltimoNroValeAcademico();
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

    public double ObtenerMontoConceptoDetalladoVitalicio(int idAnioMes, int idConcepto) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        Object objMonto = amBO.ObtenerMontoConceptoDetalladoVitalicio(idAnioMes, idConcepto);
        if (objMonto == null) {
            return 0;
        } else {
            return (double) objMonto;
        }
    }

    public double ObtenerMontoSociedadConceptoDetallado(int idAnioMes, int idConcepto) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        Object objMonto = amBO.ObtenerMontoSociedadConceptoDetallado(idAnioMes, idConcepto);
        if (objMonto == null) {
            return 0;
        } else {
            return (double) objMonto;
        }
    }

    private void CancelarDeuda(DocumentoPagoDet dpd) {
        DeudasBO dBO = DeudasBO.getInstance();
        Deuda d = dBO.ObtenerDeudaClienteConcepto(dpd.getDocumentoPago().getCliente().getIdCliente(), dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle());
        if (d != null) {
            if (d.getEstado().equals("DP")) {
                d.setMontoCancelado(dpd.getPrecioVenta());
                if (d.getMonto() <= d.getMontoCancelado()) {
                    d.setEstado("DC");
                    dBO.ActualizarDeuda(d);
                    ClienteBO cBO = ClienteBO.getInstance();
                    cBO.ValidarHabilitacionColegiado(dpd.getDocumentoPago().getCliente());
                }
            }
        }
    }

    private void enviarConstanciaHabilitacionEmail(int idConstancia) {
        String url = UtilConstante.URI_ENVIO_CORREO_CONSTANCIA + idConstancia;
        String result = "";
        try {
            Response response = Request.get(url).addHeader("Authorization", UtilConstante.TOKEN_CORREO_CONSTANCIA).addHeader("Keyword", UtilConstante.KEYWORD_CORREO_CONSTANCIA).execute();
            result = response.returnContent().asString();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generacionEstadoCuenta() {
        String url = UtilConstante.URI_ENVIO_PRUEBA;
        String result = "";
        try {
            Response response = Request.get(url).addHeader("Authorization", UtilConstante.TOKEN_PRUEBA).addHeader("Keyword", UtilConstante.KEYWORD_PRUEBA).execute();
            result = response.returnContent().asString();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarConstanciaHabilitacion(DocumentoPagoDet dpd) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            SeguridadBO sBO = SeguridadBO.getInstance();
            Cliente contador = (Cliente) sBO.CargarObjeto("Cliente", dpd.getDocumentoPago().getCliente().getIdCliente());
            if (!Objects.isNull(contador)) {
                ConstanciaHabilitacion constancia = new ConstanciaHabilitacion();
                constancia.setBorrado("1");
                constancia.setOrigenGeneracion("CAJA");
                constancia.setCliente(contador);
                constancia.setFecha(new Date());
                constancia.setTipo("C");
                constancia.setDocumentoPagoDet(dpd);
                //--------------------------------------------------------------
                CuotasBO cuotaBO = CuotasBO.getInstance();
                Cuotas cuota = cuotaBO.ObtenerUltimaCuotaOrdinaria(contador.getIdCliente());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
//                calendar.add(Calendar.MONTH, 3);
                Calendar calendarVigencia = Calendar.getInstance();
                if (cuota != null) {
                    calendarVigencia.set(cuota.getAnioMes().getAnio(), cuota.getAnioMes().getMes() - 1, 1);
                    calendarVigencia.add(Calendar.MONTH, 3);
                } else {
                    calendarVigencia.setTime(contador.getCfechaAfiliacion());
                    calendarVigencia.add(Calendar.MONTH, 2);
                }
//                calendarVigencia.setTime(calendar.getTime());
                Integer ultimoDiaMes = calendarVigencia.getActualMaximum(Calendar.DAY_OF_MONTH);
                calendarVigencia.set(calendarVigencia.get(Calendar.YEAR), calendarVigencia.get(Calendar.MONDAY),
                        ultimoDiaMes);
                Date fechaVigencia = calendarVigencia.getTime();
                //--------------------------------------------------------------
                constancia.setFechaVigencia(fechaVigencia);
                ClienteBO cBO = ClienteBO.getInstance();
                List<ClienteCertificado> lCertificados = cBO.ObtenerClienteCertificado_SegunCliente(dpd.getDocumentoPago().getCliente().getIdCliente());
                if (lCertificados.size() > 0) {
                    constancia.setCertificacion("S");
                } else {
                    constancia.setCertificacion("N");
                }
                Integer nroCorrelativo = dpDAO.ObtenerCorrelativoConstanciaHabilitacion();
                constancia.setNro(nroCorrelativo + 1);
                dpDAO.GuardarObjeto(constancia);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void generarConstanciaHabilitacionSociedad(DocumentoPagoDet dpd) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            SeguridadBO sBO = SeguridadBO.getInstance();
            Cliente contador = (Cliente) sBO.CargarObjeto("Cliente", dpd.getDocumentoPago().getCliente().getIdCliente());
            if (!Objects.isNull(contador)) {
                ConstanciaHabilitacion constancia = new ConstanciaHabilitacion();
                constancia.setBorrado("1");
                constancia.setOrigenGeneracion("CAJA");
                constancia.setCliente(contador);
                constancia.setFecha(new Date());
                constancia.setTipo("S");
                constancia.setDocumentoPagoDet(dpd);
                //--------------------------------------------------------------
                CuotasBO cuotaBO = CuotasBO.getInstance();
                Cuotas cuota = cuotaBO.ObtenerUltimaCuotaOrdinaria(contador.getIdCliente());
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                Calendar calendarVigencia = Calendar.getInstance();
                if (cuota != null) {
                    calendarVigencia.set(cuota.getAnioMes().getAnio(), cuota.getAnioMes().getMes() - 1, 1);
                    calendarVigencia.add(Calendar.MONTH, 3);
                } else {
                    calendarVigencia.setTime(contador.getSfechaAfiliacion());
                    calendarVigencia.add(Calendar.MONTH, 2);
                }
                Integer ultimoDiaMes = calendarVigencia.getActualMaximum(Calendar.DAY_OF_MONTH);
                calendarVigencia.set(calendarVigencia.get(Calendar.YEAR), calendarVigencia.get(Calendar.MONDAY),
                        ultimoDiaMes);
                Date fechaVigencia = calendarVigencia.getTime();
//                //--------------------------------------------------------------
                constancia.setFechaVigencia(fechaVigencia);
                constancia.setCertificacion("N");
//                ClienteBO cBO = ClienteBO.getInstance();
//                List<ClienteCertificado> lCertificados = cBO.ObtenerClienteCertificado_SegunCliente(dpd.getDocumentoPago().getCliente().getIdCliente());
//                if (lCertificados.size() > 0) {
//                    constancia.setCertificacion("S");
//                } else {
//                    constancia.setCertificacion("N");
//                }
                Integer nroCorrelativo = dpDAO.ObtenerCorrelativoConstanciaHabilitacionSociedad();
                constancia.setNro(nroCorrelativo + 1);
                dpDAO.GuardarObjeto(constancia);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean GuardarDetalleDocumentoPago(DocumentoPagoDet dpd, List<ValeAcademico> lva) {
        try {
            double montoConcepto = 0;
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            if (dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 1 || dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 7220) {
                Object[] cuotaDesde = (Object[]) this.cuotasAPagar.get(0);
                Object[] cuotaHasta = (Object[]) this.cuotasAPagar.get(this.cuotasAPagar.size() - 1);
                /*------------OBTENER CUOTAS TOTALES DETALLADAS,CUOTA ORDINARIA,F. CLUB, F.MUTUAL---------------*/
                List<Object> lConceptos = null;
                if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("C") && dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 1) {
                    if (dpd.getDocumentoPago().getCliente().getEstado().equals("O") || dpd.getDocumentoPago().getCliente().getEstado().equals("V")) {
                        lConceptos = this.ObtenerConceptosCuotaVitalicio((int) cuotaDesde[0], (int) cuotaHasta[0]);
                    } else {
                        lConceptos = this.ObtenerConceptosCuota((int) cuotaDesde[0], (int) cuotaHasta[0]);
                    }
                }

                if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("E") && dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 1) {
                    if (this.clienteEmpresa.getEstado().equals("O") || this.clienteEmpresa.getEstado().equals("V")) {
                        lConceptos = this.ObtenerConceptosCuotaVitalicio((int) cuotaDesde[0], (int) cuotaHasta[0]);
                    } else {
                        lConceptos = this.ObtenerConceptosCuota((int) cuotaDesde[0], (int) cuotaHasta[0]);
                    }
                }
                if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("S") && dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 7220) {
                    if (dpd.getDocumentoPago().getCliente().getSciudad().equals("A")) {
                        lConceptos = this.ObtenerConceptosCuotaSociedadArequipa((int) cuotaDesde[0], (int) cuotaHasta[0]);
                    } else {
                        lConceptos = this.ObtenerConceptosCuotaSociedadLima((int) cuotaDesde[0], (int) cuotaHasta[0]);
                    }
                }
                SeguridadBO sBO = SeguridadBO.getInstance();
                for (Object pobj : lConceptos) {
                    Object[] obj = (Object[]) pobj;
                    int idConcepto = (int) obj[0];
                    ConceptoPagoDetalle concepto = (ConceptoPagoDetalle) sBO.CargarObjeto("ConceptoPagoDetalle", idConcepto);
                    TipoAfectacion ta = (TipoAfectacion) sBO.CargarObjeto("TipoAfectacion", concepto.getTipoAfectacion().getId());
                    concepto.setTipoAfectacion(ta);
                    TipoTributo tt = (TipoTributo) sBO.CargarObjeto("TipoTributo", concepto.getTipoAfectacion().getTipoTributo().getId());
                    concepto.getTipoAfectacion().setTipoTributo(tt);
                    DocumentoPagoDet dpdCuota = new DocumentoPagoDet();
                    dpdCuota.setDocumentoPago(dpd.getDocumentoPago());
                    dpdCuota.setConceptoPagoDetalle(concepto);
                    dpdCuota.setCantidad(dpd.getCantidad());
                    dpdCuota.setCodigoTipoTributo(concepto.getTipoAfectacion().getTipoTributo().getCodigo());
                    dpdCuota.setCodigoInternacionalTipoTributo(concepto.getTipoAfectacion().getTipoTributo().getCodigoInternacional());
                    dpdCuota.setNombreTipoTributo(concepto.getTipoAfectacion().getTipoTributo().getNombre());
                    dpdCuota.setCodigoTipoAfectacion(concepto.getTipoAfectacion().getCodigo());
                    dpdCuota.setValorVenta((double) obj[1]);
                    dpdCuota.setIgv(0.0);
                    dpdCuota.setIgvPorcentaje((concepto.getPorcentajeIgv() != null ? concepto.getPorcentajeIgv() : 0.0));
                    dpdCuota.setPrecioVenta((double) obj[1]);
                    /*OBTENGO EL PRIMER MES DEL CONCEPTO*/
                    Object[] ConceptoPrimero = this.ObtenerPrimerConceptosCuota(concepto.getIdConceptoPagoDetalle(), (int) cuotaDesde[0], (int) cuotaHasta[0]);
                    dpdCuota.setAnioDesde((int) ConceptoPrimero[1]);
                    dpdCuota.setMesDesde((int) ConceptoPrimero[2]);
                    /*OBTENGO EL ULTIMO MES DEL CONCEPTO*/
                    Object[] ConceptoUltimo = this.ObtenerUltimoConceptosCuota(concepto.getIdConceptoPagoDetalle(), (int) cuotaDesde[0], (int) cuotaHasta[0]);
                    dpdCuota.setAnioHasta((int) ConceptoUltimo[1]);
                    dpdCuota.setMesHasta((int) ConceptoUltimo[2]);
                    dpdCuota.setObservacion(String.valueOf((int) ConceptoPrimero[1]) + " " + this.ObtenerMes((int) ConceptoPrimero[2]) + " A " + String.valueOf((int) ConceptoUltimo[1]) + " " + this.ObtenerMes((int) ConceptoUltimo[2]));
                    if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("S") && concepto.getIdConceptoPagoDetalle() == 2) {
                        concepto.setIdConceptoPagoDetalle(7220);
                    }
                    dpDAO.GuardarDocumentoPagoDet(dpdCuota);
                    if (dpdCuota.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 2) {
                        String mensaje = " | Vale Academico Nro ";
                        boolean tieneVale = false;
                        for (ValeAcademico va  : lva) {
                            tieneVale = true;
                            int nro = this.ObtenerUltimoNroValeAcademico();
                            va.setNro(nro);
                            va.setDocumentoPagoDetalle(dpdCuota);
                            dpDAO.GuardarObjeto(va);
                            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                            mensaje = mensaje + va.getNro() + " Valido: " + f.format(va.getFechaFin()) + " - " + f.format(va.getFechaFin()) + " | ";
                        }
                        if (tieneVale) {
                            dpdCuota.setObservacion(dpdCuota.getObservacion() + mensaje);
                            dpDAO.ActualizarObjeto(dpdCuota);
                        }
                    }
                    /*INSERTANDO CUENTAS CORRIENTES*/
                    CuentaCorriente cuentaCorriente = new CuentaCorriente();
                    cuentaCorriente.setCliente(dpd.getDocumentoPago().getCliente());
                    cuentaCorriente.setDocumentoPagoDet(dpdCuota);
                    ClienteBO cliBO = ClienteBO.getInstance();
                    cliBO.InsertarCuentaCorrienteCliente(cuentaCorriente);
                    /*-----------------------------*/
                    CuotasBO cBO = CuotasBO.getInstance();
                    double montoDescuento = 0;
                    double montoDescuentoTotal = 0;
                    int contador = 0;
                    int contadorConceptoCuota = 0;
                    for (Object pobjCuota : this.cuotasAPagar) {
                        Object[] objCuota = (Object[]) pobjCuota;
                        if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("S") && concepto.getIdConceptoPagoDetalle() == 7220) {
                            montoConcepto = this.ObtenerMontoSociedadConceptoDetallado((int) objCuota[1], 2);
                        } else {
                            if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("C")) {
                                if (dpd.getDocumentoPago().getCliente().getEstado().equals("V") || dpd.getDocumentoPago().getCliente().getEstado().equals("O")) {
                                    montoConcepto = this.ObtenerMontoConceptoDetalladoVitalicio((int) objCuota[1], concepto.getIdConceptoPagoDetalle());
                                } else {
                                    montoConcepto = this.ObtenerMontoConceptoDetallado((int) objCuota[1], concepto.getIdConceptoPagoDetalle());
                                }
                            } else {
                                montoConcepto = this.ObtenerMontoConceptoDetallado((int) objCuota[1], concepto.getIdConceptoPagoDetalle());
                            }
                        }
                        if (montoConcepto > 0) {
                            Cuotas cuota = new Cuotas();
                            AnioMes anioMes = new AnioMes();
                            anioMes.setId((int) objCuota[1]);
                            cuota.setConceptoPagoDetalle(concepto);
                            cuota.setDocumentoPagoDet(dpdCuota);
                            cuota.setAnioMes(anioMes);
                            if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("E")) {
                                cuota.setCliente(this.clienteEmpresa);
                            } else {
                                cuota.setCliente(dpd.getDocumentoPago().getCliente());
                            }
                            if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("S")) {
                                if (dpd.getDocumentoPago().getCliente().getSciudad().equals("A")) {
                                    montoConcepto = montoConcepto * 2;
                                } else {
                                    montoConcepto = montoConcepto * 4;
                                }
                            }
                            if ((boolean) objCuota[6]) {
                                int id = (int) objCuota[1];
                                montoDescuento = cBO.ObtenerDescuentoConceptoUnico(id, concepto.getIdConceptoPagoDetalle());
                                /*--------------PONER CODIGO PARA EL DESCUENTO DE 10 CENTIMOS-------------*/
                                if (concepto.getIdConceptoPagoDetalle() == 2 && contador < 4) {
                                    montoDescuento = montoDescuento + 0.1;
                                    montoDescuento = Math.round(montoDescuento * Math.pow(10, 2)) / Math.pow(10, 2);
                                    contador = contador + 1;
                                } else {
                                    if (concepto.getIdConceptoPagoDetalle() == 2 && contador >= 4) {
                                        montoDescuento = montoDescuento + 0.2;
                                        montoDescuento = Math.round(montoDescuento * Math.pow(10, 2)) / Math.pow(10, 2);
                                        contador = contador + 1;
                                    }
                                }
                                if (dpd.getDocumentoPago().getCliente().getTipoCliente().equals("C") || dpd.getDocumentoPago().getCliente().getTipoCliente().equals("E")) {
                                    montoDescuentoTotal = montoDescuentoTotal + montoDescuento;
                                } else {
                                    if (dpd.getDocumentoPago().getCliente().getSciudad().equals("A")) {
                                        montoDescuento = montoDescuento * 2;
                                        montoDescuentoTotal = montoDescuentoTotal + montoDescuento;
                                    } else {
                                        montoDescuento = montoDescuento * 4;
                                        montoDescuentoTotal = montoDescuentoTotal + montoDescuento;
                                    }
                                }
                            }
                            cuota.setMonto(montoConcepto - montoDescuento);
                            cuota.setEstado("C");
                            cBO.GuardarCuota(cuota);
                            contadorConceptoCuota++;
                        }
                    }
                    dpdCuota.setCantidad(contadorConceptoCuota);
                    dpDAO.ActualizarObjeto(dpdCuota);
                    if (montoDescuentoTotal > 0) {
                        dpdCuota.setValorVenta(dpdCuota.getValorVenta() - montoDescuentoTotal);
                        dpdCuota.setPrecioVenta(dpdCuota.getPrecioVenta() - montoDescuentoTotal);
                        dpDAO.ActualizarDocumentoPagoDet(dpdCuota);
                    }
                    montoDescuentoTotal = 0;
                    montoDescuento = 0;
                }
            } else {
                if (dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 10) {
                    String cuotasFinanciadas = "CUOTA NRO: ";
                    for (FinanciamientoDocumentoPago fdp : this.financiamientoAPagar) {
                        cuotasFinanciadas = cuotasFinanciadas + fdp.getNroCuota() + " ";
                    }
                    dpd.setObservacion(cuotasFinanciadas);
                    dpDAO.GuardarDocumentoPagoDet(dpd);
                    /*INSERTANDO CUENTAS CORRIENTES*/
                    CuentaCorriente cuentaCorriente = new CuentaCorriente();
                    cuentaCorriente.setCliente(dpd.getDocumentoPago().getCliente());
                    cuentaCorriente.setDocumentoPagoDet(dpd);
                    ClienteBO cliBO = ClienteBO.getInstance();
                    cliBO.InsertarCuentaCorrienteCliente(cuentaCorriente);
                    /*-----------------------------*/
                    for (FinanciamientoDocumentoPago fdp : this.financiamientoAPagar) {
                        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
                        fdp.setEstado("FC");
                        fdp.setDocumentoPagoDet(dpd);
                        fBO.ActualizarFinanciamiento(fdp);
                    }
                } else {
                    if (dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 5850) {
                        String cuotasFinanciadas = "CUOTA NRO: ";
                        for (ReincorporacionDocumentoPago rdp : this.reincorporacionAPagar) {
                            cuotasFinanciadas = cuotasFinanciadas + rdp.getNroCuota() + " ";
                        }
                        dpd.setObservacion(cuotasFinanciadas);
                        dpDAO.GuardarDocumentoPagoDet(dpd);
                        /*INSERTANDO CUENTAS CORRIENTES*/
                        CuentaCorriente cuentaCorriente = new CuentaCorriente();
                        cuentaCorriente.setCliente(dpd.getDocumentoPago().getCliente());
                        cuentaCorriente.setDocumentoPagoDet(dpd);
                        ClienteBO cliBO = ClienteBO.getInstance();
                        cliBO.InsertarCuentaCorrienteCliente(cuentaCorriente);
                        /*-----------------------------*/
                        for (ReincorporacionDocumentoPago rdp : this.reincorporacionAPagar) {
                            FinanciamientoBO fBO = FinanciamientoBO.getInstance();
                            rdp.setEstado("RC");
                            rdp.setDocumentoPagoDet(dpd);
                            fBO.ActualizarReincorporacion(rdp);
                        }
                    } else {
                        if (dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 3939) { //constancia de habilitacion
                            dpDAO.GuardarDocumentoPagoDet(dpd);
                            this.generarConstanciaHabilitacion(dpd);
                        } else {
                            if (dpd.getConceptoPagoDetalle().getIdConceptoPagoDetalle() == 3940) { //constancia de habilitacion sociedades
                                dpDAO.GuardarDocumentoPagoDet(dpd);
                                this.generarConstanciaHabilitacionSociedad(dpd);
                            } else {
                                dpDAO.GuardarDocumentoPagoDet(dpd);
                            }
                        }
                    }
                }
            }
            this.CancelarDeuda(dpd);
            if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
                if (dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                    SeguridadBO sBO = SeguridadBO.getInstance();
                    Date fechaServer = sBO.ObtenerFechaHoraServidor();
                    dpd.getDocumentoPago().setFechaPago(fechaServer);
                    dpd.getDocumentoPago().setFechaSunat(fechaServer);
                }
            }
            dpDAO.CulminarComprobante(dpd.getDocumentoPago().getIdDocumentoPago());
            dpd.getDocumentoPago().setEstado("C");
            return true;
        } catch (Exception e) {
            System.out.println("Error en el Ingreso del Detalle de Documento Pago");
            return false;
        }
    }

    @Override
    public boolean guardarObjeto(Object obj) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        dpDAO.GuardarObjeto(obj);
        return true;
    }

    @Override
    public boolean ActualizarObjeto(Object obj) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        dpDAO.ActualizarObjeto(obj);
        return true;
    }

    @Override
    public void exportarDerechoHabientes() {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        List<Object> l = dpDAO.obtenerTodosDerechoHabiente();
        ExcelBO eBO = ExcelBO.getInstance();
        List<String> cabecera = new ArrayList<String>();
        String columnaCodigo = "Codigo";
        String columnaApellidos = "Apellidos";
        String columnaNombres = "Nombres";
        String columnaNombreDerechoHabiente = "NombreDerechoHabiente";
        String columnaApellidoDerechoHabiente = "ApellidoDerechoHabiente";
        String columnaParentesco = "Parentesco";
        String columnaDniDerechoHabiente = "DniDerechoHabiente";
        String columnaFechaNacimientoDerechoHabiente = "FechaNacimientoDerechoHabiente";
        String columnaEmailDerechoHabiente = "EmailDerechoHabiente";
        String columnaCelularDerechoHabiente = "CelularDerechoHabiente";
        String columnaSexoDerechoHabiente = "SexoDerechoHabiente";
        cabecera.add(columnaCodigo);
        cabecera.add(columnaApellidos);
        cabecera.add(columnaNombres);
        cabecera.add(columnaNombreDerechoHabiente);
        cabecera.add(columnaApellidoDerechoHabiente);
        cabecera.add(columnaParentesco);
        cabecera.add(columnaDniDerechoHabiente);
        cabecera.add(columnaFechaNacimientoDerechoHabiente);
        cabecera.add(columnaEmailDerechoHabiente);
        cabecera.add(columnaCelularDerechoHabiente);
        cabecera.add(columnaSexoDerechoHabiente);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> mapPadre = new HashMap<String, Object>();
        for (Object pobj : l) {
            Object[] obj = (Object[]) pobj;
            HashMap<String, excelItemDTO> mapHijo = new HashMap<String, excelItemDTO>();
            excelItemDTO itemCodigo = new excelItemDTO();
            itemCodigo.setNombreColumna(columnaCodigo);
            itemCodigo.setValorColumna((String) obj[0]);
            itemCodigo.setTipoDato("S");
            excelItemDTO itemApellidos = new excelItemDTO();
            itemApellidos.setNombreColumna(columnaApellidos);
            itemApellidos.setValorColumna(obj[1]);
            itemApellidos.setTipoDato("S");
            excelItemDTO itemcolumnaNombres = new excelItemDTO();
            itemcolumnaNombres.setNombreColumna(columnaNombres);
            itemcolumnaNombres.setValorColumna(obj[2]);
            itemcolumnaNombres.setTipoDato("S");
            excelItemDTO itemcolumnaNombreDerechoHabiente = new excelItemDTO();
            itemcolumnaNombreDerechoHabiente.setNombreColumna(columnaNombreDerechoHabiente);
            itemcolumnaNombreDerechoHabiente.setValorColumna(obj[3]);
            itemcolumnaNombreDerechoHabiente.setTipoDato("S");
            excelItemDTO itemcolumnaApellidoDerechoHabiente = new excelItemDTO();
            itemcolumnaApellidoDerechoHabiente.setNombreColumna(columnaApellidoDerechoHabiente);
            itemcolumnaApellidoDerechoHabiente.setValorColumna(obj[4]);
            itemcolumnaApellidoDerechoHabiente.setTipoDato("S");
            excelItemDTO itemcolumnaParentesco = new excelItemDTO();
            itemcolumnaParentesco.setNombreColumna(columnaParentesco);
            itemcolumnaParentesco.setValorColumna(obj[5]);
            itemcolumnaParentesco.setTipoDato("S");
            excelItemDTO itemcolumnaDniDerechoHabiente = new excelItemDTO();
            itemcolumnaDniDerechoHabiente.setNombreColumna(columnaDniDerechoHabiente);
            itemcolumnaDniDerechoHabiente.setValorColumna(obj[6]);
            itemcolumnaDniDerechoHabiente.setTipoDato("S");
            excelItemDTO itemcolumnaFechaNacimientoDerechoHabiente = new excelItemDTO();
            itemcolumnaFechaNacimientoDerechoHabiente.setNombreColumna(columnaFechaNacimientoDerechoHabiente);
            itemcolumnaFechaNacimientoDerechoHabiente.setValorColumna(obj[7] != null ? f.format((Date) obj[7]) : "");
            itemcolumnaFechaNacimientoDerechoHabiente.setTipoDato("S");
            excelItemDTO itemcolumnaEmailDerechoHabiente = new excelItemDTO();
            itemcolumnaEmailDerechoHabiente.setNombreColumna(columnaEmailDerechoHabiente);
            itemcolumnaEmailDerechoHabiente.setValorColumna(obj[8]);
            itemcolumnaEmailDerechoHabiente.setTipoDato("S");
            excelItemDTO itemcolumnaCelularDerechoHabiente = new excelItemDTO();
            itemcolumnaCelularDerechoHabiente.setNombreColumna(columnaCelularDerechoHabiente);
            itemcolumnaCelularDerechoHabiente.setValorColumna(obj[9]);
            itemcolumnaCelularDerechoHabiente.setTipoDato("S");
            excelItemDTO itemcolumnaSexoDerechoHabiente = new excelItemDTO();
            itemcolumnaSexoDerechoHabiente.setNombreColumna(columnaSexoDerechoHabiente);
            itemcolumnaSexoDerechoHabiente.setValorColumna(obj[10]);
            itemcolumnaSexoDerechoHabiente.setTipoDato("S");
            mapHijo.put(columnaCodigo, itemCodigo);
            mapHijo.put(columnaApellidos, itemApellidos);
            mapHijo.put(columnaNombres, itemcolumnaNombres);
            mapHijo.put(columnaNombreDerechoHabiente, itemcolumnaNombreDerechoHabiente);
            mapHijo.put(columnaApellidoDerechoHabiente, itemcolumnaApellidoDerechoHabiente);
            mapHijo.put(columnaParentesco, itemcolumnaParentesco);
            mapHijo.put(columnaDniDerechoHabiente, itemcolumnaDniDerechoHabiente);
            mapHijo.put(columnaFechaNacimientoDerechoHabiente, itemcolumnaFechaNacimientoDerechoHabiente);
            mapHijo.put(columnaEmailDerechoHabiente, itemcolumnaEmailDerechoHabiente);
            mapHijo.put(columnaCelularDerechoHabiente, itemcolumnaCelularDerechoHabiente);
            mapHijo.put(columnaSexoDerechoHabiente, itemcolumnaSexoDerechoHabiente);
            mapPadre.put(String.valueOf(mapPadre.size()), mapHijo);
        }
        eBO.generarExcel("reporteDerechoHabiente", "DERECHO HABIENTE", cabecera, mapPadre);
    }

    @Override
    public void exportarValeAcademico(List<ValeAcademico> lValeAcademico) {
        ExcelBO eBO = ExcelBO.getInstance();
        List<String> cabecera = new ArrayList<String>();
        String columnaVale = "Vale";
        String columnaFechaDesde = "Fecha Desde";
        String columnaFechaHasta = "Fecha Hasta";
        String columnaCliente = "Cliente";
        String columnaMonto = "Monto";
        cabecera.add(columnaVale);
        cabecera.add(columnaFechaDesde);
        cabecera.add(columnaFechaHasta);
        cabecera.add(columnaCliente);
        cabecera.add(columnaMonto);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> mapPadre = new HashMap<String, Object>();
        for (ValeAcademico obj : lValeAcademico) {
            HashMap<String, excelItemDTO> mapHijo = new HashMap<String, excelItemDTO>();
            excelItemDTO itemVale = new excelItemDTO();
            itemVale.setNombreColumna(columnaVale);
            itemVale.setValorColumna(String.format("%07d", obj.getNro()));
            itemVale.setTipoDato("S");
            excelItemDTO itemFechaDesde = new excelItemDTO();
            itemFechaDesde.setNombreColumna(columnaFechaDesde);
            itemFechaDesde.setValorColumna(f.format(obj.getFechaInicio()));
            itemFechaDesde.setTipoDato("S");
            excelItemDTO itemFechaHasta = new excelItemDTO();
            itemFechaHasta.setNombreColumna(columnaFechaHasta);
            itemFechaHasta.setValorColumna(f.format(obj.getFechaFin()));
            itemFechaHasta.setTipoDato("S");
            excelItemDTO itemCliente = new excelItemDTO();
            itemCliente.setTipoDato("S");
            if (obj.getDocumentoPagoDetalle() != null) {
                Cliente c = null;
                if (obj.getDocumentoPagoDetalle().getDocumentoPago().getClienteByIdContadorEmpresa() != null) {
                    SeguridadBO sBO = SeguridadBO.getINSTANCE();
                    c = (Cliente) sBO.CargarObjeto("Cliente", obj.getDocumentoPagoDetalle().getDocumentoPago().getClienteByIdContadorEmpresa().getIdCliente());
                } else {
                    c = obj.getDocumentoPagoDetalle().getDocumentoPago().getCliente();
                }
                itemCliente.setNombreColumna(columnaCliente);
                itemCliente.setValorColumna(c.getPapePat() + " " + c.getPapeMat() + " " + c.getPnombre());
            }
            excelItemDTO itemMonto = new excelItemDTO();
            itemMonto.setNombreColumna(columnaMonto);
            itemMonto.setValorColumna(obj.getMonto());
            itemMonto.setTipoDato("D");
            mapHijo.put(columnaVale, itemVale);
            mapHijo.put(columnaFechaDesde, itemFechaDesde);
            mapHijo.put(columnaFechaHasta, itemFechaHasta);
            mapHijo.put(columnaCliente, itemCliente);
            mapHijo.put(columnaMonto, itemMonto);
            mapPadre.put(String.valueOf(mapPadre.size()), mapHijo);
        }
        eBO.generarExcel("reporteValeConsumo", "VALES DE CONSUMO", cabecera, mapPadre);
    }

    @Override
    public void exportarValeAcademicoDetalle(List<ValeAcademico> lValeAcademico) {
        ExcelBO eBO = ExcelBO.getInstance();
        List<String> cabecera = new ArrayList<String>();
        String columnaVale = "Vale";
        String columnaFechaDesde = "Fecha Desde";
        String columnaFechaHasta = "Fecha Hasta";
        String columnaCliente = "Cliente";
        String columnaMonto = "Monto";
        String columnaFechaConsumo = "FechaConsumo";
        String columnaConsumo = "Consumo";
        String columnaMontoConsumo = "MontoConsumo";
        cabecera.add(columnaVale);
        cabecera.add(columnaFechaDesde);
        cabecera.add(columnaFechaHasta);
        cabecera.add(columnaCliente);
        cabecera.add(columnaMonto);
        cabecera.add(columnaFechaConsumo);
        cabecera.add(columnaConsumo);
        cabecera.add(columnaMontoConsumo);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> mapPadre = new HashMap<String, Object>();
        SeguridadBO sBO = SeguridadBO.getINSTANCE();
        for (ValeAcademico obj : lValeAcademico) {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            List<ValeAcademicoConsumo> l = dpDAO.obtenerValeAcademicoConsumoDetalle(obj.getId());
            if (l.size() > 0) {
                for (ValeAcademicoConsumo vac : l) {
                    HashMap<String, excelItemDTO> mapHijo = new HashMap<String, excelItemDTO>();
                    excelItemDTO itemVale = new excelItemDTO();
                    itemVale.setNombreColumna(columnaVale);
                    itemVale.setValorColumna(String.format("%07d", obj.getNro()));
                    itemVale.setTipoDato("S");
                    excelItemDTO itemFechaDesde = new excelItemDTO();
                    itemFechaDesde.setNombreColumna(columnaFechaDesde);
                    itemFechaDesde.setValorColumna(f.format(obj.getFechaInicio()));
                    itemFechaDesde.setTipoDato("S");
                    excelItemDTO itemFechaHasta = new excelItemDTO();
                    itemFechaHasta.setNombreColumna(columnaFechaHasta);
                    itemFechaHasta.setValorColumna(f.format(obj.getFechaFin()));
                    itemFechaHasta.setTipoDato("S");
                    excelItemDTO itemCliente = new excelItemDTO();
                    itemCliente.setTipoDato("S");
                    if (obj.getDocumentoPagoDetalle() != null) {
                        Cliente c = null;
                        if (obj.getDocumentoPagoDetalle().getDocumentoPago().getClienteByIdContadorEmpresa() != null) {
                            c = (Cliente) sBO.CargarObjeto("Cliente", obj.getDocumentoPagoDetalle().getDocumentoPago().getClienteByIdContadorEmpresa().getIdCliente());
                        } else {
                            c = obj.getDocumentoPagoDetalle().getDocumentoPago().getCliente();
                        }
                        itemCliente.setNombreColumna(columnaCliente);
                        itemCliente.setValorColumna(c.getPapePat() + " " + c.getPapeMat() + " " + c.getPnombre());
                    }
                    excelItemDTO itemMonto = new excelItemDTO();
                    itemMonto.setNombreColumna(columnaMonto);
                    itemMonto.setValorColumna(obj.getMonto());
                    itemMonto.setTipoDato("D");
                    excelItemDTO itemFechaConsumo = new excelItemDTO();
                    itemFechaConsumo.setNombreColumna(columnaFechaConsumo);
                    itemFechaConsumo.setValorColumna(f.format(vac.getFecha()));
                    itemFechaConsumo.setTipoDato("S");
                    excelItemDTO itemConsumo = new excelItemDTO();
                    itemConsumo.setNombreColumna(columnaConsumo);
                    itemConsumo.setValorColumna(vac.getInversion().getEvento().getNombre());
                    itemConsumo.setTipoDato("S");
                    excelItemDTO itemMontoConsumo = new excelItemDTO();
                    itemMontoConsumo.setNombreColumna(columnaMontoConsumo);
                    itemMontoConsumo.setValorColumna(vac.getMonto());
                    itemMontoConsumo.setTipoDato("D");
                    mapHijo.put(columnaVale, itemVale);
                    mapHijo.put(columnaFechaDesde, itemFechaDesde);
                    mapHijo.put(columnaFechaHasta, itemFechaHasta);
                    mapHijo.put(columnaCliente, itemCliente);
                    mapHijo.put(columnaMonto, itemMonto);
                    mapHijo.put(columnaFechaConsumo, itemFechaConsumo);
                    mapHijo.put(columnaConsumo, itemConsumo);
                    mapHijo.put(columnaMontoConsumo, itemMontoConsumo);
                    mapPadre.put(String.valueOf(mapPadre.size()), mapHijo);
                }
            } else {
                HashMap<String, excelItemDTO> mapHijo = new HashMap<String, excelItemDTO>();
                excelItemDTO itemVale = new excelItemDTO();
                itemVale.setNombreColumna(columnaVale);
                itemVale.setValorColumna(String.format("%07d", obj.getNro()));
                itemVale.setTipoDato("S");
                excelItemDTO itemFechaDesde = new excelItemDTO();
                itemFechaDesde.setNombreColumna(columnaFechaDesde);
                itemFechaDesde.setValorColumna(f.format(obj.getFechaInicio()));
                itemFechaDesde.setTipoDato("S");
                excelItemDTO itemFechaHasta = new excelItemDTO();
                itemFechaHasta.setNombreColumna(columnaFechaHasta);
                itemFechaHasta.setValorColumna(f.format(obj.getFechaFin()));
                itemFechaHasta.setTipoDato("S");
                excelItemDTO itemCliente = new excelItemDTO();
                itemCliente.setTipoDato("S");
                if (obj.getDocumentoPagoDetalle() != null) {
                    Cliente c = null;
                    if (obj.getDocumentoPagoDetalle().getDocumentoPago().getClienteByIdContadorEmpresa() != null) {
                        c = (Cliente) sBO.CargarObjeto("Cliente", obj.getDocumentoPagoDetalle().getDocumentoPago().getClienteByIdContadorEmpresa().getIdCliente());
                    } else {
                        c = obj.getDocumentoPagoDetalle().getDocumentoPago().getCliente();
                    }
                    itemCliente.setNombreColumna(columnaCliente);
                    itemCliente.setValorColumna(c.getPapePat() + " " + c.getPapeMat() + " " + c.getPnombre());
                }
                excelItemDTO itemMonto = new excelItemDTO();
                itemMonto.setNombreColumna(columnaMonto);
                itemMonto.setValorColumna(obj.getMonto());
                itemMonto.setTipoDato("D");
                mapHijo.put(columnaVale, itemVale);
                mapHijo.put(columnaFechaDesde, itemFechaDesde);
                mapHijo.put(columnaFechaHasta, itemFechaHasta);
                mapHijo.put(columnaCliente, itemCliente);
                mapHijo.put(columnaMonto, itemMonto);
                mapPadre.put(String.valueOf(mapPadre.size()), mapHijo);
            }

        }
        eBO.generarExcel("reporteValeConsumo", "VALES DE CONSUMO", cabecera, mapPadre);
    }

    @Override
    public List buscarValeAcademico(String filtro) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.buscarValeAcademico(filtro);
    }

    @Override
    public List buscarValeAcademicoDetalle(String filtro) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.buscarValeAcademicoDetalle(filtro);
    }

    @Override
    public List BuscarComprobantePago(String anio, String mes, String dia) {
        int pmes = 0;
        List lComprobantes = null;
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        if (mes.equals("--------------")) {
            lComprobantes = dpDAO.BuscarComprobantePagoPorAnio(Integer.valueOf(anio));
        }
        if (!mes.equals("--------------") && dia.isEmpty()) {
            pmes = this.ObtenerMes(mes);
            lComprobantes = dpDAO.BuscarComprobantePagoPorAnioMes(Integer.valueOf(anio), pmes);
        }
        if (!mes.equals("--------------") && !dia.isEmpty()) {
            pmes = this.ObtenerMes(mes);
            lComprobantes = dpDAO.BuscarComprobantePagoPorAnioMesDia(Integer.valueOf(anio), pmes, Integer.valueOf(dia));
        }
        int idDocumentoPago;
        String tipoCliente;
        String nroSerie;
        int nroComprobantePago;
        String nombreDocPago;
        //double monto;
        List<Object> listaFinal = new ArrayList();
        Object[] objFinal;
        for (Object pobj : lComprobantes) {
            objFinal = new Object[16];
            Object[] obj = (Object[]) pobj;
            objFinal[0] = idDocumentoPago = (int) obj[0];
            objFinal[1] = nombreDocPago = (String) obj[1];
            objFinal[2] = nroSerie = (String) obj[2];
            objFinal[3] = nroComprobantePago = (int) obj[3];
            objFinal[4] = obj[4]; //FECHA COMPROBANTE
            objFinal[5] = tipoCliente = (String) obj[5];
            objFinal[10] = obj[15]; //ESTADO COMPROBANTE
            objFinal[11] = obj[16]; //ID CLIENTE
            objFinal[13] = obj[19]; //ID TIPO DOCUMENTO
            objFinal[14] = obj[20]; //TIENE IGV
            objFinal[6] = obj[22]; //NOMBRE DEL CLIENTE
            objFinal[15] = obj[23]; //MONEDA
            if (tipoCliente.equals("C")) {
                //objFinal[6] = obj[6]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[7]; //CODIGO
                objFinal[9] = obj[8]; //DIRECCION
                objFinal[12] = obj[17]; //FECHA AFILIACION
            }
            if (tipoCliente.equals("S")) {
                //objFinal[6] = obj[9]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[10]; //CODIGO
                objFinal[9] = obj[11]; //DIRECCION
                objFinal[12] = obj[18]; //FECHA AFILIACION
            }
            if (tipoCliente.equals("E")) {
                //objFinal[6] = obj[13]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[12]; //RUC
                objFinal[9] = obj[14]; //DIRECCION
            }
            if (tipoCliente.equals("P")) {
                //objFinal[6] = obj[6]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[21]; //NRO DOCUMENTO
                objFinal[9] = obj[8]; //DIRECCION
            }
            /*if (tipoPagador.equals("C")) {
             monto = dpDAO.obtenerContadorMontoTotalComprobante(idDocumentoPago);
             Object[] objResult = (Object[]) result;
             objFinal[6] = objResult[0];
             objFinal[7] = objResult[1];
             }
             if (tipoPagador.equals("S")) {
             result = dpDAO.obtenerSociedadMontoTotalComprobante(idDocumentoPago);
             Object[] objResult = (Object[]) result;
             objFinal[6] = objResult[0];
             objFinal[7] = objResult[1];
             }*/
            listaFinal.add(objFinal);
        }
        return listaFinal;
    }

    @Override
    public List BuscarNroComprobantePago(int nroComprobante) {
        int pmes = 0;
        List lComprobantes = null;
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        lComprobantes = dpDAO.BuscarNroComprobantePago(nroComprobante);
        int idDocumentoPago;
        String tipoCliente;
        String nroSerie;
        int nroComprobantePago;
        String nombreDocPago;
        //double monto;
        List<Object> listaFinal = new ArrayList();
        Object[] objFinal;
        for (Object pobj : lComprobantes) {
            objFinal = new Object[16];
            Object[] obj = (Object[]) pobj;
            objFinal[0] = idDocumentoPago = (int) obj[0];
            objFinal[1] = nombreDocPago = (String) obj[1];
            objFinal[2] = nroSerie = (String) obj[2];
            objFinal[3] = nroComprobantePago = (int) obj[3];
            objFinal[4] = obj[4]; //FECHA COMPROBANTE
            objFinal[5] = tipoCliente = (String) obj[5];
            objFinal[10] = obj[15]; //ESTADO COMPROBANTE
            objFinal[11] = obj[16]; //ID CLIENTE
            objFinal[13] = obj[19]; //ID TIPO DOCUMENTO
            objFinal[14] = obj[20]; //TIENE IGV
            objFinal[6] = obj[22]; //NOMBRE DEL CLIENTE
            objFinal[15] = obj[23]; //MONEDA
            if (tipoCliente.equals("C")) {
                //objFinal[6] = obj[6]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[7]; //CODIGO
                objFinal[9] = obj[8]; //DIRECCION
                objFinal[12] = obj[17]; //FECHA AFILIACION
            }
            if (tipoCliente.equals("S")) {
                //objFinal[6] = obj[9]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[10]; //CODIGO
                objFinal[9] = obj[11]; //DIRECCION
                objFinal[12] = obj[18]; //FECHA AFILIACION
            }
            if (tipoCliente.equals("E")) {
                //objFinal[6] = obj[13]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[12]; //RUC
                objFinal[9] = obj[14]; //DIRECCION
            }
            if (tipoCliente.equals("P")) {
                //objFinal[6] = obj[6]; //NOMBRE PAGADOR
                objFinal[7] = dpDAO.obtenerMontoTotalComprobante(idDocumentoPago);
                objFinal[8] = obj[21]; //NRO DOCUMENTO
                objFinal[9] = obj[8]; //DIRECCION
            }
            /*if (tipoPagador.equals("C")) {
             monto = dpDAO.obtenerContadorMontoTotalComprobante(idDocumentoPago);
             Object[] objResult = (Object[]) result;
             objFinal[6] = objResult[0];
             objFinal[7] = objResult[1];
             }
             if (tipoPagador.equals("S")) {
             result = dpDAO.obtenerSociedadMontoTotalComprobante(idDocumentoPago);
             Object[] objResult = (Object[]) result;
             objFinal[6] = objResult[0];
             objFinal[7] = objResult[1];
             }*/
            listaFinal.add(objFinal);
        }
        return listaFinal;
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

    private int ObtenerMes(String mes) {
        int pmes = 0;
        if (mes.equals("ENERO")) {
            pmes = 1;
        }
        if (mes.equals("FEBRERO")) {
            pmes = 2;
        }
        if (mes.equals("MARZO")) {
            pmes = 3;
        }
        if (mes.equals("ABRIL")) {
            pmes = 4;
        }
        if (mes.equals("MAYO")) {
            pmes = 5;
        }
        if (mes.equals("JUNIO")) {
            pmes = 6;
        }
        if (mes.equals("JULIO")) {
            pmes = 7;
        }
        if (mes.equals("AGOSTO")) {
            pmes = 8;
        }
        if (mes.equals("SEPTIEMBRE")) {
            pmes = 9;
        }
        if (mes.equals("OCTUBRE")) {
            pmes = 10;
        }
        if (mes.equals("NOVIEMBRE")) {
            pmes = 11;
        }
        if (mes.equals("DICIEMBRE")) {
            pmes = 12;
        }
        return pmes;
    }

    @Override
    public Cobrador ObtenerCobradorComprobantePago(int idDocumentoPago) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            return dpDAO.ObtenerCobradorComprobantePago(idDocumentoPago);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en el Ingreso del Detalle de Documento Pago");
            return null;
        }
    }

    @Override
    public String ObtenerPeriodoCancelado(int idDocumentoPago) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerPeriodoCancelado(idDocumentoPago);
    }

    @Override
    public double ObtenerIgv(int idDocumentoPago) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerIgv(idDocumentoPago);
    }

    @Override
    public int obtenerIDUsuario_SegunIDCobrador(int idCobrador) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            return dpDAO.obtenerIDUsuario_SegunIDCobrador(idCobrador);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en el Ingreso del Detalle de Documento Pago");
            return 0;
        }
    }

    @Override
    public List ObtenerDetalleComprobante(int idDocumentoPago) {
        try {
            //this.documentoPago.setDocumentoPagoDets(dpd);
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            return dpDAO.ObtenerDetalleComprobante(idDocumentoPago);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en Obtener Detalle Comprobante");
            return null;
        }
    }

    @Override
    public List obtenerValeAcademicoConsumoDetalle(int idValeAcademico) {
        try {
            //this.documentoPago.setDocumentoPagoDets(dpd);
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            return dpDAO.obtenerValeAcademicoConsumoDetalle(idValeAcademico);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en Obtener Detalle Comprobante");
            return null;
        }
    }

    @Override
    public List obtenerValeAcademicoConsumoDetalleParticipante(int idValeAcademicoConsumo) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            return dpDAO.obtenerValeAcademicoConsumoDetalleParticipante(idValeAcademicoConsumo);
        } catch (Exception e) {
            System.out.println("Error en Obtener Detalle Comprobante");
            return null;
        }
    }

    @Override
    public List ObtenerResumenDiarioDetalle(int id) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            return dpDAO.ObtenerResumenDiarioDetalle(id);
        } catch (Exception e) {
            System.out.println("Error en Obtener Detalle Comprobante");
            return null;
        }
    }

    @Override
    public DocumentoPagoDet ObtenerUltimaCuotaOrdinariaContador(Cliente cliente, String tipoPagador) {
        try {
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            DocumentoPagoDet ultimoPago = dpDAO.ObtenerUltimaCuotaOrdinariaContador(cliente.getIdCliente());
            if (ultimoPago == null) {
                ultimoPago = new DocumentoPagoDet();
                SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
                SimpleDateFormat formatMes = new SimpleDateFormat("MM");
                int anio = 0;
                int mes = 0;
                if (tipoPagador.equals("C")) {
                    anio = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
                    mes = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
                }
                if (tipoPagador.equals("S")) {
                    anio = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
                    mes = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
                }
                ultimoPago.setAnioHasta(anio);
                ultimoPago.setMesHasta(mes);
            }
            return ultimoPago;
        } catch (Exception e) {
            System.out.println("Error en Obtener Ultima Cuota Ordinaria Cliente");
            return null;
        }
    }

    @Override
    public void HabilitarCliente(int idCliente) {
        if (this.listaClientes != null) {
            for (Cliente c : this.listaClientes) {
                if (c.getIdCliente() == idCliente) {
                    c.setEstado("H");
                }
            }
        }
    }

    @Override
    public void InhabilitarCliente(int idCliente) {
        if (this.listaClientes != null) {
            for (Cliente c : this.listaClientes) {
                if (c.getIdCliente() == idCliente) {
                    c.setEstado("I");
                }
            }
        }
    }

    @Override
    public double ObtenerMontoCuotasAPagarCliente(int cantCuotas, DocumentoPago documentoPago) {
        CuotasBO cBO = CuotasBO.getInstance();
        List<Object> listaCuotas;
        /*CASO ESPECIAL SI ES MIEMBRO VITALICIO*/
        if (documentoPago.getCliente().getTipoCliente().equals("C")) {
            if (documentoPago.getCliente().getEstado().equals("V") || documentoPago.getCliente().getEstado().equals("O")) {
                listaCuotas = cBO.ObtenerTodasCuotasClienteVitalicio(documentoPago.getCliente(), cantCuotas);
            } else {
                listaCuotas = cBO.ObtenerTodasCuotasCliente(documentoPago.getCliente(), cantCuotas);
            }
        } else {
            listaCuotas = cBO.ObtenerTodasCuotasSociedades(documentoPago.getCliente(), cantCuotas);
        }
        this.cuotasAPagar = new ArrayList();
        Object[] objFinal;
        double monto = 0;
        double montoTotalMesesAPagar = 0;
        double cuotaMes = 0;
        int contador = 0;
        for (Object pobj : listaCuotas) {
            Object[] obj = (Object[]) pobj;
            if (contador < cantCuotas) {
                cuotaMes = (double) obj[5];
                if (obj[4] == null) {
                    objFinal = new Object[7];
                    obj[4] = obj[5];
                    objFinal[0] = obj[0];
                    objFinal[1] = obj[1];
                    objFinal[2] = obj[2];
                    objFinal[3] = obj[3];
                    objFinal[4] = obj[4];
                    objFinal[5] = obj[5];
                    objFinal[6] = false;
                    montoTotalMesesAPagar = montoTotalMesesAPagar + cuotaMes;
                    this.cuotasAPagar.add(objFinal);
                    contador = contador + 1;
                }
            } else {
                break;
            }
        }
        return montoTotalMesesAPagar;
    }

    @Override
    public double ObtenerMontoCuotasAPagarClienteEmpresa(Cliente cliente, int cantCuotas) {
        CuotasBO cBO = CuotasBO.getInstance();
        List<Object> listaCuotas;
        if (cliente.getTipoCliente().equals("C")) {
            if (cliente.getEstado().equals("V") || cliente.getEstado().equals("O")) {
                listaCuotas = cBO.ObtenerTodasCuotasClienteVitalicio(cliente, cantCuotas);
            } else {
                listaCuotas = cBO.ObtenerTodasCuotasCliente(cliente, cantCuotas);
            }
        } else {
            if (cliente.getSciudad().equals("A")) {
                listaCuotas = cBO.ObtenerTodasCuotasSociedades(cliente, cantCuotas);
            } else {
                listaCuotas = cBO.ObtenerTodasCuotasCliente(cliente, cantCuotas);
            }
        }
        this.cuotasAPagar = new ArrayList();
        Object[] objFinal;
        double monto = 0;
        double montoTotalMesesAPagar = 0;
        double cuotaMes = 0;
        int contador = 0;
        for (Object pobj : listaCuotas) {
            Object[] obj = (Object[]) pobj;
            if (contador < cantCuotas) {
                cuotaMes = (double) obj[5];
                if (obj[4] == null) {
                    objFinal = new Object[7];
                    obj[4] = obj[5];
                    objFinal[0] = obj[0];
                    objFinal[1] = obj[1];
                    objFinal[2] = obj[2];
                    objFinal[3] = obj[3];
                    objFinal[4] = obj[4];
                    objFinal[5] = obj[5];
                    objFinal[6] = false;
                    montoTotalMesesAPagar = montoTotalMesesAPagar + cuotaMes;
                    this.cuotasAPagar.add(objFinal);
                    contador = contador + 1;
                }
            } else {
                break;
            }
        }
        return montoTotalMesesAPagar;
    }

    @Override
    public double ObtenerMontoFinanciamientoAPagarCliente(int cantCuotas, DocumentoPago documentoPago) {
        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
        List<FinanciamientoDocumentoPago> listaFinanciamiento = fBO.ObtenerTodosFinanciamientoActivosCliente(documentoPago.getCliente().getIdCliente());
        this.financiamientoAPagar = new ArrayList();
        double montoTotalAPagar = 0;
        /*double monto = 0;
         double montoCuota = 0;*/
        int contador = 0;
        for (FinanciamientoDocumentoPago fdp : listaFinanciamiento) {
            montoTotalAPagar = montoTotalAPagar + fdp.getMonto();
            //monto = monto + fdp.getMonto();
            this.financiamientoAPagar.add(fdp);
            contador = contador + 1;
            if (contador == cantCuotas) {
                break;
            }
        }
        return montoTotalAPagar;
    }

    @Override
    public double ObtenerMontoFinanciamientoAPagarClienteEmpresa(Cliente cliente, int cantCuotas) {
        FinanciamientoBO fBO = FinanciamientoBO.getInstance();
        List<FinanciamientoDocumentoPago> listaFinanciamiento = fBO.ObtenerTodosFinanciamientoActivosCliente(cliente.getIdCliente());
        this.financiamientoAPagar = new ArrayList();
        double montoTotalAPagar = 0;
        /*double monto = 0;
         double montoCuota = 0;*/
        int contador = 0;
        for (FinanciamientoDocumentoPago fdp : listaFinanciamiento) {
            montoTotalAPagar = montoTotalAPagar + fdp.getMonto();
            //monto = monto + fdp.getMonto();
            this.financiamientoAPagar.add(fdp);
            contador = contador + 1;
            if (contador == cantCuotas) {
                break;
            }
        }
        return montoTotalAPagar;
    }

    @Override
    public double ObtenerMontoReincorporacionAPagarCliente(int cantCuotas, DocumentoPago documentoPago) {
        ReincorporacionBO rBO = ReincorporacionBO.getInstance();
        List<ReincorporacionDocumentoPago> listaReincorporacion = rBO.ObtenerTodosReincorporacionesActivosCliente(documentoPago.getCliente().getIdCliente());
        this.reincorporacionAPagar = new ArrayList();
        double montoTotalAPagar = 0;
        /*double monto = 0;
         double montoCuota = 0;*/
        int contador = 0;
        for (ReincorporacionDocumentoPago rdp : listaReincorporacion) {
            montoTotalAPagar = montoTotalAPagar + rdp.getMontoFondo() + rdp.getMontoOtros();
            rdp.setMonto(rdp.getMontoFondo() + rdp.getMontoOtros());
            this.reincorporacionAPagar.add(rdp);
            contador = contador + 1;
            if (contador == cantCuotas) {
                break;
            }
        }
        return montoTotalAPagar;
    }

    @Override
    public double ObtenerMontoReincorporacionAPagarClienteEmpresa(Cliente cliente, int cantCuotas) {
        ReincorporacionBO rBO = ReincorporacionBO.getInstance();
        List<ReincorporacionDocumentoPago> listaReincorporacion = rBO.ObtenerTodosReincorporacionesActivosCliente(cliente.getIdCliente());
        this.reincorporacionAPagar = new ArrayList();
        double montoTotalAPagar = 0;
        /*double monto = 0;
         double montoCuota = 0;*/
        int contador = 0;
        for (ReincorporacionDocumentoPago rdp : listaReincorporacion) {
            montoTotalAPagar = montoTotalAPagar + rdp.getMontoFondo() + rdp.getMontoOtros();
            rdp.setMonto(rdp.getMontoFondo() + rdp.getMontoOtros());
            this.reincorporacionAPagar.add(rdp);
            contador = contador + 1;
            if (contador == cantCuotas) {
                break;
            }
        }
        return montoTotalAPagar;
    }

    public boolean VerificarSiCuotaTieneFinanciamiento(Object[] objCuota, List listaCuotasFinanciadas) {
        double montoFinanciado = 0;
        int idAnioMes = (int) objCuota[1];
        /*VERIFICAR SI LA CUOTA ESTA CANCELADA ENTONCES NO TIENE FINANCIAMIENTO*/
        if (objCuota[4] != null) {
            if ((double) objCuota[4] == (double) objCuota[5]) {
                return false;
            }
        }
        /*---------------------------------------------------------------------*/
        List<FinanciamientoDocumentoPago> lista = listaCuotasFinanciadas;
        if (lista == null) {
            return false;
        }
        /*for (FinanciamientoDocumentoPago fdp : lista) {
         if (fdp.getAnioMes().getId() == idAnioMes) {
         montoFinanciado = montoFinanciado + fdp.getMonto();
         }
         }*/
        if (objCuota[4] == null) {
            double montoCuota = (double) objCuota[5];
            if (montoCuota == montoFinanciado) {
                return true;
            }
        } else {
            double montoCuota = (double) objCuota[5];
            double monto = (double) objCuota[4];
            if (montoCuota == montoFinanciado + monto) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void LimpiarCuotasAPagar() {
        if (this.cuotasAPagar != null) {
            this.cuotasAPagar.clear();
        }
    }

    @Override
    public void LimpiarFinanciamientoAPagar() {
        if (this.financiamientoAPagar != null) {
            this.financiamientoAPagar.clear();
        }
    }

    @Override
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc, String nroSerie) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerTipoDocSerie(idTipoDoc, nroSerie);
    }

    @Override
    public boolean EsFactibleEliminarCuota(DocumentoPago dp) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        AnioMes amDocumento = dpDAO.ObtenerAnioMesCuotaDocumentoPago(dp.getIdDocumentoPago());
        AnioMes amUltimaCuota = null;
        if (amDocumento != null) {
            Cliente cliente = null;
            if (dp.getClienteByIdContadorEmpresa() != null) {
                cliente = dp.getClienteByIdContadorEmpresa();
            } else {
                cliente = dp.getCliente();
            }
            amUltimaCuota = dpDAO.ObtenerAnioMesUltimaCuota(cliente.getIdCliente(), dp.getIdDocumentoPago());
            if (amDocumento.getId() == amUltimaCuota.getId()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public int EsFactibleEliminarFinanciamiento(DocumentoPago dp) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        FinanciamientoDocumentoPago fdp = dpDAO.ObtenerFinanciamientoDocumentoPago(dp.getIdDocumentoPago());
        FinanciamientoDocumentoPago ultimoFinanciamiento = null;
        if (fdp != null) {
            if (dp.getClienteByIdContadorEmpresa() != null) {
                Financiamiento f = dpDAO.ObtenerCabeceraUltimoFinanciamiento(dp.getClienteByIdContadorEmpresa().getIdCliente());
                ultimoFinanciamiento = dpDAO.ObtenerUltimoFinanciamiento(dp.getClienteByIdContadorEmpresa().getIdCliente(), f.getIdFinanciamiento());
            } else {
                Financiamiento f = dpDAO.ObtenerCabeceraUltimoFinanciamiento(dp.getCliente().getIdCliente());
                ultimoFinanciamiento = dpDAO.ObtenerUltimoFinanciamiento(dp.getCliente().getIdCliente(), f.getIdFinanciamiento());
            }
            if (ultimoFinanciamiento == null) //NO TIENE FINANCIAMIENTO
            {
                return 0;
            }
            if (ultimoFinanciamiento.getId() == fdp.getId()) {
                int idDocumentoPagoDetalle = ultimoFinanciamiento.getDocumentoPagoDet().getIdDocumentoPagoDet();
                return idDocumentoPagoDetalle;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int EsFactibleEliminarReincorporacion(DocumentoPago dp) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        ReincorporacionDocumentoPago rdp = dpDAO.ObtenerReincorporacionDocumentoPago(dp.getIdDocumentoPago());
        ReincorporacionDocumentoPago ultimoFinanciamiento = null;
        if (rdp != null) {
            ultimoFinanciamiento = dpDAO.ObtenerUltimoReincorporacion(dp.getCliente().getIdCliente());
            if (ultimoFinanciamiento == null) //NO TIENE FINANCIAMIENTO
            {
                return 0;
            }
            if (ultimoFinanciamiento.getId() == rdp.getId()) {
                int idDocumentoPagoDetalle = ultimoFinanciamiento.getDocumentoPagoDet().getIdDocumentoPagoDet();
                return idDocumentoPagoDetalle;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean EliminarComprobante(DocumentoPago documentoPago) {
        try {
            /*VERIFICAR SI SE PUEDE ELIMINAR CUOTA*/
            boolean esFactibleCuota = this.EsFactibleEliminarCuota(documentoPago);
            if (esFactibleCuota == false) {
                SeguridadBO sBO = SeguridadBO.getInstance();
                sBO.GenerarLog("NO PUEDE ELIMINAR UNA CUOTA INTERMEDIA(DocumentoPagoBO.EliminarComprobante)");
                return false;
            }
            /*-------------------------------*/
            int esFactibleFinanciamiento = this.EsFactibleEliminarFinanciamiento(documentoPago);
            int esFactibleReincorporacion = this.EsFactibleEliminarReincorporacion(documentoPago);
            /*-------------------------------*/
            if (esFactibleCuota && esFactibleFinanciamiento > -1 && esFactibleReincorporacion > -1) {
                DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
                dpDAO.EliminarCuentasCorrientes(documentoPago.getCliente().getIdCliente(), documentoPago.getIdDocumentoPago());
                if (documentoPago.getClienteByIdContadorEmpresa() != null) {
                    dpDAO.EliminarCuotasCliente(documentoPago.getClienteByIdContadorEmpresa().getIdCliente(), documentoPago.getIdDocumentoPago());
                } else {
                    dpDAO.EliminarCuotasCliente(documentoPago.getCliente().getIdCliente(), documentoPago.getIdDocumentoPago());
                }
                if (esFactibleFinanciamiento > 0) {
                    FinanciamientoDocumentoPago fdp = dpDAO.ObtenerFinanciamientoDocumentoPagoDet(esFactibleFinanciamiento);
                    if (fdp.getEstado().equals("CI")) {
                        dpDAO.EliminarTodoFinanciamiento(fdp.getId());
                        //dpDAO.
                    } else {
                        dpDAO.EliminarFinanciamiento(esFactibleFinanciamiento);
                    }
                }
                if (esFactibleReincorporacion > 0) {
                    dpDAO.EliminarReincorporacion(esFactibleReincorporacion);
                }
                List docDetalle = dpDAO.ObtenerDetalleComprobanteElmininar(documentoPago.getIdDocumentoPago());
                int contador = 0;
                for (Object pobj : docDetalle) {
                    Object[] obj = (Object[]) pobj;
                    DocPagoAnulado dpa = null;
                    if (contador == 0) {
                        dpa = new DocPagoAnulado();
                        dpa.setTipoDoc((String) obj[0]);
                        dpa.setNroSerie((String) obj[1]);
                        dpa.setNroDoc((int) obj[2]);
                        dpa.setFechaDoc((Date) obj[3]);
                        dpDAO.GuardarDocumentoAnulado(dpa);
                    }
                    DocPagoAnuladoDetalle dpad = new DocPagoAnuladoDetalle();
                    dpad.setDocPagoAnulado(dpa);
                    ConceptoPagoDetalle cpd = new ConceptoPagoDetalle();
                    cpd.setIdConceptoPagoDetalle((int) obj[4]);
                    dpad.setConceptoPagoDetalle(cpd);
                    dpad.setPrecioUnitario((double) obj[5]);
                    dpad.setCantidad((int) obj[6]);
                    dpDAO.GuardarDocumentoAnuladoDetalle(dpad);
                    contador = contador + 1;
                }
//                dpDAO.Eliminar
                dpDAO.EliminarDetalleComprobante(documentoPago.getIdDocumentoPago());
                dpDAO.EliminarComprobante(documentoPago.getIdDocumentoPago());
                this.ValidarHabilitacion(documentoPago);
                return true;
            } else {
                //mensaje = "NO PUEDE ELIMINAR UN CUOTA DE FINANCIAMIENTO INTERMEDIA";
                SeguridadBO sBO = SeguridadBO.getInstance();
                sBO.GenerarLog("NO PUEDE ELIMINAR UN CUOTA DE FINANCIAMIENTO INTERMEDIA(DocumentoPagoBO.EliminarComprobante)");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("NO SE PUDO ELIMINAR");
            return false;
        }
    }

    @Override
    public boolean AnularComprobante(DocumentoPago documentoPago) {
        try {
            /*VERIFICAR SI SE PUEDE ELIMINAR CUOTA*/
            boolean esFactibleCuota = this.EsFactibleEliminarCuota(documentoPago);
            if (esFactibleCuota == false) {
                SeguridadBO sBO = SeguridadBO.getInstance();
                sBO.GenerarLog("NO PUEDE ANULAR UNA CUOTA INTERMEDIA(DocumentoPagoBO.AnularComprobante)");
                return false;
            }
            /*-------------------------------*/
 /*VERIFICAR SI SE PUEDE ELIMINAR FINANCIAMIENTO*/
            int esFactibleFinanciamiento = this.EsFactibleEliminarFinanciamiento(documentoPago);
            int esFactibleReincorporacion = this.EsFactibleEliminarReincorporacion(documentoPago);
            /*-------------------------------*/
            if (esFactibleCuota && esFactibleFinanciamiento > -1 && esFactibleReincorporacion > -1) {
                //---------------------------------------------------------------------------
                this.GenerarArchivoElectronico_Anulacion_ComprobantePago(documentoPago);
                //---------------------------------------------------------------------------
                DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
                dpDAO.EliminarCuentasCorrientes(documentoPago.getCliente().getIdCliente(), documentoPago.getIdDocumentoPago());
                dpDAO.EliminarCuotasCliente(documentoPago.getCliente().getIdCliente(), documentoPago.getIdDocumentoPago());
                if (esFactibleReincorporacion > 0) {
                    dpDAO.EliminarReincorporacion(esFactibleReincorporacion);
                }
                if (esFactibleFinanciamiento > 0) {
                    dpDAO.EliminarFinanciamiento(esFactibleFinanciamiento);
                }
                DocumentoPagoDet detAnulado = new DocumentoPagoDet();
                ConceptoPagoDetalle concepto = new ConceptoPagoDetalle();
                concepto.setIdConceptoPagoDetalle(6989);
                detAnulado.setConceptoPagoDetalle(concepto);
                detAnulado.setDocumentoPago(documentoPago);
                detAnulado.setValorVenta(0.0);
                detAnulado.setPrecioVenta(0.0);
                detAnulado.setIgv(0.0);
                detAnulado.setCantidad(0);
                detAnulado.setPrecioUnitario(0.0);
                dpDAO.AnularComprobante(documentoPago.getIdDocumentoPago());
                dpDAO.AnularDetalle(documentoPago.getIdDocumentoPago(), detAnulado);
                this.ValidarHabilitacion(documentoPago);
                return true;
            } else {
                //mensaje = "NO PUEDE ELIMINAR UN CUOTA DE FINANCIAMIENTO INTERMEDIA";
                SeguridadBO sBO = SeguridadBO.getInstance();
                sBO.GenerarLog("NO PUEDE ANULAR UN CUOTA DE FINANCIAMIENTO INTERMEDIA(DocumentoPagoBO.AnularComprobante)");
                return false;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("NO SE PUDO ELIMINAR");
            return false;
        }
    }

    @Override
    public void ValidarHabilitacion(DocumentoPago dp) {
        if (dp.getCliente().getTipoCliente().equals("C") || dp.getCliente().getTipoCliente().equals("S")) {
            Cliente cliente = dp.getCliente();
            ClienteBO cBO = ClienteBO.getInstance();
            cBO.ValidarHabilitacionColegiado(cliente);
        }
    }

    public DocumentoPagoBO() {
        resultadoBusqueda = new ArrayList();
        this.cuotasAPagar = new ArrayList();
        this.financiamientoAPagar = new ArrayList();
        this.reincorporacionAPagar = new ArrayList();
        this.clienteEmpresa = new Cliente(); //USADO PARA EL CASO EN QUE LA EMPRESA PAGA LA CUOTA DEL CONTADOR
    }

    @Override
    public boolean CerrarCaja(String fecha) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.CerrarCaja(fecha);
    }

    @Override
    public boolean ComprobanteTieneCurso(int id) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ComprobanteTieneCurso(id);
    }

    @Override
    public boolean EstaCerradoElDia(String fecha) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.EstaCerradoElDia(fecha);
    }

    @Override
    public void ActualizarCabeceraDocumento(DocumentoPago doc) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        dpDAO.ActualizarCabeceraDocumento(doc);
    }

    @Override
    public DocumentoPago ObtenerDocumentoPago(int idDocPago) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerDocumentoPago(idDocPago);
    }

    @Override
    public List ObtenerDocumentosPagos_SegunListado(List l) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerDocumentosPagos_SegunListado(l);
    }

    @Override
    public void ResetearListaCuotas() {
        if (this.cuotasAPagar != null) {
            while (this.cuotasAPagar.size() > 0) {
                this.cuotasAPagar.remove(0);
            }
        }
        if (this.financiamientoAPagar != null) {
            while (this.financiamientoAPagar.size() > 0) {
                this.financiamientoAPagar.remove(0);
            }
        }
        if (this.reincorporacionAPagar != null) {
            while (this.reincorporacionAPagar.size() > 0) {
                this.reincorporacionAPagar.remove(0);
            }
        }
        this.clienteEmpresa = new Cliente();
    }

    @Override
    public List ObtenerVentas(String desde, String hasta) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerVentas(desde, hasta);
    }

    @Override
    public byte[] generarExcelReporteVentasTipoEvento(String desde, String hasta) {
        SimpleDateFormat fFecha = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> filas = new HashMap<String, Object>();
        HashMap<String, caja.utils.excelItemDTO> celdas = new HashMap<String, caja.utils.excelItemDTO>();
        celdas.put("0", new caja.utils.excelItemDTO("TIPEVE", "S"));
        celdas.put("1", new caja.utils.excelItemDTO("DESEVE", "S"));
        celdas.put("2", new caja.utils.excelItemDTO("TIPDOC", "S"));
        celdas.put("3", new caja.utils.excelItemDTO("SERDOC", "S"));
        celdas.put("4", new caja.utils.excelItemDTO("NUMDOC", "S"));
        celdas.put("5", new caja.utils.excelItemDTO("FECDOC", "S"));
        celdas.put("6", new caja.utils.excelItemDTO("NOMBRE", "S"));
        celdas.put("7", new caja.utils.excelItemDTO("VALVTA", "S"));
        celdas.put("8", new caja.utils.excelItemDTO("IGVVTA", "S"));
        celdas.put("9", new caja.utils.excelItemDTO("TOTAL", "S"));
        celdas.put("10", new caja.utils.excelItemDTO("COBRADOR", "S"));
        filas.put(String.valueOf(filas.size()), celdas);
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        List<Object[]> l = dpDAO.ObtenerVentas(desde, hasta);
        for (int i = 0; i < l.size(); i++) {
            Object[] obj = (Object[]) l.get(i);
            String codigo = (String) obj[0];
            String nombreConcepto = (String) obj[1];
            String nombreDocumentoPago = (String) obj[2];
            String nroSerie = (String) obj[3];
            String nroDocumento = (String) obj[4];
            Date fechaPago = (Date) obj[5];
            String nombreCliente = (String) obj[6];
            Double valorVenta = (Double) obj[7];
            Double igv = (Double) obj[8];
            Double total = (Double) obj[9];
            String cobrado = (!Objects.isNull(obj[10]) ? (String) obj[10] : "");
            celdas = new HashMap<String, caja.utils.excelItemDTO>();
            celdas.put("0", new caja.utils.excelItemDTO(codigo, "S"));
            celdas.put("1", new caja.utils.excelItemDTO(nombreConcepto, "S"));
            celdas.put("2", new caja.utils.excelItemDTO(nombreDocumentoPago, "S"));
            celdas.put("3", new caja.utils.excelItemDTO(nroSerie, "S"));
            celdas.put("4", new caja.utils.excelItemDTO(nroDocumento, "S"));
            celdas.put("5", new caja.utils.excelItemDTO(fFecha.format(fechaPago), "S"));
            celdas.put("6", new caja.utils.excelItemDTO(nombreCliente, "S"));
            celdas.put("7", new caja.utils.excelItemDTO(valorVenta, "N"));
            celdas.put("8", new caja.utils.excelItemDTO(igv, "N"));
            celdas.put("9", new caja.utils.excelItemDTO(total, "N"));
            celdas.put("10", new caja.utils.excelItemDTO(cobrado, "S"));
            filas.put(String.valueOf(filas.size()), celdas);
        }
        return GenerarExcel.generarExcelFast(filas);
    }

    @Override
    public List BuscarContador(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();
        DocumentoPagoDAO doc = DocumentoPagoDAO.getInstance();
        if (tipoBusqueda == 1) {
            resultadoBusqueda = doc.BuscarContadorPorNombre(busqueda);
            return resultadoBusqueda;
        } else {
            resultadoBusqueda = doc.BuscarContadorPorNumero(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public DocumentoPago ObtenerContador(int numero) {
        for (DocumentoPago doc : resultadoBusqueda) {
            if (doc.getNroDocumentoPago().equals(numero)) {
                return doc;
            }
        }
        return null;
    }

    @Override
    public List ObtenerComprobantesAnioMes(int anio, int mes) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerComprobantesAnioMes(anio, mes);
    }

    @Override
    public List ObtenerComprobantes_SegunFiltro(String filtro) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerComprobantes_SegunFiltro(filtro);
    }

    @Override
    public DocumentoPago ObtenerComprobantePago_SegunTipoDocumentoSerieNro(int idDocumento, String nroSerie, int nroDocumento) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerComprobantePago_SegunTipoDocumentoSerieNro(idDocumento, nroSerie, nroDocumento);
    }

    @Override
    public List ObtenerComprobantesDesdeHasta(String fechaDesde, String fechaHasta) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerComprobantesDesdeHasta(fechaDesde, fechaHasta);
    }

    @Override
    public List ObtenerDocumentoPagoDesdeHasta(String fechaDesde, String fechaHasta) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerDocumentoPagoDesdeHasta(fechaDesde, fechaHasta);
    }

    @Override
    public double ObtenerMontoTotalComprobante(int idDocumentoPago) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.ObtenerMontoTotalComprobante(idDocumentoPago);
    }

    @Override
    public Operacion verificarExisteNroOperacion(String nroCuenta, String nroOperacion) {
        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
        return dpDAO.verificarExisteNroOperacion(nroCuenta, nroOperacion);
    }

    @Override
    public boolean GenerarNumeracionElectronicaComprobante(DocumentoPago cp) {
        try {
            DocumentoPagoDAO cpDAO = DocumentoPagoDAO.getInstance();
            cpDAO.GenerarNumeracionElectronicaComprobante(cp);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void GenerarArchivoElectronicoSUNAT(DocumentoPago documento) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        JSONObject objPrincipal = new JSONObject();
        SeguridadBO sBO = SeguridadBO.getInstance();
//        TipoOperacion to = (TipoOperacion) sBO.CargarObjeto("TipoOperacion", this.comprobantePago.getTipoOperacion().getId());
//        this.comprobantePago.setTipoOperacion(to);
        JSONObject objCabecera = new JSONObject();
        objCabecera.put("tipOperacion", "0101");
        objCabecera.put("fecEmision", formato.format(documento.getFechaPago()));
        objCabecera.put("horEmision", formatoHora.format(documento.getFechaSunat()));
        objCabecera.put("fecVencimiento", "");
        objCabecera.put("codLocalEmisor", "0000");
//        TipoDocIdentidad tdi = (TipoDocIdentidad) sBO.CargarObjeto("TipoDocIdentidad", this.comprobantePago.getClienteProveedor().getTipoDocIdentidad().getId());
//        documento.getCliente().setTipoDocIdentidad(tdi);
//        if (documento.getCliente().getTipoCliente() == null) {
//            JOptionPane.showMessageDialog(this, "NO SE PUEDE GENERAR EL DOCUMENTO ELECTRONICO", "ERROR", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        objCabecera.put("tipDocUsuario", documento.getCliente().getTipoDocIdentidad().getCodigo()); //CATALOGO 06
//        objCabecera.put("numDocUsuario", this.comprobantePago.getClienteProveedor().getNroDocIdentidad());
        String rzSocial = "";
        if (documento.getCliente().getTipoCliente().equals("C")) {
            objCabecera.put("tipDocUsuario", "1"); //CATALOGO 06
            objCabecera.put("numDocUsuario", documento.getCliente().getPnroDocumento());
//            objCabecera.put("rznSocialUsuario", documento.getCliente().getPapePat() + " " + documento.getCliente().getPapeMat() + " " + documento.getCliente().getPnombre());
            rzSocial = documento.getCliente().getPapePat() + " " + documento.getCliente().getPapeMat() + " " + documento.getCliente().getPnombre();
            rzSocial = rzSocial.replace("&", "&amp;");
            objCabecera.put("rznSocialUsuario", rzSocial);
        } else {
            if (documento.getCliente().getTipoCliente().equals("S")) {
                objCabecera.put("tipDocUsuario", "6"); //CATALOGO 06
                objCabecera.put("numDocUsuario", documento.getCliente().getSruc());
                rzSocial = documento.getCliente().getSnombreSociedad();
                rzSocial = rzSocial.replace("&", "&amp;");
                objCabecera.put("rznSocialUsuario", rzSocial);
            } else {
                if (documento.getCliente().getTipoCliente().equals("E")) {
                    objCabecera.put("tipDocUsuario", "6"); //CATALOGO 06
                    objCabecera.put("numDocUsuario", documento.getCliente().getEruc());
                    rzSocial = documento.getCliente().getEnombre();
                    rzSocial = rzSocial.replace("&", "&amp;");
                    objCabecera.put("rznSocialUsuario", rzSocial);
                } else {
                    if (documento.getCliente().getTipoCliente().equals("P")) {
                        objCabecera.put("tipDocUsuario", "1"); //CATALOGO 06
                        objCabecera.put("numDocUsuario", documento.getCliente().getPnroDocumento());
//                        objCabecera.put("rznSocialUsuario", documento.getCliente().getPapePat() + " " + documento.getCliente().getPapeMat() + " " + documento.getCliente().getPnombre());
                        rzSocial = documento.getCliente().getPapePat() + " " + documento.getCliente().getPapeMat() + " " + documento.getCliente().getPnombre();
                        rzSocial = rzSocial.replace("&", "&amp;");
                        objCabecera.put("rznSocialUsuario", rzSocial);
                    }
                }
            }
        }
        if (documento.getMoneda() == null) {
            String msg = "NO SE PUDO GENERAR EL COMPROBANTE ELECTRONICO - ERROR EN LA MONEDA";
            JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        if (documento.getMoneda().equals("S")) {
            objCabecera.put("tipMoneda", "PEN"); //CATALOGO 02
        } else {
            objCabecera.put("tipMoneda", "USD"); //CATALOGO 02
        }
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        double totaliGV = dpBO.ObtenerSumaTotalIGV(documento.getIdDocumentoPago());
        objCabecera.put("sumTotTributos", String.valueOf(totaliGV)); //  IGV
        double totalValorVenta = dpBO.ObtenerSumaTotalValorVenta(documento.getIdDocumentoPago());
        objCabecera.put("sumTotValVenta", String.valueOf(totalValorVenta)); //
        double totalPrecioVenta = dpBO.ObtenerSumaTotalPrecioVenta(documento.getIdDocumentoPago());
        objCabecera.put("sumPrecioVenta", String.valueOf(totalPrecioVenta)); //
        objCabecera.put("sumDescTotal", "0.0"); //DESCUENTOS
        objCabecera.put("sumOtrosCargos", "0.0"); //OTROS CARGOS
        objCabecera.put("sumTotalAnticipos", "0.0"); //PREGUNTAR SR VICTOR HUGO
        objCabecera.put("sumImpVenta", String.valueOf(totalPrecioVenta)); // PRECIO VENTA - DESCUENTO + OTROS CARGOS - ANTICIPOS
        objCabecera.put("ublVersionId", "2,1");
        objCabecera.put("customizationId", "2.0");
        objPrincipal.put("cabecera", objCabecera);
        //---------------------------------------------DETALLE----------------------------------------
//        ComprobanteBO cpBO = ComprobanteBO.getInstance();
        List<DocumentoPagoDet> lDetalle = dpBO.ObtenerDetalleComprobante(documento.getIdDocumentoPago());
        JSONArray objDetalle = new JSONArray();
//        SeguridadBO sBO = SeguridadBO.getInstance();
        for (DocumentoPagoDet cpd : lDetalle) {
            JSONObject det = new JSONObject();
            det.put("codUnidadMedida", "NIU");
            det.put("ctdUnidadItem", String.valueOf(cpd.getCantidad()));
            det.put("codProducto", cpd.getConceptoPagoDetalle().getTipoCodigo() + cpd.getConceptoPagoDetalle().getCodigo());
            det.put("codProductoSUNAT", ""); //ENLAZAR CON LAS TABLAS DE LA SUNAT - ESTA CON "C" - OPCIONAL ENVIAR
            det.put("desItem", cpd.getConceptoPagoDetalle().getDescripcion());
            double valorUnitario = cpd.getValorVenta() / cpd.getCantidad();
            valorUnitario = Math.round(valorUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
            det.put("mtoValorUnitario", String.valueOf(valorUnitario));
            det.put("sumTotTributosItem", String.valueOf(cpd.getIgv())); //Tributo: Monto de IGV por ítem + Tributo ISC: Monto de ISC por ítem + Tributo Otro: Código de tipo de tributo OTRO por Item
            det.put("codTriIGV", cpd.getCodigoTipoTributo()); //ENLAZAR TABLA CONCEPTO CON EL CATALOGO 05 - SUNAT
            det.put("mtoIgvItem", String.valueOf(cpd.getIgv()));
            det.put("mtoBaseIgvItem", String.valueOf(cpd.getValorVenta()));
            det.put("nomTributoIgvItem", cpd.getNombreTipoTributo()); //ENLAZAR TABLA CONCEPTO CON EL CATALOGO 05 - SUNAT - CAMPO NAME
            det.put("codTipTributoIgvItem", cpd.getCodigoInternacionalTipoTributo()); //ENLAZAR TABLA CONCEPTO CON EL CATALOGO 05 - SUNAT - CODIGO -- LLAMAR SUNAT
            det.put("tipAfeIGV", cpd.getCodigoTipoAfectacion()); //PREGUNTAR SI SIEMPRE SE USA ESE CODIGO - CATALOGO 07
            det.put("porIgvItem", String.valueOf(cpd.getIgvPorcentaje())); //Colocar 18.00 para expresar 18% - AGREGAR EL CAMPO PORCENTAJE EN EL DETALLE
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
            //--------------------------TRIBUTO ICBPER 7152--------------------------
            det.put("codTriIcbper", "");
            det.put("mtoTriIcbperItem", "0.0");
            det.put("ctdBolsasTriIcbperItem", "0");
            det.put("nomTributoIcbperItem", "");
            det.put("codTipTributoIcbperItem", "");
            det.put("mtoTriIcbperUnidad", "0.0");
            //------------------------------------------------------------------
            double precioUnitario = (cpd.getValorVenta() + cpd.getIgv()) / cpd.getCantidad();
            precioUnitario = Math.round(precioUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
            det.put("mtoPrecioVentaUnitario", String.valueOf(precioUnitario));
            det.put("mtoValorVentaItem", String.valueOf(cpd.getValorVenta()));
            det.put("mtoValorReferencialUnitario", "0.0");
            //-----------------------TRIBUTOS GENERALES-------------------------
            det.put("ideTributo", cpd.getCodigoTipoTributo());
            det.put("nomTributo", cpd.getNombreTipoTributo());
            det.put("codTipTributo", cpd.getCodigoInternacionalTipoTributo());
            det.put("mtoBaseImponible", String.valueOf(cpd.getValorVenta()));
            det.put("mtoTributo", String.valueOf(cpd.getIgv()));
            objDetalle.add(det);
        }
        objPrincipal.put("detalle", objDetalle);
        //---------------------------------LEYENDA------------------------------
        JSONObject objLeyenda = new JSONObject();
        objLeyenda.put("codLeyenda", "1000");
        objLeyenda.put("desLeyenda", NumeroLetras.convertirNumerosALetras(String.valueOf(totalPrecioVenta), documento.getMoneda()).replace("/", "'/'"));
        objPrincipal.put("leyenda", objLeyenda);
        String nombreArchivo = "";
        try {
            TipoDocSerie tds = (TipoDocSerie) sBO.CargarObjeto("TipoDocSerie", documento.getTipoDocSerie().getId());
            documento.setTipoDocSerie(tds);
            Serie s = (Serie) sBO.CargarObjeto("Serie", documento.getTipoDocSerie().getSerie().getIdSerie());
            documento.getTipoDocSerie().setSerie(s);
            TipoDocPago td = (TipoDocPago) sBO.CargarObjeto("TipoDocPago", documento.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago());
            documento.getTipoDocSerie().setTipoDocPago(td);
            nombreArchivo = "20174327026-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + ".json";
//            nombreArchivo = "20100194601-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getTipoDocSerie().getSerie().getSerie() + String.format("%08d", documento.getNroDocumentoPago()) + ".json";
//            FileWriter archivo = new FileWriter("\\D:\\" + nombreArchivo);
            FileWriter archivo = new FileWriter(sBO.getDIR_FACTURADOR() + nombreArchivo);
            archivo.write(objPrincipal.toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            //manejar error
        }
    }

    private void GenerarArchivoElectronico_ComunicacionBaja_SUNAT(DocumentoPago documento) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoJson = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat fAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat fMes = new SimpleDateFormat("MM");
        SimpleDateFormat fDia = new SimpleDateFormat("dd");
        JSONObject objPrincipal = new JSONObject();
        SeguridadBO sBO = SeguridadBO.getInstance();
        documento.setFechaAnulacion(sBO.ObtenerFechaHoraServidor());
        JSONObject objCabecera = new JSONObject();
        objCabecera.put("fecGeneracion", formato.format(documento.getFechaSunat()));
        objCabecera.put("fecComunicacion", formato.format(documento.getFechaAnulacion()));
        objCabecera.put("tipDocBaja", documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago());
        objCabecera.put("numDocBaja", documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()));
        objCabecera.put("desMotivoBaja", documento.getMotivoAnulacion());
        objPrincipal.put("cabecera", objCabecera);
        String nombreArchivo = "";
        try {
//            TipoDocSerie tds = (TipoDocSerie) sBO.CargarObjeto("TipoDocSerie", documento.getTipoDocSerie().getId());
//            documento.setTipoDocSerie(tds);
//            Serie s = (Serie) sBO.CargarObjeto("Serie", documento.getTipoDocSerie().getSerie().getIdSerie());
//            documento.getTipoDocSerie().setSerie(s);
//            TipoDocPago td = (TipoDocPago) sBO.CargarObjeto("TipoDocPago", documento.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago());
//            documento.getTipoDocSerie().setTipoDocPago(td);
            int correlativoAnulacion = this.ObtenerCorrelativoAnulacion(documento, Integer.valueOf(fAnio.format(documento.getFechaAnulacion())), Integer.valueOf(fMes.format(documento.getFechaAnulacion())), Integer.valueOf(fDia.format(documento.getFechaAnulacion())));
//            int correlativoAnulacion = this.ObtenerCorrelativoAnulacion(documento, formatoJson.format(documento.getFechaAnulacion()));
            nombreArchivo = "20174327026-RA-" + formatoJson.format(documento.getFechaAnulacion()) + "-" + String.format("%03d", correlativoAnulacion) + ".json";
//            FileWriter archivo = new FileWriter("\\D:\\prueba\\" + nombreArchivo);
            FileWriter archivo = new FileWriter(sBO.getDIR_FACTURADOR() + nombreArchivo);
            archivo.write(objPrincipal.toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            //manejar error
        }
    }

    private void GenerarArchivoElectronico_ResumenDiario_SUNAT(DocumentoPago documento) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoJson = new SimpleDateFormat("yyyyMMdd");
        JSONObject objPrincipal = new JSONObject();
        SeguridadBO sBO = SeguridadBO.getInstance();
//        TipoOperacion to = (TipoOperacion) sBO.CargarObjeto("TipoOperacion", this.comprobantePago.getTipoOperacion().getId());
//        this.comprobantePago.setTipoOperacion(to);
        documento.setFechaAnulacion(sBO.ObtenerFechaHoraServidor());
        JSONObject objCabecera = new JSONObject();
        objCabecera.put("fecEmision", formato.format(documento.getFechaSunat()));
        objCabecera.put("fecResumen", formato.format(documento.getFechaAnulacion()));
        objCabecera.put("tipDocResumen", documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago());
        objCabecera.put("idDocResumen", documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()));
        if (documento.getCliente().getTipoCliente().equals("C")) {
            objCabecera.put("tipDocUsuario", "1"); //CATALOGO 06
            objCabecera.put("numDocUsuario", documento.getCliente().getPnroDocumento());
        } else {
            if (documento.getCliente().getTipoCliente().equals("S")) {
                objCabecera.put("tipDocUsuario", "6"); //CATALOGO 06
                objCabecera.put("numDocUsuario", documento.getCliente().getSruc());
            } else {
                if (documento.getCliente().getTipoCliente().equals("E")) {
                    objCabecera.put("tipDocUsuario", "6"); //CATALOGO 06
                    objCabecera.put("numDocUsuario", documento.getCliente().getEruc());
                } else {
                    if (documento.getCliente().getTipoCliente().equals("P")) {
                        objCabecera.put("tipDocUsuario", "1"); //CATALOGO 06
                        objCabecera.put("numDocUsuario", documento.getCliente().getPnroDocumento());
                    }
                }
            }
        }
//        if (documento.getMoneda() == null) {
//            String msg = "NO SE PUDO GENERAR EL COMPROBANTE ELECTRONICO - ERROR EN LA MONEDA";
//            JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
        if (documento.getMoneda().equals("S")) {
            objCabecera.put("tipMoneda", "PEN"); //CATALOGO 02
        } else {
            objCabecera.put("tipMoneda", "USD"); //CATALOGO 02
        }
        double totalValorVentaGrabadas = 0.0;
        double totalValorVentaExoneradas = 0.0;
        double totalValorVentaInafectas = 0.0;
        List<Object> lTributos = this.ObtenerTributos_TotalesValorVenta(documento.getIdDocumentoPago());
        for (Object tt : lTributos) {
            Object[] Objtt = (Object[]) tt;
            if (Objtt != null) {
                if (Objtt[0] != null) {
                    if (Objtt[0].equals("1000")) {
                        totalValorVentaGrabadas = (double) Objtt[1];
                    } else {
                        if (Objtt[0].equals("9997")) {
                            totalValorVentaExoneradas = (double) Objtt[1];
                        } else {
                            if (Objtt[0].equals("9998")) {
                                totalValorVentaInafectas = (double) Objtt[1];
                            } else {
                            }
                        }
                    }
                }
            }
        }
        double totalIGV = this.ObtenerSumaTotalIGV(documento.getIdDocumentoPago());
        double totalPrecioVenta = this.ObtenerSumaTotalPrecioVenta(documento.getIdDocumentoPago());
        objCabecera.put("totValGrabado", String.valueOf(totalValorVentaGrabadas));
        objCabecera.put("totValExoneado", String.valueOf(totalValorVentaExoneradas));
        objCabecera.put("totValInafecto", String.valueOf(totalValorVentaInafectas));
        objCabecera.put("monValGratuito", "0.0");//-------------OK--------------
        objCabecera.put("totOtroCargo", "0.0");//-------------OK--------------
        objCabecera.put("monTribIsc", "0.0");//-------------OK--------------
        objCabecera.put("monTribIgv", String.valueOf(totalIGV));
        objCabecera.put("monTribOtro", "0.0");//-------------OK--------------
        objCabecera.put("totImpCpe", String.valueOf(totalPrecioVenta));
        objCabecera.put("tipDocModifico", "");//-------------OK--------------
        objCabecera.put("serDocModifico", "");//-------------OK--------------
        objCabecera.put("numDocModifico", "");//-------------OK--------------
        objCabecera.put("tipRegPercepcion", "");//-------------OK--------------
        objCabecera.put("porPercepcion", "");//-------------OK--------------
        objCabecera.put("monBasePercepcion", "");//-------------OK--------------
        objCabecera.put("monPercepcion", "");//-------------OK--------------
        objCabecera.put("monTotIncPercepcion", "");//-------------OK--------------
        objCabecera.put("tipEstado", "3");
        objPrincipal.put("cabecera", objCabecera);
        String nombreArchivo = "";
        try {
//            int correlativoAnulacion = this.ObtenerCorrelativoAnulacion(documento, formatoJson.format(documento.getFechaAnulacion()));
//            nombreArchivo = "20174327026-RC-" + formatoJson.format(documento.getFechaAnulacion()) + "-" + String.format("%03d", correlativoAnulacion) + ".json";
            FileWriter archivo = new FileWriter(sBO.getDIR_FACTURADOR() + nombreArchivo);
            archivo.write(objPrincipal.toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            //manejar error
        }
    }

    @Override
    public List ObtenerTributosGenerales(int idDocumentoPago) {
        try {
            //this.documentoPago.setDocumentoPagoDets(dpd);
            DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
            return dpDAO.ObtenerTributosGenerales(idDocumentoPago);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en Obtener Detalle Comprobante");
            return null;
        }
    }

    @Override
    public List BuscarResumenDiario_SegunNroNota(int nro) {
        DocumentoPagoDAO ncDAO = DocumentoPagoDAO.getInstance();
        return ncDAO.BuscarResumenDiario_SegunNroNota(nro);
    }

    @Override
    public List BuscarResumenDiario_SegunFecha(Date desde, Date hasta) {
        DocumentoPagoDAO ncDAO = DocumentoPagoDAO.getInstance();
        return ncDAO.BuscarResumenDiario_SegunFecha(desde, hasta);
    }

    private void GenerarArchivoElectronico_ResumenDiario_SUNAT_TXT(DocumentoPago documento) {
        FileWriter w1001 = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoJson = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat fAnio = new SimpleDateFormat("yyyy");
            SimpleDateFormat fMes = new SimpleDateFormat("MM");
            SimpleDateFormat fDia = new SimpleDateFormat("dd");
//            JSONObject objPrincipal = new JSONObject();
            SeguridadBO sBO = SeguridadBO.getInstance();
            BufferedWriter bw1001 = null;
            PrintWriter wr1001 = null;
            File f1001;
            //------------------------------------------------------------------
            documento.setFechaAnulacion(sBO.ObtenerFechaHoraServidor());
//            int correlativoAnulacion = this.ObtenerCorrelativoAnulacion(documento, formatoJson.format(documento.getFechaAnulacion()));
            int correlativoAnulacion = this.ObtenerCorrelativoAnulacion(documento, Integer.valueOf(fAnio.format(documento.getFechaAnulacion())), Integer.valueOf(fMes.format(documento.getFechaAnulacion())), Integer.valueOf(fDia.format(documento.getFechaAnulacion())));
            String nombreArchivo = "20174327026-RC-" + formatoJson.format(documento.getFechaAnulacion()) + "-" + String.format("%03d", correlativoAnulacion);
//            FileWriter archivo = new FileWriter("\\\\192.168.1.67\\DATA\\" + nombreArchivo);
            //------------------------------------------------------------------
            f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".rdi");
//            f1001 = new File("D:\\" + nombreArchivo);
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            String exportacion = "";
            exportacion = exportacion + formato.format(documento.getFechaSunat()) + "|"; //fecEmision
            exportacion = exportacion + formato.format(documento.getFechaAnulacion()) + "|"; //fecResumen
            exportacion = exportacion + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "|"; //tipDocResumen
            exportacion = exportacion + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago()) + "|"; //idDocResumen
            if (documento.getCliente().getTipoCliente().equals("C")) {
                exportacion = exportacion + "1" + "|"; //tipDocUsuario
                exportacion = exportacion + documento.getCliente().getPnroDocumento() + "|"; //numDocUsuario
            } else {
                if (documento.getCliente().getTipoCliente().equals("S")) {
                    exportacion = exportacion + "6" + "|"; //tipDocUsuario
                    exportacion = exportacion + documento.getCliente().getSruc() + "|"; //numDocUsuario
                } else {
                    if (documento.getCliente().getTipoCliente().equals("E")) {
                        exportacion = exportacion + "6" + "|"; //tipDocUsuario
                        exportacion = exportacion + documento.getCliente().getEruc() + "|"; //numDocUsuario
                    } else {
                        if (documento.getCliente().getTipoCliente().equals("P")) {
                            exportacion = exportacion + "1" + "|"; //tipDocUsuario
                            exportacion = exportacion + documento.getCliente().getPnroDocumento() + "|"; //numDocUsuario
                        }
                    }
                }
            }
            if (documento.getMoneda().equals("S")) {
                exportacion = exportacion + "PEN" + "|"; //CATALOGO 02 //tipMoneda
            } else {
                exportacion = exportacion + "USD" + "|"; //CATALOGO 02 //tipMoneda
            }
            documento.setFechaAnulacion(sBO.ObtenerFechaHoraServidor());
            this.ActualizarCabeceraDocumento(documento);
            double totalValorVentaGrabadas = 0.0;
            double totalValorVentaExoneradas = 0.0;
            double totalValorVentaInafectas = 0.0;
            List<Object> lTributos = this.ObtenerTributos_TotalesValorVenta(documento.getIdDocumentoPago());
            for (Object tt : lTributos) {
                Object[] Objtt = (Object[]) tt;
                if (Objtt != null) {
                    if (Objtt[0] != null) {
                        if (Objtt[0].equals("1000")) {
                            totalValorVentaGrabadas = (double) Objtt[1];
                        } else {
                            if (Objtt[0].equals("9997")) {
                                totalValorVentaExoneradas = (double) Objtt[1];
                            } else {
                                if (Objtt[0].equals("9998")) {
                                    totalValorVentaInafectas = (double) Objtt[1];
                                } else {
                                }
                            }
                        }
                    }
                }
            }
            double totalIGV = this.ObtenerSumaTotalIGV(documento.getIdDocumentoPago());
            double totalPrecioVenta = this.ObtenerSumaTotalPrecioVenta(documento.getIdDocumentoPago());
            exportacion = exportacion + String.valueOf(totalValorVentaGrabadas) + "|"; //totValGrabado
            exportacion = exportacion + String.valueOf(totalValorVentaExoneradas) + "|"; //totValExoneado
            exportacion = exportacion + String.valueOf(totalValorVentaInafectas) + "|"; //totValInafecto
            exportacion = exportacion + "0.0" + "|"; //totValExportado -NEW
            exportacion = exportacion + "0.0" + "|"; // monValGratuito
            exportacion = exportacion + "0.0" + "|"; // totOtroCargo
//            exportacion = exportacion + "0.0" + "|"; // monTribIsc
//            exportacion = exportacion + String.valueOf(totalIGV) + "|"; // monTribIgv
//            exportacion = exportacion + "0.0" + "|"; // monTribOtro
            exportacion = exportacion + String.valueOf(totalPrecioVenta) + "|"; // totImpCpe
            exportacion = exportacion + "" + "|"; // tipDocModifico
            exportacion = exportacion + "" + "|"; // serDocModifico
            exportacion = exportacion + "" + "|"; // numDocModifico
            exportacion = exportacion + "" + "|"; // tipRegPercepcion
            exportacion = exportacion + "" + "|"; // porPercepcion
            exportacion = exportacion + "" + "|"; // monBasePercepcion
            exportacion = exportacion + "" + "|"; // monPercepcion
            exportacion = exportacion + "" + "|"; // monTotIncPercepcion
            exportacion = exportacion + "3" + "|"; // tipEstado
            wr1001.write(exportacion + "\r\n");
            wr1001.close();
            bw1001.close();
            //-------------------------NUEVO CODIGO----------------------------- 
            exportacion = "";
            f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".trd");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            List<DocumentoPagoDet> lDetalle = this.ObtenerDetalleComprobante(documento.getIdDocumentoPago());
            for (DocumentoPagoDet cpd : lDetalle) {
                exportacion = exportacion + "1|"; //Linea Documento
                exportacion = exportacion + cpd.getCodigoTipoTributo() + "|"; //Linea Documento
                exportacion = exportacion + cpd.getNombreTipoTributo() + "|"; //Linea Documento
                exportacion = exportacion + cpd.getCodigoInternacionalTipoTributo() + "|"; //Linea Documento
                exportacion = exportacion + String.valueOf(cpd.getValorVenta()) + "|"; //Linea Documento
                exportacion = exportacion + String.valueOf(cpd.getIgv()) + "|"; //Linea Documento
            }
            wr1001.write(exportacion + "\r\n");
            wr1001.close();
            bw1001.close();
            //-----------------------FIN NUEVO CODIGO---------------------------
        } catch (IOException ex) {
            Logger.getLogger(DocumentoPagoBO.class.getName()).log(Level.SEVERE, null, ex);
            //manejar error
        } finally {
            try {
                w1001.close();
            } catch (IOException ex) {
                Logger.getLogger(DocumentoPagoBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generacionArchivoFormaPago(DocumentoPago doc, List<DocumentoPagoDet> lDetalle, FileWriter w1001, BufferedWriter bw1001, PrintWriter wr1001, File f1001, String nombreArchivo, String formaPago, Double totalPrecioVenta) {
        try {
            SeguridadBO sBO = SeguridadBO.getINSTANCE();
            f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".PAG");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            String cadena = "";
            Double montoDetraccion = 0.0;
            for (DocumentoPagoDet dpd : lDetalle) {
                montoDetraccion = montoDetraccion + (!Objects.isNull(dpd.getMontoDetraccion()) ? dpd.getMontoDetraccion() : 0.0);
            }
            Double montoSinDetraccion = totalPrecioVenta - montoDetraccion;
            montoSinDetraccion = Math.round(montoSinDetraccion * Math.pow(10, 2)) / Math.pow(10, 2);
            if (!Objects.isNull(doc.getTieneDetraccion())) {
                if (doc.getTieneDetraccion().equals("S")) {
                    cadena = cadena + formaPago + "|" + (formaPago.equals("Credito") ? String.valueOf(montoSinDetraccion) : "0.0") + "|PEN";
                } else {
                    cadena = cadena + formaPago + "|" + (formaPago.equals("Credito") ? String.valueOf(totalPrecioVenta) : "0.0") + "|PEN";
                }
            } else {
                cadena = cadena + formaPago + "|" + (formaPago.equals("Credito") ? String.valueOf(totalPrecioVenta) : "0.0") + "|PEN";
            }
            wr1001.write(cadena + "\r\n");
            wr1001.close();
            bw1001.close();
        } catch (IOException ex) {
            Logger.getLogger(DocumentoPagoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generacionArchivoDetalleFormaPagoCredito(DocumentoPago doc, List<DocumentoPagoDet> lDetalle, FileWriter w1001, BufferedWriter bw1001, PrintWriter wr1001, File f1001, String nombreArchivo, String formaPago, Date fechaVencimiento, Double totalPrecioVenta) {
        try {
            if (formaPago.equals("Credito")) {
                SeguridadBO sBO = SeguridadBO.getINSTANCE();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".DPA");
                w1001 = new FileWriter(f1001);
                bw1001 = new BufferedWriter(w1001);
                wr1001 = new PrintWriter(bw1001);
                String cadena = "";
                Double montoDetraccion = 0.0;
                for (DocumentoPagoDet dpd : lDetalle) {
                    montoDetraccion = montoDetraccion + (!Objects.isNull(dpd.getMontoDetraccion()) ? dpd.getMontoDetraccion() : 0.0);
                }
                Double montoSinDetraccion = totalPrecioVenta - montoDetraccion;
                montoSinDetraccion = Math.round(montoSinDetraccion * Math.pow(10, 2)) / Math.pow(10, 2);
                if (!Objects.isNull(doc.getTieneDetraccion())) {
                    if (doc.getTieneDetraccion().equals("S")) {
                        cadena = cadena + String.valueOf(montoSinDetraccion) + "|" + f.format(fechaVencimiento) + "|PEN";
                    } else {
                        cadena = cadena + String.valueOf(totalPrecioVenta) + "|" + f.format(fechaVencimiento) + "|PEN";
                    }
                } else {
                    cadena = cadena + String.valueOf(totalPrecioVenta) + "|" + f.format(fechaVencimiento) + "|PEN";
                }
                wr1001.write(cadena + "\r\n");
                wr1001.close();
                bw1001.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(DocumentoPagoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarArchivoACA(String nombreArchivo, DocumentoPago doc, List<DocumentoPagoDet> lDetalle) {
        try {
            if (!Objects.isNull(doc.getTieneDetraccion())) {
                if (doc.getTieneDetraccion().equals("S")) {
                    Double montoComprobante = 0.0;
                    for (DocumentoPagoDet dpd : lDetalle) {
                        montoComprobante = montoComprobante + dpd.getValorVenta() + dpd.getIgv();
                    }
                    if (montoComprobante > 700) {
                        SeguridadBO sBO = SeguridadBO.getINSTANCE();
                        File f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".ACA");
                        FileWriter w1001 = new FileWriter(f1001);
                        BufferedWriter bw1001 = new BufferedWriter(w1001);
                        PrintWriter wr1001 = new PrintWriter(bw1001);
                        String detraccion = "";
                        String nroCuenta = sBO.getNRO_CUENTA_DETRACCION();
                        Double montoDetraccion = 0.0;
                        for (DocumentoPagoDet dpd : lDetalle) {
                            montoDetraccion = montoDetraccion + (!Objects.isNull(dpd.getMontoDetraccion()) ? dpd.getMontoDetraccion() : 0.0);
                        }
                        if (lDetalle.size() > 0) {
                            DocumentoPagoDet dpd = (DocumentoPagoDet) lDetalle.get(0);
                            detraccion = detraccion + nroCuenta + "|";
                            detraccion = detraccion + dpd.getCodigoBienServicioDetraccion() + "|";
                            detraccion = detraccion + dpd.getPorcentajeDetraccion() + "|";
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
            }
        } catch (IOException e) {
        }
    }

    private void GenerarArchivoElectronicoSUNAT_TXT(DocumentoPago documento) {
        try {
            SeguridadBO sBO = SeguridadBO.getINSTANCE();
            FileWriter w1001 = null;
            BufferedWriter bw1001 = null;
            PrintWriter wr1001 = null;
            File f1001;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            String nombreArchivo = "20174327026" + "-" + documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() + "-" + documento.getNroSerie() + "-" + String.format("%08d", documento.getNroDocumentoPago());
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
//            f1001 = new File("\\\\D:\\" + nombreArchivo + ".CAB");
            f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".CAB");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            String cabecera = "";
            String tipoOperacion = "0101";
            if (!Objects.isNull(documento.getTieneDetraccion())) {
                if (documento.getTieneDetraccion().equals("S")) {
                    tipoOperacion = "1001";
                }
            }
            cabecera = cabecera + tipoOperacion + "|"; //Tipo de operación 01
            cabecera = cabecera + formato.format(documento.getFechaPago()) + "|"; //Fecha de emisión 02
            cabecera = cabecera + formatoHora.format(documento.getFechaSunat()) + "|"; //Hora de Emisión 03
            cabecera = cabecera + "" + "|"; //Fecha de vencimiento 04
            cabecera = cabecera + "0000" + "|"; //Código del domicilio fiscal o de local anexo del emisor 05
            String rzSocial = "";
            if (documento.getCliente().getTipoCliente().equals("C")) {
                cabecera = cabecera + "1" + "|"; //Tipo de documento de identidad del adquirente o usuario
                cabecera = cabecera + documento.getCliente().getPnroDocumento() + "|"; //Número de documento de identidad del adquirente o usuario
                rzSocial = documento.getCliente().getPapePat() + " " + documento.getCliente().getPapeMat() + " " + documento.getCliente().getPnombre();
                rzSocial = rzSocial.replace("&", "&amp;");
                cabecera = cabecera + rzSocial + "|"; //Apellidos y nombres, denominación o razón social del adquirente o usuario 
            } else {
                if (documento.getCliente().getTipoCliente().equals("S")) {
                    cabecera = cabecera + "6" + "|"; //Tipo de documento de identidad del adquirente o usuario
                    cabecera = cabecera + documento.getCliente().getSruc() + "|"; //Número de documento de identidad del adquirente o usuario
                    rzSocial = documento.getCliente().getSnombreSociedad();
                    rzSocial = rzSocial.replace("&", "&amp;");
                    cabecera = cabecera + rzSocial + "|"; //Apellidos y nombres, denominación o razón social del adquirente o usuario 
                } else {
                    if (documento.getCliente().getTipoCliente().equals("E")) {
                        cabecera = cabecera + "6" + "|"; //Tipo de documento de identidad del adquirente o usuario
                        cabecera = cabecera + documento.getCliente().getEruc() + "|"; //Número de documento de identidad del adquirente o usuario
                        rzSocial = documento.getCliente().getEnombre();
                        rzSocial = rzSocial.replace("&", "&amp;");
                        cabecera = cabecera + rzSocial + "|"; //Apellidos y nombres, denominación o razón social del adquirente o usuario 
                    } else {
                        if (documento.getCliente().getTipoCliente().equals("P")) {
                            cabecera = cabecera + "1" + "|"; //Tipo de documento de identidad del adquirente o usuario
                            cabecera = cabecera + documento.getCliente().getPnroDocumento() + "|"; //Número de documento de identidad del adquirente o usuario
                            rzSocial = documento.getCliente().getPapePat() + " " + documento.getCliente().getPapeMat() + " " + documento.getCliente().getPnombre();
                            rzSocial = rzSocial.replace("&", "&amp;");
                            cabecera = cabecera + rzSocial + "|"; //Apellidos y nombres, denominación o razón social del adquirente o usuario 
                        }
                    }
                }
            }
            //----------------------------------------------------------------------
            if (documento.getMoneda() == null) {
                String msg = "NO SE PUDO GENERAR EL COMPROBANTE ELECTRONICO - ERROR EN LA MONEDA";
                JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if (documento.getMoneda().equals("S")) {
                cabecera = cabecera + "PEN" + "|"; //Tipo de moneda en la cual se emite la factura electrónica
            } else {
                cabecera = cabecera + "USD" + "|"; //Tipo de moneda en la cual se emite la factura electrónica
            }
            double totaliGV = dpBO.ObtenerSumaTotalIGV(documento.getIdDocumentoPago());
            cabecera = cabecera + String.valueOf(totaliGV) + "|"; //Sumatoria Tributos
            double totalValorVenta = dpBO.ObtenerSumaTotalValorVenta(documento.getIdDocumentoPago());
            cabecera = cabecera + String.valueOf(totalValorVenta) + "|"; //Total valor de venta
            double totalPrecioVenta = dpBO.ObtenerSumaTotalPrecioVenta(documento.getIdDocumentoPago());
            cabecera = cabecera + String.valueOf(totalPrecioVenta) + "|"; //Total Precio de Venta
            cabecera = cabecera + "0.0" + "|"; //Total descuentos
            cabecera = cabecera + "0.0" + "|"; //Sumatoria otros Cargos
            cabecera = cabecera + "0.0" + "|"; //Total Anticipos
            cabecera = cabecera + String.valueOf(totalPrecioVenta) + "|"; //Importe total de la venta, cesion en uso o del servicio prestado
            cabecera = cabecera + "2.1" + "|"; //Versión UBL
            cabecera = cabecera + "2.0" + "|"; //Customization Documento
            wr1001.write(cabecera + "\r\n");
            wr1001.close();
            bw1001.close();
            //---------------------------------------------DETALLE----------------------------------------
//            f1001 = new File("\\\\D:\\" + nombreArchivo + ".DET");
            f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".DET");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            List<DocumentoPagoDet> lDetalle = dpBO.ObtenerDetalleComprobante(documento.getIdDocumentoPago());
            for (DocumentoPagoDet cpd : lDetalle) {
                String detalle = "";
                detalle = detalle + "NIU" + "|"; //Código de unidad de medida por ítem
                detalle = detalle + String.valueOf(cpd.getCantidad()) + "|"; //Cantidad de unidades por ítem
                detalle = detalle + cpd.getConceptoPagoDetalle().getTipoCodigo() + cpd.getConceptoPagoDetalle().getCodigo() + "|"; //Código de producto
                detalle = detalle + "" + "|"; //Codigo producto SUNAT
                detalle = detalle + cpd.getConceptoPagoDetalle().getDescripcion() + "|"; //Descripción detallada del servicio prestado, bien vendido o cedido en uso, indicando las características.
                double valorUnitario = cpd.getValorVenta() / cpd.getCantidad();
                valorUnitario = Math.round(valorUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
                detalle = detalle + String.valueOf(valorUnitario) + "|"; //Valor Unitario (cac:InvoiceLine/cac:Price/cbc:PriceAmount)
                detalle = detalle + String.valueOf(cpd.getIgv()) + "|"; //Sumatoria Tributos por item
                //--------------------------------------------------------------
                detalle = detalle + cpd.getCodigoTipoTributo() + "|"; //Tributo: Códigos de tipos de tributos IGV
                detalle = detalle + String.valueOf(cpd.getIgv()) + "|"; //Tributo: Monto de IGV por ítem
                detalle = detalle + String.valueOf(cpd.getValorVenta()) + "|"; //Tributo: Base Imponible IGV por Item
                detalle = detalle + cpd.getNombreTipoTributo() + "|"; //Tributo: Nombre de tributo por item
                detalle = detalle + cpd.getCodigoInternacionalTipoTributo() + "|"; //Tributo: Código de tipo de tributo por Item
                detalle = detalle + cpd.getCodigoTipoAfectacion() + "|"; //Tributo: Afectación al IGV por ítem
                detalle = detalle + String.valueOf(cpd.getIgvPorcentaje()) + "|"; //Tributo: Porcentaje de IGV
                //-------------------------------ISC----------------------------
                detalle = detalle + "" + "|"; //Tributo ISC: Códigos de tipos de tributos ISC
                detalle = detalle + "0.0" + "|"; //Tributo ISC: Monto de ISC por ítem
                detalle = detalle + "0.0" + "|"; //Tributo ISC: Base Imponible ISC por Item
                detalle = detalle + "" + "|"; //Tributo ISC: Nombre de tributo por item
                detalle = detalle + "" + "|"; //Tributo ISC: Código de tipo de tributo por Item
                detalle = detalle + "" + "|"; //Tributo ISC: Tipo de sistema ISC
                detalle = detalle + "0" + "|"; //Tributo ISC: Porcentaje de ISC
                //--------------------------OTROS TRIBUTOS--------------------------
                detalle = detalle + "" + "|"; //Tributo Otro: Códigos de tipos de tributos OTRO
                detalle = detalle + "0.0" + "|"; //Tributo Otro: Monto de tributo OTRO por iItem
                detalle = detalle + "0.0" + "|"; //Tributo Otro: Base Imponible de tributo OTRO por Item
                detalle = detalle + "" + "|"; //Tributo Otro:  Nombre de tributo OTRO por item
                detalle = detalle + "" + "|"; //Tributo Otro: Código de tipo de tributo OTRO por Item
                detalle = detalle + "0" + "|"; //Tributo Otro: Porcentaje de tributo OTRO por Item
                //--------------------------TRIBUTO ICBPER7152----------------------
                detalle = detalle + "" + "|"; //Tributo ICBPER: Códigos de tipos de tributos ICBPER
                detalle = detalle + "0.0" + "|"; //Tributo ICBPER: Monto de tributo ICBPER por iItem
                detalle = detalle + "0" + "|"; //Tributo ICBPER: Cantidad de bolsas plásticas por Item
                detalle = detalle + "" + "|"; //Tributo ICBPER:  Nombre de tributo ICBPER por item
                detalle = detalle + "" + "|"; //Tributo ICBPER: Código de tipo de tributo ICBPER por Item
                detalle = detalle + "0.0" + "|"; //Tributo ICBPER: Monto de tributo ICBPER por Unidad
                //------------------------------------------------------------------
                double precioUnitario = (cpd.getValorVenta() + cpd.getIgv()) / cpd.getCantidad();
                precioUnitario = Math.round(precioUnitario * Math.pow(10, 2)) / Math.pow(10, 2);
                detalle = detalle + String.valueOf(precioUnitario) + "|"; //Precio de venta unitario cac:InvoiceLine/cac:PricingReference/cac:AlternativeConditionPrice
                detalle = detalle + String.valueOf(cpd.getValorVenta()) + "|"; //Valor de venta por Item cac:InvoiceLine/cbc:LineExtensionAmount
                detalle = detalle + "0.0" + "|"; //Valor REFERENCIAL unitario (gratuitos) cac:InvoiceLine/cac:PricingReference/cac:AlternativeConditionPrice
                wr1001.write(detalle + "\r\n");
            }
            wr1001.close();
            bw1001.close();
            //-----------------------TRIBUTOS GENERALES-------------------------
//            f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".TRI");
            f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".TRI");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            List<Object> lTributos = dpBO.ObtenerTributosGenerales(documento.getIdDocumentoPago());
            for (Object pobj : lTributos) {
                String tributos = "";
                Object[] obj = (Object[]) pobj;
                tributos = tributos + obj[0] + "|";
                tributos = tributos + obj[1] + "|";
                tributos = tributos + obj[2] + "|";
                double montoBase = Math.round(((double) obj[3]) * Math.pow(10, 2)) / Math.pow(10, 2);
                double montoTributo = Math.round(((double) obj[4]) * Math.pow(10, 2)) / Math.pow(10, 2);
                tributos = tributos + String.valueOf(montoBase) + "|";
                tributos = tributos + String.valueOf(montoTributo) + "|";
                wr1001.write(tributos + "\r\n");
            }
            wr1001.close();
            bw1001.close();
            //---------------------------------LEYENDA------------------------------
//            f1001 = new File("\\\\192.168.1.67\\DATA\\" + nombreArchivo + ".LEY");
            f1001 = new File(sBO.getDIR_FACTURADOR() + nombreArchivo + ".LEY");
            w1001 = new FileWriter(f1001);
            bw1001 = new BufferedWriter(w1001);
            wr1001 = new PrintWriter(bw1001);
            String leyenda = "";
            leyenda = leyenda + "1000" + "|";
            leyenda = leyenda + NumeroLetras.convertirNumerosALetras(String.valueOf(totalPrecioVenta), documento.getMoneda()).replace("/", "'/'");
            wr1001.write(leyenda + "\r\n");
            if (!Objects.isNull(documento.getTieneDetraccion())) {
                if (documento.getTieneDetraccion().equals("S")) {
                    wr1001.write("2006|Operación sujeta a detracción" + "\r\n");
                }
            }
            wr1001.close();
            bw1001.close();
            if (documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() != null) {
                if (documento.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
                    this.generacionArchivoFormaPago(documento, lDetalle, w1001, bw1001, wr1001, f1001, nombreArchivo, documento.getFormaPagoSunat(), totalPrecioVenta);
                    this.generacionArchivoDetalleFormaPagoCredito(documento, lDetalle, w1001, bw1001, wr1001, f1001, nombreArchivo, documento.getFormaPagoSunat(), documento.getFechaVencimientoSunat(), totalPrecioVenta);
                }
            }
            this.generarArchivoACA(nombreArchivo, documento, lDetalle);
        } catch (IOException e) {
        }
    }

    @Override
    public void GenerarArchivosElectronico_ComprobantePago(DocumentoPago dp) {
        if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
            if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
//                this.GenerarArchivoElectronicoSUNAT(dp);
                this.GenerarArchivoElectronicoSUNAT_TXT(dp);
            }
        }
    }

    @Override
    public void GenerarArchivoElectronico_Anulacion_ComprobantePago(DocumentoPago dp) {
        if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico() != null) {
            if (dp.getTipoDocSerie().getTipoDocPago().getEsElectronico().equals("S")) {
                if (dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago() != null) {
                    if (dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("01")) {
                        this.GenerarArchivoElectronico_ComunicacionBaja_SUNAT(dp);
                    } else {
                        if (dp.getTipoDocSerie().getTipoDocPago().getCodigoDocPago().equals("03")) {
//                            this.GenerarArchivoElectronico_ResumenDiario_SUNAT(dp);
                            this.GenerarArchivoElectronico_ResumenDiario_SUNAT_TXT(dp);
                        }
                    }
                }
            }
        }
    }

}
