package it.uniroma3.romatremotors.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import it.uniroma3.romatremotors.model.Credentials;

@Controller
public interface CredentialsRepository extends CrudRepository<Credentials, String>{

	public Optional<Credentials> findByUsername(String username);
}
