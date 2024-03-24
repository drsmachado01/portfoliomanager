package br.com.darlan.portfoliomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.darlan.portfoliomanager")
public class PortfoliomanagerApplication  {

	public static void main(String[] args) {
		SpringApplication.run(PortfoliomanagerApplication.class, args);
	}

}
