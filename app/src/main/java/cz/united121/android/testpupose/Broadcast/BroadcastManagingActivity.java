package cz.united121.android.testpupose.Broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cz.united121.android.testpupose.R;


/**
 * Original project name : TestPupose
 * Created by Petr Lorenc[petr.lorenc@ackee.cz] on 15.6.2015.
 */
public class BroadcastManagingActivity extends Activity {
    private static final String TAG = BroadcastManagingActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_manager);
        Log.d(TAG,"onCreate");

    }

    public void startService(View view){
        Log.d(TAG,"startService");
        startService(new Intent(getBaseContext(),MyService.class));
    }

    public void stopService(View view){
        Log.d(TAG,"stopService");
        stopService(new Intent(getBaseContext(),MyService.class));
    }
}
