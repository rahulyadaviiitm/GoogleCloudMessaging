package com.example.rahul.gcm.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.rahul.gcm.activity.MainActivity;
import com.example.rahul.gcm.app.Config;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Rahul on 19-05-2016.
 */
public class MyGcmPushReceiver extends GcmListenerService {

    private static final String TAG = MyGcmPushReceiver.class.getSimpleName();

    private NotificationUtils notificationUtils;

    /**
     * Called when message is received.
     *
     * @param from   SenderID of the sender.
     * @param bundle Data bundle containing message data as key/value pairs.
     *               For Set of keys use data.keySet().
     */

    @Override
    public void onMessageReceived(String from, Bundle bundle) {
        String title = bundle.getString("title");
        String message = bundle.getString("message");
        String image = bundle.getString("image");
        String timestamp = bundle.getString("created_at");
        Log.e(TAG, "From: " + from);
        Log.e(TAG, "Title: " + title);
        Log.e(TAG, "message: " + message);
        Log.e(TAG, "image: " + image);
        Log.e(TAG, "timestamp: " + timestamp);
    /**
     * Showing notification with text only
//     */
        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        resultIntent.putExtra("message", message);
        showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
        NotificationUtils notificationUtils = new NotificationUtils();
        notificationUtils.playNotificationSound();
    }

    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }
}
