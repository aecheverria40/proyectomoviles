package com.example.abiel.pmoviles.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CoordinadorModel extends UsuarioModel {
//    @SerializedName("id")
//    @Expose
//    private Integer id;
    @SerializedName("apellidoMaternoCoordinador")
    @Expose
    private String apellidoMaternoCoordinador;
    @SerializedName("apellidoPaternoCoordinador")
    @Expose
    private String apellidoPaternoCoordinador;
    @SerializedName("direccionCoordinador")
    @Expose
    private String direccionCoordinador;
    @SerializedName("email_Coordinador")
    @Expose
    private String email_Coordinador;
    @SerializedName("nombreCoordinador")
    @Expose
    private String nombreCoordinador;
    @SerializedName("telefonoCoordinador")
    @Expose
    private String telefonoCoordinador;
    @SerializedName("user")
    @Expose
    private UsuarioModel user;

    public UsuarioModel getUser() {
        return user;
    }

    public void setUser(UsuarioModel user) {
        this.user = user;
    }

//    public int getCoorId() {
//        return id;
//    }
//
//    public void setCoorId(int Id) {
//        this.id = Id;
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

    @Override
    public String toString() {
        if (getNombreCoordinador().isEmpty()) {
            return getId() + " - " + "Sin Nombre";
        } else {
            return getId() + " - " + getNombreCoordinador();

        }
    }
}
