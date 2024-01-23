package com.viimeiset.koiranvaatekauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viimeiset.koiranvaatekauppa.domain.Tuote;
import com.viimeiset.koiranvaatekauppa.domain.TuoteRepository;
import com.viimeiset.koiranvaatekauppa.domain.Valmistaja;
import com.viimeiset.koiranvaatekauppa.domain.ValmistajaRepository;

@RestController
@RequestMapping("/api")
public class RestTuoteController {

	// injektoi TuoteRepossitoryn
	@Autowired
	TuoteRepository repository;

	// injektoi ValmistajaRepossitoryn
	@Autowired
	ValmistajaRepository valmistajaRepository;
	

	// GET pyyntö jolla haetaan tietokannasta kaikki tuotteet
	@GetMapping("/tuotteet")

	public Iterable<Tuote> getTuotteet() {
		return repository.findAll();
	}

	// GET pyyntö jolla haetaan tietokannasta kaikki valmistajat
	@GetMapping("/valmistajat")

	public Iterable<Valmistaja> getValmistajat() {
		return valmistajaRepository.findAll();
	}
	// GET pyyntö jolla haetaan tietokannasta kaikki takit.

	@GetMapping("/tuotteet/{tyyppi}")

	public Iterable<Tuote> getTakit() {
		return repository.findByTyyppi("Takki");

	}

}
