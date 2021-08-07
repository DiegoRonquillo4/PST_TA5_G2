package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    ArrayList<String> listaLibros;
    ArrayList<String> listaTitulo;
    ArrayList<String> listaDescripcion;
    ArrayAdapter adaptador;
    ListView lvLibros;
    EditText edtBuscar2;
    String categoria;
    ImageButton btnBuscar2;
    TextView tvCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        lvLibros=(ListView) findViewById(R.id.lvLibros2);
        edtBuscar2=(EditText) findViewById(R.id.edtBuscar2);
        tvCategoria=(TextView) findViewById(R.id.tvCategoria2);
        categoria= getIntent().getStringExtra("categoria");
        tvCategoria.setText("Categoría: "+categoria);
        listaLibros=new ArrayList<String>();
        listaTitulo=new ArrayList<String>();
        listaDescripcion=new ArrayList<String>();
        cargarCategoria();
        btnBuscar2=(ImageButton) findViewById(R.id.btnBuscar2);
        btnBuscar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
    }
    public void cargarCategoria() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        listaDescripcion.clear();
        listaTitulo.clear();
        listaLibros.clear();

        Cursor registro=bd.rawQuery("select * from libro where categoria='"+categoria+"'",null);
        if(registro.moveToFirst()){
            do{
                String titulo= registro.getString(1);
                String autor= registro.getString(2);
                String editorial= registro.getString(3);
                String libro="\nTitulo: "+titulo+"\nAutor: "+autor+"\n"+"Editorial: "+editorial+"\n";
                listaLibros.add(libro);
                listaTitulo.add(titulo);
                listaDescripcion.add(registro.getString(4));
            }while (registro.moveToNext());
        }
            adaptador=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaLibros);
            lvLibros.setAdapter(adaptador);
            lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener()    {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(CategoryActivity.this);
                    builder.setMessage(listaDescripcion.get(position))
                            .setTitle(listaTitulo.get(position));
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
            });
            bd.close();
        }
    public void buscar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String busqueda= edtBuscar2.getText().toString();
        listaDescripcion.clear();
        listaTitulo.clear();
        listaLibros.clear();
        Cursor registro=bd.rawQuery("select * from libro where categoria='"+categoria+"' and titulo like '%"+busqueda+"%'",null);
        if(registro.moveToFirst()){
            do{
                String titulo= registro.getString(1);
                String autor= registro.getString(2);
                String editorial= registro.getString(3);
                String libro="\nTitulo: "+titulo+"\nAutor: "+autor+"\n"+"Editorial: "+editorial+"\n";
                listaLibros.add(libro);
                listaTitulo.add(titulo);
                listaDescripcion.add(registro.getString(4));
            }while (registro.moveToNext());
        }
        adaptador=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaLibros);
        lvLibros.setAdapter(adaptador);
        lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(CategoryActivity.this);
                builder.setMessage(listaDescripcion.get(position))
                        .setTitle(listaTitulo.get(position));
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        bd.close();
    }
}
