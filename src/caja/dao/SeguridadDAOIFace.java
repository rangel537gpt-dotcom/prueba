/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;
import caja.mapeo.entidades.Usuario;
import caja.mapeo.entidades.ValeAcademico;
import java.util.Date;
import java.util.Set;
/**
 *
 * @author user
 */
public interface SeguridadDAOIFace {
    public Usuario EncontrarUsuario(String usuario,String macAddress);
    public Usuario InicializarDatosUsuario(Usuario u);
    public boolean CambiarContrasena(Usuario usuario);
    public Date ObtenerFechaServidor();
    public Date SumaMesesFechaServidor(int cantMeses);
    public Object CargarObjeto(String nombreClase, int id);
    public Date ObtenerFechaHoraServidor();
    public Set CargarListaValeAcademicoConsumos(ValeAcademico va);
}
