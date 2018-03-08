package com.example.albin.forgetmenot.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.albin.forgetmenot.R;

import java.util.Calendar;

/**
 * Created by albin on 10/11/16.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month,  day);
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView tvDate = (TextView) getActivity().findViewById(R.id.tvReminderDate);
        int monthnew  = month +1;
        tvDate.setText(view.getDayOfMonth() + "/" + monthnew + "/" + view.getYear());

//        view.getMonth()
    }
}
