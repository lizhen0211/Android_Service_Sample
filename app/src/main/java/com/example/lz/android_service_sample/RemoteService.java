package com.example.lz.android_service_sample;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import static com.example.lz.android_service_sample.MainActivity.LOG_TAG;

public class RemoteService extends Service {

    private boolean bindCoreServiceSuccess;

    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(LOG_TAG, "RemoteService:" + "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(LOG_TAG, "RemoteService:" + "onStartCommand");
        if (!bindCoreServiceSuccess) {
            Intent bindIntent = new Intent(RemoteService.this, CoreService.class);
            bindCoreServiceSuccess = bindService(bindIntent, connection, BIND_IMPORTANT);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(LOG_TAG, "RemoteService:" + "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(LOG_TAG, "RemoteService:" + "onServiceDisconnected");
            bindCoreServiceSuccess = false;

            Intent intent = new Intent(RemoteService.this, CoreService.class);
            startService(intent);
            bindCoreServiceSuccess = bindService(intent, connection, BIND_IMPORTANT);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG, "RemoteService:" + "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new RemoteBinder();
    }

    class RemoteBinder extends Binder {
    }
}
