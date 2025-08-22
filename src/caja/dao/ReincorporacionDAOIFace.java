/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Reincorporacion;
import java.util.List;

/**
 *
 * @author user
 */
public interface ReincorporacionDAOIFace {
    
    public List ObtenerTodosReincorporaciones(int idCliente);
    public Reincorporacion ObtenerReincorporacion(int idReincorporacion);
    public List ObtenerDetalleReincorporacion(int idReincorporacion);
    public List ObtenerTodosReincorporacionActivosCliente(int idCliente);
    public List ObtenerTodasReincoporacionesPendientes(int idCliente);
    
}
