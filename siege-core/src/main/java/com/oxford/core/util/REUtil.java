package com.oxford.core.util;

import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author Chova
 * @date 2021/02/05
 */
public class REUtil {

    /**
     * 手机号格式
     * 想要更加准确地验证匹配手机号只进行匹配11位数字是完全不能满足条件的,还需要了解手机号开放的号段
     */
    private static Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^1[0-9]{10}$");

    /**
     * 汉字格式,一个或者多个汉字
     */
    private static Pattern CHINESE_CHARACTER_PATTERN = Pattern.compile("^[u0391-uFFE5]+$");

    /**
     * 邮政编码格式
     */
    private static Pattern POSTAL_CODE_PATTERN = Pattern.compile("^[1-9][0-9]{5}$");

    /**
     * URL格式
     */
    private static Pattern URL_PATTERN = Pattern.compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");

    /**
     * 身份证号格式
     */
    private static Pattern CHINESE_ID_PATTERN = Pattern.compile("/^[1-9]\\d{5}(18|19|20|21|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$/");

    /**
     * 验证手机号码格式是否正确
     *
     * @param phoneNum 手机号
     * @return boolean 手机号的格式是否正确
     */
    public static boolean isPhoneNumber(String phoneNum) {
        return PHONE_NUMBER_PATTERN.matcher(phoneNum).matches();
    }

    /**
     * 验证是否包含一个或者多个汉字
     *
     * @param str 需要验证的字符串
     * @return boolean 是否包含一个或者多个汉字
     */
    public static boolean isContainsChineseCharacter(String str) {
        return CHINESE_CHARACTER_PATTERN.matcher(str).matches();
    }

    /**
     * 验证邮政编码格式是否正确
     *
     * @param postalCode 邮政编码
     * @return boolean 邮政编码格式是否正确
     */
    public static boolean isPostalCode(String postalCode) {
        return POSTAL_CODE_PATTERN.matcher(postalCode).matches();
    }

    /**
     * 验证URL格式是否正确
     *
     * @param url URL
     * @return boolean URL格式是否正确
     */
    public static boolean isURL(String url) {
        return POSTAL_CODE_PATTERN.matcher(url).matches();
    }

    /**
     * 身份证号格式
     *
     * @param chineseId chineseId
     * @return boolean 身份证号格式是否正确
     */
    public static boolean isChineseId(String chineseId) {
        return POSTAL_CODE_PATTERN.matcher(chineseId).matches();
    }
}
