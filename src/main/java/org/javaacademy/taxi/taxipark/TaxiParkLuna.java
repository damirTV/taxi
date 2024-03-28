package org.javaacademy.taxi.taxipark;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("luna")
@Component
public class TaxiParkLuna extends TaxiPark {

    public TaxiParkLuna(@Lazy Taxi taxi1, @Lazy Taxi taxi2, @Lazy Taxi taxi3) {
        setTaxiQueue(taxi1);
        setTaxiQueue(taxi2);
        setTaxiQueue(taxi3);
        }
}
