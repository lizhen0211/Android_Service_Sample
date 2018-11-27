package com.example.lz.android_service_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoubleServiceGuardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_service_guard);
    }

    public void onDoubleProcessClick(View view) {
        startService(new Intent(this, CoreService.class));
        startService(new Intent(this, RemoteService.class));
    }

    public void stopCore(View view){
        stopService(new Intent(this, CoreService.class));
    }

    public void stopRemote(View view){
        stopService(new Intent(this, RemoteService.class));
    }
}
