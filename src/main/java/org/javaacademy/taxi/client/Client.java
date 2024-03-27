package org.javaacademy.taxi.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Client {
    @NonNull
    private String address;
}
