package com.example.rahul.gcm.gcm;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.rahul.gcm.R;
import com.example.rahul.gcm.app.Config;
import com.example.rahul.gcm.app.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Rahul on 18-05-2016.
 */
public class NotificationUtils {
    private static String TAG=NotificationUtils.class.getSimpleName();
    private Context mContext;
    public NotificationUtils(){

    }
    public NotificationUtils(Context mContext){

        this.mContext=mContext;
    }
    public  void showNotificationMessage(final String title,final String message,final String timeStamp,Intent intent){
        if(TextUtils.isEmpty(message))
            return;
        final int icon= R.mipmap.ic_launcher;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent resultPendingIntent=PendingIntent.getActivity(mContext,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        final NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(mContext);
        final Uri alarmSound= Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+mContext.getPackageName()+"raw/notification");
            showSmallNotification(mBuilder, icon, title, message, timeStamp, resultPendingIntent, alarmSound);
            playNotificationSound();
    }
    private void showSmallNotification(NotificationCompat.Builder mBuilder, int icon, String title, String message, String timeStamp, PendingIntent resultPendingIntent, Uri alarmSound) {

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.addLine(message);


        Notification notification;
        notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setSound(alarmSound)
                .setStyle(inboxStyle)
                .setWhen(getTimeMilliSec(timeStamp))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentText(message)
                .build();

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Config.NOTIFICATION_ID, notification);
    }


    // Playing notification sound
    public void playNotificationSound() {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + MyApplication.getInstance().getApplicationContext().getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(MyApplication.getInstance().getApplicationContext(), alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Clears notification tray messages
    public static void clearNotifications() {
        NotificationManager notificationManager = (NotificationManager) MyApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public static long getTimeMilliSec(String timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(timeStamp);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}


