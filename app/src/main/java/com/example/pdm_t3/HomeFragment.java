package com.example.pdm_t3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private TextView homeNombreTextView;
    private TextView homeRfcTextView;
    private TextView homeCorreoTextView;
    private TextView homePasswordTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home, container, false);

        // Obtener la referencia al TextView home_nombre
        homeNombreTextView = view.findViewById(R.id.home_nombre);
        homeRfcTextView = view.findViewById(R.id.home_rfc);
        homeCorreoTextView = view.findViewById(R.id.home_email);
        homePasswordTextView = view.findViewById(R.id.home_password);

        Bundle args = getArguments();

        if (args != null) {
            // Modificar el texto según sea necesario
            if (homeNombreTextView != null) {
                homeNombreTextView.setText(args.getString("nombre"));
            }
            if (homeRfcTextView != null) {
                homeRfcTextView.setText(args.getString("rfc"));
            }
            if (homeCorreoTextView != null) {
                homeCorreoTextView.setText(args.getString("correo"));
            }
            if (homePasswordTextView != null) {
                homePasswordTextView.setText(args.getString("password"));
            }
        }

        return view;
    }

}
