/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteCertificado;
import caja.mapeo.entidades.ClienteDerechoHabiente;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface ContadorBOIFace {
    public List BuscarContador(String busqueda, int tipoBusqueda);
    public void GuardarContador(Cliente c);
    public Cliente ObtenerContador(String dni);
    public List ObtenerContadorUbigeoDepartamento();
    public List ObtenerContadorUbigeoProvincia(int idDepartamento);
    public List ObtenerContadorUbigeoDistrito(int idProvincia);
    public List ObtenerContadorUbigeoLocalidad(int idDistrito);
    public void ActualizarContador(Cliente c);
    public void EliminarContador(Cliente c, String codCole);
    public Cliente ObtenerContador_SegunCodigoColegiado(String cc);
    public Cliente ObtenerContadorSegunDNI_Distinto(String dni, int idCliente);
    public Cliente ObtenerContador_SegunCodigoColegiado_Distinto(String cc, int idCliente);
    public Cliente ObtenerContadorSegunDNI(String dni);
    public void GuardarClienteCertificado(ClienteCertificado c);
    public void ActualizarClienteCertificado(ClienteCertificado c);
    public void EliminarClienteCertificado(ClienteCertificado c);
    public List ObtenerCuotasPendientesContador(int idCliente);
    public List ObtenerFinanciamientoPendientesContador(int idCliente);
    public List ObtenerReincorporacionPendientesContador(int idCliente);
    public List ObtenerDeudasContador(int idCliente);
    public ClienteCertificado ObtenerUltimoCertificadoContador_SegunCodigo(String codigo);
    public List ObtenerCuotasPendientesSociedad(int idCliente);
    public void EliminarClienteDerechoHabiente(ClienteDerechoHabiente c);
    public void GuardarObjeto(Object cont);
    public void ActualizarObjeto(Object cont);
    public List obtenerDerechoHabiente(String dni);
    public String obteneroCodigoUbigeo(int idDistrito);
    public List buscarAuditorIndependiente(String busqueda, int tipoBusqueda);
    public List BuscarFichaInscripcion(String filtro);
    
}
