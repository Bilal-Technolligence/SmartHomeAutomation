package com.example.smarthomeautomation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SensorsActivity extends AppCompatActivity {
    TextView temprature,humidity;
    DatabaseReference dref= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sensors );
        temprature = findViewById(R.id.txtTemprature);
        humidity = findViewById(R.id.txtHumidity);
        dref.child("sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                String temp = dataSnapshot.child("Temperature").getValue().toString();
                String hum = dataSnapshot.child("Humidity").getValue().toString();

                temprature.setText(temp+" °C");
                humidity.setText(hum+" %");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
