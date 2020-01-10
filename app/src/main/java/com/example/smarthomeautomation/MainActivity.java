package com.example.smarthomeautomation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class MainActivity extends BaseClass {
    CardView airconditionar, tv, router, washmachine, lightbulb, fridge;
    ImageView bulblighton;
    DatabaseReference dref= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
      //  setContentView( R.layout.activity_main );
        lightbulb = findViewById( R.id.cardBulb );
        airconditionar = findViewById( R.id.ac );
        washmachine = findViewById( R.id.washingmachine );
        tv = findViewById( R.id.tv );
        router = findViewById( R.id.wifi );
        fridge = findViewById( R.id.cardFrig );

        lightbulb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "light");
                startActivity(i);
            }
        } );
        airconditionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "ac");
                startActivity(i);
            }
        });
        washmachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "washingmachine");
                startActivity(i);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "tv");
                startActivity(i);
            }
        });
        router.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "router");
                startActivity(i);
            }
        });
        fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ButtonActivity.class);
                i.putExtra("type" , "fridge");
                startActivity(i);
            }
        });


    }

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_home;
    }
}
