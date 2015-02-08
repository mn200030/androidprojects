package com.example.masahironishiyama.httpsample;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by MasahiroNishiyama on 2015/02/08.
 */
public class HttpGetTask extends AsyncTask<String, String, String> {

    private String url;
    private TextView textView;

    public HttpGetTask(String url, TextView textView) {
        super();
        this.url = url;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL _url = new URL(url);
            URLConnection con = _url.openConnection();
            con.setDoInput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView.setText(s);
    }
}
