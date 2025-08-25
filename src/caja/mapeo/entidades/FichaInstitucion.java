/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caja.mapeo.entidades;

import java.util.Date;

public class FichaInstitucion {

    private int id;
    private FichaDatos fichaDatos;
    private String nombre;
    private Date fechaIngreso;
    private String borrado;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FichaDatos getFichaDatos() {
        return fichaDatos;
    }

    public void setFichaDatos(FichaDatos fichaDatos) {
        this.fichaDatos = fichaDatos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }
}
