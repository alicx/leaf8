package com.leaf8.alicx.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by alicx on 4/21/15.
 */
public class BatteryBroadcastReceiver extends BroadcastReceiver {

    private final static String BATTERY_LEVEL = "level";

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BATTERY_LEVEL, 0);

        Toast.makeText(context, level, Toast.LENGTH_LONG).show();
    }
}
