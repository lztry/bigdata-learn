package com.lz.bigdata.kafka.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.IOException;
import java.util.Properties;

public class KafkaProducerTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("config/kafka.properties"));
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test", "kafka", "first");
        while (true) {
            producer.send(producerRecord);
        }
    }
}

