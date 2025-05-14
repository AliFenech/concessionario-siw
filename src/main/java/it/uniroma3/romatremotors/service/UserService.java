package it.uniroma3.romatremotors.service;

import org.springframework.stereotype.Service;

import it.uniroma3.romatremotors.model.User;
import it.uniroma3.romatremotors.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
