package com.electronic.lapsus.caputup;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.electronic.lapsus.caputup.CircularProgressBar.ProgressAnimationListener;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_initmsg = null;
    TextView testing = null;
    SensorManager sensorManager;
    Sensor sensor;
    CircularProgressBar cp;
    boolean start = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_details);

        tv_initmsg = findViewById(R.id.init_msg);


        testing = findViewById(R.id.init_msg);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
        setResult(RESULT_OK, intent);

        cp = (CircularProgressBar) findViewById(R.id.circularprogressbar);
        cp.setSoundEffectsEnabled(true);


    }

    @Override
    public void onSensorChanged(final SensorEvent event) {

        final float x, y, z;
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        if (z < 5 && start) {
            start = false;
            cp.animateProgressTo(10, 0, new ProgressAnimationListener() {

                @Override
                public void onAnimationStart() {
                    cp.setSubTitle("");
                    cp.setVisibility(View.VISIBLE);
                    tv_initmsg.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationProgress(int progress) {
                    cp.setTitle(String.valueOf(progress));

                    cp.playSoundEffect(SoundEffectConstants.CLICK);
                }

                @Override
                public void onAnimationFinish() {

                    tv_initmsg.setVisibility(View.GONE);

                    cp.animateProgressTo(60, 0, new ProgressAnimationListener() {
                        @Override
                        public void onAnimationStart() {
                            tv_initmsg.setVisibility(View.VISIBLE);
                            tv_initmsg.setText("Starting animation");

                        }

                        @Override
                        public void onAnimationFinish() {

                            tv_initmsg.setVisibility(View.GONE);
                            cp.setVisibility(View.GONE);

                        }

                        @Override
                        public void onAnimationProgress(int progress) {
                            cp.setTitle(String.valueOf(progress));
                            float x, y, z;
                            x = event.values[0];
                            y = event.values[1];
                            z = event.values[2];


                            if (z >= 5) {
                                tv_initmsg.setText("Siguiente Pregunta " + z);
                            } else if( z >= -5){
                                tv_initmsg.setText("Responde Pregunta " + z);
                            }

                        }
                    });
                }
            });
        }

    //    tv_initmsg.setText("\n" + "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
