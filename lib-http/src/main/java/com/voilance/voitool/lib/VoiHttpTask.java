package com.voilance.voitool.lib;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;

public abstract class VoiHttpTask {

    protected VoiHttpClient mClient;
    protected VoiHttpCallback mCallback;
    protected Request.Builder mRequestBuilder;
    protected Request mRequest;
    protected Call mCall;
    protected boolean mIsExecuted = false;

    protected static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");

    public VoiHttpTask(HttpUrl url) {
        mRequestBuilder = new Request.Builder().url(url);
    }

    public VoiHttpTask(String url) {
        mRequestBuilder = new Request.Builder().url(url);
    }

    public String getMethod() {
        return mRequest.method();
    }

    public VoiHttpTask addHeader(String name, String value) {
        mRequestBuilder.addHeader(name, value);
        return this;
    }

    public VoiHttpTask enqueue(VoiHttpCallback callback) {
        if (callback != null) {
            mCallback = callback;
        }
        return this;
    }

    public VoiHttpTask execute() {
        return this;
    }
}
