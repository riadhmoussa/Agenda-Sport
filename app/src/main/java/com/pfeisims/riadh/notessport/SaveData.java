package com.pfeisims.riadh.notessport;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

/**
 * Created by Riadh on 07/12/2017.
 */

public class SaveData {
    SharedPreferences ShredRef;
    Context context;
    public SaveData(Context context){
        ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
        this.context=context;
    }

    public  void SaveData(int hour,int minute,int year,int month,int day){
        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putInt("hour",hour);
        editor.putInt("minute",minute);
        editor.putInt("year",year);
        editor.putInt("month",month);
        editor.putInt("day",day);
        editor.commit();
    }

    public void LoadData(){
        int minute=ShredRef.getInt("minute",0);
        int hour=ShredRef.getInt("hour",0);
        int year=ShredRef.getInt("year",0);
        int month=ShredRef.getInt("month",0);
        int day=ShredRef.getInt("day",0);
        AlarmSet(hour,minute,year,month,day);

    }
    void AlarmSet(int Hour,int Minute,int Year,int Month,int Day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, Minute);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager am = (AlarmManager)context.getSystemService  (Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        intent.setAction("com.moussa.alarm");
        intent.putExtra("MyMessage","hello from alarm");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY , pi);
    }
}
