package com.voilance.voitool.tool;

import org.gradle.api.Project;

public final class RouterBuildGradleEditor implements IBuildGradleEditor {

    @Override
    public void edit(Project project, IProjectInfo info) {
        project.getDependencies().add("implementation", info.group() + ":lib-router:" + info.version());
    }
}
