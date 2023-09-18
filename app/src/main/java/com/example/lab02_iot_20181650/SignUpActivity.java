package com.example.lab02_iot_20181650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab02_iot_20181650.databinding.ActivityMainBinding;
import com.example.lab02_iot_20181650.dto.Api;
import com.example.lab02_iot_20181650.dto.Results;
import com.example.lab02_iot_20181650.service.RandomUserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    RandomUserService randomUserService;
    private ActivityMainBinding binding;
    TextView txtName;
    TextView txtLast;
    TextView txtCorreo;
    TextView txtContrasena;
    List<Results> results;
    CheckBox chBxVerif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnGetData = findViewById(R.id.btnGetData);
        txtName = findViewById(R.id.txtNombre);
        txtLast = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);
        chBxVerif = findViewById(R.id.chbxAccept);
        btnCreate.setOnClickListener(v -> {
            if(chBxVerif.isChecked()) {
                Intent intent = new Intent(SignUpActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            } else{
                Toast.makeText(this, "No se olvide de aceptar los términos", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, "Usted se encuentra en la vista de Registro", Toast.LENGTH_SHORT).show();
        randomUserService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUserService.class);

        Log.d("msg", "LISTO");
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
;               randomUserService.getApi().enqueue(new Callback<List<Results>>() {
                    @Override
                    public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                        results = response.body();
                        for (Results r : results){
                            txtCorreo.setText(r.getEmail());
                            txtName.setText(r.getName().getFirst());
                            txtLast.setText(r.getName().getLast());
                            txtContrasena.setText(r.getLogin().getPassword());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Results>> call, Throwable t) {

                    }
                });
            }
        });
    }
}