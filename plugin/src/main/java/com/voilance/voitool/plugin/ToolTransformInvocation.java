package com.voilance.voitool.plugin;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.SecondaryInput;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.voilance.voitool.tool.ITransformListener;
import com.voilance.voitool.tool.ITransformer;

import java.util.Collection;
import java.util.List;

public class ToolTransformInvocation implements TransformInvocation, ITransformListener {

    private TransformInvocation mTransformInvocation;
    private List<ITransformer> mTransformerList;

    public ToolTransformInvocation(TransformInvocation invocation) {
        mTransformInvocation = invocation;
        mTransformerList = ToolTable.getTransformers();
    }

    @Override
    public Context getContext() {
        return mTransformInvocation.getContext();
    }

    @Override
    public Collection<TransformInput> getInputs() {
        return mTransformInvocation.getInputs();
    }

    @Override
    public Collection<TransformInput> getReferencedInputs() {
        return mTransformInvocation.getReferencedInputs();
    }

    @Override
    public Collection<SecondaryInput> getSecondaryInputs() {
        return mTransformInvocation.getSecondaryInputs();
    }

    @Override
    public TransformOutputProvider getOutputProvider() {
        return mTransformInvocation.getOutputProvider();
    }

    @Override
    public boolean isIncremental() {
        return mTransformInvocation.isIncremental();
    }

    @Override
    public void onTransformStart() {
        for (ITransformer transformer : mTransformerList) {
            transformer.onTransformStart();
        }
    }

    @Override
    public void onTransformEnd() {
        for (ITransformer transformer : mTransformerList) {
            transformer.onTransformEnd();
        }
    }

    public byte[] onTransform(byte[] bytes) {
        for (ITransformer transformer : mTransformerList) {
            bytes = transformer.transform(bytes);
        }
        return bytes;
    }

    public void onLastTransform(String dirPath) {
        for (ITransformer transformer : mTransformerList) {
            transformer.lastTransform(dirPath);
        }
    }
}
