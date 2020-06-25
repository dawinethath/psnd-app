package kh.com.psnd.internal;

public class MobileInternal {

    public static native String url();

    public static native String secret();

//    public static native String encode(String str);

    static {
        System.loadLibrary("internal-jni");
    }
}