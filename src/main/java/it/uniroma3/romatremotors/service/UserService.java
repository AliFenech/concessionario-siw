package it.uniroma3.romatremotors.service;

import org.springframework.stereotype.Service;

import it.uniroma3.romatremotors.model.Utente;
import it.uniroma3.romatremotors.repository.UtenteRepository;

@Service
public class UserService {

	private UtenteRepository userRepository;
	
	public Utente getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	
	public Utente saveUser(Utente user) {
		return userRepository.save(user);
	}

}
