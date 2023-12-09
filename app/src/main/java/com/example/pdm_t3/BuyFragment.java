package com.example.pdm_t3;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class BuyFragment extends Fragment {

    private TextView buyVersa;
    private TextView buySentra;
    private TextView buyAltima;
    private TextView buyNissanZ;
    private TextView buyNissanGtr;

    SQLite_OpenHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.buy, container, false);

        buyVersa = view.findViewById(R.id.buy_versa);
        buySentra = view.findViewById(R.id.buy_sentra);
        buyAltima = view.findViewById(R.id.buy_altima);
        buyNissanZ = view.findViewById(R.id.buy_nissan_z);
        buyNissanGtr = view.findViewById(R.id.buy_nissan_gtr);

        Bundle args = getArguments();

        helper = new SQLite_OpenHelper(getContext(), "BDUsuarios", null, 4);

        Cursor cursor = helper.getPedidosDeUnCliente(args.getInt("id"));

        Bundle contadorPedidos = obtenerConteoPedidosPorCarro(cursor);

        if (args != null) {
            // Modificar el texto según sea necesario
            if (buyVersa != null) {
                buyVersa.setText(String.valueOf(contadorPedidos.getInt("1", 0)));
            }
            if (buySentra != null) {
                buySentra.setText(String.valueOf(contadorPedidos.getInt("2", 0)));
            }
            if (buyAltima != null) {
                buyAltima.setText(String.valueOf(contadorPedidos.getInt("3", 0)));
            }
            if (buyNissanZ != null) {
                buyNissanZ.setText(String.valueOf(contadorPedidos.getInt("4", 0)));
            }
            if (buyNissanGtr != null) {
                buyNissanGtr.setText(String.valueOf(contadorPedidos.getInt("5", 0)));
            }
        }

        return view;
    }

    public Bundle obtenerConteoPedidosPorCarro(Cursor cursorPedidos) {
        Bundle conteoPedidosBundle = new Bundle();

        if (cursorPedidos != null && cursorPedidos.moveToFirst()) {
            do {
                // Asegúrate de obtener el índice de la columna correctamente
                int indexCarroID = cursorPedidos.getColumnIndex("Carro_ID");

                // Verifica si el índice es válido antes de intentar acceder a la columna
                if (indexCarroID >= 0) {
                    int carroID = cursorPedidos.getInt(indexCarroID);

                    // Verifica si el carroID ya está en el Bundle
                    if (conteoPedidosBundle.containsKey(String.valueOf(carroID))) {
                        int cantidadActual = conteoPedidosBundle.getInt(String.valueOf(carroID));
                        conteoPedidosBundle.putInt(String.valueOf(carroID), cantidadActual + 1);
                    } else {
                        // Si no está en el Bundle, agrega la entrada con un contador de 1
                        conteoPedidosBundle.putInt(String.valueOf(carroID), 1);
                    }
                }
            } while (cursorPedidos.moveToNext());
        }

        // Importante: Cierra el cursor cuando hayas terminado de usarlo.
        if (cursorPedidos != null && !cursorPedidos.isClosed()) {
            cursorPedidos.close();
        }

        return conteoPedidosBundle;
    }
}