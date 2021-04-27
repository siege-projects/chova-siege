package com.oxford.framework.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 使用Spring AOP记录程序执行时间的类
 *
 * @author Chova
 * @date 2020-10-08
 */
@Component
@Aspect
public class ExecuteTimeAop {
    /**
     * 引入日志配置
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteTimeAop.class);

    /**
     * 定义一个切入点，拦截指定包中的内容
     * - 第一个 * 表示任意修饰符和任意返回值
     * - 第二个 * 表示SpringBoot下的任意web包或者子包
     * - 第三个 * 表示任意方法
     * - .. 表示匹配任意数量的参数
     */
    @Pointcut("execution(* com.oxford.framework.springboot..*.*(..))")
    public void executionTimePointcut() {
    }

    /**
     * 使用Around环绕增强记录程序执行时间
     *
     * @param joinPoint 流程切入点
     * @return Object 流程执行结果
     * @throws Throwable 抛出异常
     */
    @Around(value = "executionTimePointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("===========================Method Start============================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        LOGGER.info("请求地址URL：" + request.getRequestURI());
        LOGGER.info("用户IP：" + request.getRemoteAddr());
        LOGGER.info("类方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("参数：" + Arrays.toString(joinPoint.getArgs()));
        LOGGER.info("执行时间：" + (end - start) + "ms");
        LOGGER.info("===========================Method End============================");
        return result;
    }
}
