package com.example.firstapp;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firstapp.model.User;

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> loggedInUser = new MutableLiveData<>();

    public LiveData<User> getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        loggedInUser.setValue(user);
    }
}
