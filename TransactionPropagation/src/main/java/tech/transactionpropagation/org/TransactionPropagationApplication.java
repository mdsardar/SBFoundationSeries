package tech.transactionpropagation.org;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TransactionPropagationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionPropagationApplication.class, args);
	}

}

