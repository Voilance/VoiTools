package com.voilance.voitool.lib;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public final class VoiHttpClient {

    String mName;
    volatile OkHttpClient mInstance;

    public static final String UNKNOWN_CLIENT = "unknown_client";

    public VoiHttpClient(String name) {
        mName = name != null ? name : UNKNOWN_CLIENT;
        mInstance = new OkHttpClient();
    }

    public VoiHttpClient(String name, OkHttpClient client) {
        mName = name != null ? name : UNKNOWN_CLIENT;
        mInstance = client != null ? client : new OkHttpClient();
    }

    public String getClientName() {
        return mName;
    }

    private VoiHttpClient getInstance() {
        if (mInstance == null) {
            synchronized (this) {
                mInstance = new OkHttpClient();
            }
        }
        return this;
    }

    public static class Builder {
        private String mName;
        private OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();

        public Builder(String name) {
            mName = name != null ? name : UNKNOWN_CLIENT;
        }

        public Builder setConnectTimeout(long mills) {
            mBuilder.connectTimeout(mills, TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder setReadTimeout(long mills) {
            mBuilder.readTimeout(mills, TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder setWriteTimeout(long mills) {
            mBuilder.writeTimeout(mills, TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder cookie() {
            mBuilder.cookieJar(new CookieJar() {
                private Map<String, List<Cookie>> mCookieMap = new HashMap<>();

                @Override
                public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                    if (httpUrl != null && list != null) {
                        mCookieMap.put(httpUrl.host(), list);
                    }
                }

                @NotNull
                @Override
                public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                    if (httpUrl != null) {
                        if (mCookieMap.get(httpUrl.host()) != null) {
                            return mCookieMap.get(httpUrl.host());
                        }
                    }
                    return new ArrayList<>();
                }
            });
            return this;
        }

        public VoiHttpClient build() {
            return new VoiHttpClient(mName, mBuilder.build());
        }
    }
}
