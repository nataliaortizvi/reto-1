package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
                    Log.e("Estado enviado", ""+state);
                    Toast.makeText(getActivity(), "Aqui editarias", Toast.LENGTH_SHORT).show();
                    listener.onPerfil(state);
                }
        );
        return view;
    }

    public void setListener (OnPerfilListener listener) {
        this.listener =listener;
    }

    public interface OnPerfilListener {
        void onPerfil(int change);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}