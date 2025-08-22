/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cuotas;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class CuotasDAO implements CuotasDAOIFace {

    Session session = null;
    Transaction transaction = null;

    private static CuotasDAO INSTANCE = new CuotasDAO();
    //private List<Persona> resultadoBusqueda;
    //private List<Contador> listaContadores;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (CuotasDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CuotasDAO();
                }
            }
        }
    }

    public static CuotasDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public Cuotas ObtenerUltimaCuota(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Cuotas c WHERE c.cliente.idCliente= " + idCliente + " Order By c.anioMes.nroOrden Desc");
            q.setMaxResults(1);
            List<Cuotas> resultado = (List<Cuotas>) q.list();
            Cuotas detalle = (Cuotas) q.uniqueResult();
            if (detalle != null) {
                Hibernate.initialize(detalle.getAnioMes());
            }
            transaction.commit();
            return detalle;

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
    public Cuotas ObtenerCuota(int idCliente, int idAnioMes, int idConcepto) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Cuotas c WHERE c.cliente.idCliente= " + idCliente + "AND c.anioMes.id = " + idAnioMes + " AND c.conceptoPagoDetalle.idConceptoPagoDetalle = " + idConcepto + " Order By c.anioMes.nroOrden Desc");
            q.setMaxResults(1);
            Cuotas detalle = (Cuotas) q.uniqueResult();
            if (detalle != null) {
                Hibernate.initialize(detalle.getAnioMes());
            }
            transaction.commit();
            return detalle;

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
    public void GuardarCuota(Cuotas c) {
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
    public int ObtenerIdAnioMes(int Anio, int Mes) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<AnioMes> resultado = null;
            Query q = null;
            q = session.createQuery("FROM AnioMes am WHERE am.anio = " + Anio + " AND am.mes = " + Mes);
            q.setMaxResults(1);
            resultado = (List<AnioMes>) q.list();
            int idAnioMes = ((AnioMes) q.uniqueResult()).getId();
            transaction.commit();
            return idAnioMes;

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
    public List ObtenerCuotasPendientesCliente(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,SUM(Cuotas.Monto),AnioMes.MontoCuota FROM AnioMes LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + " WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + " GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,AnioMes.MontoCuota ORDER BY AnioMes.NroOrden");
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
    public List ObtenerCuotasPendientesCliente_ParaFinanciar(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,SUM(Cuotas.Monto),AnioMesConcepto.Monto,AnioMesConcepto.IdConcepto,AnioMesConcepto.EsFinanciado\n"
                    + "FROM AnioMes LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + "\n"
                    + "INNER JOIN AnioMesConcepto ON AnioMes.Id = AnioMesConcepto.IdAnioMes\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,AnioMesConcepto.IdConcepto,AnioMesConcepto.Monto,AnioMesConcepto.EsFinanciado\n"
                    + "HAVING SUM(Cuotas.Monto) IS NULL \n"
                    + "ORDER BY AnioMes.NroOrden";
            SQLQuery q = session.createSQLQuery(sql);
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
    public List ObtenerCuotasPendientesCliente_ParaFinanciar_Sociedad(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,SUM(2*Cuotas.Monto),2*AnioMesConcepto.monto_sociedad as Monto,AnioMesConcepto.IdConcepto,AnioMesConcepto.EsFinanciado\n"
                    + "FROM AnioMes LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + "\n"
                    + "INNER JOIN AnioMesConcepto ON AnioMes.Id = AnioMesConcepto.IdAnioMes AND AnioMesConcepto.EsFinanciado='S'\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,AnioMesConcepto.IdConcepto,AnioMesConcepto.monto_sociedad,AnioMesConcepto.EsFinanciado\n"
                    + "ORDER BY AnioMes.NroOrden";
            SQLQuery q = session.createSQLQuery(sql);
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
    public List ObtenerCuotasPendientesCliente_ParaFinanciar_SociedadLIMA(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,SUM(4*Cuotas.Monto),4*AnioMesConcepto.monto_sociedad as Monto,AnioMesConcepto.IdConcepto,AnioMesConcepto.EsFinanciado\n"
                    + "FROM AnioMes LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + "\n"
                    + "INNER JOIN AnioMesConcepto ON AnioMes.Id = AnioMesConcepto.IdAnioMes AND AnioMesConcepto.EsFinanciado='S'\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,AnioMesConcepto.IdConcepto,AnioMesConcepto.monto_sociedad,AnioMesConcepto.EsFinanciado\n"
                    + "ORDER BY AnioMes.NroOrden";
            SQLQuery q = session.createSQLQuery(sql);
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
    public List ObtenerCuotasPendientesClienteVitalicio(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,SUM(Cuotas.Monto),SUM(AnioMesConcepto.Monto) AS MONTOANIOCONCEPTO\n"
                    + "FROM AnioMesConcepto LEFT OUTER JOIN Cuotas ON AnioMesConcepto.IdAnioMes=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + " AND Cuotas.IdConceptoPagoDetalle=AnioMesConcepto.IdConcepto\n"
                    + "LEFT OUTER JOIN AnioMes ON AnioMes.Id=AnioMesConcepto.IdAnioMes AND AnioMesConcepto.es_vitalicio='S'\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes\n"
                    + "ORDER BY AnioMes.NroOrden";
            SQLQuery q = session.createSQLQuery(sql);
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
    public List ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,SUM(Cuotas.Monto),AnioMesConcepto.Monto,AnioMesConcepto.IdConcepto,AnioMesConcepto.EsFinanciado\n"
                    + "FROM AnioMes LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + "\n"
                    + "INNER JOIN AnioMesConcepto ON AnioMes.Id=AnioMesConcepto.IdAnioMes AND AnioMesConcepto.es_vitalicio='S'\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,AnioMesConcepto.Monto,AnioMesConcepto.IdConcepto,AnioMesConcepto.EsFinanciado\n"
                    + "HAVING SUM(Cuotas.Monto) IS NULL \n"
                    + "ORDER BY AnioMes.NroOrden";
            SQLQuery q = session.createSQLQuery(sql);
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
    public List ObtenerCuotasPendientesSociedad(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,2*SUM(Cuotas.Monto),2*SUM(AnioMesConcepto.Monto_Sociedad) AS MONTOANIOCONCEPTO\n"
                    + "FROM AnioMesConcepto LEFT OUTER JOIN Cuotas ON AnioMesConcepto.IdAnioMes=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + " AND (Cuotas.IdConceptoPagoDetalle=AnioMesConcepto.IdConcepto OR Cuotas.IdConceptoPagoDetalle=7220)\n"
                    + "LEFT OUTER JOIN AnioMes ON AnioMes.Id=AnioMesConcepto.IdAnioMes AND AnioMesConcepto.EsFinanciado='S'\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes HAVING 2*SUM(Cuotas.Monto) IS NULL\n"
                    + "ORDER BY AnioMes.NroOrden";
            SQLQuery q = session.createSQLQuery(sql);
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
    public List ObtenerCuotasPendientesSociedadLIMA(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,4*SUM(Cuotas.Monto),4*SUM(AnioMesConcepto.Monto_Sociedad) AS MONTOANIOCONCEPTO\n"
                    + "FROM AnioMesConcepto LEFT OUTER JOIN Cuotas ON AnioMesConcepto.IdAnioMes=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + " AND (Cuotas.IdConceptoPagoDetalle=AnioMesConcepto.IdConcepto OR Cuotas.IdConceptoPagoDetalle=7220)\n"
                    + "LEFT OUTER JOIN AnioMes ON AnioMes.Id=AnioMesConcepto.IdAnioMes AND AnioMesConcepto.EsFinanciado='S'\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + "\n"
                    + "GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes HAVING 2*SUM(Cuotas.Monto) IS NULL\n"
                    + "ORDER BY AnioMes.NroOrden";
            SQLQuery q = session.createSQLQuery(sql);
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
    public Object[] ObtenerCuotaMesAcutal(int idCliente, int anio, int mes) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Object> resultado = null;
            //Query q = null;
            //q = session.createQuery("FROM Cuotas c WHERE c.cliente.idCliente = " + idCliente + " c.anioMes.anio = " + anio + " AND c.anioMes.mes = " + mes);
            SQLQuery q = session.createSQLQuery("SELECT AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,SUM(Cuotas.Monto),AnioMes.MontoCuota FROM AnioMes LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente = " + idCliente + " WHERE AnioMes.Anio=" + anio + " AND AnioMes.Mes=" + mes + " GROUP BY AnioMes.NroOrden,AnioMes.Id,AnioMes.Anio,AnioMes.Mes,AnioMes.MontoCuota ORDER BY AnioMes.NroOrden");
            q.setMaxResults(1);
            resultado = (List<Object>) q.list();
            Object[] cuota = ((Object[]) q.uniqueResult());
            transaction.commit();
            return cuota;

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
    public List ObtenerTodasCuotasCanceladas(int idCliente, int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            //Query q = null;
            //q = session.createQuery("SELECT c.anioMes.nroOrden,c.anioMes.anio,c.anioMes.mes,c.monto,c.documentoPagoDet.documentoPago.tipoDocPago.nombreDocPago,c.documentoPagoDet.documentoPago.nroSerie,c.documentoPagoDet.documentoPago.nroDocumentoPago,c.documentoPagoDet.documentoPago.fechaPago FROM Cuotas c WHERE c.cliente.idCliente = " + idCliente + " ORDER BY c.anioMes.nroOrden DESC");
            /*String sql = "SELECT AnioMes.NroOrden,AnioMes.Anio,AnioMes.Mes,SUM(Cuotas.Monto),TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,DocumentoPago.FechaPago FROM AnioMes\n"
             + "LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente=" + idCliente + "\n"
             + "LEFT OUTER JOIN DocumentoPagoDet ON Cuotas.IdDocDet=DocumentoPagoDet.IdDocumentoPagoDet\n"
             + "LEFT OUTER JOIN DocumentoPago ON DocumentoPagoDet.IdDocumentoPago=DocumentoPago.IdDocumentoPago\n"
             + "LEFT OUTER JOIN TipoDocPago ON DocumentoPago.IdTipoDocPago=TipoDocPago.IdTipoDocPago\n"
             + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + " GROUP BY AnioMes.NroOrden,AnioMes.Anio,AnioMes.Mes,TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,DocumentoPago.FechaPago ORDER BY AnioMes.NroOrden DESC";/*/
            String sql = "SELECT AnioMes.NroOrden,AnioMes.Anio,AnioMes.Mes,SUM(Cuotas.Monto),TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,DocumentoPago.FechaPago FROM AnioMes\n"
                    + "LEFT OUTER JOIN Cuotas ON AnioMes.Id=Cuotas.IdAnioMes AND Cuotas.IdCliente=" + idCliente + "\n"
                    + "LEFT OUTER JOIN DocumentoPagoDet ON Cuotas.IdDocDet=DocumentoPagoDet.IdDocumentoPagoDet\n"
                    + "LEFT OUTER JOIN DocumentoPago ON DocumentoPagoDet.IdDocumentoPago=DocumentoPago.IdDocumentoPago\n"
                    + "LEFT OUTER JOIN TipoDocSerie ON DocumentoPago.IdTipoPagoSerie=TipoDocSerie.Id\n"
                    + "LEFT OUTER JOIN TipoDocPago ON TipoDocSerie.IdTipoDoc=TipoDocPago.IdTipoDocPago\n"
                    + "WHERE AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + " GROUP BY AnioMes.NroOrden,AnioMes.Anio,AnioMes.Mes,TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,DocumentoPago.FechaPago ORDER BY AnioMes.NroOrden DESC";
            SQLQuery q = session.createSQLQuery(sql);
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
    public List ObtenerMontoTotalFinanciados(int desde, int hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT SUM(AnioMesConcepto.Monto),AnioMesConcepto.EsFinanciado,AnioMesConcepto.IdConcepto FROM AnioMesConcepto,AnioMes WHERE AnioMesConcepto.IdAnioMes=AnioMes.Id AND AnioMes.NroOrden>=" + desde + " AND AnioMes.NroOrden<=" + hasta + " GROUP BY AnioMesConcepto.EsFinanciado,AnioMesConcepto.IdConcepto");
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
    public double ObtenerDescuentoConcepto(int idAnioMes) {
        try {
            double montoDescuento = 0;
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT SUM(AnioMesConcepto.Monto*AnioMesConcepto.PorcentajeDescuento/100)\n"
                    + "FROM AnioMesConcepto\n"
                    + "WHERE AnioMesConcepto.IdAnioMes = " + idAnioMes);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                montoDescuento = (double) q.uniqueResult();
            }
            transaction.commit();
            return montoDescuento;
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
    public double ObtenerDescuentoConceptoUnico(int idAnioMes, int idConcepto) {
        try {
            double montoDescuento = 0;
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT SUM(AnioMesConcepto.Monto*AnioMesConcepto.PorcentajeDescuento/100)\n"
                    + "FROM AnioMesConcepto\n"
                    + "WHERE AnioMesConcepto.IdAnioMes = " + idAnioMes + " AND\n"
                    + "AnioMesConcepto.IdConcepto = " + idConcepto);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                montoDescuento = (double) q.uniqueResult();
            }
            transaction.commit();
            return montoDescuento;
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
    public double ObtenerMontoCuotaAnioMes(int Anio, int Mes) {
        try {
            double montoDescuento = 0;
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT SUM(AnioMesConcepto.Monto) FROM AnioMesConcepto,AnioMes WHERE AnioMesConcepto.IdAnioMes = AnioMes.Id AND AnioMes.Anio = " + Anio + " AND AnioMes.Mes = " + Mes);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                montoDescuento = (double) q.uniqueResult();
            }
            transaction.commit();
            return montoDescuento;
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
