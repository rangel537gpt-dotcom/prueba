/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.ReincorporacionDAO;
import caja.mapeo.entidades.Reincorporacion;
import java.util.List;

/**
 *
 * @author user
 */
public class ReincorporacionBO implements ReincorporacionBOIFace {

    private static ReincorporacionBO INSTANCE = new ReincorporacionBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ReincorporacionBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReincorporacionBO();
                }
            }
        }

    }

    public static ReincorporacionBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosReincorporaciones(int idCliente) {
        ReincorporacionDAO rDAO = ReincorporacionDAO.getInstance();
        return rDAO.ObtenerTodosReincorporaciones(idCliente);
    }

    @Override
    public Reincorporacion ObtenerReincorporacion(int idReincorporacion) {
        ReincorporacionDAO rDAO = ReincorporacionDAO.getInstance();
        return rDAO.ObtenerReincorporacion(idReincorporacion);
    }

    @Override
    public List ObtenerDetalleReincorporacion(int idReincorporacion) {
        ReincorporacionDAO rDAO = ReincorporacionDAO.getInstance();
        return rDAO.ObtenerDetalleReincorporacion(idReincorporacion);
    }

    @Override
    public List ObtenerTodosReincorporacionesActivosCliente(int idCliente) {
        try {
            ReincorporacionDAO rDAO = ReincorporacionDAO.getInstance();
            return rDAO.ObtenerTodosReincorporacionActivosCliente(idCliente);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en el Ingreso del Financiamiento");
            return null;
        }
    }

    @Override
    public List ObtenerTodasReincoporacionesPendientes(int idCliente) {
        ReincorporacionDAO rDAO = ReincorporacionDAO.getInstance();
        return rDAO.ObtenerTodasReincoporacionesPendientes(idCliente);
    }

}
