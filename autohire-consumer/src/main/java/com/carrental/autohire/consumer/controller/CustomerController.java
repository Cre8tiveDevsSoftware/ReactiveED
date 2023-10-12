package com.carrental.autohire.consumer.controller;

import com.carrental.autohire.consumer.service.CustomerService;
import com.carrental.autohire.consumer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    public CustomerController() {

    }

    @GetMapping(value = "/allCustomers",
            produces="application/json")
    public Flux<Customer> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return Flux.fromIterable(customers);
    }
}

