package com.example.reto1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class editPerfil extends Fragment {

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
        return inflater.inflate(R.layout.fragment_edit_perfil, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}