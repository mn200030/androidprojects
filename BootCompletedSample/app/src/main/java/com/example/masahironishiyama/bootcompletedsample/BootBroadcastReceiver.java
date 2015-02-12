package com.example.masahironishiyama.bootcompletedsample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

    final static String TAG = "ServiceTest5";

    public BootBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "scheduleService()");
        Intent i = new Intent(context, MyIntentService.class);
        PendingIntent pendingIntent =
            PendingIntent.getService(
                context,
                -1,
                i,
                PendingIntent.FLAG_UPDATE_CURRENT
            );
        AlarmManager alarmManager =
            (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(
            AlarmManager.RTC,
            System.currentTimeMillis(),
            10000,
            pendingIntent
        );
    }
}
