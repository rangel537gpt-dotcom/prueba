/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.EventoConceptoPago;
import caja.mapeo.entidades.Participante;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface EventoBOIFace {
    public void GuardarEventoConcepto(EventoConceptoPago ec);
    public void EliminarEventoConcepto(EventoConceptoPago ec);
    public List obtenerEventoConceptoPago_SegunIdInversion(int idInversion);
    public void GuardarParticipante(Participante p);
    public List obtenerAsignacionEvento_SegunIdEvento(int idEvento);
    public List obtenerParticipante_SegunEventoCliente(int idEvento, int idCliente);
    public void GuardarObjeto(Object p);
    public void ActualizarObjeto(Object p);
}
