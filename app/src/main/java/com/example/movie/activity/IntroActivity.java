package com.example.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.movie.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getInBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}