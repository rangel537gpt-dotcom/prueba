/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.CodigoBienServicioDetraccion;
import caja.mapeo.entidades.CodigoMedioPago;
import caja.mapeo.entidades.TipoTributo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class ManagerDAO implements ManagerDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<TipoTributo> resultadoBusqueda = null;

    private static ManagerDAO INSTANCE = new ManagerDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ManagerDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ManagerDAO();
                }
            }
        }
    }

    public static ManagerDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public ManagerDAO() {
    }

    @Override
    public List BuscarTipoTributoPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM TipoTributo WHERE nombre LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoTributo>) q.list();
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
    public List BuscarTipoTributoPorCodigo(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM TipoTributo WHERE codigoDocIde LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoTributo>) q.list();
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
    public List BuscarTipoTributoPorDescripcion(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM TipoTributo WHERE descripcion LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<TipoTributo>) q.list();
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
    public List ObtenerTodosCodigoBienServicioDetraccion() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM CodigoBienServicioDetraccion tt ORDER BY tt.codigo");
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
    public CodigoMedioPago obtenerCodigoMedioPago(String codigo) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM CodigoMedioPago tt WHERE tt.codigo like '" + codigo + "' ORDER BY tt.codigo");
            List l = q.list();
            CodigoMedioPago cmp = null;
            if (l.size() > 0) {
                cmp = (CodigoMedioPago) l.get(0);
            }
            transaction.commit();
            return cmp;
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
    public CodigoBienServicioDetraccion obtenerCodigoBienServicioDetraccion(String codigo) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM CodigoBienServicioDetraccion tt WHERE tt.codigo like '" + codigo + "' ORDER BY tt.codigo");
            List l = q.list();
            CodigoBienServicioDetraccion cmp = null;
            if (l.size() > 0) {
                cmp = (CodigoBienServicioDetraccion) l.get(0);
            }
            transaction.commit();
            return cmp;
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
    public List ObtenerTodosCodigoMedioPago() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM CodigoMedioPago tt ORDER BY tt.codigo");
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
    public List ObtenerUniversidad_SegunFiltro(String filtro) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Universidad u WHERE " + filtro + " ORDER BY u.nombre");
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
    public List ObtenerAfectaciones(int idTipoTributo) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM TipoAfectacion tt WHERE tt.tipoTributo.id = " + idTipoTributo + " ORDER BY tt.codigo");
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

    @Override
    public void GuardarObjeto(Object est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void ActualizarObjeto(Object est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(est);
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
