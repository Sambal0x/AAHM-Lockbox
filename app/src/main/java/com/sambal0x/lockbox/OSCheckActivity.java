package com.sambal0x.lockbox;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.util.Log;
import android.os.Build;

public class OSCheckActivity extends AppCompatActivity {

    private static final String TAG = "OSCheck";

    private TextView statusText;
    private TextView apiLevelText;
    private Button checkOsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oscheck);

        statusText = findViewById(R.id.statusText);
        apiLevelText = findViewById(R.id.apiLevelText);
        checkOsBtn = findViewById(R.id.checkOsBtn);

        // Show actual API
        apiLevelText.setText("Your API: " + Build.VERSION.SDK_INT + "\nRequired: 999+");

        checkOsBtn.setOnClickListener(v -> runOsCheck());
    }

    private void runOsCheck() {
        // Call native method - returns BOOLEAN now!
        boolean isCompatible = NativeEnvCheck.performOsCheck();

        // Update UI based on boolean
        if (isCompatible) {
            statusText.setText("COMPATIBLE - Passcode is '1337' ");
            statusText.setTextColor(Color.GREEN);
            Log.d(TAG, "OS Check: PASS");
        } else {
            statusText.setText("INCOMPATIBLE");
            statusText.setTextColor(Color.RED);
            Log.d(TAG, "OS Check: FAIL");
        }
    }
}