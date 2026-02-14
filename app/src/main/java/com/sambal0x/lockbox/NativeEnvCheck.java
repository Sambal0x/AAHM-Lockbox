package com.sambal0x.lockbox;

public class NativeEnvCheck {

    static {
        System.loadLibrary("nativeenv");
    }

    public static native boolean performOsCheck();
}
