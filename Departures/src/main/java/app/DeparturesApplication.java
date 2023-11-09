package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import app.model.Flight;
import app.model.FlightRepository;
import app.model.Type;
import app.model.TypeRepository;
import app.model.AppUser;
import app.model.AppUserRepository;



@SpringBootApplication
public class DeparturesApplication {
	
	private static final Logger log = LoggerFactory.getLogger(DeparturesApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DeparturesApplication.class, args);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	@Bean
	public CommandLineRunner flightTest(FlightRepository repository, TypeRepository trepository, AppUserRepository urepository, PasswordEncoder passwordEncoder) {
		return (args) -> {
			log.info("Refresh flights");
			trepository.save(new Type("Commercial"));
			trepository.save(new Type("VIP"));
			trepository.save(new Type("Private"));
			trepository.save(new Type("Freight"));
			trepository.save(new Type("Military"));
				
			AppUser user1 = new AppUser("Customer", passwordEncoder.encode("customer"), "USER");
			AppUser user2 = new AppUser("Admin", passwordEncoder.encode("admin"), "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all flights");
			for (Flight flight : repository.findAll()) {
				log.info(flight.toString());
			}

		};
	}}
