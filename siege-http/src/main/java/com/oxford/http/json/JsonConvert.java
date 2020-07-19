package com.oxford.http.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Json转换工具类
 *
 * @author Chova
 * @date 2020-07-19
 */
public class JsonConvert {
    public static final SerializerFeature[] FEATURESWITHNULLVALUE = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty};

    /**
     * 将String类型的Json数据转换为对象
     *
     * @param data  String类型的Json数据
     * @param clazz 对象类
     * @param <T>   String类型的数据对象类型
     * @return String类型的Json数据转换的对象
     */
    public static <T> T convertJsonToObject(String data, Class<T> clazz) {
        try {
            T t = JSON.parseObject(data, clazz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Json对象转换为Java对象
     *
     * @param data  Json对象
     * @param clazz 从Json对象转换的Java的类对象
     * @param <T>   Json对象类的类型
     * @return 从Json对象转化换的Java对象
     */
    public static <T> T convertJsonToObject(JSONObject data, Class<T> clazz) {
        try {
            T t = JSONObject.toJavaObject(data, clazz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将String类型的对象转换为数组
     *
     * @param data  String类型的Json对象
     * @param clazz 从String类型Json对象转换的Java类对象
     * @param <T>   Json对象类的类型
     * @return 从String类型的Json对象转换的List集合
     */
    public static <T> List<T> convertJsonToArray(String data, Class<T> clazz) {
        try {
            List<T> t = JSON.parseArray(data, clazz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将集合类型的Json对象转换为数组
     *
     * @param data  Json集合对象
     * @param clazz 从Json集合对象转换的Java类对象
     * @param <T>   Json集合对象的Java类类型
     * @return 从Json集合对象转换的Java的List集合
     */
    public static <T> List<T> convertJsonToArray(List<JSONObject> data, Class<T> clazz) {
        try {
            List<T> t = new ArrayList<>();
            for (JSONObject jsonObject : data) {
                t.add(convertJsonToObject(jsonObject, clazz));
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将对象转换成String类型的Json对象
     *
     * @param obj 要转换成String类型的Json对象的Java对象
     * @return Java对象转换成为String类型Json对象
     */
    public static String convertObjectToJson(Object obj) {
        try {
            String text = JSON.toJSONString(obj);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Java对象转换成Json对象
     *
     * @param obj 要转换成Json对象的Java对象
     * @return 从Java对象转换成的Json对象
     */
    public static JSONObject converObjectToJSONObject(Object obj) {
        try {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Java对象转化为Json对象并且过滤值为空的数据
     *
     * @param obj 要转换成String类型的Json对象的Java对象
     * @return 从Java对象转换的过滤值为空的数据的String类型的Json对象
     */
    public static String convertObjectToJSONWithNullValue(Object obj) {
        try {
            String text = JSONObject.toJSONString(obj, FEATURESWITHNULLVALUE);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}