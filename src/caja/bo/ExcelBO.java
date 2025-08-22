/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author user
 */
public class ExcelBO implements ExcelBOIFace {

    private static ExcelBO INSTANCE = new ExcelBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (PersonaBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ExcelBO();
                }
            }
        }
    }

    public static ExcelBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public ExcelBO() {
    }

    public HashMap crearArchivo(HashMap<String, Object> datos) {
        FileOutputStream archivo = null;
        String nombreCarpeta = (String) datos.get("nombreCarpeta");
        try {
            File carpeta = new File("archivos");
            carpeta.mkdirs();
            carpeta = new File("archivos/exportar");
            carpeta.mkdirs();
            carpeta = new File("archivos/exportar/" + nombreCarpeta);
            carpeta.mkdirs();
            String ruta = "archivos/exportar/" + nombreCarpeta;
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            String nombreArchivo = ruta + "/" + nombreCarpeta + "_" + formato.format(fechaSistema) + ".xls";
            archivo = new FileOutputStream(nombreArchivo);
            datos.put("archivo", archivo);
            datos.put("nombreArchivo", nombreArchivo);
            return datos;
        } catch (Exception ex) {
            Logger.getLogger(ExcelBO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void crearCabecera(HashMap<String, Object> datos, List<String> lista) {
        Workbook libro = (Workbook) datos.get("libro");
        String nombreHoja = (String) datos.get("nombreHoja");
        Sheet hoja = libro.createSheet(nombreHoja);
        Row fila = hoja.createRow((short) 0);
        int contador = 0;
        for (String campo : lista) {
            Cell celda = fila.createCell((short) contador);
            celda.setCellValue(campo);
            contador = contador + 1;
        }
        datos.put("hoja", hoja);
    }

    private int obtenerColumna(Workbook wb, String nombreColumna) {
        Sheet s = wb.getSheetAt(0);
        Row r = s.getRow(0);

        int patchColumn = -1;
        for (int cn = 0; cn < r.getLastCellNum(); cn++) {
            Cell c = r.getCell(cn);
            if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
                continue;
            }
            if (c.getCellType() == Cell.CELL_TYPE_STRING) {
                String text = c.getStringCellValue();
                if (nombreColumna.equals(text)) {
                    patchColumn = cn;
                    break;
                }
            }
        }
        return patchColumn;
    }

    public void llenarDatos(HashMap<String, Object> datos, int contador, HashMap<String, excelItemDTO> detalle) {
        Workbook libro = (Workbook) datos.get("libro");
        Sheet hoja = (Sheet) datos.get("hoja");
        Row fila = hoja.createRow((short) contador);
        for (String nombreColumna : detalle.keySet()) {
            excelItemDTO item = detalle.get(nombreColumna);
            int columna = this.obtenerColumna(libro, nombreColumna);
            if (columna >= 0) {
                Cell celda = fila.createCell((short) columna);
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
                            }
                        }
                    }
                }
            }
        }
    }
//
//    public void llenarDatos(Workbook libro, Sheet hoja, int contador, String nombreColumna, String dato) {
//        int columna = this.obtenerColumna(libro, nombreColumna);
//        if (columna >= 0) {
//            Row fila = hoja.createRow((short) contador);
//            Cell celda = fila.createCell((short) columna);
//            if (dato != null) {
//                celda.setCellValue(dato);
//            }
//        }
//    }

    @Override
    public void generarExcel(String nombreCarpeta, String nombreHoja, List<String> cabecera, HashMap<String, Object> detalle) {
        try {
            HashMap<String, Object> datos = new HashMap<String, Object>();
            HashMap<String, excelItemDTO> subDetalle = new HashMap<String, excelItemDTO>();
            datos.put("nombreCarpeta", nombreCarpeta);
            datos.put("nombreHoja", nombreHoja);
            datos = this.crearArchivo(datos);
            FileOutputStream archivo = (FileOutputStream) datos.get("archivo");
            if (archivo != null) {
                Workbook libro = new HSSFWorkbook();
                datos.put("libro", libro);
                this.crearCabecera(datos, cabecera);
//                int contador = 0;
                for (int i = 0; i < detalle.keySet().size(); i++) {
                    subDetalle = (HashMap<String, excelItemDTO>) detalle.get(String.valueOf(i));
                    this.llenarDatos(datos, i + 1, subDetalle);
//                    contador++;
                }
                libro.write(archivo);
                archivo.close();
                try {
                    File objFile = new File((String) datos.get("nombreArchivo"));
                    Desktop.getDesktop().open(objFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "SE GENERÓ EL ARCHIVO", "OK", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

