package com.example.smarthomeautomation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends BaseClass {
    Switch airconditionar, tv, router, washmachine, lightbulb, fridge;
    ImageView bulblighton;

    DatabaseReference dref= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
      //  setContentView( R.layout.activity_main );

//        airconditionar = (Switch) findViewById( R.id.btnaircononoff );
//        tv = (Switch) findViewById( R.id.btntvonoff );
//        router = (Switch) findViewById( R.id.btnrouter );
//        washmachine = (Switch) findViewById( R.id.btnmachine );
//        lightbulb = (Switch) findViewById( R.id.btnlight );
//        fridge = (Switch) findViewById( R.id.btnfridge );
//
//        lightbulb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(lightbulb.isChecked()){
//                    Toast.makeText( MainActivity.this, "LightOn", Toast.LENGTH_SHORT ).show();
//                    dref.child( "Button" ).child( "b3" ).setValue( 1 );
//
//                    String uri = "@drawable/bulb";  // where myresource (without the extension) is the file
//
//                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//
//                    bulblighton= (ImageView)findViewById(R.id.imagebulb);
//                    Drawable res = getResources().getDrawable(imageResource);
//                    bulblighton.setImageDrawable(res);
////                    dref.child( "SmokeGas" ).child( "Smoke" ).setValue( 0 );
////                    Animation animation = AnimationUtils.loadAnimation(ActivitySmoke.this, R.anim.fananim);
////                    animationTarget.startAnimation(animation);
//
//
//
//                }
//                else
//                {
//                    dref.child( "Button" ).child( "b3" ).setValue( 0 );
//
//
//                    String uri = "@drawable/lightbulb";  // where myresource (without the extension) is the file
//
//                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//
//                    bulblighton= (ImageView)findViewById(R.id.imagebulb);
//                    Drawable res = getResources().getDrawable(imageResource);
//                    bulblighton.setImageDrawable(res);
//
//                    Toast.makeText( MainActivity.this, "LightOff", Toast.LENGTH_SHORT ).show();
////                    Animation animation = AnimationUtils.loadAnimation(ActivitySmoke.this, R.anim.stopfananim);
////                    animationTarget.startAnimation(animation);
//                }
//            }
//        });



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
