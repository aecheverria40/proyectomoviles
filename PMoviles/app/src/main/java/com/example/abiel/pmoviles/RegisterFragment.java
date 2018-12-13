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
import com.example.abiel.pmoviles.Interface.UsuarioService;
import com.example.abiel.pmoviles.Models.UsuarioModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private TextView mResponseTv;
    private UsuarioService musuarioService;
    private static final String TAG = Main2Activity.class.getName();

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText user_name = (EditText) view.findViewById(R.id.usu_username);
        final EditText user_email = (EditText) view.findViewById(R.id.usu_email);
        final EditText user_email2 = (EditText) view.findViewById(R.id.usu_email2);
        final EditText user_password = (EditText) view.findViewById(R.id.usu_password);

        Button submitBtn = (Button) view.findViewById(R.id.btn_submit);
        mResponseTv = (TextView) view.findViewById(R.id.tv_response);

        musuarioService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lo del tutorial
//                String title = titleEt.getText().toString().trim();
//                String body = bodyEt.getText().toString().trim();

                //Los mios
                String username = user_name.getText().toString().trim();
                String email= user_email.getText().toString().trim();
                String email2 = user_email2.getText().toString().trim();
                String password=user_password.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    sendPost(username,email,email2,password);
                }
            }
        });

        return view;
    }

    public void sendPost(String username,String email, String email2,String password){
        musuarioService.postUsuario(username,email,email2,password).enqueue(new Callback<UsuarioModel>() {
            @Override
            public void onResponse(Call<UsuarioModel> call, Response<UsuarioModel> response) {
                String msg;
                if(response.isSuccessful()){
                    msg = response.raw().toString();
                    showResponse(msg);
                    Log.i(TAG, "post submitted to API" + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<UsuarioModel> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response){
        if(mResponseTv.getVisibility() == View.GONE){
            mResponseTv.setVisibility(View.VISIBLE);
        }

        mResponseTv.setText(response);
    }

}