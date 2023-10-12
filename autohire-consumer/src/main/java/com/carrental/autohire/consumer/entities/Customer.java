package com.carrental.autohire.consumer.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@JsonRootName("Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Car> cars;
    @JsonManagedReference
    public List<Car> getCars(){
        return this.cars;
    }
}