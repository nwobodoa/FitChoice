package com.example.fitchoices;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitchoices.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static android.content.ContentValues.TAG;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        progressBar = binding.loading;
    }

    public void registerNewUser(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String email, password, confirmPassword, userName;
        email = binding.editTxtEmail.getText().toString();
        password = binding.editTextPwd.getText().toString();
        confirmPassword = binding.editTxtConfirmPwd.getText().toString();
        userName = binding.editTextUsername.getText().toString();

        if (password.trim().length() < 5) {
            Toast.makeText(this, "password too short!", Toast.LENGTH_LONG).show();
            binding.editTextPwd.requestFocus();
            return;
        }

        if (!password.trim().equals(confirmPassword.trim())) {
            Toast.makeText(this, "Password mismatch!", Toast.LENGTH_LONG).show();
            binding.editTextPwd.requestFocus();
            return;
        }
        if (email.isBlank()) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            binding.editTxtEmail.requestFocus();
            return;
        }
        if (userName.isBlank()) {
            Toast.makeText(this, "please enter your Username", Toast.LENGTH_LONG).show();
            binding.editTextUsername.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(),
                            "Registration successful!",
                        Toast.LENGTH_LONG).show();
               progressBar.setVisibility(View.GONE);

                FirebaseUser user = mAuth.getCurrentUser();
                UserProfileChangeRequest update = new UserProfileChangeRequest.Builder()
                        .setDisplayName(userName)
                                .build();
                assert user != null;
                user.updateProfile(update);


                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            } else {
                Toast.makeText(getApplicationContext(),
                        "Registration Failed! Try again",
                        Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);

            }
        }
    });
    }
}