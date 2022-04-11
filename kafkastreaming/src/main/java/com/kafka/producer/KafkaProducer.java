package com.kafka.producer;


import com.kafka.data.Employee;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    public void sendDataToKafka(KafkaTemplate<String, Employee> kafkaTemplate){
    kafkaTemplate.send("kafka_test", new Employee("Debo", "1234"));
    }
}
