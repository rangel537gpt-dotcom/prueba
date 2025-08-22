/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
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
public class FinanciamientoDAO implements FinanciamientoDAOIFace {

    private Session sesion = null;
    private Transaction tx;
    private static FinanciamientoDAO INSTANCE = new FinanciamientoDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FinanciamientoDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FinanciamientoDAO();
                }
            }
        }

    }

    public static FinanciamientoDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public void CargarFinanciamientoCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void GuardarFinanciamiento(Financiamiento finan) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(finan);
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
    public void GuardarFinanciamientoDocumentoPago(FinanciamientoDocumentoPago finanDoc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(finanDoc);
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
    public void ActualizarFinanciamientoDocumentoPago(FinanciamientoDocumentoPago finanDoc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(finanDoc);
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
    public List ObtenerTodosFinanciamientoActivosCliente(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<FinanciamientoDocumentoPago> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM FinanciamientoDocumentoPago fdp WHERE fdp.estado = 'FS' AND fdp.financiamiento.cliente.idCliente=" + idCliente + " ORDER BY fdp.nroCuota ASC");
            resultadoBusqueda = q.list();
            /*for (FinanciamientoDocumentoPago fdp : resultadoBusqueda) {
             Hibernate.initialize(fdp.getAnioMes());
             }*/
            tx.commit();
            return resultadoBusqueda;

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
    public void ActualizarFinanciamiento(FinanciamientoDocumentoPago fdp) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(fdp);
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
    public void ActualizarReincorporacion(ReincorporacionDocumentoPago rdp) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(rdp);
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
    public List ObtenerTodosFinanciamientos(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Financiamiento> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM Financiamiento f WHERE f.cliente = " + idCliente + " ORDER BY f.idFinanciamiento DESC");
            resultadoBusqueda = q.list();
            /*for (FinanciamientoDocumentoPago fdp : resultadoBusqueda) {
             Hibernate.initialize(fdp.getAnioMes());
             }*/
            tx.commit();
            return resultadoBusqueda;

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
    public List ObtenerTodosFinanciamientosTodosContadores() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Financiamiento> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM Financiamiento f ORDER BY f.idFinanciamiento DESC");
            resultadoBusqueda = q.list();
            for (Financiamiento f : resultadoBusqueda) {
                Hibernate.initialize(f.getCliente().getCcodigoCole());
                Hibernate.initialize(f.getCliente().getPapePat());
                Hibernate.initialize(f.getCliente().getPapeMat());
                Hibernate.initialize(f.getCliente().getPnombre());
            }
            tx.commit();
            return resultadoBusqueda;

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
    public List ObtenerDetalleFinanciamiento(int idFinanciamiento) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();

            /*
             SQLQuery q = sesion.createSQLQuery("SELECT FinanciamientoDocumentoPago.Estado,FinanciamientoDocumentoPago.NroCuota,FinanciamientoDocumentoPago.Monto,TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,DocumentoPago.FechaPago,FinanciamientoDocumentoPago.FechaVencimiento,FinanciamientoDocumentoPago.DiasProrroga\n"
             + "FROM Financiamiento,FinanciamientoDocumentoPago\n"
             + "LEFT OUTER JOIN DocumentoPagoDet ON FinanciamientoDocumentoPago.IdDocPagoDet=DocumentoPagoDet.IdDocumentoPagoDet\n"
             + "LEFT OUTER JOIN DocumentoPago ON DocumentoPagoDet.IdDocumentoPago=DocumentoPago.IdDocumentoPago\n"
             + "LEFT OUTER JOIN TipoDocPago ON DocumentoPago.IdTipoDocPago=TipoDocPago.IdTipoDocPago\n"
             + "WHERE Financiamiento.IdFinanciamiento=FinanciamientoDocumentoPago.IdFinanciamiento AND\n"
             + "Financiamiento.IdFinanciamiento = " + idFinanciamiento);
             */
            SQLQuery q = sesion.createSQLQuery("SELECT FinanciamientoDocumentoPago.Estado,FinanciamientoDocumentoPago.NroCuota,FinanciamientoDocumentoPago.Monto,TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,DocumentoPago.FechaPago,FinanciamientoDocumentoPago.FechaVencimiento,FinanciamientoDocumentoPago.DiasProrroga\n"
                    + "FROM Financiamiento,FinanciamientoDocumentoPago\n"
                    + "LEFT OUTER JOIN DocumentoPagoDet ON FinanciamientoDocumentoPago.IdDocPagoDet=DocumentoPagoDet.IdDocumentoPagoDet\n"
                    + "LEFT OUTER JOIN DocumentoPago ON DocumentoPagoDet.IdDocumentoPago=DocumentoPago.IdDocumentoPago\n"
                    + "LEFT OUTER JOIN TipoDocSerie ON DocumentoPago.IdTipoPagoSerie=TipoDocSerie.Id\n"
                    + "LEFT OUTER JOIN TipoDocPago ON TipoDocSerie.IdTipoDoc=TipoDocPago.IdTipoDocPago\n"
                    + "WHERE Financiamiento.IdFinanciamiento=FinanciamientoDocumentoPago.IdFinanciamiento AND\n"
                    + "Financiamiento.IdFinanciamiento = " + idFinanciamiento);

            List listaResultado = q.list();
            tx.commit();
            return listaResultado;
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
    public List ObtenerTodosFinanciamientosPendientes(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<FinanciamientoDocumentoPago> resultadoBusqueda = null;
            Query q = null;
            int idFinanciamiento = 0;
            q = sesion.createQuery("SELECT f.idFinanciamiento FROM Financiamiento f WHERE f.cliente.idCliente = " + idCliente + " ORDER BY f.nroFinanciamiento DESC");
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                idFinanciamiento = (int) q.uniqueResult();
            }
            q = sesion.createQuery("FROM FinanciamientoDocumentoPago fdp WHERE fdp.estado = 'FS' AND fdp.financiamiento.idFinanciamiento = " + idFinanciamiento + " ORDER BY fdp.fechaVencimiento ASC");
            resultadoBusqueda = q.list();
            /*for (FinanciamientoDocumentoPago fdp : resultadoBusqueda) {
             Hibernate.initialize(fdp.getAnioMes());
             }*/
            tx.commit();
            return resultadoBusqueda;

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
    public List ObtenerTodasDeudasPendientes(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Deuda> resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM Deuda d WHERE d.estado = 'DP' AND d.cliente.idCliente = " + idCliente);
            resultadoBusqueda = q.list();
            /*for (FinanciamientoDocumentoPago fdp : resultadoBusqueda) {
             Hibernate.initialize(fdp.getAnioMes());
             }*/
            tx.commit();
            return resultadoBusqueda;

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
    public int ObtenerNroFinanciamiento(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            int nroFinanciamiento = 0;
            q = sesion.createQuery("SELECT f.nroFinanciamiento FROM Financiamiento f WHERE f.cliente.idCliente = " + idCliente + " ORDER BY f.nroFinanciamiento DESC");
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                nroFinanciamiento = (int) q.uniqueResult();
            }
            tx.commit();
            return nroFinanciamiento;
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
    public void ActualizarDeudaPendiente(Deuda d) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(d);
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
    public void CancelarDeudas(int idCliente, int idFinanciamiento) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            SQLQuery q = sesion.createSQLQuery("UPDATE Deuda SET IdFinanciamientoNuevo = " + idFinanciamiento + ",Estado = 'DF' WHERE IdCliente = " + idCliente + " AND Estado = 'DP'");
            q.executeUpdate();
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
    public void CancelarFinanciamientos(int idCliente, int idFinanciamiento, int nroFinanciamiento) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            SQLQuery q = sesion.createSQLQuery("UPDATE FinanciamientoDocumentoPago SET FinanciamientoDocumentoPago.IdFinanciamientoNuevo = " + idFinanciamiento + ",FinanciamientoDocumentoPago.Estado = 'FF' FROM Financiamiento WHERE Financiamiento.NroFinanciamiento = " + nroFinanciamiento + " AND Financiamiento.IdCliente = " + idCliente + " AND Financiamiento.IdFinanciamiento = FinanciamientoDocumentoPago.IdFinanciamiento AND FinanciamientoDocumentoPago.Estado = 'FS'");
            q.executeUpdate();
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

}
