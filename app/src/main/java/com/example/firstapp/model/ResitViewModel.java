package com.example.firstapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firstapp.model.Resit;

public class ResitViewModel extends ViewModel {
    private final MutableLiveData<Resit> selectedResit = new MutableLiveData<>();

    public void selectResit(Resit resit) {
        selectedResit.setValue(resit);
    }

    public LiveData<Resit> getSelectedResit() {
        return selectedResit;
    }
}
