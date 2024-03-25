package org.javaacademy.taxi.taxipark;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.taxi.Client;
import org.javaacademy.taxi.TimeOfDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Slf4j
@Profile("luna")
@Component
public class TaxiParkLuna extends TaxiPark {
    @Value("${taxipark.name}")
    private String name;
    private BigDecimal income = BigDecimal.ZERO;
    private final Taxi taxi;

    public TaxiParkLuna(@Lazy Taxi taxi) {
        this.taxi = taxi;
    }

    @Override
    public void setIncome(BigDecimal incomeByTaxi) {
        income = income.add(incomeByTaxi);
    }

    @Override
    public void acceptOrder(Client client, TimeOfDay timeOfDay) {
        taxi.acceptOrder(client, timeOfDay);
    }

    public void report() {
        log.info("Итог работы таксопарка:\nИмя таксопарка:" +
                " {}\nЗаработано: {}\nВодитель машины {} заработал: {}", name, income, taxi.getNumber(), taxi.getIncome());
    }
}
