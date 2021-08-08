package com.example.amst2;


import android.app.Application;
import android.os.SystemClock;
public class MyApp extends Application {
    //Clase para definir el tiempo del splash screen
    @Override
    public void onCreate() {
        super.onCreate();
        //Se define el tiempo del splahscreen en 3s.
        SystemClock.sleep(3000);
    }
}
