package com.example.pdm_t3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDUsuarios",null,4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Boton Iniciar sesion
        Button btnLogin = findViewById(R.id.boton_iniciar_sesion);

        btnLogin.setOnClickListener(view -> {

            // Cajas de texto
            EditText correo = findViewById(R.id.registro_editTextCorreo);
            EditText password = findViewById(R.id.registro_editTextPassword);

            try {
                Cursor respuesta = helper.getUsuarioByCorreoPassword
                        (correo.getText().toString(), password.getText().toString());

                if(respuesta.getCount() > 0){
                    // Obtener los índices de las columnas
                    if (respuesta != null && respuesta.moveToFirst()) {
                        // Obtener los índices de las columnas
                        int idIndex = respuesta.getColumnIndex("_ID");
                        int nombreIndex = respuesta.getColumnIndex("Nombre");
                        int rfcIndex = respuesta.getColumnIndex("Rfc");
                        int correoIndex = respuesta.getColumnIndex("Correo");
                        int passwordIndex = respuesta.getColumnIndex("Password");

                        // Obtener los valores de las columnas
                        int id = respuesta.getInt(idIndex);
                        String nombre = respuesta.getString(nombreIndex);
                        String rfc = respuesta.getString(rfcIndex);
                        String email = respuesta.getString(correoIndex);
                        String pas = respuesta.getString(passwordIndex);

                        // Cerrar el cursor
                        respuesta.close();

                        Bundle bundle = new Bundle();
                        bundle.putInt("id", id);
                        bundle.putString("nombre", nombre);
                        bundle.putString("rfc", rfc);
                        bundle.putString("correo", email);
                        bundle.putString("password", pas);


                        Intent intentLogin = new Intent(this, Principal.class);
                        intentLogin.putExtra("values",bundle);
                        startActivity(intentLogin);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",
                            Toast.LENGTH_SHORT).show();
                }

                correo.setText("");
                password.setText("");
                correo.findFocus();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(),"Error al leer la base de datos",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Boton registrarse
        TextView btnSignUp = findViewById(R.id.text_sign_up);
        btnSignUp.setOnClickListener(view -> {
            Intent intentSignUp = new Intent(this, Registro.class);
            startActivity(intentSignUp);
        });
    }
}