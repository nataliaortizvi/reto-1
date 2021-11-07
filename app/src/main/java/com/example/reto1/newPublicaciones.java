package com.example.reto1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.reto1.databinding.FragmentNewPublicacionesBinding;
import com.google.gson.Gson;

public class newPublicaciones extends Fragment implements View.OnClickListener {

    private FragmentNewPublicacionesBinding binding;

    private OnNewPublicacionesListener listener = null;
    private onNewUbiListener observer;

    private EventAdapter adapter;
    private Bitmap bitmap;

    private int state;

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
        state = 0;
        // Inflate the layout for this fragment
        binding = FragmentNewPublicacionesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnCrear.setOnClickListener(this);
        binding.btnAddUbi.setOnClickListener(this);

        return view;
    }

    //COSAS DE PATRON OBSERVER (FRAGMENTO OBSERVADO)
    //1. SUBSCRIPCION
    public void setListener (OnNewPublicacionesListener listener) {
        this.listener = listener;
    }

    public void setListener (onNewUbiListener observer) {
        this.observer = observer;
    }

    //2. INTERFAZ
    public interface OnNewPublicacionesListener {
        void onNewPublicaciones(Event nuevo);
    }

    public interface onNewUbiListener {
        void onNewUbi(int newUbi);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCrear:
                String nombre = binding.nombreEventTxt.getText().toString();
                String inicio = binding.horaInicioTxt.getText().toString();
                String fin = binding.horaFinTxt.getText().toString();

                SharedPreferences preferences = this.getActivity().getSharedPreferences("losRestaurantes", MODE_PRIVATE);
                String json = preferences.getString("res", "NO_OBJ");
                if(!json.equals("NO_OBJ")){
                    Gson gson = new Gson();
                    Restaurante elRestaurante = gson.fromJson(json, Restaurante.class);

                    //Recibe la imagen del sharedPreference
                    SharedPreferences phPreferences = getActivity().getSharedPreferences("photoShared", MODE_PRIVATE);
                    String thePhoto = phPreferences.getString("imageData", "NO_PHOTO");
                    if (!thePhoto.equals("NO_PHOTO")) {
                        byte[] b = Base64.decode(thePhoto, Base64.DEFAULT);
                        bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                    }

                    //Crea el objeto Evento
                    Event elEvento = new Event (nombre, elRestaurante.getNombreRestaurante(), inicio, fin, bitmap);

                    Gson gson1 = new Gson();
                    String json1 = gson.toJson(elEvento);

                    //LocalStorage = sharedPreferences
                    SharedPreferences preferences1 = this.getActivity().getSharedPreferences("lasPublicaciones", MODE_PRIVATE);
                    preferences1.edit().putString("pub", json1).apply();

                    listener.onNewPublicaciones(elEvento);
                    Toast.makeText(getActivity(), "ir a publicaciones", Toast.LENGTH_SHORT).show();

                    Log.e(">>>>","SE FUEEEE");
                    //getActivity().onBackPressed();
                }
                break;
            case R.id.btnAddUbi:
                state = 2;
                observer.onNewUbi(state);
                Toast.makeText(getActivity(), ""+state, Toast.LENGTH_SHORT).show();
        }
    }


}