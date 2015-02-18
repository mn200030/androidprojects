package com.example.masahironishiyama.fragmentsample4;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlagmentList extends Fragment implements AdapterView.OnItemClickListener {

    static List<String> dataList = new ArrayList<>();
    static ArrayAdapter<String> adapter;
    ListView listView;

    public FlagmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView)v.findViewById(R.id.listView1);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //データ初期化
        if (dataList.isEmpty()) {
            dataList.add("Foo");
            dataList.add("Bar");
            dataList.add("Baz");
        }

        //アダプターの作成と設定
        adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dataList
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Activity a = getActivity();
            if (!(a instanceof OnSampleListChangeListener)) {
                return;
            }
            OnSampleListChangeListener listener = (OnSampleListChangeListener)a;
            listener.onListSelectedChanged(dataList.get(position));
    }
}
