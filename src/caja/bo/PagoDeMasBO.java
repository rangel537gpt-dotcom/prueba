/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.DeudasDAO;
import caja.dao.PagoDeMasDAO;
import caja.dao.ReincorporacionDAO;
import caja.mapeo.entidades.Reincorporacion;
import java.util.List;

/**
 *
 * @author user
 */
public class PagoDeMasBO implements PagoDeMasBOIFace {

    private static PagoDeMasBO INSTANCE = new PagoDeMasBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (PagoDeMasBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PagoDeMasBO();
                }
            }
        }

    }

    public static PagoDeMasBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosPagoDeMas(int idCliente) {
        PagoDeMasDAO pdmDAO = PagoDeMasDAO.getInstance();
        return pdmDAO.ObtenerTodosPagoDeMas(idCliente);
    }

    @Override
    public Reincorporacion ObtenerReincorporacion(int idReincorporacion) {
        ReincorporacionDAO rDAO = ReincorporacionDAO.getInstance();
        return rDAO.ObtenerReincorporacion(idReincorporacion);
    }

}
