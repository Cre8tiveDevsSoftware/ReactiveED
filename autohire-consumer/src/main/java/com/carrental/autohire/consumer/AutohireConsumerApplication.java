package com.carrental.autohire.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.carrental.autohire.consumer.repository"})
@ComponentScan(basePackages = { "com.carrental.autohire.consumer","com.carrental.autohire.consumer.repository" })
@EntityScan(basePackages = {"com.carrental.autohire.consumer.entities"})
public class AutohireConsumerApplication{
	public static void main(String[] args) {
		SpringApplication.run(AutohireConsumerApplication.class, args);
	}

}
