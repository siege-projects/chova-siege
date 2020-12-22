package com.oxford.springboot.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * kafka消息生产者
 *
 * @author Chova
 * @date 2020/12/22
 */
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send() {
        String topic = "topic";
        String info = "业务处理信息";
        ListenableFuture future = kafkaTemplate.send(topic, info);
        future.addCallback(s -> System.out.println("主题:" + topic + "消息" + info + "发送成功."), e -> System.out.println("主题:" + topic + "消息" + info + "发送失败."));
    }
}
