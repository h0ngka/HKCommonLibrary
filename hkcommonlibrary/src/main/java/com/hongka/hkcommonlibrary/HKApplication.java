package com.hongka.hkcommonlibrary;

import android.app.Application;

import com.facebook.stetho.Stetho;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jusung.kim@sk.com on 2017/05/22
 */

public class HKApplication extends Application {

    private static Boolean sIsDebug;

    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebugBuild()) {
            Stetho.initializeWithDefaults(this);
        }
    }

    public static boolean isDebugBuild() {
        if (sIsDebug == null) {
            try {
                final Class<?> activityThread = Class.forName("android.app.ActivityThread");
                final Method currentPackage = activityThread.getMethod("currentPackageName");
                final String packageName = (String) currentPackage.invoke(null, (Object[]) null);
                final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
                final Field DEBUG = buildConfig.getField("DEBUG");
                DEBUG.setAccessible(true);
                sIsDebug = DEBUG.getBoolean(null);
            } catch (final Throwable t) {
                final String message = t.getMessage();
                if (message != null && message.contains("BuildConfig")) {
                    // Proguard obfuscated build. Most likely a production build.
                    sIsDebug = false;
                } else {
                    sIsDebug = BuildConfig.DEBUG;
                }
            }
        }
        return sIsDebug;
    }
}
