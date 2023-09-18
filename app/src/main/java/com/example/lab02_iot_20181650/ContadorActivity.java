package com.example.lab02_iot_20181650;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab02_iot_20181650.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutorService;

public class ContadorActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    int i = 104;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);
        Toast.makeText(this, "Usted se encuentra en la vista Contador", Toast.LENGTH_SHORT).show();
        ApplicationThreads application = (ApplicationThreads) getApplication();
        ExecutorService executorService = application.executorService;
        //View Model para el Contador
        ContadorViewModel contadorViewModel = new ViewModelProvider(ContadorActivity.this).get(ContadorViewModel.class);
        contadorViewModel.getContador().observe(this, contador -> {
            TextView txtContador = findViewById(R.id.txtContador);
            txtContador.setText(String.valueOf(contador));
        });
        Button btnContador = findViewById(R.id.btnInicaContadot);
        btnContador.setOnClickListener(view -> executorService.execute(() -> {
            for (int i = 104; i <= 226; i++) {
                contadorViewModel.getContador().postValue(i); //o1
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(i == 226){
                Toast.makeText(this, "Cuenta terminada", Toast.LENGTH_LONG).show();
            }
        }));
    }
}