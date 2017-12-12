package com.hongka.hkcommonlibrary.retrofit;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongka.hkcommonlibrary.BuildConfig;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jusung.kim@sk.com on 2017/05/22
 */

public class RestClient {
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final int READ_TIMEOUT = 15000;

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    private static RestClient sInstance;
    private static String sBaseUrl;
    public static synchronized RestClient getInstance(String baseUrl) {
        if (sInstance == null || !baseUrl.equals(sBaseUrl)) {
            clearInstance();
            sBaseUrl = baseUrl;
            sInstance = new RestClient(baseUrl, BuildConfig.DEBUG);
        }

        return sInstance;
    }

    public static synchronized void clearInstance() {
        sBaseUrl = null;
        sInstance = null;
    }

    public RestClient(String baseUrl, boolean enableLog) {
        this(baseUrl, enableLog, null);
    }

    public RestClient(String baseUrl, boolean enableLog, List<String> ignoreUrls) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);

        builder.addInterceptor(new UserAgentInterceptor());

        if (baseUrl.contains("https://")) {
            // support https, TLS 1.0 for Android 4.x (TLS 1.2 is supported over Android 5.0)
            builder.connectionSpecs(Collections.singletonList(ConnectionSpec.COMPATIBLE_TLS)).build();
        }

        if (enableLog) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // 아래 url을 포함하는 요청은 로깅을 무시하자.
            if (ignoreUrls != null && !ignoreUrls.isEmpty()) {
                HttpLoggingInterceptorWithIgnoreUrls ignoreUrlsInterceptor = new HttpLoggingInterceptorWithIgnoreUrls(loggingInterceptor, ignoreUrls);
                builder.addInterceptor(ignoreUrlsInterceptor);
            }
        }
//        builder = addCustomInterceptor(builder);

//        if (HKApplication.isDebugBuild()) {
//            builder.addNetworkInterceptor(new StethoInterceptor());
//        }

        mOkHttpClient = builder.build();

        // ++ retrofit 생성
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(mOkHttpClient).build();
    }

    public <T> T getApi(Class<T> api) {
        return mRetrofit.create(api);
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void requestCancelAll() {
        if (getOkHttpClient() != null && getOkHttpClient().dispatcher() != null) {
            getOkHttpClient().dispatcher().cancelAll();
        }
    }
}
