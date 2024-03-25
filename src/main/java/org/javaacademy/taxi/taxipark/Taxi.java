package org.javaacademy.taxi.taxipark;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.taxi.Client;
import org.javaacademy.taxi.TimeOfDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Slf4j
@Component
public class Taxi {
    @Value("${taxi.rate.night}")
    private int rateNight;
    @Value("${taxi.rate.day}")
    private int rateDay;
    @Getter
    private int number;
    @Getter
    BigDecimal income;
    private Client client;
    private TaxiPark taxiPark;

    public Taxi(Client client, TaxiPark taxiPark) {
        this.client = client;
        this.taxiPark = taxiPark;
        this.number = generateNumber();
    }

    public void acceptOrder(Client client, TimeOfDay timeOfDay) {
        int km = tarrifs(client.getAddress());
        int rateCurrent;
        BigDecimal revenue;
        if (timeOfDay == TimeOfDay.DAY) {
            rateCurrent = rateDay;
        } else {
            rateCurrent = rateNight;
        }
        revenue = new BigDecimal(km * rateCurrent);
        income = revenue.multiply(new BigDecimal("0.5"));
        taxiPark.setIncome(revenue.multiply(new BigDecimal("0.5")));
        log.info("Доход: {}", revenue);
        log.info("Прибыль: {}", income);
        log.info("Номер такси: {}", number);
    }

    private int tarrifs(String address) {
        if (address.equals("Березовая роща")) {
            return 10;
        }
        if (address.equals("Кандикюля")) {
            return 4;
        }
        if (address.equals("Строитель")) {
            return 12;
        }
        throw new RuntimeException("Слышь, братан, дорогу покажешь?");
    }

    private int generateNumber() {
        return (int) (Math.random() * 100);
    }
}
