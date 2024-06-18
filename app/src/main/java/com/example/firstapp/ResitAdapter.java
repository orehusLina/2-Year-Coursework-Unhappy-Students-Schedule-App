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

    private List<Resit> resitList;

    public ResitAdapter(List<Resit> resitList) {
        this.resitList = resitList;
    }

    @NonNull
    @Override
    public ResitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resit, parent, false);
        return new ResitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResitViewHolder holder, int position) {
        Resit resit = resitList.get(position);
        holder.courseTextView.setText(resit.getCourse());
        holder.facultyTextView.setText(resit.getFaculty());
        // Set other fields as needed
    }

    @Override
    public int getItemCount() {
        return resitList.size();
    }

    public static class ResitViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTextView;
        public TextView facultyTextView;

        public ResitViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTextView = itemView.findViewById(R.id.courseTextView);
            facultyTextView = itemView.findViewById(R.id.facultyTextView);
            // Initialize other TextViews as needed
        }
    }
}