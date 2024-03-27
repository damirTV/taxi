package org.javaacademy.taxi;

import org.javaacademy.taxi.client.Client;
import org.javaacademy.taxi.client.ClientService;
import org.javaacademy.taxi.taxipark.TimeOfDay;
import org.javaacademy.taxi.taxipark.TaxiPark;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Runner {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Runner.class, args);
		ClientService clientService = context.getBean(ClientService.class);

		Client client1 = clientService.createClient("Кандикюля");
		Client client2 = clientService.createClient("Строитель");
		Client client3 = clientService.createClient("Березовая роща");
		Client client4 = clientService.createClient("Ломоносов");

		TaxiPark taxiPark = context.getBean(TaxiPark.class);

		taxiPark.acceptOrder(client1, TimeOfDay.DAY);
		taxiPark.acceptOrder(client2, TimeOfDay.DAY);
		taxiPark.acceptOrder(client3, TimeOfDay.NIGHT);
		taxiPark.acceptOrder(client4, TimeOfDay.NIGHT);
	}
}
