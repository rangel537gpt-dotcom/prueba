/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.Cuotas;
import caja.mapeo.entidades.DocumentoPagoDet;
import java.util.List;

/**
 *
 * @author user
 */
public interface CuotasBOIFace {
    
    public void InsertarCuota();
    public DocumentoPagoDet ObtenerUltimaCuotaOrdinaria(Cliente cliente);
    public boolean GuardarCuota(Cuotas cuota/*Cliente cliente,DocumentoPagoDet dpd*/);
    public List ObtenerCuotasPendientesCliente(Cliente cliente);
    public List ObtenerTodasCuotasCliente(Cliente cliente,int cantCuotas);
    public Object ObtenerCuotaMesAcutal(int idCliente);
    public List ObtenerTodasCuotasCanceladas(Cliente cliente);
    public List ObtenerMontoTotalFinanciados(int desde,int hasta);
    public Cuotas ObtenerUltimaCuotaOrdinaria(int idCliente);
    public List ObtenerTodasCuotasClienteVitalicio(Cliente cliente, int cantCuotas);
    public List ObtenerTodasCuotasSociedades(Cliente cliente, int cantCuotas);
    public double ObtenerDescuentoConcepto(int id);
    public double ObtenerDescuentoConceptoUnico(int idAnioMes, int idConcepto);
    public List ObtenerTodasCuotasCanceladasSociedades(Cliente cliente);
    public double ObtenerMontoCuotaAnioMes(int Anio, int Mes);
    public List ObtenerCuotasPendientesCliente_ParaFinanciar(Cliente cliente);
    public List ObtenerCuotasPendientesCliente_30anios(Cliente cliente);
    public List ObtenerTodasCuotasCliente30Anios_CajaArequipa(Cliente cliente, int cantCuotas);
    public List ObtenerTodasCuotasClienteVitalicio_CajaArequipa(Cliente cliente, int cantCuotas);
    public List ObtenerTodasCuotasClienteVitalicio_BBVA(Cliente cliente, int cantCuotas);
    public List ObtenerTodasCuotasCliente30Anios_BBVA(Cliente cliente, int cantCuotas);
    public Cuotas ObtenerUltimaCuota(int id);
    public Cuotas ObtenerCuota(int idCliente, int idAnioMes, int idConcepto);
    public List ObtenerCuotasPendientesClienteSociedades(Cliente cliente);
    
}
