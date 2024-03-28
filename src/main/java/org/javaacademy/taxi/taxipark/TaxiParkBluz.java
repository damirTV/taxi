package org.javaacademy.taxi.taxipark;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.taxi.client.Client;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Profile("bluz")
@Component
public class TaxiParkBluz extends TaxiPark implements ApplicationListener<ContextClosedEvent> {
    private final Queue<Taxi> taxiQueue = new LinkedList<>();

    public TaxiParkBluz(@Lazy Taxi taxi1, @Lazy Taxi taxi2) {
        super();
        this.taxiQueue.addAll(List.of(taxi1, taxi2));
    }

    @Override
    public void addIncome(BigDecimal incomeByTaxi) {
        this.setIncome(this.getIncome().add(incomeByTaxi));
    }

    @Override
    public void acceptOrder(Client client, TimeOfDay timeOfDay) {
        log.info("Таксопарк принял заказ на адрес: {}", client.getAddress());
        Taxi taxi = taxiQueue.remove();
        transferOrderToTaxi(taxi, client, timeOfDay);
        taxiQueue.add(taxi);
    }

    private void report() {
        log.info("Итог работы таксопарка. Имя таксопарка: {}, заработано: {} руб.", this.getName(), this.getIncome());
        taxiQueue.forEach(taxi -> log.info("Водитель машины №{} заработал: {} руб.", taxi.getNumber(), taxi.getIncome()));
    }

    private void transferOrderToTaxi(Taxi taxi, Client client, TimeOfDay timeOfDay) {
        try {
            taxi.acceptOrder(client, timeOfDay);
        } catch (RuntimeException e) {
            log.error("Заказ отменен. Неизвестный адрес");
        }
    }

    @Override
    public void onApplicationEvent(@NonNull ContextClosedEvent event) {
        report();
    }
}
