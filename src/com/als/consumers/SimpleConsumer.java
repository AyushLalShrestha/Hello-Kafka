//package com.als.consumers;

import java.util.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SimpleConsumer {

    public static void main(String[] args) throws Exception {

        String topicName = "logpoint";
        String groupName = "consumerGroup-1";

        KafkaConsumer<String, String> consumer = null;

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");

        consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topicName));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Topic:" + record.topic() +
                            " Partition:" + record.partition() +
                            " Offset:" + record.offset() +
                            " Key:" + record.key() +
                            " Value:" + record.value());
                    // Do something like save it to Database or some other processing
                }
                // consumer.commitSync(rebalanceListner.getCurrentOffsets());
                System.out.println("Waiting before commit for 20 seconds");
                Thread.sleep(20000);
                System.out.println("Committing now, then reading");
                consumer.commitAsync();
            }
        } catch (Exception ex) {
            System.out.println("Exception.");
            ex.printStackTrace();
        } finally {
            consumer.close();
        }

    }
}
