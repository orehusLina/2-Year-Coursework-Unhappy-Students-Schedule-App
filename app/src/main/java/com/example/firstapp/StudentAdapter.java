package com.example.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imageViewStudentPhoto;
        private TextView textViewStudentName;
        private TextView textViewStudentGroup;
        private TextView textViewStudentSpeciality;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewStudentPhoto = itemView.findViewById(R.id.imageViewStudentPhoto);
            textViewStudentName = itemView.findViewById(R.id.textViewStudentName);
            textViewStudentGroup = itemView.findViewById(R.id.textViewStudentGroup);
            textViewStudentSpeciality = itemView.findViewById(R.id.textViewStudentSpeciality);
        }

        public void bind(Student student) {
            // Здесь вы можете загрузить фото студента, используя библиотеку, например, Glide или Picasso
            // Glide.with(itemView.getContext()).load(student.getPhotoUrl()).into(imageViewStudentPhoto);

            textViewStudentName.setText(student.getFullName());
            textViewStudentGroup.setText("Группа: " + student.getGroup());
            textViewStudentSpeciality.setText("Специальность: " + student.getSpecialty());
        }
    }
}
