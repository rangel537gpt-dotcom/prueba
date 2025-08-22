/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.TipoDocPago;
import java.util.List;

/**
 *
 * @author user
 */
public interface TipoDocPagoBOIFace {
    public List BuscarTipoDocumentoPago(String busqueda, int tipoBusqueda);
    public void GuardarTipoDocumentoPago(TipoDocPago doc, String codigoDocPago);
    public TipoDocPago ObtenerTipoDocPago(String codigoTipoDoc);
    public void ActualizarTipoDocumentoPago(TipoDocPago tdp, String codigo);
    public List ObtenerSeries(int idTipoDocumento);
    public List ObtenerTodosTipoDocPago();
    
}
