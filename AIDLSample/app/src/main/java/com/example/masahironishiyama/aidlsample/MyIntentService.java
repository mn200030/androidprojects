package com.example.masahironishiyama.aidlsample;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    final static String TAG = "ServiceTest7";
    final IMyIntentService.Stub binder = new IMyIntentService.Stub() {
        @Override
        public void setMessage(String message) throws RemoteException {
            MyIntentService.this.setMessage(message);
        }
    };
    String message;

    public MyIntentService() {
        super("MyIntentService");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return binder;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandler");
        Log.d(TAG, "message = [" + message + "]");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
