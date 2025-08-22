/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Deuda;
import caja.mapeo.entidades.Reincorporacion;
import java.util.List;

/**
 *
 * @author user
 */
public interface DeudasBOIFace {
    
    public List ObtenerTodasDeudas(int idCliente);
    public Reincorporacion ObtenerReincorporacion(int idReincorporacion);
    public List ObtenerTodasDeudasPendientes(int idCliente);  
    public void ActualizarDeuda(Deuda d);
    public Deuda ObtenerDeudaClienteConcepto(int idCliente, int idConceptoPagoDetalle);
}
