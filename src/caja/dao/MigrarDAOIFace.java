/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.dao;

import caja.mapeo.entidades.Cliente;
import caja.mapeo.entidades.DocumentoPago;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface MigrarDAOIFace {
    
    public List ObtenerDatosBaseAnterior();
    public List ObtenerClientes();
    public List ObtenerCabeceraBaseAnterior();
    public List ObtenerDetalleBaseAnterior();
    public DocumentoPago ObtenerCabecera(int idTipoDocSerie,int nroDoc,Date fecha);
    public void InsertarFecha(String fecha);
    public List ObtenerDatosBaseAnteriorSociedades();
    
}
