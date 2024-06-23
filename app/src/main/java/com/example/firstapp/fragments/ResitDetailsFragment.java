package com.example.firstapp.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.firstapp.StudentAdapter;
import com.example.firstapp.model.Resit;
import com.example.firstapp.model.ResitViewModel;
import com.example.firstapp.model.Student;

import java.util.List;

public class ResitDetailsFragment extends Fragment {

    private ResitViewModel resitViewModel;
    private RecyclerView recyclerViewStudents;
    private StudentAdapter studentAdapter;

    public ResitDetailsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resit_details, container, false);

        resitViewModel = new ViewModelProvider(requireActivity()).get(ResitViewModel.class);

        TextView textViewSubject = view.findViewById(R.id.textViewSubject);
        TextView textViewTeacher = view.findViewById(R.id.textViewTeacher);
        TextView textViewGroups = view.findViewById(R.id.textViewGroups);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewTime = view.findViewById(R.id.textViewTime);
        TextView textViewPlace = view.findViewById(R.id.textViewPlace);

        recyclerViewStudents = view.findViewById(R.id.recyclerViewStudents);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewStudents.setHasFixedSize(true);

        resitViewModel.getSelectedResit().observe(getViewLifecycleOwner(), new Observer<Resit>() {
            @Override
            public void onChanged(Resit resit) {
                if (resit != null) {
                    textViewSubject.setText(resit.getSubject());
                    textViewTeacher.setText(resit.getTeacherListToString());
                    textViewGroups.setText("Группы: " + resit.getGroups().toString());
                    textViewDate.setText(resit.getDate());
                    textViewTime.setText(resit.getTime());
                    textViewPlace.setText(resit.getPlace());

                    List<Student> students = resit.getStudentList();
                    Log.e("AAAAAAAAAA", students.toString());
                    studentAdapter = new StudentAdapter(students);
                    recyclerViewStudents.setAdapter(studentAdapter);
                }
            }
        });

        return view;
    }
}
