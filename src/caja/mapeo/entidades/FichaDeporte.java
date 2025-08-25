/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caja.mapeo.entidades;

public class FichaDeporte {

    private int id;
    private Deporte deporte;
    private FichaDatos fichaDatos;
    private String borrado;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public FichaDatos getFichaDatos() {
        return fichaDatos;
    }

    public void setFichaDatos(FichaDatos fichaDatos) {
        this.fichaDatos = fichaDatos;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }
}
