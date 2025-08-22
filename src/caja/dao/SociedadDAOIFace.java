/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

/*import caja.entidades.Sociedad;
import caja.entidades.SociedadCobrador;*/
import caja.mapeo.entidades.Cliente;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface SociedadDAOIFace {
    public List BuscarSociedadPorCodigo(String busqueda);
    public List BuscarSociedadPorNombre(String busqueda);
    public List BuscarSociedadPorRUC(String busqueda);
    public List BuscarSociedadPorCondicion(String busqueda);
    public List ObtenerSociedadUbigeoDepartamento();
    public List ObtenerSociedadUbigeoProvincia(int idDepartamento);
    public List ObtenerSociedadUbigeoDistrito(int idProvincia);
    public void GuardarSociedad(Cliente soc);
    public void ActualizarSociedad(Cliente soc);
    public void EliminarSociedad(Cliente soc);
    public List ObtenerSociedadMiembro_SegunCliente(int idCliente);
    public List ObtenerSociedadVigencia_SegunCliente(int idCliente);
    
}
