package com.oxford.framework.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * Spring事件模型 - 事件
 * <p>
 * 事件类,继承ApplicationEvent,并且写出相应的构造函数
 *
 * @author Chova
 * @date 2021/02/01
 */
public class Event extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    private String message;

    /**
     * 自定义Event类构造函数
     *
     * @param source  事件关联的数据
     * @param message 事件信息
     */
    public Event(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
