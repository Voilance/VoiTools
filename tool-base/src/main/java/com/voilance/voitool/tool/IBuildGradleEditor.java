package com.voilance.voitool.tool;

import org.gradle.api.Project;

public interface IBuildGradleEditor {

    void edit(Project project, IProjectInfo info);
}
