/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import java.util.List;

/**
 *
 * @author user
 */
public interface FinanciamientoDAOIFace {
    
    public void CargarFinanciamientoCliente();
    public void GuardarFinanciamiento(Financiamiento financiamiento);
    public void GuardarFinanciamientoDocumentoPago(FinanciamientoDocumentoPago finanDoc);
    public void ActualizarFinanciamientoDocumentoPago(FinanciamientoDocumentoPago finanDoc);
    public List ObtenerTodosFinanciamientoActivosCliente(int idCliente);
    public void ActualizarFinanciamiento(FinanciamientoDocumentoPago fdp);
    public List ObtenerTodosFinanciamientos(int idCliente);
    public List ObtenerDetalleFinanciamiento(int idFinanciamiento);
    public List ObtenerTodosFinanciamientosPendientes(int idCliente);
    public int ObtenerNroFinanciamiento(int idCliente);
    public List ObtenerTodasDeudasPendientes(int idCliente);
    public void ActualizarDeudaPendiente(Deuda d);
    public void ActualizarReincorporacion(ReincorporacionDocumentoPago rdp);
    public void CancelarDeudas(int idCliente, int idFinanciamiento);
    public void CancelarFinanciamientos(int idCliente, int idFinanciamiento, int nroFinanciamiento);
    public List ObtenerTodosFinanciamientosTodosContadores();
    
}
