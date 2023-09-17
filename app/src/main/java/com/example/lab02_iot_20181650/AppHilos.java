package com.example.lab02_iot_20181650;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppHilos extends Application {
    public ExecutorService executorService = Executors.newFixedThreadPool(4);
}
