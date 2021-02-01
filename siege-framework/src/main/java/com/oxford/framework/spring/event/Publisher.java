package com.oxford.framework.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Spring事件模型 - 事件发布者
 * <p>
 * 事件发布者,使用AbstractApplicationContext类中实现ApplicationEventPublisher接口,重写的publishEvent()方法
 *
 * @author Chova
 * @date 2021/02/01
 */
public class Publisher {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 发布事件
     *
     * @param message 事件
     */
    public void publishEvent(String message) {
        Event event = new Event(this, message);
        applicationContext.publishEvent(event);
    }
}
