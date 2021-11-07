package com.example.reto1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.reto1.databinding.FragmentEditPerfilBinding;
import com.google.gson.Gson;

public class editPerfil extends Fragment {

    private @NonNull FragmentEditPerfilBinding binding;

    private int state;

    private OnEditPerfilListener listener = null;

    public editPerfil() {
        // Required empty public constructor
    }


    public static editPerfil newInstance() {
        editPerfil fragment = new editPerfil();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        state = 1;
        binding = FragmentEditPerfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.editarBtn.setOnClickListener(
                v->{
                    state = 0;
                    String nomRestaurante = binding.restauranteTxt.getText().toString();
                    String description = binding.descripTxt.getText().toString();

                    Restaurante elRestaurante = new Restaurante(nomRestaurante,description);
                    Gson gson = new Gson();
                    String json = gson.toJson(elRestaurante);

                    //LocalStorage = sharedPreferences
                    SharedPreferences preferences = this.getActivity().getSharedPreferences("losRestaurantes", Context.MODE_PRIVATE);
                    preferences.edit().putString("res", json).apply();


                    listener.onEditPerfil(state);
                }
        );


        return view;
    }

    //COSAS DE PATRON OBSERVER (FRAGMENTO OBSERVADO)
    //1. SUBSCRIPCION
    public void setListener (editPerfil.OnEditPerfilListener listener) {
        this.listener = listener;
    }

    //2. INTERFAZ
    public interface OnEditPerfilListener {
        void onEditPerfil(int change);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}