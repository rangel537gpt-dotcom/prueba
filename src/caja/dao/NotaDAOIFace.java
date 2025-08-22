/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;


import caja.mapeo.entidades.Nota;
import caja.mapeo.entidades.NotaDetalle;
import java.util.Date;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface NotaDAOIFace {
    public List BuscarTodasNotasCredito();
    public void GuardarNota(Nota nc);
//    public List BuscarUnidad_SegunNombre(String nombre);
    public void ActualizarNota(Nota nc);
    public void ActualizarNotaDetalle(NotaDetalle ncd);
    public void GuardarNotaDetalle(NotaDetalle ncd);
    public void GenerarNumeracionElectronicaNota(Nota nc);
    public int ObtenerNroComprobanteCorrelativoProvisional(String serie);
    public void EliminarNotaDetalle(NotaDetalle ncd);
    public List BuscarNota_SegunFecha(Date d, Date h);
    public List BuscarNota_SegunNroNota(int nro);
    public List BuscarNota_SegunNroDocIdentidad(String doc);
    public List BuscarNota_SegunFiltro(String filtro);
    public List ObtenerDetalleNota(int idNC);
    public Nota ObtenerNotaCredito(int idTipoNota, String nroSerie, int nro);
    public void GuardarNotaElectronica(Nota nc);
    public List BuscarNota_AplicadasComprobante(int idComprobante);
    public double ObtenerMontoTotalNotAplicadaComprobante(int idNota);
    public List ObtenerTodosTipoNota(int idTipoNota);
    public double ObtenerSumaTotalIGV(int idNC);
    public double ObtenerSumaTotalPrecioVenta(int idNC);
    public double ObtenerSumaTotalValorVenta(int idNC);
    public void EliminarDetalleNota(int idNota);
    public void EliminarNota(Nota n);
    public List ObtenerTributosGenerales(int idNC);
    public List ObtenerIdParticipantes_SegunNotaCredito(int idNota);
    public void actualizarBorradoPartipantes(int idParticipante, String estadoBorrado);
    public List ObtenerIdConstancia_SegunNotaCredito(int idNota);
    public void actualizarBorradoConstancias(int idConstancia, String estadoBorrado);
}
