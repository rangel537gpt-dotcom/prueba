/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface CobradorDAOIFace {
    public List BuscarCobradorPorCodigo(String busqueda);
    public List BuscarCobradorPorApePat(String busqueda);
    public List BuscarCobradorPorApeMat(String busqueda);
    public List BuscarCobradorPorNombre(String busqueda);
    public List BuscarCobradorPorDNI(String busqueda);
    
    public List ObtenerCobradorUbigeoDepartamento();
    public List ObtenerCobradorUbigeoProvincia(int idDepartamento);
    public List ObtenerCobradorUbigeoDistrito(int idProvincia);
    
    public void GuardarCobrador(Cobrador cob);
    public void GuardarCliente(Cliente c);
    public void ActualizarCobrador(Cobrador cob);
    public void EliminarCobrador(Cliente cob);
    
//    public List BuscarCobradorPorZona(String busqueda);
//    public List BuscarCobradorPorCodigo(String busqueda);
//    public void GuardarCobrador(Cobrador cob);
    //public void GuardarPersona(Persona p);
    public List ObtenerTodosCobradores();
}
