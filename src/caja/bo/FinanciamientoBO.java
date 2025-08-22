/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.ClienteDAO;
import caja.dao.CuotasDAO;
import caja.dao.DocumentoPagoDAO;
import caja.dao.FinanciamientoDAO;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.mapeo.entidades.CuentaCorriente;
import caja.mapeo.entidades.Cuotas;
import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import caja.mapeo.entidades.TipoAfectacion;
import caja.mapeo.entidades.TipoDocPago;
import caja.mapeo.entidades.TipoDocSerie;
import caja.mapeo.entidades.TipoTributo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class FinanciamientoBO implements FinanciamientoBOIFace {

    private double deudaAnterior;
    private double montoDeuda;
    private String rangoFechaDeuda;
    private int anioDesde;
    private int mesDesde;
    private int anioHasta;
    private int mesHasta;
    private List cuotasAFinanciar;
    private double montoSIFinanciado;
    private double montoNOFinanciado;
    private double montoFinanPendiente;
    private List<Object> listaMontosFinanciados;
    private List<FinanciamientoDocumentoPago> listaFinanciamientoPendiente;
    private List<Deuda> listaDeudaPendiente;
    private static FinanciamientoBO INSTANCE = new FinanciamientoBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FinanciamientoBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FinanciamientoBO();
                }
            }
        }

    }

    public static FinanciamientoBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public double getDeudaAnterior() {
        return deudaAnterior;
    }

    public void setDeudaAnterior(double deudaAnterior) {
        this.deudaAnterior = deudaAnterior;
    }

    public double getMontoSIFinanciado() {
        return montoSIFinanciado;
    }

    public double getMontoNOFinanciado() {
        return montoNOFinanciado;
    }

    public String getRangoFechaDeuda() {
        return rangoFechaDeuda;
    }

    public void setRangoFechaDeuda(String rangoFechaDeuda) {
        this.rangoFechaDeuda = rangoFechaDeuda;
    }

    public double getMontoDeuda() {
        return montoDeuda;
    }

    public void setMontoDeuda(double montoDeuda) {
        this.montoDeuda = montoDeuda;
    }

    public double getMontoFinanPendiente() {
        return montoFinanPendiente;
    }

    public void setMontoFinanPendiente(double montoFinanPendiente) {
        this.montoFinanPendiente = montoFinanPendiente;
    }

    private String ObtenerMes(int mes) {
        String pmes = "";
        if (mes == 1) {
            pmes = "ENE";
        }
        if (mes == 2) {
            pmes = "FEB";
        }
        if (mes == 3) {
            pmes = "MAR";
        }
        if (mes == 4) {
            pmes = "ABR";
        }
        if (mes == 5) {
            pmes = "MAY";
        }
        if (mes == 6) {
            pmes = "JUN";
        }
        if (mes == 7) {
            pmes = "JUL";
        }
        if (mes == 8) {
            pmes = "AGO";
        }
        if (mes == 9) {
            pmes = "SEP";
        }
        if (mes == 10) {
            pmes = "OCT";
        }
        if (mes == 11) {
            pmes = "NOV";
        }
        if (mes == 12) {
            pmes = "DIC";
        }
        return pmes;
    }

    /*public double ObtenerMontoCuotasPendientes(Cliente cliente) {
     double monto;
     CuotasBO cBO = CuotasBO.getInstance();
     AnioMesBO amBO = AnioMesBO.getInstance();
     List<Object> listaCuotasPendientes = cBO.ObtenerCuotasPendientesCliente(cliente);
     List<Object> listaFinal = new ArrayList();
     Object[] objFinal;
     double totalMeses = 0;
     double cuotaMes = 0;
     for (Object pobj : listaCuotasPendientes) {
     Object[] obj = (Object[]) pobj;
     cuotaMes = amBO.ObtenerAnioMes((int) obj[2], (int) obj[3]).getMontoCuota();
     if (obj[4] == null) {
     objFinal = new Object[6];
     objFinal = obj;
     totalMeses = totalMeses + cuotaMes;
     listaFinal.add(objFinal);
     } else {
     monto = (double) obj[4];
     if (monto < cuotaMes) {
     objFinal = new Object[6];
     objFinal = obj;
     totalMeses = totalMeses + monto;
     listaFinal.add(objFinal);
     }
     }
     }
     Object[] financiamientoDesde = (Object[]) listaFinal.get(0);
     Object[] financiamientoHasta = (Object[]) listaFinal.get(listaFinal.size() - 1);
     this.anioDesde = (int) financiamientoDesde[2];
     this.mesDesde = (int) financiamientoDesde[3];
     this.anioHasta = (int) financiamientoHasta[2];
     this.mesHasta = (int) financiamientoHasta[3];
     this.rangoFechaDeuda = this.ObtenerMes(this.mesDesde) + " " + this.anioDesde + " A " + this.ObtenerMes(this.mesHasta) + " " + this.anioHasta;
     this.cuotasAFinanciar = listaFinal;
     return totalMeses;
     }*/
    private boolean ExisteElementoListaMontosFinanciado(List listado, int id) {
        boolean existe = false;
        for (Object pobj : listado) {
            Object[] obj = (Object[]) pobj;
            int idConcepto = (int) obj[2];
//            String esFinanciado = (String)obj[7];
            if (idConcepto == id) {
                existe = true;
            }
        }
        return existe;
    }

    private void AgregarMonto_ListaMontosFinanciado(List listado, int id, double monto) {
//        boolean existe = false;
        for (Object pobj : listado) {
            Object[] obj = (Object[]) pobj;
            double montoTotal = (double) obj[0];
            int idConcepto = (int) obj[2];
//            String esFinanciado = (String)obj[7];
            if (idConcepto == id) {
                montoTotal = montoTotal + monto;
                obj[0] = montoTotal;
                break;
            }
        }
//        return existe;
    }

    private void ObtenerMontosFinanciados_NoFinanciados(List listaCuotasPendientes) {
        this.listaMontosFinanciados = new ArrayList();
        Object[] objMontoFinanciado;
        for (Object pobj : listaCuotasPendientes) {
            Object[] obj = (Object[]) pobj;
            int idConcepto = (int) obj[6];
            String esFinanciado = (String) obj[7];
            boolean existe = ExisteElementoListaMontosFinanciado(this.listaMontosFinanciados, idConcepto);
            if (!existe) {
                objMontoFinanciado = new Object[3];
                objMontoFinanciado[0] = 0.0;
                objMontoFinanciado[1] = esFinanciado;
                objMontoFinanciado[2] = idConcepto;
                this.listaMontosFinanciados.add(objMontoFinanciado);
            }
        }
        for (Object pobj : listaCuotasPendientes) {
            Object[] obj = (Object[]) pobj;
            int idConcepto = (int) obj[6];
            double monto = (double) obj[5];
            if (((String) obj[7]).equals("N")) {
                this.montoNOFinanciado = this.montoNOFinanciado + (double) obj[5];
            } else {
                this.montoSIFinanciado = this.montoSIFinanciado + (double) obj[5];
            }
            this.AgregarMonto_ListaMontosFinanciado(this.listaMontosFinanciados, idConcepto, monto);
        }
    }

    public void ObtenerMontoCuotasPendientes(Cliente cliente) {
        CuotasBO cBO = CuotasBO.getInstance();
        List<Object> listaCuotasPendientes = cBO.ObtenerCuotasPendientesCliente_ParaFinanciar(cliente);
        if (listaCuotasPendientes != null && listaCuotasPendientes.size() > 0) {
            Object[] financiamientoDesde = (Object[]) listaCuotasPendientes.get(0);
            Object[] financiamientoHasta = (Object[]) listaCuotasPendientes.get(listaCuotasPendientes.size() - 1);
            this.ObtenerMontosFinanciados_NoFinanciados(listaCuotasPendientes);
            this.anioDesde = (int) financiamientoDesde[2];
            this.mesDesde = (int) financiamientoDesde[3];
            this.anioHasta = (int) financiamientoHasta[2];
            this.mesHasta = (int) financiamientoHasta[3];
            this.rangoFechaDeuda = this.ObtenerMes(this.mesDesde) + " " + this.anioDesde + " A " + this.ObtenerMes(this.mesHasta) + " " + this.anioHasta;
            this.cuotasAFinanciar = listaCuotasPendientes;
        } else {
            this.montoNOFinanciado = 0;
            this.montoSIFinanciado = 0;
            this.anioDesde = 0;
            this.mesDesde = 0;
            this.anioHasta = 0;
            this.mesHasta = 0;
            this.rangoFechaDeuda = "ESTA AL DIA";
            this.cuotasAFinanciar = null;
        }
    }

    public void ObtenerMontosFinanciadosAnteriormente(Cliente cli) {
        double montoFinanciamientoPendiente = 0;
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        this.listaFinanciamientoPendiente = fDAO.ObtenerTodosFinanciamientosPendientes(cli.getIdCliente());
        for (FinanciamientoDocumentoPago fdp : listaFinanciamientoPendiente) {
            montoFinanciamientoPendiente = montoFinanciamientoPendiente + fdp.getMonto();
        }
        this.montoFinanPendiente = montoFinanciamientoPendiente;
        this.montoSIFinanciado = this.montoSIFinanciado + montoFinanciamientoPendiente;
    }

    public void ObtenerMontoDeudasPendientes(Cliente cli) {
        double montoDeudaPendiente = 0;
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        this.listaDeudaPendiente = fDAO.ObtenerTodasDeudasPendientes(cli.getIdCliente());
        for (Deuda d : this.listaDeudaPendiente) {
            montoDeudaPendiente = montoDeudaPendiente + d.getMonto();
        }
        this.montoSIFinanciado = this.montoSIFinanciado + montoDeudaPendiente;
    }

    @Override
    public void ObtenerDeudaTotal(Cliente cli) {
        //FinanciamientoDAO fdao = FinanciamientoDAO.getInstance();
        this.montoSIFinanciado = 0;
        this.montoNOFinanciado = 0;
        this.ObtenerMontosFinanciadosAnteriormente(cli);
        this.ObtenerMontoCuotasPendientes(cli);
        this.ObtenerMontoDeudasPendientes(cli);
    }

    public double ObtenerMontoConceptoDetallado(int idAnioMes, int idConcepto) {
        AnioMesBO amBO = AnioMesBO.getInstance();
        Object objMonto = amBO.ObtenerMontoConceptoDetallado(idAnioMes, idConcepto);
        if (objMonto == null) {
            return 0;
        } else {
            return (double) objMonto;
        }
    }

    private int ObtenerNroOrdenDesdeListadoCuotasAFinanciar(List listado) {
        int contador = 0;
        int nroDesde = 0;
        for (Object pobj : listado) {
            Object[] obj = (Object[]) pobj;
            int nroOrden = (int) obj[0];
            if (contador == 0) {
                nroDesde = nroOrden;
            } else {
                if (nroDesde > nroOrden) {
                    nroDesde = nroOrden;
                }
            }
            contador = contador + 1;
        }
        return nroDesde;
    }

    private int ObtenerNroOrdenHastaListadoCuotasAFinanciar(List listado) {
        int nroHasta = 0;
        for (Object pobj : listado) {
            Object[] obj = (Object[]) pobj;
            int nroOrden = (int) obj[0];
            if (nroHasta < nroOrden) {
                nroHasta = nroOrden;
            }
        }
        return nroHasta;
    }

    private double ObtenerMontoListadoCuotasAFinanciar_SegunAnioMes(List listado, int id, int a, int m) {
        double montoTotal = 0;
        for (Object pobj : listado) {
            Object[] obj = (Object[]) pobj;
            int anio = (int) obj[2];
            int mes = (int) obj[3];
            double monto = (double) obj[5];
            int idConcepto = (int) obj[6];
            if (anio == a && mes == m && idConcepto == id) {
                montoTotal = montoTotal + monto;
            }
        }
        return montoTotal;
    }

    public boolean GenerarDocumentoPago(DocumentoPago doc, Financiamiento financiamiento, DocumentoPagoDet dpDetalle/*, int idTipoDoc, int nroSerie*/) {
        try {
            SeguridadBO sBO = SeguridadBO.getInstance();
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            Cliente cliente = (Cliente) sBO.CargarObjeto("Cliente", financiamiento.getCliente().getIdCliente());
            doc.setCliente(cliente);
            doc.setTieneIgv("N");
            doc.setEstado("C");
            doc.setMoneda("S");
            doc.setTipoPago("CON");
            doc.setFormaPagoSunat("Contado");
            Date ahora = new Date();
            doc.setFechaVencimientoSunat(ahora);
            if (financiamiento.getCliente().getTipoCliente().equals("C")) {
                doc.setNombreCliente(financiamiento.getCliente().getPapePat() + " " + financiamiento.getCliente().getPapeMat() + " " + financiamiento.getCliente().getPnombre());
            } else {
                doc.setNombreCliente(financiamiento.getCliente().getSnombreSociedad());
            }
            doc.setFechaSunat(sBO.ObtenerFechaHoraServidor());
            dpBO.GuardarDocumentoPago_CualquierNro(doc);
            ConceptoPagoDetalle cPagoDetalle = (ConceptoPagoDetalle) sBO.CargarObjeto("ConceptoPagoDetalle", 5);
            dpDetalle.setDocumentoPago(doc);
            dpDetalle.setCantidad(1);
            dpDetalle.setPrecioUnitario(financiamiento.getPrimerPago());
            dpDetalle.setIgv(0.0);
            dpDetalle.setValorVenta(financiamiento.getPrimerPago());
            dpDetalle.setPrecioVenta(financiamiento.getPrimerPago());
            dpDetalle.setConceptoPagoDetalle(cPagoDetalle);
            if (cPagoDetalle != null) {
                TipoAfectacion ta = (TipoAfectacion) sBO.CargarObjeto("TipoAfectacion", cPagoDetalle.getTipoAfectacion().getId());
                cPagoDetalle.setTipoAfectacion(ta);
                TipoTributo tt = (TipoTributo) sBO.CargarObjeto("TipoTributo", cPagoDetalle.getTipoAfectacion().getTipoTributo().getId());
                cPagoDetalle.getTipoAfectacion().setTipoTributo(tt);
                dpDetalle.setCodigoTipoTributo(cPagoDetalle.getTipoAfectacion().getTipoTributo().getCodigo());
                dpDetalle.setCodigoInternacionalTipoTributo(cPagoDetalle.getTipoAfectacion().getTipoTributo().getCodigoInternacional());
                dpDetalle.setNombreTipoTributo(cPagoDetalle.getTipoAfectacion().getTipoTributo().getNombre());
                dpDetalle.setCodigoTipoAfectacion(cPagoDetalle.getTipoAfectacion().getCodigo());
            }
            if (cPagoDetalle.getPorcentajeIgv() != null) {
                dpDetalle.setIgvPorcentaje(cPagoDetalle.getPorcentajeIgv());
            } else {
                dpDetalle.setIgvPorcentaje(0.0);
            }
            DocumentoPagoDAO pDAO = DocumentoPagoDAO.getInstance();
            pDAO.GuardarDocumentoPagoDet(dpDetalle);
            /*INSERTANDO CUENTAS CORRIENTES*/
            CuentaCorriente cuentaCorriente = new CuentaCorriente();
            cuentaCorriente.setCliente(cliente);
            cuentaCorriente.setDocumentoPagoDet(dpDetalle);
            ClienteBO cliBO = ClienteBO.getInstance();
            cliBO.InsertarCuentaCorrienteCliente(cuentaCorriente);
            /*-----------------------------*/
            CuotasDAO cDAO = CuotasDAO.getInstance();
            for (Object pobj : this.listaMontosFinanciados) {
                Object[] obj = (Object[]) pobj;
                if (((String) obj[1]).equals("N")) {
                    DocumentoPagoDet dpd = new DocumentoPagoDet();
//                    cPagoDetalle = new ConceptoPagoDetalle();
//                    cPagoDetalle.setIdConceptoPagoDetalle((int) obj[2]);
                    cPagoDetalle = (ConceptoPagoDetalle) sBO.CargarObjeto("ConceptoPagoDetalle", (int) obj[2]);
                    dpd = new DocumentoPagoDet();
                    dpd.setDocumentoPago(doc);
                    dpd.setCantidad(1);
                    dpd.setPrecioUnitario((double) obj[0]);
                    dpd.setIgv(0.0);
                    dpd.setValorVenta((double) obj[0]);
                    dpd.setPrecioVenta((double) obj[0]);
                    dpd.setConceptoPagoDetalle(cPagoDetalle);
                    if (cPagoDetalle != null) {
                        TipoAfectacion ta = (TipoAfectacion) sBO.CargarObjeto("TipoAfectacion", cPagoDetalle.getTipoAfectacion().getId());
                        cPagoDetalle.setTipoAfectacion(ta);
                        TipoTributo tt = (TipoTributo) sBO.CargarObjeto("TipoTributo", cPagoDetalle.getTipoAfectacion().getTipoTributo().getId());
                        cPagoDetalle.getTipoAfectacion().setTipoTributo(tt);
                        dpd.setCodigoTipoTributo(cPagoDetalle.getTipoAfectacion().getTipoTributo().getCodigo());
                        dpd.setCodigoInternacionalTipoTributo(cPagoDetalle.getTipoAfectacion().getTipoTributo().getCodigoInternacional());
                        dpd.setNombreTipoTributo(cPagoDetalle.getTipoAfectacion().getTipoTributo().getNombre());
                        dpd.setCodigoTipoAfectacion(cPagoDetalle.getTipoAfectacion().getCodigo());
                    }
                    if (cPagoDetalle.getPorcentajeIgv() != null) {
                        dpd.setIgvPorcentaje(cPagoDetalle.getPorcentajeIgv());
                    } else {
                        dpd.setIgvPorcentaje(0.0);
                    }
                    pDAO.GuardarDocumentoPagoDet(dpd);
                    int desde = ObtenerNroOrdenDesdeListadoCuotasAFinanciar(this.cuotasAFinanciar);
                    int hasta = ObtenerNroOrdenHastaListadoCuotasAFinanciar(this.cuotasAFinanciar);
                    for (int i = desde; i <= hasta; i++) {
                        AnioMesBO amBO = AnioMesBO.getInstance();
                        AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(i);
//                        AnioMes am = amBO.ObtenerAnioMes((int) objCuotaFinanciada[2], (int) objCuotaFinanciada[3]);
                        double montoConcepto = this.ObtenerMontoListadoCuotasAFinanciar_SegunAnioMes(this.cuotasAFinanciar, (int) obj[2], am.getAnio(), am.getMes());
                        if (montoConcepto > 0) {
                            //--------------INGRESO CUOTAS-------------------
                            Cuotas cuota = new Cuotas();
                            cuota.setDocumentoPagoDet(dpd);
                            cuota.setConceptoPagoDetalle(dpd.getConceptoPagoDetalle());
                            cuota.setAnioMes(am);
                            cuota.setCliente(financiamiento.getCliente());
                            cuota.setConceptoPagoDetalle(new ConceptoPagoDetalle((int) obj[2], ""));
                            cuota.setMonto(montoConcepto);
                            cuota.setEstado("C");
                            cDAO.GuardarCuota(cuota);
                        }
                    }
//                    for (Object pobjCuotaFinanciada : this.cuotasAFinanciar) {
//                        AnioMesBO amBO = AnioMesBO.getInstance();
//                        Object[] objCuotaFinanciada = (Object[]) pobjCuotaFinanciada;
//                        AnioMes am = amBO.ObtenerAnioMes((int) objCuotaFinanciada[2], (int) objCuotaFinanciada[3]);
//                        double montoConcepto = this.ObtenerMontoConceptoDetallado(am.getId(), (int) obj[2]);
//                        if (montoConcepto > 0) {
//                            //--------------INGRESO CUOTAS-------------------
//                            Cuotas cuota = new Cuotas();
//                            cuota.setDocumentoPagoDet(dpd);
//                            cuota.setConceptoPagoDetalle(dpd.getConceptoPagoDetalle());
//                            cuota.setAnioMes(am);
//                            cuota.setCliente(financiamiento.getCliente());
//                            cuota.setConceptoPagoDetalle(new ConceptoPagoDetalle((int) obj[2], ""));
//                            cuota.setMonto(montoConcepto);
//                            cuota.setEstado("C");
//                            cDAO.GuardarCuota(cuota);
//                        }
//                    }
                } else {
                    int desde = ObtenerNroOrdenDesdeListadoCuotasAFinanciar(this.cuotasAFinanciar);
                    int hasta = ObtenerNroOrdenHastaListadoCuotasAFinanciar(this.cuotasAFinanciar);
                    for (int i = desde; i <= hasta; i++) {
                        AnioMesBO amBO = AnioMesBO.getInstance();
                        AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(i);
//                        double montoConcepto = this.ObtenerMontoConceptoDetallado(am.getId(), (int) obj[2]);
                        double montoConcepto = this.ObtenerMontoListadoCuotasAFinanciar_SegunAnioMes(this.cuotasAFinanciar, (int) obj[2], am.getAnio(), am.getMes());
                        if (montoConcepto > 0) {
                            //--------------INGRESO CUOTAS-------------------
                            Cuotas cuota = new Cuotas();
                            cuota.setDocumentoPagoDet(dpDetalle);
                            cuota.setAnioMes(am);
                            cuota.setCliente(financiamiento.getCliente());
                            cuota.setConceptoPagoDetalle(new ConceptoPagoDetalle((int) obj[2], ""));
                            cuota.setMonto(montoConcepto);
                            cuota.setEstado("C");
                            cDAO.GuardarCuota(cuota);
                        }
                    }
//                    for (Object pobjCuotaFinanciada : this.cuotasAFinanciar) {
//                        AnioMesBO amBO = AnioMesBO.getInstance();
//                        Object[] objCuotaFinanciada = (Object[]) pobjCuotaFinanciada;
//                        AnioMes am = amBO.ObtenerAnioMes((int) objCuotaFinanciada[2], (int) objCuotaFinanciada[3]);
////                        double montoConcepto = this.ObtenerMontoConceptoDetallado(am.getId(), (int) obj[2]);
//                        double montoConcepto = this.ObtenerMontoListadoCuotasAFinanciar_SegunAnioMes(this.cuotasAFinanciar, (int) obj[2], (int) objCuotaFinanciada[2], (int) objCuotaFinanciada[3]);
//                        if (montoConcepto > 0) {
//                            //--------------INGRESO CUOTAS-------------------
//                            Cuotas cuota = new Cuotas();
//                            cuota.setDocumentoPagoDet(dpDetalle);
//                            cuota.setAnioMes(am);
//                            cuota.setCliente(financiamiento.getCliente());
//                            cuota.setConceptoPagoDetalle(new ConceptoPagoDetalle((int) obj[2], ""));
//                            cuota.setMonto(montoConcepto);
//                            cuota.setEstado("C");
//                            cDAO.GuardarCuota(cuota);
//                        }
//                    }
                }
            }

            /*CuotasDAO cDAO = CuotasDAO.getInstance();
             for (Object pobj : this.cuotasAFinanciar) {
             Object[] obj = (Object[]) pobj;
             AnioMes am = amBO.ObtenerAnioMes((int) obj[2], (int) obj[3]);
             //--------------INGRESO CUOTAS-------------------
             Cuotas cuota = new Cuotas();
             cuota.setDocumentoPagoDet(dpDetalle);
             cuota.setAnioMes(am);
             cuota.setCliente(financiamiento.getCliente());
             cuota.setMonto(am.getMontoCuota());
             cuota.setEstado("C");
             cDAO.GuardarCuota(cuota);
             }*/
            this.cuotasAFinanciar = null;

            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en el Ingreso del Financiamiento");
            return false;
        }
    }

    /*public boolean GuardarFinanciamientoDetalle(Financiamiento financiamiento, DocumentoPagoDet dpDetalle) {
     try {
     AnioMesBO amBO = AnioMesBO.getInstance();
     double montoInicial = financiamiento.getPrimerPago();
     double montoCadaCuota = financiamiento.getMontoCadaCuota();
     FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
     CuotasDAO cDAO = CuotasDAO.getInstance();
     List<FinanciamientoDocumentoPago> listaCuotasFinanciadas = new ArrayList();
     for (Object pobj : this.cuotasAFinanciar) {
     Object[] obj = (Object[]) pobj;
     AnioMes am = amBO.ObtenerAnioMes((int) obj[2], (int) obj[3]);
     if (montoInicial > 0) {
     if (am.getMontoCuota() <= montoInicial) {
     FinanciamientoDocumentoPago fdp = new FinanciamientoDocumentoPago();
     fdp.setFinanciamiento(financiamiento);
     fdp.setAnioMes(am);
     fdp.setMonto(am.getMontoCuota());
     fdp.setEstado("C");
     fdp.setDocumentoPagoDet(dpDetalle);
     fdp.setNroCuota(0);
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     //listaCuotasFinanciadas.add(fdp);
     montoInicial = montoInicial - am.getMontoCuota();
     //--------------INGRESO CUOTAS-------------------
     Cuotas cuota = new Cuotas();
     cuota.setDocumentoPagoDet(dpDetalle);
     cuota.setAnioMes(am);
     cuota.setCliente(financiamiento.getCliente());
     cuota.setMonto(am.getMontoCuota());
     cuota.setEstado("C");
     cDAO.GuardarCuota(cuota);
     } else {
     FinanciamientoDocumentoPago fdp = new FinanciamientoDocumentoPago();
     fdp.setFinanciamiento(financiamiento);
     fdp.setAnioMes(am);
     fdp.setMonto(montoInicial);
     fdp.setEstado("C");
     fdp.setDocumentoPagoDet(dpDetalle);
     fdp.setNroCuota(0);
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     //listaCuotasFinanciadas.add(fdp);
     //--------------INGRESO CUOTAS-------------------
     Cuotas cuota = new Cuotas();
     cuota.setDocumentoPagoDet(dpDetalle);
     cuota.setAnioMes(am);
     cuota.setCliente(financiamiento.getCliente());
     cuota.setMonto(montoInicial);
     cuota.setEstado("C");
     cDAO.GuardarCuota(cuota);
     //------------------------------------------------
     fdp = new FinanciamientoDocumentoPago();
     fdp.setFinanciamiento(financiamiento);
     fdp.setAnioMes(am);
     fdp.setMonto(am.getMontoCuota() - montoInicial);
     fdp.setEstado("F");
     fdp.setMontoCuota(financiamiento.getMontoCadaCuota());
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     listaCuotasFinanciadas.add(fdp);
     montoInicial = 0;
     montoCadaCuota = montoCadaCuota - fdp.getMonto();
     }
     } else {
     if (montoCadaCuota == 0) {
     montoCadaCuota = financiamiento.getMontoCadaCuota();
     }
     if (montoCadaCuota > 0) {
     if (am.getMontoCuota() <= montoCadaCuota) {
     FinanciamientoDocumentoPago fdp = new FinanciamientoDocumentoPago();
     fdp.setFinanciamiento(financiamiento);
     fdp.setAnioMes(am);
     fdp.setMonto(am.getMontoCuota());
     fdp.setEstado("F");
     fdp.setMontoCuota(financiamiento.getMontoCadaCuota());
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     listaCuotasFinanciadas.add(fdp);
     montoCadaCuota = montoCadaCuota - am.getMontoCuota();
     } else {
     FinanciamientoDocumentoPago fdp = new FinanciamientoDocumentoPago();
     fdp.setFinanciamiento(financiamiento);
     fdp.setAnioMes(am);
     fdp.setMonto(montoCadaCuota);
     fdp.setEstado("F");
     fdp.setMontoCuota(financiamiento.getMontoCadaCuota());
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     listaCuotasFinanciadas.add(fdp);
     //-------------------------------------------------
     fdp = new FinanciamientoDocumentoPago();
     fdp.setFinanciamiento(financiamiento);
     fdp.setAnioMes(am);
     fdp.setEstado("F");
     fdp.setMontoCuota(financiamiento.getMontoCadaCuota());
     if ((am.getMontoCuota() - montoCadaCuota) <= financiamiento.getMontoCadaCuota()) {
     fdp.setMonto(am.getMontoCuota() - montoCadaCuota);
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     montoCadaCuota = financiamiento.getMontoCadaCuota() - fdp.getMonto();
     listaCuotasFinanciadas.add(fdp);
     } else {
     fdp.setMonto(financiamiento.getMontoCadaCuota());
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     listaCuotasFinanciadas.add(fdp);
     montoCadaCuota = am.getMontoCuota() - financiamiento.getMontoCadaCuota() - montoCadaCuota;
     fdp = new FinanciamientoDocumentoPago();
     fdp.setFinanciamiento(financiamiento);
     fdp.setAnioMes(am);
     fdp.setEstado("F");
     fdp.setMontoCuota(financiamiento.getMontoCadaCuota());
     fdp.setMonto(montoCadaCuota);
     fDAO.GuardarFinanciamientoDocumentoPago(fdp);
     listaCuotasFinanciadas.add(fdp);
     montoCadaCuota = financiamiento.getMontoCadaCuota() - montoCadaCuota;
     }
     }
     }
     }
     }
     int nroCuota = 1;
     double montoFinanciamiento = 0;
     SeguridadBO sBO = SeguridadBO.getInstance();
     for (FinanciamientoDocumentoPago f : listaCuotasFinanciadas) {
     if (montoFinanciamiento == financiamiento.getMontoCadaCuota()) {
     nroCuota = nroCuota + 1;
     montoFinanciamiento = 0;
     }
     f.setNroCuota(nroCuota);
     f.setFechaVencimiento(sBO.SumaMesesFechaServidor(nroCuota));
     fDAO.ActualizarFinanciamientoDocumentoPago(f);
     montoFinanciamiento = montoFinanciamiento + f.getMonto();
     }
     return true;
     } catch (Exception e) {
     //e.printStackTrace();
     System.out.println("Error en el Ingreso del Financiamiento");
     return false;
     }
     }*/
    public boolean GuardarDeudaDetalle(DocumentoPagoDet dpDetalle) {
        try {
            FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
            for (Deuda d : this.listaDeudaPendiente) {
                d.setDocumentoPagoDet(dpDetalle);
                d.setMontoCancelado(d.getMonto());
                d.setEstado("DF");
                fDAO.ActualizarDeudaPendiente(d);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error en el Ingreso del Financiamiento");
            return false;
        }
    }

    public boolean GuardarFinanciamientoDetalle(Financiamiento financiamiento, DocumentoPagoDet dpDetalle, int diasProrroga) {
        try {
            //AnioMesBO amBO = AnioMesBO.getInstance();
            FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
            /*CuotasDAO cDAO = CuotasDAO.getInstance();
             for (Object pobj : this.cuotasAFinanciar) {
             Object[] obj = (Object[]) pobj;
             AnioMes am = amBO.ObtenerAnioMes((int) obj[2], (int) obj[3]);
             //--------------INGRESO CUOTAS-------------------
             Cuotas cuota = new Cuotas();
             cuota.setDocumentoPagoDet(dpDetalle);
             cuota.setAnioMes(am);
             cuota.setCliente(financiamiento.getCliente());
             cuota.setMonto(am.getMontoCuota());
             cuota.setEstado("C");
             cDAO.GuardarCuota(cuota);
             }
             this.cuotasAFinanciar = null;*/
            SeguridadBO sBO = SeguridadBO.getInstance();
            FinanciamientoDocumentoPago fdp;
            fdp = new FinanciamientoDocumentoPago();
            fdp.setFinanciamiento(financiamiento);
            fdp.setEstado("CI");
            fdp.setDocumentoPagoDet(dpDetalle);
            fdp.setMonto(dpDetalle.getCantidad() * dpDetalle.getPrecioUnitario());
            fdp.setNroCuota(0);
            fDAO.GuardarFinanciamientoDocumentoPago(fdp);

            for (int i = 1; i <= financiamiento.getNroCuotas(); i++) {
                fdp = new FinanciamientoDocumentoPago();
                fdp.setFinanciamiento(financiamiento);
                fdp.setEstado("FS");
                //fdp.setMontoCuota(financiamiento.getMontoCadaCuota());
                fdp.setMonto(financiamiento.getMontoCadaCuota());
                fdp.setNroCuota(i);
                fdp.setFechaVencimiento(sBO.SumaMesesFechaServidor(i));
                fdp.setDiasProrroga(diasProrroga);
                if (i == financiamiento.getNroCuotas()) {
                    fdp.setMonto(fdp.getMonto() + financiamiento.getMontoSobrante());
                }
                fDAO.GuardarFinanciamientoDocumentoPago(fdp);
            }
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en el Ingreso del Financiamiento");
            return false;
        }
    }

    @Override
    public boolean GuardarFinanciamiento(DocumentoPago doc, Financiamiento financiamiento, int idTipoDoc, String nroSerie, int diasProrroga) {
        try {
            boolean procedimientoExitoso = true;
            FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
            financiamiento.setAnioDesde(this.anioDesde);
            financiamiento.setMesDesde(this.mesDesde);
            financiamiento.setAnioHasta(this.anioHasta);
            financiamiento.setMesHasta(this.mesHasta);
            int nroFinanciamiento = fDAO.ObtenerNroFinanciamiento(financiamiento.getCliente().getIdCliente());
            nroFinanciamiento = nroFinanciamiento + 1;
            financiamiento.setNroFinanciamiento(nroFinanciamiento);
            fDAO.GuardarFinanciamiento(financiamiento);
            DocumentoPagoDet dpDetalle = new DocumentoPagoDet();
            procedimientoExitoso = this.GenerarDocumentoPago(doc, financiamiento, dpDetalle/*, idTipoDoc, nroSerie*/);
            procedimientoExitoso = this.GuardarFinanciamientoDetalle(financiamiento, dpDetalle, diasProrroga);
            procedimientoExitoso = this.GuardarDeudaDetalle(dpDetalle);
            /*ASIGNAMOS EL ID DEL FINANCIAMIENTO A LAS FINANCIAMIENTOS Y DEUDAS CANCELADAS*/
            fDAO.CancelarDeudas(financiamiento.getCliente().getIdCliente(), financiamiento.getIdFinanciamiento());
            if (financiamiento.getIdFinanciamiento() > 1) {
                fDAO.CancelarFinanciamientos(financiamiento.getCliente().getIdCliente(), financiamiento.getIdFinanciamiento(), financiamiento.getNroFinanciamiento() - 1);
            }
            /*----------------------------------------------------------------------------*/
            ClienteDAO cDAO = ClienteDAO.getInstance();
            procedimientoExitoso = cDAO.HabilitarCliente(financiamiento.getCliente().getIdCliente()); //AQUI ACTUALIZO EN LA BASE DE DATOS Y NO EN LA LISTA ENLAZADA
            DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
            dpBO.HabilitarCliente(financiamiento.getCliente().getIdCliente());    // AQUI SOLAMENTE HABILITO A LA LISTA ENLAZADA MAS NO A LA BASE DE DATOS
            this.deudaAnterior = 0;
            this.montoDeuda = 0;
            this.montoNOFinanciado = 0;
            this.montoSIFinanciado = 0;
            return procedimientoExitoso;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en el Ingreso del Financiamiento");
            return false;
        }
    }

    @Override
    public List ObtenerTodosFinanciamientoActivosCliente(int idCliente) {
        try {
            FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
            return fDAO.ObtenerTodosFinanciamientoActivosCliente(idCliente);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error en el Ingreso del Financiamiento");
            return null;
        }
    }

    @Override
    public void ActualizarFinanciamiento(FinanciamientoDocumentoPago fdp) {
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        fDAO.ActualizarFinanciamiento(fdp);
    }

    @Override
    public void ActualizarReincorporacion(ReincorporacionDocumentoPago rdp) {
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        fDAO.ActualizarReincorporacion(rdp);
    }

    @Override
    public List ObtenerTodosFinanciamientos(int idCliente) {
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        return fDAO.ObtenerTodosFinanciamientos(idCliente);
    }

    @Override
    public List ObtenerTodosFinanciamientosTodosContadores() {
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        return fDAO.ObtenerTodosFinanciamientosTodosContadores();
    }

    @Override
    public List ObtenerDetalleFinanciamiento(int idFinanciamiento) {
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        return fDAO.ObtenerDetalleFinanciamiento(idFinanciamiento);
    }

    @Override
    public List ObtenerTodosFinanciamientosPendientes(int idCliente) {
        FinanciamientoDAO fDAO = FinanciamientoDAO.getInstance();
        return fDAO.ObtenerTodosFinanciamientosPendientes(idCliente);
    }

}
