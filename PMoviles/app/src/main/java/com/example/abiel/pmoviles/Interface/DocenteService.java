package com.example.abiel.pmoviles.Interface;


import com.example.abiel.pmoviles.Models.DocenteModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface DocenteService {
    String API_ROUT = "/api/docente/";
    String API_PUT = "/api/docente/{id}";

    @GET(API_ROUT)
    Call<List<DocenteModel>> getDocente();

    @POST(API_ROUT)
    @FormUrlEncoded
    Call<DocenteModel> postDocente(@Field("idDocente") String IdDocente,
                                   @Field("apellidoPaternoDocente") String apellidoPaternoDocente,
                                   @Field("apellidoMaterno") String apellidoMaterno,
                                   @Field("nombreDocente") String nombreDocente,
                                   @Field("direccionDocente") String direccionDocente,
                                   @Field("telefonoDocente") String telefonoDocente,
                                   @Field("emailDocente") String emailDocente
    );

    @PUT(API_PUT)
    @FormUrlEncoded
    Call<DocenteModel> putDocente(@Path("idDocente") String IdDocente,
                                  @Field("apellidoPaternoDocente") String apellidoPaternoDocente,
                                  @Field("apellidoMaterno") String apellidoMaterno,
                                  @Field("nombreDocente") String nombreDocente,
                                  @Field("direccionDocente") String direccionDocente,
                                  @Field("telefonoDocente") String telefonoDocente,
                                  @Field("emailDocente") String emailDocente
    );
}
