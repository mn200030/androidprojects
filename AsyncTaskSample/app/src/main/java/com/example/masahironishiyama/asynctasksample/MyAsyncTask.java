package com.example.masahironishiyama.asynctasksample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by MasahiroNishiyama on 2015/02/18.
 */
public class MyAsyncTask extends AsyncTask<String, Integer, Long>
    implements DialogInterface.OnCancelListener {

    final String TAG = "MyAsyncTask";
    ProgressDialog dialog;
    Context context;

    public MyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Long doInBackground(String... params) {
        try {
            for (int i = 0 ; i < 10 ; i++) {
                if (isCancelled()) {
                    Log.d(TAG, "Cancelled!");
                    i = 10;
                } else {
                    Thread.sleep(1000);
                    publishProgress((i+1) * 10);
                }
            }
        } catch (InterruptedException e) {
            Log.d(TAG, "InterruptedException in doInBackground");
        }
        return 123L;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute");
        dialog = new ProgressDialog(context);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading data...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(this);
        dialog.setMax(100);
        dialog.setProgress(0);
        dialog.show();
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute - " + result);
        dialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate - " + values[0]);
        dialog.setProgress(values[0]);
    }

    @Override
    protected void onCancelled(Long aLong) {
        super.onCancelled(aLong);
    }

    @Override
    protected void onCancelled() {
        Log.d(TAG, "onCancelled");
        super.onCancelled();
        dialog.dismiss();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.d(TAG, "Dialog onCancell... calling cancel(true)");
        this.cancel(true);
    }
}
