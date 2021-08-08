package com.example.amst2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class CategoryFragment extends Fragment {
    //Fragment que contiene los botones de cada categoría de libro disponible y llevan a activity CategoryActivity
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnAventura;
    Button btnFiccion;
    Button btnInfantil;
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
//Se establecen 3 botones con sus respectivas acciones onClick
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_category, container, false);
        btnAventura=(Button) view.findViewById(R.id.btnAventura);
        btnFiccion=(Button) view.findViewById(R.id.btnFiccion);
        btnInfantil=(Button) view.findViewById(R.id.btnInfantil);
        //Se abre Activity Categoría mandando como información la categoría seleccionada (el botón presionado)
        btnAventura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoria=new Intent(getActivity(), CategoryActivity.class);
                categoria.putExtra("categoria", "Aventura");
                startActivity(categoria);
            }
        });
        btnFiccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoria=new Intent(getActivity(), CategoryActivity.class);
                categoria.putExtra("categoria", "Ficción");
                startActivity(categoria);
            }
        });
        btnInfantil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoria=new Intent(getActivity(), CategoryActivity.class);
                categoria.putExtra("categoria", "Infantil");
                startActivity(categoria);
            }
        });
        return view;
    }
}