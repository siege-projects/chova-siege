package com.oxford.springboot.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

/**
 * Kafka消息消费者
 *
 * @author Chova
 * @date 2020/12/22
 */
public class Consumer {

    /**
     * 使用@kafkaListener注解可以监听主题,分区,消费组等
     */
    @KafkaListener(topics = "topic")
    public void receive(String info) {
        System.out.println("消费消息:" + info);
    }
}
