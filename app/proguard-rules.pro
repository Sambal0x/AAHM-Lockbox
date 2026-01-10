# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Don’t remove or optimize anything
-dontshrink
-dontoptimize

# Allow renaming and access modification
-allowaccessmodification

# Optional: Collapse packages to make names shorter
-repackageclasses

# Keep core Android components
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Keep Activity methods used in XML (like onClick)
-keepclassmembers class * {
    public void onClick(android.view.View);
}

# Keep important runtime and reflection attributes
-keepattributes *Annotation*,InnerClasses,Signature

# Rename source file attribute (hides original filename)
-renamesourcefileattribute SourceFile

# Keep app classes (don’t delete them), but allow renaming of their members
-keep class com.sambal0x.lockbox.**

# Suppress harmless warnings
-dontwarn **