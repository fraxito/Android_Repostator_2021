package com.example.repostator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.repostator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent ventanaCaptura = new Intent(this, Repostaje.class);

        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ventanaCaptura);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<String> listadoRepostajes = new ArrayList<>();
        ListView listaVista;
        SharedPreferences sp = getSharedPreferences("datos", Context.MODE_PRIVATE);

        int size = sp.getInt("listado_size", 0);
        String precio = "";
        String kilometros = "";
        String litros = "";
        String dia = "";
        String mes = "";
        String year = "";
        double costeTotal = 0;

        for (int i=1; i<= size; i++){
            precio = sp.getString("precio_"+i, "0");
            kilometros = sp.getString("kilometros_"+i, "0");
            litros = sp.getString("litros_"+i, "0");
            costeTotal = Double.valueOf(precio) * Double.valueOf(litros);
            dia = String.valueOf(sp.getInt("dia_"+i, 0));
            mes = String.valueOf(sp.getInt("mes_"+i, 0));
            year = String.valueOf(sp.getInt("year_"+i, 0));

            listadoRepostajes.add(kilometros + " Km " + litros
                    + " L " + precio + " €/L coste: " +
                    String.format("%.2f",costeTotal) +
                    " fecha: " + dia + "/" + mes + "/" + year);
        }

        listaVista = findViewById(R.id.marcoLista); //enlaza el controlador con la vista

        //crea un objeto para comunicar el array list con el listview
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listadoRepostajes
        );

        //enlaza el arraylist con el listview para que se vea en pantalla
        listaVista.setAdapter(arrayAdapter);


    }

}