package com.example.reto1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.reto1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements Perfil.OnPerfilListener, Publicaciones.OnPublicacionesListener,
        editPerfil.OnEditPerfilListener, newPublicaciones.onNewUbiListener {

    private Perfil perfil;
    private Publicaciones publicaciones;
    private newPublicaciones newPublicacion;
    private MapsFragment maps;
    private editPerfil editPerfils;

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
        //navigationBar = findViewById(R.id.navigationBar);

        perfil = Perfil.newInstance();
        publicaciones = Publicaciones.newInstance();
        newPublicacion = newPublicaciones.newInstance();
        maps = MapsFragment.newInstance();
        editPerfils = editPerfil.newInstance();

        perfil.setListener(this::onPerfil);
        publicaciones.setListener(this::onPublicaciones);
        editPerfils.setListener(this::onEditPerfil);

        //newPublicacion.setListener(this::onNewPublicaciones);
        newPublicacion.setListener(publicaciones);
        newPublicacion.setListener(this::onNewUbi);
        showFragment(perfil);


        navigationBar.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId()== R.id.perfilBar) {
                showFragment(perfil);
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
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onPerfil(int change) {
        if (change == 1) {
            showFragment(editPerfils);
        }
    }

    @Override
    public void onPublicaciones(int change) {
        if (change == 1) {
            showFragment(newPublicacion);
        } else if (change == 3) {
            showFragment(publicaciones);
        }
    }

    @Override
    public void onEditPerfil(int change) {
        if (change == 0) {
            showFragment(perfil);
        }
    }

    @Override
    public void onNewUbi(int newUbi) {
        if (newUbi == 2) {
            showFragment(maps);
        }
    }
}
