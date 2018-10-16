package com.als.producers;

import java.util.*;
import org.apache.kafka.clients.producer.*;
public class SimpleProducer {

    public static void main(String[] args) throws Exception{

        String topicName = "testtopic";
        String key = "Key1";
        String value = "Value-1";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer <>(props);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter next Message : ");
            String message = sc.nextLine();
            if(message.equalsIgnoreCase("exit")){
                break;
            }
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
            producer.send(record);
        }
        producer.close();

        System.out.println("SimpleProducer Completed.");
    }
}