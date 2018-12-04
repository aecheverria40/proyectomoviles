package com.example.abiel.pmoviles.Interface;
import com.example.abiel.pmoviles.Models.UsuarioModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {
    String API_ROUT = "api/usuario/";
    String API_POST = "api/register/";
    String API_PUT = "api/register/{id}/";
    String API_DELETE = "api/register/{id}/";

    @GET(API_ROUT)
    Call<List<UsuarioModel>> getUsuarios();

    @GET(API_ROUT + "{id}")
    Call<UsuarioModel> getUsuario();

    @POST(API_POST)
    @FormUrlEncoded
    Call<UsuarioModel> postUsuario(@Field("username") String username,
                                   @Field("email") String email,
                                   @Field("email2") String email2,
                                   @Field("password") String password);

    @PUT(API_PUT)
    @FormUrlEncoded
    Call<UsuarioModel> putUsuario(@Path("id") int id,
                                  @Field("email") String email,
                                  @Field("email2") String email2,
                                  @Field("password") String password
                                  );

    @DELETE(API_DELETE)
    Call<UsuarioModel> deleteUsuarios(@Path("id") int id);
}
