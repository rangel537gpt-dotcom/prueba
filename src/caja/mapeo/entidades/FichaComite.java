/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caja.mapeo.entidades;

public class FichaComite {

    private int id;
    private FichaDatos fichaDatos;
    private String comite;
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

    public String getComite() {
        return comite;
    }

    public void setComite(String comite) {
        this.comite = comite;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }
}
