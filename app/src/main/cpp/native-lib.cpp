#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_br_com_rogersdk_calculadorajni_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_br_com_rogersdk_calculadorajni_MainActivity_sum(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
    return x + y;
}

extern "C" JNIEXPORT jint JNICALL
Java_br_com_rogersdk_calculadorajni_MainActivity_minus(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
    return x - y;
}

extern "C" JNIEXPORT jint JNICALL
Java_br_com_rogersdk_calculadorajni_MainActivity_divide(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
    return x / y;
}

extern "C" JNIEXPORT jint JNICALL
Java_br_com_rogersdk_calculadorajni_MainActivity_multiply(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
    return x * y;
}