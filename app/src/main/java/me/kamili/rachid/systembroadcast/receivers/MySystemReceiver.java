package me.kamili.rachid.systembroadcast.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MySystemReceiver extends BroadcastReceiver {

    private static final String TAG = "MySystemReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String text;
        switch (intent.getAction()) {
            case "ANOTHER_APP_ACTION":
                text = "This is the main app.";
                Log.d(TAG, "onReceive: " + text);
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                boolean status = intent.getBooleanExtra("state", false);
                text = "Airplane mode :" + status;
                Log.d(TAG, "onReceive: " + text);
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_TIME_TICK:
                text = "The current time has changed";
                Log.d(TAG, "onReceive: " + text);
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_HEADSET_PLUG:
                int state = intent.getIntExtra("state",0);
                text = state==1? "Wired Headset plugged in" : "Wired Headset unplugged" ;
                Log.d(TAG, "onReceive: " + text);
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_POWER_CONNECTED:
                text = "Power got connected to the device";
                Log.d(TAG, "onReceive: " + text);
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                text = "Power got disconnected to the device";
                Log.d(TAG, "onReceive: " + text);
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
