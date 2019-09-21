package com.voilance.voitool.tool;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouterTransformer implements ITransformer {

    private static final String VOIROUTE_ANNOTATION_DESC = "Lcom/voilance/voitool/lib/VoiRoute;";
    private static final Map<String, String> ROUTE_MAP = new HashMap<>();

    @Override
    public void onTransformStart() {
        System.out.println("RouterTransformer is ready!");
    }

    @Override
    public void onTransformEnd() {
        System.out.println("RouterTransformer is finished!");
    }

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
                System.out.println(node.desc);
                if (VOIROUTE_ANNOTATION_DESC.equals(node.desc)) {
                    if (node.values.size() == 2) {
                        ROUTE_MAP.put((String) node.values.get(1), cn.name.replace(File.separator, "."));
                        System.out.println(node.values.get(1) + " -> " + cn.name.replace(File.separator, "."));
                    }
                }
            }
        }

        return bytes;
    }
}
