/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.DocumentoPago;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class MigrarDAO implements MigrarDAOIFace {

    Session session = null;
    Transaction transaction = null;
    private static MigrarDAO INSTANCE = new MigrarDAO();

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (MigrarDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MigrarDAO();
                }
            }
        }
    }

    public static MigrarDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List ObtenerDatosBaseAnterior() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AS_MES_CO,AS_ANO_CO,MFDOCLUB,AFDOCLUB,MFDO2007,AFDO2007,AS_DNI,AS_FECAFI,ID FROM COLEGIADOS_EXPORT ORDER BY ID");
            //SQLQuery q = session.createSQLQuery("SELECT * FROM [COLEGIADOS_EXPORT]");
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
    public List ObtenerDatosBaseAnteriorSociedades() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT AS_MES_CO,AS_ANO_CO,AS_CIUDAD,AS_MATRIC,AS_FECAFI,ID FROM COLEGIADOS_EXPORT ORDER BY ID");
            //SQLQuery q = session.createSQLQuery("SELECT * FROM [COLEGIADOS_EXPORT]");
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
    public List ObtenerClientes() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Cliente> resultado = null;
            Query q = null;
            q = session.createQuery("FROM Cliente");
            resultado = (List<Cliente>) q.list();
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
    public List ObtenerCabeceraBaseAnterior() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT CA_TIPDOC,CA_SERDOC,CA_NUMDOC,CA_FECEMI,CA_TIPCLI,CA_CODCLI,CA_NOMCLI,CA_IGVVTA,ID FROM CCMOVCAB_EXPORT ORDER BY ID");
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
    public List ObtenerDetalleBaseAnterior() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("SELECT DE_TIPDOC,DE_SERDOC,DE_NUMDOC,DE_FECEMI,DE_TIPEVE,DE_DESCRI,DE_CANTID,DE_VALVTA,ID,DE_IGVITE FROM CCMOVDET_EXPORT ORDER BY ID");
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
    public DocumentoPago ObtenerCabecera(int idTipoDocSerie, int nroDoc, Date fecha) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            DocumentoPago resultado = null;
            Query q = null;
            SimpleDateFormat fAnio = new SimpleDateFormat("yyyy");
            SimpleDateFormat fmes = new SimpleDateFormat("MM");
            SimpleDateFormat fdia = new SimpleDateFormat("dd");
            int anio = Integer.valueOf(fAnio.format(fecha));
            int mes = Integer.valueOf(fmes.format(fecha));
            int dia = Integer.valueOf(fdia.format(fecha));
            q = session.createQuery("FROM DocumentoPago d WHERE d.tipoDocSerie.id = " + idTipoDocSerie + " AND d.nroDocumentoPago = " + nroDoc + " AND YEAR(d.fechaPago) = " + anio + " AND MONTH(d.fechaPago) = " + mes + " AND DAY(d.fechaPago) = " + dia);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                resultado = (DocumentoPago) q.uniqueResult();
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
    public void InsertarFecha(String fecha) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("INSERT INTO CierreDiario(Fecha,Estado) VALUES ('" + fecha + "','C')");
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
}
