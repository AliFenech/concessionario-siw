package it.uniroma3.romatremotors.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import it.uniroma3.romatremotors.model.User;

@Controller
public interface UserRepository extends CrudRepository<User, Long>{

}
