/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Bbva;
import caja.mapeo.entidades.BbvaContador;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.CuentaCorriente;
import caja.mapeo.entidades.BbvaContadorDetalle;
import caja.mapeo.entidades.BbvaRetorno;
import caja.mapeo.entidades.BbvaRetornoDetalle;
import caja.mapeo.entidades.BbvaRetornoDetalleDocumento;
import caja.mapeo.entidades.EstructuraPagoCajaArequipa;
import caja.mapeo.entidades.FichaDatos;
import caja.mapeo.entidades.Scotiabank;
import caja.mapeo.entidades.ScotiabankContador;
import caja.mapeo.entidades.ScotiabankContadorDetalle;
import caja.mapeo.entidades.ScotiabankRetorno;
import caja.mapeo.entidades.ScotiabankRetornoDetalle;
import caja.mapeo.entidades.ScotiabankRetornoDetalleDocumento;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface ClienteDAOIFace {
    /*public List BuscarContadorPorApePat(String busqueda);
    public List BuscarContadorPorApeMat(String busqueda);
    public List BuscarContadorPorNombre(String busqueda);
    public List BuscarContadorPorDNI(String busqueda);*/
    public List BuscarContadorPorCodigoCole(String busqueda);
    public void GuardarCliente(Cliente cliente);
    public List ObtenerTodosClientes();
    public List ObtenerHistorialPagos(int idContador);
    public void ObtenerCalculoFinanciamientoContador(int idContador);
    public boolean HabilitarCliente(int idCliente);
    public boolean InhabilitarCliente(int idCliente);
    public void InsertarCuentaCorrienteCliente(CuentaCorriente cuentaCorriente);
    public List ObtenerTodosContadores();
    public List ObtenerTodasSociedas();
    public Cliente ObtenerClienteSegunCodigoContador(String codigo);
    public void GuardarEstructuraCajaArequipa(EstructuraPagoCajaArequipa est);
    public int obtenerNroOperacion_CajaArequipa();
    public List ObtenerListado_CajaArequipa(int nroOperacion);
    public List ObtenerListado_BBVA(int nroOperacion);
    public List ObtenerContadores_BBVA(int nroOrdenDesde, int nroOrdenHasta);
    public void GuardarBbvaContadorDetalle(BbvaContadorDetalle est);
    public int obtenerNroOperacion_BBVA();
    public void GuardarBbvaContador(BbvaContador est);
    public void GuardarBbva(Bbva est);
    public List ObtenerBbvaContador_SegunOperacion(int nroOp);
    public Bbva ObtenerBbva(int nroOp);
    public List ObtenerBbvaOperaciones(int anio, int mes);
    public int obtenerVersion_BBVA(String fecha);
    public List ObtenerBbvaContadorDetalle(int id);
    public void GuardarScotiabank(Scotiabank est);
    public void GuardarScotiabankContador(ScotiabankContador est);
    public void GuardarScotiabankContadorDetalle(ScotiabankContadorDetalle est);
    public List ObtenerScotiabankOperaciones(int anio, int mes);
    public Scotiabank ObtenerScotiabank(int nroOp);
    public List ObtenerListado_Scotiabank(int nroOperacion);
    public List ObtenerScotiabankContador_SegunOperacion(int nroOp);
    public List ObtenerScotiabankContadorDetalle(int id);
    public int obtenerNroOperacion_SCOTIABANK();
    public int ObtenerCorrelativoScotiabank_SegunIdScotiabank(int id);
    public int ObtenerCorrelativoScotiabank_SegunIdScotiabank_AnioMes(int idSC, int idAnioMes);
    public void actualizarCorrelativoScotiabank(int nroOperacion);
    public double obtenerMontoTotal_SegunNroOperacionScotiabank(int nroOperacion);
    public void GuardarBbvaRetornoDetalle(BbvaRetornoDetalle est);
    public void GuardarBbvaRetorno(BbvaRetorno est);
    public List ObtenerBbvaContadorDetalle_SegunIdentificadoraPagoIdBbva(int idBbva, String identificador);
    public List ObtenerBbvaRetornoDetalle_SegunIdBbvaRetorno(int idRetorno);
    public List ObtenerBbvaRetornoDetalle_SegunClienteFecha(int idBbva, int idCliente, String fecha);
    public List ObtenerBbvaRetorno_SegunIdBbva(int idBbva);
    public List ObtenerBbvaRetornoDetalle(int idRetorno);
    public void ActualizarBbvaRetorno(BbvaRetorno est);
    public List ObtenerPagosNoPertenecen_Bbva(int idRetorno);
    public List ObtenerMontosDiferentes_Bbva(int idBbva, int idRetorno);
    public List ObtenerBbvaRetornoDetalle_ParaValidacion(int idRetorno);
    public List ObtenerBbvaTodasOperaciones();
    public List ObtenerBbvaRetornoDetalle_SegunCliente(int idBbva, int idCliente);
    public List ObtenerBbvaRetornoSoloClientes_SegunIdBbvaRetorno(int idRetorno);
    public void GuardarBbvaRetornoDetalleDocumento(BbvaRetornoDetalleDocumento est);
    public Bbva ObtenerUltimoBbvaGenerado();
    public List ObtenerComprobantePago_SegunBbvaRetorno(int idRetorno) ;
    public List ObtenerScotiabankRetornoSoloClientes_SegunIdScotiabankRetorno(int idRetorno);
    public List ObtenerScotiabankRetornoDetalle_SegunCliente(int idScotiabank, int idCliente);
    public List ObtenerScotiabankContadorDetalle_SegunIdentificadoraPagoIdScotiabank(int idScotiabank, String identificador);
    public void GuardarScotiabankRetornoDetalleDocumento(ScotiabankRetornoDetalleDocumento est);
    public void ActualizarScotiabankRetorno(ScotiabankRetorno est);
    public List ObtenerComprobantePago_SegunScotiabankRetorno(int idRetorno);
    public List ObtenerMontosDiferentes_Scotiabank(int idScotiabank, int idRetorno);
    public void GuardarScotiabankRetornoDetalle(ScotiabankRetornoDetalle est);
    public void GuardarScotiabankRetorno(ScotiabankRetorno est);
    public List ObtenerScotiabankRetorno_SegunIdScotiabank(int idBbva);
    public List ObtenerScotiabankRetornoDetalle(int idRetorno);
    public List ObtenerScotiabankOperaciones();
    public Scotiabank ObtenerUltimoScotiabankGenerado();
    public List ObtenerPagosNoPertenecen_Scotiabank(int idRetorno);
    public List ObtenerScotiabankRetornoDetalle_ParaValidacion(int idRetorno);
    public List ObtenerCursos(Integer idColegiado);
    public List ObtenerClienteCertificado_SegunCliente(int idCliente);
    public List ObtenerContadoresSegunCobrador(String codigoContador);
    public void ejecutarProcedimientoEstadoTemporal();
    public void GuardarObjeto(Object est);
    public void ActualizarObjeto(Object est);
    public List ObtenerClienteDerechoHabiente_SegunCliente(int idCliente);
    public List ObtenerClienteAuditorIndependiente_SegunCliente(int idCliente);
    public List ObtenerClienteComitePerito_SegunCliente(int idCliente);
    public FichaDatos obtenerFicha(Integer nro);
    public List ObtenerFichaComiteDesearia_SegunFicha(int idFicha);
    public List ObtenerFichaDeporte_SegunFicha(int idFicha);
    public List ObtenerFichaInstitucion_SegunFicha(int idFicha);
    public List obtenerUniversidades();
    
}
