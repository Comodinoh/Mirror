# What this is

Mirror is a library that lets you swiftly and niftly bypass the Java Module System and any type of memory restriction to access the sun.misc.Unsafe Singleton instance and break the whole JVM!! (And also some QOL reflection features)

# How this works

Through the use of a JNI .so (.dll on windows) that resides in `lib/src/main/resources/libnative_mirror.so` that is compiled from `native/UnsafeReflection.cpp` it can directly return the Unsafe object, essentially bypassing any Java restriction.

