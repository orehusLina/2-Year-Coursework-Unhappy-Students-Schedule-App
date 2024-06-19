package com.example.firstapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapp.MainActivity;
import com.example.firstapp.R;
import com.example.firstapp.model.User;
import com.example.firstapp.UserAPI;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends Fragment {
    public static String TAG = "TAG";

    EditText firstName, lastName, username, password;
    RadioGroup roleRadioGroup;
    Button registerButton;
    TextView logInLink;
    User user;

    public Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        firstName = view.findViewById(R.id.first_name_register);
        lastName = view.findViewById(R.id.last_name_register);
        username = view.findViewById(R.id.username_register);
        password = view.findViewById(R.id.password_register);
        roleRadioGroup = view.findViewById(R.id.role_radio_group);
        registerButton = view.findViewById(R.id.register_button);
        logInLink = view.findViewById(R.id.log_in_link);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        logInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle log in link click
                Toast.makeText(getContext(), "Log in link clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void registerUser() {
        /*
        user = new User();
        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());

        int selectedRoleId = roleRadioGroup.getCheckedRadioButtonId();
        switch (selectedRoleId) {
            case R.id.role_student:
                user.setRole("Student");
                break;
            case R.id.role_teacher:
                user.setRole("Teacher");
                break;
            case R.id.role_admin:
                user.setRole("Admin");
                break;
            default:
                // Handle default case
                break;
        }


         */
        Log.d(TAG, "onClick: FirstName " + firstName + " Username " + username);
        SendPostReq(user);
    }

    private void SendPostReq(final User user) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<Void> call = userAPI.signUp(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(getContext(), "You have signed up successfully & saved your data!!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to sign up!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get Response!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
