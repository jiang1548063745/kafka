package com.rorschach.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * 消费者
 */
public class SimpleConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", "rorschach-group");

        // 是否自动提交偏移量 只有 commit 以后才更新消费组的 offset
        properties.put("enable.auto.commit", "true");

        // 消费者自动提交的间隔
        properties.put("auto.commit.intervals.ms", "1000");

        // 从最早的数据开始消费 earliest | latest | none
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer= new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList("my_topic"));

        try {
            while (true){
                ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String,String> record: records){
                    System.out.printf("offset = %d ,key =%s, value= %s, partition= %s%n" ,
                            record.offset(),record.key(),record.value(),record.partition());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
