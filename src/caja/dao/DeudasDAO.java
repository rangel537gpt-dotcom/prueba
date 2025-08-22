/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Deuda;
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
public class DeudasDAO implements DeudasDAOIFace {

    private Session sesion = null;
    private Transaction tx;

    private static DeudasDAO INSTANCE = new DeudasDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (DeudasDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DeudasDAO();
                }
            }
        }

    }

    public static DeudasDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosDeudas(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Deuda> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM Deuda d WHERE d.cliente.idCliente = " + idCliente + " ORDER BY d.conceptoPagoDetalle.descripcion DESC");
            resultadoBusqueda = q.list();
            for (Deuda d : resultadoBusqueda) {
                Hibernate.initialize(d.getConceptoPagoDetalle());
            }
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
    public void ActualizarDeuda(Deuda d) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(d);
            tx.commit();
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
    public List ObtenerTodasDeudasPendientes(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Deuda> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM Deuda d WHERE d.estado = 'DP' AND d.cliente.idCliente = " + idCliente + " ORDER BY d.conceptoPagoDetalle.descripcion DESC");
            resultadoBusqueda = q.list();
            for (Deuda d : resultadoBusqueda) {
                Hibernate.initialize(d.getConceptoPagoDetalle());
            }
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
    public Deuda ObtenerDeudaClienteConcepto(int idCliente, int idConceptoPagoDetalle) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Deuda d = null;
            Query q = sesion.createQuery("FROM Deuda d WHERE d.conceptoPagoDetalle.idConceptoPagoDetalle = " + idConceptoPagoDetalle + " AND d.cliente.idCliente = " + idCliente);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                d = (Deuda)q.uniqueResult();
            }
            tx.commit();
            return d;
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
