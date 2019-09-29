package com.voilance.voitool.lib;

public final class VoiHttp {

    private VoiHttpTask mTask;

    public static VoiHttpTask get(String url) {
        return new VoiHttpGetTask(url);
    }
}
