/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class UsuarioDAO implements UsuarioDAOIFace {

    Session session = null;
    Transaction transaction = null;

    //List<Cliente> resultadoBusqueda = null;
    List<Usuario> resultadoBusqueda = null;
    private static UsuarioDAO INSTANCE = new UsuarioDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (UsuarioDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UsuarioDAO();
                }
            }
        }
    }

    public static UsuarioDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public UsuarioDAO() {
    }

    @Override
    public List BuscarUsuarioPorRol(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            q = session.createQuery("FROM Usuario WHERE rolUsuario LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Usuario>) q.list();
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
    public List BuscarUsuarioPorLogin(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            q = session.createQuery("FROM Usuario WHERE login LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Usuario>) q.list();
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
    public List BuscarUsuarioPorMac(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            q = session.createQuery("FROM Usuario WHERE mac LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Usuario>) q.list();
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
    public void GuardarUsuario(Usuario u) {
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
    public void ActualizarUsuario(Usuario u) {
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

    @Override
    public List ObtenerTodosRoles() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "SELECT DISTINCT u.rolUsuario FROM Usuario u ORDER BY u.rolUsuario";
            Query q = session.createQuery(hql);
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
    public List ObtenerTodosTipoDocumentoSerie_Asignados(int idUsuario) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT UsuarioTipoDocumentoSerie.Id,TipoDocPago.NombreDocPago,Serie.Serie\n"
                    + "FROM UsuarioTipoDocumentoSerie,TipoDocSerie,TipoDocPago,Serie\n"
                    + "WHERE UsuarioTipoDocumentoSerie.IdTipoDocSerie = TipoDocSerie.Id and\n"
                    + "TipoDocSerie.IdTipoDoc = TipoDocPago.IdTipoDocPago and\n"
                    + "TipoDocSerie.IdSerie = Serie.IdSerie and\n"
                    + "UsuarioTipoDocumentoSerie.IdUsuario = " + idUsuario;
            Query q = session.createSQLQuery(sql);
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
    public int guardarTipoDocumentoSerieUsuario(int idUsuario, int idTipoDocSerie) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "INSERT INTO USUARIOTIPODOCUMENTOSERIE(IDUSUARIO,IDTIPODOCSERIE,ESTADO)VALUES(" + idUsuario + "," + idTipoDocSerie + ",'A')";
            Query q = session.createSQLQuery(sql);
            q.executeUpdate();
            sql = "SELECT ID FROM USUARIOTIPODOCUMENTOSERIE ORDER BY ID DESC";
            q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            int id = (int)q.uniqueResult();
            transaction.commit();
            return id;
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
    public void eliminarTipoDocumentoSerieUsuario(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "DELETE FROM USUARIOTIPODOCUMENTOSERIE WHERE ID = " + id;
            Query q = session.createSQLQuery(sql);
            q.executeUpdate();
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
    public void EliminarUsuario(Usuario u) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(u);
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
