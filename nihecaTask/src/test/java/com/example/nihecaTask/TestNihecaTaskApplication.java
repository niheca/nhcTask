package com.example.nihecaTask;

import org.springframework.boot.SpringApplication;

public class TestNihecaTaskApplication {

	public static void main(String[] args) {
		SpringApplication.from(NihecaTaskApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
