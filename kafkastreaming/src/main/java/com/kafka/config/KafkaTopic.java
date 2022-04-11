package com.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    //crees topic
    @Bean
    public NewTopic myTopic(){
        return TopicBuilder.name("kafka_test").build();
    }
}
