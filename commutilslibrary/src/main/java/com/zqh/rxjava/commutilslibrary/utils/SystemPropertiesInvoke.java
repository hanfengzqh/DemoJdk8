package com.zqh.rxjava.commutilslibrary.utils;

import android.util.Log;

import java.lang.reflect.Method;

public class SystemPropertiesInvoke {
    private static final String TAG = "SystemPropertiesInvoke";
    private static Method getLongMethod, getBooleanMethod, getIntegerMethod, getStringMethod;

    public static int getInt(final String key, final int def) {
        try {
            if (getIntegerMethod == null) {
                getIntegerMethod = Class.forName("android.os.SystemProperties")
                        .getMethod("getInt", String.class, int.class);
            }

            return ((Integer) getIntegerMethod.invoke(null, key, def)).intValue();
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return def;
        }
    }

    public static long getLong(final String key, final long def) {
        try {
            if (getLongMethod == null) {
                getLongMethod = Class.forName("android.os.SystemProperties")
                        .getMethod("getLong", String.class, long.class);
            }

            return ((Long) getLongMethod.invoke(null, key, def)).longValue();
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return def;
        }
    }

    public static boolean getBoolean(final String key, final boolean def) {
        try {
            if (getBooleanMethod == null) {
                getBooleanMethod = Class.forName("android.os.SystemProperties")
                        .getMethod("getBoolean", String.class, boolean.class);
            }
            return (Boolean) getBooleanMethod.invoke(null, key, def);
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return def;
        }
    }

    public static String getString(final String key, final String def) {
        try {
            if (getStringMethod == null) {
                getStringMethod = Class.forName("android.os.SystemProperties")
                        .getMethod("get", String.class, String.class);
            }
            return (String) getStringMethod.invoke(null, key, def);
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return def;
        }
    }
}