package ro.comodinoh.mirror;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassMirror<T> {
    private final Class<T> clazz;
    private final Map<String, Field> fieldMap;
    private final Map<MethodID, Method> methodMap;

    ClassMirror(Class<T> clazz) {
        this.clazz = clazz;
        this.fieldMap = new ConcurrentHashMap<>();
        this.methodMap = new ConcurrentHashMap<>();
    }

    /**
     * Computes and looks up the field in the class if its absent, otherwise returns the cached one
     * @param name Field name
     * @return The java Field instance
     * @throws RuntimeException If the field with the name does not exist, RuntimeException is thrown.
     */
    public Field getField(String name) {
        return fieldMap.computeIfAbsent(name, (n) -> {
            try {
                return clazz.getDeclaredField(n);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Computes and looks up the method in the class if its absent, otherwise returns the cached one
     * @param name Method name
     * @param params Types of the parameters
     * @return The java Method instance
     * @throws RuntimeException If the method with the name and the parameter types does not exist, RuntimeException is thrown.
     */
    public Method getMethod(String name, Class<?>... params) {
        return methodMap.computeIfAbsent(new MethodID(name, params), (id) -> {
            try {
                return clazz.getDeclaredMethod(id.getName(), id.getParams());
            } catch(NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
