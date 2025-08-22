/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

/**
 *
 * @author user
 */
public class AdministracionCursoDTO {
    
    private String codigoCurso;
    private String nombreCurso;

    public AdministracionCursoDTO() {
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    @Override
    public String toString(){
        return this.codigoCurso + "-" + this.nombreCurso;
    }
    
    
}
