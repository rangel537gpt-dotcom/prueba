/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.CodigoBienServicioDetraccion;
import caja.mapeo.entidades.CodigoMedioPago;
import caja.mapeo.entidades.TipoTributo;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface ManagerDAOIFace {
    public List BuscarTipoTributoPorNombre(String busqueda);
    public List BuscarTipoTributoPorCodigo(String busqueda);
    public List BuscarTipoTributoPorDescripcion(String busqueda);
    public void GuardarTipoTributo(TipoTributo tdi);
    public void ActualizarTipoTributo(TipoTributo tdi);
    public List ObtenerTodosCodigoBienServicioDetraccion();
    public List ObtenerAfectaciones(int idTipoTributo);
    public List ObtenerTodosCodigoMedioPago();
    public CodigoMedioPago obtenerCodigoMedioPago(String codigo);
    public CodigoBienServicioDetraccion obtenerCodigoBienServicioDetraccion(String codigo);
    public List ObtenerUniversidad_SegunFiltro(String filtro);
    public void GuardarObjeto(Object est);
    public void ActualizarObjeto(Object est);
}
