/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteCertificado;
import caja.mapeo.entidades.ClienteDerechoHabiente;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface ContadorDAOIFace {
    public List BuscarContadorPorApePat(String busqueda);
    public List BuscarContadorPorApeMat(String busqueda);
    public List BuscarPorApellidos(String busqueda);
    public List BuscarContadorPorNombre(String busqueda);
    public List BuscarContadorPorDNI(String busqueda);
    public List BuscarContadorPorCodigoCole(String busqueda);
    public List ObtenerContadorUbigeoDepartamento();
    public List ObtenerContadorUbigeoProvincia(int idDepartamento);
    public List ObtenerContadorUbigeoDistrito(int idProvincia);
    public List ObtenerContadorUbigeoLocalidad(int idDistrito);
    public void GuardarContador(Cliente cont);
    public void ActualizarContador(Cliente cont);
    public void EliminarContador(Cliente cont);
    public List BuscarContadorPorApellidos(String busqueda);
    public Cliente ObtenerContador_SegunCodigoColegiado(String cc);
    public Cliente ObtenerContadorSegunDNI_Distinto(String dni, int idCliente);
    public Cliente ObtenerContador_SegunCodigoColegiado_Distinto(String cc, int idCliente);
    public Cliente ObtenerContadorSegunDNI(String dni);
    public void GuardarClienteCertificado(ClienteCertificado cont);
    public void ActualizarClienteCertificado(ClienteCertificado cont);
    public void EliminarClienteCertificado(ClienteCertificado cont);
    public List ObtenerCuotasPendientesContador(int idCliente);
    public List ObtenerFinanciamientoPendientesContador(int idCliente);
    public List ObtenerReincorporacionPendientesContador(int idCliente);
    public List ObtenerDeudasContador(int idCliente);
    public ClienteCertificado ObtenerUltimoCertificadoContador_SegunCodigo(String codigo);
    public List ObtenerCuotasPendientesSociedad(int idCliente);
    public void EliminarClienteDerechoHabiente(ClienteDerechoHabiente cont);
    public void GuardarObjeto(Object cont);
    public void ActualizarObjeto(Object cont);
    public List obtenerDerechoHabiente(String dni);
    public String obteneroCodigoUbigeo(int idDistrito);
    public List buscarAuditorIndependiente(String filtro);
    public List BuscarFichaInscripcion(String filtro);
}
