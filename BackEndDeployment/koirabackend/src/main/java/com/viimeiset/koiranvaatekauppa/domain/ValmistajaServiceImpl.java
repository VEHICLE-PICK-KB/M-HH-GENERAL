package com.viimeiset.koiranvaatekauppa.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValmistajaServiceImpl implements ValmistajaService{

	@Autowired
	ValmistajaRepository valmistajaRepo;
	
	@Override
	public List<Tuote> findTuotteetByValmistajaId(Long valmistajaId) {
	    Optional<Valmistaja> valmistajaOptional = valmistajaRepo.findById(valmistajaId);

	    return valmistajaOptional.map(Valmistaja::getTuotteet).orElse(Collections.emptyList());
	}



}
