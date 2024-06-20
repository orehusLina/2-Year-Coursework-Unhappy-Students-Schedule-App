package com.example.firstapp.fragments;

import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.R;
import com.example.firstapp.ResitAdapter;
import com.example.firstapp.UserViewModel;
import com.example.firstapp.model.Resit;
import com.example.firstapp.model.ResitRequest;
import com.example.firstapp.model.User;
import com.example.firstapp.utils.RetrofitClient;
import com.example.firstapp.utils.UserAPI;

import java.util.List;

public class MyResitsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ResitAdapter resitAdapter;
    private UserViewModel userViewModel;

    public MyResitsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_resits, container, false);

        recyclerView = view.findViewById(R.id.myResitsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            public void onChanged(User user) {
                if (user != null) {
                    // Обновление UI с новыми данными о пользователе
                    fetchUserResits(user);
                }
            }
        });
        return view;
    }

    private void fetchUserResits(User user) {
        UserAPI api = RetrofitClient.getAPI();
        Call<List<ResitRequest>> call = api.getResits();
        call.enqueue(new Callback<List<ResitRequest>>() {
            @Override
            public void onResponse(Call<List<ResitRequest>> call, Response<List<ResitRequest>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ResitRequest> resitRequests = response.body();
                    List<Resit> resits = Resit.convertToResits(resitRequests);
                    displayResitsInTextView(resits);
                } else {
                    Log.d("ERROR", "ошибочка");
                }
            }

            @Override
            public void onFailure(Call<List<ResitRequest>> call, Throwable t) {
                // Handle the error
            }
        });
    }

    private void displayResitsInTextView(List<Resit> resits) {
        resitAdapter = new ResitAdapter(resits);
        recyclerView.setAdapter(resitAdapter);
    }
}