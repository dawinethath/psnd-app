package kh.com.psnd.internal;

public class MobileInternal {

    public static native String url();

    static {
        System.loadLibrary("internal-jni");
    }
}