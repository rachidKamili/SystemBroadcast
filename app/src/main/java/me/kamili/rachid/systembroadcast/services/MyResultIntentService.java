package me.kamili.rachid.systembroadcast.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

public class MyResultIntentService extends IntentService {

    public MyResultIntentService() {
        super("MyResultIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            // Extract the receiver passed into the service
            ResultReceiver rec = intent.getParcelableExtra("receiver");
            // Extract additional values from the bundle
            String val = intent.getStringExtra("data");
            // To send a message to the Activity, create a pass a Bundle
            Bundle bundle = new Bundle();
            bundle.putString("resultValue", "My Result Value. Passed in: " + val);
            // Here we call send passing a resultCode and the bundle of extras
            rec.send(Activity.RESULT_OK, bundle);
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
