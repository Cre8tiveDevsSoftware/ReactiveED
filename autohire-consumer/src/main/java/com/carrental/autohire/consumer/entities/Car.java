package com.carrental.autohire.consumer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonRootName("Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String manufacturer;
    private String model;
    private int modelYear;
    private String color;
    private Double price;
    private Boolean isBooked;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @JsonBackReference
    public Customer getCustomer(){
        return this.customer;
    }

    @Override
    public String toString() {
        return "Car : {" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", modelYear=" + modelYear +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", isBooked=" + isBooked +
                ", customer= [" + customer.getFirstname() + " " + customer.getLastname()+"]"+
                "}";
    }
}