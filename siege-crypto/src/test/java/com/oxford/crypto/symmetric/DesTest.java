package com.oxford.crypto.symmetric;

import java.security.NoSuchAlgorithmException;

/**
 * DES加密解密 测试类
 *
 * @author Chova
 * @date 2020/10/10
 */
public class DesTest {
    public static void main(String[] args) {
        String data = "chova-siege";
        try {
            String key = DES.keyGenerator();
            System.out.println(key.length());
            String encrypt = DES.encrypt(data, key);
            System.out.println("加密后的密文:" + encrypt);

            String decrypt = DES.decrypt(encrypt, key);
            System.out.println("解密后的明文:" + decrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
