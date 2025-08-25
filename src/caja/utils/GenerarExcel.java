/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caja.utils;

import java.util.List;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import org.apache.commons.lang3.time.StopWatch;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author user
 */
public class GenerarExcel {

    private static void crearCabecera(HashMap<String, Object> datos, List<String> lista) {
        Workbook libro = (Workbook) datos.get("libro");
        String nombreHoja = (String) datos.get("nombreHoja");
        Sheet hoja = (Sheet) libro.createSheet(nombreHoja);
        Row fila = hoja.createRow((short) 0);
        int contador = 0;
        for (String campo : lista) {
            Cell celda = fila.createCell((short) contador);
            celda.setCellValue(campo);
            contador = contador + 1;
        }
        datos.put("hoja", hoja);
    }

    private static int obtenerColumna(Workbook wb, String nombreColumna) {
        return 0;
    }

    private static void llenarDatos(HashMap<String, Object> datos, int contador,
            HashMap<String, excelItemDTO> detalle) {
        try {
            Workbook libro = (Workbook) datos.get("libro");
            Sheet hoja = (Sheet) datos.get("hoja");
            Row fila = hoja.createRow((Integer) contador);
            for (String nombreColumna : detalle.keySet()) {
                excelItemDTO item = detalle.get(nombreColumna);
                int columna = obtenerColumna(libro, nombreColumna);
                if (columna >= 0) {
                    Cell celda = fila.createCell((Integer) columna);
                    if (item != null) {
                        if (item.getTipoDato().equals("S")) {
                            celda.setCellValue((String) item.getValorColumna());
                        } else {
                            if (item.getTipoDato().equals("I")) {
                                celda.setCellValue((Integer) item.getValorColumna());
                            } else {
                                if (item.getTipoDato().equals("D")) {
                                    celda.setCellValue((Double) item.getValorColumna());
                                } else {
                                    if (item.getTipoDato().equals("B")) {
                                        celda.setCellValue((Boolean) item.getValorColumna());
                                    } else {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void generarCelda(HashMap<String, excelItemDTO> fila, String columna, Object valor, String tipo) {
        excelItemDTO item = new excelItemDTO(columna, valor, tipo);
        fila.put(columna, item);
//		mapPadre.put(String.valueOf(mapPadre.size()), map);
    }

//    public static Response generarExcel(String nombreHoja, List<String> cabecera, HashMap<String, Object> detalle) {
//        try {
//            HashMap<String, Object> datos = new HashMap<String, Object>();
//            HashMap<String, excelItemDTO> subDetalle = new HashMap<String, excelItemDTO>();
//            datos.put("nombreHoja", nombreHoja);
//            Workbook libro = new HSSFWorkbook();
//            datos.put("libro", libro);
//            crearCabecera(datos, cabecera);
//            for (int i = 0; i < detalle.keySet().size(); i++) {
//                System.out.println(i);
//                if (i == 99) {
//                    boolean a = true;
//                }
//                subDetalle = (HashMap<String, excelItemDTO>) detalle.get(String.valueOf(i));
//                llenarDatos(datos, i + 1, subDetalle);
//            }
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            libro.write(bos);
//            byte[] excel = bos.toByteArray();
//            libro.close();
//            return Respuesta.okFile(excel, "archivo");
//        } catch (IOException e) {
//            System.out.println(e);
//            return null;
//        }
//    }


    public static byte[] generarExcelFast(HashMap<String, Object> filas) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            org.dhatim.fastexcel.Workbook wb = new org.dhatim.fastexcel.Workbook(bos, "DemoExcel", "1.0");
            org.dhatim.fastexcel.Worksheet ws = wb.newWorksheet("Sheet 1");
            StopWatch watch = new StopWatch();
            watch.start();
            for (int i = 0; i < filas.keySet().size(); i++) {
                HashMap<String, excelItemDTO> celdas = (HashMap<String, excelItemDTO>) filas.get(String.valueOf(i));
                for (int j = 0; j < celdas.keySet().size(); j++) {
                    excelItemDTO item = celdas.get(String.valueOf(j));
                    if (item.getTipoDato().equals("S")) {
                        ws.value(i, j, String.valueOf(item.getValorColumna()));
                    } else {
                        if (item.getTipoDato().equals("N")) {
                            ws.value(i, j, (Number) item.getValorColumna());
                        } else {
                            if (item.getTipoDato().equals("B")) {
                                ws.value(i, j, (Boolean) item.getValorColumna());
                            } else {

                            }
                        }
                    }
                }
            }
            wb.finish();
            watch.stop();
            byte[] excel = bos.toByteArray();
            return excel;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
