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
public class excelCabeceraDTO {

    private String nombreCabecera;
    private String tipoDato;

    public excelCabeceraDTO() {
    }

    public excelCabeceraDTO(String nombreCabecera, String tipoDato) {
        this.nombreCabecera = nombreCabecera;
        this.tipoDato = tipoDato;
    }

    public String getNombreCabecera() {
        return nombreCabecera;
    }

    public void setNombreCabecera(String nombreCabecera) {
        this.nombreCabecera = nombreCabecera;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

}
