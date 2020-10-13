package com.oxford.crypto.digest;

import java.security.MessageDigest;

import static com.oxford.crypto.constant.CryptoConstant.CHARACTER_UTF_8;
import static com.oxford.crypto.constant.CryptoConstant.MD5;

/**
 * MD5摘要加密
 *
 * @author Chova
 * @date 2020/10/10
 */
public class MD5 {

    /**
     * 使用MD5对数据进行加密
     *
     * @param data        需要加密的数据
     * @param charsetName 编码方式
     * @return String MD5加密的数据
     */
    public static String encrypt(String data, String charsetName) {
        String encryptData = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            if (null == charsetName || "".equals(charsetName)) {
                encryptData = byteArray2HexString(digest.digest(data.getBytes(CHARACTER_UTF_8)));
            } else {
                encryptData = byteArray2HexString(digest.digest(data.getBytes(charsetName)));
            }
            return encryptData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个字节转换为16进制的字符串
     *
     * @param b 需要转换的单个字节
     * @return String 字节转换的16进制字符串
     */
    private static String byte2HexString(byte b) {
        final String[] DIGITS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String[] hexString = new String[2];

        hexString[0] = DIGITS[(b >>> 4) & 0X0F];
        hexString[1] = DIGITS[b & 0X0F];

        return String.valueOf(hexString);
    }

    /**
     * 将字节数组转换为16进制的字符串
     *
     * @param bytes 需要转换的字节数组
     * @return String 字节数组转换的16进制字符串
     */
    private static String byteArray2HexString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        int bufferLength = buffer.length();
        for (int i = 0; i < bufferLength; i++) {
            String byteHexString = byte2HexString(bytes[i]);
            buffer.append(byteHexString);
        }
        return buffer.toString();
    }
}
