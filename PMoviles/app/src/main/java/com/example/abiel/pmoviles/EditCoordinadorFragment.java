package com.example.abiel.pmoviles;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abiel.pmoviles.Interface.ApiUtils;
import com.example.abiel.pmoviles.Interface.CoordinadorService;
import com.example.abiel.pmoviles.Models.CoordinadorModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditCoordinadorFragment extends Fragment {
    private TextView mResponseTv;
    private EditText nombre, apellidopa, apellidoma, telefono, correo, direccion, coordinador_id;
    private Button btn_submit;
    private static final String TAG = Main2Activity.class.getName();

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
        String value_id = getArguments().getString("Id");
        String value_nombre = getArguments().getString("nombre");
        String value_apellidopa = getArguments().getString("apellidopa");
        String value_apellidoma = getArguments().getString("apellidoma");
        String value_direccion = getArguments().getString("direccion");
        String value_telefono = getArguments().getString("telefono");
        String value_correo = getArguments().getString("correo");

        nombre = (EditText) view.findViewById(R.id.coordinator_nombre);
        apellidoma = (EditText) view.findViewById(R.id.coordinator_apellidoma);
        apellidopa = (EditText) view.findViewById(R.id.coordinator_apellidopa);
        telefono = (EditText) view.findViewById(R.id.coordinator_telefono);
        correo = (EditText) view.findViewById(R.id.coordinator_email);
        direccion = (EditText) view.findViewById(R.id.coordinator_direccion);
        coordinador_id = (EditText) view.findViewById(R.id.coordinator_id);

        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        mResponseTv = (TextView) view.findViewById(R.id.tv_response);

        coordinador_id.setText(value_id);
        coordinador_id.setEnabled(false);

        nombre.setText(value_nombre);
        apellidoma.setText(value_apellidoma);
        apellidopa.setText(value_apellidopa);
        telefono.setText(value_telefono);
        correo.setText(value_correo);
        direccion.setText(value_direccion);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "pushado");
                Integer id = Integer.parseInt(coordinador_id.getText().toString().trim());
                String nombre_data = nombre.getText().toString().trim();
                String apellidopa_data = apellidopa.getText().toString().trim();
                String apellidoma_data = apellidoma.getText().toString().trim();
                String telefono_data = telefono.getText().toString().trim();
                String correo_data = correo.getText().toString().trim();
                String direccion_data = direccion.getText().toString().trim();

                if (/*!TextUtils.isEmpty(id) && */!TextUtils.isEmpty(nombre_data) && !TextUtils.isEmpty(apellidopa_data) && !TextUtils.isEmpty(direccion_data)
                        && !TextUtils.isEmpty(apellidoma_data) && !TextUtils.isEmpty(telefono_data) && !TextUtils.isEmpty(correo_data)){
                    sendPutCoordinador(id, apellidoma_data, apellidopa_data, direccion_data, correo_data, nombre_data, telefono_data);
                }
            }
        });

        return view;
    }

    private void sendPutCoordinador(int id, String apellidoMaternoCoordinador, String apellidoPaternoCoordinador,
                                    String direccionCoordinador, String email_Coordinador, String nombreCoordinador,
                                    String telefonoCoordinador){

        mcoordinadorService.putCoordinador(id, apellidoMaternoCoordinador, apellidoPaternoCoordinador,
                direccionCoordinador,email_Coordinador , nombreCoordinador, telefonoCoordinador).
                enqueue(new Callback<CoordinadorModel>() {
                    @Override
                    public void onResponse(Call<CoordinadorModel> call, Response<CoordinadorModel> response) {
                        String msg;
                        if (response.isSuccessful()){
                            msg = response.raw().toString();
                            showResponse(msg);
                            Log.i(TAG, "post submitted to API" + response.body().toString());

                            //Caja de Dialogo
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                            builder.setMessage("Cambios guardados con exito.");
                            builder.setTitle("Exito");

                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new CoordinadoresFragment()).commit();
                                }
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }
                        else{
                            msg = response.raw().toString();
                            showResponse(msg);
                            //Log.i(TAG, "post not submitted to API" + response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<CoordinadorModel> call, Throwable t) {
                        String msg;
                        msg = "No";
                        showResponse(msg);
                        Log.e(TAG, "Unable to submit post to API.");
                    }
                });
    }

    public void showResponse(String response){
        if (mResponseTv.getVisibility() == View.GONE){
            mResponseTv.setVisibility(View.VISIBLE);
        }

        mResponseTv.setText(response);
    }

}
