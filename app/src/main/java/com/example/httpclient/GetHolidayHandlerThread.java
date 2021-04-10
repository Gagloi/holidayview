package com.example.httpclient;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class GetHolidayHandlerThread extends HandlerThread {

    Handler handler;

    public GetHolidayHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        handler = new Handler(this.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d("ded", msg.toString());
            }
        };
    }

}
