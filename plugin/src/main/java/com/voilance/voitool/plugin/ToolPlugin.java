package com.voilance.voitool.plugin;

import com.android.build.gradle.AppExtension;
import com.voilance.voitool.tool.IBuildGradleEditor;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.List;

public final class ToolPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        if (project.getPlugins().hasPlugin("com.android.application")) {
            project.getExtensions()
                .getByType(AppExtension.class)
                .registerTransform(new ToolTransform());

            project.afterEvaluate(p -> {
                ToolProjectInfo projectInfo = new ToolProjectInfo(true);
                List<IBuildGradleEditor> editorList = ToolTable.getBuildGradleEditors();
                if (editorList != null && editorList.size() > 0) {
                    for (Project subProject : p.getRootProject().getSubprojects()) {
                        if (subProject.getPlugins().hasPlugin("com.android.application") ||
                            subProject.getPlugins().hasPlugin("com.android.library")) {
                            for (IBuildGradleEditor editor : editorList) {
                                editor.edit(subProject, projectInfo);
                            }
                        }
                    }
                }
            });

        }
    }
}
