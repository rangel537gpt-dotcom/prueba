/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;
import caja.mapeo.entidades.Cliente;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface SociedadBOIFace {
    public List BuscarSociedad(String busqueda, int tipoBusqueda);
    public void GuardarSociedad(Cliente s);
    public Cliente ObtenerSociedad(String codigo);
    public List ObtenerSociedadUbigeoDepartamento();
    public List ObtenerSociedadUbigeoProvincia(int idDepartamento);
    public List ObtenerSociedadUbigeoDistrito(int idProvincia);
    public void ActualizarSociedad(Cliente s);
    public void EliminarSociedad(Cliente s, String codSoc);
    public List BuscarTrabajador(String busqueda, int tipoBusqueda);
    public List ObtenerSociedadMiembro_SegunCliente(int idCliente);
    public List ObtenerSociedadVigencia_SegunCliente(int idCliente);   
}
