package org.javaacademy.taxi.taxipark;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;

@Profile("luna")
@Component
public class TaxiParkLuna extends TaxiPark {

    public TaxiParkLuna(@Lazy Taxi taxi1, @Lazy Taxi taxi2, @Lazy Taxi taxi3) {
        setTaxiQueue(List.of(taxi1, taxi2, taxi3));
        }
}
