/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.DocumentoPagoDAO;
import caja.dao.NotaDAO;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.Nota;
import caja.mapeo.entidades.NotaDetalle;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import java.util.Date;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class NotaBO implements NotaBOIFace {

    private static NotaBO INSTANCE = new NotaBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (NotaBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotaBO();
                }
            }
        }
    }

    public static NotaBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public NotaBO() {

    }

    @Override
    public boolean GuardarNota(Nota nc) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.GuardarNota(nc);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean GuardarNotaElectronica(Nota nc) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.GuardarNotaElectronica(nc);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean GuardarNotaDetalle(NotaDetalle ncd) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.GuardarNotaDetalle(ncd);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean ActualizarNotaDetalle(NotaDetalle ncd) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.ActualizarNotaDetalle(ncd);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean GenerarNumeracionElectronicaNota(Nota nc) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.GenerarNumeracionElectronicaNota(nc);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean eliminarCuotaFinanciamientoReincorporacion(DocumentoPago dp) {
        try {
            /*VERIFICAR SI SE PUEDE ELIMINAR CUOTA*/
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            boolean esFactibleCuota = dpBO.EsFactibleEliminarCuota(dp);
            if (esFactibleCuota == false) {
                SeguridadBO sBO = SeguridadBO.getInstance();
                sBO.GenerarLog("NO PUEDE ELIMINAR UNA CUOTA INTERMEDIA(DocumentoPagoBO.EliminarComprobante)");
                return false;
            }
            /*-------------------------------*/
 /*VERIFICAR SI SE PUEDE ELIMINAR FINANCIAMIENTO*/
            int esFactibleFinanciamiento = dpBO.EsFactibleEliminarFinanciamiento(dp);
            int esFactibleReincorporacion = dpBO.EsFactibleEliminarReincorporacion(dp);
            /*-------------------------------*/
            if (esFactibleCuota && esFactibleFinanciamiento > -1 && esFactibleReincorporacion > -1) {
                DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
                dpDAO.EliminarCuentasCorrientes(dp.getCliente().getIdCliente(), dp.getIdDocumentoPago());
                if (dp.getClienteByIdContadorEmpresa() != null) {
                    dpDAO.EliminarCuotasCliente(dp.getClienteByIdContadorEmpresa().getIdCliente(), dp.getIdDocumentoPago());
                } else {
                    dpDAO.EliminarCuotasCliente(dp.getCliente().getIdCliente(), dp.getIdDocumentoPago());
                }
                if (esFactibleFinanciamiento > 0) {
                    FinanciamientoDocumentoPago fdp = dpDAO.ObtenerFinanciamientoDocumentoPagoDet(esFactibleFinanciamiento);
                    if (fdp.getEstado().equals("CI")) {
                        dpDAO.EliminarTodoFinanciamiento(fdp.getId());
                        //dpDAO.
                    } else {
                        dpDAO.EliminarFinanciamiento(esFactibleFinanciamiento);
                    }
                }
                if (esFactibleReincorporacion > 0) {
                    dpDAO.EliminarReincorporacion(esFactibleReincorporacion);
                }
                dpBO.ValidarHabilitacion(dp);
                return true;
            } else {
                //mensaje = "NO PUEDE ELIMINAR UN CUOTA DE FINANCIAMIENTO INTERMEDIA";
                SeguridadBO sBO = SeguridadBO.getInstance();
                sBO.GenerarLog("NO PUEDE ELIMINAR UN CUOTA DE FINANCIAMIENTO INTERMEDIA(DocumentoPagoBO.EliminarComprobante)");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("NO SE PUDO ELIMINAR");
            return false;
        }
    }

    @Override
    public double ObtenerSumaTotalValorVenta(int idNota) {
        double montoValorVenta = 0;
        NotaDAO ncDAO = NotaDAO.getInstance();
        montoValorVenta = ncDAO.ObtenerSumaTotalValorVenta(idNota);
        montoValorVenta = Math.round(montoValorVenta * Math.pow(10, 2)) / Math.pow(10, 2);
        return montoValorVenta;
    }

    @Override
    public double ObtenerSumaTotalIGV(int idNota) {
        double igv = 0;
        NotaDAO ncDAO = NotaDAO.getInstance();
        igv = ncDAO.ObtenerSumaTotalIGV(idNota);
        igv = Math.round(igv * Math.pow(10, 2)) / Math.pow(10, 2);
        return igv;
    }

    @Override
    public double ObtenerSumaTotalPrecioVenta(int idNota) {
        double monto = 0;
        NotaDAO ncDAO = NotaDAO.getInstance();
        monto = ncDAO.ObtenerSumaTotalPrecioVenta(idNota);
        monto = Math.round(monto * Math.pow(10, 2)) / Math.pow(10, 2);
        return monto;
    }

    @Override
    public Nota ObtenerNotaCredito(int idTipoNota, String nroSerie, int nro) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.ObtenerNotaCredito(idTipoNota, nroSerie, nro);
    }

    @Override
    public boolean ActualizarNota(Nota nc) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.ActualizarNota(nc);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean EliminarNotaDetalle(NotaDetalle ncd) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.EliminarNotaDetalle(ncd);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean EliminarNota(Nota n) {
        try {
            NotaDAO ncDAO = NotaDAO.getInstance();
            ncDAO.EliminarNota(n);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List BuscarTodasNotasCredito() {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.BuscarTodasNotasCredito();
    }

    @Override
    public List BuscarNota_SegunFiltro(String filtro) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.BuscarNota_SegunFiltro(filtro);
    }

    @Override
    public List BuscarNota_AplicadasComprobante(int idComprobante) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.BuscarNota_AplicadasComprobante(idComprobante);
    }

    @Override
    public double ObtenerMontoTotalNotAplicadaComprobante(int idComprobante) {
        NotaDAO cpDAO = NotaDAO.getInstance();
        double saldo = cpDAO.ObtenerMontoTotalNotAplicadaComprobante(idComprobante);
        saldo = Math.round(saldo * Math.pow(10, 2)) / Math.pow(10, 2);
        return saldo;
    }

    @Override
    public List BuscarNota_SegunNroDocIdentidad(String doc) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.BuscarNota_SegunNroDocIdentidad(doc);
    }

    @Override
    public List BuscarNota_SegunNroNota(int nro) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.BuscarNota_SegunNroNota(nro);
    }

    @Override
    public List BuscarNota_SegunFecha(Date desde, Date hasta) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.BuscarNota_SegunFecha(desde, hasta);
    }

    @Override
    public List ObtenerDetalleNota(int idNC) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.ObtenerDetalleNota(idNC);
    }

    @Override
    public List ObtenerIdParticipantes_SegunNotaCredito(int idNC) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.ObtenerIdParticipantes_SegunNotaCredito(idNC);
    }

    @Override
    public List ObtenerIdConstancia_SegunNotaCredito(int idNC) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.ObtenerIdConstancia_SegunNotaCredito(idNC);
    }

    @Override
    public void actualizarBorradoPartipantes(int idParticipante, String estadoBorrado) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        ncDAO.actualizarBorradoPartipantes(idParticipante, estadoBorrado);
    }

    @Override
    public void actualizarBorradoConstancias(int idParticipante, String estadoBorrado) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        ncDAO.actualizarBorradoConstancias(idParticipante, estadoBorrado);
    }

    @Override
    public List ObtenerTributosGenerales(int idNC) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.ObtenerTributosGenerales(idNC);
    }

    @Override
    public List ObtenerTodosTipoNota(int idTipoNota) {
        NotaDAO ncDAO = NotaDAO.getInstance();
        return ncDAO.ObtenerTodosTipoNota(idTipoNota);
    }

}
