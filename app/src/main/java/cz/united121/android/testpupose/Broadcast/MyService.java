package cz.united121.android.testpupose.Broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import cz.united121.android.testpupose.Helpers.Constant;

/**
 * Original project name : TestPupose
 * Created by Petr Lorenc[petr.lorenc@ackee.cz] on 16.6.2015.
 */
public class MyService extends Service {
    private static final String TAG = MyService.class.getName();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        Intent intent = new Intent();
        intent.setAction(Constant.CUSTOM_BROADCAST);
        sendBroadcast(intent);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onLowMemory() {
        Log.d(TAG,"onLowMemory");
        super.onLowMemory();
    }
}
