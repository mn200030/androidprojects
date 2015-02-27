package com.example.masahironishiyama.smssample2;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.masahironishiyama.smssample2.R;


public class MainActivity extends ActionBarActivity {

    final String ACTION_SENT = "com.example.masahironishiyama.smssample2.ACTION_SENT";
    PendingIntent sentIntent;
    SmsBroadcastReceiver broadcastReceiver;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sentIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_SENT), 0);

        buttonSend = (Button)findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("08067177309",null,"Yeah",sentIntent,null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new SmsBroadcastReceiver();
        registerReceiver(broadcastReceiver, new IntentFilter(ACTION_SENT));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
