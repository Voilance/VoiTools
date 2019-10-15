package com.voilance.voitool.lib;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public final class VoiHttpClient {

    private static volatile Map<String, VoiHttpClient> CLIENT_MAP = new HashMap<>();
    public static final String DEFAULT_CLIENT_TAG = "default_client";
    static {
        CLIENT_MAP.put(DEFAULT_CLIENT_TAG, new VoiHttpClient(DEFAULT_CLIENT_TAG));
    }

    public static VoiHttpClient getClient(@NotNull String tag) {
        if (tag != null) {
            if (CLIENT_MAP.get(tag) != null) {
                synchronized (CLIENT_MAP) {
                    if (CLIENT_MAP.get(tag) != null) {
                        return CLIENT_MAP.get(tag);
                    }
                }
            }
        }
        return CLIENT_MAP.get(DEFAULT_CLIENT_TAG);
    }

    private String mTag;
    private volatile OkHttpClient mClient;

    public VoiHttpClient(@NotNull String tag, @NotNull OkHttpClient client) {
        if (tag == null || client == null) {
            throw new NullPointerException("parameter should not be null!");
        }
        if (!CLIENT_MAP.containsKey(tag)) {
            synchronized (CLIENT_MAP) {
                if (!CLIENT_MAP.containsKey(tag)) {
                    mTag = tag;
                    mClient = client;
                    CLIENT_MAP.put(tag, this);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("parameter tag already exist!");
    }

    public VoiHttpClient(String tag) {
        this(tag, new OkHttpClient());
    }

}
