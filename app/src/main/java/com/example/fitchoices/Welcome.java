package com.example.fitchoices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.fitchoices.databinding.ActivityWelcomeBinding;

public class Welcome extends AppCompatActivity {
    private ActivityWelcomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();

        Uri uri = Uri.parse("android.resource://"+ getPackageName()+"/"+R.raw.video2);
        binding.videoView.setVideoURI(uri);
        binding.videoView.start();

        binding.videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        binding.btnNew.setOnClickListener(v ->{
            startActivity(new Intent(Welcome.this, SignupActivity.class));

        });
        binding.btnSignIn.setOnClickListener(v -> startActivity(new Intent(Welcome.this,LoginActivity.class)));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        binding.videoView.resume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        binding.videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.videoView.suspend();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.videoView.stopPlayback();
    }
}