/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.TipoDocIdentidad;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface TipoDocIdentidadBOIFace {
    public List BuscarTipoDocumntoIdentidad(String busqueda, int tipoBusqueda);
    public void GuardarTipoDocumntoIdentidad(TipoDocIdentidad tdi, String codigoTipoDocIde);
    public TipoDocIdentidad ObtenerTipoDocumntoIdentidad(String codigoTipoDocIde);
    public void ActualizarTipoDocumntoIdentidad(TipoDocIdentidad tdi, String codigo);
}
