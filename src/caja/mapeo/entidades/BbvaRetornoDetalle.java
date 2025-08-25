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
public class BbvaRetornoDetalle implements java.io.Serializable {

    private int id;
    private int idBbvaRetorno;
    private String nombre;
    private String referencia;
    private double importeOrigen;
    private Date fechaPago;
    private int oficinaPago;
    private double importeDeposito;
    private double importeMora;
    private int nroMovimiento;
    private int tipoValor;
    private int canalEntrada;
    private int idDocumentoPagoDetalle;
    private int idCliente;
    private String tipoCobranza;
    private int idAnioMes;
    

    public BbvaRetornoDetalle() {
    }

    public BbvaRetornoDetalle(int id) {
        this.id = id;
    }

    public BbvaRetornoDetalle(int id, int idBbvaRetorno, String nombre, String referencia, double importeOrigen, Date fechaPago, int oficinaPago, double importeDeposito, int nroMovimiento, int tipoValor, int canalEntrada, int idDocumentoPagoDetalle) {
        this.id = id;
        this.idBbvaRetorno = idBbvaRetorno;
        this.nombre = nombre;
        this.referencia = referencia;
        this.importeOrigen = importeOrigen;
        this.fechaPago = fechaPago;
        this.oficinaPago = oficinaPago;
        this.importeDeposito = importeDeposito;
        this.nroMovimiento = nroMovimiento;
        this.tipoValor = tipoValor;
        this.canalEntrada = canalEntrada;
        this.idDocumentoPagoDetalle = idDocumentoPagoDetalle;
    }

    public int getIdBbvaRetorno() {
        return idBbvaRetorno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getImporteOrigen() {
        return importeOrigen;
    }

    public void setImporteOrigen(double importeOrigen) {
        this.importeOrigen = importeOrigen;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getOficinaPago() {
        return oficinaPago;
    }

    public void setOficinaPago(int oficinaPago) {
        this.oficinaPago = oficinaPago;
    }

    public double getImporteDeposito() {
        return importeDeposito;
    }

    public void setImporteDeposito(double importeDeposito) {
        this.importeDeposito = importeDeposito;
    }

    public int getNroMovimiento() {
        return nroMovimiento;
    }

    public void setNroMovimiento(int nroMovimiento) {
        this.nroMovimiento = nroMovimiento;
    }

    public int getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(int tipoValor) {
        this.tipoValor = tipoValor;
    }

    public int getCanalEntrada() {
        return canalEntrada;
    }

    public void setCanalEntrada(int canalEntrada) {
        this.canalEntrada = canalEntrada;
    }

    public int getIdDocumentoPagoDetalle() {
        return idDocumentoPagoDetalle;
    }

    public void setIdDocumentoPagoDetalle(int idDocumentoPagoDetalle) {
        this.idDocumentoPagoDetalle = idDocumentoPagoDetalle;
    }    

    public void setIdBbvaRetorno(int idBbvaRetorno) {
        this.idBbvaRetorno = idBbvaRetorno;
    }

    public double getImporteMora() {
        return importeMora;
    }

    public void setImporteMora(double importeMora) {
        this.importeMora = importeMora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoCobranza() {
        return tipoCobranza;
    }

    public void setTipoCobranza(String tipoCobranza) {
        this.tipoCobranza = tipoCobranza;
    }

    public int getIdAnioMes() {
        return idAnioMes;
    }

    public void setIdAnioMes(int idAnioMes) {
        this.idAnioMes = idAnioMes;
    }
    

}
