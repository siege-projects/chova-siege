package com.oxford.http.interceptor;

import java.io.Serializable;

/**
 * 响应信息
 *
 * @author Chova
 * @date 2020/12/22
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应码 - 请求成功
     */
    public static final int SUCCESS = 200;

    /**
     * 响应码 - 服务器内部异常
     */
    public static final int FAIL = 500;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(String msg, T data) {
        this(0, msg, data);
    }

    public R(int code, String msg) {
        this(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
