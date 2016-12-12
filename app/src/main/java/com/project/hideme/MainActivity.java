package com.project.hideme;

import android.Manifest;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button hideMeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideMeBtn   = (Button) findViewById(R.id.hidemeBtn);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_PHONE_STATE)) {
                Snackbar.make(hideMeBtn, "Phone state permission required to open app after " +
                        "hiding it.",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[] {Manifest.permission.READ_PHONE_STATE}, 100);
                            }
                        });
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest
                        .permission.READ_PHONE_STATE}, 100);
            }
        }

        hideMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                ComponentName componentName = new ComponentName(getApplicationContext(), MainActivity.class);
                packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Its Hide now", Toast
                                .LENGTH_SHORT).show();
                        finish();
                    }
                }, 1000);
            };
        });
    }
}
