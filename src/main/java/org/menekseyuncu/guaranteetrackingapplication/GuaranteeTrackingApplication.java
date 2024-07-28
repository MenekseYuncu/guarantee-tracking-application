package org.menekseyuncu.guaranteetrackingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main entry point for the Guarantee Tracking application.
 * <p>
 * It initializes the Spring Boot application and enables scheduling capabilities.
 */
@SpringBootApplication
@EnableScheduling
public class GuaranteeTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuaranteeTrackingApplication.class, args);
	}

}
