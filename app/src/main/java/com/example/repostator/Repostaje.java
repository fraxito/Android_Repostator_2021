package com.example.repostator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class Repostaje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repostaje);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button botonGuardar = findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escribeDatos();
                finish();
            }
        });

    }

    private void escribeDatos(){
        EditText editPrecio = (EditText) findViewById(R.id.precio);
        EditText editKilometros = (EditText) findViewById(R.id.kilometros);
        EditText editLitros = (EditText) findViewById(R.id.litros);
        DatePicker editFecha = findViewById(R.id.fecha);
        SharedPreferences sp = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int posicion = sp.getInt("listado_size", 0);
        posicion++;
        editor.putString("precio_" + posicion, editPrecio.getText().toString());
        editor.putString("kilometros_" + posicion, editKilometros.getText().toString());
        editor.putString("litros_" + posicion, editLitros.getText().toString());
        editor.putInt("dia_"+posicion, editFecha.getDayOfMonth());
        editor.putInt("mes_"+posicion, editFecha.getMonth());
        editor.putInt("year_"+posicion, editFecha.getYear());

        editor.putInt("listado_size", posicion);

        editor.commit();
    }

}