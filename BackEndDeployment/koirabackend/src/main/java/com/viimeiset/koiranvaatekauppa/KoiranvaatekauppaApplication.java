package com.viimeiset.koiranvaatekauppa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viimeiset.koiranvaatekauppa.domain.TuoteRepository;
import com.viimeiset.koiranvaatekauppa.domain.AppUser;
import com.viimeiset.koiranvaatekauppa.domain.AppUserRepository;
import com.viimeiset.koiranvaatekauppa.domain.ValmistajaRepository;
import com.viimeiset.koiranvaatekauppa.domain.Tuote;
import com.viimeiset.koiranvaatekauppa.domain.Valmistaja;
import com.viimeiset.koiranvaatekauppa.domain.Tyyppi;
import com.viimeiset.koiranvaatekauppa.domain.Koko;

@SpringBootApplication
public class KoiranvaatekauppaApplication {

	private static final Logger log = LoggerFactory.getLogger(KoiranvaatekauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KoiranvaatekauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(TuoteRepository tuoteRepository, ValmistajaRepository valmistajaRepository,
			AppUserRepository uRepository) {
		return (args) -> {
			log.info("Tallennetaan tuote ja valmistajatiedot");

			//Valmistaja valmistaja1 = new Valmistaja("Northface");
			//Valmistaja valmistaja2 = new Valmistaja("Adidas");
			//Valmistaja valmistaja3 = new Valmistaja("Puma");
			//valmistajaRepository.saveAll(Arrays.asList(valmistaja1, valmistaja2, valmistaja3));

			//Tuote tuote1 = new Tuote("Takki", Tyyppi.VAATE, "Musta", Koko.M, 29.99, valmistaja1, 10);
			//Tuote tuote2 = new Tuote("Rukkanen", Tyyppi.VAATE, "Sininen", Koko.L, 59.99, valmistaja2, 2);
			//Tuote tuote3 = new Tuote("Koiranruoka", Tyyppi.RUOKA, "Punainen", Koko.S, 14.99, valmistaja3, 20);
			//Tuote tuote4 = new Tuote("Koiralelu", Tyyppi.LELU, "Punainen", Koko.L, 39.99, valmistaja1, 3);
			//Tuote tuote5 = new Tuote("Hattu", Tyyppi.VAATE, "Vihreä", Koko.S, 89.99, valmistaja2, 10);
			//Tuote tuote6 = new Tuote("Pipo", Tyyppi.VAATE, "Musta", Koko.S, 9.99, valmistaja3, 100);
			//tuoteRepository.saveAll(Arrays.asList(tuote1, tuote2, tuote3, tuote4, tuote5,
					//tuote6));

			//AppUser user1 = new AppUser("Uuser", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					//"ROLE_USER", null, null, null);
			//AppUser user2 = new AppUser("Aadmin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					//"ROLE_ADMIN", null, null, null);
			//AppUser asiakas = new AppUser("Aasiakas", "$2y$10$byFwZxRhpVtCUs2abOdwdeAw8vwgsqNmRMqVzPZu/0P/i7rrB9Cba",
					//"USER", "Aasi", "Asiakas", "Asiakas@asiakas.com");
			//AppUser user1 = new AppUser("User", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
			//"USER", null, null, null);
			//uRepository.save(user1);
			//uRepository.save(user2);
			//uRepository.save(asiakas);

			log.info("Lisätään kaikki data.");
			for (Tuote tuote : tuoteRepository.findAll()) {
				log.info(tuote.toString());
			}
		};

	}

}