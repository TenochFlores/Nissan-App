package com.example.pdm_t3;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;


public class CarsFragment extends Fragment {

    SQLite_OpenHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.autos, container, false);

        helper = new SQLite_OpenHelper(getContext(), "BDUsuarios", null, 4);

        Bundle args = getArguments();

        // Obtén una referencia al botón utilizando el ID asignado en autos.xml
        Button botonVersa = view.findViewById(R.id.boton_versa);
        botonVersa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(args != null)
                    helper.insertarPedido(args.getInt("id"),1);
                    Toast.makeText(getContext(),"Has sido añadido a la lista de espera de Versa",Toast.LENGTH_LONG).show();
            }
        });

        Button botonSentra = view.findViewById(R.id.boton_sentra);
        botonSentra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.insertarPedido(args.getInt("id"),2);
                Toast.makeText(getContext(),"Has sido añadido a la lista de espera de Sentra",Toast.LENGTH_LONG).show();
            }
        });

        Button botonAltima = view.findViewById(R.id.boton_altima);
        botonAltima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.insertarPedido(args.getInt("id"),3);
                Toast.makeText(getContext(),"Has sido añadido a la lista de espera de Altima",Toast.LENGTH_LONG).show();
            }
        });


        Button botonNissanZ = view.findViewById(R.id.boton_nissan_z);
        botonNissanZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.insertarPedido(args.getInt("id"),4);
                Toast.makeText(getContext(),"Has sido añadido a la lista de espera de Nissan Z",Toast.LENGTH_LONG).show();
            }
        });


        Button botonGtr = view.findViewById(R.id.boton_gtr);
        botonGtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.insertarPedido(args.getInt("id"),5);
                Toast.makeText(getContext(),"Has sido añadido a la lista de espera de Nissan GTR",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
