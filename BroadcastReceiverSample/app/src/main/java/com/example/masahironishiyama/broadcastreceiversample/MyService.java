package com.example.masahironishiyama.broadcastreceiversample;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyService extends IntentService {

    final static String TAG = "ServiceTest4";

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(10000);
            Intent broadcastIntent = new Intent();
            broadcastIntent.putExtra("message", "Hello, BroadCast..");
            broadcastIntent.setAction("MY_ACTION");
            getBaseContext().sendBroadcast(broadcastIntent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
