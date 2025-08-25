/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.mapeo.entidades;

/**
 *
 * @author user
 */
public class BbvaContadorDetalle implements java.io.Serializable {

    private int id;
    private int idConcepto;
    private double monto;
    private String tipoConceptoAgrupar;
    private String identificacionPago;
    private String Concepto;
    private int idAnioMesCuota;
    private int nroConcepto;
    private int idFinanciamientoDetalle;
    private int idReincorporacionDetalle;
    private int idDeudaDetalle;
    private int idBbvaContador;
//    private Date fechaOperacion;
//    private int nroConcepto;

    public BbvaContadorDetalle() {
    }

    public BbvaContadorDetalle(int id) {
        this.id = id;
    }

    public BbvaContadorDetalle(int id, int idConcepto, double monto, String tipoConceptoAgrupar, String Concepto, int idAnioMesCuota, int nroConcepto, int idFinanciamientoDetalle, int idReincorporacionDetalle, int idDeudaDetalle) {
        this.id = id;
        this.idConcepto = idConcepto;
        this.monto = monto;
        this.tipoConceptoAgrupar = tipoConceptoAgrupar;
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

    public int getIdBbvaContador() {
        return idBbvaContador;
    }

    public void setIdBbvaContador(int idBbvaContador) {
        this.idBbvaContador = idBbvaContador;
    }

    public String getIdentificacionPago() {
        return identificacionPago;
    }

    public void setIdentificacionPago(String identificacionPago) {
        this.identificacionPago = identificacionPago;
    }

}
