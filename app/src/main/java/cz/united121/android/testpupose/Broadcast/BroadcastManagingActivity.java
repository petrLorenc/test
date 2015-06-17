package cz.united121.android.testpupose.Broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.parse.Parse;

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
        Log.d(TAG,"startService");
        startService(new Intent(getBaseContext(),MyService.class));
    }

    public void stopService(View view){
        Log.d(TAG,"stopService");
        stopService(new Intent(getBaseContext(),MyService.class));
    }
}
