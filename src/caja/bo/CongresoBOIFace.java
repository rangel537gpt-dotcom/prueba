/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

//import cajachica.mapeo.entidades.Dependencia;
import caja.mapeo.entidades.Congreso;
import caja.mapeo.entidades.CongresoConcepto;
import caja.mapeo.entidades.CongresoParticipantes;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface CongresoBOIFace {
    public List BuscarCongreso(String busqueda, int tipoBusqueda);
    public boolean GuardarCongreso(Congreso g);
    public List ObtenerTodasCongresos();
    public List BuscarCongreso(String tipoDocumento);
    public boolean ActualizarCongreso(Congreso o);
    public boolean GuardarCongresoConcepto(CongresoConcepto cc);
    public boolean ActualizarCongresoConcepto(CongresoConcepto cc);
    public List ObtenerTodosCongresoConcepto();
    public List ObtenerTodosParticipantesCongresos(int idCongreso);
    public List VerificarSiConceptoPerteneceCongreso(int idConcepto);
    public boolean GuardarCongresoParticipantes(CongresoParticipantes cp);
    public List ObtenerTodosParticipantesCongreso(int idCongreso);
    public boolean ActualizarCongresoParticipantes(CongresoParticipantes cp);
    public void ExportarArchivoExcelParticipantesCongreso(int idCongreso);
}
