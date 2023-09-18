package com.example.lab02_iot_20181650;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContadorViewModel extends ViewModel {
    private final MutableLiveData<Integer> contador = new MutableLiveData<>();
    public MutableLiveData<Integer> getContador(){
        return contador;
    }
}
