package com.voilance.voitool.plugin;

import com.voilance.voitool.tool.IProjectInfo;

public final class ToolProjectInfo implements IProjectInfo {

    private boolean mIsDebug = true;
    public ToolProjectInfo(boolean isDebug) {
        mIsDebug = isDebug;
    }

    @Override
    public String group() {
        return mIsDebug ? "com.voilance.voitools" : "com.github.Voilance.voitools";
    }

    @Override
    public String version() {
        return mIsDebug ? "0.13-debug" : "0.2";
    }
}
