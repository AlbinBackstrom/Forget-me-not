package com.example.albin.forgetmenot;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.albin.forgetmenot.controller.TimerListviewAdapter;
import com.example.albin.forgetmenot.database.TimerDbController;

/**
 * Created by albin on 13/11/16.
 */

public class Tab2 extends Fragment {
    ListView listViewTimer;
    TimerListviewAdapter tlvAdapter;
    Cursor c;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("tab1" , "onCreateView");
        View v = inflater.inflate(R.layout.tab_2, container, false);
        listViewTimer = (ListView)v.findViewById(R.id.listviewTimer);
        TimerDbController timerDbController = new TimerDbController(v.getContext());
        c = timerDbController.GetAllTimers();
        tlvAdapter = new TimerListviewAdapter(getContext(), c);
        listViewTimer.setAdapter(tlvAdapter);
        registerForContextMenu(listViewTimer);
        return v;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Timer");
        menu.add(0,v.getId(), 0, "Edit");
        menu.add(0,v.getId(), 0, "Radera");

    }
}
