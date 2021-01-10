package com.oxford.http;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * HTTP中的码相关处理类
 *
 * @author Chova
 * @date 2020/07/09
 */
public class Code {

    /**
     * 将字符串进行HTTP编码
     *
     * @param text
     * @return String HTTP编码
     */
    public static String urlEncode(String text) {
        return Base64.getUrlEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将HTTP编码解析为字符串
     *
     * @param encode HTTP编码
     * @return String HTTP解码后的字符串
     */
    public static String urlDecode(String encode) {
        return new String(Base64.getUrlDecoder().decode(encode), StandardCharsets.UTF_8);
    }
}
