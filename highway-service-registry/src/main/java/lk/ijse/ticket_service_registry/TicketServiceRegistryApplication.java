package lk.ijse.ticket_service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TicketServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceRegistryApplication.class, args);
	}

}
