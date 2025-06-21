package it.uniroma3.romatremotors.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.romatremotors.model.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long>{
	
}
