/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.UsuarioDAO;
import caja.mapeo.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class UsuarioBO implements UsuarioBOIFace{
    private static UsuarioBO INSTANCE = new UsuarioBO();
    private List<Usuario> resultadoBusqueda;
    
    
    public static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized (UsuarioBO.class)
            {
                if(INSTANCE == null)
                    INSTANCE = new UsuarioBO();
            }
        }
    }
    
    
    public static UsuarioBO getInstance()
    {
        createInstance();
        return INSTANCE;
    }
    
    public UsuarioBO()
    {
        resultadoBusqueda = new ArrayList();
    }
    
    
    public List<Usuario> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Usuario> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    
    @Override
    public List BuscarUsuario(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        UsuarioDAO u = UsuarioDAO.getInstance();
        if(tipoBusqueda == 1)
        {
            resultadoBusqueda = u.BuscarUsuarioPorLogin(busqueda);
            return resultadoBusqueda;
        }
        else if(tipoBusqueda == 2)
        {
            resultadoBusqueda = u.BuscarUsuarioPorRol(busqueda);
            return resultadoBusqueda;
        }
        
        else
        {
            resultadoBusqueda = u.BuscarUsuarioPorMac(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarUsuario(Usuario u, String mac) {
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.GuardarUsuario(u);
        
//        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
//        List<Cliente> lClientes= docPago.ObtenerTodosClientes();
//        if(lClientes != null){
//            lClientes.add(c);
//        }
    }

    @Override
    public Usuario ObtenerUsuario(String login) {
        for(Usuario u : resultadoBusqueda)
        {
           if(u.getLogin().equals(login))
           {
               return u;
           } 
        }
        return null;
    }

    @Override
    public List ObtenerTodosRoles() {
        UsuarioDAO  usuarioDAO = UsuarioDAO.getInstance();
        return usuarioDAO.ObtenerTodosRoles();
    }

    @Override
    public List ObtenerTodosTipoDocumentoSerie_Asignados(int idUsuario) {
        UsuarioDAO  usuarioDAO = UsuarioDAO.getInstance();
        return usuarioDAO.ObtenerTodosTipoDocumentoSerie_Asignados(idUsuario);
    }

    @Override
    public int guardarTipoDocumentoSerieUsuario(int idUsuario, int idTipoDocSerie) {
        UsuarioDAO  usuarioDAO = UsuarioDAO.getInstance();
        return usuarioDAO.guardarTipoDocumentoSerieUsuario(idUsuario, idTipoDocSerie);
    }

    @Override
    public void eliminarTipoDocumentoSerieUsuario(int id) {
        UsuarioDAO  usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.eliminarTipoDocumentoSerieUsuario(id);
    }

    @Override
    public void ActualizarUsuario(Usuario u, String mac) {
        UsuarioDAO  usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.ActualizarUsuario(u);
    }

    @Override
    public void EliminarUsuario(Usuario u, String mac) {
        UsuarioDAO  usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.EliminarUsuario(u);
    }
}
