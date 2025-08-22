/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteSociedadMiembro;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class SociedadDAO implements SociedadDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<Cliente> resultadoBusqueda = null;
    private static SociedadDAO INSTANCE = new SociedadDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (SociedadDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SociedadDAO();
                }
            }
        }
    }

    public static SociedadDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public SociedadDAO() {
    }

    @Override
    public List BuscarSociedadPorCodigo(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'S' AND c.scodigoSoc LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public List BuscarSociedadPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'S' AND c.snombreSociedad LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public List BuscarSociedadPorRUC(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'S' AND c.sruc LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public List BuscarSociedadPorCondicion(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'S' AND c.scondicion LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public void GuardarSociedad(Cliente soc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(soc);
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
    public void ActualizarSociedad(Cliente soc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(soc);
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
    public void EliminarSociedad(Cliente soc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(soc);
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
    public List ObtenerSociedadUbigeoDepartamento() {
        try {

            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            q = session.createQuery("FROM UbigeoDepartamento");
            resultadoBusqueda1 = (List<UbigeoDepartamento>) q.list();
            transaction.commit();
            return resultadoBusqueda1;

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
    public List ObtenerSociedadUbigeoProvincia(int idDepartamento) {
        try {

            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            q = session.createQuery("FROM UbigeoProvincia up WHERE ubigeoDepartamento.idDepartamento =" + idDepartamento);
            //"FROM UbigeoProvincia up WHERE ubigeoDepartamento.idDepartamento =:aqui").setParameter("aqui",idDepartamento);
            resultadoBusqueda1 = (List<UbigeoProvincia>) q.list();
            transaction.commit();
            return resultadoBusqueda1;

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
    public List ObtenerSociedadMiembro_SegunCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ClienteSociedadMiembro cc where cc.borrado = '1' AND cc.cliente.idCliente = " + idCliente;
            Query q = session.createQuery(hql);
            List<ClienteSociedadMiembro> l = q.list();
            for (ClienteSociedadMiembro c : l) {
                Hibernate.initialize(c.getClienteMiembro());
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
    public List ObtenerSociedadVigencia_SegunCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ClienteSociedadVigencia cc where cc.cliente.idCliente = " + idCliente;
            Query q = session.createQuery(hql);
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
    public List ObtenerSociedadUbigeoDistrito(int idProvincia) {
        try {

            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            q = session.createQuery("FROM UbigeoDistrito ud WHERE ubigeoProvincia.idProvincia =" + idProvincia);
            resultadoBusqueda1 = (List<UbigeoDistrito>) q.list();
            transaction.commit();
            return resultadoBusqueda1;

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
