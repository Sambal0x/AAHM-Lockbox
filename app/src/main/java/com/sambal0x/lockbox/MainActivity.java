package com.sambal0x.lockbox;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.content.Intent;
import android.widget.Toast;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Lockbox";
    private EditText pinInput;
    private TextView statusText;
    private Button loginBtn;
    private Button launchOsCheckBtn; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinInput = findViewById(R.id.pinInput);
        statusText = findViewById(R.id.statusText);
        loginBtn = findViewById(R.id.loginBtn);
        launchOsCheckBtn = findViewById(R.id.btnLaunchOsCheck);

        loginBtn.setOnClickListener(view -> onLoginClicked(view));

        // Launch OS Check activity
        launchOsCheckBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, OSCheckActivity.class));
        });

        // Force-load DebugUtils so Frida can see it in our lab
        try { Class.forName("com.sambal0x.lockbox.DebugUtils"); } catch (Exception ignored) {}
    }

    public void onLoginClicked(View view) {
        String pin = pinInput.getText().toString();

        if (checkPin(pin)) {
            statusText.setText("Access Granted!");
            sendToServer("sensitive-data", calculateHash(pin));
        } else {
            statusText.setText("Access Denied!");
        }
    }

    private static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) sb.append(String.format("%02x ", b));
        return sb.toString();
    }

    // Original string-based checkPin
    private boolean checkPin(String pin) {
        String hashed = calculateHash(pin);
        // Expected MD5("1337")
        return hashed.equals("e48e13207341b6bffb7fb1622282247b");
    }

    // NEW: overloaded version that accepts an int
    // This creates an ambiguous method name for Frida if you try to hook checkPin
    private boolean checkPin(int pin) {
        // Convert to string and delegate to the main implementation
        return checkPin(Integer.toString(pin));
    }

    private String calculateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            Log.e(TAG, "calcHash error", e);
            return "";
        }
    }

    private void sendToServer(String data, String hash) {
        Log.d(TAG, "Uploading data='" + data + "' with hash=" + hash);
    }

    // Hidden developer backdoor method
    private void enableDeveloperMode() {
        Log.d(TAG, "Developer mode ENABLED");
        // Maybe change some runtime state
        runOnUiThread(() -> {
            statusText.setText("Developer Mode Activated!");
        });
    }

    // Another example — fake internal bypass
    private void disableRootDetection() {
        Log.d(TAG, "Root detection DISABLED");
        runOnUiThread(() -> {
            statusText.setText("Root Detection Disabled!");
        });
    }
}
