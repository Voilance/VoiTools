package com.voilance.voitool.plugin;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ToolTransform extends Transform {

    private ToolTransformInvocation mToolTransformInvocation;

    @Override
    public String getName() {
        return "VoiTool";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return true;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        mToolTransformInvocation = new ToolTransformInvocation(transformInvocation);
        mToolTransformInvocation.onTransformStart();
        processInputs();
        mToolTransformInvocation.onTransformEnd();
    }

    private void processInputs() throws IOException {
        TransformOutputProvider output = mToolTransformInvocation.getOutputProvider();
        if (output != null) {
            output.deleteAll();
        }
        for (TransformInput transformInput : mToolTransformInvocation.getInputs()) {
            for (JarInput jarInput : transformInput.getJarInputs()) {
                processJarInput(jarInput, output);
            }
            for (DirectoryInput dirInput : transformInput.getDirectoryInputs()) {
                processDirInput(dirInput, output);
            }
        }
    }

    private void processJarInput(JarInput input, TransformOutputProvider output) throws IOException {
        File targetFile = output.getContentLocation(input.getName(), input.getContentTypes(), input.getScopes(), Format.JAR);
        FileUtils.copyFile(input.getFile(), targetFile);
    }

    private void processDirInput(DirectoryInput input, TransformOutputProvider output) throws IOException {
        for (File file : ToolHelper.getClassFileFromDirectory(input.getFile())) {
            byte[] bytes = mToolTransformInvocation.onTransform(ToolHelper.fileToBytes(file));
            ToolHelper.bytesToFile(bytes, file);
        }
        File targetFile = output.getContentLocation(input.getName(), input.getContentTypes(), input.getScopes(), Format.DIRECTORY);
        FileUtils.copyDirectory(input.getFile(), targetFile);
    }
}
