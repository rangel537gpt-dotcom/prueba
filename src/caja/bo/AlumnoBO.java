/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.AlumnoDAO;
import caja.mapeo.entidades.Alumnos;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.entidades.Cursos;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Evento;
import caja.mapeo.entidades.Inversion;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import java.util.ArrayList;
import java.util.Date;
//import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class AlumnoBO implements AlumnoBOIFace {

    private static AlumnoBO INSTANCE = new AlumnoBO();
    private List<Alumnos> resultadoBusqueda;
    //private Alumno alumno;
    private List<TipoDocPago> listaTipoDocPago;
    private List<ConceptoPagoDetalle> listaConceptoPago;
    private List<Cliente> listaClientes;
    private DocumentoPagoDet documentoPagoDet;
    private List<Cursos> listCursos;
    private List<DocumentoPagoDet> listDocumentoPagoDet;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AlumnoBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AlumnoBO();
                }
            }
        }
    }

    public static AlumnoBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public AlumnoBO() {
        resultadoBusqueda = new ArrayList();
    }

    public List<Alumnos> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Alumnos> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List BuscarAlumno(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        AlumnoDAO alu = AlumnoDAO.getInstance();
        if (tipoBusqueda == 1) {
            resultadoBusqueda = alu.BuscarAlumnoPorCodigoCurso(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 2) {
            resultadoBusqueda = alu.BuscarAlumnoPorNombreCurso(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 3) {
            resultadoBusqueda = alu.BuscarAlumnoPorNroDocumento(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 4) {
            resultadoBusqueda = alu.BuscarAlumnoPorCodigoColegiatura(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 5) {
            resultadoBusqueda = alu.BuscarAlumnoPorApellidoPaterno(busqueda);
            return resultadoBusqueda;
        } else {
            resultadoBusqueda = alu.BuscarAlumnoPorApellidoMaterno(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public List BuscarAlumnoPorIdComprobantePago(int idComprobantePago) {
        resultadoBusqueda.clear();
        AlumnoDAO alu = AlumnoDAO.getInstance();
        List<Alumnos> l = alu.BuscarAlumnoPorIdComprobantePago(idComprobantePago);
        return l;
    }

    @Override
    public List ObtenerTodosConceptoPagoCursos() {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        if (listaConceptoPago == null) {
            this.listaConceptoPago = aluDAO.ObtenerTodosConceptoPagoCursos();
        }
        return this.listaConceptoPago;
    }

    @Override
    public List ObtenerTodosClientes() {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        if (this.listaClientes == null) {
            this.listaClientes = aluDAO.ObtenerTodosClientes();
        }
        return this.listaClientes;
    }

    @Override
    public List ObtenerTodosTipoDocPago() {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        if (this.listaTipoDocPago == null) {
            this.listaTipoDocPago = aluDAO.ObtenerTodosTipoDocPago();
        }
        return this.listaTipoDocPago;
    }

    @Override
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc, int nroSerie) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        return aluDAO.ObtenerTipoDocSerie(idTipoDoc, nroSerie);
    }

    @Override
    public List ObtenerConceptoCurso(int idTipoDocPago, String serie, int nroDocumentoPago) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        //if(listCursos == null)
        listCursos = aluDAO.ObtenerConceptoCurso(idTipoDocPago, serie, nroDocumentoPago);
        return listCursos;
    }

    @Override
    public List ObtenerTodosAlumnosPorCurso(String codigo) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        return aluDAO.ObtenerTodosAlumnosPorCurso(codigo);
    }

    @Override
    public Cliente ObtenerPersonaPorDNI_Colegiatura(int tipoCliente, String dni, String codigoColegiatura) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        return aluDAO.ObtenerPersonaPorDNI_Colegiatura(tipoCliente, dni, codigoColegiatura);
    }

    @Override
    public String ObtenerContadorPorCodigoColegiatura(String codCole) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        return aluDAO.ObtenerContadorPorCodigoColegiatura(codCole);
    }

    @Override
    public boolean GuardarAlumnos(Alumnos alu) {
        try {
            AlumnoDAO aluDAO = AlumnoDAO.getInstance();

            aluDAO.GuardarAlumnos(alu);
            //this.GuardarAlumnos(alu);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return false;
        }
    }

    @Override
    public List ObtnerDocumentoPagoDetPorId(int idDocPagoDet) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        return aluDAO.ObtnerDocumentoPagoDetPorId(idDocPagoDet);
    }

    @Override
    public int ObtenerIdDocumentoPagoDetalle(int nroDocumentoPago, String serie, String codigoCurso) {
        try {
            AlumnoDAO aluDAO = AlumnoDAO.getInstance();
            int num = aluDAO.ObtenerIdDocumentoPagoDetalle(nroDocumentoPago, serie, codigoCurso);
            return num;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("bloque de código donde se trata el problema");
            return 0;
        }
//        if(listDocumentoPagoDet == null)
//            listDocumentoPagoDet = aluDAO.ObtenerIdDocumentoPagoDetalle(nroDocumentoPago, serie, codigoCurso);
//        return listDocumentoPagoDet;
    }

    @Override
    public List ObtenerPersonaPorCodigoColegiatura(String codigo) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        return aluDAO.ObtenerPersonaPorCodigoColegiatura(codigo);
    }

    @Override
    public List ObtenerAlumnosExcel(String codigoCurso, String desde, String hasta) {
        AlumnoDAO aluDAO = AlumnoDAO.getInstance();
        return aluDAO.ObtenerAlumnosExcel(codigoCurso, desde, hasta);
    }

    @Override
    public List ObtenerTodosEventos_EstadoHabil() {
        AlumnoDAO aDAO = AlumnoDAO.getInstance();
        List<Evento> l = aDAO.ObtenerTodosEventos_Habilitados();
        return l;
    }

    @Override
    public List ObtenerTodosEventos_Habiles() {
        SeguridadBO sBO = SeguridadBO.getINSTANCE();
        AlumnoDAO aDAO = AlumnoDAO.getInstance();
        List<Evento> l = aDAO.ObtenerTodosEventos();
        Date fechaServer = sBO.ObtenerFechaServidor();
        List<Evento> lFinal = new ArrayList();
        for (Evento e : l) {
            List<Inversion> lI = aDAO.ObtenerInversion_SegunEvento(e.getId());
            for (Inversion i : lI) {
                if (!i.getFechaInicio().after(fechaServer) && !i.getFechaFin().before(fechaServer)) {
                    lFinal.add(e);
                    break;
                }
            }
        }
        return lFinal;
    }

    @Override
    public List ObtenerTodosEventos() {
        AlumnoDAO aDAO = AlumnoDAO.getInstance();
        List<Evento> l = aDAO.ObtenerTodosEventos();
        return l;
    }

    @Override
    public List ObtenerTodosTipoParticipantes_SegunEvento(int idEvento) {
        AlumnoDAO aDAO = AlumnoDAO.getInstance();
        return aDAO.ObtenerTodosTipoParticipantes_SegunEvento(idEvento);
    }

    @Override
    public List ObtenerTodasModalidades_SegunEvento(int idEvento) {
        AlumnoDAO aDAO = AlumnoDAO.getInstance();
        return aDAO.ObtenerTodasModalidades_SegunEvento(idEvento);
    }

    @Override
    public List ObtenerTodasInversiones_SegunEvento(int idEvento) {
        AlumnoDAO aDAO = AlumnoDAO.getInstance();
        return aDAO.ObtenerTodasInversiones_SegunEvento(idEvento);
    }

    @Override
    public void EliminarAlumnos(Alumnos alu) {
        AlumnoDAO alumnosDAO = AlumnoDAO.getInstance();
        alumnosDAO.EliminarAlumno(alu);
    }

    @Override
    public Alumnos ObtenerAlumno(String nroDocumento) {
        for (Alumnos a : resultadoBusqueda) {
            if (a.getDni() != null) {
                if (a.getDni().equals(nroDocumento)) {
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public Alumnos ObtenerAlumnoPorCodigo(String codigoColegiatura) {
        for (Alumnos a : resultadoBusqueda) {
            if (a.getCodigo() != null) {
                if (a.getCodigo().equals(codigoColegiatura)) {
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public List<?> ObtenerParticipantes_SegunEvento(int idEvento) {
        AlumnoDAO alumnosDAO = AlumnoDAO.getInstance();
        return alumnosDAO.ObtenerParticipantes_SegunEvento(idEvento);
    }

    @Override
    public List<?> ObtenerCorreoParticipantes_SegunIdDocumentoPago(int idDocumentoPagoDet) {
        AlumnoDAO alumnosDAO = AlumnoDAO.getInstance();
        return alumnosDAO.ObtenerCorreoParticipantes_SegunIdDocumentoPago(idDocumentoPagoDet);
    }

    @Override
    public List<?> ObtenerParticipantes_SegunEventoFechaComprobante(int idEvento, String desde, String hasta) {
        AlumnoDAO alumnosDAO = AlumnoDAO.getInstance();
        return alumnosDAO.ObtenerParticipantes_SegunEventoFechaComprobante(idEvento, desde, hasta);
    }

}
