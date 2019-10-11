package com.voilance.voitool.lib;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

public final class VoiHttpGetTask extends VoiHttpTask {
    public VoiHttpGetTask(HttpUrl url) {
        super(url);
    }

    public void exec() {
    }
}
