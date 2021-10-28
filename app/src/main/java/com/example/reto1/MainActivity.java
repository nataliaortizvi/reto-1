package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnBoton;
    private EditText inputNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*pedir permisos en tiempo de ejecución
        requestPermissions(new String[]{
                Manifest.permission.CALL_PHONE
        }, 0);

        */


        //referenciar
        btnBoton = findViewById(R.id.btnBoton);
        inputNombre = findViewById(R.id.inputNombre);

    }
}