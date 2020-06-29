package com.example.smarthomeautomation;


import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    CardView Camera, Fans, Sensors, lightbulb, fridge;
    ImageView bulblighton;
    DatabaseReference dref= FirebaseDatabase.getInstance().getReference();
    TextView temperature, humidity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        lightbulb = v.findViewById( R.id.lights );
        Camera = v.findViewById( R.id.camera );
        Sensors = v.findViewById( R.id.sensors );
        Fans = v.findViewById( R.id.fans );
        temperature = v.findViewById( R.id.txttemp );
        humidity = v.findViewById( R.id.txthum );
        dref.child("sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    String temp = dataSnapshot.child("Temperature").getValue().toString();
                    String hum = dataSnapshot.child("Humidity").getValue().toString();

                    temperature.setText(temp+" °C");
                    humidity.setText(hum+" %");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        lightbulb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity() , ButtonActivity.class);
                i.putExtra("type" , "light");
                startActivity(i);
            }
        } );
        Fans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity() , ButtonActivity.class);
                i.putExtra("type" , "fan");
                startActivity(i);
            }
        });

        Sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity() , SensorsActivity.class);
                // i.putExtra("type" , "tv");
                startActivity(i);
            }
        });
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity() , LiveCamera.class);
                startActivity(i);
            }
        });
        return v;
    }

}
