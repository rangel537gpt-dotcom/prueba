/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.TipoDocPago;
import java.util.List;

/**
 *
 * @author user
 */
public interface TipoDocPagoDAOIFace {
    public List BuscarTipoDocPagoPorNombre(String busqueda);
    public List BuscarTipoDocPagoPorCodigo(String busqueda);
    public void GuardarTipoDocPago(TipoDocPago tipoDoc);
    public void ActualizarTipoDocPago(TipoDocPago docPago);
    public List ObtenerSeries(int idTipoDocumento);
    public List ObtenerTodosTipoDocPago();
    
}
