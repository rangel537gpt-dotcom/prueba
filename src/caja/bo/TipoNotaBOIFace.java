/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.TipoNota;
import java.util.List;

/**
 *
 * @author juan carlos
 */
public interface TipoNotaBOIFace {
    public List BuscarTodosTipoNotaCredito();
    public boolean GuardarTipoNotaCredito(TipoNota u);
    public List BuscarTipoNotaCredito_SegunNombre(String nombre);
    public boolean ActualizarTipoNotaCredito(TipoNota u);
}
