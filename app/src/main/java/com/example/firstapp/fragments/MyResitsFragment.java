package com.example.firstapp.fragments;

import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.firstapp.model.Student;
import com.example.firstapp.model.Teacher;
import com.example.firstapp.model.User;
import com.example.firstapp.utils.RetrofitClient;
import com.example.firstapp.utils.UserAPI;

import java.util.ArrayList;
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
                    List<Student> students = Resit.convertStudentsFromRequests(resitRequests);
                    List<Teacher> teachers = Resit.convertTeachersFromRequests(resitRequests);

                    // Фильтрация пересдач по роли пользователя
                    List<Resit> filteredResits = filterResitsByUserRole(resits, user);
                    Log.d("ПЕРЕСДАЧИ", String.valueOf(filteredResits.size()));
                    for (Resit resit : filteredResits) {
                        Log.v("ГРИШКААА", resit.getSubject());
                    }
                    displayResits(filteredResits);
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

    private List<Resit> filterResitsByUserRole(List<Resit> resits, User user) {
        List<Resit> filteredResits = new ArrayList<>();
        String role = user.getRole(); // Предполагается, что роль хранится в поле role

        if ("Student".equals(role)) {
            for (Resit resit : resits) {
                for (Student student : resit.getStudentList()) {
                    //Log.d("ТУДЕНТЫТЫ", String.valueOf(resit.getStudentList().size()));
                    boolean a = student.matchesUser(user);
                    //Log.e("ITS A MATCH", String.valueOf(a));
                    if (student.matchesUser(user)) {
                        filteredResits.add(resit);
                        break;
                    }
                }
            }
        } else if ("Teacher".equals(role)) {
            for (Resit resit : resits) {
                for (Teacher teacher : resit.getTeacherList()) {
                    if (teacher.matchesUser(user)) {
                        filteredResits.add(resit);
                        break;
                    }
                }
            }
        } else if ("Administrator".equals(role)) {
            filteredResits.addAll(resits);
        }

        return filteredResits;
    }

    private void displayResits(List<Resit> resits) {
        resitAdapter = new ResitAdapter(resits);
        recyclerView.setAdapter(resitAdapter);
    }
}
