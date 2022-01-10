package com.tesoem.g7s21hglepf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LauncherActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LauncherActivity1.this,MainActivity.class ));

            }
        };

        Handler h = new Handler();
        h.postDelayed(r,5000);
    }
}