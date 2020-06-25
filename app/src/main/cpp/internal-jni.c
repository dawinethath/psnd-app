#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_kh_com_psnd_internal_MobileInternal_url(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "https://psnd.app/api/v1/");
}

JNIEXPORT jstring JNICALL
Java_kh_com_psnd_internal_MobileInternal_secret(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "12345678901234567890123456789012");
}

//JNIEXPORT jstring JNICALL
//Java_kh_com_psnd_internal_MobileInternal_encode(JNIEnv *env, jobject thiz, jstring str) {
//
//    return (*env)->NewStringUTF(env, "sWMFdiopESUWnaJAHuowIwk3kddwsd");
//}

//void replaceAll(char *str, char oldChar, char newChar) {
//    int i = 0;
//    /* Run till end of string */
//    while (str[i] != '\0') {
//        /* If occurrence of character is found */
//        if (str[i] == oldChar) {
//            str[i] = newChar;
//        }
//        i++;
//    }
//}
