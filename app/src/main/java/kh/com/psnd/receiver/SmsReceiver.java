package kh.com.psnd.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SmsReceiver extends BroadcastReceiver {

    public SmsReceiver(SmsUpdater updater) {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //...
    }

    public interface SmsUpdater {
        void gotSms(String num, String msg, long time, String imei);
    }
}