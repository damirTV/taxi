package org.javaacademy.taxi.taxipark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaxiConfig {
    @Bean
    public Taxi taxi1() {
        return new Taxi();
    }

    @Bean
    public Taxi taxi2() {
        return new Taxi();
    }

    @Bean
    public Taxi taxi3() {
        return new Taxi();
    }
}
