package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reto1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Perfil perfil;
    private Publicaciones publicaciones;
    private Mapa mapa;

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
        mapa = Mapa.newInstance();
        showFragment(perfil);

        navigationBar.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId()== R.id.perfilBar) {
                showFragment(perfil);
            } else if (menuItem.getItemId()== R.id.publicationBar) {
                showFragment(publicaciones);
            } else if (menuItem.getItemId()== R.id.mapBar) {
                showFragment(mapa);
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
}
