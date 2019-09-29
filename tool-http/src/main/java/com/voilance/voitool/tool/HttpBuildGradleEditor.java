package com.voilance.voitool.tool;

import org.gradle.api.Project;

public class HttpBuildGradleEditor implements IBuildGradleEditor {
    @Override
    public void edit(Project project, IProjectInfo info) {
        project.getDependencies().add("implementation", info.group() + ":lib-http:" + info.version());
    }
}
