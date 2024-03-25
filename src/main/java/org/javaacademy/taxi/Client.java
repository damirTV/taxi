package org.javaacademy.taxi;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Client {
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }
}
