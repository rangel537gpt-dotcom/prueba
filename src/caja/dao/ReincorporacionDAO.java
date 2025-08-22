/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Reincorporacion;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class ReincorporacionDAO implements ReincorporacionDAOIFace {

    private Session sesion = null;
    private Transaction tx;

    private static ReincorporacionDAO INSTANCE = new ReincorporacionDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ReincorporacionDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReincorporacionDAO();
                }
            }
        }

    }

    public static ReincorporacionDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosReincorporaciones(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Reincorporacion> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM Reincorporacion r WHERE r.cliente.idCliente = " + idCliente + " ORDER BY r.nroReincorporacion DESC");
            resultadoBusqueda = q.list();
            /*for (FinanciamientoDocumentoPago fdp : resultadoBusqueda) {
             Hibernate.initialize(fdp.getAnioMes());
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

    @Override
    public List ObtenerDetalleReincorporacion(int idReincorporacion) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<ReincorporacionDocumentoPago> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM ReincorporacionDocumentoPago rdp WHERE rdp.reincorporacion.id = " + idReincorporacion + " ORDER BY rdp.nroCuota ASC");
            resultadoBusqueda = q.list();
            for (ReincorporacionDocumentoPago rdp : resultadoBusqueda) {
                if (rdp.getDocumentoPagoDet() != null) {
                    Hibernate.initialize(rdp.getDocumentoPagoDet());
                    Hibernate.initialize(rdp.getDocumentoPagoDet().getDocumentoPago());
                    Hibernate.initialize(rdp.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getTipoDocPago());
                }
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
    public List ObtenerTodosReincorporacionActivosCliente(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<ReincorporacionDocumentoPago> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM ReincorporacionDocumentoPago rdp WHERE rdp.estado = 'RS' AND rdp.reincorporacion.cliente.idCliente=" + idCliente + " ORDER BY rdp.nroCuota ASC");
            resultadoBusqueda = q.list();
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
    public List ObtenerTodasReincoporacionesPendientes(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<ReincorporacionDocumentoPago> resultadoBusqueda = null;
            Query q = null;
            int idReincoporacion = 0;
            q = sesion.createQuery("SELECT r.id FROM Reincorporacion r WHERE r.cliente.idCliente = " + idCliente + " ORDER BY r.nroReincorporacion DESC");
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                idReincoporacion = (int) q.uniqueResult();
            }
            q = sesion.createQuery("FROM ReincorporacionDocumentoPago rdp WHERE rdp.estado = 'RS' AND rdp.reincorporacion.id = " + idReincoporacion + " ORDER BY rdp.fechaVencimiento ASC");
            resultadoBusqueda = q.list();
            /*for (FinanciamientoDocumentoPago fdp : resultadoBusqueda) {
             Hibernate.initialize(fdp.getAnioMes());
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

}
