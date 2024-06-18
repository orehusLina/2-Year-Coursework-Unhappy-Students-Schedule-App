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

    private List<Resit> resitsList;

    public ResitAdapter(List<Resit> resitsList) {
        this.resitsList = resitsList;
    }

    public static class ResitViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTextView;
        public TextView facultyTextView;

        public ResitViewHolder(View view) {
            super(view);
            courseTextView = view.findViewById(R.id.courseTextView);
            facultyTextView = view.findViewById(R.id.facultyTextView);

        }
    }

    @NonNull
    @Override
    public ResitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resit_item, parent, false);
        return new ResitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResitViewHolder holder, int position) {
        Resit resit = resitsList.get(position);
        holder.courseTextView.setText(resit.getCourse());
        holder.facultyTextView.setText(resit.getFaculty());

    }

    @Override
    public int getItemCount() {
        return resitsList.size();
    }
}
