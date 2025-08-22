/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Nota;
import caja.mapeo.entidades.NotaDetalle;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class NotaDAO implements NotaDAOIFace {

    Session session = null;
    Transaction transaction = null;
    private static NotaDAO INSTANCE = new NotaDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (NotaDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotaDAO();
                }
            }
        }
    }

    public static NotaDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public NotaDAO() {
    }

    @Override
    public List BuscarTodasNotasCredito() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = null;
            q = session.createQuery("FROM UnidadMedida u ORDER BY u.nombre");
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
    public List BuscarNota_SegunFiltro(String filtro) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Nota n WHERE 1 = 1 AND " + filtro + " ORDER BY n.nro DESC");
            List<Nota> listado = q.list();
            for (Nota nc : listado) {
                Hibernate.initialize(nc.getTipoNota());
                Hibernate.initialize(nc.getDocumentoPago());
                Hibernate.initialize(nc.getDocumentoPago().getCliente());
//                Hibernate.initialize(nc.getComprobantePago().getTipoDocumento());
            }
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
    public double ObtenerMontoTotalNotAplicadaComprobante(int idComprobante) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            double monto = 0;
            double total = 0;
            Query q = session.createQuery("FROM NotaDetalle nd WHERE nd.nota.comprobantePagoByIdComprobanteAplicacion.id = " + idComprobante);
            List<NotaDetalle> listado = q.list();
//            for (NotaDetalle n : listado) {
//                monto = n.getCantidad() * n.getValorVentaUnitario();
//                monto = monto - monto * n.getDescuento() / 100;
//                if (n.getIgv() != null) {
//                    if (n.getIgv() > 0) {
//                        monto = monto * ((100 + n.getIgv())/100);
//                    }
//                }
//                total = total + monto + ((n.getDescuentoDinero() != null) ? n.getDescuentoDinero() : 0.0);
//            }
            transaction.commit();
            return total;
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
    public List BuscarNota_AplicadasComprobante(int idComprobante) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Nota nc WHERE nc.comprobantePagoByIdComprobanteAplicacion.id = " + idComprobante + " ORDER BY nc.fecha");
            List<Nota> listado = q.list();
//            for (Nota nc : listado) {
//                Hibernate.initialize(nc.getTipoDocumento());
//                Hibernate.initialize(nc.getMotivoNota());
//                Hibernate.initialize(nc.getComprobantePago().getTipoMoneda());
//                Hibernate.initialize(nc.getComprobantePago().getTipoDocumento());
//            }
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
    public void EliminarDetalleNota(int idNota) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "DELETE from NotaDetalle nd where nd.nota.id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", idNota);
            query.executeUpdate();
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
    public List BuscarNota_SegunNroDocIdentidad(String doc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Nota nc WHERE nc.comprobantePago.clienteProveedor.nroDocIdentidad like '%" + doc + "%' ORDER BY nc.nro DESC");
            List<Nota> listado = q.list();
//            for (Nota nc : listado) {
//                Hibernate.initialize(nc.getComprobantePago());
//                Hibernate.initialize(nc.getMotivoNota());
//                Hibernate.initialize(nc.getComprobantePago().getClienteProveedor());
//                Hibernate.initialize(nc.getComprobantePago().getTipoDocumento());
//            }
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
    public List BuscarNota_SegunNroNota(int nro) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Nota nc WHERE nc.nro = " + nro + " ORDER BY nc.nro DESC");
            List<Nota> listado = q.list();
//            for (Nota nc : listado) {
//                Hibernate.initialize(nc.getComprobantePago());
//                Hibernate.initialize(nc.getMotivoNota());
//                Hibernate.initialize(nc.getComprobantePago().getClienteProveedor());
//                Hibernate.initialize(nc.getComprobantePago().getTipoDocumento());
//            }
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
    public List BuscarNota_SegunFecha(Date d, Date h) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Nota n WHERE fecha between :desde and :hasta ORDER BY n.nro DESC");
            q.setParameter("desde", d);
            q.setParameter("hasta", h);
            List<Nota> listado = q.list();
            for (Nota nc : listado) {
                Hibernate.initialize(nc.getTipoNota());
                Hibernate.initialize(nc.getDocumentoPago());
                Hibernate.initialize(nc.getDocumentoPago().getCliente());
//                Hibernate.initialize(nc.getComprobantePago().getTipoDocumento());
            }
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
    public List ObtenerDetalleNota(int idNC) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM NotaDetalle ncd WHERE ncd.nota.id = " + idNC);
            List<NotaDetalle> listado = q.list();
            for (NotaDetalle ncd : listado) {
                Hibernate.initialize(ncd.getDocumentoPagoDet());
                Hibernate.initialize(ncd.getDocumentoPagoDet().getConceptoPagoDetalle());
//                Hibernate.initialize(ncd.getComprobantePagoDetalle().getUnidadMedida());
//                Hibernate.initialize(ncd.getComprobantePagoDetalle().getTipoAfectacion());
            }
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
    public List ObtenerIdParticipantes_SegunNotaCredito(int idNota) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT CURSOS.participante.id\n"
                    + "FROM NotaDetalle,DocumentoPagoDet,CURSOS.participante\n"
                    + "WHERE NotaDetalle.IdDocumentoPagoDet = DocumentoPagoDet.IdDocumentoPagoDet AND\n"
                    + "CURSOS.participante.id_documento_pago_det = DocumentoPagoDet.IdDocumentoPagoDet AND\n"
                    + "NotaDetalle.Cantidad - DocumentoPagoDet.Cantidad = 0 AND\n"
                    + "NotaDetalle.IdNota = :idNota AND\n"
                    + "(ISNULL(DocumentoPagoDet.ValorVenta,0) + ISNULL(DocumentoPagoDet.Igv,0)) - (ISNULL(DocumentoPagoDet.ValorVenta,0) + ISNULL(DocumentoPagoDet.Igv,0)) = 0\n"
                    + "ORDER BY cursos.participante.id";
            Query q = session.createSQLQuery(sql).setParameter("idNota", idNota);
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
    public List ObtenerIdConstancia_SegunNotaCredito(int idNota) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT constancia_habilitacion.id\n"
                    + "FROM NotaDetalle,DocumentoPagoDet,constancia_habilitacion\n"
                    + "WHERE NotaDetalle.IdDocumentoPagoDet = DocumentoPagoDet.IdDocumentoPagoDet AND\n"
                    + "constancia_habilitacion.id_documento_pago_det = DocumentoPagoDet.IdDocumentoPagoDet AND\n"
                    + "NotaDetalle.Cantidad - DocumentoPagoDet.Cantidad = 0 AND\n"
                    + "NotaDetalle.IdNota = :idNota\n"
                    + "ORDER BY constancia_habilitacion.id";
            Query q = session.createSQLQuery(sql).setParameter("idNota", idNota);
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
    public List ObtenerTributosGenerales(int idNC) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT NOTADETALLE.CodigoTipoTributo,NOTADETALLE.NombreTipoTributo,\n"
                    + "NOTADETALLE.CodigoInternacionalTipoTributo,SUM(NOTADETALLE.ValorVenta) AS VV,SUM(NOTADETALLE.Igv) AS IGV\n"
                    + "FROM NOTADETALLE,DOCUMENTOPAGODET \n"
                    + "WHERE \n"
                    + "NOTADETALLE.IdNota = " + idNC + " AND\n"
                    + "NOTADETALLE.IdDocumentoPagoDet = DOCUMENTOPAGODET.IdDocumentoPagoDet\n"
                    + "GROUP BY NOTADETALLE.CodigoTipoTributo,NOTADETALLE.NombreTipoTributo,\n"
                    + "NOTADETALLE.CodigoInternacionalTipoTributo";
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
    public void actualizarBorradoConstancias(int idConstancia, String estadoBorrado) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "update constancia_habilitacion\n"
                    + "set borrado = :borrado\n"
                    + "where id = :id";
            Query q = session.createSQLQuery(sql).setParameter("id", idConstancia).setParameter("borrado", estadoBorrado);
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
    public void actualizarBorradoPartipantes(int idParticipante, String estadoBorrado) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "update cursos.participante\n"
                    + "set borrado = :borrado\n"
                    + "where id = :idParticipante";
            Query q = session.createSQLQuery(sql).setParameter("idParticipante", idParticipante).setParameter("borrado", estadoBorrado);
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
    public double ObtenerSumaTotalIGV(int idNC) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            double montoIgv = 0;
            Query q = session.createQuery("SELECT SUM(ncd.igv) FROM NotaDetalle ncd WHERE ncd.nota.id = " + idNC);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                montoIgv = (double) q.uniqueResult();
            }
            transaction.commit();
            return montoIgv;
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
    public double ObtenerSumaTotalValorVenta(int idNC) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            double montoIgv = 0;
            Query q = session.createQuery("SELECT SUM(ncd.valorVenta) FROM NotaDetalle ncd WHERE ncd.nota.id = " + idNC);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                montoIgv = (double) q.uniqueResult();
            }
            transaction.commit();
            return montoIgv;
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
    public double ObtenerSumaTotalPrecioVenta(int idNC) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            double montoIgv = 0;
            Query q = session.createQuery("SELECT SUM(ncd.valorVenta + ncd.igv) FROM NotaDetalle ncd WHERE ncd.nota.id = " + idNC);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                montoIgv = (double) q.uniqueResult();
            }
            transaction.commit();
            return montoIgv;
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
    public List ObtenerTodosTipoNota(int idTipoNota) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM TipoNota t WHERE t.tipoNota = " + idTipoNota);
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
    public Nota ObtenerNotaCredito(int idTipoNota, String nroSerie, int nro) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Nota n WHERE n.tipoDocumento.id = " + idTipoNota + " AND n.nroSerie = '" + nroSerie + "' AND n.nro = " + nro);
            q.setMaxResults(1);
            Nota n = null;
            if (q.uniqueResult() != null) {
                n = (Nota) q.uniqueResult();
            }
            transaction.commit();
            return n;
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
    public int ObtenerNroComprobanteCorrelativoProvisional(String serie) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            int nroCorrelativo = 0;
            Query q = session.createQuery("FROM Nota cp WHERE cp.nroSerie like '" + serie + "' AND cp.nro != null ORDER BY cp.nro DESC");
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                nroCorrelativo = ((Nota) q.uniqueResult()).getNroProvisional();
            }
            nroCorrelativo = nroCorrelativo + 1;
            transaction.commit();
            return nroCorrelativo;
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
    public void GuardarNota(Nota nc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(nc);
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
    public void GuardarNotaElectronica(Nota nc) {
        try {
            int nroCorrelativoProvisional = this.ObtenerNroComprobanteCorrelativoProvisional(nc.getNroSerie());
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            nc.setNro(nroCorrelativoProvisional);
            nc.setNroProvisional(nroCorrelativoProvisional);
            session.save(nc);
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
    public void GuardarNotaDetalle(NotaDetalle ncd) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(ncd);
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
    public void GenerarNumeracionElectronicaNota(Nota nc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            int nroSolicitud = 0;
            String hql = "SELECT nc.nro FROM Nota nc WHERE nc.nroSerie = '" + nc.getNroSerie() + "' ORDER BY nc.nro DESC";
            Query q = session.createQuery(hql);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                nroSolicitud = (int) q.uniqueResult();
            }
            nc.setNro(nroSolicitud + 1);
            session.update(nc);
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
    public void ActualizarNotaDetalle(NotaDetalle ncd) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(ncd);
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
    public void ActualizarNota(Nota nc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(nc);
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
    public void EliminarNotaDetalle(NotaDetalle ncd) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(ncd);
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
    public void EliminarNota(Nota n) {
        try {
            this.EliminarDetalleNota(n.getId());
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(n);
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
