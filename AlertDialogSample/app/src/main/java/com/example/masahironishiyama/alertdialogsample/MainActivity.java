package com.example.masahironishiyama.alertdialogsample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private final String TAG = "DialogTest";
    final CharSequence[] items = { "Toyota", "Nissan", "Honda" };
    final boolean[] itemsChecked = new boolean[items.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSimple = (Button)findViewById(R.id.buttonSimple);
        buttonSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Shit...AlertDialog..")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

        Button buttonIcon = (Button)findViewById(R.id.buttonIcon);
        buttonIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Oops! An error occured.")
                        .setMessage("Want to try again?")
                        .setIcon(R.drawable.error)
                        .setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }
                        )
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        Button multiSelect = (Button)findViewById(R.id.buttonMultiSelect);
        multiSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Hell...AlertDialog..")
                        .setMultiChoiceItems(
                                items,
                                itemsChecked,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        Log.d(TAG, items[which] + " Checked");
                                    }
                                })
                        .setPositiveButton(
                                "Close",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d(TAG,
                                              itemsChecked[0] + "-" + itemsChecked[1] + "-" + itemsChecked[2]
                                                );
                                    }
                                })
                        .show();

            }
        });

        Button singleSelect = (Button)findViewById(R.id.buttonSingleSelect);
        singleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Shit...AlertDialog..")
                        .setSingleChoiceItems(
                                items,
                                0,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d(TAG, items[which] + "Selected");
                                    }
                                })
                        .setPositiveButton("Close", null)
                        .show();
            }
        });

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View view = inflater.inflate(R.layout.dialog5, null);
        final EditText editText = (EditText)view.findViewById(R.id.editText1);

        Button custom = (Button)findViewById(R.id.buttonCustomLayout);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Hell...AlertDialog..")
                        .setView(view)
                        .setPositiveButton(
                                "Close",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d(TAG, "input text = " + editText.getText().toString());
                                    }
                                })
                        .show();
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
