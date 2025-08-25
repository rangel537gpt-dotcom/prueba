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
public class ScotiabankRetornoDetalle implements java.io.Serializable {

    private int id;
    private int idScotiabankRetorno;
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
    private String cuentaEmpresa;
    private String secuenciaServicio;
    private String codigoUsuario;
    private String numeroRecibo;
    private String nombreUsuario;
    private String monedaCobro;
    private double importe1;
    private double importe2;
    private double importe3;
    private double importe4;
    private double importe5;
    private double importe6;
    private Date fechaVencimiento;
    private String tipoPago;
    private String medioPago;
    private String numeroOperacion;
    private String referenciaCobro;
    private String horaPago;
    private Date fechaRealPago;
    private String canal;
    

    public ScotiabankRetornoDetalle() {
    }

    public ScotiabankRetornoDetalle(int id) {
        this.id = id;
    }

    public ScotiabankRetornoDetalle(int id, int idBbvaRetorno, String nombre, String referencia, double importeOrigen, Date fechaPago, int oficinaPago, double importeDeposito, int nroMovimiento, int tipoValor, int canalEntrada, int idDocumentoPagoDetalle) {
        this.id = id;
        this.idScotiabankRetorno = idBbvaRetorno;
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

    public int getIdScotiabankRetorno() {
        return idScotiabankRetorno;
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

    public void setIdScotiabankRetorno(int idBbvaRetorno) {
        this.idScotiabankRetorno = idBbvaRetorno;
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

    public String getCuentaEmpresa() {
        return cuentaEmpresa;
    }

    public void setCuentaEmpresa(String cuentaEmpresa) {
        this.cuentaEmpresa = cuentaEmpresa;
    }

    public String getSecuenciaServicio() {
        return secuenciaServicio;
    }

    public void setSecuenciaServicio(String secuenciaServicio) {
        this.secuenciaServicio = secuenciaServicio;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMonedaCobro() {
        return monedaCobro;
    }

    public void setMonedaCobro(String monedaCobro) {
        this.monedaCobro = monedaCobro;
    }

    public double getImporte1() {
        return importe1;
    }

    public void setImporte1(double importe1) {
        this.importe1 = importe1;
    }

    public double getImporte2() {
        return importe2;
    }

    public void setImporte2(double importe2) {
        this.importe2 = importe2;
    }

    public double getImporte3() {
        return importe3;
    }

    public void setImporte3(double importe3) {
        this.importe3 = importe3;
    }

    public double getImporte4() {
        return importe4;
    }

    public void setImporte4(double importe4) {
        this.importe4 = importe4;
    }

    public double getImporte5() {
        return importe5;
    }

    public void setImporte5(double importe5) {
        this.importe5 = importe5;
    }

    public double getImporte6() {
        return importe6;
    }

    public void setImporte6(double importe6) {
        this.importe6 = importe6;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getReferenciaCobro() {
        return referenciaCobro;
    }

    public void setReferenciaCobro(String referenciaCobro) {
        this.referenciaCobro = referenciaCobro;
    }

    public String getHoraPago() {
        return horaPago;
    }

    public void setHoraPago(String horaPago) {
        this.horaPago = horaPago;
    }

    public Date getFechaRealPago() {
        return fechaRealPago;
    }

    public void setFechaRealPago(Date fechaRealPago) {
        this.fechaRealPago = fechaRealPago;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }
    

}
