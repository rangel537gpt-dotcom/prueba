/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.TipoNota;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class TipoNotaDAO implements TipoNotaDAOIFace {

    Session session = null;
    Transaction transaction = null;
    private static TipoNotaDAO INSTANCE = new TipoNotaDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (TipoNotaDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TipoNotaDAO();
                }
            }
        }
    }

    public static TipoNotaDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public TipoNotaDAO() {
    }

    @Override
    public List BuscarTodosTipoNotaCredito() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM TipoNota u WHERE u.tipoNota = 1 ORDER BY u.codigo");
            List listado = q.list();
            transaction.commit();
            return listado;
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
    public List BuscarTipoNotaCredito_SegunNombre(String nombre) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM TipoNotaCredito u WHERE u.descripcion like '%" + nombre + "%' ORDER BY u.descripcion");
            List listado = q.list();
            transaction.commit();
            return listado;
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
    public void GuardarTipoNotaCredito(TipoNota u) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(u);
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
    public void ActualizarTipoNotaCredito(TipoNota u) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(u);
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
