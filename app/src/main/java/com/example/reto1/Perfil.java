package com.example.reto1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.reto1.databinding.FragmentPerfilBinding;

public class Perfil extends Fragment {

    private int state;

    private FragmentPerfilBinding binding;

    private OnPerfilListener listener = null;

    public Perfil() {
        // Required empty public constructor
    }

    public static Perfil newInstance() {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        state = 0;
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.editBtn.setOnClickListener(
                v-> {
                    state = 1;

                    //DISPARO DEL EVENTO QUE SE EJECUTA EN LA CLASE OBSERVADORA
                    listener.onPerfil(state);
                }
        );
        return view;
    }

    //COSAS DE PATRON OBSERVER (FRAGMENTO OBSERVADO)
    //1. SUBSCRIPCION
    public void setListener (OnPerfilListener listener) {
        this.listener = listener;
    }

    //2. INTERFAZ
    public interface OnPerfilListener {
        void onPerfil(int change);
    }

    //DESTRUCCION DEL FRAGMENTO
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}