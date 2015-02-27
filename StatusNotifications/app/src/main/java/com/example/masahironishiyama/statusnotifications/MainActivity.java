package com.example.masahironishiyama.statusnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonNotify = (Button)findViewById(R.id.buttonNotify);
        buttonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通知を選択した時のインテント
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com"));

                PendingIntent pendingIntent = PendingIntent.getActivity(
                        getApplicationContext(),0,intent,0);
                // 通知の作成
                NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(getApplicationContext());
                builder.setContentIntent(pendingIntent);
                // ステータスバーに表示されるテキスト
                builder.setTicker("Ticker");
                // アイコン
                builder.setSmallIcon(R.drawable.ic_launcher);
                // Notificationを開いたときに表示されるタイトル
                builder.setContentTitle("ContentTitle");
                // Notificationを開いたときに表示されるサブタイトル
                builder.setContentText("ContentText");
                // 通知するタイミング
                builder.setWhen(System.currentTimeMillis());
                // 通知時の音・バイブ・ライト
                builder.setDefaults(
                        Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE |
                        Notification.DEFAULT_LIGHTS
                );
                // タップするとキャンセル(消える)
                builder.setAutoCancel(true);
                // NotificationManagerを取得
                NotificationManager manager = (NotificationManager)getSystemService(
                        Service.NOTIFICATION_SERVICE);
                // Notificationを作成して通知
                manager.notify(0, builder.build());
            }
        });
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
