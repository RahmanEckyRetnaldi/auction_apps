#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_rer_core_utils_NativeLib_getDevBaseUrl(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("https://66f817b12a683ce9730e9db2.mockapi.io/");

}