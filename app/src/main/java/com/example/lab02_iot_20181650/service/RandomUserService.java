package com.example.lab02_iot_20181650.service;

import com.example.lab02_iot_20181650.dto.Api;
import com.example.lab02_iot_20181650.dto.Results;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomUserService {
    @GET("/api/")
    Call<List<Results>> getApi();
}
