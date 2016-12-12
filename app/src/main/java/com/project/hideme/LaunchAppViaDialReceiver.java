package com.project.hideme;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by Shreyash on 30-11-2016
 */

public class LaunchAppViaDialReceiver extends BroadcastReceiver {
    String phoneno;
    String password = "1234";
    @Override
    public void onReceive(Context context, final Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            phoneno = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
        }
        if(phoneno.equals(password)) {
            ComponentName componentName = new ComponentName(context, MainActivity.class);
            context.getPackageManager().setComponentEnabledSetting( componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            Intent intent1 = new Intent(context , MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            context.startActivity(intent1);
            context.stopService(intent);
        }
    }
}