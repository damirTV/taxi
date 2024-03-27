package org.javaacademy.taxi;

import org.javaacademy.taxi.taxipark.TaxiParkLuna;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Runner {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Runner.class, args);

		Client client1 = new Client();
		client1.setAddress("Кандикюля");

		Client client2 = new Client();
		client2.setAddress("Строитель");

		Client client3 = new Client();
		client3.setAddress("Березовая роща");

		Client client4 = new Client();
		client4.setAddress("Ломоносов");

		context.getBean(TaxiParkLuna.class).acceptOrder(client1, TimeOfDay.DAY);
		context.getBean(TaxiParkLuna.class).acceptOrder(client2, TimeOfDay.DAY);
		context.getBean(TaxiParkLuna.class).acceptOrder(client3, TimeOfDay.NIGHT);
		context.getBean(TaxiParkLuna.class).acceptOrder(client4, TimeOfDay.NIGHT);
		context.getBean(TaxiParkLuna.class).report();
	}
}
