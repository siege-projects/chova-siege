package com.oxford.framework.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * Spring事件模型 - 事件监听者
 * <p>
 * 事件监听者,实现ApplicationListener接口,重写onApplicationEvent()方法
 *
 * @author Chova
 * @date 2021/02/01
 */
public class Listener implements ApplicationListener<Event> {

    /**
     * 监听事件
     *
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(Event event) {
        String message = event.getMessage();
        System.out.println("事件监听者接收到信息:" + message);
    }
}
