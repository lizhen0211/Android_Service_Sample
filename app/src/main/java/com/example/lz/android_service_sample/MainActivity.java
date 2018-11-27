package com.example.lz.android_service_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    public static final String LOG_TAG = "log_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSimpleDemoClick(View view) {
        Intent intent = new Intent(this, SimpleDemoActivity.class);
        startActivity(intent);
    }

    public void onDoubleServiceGuardClick(View view){
        Intent intent = new Intent(this, DoubleServiceGuardActivity.class);
        startActivity(intent);
    }


}
