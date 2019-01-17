package com.electronic.lapsus.caputup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.pb_progress_bar);
        textView = findViewById(R.id.tv_loading);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HomeActivity ha = new HomeActivity();
                ha.getCategories();
                progressAnimation();
            }
        });

    }

    public void progressAnimation(){
        ProgressBarAnimation animation = new ProgressBarAnimation(this, progressBar, textView, 0f, 100f);
        animation.setDuration(5000);
        progressBar.setAnimation(animation);
    }

}
