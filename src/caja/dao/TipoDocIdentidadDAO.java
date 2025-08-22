/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.TipoDocIdentidad;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class TipoDocIdentidadDAO implements TipoDocIdentidadDAOIFace{
    
    Session session = null;
    Transaction transaction = null;
    
    List<TipoDocIdentidad> resultadoBusqueda = null;
   
    private static TipoDocIdentidadDAO INSTANCE = new TipoDocIdentidadDAO();
    
    
    public static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized(TipoDocIdentidadDAO.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new TipoDocIdentidadDAO();
                }    
            }
        }
    }
    
    
    public static TipoDocIdentidadDAO getInstance()
    {
        createInstance();
        return INSTANCE;
    }
    
    
    public TipoDocIdentidadDAO()
    {}

    @Override
    public List BuscarTipoDocIdentidadPorNombre(String busqueda) {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if(resultadoBusqueda != null)
            {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("FROM TipoDocIdentidad WHERE nombre LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoDocIdentidad>) q.list();
            transaction.commit();
            return resultadoBusqueda;
        }
        catch(RuntimeException e)
        {
              try
              {
                  transaction.rollback();
              }
              catch(RuntimeException rbe)
              {
                  rbe.printStackTrace();
              }
              throw e;
        }
    }

    @Override
    public List BuscarTipoDocIdentidadPorCodigo(String busqueda) {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if(resultadoBusqueda != null)
            {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("FROM TipoDocIdentidad WHERE codigoDocIde LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoDocIdentidad>) q.list();
            transaction.commit();
            return resultadoBusqueda;
        }
        catch(RuntimeException e)
        {
              try
              {
                  transaction.rollback();
              }
              catch(RuntimeException rbe)
              {
                  rbe.printStackTrace();
              }
              throw e;
        }
    }
    
    
    @Override
    public List BuscarTipoDocIdentidadPorDescripcion(String busqueda) {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if(resultadoBusqueda != null)
            {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("FROM TipoDocIdentidad WHERE descripcion LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoDocIdentidad>) q.list();
            transaction.commit();
            return resultadoBusqueda;
        }
        catch(RuntimeException e)
        {
              try
              {
                  transaction.rollback();
              }
              catch(RuntimeException rbe)
              {
                  rbe.printStackTrace();
              }
              throw e;
        }
    }

    @Override
    public void GuardarTipoDocIdentidad(TipoDocIdentidad tdi) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(tdi);
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
    public void ActualizarTipoDocIdentidad(TipoDocIdentidad tdi) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(tdi);
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
