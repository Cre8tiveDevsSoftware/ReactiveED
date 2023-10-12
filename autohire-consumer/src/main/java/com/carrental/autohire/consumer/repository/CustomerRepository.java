package com.carrental.autohire.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carrental.autohire.consumer.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
