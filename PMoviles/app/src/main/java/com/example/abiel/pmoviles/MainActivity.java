package com.example.abiel.pmoviles;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
    private DrawerLayout mDrawer;
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        Menu m = navigationView.getMenu();
        SubMenu coordinador = m.addSubMenu("Coordinador");
        coordinador.add("Registrar");
        coordinador.add("Coordinadores");
        coordinador.add("Escuelas");
        coordinador.add("Maestros");
        SubMenu subMenu1 = m.addSubMenu("Maestros");
        subMenu1.add("Materias");
        subMenu1.add("Horarios");
        SubMenu alumno = m.addSubMenu("Alumno");
        alumno.add("Registrar Materia");
        alumno.add("Horario");
        alumno.add("Calificaciones");
        SubMenu opciones = m.addSubMenu("Opciones");
        alumno.add("Salir");

        setupDrawerContent(navigationView);

    }

    private void setupDrawerContent(final NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem menuItem){
        Fragment fragment = null;
        Class fragmentClass = null;
        CharSequence m = menuItem.getTitle();
        //CharSequence m2 = menuItem.getItemId();
        switch (menuItem.getTitle().toString()){
            case "Registrar":
                fragmentClass = RegisterFragment.class;
                break;

            case "Coordinadores":
                fragmentClass = CoordinadoresFragment.class;

                break;

            case "Salir":
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        //Highlight
        menuItem.setChecked(true);

        //Cerar
        //mDrawer.closeDrawers();
    }
}
