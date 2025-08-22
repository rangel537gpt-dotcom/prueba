/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.AnioMes;
import caja.mapeo.entidades.AnioMesConcepto;
import java.util.List;

/**
 *
 * @author user
 */
public interface AnioMesDAOIFace {
    
    public List ObtenerTodosMesAnio();
    public void InsertarAnioMes(AnioMes am);
    public void InsertarAnioMesConcepto(AnioMesConcepto amc);
    public List ObtenerConceptosCuota(int desde, int hasta);
    public Object ObtenerPrimerConceptosCuota(int idConcepto, int desde, int hasta);
    public Object ObtenerUltimoConceptosCuota(int idConcepto, int desde, int hasta);
    public Object ObtenerMontoConceptoDetallado(int idAnioMes, int idConcepto);
    public List ObtenerConceptosCuotaVitalicio(int desde, int hasta);
    public List ObtenerConceptosCuotaSociedadArequipa(int desde, int hasta);
    public List ObtenerConceptosCuotaSociedadLima(int desde, int hasta);
    public Object ObtenerMontoSociedadConceptoDetallado(int idAnioMes, int idConcepto);
    public Object ObtenerMontoConceptoDetalladoVitalicio(int idAnioMes, int idConcepto);
    
}
