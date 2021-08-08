package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//Activity para registrar un nuevo usuario
public class RegistrarActivity extends AppCompatActivity {
    EditText usuario, nombres, apellidos, correo, celular, favorito, contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //Se inicializan las variables
        usuario=(EditText)findViewById(R.id.etUser);
        celular = (EditText)findViewById(R.id.etCelular);
        nombres = (EditText)findViewById(R.id.etNombres);
        apellidos = (EditText)findViewById(R.id.etApellidos);
        favorito = (EditText)findViewById(R.id.etFavorito);
        contraseña = (EditText)findViewById(R.id.etPassword);
        correo = (EditText)findViewById(R.id.etCorreo);
    }
    //Función registro para botón Registrar (Agrega un registro a la tabla usuarios de la base de datos)
    public void registro(View view){
        //Se establece la conexión con la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        //En modo writable
        SQLiteDatabase bd= admin.getWritableDatabase();
        //Se obtiene los valores ingresados en los EdidText
        String usuarioText= usuario.getText().toString();
        String celularText= celular.getText().toString();
        String nombresText= nombres.getText().toString();
        String apellidosText= apellidos.getText().toString();
        String favoritoText= favorito.getText().toString();
        String contraseñaText= contraseña.getText().toString();
        String correoText= correo.getText().toString();
        //Se valida que no haya campos vacíos
        if(!usuarioText.isEmpty()||!celularText.isEmpty()||!nombresText.isEmpty()||!apellidosText.isEmpty()||!correoText.isEmpty()||!favoritoText.isEmpty()||!contraseñaText.isEmpty()){
            //Se realiza la operación de insertar el registro con los datos proporcionados
            bd.execSQL("insert into usuarios (usuario,nombres,apellidos, correo, celular, favorito, contraseña)"+"values ('"+usuarioText+"','"+nombresText+"','"+apellidosText+"','"+correoText+"','"+celularText+"','"+favoritoText+"','"+contraseñaText+"')");
            bd.close();
            //Se resetea a strings vacíos los EdidText
            celular.setText("");
            nombres.setText("");
            apellidos.setText("");
            favorito.setText("");
            contraseña.setText("");
            correo.setText("");
            usuario.setText("");
            //Alerta toast con mensaje de operación exitosa
            Toast.makeText(this,"Usuario Registrado", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            //Alerta toast con mensaje de error
            Toast.makeText(this,"Por favor, llene todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }
}