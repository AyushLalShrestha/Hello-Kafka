package com.als.producers;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.kafka.clients.producer.*;
import com.als.entity.LogRecord;

public class LogsProducer {

    public static void main(String[] args) throws Exception {

        String topicName = "logpoint";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.als.serializerDeserializer.LogRecordSerializer");

        Producer<String, LogRecord> producer = new KafkaProducer<>(props);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Log Message: ");
            String message = sc.nextLine();

            Date date = new Date();
            LogRecord logRecord = new LogRecord(date.toString(), message, "54.22.31.78");

            RecordMetadata recordResult = producer.send(new ProducerRecord<String, LogRecord>(topicName, "syslog", logRecord)).get();
            System.out.println("LogRecord Produced: " + recordResult.toString());
        }
        //        producer.close();

    }
}