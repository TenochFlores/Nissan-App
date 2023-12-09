package com.example.pdm_t3;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CarsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.autos, container, false);

        // Obtén una referencia al botón utilizando el ID asignado en autos.xml
        Button botonVersa = view.findViewById(R.id.boton_versa);

        botonVersa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BuyFragment()).commit();
                }
            }
        });

        return view;
    }
}
