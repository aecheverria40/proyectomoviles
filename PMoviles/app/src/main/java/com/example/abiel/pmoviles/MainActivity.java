package com.example.abiel.pmoviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.abiel.pmoviles.Interface.UsuarioService;
import com.example.abiel.pmoviles.Interface.CoordinadorService;
import com.example.abiel.pmoviles.Models.CoordinadorModel;
import com.example.abiel.pmoviles.Models.UsuarioModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
        getCoordinadores(arrayAdapter);

    }

    private void getCoordinadores(final ArrayAdapter arrayAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://alejandro123.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CoordinadorService coordinadorService = retrofit.create(CoordinadorService.class);
        Call< List<CoordinadorModel> > call = coordinadorService.getCoordinador();

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
}
