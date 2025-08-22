/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;
import caja.mapeo.entidades.Bbva;
import caja.mapeo.entidades.BbvaContador;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.CuentaCorriente;
import caja.mapeo.entidades.BbvaContadorDetalle;
import caja.mapeo.entidades.BbvaRetorno;
import caja.mapeo.entidades.BbvaRetornoDetalle;
import caja.mapeo.entidades.BbvaRetornoDetalleDocumento;
import caja.mapeo.entidades.ClienteDerechoHabiente;
//import caja.mapeo.entidades.Cuotas;
import caja.mapeo.entidades.EstructuraPagoCajaArequipa;
import caja.mapeo.entidades.FichaDatos;
import caja.mapeo.entidades.Scotiabank;
import caja.mapeo.entidades.ScotiabankContador;
import caja.mapeo.entidades.ScotiabankContadorDetalle;
import caja.mapeo.entidades.ScotiabankRetorno;
import caja.mapeo.entidades.ScotiabankRetornoDetalle;
import caja.mapeo.entidades.ScotiabankRetornoDetalleDocumento;
import java.util.Date;
import java.util.List;
import javax.swing.JProgressBar;

/**
 *
 * @author juan carlos
 */
public interface ClienteBOIFace {
    public List ObtenerHistorialPagos(int idContador);
//    public void ValidarHabilitacion(Cliente cliente);
    public void ValidarHabilitacionTodosMiembros();
    public void InsertarCuentaCorrienteCliente(CuentaCorriente cuentaCorriente);
    public List ObtenerTodosContadores();
    public List ObtenerTodasSociedades();
    public Cliente ObtenerClienteSegunCodigoContador(String codigo);
    public List ObtenerTodosClientes();
    public void GenerarEstructuraDatosCobranzaInstitucional(String tipoMovimiento);
    public boolean GuardarEstructuraCajaArequipa(EstructuraPagoCajaArequipa e);
    public void GenerarEstructuraDatosCobranzaInstitucional_BBVA(JProgressBar pb, String tipoMovimiento, int nroOrdenDesde, int nroOrdenHasta, String tipoIdentificacion);
    public List ObtenerContadores_BBVA(int nroOrdenDesde, int nroOrdenHasta);
    public boolean GuardarBbvaContadorDetalle(BbvaContadorDetalle e);
    public boolean GuardarBbva(Bbva e);
    public boolean GuardarBbvaContador(BbvaContador e);
    public Bbva ObtenerBbva(int nroOp);
    public List ObtenerBbvaContador_SegunOperacion(int nroOp);
    public List ObtenerBbvaOperaciones(int anio, int mes);
    public List ObtenerBbvaContadorDetalle(int idBbvaContador);
    public void GenerarEstructuraDatosCobranzaInstitucional_Scotiabank(JProgressBar pb, int nroOrdenDesde, int nroOrdenHasta);
    public boolean GuardarScotiabank(Scotiabank e);
    public boolean GuardarScotiabankContadorDetalle(ScotiabankContadorDetalle e);
    public boolean GuardarScotiabankContador(ScotiabankContador e);
    public List ObtenerScotiabankOperaciones(int anio, int mes);
    public Scotiabank ObtenerScotiabank(int nroOp);
    public List ObtenerScotiabankContador_SegunOperacion(int nroOp);
    public List ObtenerScotiabankContadorDetalle(int idBbvaContador);
    public boolean GuardarBbvaRetorno(BbvaRetorno e);
    public boolean GuardarBbvaRetornoDetalle(BbvaRetornoDetalle e);
    public List ObtenerBbvaContadorDetalle_SegunIdentificadoraPagoIdBbva(int idBbva, String identificador);
    public List ObtenerBbvaRetornoDetalle_SegunIdBbvaRetorno(int idRetorno);
    public void GenerarComprobantePago_SegunBbvaRetorno(Date fechaPago, int idTipoDocPago, String nroSerie, int bbvaRetorno, Cliente cliente);
    public List ObtenerBbvaRetorno_SegunIdBbva(int idBbva);
    public List ObtenerBbvaRetornoDetalle(int idRetorno);
    public void GenerarComprobantePagoBbva(Date fecha, int idTipoDoc, String nroSerie, int idBbvaRetorno);
    public boolean ActualizarBbvaRetorno(BbvaRetorno e);
    public List ObtenerBbvaRetornoDetalle_ParaValidacion(int idRetorno);
    public List ObtenerBbvaTodasOperaciones();
    public List ObtenerBbvaRetornoSoloClientes_SegunIdBbvaRetorno(int idRetorno);
    public boolean GuardarBbvaRetornoDetalleDocumento(BbvaRetornoDetalleDocumento e);
    public Bbva ObtenerUltimoBbvaGenerado();
    public List ObtenerComprobantePago_SegunBbvaRetorno(int idRetorno) ;
    public void ImprimirDocumentoPago_SegunBbva(int idBbvaRetorno);
    public void GenerarComprobantePagoScotiabank(Date fecha, int idTipoDoc, String nroSerie, int idScotiabankRetorno);
    public List ObtenerScotiabankRetornoSoloClientes_SegunIdScotiabankRetorno(int idRetorno);
    public void GenerarComprobantePago_SegunScotiabankRetorno(Date fecha, int idTipoDocPago, String nroSerie, int scotiabankRetorno, Cliente cliente);
    public boolean GuardarScotiabankRetornoDetalleDocumento(ScotiabankRetornoDetalleDocumento e);
    public boolean ActualizarScotiabankRetorno(ScotiabankRetorno e);
    public List ObtenerComprobantePago_SegunScotiabankRetorno(int idRetorno);
    public void ImprimirDocumentoPago_SegunScotiabank(int idScotiabankRetorno);
    public boolean GuardarScotiabankRetornoDetalle(ScotiabankRetornoDetalle e);
    public boolean GuardarScotiabankRetorno(ScotiabankRetorno e);
    public List ObtenerScotiabankRetorno_SegunIdScotiabank(int idBbva);
    public List ObtenerScotiabankRetornoDetalle(int idRetorno);
    public List ObtenerScotiabankOperaciones();
    public Scotiabank ObtenerUltimoScotiabankGenerado();
    public List ObtenerScotiabankRetornoDetalle_ParaValidacion(int idRetorno);
    public List ObtenerCursos(Integer idColegiado);
    public List ObtenerClienteCertificado_SegunCliente(int idCliente);
    public void ValidarHabilitacionColegiado(Cliente c);
    public List ObtenerContadoresSegunCobrador(String codContador);
    public boolean GuardarObjeto(Object e);
    public boolean ActualizarObjeto(Object e);
    public List ObtenerClienteDerechoHabiente_SegunCliente(int idCliente);
    public List ObtenerClienteAuditorIndependiente_SegunCliente(int idCliente);
    public List ObtenerClienteComitePerito_SegunCliente(int idCliente);
    public FichaDatos obtenerFicha(Integer nro);
    public List ObtenerFichaComiteDesearia_SegunFicha(int idFicha);
    public List ObtenerFichaDeporte_SegunFicha(int idFicha);
    public List ObtenerFichaInstitucion_SegunFicha(int idFicha);
    public List obtenerUniversidades();
}
