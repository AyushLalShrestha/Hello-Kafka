package com.als.producers;

import java.util.*;
import org.apache.kafka.clients.producer.*;

public class PartionerProducer {

    public static void main(String[] args) throws Exception {

        String topicName = "logpoint";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "LogsPartitioner");
        props.put("log.collector.name", "syslog");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>(topicName, "syslog", "500" + i));

        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>(topicName, "snare", "500" + i));

        producer.close();

        System.out.println("SimpleProducer Completed.");
    }
}
