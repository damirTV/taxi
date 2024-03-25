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
		client1.setAddress("Строитель");
		context.getBean(TaxiParkLuna.class).acceptOrder(client1, TimeOfDay.DAY);
		context.getBean(TaxiParkLuna.class).report();
	}
}
