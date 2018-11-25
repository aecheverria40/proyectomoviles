package com.example.abiel.pmoviles.Interface;
//Las librerias ocupadas
import com.example.abiel.pmoviles.Models.CoordinadorModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoordinadorService {
    String API_ROUT = "/api/coordinador/";

    @GET(API_ROUT)
    Call<List<CoordinadorModel>> getCoordinador();
}
