/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.ConceptoPagoDetalle;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface ConceptoPagoDetalleDAOIFace {
    public List BuscarConceptoPagoPorNombre(String busqueda);
    public List BuscarConceptoPagoPorCodigo(String busqueda);
    public List BuscarConceptoPagoPorTipoCodigo(String busqueda);
    public List BuscarConceptoPagoPorCargo(String busqueda);
    public List BuscarConceptoPagPorAbono(String busqueda);
    public void GuardarConceptoPagoDetalle(ConceptoPagoDetalle conPagoDet);
    public void ActualizarConceptoPagoDetalle(ConceptoPagoDetalle conPagoDet);
    public void EliminarConceptoPagoDetalle(ConceptoPagoDetalle conPagoDet);
}
