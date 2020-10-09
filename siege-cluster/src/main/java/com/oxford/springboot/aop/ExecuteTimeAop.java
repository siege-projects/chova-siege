package com.oxford.springboot.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
     */
    @Pointcut("execution(* com.oxford.springboot..*.*(..))")
    public void executionTimePointcut() {
    }
}
