package com.example.covid_19application.ui.clinic;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covid_19application.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClinicFragment extends Fragment implements OnMapReadyCallback {
    MapView mapView;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_clinic, container, false);

        mapView=root.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        AssetManager assetManager=getActivity().getAssets();
        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        String reader=null;
        try{
            is=assetManager.open("Clinic.csv");
            isr=new InputStreamReader(is);
            br=new BufferedReader(isr);
            while((reader=br.readLine())!=null) {
                String[] str = reader.split(",");
                str[4] = str[4].replaceAll("\\\"","");
                str[6] = str[6].replaceAll("\\\"","");
                str[7] = str[7].replaceAll("\\\"","");
//                Log.i("text1",str[6]);
//                Log.i("text2",str[7]);
//                Log.i("text3",str[4]);
                LatLng center = new LatLng(Double.parseDouble(str[6]), Double.parseDouble(str[7]));
                mMap.addMarker(new MarkerOptions().position(center).title(str[4]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        LatLng center = new LatLng(35.15619223058208, 129.05952545389732);
        mMap.addMarker(new MarkerOptions().position(center).title("부산IT교육센터"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,15));
    }
}