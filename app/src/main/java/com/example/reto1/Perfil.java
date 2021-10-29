package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.reto1.databinding.FragmentPerfilBinding;

public class Perfil extends Fragment {

    private FragmentPerfilBinding binding;

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
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.editBtn.setOnClickListener(
                v-> {
                    Toast.makeText(getActivity(), "Aqui editarias", Toast.LENGTH_SHORT).show();
                }
        );
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
}