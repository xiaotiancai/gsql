package com.futech.basesql.utils;



public class SqlUtil {

    public static String sqlFieldToCamelCase(String filed) {
        String[] s = filed.split("_");
        int l = s.length;
        StringBuffer sb = new StringBuffer();
        sb.append(s[0]);
        for (int i = 1; i < l; i++) {
            char[] chars = s[i].toCharArray();
            chars[0] -= 32;
            sb.append(String.valueOf(chars));
        }

        return sb.toString();
    }
}
