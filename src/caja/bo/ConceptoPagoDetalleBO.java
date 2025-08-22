/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.ConceptoPagoDetalleDAO;
import caja.mapeo.entidades.ConceptoPagoDetalle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public class ConceptoPagoDetalleBO implements ConceptoPagoDetalleBOIFace {

    private static ConceptoPagoDetalleBO INSTANCE = new ConceptoPagoDetalleBO();
    private List<ConceptoPagoDetalle> resultadoBusqueda;

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ConceptoPagoDetalleBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConceptoPagoDetalleBO();
                }
            }
        }
    }

    public static ConceptoPagoDetalleBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    public ConceptoPagoDetalleBO() {
        resultadoBusqueda = new ArrayList();
    }

    public List<ConceptoPagoDetalle> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<ConceptoPagoDetalle> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    @Override
    public List BuscarConceptoPagoDetalle(String busqueda, int tipoBusqueda) {
        resultadoBusqueda.clear();

        ConceptoPagoDetalleDAO cpd = ConceptoPagoDetalleDAO.getInstance();
        if (tipoBusqueda == 1) {
            resultadoBusqueda = cpd.BuscarConceptoPagoPorNombre(busqueda);
            return resultadoBusqueda;
        }if (tipoBusqueda == 2) {
            resultadoBusqueda = cpd.BuscarConceptoPagoPorCodigo(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 3) {
            resultadoBusqueda = cpd.BuscarConceptoPagoPorTipoCodigo(busqueda);
            return resultadoBusqueda;
        } else if (tipoBusqueda == 4) {
            resultadoBusqueda = cpd.BuscarConceptoPagoPorCargo(busqueda);
            return resultadoBusqueda;
        } else {
            resultadoBusqueda = cpd.BuscarConceptoPagPorAbono(busqueda);
            return resultadoBusqueda;
        }
    }

    @Override
    public void GuardarConceptoPagoDetalle(ConceptoPagoDetalle cpd) {
        ConceptoPagoDetalleDAO conceptoPagoDetalleDAO = ConceptoPagoDetalleDAO.getInstance();
        conceptoPagoDetalleDAO.GuardarConceptoPagoDetalle(cpd);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lConceptos = dpBO.ObtenerTodosConceptoPago();
        if (lConceptos != null) {
            lConceptos.add(cpd);
        }
    }

    @Override
    public ConceptoPagoDetalle ObtenerConceptoPagoDetalle1(String codigo) {
        String cConcepto = "";
        for (ConceptoPagoDetalle cpd : resultadoBusqueda) {
            cConcepto = cpd.getTipoCodigo() + cpd.getCodigo();
            if (cConcepto.equals(codigo)) {
                return cpd;
            }
        }
        return null;
    }

    @Override
    public void ActualizarConceptoPagoDetalle(ConceptoPagoDetalle cpd) {
        ConceptoPagoDetalleDAO conceptoPagoDetalleDAO = ConceptoPagoDetalleDAO.getInstance();
        conceptoPagoDetalleDAO.ActualizarConceptoPagoDetalle(cpd);
        DocumentoPagoBO dpBO = DocumentoPagoBO.getInstance();
        List<ConceptoPagoDetalle> lConceptos = dpBO.ObtenerTodosConceptoPago();
        if(lConceptos != null){
            lConceptos.add(cpd);
        }
    }

    @Override
    public void EliminarCobrador(ConceptoPagoDetalle cpd, int idConceptoPagoDetalle) {
        ConceptoPagoDetalleDAO conceptoPagoDetalleDAO = ConceptoPagoDetalleDAO.getInstance();
        conceptoPagoDetalleDAO.EliminarConceptoPagoDetalle(cpd);
    }

}
