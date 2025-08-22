/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.ClienteDAO;
import caja.dao.ManagerDAO;
import caja.dao.TipoTributoDAO;
import caja.mapeo.entidades.CodigoBienServicioDetraccion;
import caja.mapeo.entidades.CodigoMedioPago;
import caja.mapeo.entidades.TipoTributo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class ManagerBO implements ManagerBOIFace {

    private static ManagerBO INSTANCE = new ManagerBO();
    private List<TipoTributo> resultadoBusqueda;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ManagerBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ManagerBO();
                }
            }
        }
    }

    public static ManagerBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public ManagerBO() {
        resultadoBusqueda = new ArrayList();
    }

    public List<TipoTributo> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<TipoTributo> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List BuscarTipoDocumntoIdentidad(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        TipoTributoDAO tdi = TipoTributoDAO.getInstance();
        if (tipoBusqueda == 1) {
            resultadoBusqueda = tdi.BuscarTipoTributoPorNombre(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 2) {
            resultadoBusqueda = tdi.BuscarTipoTributoPorCodigo(busqueda);
            return resultadoBusqueda;
        } else {
            resultadoBusqueda = tdi.BuscarTipoTributoPorDescripcion(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarTipoDocumntoIdentidad(TipoTributo tdi, String codigoTipoDocIde) {
        TipoTributoDAO tipoDocIdentidadDAO = TipoTributoDAO.getInstance();
        tipoDocIdentidadDAO.GuardarTipoTributo(tdi);

//        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
//        List<TipoTributo> lTipoDoc = docPago.ObtenerTodosClientes(); implementar ObtenerTodosTipoTributo
//        if(lTipoDoc != null){
//            lTipoDoc.add(tdi);
    }

    @Override
    public TipoTributo ObtenerTipoDocumntoIdentidad(String codigoTipoDocIde) {
        for (TipoTributo tdi : resultadoBusqueda) {
            /*if(tdi.getCodigoDocIde().equals(codigoTipoDocIde))
           {
               return tdi;
           }*/
        }
        return null;
    }

    @Override
    public CodigoMedioPago obtenerCodigoMedioPago(String codigo) {
        ManagerDAO mgDAO = ManagerDAO.getInstance();
        return mgDAO.obtenerCodigoMedioPago(codigo);
    }

    @Override
    public CodigoBienServicioDetraccion obtenerCodigoBienServicioDetraccion(String codigo) {
        ManagerDAO mgDAO = ManagerDAO.getInstance();
        return mgDAO.obtenerCodigoBienServicioDetraccion(codigo);
    }

    @Override
    public List ObtenerTodosCodigoBienServicioDetraccion() {
        ManagerDAO mgDAO = ManagerDAO.getInstance();
        return mgDAO.ObtenerTodosCodigoBienServicioDetraccion();
    }

    @Override
    public List ObtenerTodosCodigoMedioPago() {
        ManagerDAO mgDAO = ManagerDAO.getInstance();
        return mgDAO.ObtenerTodosCodigoMedioPago();
    }

    @Override
    public List ObtenerAfectaciones(int idTipoTributo) {
        TipoTributoDAO tipoDocIdentidadDAO = TipoTributoDAO.getInstance();
        return tipoDocIdentidadDAO.ObtenerAfectaciones(idTipoTributo);
    }

    @Override
    public void ActualizarTipoDocumntoIdentidad(TipoTributo tdi, String codigo) {
        TipoTributoDAO tipoDocIdentidadDAO = TipoTributoDAO.getInstance();
        tipoDocIdentidadDAO.ActualizarTipoTributo(tdi);
    }

    @Override
    public List ObtenerUniversidad_SegunFiltro(String filtro) {
        ManagerDAO mgDAO = ManagerDAO.getInstance();
        return mgDAO.ObtenerUniversidad_SegunFiltro(filtro);
    }
    
    @Override
    public boolean GuardarObjeto(Object e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.GuardarObjeto(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ActualizarObjeto(Object e) {
        try {
            ClienteDAO gaDAO = ClienteDAO.getInstance();
            gaDAO.ActualizarObjeto(e);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
}
