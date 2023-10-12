package com.carrental.autohire.dto;

public record CarRequestDto (String manufacturer,
                             String model,
                             int year,
                             String color,
                             Long price,
                             Boolean isBooked){
}
