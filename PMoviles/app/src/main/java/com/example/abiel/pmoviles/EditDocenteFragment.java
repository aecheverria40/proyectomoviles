package com.example.abiel.pmoviles;


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
import com.example.abiel.pmoviles.Interface.DocenteService;
import com.example.abiel.pmoviles.Models.DocenteModel;

import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDocenteFragment extends Fragment {
    private TextView mResponseTv;

    private Button btn_submit;
    private EditText Id_docente,apellidoPa,apellidoMa,NombreDocente,docente_direccion,docente_email,docente_telefono;
    private static final String TAG ="";
    private DocenteService mDocenteService;
    public EditDocenteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_docente, container, false);

        // Inflate the layout for this fragment
        final String value_id = getArguments().getString("Id_docente");
        mDocenteService = ApiUtils.getAPIServiceDocente();

        btn_submit = (Button)view.findViewById(R.id.btn_submitd);
        Id_docente = view.findViewById(R.id.Id_docente);
        apellidoPa = view.findViewById(R.id.apellidoPa);
        apellidoMa = view.findViewById(R.id.apellidoMa);
        NombreDocente = view.findViewById(R.id.NombreDocente);
        docente_direccion = view.findViewById(R.id.docente_direccion);
        docente_email = view.findViewById(R.id.docente_email);
        docente_telefono = view.findViewById(R.id.docente_telefono);

        mResponseTv = view.findViewById(R.id.tv_response);

        Id_docente.setText(value_id);
        Id_docente.setEnabled(false);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pushado docente");
                String id =Id_docente.getText().toString().trim();//Integer id = Integer.parseInt(coordinador_id.getText().toString().trim());
                String apellidoPaterno = apellidoPa.getText().toString().trim();
                String apellidoMaterno = apellidoMa.getText().toString().trim();
                String NombreDocentee = NombreDocente.getText().toString().trim();
                String Docente_Direccion = docente_direccion.getText().toString().trim();
                String Docente_Email = docente_email.getText().toString().trim();
                String Docente_Telefono = docente_telefono.getText().toString().trim();


                if (/*!TextUtils.isEmpty(id) && */!TextUtils.isEmpty(apellidoPaterno) && !TextUtils.isEmpty(apellidoMaterno) && !TextUtils.isEmpty(NombreDocentee)
                        && !TextUtils.isEmpty(Docente_Direccion)&& !TextUtils.isEmpty(Docente_Email) && !TextUtils.isEmpty(Docente_Telefono)){
                   // sendPutCoordinador(id, apellidoPaterno, apellidoMaterno, NombreDocentee, docente_direccion);
                }
            }
        });
        return view;
    }
    //Falta email y telefono too
    //terminar,  parado porque falta hacer el servicio
    private void sendPutDocente(String id,String apellidoPaterno,String apellidoMaterno,String NombreDocente, String docente_direccion,String Docente_Email,String Docente_Telefono){
        mDocenteService.putDocente(id,apellidoPaterno,apellidoMaterno,NombreDocente,docente_direccion,Docente_Telefono,Docente_Email).
                enqueue(new Callback<DocenteModel>() {
                    @Override
                    public void onResponse(Call<DocenteModel> call, Response<DocenteModel> response) {
                        String msg;
                        if(response.isSuccessful())
                        {
                            msg = response.raw().toString();
                            showResponse(msg);
                            Log.i(TAG,"post submitted to api"+ response.body().toString());
                        }
                        else{
                            msg = response.raw().toString();
                            showResponse(msg);
                            Log.i(TAG, "post not submitted to API" + response.body().toString());

                        }
                    }

                    @Override
                    public void onFailure(Call<DocenteModel> call, Throwable t) {
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
