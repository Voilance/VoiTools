package com.voilance.voitool.tool;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouterTransformer implements ITransformer {

    @Override
    public void onTransformStart() { }

    @Override
    public void onTransformEnd() {}


    private static final Map<String, String> ROUTE_MAP = new HashMap<>();
    @Override
    public byte[] transform(byte[] bytes) {
        if (bytes == null) {
            return new byte[0];
        }

        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        List<AnnotationNode> annotationNodeList = cn.invisibleAnnotations;
        if (annotationNodeList != null) {
            for (AnnotationNode node : annotationNodeList) {
                if ("Lcom/voilance/voitool/lib/VoiRoute;".equals(node.desc)) {
                    if (node.values.size() == 2) {
                        String route = (String) node.values.get(1);
                        String path = cn.name.replace("/", ".");
                        ROUTE_MAP.put(route, path);
                    }
                }
            }
        }

        return bytes;
    }


    @Override
    public void lastTransform(String dirPath) {
        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;

        cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_SUPER, "com/voilance/voitool/lib/VoiRouteTable", null, "java/lang/Object", null);
        cw.visitSource("VoiRouteTable.java", null);

        {
            mv = cw.visitMethod(Opcodes.ACC_PRIVATE, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "init", "(Ljava/util/Map;)V", "(Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V", null);
            mv.visitCode();
            for (Map.Entry<String, String> entry : ROUTE_MAP.entrySet()) {
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitLdcInsn(entry.getKey());
                mv.visitLdcInsn(entry.getValue());
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
                mv.visitInsn(Opcodes.POP);
            }
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(3, 1);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        try (FileOutputStream fos = new FileOutputStream(dirPath + File.separator + "VoiRouteTable.class")) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
