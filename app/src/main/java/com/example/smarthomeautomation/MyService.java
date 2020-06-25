package com.example.smarthomeautomation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyService extends Service {
    private final String CHANNEL_ID ="personal" ;
    public final int NOTIFICATION_ID = 001;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (0<=1){
                    try {
                        Thread.sleep(5000);
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        databaseReference.child("sensor").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                        String smokeStatus =  dataSnapshot.child("Smoke_alarm)").getValue().toString();
                                        String gasStatus =  dataSnapshot.child("Fire_alarm").getValue().toString();
                                        if(smokeStatus.equals("1")){
                                            databaseReference.child("Alerts").child("Smoke_alarm)").setValue("0");
                                            generateAlert("Smoke Detected" ,"Please attention here. Smoke found in your kitchen");

                                        }
                                    if(gasStatus.equals("1")){
                                        databaseReference.child("Alerts").child("Fire_alarm").setValue("0");
                                        generateAlert("Fire Detected" ,"Please attention here. Fire found near you");

                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        return START_STICKY;
    }

    private void ShowNotification(String name, String msg) {
        //createNotificationChannel(name , msg);
        Uri alarmSound = RingtoneManager.getDefaultUri( RingtoneManager.TYPE_NOTIFICATION);
        Intent notificationIntent = new Intent(this, SensorsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.logo).setContentTitle(name).setPriority(1).setSound(alarmSound).setContentText(msg).setContentIntent(pendingIntent);
        Notification notification=builder.build();
        startForeground(123, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
    }
    private void generateAlert(String name, String msg) {
        createNotificationChannel();

        Intent notificationIintent = new Intent(this, SensorsActivity.class);
        TaskStackBuilder taskStackBuilder= TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntent(notificationIintent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentTitle(name);
        builder.setContentText(msg);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent( pendingIntent );

        NotificationManager notificationManager = (NotificationManager)getSystemService( Context.NOTIFICATION_SERVICE );
        notificationManager.notify(NOTIFICATION_ID,builder.build() );

    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name = "personal";
            String Description = "Please review time table";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel( CHANNEL_ID,name,importance );
            notificationChannel.setDescription( Description );

            NotificationManager notificationManager = (NotificationManager)getSystemService( NOTIFICATION_SERVICE );
            notificationManager.createNotificationChannel( notificationChannel );
        }
    }
}

