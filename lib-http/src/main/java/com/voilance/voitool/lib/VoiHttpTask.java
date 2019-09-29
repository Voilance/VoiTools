package com.voilance.voitool.lib;

public abstract class VoiHttpTask {

    protected String mUrl;

    protected VoiHttpTask(String url) {
        mUrl = url;
    }

    protected void syncExecute() {}

    protected void asyncExecute() {}
}
