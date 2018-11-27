package com.example.lz.android_service_sample;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import static com.example.lz.android_service_sample.MainActivity.LOG_TAG;

public class CoreService extends Service {

    private boolean bindRemoteServiceSuccess;

    public CoreService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(LOG_TAG, "CoreService:" + "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(LOG_TAG, "CoreService:" + "onStartCommand");
        if (!bindRemoteServiceSuccess) {
            Intent bindIntent = new Intent(CoreService.this, RemoteService.class);
            bindRemoteServiceSuccess = bindService(bindIntent, connection, BIND_IMPORTANT);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(LOG_TAG, "CoreService:" + "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(LOG_TAG, "CoreService:" + "onServiceDisconnected");
            bindRemoteServiceSuccess = false;

            Intent bindIntent = new Intent(CoreService.this, RemoteService.class);
            startService(bindIntent);
            bindRemoteServiceSuccess = bindService(bindIntent, connection, Context.BIND_IMPORTANT);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG, "CoreService:" + "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new CoreBinder();
    }

    class CoreBinder extends Binder {
    }
}
