package com.example.albin.forgetmenot.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.albin.forgetmenot.R;

import java.util.Calendar;

/**
 * Created by albin on 10/11/16.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDat, int minute) {

        TextView tvTime = (TextView) getActivity().findViewById(R.id.tvReminderTime);
        tvTime.setText(pad(view.getHour()) + ":" + pad(view.getMinute()));
    }

    /**
     * This method checks if the minutes and its two digits contains a zero as
     * first digit. Standard Java/Android do not show two digits if it start with zero.
     * 01:01 = 1:1
     * 22:04 = 22:4
     * With this method the minutes will be displayed as in the first column
     *
     * @param input integer from setText(pad(view.getHour()))
     * @return formatted string
     */
    public String pad(int input) {

        String str = "";

        if (input > 10) {

            str = Integer.toString(input);
        } else {
            str = "0" + Integer.toString(input);

        }
        return str;
    }

}
