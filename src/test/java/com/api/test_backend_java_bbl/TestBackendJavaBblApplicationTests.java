package com.api.test_backend_java_bbl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TestBackendJavaBblApplicationTests {

	@Test
	void contextLoads() {
	}

}
