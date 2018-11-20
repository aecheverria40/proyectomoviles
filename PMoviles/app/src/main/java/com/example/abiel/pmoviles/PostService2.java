package com.example.abiel.pmoviles;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService2 {

    String API_ROUTE = "/posts";

    @GET(API_ROUTE)
    Call< List<Post> > getPost();

}