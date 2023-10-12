package com.carrental.autohire.consumer.controller;

import com.carrental.autohire.consumer.service.CarService;
import com.carrental.autohire.consumer.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin("*")
public class CarController {
    @Autowired
    private CarService carService;
    public CarController() {

    }
    @GetMapping(value = "/allCars",
            produces="application/json")
    public Flux<List<Car>> getAllCars() {
        List<Car> cars = carService.findAllCars();
        return Flux.just(cars);
    }
}

