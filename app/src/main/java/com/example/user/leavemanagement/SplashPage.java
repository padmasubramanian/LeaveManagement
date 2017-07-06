package com.example.user.leavemanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashPage extends AppCompatActivity {
int delay=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent activity_user_interface = new Intent((SplashPage.this), Login.class);
                startActivity(activity_user_interface);
            }
        }, delay);


    }
}
