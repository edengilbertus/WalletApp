# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep custom views
-keep class com.wallet.app.views.** { *; }

# Keep activities
-keep class com.wallet.app.** extends android.app.Activity { *; }
