package com.example.fitchoices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fitchoices.databinding.ActivityBmiactivityBinding;

public class BMIActivity extends AppCompatActivity {
    ActivityBmiactivityBinding binding;
    TextView result;
    String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBmiactivityBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            result = binding.txtViewResult;

            Bundle b = new Bundle();
            double bmi = b.getDouble("bmi");
            String gender = b.getString("gender");
            double weight = b.getDouble("weight");
            String strAge = b.getString("age");
            double targetWeight = b.getDouble("targetWeight");
            double height = b.getDouble("height");


            if(bmi < 18.5){
                status = "Underweight";

            }
            else if (bmi < 25){
                status = "Normal";

            }
           else if (bmi < 30){
                status = "Overweight";
            }
            else{
                status = "Obese";
            }
    }

    public void finish(View view) {
        startActivity(new Intent(this,MainActivity2.class));

    }
}