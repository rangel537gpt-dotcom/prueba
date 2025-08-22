/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.AnioMesDAO;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.AnioMesConcepto;
import java.util.List;

/**
 *
 * @author user
 */
public class AnioMesBO implements AnioMesBOIFace {

    private List listaAnioMes;
    private static AnioMesBO INSTANCE = new AnioMesBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AnioMesBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AnioMesBO();
                }
            }
        }
    }

    public static AnioMesBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosMesAnio() {
        if (this.listaAnioMes == null) {
            AnioMesDAO amDAO = AnioMesDAO.getInstance();
            this.listaAnioMes = amDAO.ObtenerTodosMesAnio();
        }
        return this.listaAnioMes;
    }

    @Override
    public AnioMes ObtenerAnioMes(int anio, int mes) {
        List<AnioMes> lAnioMes = this.ObtenerTodosMesAnio();
        for (AnioMes am : lAnioMes) {
            if (am.getAnio() == anio && am.getMes() == mes) {
                return am;
            }
        }
        return null;
    }

    @Override
    public void InsetarAnioMes(AnioMes am) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        amDAO.InsertarAnioMes(am);
    }

    @Override
    public void InsertarAnioMesConcepto(AnioMesConcepto amc) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        amDAO.InsertarAnioMesConcepto(amc);
    }

    @Override
    public List ObtenerConceptosCuota(int desde, int hasta) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerConceptosCuota(desde, hasta);
    }

    @Override
    public List ObtenerConceptosCuotaSociedadArequipa(int desde, int hasta) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerConceptosCuotaSociedadArequipa(desde, hasta);
    }

    @Override
    public List ObtenerConceptosCuotaSociedadLima(int desde, int hasta) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerConceptosCuotaSociedadLima(desde, hasta);
    }

    @Override
    public List ObtenerConceptosCuotaVitalicio(int desde, int hasta) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerConceptosCuotaVitalicio(desde, hasta);
    }

    @Override
    public Object ObtenerPrimerConceptosCuota(int idConcepto, int desde, int hasta) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerPrimerConceptosCuota(idConcepto, desde, hasta);
    }

    @Override
    public Object ObtenerUltimoConceptosCuota(int idConcepto, int desde, int hasta) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerUltimoConceptosCuota(idConcepto, desde, hasta);
    }

    @Override
    public Object ObtenerMontoConceptoDetallado(int idAnioMes, int idConcepto) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerMontoConceptoDetallado(idAnioMes, idConcepto);
    }

    @Override
    public Object ObtenerMontoConceptoDetalladoVitalicio(int idAnioMes, int idConcepto) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerMontoConceptoDetalladoVitalicio(idAnioMes, idConcepto);
    }

    @Override
    public Object ObtenerMontoSociedadConceptoDetallado(int idAnioMes, int idConcepto) {
        AnioMesDAO amDAO = AnioMesDAO.getInstance();
        return amDAO.ObtenerMontoSociedadConceptoDetallado(idAnioMes, idConcepto);
    }

    @Override
    public AnioMes ObtenerAnioMesSegunNroOrden(int nroOrden) {
        List<AnioMes> lAnioMes = this.ObtenerTodosMesAnio();
        for (AnioMes am : lAnioMes) {
            if (am.getNroOrden() == nroOrden) {
                return am;
            }
        }
        return null;
    }

}
