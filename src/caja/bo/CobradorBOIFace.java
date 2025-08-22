/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

//import caja.entidades.Cobrador;
//import caja.entidades.Persona;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface CobradorBOIFace {
    public List BuscarCobrador(String busqueda, int tipoBusqueda);
    public void GuardarCobrador(Cobrador c);
    public Cliente ObtenerCobrador(String dni);
    
    public List ObtenerCobradorUbigeoDepartamento();
    public List ObtenerCobradorUbigeoProvincia(int idDepartamento);
    public List ObtenerCobradorUbigeoDistrito(int idProvincia);
    
    public void ActualizarCobrador(Cobrador c);
    
    public List ObtenerTodosCobradores();
}
