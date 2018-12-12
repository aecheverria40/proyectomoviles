package com.example.abiel.pmoviles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        ArrayAdapter <CoordinadorModel> arrayAdapter = new ArrayAdapter<CoordinadorModel>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, titles);

        list = view.findViewById(R.id.list);

        mcoordinadorService = ApiUtils.getAPIServiceCoordinador();

        list.setAdapter(arrayAdapter);

        getCoordinador2(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CoordinadorModel coordinador = (CoordinadorModel) parent.getItemAtPosition(position);

                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        coordinador.getApellidoMaternoCoordinador() + coordinador.getId(), Toast.LENGTH_SHORT);

                toast.show();

                Integer coordinador_id = coordinador.getId();
                String co_id = coordinador_id.toString();

                //Fragmento de EditCoordinador
                EditCoordinadorFragment editCoordinadorFragment = new EditCoordinadorFragment();
                Bundle args = new Bundle();

                args.putString("Id", co_id);
                args.putString("nombre", coordinador.getNombreCoordinador());
                args.putString("apellidopa", coordinador.getApellidoPaternoCoordinador());
                args.putString("apellidoma", coordinador.getApellidoMaternoCoordinador());
                args.putString("direccion", coordinador.getDireccionCoordinador());
                args.putString("telefono", coordinador.getTelefonoCoordinador());
                args.putString("correo", coordinador.getEmail_Coordinador());

                editCoordinadorFragment.setArguments(args);
//                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new EditCoordinadorFragment(), null).commit();
//                getFragmentManager().beginTransaction().add(R.id.fragment_container, editCoordinadorFragment).commit();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().
                        replace(R.id.fragment_container, editCoordinadorFragment,
                                null).addToBackStack(null).commit();

            }
        });


        return view;
    }

    private void getCoordinador2(final ArrayAdapter arrayAdapter){
        mcoordinadorService.getCoordinador().enqueue(new Callback<List<CoordinadorModel>>() {
            @Override
            public void onResponse(Call<List<CoordinadorModel>> call, Response<List<CoordinadorModel>> response) {
                for (CoordinadorModel get : response.body()){
                    titles.add(get);
                    //titles.add(get);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CoordinadorModel>> call, Throwable t) {

            }
        });
    }

}

