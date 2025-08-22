/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

//import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ConceptoPagoDetalle;
//import caja.mapeo.entidades.Reincorporacion;
//import caja.mapeo.entidades.DocumentoPagoDet;

/**
 *
 * @author user
 */
public class ScotiabankRetornoDeudaDTO {

    private int idDeuda;
    private Cliente cliente;
    private ConceptoPagoDetalle concepto;
    private double monto;
    private String referencia;
    private int idRetornoDetalle;

    public ScotiabankRetornoDeudaDTO() {
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ConceptoPagoDetalle getConcepto() {
        return concepto;
    }

    public void setConcepto(ConceptoPagoDetalle concepto) {
        this.concepto = concepto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(int idDeuda) {
        this.idDeuda = idDeuda;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getIdRetornoDetalle() {
        return idRetornoDetalle;
    }

    public void setIdRetornoDetalle(int idRetornoDetalle) {
        this.idRetornoDetalle = idRetornoDetalle;
    }






}
