package com.example.abiel.pmoviles.Interface;

public class ApiUtils {
    private ApiUtils(){}

    public static String BASE_URL = "http://alejandro123.pythonanywhere.com/";

    //Modelo Usuario
    public  static UsuarioService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(UsuarioService.class);
    }

    //Modelo Coordinador
    public static CoordinadorService getAPIServiceCoordinador(){
        return RetrofitClient.getClient(BASE_URL).create(CoordinadorService.class);
    }

}
