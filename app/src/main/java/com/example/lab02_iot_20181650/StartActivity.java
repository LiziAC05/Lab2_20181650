package com.example.lab02_iot_20181650;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toast.makeText(this, "Usted se encuentra en la vista de Inicio", Toast.LENGTH_SHORT).show();

        Button btnContador = findViewById(R.id.btnContador);
        btnContador.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, ContadorActivity.class);
            startActivity(intent);
        });

        Button btnCronometro = findViewById(R.id.btnCronometro);
        btnCronometro.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, CronometroActivity.class);
            startActivity(intent);
        });

    }
}