/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class ConceptoPagoDetalleDAO implements ConceptoPagoDetalleDAOIFace{

    private Session session = null;
    private Transaction transaction;
    
    List<ConceptoPagoDetalle> resultadoBusqueda = null;
    private static ConceptoPagoDetalleDAO INSTANCE = new ConceptoPagoDetalleDAO();

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ConceptoPagoDetalleDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConceptoPagoDetalleDAO();
                }
            }
        }
    }

    public static ConceptoPagoDetalleDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    public ConceptoPagoDetalleDAO()
    {}
    
    @Override
    public List BuscarConceptoPagoPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM ConceptoPagoDetalle WHERE descripcion LIKE '%" + busqueda + "%'");
            List resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public List BuscarConceptoPagoPorCodigo(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM ConceptoPagoDetalle WHERE codigo LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public List BuscarConceptoPagoPorTipoCodigo(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM ConceptoPagoDetalle WHERE tipoCodigo LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public List BuscarConceptoPagoPorCargo(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM ConceptoPagoDetalle WHERE cargo LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public List BuscarConceptoPagPorAbono(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM ConceptoPagoDetalle WHERE abono LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public void GuardarConceptoPagoDetalle(ConceptoPagoDetalle conPagoDet) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(conPagoDet);
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
    public void ActualizarConceptoPagoDetalle(ConceptoPagoDetalle conPagoDet) {
        
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(conPagoDet);
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
    public void EliminarConceptoPagoDetalle(ConceptoPagoDetalle conPagoDet) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(conPagoDet);
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
