package com.example.abiel.pmoviles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.abiel.pmoviles.Interface.ApiUtils;
import com.example.abiel.pmoviles.Interface.DocenteService;
import com.example.abiel.pmoviles.Models.CoordinadorModel;
import com.example.abiel.pmoviles.Models.DocenteModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DocenteFragment extends Fragment {
    private DocenteService mdocenteService;

    ListView list;
    ArrayList<DocenteModel> titles = new ArrayList<>();

    public DocenteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_docente, container, false);

        //Cliente para Get
        ArrayAdapter<DocenteModel> arrayAdapter = new ArrayAdapter<DocenteModel>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, titles);

        list = view.findViewById(R.id.list);

        mdocenteService = ApiUtils.getAPIServiceDocente();

        list.setAdapter(arrayAdapter);

        getDocentes(arrayAdapter);

        //Click manda a editar
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DocenteModel docenteModel = (DocenteModel) parent.getItemAtPosition(position);

                Integer do_id = docenteModel.getId();
                String docente_id = do_id.toString();

                //Enviar los valores que existen al fragmento Editar
                EditDocenteFragment editDocenteFragment = new EditDocenteFragment();
                Bundle args = new Bundle();
                args.putString("Id", docente_id);
                args.putString("apellidoma", docenteModel.getApellidoMaterno());
                args.putString("apellidopa", docenteModel.getApellidoPaternoDocente());
                args.putString("nombre", docenteModel.getNombreDocente());
                args.putString("direccion", docenteModel.getDireccionDocente());
                args.putString("correo", docenteModel.getEmailDocente());
                args.putString("telefono", docenteModel.getTelefonoDocente());

                editDocenteFragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().
                        replace(R.id.fragment_container, editDocenteFragment,
                                null).addToBackStack(null).commit();
            }
        });

        return view;
    }

    private void getDocentes(final ArrayAdapter arrayAdapter){
        mdocenteService.getDocente().enqueue(new Callback<List<DocenteModel>>() {
            @Override
            public void onResponse(Call<List<DocenteModel>> call, Response<List<DocenteModel>> response) {
                for (DocenteModel get : response.body()){
                    titles.add(get);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DocenteModel>> call, Throwable t) {

            }
        });
    }

}
