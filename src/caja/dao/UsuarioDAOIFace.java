/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Usuario;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface UsuarioDAOIFace {
    public List BuscarUsuarioPorRol(String busqueda);
    public List BuscarUsuarioPorLogin(String busqueda);
    public List BuscarUsuarioPorMac(String busqueda);
    public void GuardarUsuario(Usuario u);
    public void ActualizarUsuario(Usuario u);
    public void EliminarUsuario(Usuario u);
    public List ObtenerTodosRoles();
    public List ObtenerTodosTipoDocumentoSerie_Asignados(int idUsuario);
    public int guardarTipoDocumentoSerieUsuario(int idUsuario, int idTipoDocSerie);
    public void eliminarTipoDocumentoSerieUsuario(int id);
    //public void GuardarCliente(Cliente c);
    //public void ActualizarUsuario(Cliente cob);
}
