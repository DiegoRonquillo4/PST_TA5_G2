package com.example.amst2;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//HomeFragment que incluye una ListView con los libros registrados y una opción para buscar por título
public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView lvLibros;
    ArrayList<String> listaLibros;
    ArrayList<String> listaTitulo;
    ArrayList<String> listaDescripcion;
    ArrayAdapter adaptador;
    EditText edtBuscar;
    ImageButton btnBuscar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        //Se instancian las variables
        lvLibros=(ListView) view.findViewById(R.id.lvLibros);
        edtBuscar=(EditText) view.findViewById(R.id.edtBuscar);
        btnBuscar=(ImageButton) view.findViewById(R.id.btnBuscar);
        //Se asocia la función buscar al onClick del botón buscar
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
        listaLibros=new ArrayList<String>();
        listaTitulo=new ArrayList<String>();
        listaDescripcion=new ArrayList<String>();
        cargarLibros();
        return view;
    }
    //Función para cargar libros a la ListView al abrir el fragment Home
    public void cargarLibros() {
        //Se establece la conexión con la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(),
                "admin", null, 1);
        //En modo writable (se modifica)
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Se vacían los ArrayLists por si estaban llenos previamente
        listaDescripcion.clear();
        listaTitulo.clear();
        listaLibros.clear();
        //Se seleccionan todos los registros de libros con Cursor
        Cursor registro=bd.rawQuery("select * from libro",null);
        if(registro.moveToFirst()){
            //Para cada registro se obtienen los datos y se añaden a los ArrayLists respectivos
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
        //Se añade la lista de libros a un adaptador para mostrarlos en la ListView
        adaptador=new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1,listaLibros);
        lvLibros.setAdapter(adaptador);
        lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Se establece un diálogo de alerta con la descripción del libro al hacer click sobre el.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setMessage(listaDescripcion.get(position))
                        .setTitle(listaTitulo.get(position));
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        bd.close();
    }
    //Función para cargar libros a la ListView según búsqueda de título
    public void buscar() {
        //Se establece la conexión con la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(),
                "admin", null, 1);
        //En modo writable (modifica)
        SQLiteDatabase bd = admin.getWritableDatabase();
        String busqueda= edtBuscar.getText().toString();
        listaDescripcion.clear();
        listaTitulo.clear();
        listaLibros.clear();
        //Se seleccionan los registros donde el titulo contenga la búsqueda ingresada
        Cursor registro=bd.rawQuery("select * from libro where titulo like '%"+busqueda+"%'",null);
        if(registro.moveToFirst()){
            //Para cada registro que cumpla se añaden los valores a los ArrayLists
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
        //Se muestra la lista de libros en una ListView por medio de un adaptador
        adaptador=new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1,listaLibros);
        lvLibros.setAdapter(adaptador);
        lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Se establece un cuadro de diálogo al hacer click en los elementos de la lista
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setMessage(listaDescripcion.get(position))
                        .setTitle(listaTitulo.get(position));
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        bd.close();
    }
}