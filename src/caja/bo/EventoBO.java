/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.EventoDAO;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.EventoConceptoPago;
import caja.mapeo.entidades.Participante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class EventoBO implements EventoBOIFace {

    private static EventoBO INSTANCE = new EventoBO();
    private List<Cliente> resultadoBusqueda;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (EventoBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EventoBO();
                }
            }
        }
    }

    public static EventoBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public EventoBO() {
        resultadoBusqueda = new ArrayList();
    }

    public List<Cliente> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Cliente> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List obtenerEventoConceptoPago_SegunIdInversion(int idInversion) {
        EventoDAO eDAO = EventoDAO.getInstance();
        return eDAO.obtenerEventoConceptoPago_SegunIdInversion(idInversion);
    }

    @Override
    public List obtenerAsignacionEvento_SegunIdEvento(int idEvento) {
        EventoDAO eDAO = EventoDAO.getInstance();
        return eDAO.obtenerAsignacionEvento_SegunIdEvento(idEvento);
    }

    @Override
    public List obtenerParticipante_SegunEventoCliente(int idEvento, int idCliente) {
        EventoDAO eDAO = EventoDAO.getInstance();
        return eDAO.obtenerParticipante_SegunEventoCliente(idEvento, idCliente);
    }

    @Override
    public void GuardarEventoConcepto(EventoConceptoPago ec) {
        EventoDAO eDAO = EventoDAO.getInstance();
        eDAO.GuardarEventoConcepto(ec);
    }

    @Override
    public void GuardarParticipante(Participante p) {
        EventoDAO eDAO = EventoDAO.getInstance();
        eDAO.GuardarParticipante(p);
    }

    @Override
    public void ActualizarObjeto(Object p) {
        EventoDAO eDAO = EventoDAO.getInstance();
        eDAO.ActualizarObjeto(p);
    }

    @Override
    public void GuardarObjeto(Object p) {
        EventoDAO eDAO = EventoDAO.getInstance();
        eDAO.GuardarObjeto(p);
    }

    @Override
    public void EliminarEventoConcepto(EventoConceptoPago ec) {
        EventoDAO eDAO = EventoDAO.getInstance();
        eDAO.EliminarEventoConcepto(ec);
    }

}
