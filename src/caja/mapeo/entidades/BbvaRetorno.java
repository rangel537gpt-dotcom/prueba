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
public class BbvaRetorno implements java.io.Serializable {

    private int id;
    private int idBbva;
    private byte[] archivo;
    private String nombreArchivo;
    private Date fecha;
    private String seGeneroComprobantes;
    private String seSubioArchivo;
    private String ruc;
    private String codigoClase;
    private String moneda;
    private String cuentaRecaudadora;
    private int totalRegistros;
    private double totalPagos;
    private double totalDepositos;

    public BbvaRetorno() {
    }

    public BbvaRetorno(int id) {
        this.id = id;
    }

    public BbvaRetorno(int id, int idBbva, byte[] archivo, String nombreArchivo) {
        this.id = id;
        this.idBbva = idBbva;
        this.archivo = archivo;
        this.nombreArchivo = nombreArchivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBbva() {
        return idBbva;
    }

    public void setIdBbva(int idBbva) {
        this.idBbva = idBbva;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSeGeneroComprobantes() {
        return seGeneroComprobantes;
    }

    public void setSeGeneroComprobantes(String seGeneroComprobantes) {
        this.seGeneroComprobantes = seGeneroComprobantes;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCuentaRecaudadora() {
        return cuentaRecaudadora;
    }

    public void setCuentaRecaudadora(String cuentaRecaudadora) {
        this.cuentaRecaudadora = cuentaRecaudadora;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public double getTotalPagos() {
        return totalPagos;
    }

    public void setTotalPagos(double totalPagos) {
        this.totalPagos = totalPagos;
    }

    public double getTotalDepositos() {
        return totalDepositos;
    }

    public void setTotalDepositos(double totalDepositos) {
        this.totalDepositos = totalDepositos;
    }

    public String getSeSubioArchivo() {
        return seSubioArchivo;
    }

    public void setSeSubioArchivo(String seSubioArchivo) {
        this.seSubioArchivo = seSubioArchivo;
    }
    
    

}
