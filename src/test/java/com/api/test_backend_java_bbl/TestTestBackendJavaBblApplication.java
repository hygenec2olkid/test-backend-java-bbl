package com.api.test_backend_java_bbl;

import org.springframework.boot.SpringApplication;

public class TestTestBackendJavaBblApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestBackendJavaBblApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
