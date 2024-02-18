package exercise;

import java.lang.reflect.Field;
import java.lang.annotation.Annotation;
import java.util.*;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> notValidFields = new ArrayList<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(address);
                    if (value == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Map<String, List<String>> notValidFields = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof NotNull) {
                    try {
                        Object value = field.get(object);
                        if (value == null) {
                            writeErrors(notValidFields, field.getName(), "Null");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (annotation instanceof MinLength) {
                    try {
                        Object obj = field.get(object);
                        if ((obj == null) || (((String) obj).length() < ((MinLength) annotation).minLength())) {
                            writeErrors(notValidFields, field.getName(), "Length less than min");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return notValidFields;
    }

    private static void writeErrors(Map<String, List<String>> notValidateFields, String fieldName, String error) {
        List<String> fildErrors = notValidateFields.get(fieldName);
        if (fildErrors == null) {
            fildErrors = new ArrayList<>();
            notValidateFields.put(fieldName, fildErrors);
        }
        fildErrors.add(error);
    }
}
// END
