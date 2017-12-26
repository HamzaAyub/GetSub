package com.example.hp.beyfikar.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.beyfikar.R;
import com.example.hp.beyfikar.map.OrderMapActivity;

public class MainSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);



              Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    startActivity(new Intent(MainSplashActivity.this , OrderMapActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
