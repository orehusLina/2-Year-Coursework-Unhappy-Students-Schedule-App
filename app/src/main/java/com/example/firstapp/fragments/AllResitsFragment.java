package com.example.firstapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.R;
import com.example.firstapp.ResitAdapter;
import com.example.firstapp.model.Resit;
import com.example.firstapp.model.ResitRequest;
import com.example.firstapp.model.ResitViewModel;
import com.example.firstapp.utils.RetrofitClient;
import com.example.firstapp.utils.UserAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllResitsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResitAdapter resitAdapter;
    private ResitViewModel resitViewModel;

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

        resitViewModel = new ViewModelProvider(requireActivity()).get(ResitViewModel.class);
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
                    setupRecyclerView(resits);
                } else {
                    // Обработка ошибки
                }
            }

            @Override
            public void onFailure(Call<List<ResitRequest>> call, Throwable t) {
                // Обработка ошибки
            }
        });
    }

    private void setupRecyclerView(List<Resit> resits) {
        resitAdapter = new ResitAdapter(resits);
        recyclerView.setAdapter(resitAdapter);

        // Установка слушателя кликов на карточки пересдач
        resitAdapter.setResitClickListener(new ResitAdapter.ResitClickListener() {
            @Override
            public void onResitClick(Resit resit) {
                // Устанавливаем выбранную пересдачу в ViewModel
                resitViewModel.selectResit(resit);

                // Открываем фрагмент с деталями пересдачи
                openResitDetailsFragment();
            }
        });
    }

    private void openResitDetailsFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ResitDetailsFragment fragment = new ResitDetailsFragment();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
