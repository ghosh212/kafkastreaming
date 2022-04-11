package com.kafka.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.kafka.data.Employee;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstarp-servers}")
    private String bootstrap;

    //map which would contain the configuration, which would b e passed to a producer factory
    public Map<String, Object> producerConfig(){
        HashMap<String,Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }

    //producer factory
    //will create producer instances
    @Bean
    public ProducerFactory<String, Employee> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    //kafka template
    public KafkaTemplate<String, Employee> kafkaTemplate(ProducerFactory<String, Employee> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
