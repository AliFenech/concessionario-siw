package it.uniroma3.romatremotors.service;

import org.springframework.stereotype.Service;

import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.repository.CredentialsRepository;

@Service
public class CredentialsService {

	private CredentialsRepository credentialsRepository;
	
	public Credentials getCredentials(String username) {
		return credentialsRepository.findById(username).get();
	}
	
	public Credentials saveCredentials(Credentials credentials) {
		return credentialsRepository.save(credentials);
	}
}
