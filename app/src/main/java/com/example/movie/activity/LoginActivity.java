package com.example.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.movie.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.loginBtn.setOnClickListener(view -> {
            if (binding.userEdt.getText().toString().isEmpty() ||
                    binding.passEdt.getText().toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill UserName and Password", Toast.LENGTH_SHORT).show();
            } else if (binding.userEdt.getText().toString().equals("test") &&
                    binding.passEdt.getText().toString().equals("123456")) {
                startActivity(new Intent(this, MainActivity.class));
            }else {
                Toast.makeText(this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}