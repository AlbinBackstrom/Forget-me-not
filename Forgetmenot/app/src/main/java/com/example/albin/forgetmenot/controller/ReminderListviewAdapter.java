package com.example.albin.forgetmenot.controller;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.albin.forgetmenot.R;
import com.example.albin.forgetmenot.database.ReminderDbControlller;

/**
 * Created by albin on 12/11/16.
 */

public class ReminderListviewAdapter extends CursorAdapter {


    public ReminderListviewAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_reminder_listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvReminderTitle = (TextView)view.findViewById(R.id.tvReminderTitle);
        TextView tvReminderDate = (TextView)view.findViewById(R.id.tvReminderDate);
        TextView tvReminderTime = (TextView)view.findViewById(R.id.tvReminderTime);

        String title = cursor.getString(cursor.getColumnIndex(ReminderDbControlller.COLUMN_NAME_TITLE_TR));
        String date = cursor.getString(cursor.getColumnIndex(ReminderDbControlller.COLUMN_NAME_DATE_TR));
        String time = cursor.getString(cursor.getColumnIndex(ReminderDbControlller.COLUMN_NAME_TIME_TR));

        tvReminderTitle.setText(title);
        tvReminderDate.setText(date);
        tvReminderTime.setText(time);

    }
}
