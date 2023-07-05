package com.example.fitchoices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitchoices.databinding.ActivitySetupBinding;

public class Setup extends AppCompatActivity {
    EditText editText;
    String gender;
    String strDateOfBirth;
    private ActivitySetupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences("PREFERENCE",MODE_PRIVATE);

        String FirstTime = preferences.getString("FirstTimeInstall", "");

    if(FirstTime.equals("Yes")){

        startActivity(new Intent(this,MainActivity2.class));

    }else{
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FirstTimeInstall", "Yes");
        editor.apply();

    }




        SetupViewModel setupViewModel = new ViewModelProvider(this).get(SetupViewModel.class);
        binding.btnNext.setOnClickListener(v -> {
            inputValidation();
        });

        setupViewModel.getWhyWeAsk().observe(this, message -> {
            binding.showWhy.setText(message);
        });

        binding.txtHeightWhy.setOnClickListener(view -> {
            setupViewModel.updateWhyWeAsk(getResources().getString(R.string.heightWhy));
        });

        binding.txtGenderWhy.setOnClickListener(view -> {
            setupViewModel.updateWhyWeAsk("Please enter your gender. It is important for calorie budget" +
                    " Calorie Budget-female bodies need fewer calories");
        });
        binding.txtAgeWhy.setOnClickListener(view -> setupViewModel.updateWhyWeAsk("Date of birth will be used to calculate your age on any date-" +
                " calorie and nutrient needs change with age"));


    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbFemale:
                if (checked)
                    gender = binding.rbFemale.getText().toString();
                break;
            case R.id.rbMale:
                if (checked)
                    gender = binding.rbMale.getText().toString();
                break;

        }
    }


    public void inputValidation() {
        strDateOfBirth = binding.editDateOfBirth.getText().toString();
        String strWeight = binding.editTextWeight.getText().toString();
        String strHeight = binding.editTextHeight.getText().toString();
        int selBtn = binding.rdGroup.getCheckedRadioButtonId();
        String strTargetWeight = binding.tartgetWeight.getText().toString();

        if (selBtn == -1) {
            Toast.makeText(this, "Gender not selected", Toast.LENGTH_SHORT).show();
            return;
        }
        if (strWeight.isEmpty()) {
            Toast.makeText(this, "please enter your current weight", Toast.LENGTH_SHORT).show();
            return;
        }
        if (strDateOfBirth.isEmpty()) {
            Toast.makeText(this, "please enter your date of birth", Toast.LENGTH_SHORT).show();
            return;
        }
        if (strHeight.isEmpty()) {
            Toast.makeText(this, "please enter your height", Toast.LENGTH_SHORT).show();
            return;
        }
        if (strTargetWeight.isEmpty()) {
            Toast.makeText(this, "please enter your target weight", Toast.LENGTH_SHORT).show();
            return;
        }

            try {

                double weight = Double.parseDouble(strWeight);
                double height = Double.parseDouble(strHeight);
                double bmi = ((weight / height / height) * 10000);
                double targetWeight = Double.parseDouble(strTargetWeight);


                Bundle bundle = new Bundle();
                bundle.putString("gender", gender);
                bundle.putDouble("weight", weight);
                bundle.putDouble("height",height);
                bundle.putDouble("Bmi", bmi);
                bundle.putString("age", strDateOfBirth);
                bundle.putDouble("targetWeight", targetWeight);
                Intent intent = new Intent(Setup.this, BMIActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




        public void showDatePickerDialog (View view){
            DialogFragment newFragment = new DatePickerFragment(binding.editDateOfBirth);
            newFragment.show(getSupportFragmentManager(), "datePicker");
        }
    }
