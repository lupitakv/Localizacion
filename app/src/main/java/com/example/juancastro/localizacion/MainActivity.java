package com.example.juancastro.localizacion;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity implements GoogleMap.OnMapClickListener {

    private final LatLng House= new LatLng(18.240206, -100.540985);
    private GoogleMap googlemap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googlemap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        googlemap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
        googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(House, 15));

        //Mostrar / ocultar tu ubicacion
        googlemap.setMyLocationEnabled(true);
        //Mostara / ocultar los controles de Zoom
        googlemap.getUiSettings().setZoomControlsEnabled(true);
        //Mostrar / ocultar los botones de localizacion
        googlemap.getUiSettings().setMyLocationButtonEnabled(true);
        //Mostrar / ocultar icon de compas
        googlemap.getUiSettings().setCompassEnabled(true);
        //Mostrar / ocultar evento de rotar
        googlemap.getUiSettings().setRotateGesturesEnabled(true);
        //Mostrar / ocultar funcionalidad de Zoom
        googlemap.getUiSettings().setZoomGesturesEnabled(true);

        googlemap.addMarker(new MarkerOptions().position(House).title("Mi house ").snippet("Mi house").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        googlemap.setOnMapClickListener(this);

    }

    public void moveCamera(View view) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(18.240206, -100.540985)).zoom(15).build();
        googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void animateCamera(View view) {
        if (googlemap.getMyLocation()!= null)
            googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googlemap.getMyLocation().getLatitude(),googlemap.getMyLocation().getLongitude()),15));
    }

    public void addMarker(View view) {
        googlemap.addMarker(new MarkerOptions().position(new LatLng(googlemap.getCameraPosition().target.latitude, googlemap.getCameraPosition().target.longitude)));
    }

    @Override
    public void onMapClick (LatLng puntoPulsado) {
        googlemap.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.UNO) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.DOS) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.TRES) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.CUATRO) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.CINCO) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}