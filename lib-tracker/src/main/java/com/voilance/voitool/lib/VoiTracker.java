package com.voilance.voitool.lib;

public final class VoiTracker {
    private VoiTracker() {}

    private static String mTag = "VoiTrackerTag";
    private static volatile boolean mIsLogToConsole = true;

    public static void setLogToConsole(boolean isLogToConsole) {
        mIsLogToConsole = isLogToConsole;
    }

    public static void log(String msg) {
        if (mIsLogToConsole) {
            System.out.println(mTag + ": " + msg + "-" + System.currentTimeMillis());
        }
    }
}
