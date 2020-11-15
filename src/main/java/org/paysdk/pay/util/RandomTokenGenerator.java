package org.paysdk.pay.util;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class RandomTokenGenerator {

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String digits = "0123456789";

    private static final char[] alphanum = (upper + lower + digits).toCharArray();

    private static final Random random = new SecureRandom();

    private static final char[] buf = new char[20];

//    public static String nextString() {
//        for (int i = 0; i < buf.length; ++i)
//            buf[i] = alphanum[random.nextInt(alphanum.length)];
//        return new String(buf);
//    }

    public static String nextString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
