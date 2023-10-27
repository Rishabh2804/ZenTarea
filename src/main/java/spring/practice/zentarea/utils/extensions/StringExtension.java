package spring.practice.zentarea.utils.extensions;

import spring.practice.zentarea.utils.logging.*;

import java.lang.reflect.*;

public final class StringExtension {

    /**
     * Returns a string representation of the given object
     *
     * @param obj the object to get the string representation of
     * @return a string representation of the given object
     */
    public static String toString(Object obj) {
        if (obj == null) return "null";

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
