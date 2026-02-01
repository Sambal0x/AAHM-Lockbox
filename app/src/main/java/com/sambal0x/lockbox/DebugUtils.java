package com.sambal0x.lockbox;

import android.util.Log;

public class DebugUtils {

    // Static method - global security override
    // Real example: Master control for all vault security
    public static boolean setVaultSecurityLevel(int level) {
        Log.d("VaultInternal", "Vault security level set to: " + level);

        // Level 0 = disabled (maintenance mode)
        // Level 1 = normal user
        // Level 2 = enhanced security
        // Level 3 = maximum (bank vault mode)

        if (level == 0) {
            Log.d("VaultInternal", "WARNING: Vault is in MAINTENANCE MODE!");
            Log.d("VaultInternal", "- All PIN checks disabled");
            Log.d("VaultInternal", "- Audit logging turned off");
            Log.d("VaultInternal", "- Time locks removed");
            return true;
        }
        return false;
    }

    // Instance method - disable root/jailbreak detection
    // Real example: Testing method left in production
    public void disableRootDetection() {
        Log.d("VaultInternal", "ROOT DETECTION DISABLED!");
        Log.d("VaultInternal", "- Root check bypassed");
        Log.d("VaultInternal", "- SafetyNet attestation disabled");
        Log.d("VaultInternal", "- Emulator detection turned off");
        Log.d("VaultInternal", "- Debug checks removed");
    }
}