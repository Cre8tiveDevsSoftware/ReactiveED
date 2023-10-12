package com.carrental.autohire.consumer.listener;

import com.carrental.autohire.data.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class GreetingKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingKafkaConsumer.class);
    private CountDownLatch latch = new CountDownLatch(1);
    @Value(value = "${greeting.group.id}")
    private String greetingGroupId;
    private String payload;

    @KafkaListener(topics = "${greeting.topic.name}",groupId = "${greeting.group.id}", containerFactory = "greetingKafkaListenerContainerFactory")
    public void receive(Greeting greeting) {
        LOGGER.info("received payload='{}'", greeting.toString());
        payload = greeting.toString();
        latch.countDown();
    }
    public CountDownLatch getLatch() {
        return latch;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public String getPayload() {
        return payload;
    }
}
