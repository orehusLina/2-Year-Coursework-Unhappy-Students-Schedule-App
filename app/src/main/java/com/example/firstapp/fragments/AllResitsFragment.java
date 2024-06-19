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
        Call<List<ResitRequest>> call = api.getResits();
        call.enqueue(new Callback<List<ResitRequest>>() {
            @Override
            public void onResponse(Call<List<ResitRequest>> call, Response<List<ResitRequest>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ResitRequest> resitRequests = response.body();
                    List<Resit> resits = convertToResits(resitRequests); // Преобразование в Resit
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

    private List<Resit> convertToResits(List<ResitRequest> resitRequests) {
        List<Resit> resits = new ArrayList<>();
        List<Student> existingStudents = new ArrayList<>();
        List<Teacher> existingTeachers = new ArrayList<>();

        // Проходим по всем ResitRequest для формирования уникальных пересдач
        for (ResitRequest resitRequest : resitRequests) {
            Student student = new Student(resitRequest);
            Teacher teacher = new Teacher(resitRequest);

            // Проверяем, существует ли уже такая пересдача в списке resits
            boolean found = false;
            for (Resit existingResit : resits) {

                // Добавляем пересдачу студенту и преподавателю
                student.addUniqueResit(existingResit);
                teacher.addUniqueResit(existingResit);

                // Здесь определяем условия уникальности пересдачи
                if (isSameResit(existingResit, resitRequest)) {
                    // Найдена уже существующая пересдача, добавляем уникальных студентов и преподавателей
                    existingResit.addUniqueStudent(student);
                    existingResit.addUniqueTeacher(teacher);
                    found = true;
                    break;
                }
            }

            // Если не найдена существующая пересдача, создаем новую
            if (!found) {
                Resit resit = new Resit(
                        resitRequest.getSubject(),
                        new ArrayList<>(), // Список студентов будет заполняться позже
                        new HashSet<>(), // Список преподавателей будет заполняться позже
                        resitRequest.getCourse(),
                        resitRequest.getFaculty(),
                        resitRequest.getDegree(),
                        resitRequest.getFormOfStudy(),
                        resitRequest.getExamType(),
                        resitRequest.getCommissionRetake(),
                        new ArrayList<>(), // Список групп
                        new ArrayList<>(), // Список специальностей
                        resitRequest.getDate(),
                        resitRequest.getTime(),
                        resitRequest.getPlace()
                );

                // Добавляем уникальных студентов и преподавателей к новой пересдаче
                resit.addUniqueStudent(student);
                resit.addUniqueTeacher(teacher);
                resit.addUniqueGroup(resitRequest.getGroup());
                resit.addUniqueSpecialty(resitRequest.getSpecialty());

                // Добавляем новую пересдачу в список
                resits.add(resit);
            }
        }

        return resits;
    }

    // Метод для определения уникальности пересдачи
    private boolean isSameResit(Resit resit, ResitRequest resitRequest) {
        return resit.getDate().equals(resitRequest.getDate()) &&
                resit.getTime().equals(resitRequest.getTime()) &&
                resit.getPlace().equals(resitRequest.getPlace()) &&
                resit.getExamType().equals(resitRequest.getExamType()); // Добавьте другие условия по необходимости
    }

}
