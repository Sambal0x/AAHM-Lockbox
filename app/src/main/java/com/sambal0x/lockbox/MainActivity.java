package com.sambal0x.lockbox;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Lockbox";
    private EditText pinInput;
    private TextView statusText;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinInput = findViewById(R.id.pinInput);
        statusText = findViewById(R.id.statusText);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClicked(view);
            }
        });
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

    private boolean checkPin(String pin) {
        String hashed = calculateHash(pin);
        // Expected MD5("1337")
        return hashed.equals("e48e13207341b6bffb7fb1622282247b");
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
}
