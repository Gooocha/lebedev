package com.lebzlon.measurementtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MeasurementTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeasurementTrackerApplication.class, args);
    }
}
