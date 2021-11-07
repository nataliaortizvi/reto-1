package com.example.reto1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.reto1.databinding.FragmentPerfilBinding;
import com.google.gson.Gson;

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

        SharedPreferences preferences = this.getActivity().getSharedPreferences("losRestaurantes", Context.MODE_PRIVATE);
        String json = preferences.getString("res", "NO_OBJ");
        if(!json.equals("NO_OBJ")){
            Gson gson = new Gson();
            Restaurante elRestaurante = gson.fromJson(json, Restaurante.class);

            binding.restauranteTxt.setText(elRestaurante.getNombreRestaurante());
            binding.descripTxt.setText(elRestaurante.getDescripRestaurante());
        }


        //click
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