package com.electronic.lapsus.caputup;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.electronic.lapsus.caputup.CircularProgressBar.ProgressAnimationListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_initmsg = null;
    ImageView correct_iv = null;
    ImageView pass_iv = null;
    SensorManager sensorManager;
    Sensor sensor;
    CircularProgressBar cp;
    boolean start = true;
    Category category_selected;
    int index = 0;
    int valid = 0;
    int invalid = 0;
    Dialog myDialog;
    ImageView imgCategoryPopup_iv = null;
    TextView categoryNamePopUp_tv = null;
    TextView validPopup_tv = null;
    TextView invalidPopup_tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_details);

        tv_initmsg = findViewById(R.id.init_msg);
        correct_iv = findViewById(R.id.correctimg);
        pass_iv = findViewById(R.id.passimg);

        myDialog = new Dialog(this);

        category_selected = (Category) getIntent().getSerializableExtra("category");

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
                        }

                        @Override
                        public void onAnimationFinish() {

                            tv_initmsg.setVisibility(View.GONE);
                            cp.setVisibility(View.GONE);

                            pass_iv.setVisibility(View.GONE);
                            correct_iv.setVisibility(View.GONE);
                            ShowPopup();

                        }

                        @Override
                        public void onAnimationProgress(int progress) {
                            cp.setTitle(String.valueOf(progress));
                            float x, y, z;
                            x = event.values[0];
                            y = event.values[1];
                            z = event.values[2];

                            if (index < category_selected.getWords().size()) {

                                if (z >= 5) {
                                    //    tv_initmsg.setText("Siguiente Palabra " + z);
                                    tv_initmsg.setText(category_selected.getWords().get(index));
                                    pass_iv.setVisibility(View.VISIBLE);
                                    correct_iv.setVisibility(View.GONE);
                                    index++;
                                    invalid++;
                                } else if (z <= -5) {
                                    //    tv_initmsg.setText("Palabra correcta " + z);
                                    tv_initmsg.setText(category_selected.getWords().get(index));
                                    pass_iv.setVisibility(View.GONE);
                                    correct_iv.setVisibility(View.VISIBLE);
                                    index++;
                                    valid++;
                                } else {
                                    //   tv_initmsg.setText("Responde Palabra " + z);
                                    tv_initmsg.setText(category_selected.getWords().get(index));
                                    pass_iv.setVisibility(View.GONE);
                                    correct_iv.setVisibility(View.GONE);
                                }

                            }
                            else {

                                pass_iv.setVisibility(View.GONE);
                                correct_iv.setVisibility(View.GONE);
                                cp.setVisibility(View.GONE);
                                ShowPopup();

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

    public void ShowPopup() {
        TextView txtclose;
        Button btnTryAgain;
        Button btnChangeCategory;
        myDialog.setContentView(R.layout.custompopup);
        myDialog.setCanceledOnTouchOutside(false);

        btnTryAgain = (Button) myDialog.findViewById(R.id.tryagain);
        btnChangeCategory = (Button) myDialog.findViewById(R.id.changeCategory);
        validPopup_tv = (TextView) myDialog.findViewById(R.id.validPopup);
        invalidPopup_tv = (TextView) myDialog.findViewById(R.id.invalidPopup);
        categoryNamePopUp_tv = (TextView) myDialog.findViewById(R.id.categoryNamePopup);
        imgCategoryPopup_iv = (ImageView) myDialog.findViewById(R.id.categoryImgPopup);

        validPopup_tv.setText(String.valueOf(valid));
        invalidPopup_tv.setText(String.valueOf(invalid));
        categoryNamePopUp_tv.setText(category_selected.getTitle());

        URL url = null;
        try {
            url = new URL(category_selected.getImageUrl());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imgCategoryPopup_iv.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        btnChangeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("category", category_selected);
                startActivityForResult(intent, category_selected.getId());
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



}
