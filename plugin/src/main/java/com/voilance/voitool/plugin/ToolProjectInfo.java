package com.voilance.voitool.plugin;

import com.voilance.voitool.tool.IProjectInfo;

public final class ToolProjectInfo implements IProjectInfo {

    private boolean mIsDebug = true;
    public ToolProjectInfo(boolean isDebug) {
        mIsDebug = isDebug;
    }

    @Override
    public String group() {
        return mIsDebug ? "com.voilance.voitool" : "com.github.Voilance.voitool";
    }

    @Override
    public String version() {
        return mIsDebug ? "0.6-debug" : "0.1";
    }
}
