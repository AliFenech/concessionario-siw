package it.uniroma3.romatremotors.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.romatremotors.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
