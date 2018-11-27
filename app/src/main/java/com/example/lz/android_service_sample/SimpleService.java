package com.example.lz.android_service_sample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import static com.example.lz.android_service_sample.MainActivity.LOG_TAG;

public class SimpleService extends Service {
    public SimpleService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(LOG_TAG, "SimpleService:" + "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(LOG_TAG, "SimpleService:" + "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG, "SimpleService:" + "onDestroy");
    }

    private SimpleBinder binder = new SimpleBinder();
    @Override
    public IBinder onBind(Intent intent) {
        int paramOne = intent.getIntExtra("paramOne", 0);
        int paramTwo = intent.getIntExtra("paramTwo", 0);
        binder.setParamOne(paramOne);
        binder.setParamTwo(paramTwo);
        Log.e(LOG_TAG, "SimpleService:" + "onBind");
        return binder;
    }

    class SimpleBinder extends Binder {

        private int paramOne;

        private int paramTwo;

        public SimpleBinder() {
        }

        public int getParamOne() {
            return paramOne;
        }

        public void setParamOne(int paramOne) {
            this.paramOne = paramOne;
        }

        public int getParamTwo() {
            return paramTwo;
        }

        public void setParamTwo(int paramTwo) {
            this.paramTwo = paramTwo;
        }

        public int add() {
            return paramOne + paramTwo;
        }

        public int sub() {
            return paramOne - paramTwo;
        }

        public int mult() {
            return paramOne * paramTwo;
        }

        public int divi() {
            return paramOne / paramTwo;
        }
    }
}
