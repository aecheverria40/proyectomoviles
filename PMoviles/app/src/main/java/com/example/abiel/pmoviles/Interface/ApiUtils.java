package com.example.abiel.pmoviles.Interface;

public class ApiUtils {
    private ApiUtils(){}

    public static String BASE_URL = "http://alejandro123.pythonanywhere.com/";

    public  static UsuarioService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(UsuarioService.class);
    }
}
