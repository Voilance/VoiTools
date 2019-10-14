package com.voilance.voitool.lib;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class VoiHttpCallback implements Callback {

    @Override
    public final void onFailure(@NotNull Call call, @NotNull IOException e) {}

    @Override
    public final void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {}

    public void onFailure(@NotNull VoiHttpTask task, @NotNull IOException e) {}

    public void onResponse(@NotNull VoiHttpTask task, @NotNull VoiHttpResult result) {}
}
