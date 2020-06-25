package core.lib.utils;

import android.util.Base64;

public class MyBase64Utils {

    public static String encode(String string) {
        byte[] encode = Base64.encode(string.getBytes(), Base64.NO_WRAP);
        return Base64.encodeToString(encode, Base64.NO_WRAP);
    }

    public static String decode(String string) {
        try {
            byte[] decode = Base64.decode(string, Base64.NO_WRAP);
            return new String(decode, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
