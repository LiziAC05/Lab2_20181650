package com.example.lab02_iot_20181650.service;

import com.example.lab02_iot_20181650.dto.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("/api/")
    Call<Results> getResults();
}
