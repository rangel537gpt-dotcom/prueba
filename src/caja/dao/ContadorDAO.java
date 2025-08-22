/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

//import caja.bo.ReporteCuentaCorriente;
import caja.conf.SessionHibernateUtil;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ClienteAuditorIndependiente;
import caja.mapeo.entidades.ClienteCertificado;
import caja.mapeo.entidades.ClienteDerechoHabiente;
import caja.mapeo.entidades.FichaDatos;
import caja.mapeo.entidades.UbigeoDepartamento;
import caja.mapeo.entidades.UbigeoDistrito;
import caja.mapeo.entidades.UbigeoLocalidad;
import caja.mapeo.entidades.UbigeoProvincia;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import sun.security.jca.GetInstance;

/**
 *
 * @author juan carlos
 */
public class ContadorDAO implements ContadorDAOIFace {

    Session session = null;
    Transaction transaction = null;

    List<Cliente> resultadoBusqueda = null;
    //List<UbigeoDepartamento> busqueda = null;
    private static ContadorDAO INSTANCE = new ContadorDAO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ContadorDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ContadorDAO();
                }
            }
        }
    }

    public static ContadorDAO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public ContadorDAO() {
    }

    @Override
    public List BuscarContadorPorApePat(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente WHERE papePat LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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

            q = session.createQuery("FROM Cliente WHERE papeMat LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public List BuscarFichaInscripcion(String filtro) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM FichaDatos f WHERE 1=1 " + filtro + " order by f.apePat, f.apeMat, f.nombre");
            List<FichaDatos> l = (List<FichaDatos>) q.list();
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
    public List BuscarContadorPorNombre(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'C' AND c.papePat + ' ' + c.papeMat + ' ' + c.pnombre LIKE '%" + busqueda + "%' ORDER BY c.papePat,c.papeMat,c.pnombre");
            resultadoBusqueda = (List<Cliente>) q.list();
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

            q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'C' AND c.pnroDocumento LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public List BuscarContadorPorCodigoCole(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }
            Query q = session.createQuery("FROM Cliente c WHERE c.tipoCliente = 'C' AND c.ccodigoCole LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public Cliente ObtenerContador_SegunCodigoColegiado(String cc) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Cliente WHERE ccodigoCole LIKE '" + cc + "'");
            Cliente c = null;
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                c = (Cliente) q.uniqueResult();
            }
            transaction.commit();
            return c;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
                return null;
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Cliente ObtenerContadorSegunDNI_Distinto(String dni, int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Cliente c WHERE c.pnroDocumento like '" + dni + "' AND c.idCliente <> " + idCliente);
            Cliente c = null;
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                c = (Cliente) q.uniqueResult();
            }
            transaction.commit();
            return c;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
                return null;
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Cliente ObtenerContadorSegunDNI(String dni) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Cliente c WHERE c.pnroDocumento like '" + dni + "'");
            Cliente c = null;
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                c = (Cliente) q.uniqueResult();
            }
            transaction.commit();
            return c;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
                return null;
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public Cliente ObtenerContador_SegunCodigoColegiado_Distinto(String cc, int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Cliente c WHERE c.idCliente <> " + idCliente + " AND c.ccodigoCole LIKE '" + cc + "'");
            Cliente c = null;
            q.setMaxResults(1);
            if (q.uniqueResult() != null) {
                c = (Cliente) q.uniqueResult();
            }
            transaction.commit();
            return c;
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
                return null;
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        }
    }

    @Override
    public void GuardarContador(Cliente cont) {
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
    }

    @Override
    public void GuardarClienteCertificado(ClienteCertificado cont) {
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
    }

    @Override
    public void ActualizarClienteCertificado(ClienteCertificado cont) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(cont);
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
    public void GuardarObjeto(Object cont) {
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
    }

    @Override
    public void ActualizarObjeto(Object cont) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(cont);
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
    public void EliminarClienteCertificado(ClienteCertificado cont) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(cont);
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
    public void EliminarClienteDerechoHabiente(ClienteDerechoHabiente cont) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(cont);
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
    public void ActualizarContador(Cliente cont) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(cont);
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
    public void EliminarContador(Cliente cont) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(cont);
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
    public List ObtenerContadorUbigeoDepartamento() {
        try {

            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            q = session.createQuery("FROM UbigeoDepartamento");
            resultadoBusqueda1 = (List<UbigeoDepartamento>) q.list();
            transaction.commit();
            return resultadoBusqueda1;

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
    public ClienteCertificado ObtenerUltimoCertificadoContador_SegunCodigo(String codigo) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM ClienteCertificado c WHERE c.cliente.ccodigoCole like '" + codigo + "' ORDER BY c.hasta DESC");
            q.setMaxResults(1);
            ClienteCertificado cc = null;
            if (q.uniqueResult() != null) {
                cc = (ClienteCertificado) q.uniqueResult();
            }
            transaction.commit();
            return cc;
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
    public List ObtenerCuotasPendientesContador(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createSQLQuery("exec ObtenerCuotasPendientesContador " + idCliente);
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
    public List obtenerDerechoHabiente(String dni) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM ClienteDerechoHabiente c WHERE c.dni like '" + dni + "'");
            List<ClienteDerechoHabiente> l = q.list();
            for (ClienteDerechoHabiente c : l) {
                Hibernate.initialize(c.getCliente());
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
    public List ObtenerCuotasPendientesSociedad(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createSQLQuery("exec ObtenerCuotasPendientesSociedad " + idCliente);
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
    public List ObtenerFinanciamientoPendientesContador(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createSQLQuery("exec ObtenerFinanciamientoPendientesContador " + idCliente);
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
    public List ObtenerReincorporacionPendientesContador(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createSQLQuery("exec ObtenerReincorporacionPendientesContador " + idCliente);
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
    public List ObtenerDeudasContador(int idCliente) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createSQLQuery("exec ObtenerDeudasContador " + idCliente);
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
    public List ObtenerContadorUbigeoProvincia(int idDepartamento) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            q = session.createQuery("FROM UbigeoProvincia up WHERE ubigeoDepartamento.idDepartamento =" + idDepartamento);
            resultadoBusqueda1 = (List<UbigeoProvincia>) q.list();
            transaction.commit();
            return resultadoBusqueda1;
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
    public String obteneroCodigoUbigeo(int idDistrito) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String sql = "SELECT UbigeoDepartamento.CodigoDepartamento + UbigeoProvincia.CodigoProvincia + UbigeoDistrito.CodigoDistrito AS codigo_ubigeo\n"
                    + "FROM UbigeoDistrito,UbigeoProvincia,UbigeoDepartamento\n"
                    + "WHERE \n"
                    + "UbigeoDistrito.IdProvincia = UbigeoProvincia.IdProvincia AND\n"
                    + "UbigeoProvincia.IdDepartamento = UbigeoDepartamento.IdDepartamento AND\n"
                    + "IdDistrito = " + idDistrito;
            Query q = session.createSQLQuery(sql);
            List l = (List<String>) q.list();
            transaction.commit();
            if (l.size() > 0) {
                return (String) l.get(0);
            }
            return "";
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
    public List ObtenerContadorUbigeoDistrito(int idProvincia) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            q = session.createQuery("FROM UbigeoDistrito ud WHERE ubigeoProvincia.idProvincia =" + idProvincia);
            resultadoBusqueda1 = (List<UbigeoDistrito>) q.list();
            transaction.commit();
            return resultadoBusqueda1;

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
    public List BuscarPorApellidos(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente WHERE (papePat + ' ' + papeMat) LIKE '%" + busqueda + "%'");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public List BuscarContadorPorApellidos(String busqueda) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query q = null;
            if (resultadoBusqueda != null) {
                resultadoBusqueda.clear();
            }

            q = session.createQuery("FROM Cliente WHERE tipoCliente = 'C' AND (papePat + ' ' + papeMat) LIKE '%" + busqueda + "%' ORDER BY papePAT,papeMat");
            resultadoBusqueda = (List<Cliente>) q.list();
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
    public List buscarAuditorIndependiente(String filtro) {
        try {
            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM ClienteAuditorIndependiente a WHERE a.borrado = '1' AND a.cliente.tipoCliente = 'C' " + filtro + " ORDER BY a.cliente.papePat,a.cliente.papeMat");
            List<ClienteAuditorIndependiente> l = (List<ClienteAuditorIndependiente>) q.list();
            for (ClienteAuditorIndependiente c : l) {
                Hibernate.initialize(c.getCliente());
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
    public List ObtenerContadorUbigeoLocalidad(int idDistrito) {
        try {

            session = SessionHibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List resultadoBusqueda1 = null;
            Query q = null;
            //  q = session.createQuery("FROM UbigeoDistrito ud WHERE ubigeoProvincia.idProvincia =" + idProvincia);
            q = session.createQuery("FROM UbigeoLocalidad ul WHERE ubigeoDistrito.idDistrito =" + idDistrito);
            resultadoBusqueda1 = (List<UbigeoLocalidad>) q.list();
            transaction.commit();
            return resultadoBusqueda1;

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
