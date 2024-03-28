package org.javaacademy.taxi.taxipark;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("bluz")
@Component
public class TaxiParkBluz extends TaxiPark {

    public TaxiParkBluz(@Lazy Taxi taxi1, @Lazy Taxi taxi2) {
        setTaxiQueue(taxi1);
        setTaxiQueue(taxi2);
    }
}
