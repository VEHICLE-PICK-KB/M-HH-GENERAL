package app.testcases;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import app.controller.FlightController;

@SpringBootTest
class DeparturesApplicationTests {
	@Autowired
	private FlightController controller;

	@Test
	void contextLoads() throws Exception {
			assertThat(controller).isNotNull();
	}

}
