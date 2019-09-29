package com.voilance.voitool.tool;

public class HttpTransformer implements ITransformer {

    @Override
    public void onTransformStart() {}

    @Override
    public void onTransformEnd() {}

    @Override
    public byte[] transform(byte[] bytes) {
        if (bytes == null) {
            return new byte[0];
        }
        return bytes;
    }

    @Override
    public void lastTransform(String dirPath) {}
}
