package it.uniroma3.romatremotors.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.romatremotors.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
}
