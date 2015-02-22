package com.example.masahironishiyama.cutomizedlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MasahiroNishiyama on 2015/02/22.
 */
public class BookAdapter extends BaseAdapter {

    private List<Book> dataList;
    private Context context;

    public BookAdapter(List<Book> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (dataList == null) {
            return 0;
        }
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

        TextView textView1;
        TextView textView2;
        TextView textView3;
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, null);
        }

        Book book = (Book)getItem(position);
        if (book != null) {
            textView1 = (TextView)v.findViewById(R.id.textView1);
            textView2 = (TextView)v.findViewById(R.id.textView2);
            textView3 = (TextView)v.findViewById(R.id.textView3);

            textView1.setText(book.getTitle());
            textView2.setText(book.getAuthor());
            textView3.setText(book.getIsbn());
        }

        return v;
    }
}
