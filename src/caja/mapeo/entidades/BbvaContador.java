/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.mapeo.entidades;

/**
 *
 * @author user
 */
public class BbvaContador implements java.io.Serializable {

    private int id;
    private int idCliente;
    private int idBbva;

    public BbvaContador() {
    }

    public BbvaContador(int id) {
        this.id = id;
    }

    public BbvaContador(int id, int idCliente, int idBbva) {
        this.id = id;
        this.idCliente = idCliente;
        this.idBbva = idBbva;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdBbva() {
        return idBbva;
    }

    public void setIdBbva(int idBbva) {
        this.idBbva = idBbva;
    }
    
    

}
