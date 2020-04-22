package com.example.yummy.presentation.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.yummy.R;
import com.example.yummy.presentation.Utils.Common;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Common.setStatusBarColor(this, ContextCompat.getColor(this, R.color.primary_color_main),false);


        startMainActivity();
    }


    public void startMainActivity() {

        int delayTime = 2000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivityIntent);
                finish();
            }
        }, delayTime);

    }
}
