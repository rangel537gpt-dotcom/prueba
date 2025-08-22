/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.TipoDocIdentidadDAO;
import caja.mapeo.entidades.TipoDocIdentidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class TipoDocIdentidadBO implements TipoDocIdentidadBOIFace{
    
    private static TipoDocIdentidadBO INSTANCE = new TipoDocIdentidadBO();
    private List<TipoDocIdentidad> resultadoBusqueda;
    
    
    public static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized (TipoDocIdentidadBO.class)
            {
                if(INSTANCE == null)
                    INSTANCE = new TipoDocIdentidadBO();
            }
        }
    }

    
    public static TipoDocIdentidadBO getInstance()
    {
        createInstance();
        return INSTANCE;
    }
    
    public TipoDocIdentidadBO()
    {
        resultadoBusqueda = new ArrayList();
    }
    
    
    public List<TipoDocIdentidad> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<TipoDocIdentidad> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List BuscarTipoDocumntoIdentidad(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        TipoDocIdentidadDAO tdi = TipoDocIdentidadDAO.getInstance();
        if(tipoBusqueda == 1)
        {
            resultadoBusqueda = tdi.BuscarTipoDocIdentidadPorNombre(busqueda);
            return resultadoBusqueda;
        }
        else if(tipoBusqueda == 2)
        {
            resultadoBusqueda = tdi.BuscarTipoDocIdentidadPorCodigo(busqueda);
            return resultadoBusqueda;
        }
        
        else
        {
            resultadoBusqueda = tdi.BuscarTipoDocIdentidadPorDescripcion(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarTipoDocumntoIdentidad(TipoDocIdentidad tdi, String codigoTipoDocIde) {
        TipoDocIdentidadDAO tipoDocIdentidadDAO = TipoDocIdentidadDAO.getInstance();
        tipoDocIdentidadDAO.GuardarTipoDocIdentidad(tdi);
        
//        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
//        List<TipoDocIdentidad> lTipoDoc = docPago.ObtenerTodosClientes(); implementar ObtenerTodosTipoDocIdentidad
//        if(lTipoDoc != null){
//            lTipoDoc.add(tdi);
        }
    

    @Override
    public TipoDocIdentidad ObtenerTipoDocumntoIdentidad(String codigoTipoDocIde) {
        for(TipoDocIdentidad tdi : resultadoBusqueda)
        {
           /*if(tdi.getCodigoDocIde().equals(codigoTipoDocIde))
           {
               return tdi;
           }*/
        }
        return null;
    }

    @Override
    public void ActualizarTipoDocumntoIdentidad(TipoDocIdentidad tdi, String codigo) {
        TipoDocIdentidadDAO tipoDocIdentidadDAO = TipoDocIdentidadDAO.getInstance();
        tipoDocIdentidadDAO.ActualizarTipoDocIdentidad(tdi);
    }
}
