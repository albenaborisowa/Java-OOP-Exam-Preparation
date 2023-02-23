package football.util;

import football.entities.field.Field;

public class StringUtils {
    public static void nullOrEmpty(String str, String message) {
        if (str == null || str.trim().isEmpty()) {
            throw new NullPointerException(message);
        }
    }
}
