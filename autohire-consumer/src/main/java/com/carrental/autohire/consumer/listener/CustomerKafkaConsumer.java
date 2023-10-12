package com.carrental.autohire.consumer.listener;

import com.carrental.autohire.dto.CustomerRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class CustomerKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerKafkaConsumer.class);
    private CountDownLatch latch = new CountDownLatch(1);

    private String payload;
    @KafkaListener(topics = "${customer.topic.name}", containerFactory = "customerKafkaListenerContainerFactory")
    public void receive(CustomerRequestDTO customer) {
        LOGGER.info("received payload='{}'", customer.toString());
        payload = customer.toString();
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
