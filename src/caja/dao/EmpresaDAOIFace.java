/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteEstudioContable;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface EmpresaDAOIFace {
    public List BuscarEmpresaPorRUC(String busqueda);
    public List BuscarEmpresaPorNroCta(String busqueda);
    public List BuscarEmpresaPorCondicion(String busqueda);
    public List BuscarEmpresaPorNombre(String busqueda);
    public void GuardarEmpresa(Cliente emp);
    public void ActualizarEmpresa(Cliente emp);
    public void EliminarEmpresa(Cliente emp);
    public List ObtenerEmpresaUbigeoDistrito(int idProvincia);
    public List ObtenerEmpresaUbigeoProvincia(int idDepartamento);
    public List ObtenerEmpresaUbigeoDepartamento();
    public ClienteEstudioContable obtenerEstudioContable(int id);
    public List obtenerEspecialidades();
    public void guardarObjeto(Object o);
    public void actualizarObjeto(Object o);
    public List obtenerEspecialidades(int id);
}
