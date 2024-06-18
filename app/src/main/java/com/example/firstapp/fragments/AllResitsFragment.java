package com.example.firstapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private ResitAdapter adapter;
    private TextView resitsTextView;

    public AllResitsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_resits, container, false);
        resitsTextView = view.findViewById(R.id.resitsTextView);

        UserAPI api = RetrofitClient.getAPI();
        Call<List<Resit>> call = api.getResits();
        call.enqueue(new Callback<List<Resit>>() {
            @Override
            public void onResponse(Call<List<Resit>> call, Response<List<Resit>> response) {
                if (response.isSuccessful()) {
                    List<Resit> resits = response.body();
                    /*
                    for (Resit resit : resits) {
                        Log.d("Resit", resit.toString());
                    }
                     */
                    displayResits(resits);
                } else {
                    Log.e("Error", "Request failed with status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Resit>> call, Throwable t) {
                Log.e("Error", "Request failed: " + t.getMessage());
            }
        });

        //recyclerView = view.findViewById(R.id.recycler_view_resits);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //loadResits();

        return view;
    }

    private void displayResits(List<Resit> resits) {
        StringBuilder sb = new StringBuilder();
        for (Resit resit : resits) {
            sb.append(resit.toString()).append("\n\n");
        }
        resitsTextView.setText(sb.toString());
    }
    /*
    private void loadResits() {
        UserAPI userAPI = RetrofitClient.getClient().create(UserAPI.class);
        Call<List<Resit>> call = userAPI.getResits();
        call.enqueue(new Callback<List<Resit>>() {
            @Override
            public void onResponse(@NonNull Call<List<Resit>> call, @NonNull Response<List<Resit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Resit> resits = response.body();
                    adapter = new ResitAdapter(resits);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Failed to load resits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Resit>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Failed to load resits", Toast.LENGTH_SHORT).show();
            }
        });
    }
     */
}
