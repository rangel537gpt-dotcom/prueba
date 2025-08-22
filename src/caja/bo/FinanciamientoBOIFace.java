/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.DocumentoPago;
import caja.mapeo.entidades.Financiamiento;
import caja.mapeo.entidades.FinanciamientoDocumentoPago;
import caja.mapeo.entidades.ReincorporacionDocumentoPago;
import java.util.List;

/**
 *
 * @author user
 */
public interface FinanciamientoBOIFace {
    
    public void ObtenerDeudaTotal(Cliente cli);
    public boolean GuardarFinanciamiento(DocumentoPago doc,Financiamiento financiamiento,int idTipoDoc,String nroSerie, int diasProrroga);
    public List ObtenerTodosFinanciamientoActivosCliente(int idCliente);
    public void ActualizarFinanciamiento(FinanciamientoDocumentoPago fdp);
    public List ObtenerTodosFinanciamientos(int idCliente);
    public List ObtenerDetalleFinanciamiento(int idFinanciamiento);
    public List ObtenerTodosFinanciamientosPendientes(int idCliente);
    public void ActualizarReincorporacion(ReincorporacionDocumentoPago rdp);
    public List ObtenerTodosFinanciamientosTodosContadores();
    
}
