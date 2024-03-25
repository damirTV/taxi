package org.javaacademy.taxi.taxipark;

import org.javaacademy.taxi.Client;
import org.javaacademy.taxi.TimeOfDay;
import java.math.BigDecimal;

public abstract class TaxiPark {

    public abstract void setIncome(BigDecimal incomeByTaxi);
    public abstract void acceptOrder(Client client, TimeOfDay timeOfDay);
}
