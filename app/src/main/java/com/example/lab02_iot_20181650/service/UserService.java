package com.example.lab02_iot_20181650.service;

import com.example.lab02_iot_20181650.dto.Results;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("/api/")
    Call<ArrayList<Results>> getResults();
}
