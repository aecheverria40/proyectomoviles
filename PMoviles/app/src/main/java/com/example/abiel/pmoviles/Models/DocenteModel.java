package com.example.abiel.pmoviles.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocenteModel extends UsuarioModel{
    @SerializedName("user")
    @Expose
    public UsuarioModel user;
    @SerializedName("apellidoPaternoDocente")
    @Expose
    public String apellidoPaternoDocente;
    @SerializedName("apellidoMaterno")
    @Expose
    public String apellidoMaterno;
    @SerializedName("nombreDocente")
    @Expose
    public String nombreDocente;
    @SerializedName("direccionDocente")
    @Expose
    public String direccionDocente;
    @SerializedName("telefonoDocente")
    @Expose
    public String telefonoDocente;
    @SerializedName("emailDocente")
    @Expose
    public String emailDocente;
    //Cambiar a Escuelas
    @SerializedName("escuelas")
    @Expose
    public int escuelas;

    public UsuarioModel getUser() {
        return user;
    }

    public void setUser(UsuarioModel user) {
        this.user = user;
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

    @Override
    public String toString()
    {

        if (getNombreDocente().isEmpty()) {
            return getId() + " - " + "Sin Nombre";
        } else {
            return getId() + " - " + getNombreDocente();

        }
    }
}
