package org.javaacademy.taxi.taxipark;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.taxi.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Getter
public abstract class TaxiPark implements ApplicationListener<ContextClosedEvent> {
    @Value("${taxipark.name}")
    private String name;
    @Setter
    private BigDecimal income = BigDecimal.ZERO;
    protected Queue<Taxi> taxiQueue = new LinkedList<>();

    public void acceptOrder(Client client, TimeOfDay timeOfDay) {
        log.info("Таксопарк принял заказ на адрес: {}", client.getAddress());
        Taxi taxi = taxiQueue.remove();
        transferOrderToTaxi(taxi, client, timeOfDay);
        taxiQueue.add(taxi);
    }

    private void transferOrderToTaxi(Taxi taxi, Client client, TimeOfDay timeOfDay) {
        try {
            taxi.acceptOrder(client, timeOfDay);
        } catch (RuntimeException e) {
            log.error("Заказ отменен. Неизвестный адрес");
        }
    }

    public void addIncome(BigDecimal incomeByTaxi) {
        this.setIncome(this.getIncome().add(incomeByTaxi));
    }

    public void setTaxiQueue(Taxi taxi) {
        this.taxiQueue.add(taxi);
    }

    private void report() {
        log.info("Итог работы таксопарка. Имя таксопарка: {}, заработано: {} руб.", this.getName(), this.getIncome());
        taxiQueue.forEach(taxi -> log.info("Водитель машины №{} заработал: {} руб.", taxi.getNumber(), taxi.getIncome()));
    }

    public void onApplicationEvent(@NonNull ContextClosedEvent event) {
        report();
    }
}
