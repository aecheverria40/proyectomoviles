package com.example.abiel.pmoviles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abiel.pmoviles.Interface.ApiUtils;
import com.example.abiel.pmoviles.Interface.CoordinadorService;
import com.example.abiel.pmoviles.Models.CoordinadorModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoordinadoresFragment extends Fragment {
    private Toolbar toolbar;
    private CoordinadorService mcoordinadorService;

    ListView list;
    ArrayList<CoordinadorModel> titles = new ArrayList<>();

    //ArrayList<CoordinadorModel> coorinadores = new ArrayList<>();

    public CoordinadoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coordinadores, container, false);

        //Cliente para Get
        ArrayAdapter <CoordinadorModel> arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, titles);

        list = view.findViewById(R.id.list);

        mcoordinadorService = ApiUtils.getAPIServiceCoordinador();

        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String title= parent.getItemAtPosition(position).getTitle();
                //id = parent.getItemAtPosition(position).;

                Toast toast = Toast.makeText(getActivity().getApplicationContext(),position + 1,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        getCoordinador2(arrayAdapter);
        return view;
    }

    private void getCoordinador2(final ArrayAdapter arrayAdapter){
        mcoordinadorService.getCoordinador().enqueue(new Callback<List<CoordinadorModel>>() {
            @Override
            public void onResponse(Call<List<CoordinadorModel>> call, Response<List<CoordinadorModel>> response) {
                for (CoordinadorModel get : response.body()){
                    //titles.add(get.getNombreCoordinador() + " " + get.getUser().username);
                    titles.add(get);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CoordinadorModel>> call, Throwable t) {

            }
        });
    }


}
