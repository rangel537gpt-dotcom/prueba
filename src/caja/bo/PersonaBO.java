/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.PersonaDAO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class PersonaBO implements PersonaBOIFace{
    
    private static PersonaBO INSTANCE = new PersonaBO();
    private List<Cliente> resultadoBusqueda;
    
    private List<UbigeoDepartamento> listaUbigeoDepartamento;
    private List<UbigeoProvincia> listaUbigeoProvincia;
    private List<UbigeoDistrito> listaUbigeoDistrito;
    
    public static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized (PersonaBO.class)
            {
                if(INSTANCE == null)
                    INSTANCE = new PersonaBO();
            }
        }
    }
    
    
    public static PersonaBO getInstance()
    {
        createInstance();
        return INSTANCE;
    }
    
    public PersonaBO()
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
    public List BuscarPersona(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        PersonaDAO p = PersonaDAO.getInstance();
        if(tipoBusqueda == 1)
        {
            resultadoBusqueda = p.BuscarPersonaPorNroDocumento(busqueda);
            return resultadoBusqueda;
        }
        else if(tipoBusqueda == 2)
        {
            resultadoBusqueda = p.BuscarPersonaPorApePat(busqueda);
            return resultadoBusqueda;
        }
        
        else if(tipoBusqueda == 3)
        {
            resultadoBusqueda = p.BuscarPersonaPorApeMat(busqueda);
            return resultadoBusqueda; 
        }
        
        else
        {
            resultadoBusqueda = p.BuscarPersonaPorNombre(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarPersona(Cliente c) {
        PersonaDAO personaDAO = PersonaDAO.getInstance();
        personaDAO.GuardarPersona(c);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        dpBO.AgregarClientesListadoLocal(c);
    }

    @Override
    public Cliente ObtenerPersona(String nroDocumento) {
        for(Cliente c : resultadoBusqueda)
        {
           if(c.getPnroDocumento().equals(nroDocumento))
           {
               return c;
           } 
        }
        return null;
    }

    
    @Override
    public List ObtenerPersonaUbigeoDepartamento() {
       PersonaDAO personaDAO = PersonaDAO.getInstance();
        if (this.listaUbigeoDepartamento == null) {
            this.listaUbigeoDepartamento = personaDAO.ObtenerPersonaUbigeoDepartamento();
        }
        return this.listaUbigeoDepartamento;
    }
    
    @Override
    public List ObtenerPersonaUbigeoProvincia(int idDepartamento ) {
        PersonaDAO personaDAO = PersonaDAO.getInstance();
        
        return this.listaUbigeoProvincia = personaDAO.ObtenerPersonaUbigeoProvincia(idDepartamento);
//        return this.listaUbigeoDistrito = contadorDAO.ObtenerContadorUbigeoDistrito(idProvincia);
        
    }

    @Override
    public List ObtenerPersonaUbigeoDistrito(int idDepartamento) {
        PersonaDAO personaDAO = PersonaDAO.getInstance();
        
        return this.listaUbigeoDistrito = personaDAO.ObtenerPersonaUbigeoDistrito(idDepartamento);
        
    }
    
    @Override
    public void ActualizarPersona(Cliente c) {
        PersonaDAO personaDAO = PersonaDAO.getInstance();
        personaDAO.ActualizarPersona(c);
    }
    
    @Override
    public String ObtenerNombreDepartamento(int id) {
        PersonaDAO personaDAO = PersonaDAO.getInstance();
        return personaDAO.ObtenerNombreDepartamento(id);
    }
    
    @Override
    public String ObtenerNombreProvincia(int id) {
        PersonaDAO personaDAO = PersonaDAO.getInstance();
        return personaDAO.ObtenerNombreProvincia(id);
    }
    
    @Override
    public String ObtenerNombreDistrito(int id) {
        PersonaDAO personaDAO = PersonaDAO.getInstance();
        return personaDAO.ObtenerNombreDistrito(id);
    }
    
}
