/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.DeudasDAO;
import caja.dao.ReincorporacionDAO;
import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.Reincorporacion;
import java.util.List;

/**
 *
 * @author user
 */
public class DeudasBO implements DeudasBOIFace {

    private static DeudasBO INSTANCE = new DeudasBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (DeudasBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DeudasBO();
                }
            }
        }

    }

    public static DeudasBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodasDeudas(int idCliente) {
        DeudasDAO dDAO = DeudasDAO.getInstance();
        return dDAO.ObtenerTodosDeudas(idCliente);
    }

    @Override
    public void ActualizarDeuda(Deuda d) {
        DeudasDAO dDAO = DeudasDAO.getInstance();
        dDAO.ActualizarDeuda(d);
    }

    @Override
    public Deuda ObtenerDeudaClienteConcepto(int idCliente, int idConceptoPagoDetalle) {
        DeudasDAO dDAO = DeudasDAO.getInstance();
        return dDAO.ObtenerDeudaClienteConcepto(idCliente, idConceptoPagoDetalle);
    }

    @Override
    public List ObtenerTodasDeudasPendientes(int idCliente) {
        DeudasDAO dDAO = DeudasDAO.getInstance();
        return dDAO.ObtenerTodasDeudasPendientes(idCliente);
    }

    @Override
    public Reincorporacion ObtenerReincorporacion(int idReincorporacion) {
        ReincorporacionDAO rDAO = ReincorporacionDAO.getInstance();
        return rDAO.ObtenerReincorporacion(idReincorporacion);
    }

}
