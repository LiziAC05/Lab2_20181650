package com.example.lab02_iot_20181650;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class CronometroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        Toast.makeText(this, "Usted se encuentra en la vista Cronómetro", Toast.LENGTH_SHORT).show();

    }
}