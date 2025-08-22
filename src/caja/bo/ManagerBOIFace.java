/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.CodigoBienServicioDetraccion;
import caja.mapeo.entidades.CodigoMedioPago;
import caja.mapeo.entidades.TipoTributo;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface ManagerBOIFace {
    public List BuscarTipoDocumntoIdentidad(String busqueda, int tipoBusqueda);
    public void GuardarTipoDocumntoIdentidad(TipoTributo tdi, String codigoTipoDocIde);
    public TipoTributo ObtenerTipoDocumntoIdentidad(String codigoTipoDocIde);
    public void ActualizarTipoDocumntoIdentidad(TipoTributo tdi, String codigo);
    public List ObtenerTodosCodigoBienServicioDetraccion();
    public List ObtenerAfectaciones(int idTipoTributo);
    public List ObtenerTodosCodigoMedioPago();
    public CodigoMedioPago obtenerCodigoMedioPago(String codigo);
    public CodigoBienServicioDetraccion obtenerCodigoBienServicioDetraccion(String codigo);
    public List ObtenerUniversidad_SegunFiltro(String filtro);
    public boolean GuardarObjeto(Object e);
    public boolean ActualizarObjeto(Object e);
}
