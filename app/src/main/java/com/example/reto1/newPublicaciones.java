package com.example.reto1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.reto1.databinding.FragmentNewPublicacionesBinding;

public class newPublicaciones extends Fragment {

    private Event evento;
    private int state;

    private FragmentNewPublicacionesBinding binding;

    private OnNewPublicacionesListener listener = null;

    public newPublicaciones() {
        // Required empty public constructor
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
                    listener.onNewPublicaciones(new Event(nombre, inicio, fin));
                    Log.e(">>>>","te odio:enviandooo");
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