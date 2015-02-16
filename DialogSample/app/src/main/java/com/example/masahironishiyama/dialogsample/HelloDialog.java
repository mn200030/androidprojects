package com.example.masahironishiyama.dialogsample;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by MasahiroNishiyama on 2015/02/16.
 */
public class HelloDialog extends Dialog {

    public HelloDialog(Context context) {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.test_title);
        setContentView(R.layout.dialog);
    }
}
