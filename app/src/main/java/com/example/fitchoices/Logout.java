package com.example.fitchoices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fitchoices.databinding.ActivityLogoutBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {
private ActivityLogoutBinding binding;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth= FirebaseAuth.getInstance();
//        BtnLogout.setOnClickListener(new viewOnClickListener){
//            FirebaseAuth.getInstance().signOut();
//        }

    }
}