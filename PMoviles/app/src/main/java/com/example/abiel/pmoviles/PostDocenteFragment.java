package com.example.abiel.pmoviles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abiel.pmoviles.Interface.ApiUtils;
import com.example.abiel.pmoviles.Interface.UsuarioService;
/*NO TERMINADO*/

/**
 * A simple {@link Fragment} subclass.
 */
public class PostDocenteFragment extends Fragment {
    private TextView mResponseTv;
    private UsuarioService musuarioService;
    private static final String TAG = Main2Activity.class.getName();

    public PostDocenteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_docente, container, false);

        final EditText id_docente = (EditText) view.findViewById(R.id.Id_docente);
        final EditText nombre_docente = (EditText) view.findViewById(R.id.docente_nombre);
        final EditText apellidopa_docente = (EditText) view.findViewById(R.id.docente_apellidoPa);
        final EditText apellidoma_docente = (EditText) view.findViewById(R.id.docente_apellidoPa);
        final EditText correo_docente = (EditText) view.findViewById(R.id.docente_email);
        final EditText telefono_docente = (EditText) view.findViewById(R.id.docente_telefono);
        final EditText direccion_docente = (EditText) view.findViewById(R.id.docente_direccion);

        Button submitBtn = (Button) view.findViewById(R.id.btn_submit);
        mResponseTv = (TextView) view.findViewById(R.id.tv_response);

        musuarioService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = id_docente.getText().toString().trim();
                String nombre = nombre_docente.getText().toString().trim();
                String apellidopa = apellidopa_docente.getText().toString().trim();
                String apellidoma = apellidoma_docente.getText().toString().trim();
                String correo = correo_docente.getText().toString().trim();
                String telefono = telefono_docente.getText().toString().trim();
                String direccion = direccion_docente.getText().toString().trim();
            }
        });

        return view;
    }

}
