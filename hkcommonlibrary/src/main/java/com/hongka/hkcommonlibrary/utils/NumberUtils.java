package com.hongka.hkcommonlibrary.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by jusung.kim@sk.com on 2017/05/23
 */

public class NumberUtils {
    // 홀수인가?
    public static boolean isOdd(int value) {
        return value % 2 == 1;
    }

    // 짝수인가?
    public static boolean isEven(int value) {
        return value % 2 == 0;
    }

    // 천의 자리 콤마 찍기
    public static String commaString(double value) {
        return NumberFormat.getNumberInstance(Locale.US).format(value);
    }

    // 콤마 스트링을 숫자로
    public static double parseComma(String comma) {
        try {
            return NumberFormat.getNumberInstance(Locale.US).parse(comma).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean parseBoolean(String str) {
        return parse(str, false);
    }

    public static byte parseByte(String str) {
        return parse(str, (byte) 0);
    }

    public static short parseShort(String str) {
        return parse(str, (short) 0);
    }

    public static int parseInt(String str) {
        return parse(str, 0);
    }

    public static int parseIntRadix(String str, int radix) {
        return parseIntRadix(str, radix, 0);
    }

    public static int parseIntRadix(String str, int radix, int fallback) {
        try {
            return Integer.parseInt(str, radix);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static long parseLong(String str) {
        return parse(str, 0L);
    }

    public static float parseFloat(String str) {
        return parse(str, .0f);
    }

    public static char parseChar(String str) {
        return parse(str, '\0');
    }

    public static double parseDouble(String str) {
        return parse(str, .0);
    }

    public static boolean parse(String str, boolean fallback) {
        if (str == null) {
            return fallback;
        }
        return Boolean.parseBoolean(str);
    }

    public static byte parse(String str, byte fallback) {
        try {
            return Byte.parseByte(str);
        } catch (Exception e) {
            return fallback;
        }
    }


    public static short parse(String str, short fallback) {
        try {
            return Short.parseShort(str);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static int parse(String str, int fallback) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static long parse(String str, long fallback) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static float parse(String str, float fallback) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static double parse(String str, double fallback) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static char parse(String str, char fallback) {
        if (str == null || str.isEmpty()) {
            return fallback;
        }
        return str.charAt(0);
    }
}
