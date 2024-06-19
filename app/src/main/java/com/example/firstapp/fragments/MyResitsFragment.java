package com.example.firstapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.firstapp.R;
import com.example.firstapp.model.User;

public class MyResitsFragment extends Fragment {

    public MyResitsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_resits, container, false);

        // Получение данных о пользователе из аргументов
        Bundle args = getArguments();
        if (args != null && args.containsKey("loggedInUser")) {
            User loggedInUser = (User) args.getSerializable("loggedInUser");
            if (loggedInUser != null) {
                // Пример вывода данных о пользователе в TextView
                TextView usernameTextView = view.findViewById(R.id.usernameTextView);
                TextView fullNameTextView = view.findViewById(R.id.fullNameTextView);
                TextView roleTextView = view.findViewById(R.id.roleTextView);

                usernameTextView.setText("Username: " + loggedInUser.getUsername());
                fullNameTextView.setText("Full Name: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
                roleTextView.setText("Role: " + loggedInUser.getRole());
            }
        }

        // Далее ваш код фрагмента

        return view;
    }

}
