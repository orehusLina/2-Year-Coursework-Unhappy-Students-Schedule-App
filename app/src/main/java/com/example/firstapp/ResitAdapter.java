package com.example.firstapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstapp.model.Resit;
import com.example.firstapp.model.TeacherPicture;
import com.example.firstapp.utils.RetrofitClient;
import com.example.firstapp.utils.UserAPI;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResitAdapter extends RecyclerView.Adapter<ResitAdapter.ResitViewHolder> {

    private List<Resit> resits;
    private ResitClickListener listener;
    public ResitAdapter(List<Resit> resits) {
        this.resits = resits;
    }

    public void setResitClickListener(ResitClickListener listener) {
        this.listener = listener;
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

        String firstTeacherFullName = resit.getFirstTeacherName();

        holder.textViewSubject.setText(resit.getSubject());
        holder.textViewTeacher.setText(resit.getTeacherListToString());
        holder.textViewGroups.setText("Группы: " + resit.getGroups().toString());
        holder.textViewDate.setText(resit.getDate());
        holder.textViewTime.setText(resit.getTime());
        holder.textViewPlace.setText(resit.getPlace());
        loadTeacherPhoto(firstTeacherFullName, holder.imageViewTeacherPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onResitClick(resit);
                }
            }
        });
    }

    private void loadTeacherPhoto(String teacherName, CircleImageView imageView) {
        // Используем Retrofit для получения списка фотографий преподавателей
        UserAPI userAPI = RetrofitClient.getAPI();
        Call<List<TeacherPicture>> call = userAPI.getTeacherPictures();
        call.enqueue(new Callback<List<TeacherPicture>>() {
            @Override
            public void onResponse(Call<List<TeacherPicture>> call, Response<List<TeacherPicture>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TeacherPicture> teacherPictures = response.body();

                    // Находим фотографию нужного преподавателя по его имени
                    for (TeacherPicture teacherPicture : teacherPictures) {
                        Log.d("who", teacherPicture.toString());
                        // Предполагаем, что имя преподавателя сравнивается по полному совпадению
                        Log.e("TEACHERNAME", teacherName);
                        Log.e("TEACHERTEACHER", teacherPicture.getName());
                        if (teacherName.equals(teacherPicture.getName())) {
                            Log.e("AAAAAAAAAAA", "FFFFFFFFFFFFFFF");
                            String teacherPhotoUrl = teacherPicture.getUrl();

                            // Загрузка фотографии с помощью Glide
                            Glide.with(imageView.getContext())
                                    .load(teacherPhotoUrl)
                                    .placeholder(R.drawable.ic_teacher) // Опциональное изображение заглушки
                                    .error(R.drawable.ic_teacher) // Опциональное изображение для ошибки
                                    .into(imageView);
                            return; // Выходим из цикла, как только найдем нужную фотографию
                        }
                    }

                    // Если фотография не найдена, можно установить изображение заглушки
                    Glide.with(imageView.getContext())
                            .load(R.drawable.ic_teacher)
                            .placeholder(R.drawable.ic_teacher)
                            .error(R.drawable.ic_teacher)
                            .into(imageView);
                } else {
                    // Обработка ошибки запроса
                    Log.e("ResitAdapter", "Failed to get teacher pictures: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<TeacherPicture>> call, Throwable t) {
                // Обработка ошибки
                Log.e("ResitAdapter", "Error loading teacher pictures", t);
            }
        });
    }


    @Override
    public int getItemCount() {
        return resits.size();
    }

    public static class ResitViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSubject, textViewTeacher, textViewGroups, textViewDate, textViewTime, textViewPlace;
        CircleImageView imageViewTeacherPhoto;

        public ResitViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
            textViewTeacher = itemView.findViewById(R.id.textViewTeacher);
            textViewGroups = itemView.findViewById(R.id.textViewGroups);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
            imageViewTeacherPhoto = itemView.findViewById(R.id.imageViewTeacherPhoto);
        }
    }

    public interface ResitClickListener {
        void onResitClick(Resit resit);
    }
    public void updateData(List<Resit> newResits) {
        resits.clear();
        resits.addAll(newResits);
        notifyDataSetChanged();
    }
}
