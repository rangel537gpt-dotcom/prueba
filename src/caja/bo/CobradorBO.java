/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.CobradorDAO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.ArrayList;
import java.util.List;
//import sun.security.jca.GetInstance;

/**
 *
 * @author juan carlos
 */
public class CobradorBO implements CobradorBOIFace {

    private static CobradorBO INSTANCE = new CobradorBO();
    private List<Cliente> resultadoBusqueda;
    private List<Cobrador> resultado;
    
    private List<UbigeoDepartamento> listaUbigeoDepartamento;
    private List<UbigeoProvincia> listaUbigeoProvincia;
    private List<UbigeoDistrito> listaUbigeoDistrito;
    
    
    public static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized (CobradorBO.class)
            {
                if(INSTANCE == null)
                    INSTANCE = new CobradorBO();
            }
        }
    }
    
    
    public static CobradorBO getInstance()
    {
        createInstance();
        return INSTANCE;
    }
    
    public CobradorBO()
    {
        resultadoBusqueda = new ArrayList();
    }
    
    
    public List<Cliente> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Cliente> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }
    
    
    @Override
    public List BuscarCobrador(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        CobradorDAO cob = CobradorDAO.getInstance();
        if(tipoBusqueda == 1)
        {
            resultadoBusqueda = cob.BuscarCobradorPorApePat(busqueda);
            return resultadoBusqueda;
        }
        else if(tipoBusqueda == 2)
        {
            resultadoBusqueda = cob.BuscarCobradorPorApeMat(busqueda);
            return resultadoBusqueda;
        }
        
        else if(tipoBusqueda == 3)
        {
            resultadoBusqueda = cob.BuscarCobradorPorNombre(busqueda);
            return resultadoBusqueda; 
        }
        
        else if(tipoBusqueda == 4)
        {
            resultadoBusqueda = cob.BuscarCobradorPorDNI(busqueda);
            return resultadoBusqueda;
        }
        
        else
        {
            resultado = cob.BuscarCobradorPorCodigo(busqueda);
            return resultado;
        }
    }

    @Override
    public void GuardarCobrador(Cobrador c) {
        CobradorDAO cobradorDAO = CobradorDAO.getInstance();
        cobradorDAO.GuardarCobrador(c);
    }

    @Override
    public Cliente ObtenerCobrador(String dni) {
        for(Cliente c : resultadoBusqueda)
        {
           if(c.getPnroDocumento().equals(dni))
           {
               return c;
           } 
        }
        return null; 
    }

   
    @Override
    public List ObtenerCobradorUbigeoDepartamento() {
       CobradorDAO cobradorDAO = CobradorDAO.getInstance();
        if (this.listaUbigeoDepartamento == null) {
            this.listaUbigeoDepartamento = cobradorDAO.ObtenerCobradorUbigeoDepartamento();
        }
        return this.listaUbigeoDepartamento;
    }
    
    @Override
    public List ObtenerCobradorUbigeoProvincia(int idDepartamento ) {
        CobradorDAO cobradorDAO = CobradorDAO.getInstance();
        
        return this.listaUbigeoProvincia = cobradorDAO.ObtenerCobradorUbigeoProvincia(idDepartamento);
//        return this.listaUbigeoDistrito = contadorDAO.ObtenerContadorUbigeoDistrito(idProvincia);
        
    }

    @Override
    public List ObtenerCobradorUbigeoDistrito(int idDepartamento) {
        CobradorDAO cobradorDAO = CobradorDAO.getInstance();
        
        return this.listaUbigeoDistrito = cobradorDAO.ObtenerCobradorUbigeoDistrito(idDepartamento);
        
    }
    

    @Override
    public void ActualizarCobrador(Cobrador c) {
        CobradorDAO cobradorDAO = CobradorDAO.getInstance();
        cobradorDAO.ActualizarCobrador(c);
    }

    @Override
    public List ObtenerTodosCobradores() {
        CobradorDAO coDAO = CobradorDAO.getInstance();
        return coDAO.ObtenerTodosCobradores();
    }
}
