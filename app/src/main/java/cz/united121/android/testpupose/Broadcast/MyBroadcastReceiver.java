package cz.united121.android.testpupose.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import cz.united121.android.testpupose.NavigationDrawerTest;

/**
 * Original project name : TestPupose
 * Created by Petr Lorenc[petr.lorenc@ackee.cz] on 16.6.2015.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MyBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");

        Intent i = new Intent(context, NavigationDrawerTest.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public MyBroadcastReceiver() {
        super();
        Log.d(TAG, "MyBroadcastReceiver");
    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        Log.d(TAG,"peekService");
        return super.peekService(myContext, service);
    }
}
