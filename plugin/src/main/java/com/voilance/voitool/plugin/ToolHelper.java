package com.voilance.voitool.plugin;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ToolHelper {
    private ToolHelper() {}

    public static List<File> getClassFileFromDirectory(File dir) {
        List<File> list = new ArrayList<>();
        getClassFileFromDirectory(dir, list);
        return list;
    }

    private static void getClassFileFromDirectory(File file, List<File> list) {
        if (file == null || list == null) {
            return;
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                getClassFileFromDirectory(f, list);
            }
        } else {
            if (file.getName().endsWith(".class")) {
                list.add(file);
            }
        }
    }

    public static byte[] fileToBytes(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return IOUtils.toByteArray(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static void bytesToFile(byte[] bytes, File file) {
        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
