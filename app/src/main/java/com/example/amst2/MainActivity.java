package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Activity para inicio de sesión
public class MainActivity extends AppCompatActivity {
    EditText etUser, etPassword;
    TextView tvRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se instancian las variables linkeandolos con las id del XML.
        etUser = (EditText)findViewById(R.id.etUser);
        etPassword = (EditText)findViewById(R.id.etPassword);
        tvRegistrar = (TextView) findViewById(R.id.tvRegistrar);
        //Se define la opción onClick en el texto -Registrar- que abre la activity RegistrarActivity
        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar=new Intent(v.getContext(), RegistrarActivity.class);
                startActivity(registrar);
            }
        });
    }
    //Función para el botón iniciar sesión que valida si los datos ingresados son correctos y constan en la base de datos
    public void iniciarSesión (View view){
        //Se establece la conexión con la base de datos
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "administracion", null,1);
        //En modo readable (no se modifica)
        SQLiteDatabase bd=admin.getReadableDatabase();
        //Se obtiene el valor ingresado en el EdidText de usuario y contraseña
        String usuarioText = etUser.getText().toString();
        String contraseñaText = etPassword.getText().toString();
        //Se valida que el campo no este vacío
        if(!usuarioText.isEmpty()||!contraseñaText.isEmpty()){
            //Se realiza la operación select para validar usuario y contraseña
            Cursor fila= bd.rawQuery(
                    "select nombres,apellidos,correo, celular, favorito from usuarios where usuario='"+usuarioText+"' and contraseña='"+contraseñaText+"'",null);
            //Se utiliza el cursor para validar si las credenciales son válidas
            if(fila.moveToFirst()){
                Intent datos=new Intent(this, HomeActivity.class);
                datos.putExtra("user", etUser.getText().toString());
                datos.putExtra("nombres", fila.getString(0));
                datos.putExtra("apellidos", fila.getString(1));
                datos.putExtra("correo", fila.getString(2));
                datos.putExtra("celular", fila.getString(3));
                datos.putExtra("favorito", fila.getString(4));
                bd.close();
                //Alerta toast con mensaje de operación exitosa
                Toast.makeText(this,"Inicio de sesión exitoso",
                        Toast.LENGTH_SHORT).show();
                etUser.setText("");
                etPassword.setText("");
                startActivity(datos);
            }else{
                //Alerta toast con mensaje de error
                Toast.makeText(this,"Credenciales incorrectas",
                        Toast.LENGTH_SHORT).show();
                etUser.setText("");
                etPassword.setText("");
                bd.close();
            }

        }else{
            //Alerta toast con mensaje de campos vacíos
            Toast.makeText(this,"Complete todos los campos",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
