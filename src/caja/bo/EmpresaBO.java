/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.ClienteDAO;
import caja.dao.EmpresaDAO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteEstudioContable;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class EmpresaBO implements EmpresaBOIFace {

    private static EmpresaBO INSTANCE = new EmpresaBO();
    private List<Cliente> resultadoBusqueda;
    private List<UbigeoDepartamento> listaUbigeoDepartamento;
    private List<UbigeoProvincia> listaUbigeoProvincia;
    private List<UbigeoDistrito> listaUbigeoDistrito;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (EmpresaBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmpresaBO();
                }
            }
        }
    }

    public static EmpresaBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public EmpresaBO() {
        resultadoBusqueda = new ArrayList();
    }

    public List<Cliente> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Cliente> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List BuscarEmpresa(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        EmpresaDAO emp = EmpresaDAO.getInstance();
        if (tipoBusqueda == 1) {
            resultadoBusqueda = emp.BuscarEmpresaPorRUC(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 2) {
            resultadoBusqueda = emp.BuscarEmpresaPorNombre(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 3) {
            resultadoBusqueda = emp.BuscarEmpresaPorNroCta(busqueda);
            return resultadoBusqueda;
        } else {
            resultadoBusqueda = emp.BuscarEmpresaPorCondicion(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarEmpresa(Cliente c, String ruc) {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        empresaDAO.GuardarEmpresa(c);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        dpBO.AgregarClientesListadoLocal(c);
    }

    @Override
    public void guardarObjeto(Object o) {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        empresaDAO.guardarObjeto(o);
    }

    @Override
    public void actualizarObjeto(Object o) {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        empresaDAO.actualizarObjeto(o);
    }

    @Override
    public Cliente ObtenerEmpresa(String ruc) {
        for (Cliente c : resultadoBusqueda) {
            if (c.getEruc().equals(ruc)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public ClienteEstudioContable obtenerEstudioContable(Integer id) {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        return empresaDAO.obtenerEstudioContable(id);
    }

    @Override
    public List obtenerEspecialidades() {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        return empresaDAO.obtenerEspecialidades();
    }

    @Override
    public List obtenerEspecialidades(int idClienteEstudioContable) {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        return empresaDAO.obtenerEspecialidades(idClienteEstudioContable);
    }

    @Override
    public void ActualizarEmpresa(Cliente c, String ruc) {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        empresaDAO.ActualizarEmpresa(c);

        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = docPago.ObtenerTodosClientes();
//        for (Cliente cl : lClientes) {
//            if (cl.getIdCliente() == c.getIdCliente()) {
//                cl = c;
//                break;
//            }
//        }
        for (int i = 0; i < lClientes.size(); i++) {
            Cliente cliTemp = lClientes.get(i);
            if (cliTemp.getIdCliente() == c.getIdCliente()) {
                lClientes.set(i, c);
                break;
            }
        }
    }

    @Override
    public void EliminarEmpresa(Cliente c, String eruc) {
        EmpresaDAO empresaDAO = EmpresaDAO.getInstance();
        empresaDAO.EliminarEmpresa(c);
    }

    @Override
    public List ObtenerEmpresaUbigeoDepartamento() {
        EmpresaDAO eDAO = EmpresaDAO.getInstance();
        if (this.listaUbigeoDepartamento == null) {
            this.listaUbigeoDepartamento = eDAO.ObtenerEmpresaUbigeoDepartamento();
        }
        return this.listaUbigeoDepartamento;
    }

    @Override
    public List ObtenerEmpresaUbigeoProvincia(int idDepartamento) {
        EmpresaDAO contadorDAO = EmpresaDAO.getInstance();
        return this.listaUbigeoProvincia = contadorDAO.ObtenerEmpresaUbigeoProvincia(idDepartamento);
    }

    @Override
    public List ObtenerEmpresaUbigeoDistrito(int idProvincia) {
        EmpresaDAO contadorDAO = EmpresaDAO.getInstance();
        return this.listaUbigeoDistrito = contadorDAO.ObtenerEmpresaUbigeoDistrito(idProvincia);
    }

}
