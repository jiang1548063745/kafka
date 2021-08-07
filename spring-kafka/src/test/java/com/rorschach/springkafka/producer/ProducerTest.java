package com.rorschach.springkafka.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProducerTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    void contextLoads() {
        long time = System.currentTimeMillis();
        System.out.println("---" + time + ", 已发出");
        kafkaProducer.send("测试消息, " + time);
    }
}
