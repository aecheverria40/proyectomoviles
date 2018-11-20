package com.example.abiel.pmoviles;
//Las librerias ocupadas
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostService {
    String API_ROUT = "/api/coordinador/";

    @GET(API_ROUT)
    Call<List<CoordinadorModel>> getPost();
}
