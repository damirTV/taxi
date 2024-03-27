package org.javaacademy.taxi.taxipark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaxiConfig {
    @Bean
    public Taxi taxi1(TaxiPark taxiPark) {
        return new Taxi(taxiPark);
    }

    @Bean
    public Taxi taxi2(TaxiPark taxiPark) {
        return new Taxi(taxiPark);
    }

    @Bean
    public Taxi taxi3(TaxiPark taxiPark) {
        return new Taxi(taxiPark);
    }
}
