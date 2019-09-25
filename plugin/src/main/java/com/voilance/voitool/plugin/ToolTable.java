package com.voilance.voitool.plugin;

import com.voilance.voitool.tool.IBuildGradleEditor;
import com.voilance.voitool.tool.ITransformer;

import java.util.ArrayList;
import java.util.List;

public final class ToolTable {
    private ToolTable() {}

    private static final String[] BUILD_GRADLE_EDITOR_TABLE = new String[] {
        "com.voilance.voitool.tool.RouterBuildGradleEditor",
        "com.voilance.voitool.tool.TrackerBuildGradleEditor"
    };

    private static final String[] TRANSFORMER_TABLE = new String[] {
        "com.voilance.voitool.tool.RouterTransformer",
        "com.voilance.voitool.tool.TrackerTransformer"
    };


    public static List<IBuildGradleEditor> getBuildGradleEditors() {
        List<IBuildGradleEditor> list = new ArrayList<>();
        for (String s : BUILD_GRADLE_EDITOR_TABLE) {
            try {
                Class<?> clas = Class.forName(s);
                Object obj = clas.newInstance();
                if (obj instanceof IBuildGradleEditor) {
                    list.add((IBuildGradleEditor) obj);
                }
            } catch (Exception e) {
                continue;
            }
        }
        return list;
    }

    public static List<ITransformer> getTransformers() {
        List<ITransformer> list = new ArrayList<>();
        for (String s : TRANSFORMER_TABLE) {
            try {
                Class<?> clas = Class.forName(s);
                Object obj = clas.newInstance();
                if (obj instanceof ITransformer) {
                    list.add((ITransformer) obj);
                }
            } catch (Exception e) {
                continue;
            }
        }
        return list;
    }
}
