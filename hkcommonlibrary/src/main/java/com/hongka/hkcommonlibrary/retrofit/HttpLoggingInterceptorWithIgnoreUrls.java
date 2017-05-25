package com.hongka.hkcommonlibrary.retrofit;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * Created by puresprout@sk.com on 2016. 10. 19..
 * <p>
 * 로깅을 무시할 url 목록을 가지는 HttpLoggingInterceptor에 대한 Proxy 클래스
 * <p>
 * HttpLoggingInterceptor와 로깅을 무시할 url 목록을 주입해 주면,
 * 로깅을 무시할 url은 HttpLoggingInterceptor를 타지 않고, 로깅을 할 url만 HttpLoggingInterceptor를 타게 된다.
 */
public class HttpLoggingInterceptorWithIgnoreUrls implements Interceptor {
    HttpLoggingInterceptor httpLoggingInterceptor;
    List<String> ignoreUrls;

    public HttpLoggingInterceptorWithIgnoreUrls(HttpLoggingInterceptor interceptor, List<String> ignoreUrls) {
        this.httpLoggingInterceptor = interceptor;
        this.ignoreUrls = ignoreUrls;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();

        for (String ignoreUrl : ignoreUrls) {
            if (url.contains(ignoreUrl)) {
                String message = "--> " + request.method() + ' ' + request.url() + " logging ignored...";
                Platform.get().log(INFO, message, null);

                return chain.proceed(request);
            }
        }

        return httpLoggingInterceptor.intercept(chain);
    }
}
