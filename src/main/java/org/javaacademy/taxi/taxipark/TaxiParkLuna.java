package org.javaacademy.taxi.taxipark;

import lombok.extern.slf4j.Slf4j;
import org.javaacademy.taxi.Client;
import org.javaacademy.taxi.TimeOfDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Profile("luna")
@Component
public class TaxiParkLuna extends TaxiPark {
    @Value("${taxipark.name}")
    private String name;
    private BigDecimal income = BigDecimal.ZERO;
    private final Taxi taxi1;
    private final Taxi taxi2;
    private final Taxi taxi3;
    private Queue<Taxi> taxiQueue = new LinkedList<>();

    public TaxiParkLuna(Taxi taxi1, Taxi taxi2, Taxi taxi3) {
        this.taxi1 = taxi1;
        this.taxi2 = taxi2;
        this.taxi3 = taxi3;
        this.taxiQueue.addAll(List.of(taxi1, taxi2, taxi3));
        }

    @Override
    public void setIncome(BigDecimal incomeByTaxi) {
        income = income.add(incomeByTaxi);
    }

    @Override
    public void acceptOrder(Client client, TimeOfDay timeOfDay) {
        log.info("Таксопарк принял заказ на адрес: {}", client.getAddress());
        Taxi taxi = taxiQueue.remove();
        transferOrderToTaxi(taxi, client, timeOfDay);
        taxiQueue.add(taxi);
    }

    public void report() {
        log.info("Итог работы таксопарка. Имя таксопарка: {}, заработано: {} руб.", name, income);
        taxiQueue.forEach(taxi -> log.info("Водитель машины №{} заработал: {} руб.", taxi.getNumber(), taxi.getIncome()));
    }

    private void transferOrderToTaxi(Taxi taxi, Client client, TimeOfDay timeOfDay) {
        try {
            taxi.acceptOrder(client, timeOfDay);
        } catch (RuntimeException e) {
            log.error("Заказ отменен. Неизвестный адрес");
        }
    }
}
