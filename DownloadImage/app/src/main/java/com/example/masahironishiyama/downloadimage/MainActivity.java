package com.example.masahironishiyama.downloadimage;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText urlText;
    private Button downloadButton;
    private TextView progressText;
    private ImageView imageView;
    private DownloadProgressReceiver receiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlText = (EditText)findViewById(R.id.urlText);
        downloadButton = (Button)findViewById(R.id.downloadButton);
        progressText = (TextView)findViewById(R.id.progressTextView);
        imageView = (ImageView)findViewById(R.id.imageView);

        downloadButton.setOnClickListener(this);
        registerDownloadBroadcastReceiver();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
        startDownload();
    }

    private void registerDownloadBroadcastReceiver() {
        receiver = new DownloadProgressReceiver(progressText, imageView);
        intentFilter = new IntentFilter();
        intentFilter.addAction("DOWNLOAD_PROGRESS_ACTION");
        registerReceiver(receiver, intentFilter);
    }

    private void startDownload() {
        Intent intent = new Intent(getBaseContext(), DownloadService.class);
        intent.putExtra("url", urlText.getText().toString());
        startService(intent);
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
