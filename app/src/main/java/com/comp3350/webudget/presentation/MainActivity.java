package com.comp3350.webudget.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.comp3350.webudget.R;
import com.comp3350.webudget.application.Main;
import com.comp3350.webudget.application.Services;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private int timeout = 2500;
    public static File dataDirectory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO remove: This is for connecting to the database fake
        Services.testSetup();

        Context context = getApplicationContext();
        File dataDirectory = context.getDir("db", Context.MODE_PRIVATE);

        Main.setDBPathName(dataDirectory.toString());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
                finish();
            }
        } , timeout);
    }
}