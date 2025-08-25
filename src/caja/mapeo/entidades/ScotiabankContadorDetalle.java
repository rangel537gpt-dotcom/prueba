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
public class ScotiabankContadorDetalle implements java.io.Serializable {

    private int id;
    private int idConcepto;
    private double monto;
    private String identificacionPago;
    private String Concepto;
    private String referenciaRecibo;
    private int correlativo;
    private int idAnioMesCuota;
    private int nroConcepto;
    private int idFinanciamientoDetalle;
    private int idReincorporacionDetalle;
    private int idDeudaDetalle;
    private int idScotiabankContador;
    private String tipoConceptoAgrupar;

    public ScotiabankContadorDetalle() {
    }

    public ScotiabankContadorDetalle(int id) {
        this.id = id;
    }

    public ScotiabankContadorDetalle(int id, int idConcepto, double monto, String tipoConceptoAgrupar, String Concepto, int idAnioMesCuota, int nroConcepto, int idFinanciamientoDetalle, int idReincorporacionDetalle, int idDeudaDetalle) {
        this.id = id;
        this.idConcepto = idConcepto;
        this.monto = monto;
        this.Concepto = Concepto;
        this.idAnioMesCuota = idAnioMesCuota;
        this.nroConcepto = nroConcepto;
        this.idFinanciamientoDetalle = idFinanciamientoDetalle;
        this.idReincorporacionDetalle = idReincorporacionDetalle;
        this.idDeudaDetalle = idDeudaDetalle;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdAnioMesCuota() {
        return idAnioMesCuota;
    }

    public void setIdAnioMesCuota(int idAnioMesCuota) {
        this.idAnioMesCuota = idAnioMesCuota;
    }

    public int getNroConcepto() {
        return nroConcepto;
    }

    public void setNroConcepto(int nroConcepto) {
        this.nroConcepto = nroConcepto;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String Concepto) {
        this.Concepto = Concepto;
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

    public int getIdDeudaDetalle() {
        return idDeudaDetalle;
    }

    public void setIdDeudaDetalle(int idDeudaDetalle) {
        this.idDeudaDetalle = idDeudaDetalle;
    }

    public String getIdentificacionPago() {
        return identificacionPago;
    }

    public void setIdentificacionPago(String identificacionPago) {
        this.identificacionPago = identificacionPago;
    }

    public String getReferenciaRecibo() {
        return referenciaRecibo;
    }

    public void setReferenciaRecibo(String referenciaRecibo) {
        this.referenciaRecibo = referenciaRecibo;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public int getIdScotiabankContador() {
        return idScotiabankContador;
    }

    public void setIdScotiabankContador(int idScotiabankContador) {
        this.idScotiabankContador = idScotiabankContador;
    }

    public String getTipoConceptoAgrupar() {
        return tipoConceptoAgrupar;
    }

    public void setTipoConceptoAgrupar(String tipoConceptoAgrupar) {
        this.tipoConceptoAgrupar = tipoConceptoAgrupar;
    }

}
