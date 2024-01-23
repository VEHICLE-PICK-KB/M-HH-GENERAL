package com.viimeiset.koiranvaatekauppa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface TuoteRepository extends CrudRepository <Tuote, Long>{
	
	List<Tuote> findByTyyppi(String tyyppi);
	List<Tuote> findByTuoteNimi(String tuoteNimi);
	
    
}
