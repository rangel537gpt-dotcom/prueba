/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.TipoNotaDAO;
import caja.mapeo.entidades.TipoNota;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class TipoNotaBO implements TipoNotaBOIFace {

    private static TipoNotaBO INSTANCE = new TipoNotaBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (TipoNotaBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TipoNotaBO();
                }
            }
        }
    }

    public static TipoNotaBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public TipoNotaBO() {

    }

    @Override
    public boolean GuardarTipoNotaCredito(TipoNota u) {
        try {
            TipoNotaDAO uDAO = TipoNotaDAO.getInstance();
            uDAO.GuardarTipoNotaCredito(u);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean ActualizarTipoNotaCredito(TipoNota u) {
        try {
            TipoNotaDAO uDAO = TipoNotaDAO.getInstance();
            uDAO.ActualizarTipoNotaCredito(u);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List BuscarTodosTipoNotaCredito() {
        TipoNotaDAO uDAO = TipoNotaDAO.getInstance();
        return uDAO.BuscarTodosTipoNotaCredito();
    }

    @Override
    public List BuscarTipoNotaCredito_SegunNombre(String nombre) {
        TipoNotaDAO uDAO = TipoNotaDAO.getInstance();
        return uDAO.BuscarTipoNotaCredito_SegunNombre(nombre);
    }

}
