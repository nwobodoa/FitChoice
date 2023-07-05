package com.example.fitchoices;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitchoices.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    private String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
       binding.btnSignIn2.setEnabled(false);

        binding.textInputEmail.addTextChangedListener(mTextWatcher);
        binding.textInputPassword.addTextChangedListener(mTextWatcher);


        binding.txtViewForgotPwd.setOnClickListener(v -> {
            startActivity(new Intent(this, PasswordReset.class));
        });

        binding.btnSignIn2.setOnClickListener(v -> {

            if(email.isEmpty()){
                binding.textInputEmail.setError("Email is empty");
                binding.textInputEmail.requestFocus();
                return;
            }
            if(password.isEmpty()){
                binding.textInputPassword.setError("Password is empty");
                binding.textInputPassword.requestFocus();
                return;
            }

    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                   startActivity(new Intent(LoginActivity.this, Setup.class));
                    FirebaseUser user = mAuth.getCurrentUser();
                    assert user != null;
                    String userName = user.getDisplayName();
                    Toast.makeText(this,"Welcome " + userName + "!",Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Please check your login credentials, Authentication Failed.",
                            Toast.LENGTH_LONG).show();

                }
            });

        });


    }
    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            email = binding.textInputEmail.getText().toString();
            password = binding.textInputPassword.getText().toString();
            binding.btnSignIn2.setEnabled(!email.isEmpty() && !password.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    };
}
