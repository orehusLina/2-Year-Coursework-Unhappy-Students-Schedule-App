package com.example.firstapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.firstapp.R;
import com.example.firstapp.model.UserViewModel;
import com.example.firstapp.model.User;

public class ProfileFragment extends Fragment {

    private UserViewModel userViewModel;

    private TextView textViewName;
    private TextView textViewLastName;
    private TextView textViewRole;
    private Button buttonLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewName = view.findViewById(R.id.textViewName);
        textViewLastName = view.findViewById(R.id.textViewLastName);
        textViewRole = view.findViewById(R.id.textViewRole);
        buttonLogout = view.findViewById(R.id.buttonLogout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    // Update UI with user data
                    textViewName.setText("Имя: " + user.getFirstName());
                    textViewLastName.setText("Фамилия: " + user.getLastName());
                    textViewRole.setText("Роль: " + user.getRole());
                }
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout button click
                // Example: navigate to login screen or perform logout action
            }
        });
    }
}
