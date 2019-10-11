package com.voilance.voitool.lib;

import java.util.HashMap;
import java.util.Map;

public final class VoiHttp {

    private static volatile Map<String, VoiHttpClient> mClients = new HashMap<>();
    public static final String DEFAULT_CLIENT = "default_client";

    public static VoiHttpClient getClient() {
        return mClients.get(DEFAULT_CLIENT);
    }

    public static VoiHttpClient getClient(String name) {
        VoiHttpClient client = mClients.get(name);
        return client != null ? client : getClient();
    }

    public static void addClient(VoiHttpClient client) {
        mClients.put(client.getClientName(), client);
    }

    public static void removeClient(String name) {
        if (name != null && mClients.containsKey(name)) {
            mClients.remove(name);
        }
    }

    public static boolean hasClient(String name) {
        if (name != null && mClients.containsKey(name)) {
            if (mClients.get(name) != null) {
                return true;
            } else {
                removeClient(name);
            }
        }
        return false;
    }
}
