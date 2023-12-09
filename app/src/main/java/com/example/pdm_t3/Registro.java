package com.example.pdm_t3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Registro extends AppCompatActivity {

    Button btnGrabarUsu;
    EditText txtNomUsu, txtRfcUsu, txtCorUsu, txtPasUsu;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BDUsuarios", null, 4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnGrabarUsu = (Button) findViewById(R.id.registro_boton);
        txtNomUsu = (EditText) findViewById(R.id.registro_editTextNombre);
        txtRfcUsu = (EditText) findViewById(R.id.registro_editTextRFC);
        txtCorUsu = (EditText) findViewById(R.id.registro_editTextCorreo);
        txtPasUsu = (EditText) findViewById(R.id.registro_editTextPassword);

        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                helper.insertarRegistro(String.valueOf(txtNomUsu.getText()),
                        String.valueOf(txtRfcUsu.getText()),
                        String.valueOf(txtCorUsu.getText()),
                        String.valueOf(txtPasUsu.getText()));
                helper.cerrar();

                Toast.makeText(getApplicationContext(), "Registro almacenado con exito",
                        Toast.LENGTH_SHORT).show();

                Intent intent_to_Login = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent_to_Login);
            }
        });
    }
}