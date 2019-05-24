package com.als.consumers;

import java.util.*;

import com.als.entity.LogRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class LogConsumer {

    public static void main(String[] args) throws Exception {

        String topicName = "logpoint";
        String groupName = "consumerGroup-1";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.als.serializerDeserializer.LogRecordDeserializer");


        KafkaConsumer<String, LogRecord> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, LogRecord> records = consumer.poll(100);
            for (ConsumerRecord<String, LogRecord> record : records) {
                System.out.println("LogRecord= " + String.valueOf(record.value().getMsg()));
            }
        }

    }
}
