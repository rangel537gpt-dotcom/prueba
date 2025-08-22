/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.TipoDocPago;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class TipoDocPagoDAO implements TipoDocPagoDAOIFace {

     private Session session = null;
    private Transaction transaction;
    
    List<TipoDocPago> resultadoBusqueda = null;
    private static TipoDocPagoDAO INSTANCE = new TipoDocPagoDAO();

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (TipoDocPagoDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TipoDocPagoDAO();
                }
            }
        }
    }

    public static TipoDocPagoDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    public TipoDocPagoDAO()
    {}

    
    
    @Override
    public List BuscarTipoDocPagoPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM TipoDocPago WHERE nombreDocPago LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoDocPago>) q.list();
            transaction.commit();
            return resultadoBusqueda;

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
    public List BuscarTipoDocPagoPorCodigo(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM TipoDocPago WHERE codigoDocPago LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoDocPago>) q.list();
            transaction.commit();
            return resultadoBusqueda;

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
    public void GuardarTipoDocPago(TipoDocPago tipoDoc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(tipoDoc);
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
    public void ActualizarTipoDocPago(TipoDocPago docPago) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(docPago);
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
    public List ObtenerTodosTipoDocPago() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM TipoDocPago");
            resultadoBusqueda = (List<TipoDocPago>) q.list();
            transaction.commit();
            return resultadoBusqueda;

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
    public List ObtenerSeries(int idTipoDocumento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("SELECT td.serie FROM TipoDocSerie td WHERE td.tipoDocPago.idTipoDocPago = " + idTipoDocumento);
            List resultadoBusqueda = q.list();
            transaction.commit();
            return resultadoBusqueda;

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
