package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import app.model.Category;
import app.model.CategoryRepository;
import app.model.Book;
import app.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository drepository) {
		return (args) -> {
			log.info("save books");
			drepository.save(new Category("Science"));
			drepository.save(new Category("Fiction"));
			drepository.save(new Category("Null"));
			
			repository.save(new Book("Book X", "XXXXXXX", "9999", "127379764628974", "99€", drepository.findByName("Science").get(0)));
			repository.save(new Book("Book Y", "YYYYYYY", "9999", "8374648329744683", "89€", drepository.findByName("Fiction").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}}
