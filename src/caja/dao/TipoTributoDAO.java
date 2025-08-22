/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.TipoTributo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class TipoTributoDAO implements TipoTributoDAOIFace{
    
    Session session = null;
    Transaction transaction = null;
    
    List<TipoTributo> resultadoBusqueda = null;
   
    private static TipoTributoDAO INSTANCE = new TipoTributoDAO();
    
    
    public static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized(TipoTributoDAO.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new TipoTributoDAO();
                }    
            }
        }
    }
    
    
    public static TipoTributoDAO getInstance()
    {
        createInstance();
        return INSTANCE;
    }
    
    
    public TipoTributoDAO()
    {}

    @Override
    public List BuscarTipoTributoPorNombre(String busqueda) {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if(resultadoBusqueda != null)
            {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("FROM TipoTributo WHERE nombre LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoTributo>) q.list();
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
    public List BuscarTipoTributoPorCodigo(String busqueda) {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if(resultadoBusqueda != null)
            {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("FROM TipoTributo WHERE codigoDocIde LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoTributo>) q.list();
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
    public List BuscarTipoTributoPorDescripcion(String busqueda) {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            
            Query q = null;
            if(resultadoBusqueda != null)
            {
                resultadoBusqueda.clear();
            }
            
            q = session.createQuery("FROM TipoTributo WHERE descripcion LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoTributo>) q.list();
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
    public List ObtenerTodosTipoTributo() {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM TipoTributo tt ORDER BY tt.codigo");
            List l = q.list();
            transaction.commit();
            return l;
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
    public List ObtenerAfectaciones(int idTipoTributo) {
        try
        {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM TipoAfectacion tt WHERE tt.tipoTributo.id = " + idTipoTributo + " ORDER BY tt.codigo");
            List l = q.list();
            transaction.commit();
            return l;
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
    public void GuardarTipoTributo(TipoTributo tdi) {
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
    public void ActualizarTipoTributo(TipoTributo tdi) {
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
