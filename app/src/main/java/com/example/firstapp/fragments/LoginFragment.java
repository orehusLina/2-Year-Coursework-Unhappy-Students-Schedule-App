package com.example.firstapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.firstapp.FrontPageActivity;
import com.example.firstapp.MainActivity;
import com.example.firstapp.R;
import com.example.firstapp.model.User;
import com.example.firstapp.utils.UserAPI;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {
    private EditText email, password;
    private Button submitButton;
    private TextView signUpLink;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        submitButton = view.findViewById(R.id.submit_button);
        signUpLink = view.findViewById(R.id.sign_up_link);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle sign up link click
                Toast.makeText(getContext(), "Sign up link clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loginUser() {
        final User user = new User();
        user.setUsername(email.getText().toString());
        user.setPassword(password.getText().toString());
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
        Call<Void> call = userAPI.login(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 200) {
                    Intent intent = new Intent(getActivity(), FrontPageActivity.class);
                    startActivity(intent);
                    getActivity().finish(); // закрываем текущую активность
                    Toast.makeText(getContext(), "You have logged in successfully!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to login!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get Response!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
