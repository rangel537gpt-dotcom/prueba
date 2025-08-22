/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.CuotasDAO;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cuotas;
import caja.mapeo.entidades.DocumentoPagoDet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author user
 */
public class CuotasBO implements CuotasBOIFace {

    private static CuotasBO INSTANCE = new CuotasBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (CuotasBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CuotasBO();
                }
            }
        }
    }

    public static CuotasBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public void InsertarCuota() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DocumentoPagoDet ObtenerUltimaCuotaOrdinaria(Cliente cliente) {
        try {
            CuotasDAO cuotasDAO = CuotasDAO.getInstance();
            //OBTENER CUOTAS PENDIENTES
            /*{FALTA IMPLEMENTAR}*/
            //-------------------------
            //SI NO TIENE CUOTAS PENDIENTES ENTONCES PROSIGUE EL PAGO DEL SIGUIENTE MES
            Cuotas ultimaCuota = cuotasDAO.ObtenerUltimaCuota(cliente.getIdCliente());
            //DocumentoPagoDet ultimoPago = cuotaDAO.ObtenerUltimaCuotaOrdinariaContador(idCliente);
            DocumentoPagoDet detPago = new DocumentoPagoDet();
            if (ultimaCuota == null) {
                SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
                SimpleDateFormat formatMes = new SimpleDateFormat("MM");
                int anio = 0;
                int mes = 0;
                if (cliente.getTipoCliente().equals("C")) {
                    anio = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
                    mes = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
                }
                if (cliente.getTipoCliente().equals("S")) {
                    anio = Integer.valueOf(formatAnio.format(cliente.getSfechaAfiliacion()));
                    mes = Integer.valueOf(formatMes.format(cliente.getSfechaAfiliacion()));
                }
                detPago.setAnioHasta(anio);
                detPago.setMesHasta(mes);
            } else {
                detPago.setAnioHasta(ultimaCuota.getAnioMes().getAnio());
                detPago.setMesHasta(ultimaCuota.getAnioMes().getMes());
            }
            return detPago;
        } catch (Exception e) {
            System.out.println("Error en Obtener Ultima Cuota Ordinaria Cliente");
            return null;
        }
    }

    @Override
    public Cuotas ObtenerUltimaCuotaOrdinaria(int idCliente) {
        try {
            CuotasDAO cuotasDAO = CuotasDAO.getInstance();
            //OBTENER CUOTAS PENDIENTES
            /*{FALTA IMPLEMENTAR}*/
            //-------------------------
            //SI NO TIENE CUOTAS PENDIENTES ENTONCES PROSIGUE EL PAGO DEL SIGUIENTE MES
            Cuotas ultimaCuota = cuotasDAO.ObtenerUltimaCuota(idCliente);
            return ultimaCuota;
            /*if (ultimaCuota == null) {
             SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
             SimpleDateFormat formatMes = new SimpleDateFormat("MM");
             int anio = 0;
             int mes = 0;
             if (cliente.getTipoCliente().equals("C")) {
             anio = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
             mes = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
             }
             if (cliente.getTipoCliente().equals("S")) {
             anio = Integer.valueOf(formatAnio.format(cliente.getSfechaAfiliacion()));
             mes = Integer.valueOf(formatMes.format(cliente.getSfechaAfiliacion()));
             }
             detPago.setAnioHasta(anio);
             detPago.setMesHasta(mes);
             } else {
             detPago.setAnioHasta(ultimaCuota.getAnioMes().getAnio());
             detPago.setMesHasta(ultimaCuota.getAnioMes().getMes());
             }
             return detPago;*/
        } catch (Exception e) {
            System.out.println("Error en Obtener Ultima Cuota Ordinaria Cliente");
            return null;
        }
    }

    @Override
    public boolean GuardarCuota(Cuotas cuota/*Cliente cliente, DocumentoPagoDet dpd*/) {
        try {
            /*Cuotas cuota = new Cuotas();*/
            CuotasDAO cDAO = CuotasDAO.getInstance();
            /*int idAnioMes = cDAO.ObtenerIdAnioMes(dpd.getAnioDesde(), dpd.getMesDesde());
             //int cantidad = (dpd.getAnioHasta() * 12 + dpd.getMesHasta()) - (dpd.getAnioDesde() * 12 + dpd.getMesDesde());
             for (int i = 0; i < dpd.getCantidad(); i++) {
             AnioMes anioMes = new AnioMes();
             anioMes.setId(idAnioMes + i);
             cuota.setAnioMes(anioMes);
             cuota.setCliente(cliente);
             cuota.setMonto(dpd.getPrecioUnitario());
             cuota.setEstado("C");*/
            cDAO.GuardarCuota(cuota);
            /*}*/
            return true;
        } catch (Exception e) {
            System.out.println("Error en Guardar Cuota");
            return false;
        }
    }

    @Override
    public List ObtenerCuotasPendientesCliente(Cliente cliente) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (desde.getNroOrden() <= cuota.getAnioMes().getNroOrden()) {
                desde = cuota.getAnioMes();
                desde.setNroOrden(desde.getNroOrden() + 1);
            }
        }
        //--------------------------------------------------------------------------------------------------------------------
        //------------------CODIGO ADICIONAL PARA LOS 30 AÑOS DE COLEGIATURA-----------------//
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
//        AnioMes am30Anos = null;
//        if (cliente.getTipoCliente().equals("C")) {
//            AnioMes amAfiliacion = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
//            am30Anos = amBO.ObtenerAnioMesSegunNroOrden(amAfiliacion.getNroOrden() + 360);
//            if (am30Anos != null) {
//                if (am30Anos.getNroOrden() - 1 <= hasta.getNroOrden()) {
//                    List cuotasNormales = cDAO.ObtenerCuotasPendientesCliente(cliente.getIdCliente(), desde.getNroOrden(), am30Anos.getNroOrden() - 1);
//                    List cuotasVitalicias = cDAO.ObtenerCuotasPendientesClienteVitalicio(cliente.getIdCliente(), am30Anos.getNroOrden(), hasta.getNroOrden() - 1);
//                    for (Object pobj : cuotasVitalicias) {
//                        cuotasNormales.add(pobj);
//                    }
//                    return cuotasNormales;
//                }
//            }
//        }
        //-----------------------------------------------------------------------------------//
        if (Objects.equals(desde.getNroOrden(), hasta.getNroOrden())) {
            return null;
        }
        if (cliente.getTipoCliente().equals("C") && cliente.getEstado().equals("V")) {
            return cDAO.ObtenerCuotasPendientesClienteVitalicio(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
        } else {
            return cDAO.ObtenerCuotasPendientesCliente(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
        }
    }

    @Override
    public List ObtenerCuotasPendientesClienteSociedades(Cliente cliente) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getSfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getSfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        /*--------------------------------*/
        if (cliente.getSciudad().equals("A")) {
            return cDAO.ObtenerCuotasPendientesSociedad(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
        } else {
            if (cliente.getSciudad().equals("L")) {
                return cDAO.ObtenerCuotasPendientesSociedadLIMA(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
            } else {
                return null;
            }
        }
    }

    @Override
    public List ObtenerCuotasPendientesCliente_ParaFinanciar(Cliente cliente) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = 0;
        int mesAfiliacion = 0;
        if (cliente.getTipoCliente().equals("C")) {
            anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
            mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        } else {
            if (cliente.getTipoCliente().equals("S")) {
                anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getSfechaAfiliacion()));
                mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getSfechaAfiliacion()));
            }
        }
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (desde.getNroOrden() <= cuota.getAnioMes().getNroOrden()) {
                desde = cuota.getAnioMes();
                desde.setNroOrden(desde.getNroOrden() + 1);
            }
        }
        //--------------------------------------------------------------------------------------------------------------------
        //------------------CODIGO ADICIONAL PARA LOS 30 AÑOS DE COLEGIATURA-----------------//
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        AnioMes am30Anos = null;
        if (cliente.getTipoCliente().equals("C")) {
            AnioMes amAfiliacion = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
            am30Anos = amBO.ObtenerAnioMesSegunNroOrden(amAfiliacion.getNroOrden() + 360);
            if (am30Anos != null) {
                if (am30Anos.getNroOrden() - 1 <= hasta.getNroOrden()) {
                    List cuotasNormales = cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), am30Anos.getNroOrden() - 1);
                    List cuotasVitalicias = cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), am30Anos.getNroOrden(), hasta.getNroOrden() - 1);
                    for (Object pobj : cuotasVitalicias) {
                        cuotasNormales.add(pobj);
                    }
                    return cuotasNormales;
                }
            }
        }
        //-----------------------------------------------------------------------------------//
        if (Objects.equals(desde.getNroOrden(), hasta.getNroOrden())) {
            return null;
        }

        if (cliente.getTipoCliente().equals("C") && cliente.getEstado().equals("V")) {
            return cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
        } else {
            if (cliente.getTipoCliente().equals("S")) {
                if (cliente.getSciudad().equals("A")) {
                    return cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar_Sociedad(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
                } else {
                    return cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar_SociedadLIMA(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
                }
            } else {
                return cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
            }
        }

    }

    @Override
    public List ObtenerCuotasPendientesCliente_30anios(Cliente cliente) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (desde.getNroOrden() <= cuota.getAnioMes().getNroOrden()) {
                desde = cuota.getAnioMes();
                desde.setNroOrden(desde.getNroOrden() + 1);
            }
        }
        //--------------------------------------------------------------------------------------------------------------------
        //------------------CODIGO ADICIONAL PARA LOS 30 AÑOS DE COLEGIATURA-----------------//
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        AnioMes am30Anos = null;
        if (cliente.getTipoCliente().equals("C")) {
            AnioMes amAfiliacion = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
            am30Anos = amBO.ObtenerAnioMesSegunNroOrden(amAfiliacion.getNroOrden() + 360);
            if (am30Anos != null) {
                if (am30Anos.getNroOrden() - 1 <= hasta.getNroOrden()) {
                    List cuotasNormales = cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), am30Anos.getNroOrden() - 1);
                    List cuotasVitalicias = cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), am30Anos.getNroOrden(), hasta.getNroOrden() - 1);
                    for (Object pobj : cuotasVitalicias) {
                        cuotasNormales.add(pobj);
                    }
                    return cuotasNormales;
                }
            }
        }
        //-----------------------------------------------------------------------------------//
        if (Objects.equals(desde.getNroOrden(), hasta.getNroOrden())) {
            return null;
        }
        if (cliente.getTipoCliente().equals("C") && cliente.getEstado().equals("V")) {
            return cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
        } else {
            return cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() - 1);
        }
    }

    @Override
    public List ObtenerTodasCuotasCliente(Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        /*--------------------------------*/
        return cDAO.ObtenerCuotasPendientesCliente(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
    }

    @Override
    public List ObtenerTodasCuotasCliente30Anios_CajaArequipa(Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        //------------------CODIGO ADICIONAL PARA LOS 30 AÑOS DE COLEGIATURA-----------------//
        //AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        AnioMes am30Anos = null;
        if (cliente.getTipoCliente().equals("C")) {
            AnioMes amAfiliacion = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
            am30Anos = amBO.ObtenerAnioMesSegunNroOrden(amAfiliacion.getNroOrden() + 360);
            if (am30Anos != null) {
                if (am30Anos.getNroOrden() - 1 <= hasta.getNroOrden() + cantCuotas) {
                    List cuotasNormales = cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), am30Anos.getNroOrden() - 1);
                    List cuotasVitalicias = cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), am30Anos.getNroOrden(), hasta.getNroOrden() + cantCuotas);
                    for (Object pobj : cuotasVitalicias) {
                        cuotasNormales.add(pobj);
                    }
                    return cuotasNormales;
                }
            }
        }
        //-----------------------------------------------------------------------------------//
        return cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
    }

    @Override
    public List ObtenerTodasCuotasCliente30Anios_BBVA(Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        //------------------CODIGO ADICIONAL PARA LOS 30 AÑOS DE COLEGIATURA-----------------//
//        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        AnioMes am30Anos = null;
        if (cliente.getTipoCliente().equals("C")) {
            AnioMes amAfiliacion = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
            am30Anos = amBO.ObtenerAnioMesSegunNroOrden(amAfiliacion.getNroOrden() + 360);
            if (am30Anos != null) {
                if (am30Anos.getNroOrden() - 1 <= hasta.getNroOrden() + cantCuotas) {
                    List cuotasNormales = cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), am30Anos.getNroOrden() - 1);
                    List cuotasVitalicias = cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), am30Anos.getNroOrden(), hasta.getNroOrden() + cantCuotas);
                    for (Object pobj : cuotasVitalicias) {
                        cuotasNormales.add(pobj);
                    }
                    return cuotasNormales;
                }
            }
        }
        //-----------------------------------------------------------------------------------//
        return cDAO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
    }

    @Override
    public List ObtenerTodasCuotasSociedades(Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getSfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getSfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        /*--------------------------------*/
        if (cliente.getSciudad().equals("A")) {
            return cDAO.ObtenerCuotasPendientesSociedad(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
        } else {
            if (cliente.getSciudad().equals("L")) {
                return cDAO.ObtenerCuotasPendientesSociedadLIMA(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
            } else {
                return null;
            }
        }
    }

    @Override
    public List ObtenerTodasCuotasClienteVitalicio(Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        /*--------------------------------*/
        return cDAO.ObtenerCuotasPendientesClienteVitalicio(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
    }

    @Override
    public List ObtenerTodasCuotasClienteVitalicio_CajaArequipa(Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        /*--------------------------------*/
        return cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
    }

    @Override
    public List ObtenerTodasCuotasClienteVitalicio_BBVA(Cliente cliente, int cantCuotas) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        CuotasDAO cDAO = CuotasDAO.getInstance();
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        /*--------------------------------*/
        return cDAO.ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden() + cantCuotas);
    }

    @Override
    public Object[] ObtenerCuotaMesAcutal(int idCliente) {
        SeguridadBO sBO = SeguridadBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        CuotasDAO cDAO = CuotasDAO.getInstance();
        return cDAO.ObtenerCuotaMesAcutal(idCliente, anioServidor, mesServidor);
    }

    @Override
    public List ObtenerTodasCuotasCanceladas(Cliente cliente) {
        CuotasDAO cDAO = CuotasDAO.getInstance();

        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getCfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getCfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        return cDAO.ObtenerTodasCuotasCanceladas(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden());
    }

    @Override
    public List ObtenerTodasCuotasCanceladasSociedades(Cliente cliente) {
        CuotasDAO cDAO = CuotasDAO.getInstance();

        AnioMesBO amBO = AnioMesBO.getInstance();
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MM");
        int anioAfiliacion = Integer.valueOf(formatAnio.format(cliente.getSfechaAfiliacion()));
        int mesAfiliacion = Integer.valueOf(formatMes.format(cliente.getSfechaAfiliacion()));
        SeguridadBO sBO = SeguridadBO.getInstance();
        Date fechaServidor = sBO.ObtenerFechaServidor();
        int anioServidor = Integer.valueOf(formatAnio.format(fechaServidor));
        int mesServidor = Integer.valueOf(formatMes.format(fechaServidor));
        //CONSIDERO LAS CUOTAS A PARTIR DEL MES DE AFILIACION
        AnioMes desde = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
        //--------------------------------------------------------------------------------------------------------------------
        AnioMes hasta = amBO.ObtenerAnioMes(anioServidor, mesServidor);
        /*BUSCO SI TIENE PAGOS ADELANTADOS*/
        Cuotas cuota = cDAO.ObtenerUltimaCuota(cliente.getIdCliente());
        if (cuota != null) {
            if (hasta.getNroOrden() < cuota.getAnioMes().getNroOrden()) {
                hasta = cuota.getAnioMes();
            }
        }
        return cDAO.ObtenerTodasCuotasCanceladas(cliente.getIdCliente(), desde.getNroOrden(), hasta.getNroOrden());
    }

    @Override
    public List ObtenerMontoTotalFinanciados(int desde, int hasta) {
        CuotasDAO cDAO = CuotasDAO.getInstance();
        return cDAO.ObtenerMontoTotalFinanciados(desde, hasta);
    }

    @Override
    public double ObtenerDescuentoConcepto(int id) {
        CuotasDAO cDAO = CuotasDAO.getInstance();
        return cDAO.ObtenerDescuentoConcepto(id);
    }

    @Override
    public Cuotas ObtenerUltimaCuota(int id) {
        CuotasDAO cDAO = CuotasDAO.getInstance();
        return cDAO.ObtenerUltimaCuota(id);
    }

    @Override
    public Cuotas ObtenerCuota(int idCliente, int idAnioMes, int idConcepto) {
        CuotasDAO cDAO = CuotasDAO.getInstance();
        return cDAO.ObtenerCuota(idCliente, idAnioMes, idConcepto);
    }

    @Override
    public double ObtenerDescuentoConceptoUnico(int idAnioMes, int idConcepto) {
        CuotasDAO cDAO = CuotasDAO.getInstance();
        return cDAO.ObtenerDescuentoConceptoUnico(idAnioMes, idConcepto);
    }

    @Override
    public double ObtenerMontoCuotaAnioMes(int Anio, int Mes) {
        CuotasDAO cDAO = CuotasDAO.getInstance();
        return cDAO.ObtenerMontoCuotaAnioMes(Anio, Mes);
    }

}
