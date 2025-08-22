/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.TipoTributo;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface TipoTributoBOIFace {
    public List BuscarTipoDocumntoIdentidad(String busqueda, int tipoBusqueda);
    public void GuardarTipoDocumntoIdentidad(TipoTributo tdi, String codigoTipoDocIde);
    public TipoTributo ObtenerTipoDocumntoIdentidad(String codigoTipoDocIde);
    public void ActualizarTipoDocumntoIdentidad(TipoTributo tdi, String codigo);
    public List ObtenerTodosTipoTributo();
    public List ObtenerAfectaciones(int idTipoTributo);
}
