package com.example.squeesteakmenuandroidjemhendra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    boolean opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String nomorMeja = Preferences.getMeja(getApplicationContext());
        if(nomorMeja.isEmpty()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!opened){
                        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                        intent.putExtra("activity", "splash");
                        opened = true;
                        startActivity(intent);
                        finish();
                    }

                }
            }, 2000);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!opened){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        opened = true;
                        startActivity(intent);
                        finish();
                    }
                }
            }, 2000);
        }


    }
}