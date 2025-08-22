/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.AnioMesConcepto;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class AnioMesDAO implements AnioMesDAOIFace {

    Session session = null;
    Transaction transaction = null;
    private static AnioMesDAO INSTANCE = new AnioMesDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AnioMesDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AnioMesDAO();
                }
            }
        }
    }

    public static AnioMesDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosMesAnio() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<AnioMes> resultado = null;
            Query q = null;
            q = session.createQuery("FROM AnioMes");
            resultado = (List<AnioMes>) q.list();
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
    public void InsertarAnioMes(AnioMes am) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(am);
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
    public void InsertarAnioMesConcepto(AnioMesConcepto amc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(amc);
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
    public List ObtenerConceptosCuota(int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AnioMesConcepto.IdConcepto,SUM(AnioMesConcepto.Monto)\n"
                    + "FROM AnioMesConcepto,AnioMes\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=AnioMes.Id AND\n"
                    + "AnioMes.NroOrden>=" + desde + " AND\n"
                    + "AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMesConcepto.IdConcepto");
            List listaResultado = q.list();
            transaction.commit();
            return listaResultado;
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
    public List ObtenerConceptosCuotaVitalicio(int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AnioMesConcepto.IdConcepto,SUM(AnioMesConcepto.Monto)\n"
                    + "FROM AnioMesConcepto,AnioMes\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=AnioMes.Id AND\n"
                    + "AnioMes.NroOrden>=" + desde + " AND\n"
                    + "AnioMes.NroOrden<=" + hasta + " AND\n"
                    + "AnioMesConcepto.es_vitalicio='S'\n"
                    + "GROUP BY AnioMesConcepto.IdConcepto");
            List listaResultado = q.list();
            transaction.commit();
            return listaResultado;
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
    public List ObtenerConceptosCuotaSociedadArequipa(int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AnioMesConcepto.IdConcepto,SUM(AnioMesConcepto.Monto_Sociedad*2)\n"
                    + "FROM AnioMesConcepto,AnioMes\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=AnioMes.Id AND\n"
                    + "AnioMes.NroOrden>=" + desde + " AND\n"
                    + "AnioMes.NroOrden<=" + hasta + " AND\n"
                    + "AnioMesConcepto.EsFinanciado='S'\n"
                    + "GROUP BY AnioMesConcepto.IdConcepto");
            List listaResultado = q.list();
            transaction.commit();
            return listaResultado;
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
    public List ObtenerConceptosCuotaSociedadLima(int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AnioMesConcepto.IdConcepto,SUM(AnioMesConcepto.Monto_Sociedad*4)\n"
                    + "FROM AnioMesConcepto,AnioMes\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=AnioMes.Id AND\n"
                    + "AnioMes.NroOrden>=" + desde + " AND\n"
                    + "AnioMes.NroOrden<=" + hasta + " AND\n"
                    + "AnioMesConcepto.EsFinanciado='S'\n"
                    + "GROUP BY AnioMesConcepto.IdConcepto");
            List listaResultado = q.list();
            transaction.commit();
            return listaResultado;
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
    public Object ObtenerPrimerConceptosCuota(int idConcepto, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT TOP 1 AnioMes.NroOrden,AnioMes.Anio,AnioMes.Mes\n"
                    + "FROM AnioMesConcepto,AnioMes\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=AnioMes.Id AND\n"
                    + "AnioMes.NroOrden>=" + desde + " AND\n"
                    + "AnioMes.NroOrden<=" + hasta + " AND\n"
                    + "AnioMesConcepto.IdConcepto=" + idConcepto + "\n"
                    + "ORDER BY AnioMes.NroOrden ASC");
            Object obj = q.uniqueResult();
            transaction.commit();
            return obj;
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
    public Object ObtenerUltimoConceptosCuota(int idConcepto, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT TOP 1 AnioMes.NroOrden,AnioMes.Anio,AnioMes.Mes\n"
                    + "FROM AnioMesConcepto,AnioMes\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=AnioMes.Id AND\n"
                    + "AnioMes.NroOrden>=" + desde + " AND\n"
                    + "AnioMes.NroOrden<=" + hasta + " AND\n"
                    + "AnioMesConcepto.IdConcepto=" + idConcepto + "\n"
                    + "ORDER BY AnioMes.NroOrden DESC");
            Object obj = q.uniqueResult();
            transaction.commit();
            return obj;
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
    public Object ObtenerMontoConceptoDetallado(int idAnioMes, int idConcepto) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AnioMesConcepto.Monto\n"
                    + "FROM AnioMesConcepto\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=" + idAnioMes + " AND\n"
                    + "AnioMesConcepto.IdConcepto=" + idConcepto);
            Object monto = q.uniqueResult();
            transaction.commit();
            return monto;
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
    public Object ObtenerMontoConceptoDetalladoVitalicio(int idAnioMes, int idConcepto) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMesConcepto.Monto\n"
                    + "FROM AnioMesConcepto\n"
                    + "WHERE AnioMesConcepto.es_vitalicio = 'S' and AnioMesConcepto.IdAnioMes=" + idAnioMes + " AND\n"
                    + "AnioMesConcepto.IdConcepto=" + idConcepto;
            SQLQuery q = session.createSQLQuery(sql);
            Object monto = q.uniqueResult();
            transaction.commit();
            return monto;
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
    public Object ObtenerMontoSociedadConceptoDetallado(int idAnioMes, int idConcepto) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AnioMesConcepto.Monto_Sociedad\n"
                    + "FROM AnioMesConcepto\n"
                    + "WHERE AnioMesConcepto.IdAnioMes=" + idAnioMes + " AND\n"
                    + "AnioMesConcepto.IdConcepto=" + idConcepto);
            Object monto = q.uniqueResult();
            transaction.commit();
            return monto;
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
