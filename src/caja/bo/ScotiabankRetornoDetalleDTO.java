/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.DocumentoPagoDet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ScotiabankRetornoDetalleDTO {

    private String concepto;
    private int anioDesde;
    private int mesDesde;
    private int anioHasta;
    private int mesHasta;
    private double monto;
    private int idConcepto;
    private int cantidad;
    private String observacion;
    private DocumentoPagoDet docDetalle;
    private List<ScotiabankRetornoCuotaDTO> lCuotas;
    private List<ScotiabankRetornoFinanciamientoDTO> lFinanciamiento;
    private List<ScotiabankRetornoReincorporacionDTO> lReincorporacion;
    private List<ScotiabankRetornoDeudaDTO> lDeuda;

    public ScotiabankRetornoDetalleDTO() {
        this.lCuotas = new ArrayList();
        this.lFinanciamiento = new ArrayList();
        this.lReincorporacion = new ArrayList();
        this.lDeuda = new ArrayList();
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getAnioDesde() {
        return anioDesde;
    }

    public void setAnioDesde(int anioDesde) {
        this.anioDesde = anioDesde;
    }

    public int getMesDesde() {
        return mesDesde;
    }

    public void setMesDesde(int mesDesde) {
        this.mesDesde = mesDesde;
    }

    public int getMesHasta() {
        return mesHasta;
    }

    public void setMesHasta(int mesHasta) {
        this.mesHasta = mesHasta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getAnioHasta() {
        return anioHasta;
    }

    public void setAnioHasta(int anioHasta) {
        this.anioHasta = anioHasta;
    }

    public DocumentoPagoDet getDocDetalle() {
        return docDetalle;
    }

    public void setDocDetalle(DocumentoPagoDet docDetalle) {
        this.docDetalle = docDetalle;
    }

    public List<ScotiabankRetornoCuotaDTO> getlCuotas() {
        return lCuotas;
    }

    public void setlCuotas(List<ScotiabankRetornoCuotaDTO> lCuotas) {
        this.lCuotas = lCuotas;
    }

    public void agregarCuota(ScotiabankRetornoCuotaDTO c) {
        this.lCuotas.add(c);
    }

    public void agregarFinanciamiento(ScotiabankRetornoFinanciamientoDTO c) {
        this.lFinanciamiento.add(c);
    }

    public void agregarReincorporacion(ScotiabankRetornoReincorporacionDTO c) {
        this.lReincorporacion.add(c);
    }

    public void agregarDeuda(ScotiabankRetornoDeudaDTO c) {
        this.lDeuda.add(c);
    }

    public List<ScotiabankRetornoFinanciamientoDTO> getlFinanciamiento() {
        return lFinanciamiento;
    }

    public void setlFinanciamiento(List<ScotiabankRetornoFinanciamientoDTO> lFinanciamiento) {
        this.lFinanciamiento = lFinanciamiento;
    }

    public List<ScotiabankRetornoReincorporacionDTO> getlReincorporacion() {
        return lReincorporacion;
    }

    public void setlReincorporacion(List<ScotiabankRetornoReincorporacionDTO> lReincorporacion) {
        this.lReincorporacion = lReincorporacion;
    }

    public List<ScotiabankRetornoDeudaDTO> getlDeuda() {
        return lDeuda;
    }

    public void setlDeuda(List<ScotiabankRetornoDeudaDTO> lDeuda) {
        this.lDeuda = lDeuda;
    }

}
