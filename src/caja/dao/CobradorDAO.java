/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
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
public class CobradorDAO implements CobradorDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<Cliente> resultadoBusqueda = null;
    List<Cobrador> resultado = null;
    private static CobradorDAO INSTANCE = new CobradorDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (CobradorDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CobradorDAO();
                }
            }
        }
    }

    public static CobradorDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public CobradorDAO() {
    }

    @Override
    public List BuscarCobradorPorApePat(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente WHERE papePat LIKE '%" + busqueda + "%'");
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
    public List BuscarCobradorPorApeMat(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente WHERE papeMat LIKE '%" + busqueda + "%'");
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
    public List BuscarCobradorPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            Query q = session.createQuery("FROM Cobrador c WHERE c.cliente.papePat + ' ' + c.cliente.papeMat + ' ' + c.cliente.pnombre LIKE '%" + busqueda + "%'");
            List<Cobrador> l = q.list();
            for (Cobrador c : l) {
                Hibernate.initialize(c.getCliente());
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
    public List BuscarCobradorPorDNI(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            q = session.createQuery("FROM Cobrador c WHERE cliente.pnroDocumento LIKE '%" + busqueda + "%'");
            List<Cobrador> l = q.list();
            for (Cobrador c : l) {
                Hibernate.initialize(c.getCliente());
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
    public List BuscarCobradorPorCodigo(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            //List<Cobrador> resultado = null;
            Query q = null;
            if (resultado != null) {
                resultado.clear();
            }
            q = session.createQuery("FROM Cliente c WHERE c.codCobrador LIKE '%" + busqueda + "%'");
            List<Cobrador> l = q.list();
            for (Cobrador c : l) {
                Hibernate.initialize(c.getCliente());
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
    public void GuardarCobrador(Cobrador cob) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(cob);
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
    public void GuardarCliente(Cliente c) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.save(c);
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
    public void ActualizarCobrador(Cobrador cob) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(cob);
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
    public void EliminarCobrador(Cliente cob) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(cob);
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
    public List ObtenerTodosCobradores() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Cobrador> resultado = null;
            Query q = null;
            q = session.createQuery("FROM Cobrador");
            resultado = (List<Cobrador>) q.list();
            /*for (Cobrador c : resultado) {
                Hibernate.initialize(c.getPersona());
            }*/
            transaction.commit();
            return resultado;

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
    public List ObtenerCobradorUbigeoDepartamento() {
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
    public List ObtenerCobradorUbigeoProvincia(int idDepartamento) {
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
    public List ObtenerCobradorUbigeoDistrito(int idProvincia) {
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
