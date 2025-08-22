/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class PersonaDAO implements PersonaDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<Cliente> resultadoBusqueda = null;

    private static PersonaDAO INSTANCE = new PersonaDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (PersonaDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PersonaDAO();
                }
            }
        }
    }

    public static PersonaDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public PersonaDAO() {
    }

    @Override
    public List BuscarPersonaPorApePat(String busqueda) {
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
    public List BuscarPersonaPorApeMat(String busqueda) {
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
    public List BuscarPersonaPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'P' AND c.papePat + ' ' + c.papeMat + ' ' + c.pnombre LIKE '%" + busqueda + "%' ORDER BY c.papePat,c.papeMat,c.pnombre");
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
    public List BuscarPersonaPorNroDocumento(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'P' AND c.pnroDocumento LIKE '%" + busqueda + "%'");
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
    public void GuardarPersona(Cliente p) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(p);
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
    public void ActualizarPersona(Cliente p) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(p);
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
    public String ObtenerNombreDepartamento(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("SELECT d.nombreDepartamento FROM UbigeoDepartamento d WHERE d.idDepartamento = " + id);
            q.setMaxResults(1);
            String nombre = "";
            if (q.uniqueResult() != null) {
                nombre = (String)q.uniqueResult();
            }
            transaction.commit();
            return nombre;
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
    public String ObtenerNombreProvincia(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("SELECT d.nombreProvincia FROM UbigeoProvincia d WHERE d.idProvincia = " + id);
            q.setMaxResults(1);
            String nombre = "";
            if (q.uniqueResult() != null) {
                nombre = (String)q.uniqueResult();
            }
            transaction.commit();
            return nombre;
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
    public String ObtenerNombreDistrito(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("SELECT d.nombreDistrito FROM UbigeoDistrito d WHERE d.idDistrito = " + id);
            q.setMaxResults(1);
            String nombre = "";
            if (q.uniqueResult() != null) {
                nombre = (String)q.uniqueResult();
            }
            transaction.commit();
            return nombre;
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
    public List ObtenerPersonaUbigeoDepartamento() {
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
    public List ObtenerPersonaUbigeoProvincia(int idDepartamento) {
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
    public List ObtenerPersonaUbigeoDistrito(int idProvincia) {
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
