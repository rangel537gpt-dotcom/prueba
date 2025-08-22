/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

//import caja.entidades.Alumno;
import caja.mapeo.entidades.Alumnos;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.TipoDocSerie;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface AlumnoBOIFace {
    public List BuscarAlumno(String busqueda, int tipoBusqueda);
//    public void GuardarAlumno(Alumno a, String dniAlumno);
//    public Alumno ObtenerContador(String dniAlumno);
//    public void ActualizarAlumno(Alumno a, String dniAlumno);
    
    public List ObtnerDocumentoPagoDetPorId(int idDocPagoDet);
    public Cliente ObtenerPersonaPorDNI_Colegiatura(int tipoCliente, String dni, String codigoColegiatura);
    public Alumnos ObtenerAlumno(String nroDocumento);
    public Alumnos ObtenerAlumnoPorCodigo(String codigoColegiatura);
    //public List ObtenerPersonaPorDNI(String dni);
    public List ObtenerPersonaPorCodigoColegiatura(String codigo);
    
    public String ObtenerContadorPorCodigoColegiatura(String codCole);
    public List ObtenerTodosConceptoPagoCursos();
    public List ObtenerTodosClientes();
    public List ObtenerTodosTipoDocPago();
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc,int nroSerie);
    public List ObtenerConceptoCurso(int idTipoDocPago, String serie, int nroDocumentoPago);
    public List ObtenerTodosAlumnosPorCurso(String codigo);
    
    public int ObtenerIdDocumentoPagoDetalle(int nroDocumentoPago, String serie, String codigoCurso);
    
    public boolean GuardarAlumnos(Alumnos alu);
    public void EliminarAlumnos(Alumnos alu);
    public List ObtenerAlumnosExcel(String codigoCurso, String desde, String hasta);
    public List BuscarAlumnoPorIdComprobantePago(int idComprobantePago);
    public List ObtenerTodosEventos_Habiles();
    public List ObtenerTodosTipoParticipantes_SegunEvento(int idEvento);
    public List ObtenerTodasModalidades_SegunEvento(int idEvento);
    public List ObtenerTodasInversiones_SegunEvento(int idEvento);
    public List ObtenerTodosEventos();
    public List<?> ObtenerParticipantes_SegunEvento(int idEvento);
    public List<?> ObtenerParticipantes_SegunEventoFechaComprobante(int idEvento, String desde, String hasta);
    public List ObtenerTodosEventos_EstadoHabil();
    public List<?> ObtenerCorreoParticipantes_SegunIdDocumentoPago(int idDocumentoPagoDet);
    
}
