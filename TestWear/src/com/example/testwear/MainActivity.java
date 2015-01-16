package com.example.testwear;


import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationManagerCompat;
import 	android.support.v4.app.NotificationCompat.WearableExtender;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button sendBt;
	Button sendBt1;
	Button listenerBt;
	Button notifiIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendBt=(Button)findViewById(R.id.send_notify);
		sendBt1=(Button)findViewById(R.id.send_notify1);
		listenerBt=(Button)findViewById(R.id.notifi_listener);
		notifiIntent=(Button)findViewById(R.id.notifi_intent);
		init();
		addWearNotificationListener();
	}
	
	private void addWearNotificationListener() {

        /*String flatIn = Settings.Secure.getStringForUser(this.getContentResolver(),
                "enabled_notification_listeners", ActivityManager.getCurrentUser());
        String packageName = "com.androidwear.home/com.androidwear.interact.clockwork.home.stream.WearNotificationListener";
        if (flatIn == null || !flatIn.contains(packageName)) {
            flatIn = TextUtils.isEmpty(flatIn) ? packageName : flatIn + ":" + packageName;
            Settings.Secure.putStringForUser(this.getContentResolver(), Settings.Secure.ENABLED_NOTIFICATION_LISTENERS,
                    flatIn, ActivityManager.getCurrentUser());
        }*/
    }
	
	public void init(){
		sendBt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, "send notification successful", Toast.LENGTH_SHORT).show();
				//buildNotification2();
				ComponentName compo = new ComponentName("com.google.android.apps.wearable.setup",
				        "com.google.android.clockwork.setup.BrowseFragmentsActivity" );
				try{
				    Intent it = new Intent(MainActivity.this,TicwearPairActivity.class);
				    //it.setComponent(compo);
				    //it.setAction("android.bluetooth.device.action.PAIRING_REQUEST");
				    startActivity(it);
				}catch(Exception e){
				    
				}
			}});
		sendBt1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "send notification successful", Toast.LENGTH_SHORT).show();
				buildNotification2();
			}});
		
		listenerBt.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it=new Intent(MainActivity.this,NotificationListenerActivity.class);
                startActivity(it);
            }
        });
		
		notifiIntent.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
            }
        });
    }
	
	public void buildNotification1(){
    	int notificationId = 001;
    	// Build intent for notification content
    	Intent viewIntent = new Intent(this,MainActivity.class);
    	//viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
    	PendingIntent viewPendingIntent =
    	        PendingIntent.getActivity(this,0, viewIntent,0);

    	NotificationCompat.Builder notificationBuilder=
    	        new NotificationCompat.Builder(this)
    	        .setSmallIcon(R.drawable.ic_launcher)
    	        .setContentTitle("test")
    	        .setContentText("notification")
    	        .setContentIntent(viewPendingIntent);

    	// Get an instance of the NotificationManager service
    	NotificationManagerCompat notificationManager =
    	        NotificationManagerCompat.from(this);

    	// Build the notification and issues it with notification manager.
    	notificationManager.notify(notificationId,notificationBuilder.build());
    }
    
    public void buildNotification2(){
    	// Create an intent for the reply action
    	int notificationId = 002;
    	Intent actionIntent = new Intent(this, MainActivity.class);
    	PendingIntent actionPendingIntent =
    	        PendingIntent.getActivity(this, 0, actionIntent,
    	                PendingIntent.FLAG_UPDATE_CURRENT);
    	PendingIntent actionPendingIntent1 =
    	        PendingIntent.getActivity(this, 0, actionIntent,
    	                PendingIntent.FLAG_UPDATE_CURRENT);

    	// Create the action
    	NotificationCompat.Action action =
    	        new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
    	                getString(R.string.send_notifi), actionPendingIntent)
    	                .build();
    	NotificationCompat.Action action1 =
    	        new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
    	                getString(R.string.hello_world), actionPendingIntent1)
    	                .build();

    	// Build the notification and add the action via WearableExtender
    	Notification notification =
    	        new NotificationCompat.Builder(this)
    	                .setSmallIcon(R.drawable.ic_launcher)
    	                .setContentTitle("test title")
    	                .setContentText("test message test message test message test message test message test message "
    	                        + "test message test message test message test message test message test message test message test message test message ")
    	                .setGroup("test")
    	                .extend(new WearableExtender().addAction(action))
    	                .extend(new WearableExtender().addAction(action1))
    	                .build();
    	
    	Notification notification1 =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("test title 2")
                        .setContentText("test message 2")
                        .extend(new WearableExtender().addAction(action))
                        .setGroup("test")
                        .build();
    	
    	NotificationManagerCompat notificationManager =
    	        NotificationManagerCompat.from(this);

    	// Build the notification and issues it with notification manager.
    	notificationManager.notify(notificationId,notification1);
    	//notificationManager.notify(notificationId+2,notification);
    }
    
    public void buildNotification3(){
    	int notificationId = 003;
    	// Create builder for the main notification
    	// Build intent for notification content
    	Intent viewIntent = new Intent(this,MainActivity.class);
    	//viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
    	PendingIntent viewPendingIntent =
    	        PendingIntent.getActivity(this,0, viewIntent,0);
    	
    	// Create the action
    	NotificationCompat.Action action =
    	        new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
    	                getString(R.string.send_notifi), viewPendingIntent)
    	                .build();
    	NotificationCompat.Action action1 =
    	        new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
    	                getString(R.string.send_notifi), viewPendingIntent)
    	                .build();
    	
    	NotificationCompat.Builder notificationBuilder =
    	        new NotificationCompat.Builder(this)
    			.setLargeIcon( BitmapFactory. decodeResource(getResources(),R.drawable.ipmsg_service))
    			.setSmallIcon(R.drawable.ic_launcher_smsmms)
    	        .setContentTitle("Page 1")
    	        //.setContentText("Short message xxxxx xxxx  yyyyy zzzz  yyyy zzzz ttttt  ddjddd  jlkjkljkl  kjlkjlkj jlk jlkjkl jklj klj kl xxxxx xxxx  yyyyy zzzz  yyyy zzzz ttttt  ddjddd  jlkjkljkl  kjlkjlkj jlk jlkjkl jklj klj kl")
    	        .setContentText("Short message")
    	        .setContentIntent(viewPendingIntent);

    	// Create a big text style for the second page
    	BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
    	secondPageStyle.setBigContentTitle("Page 2")
    	               .bigText("A lot of text...Short message xxxxx xxxx  yyyyy zzzz  yyyy zzzz ttttt  ddjddd  jlkjkljkl  kjlkjlkj jlk jlkjkl jklj klj kl Short message xxxxx xxxx  yyyyy zzzz  yyyy zzzz ttttt  ddjddd  jlkjkljkl  kjlkjlkj jlk jlkjkl jklj klj kl");

    	// Create second page notification
    	Notification secondPageNotification =
    	        new NotificationCompat.Builder(this)
    	        .setStyle(secondPageStyle)
    	        .build();

    	// Add second page with wearable extender and extend the main notification
    	Notification twoPageNotification =
    	        new WearableExtender()
    	                .addPage(secondPageNotification)
    	                .extend(notificationBuilder)
    	                .build();

    	// Issue the notification
    	NotificationManagerCompat notificationManager =
    	        NotificationManagerCompat.from(this);
    	notificationManager.notify(notificationId, twoPageNotification);
    }
    
    public void buildNotification4(){
        NotificationCompat.Builder builder =createNotificationBuilder(this,"test",MainActivity.class);
        BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle("Page 2")
                       .bigText("A lot of text...Short message xxxxx xxxx  yyyyy zzzz  yyyy zzzz ttttt  ddjddd  jlkjkljkl  kjlkjlkj jlk jlkjkl jklj klj kl Short message xxxxx xxxx  yyyyy zzzz  yyyy zzzz ttttt  ddjddd  jlkjkljkl  kjlkjlkj jlk jlkjkl jklj klj kl");

        builder.setStyle(secondPageStyle);
        Intent actionIntent = new Intent(this, MainActivity.class);
        PendingIntent actionPendingIntent =
                PendingIntent.getActivity(this, 0, actionIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
                        getString(R.string.send_notifi), actionPendingIntent)
                        .build();
        builder.setGroup("test");
        //builder.extend(new WearableExtender().addAction(action));
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(100, builder.build());
        NotificationCompat.Builder builder1 =createNotificationBuilder(this,"test",NotificationListenerActivity.class);
        builder1.setGroup("test");
        notificationManager.notify(101, builder.build());
        
    }
    
    private static NotificationCompat.Builder createNotificationBuilder(Context mContext, String jsonString,
            Class<?> classic) {
          
          Intent notificationIntent = new Intent(mContext, classic);
          //notificationIntent.putExtra(key, jsonString.getBytes());
          PendingIntent notificationPendingIntent = PendingIntent.getActivity(mContext, 0,
              notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

          NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext).setSmallIcon(
              R.drawable.ic_launcher).extend(
              new NotificationCompat.WearableExtender().setDisplayIntent(notificationPendingIntent)
                  .setCustomSizePreset(Notification.WearableExtender.SIZE_LARGE));
          
          return notificationBuilder;

        }

}
