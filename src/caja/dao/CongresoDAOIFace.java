/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Congreso;
import caja.mapeo.entidades.CongresoConcepto;
import caja.mapeo.entidades.CongresoParticipantes;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface CongresoDAOIFace {
    public List BuscarCongresoNOMBRE(String busqueda);
    public void GuardarCongreso(Congreso o);
    public List ObtenerTodasCongresos();
    public List BuscarCongreso(String tipoDocumento);
    public void ActualizarCongreso(Congreso o);
    public void GuardarCongresoConcepto(CongresoConcepto cc);
    public void ActualizarCongresoConcepto(CongresoConcepto cc);
    public List ObtenerTodosCongresoConcepto();
    public List ObtenerTodosParticipantesCongresos(int idCongreso);
    public List VerificarSiConceptoPerteneceCongreso(int idConcepto);
    public void GuardarCongresoParticipantes(CongresoParticipantes cp);
    public List ObtenerTodosParticipantesCongreso(int idCongreso);
    public void ActualizarCongresoParticipantes(CongresoParticipantes cp);
}
