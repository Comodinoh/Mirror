package ro.comodinoh.mirror;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ro.comodinoh.mirror.UnsafeFieldAccessor;

public final class Mirror {
    public native static Unsafe getUnsafe();
    private static final Map<String, ClassMirror<?>> classMap = new ConcurrentHashMap<>();

    public static void init(File dataFolder) throws IOException {
        if(!dataFolder.isDirectory()) {
            throw new IOException("The provided file " + dataFolder.getAbsolutePath() + " is not a directory");
        }

        String osName = System.getProperty("os.name").toLowerCase();
        String fileName = osName.contains("win") ? "native_mirror.dll" : "libnative_mirror.so";

        File tempBinary = new File(dataFolder, fileName);

        InputStream is = Mirror.class.getResourceAsStream("/" + fileName);
        if (is == null) {
            throw new IOException("Native library not found in JAR: " + fileName);
        }

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        Files.copy(is, tempBinary.toPath(), StandardCopyOption.REPLACE_EXISTING);
        is.close();


        System.load(tempBinary.getAbsolutePath());
    }

    public static <T> ClassMirror<T> getClass(Class<T> type) {
        return (ClassMirror<T>) classMap.computeIfAbsent(type.getName(), (n) -> new ClassMirror(type));
    }

    public static ClassMirror<?> getClass(String name) {
        return classMap.computeIfAbsent(name, (n) -> {
            try {
                return new ClassMirror<>(Class.forName(name));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static UnsafeFieldAccessor<?> getUnsafeField(ClassMirror<?> clazz, String fieldName) {
        return new UnsafeFieldAccessor<>(clazz.getField(fieldName));
    }

    public static <T> UnsafeFieldAccessor<T> getUnsafeField(Class<?> clazz, String field) {
        return new UnsafeFieldAccessor<T>(getClass(clazz).getField(field));
    }
}
