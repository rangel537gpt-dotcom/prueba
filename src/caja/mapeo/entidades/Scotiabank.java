/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.mapeo.entidades;

import java.util.Date;

/**
 *
 * @author user
 */
public class Scotiabank implements java.io.Serializable {

    private int id;
    private int nroOperacion;
    private int nroOrdenDesde;
    private int nroOrdenHasta;
    private Date fechaOperacion;
    private Date fechaGeneracion;
//    private String tipoIdentificacion;
//    private Date fechaOperacion;
//    private int nroConcepto;

    public Scotiabank() {
    }

    public Scotiabank(int id) {
        this.id = id;
    }

    public Scotiabank(int id, int nroOperacion, int nroOrdenDesde, int nroOrdenHasta, Date fechaOperacion) {
        this.id = id;
        this.nroOperacion = nroOperacion;
        this.nroOrdenDesde = nroOrdenDesde;
        this.nroOrdenHasta = nroOrdenHasta;
        this.fechaOperacion = fechaOperacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroOrdenDesde() {
        return nroOrdenDesde;
    }

    public void setNroOrdenDesde(int nroOrdenDesde) {
        this.nroOrdenDesde = nroOrdenDesde;
    }

    public int getNroOrdenHasta() {
        return nroOrdenHasta;
    }

    public void setNroOrdenHasta(int nroOrdenHasta) {
        this.nroOrdenHasta = nroOrdenHasta;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public int getNroOperacion() {
        return nroOperacion;
    }

    public void setNroOperacion(int nroOperacion) {
        this.nroOperacion = nroOperacion;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    

}
