package com.example.tangr.quiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Act extends AppCompatActivity {
    private static int Delay_Time= 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent new_act = new Intent(Splash_Act.this, MainActivity.class);
                startActivity(new_act);
            }
        },Delay_Time);
    }
}