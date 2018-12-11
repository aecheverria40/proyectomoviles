package com.example.abiel.pmoviles.Interface;
//Las librerias ocupadas
import com.example.abiel.pmoviles.Models.CoordinadorModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CoordinadorService {
    String API_ROUT = "/api/coordinador/";
    String API_PUT = "/api/coordinador/{id}/";


    @GET(API_ROUT)
    Call<List<CoordinadorModel>> getCoordinador();

    @POST(API_ROUT)
    @FormUrlEncoded
    Call<CoordinadorModel> postCoordinador(@Field("apellidoPaternoCoordinador") String apellidoPaternoCoordinador,
                                           @Field("apellidoMaternoCoordinador") String apellidoMaternoCoordinador,
                                           @Field("nombreCoordinador") String nombreCoordinador,
                                           @Field("direccionCoordinador") String direccionCoordinador,
                                           @Field("telefonoCoordinador") String telefonoCoordinador,
                                           @Field("email_Coordinador") String email_Coordinador
                                           );

    @PUT(API_PUT)
    @FormUrlEncoded
    Call<CoordinadorModel> putCoordinador(@Path("id") int id,
                                            @Field("apellidoPaternoCoordinador") String apellidoPaternoCoordinador,
                                           @Field("apellidoMaternoCoordinador") String apellidoMaternoCoordinador,
                                           @Field("nombreCoordinador") String nombreCoordinador,
                                           @Field("direccionCoordinador") String direccionCoordinador,
                                           @Field("telefonoCoordinador") String telefonoCoordinador,
                                           @Field("email_Coordinador") String email_Coordinador
                                             );
}
