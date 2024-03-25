package org.javaacademy.taxi;

import lombok.Getter;

@Getter
public enum Address {
    LOCATION1 ("Березовая роща", 10),
    LOCATION2 ("Кандикюля", 4),
    LOCATION3 ("Строитель", 12);

    private String address;
    private int km;

    Address(String address, int km) {
        this.address = address;
        this.km = km;
    }
}
