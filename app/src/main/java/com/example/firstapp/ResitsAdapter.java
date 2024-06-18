package com.example.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.model.Resit;

import java.util.List;

public class ResitsAdapter extends RecyclerView.Adapter<ResitsAdapter.ResitViewHolder> {

    private List<Resit> resitsList;

    public ResitsAdapter(List<Resit> resitsList) {
        this.resitsList = resitsList;
    }

    public static class ResitViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTextView;
        public TextView facultyTextView;
        // добавьте остальные TextView для всех полей

        public ResitViewHolder(View view) {
            super(view);
            courseTextView = view.findViewById(R.id.courseTextView);
            facultyTextView = view.findViewById(R.id.facultyTextView);
            // инициализируйте остальные TextView
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
        // привязать остальные поля
    }

    @Override
    public int getItemCount() {
        return resitsList.size();
    }
}

