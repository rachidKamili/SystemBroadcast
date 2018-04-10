package me.kamili.rachid.seccondapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyReceiver myReceiver;
    private MySecondReceiver mySecondReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myReceiver = new MyReceiver();
        mySecondReceiver = new MySecondReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ANOTHER_APP_ACTION");
        registerReceiver(myReceiver, intentFilter);
        registerReceiver(mySecondReceiver, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
        unregisterReceiver(mySecondReceiver);
    }

}
