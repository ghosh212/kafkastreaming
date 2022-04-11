package com.kafka.consumer;

import com.kafka.data.Employee;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaLConsumer {

    //we need group id to read from same partition from a cluster
    @KafkaListener(topics = "kafka_test", groupId = "foo")
    void Listner(Employee emp){
System.out.println(emp.getEmpName() + emp.getEmpID());
    }
}
