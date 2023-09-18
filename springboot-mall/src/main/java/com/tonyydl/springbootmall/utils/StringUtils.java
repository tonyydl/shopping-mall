package com.tonyydl.springbootmall.utils;

import lombok.NonNull;

import java.util.regex.Pattern;

public class StringUtils {

    public static String makeCamelCase(@NonNull String snakeCase) {
        return Pattern.compile("_([a-z])")
                .matcher(snakeCase)
                .replaceAll(m -> m.group(1).toUpperCase());
    }
}
