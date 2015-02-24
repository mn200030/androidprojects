package com.example.masahironishiyama.progressdialog1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MasahiroNishiyama on 2015/02/24.
 */
public class TestAdapter extends BaseAdapter {

    private List<String> dataList;
    private Context context;

    public TestAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    public void add(String item) {
        dataList.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, null);
        }

        textView = (TextView)v.findViewById(R.id.row_textview1);
        textView.setText(getItem(position).toString());
        return v;
    }
}
