package com.carrental.autohire.consumer.listener;

import com.carrental.autohire.data.Greeting;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaHotStreamConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaHotStreamConsumer.class);
    private KafkaConsumer<String, Greeting> kafkaConsumer;

    public KafkaHotStreamConsumer(String greetingTopicName,Properties consumerProperties) {
        consumerProperties.put("group.id", greetingTopicName +"hot");
        kafkaConsumer = new KafkaConsumer<>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList(greetingTopicName));
    }
    /**
     * This function will start a single worker thread per topic.
     * After creating the consumer object, we subscribed to a list of Kafka topics, in the constructor.
     * For this example, the list consists of only one topic. But you can give it a try with multiple topics.
     */
    public void runSingleWorker() {
        /*
         * We will start an infinite while loop, inside which we'll be listening to
         * new messages in each topic that we've subscribed to.
         */
        while(true) {
            ConsumerRecords<String, Greeting> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, Greeting> record : records) {
                /*
                Whenever there's a new message in the Kafka topic, we'll get the message in this loop, as
                the record object.
                 */
                /*
                Getting the message as a string from the record object.
                 */
                Greeting message = record.value();

                /*
                Logging the received message to the console.
                 */
                LOGGER.info("KafkaHotStreamConsumer :: Received message: {}, offset = {} key ={} serializedSize = {}",  message,record.offset(),record.key(),record.serializedValueSize());

                /*
                Once we finish processing a Kafka message, we have to commit the offset so that
                we don't end up consuming the same message endlessly. By default, the consumer object takes
                care of this. But to demonstrate how it can be done, we have turned this default behaviour off,
                instead, we're going to manually commit the offsets.
                The code for this is below. It's pretty much self explanatory.

                {
                    Map<TopicPartition, OffsetAndMetadata> commitMessage = new HashMap<>();
                    commitMessage.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1));
                    kafkaConsumer.commitSync(commitMessage);
                    LOGGER.info("KafkaHotStreamConsumer :: Offset committed to Kafka.");
                }*/
            }
        }
    }
}
