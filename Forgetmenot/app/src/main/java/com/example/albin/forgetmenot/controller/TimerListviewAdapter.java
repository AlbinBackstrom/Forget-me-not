package com.example.albin.forgetmenot.controller;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.albin.forgetmenot.MainActivity;
import com.example.albin.forgetmenot.R;
import com.example.albin.forgetmenot.database.TimerDbController;


/**
 * Created by albin on 11/11/16.
 */

public class TimerListviewAdapter extends CursorAdapter {
    private static final String TAG = TimerListviewAdapter.class.getSimpleName();

    private int timeInMillis;
    private static CountDownTimer countDownTimer;
   private RadioButton rdBtnTimer;
    private CursorAdapter adapter;
    private int id, timeInteger;
    private String title, time, tmpTime, formattedTimeString;
    private TextView tvTitleListview, tvTimerListview;
    private TimerDbController timerDbController;
    private LinearLayout linLayClickToStart;

    public TimerListviewAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_timer_listview, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {

        timerDbController = new TimerDbController(context);
        id = cursor.getInt(cursor.getColumnIndex(TimerDbController.COLUMN_NAME_ID_TT));
        title = cursor.getString(cursor.getColumnIndex(TimerDbController.COLUMN_NAME_TITLE_TT));
        time = cursor.getString(cursor.getColumnIndex(TimerDbController.COLUMN_NAME_TIME_TT));
        tvTitleListview = (TextView) view.findViewById(R.id.tvTitelListView);
        tvTimerListview = (TextView) view.findViewById(R.id.tvTimerListview);
        linLayClickToStart = (LinearLayout) view.findViewById(R.id.linLayClickToStart);

        tmpTime = String.valueOf(timeInMillis / 1000);
        timeInteger = Integer.parseInt(String.valueOf(tmpTime));
        formattedTimeString = String.format("%02d:%02d:%02d", timeInteger / 3600,
                (timeInteger % 3600) / 60, (timeInteger % 60));
        tvTimerListview.setText(formattedTimeString);
        tvTitleListview.setText(title);

        timeInMillis = Integer.valueOf(time);



        linLayClickToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "clieckeligrgn");
                startCountdownTimer(title, timeInMillis, context);
            }
        });


        rdBtnTimer = (RadioButton) view.findViewById(R.id.radioBtnTimer);
        rdBtnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerDbController.FinishTimer(id);
                Log.d(TAG, "Radio klickad");
                //efter radion är klickd ska listview uppdateras


            }
        });


    }


    //datan ska laddas in, en toast visas att tryck för att starta
    private void startCountdownTimer(final String title, final int timeInMillis, final Context context) {

        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long timeInMillis) {

                tmpTime = String.valueOf(timeInMillis / 1000);
                timeInteger = Integer.parseInt(String.valueOf(tmpTime));
                formattedTimeString = String.format("%02d:%02d:%02d", timeInteger / 3600,
                        (timeInteger % 3600) / 60, (timeInteger % 60));
                tvTimerListview.setText(formattedTimeString);
                tvTitleListview.setText(title);

            }

            @Override
            public void onFinish() {

                showNotification(title, context, id);
                Log.d(TAG, "Nu är det slut");

            }
        }.start();
    }

    private void showNotification(String title, Context context, int id) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("id", id);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);


        NotificationCompat.Action action = new NotificationCompat.Action
                .Builder(R.drawable.ic_close_white_18dp,
                "Stäng av", pIntent)
                .build();

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context.getApplicationContext());
        notification
                .setContentTitle("Timer")
                .setContentText(title)
                .setSmallIcon(R.drawable.timer_listview)
                .addAction(action)
                .setContentIntent(pIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setAutoCancel(true)
                .setSound(soundUri)
//                .setVibrate(new long[]{ 1000, 1000, 1000, 1000, 1000})
                .setLights(Color.CYAN, 500, 500);
        notificationManager.notify(0, notification.build());

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wl.acquire(15000);

    }


    private void showTimerDialog(String title, Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Timer");
        alertDialog.setMessage(title);
        alertDialog.setNegativeButton("Turn off alarm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("alertdialog", "cancel");
            }
        });
        alertDialog.setPositiveButton("Snooza", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("alert postitc", "snooze");
            }
        });
        alertDialog.create();
        alertDialog.show();
    }


}
