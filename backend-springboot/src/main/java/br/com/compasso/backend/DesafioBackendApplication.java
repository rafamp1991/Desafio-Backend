package br.com.compasso.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class DesafioBackendApplication {

	
	/**
	 * Método principal da aplicação, responsável por executar a mesma
	 * @param args
	 * @author Rafael Martins de Padua
	 */
	public static void main(String[] args) {
		SpringApplication.run(DesafioBackendApplication.class, args);
	}

}
