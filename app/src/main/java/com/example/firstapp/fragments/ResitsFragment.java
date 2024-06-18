package com.example.firstapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.CSVLoader;
import com.example.firstapp.R;
import com.example.firstapp.ResitsAdapter;
import com.example.firstapp.model.Resit;

import java.util.List;

public class ResitsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResitsAdapter adapter;
    private CSVLoader csvLoader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resits, container, false);

        recyclerView = rootView.findViewById(R.id.resits);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        csvLoader = new CSVLoader();
        List<Resit> resitsList = csvLoader.loadCSVData("./resits.csv");

        adapter = new ResitsAdapter(resitsList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
