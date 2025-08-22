/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.TipoNota;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface TipoNotaDAOIFace {
    public List BuscarTodosTipoNotaCredito();
    public void GuardarTipoNotaCredito(TipoNota u);
    public List BuscarTipoNotaCredito_SegunNombre(String nombre);
    public void ActualizarTipoNotaCredito(TipoNota u);
}
