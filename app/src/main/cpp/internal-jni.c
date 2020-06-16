#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_kh_com_psnd_internal_MobileInternal_url(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "https://psnd.app/api/v1/");
}
