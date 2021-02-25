package com.comp3350.webudget.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.comp3350.webudget.R;

public class MainActivity extends AppCompatActivity {

    private int timeout = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
                finish();
            }
        } , timeout);
    }
}