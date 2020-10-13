package com.oxford.crypto.symmetric;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import static com.oxford.crypto.constant.CryptoConstant.*;

/**
 * 对称加密DES
 * <p>
 * DES加密解密工具类,使用PKCS5Padding的ECB模式填充
 *
 * @author Chova
 * @date 2020/10/9
 */
public class DES {

    /**
     * 将数据进行加密以Base64编码返回
     *
     * @param data 需要加密的数据
     * @param key  加密密钥
     * @return String 以Base64编码返回加密数据
     */
    public static String encrypt(String data, String key) {
        byte[] encryptData = doDes(data.getBytes(), key, Cipher.ENCRYPT_MODE);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(encryptData);
    }

    /**
     * 将数据进行解密以Base64解码返回
     *
     * @param encryptData 加密的数据
     * @param key         加密密钥
     * @return String 以Base64解码返回解密数据
     */
    public static String decrypt(String encryptData, String key) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] encryptByte = decoder.decodeBuffer(encryptData);
            byte[] decryptByte = doDes(encryptByte, key, Cipher.DECRYPT_MODE);
            return new String(decryptByte, CHARACTER_UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成DES密钥
     *
     * @return String DES密钥
     */
    public static String keyGenerator() throws NoSuchAlgorithmException {
        Random random = new Random();
        int multiple = random.nextInt(32) + 1;
        SecureRandom secureRandom = new SecureRandom();
        int byteSize = 8 * multiple;
        byte[] bytes = new byte[byteSize];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 根据模式进行DES加密和解密的操作
     *
     * @param data 需要进行DES操作数据
     * @param key  密钥
     * @param mode 模式
     * @return byte[] 进行DES操作后的数据
     */
    private static byte[] doDes(byte[] data, String key, int mode) {
        try {
            // 新建一个可信任的随机数据源
            SecureRandom secureRandom = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            /*
             * 创建密钥工厂SecretKeyFactory
             * 用来将DESKeySpec转换成SecretKey
             */
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_KEY);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            /*
             * 创建加密对象Cipher,初始化,完成对数据的加密操作
             * 使用PKCS5Padding完成的ECB模式填充
             */
            Cipher cipher = Cipher.getInstance(DES_ECB_PKCS5);
            cipher.init(mode, secretKey, secureRandom);
            byte[] result = cipher.doFinal(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
