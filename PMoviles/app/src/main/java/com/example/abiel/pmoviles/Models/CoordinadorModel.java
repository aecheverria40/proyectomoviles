package com.example.abiel.pmoviles.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CoordinadorModel extends UsuarioModel {
    //public int IdCoordinador;
    @SerializedName("apellidoMaternoCoordinador")
    @Expose
    public String apellidoMaternoCoordinador;
    @SerializedName("apellidoPaternoCoordinador")
    @Expose
    public String apellidoPaternoCoordinador;
    @SerializedName("direccionCoordinador")
    @Expose
    public String direccionCoordinador;
    @SerializedName("email_Coordinador")
    @Expose
    public String email_Coordinador;
    @SerializedName("nombreCoordinador")
    @Expose
    public String nombreCoordinador;
    @SerializedName("telefonoCoordinador")
    @Expose
    public String telefonoCoordinador;
    @SerializedName("user")
    @Expose
    public UsuarioModel user;

    public CoordinadorModel(int Id){
        id = Id;
    }

    public UsuarioModel getUser() {
        return user;
    }

    public void setUser(UsuarioModel user) {
        this.user = user;
    }

//    public int getIdCoordinador() {
//        return IdCoordinador;
//    }
//
//    public void setIdCoordinador(int idCoordinador) {
//        IdCoordinador = idCoordinador;
//    }

    public String getApellidoMaternoCoordinador() {
        return apellidoMaternoCoordinador;
    }

    public void setApellidoMaternoCoordinador(String apellidoMaternoCoordinador) {
        this.apellidoMaternoCoordinador = apellidoMaternoCoordinador;
    }

    public String getApellidoPaternoCoordinador() {
        return apellidoPaternoCoordinador;
    }

    public void setApellidoPaternoCoordinador(String apellidoPaternoCoordinador) {
        this.apellidoPaternoCoordinador = apellidoPaternoCoordinador;
    }

    public String getDireccionCoordinador() {
        return direccionCoordinador;
    }

    public void setDireccionCoordinador(String direccionCoordinador) {
        this.direccionCoordinador = direccionCoordinador;
    }

    public String getEmail_Coordinador() {
        return email_Coordinador;
    }

    public void setEmail_Coordinador(String email_Coordinador) {
        this.email_Coordinador = email_Coordinador;
    }

    public String getNombreCoordinador() {
        return nombreCoordinador;
    }

    public void setNombreCoordinador(String nombreCoordinador) {
        this.nombreCoordinador = nombreCoordinador;
    }

    public String getTelefonoCoordinador() {
        return telefonoCoordinador;
    }

    public void setTelefonoCoordinador(String telefonoCoordinador) {
        this.telefonoCoordinador = telefonoCoordinador;
    }

    public String toString()
    {
        return getNombreCoordinador() + " " + getApellidoPaternoCoordinador();
    }
}
