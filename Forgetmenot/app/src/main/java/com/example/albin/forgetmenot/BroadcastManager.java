package com.example.albin.forgetmenot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by albin on 13/11/16.
 */
//http://stackoverflow.com/questions/34583280/set-notification-for-specific-date-and-time
public class BroadcastManager extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String yourDate  = "13/11/2016";
            String yourHour = "21:07:00";
            Date d = new Date();
            DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat hour = new SimpleDateFormat("HH:mm:ss");

            if (date.equals(yourDate) && hour.equals(yourHour)){
                Intent it = new Intent(context, MainActivity.class);
                createNotification(context, it, "new message", "body", "this is a message");
            }
        }catch (Exception e){
            Log.i("date","error == "+e.getMessage());

        }

    }
//ändra import android.support.v4.app.NotificationCompat; till v7 om det inte funkar?

    private void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title, CharSequence descricao) {
        Log.d("createNotification", "metoden");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.drawable.ic_search_white_18dp);
        builder.setContentIntent(p);
        Notification n = builder.build();
        n.vibrate = new long[]{150,300,150,400};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(R.drawable.ic_search_white_18dp, n);

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }catch (Exception e){
            Log.d("create notification ", e.getMessage());
        }
    }
}
