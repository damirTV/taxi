package org.javaacademy.taxi.taxipark;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("bluz")
@Component
public class TaxiParkBluz extends TaxiPark {

    public TaxiParkBluz(@Lazy Taxi taxi1, @Lazy Taxi taxi2) {
        setTaxiQueue(List.of(taxi1, taxi2));
    }
}
