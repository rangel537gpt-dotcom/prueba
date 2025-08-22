/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteEstudioContable;
import caja.mapeo.entidades.ClienteEstudioContableEspecialidad;
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
public class EmpresaDAO implements EmpresaDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<Cliente> resultadoBusqueda = null;
    private static EmpresaDAO INSTANCE = new EmpresaDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (EmpresaDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmpresaDAO();
                }
            }
        }
    }

    public static EmpresaDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public EmpresaDAO() {
    }

    @Override
    public List BuscarEmpresaPorRUC(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'E' AND c.eruc LIKE '%" + busqueda + "%'");
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
    public List BuscarEmpresaPorNroCta(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'E' AND c.enroCta LIKE '%" + busqueda + "%'");
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
    public List BuscarEmpresaPorCondicion(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'E' AND c.econdicion LIKE '%" + busqueda + "%'");
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
    public List BuscarEmpresaPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'E' AND c.enombre LIKE '%" + busqueda + "%'");
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
    public void GuardarEmpresa(Cliente emp) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(emp);
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
    public void guardarObjeto(Object o) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(o);
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
    public void ActualizarEmpresa(Cliente emp) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(emp);
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
    public void actualizarObjeto(Object o) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(o);
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
    public void EliminarEmpresa(Cliente emp) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(emp);
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
    public List ObtenerEmpresaUbigeoProvincia(int idDepartamento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            q = session.createQuery("FROM UbigeoProvincia up WHERE ubigeoDepartamento.idDepartamento =" + idDepartamento);
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
    public List ObtenerEmpresaUbigeoDistrito(int idProvincia) {
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

    @Override
    public List ObtenerEmpresaUbigeoDepartamento() {
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
    public ClienteEstudioContable obtenerEstudioContable(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM ClienteEstudioContable cec WHERE cec.cliente.id = :id").setParameter("id", id);
            List resultadoBusqueda1 = (List<ClienteEstudioContable>) q.list();
            ClienteEstudioContable cec = null;
            if (resultadoBusqueda1.size() > 0) {
                cec = (ClienteEstudioContable) resultadoBusqueda1.get(0);
                Hibernate.initialize(cec.getClienteTitular());
            }
            transaction.commit();
            return cec;
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
    public List obtenerEspecialidades(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ClienteEstudioContableEspecialidad cc where cc.borrado = '1' and cc.clienteEstudioContable.id = " + id;
            Query q = session.createQuery(hql);
            List<ClienteEstudioContableEspecialidad> l = q.list();
            for (ClienteEstudioContableEspecialidad c : l) {
                Hibernate.initialize(c.getCurriculumTablaEspecialidades());
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
    public List obtenerEspecialidades() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM CurriculumTablaEspecialidades");
            List resultadoBusqueda1 = q.list();
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
