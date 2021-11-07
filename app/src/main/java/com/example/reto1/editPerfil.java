package com.example.reto1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.reto1.databinding.FragmentEditPerfilBinding;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class editPerfil extends Fragment implements View.OnClickListener {

    private @NonNull FragmentEditPerfilBinding binding;

    private ActivityResultLauncher <Intent> cameraLauncher;

    private int state;
    private File file;


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

        binding.editarBtn.setOnClickListener(this);
        binding.btnCamera.setOnClickListener(this);

        cameraLauncher = registerForActivityResult(new StartActivityForResult(), this::onCameraResult);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editarBtn:
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
                break;

            case R.id.btnCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File (getActivity().getExternalFilesDir(null)+"/photo.png");
                Uri uri = FileProvider.getUriForFile(getActivity(),getActivity().getPackageName(),file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                //obtener ruta de la carpeta
                Log.e(">>>>>", file.toString());
                cameraLauncher.launch(intent);
        }
    }

    public void onCameraResult (ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            Bitmap thumbnail =Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()/4, bitmap.getHeight()/4, true);
            //Mostrar la miniatura en botón
            binding.btnCamera.setImageBitmap(thumbnail);

            //Convertir la imagen en String para guardar en SharedPreferences
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            SharedPreferences preferences = this.getActivity().getSharedPreferences("photoShared", Context.MODE_PRIVATE);
            preferences.edit().putString("imageData",encodedImage).apply();

        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
            Toast.makeText(getActivity(), "Operación cancelada", Toast.LENGTH_SHORT).show();
        }
    }
}