package cz.united121.android.testpupose.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Original project name : TestPupose
 * Created by Petr Lorenc[petr.lorenc@ackee.cz] on 16.6.2015.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MyBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive");
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
