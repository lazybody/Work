package com.example.testwear;


import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationListenerActivity extends Activity{

    protected MyReceiver mReceiver = new MyReceiver();
    public static String INTENT_ACTION_NOTIFICATION = "it.gmariotti.notification";

    protected TextView title;
    protected TextView text;
    protected TextView subtext;
    protected ImageView largeIcon;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listener);
        Log.v("wh", "notification onCreate");
        //Retrieve ui elements
        title = (TextView) findViewById(R.id.nt_title);
        text = (TextView) findViewById(R.id.nt_text);
        subtext = (TextView) findViewById(R.id.nt_subtext);
        largeIcon = (ImageView) findViewById(R.id.nt_largeicon);
        
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (mReceiver == null) mReceiver = new MyReceiver();
        registerReceiver(mReceiver, new IntentFilter(INTENT_ACTION_NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(mReceiver);
    }


    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null) {
                Bundle extras = intent.getExtras();
                String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
                int notificationIcon = extras.getInt(Notification.EXTRA_SMALL_ICON);
                Bitmap notificationLargeIcon = ((Bitmap) extras.getParcelable(Notification.EXTRA_LARGE_ICON));
                CharSequence notificationText = extras.getCharSequence(Notification.EXTRA_TEXT);
                CharSequence notificationSubText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);

                title.setText(notificationTitle);
                text.setText(notificationText);
                subtext.setText(notificationSubText);

                if (notificationLargeIcon != null) {
                    largeIcon.setImageBitmap(notificationLargeIcon);
                }
            }

        }
    }
}
