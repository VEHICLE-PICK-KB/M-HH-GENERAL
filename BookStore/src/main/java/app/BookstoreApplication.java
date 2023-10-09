package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import app.model.Category;
import app.model.CategoryRepository;
import app.model.AppUser;
import app.model.AppUserRepository;
import app.model.Book;
import app.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository drepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save books");
			drepository.save(new Category("Science"));
			drepository.save(new Category("Fiction"));
			drepository.save(new Category("Null"));
			
			repository.save(new Book("Book X", "XXXXXXX", "2023", "0875678428485", "1000€", drepository.findByName("Science").get(0)));
			repository.save(new Book("Book Y", "YYYYYYY", "2018", "0954897532357", "1300€", drepository.findByName("Fiction").get(0)));	
			
			
						AppUser user1 = new AppUser("DefaultUser", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
						AppUser user2 = new AppUser("Admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
						urepository.save(user1);
						urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}}
