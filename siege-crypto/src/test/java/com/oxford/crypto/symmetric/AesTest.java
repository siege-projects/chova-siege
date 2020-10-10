package com.oxford.crypto.symmetric;

/**
 * AES加密解密 测试类
 *
 * @author Chova
 * @date 2020/9/30
 */
public class AesTest {
    public static void main(String[] args) {
        String data = "chova-siege";
        String key = AES.keyGenerator();

        String encrypted = AES.encrypt(data, key);
        System.out.println("加密后的密文:" + encrypted);

        String decrypted = AES.decrypt(encrypted, key);
        System.out.println("解密后的明文:" + decrypted);
    }
}
