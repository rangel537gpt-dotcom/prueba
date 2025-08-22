/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;
import caja.mapeo.entidades.Usuario;
import caja.mapeo.entidades.ValeAcademico;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author user
 */
public interface SeguridadBOIFace {
    public boolean AutenticarUsuario(String usuario,byte[] clave);
    public Usuario InicializarDatosUsuario();
    public boolean CambiarContrasena(byte[] contrasena);
    public Date ObtenerFechaServidor();
    public Date SumaMesesFechaServidor(int cantMeses);
    public void GenerarLog(String mensaje);
    public Object CargarObjeto(String nombreClase, int id);
    public Date ObtenerFechaHoraServidor();
    public Set CargarListaValeAcademicoConsumos(ValeAcademico va);
}
