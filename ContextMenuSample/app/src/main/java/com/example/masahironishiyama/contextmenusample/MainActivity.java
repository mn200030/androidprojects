package com.example.masahironishiyama.contextmenusample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private String[] listItems;
    private MenuInflater menuInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.planetsArray,
                        android.R.layout.simple_list_item_1
                );
        listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listItems = getBaseContext().getResources().getStringArray(R.array.planetsArray);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listView1) {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;
            String title = listItems[info.position];
            menu.setHeaderTitle(title);
            menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.listview1_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        String title = listItems[info.position];
        String operation = null;

        switch (item.getItemId()) {
            case R.id.listview1_delete:
                operation = " - Delete";
                break;
            case R.id.listview1_edit:
                operation = " - Edit";
                break;
        }

        Log.d("test", "operation = " + operation);

        if (operation == null) {
            return false;
        }

        Toast.makeText(this, title + operation, Toast.LENGTH_SHORT).show();
        return true;
    }
}
