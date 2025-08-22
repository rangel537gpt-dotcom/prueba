/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public interface ExcelBOIFace {

    public void generarExcel(String nombreCarpeta, String nombreHoja, List<String> cabecera, HashMap<String, Object> detalle);

}
