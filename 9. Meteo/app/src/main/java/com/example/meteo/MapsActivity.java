package com.example.meteo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.meteo.databinding.ActivityMapsBinding;



    public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

        private GoogleMap mMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            //LatLng sydney = new LatLng(-34, 151);
            LatLng mohamedia = new LatLng(33.7066, -7.3944);
            mMap.addMarker(new MarkerOptions().position(mohamedia).title("Marker in Mohamedia"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mohamedia));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mohamedia,12));
            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                final GoogleMap gmap=mMap;
                @Override
                public void onMapClick(LatLng latLng) {

                    gmap.addMarker(new MarkerOptions().position(latLng));
                    Toast.makeText(MapsActivity.this, latLng.latitude+"-"
                            +latLng.longitude, Toast.LENGTH_SHORT).show();

                }
            });

        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.map_options, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Change the map type based on the user's selection.
            switch (item.getItemId()) {
                case R.id.normal_map:
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    return true;
                case R.id.hybrid_map:
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    return true;
                case R.id.satellite_map:
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    return true;
                case R.id.terrain_map:
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }
