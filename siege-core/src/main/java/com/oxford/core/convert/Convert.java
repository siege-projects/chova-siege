package com.oxford.core.convert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据转换类
 *
 * @author Chova
 * @date 2020/9/30
 */
public class Convert {

    private static Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");

    /**
     * 将字节数组转化为16进制的字符串
     *
     * @param bytes 字节数组
     * @return String 16进制的字符串
     */
    public static String byteToHexStr(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        int bufferLength = buffer.length();
        for (int i = 0; i < bufferLength; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            buffer.append(hex.toUpperCase());
        }
        return buffer.toString();
    }

    /**
     * 将16进制的字符串转换为字节数组
     *
     * @param hexStr 16进制的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStrToByte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        int halfLength = hexStr.length() / 2;
        byte[] bytes = new byte[halfLength];
        for (int i = 0; i < halfLength; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            bytes[i] = (byte) (high * 16 + low);
        }
        return bytes;
    }

    /**
     * 将形如 xxxx年xx月xx日 xx:xx:xx 格式的时间转换为: xxxx-xx-xx xx:xx:xx 格式的时间
     *
     * @param timeStr xxxx年xx月xx日 xx:xx:xx
     * @return String xxxx-xx-xx xx:xx:xx
     */
    public static String toDateFormat(String timeStr) {
        int index = 0;
        Matcher matcher = pattern.matcher(timeStr);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                if (index == 2) {
                    matcher.appendReplacement(sb, URLEncoder.encode("", "UTF-8"));
                    continue;
                }
                matcher.appendReplacement(sb, URLEncoder.encode("-", "UTF-8"));
                index++;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
