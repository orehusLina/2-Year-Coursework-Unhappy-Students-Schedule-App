package com.example.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.model.Resit;

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
        holder.textViewSubject.setText(resit.getSubject());
        holder.textViewTeacher.setText(resit.getTeacherListToString());
        holder.textViewGroups.setText("Группы: " + resit.getGroups().toString());
        holder.textViewDate.setText(resit.getDate());
        holder.textViewTime.setText(resit.getTime());
        holder.textViewPlace.setText(resit.getPlace());
    }

    @Override
    public int getItemCount() {
        return resits.size();
    }

    public static class ResitViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSubject, textViewTeacher, textViewGroups, textViewDate, textViewTime, textViewPlace;

        public ResitViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
            textViewTeacher = itemView.findViewById(R.id.textViewTeacher);
            textViewGroups = itemView.findViewById(R.id.textViewGroups);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
        }
    }

    public void updateData(List<Resit> newResits) {
        resits.clear();
        resits.addAll(newResits);
        notifyDataSetChanged();
    }
}
