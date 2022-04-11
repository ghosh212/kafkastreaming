package com.kafka.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.kafka.data.Employee;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstarp-servers}")
    private String bootstrap;

    //map which would contain the configuration, which would b e passed to a producer factory
    public Map<String, Object> consumerConfig(){
        HashMap<String,Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }

    //producer factory
    //will create producer instances
    @Bean
    public ConsumerFactory<String, Employee> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    //kafka listener factory
    //consumer
    //listens for data on particular topic
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Employee>> factory(){
        ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
