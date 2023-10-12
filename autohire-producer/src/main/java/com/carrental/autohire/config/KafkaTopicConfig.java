package com.carrental.autohire.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Admin Client to create Kafka Topics
 */

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    @Value(value = "${customer.topic.name}")
    private String customerTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    @Bean
    public NewTopic greetingTopic() {
        return new NewTopic(greetingTopicName, 3, (short) 1);
    }
    @Bean
    public NewTopic customerTopic() {
        return new NewTopic(customerTopicName, 1, (short) 1);
    }

}
