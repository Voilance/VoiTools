package com.voilance.voitool.plugin;

import com.voilance.voitool.tool.IProjectInfo;

public final class ToolProjectInfo implements IProjectInfo {
    @Override
    public String group() {
        return "com.voilance.voitool";
    }

    @Override
    public String version() {
        return "0.2";
    }
}
