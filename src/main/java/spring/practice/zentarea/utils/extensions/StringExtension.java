package spring.practice.zentarea.utils.extensions;

import spring.practice.zentarea.utils.logging.*;

import java.lang.reflect.*;

public final class StringExtension {
    public static String toString(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                sb.append(field.getName()).append(": ").append(field.get(obj)).append(", ");
            } catch (IllegalAccessException e) {
                ZenLogger.logE("Error while accessing field: " + field.getName());
            }
        }
        sb.append(" }");
        return sb.toString();
    }
}
