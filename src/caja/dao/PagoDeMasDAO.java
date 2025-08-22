/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.PagoDeMas;
import caja.mapeo.entidades.Reincorporacion;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class PagoDeMasDAO implements PagoDeMasDAOIFace {

    private Session sesion = null;
    private Transaction tx;

    private static PagoDeMasDAO INSTANCE = new PagoDeMasDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (PagoDeMasDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PagoDeMasDAO();
                }
            }
        }

    }

    public static PagoDeMasDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosPagoDeMas(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<PagoDeMas> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM PagoDeMas pdm WHERE pdm.cliente.idCliente = " + idCliente);
            resultadoBusqueda = q.list();
            /*for (PagoDeMas pdm : resultadoBusqueda) {
             Hibernate.initialize(pdm.getConceptoPagoDetalle());
             }*/
            tx.commit();
            return resultadoBusqueda;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Reincorporacion ObtenerReincorporacion(int idReincorporacion) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Reincorporacion rDATA = (Reincorporacion) sesion.get(Reincorporacion.class, idReincorporacion);
            Hibernate.initialize(rDATA.getCliente());
            tx.commit();
            return rDATA;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

}
