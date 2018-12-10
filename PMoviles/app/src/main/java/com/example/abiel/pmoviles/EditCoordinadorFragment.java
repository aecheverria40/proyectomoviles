package com.example.abiel.pmoviles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abiel.pmoviles.Interface.ApiUtils;
import com.example.abiel.pmoviles.Interface.CoordinadorService;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditCoordinadorFragment extends Fragment {

    private CoordinadorService mcoordinadorService;

    public EditCoordinadorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mcoordinadorService = ApiUtils.getAPIServiceCoordinador();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_coordinador, container, false);

        return view;
    }


    private void sendPutCoordinador(int id, String apellidoMaternoCoordinador, String apellidoPaternoCoordinador,
                                    String direccionCoordinador, String email_Coordinador, String nombreCoordinador,
                                    String telefonoCoordinador){

        mcoordinadorService.putCoordinador(id, apellidoMaternoCoordinador, apellidoPaternoCoordinador,
                direccionCoordinador, email_Coordinador, nombreCoordinador, telefonoCoordinador);


    }

}
