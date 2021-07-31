package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText etUser, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUser = (EditText)findViewById(R.id.editTextUsuario);
        etPassword = (EditText)findViewById(R.id.editTextContraseña);
    }
        public void iniciar (View v){
            Intent datos=new Intent(this, HomeActivity.class);
            datos.putExtra("user", etUser.getText().toString());
            startActivity(datos);
        }
    public void regresar(View v){
        finish();
    }
}
