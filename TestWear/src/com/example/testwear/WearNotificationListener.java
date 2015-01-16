package com.example.testwear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcel;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;


@SuppressLint("NewApi")
public class WearNotificationListener extends NotificationListenerService{
    
    public final String TAG="WearNotificationListener";
    public final static String REMOVE_NOTIFICATION_ACTION="remove.listener.action";
    public final static String NOTIFICATION_OPEN_ON_PHONE="notification.open.on.phone";

    //private MobvoiApiClient mClient;
    private Context mContext;
    //public static List<NotificationAdapter> notificationsList=new ArrayList<NotificationAdapter>();
    //public static List<StreamletAdapter> streamAdapter = new ArrayList<StreamletAdapter>();
    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(); 
        filter.addAction(REMOVE_NOTIFICATION_ACTION);
        filter.addAction(NOTIFICATION_OPEN_ON_PHONE);
        //registerReceiver(notifyListenerReceiver, filter);
        mContext=this;
       // mClient = new MobvoiApiClient.Builder(this).addApi(Wearable.API)
        //        .addConnectionCallbacks(this)
        //        .addOnConnectionFailedListener(this).build();
        
        /*BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (!adapter.isEnabled()) {
            adapter.enable();
        }
        try {
            BtUtils.setScanMode(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        mClient.connect();*/
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.v("wanghui", "new notification");
        if(sbn==null)return;
       /* for(int i=0;i<notificationsList.size();i++){
            StatusBarNotification iSbn=(StatusBarNotification)notificationsList.get(i).getStatusBarNotification();
            if(iSbn!=null && isSameNotification(sbn,iSbn)){
                notificationsList.remove(i);
                NotificationAdapter notifi=new NotificationAdapter(iSbn,null);
                WearNotificationUtils.removeExistAdapter(notifi);
                break;
            }
        }
        notificationsList.add(new NotificationAdapter(sbn,null));
        WearNotificationUtils.adapterAdd(new NotificationAdapter(sbn,null), mContext);
        Intent it = new Intent(HomeActivity.WEAR_NOTIFICATION_POSTED_ACTION);
        sendBroadcast(it);*/
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.v("wanghui", "remove notification");
        /*if(sbn==null)return;
        for(int i=0;i<notificationsList.size();i++){
            StatusBarNotification iSbn=(StatusBarNotification)notificationsList.get(i).getStatusBarNotification();
            if(iSbn!=null && isSameNotification(sbn,iSbn)){
                notificationsList.remove(i);
                NotificationAdapter notifi=new NotificationAdapter(iSbn,null);
                WearNotificationUtils.removeExistAdapter(notifi);
                Intent it = new Intent(HomeActivity.WEAR_NOTIFICATION_REMOVED_ACTION);
                sendBroadcast(it);
                return;
            }
        }*/
    }
    

    @Override
    public void onDestroy() {
       /*if (mClient != null && mClient.isConnected()) {
            mClient.disconnect();
        }
        // TODO Auto-generated method stub
        super.onDestroy();
        Intent localIntent = new Intent();  
        localIntent.setClass(this, WearNotificationListener.class); 
        this.startService(localIntent);  */
    }
    
    private boolean isSameNotification(StatusBarNotification sbn1,StatusBarNotification sbn2){
        if(sbn1.getPackageName().equals(sbn2.getPackageName()) &&
                sbn1.getId()==sbn2.getId()){
            if(sbn1.getTag()==null){
                if(sbn2.getTag()!=null){
                    return false;
                }
                return true;
            }else{
                if(sbn2.getTag()==null){
                    return false;
                }else{
                    if(!sbn1.getTag().equals(sbn2.getTag())){
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
   /* private boolean isSameNotification(NotificationData sbn1,NotificationData sbn2){
        if(sbn1.getPackageName().equals(sbn2.getPackageName()) &&
                sbn1.getId()==sbn2.getId()){
            if(sbn1.getTag()==null){
                if(sbn2.getTag()!=null){
                    return false;
                }
                return true;
            }else{
                if(sbn2.getTag()==null){
                    return false;
                }else{
                    if(!sbn1.getTag().equals(sbn2.getTag())){
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public BroadcastReceiver notifyListenerReceiver=new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action=intent.getAction();
            Bundle bd=intent.getExtras();
            boolean external=bd.getBoolean("external");
            String packagename=bd.getString("package");
            String tag=bd.getString("tag");
            int id=bd.getInt("id");
            if(action.equals(REMOVE_NOTIFICATION_ACTION)){
                if(!external){
                    cancelNotification(packagename,tag,id);
                }else{
                    NotificationData niData=new NotificationData(mContext);
                    niData.setPackageName(packagename);
                    niData.setId(id);
                    niData.setTag(tag);
                    Parcel parcel=Parcel.obtain();
                    niData.writeToParcel(parcel, 0);
                    parcel.setDataPosition(0);
                    byte[] bytes = parcel.marshall();
                    parcel.recycle();
                    Wearable.MessageApi.sendMessage(mClient, "xxx", "cancel_external_notification", bytes);
                }
            }else if(action.equals(NOTIFICATION_OPEN_ON_PHONE)){
                NotificationData niData=new NotificationData(mContext);
                niData.setPackageName(packagename);
                niData.setId(id);
                niData.setTag(tag);
                Parcel parcel=Parcel.obtain();
                niData.writeToParcel(parcel, 0);
                parcel.setDataPosition(0);
                byte[] bytes = parcel.marshall();
                parcel.recycle();
                Wearable.MessageApi.sendMessage(mClient, "xxx", "notification_open_on_phone", bytes);
            }
        }
        
    };

    @Override
    public void onConnected(Bundle arg0) {
        if (mClient != null && mClient.isConnected()) {
            Wearable.MessageApi.addListener(mClient, this);
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onMessageReceived(MessageEvent event) {
        
    }*/
    
}
