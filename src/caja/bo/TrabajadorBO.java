/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.TrabajadorDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class TrabajadorBO implements TrabajadorBOIFace{
    
    private static TrabajadorBO INSTANCE = new TrabajadorBO();
    //private List<Persona> resultadoBusqueda;
    
    
    private static void createInstance() {
        if (INSTANCE == null) 
        {
            synchronized (TrabajadorBO.class) 
            {
                if (INSTANCE == null) 
                {
                    INSTANCE = new TrabajadorBO();
                }
            }
        }
    }
    
    public static TrabajadorBO getInstance() 
    {
        createInstance();
        return INSTANCE;
    }

    public TrabajadorBO() 
    {
        //resultadoBusqueda = new ArrayList();
    }
    
    /*public List<Persona> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Persona> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }*/
    
    
    /*@Override
    public List BuscarTrabajador(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();
        
        TrabajadorDAO t = TrabajadorDAO.getInstance();
        
        if(tipoBusqueda == 1)
        {
            resultadoBusqueda = t.BuscarPorApePat(busqueda);
            return resultadoBusqueda;
        }
        else if(tipoBusqueda == 2)
        {
            resultadoBusqueda = t.BuscarPorApeMat(busqueda);
            return resultadoBusqueda;
        }
        
        else if(tipoBusqueda == 3)
        {
            resultadoBusqueda = t.BuscarProNombre(busqueda);
            return resultadoBusqueda;
        }
        
        else{
            resultadoBusqueda = t.BuscarPorDNI(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarTrabajador(Persona p, String codigoTrabajador) {            
    }


    @Override
    public Persona ObtenerTrabajador(String dni) {
        for(Persona p : resultadoBusqueda)
        {
           if(p.getDni().equals(dni))
           {
               return p;
           } 
        }
        return null;    
    }*/
    
}
