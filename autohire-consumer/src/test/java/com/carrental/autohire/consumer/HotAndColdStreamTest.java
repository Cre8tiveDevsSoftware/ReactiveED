package com.carrental.autohire.consumer;

import com.carrental.autohire.consumer.controller.CustomerController;
import com.carrental.autohire.consumer.entities.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomerController.class)
public class HotAndColdStreamTest {

    @MockBean
    private CustomerController customerController;
    @BeforeEach
    void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customers = null;
        try {
            customers = objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("data/customers.json"), new TypeReference<List<Customer>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        when(customerController.getAllCustomers())
                .thenReturn(Flux.fromIterable(customers));
    }

    /**
     * Cold Stream where all the data from the begining will be presented.
     *
     */
    @Test
    public void coldStreamTest(){
        Flux<Customer> coldStream = customerController.getAllCustomers();
        coldStream.subscribe(c -> System.out.println("Subscriber 1 :: "+ c.toString()));
        coldStream.subscribe(c -> System.out.println("Subscriber 2 :: "+ c.toString()));
    }

    @Test
    public void hotStreamTest() throws InterruptedException {
        Flux<Customer> hotStream = customerController.getAllCustomers();
        ConnectableFlux<Customer> connectable = hotStream
                .delayElements(Duration.ofMillis(10))
                .publish();
        connectable.connect();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        connectable.subscribe(data -> System.out.println("Subscriber 1: "+data));
        Thread.sleep(500);
        System.out.println();
        System.out.println("subscribing 2");
        connectable.subscribe(data -> System.out.println("Subscriber 2: "+data));
        Thread.sleep(100);
        System.out.println();
        System.out.println("subscribing 3");
        connectable.subscribe(data -> System.out.println("Subscriber 3: "+data));
        Thread.sleep(5000);
    }
}
