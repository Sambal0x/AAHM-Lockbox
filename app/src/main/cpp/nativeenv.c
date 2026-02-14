#include <jni.h>
#include <android/log.h>
#include <stdlib.h>
#include <sys/system_properties.h>

#define TAG "NativeEnvCheck"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define MIN_API_LEVEL 999  // Impossible!

int get_device_api_level() {
    char sdk[PROP_VALUE_MAX];
    __system_property_get("ro.build.version.sdk", sdk);
    return atoi(sdk);
}

JNIEXPORT jboolean JNICALL
Java_com_sambal0x_lockbox_NativeEnvCheck_performOsCheck(JNIEnv *env, jclass clazz) {
    int api = get_device_api_level();

    LOGD("API Check: %d vs %d", api, MIN_API_LEVEL);

    if (api >= MIN_API_LEVEL) {
        LOGD("Result: PASS (true)");
        return JNI_TRUE;   // true (1)
    } else {
        LOGD("Result: FAIL (false)");  // Always this!
        return JNI_FALSE;  // false (0)
    }
}