/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



/**
 *
 * @author juan carlos
 */
public class TrabajadorDAO implements TrabajadorDAOIFace{
    Session session = null;
    Transaction transaction;
    //List<Persona> resultadoBusqueda = null;
    private static TrabajadorDAO INSTANCE = new TrabajadorDAO();

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (TrabajadorDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TrabajadorDAO();
                }
            }
        }
    }
    
    public static TrabajadorDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public TrabajadorDAO() {
    }
    
    /*@Override
    public List BuscarPorApePat(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("From Persona Where apePat LIKE '%" + busqueda + "%'");

            resultadoBusqueda = (List<Persona>) q.list();
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
    public List BuscarPorApeMat(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("From Persona Where apeMat LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Persona>) q.list();
            transaction.commit();
            return resultadoBusqueda;
        } 
        
        catch (RuntimeException e) 
        {
            try {
                transaction.rollback();
            } 
            catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public List BuscarProNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("From Persona Where nombre LIKE '%" + busqueda + "%'");
            
            resultadoBusqueda = (List<Persona>) q.list();
            transaction.commit();
            
            return resultadoBusqueda;
        } 
        catch (RuntimeException e) 
        {
            try {
                transaction.rollback();
            } 
            catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public List BuscarPorDNI(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("From Persona Where dni LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Persona>) q.list();
            transaction.commit();
            
            return resultadoBusqueda;
        } 
        catch (RuntimeException e) 
        {
            try {
                transaction.rollback();
            } 
            catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public void GuardarPersona(Persona p) {
        try{
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            session.save(p);
            transaction.commit();
        }
        catch(RuntimeException e)
        {
            try{
                transaction.rollback();
            }
            catch(RuntimeException rbe)
            {
                rbe.printStackTrace();
            }
            throw e;
        }
    }*/
    
}
