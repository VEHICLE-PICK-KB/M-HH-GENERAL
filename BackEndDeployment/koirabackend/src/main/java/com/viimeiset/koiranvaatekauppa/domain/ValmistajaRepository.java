package com.viimeiset.koiranvaatekauppa.domain;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface ValmistajaRepository extends CrudRepository <Valmistaja, Long> {
    Valmistaja findByNimi(String nimi);
    Optional<Valmistaja> findById(Long Id);

}