/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.Nota;
import caja.mapeo.entidades.NotaDetalle;
import java.util.Date;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface NotaBOIFace {
    public List BuscarTodasNotasCredito();
    public boolean GuardarNota(Nota nc);
    public boolean ActualizarNota(Nota nc);
    public boolean GuardarNotaDetalle(NotaDetalle ncd);
    public boolean ActualizarNotaDetalle(NotaDetalle ncd);
    public boolean GenerarNumeracionElectronicaNota(Nota nc);
    public boolean EliminarNotaDetalle(NotaDetalle ncd);
    public List BuscarNota_SegunFiltro(String filtro);
    public List BuscarNota_SegunNroDocIdentidad(String doc);
    public List BuscarNota_SegunNroNota(int nro);
    public List BuscarNota_SegunFecha(Date d, Date h);
    public List ObtenerDetalleNota(int idNC);
    public double ObtenerSumaTotalValorVenta(int idNota);
    public double ObtenerSumaTotalIGV(int idNota);
    public double ObtenerSumaTotalPrecioVenta(int idNota);
    public Nota ObtenerNotaCredito(int idTipoNota, String nroSerie, int nro);
    public boolean GuardarNotaElectronica(Nota nc);
    public List BuscarNota_AplicadasComprobante(int idComprobante);
    public double ObtenerMontoTotalNotAplicadaComprobante(int idComprobante);
    public List ObtenerTodosTipoNota(int idTipoNota);
    public boolean eliminarCuotaFinanciamientoReincorporacion(DocumentoPago dp);
    public boolean EliminarNota(Nota n);
    public List ObtenerTributosGenerales(int idNC);
    public List ObtenerIdParticipantes_SegunNotaCredito(int idNC);
    public void actualizarBorradoPartipantes(int idParticipante, String estadoBorrado);
    public List ObtenerIdConstancia_SegunNotaCredito(int idNC);
    public void actualizarBorradoConstancias(int idParticipante, String estadoBorrado);
}
