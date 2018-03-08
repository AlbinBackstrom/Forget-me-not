package com.example.albin.forgetmenot;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.albin.forgetmenot.controller.ReminderListviewAdapter;
import com.example.albin.forgetmenot.database.DatabaseHelper;
import com.example.albin.forgetmenot.database.ReminderDbControlller;

/**
 * Created by albin on 13/11/16.
 */

public class Tab1 extends Fragment {
    ListView listViewReminder;
    ReminderListviewAdapter rmlvAdapter;
    DatabaseHelper dbHelper;
    Cursor c;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("tab1" , "onAttach");
//        dbHelper = DatabaseHelper.getInstance(context);
//        dbHelper.getReadableDatabase();





    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("tab1" , "onCreateView");
        View v = inflater.inflate(R.layout.tab_1, container, false);
        listViewReminder = (ListView)v.findViewById(R.id.listviewReminder);
        ReminderDbControlller reminderDbControlller = new ReminderDbControlller(v.getContext());
        c = reminderDbControlller.GetAllReminders();
        rmlvAdapter = new ReminderListviewAdapter(getContext(), c);
        listViewReminder.setAdapter(rmlvAdapter);
        registerForContextMenu(listViewReminder);

        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.add(0,v.getId(), 0, "Avklarad");
        menu.add(0,v.getId(), 0, "Ändra");

    }

}
