/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

/**
 *
 * @author user
 */
public class excelItemDTO {

    private String nombreColumna;
    private Object valorColumna;
    private String tipoDato;

    public excelItemDTO() {
    }

    public excelItemDTO(String nombreColumna, Object valorColumna, String tipoDato) {
        this.nombreColumna = nombreColumna;
        this.valorColumna = valorColumna;
        this.tipoDato = tipoDato;
    }

    public String getNombreColumna() {
        return nombreColumna;
    }

    public void setNombreColumna(String nombreColumna) {
        this.nombreColumna = nombreColumna;
    }

    public Object getValorColumna() {
        return valorColumna;
    }

    public void setValorColumna(Object valorColumna) {
        this.valorColumna = valorColumna;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

}
