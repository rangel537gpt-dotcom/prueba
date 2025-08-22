/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.ConceptoPagoDetalle;
//import caja.mapeo.entidades.Reincorporacion;
//import caja.mapeo.entidades.DocumentoPagoDet;

/**
 *
 * @author user
 */
public class BbvaRetornoReincorporacionDTO {

    private int idReincorporacionDetalle;
    private AnioMes anioMes;
    private Cliente cliente;
    private ConceptoPagoDetalle concepto;
    private double monto;
    private int nroCuota;
    private int idRetornoDetalle;
    private String referencia;

    public BbvaRetornoReincorporacionDTO() {
    }

    public AnioMes getAnioMes() {
        return anioMes;
    }

    public void setAnioMes(AnioMes anioMes) {
        this.anioMes = anioMes;
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

    public int getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    public int getIdReincorporacionDetalle() {
        return idReincorporacionDetalle;
    }

    public void setIdReincorporacionDetalle(int idReincorporacionDetalle) {
        this.idReincorporacionDetalle = idReincorporacionDetalle;
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
