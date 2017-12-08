package com.pfeisims.riadh.notessport;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equalsIgnoreCase("com.moussa.alarm")){
            Bundle bundle=intent.getExtras();
            Toast.makeText(context, bundle.getString("MyMessage"),Toast.LENGTH_SHORT).show();

            NotificationCompat.Builder nbuild=new NotificationCompat.Builder(context);
            nbuild.setContentTitle("alerte").
                    setContentText("séance de sport ").
                    setSmallIcon(R.drawable.sport);
            NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,nbuild.build());




        }else if(intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            SaveData saveData=new SaveData(context);
            saveData.LoadData();



        }
    }
}
