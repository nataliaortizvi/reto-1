package com.example.reto1;

import static android.content.Context.LOCATION_SERVICE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.reto1.databinding.FragmentMapsBinding;
import com.example.reto1.databinding.FragmentPerfilBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements LocationListener, GoogleMap.OnMapClickListener {

    private Marker me;
    private LocationManager manager;
    private GoogleMap mMap;
    private Geocoder geocoder;

    private FragmentMapsBinding binding;

    private double latPos;
    private double lngPos;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            setInicialPos();
            newMarker();
        }


    };

    public static MapsFragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        manager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,2,this);

    }

    @SuppressLint("MissingPermission")
    public void setInicialPos() {
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            updateMyLocation(location);
        }
    }

    public void updateMyLocation (Location location) {
        LatLng myPos = new LatLng(location.getLatitude(), location.getLongitude());
        if (me == null) {
            me = mMap.addMarker(new MarkerOptions().position(myPos).title("Usted esta aquí"));
        } else {
            me.setPosition(myPos);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos,17));
    }

    public void newMarker () {
        mMap.setOnMapClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         //inflater.inflate(R.layout.fragment_maps, container, false);
         //binding = FragmentMapsBinding.inflate(getLayoutInflater());
         binding = FragmentMapsBinding.inflate(inflater, container, false);
         View view = binding.getRoot();

         binding.btnContMaps.setOnClickListener(
                 v -> {
                     Toast.makeText(getActivity(), latPos+","+lngPos, Toast.LENGTH_LONG).show();
                 }
         );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        updateMyLocation(location);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marcador"));
        latPos = latLng.latitude;
        lngPos = latLng.longitude;
        Log.e("latitud", latPos+"");
        Log.e("longitud", lngPos+"");
    }


}