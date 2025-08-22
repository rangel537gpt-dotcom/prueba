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
public interface PersonaBOIFace {
    public List BuscarPersona(String busqueda, int tipoBusqueda);
    public void GuardarPersona(Cliente c);
    public Cliente ObtenerPersona(String nroDocumento);
    public List ObtenerPersonaUbigeoDepartamento();
    public List ObtenerPersonaUbigeoProvincia(int idDepartamento);
    public List ObtenerPersonaUbigeoDistrito(int idProvincia);
    public void ActualizarPersona(Cliente c);
    public String ObtenerNombreDepartamento(int id);
    public String ObtenerNombreProvincia(int id);
    public String ObtenerNombreDistrito(int id);
}
