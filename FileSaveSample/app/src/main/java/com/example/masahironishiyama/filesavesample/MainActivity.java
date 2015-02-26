package com.example.masahironishiyama.filesavesample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class MainActivity extends ActionBarActivity {

    private static final String SAVEDIR = "/data/";

    private EditText fileName;
    private EditText content;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = (EditText)findViewById(R.id.textFilename);
        content = (EditText)findViewById(R.id.textContent);
        buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _fileName = fileName.getText().toString();
                String _content = content.getText().toString();
                String message = "Saved";
                Toast.makeText(getBaseContext(), getBaseContext().getFilesDir().toString(), Toast.LENGTH_SHORT).show();

                try {
                    PrintWriter pw = new PrintWriter(openFileOutput(_fileName, MODE_PRIVATE));
                    pw.println(_content);
                    pw.close();
                } catch (FileNotFoundException e) {
                    message = e.getMessage();
                } catch (IOException e) {
                    message = e.getMessage();
                }
                Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
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
