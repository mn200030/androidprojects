package com.example.masahironishiyama.sharedpreferencessample;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    final String KEY = "data1";

    SharedPreferences preferences;
    Button writeButton;
    Button readButton;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        writeButton = (Button)findViewById(R.id.writeButton);
        readButton = (Button)findViewById(R.id.readButton);
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textPreferenceValue);
    }

    public void onClickWriteButton(View v) {
        String value = editText.getText().toString();
        if (value == null || value.length() < 1) {
            return;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY, value);
        editor.commit();
    }

    public void onClickReadButton(View v) {
        String value = preferences.getString(KEY, "");
        textView.setText(value);
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
            getFragmentManager().beginTransaction()
                    .replace(R.id.settings_container, new SettingsFragment())
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
