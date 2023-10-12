package com.carrental.autohire.dto;

public record CarResponseDto (
                              Long id,
                              String manufacturer,
                              String model,
                              int year,
                              String color,
                              Long price,
                              Boolean isBooked){
}
