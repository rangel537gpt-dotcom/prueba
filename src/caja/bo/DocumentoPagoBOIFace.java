/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Operacion;
import caja.mapeo.entidades.ResumenDiario;
import caja.mapeo.entidades.ResumenDiarioDetalle;
import caja.mapeo.entidades.TipoDocSerie;
import caja.mapeo.entidades.ValeAcademico;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface DocumentoPagoBOIFace {
    
    public List ObtenerTodosConceptoPago();
    public List ObtenerTodosClientes();
    public List ObtenerTodosCobradores();
    public List ObtenerTodosTipoDocPago();
    public boolean GuardarDocumentoPago(DocumentoPago doc);
    public boolean GuardarDetalleDocumentoPago(DocumentoPagoDet dpd, List<ValeAcademico> lva);
    public List BuscarComprobantePago(String anio,String mes,String dia);
    public Cobrador ObtenerCobradorComprobantePago(int idDocumentoPago);
    public List ObtenerDetalleComprobante(int idDocumentoPago);
    public DocumentoPagoDet ObtenerUltimaCuotaOrdinariaContador(Cliente cliente, String tipoPagador);
    public void HabilitarCliente(int idCliente);
    //public double ObtenerMontoCuotasAPagarCliente(int cantCuotas,List listaCuotasFinanciadas);
    public double ObtenerMontoCuotasAPagarCliente(int cantCuotas, DocumentoPago dp);
    public double ObtenerMontoFinanciamientoAPagarCliente(int cantCuotas, DocumentoPago dp);
    public void InhabilitarCliente(int idCliente);
    public void LimpiarCuotasAPagar();
    public void LimpiarFinanciamientoAPagar();
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc,String nroSerie);
    public boolean EliminarComprobante(DocumentoPago dp);
    public boolean AnularComprobante(DocumentoPago dp);
    public List BuscarNroComprobantePago(int nroComprobante);
    public boolean CerrarCaja(String fecha);
    public int ObtenerSolamenteCorrelativo(int idTipoDocSerie);
    public double ObtenerMontoReincorporacionAPagarCliente(int cantCuotas, DocumentoPago dp);
    public boolean EstaCerradoElDia(String fecha);
    public void ResetearListaCuotas();
    public double ObtenerMontoCuotasAPagarClienteEmpresa(Cliente cliente, int cantCuotas);
    public double ObtenerMontoReincorporacionAPagarClienteEmpresa(Cliente cliente, int cantCuotas);
    public double ObtenerMontoFinanciamientoAPagarClienteEmpresa(Cliente cliente, int cantCuotas);
    public void ActualizarCabeceraDocumento(DocumentoPago doc);
    public DocumentoPago ObtenerDocumentoPago(int idDocPago);
    public List ObtenerVentas(String desde, String hasta);
    public List ObtenerComprobantesAnioMes(int anio, int mes);
    public List BuscarContador(String busqueda, int tipoBusqueda);
    //public DocumentoPago ObtenerContador(String estado);
    public DocumentoPago ObtenerContador(int numero);
    public double ObtenerMontoTotalComprobante(int idDocumentoPago);
    public List ObtenerComprobantesDesdeHasta(String fechaDesde, String fechaHasta);
    public Operacion verificarExisteNroOperacion(String nroCuenta, String nroOperacion);
    public boolean VerificarNroComprobanteExiste(DocumentoPago doc);
    public boolean GuardarDocumentoPago_CualquierNro(DocumentoPago doc);
    public int ObtenerCorrelativoComprobante(DocumentoPago doc);
    public List ObtenerTodosClientes_Recarga();
    public boolean VerificarNroComprobanteExiste(int idTipoDoc, int nroDoc, String nroSerie);
    public List ObtenerDeudaNoFinanciadas(int idCliente);
    public boolean GuardarDocumentoPago_SinValidacion(DocumentoPago doc);
    public boolean GuardarDocumentoPagoDet_SinValidadcion(DocumentoPagoDet det);
    public List ObtenerDocumentosPagos_SegunListado(List l);
    public void AgregarClientesListadoLocal(Cliente c);
    public boolean GenerarNumeracionElectronicaComprobante(DocumentoPago cp);
    public double ObtenerSumaTotalValorVenta(int id);
    public double ObtenerSumaTotalPrecioVenta(int id);
    public List ObtenerTodasSeries_SegunTipoDocumento(int id);
    public int ObtenerSolamenteCorrelativo_Metodo2(int idTipoDocSerie);
    public List ObtenerComprobantes_SegunFiltro(String filtro);
    public double ObtenerSumaTotalIGV(int id);
    public boolean EsFactibleEliminarCuota(DocumentoPago dp);
    public int EsFactibleEliminarFinanciamiento(DocumentoPago dp);
    public int EsFactibleEliminarReincorporacion(DocumentoPago dp);
    public void ValidarHabilitacion(DocumentoPago dp);
    public void GenerarArchivosElectronico_ComprobantePago(DocumentoPago dp);
    public List ObtenerTributos_TotalesValorVenta(int id);
    public void GenerarArchivoElectronico_Anulacion_ComprobantePago(DocumentoPago dp);
    public int ObtenerCorrelativoAnulacion(DocumentoPago doc, int anio, int mes, int dia);
    public boolean ComprobanteTieneCurso(int id);
    public List ObtenerTodosClientesNuevamente();
    public DocumentoPago ObtenerComprobantePago_SegunTipoDocumentoSerieNro(int idDocumento, String nroSerie, int nroDocumento);
    public int obtenerIDUsuario_SegunIDCobrador(int idCobrador);
    public List ObtenerTributosGenerales(int idDocumentoPago);
    public boolean GuardarResumenDiario(ResumenDiario r, List<ResumenDiarioDetalle> l);
    public List ObtenerResumenDiarioDetalle(int id);
    public List BuscarResumenDiario_SegunNroNota(int nro);
    public List BuscarResumenDiario_SegunFecha(Date desde, Date hasta);
    public List ObtenerDocumentoPagoDesdeHasta(String fechaDesde, String fechaHasta);
    public String ObtenerPeriodoCancelado(int idDocumentoPago);
    public double ObtenerIgv(int idDocumentoPago);
    public boolean guardarObjeto(Object obj);
    public boolean ActualizarObjeto(Object obj);
    public List buscarValeAcademico(String filtro);
    public List obtenerValeAcademicoConsumoDetalle(int idValeAcademico);
    public List obtenerValeAcademicoConsumoDetalleParticipante(int idValeAcademicoConsumo);
    public void exportarValeAcademico(List<ValeAcademico> lValeAcademico);
    public void exportarValeAcademicoDetalle(List<ValeAcademico> lValeAcademico);
    public List buscarValeAcademicoDetalle(String filtro);
    public void exportarDerechoHabientes();
    public void generacionEstadoCuenta();
    public byte[] generarExcelReporteVentasTipoEvento(String desde, String hasta);
}
