package com.example.abiel.pmoviles.Interface;
import com.example.abiel.pmoviles.Models.UsuarioModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuarioService {
    String API_ROUT = "/api/usuarios";

    @GET(API_ROUT)
    Call<List<UsuarioModel>> getUsuarios();

    @POST(API_ROUT)
    Call<UsuarioModel> postUsuario(@Body UsuarioModel usuarioModel);
}
