package com.oxford.http.interceptor;

import com.alibaba.fastjson.JSON;
import com.oxford.crypto.symmetric.AES;
import com.oxford.springboot.redis.Redis;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 登录拦截器LoginInterceptor
 *
 * @author Chova
 * @date 2020/12/22
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Redis redis;

    /**
     * 构造器注入Redis
     *
     * @param redis 需要注入的Redis
     */
    public LoginInterceptor(Redis redis) {
        this.redis = redis;
    }

    /**
     * 使用拦截器对请求进行预处理
     *
     * @param request  请求体
     * @param response 响应体
     * @param handler  处理器
     * @return 请求是否处理成功
     * @throws Exception 如果有异常将会抛出
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // ====================================token校验==============================================
        // 获取全局请求头的admin_uuid的值
        String adminId = response.getHeader("admin_uuid");
        // 获取adminId对应的token
        Object tokenObj = redis.get(adminId);
        if (null == adminId || null == tokenObj) {
            invalidResponse(response);
            return false;
        }

        // ====================================加密串校验==============================================
        String sign = response.getHeader("sign");
        String token = tokenObj.toString();
        String aesSign = AES.encrypt(token, "admin_id");
        if (null == sign || Objects.equals(sign, aesSign)) {
            invalidResponse(response);
            return false;
        }

        // 更新token有效期1小时
        redis.set(adminId, token, 36000);
        return true;
    }

    /**
     * 对不合法的请求返回异常
     *
     * @param response 响应体
     */
    private void invalidResponse(HttpServletResponse response) throws Exception {
        R r = new R(401, "签名不正确或者token失效");
        String msg = JSON.toJSONString(r);
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // 解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().print(msg);
    }
}
