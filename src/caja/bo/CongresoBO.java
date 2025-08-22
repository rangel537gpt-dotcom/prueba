/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.CongresoDAO;
import caja.mapeo.entidades.Congreso;
import caja.mapeo.entidades.CongresoConcepto;
import caja.mapeo.entidades.CongresoParticipantes;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author juan carlos
 */
public class CongresoBO implements CongresoBOIFace {

    private static CongresoBO INSTANCE = new CongresoBO();

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (CongresoBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CongresoBO();
                }
            }
        }

    }

    public static CongresoBO getInstance() {
        createInstance();
        return INSTANCE;
    }
    //private Object SectorDAO;

    @Override
    public List BuscarCongreso(String busqueda, int tipoBusqueda) {
        CongresoDAO gaDAO = CongresoDAO.getInstance();
        if (tipoBusqueda == 1) {
            gaDAO.BuscarCongresoNOMBRE(busqueda);
        } else {
            gaDAO.BuscarCongreso(busqueda);
        }
        return null;
    }

    @Override
    public boolean GuardarCongreso(Congreso gae) {
        try {
            CongresoDAO gaDAO = CongresoDAO.getInstance();
            gaDAO.GuardarCongreso(gae);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarCongresoConcepto(CongresoConcepto cc) {
        try {
            CongresoDAO gaDAO = CongresoDAO.getInstance();
            gaDAO.GuardarCongresoConcepto(cc);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean GuardarCongresoParticipantes(CongresoParticipantes cp) {
        try {
            CongresoDAO gaDAO = CongresoDAO.getInstance();
            gaDAO.GuardarCongresoParticipantes(cp);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ActualizarCongresoParticipantes(CongresoParticipantes cp) {
        try {
            CongresoDAO gaDAO = CongresoDAO.getInstance();
            gaDAO.ActualizarCongresoParticipantes(cp);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ActualizarCongresoConcepto(CongresoConcepto cc) {
        try {
            CongresoDAO gaDAO = CongresoDAO.getInstance();
            gaDAO.ActualizarCongresoConcepto(cc);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ActualizarCongreso(Congreso o) {
        try {
            CongresoDAO gaDAO = CongresoDAO.getInstance();
            gaDAO.ActualizarCongreso(o);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List ObtenerTodasCongresos() {
        CongresoDAO gaDAO = CongresoDAO.getInstance();
        return gaDAO.ObtenerTodasCongresos();
    }

    @Override
    public List ObtenerTodosParticipantesCongreso(int idCongreso) {
        CongresoDAO gaDAO = CongresoDAO.getInstance();
        return gaDAO.ObtenerTodosParticipantesCongreso(idCongreso);
    }

    @Override
    public List ObtenerTodosParticipantesCongresos(int idCongreso) {
        CongresoDAO gaDAO = CongresoDAO.getInstance();
        return gaDAO.ObtenerTodosParticipantesCongresos(idCongreso);
    }

    @Override
    public List ObtenerTodosCongresoConcepto() {
        CongresoDAO gaDAO = CongresoDAO.getInstance();
        return gaDAO.ObtenerTodosCongresoConcepto();
    }

    @Override
    public List BuscarCongreso(String dependencia) {
        CongresoDAO gaDAO = CongresoDAO.getInstance();
        return gaDAO.BuscarCongreso(dependencia);
    }

    @Override
    public List VerificarSiConceptoPerteneceCongreso(int idConcepto) {
        CongresoDAO gaDAO = CongresoDAO.getInstance();
        return gaDAO.VerificarSiConceptoPerteneceCongreso(idConcepto);
    }

    @Override
    public void ExportarArchivoExcelParticipantesCongreso(int idCongreso) {
        try {
            CongresoDAO cDAO = CongresoDAO.getInstance();
            List<CongresoParticipantes> listaConceptos = cDAO.ObtenerTodosParticipantesCongreso(idCongreso);
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Workbook libro = new HSSFWorkbook();
            FileOutputStream archivo = new FileOutputStream("archivos/CONGRESO/PARTICIPANTES_" + formato.format(fechaSistema) + ".xls");
            Sheet hoja = libro.createSheet("PARTICIPANTES");
            Row fila = hoja.createRow((short) 0);
            Cell celda = fila.createCell((short) 0);
            celda.setCellValue("NRO");
            celda = fila.createCell((short) 1);
            celda.setCellValue("DNI");
            celda = fila.createCell((short) 2);
            celda.setCellValue("APE PAT");
            celda = fila.createCell((short) 3);
            celda.setCellValue("APE MAT");
            celda = fila.createCell((short) 4);
            celda.setCellValue("NOMBRES");
            celda = fila.createCell((short) 5);
            celda.setCellValue("TIPO");
            celda = fila.createCell((short) 6);
            celda.setCellValue("CODIGO");
            celda = fila.createCell((short) 7);
            celda.setCellValue("NOMBRE CODIGO");
            celda = fila.createCell((short) 8);
            celda.setCellValue("NRO CMP");
            celda = fila.createCell((short) 9);
            celda.setCellValue("FEC CMP");
            celda = fila.createCell((short) 10);
            celda.setCellValue("NRO OP");
            celda = fila.createCell((short) 11);
            celda.setCellValue("FEC OP");
            celda = fila.createCell((short) 12);
            celda.setCellValue("MATERIAL");
            celda = fila.createCell((short) 13);
            celda.setCellValue("CPC/CPCC");
            celda = fila.createCell((short) 14);
            celda.setCellValue("MONTO");
            /*celda = fila.createCell((short) 6);
             celda.setCellValue("SIS. PEN.");*/
            int contador = 1;
            for (CongresoParticipantes p : listaConceptos) {
                fila = hoja.createRow((short) contador);
                celda = fila.createCell((short) 0);
                celda.setCellValue(contador);
                celda = fila.createCell((short) 1);
                celda.setCellValue(p.getCliente().getPnroDocumento());
                celda = fila.createCell((short) 2);
                celda.setCellValue(p.getCliente().getPapePat());
                celda = fila.createCell((short) 3);
                celda.setCellValue(p.getCliente().getPapeMat());
                celda = fila.createCell((short) 4);
                celda.setCellValue(p.getCliente().getPnombre());
                celda = fila.createCell((short) 5);
                if (p.getTipoParticipante() == 1) {
                    celda.setCellValue("ORDINARIO");
                }
                if (p.getTipoParticipante() == 2) {
                    celda.setCellValue("OBSERVADOR");
                }
                if (p.getTipoParticipante() == 3) {
                    celda.setCellValue("ACOMPAÑANTE");
                }
                if (p.getTipoParticipante() == 4) {
                    celda.setCellValue("ESTUDIANTE");
                }
                celda = fila.createCell((short) 6);
                celda.setCellValue(p.getDocumentoPagoDet().getConceptoPagoDetalle().getTipoCodigo() + p.getDocumentoPagoDet().getConceptoPagoDetalle().getCodigo());
                celda = fila.createCell((short) 7);
                celda.setCellValue(p.getDocumentoPagoDet().getConceptoPagoDetalle().getDescripcion());
                celda = fila.createCell((short) 8);
                celda.setCellValue(p.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getTipoDocPago().getNombreDocPago() + " " + p.getDocumentoPagoDet().getDocumentoPago().getTipoDocSerie().getSerie().getSerie() + "-" + p.getDocumentoPagoDet().getDocumentoPago().getNroDocumentoPago());
                celda = fila.createCell((short) 9);
                celda.setCellValue(formatoFecha.format(p.getDocumentoPagoDet().getDocumentoPago().getFechaPago()));
                celda = fila.createCell((short) 10);
                if (p.getDocumentoPagoDet().getDocumentoPago().getNroOperacion() != null) {
                    celda.setCellValue(p.getDocumentoPagoDet().getDocumentoPago().getNroOperacion());
                }
                celda = fila.createCell((short) 11);
                if (p.getDocumentoPagoDet().getDocumentoPago().getFechaOperacion() != null) {
                    celda.setCellValue(formatoFecha.format(p.getDocumentoPagoDet().getDocumentoPago().getFechaOperacion()));
                }
                celda = fila.createCell((short) 12);
                if (p.getEntregoMaterial().equals("S")) {
                    celda.setCellValue("SI");
                } else {
                    celda.setCellValue("NO");
                }
                if (p.getCliente().getCesCertificado() != null) {
                    celda = fila.createCell((short) 13);
                    celda.setCellValue(p.getCliente().getCesCertificado());
                }
                celda = fila.createCell((short) 14);
                celda.setCellValue(p.getDocumentoPagoDet().getValorVenta() + p.getDocumentoPagoDet().getIgv());
                contador++;
            }
            libro.write(archivo);
            archivo.close();
            JOptionPane.showMessageDialog(null,
                    "SE GENERÓ EL ARCHIVO",
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
