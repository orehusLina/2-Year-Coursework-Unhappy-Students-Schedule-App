package com.example.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.model.Resit;
import com.example.firstapp.model.ResitRequest;

import java.util.List;

public class ResitAdapter extends RecyclerView.Adapter<ResitAdapter.ResitViewHolder> {

    private List<Resit> resits;

    public ResitAdapter(List<Resit> resits) {
        this.resits = resits;
    }

    @NonNull
    @Override
    public ResitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resit, parent, false);
        return new ResitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResitViewHolder holder, int position) {
        Resit resit = resits.get(position);
        holder.textViewCourse.setText(resit.getCourse());
        holder.textViewSubject.setText(resit.getSubject());
        holder.textViewDate.setText(resit.getDate());
        holder.textViewTime.setText(resit.getTime());
        holder.textViewTeacher.setText(resit.getTeacherListToString());
        holder.textViewPlace.setText(resit.getPlace());
        // Bind other fields here if needed
    }

    @Override
    public int getItemCount() {
        return resits.size();
    }

    public static class ResitViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCourse, textViewSubject, textViewDate, textViewTime, textViewTeacher, textViewPlace;

        public ResitViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourse = itemView.findViewById(R.id.textViewCourse);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewTeacher = itemView.findViewById(R.id.textViewTeacher);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
            // Initialize other TextViews here if needed
        }
    }
}
