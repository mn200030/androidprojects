package com.example.masahironishiyama.progressdialog1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by MasahiroNishiyama on 2015/02/24.
 */
public class MyTask extends AsyncTask<String, String, String> {

    private Context context;
    private TestAdapter adapter;
    private ProgressDialog progressDialog;

    public MyTask(Context context, TestAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "failed";
        }
        return "done";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(
            context,
            "Please wait",
            "Loading data..."
        );
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        adapter.add("Hell..");
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
