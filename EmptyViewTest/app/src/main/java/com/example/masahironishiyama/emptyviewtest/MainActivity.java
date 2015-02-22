package com.example.masahironishiyama.emptyviewtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
    implements View.OnClickListener {

    Button addButton;
    Button clearButton;
    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            dataList
        );
        listView.setAdapter(adapter);
        TextView emptyView = (TextView)findViewById(R.id.emptyTextView);
        listView.setEmptyView(emptyView);

        addButton = (Button)findViewById(R.id.button1);
        clearButton = (Button)findViewById(R.id.button2);

        addButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                adapter.add("Item");
                break;
            case R.id.button2:
                adapter.clear();
                break;
        }
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
