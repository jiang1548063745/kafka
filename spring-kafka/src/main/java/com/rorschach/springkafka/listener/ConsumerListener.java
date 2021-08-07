package com.rorschach.springkafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者监听器
 */
@Component
public class ConsumerListener {

    @KafkaListener(topics = "springboot-topic", groupId = "springboot-group")
    public void onMessage(String message) {
        System.out.println("---收到消息: " + message + " ---");
    }
}
