/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

//import gradosTitulos.mapeo.CongresoEspecialidad;
import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Congreso;
import caja.mapeo.entidades.CongresoConcepto;
import caja.mapeo.entidades.CongresoParticipantes;
import java.util.List;
import org.hibernate.Hibernate;
//import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class CongresoDAO implements CongresoDAOIFace {

    Session sesion = null;
    Transaction tx;
    private static CongresoDAO INSTANCE = new CongresoDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (CongresoDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CongresoDAO();
                }
            }
        }

    }

    public static CongresoDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public CongresoDAO() {
    }

    @Override
    public List BuscarCongresoNOMBRE(String busqueda) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM Dependencia where nombre LIKE '%" + busqueda + "%'");
            List listado = q.list();
            tx.commit();
            return listado;
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
    public void GuardarCongreso(Congreso o) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(o);
            tx.commit();

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
    public void GuardarCongresoConcepto(CongresoConcepto cc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(cc);
            tx.commit();

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
    public void GuardarCongresoParticipantes(CongresoParticipantes cp) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(cp);
            tx.commit();

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
    public void ActualizarCongresoParticipantes(CongresoParticipantes cp) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(cp);
            tx.commit();

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
    public void ActualizarCongresoConcepto(CongresoConcepto cc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(cc);
            tx.commit();

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
    public void ActualizarCongreso(Congreso o) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(o);
            tx.commit();

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
    public List ObtenerTodasCongresos() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM Congreso r ORDER BY r.nombre ASC");
            List<Congreso> listado = q.list();
//            for (Congreso o : listado) {
//                Hibernate.initialize(o.getPersona());
//                Hibernate.initialize(o.getCargo());
//            }
            tx.commit();
            return listado;
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
    public List ObtenerTodosParticipantesCongreso(int idCongreso) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM CongresoParticipantes cp WHERE cp.congreso.id = " + idCongreso + " ORDER BY cp.apePat,cp.apeMat,cp.nombres ASC");
            List<CongresoParticipantes> listado = q.list();
            for (CongresoParticipantes o : listado) {
                Hibernate.initialize(o.getDocumentoPagoDet());
                Hibernate.initialize(o.getDocumentoPagoDet().getConceptoPagoDetalle());
                Hibernate.initialize(o.getDocumentoPagoDet().getDocumentoPago());
                Hibernate.initialize(o.getCongreso());
                Hibernate.initialize(o.getCliente());
                Hibernate.initialize(o.getDocumentoPagoDet().getDocumentoPago());
                Hibernate.initialize(o.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getSerie());
                Hibernate.initialize(o.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getTipoDocPago());
            }
            tx.commit();
            return listado;
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
    public List ObtenerTodosParticipantesCongresos(int idCongreso) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM CongresoParticipantes cp WHERE cp.documentoPagoDet.conceptoPagoDetalle ORDER BY r.nroDocumento,r.prefijo ASC");
            List<CongresoParticipantes> listado = q.list();
//            for (Congreso o : listado) {
//                Hibernate.initialize(o.getPersona());
//                Hibernate.initialize(o.getCargo());
//            }
            tx.commit();
            return listado;
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
    public List VerificarSiConceptoPerteneceCongreso(int idConcepto) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("SELECT DISTINCT cc.congreso FROM CongresoConcepto cc WHERE cc.conceptoPagoDetalle.idConceptoPagoDetalle = " + idConcepto);
            List<Congreso> listado = q.list();
//            for (CongresoConcepto o : listado) {
//                Hibernate.initialize(o.getCongreso());
////                Hibernate.initialize(o.getCargo());
//            }
            tx.commit();
            return listado;
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
    public List ObtenerTodosCongresoConcepto() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM CongresoConcepto c ORDER BY c.congreso.nombre");
            List<CongresoConcepto> listado = q.list();
            for (CongresoConcepto c : listado) {
                Hibernate.initialize(c.getCongreso());
                Hibernate.initialize(c.getConceptoPagoDetalle());
            }
            tx.commit();
            return listado;
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
    public List BuscarCongreso(String dependencia) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM Congreso WHERE nombre LIKE '%" + dependencia + "%' ORDER BY nombre ASC");
            List listado = q.list();
//            List<CongresoEspecialidad> listado = q.list();
//            for (CongresoEspecialidad g : listado) {
//                Hibernate.initialize(g.getCongresoEspecialidad());
//            }
            tx.commit();
            return listado;
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
