package com.viimeiset.koiranvaatekauppa.domain;

import java.util.List;

public interface ValmistajaService {

	List<Tuote> findTuotteetByValmistajaId(Long valmistajaId);

}
