package edu.projeto.cliente_servidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ClienteServidorApplication {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		System.out.println("Digite o número da porta: ");
		var port = scanner.nextLine();

		if (!port.matches("\\d+")) {
			System.out.println("Porta inválida");
			port = "8080";
		}

		System.setProperty("server.port", port);

		SpringApplication.run(ClienteServidorApplication.class, args);
	}

}
