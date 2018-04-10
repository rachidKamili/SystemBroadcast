package me.kamili.rachid.systembroadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import me.kamili.rachid.systembroadcast.receivers.MyResultReceiver;
import me.kamili.rachid.systembroadcast.receivers.MySystemReceiver;
import me.kamili.rachid.systembroadcast.services.MyResultIntentService;

public class MainActivity extends AppCompatActivity {

    private MyResultReceiver myResultReceiver;
    private MySystemReceiver mySystemReceiver;
    private EditText etMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMain = findViewById(R.id.etMain);

        setupServiceResultReceiver();

    }

    private void setupServiceResultReceiver() {
        myResultReceiver = new MyResultReceiver(new Handler());
        // This is where we specify what happens when data is received from the service
        myResultReceiver.setReceiver(new MyResultReceiver.ResultReceiverCallBack() {
            @Override
            public void onSuccess(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(MainActivity.this, resultValue, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Starts the IntentService and sending resultReceiver
    public void onServiceResultReceiver(View v) {
        Intent i = new Intent(this, MyResultIntentService.class);
        i.putExtra("data", etMain.getText().toString());
        i.putExtra("receiver", myResultReceiver);
        startService(i);
    }


    @Override
    protected void onStart() {
        super.onStart();

        //Register the broadcast to receive multiple system broadcasts
        mySystemReceiver = new MySystemReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction("ANOTHER_APP_ACTION");
        registerReceiver(mySystemReceiver, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mySystemReceiver);
    }

    public void onSendOrderedBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("ANOTHER_APP_ACTION");
        intent.putExtra("data","Notice me senpai!");
        sendOrderedBroadcast(intent,null);
    }
}
