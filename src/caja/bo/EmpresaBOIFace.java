/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteEstudioContable;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface EmpresaBOIFace {
    public List BuscarEmpresa(String busqueda, int tipoBusqueda);
    public void GuardarEmpresa(Cliente c, String ruc);
    public Cliente ObtenerEmpresa(String ruc);
    public void ActualizarEmpresa(Cliente c, String ruc);
    public void EliminarEmpresa(Cliente c, String eruc);
    public List ObtenerEmpresaUbigeoProvincia(int idDepartamento);
    public List ObtenerEmpresaUbigeoDistrito(int idProvincia);
    public List ObtenerEmpresaUbigeoDepartamento();
    public ClienteEstudioContable obtenerEstudioContable(Integer id);
    public List obtenerEspecialidades();
    public void guardarObjeto(Object o);
    public void actualizarObjeto(Object o);
    public List obtenerEspecialidades(int idClienteEstudioContable);
}
