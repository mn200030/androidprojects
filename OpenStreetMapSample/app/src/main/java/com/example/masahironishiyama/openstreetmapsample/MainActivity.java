package com.example.masahironishiyama.openstreetmapsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IMapController mapController;
        MapView map = new MapView(this, 256);
        map.setBuiltInZoomControls(true);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        MapView.LayoutParams mapParams = new MapView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,null,
                0,0, MapView.LayoutParams.BOTTOM_CENTER
        );

        LinearLayout mapLayout = (LinearLayout)findViewById(R.id.mapview);
        mapLayout.addView(map, mapParams);

        mapController = map.getController();
        mapController.setZoom(15);

        double centerLat = 35.45797;
        double centerLon = 139.632314;
        GeoPoint point = new GeoPoint(centerLat, centerLon);
        mapController.setCenter(point);
    }
}
