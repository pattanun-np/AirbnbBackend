package org.armzbot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}