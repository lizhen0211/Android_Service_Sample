package com.example.lz.android_service_sample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import static com.example.lz.android_service_sample.MainActivity.LOG_TAG;

public class SimpleDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo);
    }

    public void onStartServiceClick(View view) {
        Intent intent = new Intent(this, SimpleService.class);
        startService(intent);
    }

    public void onStopServiceClick(View view) {
        Intent intent = new Intent(this, SimpleService.class);
        stopService(intent);
    }

    public void onBindServiceClick(View view) {
        Intent intent = new Intent(this, SimpleService.class);
        intent.putExtra("paramOne", 10);
        intent.putExtra("paramTwo", 20);
        bindSuccess = bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void onUnBindServiceClick(View view) {
        if (bindSuccess) {
            unbindService(connection);
        }
    }

    private boolean bindSuccess;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(LOG_TAG, "SimpleService:" + "onServiceConnected");
            SimpleService.SimpleBinder binder = (SimpleService.SimpleBinder) service;
            Log.e(LOG_TAG, "add:" + binder.add());
            Log.e(LOG_TAG, "sub:" + binder.sub());
            Log.e(LOG_TAG, "mult:" + binder.mult());
            Log.e(LOG_TAG, "divi:" + binder.divi());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(LOG_TAG, "SimpleService:" + "onServiceDisconnected");
        }
    };
}
