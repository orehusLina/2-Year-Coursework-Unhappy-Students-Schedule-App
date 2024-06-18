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
import com.example.firstapp.UserAPI;
import com.example.firstapp.model.Resit;
import com.example.firstapp.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllResitsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResitAdapter resitAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_resits, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchResits();
        return view;
    }

    private void fetchResits() {
        UserAPI api = RetrofitClient.getAPI();
        Call<List<Resit>> call = api.getResits();
        call.enqueue(new Callback<List<Resit>>() {
            @Override
            public void onResponse(Call<List<Resit>> call, Response<List<Resit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Resit> resits = response.body();
                    resitAdapter = new ResitAdapter(resits);
                    recyclerView.setAdapter(resitAdapter);
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<List<Resit>> call, Throwable t) {
                // Handle the error
            }
        });
    }
}
