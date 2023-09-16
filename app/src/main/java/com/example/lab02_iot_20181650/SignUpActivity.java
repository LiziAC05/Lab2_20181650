package com.example.lab02_iot_20181650;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.lab02_iot_20181650.databinding.ActivityMainBinding;
import com.example.lab02_iot_20181650.dto.Results;
import com.example.lab02_iot_20181650.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    UserService userService;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toast.makeText(this, "Usted se encuentra en la vista de Registro", Toast.LENGTH_SHORT).show();
        userService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);

        userService.getResults().enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if(response.isSuccessful()){
                    Results results = response.body();
                    results.getEmail();
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });
    }
}