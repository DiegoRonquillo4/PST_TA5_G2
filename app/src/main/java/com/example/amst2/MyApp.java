package com.example.amst2;


import android.app.Application;
import android.os.SystemClock;
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
