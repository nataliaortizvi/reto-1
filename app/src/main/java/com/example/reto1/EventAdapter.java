package com.example.reto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventView> {

    private ArrayList<Event> events;

    public EventAdapter(){
        events = new ArrayList<>();

    }


    @NonNull
    @Override
    public EventView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.eventrow, parent, false);
        EventView holder = new EventView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventView holder, int position) {
        Event event = events.get(position);
        holder.getTxtNombreEvento().setText(event.getNombreEvent());
        holder.getTxtRestaurante().setText(event.getNombreRestaurant());
        holder.getTxtHoraInicio().setText(event.getHoraInicio());
        holder.getTxtHoraFin().setText(event.getHoraFin());

    }

    public void addEvent(Event row) {
        events.add(row);
    }



    @Override
    public int getItemCount() {
        return events.size();
    }
}
