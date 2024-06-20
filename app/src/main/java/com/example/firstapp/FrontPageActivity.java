package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;

import com.example.firstapp.fragments.AllResitsFragment;
import com.example.firstapp.fragments.MyResitsFragment;
import com.example.firstapp.fragments.ProfileFragment;
import com.example.firstapp.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FrontPageActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Получение данных о пользователе из Intent, если они есть
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("loggedInUser")) {
            User loggedInUser = (User) intent.getSerializableExtra("loggedInUser");
            if (loggedInUser != null) {
                userViewModel.setLoggedInUser(loggedInUser);
                // Передача loggedInUser в MyResitsFragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("loggedInUser", loggedInUser);

                MyResitsFragment fragment = new MyResitsFragment();
                fragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                bottomNav.setSelectedItemId(R.id.nav_my_resits);
            }
        } else {
            // Если данных о пользователе нет в Intent, загрузить фрагмент по умолчанию
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AllResitsFragment())
                    .commit();
            bottomNav.setSelectedItemId(R.id.nav_all_resits); // Установка выбранного пункта в Bottom Navigation
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_all_resits:
                            selectedFragment = new AllResitsFragment();
                            break;
                        case R.id.nav_my_resits:
                            selectedFragment = new MyResitsFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    return true;
                }
            };
}
