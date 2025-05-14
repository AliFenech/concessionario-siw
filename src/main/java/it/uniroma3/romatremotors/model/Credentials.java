package it.uniroma3.romatremotors.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Credentials {

	@Id
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String ruolo;
	
}
