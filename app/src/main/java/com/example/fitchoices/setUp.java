package com.example.fitchoices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitchoices.databinding.ActivitySetUpBinding;

public class setUp extends AppCompatActivity {
    private ActivitySetUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnNext.setOnClickListener(v -> startActivity(new Intent(setUp.this, Goals.class)));
    }
}