package com.viimeiset.koiranvaatekauppa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
	List<AppUser> findByRole(String role);
}