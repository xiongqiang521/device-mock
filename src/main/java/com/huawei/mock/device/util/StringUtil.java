package com.huawei.mock.device.util;

import org.springframework.util.StringUtils;

import static org.springframework.util.StringUtils.*;

public class StringUtil {
    public static Long parseLong(String s, long def) {
        if (hasText(s)) {
            return Long.parseLong(s);
        }
        return def;
    }

    /**
     * 解析integer,为空使用默认值
     * @param s
     * @param def
     * @return
     */
    public static Integer parseInt(String s, int def) {
        if (hasText(s)) {
            return Integer.parseInt(s);
        }
        return def;
    }
}
