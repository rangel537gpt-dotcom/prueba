/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.mapeo.entidades;

//import java.util.Date;

/**
 *
 * @author user
 */
public class BbvaRetornoDetalleDocumento implements java.io.Serializable {

    private int id;
    private int idBbvaRetornoDetalle;
    private int IdDocumentoPagoDet;
    private String Concepto;
    private int IdConceptoPagoDetalle;
    private double monto;
    

    public BbvaRetornoDetalleDocumento() {
    }

    public BbvaRetornoDetalleDocumento(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBbvaRetornoDetalle() {
        return idBbvaRetornoDetalle;
    }

    public void setIdBbvaRetornoDetalle(int idBbvaRetornoDetalle) {
        this.idBbvaRetornoDetalle = idBbvaRetornoDetalle;
    }

    public int getIdDocumentoPagoDet() {
        return IdDocumentoPagoDet;
    }

    public void setIdDocumentoPagoDet(int IdDocumentoPagoDet) {
        this.IdDocumentoPagoDet = IdDocumentoPagoDet;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String Concepto) {
        this.Concepto = Concepto;
    }

    public int getIdConceptoPagoDetalle() {
        return IdConceptoPagoDetalle;
    }

    public void setIdConceptoPagoDetalle(int IdConceptoPagoDetalle) {
        this.IdConceptoPagoDetalle = IdConceptoPagoDetalle;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }


    

}
