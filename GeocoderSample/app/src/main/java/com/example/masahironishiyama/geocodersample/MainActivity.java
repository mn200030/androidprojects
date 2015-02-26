package com.example.masahironishiyama.geocodersample;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements LocationListener {

    private TextView address;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        address = (TextView)findViewById(R.id.address);
        initLocation();
    }

    private void initLocation() {
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        //精度を指定する
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        provider = locationManager.getBestProvider(criteria, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 60000, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        new UpdateAddrAsyncTask(getBaseContext(), lat, lon).execute();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
