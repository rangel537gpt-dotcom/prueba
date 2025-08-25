/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.entidades;

/**
 *
 * @author juan carlos
 */
public class Cursos {
     private String descripcion;
    private String tipoCodigo;
    private String codigo;

    public Cursos(){}
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipoCodigo
     */
    public String getTipoCodigo() {
        return tipoCodigo;
    }

    /**
     * @param tipoCodigo the tipoCodigo to set
     */
    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
