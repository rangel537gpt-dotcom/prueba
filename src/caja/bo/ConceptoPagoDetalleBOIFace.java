/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.ConceptoPagoDetalle;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface ConceptoPagoDetalleBOIFace {
    public List BuscarConceptoPagoDetalle(String busqueda, int tipoBusqueda);
    public void GuardarConceptoPagoDetalle(ConceptoPagoDetalle cpd);
    public ConceptoPagoDetalle ObtenerConceptoPagoDetalle1(String tipoCodigo);
    public void ActualizarConceptoPagoDetalle(ConceptoPagoDetalle cpd);
    public void EliminarCobrador(ConceptoPagoDetalle cpd, int idConceptoPagoDetalle);
}
