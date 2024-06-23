package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.firstapp.fragments.AllResitsFragment;
import com.example.firstapp.fragments.FilterResitsFragment;
import com.example.firstapp.fragments.MyResitsFragment;
import com.example.firstapp.fragments.ProfileFragment;
import com.example.firstapp.fragments.AddResitFragment;
import com.example.firstapp.model.User;
import com.example.firstapp.model.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FrontPageActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private Toolbar toolbar;
    private FloatingActionButton fabAddResit;
    private FloatingActionButton fabFilterResit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        fabAddResit = findViewById(R.id.fab_add_resit);
        fabFilterResit = findViewById(R.id.fab_filter_resit);

        fabAddResit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new AddResitFragment());
            }
        });

        fabFilterResit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new FilterResitsFragment());
            }
        });

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
                toolbar.setTitle(R.string.my_resits_title);
            }
        } else {
            // Если данных о пользователе нет в Intent, загрузить фрагмент по умолчанию
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AllResitsFragment())
                    .commit();
            bottomNav.setSelectedItemId(R.id.nav_all_resits); // Установка выбранного пункта в Bottom Navigation
            toolbar.setTitle(R.string.all_resits_title);
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
                            toolbar.setTitle(R.string.all_resits_title);
                            showFabButtons();
                            break;
                        case R.id.nav_my_resits:
                            selectedFragment = new MyResitsFragment();
                            toolbar.setTitle(R.string.my_resits_title);
                            showFabButtons();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            toolbar.setTitle(R.string.profile_title);
                            hideFabButtons();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    return true;
                }
            };

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void showFabButtons() {
        fabAddResit.show();
        fabFilterResit.show();
    }

    private void hideFabButtons() {
        fabAddResit.hide();
        fabFilterResit.hide();
    }
}
