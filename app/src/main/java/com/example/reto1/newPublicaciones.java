package com.example.reto1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.reto1.databinding.FragmentNewPublicacionesBinding;
import com.google.gson.Gson;

public class newPublicaciones extends Fragment {

    private FragmentNewPublicacionesBinding binding;

    private OnNewPublicacionesListener listener = null;

    private EventAdapter adapter;

    public newPublicaciones() {
        // Required empty public constructor
        adapter = new EventAdapter();
    }

    public static newPublicaciones newInstance() {
        newPublicaciones fragment = new newPublicaciones();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewPublicacionesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnCrear.setOnClickListener(
                v->{

                    String nombre = binding.nombreEventTxt.getText().toString();
                    String inicio = binding.horaInicioTxt.getText().toString();
                    String fin = binding.horaFinTxt.getText().toString();

                    SharedPreferences preferences = this.getActivity().getSharedPreferences("losRestaurantes", Context.MODE_PRIVATE);
                    String json = preferences.getString("res", "NO_OBJ");
                    if(!json.equals("NO_OBJ")){
                        Gson gson = new Gson();
                        Restaurante elRestaurante = gson.fromJson(json, Restaurante.class);

                        Event elEvento = new Event (nombre, elRestaurante.getNombreRestaurante(), inicio, fin);

                        Gson gson1 = new Gson();
                        String json1 = gson.toJson(elEvento);

                        //LocalStorage = sharedPreferences
                        SharedPreferences preferences1 = this.getActivity().getSharedPreferences("lasPublicaciones", Context.MODE_PRIVATE);
                        preferences1.edit().putString("pub", json1).apply();

                        listener.onNewPublicaciones(elEvento);
                        Toast.makeText(getActivity(), "ir a publicaciones", Toast.LENGTH_SHORT).show();

                        Log.e(">>>>","SE FUEEEE");
                        //getActivity().onBackPressed();
                    }


                }
        );

        return view;
    }

    //COSAS DE PATRON OBSERVER (FRAGMENTO OBSERVADO)
    //1. SUBSCRIPCION
    public void setListener (OnNewPublicacionesListener listener) {
        this.listener = listener;
    }

    //2. INTERFAZ
    public interface OnNewPublicacionesListener {
        void onNewPublicaciones(Event nuevo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}