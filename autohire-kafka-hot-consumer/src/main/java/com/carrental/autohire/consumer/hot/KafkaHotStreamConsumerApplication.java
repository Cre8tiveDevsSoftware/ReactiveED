package com.carrental.autohire.consumer.hot;

import com.carrental.autohire.consumer.listener.KafkaHotStreamConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Properties;

@SpringBootApplication
public class KafkaHotStreamConsumerApplication  implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaHotStreamConsumerApplication.class);
    @Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;
	@Value(value = "${greeting.topic.name}")
	private String greetingTopicName;
	@Value(value = "${greeting.group.id}")
	private String greetingGroupId;

	@Value(value = "${spring.kafka.consumer.value-deserializer}")
	private String valueDeserializer;

	@Value(value ="${spring.kafka.consumer.key-deserializer}" )
	private String keyDeserializer;
    public static void main(String[] args) {
		SpringApplication.run(KafkaHotStreamConsumerApplication.class, args);
	}
	@Override
    public void run(String ... args){
		LOGGER.info("Inside CommandLineRunner");
		/*
		 * Defining Kafka consumer properties.
		 */
		Properties consumerProperties = new Properties();
		consumerProperties.put("bootstrap.servers", bootstrapAddress);
		consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valueDeserializer );
		consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,keyDeserializer );
		consumerProperties.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
		/*
		 * Creating a thread to listen to the kafka topic
		 */
		Thread kafkaHotConsumerThread = new Thread(() -> {
			LOGGER.info("Starting Kafka Hot consumer thread.");
			KafkaHotStreamConsumer simpleKafkaHotConsumer = new KafkaHotStreamConsumer(greetingTopicName,consumerProperties);
			simpleKafkaHotConsumer.runSingleWorker();
		});

		/*
		 * Starting the Hot consumer.
		 */
		kafkaHotConsumerThread.start();
	}
}
