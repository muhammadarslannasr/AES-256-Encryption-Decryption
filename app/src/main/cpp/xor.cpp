#include <jni.h>
#include <string>
#include <iostream>

void encryptDecrypt(char *input, char *output);

//extern "C" JNIEXPORT jstring JNICALL
//Java_com_example_nativetestcpp_XORActivity_base64Encode(JNIEnv *env, jobject thiz) {
//
//    char baseStr[] = "kylewbanks.com";
//
//    char encrypted[strlen(baseStr)];
//    encryptDecrypt(baseStr, encrypted);
//    printf("Encrypted:%s\n", encrypted);
//
//    char decrypted[strlen(baseStr)];
//    encryptDecrypt(encrypted, decrypted);
//    printf("Decrypted:%s\n", decrypted);
//}

void encryptDecrypt(char *input, char *output) {
    char key[] = {'K', 'C', 'Q'}; //Can be any chars, and any size array

    int i;
    for(i = 0; i < strlen(input); i++) {
        output[i] = input[i] ^ key[i % (sizeof(key)/sizeof(char))];
    }
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_encryptionanddecryption_Activities_XORActivity_encodeData(JNIEnv *env,
                                                                           jobject thiz) {
    char baseStr[] = "kylewbanks.com";

    char encrypted[strlen(baseStr)];
    encryptDecrypt(baseStr, encrypted);
    printf("Encrypted:%s\n", encrypted);

    char decrypted[strlen(baseStr)];
    encryptDecrypt(encrypted, decrypted);
    printf("Decrypted:%s\n", decrypted);

    std::string hello = "sdfas";
    return env->NewStringUTF(hello.c_str());

   // return ;
  //  return env->GetString(decrypted);
}
