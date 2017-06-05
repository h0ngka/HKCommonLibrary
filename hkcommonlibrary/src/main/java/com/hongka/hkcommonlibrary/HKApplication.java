package com.hongka.hkcommonlibrary;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jusung.kim@sk.com on 2017/05/22
 */

public class HKApplication extends Application {

    private static Boolean sIsDebug;
    protected String mUserAgent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebugBuild()) {
            Stetho.initializeWithDefaults(this);
        }

        mUserAgent = Util.getUserAgent(this, getString(R.string.app_name));
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

    public DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(this, bandwidthMeter,
                buildHttpDataSourceFactory(bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(mUserAgent, bandwidthMeter);
    }
}
