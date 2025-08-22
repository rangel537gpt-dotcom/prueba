/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Cuotas;
import java.util.List;

/**
 *
 * @author user
 */
public interface CuotasDAOIFace {
    
    public Cuotas ObtenerUltimaCuota(int idCliente);
    public void GuardarCuota(Cuotas c);
    public int ObtenerIdAnioMes(int Anio,int Mes);
    public List ObtenerCuotasPendientesCliente(int idCliente,int desde,int hasta);
    public Object ObtenerCuotaMesAcutal(int idCliente,int anio,int mes);
    public List ObtenerTodasCuotasCanceladas(int idCliente, int desde, int hasta);
    public List ObtenerMontoTotalFinanciados(int desde, int hasta);
    public List ObtenerCuotasPendientesClienteVitalicio(int idCliente, int desde, int hasta);
    public List ObtenerCuotasPendientesSociedad(int idCliente, int desde, int hasta);
    public double ObtenerDescuentoConcepto(int idAnioMes);
    public double ObtenerDescuentoConceptoUnico(int idAnioMes, int idConcepto);
    public double ObtenerMontoCuotaAnioMes(int Anio, int Mes);
    public List ObtenerCuotasPendientesCliente_ParaFinanciar(int idCliente, int desde, int hasta);
    public List ObtenerCuotasPendientesClienteVitalicio_ParaFinanciar(int idCliente, int desde, int hasta);
    public Cuotas ObtenerCuota(int idCliente, int idAnioMes, int idConcepto);
    public List ObtenerCuotasPendientesSociedadLIMA(int idCliente, int desde, int hasta);
    public List ObtenerCuotasPendientesCliente_ParaFinanciar_Sociedad(int idCliente, int desde, int hasta);
    public List ObtenerCuotasPendientesCliente_ParaFinanciar_SociedadLIMA(int idCliente, int desde, int hasta);
}
