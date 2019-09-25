package com.voilance.voitool.tool;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public final class TrackerTransformer implements ITransformer {

    @Override
    public void onTransformStart() {}

    @Override
    public void onTransformEnd() {}

    @Override
    public byte[] transform(byte[] bytes) {
        // 扫描并获取类信息
        if (bytes == null) {
            return new byte[0];
        }

        ClassReader cr = new ClassReader(bytes);
        if (cr.getClassName().equals("com/voilance/voitool/lib/VoiTracker")) {
            System.out.println("This is Voitracker and not deal with it");
            return bytes;
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        cr.accept(new TrackerClassVisitor(cw), ClassReader.EXPAND_FRAMES);
        return cw.toByteArray();
    }

    @Override
    public void lastTransform(String dirPath) {}
}
