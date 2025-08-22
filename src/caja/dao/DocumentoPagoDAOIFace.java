/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.DocPagoAnulado;
import caja.mapeo.entidades.DocPagoAnuladoDetalle;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.Operacion;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import caja.mapeo.entidades.TipoDocSerie;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface DocumentoPagoDAOIFace {
    
    public List ObtenerTodosConceptoPago();
    public List ObtenerTodosClientes();
    public List ObtenerTodosCobradores();
    public List ObtenerTodosTipoDocPago();
    //public List ObtenerTodasSociedades();
    public int ObtenerCorrelativo(int idTipoDocSerie);
    public void GuardarDocumentoPago(DocumentoPago doc);
    public void GuardarDocumentoPagoDet(DocumentoPagoDet dpd);
    public List BuscarComprobantePagoPorAnio(int anio);
    public List BuscarComprobantePagoPorAnioMes(int anio,int mes);
    public List BuscarComprobantePagoPorAnioMesDia(int anio,int mes,int dia);
    public double obtenerMontoTotalComprobante(int idDocumentoPago);
    public Cobrador ObtenerCobradorComprobantePago(int idDocumentoPago);
    public List ObtenerDetalleComprobante(int idDocumentoPago);
    public DocumentoPagoDet ObtenerUltimaCuotaOrdinariaContador(int idContador);
    public void CulminarComprobante(int idDocumentoPago);
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc, String nroSerie);
    public void EliminarDetalleComprobante(int idDocumentoPago);
    public List ObtenerDetalleComprobanteElmininar(int idDocumentoPago);
    public void EliminarComprobante(int idDocumentoPago);
    public void GuardarDocumentoAnulado(DocPagoAnulado dpa);
    public void GuardarDocumentoAnuladoDetalle(DocPagoAnuladoDetalle dpad);
    public AnioMes ObtenerAnioMesCuotaDocumentoPago(int idDocumentoPago);
    public AnioMes ObtenerAnioMesUltimaCuota(int idCliente, int idDocumentoPago);
    public void EliminarCuentasCorrientes(int idCliente, int idDocumentoPago);
    public void EliminarCuotasCliente(int idCliente, int idDocumentoPago);
    public FinanciamientoDocumentoPago ObtenerFinanciamientoDocumentoPago(int idDocumentoPago);
    public FinanciamientoDocumentoPago ObtenerUltimoFinanciamiento(int idCliente, int idFinanciamiento);
    public void EliminarFinanciamiento(int idFinanciamientoDocumentoPago);
    public void AnularComprobante(int idFinanciamientoDocumentoPago);
    public boolean VerificarSiNroDocEstaOcupado(DocumentoPago doc);
    public boolean VerificarSiNroExcedeCorrelativo(DocumentoPago doc);
    public List BuscarNroComprobantePago(int nroComprobante);
    public boolean CerrarCaja(String fecha);
    public int ObtenerSolomenteCorrelativo(int idTipoDocSerie);
    public void ActualizarDocumentoPagoDet(DocumentoPagoDet dpd);
    public ReincorporacionDocumentoPago ObtenerReincorporacionDocumentoPago(int idDocumentoPago);
    public void EliminarReincorporacion(int idReincorporacionDocumentoPago);
    public ReincorporacionDocumentoPago ObtenerUltimoReincorporacion(int idCliente);
    public boolean EstaCerradoElDia(String fecha);
    public Financiamiento ObtenerCabeceraUltimoFinanciamiento(int idCliente);
    public void EliminarTodoFinanciamiento(int idFinanciamientoDetalle);
    public FinanciamientoDocumentoPago ObtenerFinanciamientoDocumentoPagoDet(int idDocumentoPagoDet);
    public void ActualizarCabeceraDocumento(DocumentoPago doc);
    public DocumentoPago ObtenerDocumentoPago(int idDocPago);
    public void AnularDetalle(int idDocumentoPago, DocumentoPagoDet det);
    public List ObtenerVentas(String desde, String hasta);
    public List ObtenerComprobantesAnioMes(int anio, int mes);
    
    public List BuscarContadorPorNombre(String busqueda);
    //public List BuscarContadorPorEstado(String busqueda);
    public List BuscarContadorPorNumero(String busqueda);
    public double ObtenerMontoTotalComprobante(int idDocumentoPago);
    public List ObtenerComprobantesDesdeHasta(String fechaDesde, String fechaHasta);
    public Operacion verificarExisteNroOperacion(String nroCuenta, String nroOperacion);
    public boolean VerificarSiNroDocEstaOcupado(int idTipoDoc, int nroDoc, String nroSerie);
    public List ObtenerDeudaNoFinanciadas(int idCliente);
    public int ObtenerUltimoNroComprobante(int idTipoDoc, String nroSerie);
    public List ObtenerDocumentosPagos_SegunListado(List l);
    public void GenerarNumeracionElectronicaComprobante(DocumentoPago cp);
    public double ObtenerSumaTotalValorVenta(int id);
    public double ObtenerSumaTotalPrecioVenta(int id);
    public List ObtenerTodasSeries_SegunTipoDocumento(int idTipoDocumentoPago);
    public int ObtenerSolamenteCorrelativo_Metodo2(int idTipoDocSerie);
    public List ObtenerComprobantes_SegunFiltro(String filtro);
    public double ObtenerSumaTotalIGV(int id);
    public List ObtenerTributos_TotalesValorVenta(int id);
    public int ObtenerCorrelativoAnulacion(int idTipoDoc, int anio, int mes, int dia);
    public boolean ComprobanteTieneCurso(int id);
    public void GuardarOperacion(Operacion o);
    public DocumentoPago ObtenerComprobantePago_SegunTipoDocumentoSerieNro(int idTipoDocumento, String nroSerie, int nroDocumento);
    public int obtenerIDUsuario_SegunIDCobrador(int idCobrador);
    public List ObtenerTributosGenerales(int idDocumentoPago);
    public void GuardarObjeto(Object doc);
    public void ActualizarObjeto(Object doc);
    public int ObtenerCorrelativoResumenDiario(String fecha);
    public List ObtenerResumenDiarioDetalle(int id);
    public List BuscarResumenDiario_SegunNroNota(int nro);
    public List BuscarResumenDiario_SegunFecha(Date d, Date h);
    public List ObtenerDocumentoPagoDesdeHasta(String fechaDesde, String fechaHasta);
    public String ObtenerPeriodoCancelado(int idDocumentoPago);
    public double ObtenerIgv(int idDocumentoPago);
    public List buscarValeAcademico(String filtro);
    public List obtenerValeAcademicoConsumoDetalle(int idValeAcademico);
    public int ObtenerUltimoNroValeAcademico();
    public List obtenerValeAcademicoConsumoDetalleParticipante(int idValeAcademicoConsumo);
    public List buscarValeAcademicoDetalle(String filtro);
    public List obtenerTodosDerechoHabiente();
    public int ObtenerCorrelativoConstanciaHabilitacion();
    public int ObtenerCorrelativoConstanciaHabilitacionSociedad();
    
}
