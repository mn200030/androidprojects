package com.example.masahironishiyama.fragmentsample4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContent extends Fragment {

    TextView textView;

    public FragmentContent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        textView = (TextView)v.findViewById(R.id.textView1);
        return v;
    }

    public void setText(String s) {
        textView.setText(s);
    }
}
