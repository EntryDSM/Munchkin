package kr.hs.entrydsm.score.integrate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalEntity {
    protected static <T> T nullCheck(T t) {
        List<String> fields = Arrays.stream(t.getClass().getDeclaredFields())
                                                        .map(field -> {
                                                            String fieldName = field.getName();
                                                            return fieldName.substring(0, 1).toUpperCase() +
                                                                   fieldName.substring(1);
                                                        })
                                                        .collect(Collectors.toList());
        for (Method method: t.getClass().getDeclaredMethods()) {
            String methodName = method.getName();
            if (fields.stream()
                      .filter(field -> field.equals(methodName.substring(3)))
                      .toArray().length == 0) { continue; }

            if (methodName.substring(0, 3).equals("get")) {
                Object value;
                try {
                    value = method.invoke(t);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new InvalidGetterAccessException();
                }
                if (value == null) { throw new FieldNotExistException(); }
            }
        }

        return t;
    }
}
