/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.SociedadDAO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class SociedadBO implements SociedadBOIFace {

    private static SociedadBO INSTANCE = new SociedadBO();
    private List<Cliente> resultadoBusqueda;

    private List<UbigeoDepartamento> listaUbigeoDepartamento;
    private List<UbigeoProvincia> listaUbigeoProvincia;
    private List<UbigeoDistrito> listaUbigeoDistrito;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (SociedadBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SociedadBO();
                }
            }
        }
    }

    public static SociedadBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public SociedadBO() {
        resultadoBusqueda = new ArrayList();
    }

    public List<Cliente> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Cliente> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List BuscarSociedad(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();
        SociedadDAO soc = SociedadDAO.getInstance();
        if (tipoBusqueda == 1) {
            resultadoBusqueda = soc.BuscarSociedadPorCodigo(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 2) {
            resultadoBusqueda = soc.BuscarSociedadPorRUC(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 3) {
            resultadoBusqueda = soc.BuscarSociedadPorNombre(busqueda);
            return resultadoBusqueda;
        } else {
            resultadoBusqueda = soc.BuscarSociedadPorCondicion(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarSociedad(Cliente s) {
        SociedadDAO sociedadDAO = SociedadDAO.getInstance();
        sociedadDAO.GuardarSociedad(s);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        dpBO.AgregarClientesListadoLocal(s);
    }

    @Override
    public Cliente ObtenerSociedad(String codigo) {
        for (Cliente c : resultadoBusqueda) {
            if (c.getScodigoSoc().equals(codigo)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List ObtenerSociedadUbigeoDepartamento() {
        SociedadDAO sociedadDAO = SociedadDAO.getInstance();
        if (this.listaUbigeoDepartamento == null) {
            this.listaUbigeoDepartamento = sociedadDAO.ObtenerSociedadUbigeoDepartamento();
        }
        return this.listaUbigeoDepartamento;
    }

    @Override
    public List ObtenerSociedadUbigeoProvincia(int idDepartamento) {
        SociedadDAO sociedadDAO = SociedadDAO.getInstance();

        return this.listaUbigeoProvincia = sociedadDAO.ObtenerSociedadUbigeoProvincia(idDepartamento);
//        return this.listaUbigeoDistrito = contadorDAO.ObtenerContadorUbigeoDistrito(idProvincia);

    }

    @Override
    public List ObtenerSociedadUbigeoDistrito(int idDepartamento) {
        SociedadDAO sociedadDAO = SociedadDAO.getInstance();
        return this.listaUbigeoDistrito = sociedadDAO.ObtenerSociedadUbigeoDistrito(idDepartamento);
    }

    @Override
    public List ObtenerSociedadMiembro_SegunCliente(int idCliente) {
        SociedadDAO cDAO = SociedadDAO.getInstance();
        return cDAO.ObtenerSociedadMiembro_SegunCliente(idCliente);
    }

    @Override
    public List ObtenerSociedadVigencia_SegunCliente(int idCliente) {
        SociedadDAO cDAO = SociedadDAO.getInstance();
        return cDAO.ObtenerSociedadVigencia_SegunCliente(idCliente);
    }

    @Override
    public void ActualizarSociedad(Cliente s) {
        SociedadDAO sociedadDAO = SociedadDAO.getInstance();
        sociedadDAO.ActualizarSociedad(s);
        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = docPago.ObtenerTodosClientes();
        if (lClientes != null) {
            lClientes.add(s);
        }
    }

    @Override
    public List BuscarTrabajador(String busqueda, int tipoBusqueda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EliminarSociedad(Cliente s, String codSoc) {
        SociedadDAO sociedadDAO = SociedadDAO.getInstance();
        sociedadDAO.EliminarSociedad(s);

        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = docPago.ObtenerTodosClientes();
        if (lClientes != null) {
            lClientes.remove(s);
        }
    }

}
