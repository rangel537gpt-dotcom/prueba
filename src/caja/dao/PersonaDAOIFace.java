/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Cliente;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface PersonaDAOIFace {
    public List BuscarPersonaPorApePat(String busqueda);
    public List BuscarPersonaPorApeMat(String busqueda);
    public List BuscarPersonaPorNombre(String busqueda);
    public List BuscarPersonaPorNroDocumento(String busqueda);
    public List ObtenerPersonaUbigeoDepartamento();
    public List ObtenerPersonaUbigeoProvincia(int idDepartamento);
    public List ObtenerPersonaUbigeoDistrito(int idProvincia);
    public void GuardarPersona(Cliente p);
    public void ActualizarPersona(Cliente p);
    public String ObtenerNombreDepartamento(int id);
    public String ObtenerNombreProvincia(int id);
    public String ObtenerNombreDistrito(int id);
}
