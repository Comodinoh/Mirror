package ro.comodinoh.mirror;

import java.lang.reflect.Field;

public class UnsafeFieldAccessor<T> {
    private final long offset;

    protected UnsafeFieldAccessor(Field field) {
        offset = Mirror.getUnsafe().objectFieldOffset(field);
    }

    public boolean getBoolean(Object instance) {
        return Mirror.getUnsafe().getBoolean(instance, offset);
    }

    public void setBoolean(Object instance, boolean newBoolean) {
        Mirror.getUnsafe().putBoolean(instance, offset, newBoolean);
    }

    public int getInt(Object instance) {
        return Mirror.getUnsafe().getInt(instance, offset);
    }

    public void setInt(Object instance, int newInt) {
        Mirror.getUnsafe().putInt(instance, offset, newInt);
    }

    public float getFloat(Object instance) {
        return Mirror.getUnsafe().getFloat(instance, offset);
    }

    public void setFloat(Object instance, float newFloat) {
        Mirror.getUnsafe().putFloat(instance, offset, newFloat);
    }

    public double getDouble(Object instance) {
        return Mirror.getUnsafe().getDouble(instance, offset);
    }

    public void setDouble(Object instance, double newDouble) {
        Mirror.getUnsafe().putDouble(instance, offset, newDouble);
    }

    public T get(Object instance) {
        return (T) Mirror.getUnsafe().getObject(instance, offset);
    }

    public void set(Object instance, T value) {
        if(value.getClass().isPrimitive()) {
            throw new RuntimeException("Tried setting a primitive (int, long, etc.) type as an object address");
        }
        Mirror.getUnsafe().putObject(instance, offset, value);
    }
}
