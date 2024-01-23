package com.viimeiset.koiranvaatekauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.viimeiset.koiranvaatekauppa.domain.AppUser;
import com.viimeiset.koiranvaatekauppa.domain.AppUserRepository;

@RestController
@RequestMapping("/api/kayttajat")
public class RestUserController {

	@Autowired
	AppUserRepository userRepository;
	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();


	@GetMapping
	public Iterable<AppUser> getKayttajat() {
		return userRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<AppUser> luoKayttaja(@RequestBody AppUser user) {
		String kryptattuSalasana = bc.encode(user.getPasswordHash());
	    user.setPasswordHash(kryptattuSalasana);
	    AppUser newUser = new AppUser();
    	newUser.setUsername(user.getUsername());
    	newUser.setPasswordHash(user.getPasswordHash());
    	newUser.setRole("USER");
    	newUser.setEtunimi(user.getEtunimi());
    	newUser.setSukunimi(user.getSukunimi());
    	newUser.setSahkoposti(user.getSahkoposti());
		userRepository.save(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> poistaKayttaja(@PathVariable String username) {
	    AppUser user = userRepository.findByUsername(username);

	    if (user != null) {
	        userRepository.delete(user);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
		
	}
