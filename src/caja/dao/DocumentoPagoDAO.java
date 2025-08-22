/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cobrador;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.mapeo.entidades.DocPagoAnulado;
import caja.mapeo.entidades.DocPagoAnuladoDetalle;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.Operacion;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import caja.mapeo.entidades.ResumenDiario;
import caja.mapeo.entidades.ResumenDiarioDetalle;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.mapeo.entidades.ValeAcademico;
import caja.mapeo.entidades.ValeAcademicoConsumo;
import caja.mapeo.entidades.ValeAcademicoConsumoParticipante;
import java.util.ArrayList;
import java.util.Date;
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
public class DocumentoPagoDAO implements DocumentoPagoDAOIFace {

    private Session sesion = null;
    private Transaction tx;

    List<DocumentoPago> resultadoBusqueda = null;

    private static DocumentoPagoDAO INSTANCE = new DocumentoPagoDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (DocumentoPagoDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DocumentoPagoDAO();
                }
            }
        }
    }

    public static DocumentoPagoDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerTodosConceptoPago() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM ConceptoPagoDetalle c WHERE c.estado = 'H'");
            List resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public List ObtenerDeudaNoFinanciadas(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM Deuda d WHERE d.cliente.idCliente = " + idCliente + " AND d.estado = 'DP' AND d.sePuedeFinanciar = 'N'");
            resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public List ObtenerTodosClientes() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Cliente> resultado = null;
            Query q = null;
            q = sesion.createQuery("FROM Cliente c ORDER BY c.papePat,c.papeMat,c.pnombre");
            resultado = (List<Cliente>) q.list();
            /*for (Cliente c : resultado) {
             Hibernate.initialize(c.getPersona());
             }*/
            tx.commit();
            return resultado;

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
    public List ObtenerTodosCobradores() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Cobrador> resultado = null;
            Query q = null;
            q = sesion.createQuery("FROM Cobrador");
            resultado = (List<Cobrador>) q.list();
            for (Cobrador c : resultado) {
                Hibernate.initialize(c.getCliente());
            }
            tx.commit();
            return resultado;

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
    public double ObtenerSumaTotalValorVenta(int id) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createSQLQuery("SELECT SUM(DocumentoPagoDet.ValorVenta) FROM DocumentoPagoDet WHERE IdDocumentoPago = " + id);
            q.setMaxResults(1);
            double monto = 0;
            if (q.uniqueResult() != null) {
                monto = (double) q.uniqueResult();
            }
            tx.commit();
            return monto;
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
    public List ObtenerTributos_TotalesValorVenta(int id) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createSQLQuery("SELECT DocumentoPagoDet.CodigoTipoTributo,SUM(DocumentoPagoDet.ValorVenta)\n"
                    + "FROM DocumentoPago,DocumentoPagoDet \n"
                    + "WHERE DocumentoPagoDet.IdDocumentoPago = DocumentoPago.IdDocumentoPago AND\n"
                    + "DocumentoPago.IdDocumentoPago = " + id + "\n"
                    + "GROUP BY DocumentoPagoDet.CodigoTipoTributo");
            List l = q.list();
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

    @Override
    public double ObtenerSumaTotalIGV(int id) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createSQLQuery("SELECT SUM(DocumentoPagoDet.igv) FROM DocumentoPagoDet WHERE IdDocumentoPago = " + id);
            q.setMaxResults(1);
            double monto = 0;
            if (q.uniqueResult() != null) {
                monto = (double) q.uniqueResult();
            }
            tx.commit();
            return monto;
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
    public double ObtenerSumaTotalPrecioVenta(int id) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createSQLQuery("SELECT SUM(DocumentoPagoDet.ValorVenta + DocumentoPagoDet.Igv) FROM DocumentoPagoDet WHERE IdDocumentoPago = " + id);
            q.setMaxResults(1);
            double monto = 0;
            if (q.uniqueResult() != null) {
                monto = (double) q.uniqueResult();
            }
            tx.commit();
            return monto;
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
    public List ObtenerTodosTipoDocPago() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = sesion.createQuery("FROM TipoDocPago t ORDER BY t.nombreDocPago");
            resultadoBusqueda = (List<TipoDocPago>) q.list();
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
    public List ObtenerTodasSeries_SegunTipoDocumento(int idTipoDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("SELECT t.serie FROM TipoDocSerie t WHERE t.tipoDocPago.idTipoDocPago = " + idTipoDocumentoPago);
            List resultadoBusqueda = q.list();
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
    public int ObtenerCorrelativo(int idTipoDocSerie) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            int num = 0;
            Query q = sesion.createQuery("SELECT dp.nroDocumentoPago FROM DocumentoPago dp WHERE dp.tipoDocSerie.id = " + idTipoDocSerie + " ORDER BY dp.nroDocumentoPago DESC");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (int) q.uniqueResult();
            }
            //num = num + 1;
            /*ACTUALIZAMOS CORRELATIVO*/
            TipoDocSerie tdsDATA = (TipoDocSerie) sesion.get(TipoDocSerie.class, idTipoDocSerie);
            tdsDATA.setCorrelativo(num + 1);
            sesion.update(tdsDATA);
            /*------------------------*/
            tx.commit();
            return num;

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
    public int ObtenerCorrelativoResumenDiario(String fecha) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            int num = 0;
            Query q = sesion.createQuery("SELECT dp.nro FROM ResumenDiario dp WHERE dp.fecha = '" + fecha + "' ORDER BY dp.nro DESC");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (int) q.uniqueResult();
            }
            tx.commit();
            return num;
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
    public int ObtenerCorrelativoConstanciaHabilitacion() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            int num = 28809;
            Query q = sesion.createQuery("SELECT dp.nro FROM ConstanciaHabilitacion dp WHERE dp.tipo <> 'S' AND dp.borrado = '1' ORDER BY dp.nro DESC");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (int) q.uniqueResult();
            }
            tx.commit();
            return num;
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
    public int ObtenerCorrelativoConstanciaHabilitacionSociedad() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            int num = 0;
            Query q = sesion.createQuery("SELECT dp.nro FROM ConstanciaHabilitacion dp WHERE dp.tipo = 'S' AND dp.borrado = '1' ORDER BY dp.nro DESC");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (int) q.uniqueResult();
            }
            tx.commit();
            return num;
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
    public int ObtenerCorrelativoAnulacion(int idTipoDoc, int anio, int mes, int dia) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            long num = 0;
            Query q = sesion.createQuery("SELECT COUNT(dp.idDocumentoPago) FROM DocumentoPago dp WHERE dp.fechaAnulacion != NULL AND YEAR(dp.fechaAnulacion) = " + anio + " AND MONTH(dp.fechaAnulacion) = " + mes + " AND DAY(dp.fechaAnulacion) = " + dia + " AND dp.tipoDocSerie.tipoDocPago.idTipoDocPago = " + idTipoDoc);
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (Long) q.uniqueResult();
            }
            tx.commit();
            return (int) num;
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
    public int ObtenerSolomenteCorrelativo(int idTipoDocSerie) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            int num = 0;
            Query q = null;
            q = sesion.createQuery("SELECT tds.correlativo FROM TipoDocSerie tds WHERE tds.id = " + idTipoDocSerie);
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (int) q.uniqueResult();
            }
            tx.commit();
            return num;

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
    public int ObtenerSolamenteCorrelativo_Metodo2(int idTipoDocSerie) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            int num = 0;
            Query q = sesion.createQuery("SELECT d.nroDocumentoPago FROM DocumentoPago d WHERE d.tipoDocSerie.id = " + idTipoDocSerie + " ORDER BY d.nroDocumentoPago DESC");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (int) q.uniqueResult();
            }
            tx.commit();
            return num;
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
    public boolean VerificarSiNroDocEstaOcupado(DocumentoPago doc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            boolean estaOcupado = false;
            Query q = null;
            q = sesion.createQuery("SELECT d.nroDocumentoPago FROM DocumentoPago d WHERE d.tipoDocSerie.tipoDocPago.idTipoDocPago = " + doc.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() + " AND d.nroDocumentoPago = " + doc.getNroDocumentoPago() + " AND d.nroSerie = '" + doc.getNroSerie() + "'");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result == null) {
                estaOcupado = false;
            } else {
                estaOcupado = true;
            }
            tx.commit();
            return estaOcupado;

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
    public boolean VerificarSiNroDocEstaOcupado(int idTipoDoc, int nroDoc, String nroSerie) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            boolean estaOcupado = false;
//             q = null;
            Query q = sesion.createQuery("SELECT d.nroDocumentoPago FROM DocumentoPago d WHERE d.tipoDocSerie.tipoDocPago.idTipoDocPago = " + idTipoDoc + " AND d.nroDocumentoPago = " + nroDoc + " AND d.nroSerie = '" + nroSerie + "'");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result == null) {
                estaOcupado = false;
            } else {
                estaOcupado = true;
            }
            tx.commit();
            return estaOcupado;

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
    public boolean VerificarSiNroExcedeCorrelativo(DocumentoPago doc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            boolean excede = false;
            Query q = null;
            q = sesion.createQuery("SELECT tds.correlativo FROM TipoDocSerie tds WHERE tds.id = " + doc.getTipoDocSerie().getId());
            q.setMaxResults(1);
            int correlativo = (int) q.uniqueResult();
            if (correlativo <= doc.getNroDocumentoPago()) {
                excede = true;
            } else {
                excede = false;
            }
            tx.commit();
            return excede;

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
    public void GuardarDocumentoPago(DocumentoPago doc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(doc);
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
    public void GuardarObjeto(Object doc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(doc);
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
    public void ActualizarObjeto(Object doc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(doc);
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
    public void GuardarOperacion(Operacion o) {
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
    public void GuardarDocumentoPagoDet(DocumentoPagoDet dpd) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(dpd);
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
    public int ObtenerUltimoNroComprobante(int idTipoDoc, String nroSerie) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            String sql = "SELECT DP.NroDocumentoPago\n"
                    + "FROM DocumentoPago DP,TipoDocSerie TDS,SERIE S\n"
                    + "WHERE DP.IdTipoPagoSerie = TDS.Id AND\n"
                    + "TDS.IdSerie = S.IdSerie AND\n"
                    + "S.Serie = '" + nroSerie + "' AND\n"
                    + "TDS.IdTipoDoc = " + idTipoDoc + "\n"
                    + "ORDER BY DP.NroDocumentoPago DESC";
            Query q = sesion.createSQLQuery(sql);
            q.setMaxResults(1);
            int nro = 0;
            if (q.uniqueResult() != null) {
                nro = (int) q.uniqueResult();
            }

//            sesion.save(dpd);
            tx.commit();
            return nro;
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
    public int ObtenerUltimoNroValeAcademico() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            String sql = "SELECT va.nro\n"
                    + "FROM vale_academico va\n"
                    + "WHERE va.borrado = '1' \n"
                    + "ORDER BY va.nro DESC";
            Query q = sesion.createSQLQuery(sql);
            q.setMaxResults(1);
            int nro = 0;
            if (q.uniqueResult() != null) {
                nro = (int) q.uniqueResult();
            }
            tx.commit();
            return nro + 1;
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
    public void ActualizarDocumentoPagoDet(DocumentoPagoDet dpd) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            DocumentoPagoDet detDATA = (DocumentoPagoDet) sesion.get(DocumentoPagoDet.class, dpd.getIdDocumentoPagoDet());
            detDATA.setPrecioVenta(dpd.getPrecioVenta());
            detDATA.setIgv(dpd.getIgv());
            detDATA.setValorVenta(dpd.getValorVenta());
            sesion.save(detDATA);
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
    public List BuscarNroComprobantePago(int nroComprobante) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            //q = sesion.createQuery("FROM DocumentoPago WHERE YEAR(fechaPago)=" + anio);
            //q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.tipoPagador FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio);
            q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocSerie.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.cliente.tipoCliente,dp.cliente.papePat+' '+dp.cliente.papeMat+' '+dp.cliente.pnombre,dp.cliente.ccodigoCole,dp.cliente.pdireccionDomicilio,dp.cliente.snombreSociedad,dp.cliente.scodigoSoc,dp.cliente.sdireccion,dp.cliente.eruc,dp.cliente.enombre,dp.cliente.edireccion,dp.estado,dp.cliente.idCliente,dp.cliente.cfechaAfiliacion,dp.cliente.sfechaAfiliacion,dp.tipoDocSerie.tipoDocPago.idTipoDocPago,dp.tieneIgv,dp.cliente.pnroDocumento,dp.nombreCliente,dp.moneda FROM DocumentoPago dp WHERE dp.nroDocumentoPago=" + nroComprobante + " ORDER BY dp.nroSerie,dp.nroDocumentoPago");
            resultadoBusqueda = (List<Object>) q.list();
            /*q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.tipoPagador,dp.sociedad.nombreSociedad,dp.sociedad.codigoSoc,dp.sociedad.direccion FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio);
             resultadoBusqueda.addAll((List<Object>) q.list());*/
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
    public List obtenerTodosDerechoHabiente() {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List resultadoBusqueda = null;
            String sql = "select \n"
                    + "Cliente.CCodigoCole,Cliente.PApePat + ' ' + Cliente.PApeMat as apellido, Cliente.PNombre,\n"
                    + "cliente_derecho_habiente.nombre as nombre_derecho_habiente,\n"
                    + "cliente_derecho_habiente.ape_pat + ' ' + cliente_derecho_habiente.ape_mat as apellido_derecho_habiente,\n"
                    + "cliente_derecho_habiente.parentesco,\n"
                    + "cliente_derecho_habiente.dni,\n"
                    + "cliente_derecho_habiente.fecha_nacimiento,\n"
                    + "cliente_derecho_habiente.email,\n"
                    + "cliente_derecho_habiente.celular,\n"
                    + "cliente_derecho_habiente.sexo\n"
                    + "from cliente_derecho_habiente,Cliente\n"
                    + "where cliente_derecho_habiente.borrado = '1' and\n"
                    + "cliente_derecho_habiente.id_cliente = Cliente.IdCliente;";
            Query q = sesion.createSQLQuery(sql);
            resultadoBusqueda = (List<Object>) q.list();
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
    public List buscarValeAcademico(String filtro) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            String sql = "select va.id,va.nro,va.fecha_inicio,va.fecha_fin\n"
                    + "from vale_academico va,DocumentoPagoDet dpd,DocumentoPago dp,Cliente c\n"
                    + "where va.borrado like '1' and\n"
                    + "va.id_documento_pago_detalle =  dpd.IdDocumentoPagoDet and\n"
                    + "dpd.IdDocumentoPago = dp.IdDocumentoPago \n"
                    + " AND case \n"
                    + "      when dp.IdContadorEmpresa is not null \n"
                    + "      then dp.IdContadorEmpresa\n"
                    + "      else dp.IdCliente\n"
                    + "      end = c.IdCliente\n"
                    + filtro;
            Query q = sesion.createSQLQuery(sql);
//            Query q = sesion.createQuery("FROM ValeAcademico va WHERE borrado like '1' " + filtro + " ORDER BY va.id DESC");
            List resultadoBusqueda = q.list();
            List<ValeAcademico> listado = new ArrayList<ValeAcademico>();
            for (Object va  : resultadoBusqueda) {
                Object obj[] = (Object[]) va;
                int id = (int) obj[0];
                ValeAcademico oDATA = (ValeAcademico) sesion.get("caja.mapeo.entidades.ValeAcademico", id);
                if (oDATA.getDocumentoPagoDetalle() != null) {
                    Hibernate.initialize(oDATA.getDocumentoPagoDetalle());
                    Hibernate.initialize(oDATA.getDocumentoPagoDetalle().getDocumentoPago());
                    Hibernate.initialize(oDATA.getDocumentoPagoDetalle().getDocumentoPago().getCliente());
                }
                listado.add(oDATA);
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
    public List buscarValeAcademicoDetalle(String filtro) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM ValeAcademicoConsumo va WHERE va.valeAcademico.borrado like '1' AND va.borrado like '1' " + filtro + " ORDER BY va.id DESC");
            List<ValeAcademicoConsumo> resultadoBusqueda = q.list();
            for (ValeAcademicoConsumo va  : resultadoBusqueda) {
                if (va.getValeAcademico().getDocumentoPagoDetalle() != null) {
                    Hibernate.initialize(va.getValeAcademico().getDocumentoPagoDetalle());
                    Hibernate.initialize(va.getValeAcademico().getDocumentoPagoDetalle().getDocumentoPago());
                    Hibernate.initialize(va.getValeAcademico().getDocumentoPagoDetalle().getDocumentoPago().getCliente());
                }
                Hibernate.initialize(va.getInversion());
                Hibernate.initialize(va.getInversion().getEvento());
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
    public List BuscarComprobantePagoPorAnio(int anio) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            //q = sesion.createQuery("FROM DocumentoPago WHERE YEAR(fechaPago)=" + anio);
            //q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.tipoPagador FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio);
            q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocSerie.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.cliente.tipoCliente,dp.cliente.papePat+' '+dp.cliente.papeMat+' '+dp.cliente.pnombre,dp.cliente.ccodigoCole,dp.cliente.pdireccionDomicilio,dp.cliente.snombreSociedad,dp.cliente.scodigoSoc,dp.cliente.sdireccion,dp.cliente.eruc,dp.cliente.enombre,dp.cliente.edireccion,dp.estado,dp.cliente.idCliente,dp.cliente.cfechaAfiliacion,dp.cliente.sfechaAfiliacion,dp.tipoDocSerie.tipoDocPago.idTipoDocPago,dp.tieneIgv,dp.cliente.pnroDocumento,dp.nombreCliente,dp.moneda FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio + " ORDER BY dp.nroSerie,dp.nroDocumentoPago");
            resultadoBusqueda = (List<Object>) q.list();
            /*q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.tipoPagador,dp.sociedad.nombreSociedad,dp.sociedad.codigoSoc,dp.sociedad.direccion FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio);
             resultadoBusqueda.addAll((List<Object>) q.list());*/
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
    public List BuscarComprobantePagoPorAnioMes(int anio, int mes) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Object> resultadoBusqueda = null;
            Query q = null;
            //q = sesion.createQuery("FROM DocumentoPago WHERE YEAR(fechaPago)=" + anio + " AND MONTH(fechaPago)=" + mes);
            q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocSerie.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.cliente.tipoCliente,dp.cliente.papePat+' '+dp.cliente.papeMat+' '+dp.cliente.pnombre,dp.cliente.ccodigoCole,dp.cliente.pdireccionDomicilio,dp.cliente.snombreSociedad,dp.cliente.scodigoSoc,dp.cliente.sdireccion,dp.cliente.eruc,dp.cliente.enombre,dp.cliente.edireccion,dp.estado,dp.cliente.idCliente,dp.cliente.cfechaAfiliacion,dp.cliente.sfechaAfiliacion,dp.tipoDocSerie.tipoDocPago.idTipoDocPago,dp.tieneIgv,dp.cliente.pnroDocumento,dp.nombreCliente,dp.moneda  FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio + " AND MONTH(dp.fechaPago)=" + mes + " ORDER BY dp.nroSerie,dp.nroDocumentoPago");
            resultadoBusqueda = (List<Object>) q.list();
            /*q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.tipoPagador,dp.sociedad.nombreSociedad,dp.sociedad.codigoSoc,dp.sociedad.direccion FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio + " AND MONTH(dp.fechaPago)=" + mes);
             resultadoBusqueda.addAll((List<Object>) q.list());*/
            //resultadoBusqueda = (List<DocumentoPago>) q.list();
            /*for (DocumentoPago dp : resultadoBusqueda) {
             Hibernate.initialize(dp.getTipoDocPago());
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
    public List BuscarComprobantePagoPorAnioMesDia(int anio, int mes, int dia) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<Object> resultadoBusqueda = null;
            Query q = null;
            //q = sesion.createQuery("FROM DocumentoPago  WHERE YEAR(fechaPago)=" + anio + " AND MONTH(fechaPago)=" + mes + " AND DAY(fechaPago)=" + dia);
            q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocSerie.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.cliente.tipoCliente,dp.cliente.papePat+' '+dp.cliente.papeMat+' '+dp.cliente.pnombre,dp.cliente.ccodigoCole,dp.cliente.pdireccionDomicilio,dp.cliente.snombreSociedad,dp.cliente.scodigoSoc,dp.cliente.sdireccion,dp.cliente.eruc,dp.cliente.enombre,dp.cliente.edireccion,dp.estado,dp.cliente.idCliente,dp.cliente.cfechaAfiliacion,dp.cliente.sfechaAfiliacion,dp.tipoDocSerie.tipoDocPago.idTipoDocPago,dp.tieneIgv,dp.cliente.pnroDocumento,dp.nombreCliente,dp.moneda  FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio + " AND MONTH(dp.fechaPago)=" + mes + " AND DAY(dp.fechaPago)=" + dia + " ORDER BY dp.nroSerie,dp.nroDocumentoPago");
            resultadoBusqueda = (List<Object>) q.list();
            /*q = sesion.createQuery("SELECT dp.idDocumentoPago,dp.tipoDocPago.nombreDocPago,dp.nroSerie,dp.nroDocumentoPago,dp.fechaPago,dp.tipoPagador,dp.sociedad.nombreSociedad,dp.sociedad.codigoSoc,dp.sociedad.direccion FROM DocumentoPago dp WHERE YEAR(dp.fechaPago)=" + anio + " AND MONTH(dp.fechaPago)=" + mes + " AND DAY(dp.fechaPago)=" + dia);
             resultadoBusqueda.addAll((List<Object>) q.list());*/
            //resultadoBusqueda = (List<DocumentoPago>) q.list();
            /*for (DocumentoPago dp : resultadoBusqueda) {
             Hibernate.initialize(dp.getTipoDocPago());
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

    /*@Override
     public List ObtenerTodasSociedades() {
     try {
     sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
     tx = sesion.beginTransaction();
     List<Sociedad> resultado = null;
     Query q = null;
     q = sesion.createQuery("FROM Sociedad");
     resultado = (List<Sociedad>) q.list();
     tx.commit();
     return resultado;

     } catch (RuntimeException e) {
     try {
     tx.rollback();
     } catch (RuntimeException rbe) {
     rbe.printStackTrace();
     }
     throw e;
     }
     }*/
    @Override
    public double obtenerMontoTotalComprobante(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            double monto = 0;
            Query q = null;
            q = sesion.createQuery("SELECT SUM(dpd.valorVenta+dpd.igv) FROM DocumentoPagoDet dpd WHERE dpd.documentoPago.idDocumentoPago = " + idDocumentoPago);
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                monto = (double) q.uniqueResult();
            }
            tx.commit();
            return monto;
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
    public Cobrador ObtenerCobradorComprobantePago(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            DocumentoPago dpDATA = (DocumentoPago) sesion.get(DocumentoPago.class, idDocumentoPago);
            Hibernate.initialize(dpDATA.getCliente());
            Hibernate.initialize(dpDATA.getCobrador().getCliente());
            Cobrador cobrador = dpDATA.getCobrador();
            tx.commit();
            return cobrador;
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
    public String ObtenerPeriodoCancelado(int idDocumentoPago) {
        try {
            String sql = "select cast(AnioHasta as varchar(4)) + REPLICATE('0', 2-LEN(MesHasta)) + cast(MesHasta as varchar(2))\n"
                    + "from DocumentoPagoDet \n"
                    + "where \n"
                    + "DocumentoPagoDet.IdDocumentoPago = " + idDocumentoPago + " and\n"
                    + "IdConceptoPagoDetalle = 3;";
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createSQLQuery(sql);
            q.setMaxResults(1);
            String periodo = "";
            if (q.uniqueResult() != null) {
                periodo = (String) q.uniqueResult();
            }
            tx.commit();
            return periodo;
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
    public double ObtenerIgv(int idDocumentoPago) {
        try {
            String sql = "select top 1 IgvPorcentaje\n"
                    + "from DocumentoPagoDet \n"
                    + "where \n"
                    + "DocumentoPagoDet.IdDocumentoPago = " + idDocumentoPago + " and\n"
                    + "IgvPorcentaje > 0;";
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createSQLQuery(sql);
//            q.setMaxResults(1);
            double igv = 0.0;
            if (q.list().size() > 0) {
                igv = (double) q.uniqueResult();
            }
            tx.commit();
            return igv;
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
    public int obtenerIDUsuario_SegunIDCobrador(int idCobrador) {
        try {
            String sql = "SELECT USUARIO.IdUsuario \r\n"
                    + "FROM CLIENTE,COBRADOR,USUARIO\r\n"
                    + "WHERE CLIENTE.IdCliente = Cobrador.IdCliente AND\r\n"
                    + "Usuario.IdCliente = Cliente.IdCliente AND\r\n"
                    + "Cobrador.IdCobrador = :idCobrador";
            Query q = sesion.createSQLQuery(sql).setParameter("idCobrador", idCobrador).setMaxResults(1);
            int idUsuario = 0;
            if (q.uniqueResult() != null) {
                idUsuario = (int) q.uniqueResult();
            }
            tx.commit();
            return idUsuario;
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
    public List ObtenerDetalleComprobante(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<DocumentoPagoDet> resultado = null;
            Query q = sesion.createQuery("FROM DocumentoPagoDet dpd WHERE dpd.documentoPago.idDocumentoPago=" + idDocumentoPago);
            resultado = (List<DocumentoPagoDet>) q.list();
            for (DocumentoPagoDet dp : resultado) {
                Hibernate.initialize(dp.getConceptoPagoDetalle());
            }
            tx.commit();
            return resultado;
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
    public List obtenerValeAcademicoConsumoDetalle(int idValeAcademico) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<ValeAcademicoConsumo> resultado = null;
            Query q = sesion.createQuery("FROM ValeAcademicoConsumo dpd WHERE dpd.borrado = '1' AND dpd.valeAcademico.id=" + idValeAcademico + " ORDER BY dpd.id DESC");
            resultado = (List<ValeAcademicoConsumo>) q.list();
            for (ValeAcademicoConsumo dp : resultado) {
                Hibernate.initialize(dp.getInversion());
                Hibernate.initialize(dp.getInversion().getEvento());
                Hibernate.initialize(dp.getInversion().getTipoParticipante());
                Hibernate.initialize(dp.getInversion().getModalidadPago());
            }
            tx.commit();
            return resultado;
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
    public List obtenerValeAcademicoConsumoDetalleParticipante(int idValeAcademicoConsumo) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<ValeAcademicoConsumoParticipante> resultado = null;
            Query q = sesion.createQuery("FROM ValeAcademicoConsumoParticipante dpd WHERE dpd.borrado = '1' AND dpd.valeAcademicoConsumo.id=" + idValeAcademicoConsumo + " ORDER BY dpd.id DESC");
            resultado = (List<ValeAcademicoConsumoParticipante>) q.list();
            for (ValeAcademicoConsumoParticipante dp : resultado) {
                Hibernate.initialize(dp.getParticipante());
            }
            tx.commit();
            return resultado;
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
    public List ObtenerResumenDiarioDetalle(int id) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<ResumenDiarioDetalle> resultado = null;
            String hql = "FROM ResumenDiarioDetalle dpd WHERE dpd.resumenDiario.id = " + id + " ORDER BY dpd.id ASC";
            System.out.println(hql);
            Query q = sesion.createQuery(hql);
            resultado = (List<ResumenDiarioDetalle>) q.list();
            for (ResumenDiarioDetalle dp : resultado) {
                Hibernate.initialize(dp.getDocumentoPago());
                Hibernate.initialize(dp.getDocumentoPago().getTipoDocSerie());
                Hibernate.initialize(dp.getDocumentoPago().getTipoDocSerie().getTipoDocPago());
                Hibernate.initialize(dp.getDocumentoPago().getTipoDocSerie().getSerie());
                Hibernate.initialize(dp.getDocumentoPago().getCliente());
            }
            tx.commit();
            return resultado;
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
    public DocumentoPagoDet ObtenerUltimaCuotaOrdinariaContador(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List<DocumentoPagoDet> resultado = null;
            Query q = null;
            q = sesion.createQuery("FROM DocumentoPagoDet dpd WHERE dpd.conceptoPagoDetalle.idConceptoPagoDetalle = 1 AND dpd.documentoPago.cliente.idCliente= " + idCliente + " ORDER BY dpd.idDocumentoPagoDet DESC");
            q.setMaxResults(1);
            resultado = (List<DocumentoPagoDet>) q.list();
            DocumentoPagoDet detalle = null;
            if (resultado != null) {
                detalle = (DocumentoPagoDet) q.uniqueResult();
            }
            tx.commit();
            return detalle;

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
    public void CulminarComprobante(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            DocumentoPago dpDATA = (DocumentoPago) sesion.get(DocumentoPago.class, idDocumentoPago);
            dpDATA.setEstado("C");
            sesion.update(dpDATA);
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
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc, String nroSerie) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM TipoDocSerie tds WHERE tds.serie.serie = '" + nroSerie + "' AND tds.tipoDocPago.idTipoDocPago = " + idTipoDoc);
            q.setMaxResults(1);
            TipoDocSerie resultado = (TipoDocSerie) q.uniqueResult();
            Hibernate.initialize(resultado.getTipoDocPago());
            tx.commit();
            return resultado;

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
    public List ObtenerDetalleComprobanteElmininar(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            List resultado = null;
            Query q = null;
            q = sesion.createQuery("SELECT dpd.documentoPago.tipoDocSerie.tipoDocPago.nombreDocPago,dpd.documentoPago.nroSerie,dpd.documentoPago.nroDocumentoPago,dpd.documentoPago.fechaPago,dpd.conceptoPagoDetalle.idConceptoPagoDetalle,dpd.precioUnitario,dpd.cantidad FROM DocumentoPagoDet dpd WHERE dpd.documentoPago.idDocumentoPago = " + idDocumentoPago);
            resultado = (List<Object>) q.list();
            tx.commit();
            return resultado;

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
    public void EliminarDetalleComprobante(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();

            String sql = "";
            String hql = "FROM DocumentoPagoDet dpd WHERE dpd.documentoPago.idDocumentoPago = " + idDocumentoPago;
            List<DocumentoPagoDet> resultado = null;
            Query q = sesion.createQuery(hql);
            resultado = (List<DocumentoPagoDet>) q.list();
            for (DocumentoPagoDet dpd : resultado) {
                hql = "DELETE from Alumnos a where a.documentoPagoDet.idDocumentoPagoDet = :id";
                Query consulta = sesion.createQuery(hql);
                consulta.setInteger("id", dpd.getIdDocumentoPagoDet());
                consulta.executeUpdate();

                hql = "DELETE from CongresoParticipantes cp where cp.documentoPagoDet.idDocumentoPagoDet = :id";
                consulta = sesion.createQuery(hql);
                consulta.setInteger("id", dpd.getIdDocumentoPagoDet());
                consulta.executeUpdate();

                hql = "DELETE from Deuda d where d.documentoPagoDet.idDocumentoPagoDet = :id";
                consulta = sesion.createQuery(hql);
                consulta.setInteger("id", dpd.getIdDocumentoPagoDet());
                consulta.executeUpdate();

                hql = "DELETE from CuentaCorriente d where d.documentoPagoDet.idDocumentoPagoDet = :id";
                consulta = sesion.createQuery(hql);
                consulta.setInteger("id", dpd.getIdDocumentoPagoDet());
                consulta.executeUpdate();

                hql = "DELETE from Cuotas c where c.documentoPagoDet.idDocumentoPagoDet = :id";
                consulta = sesion.createQuery(hql);
                consulta.setInteger("id", dpd.getIdDocumentoPagoDet());
                consulta.executeUpdate();

                sql = "UPDATE cursos.evento_asignacion_evento_participante set borrado = '0' FROM cursos.participante p WHERE p.id = cursos.evento_asignacion_evento_participante.id_participante and p.id_documento_pago_det = :id";
                consulta = sesion.createSQLQuery(sql);
                consulta.setInteger("id", dpd.getIdDocumentoPagoDet());
                consulta.executeUpdate();

                sql = "UPDATE cursos.participante SET id_documento_pago_det = null, borrado = '0' WHERE id_documento_pago_det = :id";
                consulta = sesion.createSQLQuery(sql);
                consulta.setInteger("id", dpd.getIdDocumentoPagoDet());
                consulta.executeUpdate();

                sql = "UPDATE vale_academico SET id_documento_pago_detalle = null, borrado = '0' WHERE id_documento_pago_detalle = " + dpd.getIdDocumentoPagoDet();
                consulta = sesion.createSQLQuery(sql);
                consulta.executeUpdate();

                sql = "UPDATE constancia_habilitacion SET id_documento_pago_det = null, borrado = '0' WHERE id_documento_pago_det = " + dpd.getIdDocumentoPagoDet();
                consulta = sesion.createSQLQuery(sql);
                consulta.executeUpdate();
            }

            hql = "DELETE from DocumentoPagoDet dpd where dpd.documentoPago.idDocumentoPago = :id";
            Query query = sesion.createQuery(hql);
            query.setInteger("id", idDocumentoPago);
            query.executeUpdate();
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
    public void EliminarComprobante(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            String hql = "DELETE from Operacion dp where dp.documentoPago.idDocumentoPago = :id";
            Query query = sesion.createQuery(hql);
            query.setInteger("id", idDocumentoPago);
            query.executeUpdate();
            hql = "DELETE from DocumentoPago dp where dp.idDocumentoPago = :id";
            query = sesion.createQuery(hql);
            query.setInteger("id", idDocumentoPago);
            query.executeUpdate();
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
    public void AnularComprobante(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            String hql = "DELETE from Operacion dp where dp.documentoPago.idDocumentoPago = :id";
            Query query = sesion.createQuery(hql);
            query.setInteger("id", idDocumentoPago);
            query.executeUpdate();
            DocumentoPago dpDATA = (DocumentoPago) sesion.get(DocumentoPago.class, idDocumentoPago);
            dpDATA.setEstado("ANULADO"); //[ANULADO]
            sesion.update(dpDATA);
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
    public void GuardarDocumentoAnulado(DocPagoAnulado dpa) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(dpa);
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
    public void GuardarDocumentoAnuladoDetalle(DocPagoAnuladoDetalle dpad) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(dpad);
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
    public AnioMes ObtenerAnioMesCuotaDocumentoPago(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            String hql = "SELECT c.anioMes FROM Cuotas c where c.documentoPagoDet.documentoPago.idDocumentoPago = :id ORDER BY c.anioMes.nroOrden DESC";
            q = sesion.createQuery(hql);
            q.setInteger("id", idDocumentoPago);
            q.setMaxResults(1);
            AnioMes anioMesCuota = null;
            if (q.uniqueResult() != null) {
                anioMesCuota = (AnioMes) q.uniqueResult();
            }
            tx.commit();
            return anioMesCuota;

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
    public FinanciamientoDocumentoPago ObtenerFinanciamientoDocumentoPago(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            String hql = "FROM FinanciamientoDocumentoPago fdp where fdp.documentoPagoDet.documentoPago.idDocumentoPago = :id ORDER BY fdp.nroCuota DESC";
            q = sesion.createQuery(hql);
            q.setInteger("id", idDocumentoPago);
            q.setMaxResults(1);
            FinanciamientoDocumentoPago fdp = null;
            if (q.uniqueResult() != null) {
                fdp = (FinanciamientoDocumentoPago) q.uniqueResult();
            }
            tx.commit();
            return fdp;

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
    public FinanciamientoDocumentoPago ObtenerFinanciamientoDocumentoPagoDet(int idDocumentoPagoDet) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            String hql = "FROM FinanciamientoDocumentoPago fdp where fdp.documentoPagoDet.idDocumentoPagoDet = :id ORDER BY fdp.nroCuota DESC";
            q = sesion.createQuery(hql);
            q.setInteger("id", idDocumentoPagoDet);
            q.setMaxResults(1);
            FinanciamientoDocumentoPago fdp = null;
            if (q.uniqueResult() != null) {
                fdp = (FinanciamientoDocumentoPago) q.uniqueResult();
            }
            tx.commit();
            return fdp;

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
    public ReincorporacionDocumentoPago ObtenerReincorporacionDocumentoPago(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            String hql = "FROM ReincorporacionDocumentoPago rdp where rdp.documentoPagoDet.documentoPago.idDocumentoPago = :id ORDER BY rdp.nroCuota DESC";
            q = sesion.createQuery(hql);
            q.setInteger("id", idDocumentoPago);
            q.setMaxResults(1);
            ReincorporacionDocumentoPago rdp = null;
            if (q.uniqueResult() != null) {
                rdp = (ReincorporacionDocumentoPago) q.uniqueResult();
            }
            tx.commit();
            return rdp;

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
    public AnioMes ObtenerAnioMesUltimaCuota(int idCliente, int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            /*OBTENGO ULTIMA CUOTA CLIENTE*/
            String hql = "SELECT c.anioMes FROM Cuotas c where c.cliente.idCliente = :idCliente ORDER BY c.anioMes.nroOrden DESC";
            q = sesion.createQuery(hql);
            q.setInteger("idCliente", idCliente);
            q.setMaxResults(1);
            AnioMes ultimaAnioMesCuota = null;
            if (q.uniqueResult() != null) {
                ultimaAnioMesCuota = (AnioMes) q.uniqueResult();
            }
            tx.commit();
            return ultimaAnioMesCuota;

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
    public FinanciamientoDocumentoPago ObtenerUltimoFinanciamiento(int idCliente, int idFinanciamiento) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            /*OBTENGO ULTIMA CUOTA CLIENTE*/
            String hql = "FROM FinanciamientoDocumentoPago fdp where (fdp.estado = 'FC' OR fdp.estado = 'CI') AND fdp.financiamiento.cliente.idCliente = :idCliente AND fdp.financiamiento.idFinanciamiento=:idFinanciamiento ORDER BY fdp.financiamiento.idFinanciamiento,fdp.nroCuota DESC";
            q = sesion.createQuery(hql);
            q.setInteger("idCliente", idCliente);
            q.setInteger("idFinanciamiento", idFinanciamiento);
            q.setMaxResults(1);
            FinanciamientoDocumentoPago ultimoFinanciamiento = null;
            if (q.uniqueResult() != null) {
                ultimoFinanciamiento = (FinanciamientoDocumentoPago) q.uniqueResult();
            }
            tx.commit();
            return ultimoFinanciamiento;

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
    public Financiamiento ObtenerCabeceraUltimoFinanciamiento(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            /*OBTENGO ULTIMA CUOTA CLIENTE*/
            String hql = "FROM Financiamiento f where f.cliente.idCliente = :idCliente ORDER BY f.nroFinanciamiento DESC";
            q = sesion.createQuery(hql);
            q.setInteger("idCliente", idCliente);
            q.setMaxResults(1);
            Financiamiento f = null;
            if (q.uniqueResult() != null) {
                f = (Financiamiento) q.uniqueResult();
            }
            tx.commit();
            return f;

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
    public ReincorporacionDocumentoPago ObtenerUltimoReincorporacion(int idCliente) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            /*OBTENGO ULTIMA CUOTA CLIENTE*/
            String hql = "FROM ReincorporacionDocumentoPago rdp where rdp.estado = 'RC' AND rdp.reincorporacion.cliente.idCliente = :idCliente ORDER BY rdp.nroCuota DESC";
            q = sesion.createQuery(hql);
            q.setInteger("idCliente", idCliente);
            q.setMaxResults(1);
            ReincorporacionDocumentoPago ultimoFinanciamiento = null;
            if (q.uniqueResult() != null) {
                ultimoFinanciamiento = (ReincorporacionDocumentoPago) q.uniqueResult();
            }
            tx.commit();
            return ultimoFinanciamiento;

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
    public void EliminarCuentasCorrientes(int idCliente, int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            //String hql = "DELETE from CuentaCorriente cc where cc.documentoPagoDet.documentoPago.idDocumentoPago = :idDoc AND cc.cliente.idCliente = :idClient";
            String hql = "Delete CuentaCorriente cc where cc.idCuentaCorriente in ( Select c.idCuentaCorriente From CuentaCorriente c Where c.cliente.idCliente = :idClient AND c.documentoPagoDet.documentoPago.idDocumentoPago = :idDoc)";
            Query query = sesion.createQuery(hql);
            query.setInteger("idDoc", idDocumentoPago);
            query.setInteger("idClient", idCliente);
            query.executeUpdate();
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
    public void EliminarCuotasCliente(int idCliente, int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            //String hql = "DELETE from Cuotas c where c.documentoPagoDet.documentoPago.idDocumentoPago = :idDocumento AND c.cliente.idCliente = :idCliente";
            String hql = "Delete Cuotas c where c.id in ( Select cuota.id From Cuotas cuota Where cuota.cliente.idCliente = :idClient AND cuota.documentoPagoDet.documentoPago.idDocumentoPago = :idDoc)";
            Query query = sesion.createQuery(hql);
            query.setInteger("idDoc", idDocumentoPago);
            query.setInteger("idClient", idCliente);
            query.executeUpdate();
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
    public void EliminarFinanciamiento(int idDocumentoPagoDetalle) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            SQLQuery q = sesion.createSQLQuery("UPDATE FinanciamientoDocumentoPago SET IdDocPagoDet = null,Estado = 'FS' WHERE IdDocPagoDet = " + idDocumentoPagoDetalle);
            q.executeUpdate();
            /*FinanciamientoDocumentoPago fdpDATA = (FinanciamientoDocumentoPago) sesion.get(FinanciamientoDocumentoPago.class, idFinanciamientoDocumentoPago);
             fdpDATA.setEstado("FS");
             fdpDATA.setDocumentoPagoDet(null);
             sesion.update(fdpDATA);*/
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
    public void EliminarTodoFinanciamiento(int idFinanciamientoDetalle) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();

            Query q = null;
            /*OBTENGO ULTIMA CUOTA CLIENTE*/
            String hql = "SELECT fdp.financiamiento FROM FinanciamientoDocumentoPago fdp where fdp.id = :idFinanciamientoDetalle";
            q = sesion.createQuery(hql);
            q.setInteger("idFinanciamientoDetalle", idFinanciamientoDetalle);
            q.setMaxResults(1);
            Financiamiento fCabecera = null;
            if (q.uniqueResult() != null) {
                fCabecera = (Financiamiento) q.uniqueResult();
            }
            SQLQuery qSQL = sesion.createSQLQuery("DELETE FROM  FinanciamientoDocumentoPago WHERE IdFinanciamiento = " + fCabecera.getIdFinanciamiento());
            qSQL.executeUpdate();

            qSQL = sesion.createSQLQuery("UPDATE FinanciamientoDocumentoPago SET IdFinanciamientoNuevo = 0,Estado = 'FS' WHERE IdFinanciamientoNuevo = " + fCabecera.getIdFinanciamiento());
            qSQL.executeUpdate();

            qSQL = sesion.createSQLQuery("UPDATE Deuda SET IdFinanciamientoNuevo = 'NULL', IdDocPagDet = 'NULL', Estado = 'DP' WHERE IdFinanciamientoNuevo = " + fCabecera.getIdFinanciamiento());
            qSQL.executeUpdate();

            sesion.delete(fCabecera);
            /*FinanciamientoDocumentoPago fdpDATA = (FinanciamientoDocumentoPago) sesion.get(FinanciamientoDocumentoPago.class, idFinanciamientoDocumentoPago);
             fdpDATA.setEstado("FS");
             fdpDATA.setDocumentoPagoDet(null);
             sesion.update(fdpDATA);*/
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
    public void EliminarReincorporacion(int idDocumentoPagoDetalle) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            SQLQuery q = sesion.createSQLQuery("UPDATE ReincorporacionDocumentoPago SET IdDocPagoDetalle = null,Estado = 'RS' WHERE IdDocPagoDetalle = " + idDocumentoPagoDetalle);
            q.executeUpdate();
            //ReincorporacionDocumentoPago rdpDATA = (ReincorporacionDocumentoPago) sesion.get(ReincorporacionDocumentoPago.class, idReincorporacionDocumentoPago);
            //rdpDATA.setDocumentoPagoDet(null);
            //rdpDATA.setEstado("RS");
            //sesion.save(rdpDATA);
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
    public boolean CerrarCaja(String fecha) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            SQLQuery q = sesion.createSQLQuery("SELECT Estado FROM CierreDiario WHERE Fecha = '" + fecha + "'");
            q.setMaxResults(1);
            String Estado = (String) q.uniqueResult();
            if (Estado.equals("A")) {
                q = sesion.createSQLQuery("UPDATE CierreDiario SET Estado = 'C' WHERE Fecha = '" + fecha + "'");
                q.executeUpdate();
                tx.commit();
                return true;
            } else {
                tx.commit();
                return false;
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
    public boolean ComprobanteTieneCurso(int id) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            SQLQuery q = sesion.createSQLQuery("SELECT DOCUMENTOPAGODET.IDDOCUMENTOPAGO FROM DOCUMENTOPAGODET,CONCEPTOPAGODETALLE WHERE CONCEPTOPAGODETALLE.TIPOCODIGO = '04' AND CONCEPTOPAGODETALLE.IDCONCEPTOPAGODETALLE = DOCUMENTOPAGODET.IDCONCEPTOPAGODETALLE AND DOCUMENTOPAGODET.IDDOCUMENTOPAGO = " + id);
            List l = q.list();
            boolean tieneCurso = false;
            if (l.size() > 0) {
                tieneCurso = true;
            }
            tx.commit();
            return tieneCurso;
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
    public boolean EstaCerradoElDia(String fecha) {
        try {
            boolean estaCerrado = false;
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            SQLQuery q = sesion.createSQLQuery("SELECT Estado FROM CierreDiario WHERE Fecha = '" + fecha + "'");
            q.setMaxResults(1);
            String Estado = (String) q.uniqueResult();
            if (Estado.equals("A")) {
                estaCerrado = false;
            } else {
                estaCerrado = true;
            }
            tx.commit();
            return estaCerrado;
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
    public void ActualizarCabeceraDocumento(DocumentoPago doc) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.update(doc);
            tx.commit();
        } catch (RuntimeException e) {
            try {
                tx.rollback();
                e.printStackTrace();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public DocumentoPago ObtenerDocumentoPago(int idDocPago) {
        try {
            DocumentoPago doc = null;
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            doc = (DocumentoPago) sesion.get(DocumentoPago.class, idDocPago);
            sesion.update(doc);
            tx.commit();
            return doc;
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
    public List ObtenerDocumentosPagos_SegunListado(List l) {
        try {
//            DocumentoPago doc = null;
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("from DocumentoPago d where d.idDocumentoPago in (:ids)").setParameterList("ids", l);
            List<DocumentoPago> listado = q.list();
            for (DocumentoPago dp : listado) {
                Hibernate.initialize(dp.getCliente());
                Hibernate.initialize(dp.getTipoDocSerie());
                Hibernate.initialize(dp.getTipoDocSerie().getTipoDocPago());
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
    public void AnularDetalle(int idDocumentoPago, DocumentoPagoDet det) {
        this.EliminarDetalleComprobante(idDocumentoPago);
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            sesion.save(det);
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
    public List ObtenerVentas(String desde, String hasta) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            //Query q = null;
            List lResultado;
            String hql = "SELECT ConceptoPagoDetalle.TipoCodigo + ConceptoPagoDetalle.Codigo AS CODIGO,ConceptoPagoDetalle.Descripcion + ' ' + ISNULL(DocumentoPagoDet.Observacion,''),TipoDocPago.NombreDocPago,\n"
                    + "RIGHT('0000' + Ltrim(Rtrim(DocumentoPago.NroSerie)),4) AS SERIE,RIGHT('000000000000000' + Ltrim(Rtrim(DocumentoPago.NroDocumentoPago)),15) AS NRODOCUMENTO,DocumentoPago.FechaPago,DocumentoPago.NombreCliente,DocumentoPagoDet.ValorVenta,DocumentoPagoDet.Igv,DocumentoPagoDet.ValorVenta+DocumentoPagoDet.Igv AS TOTAL,\n"
                    + "(SELECT CLIENTE.PApePat + ' ' + CLIENTE.PApeMat + ' ' + CLIENTE.PNombre FROM Cobrador,Cliente WHERE COBRADOR.IdCobrador = DocumentoPago.IdCobrador AND COBRADOR.IdCliente = CLIENTE.IdCliente) AS COBRADOR\n"
                    + "FROM DocumentoPago,DocumentoPagoDet,ConceptoPagoDetalle,TipoDocSerie,TipoDocPago\n"
                    + "WHERE DocumentoPago.IdDocumentoPago = DocumentoPagoDet.IdDocumentoPago AND\n"
                    + "DocumentoPagoDet.IdConceptoPagoDetalle = ConceptoPagoDetalle.IdConceptoPagoDetalle AND\n"
                    + "DocumentoPago.IdTipoPagoSerie = TipoDocSerie.Id AND\n"
                    + "TipoDocSerie.IdTipoDoc = TipoDocPago.IdTipoDocPago AND DocumentoPago.FechaPago >= '" + desde + "' AND\n"
                    + "DocumentoPago.FechaPago <= '" + hasta + "'";
            SQLQuery q = sesion.createSQLQuery(hql);
            lResultado = q.list();
            tx.commit();
            return lResultado;
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
    public List BuscarContadorPorNombre(String busqueda) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            q = sesion.createQuery("FROM DocumentoPago WHERE nombreCliente LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<DocumentoPago>) q.list();
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
    public List BuscarContadorPorNumero(String busqueda) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = sesion.createQuery("FROM DocumentoPago WHERE nroDocumentoPago LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<DocumentoPago>) q.list();
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
    public List ObtenerComprobantesDesdeHasta(String fechaDesde, String fechaHasta) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM DocumentoPagoDet dpd WHERE dpd.documentoPago.fechaPago BETWEEN '" + fechaDesde + "' AND '" + fechaHasta + "' ORDER BY dpd.documentoPago.idDocumentoPago,dpd.documentoPago.fechaPago ASC");
            List<DocumentoPagoDet> lista = (List<DocumentoPagoDet>) q.list();
            for (DocumentoPagoDet dpd : lista) {
                Hibernate.initialize(dpd.getDocumentoPago());
                Hibernate.initialize(dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago());
                Hibernate.initialize(dpd.getDocumentoPago().getCliente());
                Hibernate.initialize(dpd.getConceptoPagoDetalle());
            }
            tx.commit();
            return lista;
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
    public List ObtenerDocumentoPagoDesdeHasta(String fechaDesde, String fechaHasta) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM DocumentoPago dpd WHERE dpd.fechaPago BETWEEN '" + fechaDesde + "' AND '" + fechaHasta + "' ORDER BY dpd.idDocumentoPago,dpd.fechaPago ASC");
            List<DocumentoPago> lista = (List<DocumentoPago>) q.list();
            for (DocumentoPago dpd : lista) {
                Hibernate.initialize(dpd.getTipoDocSerie().getTipoDocPago());
                Hibernate.initialize(dpd.getCliente());
            }
            tx.commit();
            return lista;
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
    public List ObtenerComprobantesAnioMes(int anio, int mes) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM DocumentoPagoDet dpd WHERE YEAR(dpd.documentoPago.fechaPago) = " + anio + " AND MONTH(dpd.documentoPago.fechaPago) = " + mes + " ORDER BY dpd.documentoPago.fechaPago ASC");
            List<DocumentoPagoDet> lista = (List<DocumentoPagoDet>) q.list();
            for (DocumentoPagoDet dpd : lista) {
                Hibernate.initialize(dpd.getDocumentoPago());
                Hibernate.initialize(dpd.getDocumentoPago().getTipoDocSerie().getTipoDocPago());
                Hibernate.initialize(dpd.getDocumentoPago().getCliente());
                Hibernate.initialize(dpd.getConceptoPagoDetalle());
            }
            tx.commit();
            return lista;
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
    public List ObtenerComprobantes_SegunFiltro(String filtro) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM DocumentoPago dp WHERE 1 = 1 AND " + filtro + " ORDER BY dp.fechaPago ASC");
            List<DocumentoPago> lista = (List<DocumentoPago>) q.list();
            for (DocumentoPago dp : lista) {
                Hibernate.initialize(dp.getTipoDocSerie().getTipoDocPago());
                Hibernate.initialize(dp.getCliente());
            }
            tx.commit();
            return lista;
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
    public DocumentoPago ObtenerComprobantePago_SegunTipoDocumentoSerieNro(int idTipoDocumento, String nroSerie, int nroDocumento) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM DocumentoPago dp WHERE dp.tipoDocSerie.tipoDocPago.idTipoDocPago = " + idTipoDocumento + " AND dp.tipoDocSerie.serie.serie = '" + nroSerie + "' AND dp.nroDocumentoPago = " + nroDocumento);
            q.setMaxResults(1);
            DocumentoPago doc = null;
            if (q.uniqueResult() != null) {
                doc = (DocumentoPago) q.uniqueResult();
            }
            tx.commit();
            return doc;
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
    public double ObtenerMontoTotalComprobante(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("SELECT SUM(dpd.valorVenta + dpd.igv) FROM DocumentoPagoDet dpd WHERE dpd.documentoPago.idDocumentoPago = " + idDocumentoPago);
            q.setMaxResults(1);
            double montoTotal = (double) q.uniqueResult();
            tx.commit();
            return montoTotal;
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
    public Operacion verificarExisteNroOperacion(String nroCuenta, String nroOperacion) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM Operacion dp WHERE dp.nroCuenta = '" + nroCuenta + "' AND dp.nroOperacion = '" + nroOperacion + "'");
            q.setMaxResults(1);
            Operacion doc = null;
            if (q.uniqueResult() != null) {
                doc = (Operacion) q.uniqueResult();
                if (doc.getDocumentoPago() != null) {
                    Hibernate.initialize(doc.getDocumentoPago().getTipoDocSerie());
                    Hibernate.initialize(doc.getDocumentoPago().getTipoDocSerie().getTipoDocPago());
                    Hibernate.initialize(doc.getDocumentoPago().getTipoDocSerie().getSerie());
                }
            }
            tx.commit();
            return doc;
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
    public void GenerarNumeracionElectronicaComprobante(DocumentoPago cp) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = null;
            int nroSolicitud = 0;
            String hql = "SELECT cp.nro FROM ComprobantePago cp WHERE cp.comprobanteElectronico = true AND cp.tipoDocumento.id = " + cp.getTipoDocSerie().getTipoDocPago().getIdTipoDocPago() + " AND cp.nroSerie = '" + cp.getNroSerie() + "' ORDER BY cp.nro DESC";
            q = sesion.createQuery(hql);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                nroSolicitud = (int) q.uniqueResult();
            }
            cp.setNroDocumentoPago(nroSolicitud + 1);
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
    public List ObtenerTributosGenerales(int idDocumentoPago) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            String sql = "SELECT DOCUMENTOPAGODET.CodigoTipoTributo,DOCUMENTOPAGODET.NombreTipoTributo,\n"
                    + "DOCUMENTOPAGODET.CodigoInternacionalTipoTributo,SUM(DOCUMENTOPAGODET.ValorVenta) AS VV,SUM(DOCUMENTOPAGODET.Igv) AS IGV\n"
                    + "FROM DOCUMENTOPAGODET \n"
                    + "WHERE \n"
                    + "DOCUMENTOPAGODET.IdDocumentoPago = " + idDocumentoPago + "\n"
                    + "GROUP BY DOCUMENTOPAGODET.CodigoTipoTributo,DOCUMENTOPAGODET.NombreTipoTributo,\n"
                    + "DOCUMENTOPAGODET.CodigoInternacionalTipoTributo";
            Query q = sesion.createSQLQuery(sql);
            List resultado = q.list();
//            for (Object dp : resultado) {
//                Hibernate.initialize(dp.getConceptoPagoDetalle());
//            }
            tx.commit();
            return resultado;
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
    public List BuscarResumenDiario_SegunNroNota(int nro) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM ResumenDiario nc WHERE nc.nro = " + nro + " ORDER BY nc.nro DESC");
            List<ResumenDiario> listado = q.list();
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
    public List BuscarResumenDiario_SegunFecha(Date d, Date h) {
        try {
            sesion = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            tx = sesion.beginTransaction();
            Query q = sesion.createQuery("FROM ResumenDiario n WHERE fecha between :desde and :hasta ORDER BY n.nro DESC");
            q.setParameter("desde", d);
            q.setParameter("hasta", h);
            List<ResumenDiario> listado = q.list();
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
