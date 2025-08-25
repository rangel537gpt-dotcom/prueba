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
public class EstructuraPagoCajaArequipa  implements java.io.Serializable {
    
    private int id;
    private int idOperacion;
    private int idCliente;
    private int idConcepto;
    private double monto;
    private String tipoConceptoAgrupar;
    private int idAnioMesCuota;
    private int idFinanciamientoDetalle;
    private int idReincorporacionDetalle;
    private Date fechaOperacion;
    private int nroConcepto;

    public EstructuraPagoCajaArequipa() {
    }

    public EstructuraPagoCajaArequipa(int id) {
        this.id = id;
    }

    public EstructuraPagoCajaArequipa(int id, int idOperacion, int idCliente, int idConcepto, double monto, String tipoConceptoAgrupar, int idAnioMesCuota, int idFinanciamientoDetalle, int idReincorporacionDetalle, Date fechaOperacion) {
        this.id = id;
        this.idOperacion = idOperacion;
        this.idCliente = idCliente;
        this.idConcepto = idConcepto;
        this.monto = monto;
        this.tipoConceptoAgrupar = tipoConceptoAgrupar;
        this.idAnioMesCuota = idAnioMesCuota;
        this.idFinanciamientoDetalle = idFinanciamientoDetalle;
        this.idReincorporacionDetalle = idReincorporacionDetalle;
        this.fechaOperacion = fechaOperacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoConceptoAgrupar() {
        return tipoConceptoAgrupar;
    }

    public void setTipoConceptoAgrupar(String tipoConceptoAgrupar) {
        this.tipoConceptoAgrupar = tipoConceptoAgrupar;
    }

    public int getIdAnioMesCuota() {
        return idAnioMesCuota;
    }

    public void setIdAnioMesCuota(int idAnioMesCuota) {
        this.idAnioMesCuota = idAnioMesCuota;
    }

    public int getIdFinanciamientoDetalle() {
        return idFinanciamientoDetalle;
    }

    public void setIdFinanciamientoDetalle(int idFinanciamientoDetalle) {
        this.idFinanciamientoDetalle = idFinanciamientoDetalle;
    }

    public int getIdReincorporacionDetalle() {
        return idReincorporacionDetalle;
    }

    public void setIdReincorporacionDetalle(int idReincorporacionDetalle) {
        this.idReincorporacionDetalle = idReincorporacionDetalle;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public int getNroConcepto() {
        return nroConcepto;
    }

    public void setNroConcepto(int nroConcepto) {
        this.nroConcepto = nroConcepto;
    }

    
    
    
    
}
