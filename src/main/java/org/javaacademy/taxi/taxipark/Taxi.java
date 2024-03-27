package org.javaacademy.taxi.taxipark;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.taxi.client.Client;
import org.springframework.beans.factory.annotation.Value;
import java.math.BigDecimal;

@Slf4j
public class Taxi {
    @Value("${taxi.rate.night}")
    private int rateNight;
    @Value("${taxi.rate.day}")
    private int rateDay;
    @Getter
    private int number;
    @Getter
    BigDecimal income = BigDecimal.ZERO;
    private final TaxiPark taxiPark;

    public Taxi(TaxiPark taxiPark) {
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
        this.setIncome(revenue.multiply(new BigDecimal("0.5")));
        taxiPark.addIncome(revenue.multiply(new BigDecimal("0.5")));
        log.info("Такси доехало до адреса. Получено от клиента: {} руб., прибыль такси: {} руб., номер такси: {}",
                revenue, income, number);
    }

    private void setIncome(BigDecimal revenue) {
        income = income.add(revenue);
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
