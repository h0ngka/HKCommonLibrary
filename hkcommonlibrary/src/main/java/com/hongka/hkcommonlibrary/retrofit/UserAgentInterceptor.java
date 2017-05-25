package com.hongka.hkcommonlibrary.retrofit;

import com.hongka.hkcommonlibrary.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by puresprout@sk.com on 2016. 12. 9..
 */
public class UserAgentInterceptor implements Interceptor {
    private String mUserAgent;

    public UserAgentInterceptor() {
        mUserAgent = BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME + "/" + BuildConfig.VERSION_CODE;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().header("User-Agent", mUserAgent).build();
        return chain.proceed(request);
    }
}