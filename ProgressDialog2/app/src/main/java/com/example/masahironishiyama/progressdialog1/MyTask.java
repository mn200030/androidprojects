package com.example.masahironishiyama.progressdialog1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

/**
 * Created by MasahiroNishiyama on 2015/02/24.
 */
public class MyTask extends AsyncTask<String, Integer, String>
    implements DialogInterface.OnCancelListener {

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
            for (int i = 0 ; i < 10 ; i++) {
                Thread.sleep(1000);
                publishProgress((i+1)*10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "failed";
        }
        return "done";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loading data..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        progressDialog.setOnCancelListener(this);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        adapter.add("Hell..");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }
}
