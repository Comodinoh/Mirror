package ro.comodinoh.mirror;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public final class FieldAccessor<T> {
    private final Field field;

    public FieldAccessor(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public T get(Object instance) throws IllegalAccessException {
        if(!field.isAccessible()) {
            field.setAccessible(true);
        }
        return (T) field.get(instance);
    }

    public Type getGenericType() {
        return field.getGenericType();
    }

    public boolean canBeAssignedTo(Class<?> clazz) {
        return clazz.isAssignableFrom(field.getType());
    }

    public void set(Object instance, T value) throws IllegalAccessException {
        if (!field.isAccessible()){
            field.setAccessible(true);
        }
        field.set(instance, value);
    }
}
