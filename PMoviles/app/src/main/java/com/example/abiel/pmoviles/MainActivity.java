package com.example.abiel.pmoviles;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.abiel.pmoviles.Interface.ApiUtils;
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
    public static FragmentManager fragmentManager;
    //private Toolbar toolbar;
    //private CoordinadorService mcoordinadorService;

    //ListView list;
    //ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CoordinadoresFragment coordinadoresFragment = new CoordinadoresFragment();

        fragmentTransaction.add(R.id.fragment_container, coordinadoresFragment, null);
        fragmentTransaction.commit();

        //Toolbar
        //toolbar = findViewById(R.id.toolBar);
        //setSupportActionBar(toolbar);

        //Cliente para Get
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        //list = findViewById(R.id.list);

        //mcoordinadorService = ApiUtils.getAPIServiceCoordinador();

        //list.setAdapter(arrayAdapter);

        //getCoordinadores(arrayAdapter);
        //getCoordinador2(arrayAdapter);

    }

//    private void getCoordinadores(final ArrayAdapter arrayAdapter) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://alejandro123.pythonanywhere.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        CoordinadorService coordinadorService = retrofit.create(CoordinadorService.class);
//        Call< List<CoordinadorModel> > call = coordinadorService.getCoordinador();
//
//        call.enqueue(new Callback<List<CoordinadorModel>>() {
//            @Override
//            public void onResponse(Call<List<CoordinadorModel>> call, Response<List<CoordinadorModel>> response) {
//                for(CoordinadorModel get : response.body()) {
//                    titles.add(get.getNombreCoordinador() + " " + get.getApellidoPaternoCoordinador() + " " +
//                    get.getUser().username);
//                }
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<CoordinadorModel>> call, Throwable t) {
//            }
//        });
//    }

//    private void getCoordinador2(final ArrayAdapter arrayAdapter){
//        mcoordinadorService.getCoordinador().enqueue(new Callback<List<CoordinadorModel>>() {
//            @Override
//            public void onResponse(Call<List<CoordinadorModel>> call, Response<List<CoordinadorModel>> response) {
//                for (CoordinadorModel get : response.body()){
//                    titles.add(get.getNombreCoordinador());
//                }
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<CoordinadorModel>> call, Throwable t) {
//
//            }
//        });
//    }
}
