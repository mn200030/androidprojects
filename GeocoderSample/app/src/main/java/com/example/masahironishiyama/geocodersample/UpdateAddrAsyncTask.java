package com.example.masahironishiyama.geocodersample;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by MasahiroNishiyama on 2015/02/26.
 */
public class UpdateAddrAsyncTask extends AsyncTask<Void, Integer, String> {

    private Context context;
    private double lat;
    private double lon;

    public UpdateAddrAsyncTask(Context context, double lat, double lon) {
        this.context = context;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = null;
        try {
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(lat, lon, 1);
//            if (addresses.size() > 0) {
//                return addresses.get(0).getLocality();
//            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
        return "null";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
