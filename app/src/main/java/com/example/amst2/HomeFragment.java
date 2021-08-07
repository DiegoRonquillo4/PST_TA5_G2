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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
        lvLibros=(ListView) view.findViewById(R.id.lvLibros);
        edtBuscar=(EditText) view.findViewById(R.id.edtBuscar);
        btnBuscar=(ImageButton) view.findViewById(R.id.btnBuscar);
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
    public void cargarLibros() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(),
                "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        listaDescripcion.clear();
        listaTitulo.clear();
        listaLibros.clear();
        Cursor registro=bd.rawQuery("select * from libro",null);
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
        adaptador=new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1,listaLibros);
        lvLibros.setAdapter(adaptador);
        lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public void buscar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(),
                "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String busqueda= edtBuscar.getText().toString();
        listaDescripcion.clear();
        listaTitulo.clear();
        listaLibros.clear();
        Cursor registro=bd.rawQuery("select * from libro where titulo like '%"+busqueda+"%'",null);
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
        adaptador=new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1,listaLibros);
        lvLibros.setAdapter(adaptador);
        lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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