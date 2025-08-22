/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.EventoConceptoPago;
import caja.mapeo.entidades.Participante;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class EventoDAO implements EventoDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<Cliente> resultadoBusqueda = null;
    private static EventoDAO INSTANCE = new EventoDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (EventoDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EventoDAO();
                }
            }
        }
    }

    public static EventoDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public EventoDAO() {
    }

    @Override
    public List obtenerEventoConceptoPago_SegunIdInversion(int idInversion) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM EventoConceptoPago ec WHERE ec.inversion.id = :idInversion").setParameter("idInversion", idInversion);
            List<EventoConceptoPago> l = q.list();
            for (EventoConceptoPago ec : l) {
                Hibernate.initialize(ec.getConceptoPagoDetalle());
                Hibernate.initialize(ec.getInversion());
                Hibernate.initialize(ec.getInversion().getTipoParticipante());
                Hibernate.initialize(ec.getInversion().getModalidadPago());
                Hibernate.initialize(ec.getEvento());
            }
            transaction.commit();
            return l;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public List obtenerAsignacionEvento_SegunIdEvento(int idEvento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM AsignacionEvento ae WHERE ae.borrado = '1' AND ae.evento.id = :idEvento").setParameter("idEvento", idEvento);
            List l = q.list();
            transaction.commit();
            return l;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public List obtenerParticipante_SegunEventoCliente(int idEvento, int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Participante p WHERE p.borrado = '1' AND p.cliente.idCliente = :idCliente AND p.evento.id = :idEvento").setParameter("idEvento", idEvento).setParameter("idCliente", idCliente);
            List l = q.list();
            transaction.commit();
            return l;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public void GuardarEventoConcepto(EventoConceptoPago ec) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(ec);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public void GuardarParticipante(Participante p) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public void ActualizarObjeto(Object p) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.merge(p);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public void GuardarObjeto(Object p) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public void EliminarEventoConcepto(EventoConceptoPago ec) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(ec);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

}
