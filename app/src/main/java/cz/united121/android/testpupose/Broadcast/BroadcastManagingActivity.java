package cz.united121.android.testpupose.Broadcast;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseObject;

import cz.united121.android.testpupose.Helpers.Constant;
import cz.united121.android.testpupose.R;


/**
 * Original project name : TestPupose
 * Created by Petr Lorenc[petr.lorenc@ackee.cz] on 15.6.2015.
 */
public class BroadcastManagingActivity extends Activity {
    private static final String TAG = BroadcastManagingActivity.class.getName();

    private Toolbar mToolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_manager);
        Log.d(TAG, "onCreate");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("TOOLBAR");


        //For Parse---------
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "dum1eTjnkhNJmgptOPaQVgzBRAVoTdwLhAm6vj6t", "ZYVqef9IqHLLI2TtebwGW2b61ZkBGfnuXu8JM63J");

    }

    public void startService(View view){
        Log.d(TAG, "startService");

        Intent i = new Intent();
        i.setAction(Constant.CUSTOM_BROADCAST);
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,i,0);

        AlarmManager mAlarmManager = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), MyService.intervalInMs, mPendingIntent);

        startService(new Intent(getBaseContext(),MyService.class));
    }

    /**
     * Cancel AlarmManager is calling here, because there was a problem with stoping service when a
     * application start the service and the turn off yourself - there was no
     * @param view
     */
    public void stopService(View view){
        Log.d(TAG, "stopService");

        Intent i = new Intent();
        i.setAction(Constant.CUSTOM_BROADCAST);
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(this, 0, i, 0);
        AlarmManager mAlarmManager = (AlarmManager) getSystemService (getApplicationContext().ALARM_SERVICE);
        mAlarmManager.cancel(mPendingIntent);

        stopService(new Intent(getBaseContext(),MyService.class));
    }
}
