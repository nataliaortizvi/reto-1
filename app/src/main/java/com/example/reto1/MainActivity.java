package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reto1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements Perfil.OnPerfilListener {

    private Perfil perfil;
    private Publicaciones publicaciones;
    private MapsFragment maps;
    private Mapa mapa;
    private editPerfil editPerfils;

    private boolean newPerfil = false;

    private ActivityMainBinding binding;

    private BottomNavigationView navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        navigationBar = binding.navigationBar;

       // navigationBar = findViewById(R.id.navigationBar);

        perfil = Perfil.newInstance();
        publicaciones = Publicaciones.newInstance();
        maps = MapsFragment.newInstance();
        mapa = Mapa.newInstance();
        editPerfils = editPerfil.newInstance();

        perfil.setListener(this::onPerfil);
        showFragment(perfil);


        navigationBar.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId()== R.id.perfilBar) {
                showFragment(perfil);
                if (newPerfil) {
                    showFragment(editPerfils);
                }

            }
            if (menuItem.getItemId()== R.id.publicationBar) {
                showFragment(publicaciones);
            }
            if (menuItem.getItemId()== R.id.mapBar) {
                showFragment(maps);
            }

            return true;
        });

    }

    public void showFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    @Override
    public void onPerfil(int change) {
        Log.e("Estado recibido", ""+change);
        if (change == 1) {
            newPerfil = true;
        }
    }
}
