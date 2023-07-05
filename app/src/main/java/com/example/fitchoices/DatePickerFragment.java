package com.example.fitchoices;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {

    final EditText editText;
    public DatePickerFragment(EditText editDateOfBirth) {
        editText =  editDateOfBirth;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireContext(),this,year,month + 1,day);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        editText.setText(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
