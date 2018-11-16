package com.example.abiel.pmoviles;

import java.util.Date;

public class UsuarioModel {
    public int Id;
    public String usu_username;
    public String usu_correo;
    public String usu_nombre;
    public String usu_password;
    public String usu_apellidoPa;
    public String usu_apellidoMa;
    public Date usu_alta;
    public String usu_tipo;
    public boolean usu_activo;
    public int Id_TipoUsuario;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsu_username() {
        return usu_username;
    }

    public void setUsu_username(String usu_username) {
        this.usu_username = usu_username;
    }

    public String getUsu_correo() {
        return usu_correo;
    }

    public void setUsu_correo(String usu_correo) {
        this.usu_correo = usu_correo;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_password() {
        return usu_password;
    }

    public void setUsu_password(String usu_password) {
        this.usu_password = usu_password;
    }

    public String getUsu_apellidoPa() {
        return usu_apellidoPa;
    }

    public void setUsu_apellidoPa(String usu_apellidoPa) {
        this.usu_apellidoPa = usu_apellidoPa;
    }

    public String getUsu_apellidoMa() {
        return usu_apellidoMa;
    }

    public void setUsu_apellidoMa(String usu_apellidoMa) {
        this.usu_apellidoMa = usu_apellidoMa;
    }

    public Date getUsu_alta() {
        return usu_alta;
    }

    public void setUsu_alta(Date usu_alta) {
        this.usu_alta = usu_alta;
    }

    public String getUsu_tipo() {
        return usu_tipo;
    }

    public void setUsu_tipo(String usu_tipo) {
        this.usu_tipo = usu_tipo;
    }

    public boolean isUsu_activo() {
        return usu_activo;
    }

    public void setUsu_activo(boolean usu_activo) {
        this.usu_activo = usu_activo;
    }

    public int getId_TipoUsuario() {
        return Id_TipoUsuario;
    }

    public void setId_TipoUsuario(int id_TipoUsuario) {
        Id_TipoUsuario = id_TipoUsuario;
    }
}
