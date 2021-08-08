package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
//SplashActivity para mostrar una pantalla Splash (pantalla de carga) al abrir la app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Luego se dirige al MainActivity (Pantalla de inicio de sesión)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // evitar regresar a este activity
    }
}