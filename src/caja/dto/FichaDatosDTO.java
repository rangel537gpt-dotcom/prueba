/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caja.dto;

import java.util.List;

public class FichaDatosDTO {

    private int id;
    private Integer nro;
    private String dni;
    private String ruc;
    private String nacionalidad;
    private String apePat;
    private String apeMat;
    private String nombre;
    private String email;
    private String telefonoFijo;
    private String telefonoCelular;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String domicilio;
    private String direccionDepartamento;
    private String direccionProvincia;
    private String direccionDistrito;
    private String centroTrabajo;
    private String trabajoDepartamento;
    private String trabajoProvincia;
    private String trabajoDistrito;
    private String centroTrabajoDireccion;
    private String procesado;
    private String fechaDb;
    private String telefonoCelular2;
    private String paginaWeb;
    private String borrado;
    private String telefonoTrabajo;
    private String emailTrabajo;
    private String fechaOptoTituloContador;
    private String universidadRealizoEstudios;
    private String universidadOtorgoTitulo;
    private String modalidadObtencionTitulo;
    private String fechaOptoGradoBachiller;
    private String estudiosEspecializacion;
    private String otrosEstudios;
    private String becasObtenidas;
    private String correspondenciaFisica;
    private String lugarCobranza;
    private String temasGustariaRecibirCapacitacion;
    private String comentarios;
    private String actividadesCulturales;
    private Boolean tieneError;
    private String mensajeError;
    private List<FichaComiteDTO> comites;
    private List<FichaInstitucionDTO> instituciones;
    private List<FichaDeporteDTO> deportes;

    public FichaDatosDTO() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDireccionDepartamento() {
        return direccionDepartamento;
    }

    public void setDireccionDepartamento(String direccionDepartamento) {
        this.direccionDepartamento = direccionDepartamento;
    }

    public String getDireccionProvincia() {
        return direccionProvincia;
    }

    public void setDireccionProvincia(String direccionProvincia) {
        this.direccionProvincia = direccionProvincia;
    }

    public String getDireccionDistrito() {
        return direccionDistrito;
    }

    public void setDireccionDistrito(String direccionDistrito) {
        this.direccionDistrito = direccionDistrito;
    }

    public String getCentroTrabajo() {
        return centroTrabajo;
    }

    public void setCentroTrabajo(String centroTrabajo) {
        this.centroTrabajo = centroTrabajo;
    }

    public String getTrabajoDepartamento() {
        return trabajoDepartamento;
    }

    public void setTrabajoDepartamento(String trabajoDepartamento) {
        this.trabajoDepartamento = trabajoDepartamento;
    }

    public String getTrabajoProvincia() {
        return trabajoProvincia;
    }

    public void setTrabajoProvincia(String trabajoProvincia) {
        this.trabajoProvincia = trabajoProvincia;
    }

    public String getTrabajoDistrito() {
        return trabajoDistrito;
    }

    public void setTrabajoDistrito(String trabajoDistrito) {
        this.trabajoDistrito = trabajoDistrito;
    }

    public String getCentroTrabajoDireccion() {
        return centroTrabajoDireccion;
    }

    public void setCentroTrabajoDireccion(String centroTrabajoDireccion) {
        this.centroTrabajoDireccion = centroTrabajoDireccion;
    }

    public String getProcesado() {
        return procesado;
    }

    public void setProcesado(String procesado) {
        this.procesado = procesado;
    }

    public String getFechaDb() {
        return fechaDb;
    }

    public void setFechaDb(String fechaDb) {
        this.fechaDb = fechaDb;
    }

    public String getTelefonoCelular2() {
        return telefonoCelular2;
    }

    public void setTelefonoCelular2(String telefonoCelular2) {
        this.telefonoCelular2 = telefonoCelular2;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }

    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public String getEmailTrabajo() {
        return emailTrabajo;
    }

    public void setEmailTrabajo(String emailTrabajo) {
        this.emailTrabajo = emailTrabajo;
    }

    public String getFechaOptoTituloContador() {
        return fechaOptoTituloContador;
    }

    public void setFechaOptoTituloContador(String fechaOptoTituloContador) {
        this.fechaOptoTituloContador = fechaOptoTituloContador;
    }

    public String getUniversidadRealizoEstudios() {
        return universidadRealizoEstudios;
    }

    public void setUniversidadRealizoEstudios(String universidadRealizoEstudios) {
        this.universidadRealizoEstudios = universidadRealizoEstudios;
    }

    public String getUniversidadOtorgoTitulo() {
        return universidadOtorgoTitulo;
    }

    public void setUniversidadOtorgoTitulo(String universidadOtorgoTitulo) {
        this.universidadOtorgoTitulo = universidadOtorgoTitulo;
    }

    public String getModalidadObtencionTitulo() {
        return modalidadObtencionTitulo;
    }

    public void setModalidadObtencionTitulo(String modalidadObtencionTitulo) {
        this.modalidadObtencionTitulo = modalidadObtencionTitulo;
    }

    public String getFechaOptoGradoBachiller() {
        return fechaOptoGradoBachiller;
    }

    public void setFechaOptoGradoBachiller(String fechaOptoGradoBachiller) {
        this.fechaOptoGradoBachiller = fechaOptoGradoBachiller;
    }

    public String getEstudiosEspecializacion() {
        return estudiosEspecializacion;
    }

    public void setEstudiosEspecializacion(String estudiosEspecializacion) {
        this.estudiosEspecializacion = estudiosEspecializacion;
    }

    public String getOtrosEstudios() {
        return otrosEstudios;
    }

    public void setOtrosEstudios(String otrosEstudios) {
        this.otrosEstudios = otrosEstudios;
    }

    public String getBecasObtenidas() {
        return becasObtenidas;
    }

    public void setBecasObtenidas(String becasObtenidas) {
        this.becasObtenidas = becasObtenidas;
    }

    public String getCorrespondenciaFisica() {
        return correspondenciaFisica;
    }

    public void setCorrespondenciaFisica(String correspondenciaFisica) {
        this.correspondenciaFisica = correspondenciaFisica;
    }

    public String getLugarCobranza() {
        return lugarCobranza;
    }

    public void setLugarCobranza(String lugarCobranza) {
        this.lugarCobranza = lugarCobranza;
    }

    public String getTemasGustariaRecibirCapacitacion() {
        return temasGustariaRecibirCapacitacion;
    }

    public void setTemasGustariaRecibirCapacitacion(String temasGustariaRecibirCapacitacion) {
        this.temasGustariaRecibirCapacitacion = temasGustariaRecibirCapacitacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getActividadesCulturales() {
        return actividadesCulturales;
    }

    public void setActividadesCulturales(String actividadesCulturales) {
        this.actividadesCulturales = actividadesCulturales;
    }

    public List<FichaComiteDTO> getComites() {
        return comites;
    }

    public void setComites(List<FichaComiteDTO> comites) {
        this.comites = comites;
    }

    public List<FichaInstitucionDTO> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<FichaInstitucionDTO> instituciones) {
        this.instituciones = instituciones;
    }

    public List<FichaDeporteDTO> getDeportes() {
        return deportes;
    }

    public void setDeportes(List<FichaDeporteDTO> deportes) {
        this.deportes = deportes;
    }

    public Boolean getTieneError() {
        return tieneError;
    }

    public void setTieneError(Boolean tieneError) {
        this.tieneError = tieneError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

}
