package com.example.abiel.pmoviles.Models;

public class DocenteModel {
    public int user;
    public String apellidoPaternoDocente;
    public String apellidoMaterno;
    public String nombreDocente;
    public String direccionDocente;
    public String telefonoDocente;
    public String emailDocente;
    public int escuelas;

    public int getUser() {
        return user;
    }

    public void setUser(int User) {
        user = User;
    }

    public String getApellidoPaternoDocente() {
        return apellidoPaternoDocente;
    }

    public void setApellidoPaternoDocente(String apellidoPaternoDocente) {
        this.apellidoPaternoDocente = apellidoPaternoDocente;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getDireccionDocente() {
        return direccionDocente;
    }

    public void setDireccionDocente(String direccionDocente) {
        this.direccionDocente = direccionDocente;
    }

    public String getTelefonoDocente() {
        return telefonoDocente;
    }

    public void setTelefonoDocente(String telefonoDocente) {
        this.telefonoDocente = telefonoDocente;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
    }

    public int getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(int escuelas) {
        this.escuelas = escuelas;
    }
}
