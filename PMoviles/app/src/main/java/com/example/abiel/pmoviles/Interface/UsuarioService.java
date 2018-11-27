package com.example.abiel.pmoviles.Interface;
import com.example.abiel.pmoviles.Models.UsuarioModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuarioService {
    String API_ROUT = "api/usuarios";
    String API_POST = "api/register";

    @GET(API_ROUT)
    Call<List<UsuarioModel>> getUsuarios();

    @POST(API_POST)
    @FormUrlEncoded
    Call<UsuarioModel> postUsuario(@Field("username") String username,
                                   @Field("email") String email,
                                   @Field("password") String password);
}
