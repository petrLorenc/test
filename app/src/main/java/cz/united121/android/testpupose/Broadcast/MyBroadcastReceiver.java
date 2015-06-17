package cz.united121.android.testpupose.Broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import cz.united121.android.testpupose.NavigationDrawerTest;
import cz.united121.android.testpupose.R;

/**
 * Original project name : TestPupose
 * Created by Petr Lorenc[petr.lorenc@ackee.cz] on 16.6.2015.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MyBroadcastReceiver.class.getName();

    /**
     * Using for updating notification later
     */
    private static int mId;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d(TAG, "onReceive");

        Log.d(TAG, "Do job!");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        query.whereEqualTo("foo", "test");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> testObject, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved " + testObject.size());
                    if(testObject.size() == 2){
                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(context)
                                        .setSmallIcon(R.drawable.aa)
                                        .setContentTitle("My notification")
                                        .setContentText("Hello World!");
                        // Creates an explicit intent for an Activity in your app
                        Intent resultIntent = new Intent(context, NavigationDrawerTest.class);

                        // The stack builder object will contain an artificial back stack for the
                        // started Activity.
                        // This ensures that navigating backward from the Activity leads out of
                        // your application to the Home screen.
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                        // Adds the back stack for the Intent (but not the Intent itself)
                        stackBuilder.addParentStack(NavigationDrawerTest.class);
                        // Adds the Intent that starts the Activity to the top of the stack
                        stackBuilder.addNextIntent(resultIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0,
                                                                        PendingIntent.FLAG_UPDATE_CURRENT);
                        mBuilder.setContentIntent(resultPendingIntent);
                        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        // mId allows you to update the notification later on.
                       mNotificationManager.notify(mId, mBuilder.build());
                    }
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
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
