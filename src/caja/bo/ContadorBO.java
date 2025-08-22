/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.ContadorDAO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteCertificado;
import caja.mapeo.entidades.ClienteDerechoHabiente;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoLocalidad;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class ContadorBO implements ContadorBOIFace {

    private static ContadorBO INSTANCE = new ContadorBO();
    private List<Cliente> resultadoBusqueda;
    private List<UbigeoDepartamento> listaUbigeoDepartamento;
    private List<UbigeoProvincia> listaUbigeoProvincia;
    private List<UbigeoDistrito> listaUbigeoDistrito;
    private List<UbigeoLocalidad> listaUbigeoLocalidad;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ContadorBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ContadorBO();
                }
            }
        }
    }

    public static ContadorBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public ContadorBO() {
        resultadoBusqueda = new ArrayList();
    }

    public List<Cliente> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Cliente> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List BuscarContador(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        ContadorDAO cont = ContadorDAO.getInstance();
        if (tipoBusqueda == 1) {
            resultadoBusqueda = cont.BuscarContadorPorApellidos(busqueda);  //BuscarContadorPorCodigoCole
            return resultadoBusqueda;
        } else if (tipoBusqueda == 2) {
            resultadoBusqueda = cont.BuscarContadorPorCodigoCole(busqueda);//BuscarContadorPorDNI
            return resultadoBusqueda;
        } else if (tipoBusqueda == 3) {
            resultadoBusqueda = cont.BuscarContadorPorDNI(busqueda); //BuscarContadorPorApePat
            return resultadoBusqueda;
        } else if (tipoBusqueda == 4) {
            resultadoBusqueda = cont.BuscarContadorPorNombre(busqueda);  //BuscarContadorPorApeMat
            return resultadoBusqueda;
        } else if (tipoBusqueda == 5) {
            resultadoBusqueda = cont.BuscarContadorPorApePat(busqueda);  //BuscarPorApellidos
            return resultadoBusqueda;
        } else {
            resultadoBusqueda = cont.BuscarContadorPorApeMat(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public List BuscarFichaInscripcion(String filtro) {
        ContadorDAO cont = ContadorDAO.getInstance();
        return cont.BuscarFichaInscripcion(filtro);
    }

    @Override
    public List buscarAuditorIndependiente(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();
        String filtro = "";
        ContadorDAO cont = ContadorDAO.getInstance();
        if (tipoBusqueda == 1) {
            filtro = "AND (a.cliente.papePat + ' ' + a.cliente.papeMat) LIKE '%" + busqueda + "%'";
        } else if (tipoBusqueda == 2) {
            filtro = "AND a.cliente.ccodigoCole LIKE '%" + busqueda + "%'";
        } else if (tipoBusqueda == 3) {
            filtro = "AND a.cliente.pnroDocumento LIKE '%" + busqueda + "%'";
        } else if (tipoBusqueda == 4) {
            filtro = "AND a.cliente.papePat + ' ' + a.cliente.papeMat + ' ' + a.cliente.pnombre LIKE '%" + busqueda + "%'";
        } else if (tipoBusqueda == 5) {
            filtro = "AND a.cliente.papePat LIKE '%" + busqueda + "%'";
        } else {
            filtro = "AND a.cliente.papeMat LIKE '%" + busqueda + "%'";
        }
        resultadoBusqueda = cont.buscarAuditorIndependiente(filtro);
        return resultadoBusqueda;
    }

    @Override
    public void GuardarContador(Cliente c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.GuardarContador(c);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        dpBO.AgregarClientesListadoLocal(c);
    }

    @Override
    public void GuardarClienteCertificado(ClienteCertificado c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.GuardarClienteCertificado(c);
    }

    @Override
    public void ActualizarClienteCertificado(ClienteCertificado c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.ActualizarClienteCertificado(c);
    }

    @Override
    public void GuardarObjeto(Object c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.GuardarObjeto(c);
    }

    @Override
    public void ActualizarObjeto(Object c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.ActualizarObjeto(c);
    }

    @Override
    public void EliminarClienteCertificado(ClienteCertificado c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.EliminarClienteCertificado(c);
    }

    @Override
    public void EliminarClienteDerechoHabiente(ClienteDerechoHabiente c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.EliminarClienteDerechoHabiente(c);
    }

    @Override
    public Cliente ObtenerContador(String dni) {
        for (Cliente c : resultadoBusqueda) {
            if (c.getPnroDocumento().equals(dni)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Cliente ObtenerContadorSegunDNI(String dni) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerContadorSegunDNI(dni);
    }

    @Override
    public Cliente ObtenerContadorSegunDNI_Distinto(String dni, int idCliente) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerContadorSegunDNI_Distinto(dni, idCliente);
    }

    @Override
    public Cliente ObtenerContador_SegunCodigoColegiado_Distinto(String cc, int idCliente) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerContador_SegunCodigoColegiado_Distinto(cc, idCliente);
    }

    @Override
    public Cliente ObtenerContador_SegunCodigoColegiado(String cc) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerContador_SegunCodigoColegiado(cc);
    }

    @Override
    public List ObtenerContadorUbigeoDepartamento() {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        if (this.listaUbigeoDepartamento == null) {
            this.listaUbigeoDepartamento = contadorDAO.ObtenerContadorUbigeoDepartamento();
        }
        return this.listaUbigeoDepartamento;
    }

    @Override
    public ClienteCertificado ObtenerUltimoCertificadoContador_SegunCodigo(String codigo) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerUltimoCertificadoContador_SegunCodigo(codigo);
    }

    @Override
    public List ObtenerCuotasPendientesContador(int idCliente) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerCuotasPendientesContador(idCliente);
    }

    @Override
    public List obtenerDerechoHabiente(String dni) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.obtenerDerechoHabiente(dni);
    }

    @Override
    public List ObtenerCuotasPendientesSociedad(int idCliente) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerCuotasPendientesSociedad(idCliente);
    }

    @Override
    public List ObtenerFinanciamientoPendientesContador(int idCliente) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerFinanciamientoPendientesContador(idCliente);
    }

    @Override
    public List ObtenerReincorporacionPendientesContador(int idCliente) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerReincorporacionPendientesContador(idCliente);
    }

    @Override
    public List ObtenerDeudasContador(int idCliente) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.ObtenerDeudasContador(idCliente);
    }

    @Override
    public List ObtenerContadorUbigeoProvincia(int idDepartamento) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return this.listaUbigeoProvincia = contadorDAO.ObtenerContadorUbigeoProvincia(idDepartamento);
    }

    @Override
    public String obteneroCodigoUbigeo(int idDistrito) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return contadorDAO.obteneroCodigoUbigeo(idDistrito);
    }

    @Override
    public List ObtenerContadorUbigeoDistrito(int idProvincia) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        return this.listaUbigeoDistrito = contadorDAO.ObtenerContadorUbigeoDistrito(idProvincia);
    }

    @Override
    public List ObtenerContadorUbigeoLocalidad(int idDistrito) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();

        return this.listaUbigeoLocalidad = contadorDAO.ObtenerContadorUbigeoLocalidad(idDistrito);
    }

    @Override
    public void ActualizarContador(Cliente c) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.ActualizarContador(c);
        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = docPago.ObtenerTodosClientes();
        if (lClientes != null) {
            lClientes.set(1, c);          //add(c);          //addAll(lClientes);

//            for(lClientes : aa ){
//                
//            }
        }

    }

    @Override
    public void EliminarContador(Cliente c, String codCole) {
        ContadorDAO contadorDAO = ContadorDAO.getInstance();
        contadorDAO.EliminarContador(c);

        DocumentoPagoBO docPago = DocumentoPagoBO.getInstance();
        List<Cliente> lClientes = docPago.ObtenerTodosClientes();
        if (lClientes != null) {
            lClientes.remove(c);
        }
    }

}
