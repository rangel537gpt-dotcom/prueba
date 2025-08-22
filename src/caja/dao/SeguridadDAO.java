/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Usuario;
import caja.mapeo.entidades.ValeAcademico;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class SeguridadDAO implements SeguridadDAOIFace {

    Session sesion = null;
    Transaction tx;
    private List<Usuario> resultadoBusqueda = null;

    private static SeguridadDAO INSTANCE = new SeguridadDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (SeguridadDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SeguridadDAO();
                }
            }
        }

    }

    public static SeguridadDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public Usuario EncontrarUsuario(String usuario, String macAddress) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = sesion.createQuery("FROM Usuario Where login='" + usuario + "' AND mac='" + macAddress + "'");
            resultadoBusqueda = (List<Usuario>) q.list();
            tx.commit();
            if (resultadoBusqueda.size() == 0) {
                return null;
            } else {
                return (Usuario) resultadoBusqueda.get(0);
            }

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Usuario InicializarDatosUsuario(Usuario pu) {

        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = sesion.createQuery("FROM Usuario Where login='" + pu.getLogin() + "'");
            resultadoBusqueda = (List<Usuario>) q.list();
            //Si lo quito da pete ya que no lo carga en la sesion
            for (Usuario u : resultadoBusqueda) {
                Hibernate.initialize(u.getCliente());
            }
            resultadoBusqueda = (List<Usuario>) q.list();
            tx.commit();
            return (Usuario) resultadoBusqueda.get(0);

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public boolean CambiarContrasena(Usuario usuario) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Usuario uDATA = (Usuario) sesion.get(Usuario.class, usuario.getIdUsuario());
            uDATA.setPassword(usuario.getPassword());
            sesion.update(uDATA);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Date ObtenerFechaServidor() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Date fechaServidor;
            //Query q = null;
            //SQLQuery q = sesion.createSQLQuery("SELECT GETDATE()");
            SQLQuery q = sesion.createSQLQuery("SELECT CONVERT (date, GETDATE())");
            fechaServidor = (Date) q.uniqueResult();
            tx.commit();
            return fechaServidor;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Date ObtenerFechaHoraServidor() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Date fechaServidor;
            //Query q = null;
            //SQLQuery q = sesion.createSQLQuery("SELECT GETDATE()");
            SQLQuery q = sesion.createSQLQuery("SELECT GETDATE()");
            fechaServidor = (Date) q.uniqueResult();
            tx.commit();
            return fechaServidor;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Date SumaMesesFechaServidor(int cantMeses) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Date fecha;
            //Query q = null;
            SQLQuery q = sesion.createSQLQuery("SELECT DATEADD(MONTH," + cantMeses + ",GETDATE())");
            fecha = (Date) q.uniqueResult();
            tx.commit();
            return fecha;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Object CargarObjeto(String nombreClase, int id) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Object oDATA = sesion.get("caja.mapeo.entidades." + nombreClase, id);
            Hibernate.initialize(oDATA);
            tx.commit();
            return oDATA;
        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Set CargarListaValeAcademicoConsumos(ValeAcademico va) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Object oDATA = sesion.get("caja.mapeo.entidades.ValeAcademico", va.getId());
//            List<ValeAcademicoConsumo> lValeAcademicoConsumo = new ArrayList<ValeAcademicoConsumo>();
//            lValeAcademicoConsumo.addAll(cargado);
            Hibernate.initialize(((ValeAcademico) oDATA).getValeAcademicoConsumos());
            Set l = ((ValeAcademico) oDATA).getValeAcademicoConsumos();
            tx.commit();
            return l;
        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

}
