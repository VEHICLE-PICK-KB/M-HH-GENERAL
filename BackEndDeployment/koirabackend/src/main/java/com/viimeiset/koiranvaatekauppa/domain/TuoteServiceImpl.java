package com.viimeiset.koiranvaatekauppa.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TuoteServiceImpl implements TuoteService {

    @Autowired
    private TuoteRepository tuoteRepository;

    @Autowired
    private ValmistajaRepository valmistajaRepository;

    @Override
    @Transactional
    public Tuote saveTuoteWithValmistaja(Tuote tuote) {

        Valmistaja existingValmistaja = valmistajaRepository.findByNimi(tuote.getValmistaja().getNimi());

        if (existingValmistaja == null) {

            valmistajaRepository.save(tuote.getValmistaja());
        } else {

            tuote.setValmistaja(existingValmistaja);
        }

        return tuoteRepository.save(tuote);
    }

}
