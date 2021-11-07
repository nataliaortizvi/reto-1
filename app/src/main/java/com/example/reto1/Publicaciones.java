package com.example.reto1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reto1.databinding.FragmentPublicacionesBinding;

public class Publicaciones extends Fragment implements newPublicaciones.OnNewPublicacionesListener{

    private int state;

    private FragmentPublicacionesBinding binding;

    private OnPublicacionesListener listener = null;

    private EventAdapter adapter;

    private SharedPreferences preferences;


    public Publicaciones() {
        // Required empty public constructor
        adapter = new EventAdapter();
    }

    public static Publicaciones newInstance() {
        Publicaciones fragment = new Publicaciones();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        state = 0;
        binding = FragmentPublicacionesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Log.e(">>>>","numero de arreglo:  "+ adapter.getItemCount());

        if(adapter.getItemCount() == 0){
            binding.noHayPubs.setVisibility(View.VISIBLE);
        }else{
            binding.noHayPubs.setVisibility(View.GONE);
            binding.btnCrearNew.setVisibility(View.GONE);

            binding.btnCrearNew2.setVisibility(View.VISIBLE);
            binding.publicacionesRecycler.setVisibility(View.VISIBLE);
        }

        RecyclerView publicacionesRecycler = binding.publicacionesRecycler;
        publicacionesRecycler.setHasFixedSize(true);
        publicacionesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        publicacionesRecycler.setAdapter(adapter);


        //CLICK
        binding.btnCrearNew.setOnClickListener(
                v->{
                    state = 1;
                    //Toast.makeText(getActivity(), "odio esto con mi vida", Toast.LENGTH_SHORT).show();
                    listener.onPublicaciones(state);
                }
        );

        binding.btnCrearNew2.setOnClickListener(
                v->{
                    state = 1;
                    listener.onPublicaciones(state);
                }
        );


        return view;
    }

    //COSAS DE PATRON OBSERVER (FRAGMENTO OBSERVADO)
    //1. SUBSCRIPCION
    public void setListener (OnPublicacionesListener listener) {
        this.listener = listener;
    }

    @Override
    public void onNewPublicaciones(Event nuevo) {
        Log.e(">>>>","LLEGOOOOOOOO: "+nuevo.getNombreEvent());
        adapter.addEvent(nuevo);

        /*SharedPreferences preferences = this.getActivity().getSharedPreferences("lasPublicaciones", Context.MODE_PRIVATE);
        String json = preferences.getString("pub", "NO_OBJ");
        if(!json.equals("NO_OBJ")){
            Gson gson = new Gson();
            Event elEvento = gson.fromJson(json, Event.class);
            adapter.addEvent(elEvento);
        }*/
    }

    //2. INTERFAZ
    public interface OnPublicacionesListener {
        void onPublicaciones(int change);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

