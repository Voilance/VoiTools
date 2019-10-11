package com.voilance.voitool.lib;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;

public abstract class VoiHttpTask {

    protected VoiHttpClient mClient;
    protected Request.Builder mRequestBuilder;
    protected Call mCall;

    protected static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");

    public VoiHttpTask(HttpUrl url) {
        mRequestBuilder = new Request.Builder().url(url);
    }

    public VoiHttpTask(String url) {
        mRequestBuilder = new Request.Builder().url(url);
    }

    public Call execute() {
        mCall = mClient.mInstance.newCall(mRequestBuilder.build());
//        mCall.execute();
        return mCall;
    }

    public void syncExecute() {}

    public void asyncExecute() {}
}
