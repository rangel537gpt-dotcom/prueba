/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.entidades.Cursos;
import caja.mapeo.entidades.Alumnos;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.mapeo.entidades.Evento;
import caja.mapeo.entidades.Inversion;
//import caja.mapeo.entidades.Cursos;
//import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan carlos
 */
public class AlumnoDAO implements AlumnoDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<Alumnos> resultadoBusqueda = null;

    private static AlumnoDAO INSTANCE = new AlumnoDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AlumnoDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AlumnoDAO();
                }
            }
        }
    }

    public static AlumnoDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public AlumnoDAO() {
    }

//    @Override
//    public void ActualizarAlumno(Alumno alumno) {
//        try {
//            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
//            transaction = session.beginTransaction();
//            session.update(alumno);
//            transaction.commit();
//        } catch (RuntimeException e) {
//            try {
//                transaction.rollback();
//            } catch (RuntimeException rbe) {
//                rbe.printStackTrace();
//            }
//            throw e;
//        }
//    }
    @Override
    public List ObtenerTodosTipoDocPago() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM TipoDocPago");
            resultadoBusqueda = (List<TipoDocPago>) q.list();
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
    public TipoDocSerie ObtenerTipoDocSerie(int idTipoDoc, int nroSerie) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            TipoDocSerie resultado = null;
            Query q = null;
            q = session.createQuery("FROM TipoDocSerie tds WHERE tds.serie.serie = " + nroSerie + " AND tds.tipoDocPago.idTipoDocPago = " + idTipoDoc);
            q.setMaxResults(1);
            resultado = (TipoDocSerie) q.uniqueResult();
            Hibernate.initialize(resultado.getTipoDocPago());
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
    public List ObtenerTodosClientes() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Cliente> resultado = null;
            Query q = null;
            q = session.createQuery("FROM Cliente");
            resultado = (List<Cliente>) q.list();
            /*for (Cliente c : resultado) {
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
    public List ObtenerTodosConceptoPagoCursos() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda = null;
            Query q = null;
            q = session.createQuery("FROM ConceptoPagoDetalle");
            resultadoBusqueda = (List<ConceptoPagoDetalle>) q.list();
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
    public List ObtenerConceptoCurso(int idTipoDocPago, String serie, int nroDocumentoPago) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT ConceptoPagoDetalle.Descripcion, ConceptoPagoDetalle.TipoCodigo, ConceptoPagoDetalle.Codigo \n"
                    + "FROM TipoDocSerie,Serie,DocumentoPago,DocumentoPagoDet,ConceptoPagoDetalle\n"
                    + "WHERE\n"
                    + "TipoDocSerie.IdTipoDoc = " + idTipoDocPago + " AND\n"
                    + "TipoDocSerie.IdSerie = Serie.IdSerie AND\n"
                    + "Serie.Serie = '" + serie + "' AND\n"
                    + "TipoDocSerie.Id = DocumentoPago.IdTipoPagoSerie AND\n"
                    + "DocumentoPago.IdDocumentoPago = DocumentoPagoDet.IdDocumentoPago AND\n"
                    + "ConceptoPagoDetalle.IdConceptoPagoDetalle = DocumentoPagoDet.IdConceptoPagoDetalle AND\n"
                    + "DocumentoPago.NroDocumentoPago = " + nroDocumentoPago + " AND\n"
                    + "ConceptoPagoDetalle.TipoCodigo = '04'";
//            String sql = "SELECT ConceptoPagoDetalle.Descripcion, ConceptoPagoDetalle.TipoCodigo, ConceptoPagoDetalle.Codigo FROM TipoDocPago \n"
//                    + "INNER JOIN TipoDocSerie ON TipoDocPago.IdTipoDocPago = TipoDocSerie.IdTipoDoc\n"
//                    + "INNER JOIN Serie ON TipoDocSerie.IdSerie = Serie.IdSerie\n"
//                    + "INNER JOIN DocumentoPago ON TipoDocSerie.IdSerie = DocumentoPago.NroSerie\n"
//                    + "INNER JOIN DocumentoPagoDet ON DocumentoPago.IdDocumentoPago = DocumentoPagoDet.IdDocumentoPago\n"
//                    + "INNER JOIN ConceptoPagoDetalle ON DocumentoPagoDet.IdConceptoPagoDetalle = ConceptoPagoDetalle.IdConceptoPagoDetalle\n"
//                    + "WHERE ConceptoPagoDetalle.TipoCodigo = '04' AND TipoDocPago.IdTipoDocPago = " + idTipoDocPago + " and Serie.serie = '" + serie + "' AND DocumentoPago.NroDocumentoPago = " + nroDocumentoPago;
            Query q = session.createSQLQuery(sql);
            List resultado = (List<Cursos>) q.list();
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
    public List ObtenerTodosAlumnosPorCurso(String codigo) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Object> resultado = null;
            Query q = null;
            q = session.createSQLQuery("SELECT alu.idAlumno, alu.idCliente, alu.idDocumentoPagoDet, \n"
                    + "alu.dni, alu.codigo, alu.apellidoPaterno, alu.apellidoMaterno, alu.nombres, \n"
                    + "alu.conceptoCurso, alu.codigoCurso, alu.monto, alu.tipoPago FROM Alumnos \n"
                    + "WHERE alu.codigoCurso = " + codigo);

            /*
             SELECT alu.dni, alu.codigo, alu.apellidoPaterno, \n" 
             + "alu.apellidoMaterno, alu.nombres, alu.conceptoCursos, alu.codigoCurso, alu.monto FROM Alumnos \n"
             + "WHERE alu.codigoCurso = "+ codigo
             */
 /*"SELECT distinct(cli.pnroDocumento), cli.papePat, cli.papeMat, cli.pnombre  FROM Cliente cli WHERE cli.idCliente =: cli.documentoPago.idCliente, \n" +
             "cli.documentoPago.idDocumentoPago =: cli.documentoPago.documentoPagoDet.idDocumentoPago, \n" +
             "cli.documentoPago.documentoPagoDet.idConceptoPagoDetalle =: cli.documentoPago.documentoPagoDet.conceptoPagoDetalle.idConceptoPagoDetalle, \n" +
             "cli.documentoPago.documentoPagoDet.conceptoPagoDetalle.tipoCodigo" + "cli.documentoPago.documentoPagoDet.conceptoPagoDetalle.codigo=" + codigo + "\n" +
             "AND cli.papePat is not null AND cli.papeMat is not null AND cli.pnombre is not null AND ORDER BY cli.papePat"*/
            //Query query = session.createQuery(q);
            resultado = (List<Object>) q.list();
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
    public Cliente ObtenerPersonaPorDNI_Colegiatura(int tipoCliente, String dni, String codigoColegiatura) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            //String codigo = "";
            //List<Object> resultado = null;
            Query q = null;
            String query = "";
            if (tipoCliente == 1) {
                query = ("FROM Cliente cli WHERE cli.pnroDocumento like '" + dni + "'");
            } else {
                query = ("FROM Cliente cli WHERE cli.ccodigoCole like '" + codigoColegiatura + "'");
            }

            q = session.createQuery(query);
            q.setMaxResults(1);
            Cliente cliente = (Cliente) q.uniqueResult();
            //resultado = (List<Object>) q.list();
            transaction.commit();
            return cliente;
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
    public String ObtenerContadorPorCodigoColegiatura(String codCole) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String codigo = "";
            Query q = null;
            q = session.createQuery("SELECT cli.pnroDocumento, cli.papePat, cli.papeMat, cli.nombre FROM Cliente cli WHERE cli.ccodigoCole = " + codCole);
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                codigo = (String) q.uniqueResult();
            }
            transaction.commit();
            return codigo;
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
    public void GuardarAlumnos(Alumnos alu) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(alu);
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
    public List ObtnerDocumentoPagoDetPorId(int idDocPagoDet) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String codigo = "";
            List<Object> resultado = null;
            Query q = null;
            String query = ("FROM DocumentoPagoDet dpd WHERE dpd.idDocumentoPagoDet = " + idDocPagoDet);

            q = session.createQuery(query);
            q.setMaxResults(1);
            resultado = (List<Object>) q.list();
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
    public int ObtenerIdDocumentoPagoDetalle(int nroDocumentoPago, String serie, String codigoCurso) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            //int resultado = null;
            int num = 0;
            Query q = null;
            q = session.createSQLQuery("SELECT DocumentoPagoDet.idDocumentoPagoDet FROM DocumentoPagoDet, DocumentoPago , TipoDocSerie, Serie, ConceptoPagoDetalle \n"
                    + "WHERE DocumentoPagoDet.idDocumentoPago = DocumentoPago.idDocumentoPago\n"
                    + "and DocumentoPagoDet.idConceptoPagoDetalle = ConceptoPagoDetalle.idConceptoPagoDetalle\n"
                    + "and TipoDocSerie.id = DocumentoPago.idTipoPagoSerie\n"
                    + "and TipoDocSerie.idSerie = Serie.idSerie\n"
                    + "and DocumentoPago.nroDocumentoPago = " + nroDocumentoPago + "\n"
                    + "and Serie.serie = '" + serie + "'\n"
                    + "and ConceptoPagoDetalle.tipoCodigo + ConceptoPagoDetalle.codigo = " + codigoCurso);

            //resultado = (List<DocumentoPagoDet>)q.list();
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result != null) {
                num = (int) q.uniqueResult();
            }

            transaction.commit();
            return num;

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
    public List ObtenerPersonaPorCodigoColegiatura(String codigo) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String codigoColegiatura = "";
            List<Object> resultado = null;
            Query q = null;
            String query = ("FROM Cliente cli WHERE cli.ccodigoCole = '" + codigo + "'");

            q = session.createQuery(query);
            q.setMaxResults(1);
            resultado = (List<Object>) q.list();
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
    public List ObtenerTodosEventos() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "FROM Evento e WHERE e.borrado = '1'";
            Query q = session.createQuery(query);
            List<Evento> resultado = (List<Evento>) q.list();
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
    public List ObtenerTodosEventos_Habilitados() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "FROM Evento e WHERE e.estado= 'H' AND e.borrado = '1' ORDER BY e.id desc";
            Query q = session.createQuery(query);
            List<Evento> resultado = (List<Evento>) q.list();
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
    public List ObtenerInversion_SegunEvento(int idEvento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "FROM Inversion i WHERE i.evento.id = :idEvento AND i.borrado = '1'";
            Query q = session.createQuery(query).setParameter("idEvento", idEvento);
            List resultado = q.list();
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
    public List ObtenerTodosTipoParticipantes_SegunEvento(int idEvento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "SELECT i.tipoParticipante FROM Inversion i WHERE i.evento.id = :idEvento AND i.borrado = '1'";
            Query q = session.createQuery(query).setParameter("idEvento", idEvento);
            List resultado = q.list();
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
    public List ObtenerTodasModalidades_SegunEvento(int idEvento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "SELECT i.modalidadPago FROM Inversion i WHERE i.evento.id = :idEvento AND i.borrado = '1'";
            Query q = session.createQuery(query).setParameter("idEvento", idEvento);
            List resultado = q.list();
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
    public List ObtenerTodasInversiones_SegunEvento(int idEvento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "FROM Inversion i WHERE i.evento.id = :idEvento AND i.borrado = '1'";
            Query q = session.createQuery(query).setParameter("idEvento", idEvento);
            List<Inversion> resultado = q.list();
            for (Inversion i : resultado) {
                Hibernate.initialize(i.getTipoParticipante());
                Hibernate.initialize(i.getModalidadPago());
            }
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
    public List ObtenerParticipantes_SegunEvento(int idEvento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "SELECT DISTINCT\r\n" + "case\r\n" + "when cl.TipoCliente = 'P' then cl.PNroDocumento\r\n" + "else ''\r\n"
                    + "end as DNI,\r\n" + "case\r\n" + "when cl.TipoCliente = 'C' then cl.CCodigoCole\r\n" + "else ''\r\n"
                    + "end as CODIGO,\r\n" + "cl.PApePat + ' ' + cl.PApeMat as apellidos,\r\n" + "cl.pnombre,\r\n"
                    + "e.nombre as evento,\r\n" + "tp.nombre as tipoParticipante,cl.tipoCliente\r\n"
                    + "FROM cursos.participante p,cursos.asignacion_evento ae,cursos.evento e,Cliente cl,cursos.inversion i,cursos.tipo_participante tp\r\n"
                    + "WHERE\r\n" + "p.id_asignacion_evento = ae.id and\r\n" + "ae.id_evento = e.id and\r\n"
                    + "p.id_cliente = cl.IdCliente and\r\n" + "p.id_inversion = i.id and\r\n"
                    + "p.borrado = '1' and i.id_tipo_participante = tp.id and e.id = :idEvento ORDER BY cl.PApePat + ' ' + cl.PApeMat,cl.pnombre";
            Query q = session.createSQLQuery(query).setParameter("idEvento", idEvento);
            List resultado = q.list();
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
    public List ObtenerCorreoParticipantes_SegunIdDocumentoPago(int idParticipante) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = "SELECT DISTINCT\n"
                    + "case\n"
                    + "when c.TipoCliente = 'P' then c.PEmail\n"
                    + "when c.TipoCliente = 'C' then c.CEmail\n"
                    + "when c.TipoCliente = 'S' then c.SEmail\n"
                    + "when c.TipoCliente = 'E' then c.EEmail\n"
                    + "end as email,\n"
                    + "c.CPasswd,\n"
                    + "e.nombre,\n"
                    + "pe.fecha_inicio\n"
                    + "FROM cursos.participante p,dbo.Cliente c,cursos.evento e,cursos.periodo pe\n"
                    + "WHERE p.id_cliente = c.IdCliente and\n"
                    + "p.id_evento = e.id and\n"
                    + "pe.id_evento = e.id and\n"
                    + "e.borrado = 1 and\n"
                    + "pe.borrado = 1 and\n"
                    + "p.borrado = 1 and\n"
                    + "p.id_documento_pago_det = :idParticipante";
            Query q = session.createSQLQuery(query).setParameter("idParticipante", idParticipante);
            List resultado = q.list();
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
    public List ObtenerParticipantes_SegunEventoFechaComprobante(int idEvento, String desde, String hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            String query = "SELECT DISTINCT\n"
//                    + "case\n"
//                    + "when cl.TipoCliente = 'P' then cl.PNroDocumento\n"
//                    + "else ''\n"
//                    + "end as DNI,\n"
//                    + "case\n"
//                    + "when cl.TipoCliente = 'C' then cl.CCodigoCole\n"
//                    + "else ''\n"
//                    + "end as CODIGO,\n"
//                    + "cl.PApePat + ' ' + cl.PApeMat as apellidos,\n"
//                    + "cl.pnombre,\n"
//                    + "e.nombre as evento,\n"
//                    + "tp.nombre as tipoParticipante,cl.tipoCliente,\n"
//                    + "dp.NroSerie + '-'  + RIGHT('0000000' + Ltrim(Rtrim(dp.NroDocumentoPago)),7) as nro,\n"
//                    + "dp.FechaPago,\n"
//                    + "dp.tipoPago,\n"
//                    + "((dpd.igv + dpd.valorVenta) / dpd.cantidad) as monto\n"
//                    + "FROM cursos.participante p,cursos.asignacion_evento ae,cursos.evento e,Cliente cl,cursos.inversion i,cursos.tipo_participante tp,dbo.DocumentoPagoDet dpd,dbo.DocumentoPago dp\n"
//                    + "WHERE\n"
//                    + "p.id_asignacion_evento = ae.id and\n"
//                    + "ae.id_evento = e.id and\n"
//                    + "p.id_cliente = cl.IdCliente and\n"
//                    + "p.id_inversion = i.id and\n"
//                    + "i.id_tipo_participante = tp.id and e.id = :idEvento and\n"
//                    + "p.id_documento_pago_det = dpd.IdDocumentoPagoDet and\n"
//                    + "dpd.IdDocumentoPago = dp.IdDocumentoPago and\n"
//                    + "e.borrado = 1 and\n"
//                    + "p.borrado = 1 and\n"
//                    + "ae.borrado = 1 and\n"
//                    + "dp.FechaPago >= :desde and\n"
//                    + "dp.FechaPago <= :hasta\n"
//                    + "ORDER BY cl.PApePat + ' ' + cl.PApeMat,cl.pnombre";
            String query = "select distinct\n"
                    + "case\n"
                    + "when cl.TipoCliente = 'P' then cl.PNroDocumento\n"
                    + "else ''\n"
                    + "end as dni,\n"
                    + "case\n"
                    + "when cl.TipoCliente = 'C' then cl.CCodigoCole\n"
                    + "else ''\n"
                    + "end as codigo,\n"
                    + "cl.PApePat + ' ' + cl.PApeMat  as apellidos,\n"
                    + "cl.PNombre as nombre,\n"
                    + "e.codigo + ' - ' + e.nombre as nombreEvento,\n"
                    + "tp.nombre as tipoParticipante,cl.tipoCliente,\n"
                    + "dp.NroSerie + '-'  + RIGHT('0000000' + Ltrim(Rtrim(dp.NroDocumentoPago)),7) as nro,\n"
                    + "dp.FechaPago,\n"
                    + "dp.tipoPago,\n"
                    + "((dpd.igv + dpd.valorVenta) / NULLIF(dpd.cantidad, 0)) as monto,\n"
                    + "'Comprobante' as tipo\n"
                    + "from cursos.participante p,dbo.DocumentoPagoDet dpd,dbo.DocumentoPago dp,dbo.Cliente cl,dbo.TipoDocSerie tds,TipoDocPago td,cursos.evento e,cursos.inversion inv,cursos.tipo_participante tp\n"
                    + "where p.id_documento_pago_det = dpd.IdDocumentoPagoDet and\n"
                    + "dpd.IdDocumentoPago = dp.IdDocumentoPago and\n"
                    + "p.id_cliente = cl.IdCliente and\n"
                    + "dp.IdTipoPagoSerie = tds.Id and\n"
                    + "tds.IdTipoDoc = td.IdTipoDocPago and\n"
                    + "p.id_evento = e.id and\n"
                    + "e.id = :idEvento and\n"
                    + "dp.fechaPago >= :desde and\n"
                    + "dp.fechaPago <= :hasta and\n"
                    + "e.borrado = '1' and\n"
                    + "p.borrado = '1' and\n"
                    + "p.id_inversion = inv.id and\n"
                    + "inv.id_tipo_participante = tp.id\n"
                    + "\n"
                    + "union all\n"
                    + "\n"
                    + "select '' as dni,c.CCodigoCole as codigo,c.PApePat + ' ' + c.PApeMat  as apellidos, c.PNombre as nombre,e.codigo + ' - ' + e.nombre as nombreEvento,'' as tipoParticipante,c.tipoCliente,'' as nro,''  as FechaPago, '' as tipoPago,0 as monto,'Vale' as tipo\n"
                    + "from dbo.vale_academico va,dbo.vale_academico_consumo vac,cursos.inversion i,dbo.DocumentoPagoDet dpd,dbo.DocumentoPago dp,dbo.Cliente c,cursos.evento e\n"
                    + "where dp.IdCliente = c.IdCliente and\n"
                    + "dp.IdDocumentoPago = dpd.IdDocumentoPago and\n"
                    + "va.id_documento_pago_detalle = dpd.IdDocumentoPagoDet and\n"
                    + "va.id = vac.id_vale_academico and\n"
                    + "vac.id_inversion = i.id and\n"
                    + "i.id_evento = e.id and\n"
                    + "vac.borrado = '1' and\n"
                    + "va.borrado = '1' and\n"
                    + "dp.IdContadorEmpresa is null and\n"
                    + "i.id_evento = :idEvento\n"
                    + "\n"
                    + "union all\n"
                    + "\n"
                    + "select '' as dni,c.CCodigoCole as codigo,c.PApePat + ' ' + c.PApeMat as apellidos,PNombre as nombre,e.codigo + ' - ' + e.nombre as nombreEvento,'' as tipoParticipante,c.tipoCliente,'' as nro,''  as FechaPago, '' as tipoPago,0 as monto,'Vale' as tipo\n"
                    + "from dbo.vale_academico va,dbo.vale_academico_consumo vac,cursos.inversion i,dbo.DocumentoPagoDet dpd,dbo.DocumentoPago dp,dbo.Cliente c,cursos.evento e\n"
                    + "where dp.IdContadorEmpresa = c.IdCliente and\n"
                    + "dp.IdDocumentoPago = dpd.IdDocumentoPago and\n"
                    + "va.id_documento_pago_detalle = dpd.IdDocumentoPagoDet and\n"
                    + "va.id = vac.id_vale_academico and\n"
                    + "vac.id_inversion = i.id and\n"
                    + "i.id_evento = e.id and\n"
                    + "vac.borrado = '1' and\n"
                    + "va.borrado = '1' and\n"
                    + "dp.IdContadorEmpresa is not null and\n"
                    + "i.id_evento = :idEvento\n"
                    + "\n"
                    + "order by cl.PApePat + ' ' + cl.PApeMat, cl.PNombre;";
            Query q = session.createSQLQuery(query).setParameter("idEvento", idEvento).setParameter("desde", desde).setParameter("hasta", hasta);
            List resultado = q.list();
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
    public List ObtenerAlumnosExcel(String codigoCurso, String desde, String hasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "select Alumnos.DNI, Alumnos.Codigo,\n"
                    + "Alumnos.ApellidoPaterno + ' ' + Alumnos.ApellidoMaterno as 'apellidos',Alumnos.Nombres as 'Nombres',\n"
                    + "Alumnos.ConceptoCurso, Alumnos.CodigoCurso,\n"
                    + "\n"
                    + "CASE \n"
                    + "WHEN Alumnos.TipoPago = 'CON'\n"
                    + "	THEN 'CONTADO'\n"
                    + "	ELSE 'CREDITO' END \n"
                    + "	AS 'CONTADO/CREDITO',\n"
                    + "CASE \n"
                    + "WHEN Cliente.TipoCliente = 'S' THEN Cliente.SNombreSociedad\n"
                    + "WHEN Cliente.TipoCliente = 'E' THEN Cliente.ENombre\n"
                    + "END AS 'EMPRESA',Cliente.TipoCliente\n"
                    + "from Alumnos, DocumentoPago, Cliente, DocumentoPagoDet\n"
                    + "where Alumnos.IdCliente = Cliente.IdCliente\n"
                    + "and Alumnos.IdDocumentoPagoDet = DocumentoPagoDet.IdDocumentoPagoDet\n"
                    + "and DocumentoPagoDet.IdDocumentoPago = DocumentoPago.IdDocumentoPago\n"
                    + "and Alumnos.CodigoCurso = '" + codigoCurso + "'\n"
                    + "and DocumentoPago.FechaPago between '" + desde + "' and '" + hasta + "' \n"
                    + "and ISNULL( (SELECT CASE WHEN Nota.IdTipoNota = 1 THEN 1 WHEN Nota.IdTipoNota = 6 THEN 1 ELSE 0 END FROM Nota WHERE Nota.IdDocumentoPago = DocumentoPago.IdDocumentoPago) , 0) = 0";
            List<Object> resultado = null;
            SQLQuery q = session.createSQLQuery(sql);
            resultado = q.list();
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
    public void EliminarAlumno(Alumnos alu) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(alu);
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
    public List BuscarAlumnoPorCodigoCurso(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Alumnos WHERE codigoCurso LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Alumnos>) q.list();
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
    public List BuscarAlumnoPorNombreCurso(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Alumnos WHERE conceptoCurso LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Alumnos>) q.list();
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
    public List BuscarAlumnoPorNroDocumento(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Alumnos WHERE dni LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Alumnos>) q.list();
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
    public List BuscarAlumnoPorIdComprobantePago(int idDocumentoPago) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Alumnos a WHERE a.documentoPagoDet.documentoPago.idDocumentoPago = " + idDocumentoPago);
            resultadoBusqueda = (List<Alumnos>) q.list();
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
    public List BuscarAlumnoPorCodigoColegiatura(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Alumnos WHERE codigo LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Alumnos>) q.list();
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
    public List BuscarAlumnoPorApellidoPaterno(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Alumnos WHERE apellidoPaterno LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Alumnos>) q.list();
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
    public List BuscarAlumnoPorApellidoMaterno(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Alumnos WHERE apellidoMaterno LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Alumnos>) q.list();
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
}

/*"SELECT ConceptoPagoDetalle.Descripcion, ConceptoPagoDetalle.TipoCodigo, ConceptoPagoDetalle.Codigo FROM TipoDocPago \n"
 + "INNER JOIN TipoDocSerie ON TipoDocPago.IdTipoDocPago = TipoDocSerie.IdTipoDoc\n"
 + "INNER JOIN Serie ON TipoDocSerie.IdSerie = Serie.IdSerie\n"
 + "INNER JOIN DocumentoPago ON TipoDocSerie.IdSerie = DocumentoPago.NroSerie\n"
 + "INNER JOIN DocumentoPagoDet ON DocumentoPago.IdDocumentoPago = DocumentoPagoDet.IdDocumentoPago\n"
 + "INNER JOIN ConceptoPagoDetalle ON DocumentoPagoDet.IdConceptoPagoDetalle = ConceptoPagoDetalle.IdConceptoPagoDetalle\n"
 + "WHERE ConceptoPagoDetalle.TipoCodigo = '04' AND TipoDocPago.IdTipoDocPago = " + 
 idTipoDocPago + " and Serie.IdSerie = " + serie + " AND DocumentoPago.NroDocumentoPago = " + nroDocumentoPago*/
