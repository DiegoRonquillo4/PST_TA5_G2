package com.example.amst2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tvUsuario, tvNombres, tvApellidos, tvCelular, tvCorreo, tvFavorito;
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        tvUsuario=(TextView) view.findViewById(R.id.tvUsuario);
        tvNombres=(TextView) view.findViewById(R.id.tvNombres);
        tvApellidos=(TextView) view.findViewById(R.id.tvApellidos);
        tvCelular=(TextView) view.findViewById(R.id.tvCelular);
        tvCorreo=(TextView) view.findViewById(R.id.tvCorreo);
        tvFavorito=(TextView) view.findViewById(R.id.tvFavorito);
        //Se modifican los TextView con la información enviada a través del Intent del MainActivity
        tvUsuario.setText(getActivity().getIntent().getStringExtra("user"));
        tvNombres.setText("Nombres: "+getActivity().getIntent().getStringExtra("nombres"));
        tvApellidos.setText("Apellidos: "+getActivity().getIntent().getStringExtra("apellidos"));
        tvCorreo.setText("Correo: "+getActivity().getIntent().getStringExtra("correo"));
        tvCelular.setText("Celular: "+getActivity().getIntent().getStringExtra("celular"));
        tvFavorito.setText("Favorito: "+getActivity().getIntent().getStringExtra("favorito"));
        return view;
    }
}