/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.TipoDocPagoDAO;
import caja.mapeo.entidades.TipoDocPago;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class TipoDocPagoBO implements TipoDocPagoBOIFace{

    private static TipoDocPagoBO INSTANCE = new TipoDocPagoBO();
    private List<TipoDocPago> resultadoBusqueda;
    
    
    public static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized (TipoDocPagoBO.class)
            {
                if(INSTANCE == null)
                    INSTANCE = new TipoDocPagoBO();
            }
        }
    }

    
    public static TipoDocPagoBO getInstance()
    {
        createInstance();
        return INSTANCE;
    }
    
    public TipoDocPagoBO()
    {
        resultadoBusqueda = new ArrayList();
    }
    
    
    public List<TipoDocPago> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<TipoDocPago> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }
    
    
    @Override
    public List BuscarTipoDocumentoPago(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        TipoDocPagoDAO tdp = TipoDocPagoDAO.getInstance();
        if(tipoBusqueda == 1)
        {
            resultadoBusqueda = tdp.BuscarTipoDocPagoPorNombre(busqueda);
            return resultadoBusqueda;
        }
        else
        {
            resultadoBusqueda = tdp.BuscarTipoDocPagoPorCodigo(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarTipoDocumentoPago(TipoDocPago doc, String codigoDocPago) {
        TipoDocPagoDAO tipoDocPagoDAO = TipoDocPagoDAO.getInstance();
        tipoDocPagoDAO.GuardarTipoDocPago(doc);
        
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<TipoDocPago> lConceptos = dpBO.ObtenerTodosTipoDocPago();
        if (lConceptos != null) {
            lConceptos.add(doc);
        }
    }
    
    @Override
    public TipoDocPago ObtenerTipoDocPago(String codigoTipoDoc) {
        for(TipoDocPago tdp : resultadoBusqueda)
        {
           if(tdp.getCodigoDocPago().equals(codigoTipoDoc))
           {
               return tdp;
           } 
        }
        return null;
    }
    
    @Override
    public List ObtenerSeries(int idTipoDocumento) {
        TipoDocPagoDAO tipoDocPagoDAO = TipoDocPagoDAO.getInstance();
        return tipoDocPagoDAO.ObtenerSeries(idTipoDocumento);
    }

    @Override
    public void ActualizarTipoDocumentoPago(TipoDocPago tdp, String codigo) {
        TipoDocPagoDAO tipoDocPagoDAO = TipoDocPagoDAO.getInstance();
        tipoDocPagoDAO.ActualizarTipoDocPago(tdp);
        
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<TipoDocPago> lConceptos = dpBO.ObtenerTodosTipoDocPago();
        if (lConceptos != null) {
            lConceptos.add(tdp);
        }
    }

    @Override
    public List ObtenerTodosTipoDocPago() {
        TipoDocPagoDAO tpDAO = TipoDocPagoDAO.getInstance();
        return tpDAO.ObtenerTodosTipoDocPago();
    }
    
}
