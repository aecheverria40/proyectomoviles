package com.example.abiel.pmoviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;


public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        list = findViewById(R.id.list);

        list.setAdapter(arrayAdapter);
        //getPosts2(arrayAdapter);
        getPosts(arrayAdapter);
        //getUsuarios(arrayAdapter);

    }

    private void getPosts(final ArrayAdapter arrayAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.94:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call< List<CoordinadorModel> > call = postService.getPost();

        call.enqueue(new Callback<List<CoordinadorModel>>() {
            @Override
            public void onResponse(Call<List<CoordinadorModel>> call, Response<List<CoordinadorModel>> response) {
                for(CoordinadorModel post : response.body()) {
                    titles.add(post.getNombreCoordinador() + " " + post.getApellidoPaternoCoordinador());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CoordinadorModel>> call, Throwable t) {
            }
        });
    }

    private void getUsuarios(final ArrayAdapter arrayAdapter){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://webapi.casasredposventa.com").
                addConverterFactory(GsonConverterFactory.create()).build();

        GetService getService = retrofit.create(GetService.class);
        Call<List<UsuarioModel>> call = getService.getUsuarios();

        call.enqueue(new Callback<List<UsuarioModel>>() {
            @Override
            public void onResponse(Call<List<UsuarioModel>> call, Response<List<UsuarioModel>> response) {
                for (UsuarioModel post : response.body()){
                    titles.add(post.getUsu_nombre() + post.getUsu_apellidoPa());
                    //arrayAdapter.notifyDataSetChanged();

                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<UsuarioModel>> call, Throwable t) {

            }
        });
    }

    private void getPosts2(final ArrayAdapter arrayAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService2 postService = retrofit.create(PostService2.class);
        Call< List<Post> > call = postService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for(Post post : response.body()) {
                    titles.add(post.getTitle());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }
}
