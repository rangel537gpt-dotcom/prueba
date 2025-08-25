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
public class ScotiabankContador implements java.io.Serializable {

    private int id;
    private int idCliente;
    private int idScotiabank;

    public ScotiabankContador() {
    }

    public ScotiabankContador(int id) {
        this.id = id;
    }

    public ScotiabankContador(int id, int idCliente, int idScotiabank) {
        this.id = id;
        this.idCliente = idCliente;
        this.idScotiabank = idScotiabank;
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

    public int getIdScotiabank() {
        return idScotiabank;
    }

    public void setIdScotiabank(int idScotiabank) {
        this.idScotiabank = idScotiabank;
    }


    
    

}
