/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

//import caja.entidades.Alumno;
import caja.mapeo.entidades.Alumnos;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.TipoDocSerie;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface AlumnoDAOIFace {
    
    public List BuscarAlumnoPorCodigoCurso(String busqueda);
    public List BuscarAlumnoPorNombreCurso(String busqueda);
    public List BuscarAlumnoPorNroDocumento(String busqueda);
    public List BuscarAlumnoPorCodigoColegiatura(String busqueda);
    public List BuscarAlumnoPorApellidoPaterno(String busqueda);
    public List BuscarAlumnoPorApellidoMaterno(String busqueda);
    
    public List ObtnerDocumentoPagoDetPorId(int idDocPagoDet); 
    
    public Cliente ObtenerPersonaPorDNI_Colegiatura(int tipoCliente, String dni, String codigoColegiatura);
    public List ObtenerPersonaPorCodigoColegiatura(String codigo);
    
    public String ObtenerContadorPorCodigoColegiatura(String codCole);
    public List ObtenerTodosConceptoPagoCursos();
    public List ObtenerTodosClientes();  
    public List ObtenerTodosTipoDocPago();
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc, int nroSerie);
    public List ObtenerConceptoCurso(int idTipoDocPago, String serie, int nroDocumentoPago);
    public List ObtenerTodosAlumnosPorCurso(String codigo);
    public void GuardarAlumnos(Alumnos alu);
     public void EliminarAlumno(Alumnos alu);
    
    public int ObtenerIdDocumentoPagoDetalle(int nroDocumentoPago, String serie, String codigoCurso);
    public List ObtenerAlumnosExcel(String codigoCurso, String desde, String hasta);
    public List BuscarAlumnoPorIdComprobantePago(int idDocumentoPago);
    public List ObtenerTodosEventos();
    public List ObtenerInversion_SegunEvento(int idEvento);
    public List ObtenerTodosTipoParticipantes_SegunEvento(int idEvento);
    public List ObtenerTodasModalidades_SegunEvento(int idEvento);
    public List ObtenerTodasInversiones_SegunEvento(int idEvento);
    public List ObtenerParticipantes_SegunEvento(int idEvento);
    public List ObtenerParticipantes_SegunEventoFechaComprobante(int idEvento, String desde, String hasta);
    public List ObtenerTodosEventos_Habilitados();
    public List ObtenerCorreoParticipantes_SegunIdDocumentoPago(int idParticipante);
        
}
