package com.example.mygame;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gsv=(GameSurfaceView)findViewById(R.id.gsv);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAcclerometer= mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    GameSurfaceView gsv;
    Sensor mAcclerometer;
    SensorManager mSensorManager;

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAcclerometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("[PCC]", String.valueOf(event.values[0]));
        Log.d("[PCC]", String.valueOf(event.values[1]));
        if(Math.abs(event.values[0])>0.5) {
            gsv.currX -= event.values[0] * 10;
        }
        if(Math.abs(event.values[1])>0.5) {
            gsv.currY += event.values[1]*10;
        }
        //value3.setText( String.valueOf(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}