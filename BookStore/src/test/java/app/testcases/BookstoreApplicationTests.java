package app.testcases;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import app.controller.BookController;

@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookController controller;

	@Test
	void contextLoads() throws Exception {
			assertThat(controller).isNotNull();
	}

}
