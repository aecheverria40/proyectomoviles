package com.example.abiel.pmoviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abiel.pmoviles.Interface.ApiUtils;
import com.example.abiel.pmoviles.Interface.UsuarioService;
import com.example.abiel.pmoviles.Models.UsuarioModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    private TextView mResponseTv;
    private UsuarioService musuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Los del tutorial
        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);

        //Los mios merengues
        final EditText user_name = (EditText) findViewById(R.id.usu_username);
        final EditText user_email = (EditText) findViewById(R.id.usu_email);
        final EditText user_password = (EditText) findViewById(R.id.usu_password);

        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        musuarioService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lo del tutorial
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();

                //Los mios
                String username = user_name.getText().toString().trim();
                String email= user_email.getText().toString().trim();
                String password=user_password.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    sendPost(username,email,password);
                }
            }
        });

    }
    public void sendPost(String username,String email,String password){
        musuarioService.postUsuario(username,email,password).enqueue(new Callback<UsuarioModel>() {
            @Override
            public void onResponse(Call<UsuarioModel> call, Response<UsuarioModel> response) {

            }

            @Override
            public void onFailure(Call<UsuarioModel> call, Throwable t) {

            }
        });
    }

}
