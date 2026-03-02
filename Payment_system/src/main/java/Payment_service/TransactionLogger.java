package Payment_service;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class TransactionLogger {

	@PostConstruct
	public void postConstruct() {
		System.out.println("Logger initialized");
	}
	
	@PreDestroy
	public void predestroy() {
		System.out.println("Logger destroyed");
	}
}
