package com.example.smarthomeautomation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    CardView Camera, Fans, Sensors, lightbulb, fridge;
    ImageView bulblighton;
    DatabaseReference dref= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
       setContentView( R.layout.activity_main );
        lightbulb = findViewById( R.id.lights );
        Camera = findViewById( R.id.camera );
        Sensors = findViewById( R.id.sensors );
        Fans = findViewById( R.id.fans );

        lightbulb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "light");
                startActivity(i);
            }
        } );
        Fans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "fan");
                startActivity(i);
            }
        });

        Sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , SensorsActivity.class);
               // i.putExtra("type" , "tv");
                startActivity(i);
            }
        });



    }

}
