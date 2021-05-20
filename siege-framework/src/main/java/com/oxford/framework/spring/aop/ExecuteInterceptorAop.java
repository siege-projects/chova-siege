package com.oxford.framework.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 使用Spring AOP执行拦截的类
 *
 * @author Chova
 * @date 2021/04/26
 */
@Component
@Aspect
public class ExecuteInterceptorAop {

    /**
     * 前置通知
     * 在目标方法调用之前执行操作
     *
     * @param joinPoint 连接点
     */
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
     * @param result 目标方法返回值
     */
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
    public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
        // 连接点方法名称
        String methodName = joinPoint.getSignature().getName();
        System.out.println("===========================后置异常通知：在方法抛出异常之后执行操作============================" + methodName + "\n" + "目标方法抛出的异常：" + exception);
    }

    /**
     * 环绕通知
     * 在目标方法调用之前和调用之后执行操作
     *
     * @param proceedingJoinPoint 通知执行的连接点
     * @return Object 通知执行的返回结果
     */
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        // 连接点方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("===========================环绕通知：在方法调用之前和调用之后执行操作。在方法调用之前执行操作============================" + methodName);

        /**
         * 通知执行返回的结果
         */
        Object result = null;

        try {
            // 通知执行并获取执行返回结果
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("===========================环绕通知：在方法调用之前和调用之后执行操作。在方法调用之后执行操作============================" + methodName);
        return result;
    }
}
