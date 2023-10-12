package com.carrental.autohire.consumer.repository;

import com.carrental.autohire.consumer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository  extends JpaRepository<Car,Integer> {
}
