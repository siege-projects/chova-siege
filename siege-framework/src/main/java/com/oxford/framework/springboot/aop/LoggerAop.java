package com.oxford.framework.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 使用SpringBoot AOP注解记录程序执行日志的类
 *
 * @author Chova
 * @date 2021/04/27
 */
@Component
@Aspect
public class LoggerAop {

    /**
     * 定义一个切入点，拦截指定包中的内容
     * - 第一个 * 表示任意修饰符和任意返回值
     * - 第二个 * 表示SpringBoot下的任意web包或子包
     * - 第三个 * 表示任意方法
     * - .. 表示任意数量的参数
     */
    @Pointcut("execution(* com.oxford.framework.springboot..*.*(..))")
    public void loggerPointcut() {
    }

    /**
     * 前置通知
     * 在目标方法调用之前执行操作
     *
     * @param joinPoint 连接点
     */
    @Before(value = "loggerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 连接点方法名称
        String methodName = joinPoint.getSignature().getName();
        // 连接点方法参数列表
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("===========================前置通知：在方法调用之前执行操作============================" + methodName);
    }

    /**
     * 后置通知
     * 在目标方法调用之后执行操作，无论是否发生异常后置通知都会执行
     *
     * @param joinPoint 连接点
     */
    @After(value = "loggerPointcut()")
    public void doAfter(JoinPoint joinPoint) {
        // 连接点方法名称
        String methodName = joinPoint.getSignature().getName();
        System.out.println("===========================后置通知：在方法调用之后执行操作============================" + methodName);
    }

    /**
     * 后置返回通知
     * 在目标方法返回值之后执行操作，可以获取到方法的返回值
     *
     * @param joinPoint 连接点
     * @param result    目标方法返回值
     */
    @AfterReturning(value = "loggerPointcut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 连接点方法名称
        String methodName = joinPoint.getSignature().getName();
        System.out.println("===========================后置返回通知：在方法返回值之后执行操作============================" + methodName + "\n" + "目标方法返回值：" + result);
    }

    /**
     * 后置异常通知
     * 在目标方法抛出异常之后执行操作，可以获取抛出的异常，并且可以指定出现特定异常后执行的操作
     *
     * @param joinPoint 连接点
     * @param exception 目标方法抛出的异常
     */
    @AfterThrowing(value = "loggerPointcut()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
        // 连接点方法名称
        String methodName = joinPoint.getSignature().getName();
        System.out.println("===========================后置异常通知：在方法抛出异常之后执行操作============================" + methodName + "\n" + "目标方法抛出的异常：" + exception);
    }

    @Around(value = "loggerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        // 连接点方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("===========================环绕通知：在方法调用之前和调用之后执行操作。在方法调用之前执行操作============================" + methodName);

        /**
         * 通知执行返回的结果
         */
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("===========================环绕通知：在方法调用之前和调用之后执行操作。在方法调用之后执行操作============================" + methodName);
        return result;
    }
}
