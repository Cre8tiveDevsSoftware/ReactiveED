package com.carrental.autohire.controller;

import com.carrental.autohire.data.Greeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class CustomerControllerTest {

    @MockBean
    private KafkaClientController customerController;
    @MockBean
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;
    
    @BeforeEach
    void init() {
        when(customerController.sayHello(Mockito.anyString()))
                .thenReturn(Mono.just(new ResponseEntity<String>("Sent message=[Hello "+Mockito.anyString()+"] with offset=[" + 1 + "]", HttpStatus.OK)));
    }
    @Test
    void whenCallSayHello_VerifyResponse(){
        StepVerifier.withVirtualTime(()->customerController.sayHello(""))
                .consumeNextWith(r -> {
                    System.out.println(r.getBody());
                    assertEquals(r.getStatusCode(), HttpStatus.OK);
                })
                .verifyComplete();
    }
}
