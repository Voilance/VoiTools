package com.voilance.voitool.plugin;

import com.android.build.gradle.AppExtension;
import com.voilance.voitool.tool.IBuildGradleEditor;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ToolPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        if (project.getPlugins().hasPlugin("com.android.application")) {
            project.getExtensions()
                .getByType(AppExtension.class)
                .registerTransform(new ToolTransform());
            project.afterEvaluate(p -> {
                ToolProjectInfo projectInfo = new ToolProjectInfo(false);
                for (Project subProject : p.getRootProject().getSubprojects()) {
                    if (subProject.getPlugins().hasPlugin("com.android.application") ||
                        subProject.getPlugins().hasPlugin("com.android.library")) {
                        for (IBuildGradleEditor editor : ToolTable.getBuildGradleEditors()) {
                            editor.edit(subProject, projectInfo);
                        }
                    }
                }
            });
        }
    }
}
