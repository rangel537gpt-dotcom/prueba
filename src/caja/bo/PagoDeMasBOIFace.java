/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.mapeo.entidades.Reincorporacion;
import java.util.List;

/**
 *
 * @author user
 */
public interface PagoDeMasBOIFace {
    
    public List ObtenerTodosPagoDeMas(int idCliente);
    public Reincorporacion ObtenerReincorporacion(int idReincorporacion);
    
}
