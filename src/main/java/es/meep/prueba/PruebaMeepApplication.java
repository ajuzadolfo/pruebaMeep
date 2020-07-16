package es.meep.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PruebaMeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaMeepApplication.class, args);
	}

}
