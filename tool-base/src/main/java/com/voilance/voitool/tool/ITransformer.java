package com.voilance.voitool.tool;

public interface ITransformer extends ITransformListener {
    byte[] transform(byte[] bytes);
}
