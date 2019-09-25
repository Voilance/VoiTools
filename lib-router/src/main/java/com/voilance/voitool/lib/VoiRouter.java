package com.voilance.voitool.lib;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class VoiRouter {

    /**
     * 路由表初始化标识
     */
    private static boolean mIsInit = false;
    /**
     * 路由表：route -> path
     * eg: "MainActivity" -> "com.example.demo.MainActivity"
     */
    private static Map<String, String> mRouteMap = new HashMap<>();

    /**
     * VoiRouteTable.class
     * 编译期动态生成的类，记录了所有使用了@VoiRoute注解的类。
     */
    private static final String ROUTE_TABLE_CLASS = "com.voilance.voitool.lib.VoiRouteTable";

    /**
     * 利用VoiRouteTable的init(Map<String, String>)方法，初始化路由表
     */
    static {
        try {
            Class<?> clas = Class.forName(ROUTE_TABLE_CLASS);
            Method method = clas.getMethod("init", Map.class);
            method.invoke(null, mRouteMap);
            mIsInit = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 路由表是否已经初始化
     */
    public static boolean isInit() {
        return mIsInit;
    }

    /**
     * 注册路由，出现同值时会覆盖旧值。
     */
    public static void registerRoute(String route, String path) {
        if (route == null ||
            path == null ||
            route.trim().length() == 0 ||
            path.trim().length() == 0) {
            return;
        }
        mRouteMap.put(route, path);
    }

    /**
     * 判断路由表中是否存在某路由
     * @param route
     */
    public static boolean hasRoute(String route) {
        return route != null && mRouteMap.containsKey(route);
    }

    /**
     * 判断路由表中是否存在某路径
     * @param path
     */
    public static boolean hasPath(String path) {
        return path != null && mRouteMap.containsValue(path);
    }

    /**
     * 利用路由名获取对应的路径
     * @param route
     */
    public static String getPath(String route) {
        return hasRoute(route) ? mRouteMap.get(route) : "";
    }

    /**
     * 根据路径获取对应的路由名
     * @param path
     */
    public static String getRoute(String path) {
        if (hasPath(path)) {
            for (Map.Entry<String, String> entry : mRouteMap.entrySet()) {
                if (entry.getValue().equals(path)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }


    private Context mContext;
    private Intent mIntent;
    private int mRequestCode = DEFAULT_REQUEST_CODE;
    private boolean mIsFinish = false;

    private static final int DEFAULT_REQUEST_CODE = -1;

    private VoiRouter(Context context) {
        mContext = context;
        mIntent = new Intent();
    }

    public static VoiRouter from(Context context) {
        return new VoiRouter(context);
    }

    public VoiRouter to(Class<?> clas) {
        mIntent.setClass(mContext, clas);
        return this;
    }

    public VoiRouter to(String route) {
        mIntent.setClassName(mContext, getPath(route));
        return this;
    }

    public VoiRouter requestCode(int code) {
        mRequestCode = code;
        return this;
    }

    public VoiRouter finish() {
        mIsFinish = true;
        return this;
    }

    public VoiRouter finish(boolean isFinish) {
        mIsFinish = isFinish;
        return this;
    }

    public void go() {
        if (mContext == null || mIntent == null) {
            return;
        }

        try {
            if (mContext instanceof Activity) {
                if (mRequestCode != DEFAULT_REQUEST_CODE) {
                    ((Activity) mContext).startActivityForResult(mIntent, mRequestCode);
                } else {
                    mContext.startActivity(mIntent);
                }
                if (mIsFinish) {
                    ((Activity) mContext).finish();
                }
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void go(Context context, Class<?> clas) {
        new VoiRouter(context).to(clas).go();
    }

    public static void go(Context context, String className) {
        new VoiRouter(context).to(className).go();
    }

    public static void go(Context context, Class<?> clas, int reqCode) {
        new VoiRouter(context).to(clas).requestCode(reqCode).go();
    }

    public static void go(Context context, String className, int reqCode) {
        new VoiRouter(context).to(className).requestCode(reqCode).go();
    }

    public static void go(Context context, Class<?> clas, boolean isFinish) {
        new VoiRouter(context).to(clas).finish(isFinish).go();
    }

    public static void go(Context context, String className, boolean isFinish) {
        new VoiRouter(context).to(className).finish(isFinish).go();
    }

    public static void go(Context context, Class<?> clas, int reqCode, boolean isFinish) {
        new VoiRouter(context).to(clas).requestCode(reqCode).finish(isFinish).go();
    }

    public static void go(Context context, String className, int reqCode, boolean isFinish) {
        new VoiRouter(context).to(className).requestCode(reqCode).finish(isFinish).go();
    }



    public VoiRouter putExtra(String key, byte value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, short value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, int value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, long value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, float value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, double value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, boolean value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, char value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, byte[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, short[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, int[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, long[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, float[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, double[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, boolean[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, char[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, String value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, String[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, CharSequence value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, CharSequence[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, Parcelable value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, Parcelable[] value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, Serializable value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtra(String key, Bundle value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public VoiRouter putExtras(Intent intent) {
        mIntent.putExtras(intent);
        return this;
    }

    public VoiRouter putExtras(Bundle bundle) {
        mIntent.putExtras(bundle);
        return this;
    }

    public VoiRouter setFlags(int flags) {
        mIntent.setFlags(flags);
        return this;
    }

    public VoiRouter addFlags(int flags) {
        mIntent.addFlags(flags);
        return this;
    }
}
