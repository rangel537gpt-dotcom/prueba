/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Bbva;
import caja.mapeo.entidades.BbvaContador;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.CuentaCorriente;
import caja.mapeo.entidades.BbvaContadorDetalle;
import caja.mapeo.entidades.BbvaRetorno;
import caja.mapeo.entidades.BbvaRetornoDetalle;
import caja.mapeo.entidades.BbvaRetornoDetalleDocumento;
import caja.mapeo.entidades.EstructuraPagoCajaArequipa;
import caja.mapeo.entidades.FichaDatos;
import caja.mapeo.entidades.FichaDeporte;
import caja.mapeo.entidades.Scotiabank;
import caja.mapeo.entidades.ScotiabankContador;
import caja.mapeo.entidades.ScotiabankContadorDetalle;
import caja.mapeo.entidades.ScotiabankRetorno;
import caja.mapeo.entidades.ScotiabankRetornoDetalle;
import caja.mapeo.entidades.ScotiabankRetornoDetalleDocumento;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
//import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import sun.security.jca.GetInstance;

/**
 *
 * @author juan carlos
 */
public class ClienteDAO implements ClienteDAOIFace {

    Session session = null;
    Transaction transaction = null;

    //List<Persona> resultadoBusqueda = null;
    //List<Contador> resultadoBusqueda1 = null;
    private static ClienteDAO INSTANCE = new ClienteDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ClienteDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClienteDAO();
                }
            }
        }
    }

    public static ClienteDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public ClienteDAO() {
    }

    @Override
    public void GuardarEstructuraCajaArequipa(EstructuraPagoCajaArequipa est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public List ObtenerBbvaOperaciones(int anio, int mes) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            Query q = session.createQuery("from Bbva where to_char(fechaOperacion,'YYYY/MM') = '" + fecha + "'");
            Query q = session.createQuery("from Bbva b where YEAR(b.fechaOperacion) = " + anio + " AND MONTH(b.fechaOperacion) = " + mes + " ORDER BY b.nroOperacion DESC");
            List l = q.list();
//            session.save(est);
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
    public List ObtenerBbvaTodasOperaciones() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            Query q = session.createQuery("from Bbva where to_char(fechaOperacion,'YYYY/MM') = '" + fecha + "'");
            Query q = session.createQuery("from Bbva b ORDER BY b.nroOperacion DESC");
            List l = q.list();
//            session.save(est);
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
    public List ObtenerScotiabankOperaciones(int anio, int mes) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            Query q = session.createQuery("from Bbva where to_char(fechaOperacion,'YYYY/MM') = '" + fecha + "'");
            Query q = session.createQuery("from Scotiabank b where YEAR(b.fechaOperacion) = " + anio + " AND MONTH(b.fechaOperacion) = " + mes);
            List l = q.list();
//            session.save(est);
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
    public List ObtenerScotiabankOperaciones() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            Query q = session.createQuery("from Bbva where to_char(fechaOperacion,'YYYY/MM') = '" + fecha + "'");
            Query q = session.createQuery("from Scotiabank b order by b.nroOperacion DESC");
            List l = q.list();
//            session.save(est);
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
    public int ObtenerCorrelativoScotiabank_SegunIdScotiabank(int id) {
        try {
            int correlativo = 0;
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT SCD.CORRELATIVO\n"
                    + "FROM SCOTIABANKCONTADORDETALLE SCD,SCOTIABANKCONTADOR SC,SCOTIABANK S\n"
                    + "WHERE \n"
                    + "S.ID = " + id + " AND\n"
                    + "S.ID = SC.IDSCOTIABANK AND\n"
                    + "SCD.IDSCOTIABANKCONTADOR = SC.ID\n"
                    + "ORDER BY SCD.CORRELATIVO DESC";
            Query q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                correlativo = (int) q.uniqueResult();
            }
//            List l = q.list();
            transaction.commit();
            return correlativo;
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
    public int ObtenerCorrelativoScotiabank_SegunIdScotiabank_AnioMes(int idSC, int idAnioMes) {
        try {
            int correlativo = 0;
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT SCD.CORRELATIVO\n"
                    + "FROM SCOTIABANKCONTADORDETALLE SCD\n"
                    + "WHERE SCD.IdAnioMesCuota = " + idAnioMes + " AND\n"
                    + "SCD.IDSCOTIABANKCONTADOR = " + idSC;
            Query q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                correlativo = (int) q.uniqueResult();
            }
//            List l = q.list();
            transaction.commit();
            return correlativo;
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
    public void GuardarBbvaContadorDetalle(BbvaContadorDetalle est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarScotiabankContadorDetalle(ScotiabankContadorDetalle est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarBbva(Bbva est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarObjeto(Object est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void ActualizarObjeto(Object est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(est);
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
    public void GuardarBbvaRetorno(BbvaRetorno est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarScotiabankRetorno(ScotiabankRetorno est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarBbvaRetornoDetalle(BbvaRetornoDetalle est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarScotiabankRetornoDetalle(ScotiabankRetornoDetalle est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarBbvaRetornoDetalleDocumento(BbvaRetornoDetalleDocumento est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarScotiabankRetornoDetalleDocumento(ScotiabankRetornoDetalleDocumento est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarScotiabank(Scotiabank est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void GuardarBbvaContador(BbvaContador est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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
    public void ActualizarBbvaRetorno(BbvaRetorno est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(est);
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
    public void ejecutarProcedimientoEstadoTemporal() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            Query q = session.getNamedProcedureCall("set_estado_temporal");
            Query q = session.createSQLQuery("exec cursos.set_estado_temporal");
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
    public void ActualizarScotiabankRetorno(ScotiabankRetorno est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(est);
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
    public void GuardarScotiabankContador(ScotiabankContador est) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(est);
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

    /*@Override
     public List BuscarContadorPorApePat(String busqueda) {
     try {
     session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
     transaction = session.beginTransaction();

     Query q = null;
     if (resultadoBusqueda != null) {
     resultadoBusqueda.clear();
     }

     q = session.createQuery("FROM Persona WHERE apePat LIKE '%" + busqueda + "%'");
     resultadoBusqueda = (List<Persona>) q.list();
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
     public List BuscarContadorPorApeMat(String busqueda) {
     try {
     session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
     transaction = session.beginTransaction();

     Query q = null;
     if (resultadoBusqueda != null) {
     resultadoBusqueda.clear();
     }

     q = session.createQuery("FROM Persona WHERE apeMat LIKE '%" + busqueda + "%'");
     resultadoBusqueda = (List<Persona>) q.list();
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
     public List BuscarContadorPorNombre(String busqueda) {
     try {
     session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
     transaction = session.beginTransaction();

     Query q = null;
     if (resultadoBusqueda != null) {
     resultadoBusqueda.clear();
     }

     q = session.createQuery("FROM Persona WHERE nombre LIKE '%" + busqueda + "%'");
     resultadoBusqueda = (List<Persona>) q.list();
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
     public List BuscarContadorPorDNI(String busqueda) {
     try {
     session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
     transaction = session.beginTransaction();

     Query q = null;
     if (resultadoBusqueda != null) {
     resultadoBusqueda.clear();
     }

     q = session.createQuery("FROM Persona WHERE dni LIKE '%" + busqueda + "%'");
     resultadoBusqueda = (List<Persona>) q.list();
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
     }*/
    @Override
    public List BuscarContadorPorCodigoCole(String busqueda) {
        /*try {
         session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
         transaction = session.beginTransaction();

         Query q = null;
         if (resultadoBusqueda1 != null) {
         resultadoBusqueda1.clear();
         }

         q = session.createQuery("FROM Contador WHERE codigoCole LIKE '%" + busqueda + "%'");
         resultadoBusqueda1 = (List<Contador>) q.list();
         transaction.commit();

         return resultadoBusqueda1;
         } catch (RuntimeException e) {
         try {
         transaction.rollback();
         } catch (RuntimeException rbe) {
         rbe.printStackTrace();
         }
         throw e;
         }*/
        return null;
    }

    /*@Override
     public void GuardarContador(Contador cont) {
     try {
     session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
     transaction = session.beginTransaction();

     session.save(cont);
     transaction.commit();
     } catch (RuntimeException e) {
     try {
     transaction.rollback();
     } catch (RuntimeException rbe) {
     rbe.printStackTrace();
     }
     throw e;
     }
     }*/
    @Override
    public void GuardarCliente(Cliente cliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(cliente);
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
    public List ObtenerTodosClientes() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Cliente> resultado = null;
//            q = session.createQuery("FROM Cliente c WHERE c.idCliente = 4619");
            Query q = session.createQuery("FROM Cliente c ORDER BY c.ccodigoCole");
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
    public int obtenerNroOperacion_CajaArequipa() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            List<Cliente> resultado = null;
//            q = session.createQuery("FROM Cliente c WHERE c.idCliente = 4619");
            int nro = 0;
            Query q = session.createSQLQuery("SELECT TOP 1 IDOPERACION FROM EstructuraPagoCajaArequipa ORDER BY IDOPERACION DESC");
//            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                nro = (int) q.uniqueResult();
            }
            transaction.commit();
            return nro;
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
    public int obtenerNroOperacion_BBVA() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            List<Cliente> resultado = null;
//            q = session.createQuery("FROM Cliente c WHERE c.idCliente = 4619");
            int nro = 0;
            Query q = session.createSQLQuery("SELECT TOP 1 NROOPERACION FROM Bbva ORDER BY NROOPERACION DESC");
//            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                nro = (int) q.uniqueResult();
            }
            transaction.commit();
            return nro;
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
    public void actualizarCorrelativoScotiabank(int nroOperacion) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            List<Cliente> resultado = null;
//            q = session.createQuery("FROM Cliente c WHERE c.idCliente = 4619");
//            int nro = 0;
            String sql = "EXEC ColocarCorrelativoScotiabank " + nroOperacion;
            Query q = session.createSQLQuery(sql);
            q.executeUpdate();
//            q.setMaxResults(1);
//            if (q.uniqueResult() != null) {
//                nro = (int) q.uniqueResult();
//            }
            transaction.commit();
//            return nro;
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
    public int obtenerNroOperacion_SCOTIABANK() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            int nro = 0;
            Query q = session.createSQLQuery("SELECT TOP 1 NROOPERACION FROM Scotiabank ORDER BY NROOPERACION DESC");
            if (q.uniqueResult() != null) {
                nro = (int) q.uniqueResult();
            }
            transaction.commit();
            return nro;
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
    public int obtenerVersion_BBVA(String fecha) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            int nro = 0;
            Query q = session.createSQLQuery("SELECT TOP 1 VERSION FROM Bbva WHERE FECHAOPERACION = '" + fecha + "' ORDER BY VERSION DESC");
            if (q.uniqueResult() != null) {
                nro = (int) q.uniqueResult();
            }
            transaction.commit();
            return nro;
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
    public List ObtenerListado_CajaArequipa(int nroOperacion) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            List<Cliente> resultado = null;
//            q = session.createQuery("FROM Cliente c WHERE c.idCliente = 4619");
            int nro = 0;
            String sql = "SELECT C.CCodigoCole,C.PApePat,C.PApeMat,C.PNombre,E.TipoConceptoAgrupar,AM.Anio,AM.MES,SUM(E.Monto)\n"
                    + "FROM EstructuraPagoCajaArequipa E,Cliente C,AnioMes AM\n"
                    + "WHERE E.IdCliente = C.IdCliente AND\n"
                    + "E.IdAnioMesCuota = AM.Id AND\n"
                    + "E.IdOperacion = " + nroOperacion + "\n"
                    + "GROUP BY C.CCodigoCole,C.PApePat,C.PApeMat,C.PNombre,E.TipoConceptoAgrupar,AM.Anio,AM.MES\n"
                    + "ORDER BY CCodigoCole,TipoConceptoAgrupar,AM.Anio,AM.MES";
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
    public List ObtenerListado_BBVA(int nroOperacion) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            List<Cliente> resultado = null;
//            q = session.createQuery("FROM Cliente c WHERE c.idCliente = 4619");
//            int nro = 0;
            String sql = "SELECT C.CCodigoCole,ISNULL(C.PApePat,'') + ' ' + ISNULL(C.PApeMat,'') + ' ' + ISNULL(C.PNombre,'') AS NOMBRE,BCD.TipoConceptoAgrupar,AM.Anio,AM.Mes,SUM(BCD.MONTO)\n"
                    + "FROM CLIENTE C,BBVA B,BBVACONTADOR BC,BBVACONTADORDETALLE BCD,ANIOMES AM\n"
                    + "WHERE C.IdCliente = BC.IDCLIENTE AND\n"
                    + "BC.ID = BCD.IdBbvaContador AND\n"
                    + "BC.IDBBVA = B.ID AND\n"
                    + "BCD.IDANIOMESCUOTA = AM.ID AND\n"
                    + "B.NROOPERACION = " + nroOperacion + "\n"
                    + "GROUP BY C.CCodigoCole,C.PApePat,C.PApeMat,C.PNombre,BCD.TipoConceptoAgrupar,AM.Anio,AM.Mes\n"
                    + "ORDER BY C.CCodigoCole,BCD.TipoConceptoAgrupar,AM.Anio,AM.Mes";
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
    public List ObtenerListado_Scotiabank(int nroOperacion) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT C.CCodigoCole,ISNULL(C.PApePat,'') + ' ' + ISNULL(C.PApeMat,'') + ' ' + ISNULL(C.PNombre,'') AS NOMBRE,SCD.TipoConceptoAgrupar,AM.Anio,AM.Mes,SUM(SCD.MONTO),SCD.CORRELATIVO\n"
                    + "FROM CLIENTE C,SCOTIABANK S,SCOTIABANKCONTADOR SC,SCOTIABANKCONTADORDETALLE SCD,ANIOMES AM\n"
                    + "WHERE C.IdCliente = SC.IDCLIENTE AND\n"
                    + "SC.ID = SCD.IdSCOTIABANKContador AND\n"
                    + "SC.IDSCOTIABANK = S.ID AND\n"
                    + "SCD.IDANIOMESCUOTA = AM.ID AND\n"
                    + "S.NROOPERACION = " + nroOperacion + "\n"
                    + "GROUP BY C.CCodigoCole,C.PApePat,C.PApeMat,C.PNombre,SCD.TipoConceptoAgrupar,AM.Anio,AM.Mes,SCD.CORRELATIVO\n"
                    + "ORDER BY C.CCodigoCole,SCD.TipoConceptoAgrupar,AM.Anio,AM.Mes";
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
    public double obtenerMontoTotal_SegunNroOperacionScotiabank(int nroOperacion) {
        try {
            double monto = 0.0;
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT SUM(SCD.MONTO)\n"
                    + "FROM SCOTIABANK S,SCOTIABANKCONTADOR SC,SCOTIABANKCONTADORDETALLE SCD\n"
                    + "WHERE SCD.IDSCOTIABANKCONTADOR = SC.ID AND\n"
                    + "SC.IDSCOTIABANK = S.ID AND\n"
                    + "S.NROOPERACION = " + nroOperacion;
            Query q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                monto = (double) q.uniqueResult();
            }
//            List listado = q.list();
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
    public List ObtenerHistorialPagos(int idContador) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT dp.fechaPago,cp.descripcion,dpd.cantidad,dpd.precioUnitario,dpd.observacion,TipoDocPago.nombreDocPago,dp.nroSerie,\n"
                    + "dp.nroDocumentoPago,dp.idDocumentoPago,dp.tieneIgv,TipoDocPago.idTipoDocPago,\n"
                    + "dp.estado,dpd.valorVenta+dpd.igv,dp.moneda,(SELECT CLI.PApeMat + ' ' + CLI.PApeMat + ' ' + CLI.PNombre FROM Cobrador COB,Cliente CLI WHERE COB.IdCobrador = DP.IdCobrador AND COB.IdCliente = CLI.IdCliente) AS COB\n"
                    + "FROM DocumentoPagoDet dpd,DocumentoPago dp,ConceptoPagoDetalle cp,TipoDocPago,TipoDocSerie\n"
                    + "WHERE \n"
                    + "dpd.IdConceptoPagoDetalle = cp.IdConceptoPagoDetalle AND\n"
                    + "dp.estado <> 'ANULADO' AND\n"
                    + "dp.IdCliente = " + idContador + " AND\n"
                    + "dp.IdTipoPagoSerie = TipoDocSerie.Id AND\n"
                    + "TipoDocSerie.IdTipoDoc = TipoDocPago.IdTipoDocPago AND\n"
                    + "dp.IdDocumentoPago = dpd.IdDocumentoPago\n"
                    + "\n"
                    + "UNION ALL\n"
                    + "\n"
                    + "SELECT dp.fechaPago,cp.descripcion,dpd.cantidad,dpd.precioUnitario,dpd.observacion,TipoDocPago.nombreDocPago,dp.nroSerie,\n"
                    + "dp.nroDocumentoPago,dp.idDocumentoPago,dp.tieneIgv,TipoDocPago.idTipoDocPago,\n"
                    + "dp.estado,dpd.valorVenta+dpd.igv,dp.moneda,(SELECT CLI.PApeMat + ' ' + CLI.PApeMat + ' ' + CLI.PNombre FROM Cobrador COB,Cliente CLI WHERE COB.IdCobrador = DP.IdCobrador AND COB.IdCliente = CLI.IdCliente) AS COB\n"
                    + "FROM DocumentoPagoDet dpd,DocumentoPago dp,ConceptoPagoDetalle cp,TipoDocPago,TipoDocSerie\n"
                    + "WHERE \n"
                    + "dpd.IdConceptoPagoDetalle = cp.IdConceptoPagoDetalle AND\n"
                    + "dp.estado <> 'ANULADO' AND\n"
                    + "dp.IdContadorEmpresa = " + idContador + " AND\n"
                    + "dp.IdTipoPagoSerie = TipoDocSerie.Id AND\n"
                    + "TipoDocSerie.IdTipoDoc = TipoDocPago.IdTipoDocPago AND\n"
                    + "dp.IdDocumentoPago = dpd.IdDocumentoPago\n"
                    + "ORDER BY dp.fechaPago DESC";
            SQLQuery q = session.createSQLQuery(sql);
            List<Object> resultado = q.list();
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
    public List ObtenerCursos(Integer idColegiado) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT  distinct DocumentoPago.IdDocumentoPago,e.codigo,e.nombre,DocumentoPago.FechaPago,TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,DocumentoPagoDet.ValorVenta + DocumentoPagoDet.Igv  as monto\n"
                    + "FROM cursos.participante p,cursos.evento e,DocumentoPagoDet,DocumentoPago,TipoDocSerie,TipoDocPago \n"
                    + "WHERE \n"
                    + "DocumentoPagoDet.IdDocumentoPago = DocumentoPago.IdDocumentoPago AND\n"
                    + "DocumentoPago.IdTipoPagoSerie = TipoDocSerie.Id AND\n"
                    + "TipoDocSerie.IdTipoDoc = TipoDocPago.IdTipoDocPago AND\n"
                    + "p.id_documento_pago_det = DocumentoPagoDet.IdDocumentoPagoDet AND\n"
                    + "p.id_evento = e.id and\n"
                    + "p.id_cliente = " + idColegiado + " and\n"
                    + "p.borrado = '1' and\n"
                    + "e.borrado = '1'\n"
                    + "ORDER BY DocumentoPago.FechaPago DESC";

//            String sql = "SELECT DocumentoPago.IdDocumentoPago,CODIGOCURSO,ConceptoCurso,DocumentoPago.FechaPago,TipoDocPago.NombreDocPago,DocumentoPago.NroSerie,DocumentoPago.NroDocumentoPago,MONTO  \n"
//                    + "FROM ALUMNOS,DocumentoPagoDet,DocumentoPago,TipoDocSerie,TipoDocPago \n"
//                    + "WHERE ALUMNOS.IdDocumentoPagoDet = DocumentoPagoDet.IdDocumentoPagoDet AND\n"
//                    + "DocumentoPagoDet.IdDocumentoPago = DocumentoPago.IdDocumentoPago AND\n"
//                    + "DocumentoPago.IdTipoPagoSerie = TipoDocSerie.Id AND\n"
//                    + "TipoDocSerie.IdTipoDoc = TipoDocPago.IdTipoDocPago AND\n"
//                    + "CODIGO = '" + codColegiado + "'\n"
//                    + "ORDER BY DocumentoPago.FechaPago DESC;";
            SQLQuery q = session.createSQLQuery(sql);
            List<Object> resultado = q.list();
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
    public List ObtenerTodosContadores() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Cliente> resultado = null;
            Query q = null;
            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'C' ORDER BY c.papePat,c.papeMat,c.pnombre DESC");
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
    public Bbva ObtenerBbva(int nroOp) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            List<Cliente> resultado = null;
            Query q = session.createQuery("FROM Bbva c WHERE c.nroOperacion = " + nroOp);
            q.setMaxResults(1);
            Bbva b = null;
            if (q.uniqueResult() != null) {
                b = (Bbva) q.uniqueResult();
            }
//            resultado = (List<Cliente>) q.list();
            transaction.commit();
            return b;
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
    public Bbva ObtenerUltimoBbvaGenerado() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Bbva c ORDER BY c.nroOperacion DESC");
            q.setMaxResults(1);
            Bbva b = null;
            if (q.uniqueResult() != null) {
                b = (Bbva) q.uniqueResult();
            }
            transaction.commit();
            return b;
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
    public Scotiabank ObtenerUltimoScotiabankGenerado() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Scotiabank c ORDER BY c.nroOperacion DESC");
            q.setMaxResults(1);
            Scotiabank b = null;
            if (q.uniqueResult() != null) {
                b = (Scotiabank) q.uniqueResult();
            }
            transaction.commit();
            return b;
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
    public Scotiabank ObtenerScotiabank(int nroOp) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Scotiabank c WHERE c.nroOperacion = " + nroOp);
            q.setMaxResults(1);
            Scotiabank b = null;
            if (q.uniqueResult() != null) {
                b = (Scotiabank) q.uniqueResult();
            }
            transaction.commit();
            return b;
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
    public List ObtenerBbvaContadorDetalle(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT IdentificacionPago,C.Codigo,C.Descripcion,BCD.Monto\n"
                    + "FROM BBVACONTADORDETALLE BCD,ConceptoPagoDetalle C\n"
                    + "WHERE BCD.IdConcepto = C.IdConceptoPagoDetalle AND\n"
                    + "BCD.IdBbvaContador = " + id;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerBbvaRetornoDetalle_SegunClienteFecha(int idBbva, int idCliente, String fecha) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AM.Anio,AM.Mes,BD.REFERENCIA\n"
                    + "FROM BBVARETORNODETALLE BD,ANIOMES AM\n"
                    + "WHERE BD.IdBbvaRetorno = " + idBbva + " AND\n"
                    + "BD.TIPOCOBRANZA = 'CUOTA' AND\n"
                    + "BD.IDANIOMES = AM.ID AND\n"
                    + "BD.IdCliente = " + idCliente + " AND\n"
                    + "BD.FechaPago = '" + fecha + "'\n"
                    + "ORDER BY AM.NROORDEN";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerBbvaRetornoDetalle_SegunCliente(int idBbva, int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AM.Anio,AM.Mes,BD.REFERENCIA,BD.ID\n"
                    + "FROM BBVARETORNODETALLE BD,ANIOMES AM\n"
                    + "WHERE BD.IdBbvaRetorno = " + idBbva + " AND\n"
                    //                    + "BD.TIPOCOBRANZA = 'CUOTA' AND\n"
                    + "BD.IDANIOMES = AM.ID AND\n"
                    + "BD.IdCliente = " + idCliente + "\n"
                    + "ORDER BY AM.NROORDEN";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerScotiabankRetornoDetalle_SegunCliente(int idScotiabank, int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT AM.Anio,AM.Mes,BD.REFERENCIA,BD.ID\n"
                    + "FROM SCOTIABANKRETORNODETALLE BD,ANIOMES AM\n"
                    + "WHERE BD.IdScotiabankRetorno = " + idScotiabank + " AND\n"
                    //                    + "BD.TIPOCOBRANZA = 'CUOTA' AND\n"
                    + "BD.IDANIOMES = AM.ID AND\n"
                    + "BD.IdCliente = " + idCliente + "\n"
                    + "ORDER BY AM.NROORDEN";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerBbvaContadorDetalle_SegunIdentificadoraPagoIdBbva(int idBbva, String identificador) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT BC.IDCLIENTE,BCD.IDCONCEPTO,BCD.MONTO,BCD.CONCEPTO,BCD.TIPOCONCEPTOAGRUPAR,BCD.IDANIOMESCUOTA,\n"
                    + "BCD.IDFINANCIAMIENTODETALLE,BCD.IDREINCORPORACIONDETALLE,BCD.IDDEUDADETALLE,BCD.NROCONCEPTO\n"
                    + "FROM BBVACONTADOR BC,BBVACONTADORDETALLE BCD\n"
                    + "WHERE BCD.IDBBVACONTADOR = BC.ID AND\n"
                    + "BC.IDBBVA = " + idBbva + " AND\n"
                    + "BCD.IDENTIFICACIONPAGO = '" + identificador + "' ORDER BY BCD.CONCEPTO,BCD.IDCONCEPTO";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerScotiabankContadorDetalle_SegunIdentificadoraPagoIdScotiabank(int idScotiabank, String identificador) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT BC.IDCLIENTE,BCD.IDCONCEPTO,BCD.MONTO,BCD.CONCEPTO,BCD.TIPOCONCEPTOAGRUPAR,BCD.IDANIOMESCUOTA,\n"
                    + "BCD.IDFINANCIAMIENTODETALLE,BCD.IDREINCORPORACIONDETALLE,BCD.IDDEUDADETALLE,BCD.NROCONCEPTO\n"
                    + "FROM SCOTIABANKCONTADOR BC,SCOTIABANKCONTADORDETALLE BCD\n"
                    + "WHERE BCD.IDSCOTIABANKCONTADOR = BC.ID AND\n"
                    + "BC.IDSCOTIABANK = " + idScotiabank + " AND\n"
                    + "BCD.IDENTIFICACIONPAGO = '" + identificador + "' ORDER BY BCD.CONCEPTO,BCD.IDCONCEPTO";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerScotiabankContadorDetalle(int id) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT IdentificacionPago,C.Codigo,C.Descripcion,BCD.Monto\n"
                    + "FROM SCOTIABANKCONTADORDETALLE BCD,ConceptoPagoDetalle C\n"
                    + "WHERE BCD.IdConcepto = C.IdConceptoPagoDetalle AND\n"
                    + "BCD.IdScotiabankContador = " + id;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerBbvaContador_SegunOperacion(int nroOp) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//            List<Cliente> resultado = null;
            Query q = session.createSQLQuery("SELECT C.ccodigoCole,C.papePat,C.papeMat,C.pnombre,BC.id FROM Cliente C,BBVA B,BBVACONTADOR BC WHERE C.IDCLIENTE = BC.IDCLIENTE AND B.ID = BC.IDBBVA AND B.NROOPERACION = " + nroOp + " ORDER BY C.ccodigoCole");
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
    public List ObtenerScotiabankContador_SegunOperacion(int nroOp) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createSQLQuery("SELECT C.ccodigoCole,C.papePat,C.papeMat,C.pnombre,BC.id FROM Cliente C,SCOTIABANK B,SCOTIABANKCONTADOR BC WHERE C.IDCLIENTE = BC.IDCLIENTE AND B.ID = BC.IDSCOTIABANK AND B.NROOPERACION = " + nroOp + " ORDER BY C.ccodigoCole");
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
    public List ObtenerContadores_BBVA(int nroOrdenDesde, int nroOrdenHasta) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//             resultado = null;
            String sql = "SELECT C.IdCliente,C.ESTADO,\n"
                    + "C.CFECHAAFILIACION,C.CCODIGOCOLE\n"
                    + "FROM CLIENTE C\n"
                    + "WHERE C.TipoCliente = 'C' AND\n"
                    + "(C.Estado = 'H' OR C.Estado = 'I' OR C.Estado = 'O' OR C.Estado = 'V') AND\n"
                    + "(SELECT TOP 1 AM.NroOrden FROM CUOTAS CU,AnioMes AM WHERE CU.IdCliente = C.IdCliente AND CU.IdAnioMes = AM.Id ORDER BY AM.NroOrden DESC) + 1 >= " + nroOrdenDesde + " AND\n"
                    + "(SELECT TOP 1 AM.NroOrden FROM CUOTAS CU,AnioMes AM WHERE CU.IdCliente = C.IdCliente AND CU.IdAnioMes = AM.Id ORDER BY AM.NroOrden DESC) + 1 <= " + nroOrdenHasta + " UNION ALL\n"
                    + "SELECT C.IdCliente,C.ESTADO,\n"
                    + "C.CFECHAAFILIACION,C.CCODIGOCOLE\n"
                    + "FROM CLIENTE C\n"
                    + "WHERE C.TipoCliente = 'C' AND\n"
                    + "(C.Estado = 'H' OR C.Estado = 'I' OR C.Estado = 'O' OR C.Estado = 'V') AND\n"
                    + "dbo.ObtenerNroOrdenSegunFechaAfiliacion(C.CFECHAAFILIACION) >= " + nroOrdenDesde + " AND\n"
                    + "dbo.ObtenerNroOrdenSegunFechaAfiliacion(C.CFECHAAFILIACION) <= " + nroOrdenHasta + " AND\n"
                    + "C.IdCliente NOT IN(SELECT CUOTAS.IDCLIENTE\n"
                    + "FROM CUOTAS) ORDER BY CCODIGOCOLE;";
            Query q = session.createSQLQuery(sql);
            List resultado = q.list();
            transaction.commit();
            return resultado;

        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
//                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public List ObtenerContadoresSegunCobrador(String codigoContador) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
//             resultado = null;
            String sql = "SELECT IdCliente,CCodigoCole,PApePat + ' ' + PApeMat + ' ' + PNombre AS NOMBRE,\n"
                    + "ISNULL(PDireccionDomicilio,'') + ' - ' + isnull((SELECT TOP 1 NOMBREDISTRITO FROM UBIGEODISTRITO WHERE IDDISTRITO = CDISDOMICILIO),'') as PDireccionDomicilio,\n"
                    + "ISNULL(PDireccionTrabajo,'') + ' - ' + isnull((SELECT TOP 1 NOMBREDISTRITO FROM UBIGEODISTRITO WHERE IDDISTRITO = CDISTRABAJO),'') as PDireccionTrabajo,\n"
                    + "PTelefonoDomicilio,\n"
                    + "PTelefonoTrabajo,CCentroTrabajo,isnull(PTelefonoCelular,'') + ' ' + isnull(PTelefonoCelular1,'') as celular,\n"
                    + "CLugarCobranza,\n"
                    + "CASE \n"
                    + "	WHEN CLugarCobranza = 'D' THEN isnull((SELECT TOP 1 NOMBREDISTRITO FROM UBIGEODISTRITO WHERE IDDISTRITO = CDISDOMICILIO),'')\n"
                    + "	WHEN CLugarCobranza = 'T' THEN isnull((SELECT TOP 1 NOMBREDISTRITO FROM UBIGEODISTRITO WHERE IDDISTRITO = CDISTRABAJO),'')\n"
                    + "	ELSE '' \n"
                    + "END AS LUGAR,CFechaAfiliacion\n"
                    + "FROM Cliente \n"
                    + "WHERE TipoCliente = 'C' AND (Estado = 'H' OR Estado = 'I' OR Estado = 'V' OR Estado = 'O') AND COBCodigoCobrador = '" + codigoContador + "' ORDER BY CASE \n"
                    + "	WHEN CLugarCobranza = 'D' THEN isnull((SELECT TOP 1 NOMBREDISTRITO FROM UBIGEODISTRITO WHERE IDDISTRITO = CDISDOMICILIO),'')\n"
                    + "	WHEN CLugarCobranza = 'T' THEN isnull((SELECT TOP 1 NOMBREDISTRITO FROM UBIGEODISTRITO WHERE IDDISTRITO = CDISTRABAJO),'')\n"
                    + "END,CCodigoCole";
            Query q = session.createSQLQuery(sql);
            List resultado = q.list();
            transaction.commit();
            return resultado;

        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
//                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Cliente ObtenerClienteSegunCodigoContador(String codigo) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Cliente resultado = null;
            Query q = null;
            q = session.createQuery("FROM Cliente c WHERE c.ccodigoCole = '" + codigo + "' ORDER BY c.papePat,c.papeMat,c.pnombre DESC");
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                resultado = (Cliente) q.uniqueResult();
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
    public List ObtenerBbvaRetornoDetalle_SegunIdBbvaRetorno(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT DISTINCT IdCliente,FechaPago\n"
                    + "FROM BbvaRetornoDetalle\n"
                    + "WHERE IdBbvaRetorno = " + idRetorno;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerBbvaRetornoSoloClientes_SegunIdBbvaRetorno(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT DISTINCT IdCliente\n"
                    + "FROM BbvaRetornoDetalle\n"
                    + "WHERE IdBbvaRetorno = " + idRetorno;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerScotiabankRetornoSoloClientes_SegunIdScotiabankRetorno(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT DISTINCT IdCliente\n"
                    + "FROM ScotiabankRetornoDetalle\n"
                    + "WHERE IdScotiabankRetorno = " + idRetorno;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerPagosNoPertenecen_Bbva(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT BRD.REFERENCIA\n"
                    + "FROM BbvaRetornoDetalle BRD,BbvaRetorno BR\n"
                    + "WHERE\n"
                    + "BRD.IdBbvaRetorno = " + idRetorno + " AND\n"
                    + "BRD.IdBbvaRetorno = BR.Id AND\n"
                    + "BRD.Referencia NOT IN (SELECT BCD.IdentificacionPago FROM BbvaContadorDetalle BCD,BbvaContador BC WHERE BCD.IdBbvaContador = BC.Id AND BC.IdBbva = BR.IdBbva)";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerPagosNoPertenecen_Scotiabank(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT BRD.REFERENCIA\n"
                    + "FROM ScotiabankRetornoDetalle BRD,ScotiabankRetorno BR\n"
                    + "WHERE\n"
                    + "BRD.IdScotiabankRetorno = " + idRetorno + " AND\n"
                    + "BRD.IdScotiabankRetorno = BR.Id AND\n"
                    + "BRD.Referencia NOT IN (SELECT BCD.IdentificacionPago FROM ScotiabankContadorDetalle BCD,ScotiabankContador BC WHERE BCD.IdScotiabankContador = BC.Id AND BC.IdScotiabank = BR.IdScotiabank)";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerMontosDiferentes_Bbva(int idBbva, int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT A.IdentificacionPago,A.monto,BRD.Referencia,BRD.ImporteDeposito\n"
                    + "FROM(\n"
                    + "SELECT IdentificacionPago,SUM(Monto) AS monto\n"
                    + "FROM BbvaContadorDetalle BCD,BbvaContador BC\n"
                    + "WHERE BCD.IdBbvaContador = BC.ID AND\n"
                    + "BC.IdBbva = " + idBbva + "\n"
                    + "GROUP BY IdentificacionPago) A,BbvaRetornoDetalle BRD,BbvaRetorno BR\n"
                    + "WHERE A.IdentificacionPago = BRD.Referencia AND\n"
                    + "BRD.IdBbvaRetorno = BR.Id AND\n"
                    + "BR.IdBbva = " + idBbva + " AND\n"
                    + "BR.ID = " + idRetorno + " AND\n"
                    + "A.Monto - BRD.ImporteDeposito <> 0";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerMontosDiferentes_Scotiabank(int idScotiabank, int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT A.IdentificacionPago,A.monto,BRD.Referencia,BRD.ImporteDeposito\n"
                    + "FROM(\n"
                    + "SELECT IdentificacionPago,SUM(Monto) AS monto\n"
                    + "FROM ScotiabankContadorDetalle BCD,ScotiabankContador BC\n"
                    + "WHERE BCD.IdScotiabankContador = BC.ID AND\n"
                    + "BC.IdScotiabank = " + idScotiabank + "\n"
                    + "GROUP BY IdentificacionPago) A,ScotiabankRetornoDetalle BRD,ScotiabankRetorno BR\n"
                    + "WHERE A.IdentificacionPago = BRD.Referencia AND\n"
                    + "BRD.IdScotiabankRetorno = BR.Id AND\n"
                    + "BR.IdScotiabank = " + idScotiabank + " AND\n"
                    + "BR.ID = " + idRetorno + " AND\n"
                    + "A.Monto - BRD.ImporteDeposito <> 0";
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerBbvaRetornoDetalle(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT C.PApePat + ' ' + C.PApeMat + ' ' + C.PNombre,BR.Referencia,\n"
                    + "BR.ImporteOrigen,BR.ImporteDeposito,\n"
                    + "BR.ImporteMora,BR.OficinaPago,BR.NroMovimiento,\n"
                    + "BR.FechaPago,BR.TipoValor,BR.CanalEntrada,(SELECT DISTINCT TOP 1 TD.NombreDocPago + ' ' + S.Serie + ' ' + CAST(DP.NroDocumentoPago AS varchar)\n"
                    + "FROM BbvaRetornoDetalleDocumento BRDD,DocumentoPagoDet DPD,DocumentoPago DP,TipoDocSerie TDS,TipoDocPago TD,Serie S\n"
                    + "WHERE BRDD.IdBbvaRetornoDetalle = BR.ID AND\n"
                    + "BRDD.IdDocumentoPagoDet = DPD.IdDocumentoPagoDet AND\n"
                    + "DPD.IdDocumentoPago = DP.IdDocumentoPago AND\n"
                    + "DP.IdTipoPagoSerie = TDS.Id AND\n"
                    + "TDS.IdTipoDoc = TD.IdTipoDocPago AND\n"
                    + "TDS.IdSerie = S.IdSerie) AS DOCUMENTO,BR.ID AS IDBR\n"
                    + "FROM BBVARETORNODETALLE BR,CLIENTE C\n"
                    + "WHERE BR.IdCliente = C.IdCliente AND\n"
                    + "BR.IdBbvaRetorno = " + idRetorno;
//            String sql = "SELECT C.PApePat + ' ' + C.PApeMat + ' ' + C.PNombre,BR.Referencia,\n"
//                    + "BR.ImporteOrigen,BR.ImporteDeposito,\n"
//                    + "BR.ImporteMora,BR.OficinaPago,BR.NroMovimiento,\n"
//                    + "BR.FechaPago,BR.TipoValor,BR.CanalEntrada\n"
//                    + "FROM BBVARETORNODETALLE BR,CLIENTE C\n"
//                    + "WHERE BR.IdCliente = C.IdCliente AND\n"
//                    + "BR.IdBbvaRetorno = " + idRetorno;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerScotiabankRetornoDetalle(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT C.PApePat + ' ' + C.PApeMat + ' ' + C.PNombre,BR.Referencia,\n"
                    + "BR.ImporteOrigen,BR.NumeroRecibo,BR.MedioPago,\n"
                    + "BR.NumeroOperacion,BR.HoraPago,BR.FechaRealPago,\n"
                    + "BR.Canal,(SELECT DISTINCT TOP 1 TD.NombreDocPago + ' ' + S.Serie + ' ' + CAST(DP.NroDocumentoPago AS varchar)\n"
                    + "FROM ScotiabankRetornoDetalleDocumento BRDD,DocumentoPagoDet DPD,DocumentoPago DP,TipoDocSerie TDS,TipoDocPago TD,Serie S\n"
                    + "WHERE BRDD.IdScotiabankRetornoDetalle = BR.ID AND\n"
                    + "BRDD.IdDocumentoPagoDet = DPD.IdDocumentoPagoDet AND\n"
                    + "DPD.IdDocumentoPago = DP.IdDocumentoPago AND\n"
                    + "DP.IdTipoPagoSerie = TDS.Id AND\n"
                    + "TDS.IdTipoDoc = TD.IdTipoDocPago AND\n"
                    + "TDS.IdSerie = S.IdSerie) AS DOCUMENTO,BR.ID AS IDBR\n"
                    + "FROM SCOTIABANKRETORNODETALLE BR,CLIENTE C\n"
                    + "WHERE BR.IdCliente = C.IdCliente AND\n"
                    + "BR.IdScotiabankRetorno = " + idRetorno;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerComprobantePago_SegunBbvaRetorno(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT DISTINCT DP.IdDocumentoPago\n"
                    + "FROM BbvaRetornoDetalleDocumento BRDD,BbvaRetornoDetalle BRD,BbvaRetorno BR,DocumentoPagoDet DPD,DocumentoPago DP\n"
                    + "WHERE BR.Id = BRD.IdBbvaRetorno AND\n"
                    + "BRD.Id = BRDD.IdBbvaRetornoDetalle AND\n"
                    + "BRDD.IdDocumentoPagoDet = DPD.IdDocumentoPagoDet AND\n"
                    + "DPD.IdDocumentoPago = DP.IdDocumentoPago AND\n"
                    + "BR.Id = " + idRetorno;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerClienteCertificado_SegunCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ClienteCertificado cc where cc.cliente.idCliente = " + idCliente;
            Query q = session.createQuery(hql);
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
    public List ObtenerClienteDerechoHabiente_SegunCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ClienteDerechoHabiente cc where cc.cliente.idCliente = " + idCliente;
            Query q = session.createQuery(hql);
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
    public List ObtenerClienteAuditorIndependiente_SegunCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ClienteAuditorIndependiente cc where cc.cliente.idCliente = " + idCliente;
            Query q = session.createQuery(hql);
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
    public List ObtenerClienteComitePerito_SegunCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ClienteComitePerito cc where cc.cliente.idCliente = " + idCliente;
            Query q = session.createQuery(hql);
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
    public List ObtenerFichaComiteDesearia_SegunFicha(int idFicha) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM FichaComite cc WHERE cc.borrado = '1' AND cc.fichaDatos.id = " + idFicha;
            Query q = session.createQuery(hql);
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
    public List ObtenerFichaDeporte_SegunFicha(int idFicha) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM FichaDeporte cc WHERE cc.borrado = '1' AND cc.fichaDatos.id = " + idFicha;
            Query q = session.createQuery(hql);
            List<FichaDeporte> l = q.list();
            for (FichaDeporte fd : l) {
                Hibernate.initialize(fd.getDeporte());
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
    public List ObtenerFichaInstitucion_SegunFicha(int idFicha) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM FichaInstitucion cc WHERE cc.borrado = '1' AND cc.fichaDatos.id = " + idFicha;
            Query q = session.createQuery(hql);
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
    public List ObtenerComprobantePago_SegunScotiabankRetorno(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT DISTINCT DP.IdDocumentoPago\n"
                    + "FROM ScotiabankRetornoDetalleDocumento BRDD,ScotiabankRetornoDetalle BRD,ScotiabankRetorno BR,DocumentoPagoDet DPD,DocumentoPago DP\n"
                    + "WHERE BR.Id = BRD.IdScotiabankRetorno AND\n"
                    + "BRD.Id = BRDD.IdScotiabankRetornoDetalle AND\n"
                    + "BRDD.IdDocumentoPagoDet = DPD.IdDocumentoPagoDet AND\n"
                    + "DPD.IdDocumentoPago = DP.IdDocumentoPago AND\n"
                    + "BR.Id = " + idRetorno;
            Query q = session.createSQLQuery(sql);
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
    public List ObtenerBbvaRetornoDetalle_ParaValidacion(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT BC.IdCliente,BCD.IdAnioMesCuota,BCD.IdConcepto,BCD.Concepto,BCD.IdFinanciamientoDetalle,BCD.IdReincorporacionDetalle,BCD.IdDeudaDetalle,BCD.IDENTIFICACIONPAGO\n"
                    + "FROM BBVARetorno BR,BBVA B,BBVAContador BC,BBVAContadorDetalle BCD,BBVARetornoDetalle BRD\n"
                    + "WHERE BR.IdBBVA = B.Id AND\n"
                    + "BC.IdBBVA = B.Id AND\n"
                    + "BC.Id = BCD.IdBBVAContador AND BRD.IdBBVARetorno = BR.ID AND\n"
                    + "BR.ID = " + idRetorno + " AND\n"
                    + "BRD.Referencia = BCD.IdentificacionPago\n"
                    + "ORDER BY BRD.Referencia,BCD.Concepto";
            Query q = session.createSQLQuery(sql);
            List<ScotiabankRetornoDetalle> l = q.list();
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
    public List ObtenerScotiabankRetornoDetalle_ParaValidacion(int idRetorno) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT BC.IdCliente,BCD.IdAnioMesCuota,BCD.IdConcepto,BCD.Concepto,BCD.IdFinanciamientoDetalle,BCD.IdReincorporacionDetalle,BCD.IdDeudaDetalle,BCD.IDENTIFICACIONPAGO\n"
                    + "FROM ScotiabankRetorno BR,Scotiabank B,ScotiabankContador BC,ScotiabankContadorDetalle BCD,ScotiabankRetornoDetalle BRD\n"
                    + "WHERE BR.IdScotiabank = B.Id AND\n"
                    + "BC.IdScotiabank = B.Id AND\n"
                    + "BC.Id = BCD.IdScotiabankContador AND BRD.IdScotiabankRetorno = BR.ID AND\n"
                    + "BR.ID = " + idRetorno + " AND\n"
                    + "BRD.Referencia = BCD.IdentificacionPago\n"
                    + "ORDER BY BRD.Referencia,BCD.Concepto";
//            String hql = "FROM BbvaRetornoDetalle bd WHERE bd.idBbvaRetorno = " + idRetorno;
            Query q = session.createSQLQuery(sql);
            List<BbvaRetornoDetalle> l = q.list();
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
    public List ObtenerBbvaRetorno_SegunIdBbva(int idBbva) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM BbvaRetorno b WHERE b.idBbva = " + idBbva + " ORDER BY b.id DESC";
            Query q = session.createQuery(hql);
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
    public List ObtenerScotiabankRetorno_SegunIdScotiabank(int idBbva) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "FROM ScotiabankRetorno b WHERE b.idScotiabank = " + idBbva + " ORDER BY b.id DESC";
            Query q = session.createQuery(hql);
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
    public void ObtenerCalculoFinanciamientoContador(int idContador) {
        /*try {
         session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
         transaction = session.beginTransaction();
         List<Object> resultado = null;
         Query q = null;
         q = session.createQuery("SELECT dpd.documentoPago.fechaPago,dpd.conceptoPagoDetalle.descripcion,dpd.cantidad,dpd.precioUnitario,dpd.anioDesde,dpd.mesDesde,dpd.anioHasta,dpd.mesHasta FROM DocumentoPagoDet dpd WHERE dpd.documentoPago.contador.idContador = " + idContador);
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
         }*/
    }

    @Override
    public boolean HabilitarCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Cliente cDATA = (Cliente) session.get(Cliente.class, idCliente);
            cDATA.setEstado("H");
            //Hibernate.initialize(dpDATA.getCobrador().getCliente());
            //Cliente cliente = dpDATA.getCobrador().getCliente();.
            session.update(cDATA);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            return false;
            //throw e;
        }
    }

    @Override
    public boolean InhabilitarCliente(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Cliente cDATA = (Cliente) session.get(Cliente.class, idCliente);
            cDATA.setEstado("I");
            session.update(cDATA);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            return false;
            //throw e;
        }
    }

    @Override
    public void InsertarCuentaCorrienteCliente(CuentaCorriente cuentaCorriente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(cuentaCorriente);
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
    public List ObtenerTodasSociedas() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Cliente> resultado = null;
            Query q = null;
//                                    "FROM Cliente c WHERE c.tipoCliente = 'C' ORDER BY c.papePat,c.papeMat,c.pnombre DESC"
            q = session.createQuery("FROM Cliente s WHERE s.tipoCliente = 'S' ORDER BY s.scodigoSoc,s.sruc DESC");
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
    public List obtenerUniversidades() {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Universidad s WHERE s.borrado = '1' ORDER BY s.nombre");
            List<Cliente> resultado = (List<Cliente>) q.list();
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
    public FichaDatos obtenerFicha(Integer nro) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM FichaDatos s WHERE s.borrado = '1' and s.nro = :nro").setParameter("nro", nro);
            List<FichaDatos> l = (List<FichaDatos>) q.list();
            FichaDatos dto = null;
            if (l.size() > 0) {
                dto = l.get(0);
            }
            transaction.commit();
            return dto;
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
