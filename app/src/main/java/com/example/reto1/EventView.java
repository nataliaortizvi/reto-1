package com.example.reto1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventView extends RecyclerView.ViewHolder {

    //views
    private ImageView imgFoto;
    private TextView txtNombreEvento, txtRestaurante, txtHoraInicio, txtHoraFin, direction;
    private Button btnMap;

    public EventView(View itemView) {
        super(itemView);

        imgFoto = itemView.findViewById(R.id.imgFoto);
        txtNombreEvento = itemView.findViewById(R.id.txtNombreEvento);
        txtRestaurante = itemView.findViewById(R.id.txtRestaurante);
        txtHoraInicio = itemView.findViewById(R.id.txtHoraInicio);
        txtHoraFin = itemView.findViewById(R.id.txtHoraFin);
        btnMap = itemView.findViewById(R.id.btnMap);
        direction = itemView.findViewById(R.id.direction);
    }

    public TextView getDirection() {
        return direction;
    }

    public ImageView getImgFoto() {
        return imgFoto;
    }

    public TextView getTxtNombreEvento() {
        return txtNombreEvento;
    }

    public TextView getTxtRestaurante() {
        return txtRestaurante;
    }

    public TextView getTxtHoraInicio() {
        return txtHoraInicio;
    }

    public TextView getTxtHoraFin() {
        return txtHoraFin;
    }

    public Button getBtnMap() {
        return btnMap;
    }
}
