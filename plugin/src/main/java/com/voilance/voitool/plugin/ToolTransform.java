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

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

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

        List<JarInput> jarInputList = new ArrayList<>();
        List<DirectoryInput> dirInputList = new ArrayList<>();
        for (TransformInput transformInput : mToolTransformInvocation.getInputs()) {
            jarInputList.addAll(transformInput.getJarInputs());
            dirInputList.addAll(transformInput.getDirectoryInputs());
        }

        for (JarInput jarInput : jarInputList) {
            processJarInput(jarInput, output);
        }
        int dirCot = 0;
        for (DirectoryInput dirInput : dirInputList) {
            processDirInput(dirInput, output, (dirCot += 1) == dirInputList.size());
        }
    }

    private void processJarInput(JarInput input, TransformOutputProvider output) throws IOException {
        System.out.println("JarInput: " + input.getName());
        String jarName = input.getName().replace(".jar", "");
        String md5Name = DigestUtils.md5Hex(input.getFile().getAbsolutePath());
        JarFile jarFile = new JarFile(input.getFile());
        Enumeration<JarEntry> enumeration = jarFile.entries();
        File tempFile = new File(input.getFile().getParent() + File.separator + "temp_classes.jar");
        if (tempFile.exists()) {
            tempFile.delete();
        }

        JarOutputStream jos = new JarOutputStream(new FileOutputStream(tempFile));
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = enumeration.nextElement();
            ZipEntry zipEntry = new ZipEntry(jarEntry.getName());
            InputStream is = jarFile.getInputStream(jarEntry);
            jos.putNextEntry(zipEntry);
            byte[] bytes = IOUtils.toByteArray(is);
            if (jarEntry.getName().endsWith(".class")) {
                bytes = mToolTransformInvocation.onTransform(bytes);
            }
            jos.write(bytes);
            jos.closeEntry();
            is.close();
        }
        jos.close();
        jarFile.close();

        File targetFile = output.getContentLocation(jarName + md5Name, input.getContentTypes(), input.getScopes(), Format.JAR);
        FileUtils.copyFile(tempFile, targetFile);
    }

    private void processDirInput(DirectoryInput input, TransformOutputProvider output, boolean isLast) throws IOException {
        for (File file : ToolHelper.getClassFileFromDirectory(input.getFile())) {
            byte[] bytes = mToolTransformInvocation.onTransform(ToolHelper.fileToBytes(file));
            ToolHelper.bytesToFile(bytes, file);
        }
        if (isLast) {
            mToolTransformInvocation.onLastTransform(input.getFile().getPath());
        }
        File targetFile = output.getContentLocation(input.getName(), input.getContentTypes(), input.getScopes(), Format.DIRECTORY);
        FileUtils.copyDirectory(input.getFile(), targetFile);
    }
}
