package com.oxford.crypto.symmetric;


import org.springframework.lang.NonNull;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import static com.oxford.crypto.constant.CryptoConstant.AES_KEY;
import static com.oxford.crypto.constant.CryptoConstant.CHARACTER_UTF_8;

/**
 * 对称密钥加密AES
 * <p>
 * AES加密解密工具类，返回Base64
 *
 * @author Chova
 * @date 2020/09/30
 */
public class AES {

    /**
     * AES加密
     *
     * @param data 需要加密的数据
     * @param key  加密密钥
     * @return String 加密的数据，加密失败则返回null
     */
    public static String encrypt(@NonNull String data, String key) {
        return doAes(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * AES解密
     *
     * @param encryptData 加密的数据
     * @param key         解密密钥
     * @return String 解密的数据，解密失败则返回null
     */
    public static String decrypt(@NonNull String encryptData, String key) {
        return doAes(encryptData, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 生成密钥
     *
     * @return String 生成的密钥
     */
    public static String keyGenerator() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_KEY);
            SecureRandom secureRandom = new SecureRandom();
            int bitSize = 128;
            keyGenerator.init(bitSize, secureRandom);
            SecretKey key = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES加密解密操作
     *
     * @param data 需要加密或者解密的数据
     * @param key  加密或者解密的密钥
     * @param mode 加密或者解密的模式
     * @return String 加密或者解密成功的数据，如果加密或者解密失败则返回null
     */
    private static String doAes(@NonNull String data, String key, int mode) {
        boolean isEncrypt = mode == Cipher.ENCRYPT_MODE;
        byte[] content;

        byte[] codeKey = Base64.getDecoder().decode(key);
        try {
            if (isEncrypt) {
                content = data.getBytes(CHARACTER_UTF_8);
            } else {
                content = Base64.getDecoder().decode(data);
            }
            // 创建密码器
            Cipher cipher = Cipher.getInstance(AES_KEY);
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(codeKey, 16), AES_KEY);
            // 初始化
            cipher.init(mode, keySpec);
            byte[] result = cipher.doFinal(content);
            if (isEncrypt) {
                return Base64.getEncoder().encodeToString(result);
            } else {
                return new String(result, CHARACTER_UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
