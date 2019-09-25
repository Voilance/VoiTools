package com.voilance.voitool.tool;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

final class TrackerMethodVisitor extends MethodVisitor {

    private String info;

    public TrackerMethodVisitor(MethodVisitor mv, String info) {
        super(Opcodes.ASM5, mv);
        this.info = info;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitLdcInsn(this.info);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/voilance/voitool/lib/VoiTracker", "log", "(Ljava/lang/String;)V", false);
    }
}
