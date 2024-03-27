package org.javaacademy.taxi.client;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public Client createClient(@NonNull String address) {
        return new Client(address);
    }
}
