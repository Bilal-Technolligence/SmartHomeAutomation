package com.example.smarthomeautomation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ButtonActivity extends AppCompatActivity {
    CardView fans, tv, light, washing, fridge, wifi , power;
    DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Intent i = getIntent();
        final String type = i.getStringExtra("type");

        fans = (CardView) findViewById(R.id.fan);
        light = (CardView) findViewById(R.id.light);
        power = (CardView) findViewById(R.id.btnPower);
        fans.setVisibility(View.GONE);
        light.setVisibility(View.GONE);
        if (type.equals("fan"))
            fans.setVisibility(View.VISIBLE);
        if (type.equals("light"))
            light.setVisibility(View.VISIBLE);
        dref.child("Button").child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue().toString();
              if(value.equals("0"))
                  power.setCardBackgroundColor(getResources().getColor(R.color.dis));
              else
                  power.setCardBackgroundColor(getResources().getColor(R.color.act));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value.equals("0")) {
                    dref.child("Button").child(type).setValue(1);
                    Toast.makeText(getApplicationContext() , type.substring(0,1).toUpperCase()+type.substring(1) +" on",Toast.LENGTH_LONG).show();
                }else {
                    dref.child("Button").child(type).setValue(0);
                    Toast.makeText(getApplicationContext() , type.substring(0,1).toUpperCase()+type.substring(1) +" off",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
