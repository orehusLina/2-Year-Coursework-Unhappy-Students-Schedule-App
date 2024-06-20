package com.example.firstapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.R;
import com.example.firstapp.ResitAdapter;
import com.example.firstapp.model.Resit;
import com.example.firstapp.model.Student;
import com.example.firstapp.model.Teacher;
import com.example.firstapp.utils.UserAPI;
import com.example.firstapp.model.ResitRequest;
import com.example.firstapp.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllResitsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResitAdapter resitAdapter;

    public AllResitsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_resits, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchResits();

        return view;
    }

    private void fetchResits() {
        UserAPI api = RetrofitClient.getAPI();
        Call<List<ResitRequest>> call = api.getResits();
        call.enqueue(new Callback<List<ResitRequest>>() {
            @Override
            public void onResponse(Call<List<ResitRequest>> call, Response<List<ResitRequest>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ResitRequest> resitRequests = response.body();
                    List<Resit> resits = Resit.convertToResits(resitRequests); // Преобразование в Resit
                    resitAdapter = new ResitAdapter(resits);
                    recyclerView.setAdapter(resitAdapter);
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<List<ResitRequest>> call, Throwable t) {
                // Handle the error
            }
        });
    }

}
