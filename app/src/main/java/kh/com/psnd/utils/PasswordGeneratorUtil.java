package kh.com.psnd.utils;

import core.lib.utils.PasswordGenerator;

public class PasswordGeneratorUtil {

    public static final int PWD_LENGTH = 6;

    public static PasswordGenerator getPasswordGenerator() {
        return new PasswordGenerator.Builder()
                .useLower(true)
                .useUpper(true)
                .useDigits(true)
                .usePunctuation(false)
                .build();
    }

}
