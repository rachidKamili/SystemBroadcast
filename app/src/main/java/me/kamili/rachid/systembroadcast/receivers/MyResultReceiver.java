package me.kamili.rachid.systembroadcast.receivers;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;


public class MyResultReceiver extends ResultReceiver {

    private ResultReceiverCallBack mResultReceiver;


    public void setReceiver(ResultReceiverCallBack receiver) {
        mResultReceiver = receiver;
    }

    public MyResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mResultReceiver != null) {
            if (resultCode == Activity.RESULT_OK) {
                mResultReceiver.onSuccess(resultCode, resultData);
            }
        }
    }

    public interface ResultReceiverCallBack {
        public void onSuccess(int resultCode, Bundle resultData);
    }

}
