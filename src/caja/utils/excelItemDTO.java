/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caja.utils;

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

    public excelItemDTO(Object valorColumna, String tipoDato) {
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
