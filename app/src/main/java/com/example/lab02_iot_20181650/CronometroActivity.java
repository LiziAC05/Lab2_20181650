package com.example.lab02_iot_20181650;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class CronometroActivity extends AppCompatActivity {
    boolean timeStarted = false;
    TextView timerText;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        Toast.makeText(this, "Usted se encuentra en la vista Cronómetro", Toast.LENGTH_SHORT).show();
        timerText = (TextView) findViewById(R.id.timerText);
        Button btnStart = findViewById(R.id.btnIniciar);
        btnStart.setOnClickListener(view -> {
            timeStarted = true;
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        time++;
                        timerText.setText(getTimerText());
                    });
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        });
        Button btnStop = findViewById(R.id.btnParar);
        btnStop.setOnClickListener(view -> {
            timeStarted = false;
            timerTask.cancel();
        });
        Button btnContinue = findViewById(R.id.btnRetomar);
        btnContinue.setOnClickListener(view -> {
            timeStarted = true;
            iniciarCuenta();
        });
        Button btnReset = findViewById(+R.id.btnLimpiar);
        btnReset.setOnClickListener(view -> {
            AlertDialog.Builder limpiar = new AlertDialog.Builder(this);
            limpiar.setTitle("Limpiar la Cuenta");
            limpiar.setMessage("¿Desea limpiar e iniciar una nueva cuenta?");
            limpiar.setPositiveButton("Limpiar Cuenta", (dialogInterface, i) -> {
                if (timerTask != null) {
                    timerTask.cancel();
                    time = 0.0;
                    timeStarted = false;
                    timerText.setText(formatTime(0, 0, 0));
                }
            });
            limpiar.setNeutralButton("Cancelar", (dialogInterface, i) -> {

            });
            limpiar.show();
        });

        timer = new Timer();
    }

    public  void iniciarCuenta(){
        timeStarted = true;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    time++;
                    timerText.setText(getTimerText());
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }


    private String getTimerText(){
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);
        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours){
        return String.format("%02d",hours) + ":"+ String.format("%02d",minutes) + ":"+String.format("%02d",seconds);
    }
}