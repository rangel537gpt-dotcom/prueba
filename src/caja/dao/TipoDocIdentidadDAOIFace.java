/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.TipoDocIdentidad;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface TipoDocIdentidadDAOIFace {
    public List BuscarTipoDocIdentidadPorNombre(String busqueda);
    public List BuscarTipoDocIdentidadPorCodigo(String busqueda);
    public List BuscarTipoDocIdentidadPorDescripcion(String busqueda);
    public void GuardarTipoDocIdentidad(TipoDocIdentidad tdi);
    public void ActualizarTipoDocIdentidad(TipoDocIdentidad tdi);
}
