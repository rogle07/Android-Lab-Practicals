package com.yogesh.androidlabpracticals.practical9;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.yogesh.androidlabpracticals.R;

public class LocationActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView tvLat, tvLng, tvAccuracy, tvStatus;
    private double currentLat = 0, currentLng = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Practical 9 – Location Services");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        tvLat = findViewById(R.id.tv_latitude);
        tvLng = findViewById(R.id.tv_longitude);
        tvAccuracy = findViewById(R.id.tv_accuracy);
        tvStatus = findViewById(R.id.tv_location_status);

        Button btnGetLocation = findViewById(R.id.btn_get_location);
        Button btnOpenMaps = findViewById(R.id.btn_open_maps);

        btnGetLocation.setOnClickListener(v -> checkPermissionAndGetLocation());
        btnOpenMaps.setOnClickListener(v -> openInMaps());
    }

    private void checkPermissionAndGetLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST);
        } else {
            getLocation();
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getLocation() {
        tvStatus.setText("📡 Fetching location...");
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                currentLat = location.getLatitude();
                currentLng = location.getLongitude();
                tvLat.setText(String.format("Latitude:   %.6f°", currentLat));
                tvLng.setText(String.format("Longitude: %.6f°", currentLng));
                tvAccuracy.setText(String.format("Accuracy:  ±%.1f m", location.getAccuracy()));
                tvStatus.setText("✅ Location retrieved successfully!");
            } else {
                tvStatus.setText("⚠️ Location unavailable. Enable GPS and try again.");
            }
        }).addOnFailureListener(e -> tvStatus.setText("❌ Error: " + e.getMessage()));
    }

    private void openInMaps() {
        if (currentLat == 0 && currentLng == 0) {
            Toast.makeText(this, "Get location first!", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri gmmIntentUri = Uri.parse("geo:" + currentLat + "," + currentLng + "?q=" + currentLat + "," + currentLng + "(My+Location)");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // Fallback to browser maps
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://maps.google.com/?q=" + currentLat + "," + currentLng));
            startActivity(browserIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Location permission denied.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
