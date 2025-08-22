/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.DocumentoPagoDAO;
import caja.dao.MigrarDAO;
import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import caja.mapeo.entidades.Cuotas;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.DocumentoPagoDet;
import caja.mapeo.entidades.TipoDocSerie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class MigrarBO implements MigrarBOIFace {

    private static MigrarBO INSTANCE = new MigrarBO();

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (MigrarBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MigrarBO();
                }
            }
        }
    }

    public static MigrarBO getInstance() {
        createInstance();
        return INSTANCE;
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

    @Override
    public void MigrarDataCuotasContador() {
        MigrarDAO mDAO = MigrarDAO.getInstance();
        List<Object> lBaseAnterior = mDAO.ObtenerDatosBaseAnterior();
        List<Cliente> lCliente = mDAO.ObtenerClientes();
        CuotasBO cBO = CuotasBO.getInstance();
        Cliente cliente = null;
        for (Object pobj : lBaseAnterior) {
            Object[] obj = (Object[]) pobj;
            Date fechaAfiliacion = (Date) obj[7];
            String dni = (String) obj[6];
            if (dni.equals("5346335")) {
                System.out.println("DNI: " + dni);
                for (Cliente cli : lCliente) {
                    if (cli.getTipoCliente().equals("C")) {
                        if (cli.getPnroDocumento().equals(dni)) {
                            cliente = cli;
                            break;
                        }
                    }
                }
                SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
                SimpleDateFormat formatMes = new SimpleDateFormat("MM");
                int anioAfiliacion = Integer.valueOf(formatAnio.format(fechaAfiliacion));
                int mesAfiliacion = Integer.valueOf(formatMes.format(fechaAfiliacion));
                /*int mesCuota = (int) ((double) obj[0]);
                 int anioCuota = (int) ((double) obj[1]);
                 int mesFondoClub = (int) ((double) obj[2]);
                 int anioFondoClub = (int) ((double) obj[3]);
                 int mesFondoMutual = (int) ((double) obj[4]);
                 int anioFondoMutual = (int) ((double) obj[5]);*/
                int mesCuota = (int) ((short) obj[0]);
                int anioCuota = (int) ((short) obj[1]);
                int mesFondoClub = (int) ((short) obj[2]);
                int anioFondoClub = (int) ((short) obj[3]);
                int mesFondoMutual = (int) ((short) obj[4]);
                int anioFondoMutual = (int) ((short) obj[5]);
                AnioMesBO amBO = AnioMesBO.getInstance();
                AnioMes amAfiliacion = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
                if (mesCuota > 0) {
                    AnioMes amCuota = amBO.ObtenerAnioMes(anioCuota, mesCuota);
                    for (int i = amAfiliacion.getNroOrden(); i < amCuota.getNroOrden(); i++) {
                        AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(i);
                        double monto = this.ObtenerMontoConceptoDetallado(am.getId(), 2);
                        if (monto > 0) {
                            Cuotas cuota = new Cuotas();
                            ConceptoPagoDetalle concepto = new ConceptoPagoDetalle();
                            concepto.setIdConceptoPagoDetalle(2);
                            cuota.setConceptoPagoDetalle(concepto);
                            cuota.setAnioMes(am);
                            cuota.setCliente(cliente);
                            cuota.setEstado("C");
                            cuota.setMonto(monto);
                            cBO.GuardarCuota(cuota);
                        }
                    }
                }
                AnioMes amFondoClub = amBO.ObtenerAnioMes(anioFondoClub, mesFondoClub);
                for (int i = amAfiliacion.getNroOrden(); i < amFondoClub.getNroOrden(); i++) {
                    AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(i);
                    double monto = this.ObtenerMontoConceptoDetallado(am.getId(), 4);
                    if (monto > 0) {
                        Cuotas cuota = new Cuotas();
                        ConceptoPagoDetalle concepto = new ConceptoPagoDetalle();
                        concepto.setIdConceptoPagoDetalle(4);
                        cuota.setConceptoPagoDetalle(concepto);
                        cuota.setAnioMes(am);
                        cuota.setCliente(cliente);
                        cuota.setEstado("C");
                        cuota.setMonto(monto);
                        cBO.GuardarCuota(cuota);
                    }
                }
                AnioMes amFondoMutual = amBO.ObtenerAnioMes(anioFondoMutual, mesFondoMutual);
                for (int i = amAfiliacion.getNroOrden(); i < amFondoMutual.getNroOrden(); i++) {
                    AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(i);
                    double monto = this.ObtenerMontoConceptoDetallado(am.getId(), 3);
                    if (monto > 0) {
                        Cuotas cuota = new Cuotas();
                        ConceptoPagoDetalle concepto = new ConceptoPagoDetalle();
                        concepto.setIdConceptoPagoDetalle(3);
                        cuota.setConceptoPagoDetalle(concepto);
                        cuota.setAnioMes(am);
                        cuota.setCliente(cliente);
                        cuota.setEstado("C");
                        cuota.setMonto(monto);
                        cBO.GuardarCuota(cuota);
                    }
                }
            }
        }
    }

    @Override
    public void MigrarDataCuotasSociedades() {
        MigrarDAO mDAO = MigrarDAO.getInstance();
        List<Object> lBaseAnterior = mDAO.ObtenerDatosBaseAnteriorSociedades();
        List<Cliente> lCliente = mDAO.ObtenerClientes();
        CuotasBO cBO = CuotasBO.getInstance();
        Cliente cliente = null;
        for (Object pobj : lBaseAnterior) {
            Object[] obj = (Object[]) pobj;
            Date fechaAfiliacion = (Date) obj[4];
            int id = (int) obj[5];
            String codSoc = (String) obj[3];
            String ciudad = (String) obj[2];
            //if (id < 1164) {
            if (!codSoc.equals("29298608")) {
                System.out.println("---------------------------------------ID:" + id + " DNI: " + codSoc + " --------------------------------");
                for (Cliente cli : lCliente) {
                    if (cli.getTipoCliente().equals("S")) {
                        if (cli.getScodigoSoc().equals(codSoc)) {
                            cliente = cli;
                            break;
                        }
                    }
                }
                SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
                SimpleDateFormat formatMes = new SimpleDateFormat("MM");
                int anioAfiliacion = Integer.valueOf(formatAnio.format(fechaAfiliacion));
                int mesAfiliacion = Integer.valueOf(formatMes.format(fechaAfiliacion));
                /*int mesCuota = (int) ((double) obj[0]);
                 int anioCuota = (int) ((double) obj[1]);
                 int mesFondoClub = (int) ((double) obj[2]);
                 int anioFondoClub = (int) ((double) obj[3]);
                 int mesFondoMutual = (int) ((double) obj[4]);
                 int anioFondoMutual = (int) ((double) obj[5]);*/
                int mesCuota = (int) ((short) obj[0]);
                int anioCuota = (int) ((short) obj[1]);
                AnioMesBO amBO = AnioMesBO.getInstance();
                AnioMes amAfiliacion = amBO.ObtenerAnioMes(anioAfiliacion, mesAfiliacion);
                if (mesCuota > 0) {
                    AnioMes amCuota = amBO.ObtenerAnioMes(anioCuota, mesCuota);
                    for (int i = amAfiliacion.getNroOrden(); i < amCuota.getNroOrden(); i++) {
                        AnioMes am = amBO.ObtenerAnioMesSegunNroOrden(i);
                        double monto = this.ObtenerMontoConceptoDetallado(am.getId(), 2);
                        if (monto > 0) {
                            Cuotas cuota = new Cuotas();
                            ConceptoPagoDetalle concepto = new ConceptoPagoDetalle();
                            concepto.setIdConceptoPagoDetalle(2);
                            cuota.setConceptoPagoDetalle(concepto);
                            cuota.setAnioMes(am);
                            cuota.setCliente(cliente);
                            cuota.setEstado("C");
                            if (ciudad.equals("A")) {
                                cuota.setMonto(2 * monto);
                            } else {
                                cuota.setMonto(4 * monto);
                            }
                            cBO.GuardarCuota(cuota);
                        }
                    }
                }
            }
            //}
        }
    }

    @Override
    public void MigrarDataCabecera() {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        MigrarDAO mDAO = MigrarDAO.getInstance();
        List<Object> lCabeceraAnterior = mDAO.ObtenerCabeceraBaseAnterior();
        List<Cliente> lCliente = mDAO.ObtenerClientes();
        Cliente cliente = null;
        int contador = 1;
        for (Object pobj : lCabeceraAnterior) {
            Object[] obj = (Object[]) pobj;
            //obj[0] TIPO DOCUMENTO VARCHAR
            //obj[1] SERIE DOCUMENTO VARCHAR
            //obj[2] NRO DOCUMENTO VARCHAR
            //obj[3] FECHA DOCUMENTO DATETIME
            //obj[4] TIPO CLIENTE VARCHAR
            //obj[5] COD CLIENTE VARCHAR
            //obj[6] NOMBRE CLIENTE VARCHAR
            //obj[7] MONTO IGV DOUBLE
            //obj[8] ID
            try {
                Date fecha = (Date) obj[3];
                String fechaComparar = "11/10/2015";
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaCMP = formateador.parse(fechaComparar);
                System.out.println("REGISTRO NRO: " + contador + " ID:" + obj[8] + " TIPO DOC:" + obj[0] + " SERIE:" + obj[1] + " NRODOC:" + obj[2] + " TIPCLI:" + obj[4] + " CODCLI:" + obj[5] + " NOM CLI:" + obj[6]);
                //if (contador >= 0) {
                if (fecha.after(fechaCMP)) {
                    DocumentoPago doc = new DocumentoPago();
                    int idTipoDoc = 0;
                    if (((String) obj[0]).equals("RI")) {
                        idTipoDoc = 3;
                    } else {
                        if (Integer.valueOf((String) obj[0]) == 3) {
                            idTipoDoc = 2;
                        } else {
                            if (Integer.valueOf((String) obj[0]) == 1) {
                                idTipoDoc = 1;
                            }
                        }
                    }
                    TipoDocSerie tds = dpBO.ObtenerTipoDocSerie(idTipoDoc, (String) obj[1]);
                    doc.setNroDocumentoPago(Integer.valueOf((String) obj[2]));
                    doc.setTipoDocSerie(tds);
                    doc.setNombreCliente((String) obj[6]);
                    doc.setFechaPago((Date) obj[3]);
//                    doc.setNroSerie(Integer.valueOf((String) obj[1]));
                    doc.setTieneIgv("N");
                    double igv = 0;
                    if (obj[7] == null) {
                        igv = 0;
                    } else {
                        igv = (double) obj[7];
                    }
                    if (igv > 0) {
                        doc.setTieneIgv("S");
                    }
                    doc.setEstado("C");
                    if (obj[5] != null) {
                        if (((String) obj[4]).equals("T")) {
                            String tipoSocio = ((String) obj[5]).substring(0, 1);
                            if (tipoSocio.equals("0")) {
                                for (Cliente cli : lCliente) {
                                    if (cli.getTipoCliente().equals("C")) {
                                        int codContador = Integer.valueOf((String) obj[5]);
                                        if (Integer.valueOf(cli.getCcodigoCole()) == codContador) {
                                            cliente = cli;
                                            break;
                                        }
                                    }
                                }
                            } else {
                                String codigoSociedad = ((String) obj[5]).substring(3, 11);
                                for (Cliente cli : lCliente) {
                                    if (cli.getTipoCliente().equals("S")) {
                                        int codSociedad = Integer.valueOf(codigoSociedad);
                                        if (Integer.valueOf(cli.getScodigoSoc()) == codSociedad) {
                                            cliente = cli;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (((String) obj[4]).equals("R")) {
                                for (Cliente cli : lCliente) {
                                    if (cli.getTipoCliente().equals("E")) {
                                        if (cli.getEruc().equals((String) obj[5])) {
                                            cliente = cli;
                                            break;
                                        }
                                    }
                                }
                            } else {
                                for (Cliente cli : lCliente) {
                                    if (cli.getTipoCliente().equals("P")) {
                                        if (cli.getPnroDocumento().equals((String) obj[5])) {
                                            cliente = cli;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        doc.setCliente(cliente);
                    }
                    DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
                    dpDAO.GuardarDocumentoPago(doc);
                }
                contador = contador + 1;
            } catch (ParseException e) {
                System.out.println("Se Produjo un Error!!!  " + e.getMessage());
            }
        }
    }

    @Override
    public void MigrarDataDetalle() {
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        MigrarDAO mDAO = MigrarDAO.getInstance();
        List<Object> lDetalleAnterior = mDAO.ObtenerDetalleBaseAnterior();
        List<Cliente> lCliente = mDAO.ObtenerClientes();
        List<ConceptoPagoDetalle> lConceptos = dpBO.ObtenerTodosConceptoPago();
        Cliente cliente = null;
        int contador = 1;
        for (Object pobj : lDetalleAnterior) {
            Object[] obj = (Object[]) pobj;
            //obj[0] TIPO DOCUMENTO VARCHAR
            //obj[1] SERIE DOCUMENTO VARCHAR
            //obj[2] NRO DOCUMENTO VARCHAR
            //obj[3] FECHA DOCUMENTO DATETIME
            //obj[4] COD DETALLE VARCHAR
            //obj[5] DESC DETALLE VARCHAR
            //obj[6] CANT DETALLE DOUBLE
            //obj[7] VALOR TOTAL DETALLE DOUBLE
            //obj[8] ID
            //obj[9] IGV
            try {
                Date fecha = (Date) obj[3];
                String fechaComparar = "11/10/2015";
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaCMP = formateador.parse(fechaComparar);
                System.out.println("REGISTRO NRO: " + contador + " ID:" + (int) obj[8] + " TIPO DOC:" + obj[0] + " SERIE:" + obj[1] + " NRODOC:" + obj[2] + " CODDET:" + obj[4] + " DESCDET:" + obj[5] + " CANTDET:" + obj[6] + " VALORDET:" + obj[7]);
                //if ((int) obj[8] >= 413001) {
                if (fecha.after(fechaCMP) && (int) obj[8] > 454732 && obj[4] != null) {
                    int idTipoDoc = 0;
                    if (((String) obj[0]).equals("RI")) {
                        idTipoDoc = 3;
                    } else {
                        if (Integer.valueOf((String) obj[0]) == 3) {
                            idTipoDoc = 2;
                        } else {
                            if (Integer.valueOf((String) obj[0]) == 1) {
                                idTipoDoc = 1;
                            }
                        }
                    }
                    if (contador == 21) {
                        boolean observacion = true;
                    }
                    int nroDoc = Integer.valueOf((String) obj[2]);
                    TipoDocSerie tds = dpBO.ObtenerTipoDocSerie(idTipoDoc, (String) obj[1]);
                    DocumentoPago doc = mDAO.ObtenerCabecera(tds.getId(), nroDoc, (Date) obj[3]);
                    if (doc != null) {
                        DocumentoPagoDet det = new DocumentoPagoDet();
                        det.setDocumentoPago(doc);
                        if (obj[6] == null) {
                            det.setCantidad(1);
                        } else {
                            det.setCantidad((int) ((double) obj[6]));
                        }

                        double precioUnitario = 0;
                        if (obj[7] == null) {
                            precioUnitario = 1;
                        } else {
                            precioUnitario = Math.round((double) obj[7] * Math.pow(10, 2)) / Math.pow(10, 2);
                        }

                        det.setPrecioUnitario(precioUnitario);
                        det.setValorVenta(precioUnitario);
                        det.setPrecioVenta(0.0);
                        det.setIgv(0.0);
                        if (obj[9] != null) {
                            double Igv = Math.round((double) obj[9] * Math.pow(10, 2)) / Math.pow(10, 2);
                            det.setIgv(Igv);
                            double PrecioVenta = Math.round((precioUnitario + Igv) * Math.pow(10, 2)) / Math.pow(10, 2);
                            det.setPrecioVenta(PrecioVenta);
                        }
                        det.setObservacion((String) obj[5]);
                        ConceptoPagoDetalle concepto = null;
                        if (obj[4] != null) {
                            for (ConceptoPagoDetalle c : lConceptos) {
                                String codigo = c.getTipoCodigo() + c.getCodigo();
                                if (codigo.equals((String) obj[4])) {
                                    concepto = c;
                                    break;
                                }
                            }
                        }
                        det.setConceptoPagoDetalle(concepto);
                        DocumentoPagoDAO dpDAO = DocumentoPagoDAO.getInstance();
                        dpDAO.GuardarDocumentoPagoDet(det);
                    }
                }
                contador = contador + 1;
            } catch (ParseException e) {
                System.out.println("Se Produjo un Error!!!  " + e.getMessage());
            }
        }
    }

    @Override
    public void LlenarDatosCierreCaja() {
        MigrarDAO mDAO;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2002, 11, 01);
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DATE);
        String fecha = "";
        while (anio < 2021) {
            mDAO = MigrarDAO.getInstance();
            fecha = Integer.toString(calendar.get(Calendar.YEAR)) + String.format("%02d", calendar.get(Calendar.MONTH) + 1) + String.format("%02d", calendar.get(Calendar.DATE));
            mDAO.InsertarFecha(fecha);
            calendar.add(Calendar.DATE, 1);
            System.out.println(Integer.toString(calendar.get(Calendar.YEAR)) + String.format("%02d", calendar.get(Calendar.MONTH)) + String.format("%02d", calendar.get(Calendar.DATE)));
        }
    }

}
