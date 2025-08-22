/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Usuario;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface UsuarioBOIFace {
    public List BuscarUsuario(String busqueda, int tipoBusqueda);
    public void GuardarUsuario(Usuario u, String mac);
    public Usuario ObtenerUsuario(String login);
    public void ActualizarUsuario(Usuario u, String mac);
    public void EliminarUsuario(Usuario u, String mac);
    public List ObtenerTodosRoles();
    public List ObtenerTodosTipoDocumentoSerie_Asignados(int idUsuario);
    public int guardarTipoDocumentoSerieUsuario(int idUsuario, int idTipoDocSerie);
    public void eliminarTipoDocumentoSerieUsuario(int id);
}
