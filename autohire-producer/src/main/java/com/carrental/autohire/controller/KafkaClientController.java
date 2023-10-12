package com.carrental.autohire.controller;

import com.carrental.autohire.data.Greeting;
import com.carrental.autohire.dto.CustomerRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/producer")
@CrossOrigin("*")
public class KafkaClientController {
    @Autowired
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, CustomerRequestDTO> customerKafkaTemplate;

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    @Value(value = "${customer.topic.name}")
    private String customerTopicName;


    @GetMapping(value = "sayHello/{customerName}")
    public Mono<ResponseEntity<String>> sayHello(@PathVariable String customerName){
        Greeting greetingMessage = new Greeting(customerName,"Hello "+ customerName +"!!!!!");
        try {
            CompletableFuture<SendResult<String, Greeting>> future = greetingKafkaTemplate.send(greetingTopicName, greetingMessage);
            long offset = future.get().getRecordMetadata().offset();
            ResponseEntity<String> responseEntity = new ResponseEntity<String>("Sent message=[" + greetingMessage.getMsg() + "] with offset=[" + offset + "]", HttpStatus.OK);

            return Mono.just(responseEntity);
        }catch (Exception e){
            e.printStackTrace();
            return Mono.just(new ResponseEntity<String>("Sent message=["+e.getMessage()+"]", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping(value = "/create/{firstname}/{lastname}/{email}", produces = "application/json")
    public Mono<ResponseEntity<Object>> insertCustomer(@PathVariable String firstname, @PathVariable String lastname,@PathVariable String email){
       CustomerRequestDTO customer = new CustomerRequestDTO(firstname,lastname,email);
       ResponseEntity<Object> responseEntity = null;
        try {
            CompletableFuture<SendResult<String, CustomerRequestDTO>> future = customerKafkaTemplate.send(customerTopicName, customer);
            long offset = future.get().getRecordMetadata().offset();
            responseEntity = new ResponseEntity<Object>(customer, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<Object>(getErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Mono.just(responseEntity);
    }
    private Map<String,Object> getErrorResponse(String errorMessage){
        Map<String,Object> response = new HashMap<>();
        response.put("errroMessage",errorMessage);
        return response;
    }
}

