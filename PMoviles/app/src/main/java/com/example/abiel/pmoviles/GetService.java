package com.example.abiel.pmoviles;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetService {
    String API_ROUT = "/api/usuarios";

    @GET(API_ROUT)
    Call<List<UsuarioModel>> getUsuarios();
}
