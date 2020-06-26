package core.lib.utils;

import android.text.TextUtils;

import core.lib.BuildConfig;
import retrofit2.Call;
import retrofit2.Response;

public class Log {
    private static final String TAG = "MyApp";

    public static void i(Object msg) {
        if (BuildConfig.DEBUG_MODE) {
            android.util.Log.i(TAG, getLocation() + msg);
        }
    }

    public static void d(Object msg) {
        if (BuildConfig.DEBUG_MODE) {
            android.util.Log.d(TAG, getLocation() + msg);
        }
    }

    public static void w(Object msg) {
        if (BuildConfig.DEBUG_MODE) {
            android.util.Log.w(TAG, getLocation() + msg);
        }
    }

    public static void i(Throwable throwable) {
        i(throwable.getMessage());
    }

    public static void e(Object msg) {
        if (BuildConfig.DEBUG_MODE) {
            android.util.Log.e(TAG, getLocation() + msg);
        }
    }

    public static void e(Throwable throwable) {
        if (BuildConfig.DEBUG_MODE) {
            android.util.Log.e(TAG, getLocation() + throwable.getMessage());
        }
    }

    public static void e(Throwable throwable, Response response) {
        String url = response.raw().request().url().toString();
        e(throwable.getMessage() + "\nURL: " + url);
    }

    public static void e(Throwable throwable, Call call) {
        String url = call.request().toString();
        e(throwable.getMessage() + "\nURL: " + url);
    }

    private static String getLocation() {
        String              className = Log.class.getName();
        StackTraceElement[] traces    = Thread.currentThread().getStackTrace();
        boolean             found     = false;

        for (StackTraceElement trace : traces) {
            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
                    }
                }
                else if (trace.getClassName().startsWith(className)) {
                    found = true;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }
}