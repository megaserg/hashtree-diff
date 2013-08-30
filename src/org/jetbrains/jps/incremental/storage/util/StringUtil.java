package org.jetbrains.jps.incremental.storage.util;

/**
 * @author JetBrains
 * @author Sergey Serebryakov
 */
public class StringUtil {

    public static String toHexString(byte[] bytes)
    {
        final String hexChar = "0123456789abcdef";

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(hexChar.charAt((b >> 4) & 0x0f));
            sb.append(hexChar.charAt(b & 0x0f));
        }
        return sb.toString();
    }

    public static boolean endsWithChar(CharSequence s, char suffix) {
        return s != null && s.length() != 0 && s.charAt(s.length() - 1) == suffix;
    }

    public static int compare(String s1, String s2, boolean ignoreCase) {
        //noinspection StringEquality
        if (s1 == s2) return 0;
        if (s1 == null) return -1;
        if (s2 == null) return 1;
        return ignoreCase ? s1.compareToIgnoreCase(s2) : s1.compareTo(s2);
    }
}
