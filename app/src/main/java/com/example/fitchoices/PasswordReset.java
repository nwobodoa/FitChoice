package com.example.fitchoices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fitchoices.databinding.ActivityPasswordResetBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordReset extends AppCompatActivity {
 private ActivityPasswordResetBinding binding;
 private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordResetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        user = FirebaseAuth.getInstance().getCurrentUser();
        String newPassword = binding.editTxtEmail.getText().toString();

        user.updatePassword(newPassword).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(PasswordReset.this,"User Password Updated Successfully!",Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}