package com.example.albin.forgetmenot;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.albin.forgetmenot.controller.ReminderListviewAdapter;
import com.example.albin.forgetmenot.controller.TimerListviewAdapter;
import com.example.albin.forgetmenot.database.DBAdapterHelper;
import com.example.albin.forgetmenot.database.DatabaseHelper;
import com.example.albin.forgetmenot.database.ReminderDbControlller;
import com.example.albin.forgetmenot.database.TimerDbController;
import com.example.albin.forgetmenot.database.TimerModel;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.Calendar;
import java.util.List;


//visar en lista över alla påminelser

//http://www.android4devs.com/2015/01/how-to-make-material-design-sliding-tabs.html
//för tutorial till tabsen


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    String title;
    String time;
    String timeToString;
    FloatingActionsMenu fabMenu;
    com.getbase.floatingactionbutton.FloatingActionButton btnNewReminder, btnNewTimer;
    DBAdapterHelper dbAdapterHelper;
    DatabaseHelper dbHelper;
    Context context;
    TimerListviewAdapter mTimerTimerListviewAdapter;
    ListView mListview;
    List<TimerModel> arrayListTimer;
    TimerListviewAdapter timerListviewAdapter;
    TimerDbController timerDbController = new TimerDbController(this);

    Cursor mCursor, rmCursor;
    ReminderListviewAdapter rmlvAdapter;
    ReminderDbControlller reminderDbControlller = new ReminderDbControlller(this);

    //Tabs
    ViewPager vIewPager;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Påminelse", "Timer"};
    int NumofTabs = 2;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabMenu = (FloatingActionsMenu) findViewById(R.id.fab_menu);
        btnNewReminder = (FloatingActionButton) findViewById(R.id.fab_add_reminder);
        btnNewTimer = (FloatingActionButton) findViewById(R.id.fab_add_timer);

        Log.d(TAG, "main oncreate check");


        dbHelper = DatabaseHelper.getInstance(this);
        dbHelper.getReadableDatabase();

        initTabs(); //setting up the tabs.
        createAlarm();


        int intId;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            intId = bundle.getInt("id", 0);
            Log.d(TAG, "TA BORT id: " + intId);
            timerDbController.FinishTimer(intId);

        }


        fabMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {


                btnNewReminder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), AddNewReminder.class);
                        startActivity(i);
                    }
                });
                btnNewTimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showNewTimerAlertDialog();
                        Log.d(TAG, "btn new timer");
                    }
                });
            }

            @Override
            public void onMenuCollapsed() {

            }
        });
    }

    public void createAlarm() {
        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);
        if (alarm) {
            Intent itAlarm = new Intent("ALARM");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, itAlarm, 0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 6000, pendingIntent);

        }
    }




    /**
     * A  method for initializing the tabs and everything about them
     */
    public void initTabs() {
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, NumofTabs);

        // Assigning ViewPager View and setting the adapter
        vIewPager = (ViewPager) findViewById(R.id.pager);
        vIewPager.setAdapter(viewPagerAdapter);

        //assigning te Sliding tab layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);// To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        //Setting custom color for the scroll bar indicator of the tab view
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorPrimaryDark);
            }
        });
        //setting the viewpager for the SlidingTabsLayout
        tabs.setViewPager(vIewPager);
//        viewPagerAdapter.notifyDataSetChanged();
    }


    public void update() {
        viewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                timerDbController.DeleteAllTimers();
                Log.d(TAG, "deleteknappen trycket");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "i onRestartMetoden");
        update();
    }


    private void showNewTimerAlertDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View alertDialogView = inflater.inflate(R.layout.custom_alert_dialog_new_timer, null);
        LinearLayout linAlarmMinutes = (LinearLayout) alertDialogView.findViewById(R.id.linMinuteAlarm);
        final EditText etTimerTime = (EditText) alertDialogView.findViewById(R.id.etTimerTime);
        final EditText etTimerTitle = (EditText) alertDialogView.findViewById(R.id.etTimerTitle);


        linAlarmMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "lin layout clicked");
                etTimerTime.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etTimerTime, InputMethodManager.SHOW_IMPLICIT);
            }

        });

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(alertDialogView);

        alertDialogView.findViewById(R.id.btnSaveTimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = etTimerTitle.getText().toString();
//                time = etTimerTime.getText().toString();
                int time = Integer.valueOf(etTimerTime.getText().toString());
                int timeToMinutes = time * 60000;
                timeToString = String.valueOf(timeToMinutes);

                Log.d(TAG, "alert btn save clicked");
                Log.d(TAG, "title: " + title + " minutes " + time);
                TimerModel model = new TimerModel();
                model.title = title;
                model.minutes = timeToString;
                final Context context = view.getContext();
                boolean insertSuccess = new TimerDbController(context).InsertNewTimer(model);
                if (insertSuccess) {
                    Log.d(TAG, "Inserted success i main från alert dialog");

//                    startNotification(title, timeToString);

                } else {
                    Log.d(TAG, "Inserted FAILED i main från alert dialog");
                }
                alertDialog.dismiss();
                update();
            }
        });

        alertDialogView.findViewById(R.id.btnCancelTimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "alert btn cancel clicked");
                alertDialog.dismiss();
            }
        });
        alertDialog.create();
        alertDialog.show();
    }


    public void startNotification(String title, String timeToString) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);


        int timeInMillis = Integer.valueOf(timeToString);
        String tmp = String.valueOf(timeInMillis / 1000);
        int timeInteger = Integer.parseInt(String.valueOf(tmp));
        String formattedTimeString = String.format("%02d:%02d:%02d", timeInteger / 3600,
                (timeInteger % 3600) / 60, (timeInteger % 60));
        Log.d(TAG, "start notficiataion");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(String.valueOf(formattedTimeString))
                .setSmallIcon(R.drawable.timer_listview)
                .setContentIntent(pIntent)
                .addAction(0, "Ta bort", pIntent)
                .addAction(0, "Ändra", pIntent)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }



}
