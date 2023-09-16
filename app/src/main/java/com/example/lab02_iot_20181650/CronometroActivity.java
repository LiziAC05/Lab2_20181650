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
    Button btnStart;
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
        btnStart = (Button) findViewById(R.id.btnIniciar);
        Button btnStop = (Button) findViewById(R.id.btnParar);
        Button btnContinue = (Button) findViewById(R.id.btnRetomar);
        Button btnReset = (Button) findViewById(+R.id.btnLimpiar);

    }

    public  void iniciarCuenta(){
        timeStarted = true;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void pararCuenta(){
        timerTask.cancel();
    }

    public void retomarCuenta(){

    }

    public void limpiarCuenta(){
        AlertDialog.Builder limpiar = new AlertDialog.Builder(this);
        limpiar.setTitle("Limpiar la Cuenta");
        limpiar.setMessage("¿Desea limpiar e iniciar una nueva cuenta?");
        limpiar.setPositiveButton("Limpiar Cuenta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (timerTask != null){
                    timerTask.cancel();
                    time = 0.0;
                    timeStarted = false;
                    timerText.setText(formatTime(0,0,0));
                }
            }
        });
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