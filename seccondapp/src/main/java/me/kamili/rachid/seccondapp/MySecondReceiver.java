package me.kamili.rachid.seccondapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MySecondReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case "ANOTHER_APP_ACTION":
                String data = intent.getStringExtra("data");
                Log.d("SECOND_APP_TAG", "onReceive: " + data);
                Toast.makeText(context, "This is the second receiver from the second app with data: " + data, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
