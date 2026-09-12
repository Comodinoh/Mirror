#include "UnsafeReflection.h"

JNIEXPORT jobject JNICALL Java_ro_comodinoh_mirror_Mirror_getUnsafe(JNIEnv * env, jclass) {
    jclass unsafeClass = env->FindClass("sun/misc/Unsafe");
    
    jfieldID fieldId = env->GetStaticFieldID(unsafeClass, "theUnsafe", "Lsun/misc/Unsafe;");
    
    jobject unsafeInstance = env->GetStaticObjectField(unsafeClass, fieldId);

    return unsafeInstance;
}
