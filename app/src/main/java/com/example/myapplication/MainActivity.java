package com.example.myapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.helpers.LocationPermissionHelper;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Location myLocation = new Location("dummyprovider");
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float ax, ay, az;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        ax = 0;
        ay = 0;
        az = 0;

//        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        LocationListener locationListener = new LocationListener() {
//            public void onLocationChanged(Location location) {
//                myLocation.set(location);
//            }
//
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//            }
//
//            public void onProviderEnabled(String provider) {
//            }
//
//            public void onProviderDisabled(String provider) {
//            }
//        };
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            LocationPermissionHelper.requestLocationPermission(this);
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.1f, locationListener);

        Button Accbutton = (Button) findViewById(R.id.Accbutton);
        Accbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView ResulttextView = (TextView) findViewById(R.id.ResulttextView);
                String tmp = "x - " + String.valueOf(ax) + "\n" + "y - " + String.valueOf(ay) + "\n" + "z - " + String.valueOf(az);
                ResulttextView.setText(tmp);
            }
        });

        Button Locbutton = (Button) findViewById(R.id.Locbutton);
        Locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView ResulttextView = (TextView) findViewById(R.id.ResulttextView);
                updateLoc();
                ResulttextView.setText(myLocation.toString());
            }
        });
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        ax = sensorEvent.values[0];
        ay = sensorEvent.values[1];
        az = sensorEvent.values[2];
    }

    private void updateLoc()
    {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationPermissionHelper.requestLocationPermission(this);
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null)
        {
            myLocation.set(location);
        }
    }

    private void garnettFunc(){
        
    }

    private void NoamFunk(){
        
    }
}

