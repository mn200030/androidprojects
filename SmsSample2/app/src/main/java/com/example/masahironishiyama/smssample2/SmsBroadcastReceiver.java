package com.example.masahironishiyama.smssample2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final Map<Integer, String> RESULTMAP = new HashMap<>();

    public SmsBroadcastReceiver() {
        RESULTMAP.put(Activity.RESULT_OK, "SMS Sent");
        RESULTMAP.put(Activity.RESULT_CANCELED, "SMS Canceled");
        RESULTMAP.put(SmsManager.RESULT_ERROR_GENERIC_FAILURE, "Generic Failure");
        RESULTMAP.put(SmsManager.RESULT_ERROR_NO_SERVICE, "No Service");
        RESULTMAP.put(SmsManager.RESULT_ERROR_NULL_PDU, "NULL PDU");
        RESULTMAP.put(SmsManager.RESULT_ERROR_RADIO_OFF, "Radio Off");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = RESULTMAP.get(getResultCode());
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}