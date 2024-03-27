package org.javaacademy.taxi.taxipark;

import lombok.Getter;
import lombok.Setter;
import org.javaacademy.taxi.client.Client;
import org.springframework.beans.factory.annotation.Value;
import java.math.BigDecimal;

@Getter
public abstract class TaxiPark {
    @Value("${taxipark.name}")
    private String name;
    @Setter
    private BigDecimal income = BigDecimal.ZERO;

    public abstract void addIncome(BigDecimal incomeByTaxi);
    public abstract void acceptOrder(Client client, TimeOfDay timeOfDay);
}
