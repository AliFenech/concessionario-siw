package it.uniroma3.romatremotors.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Credentials {

	@Id
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String ruolo;
	
	@OneToOne
	private User utente;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return the utente
	 */
	public User getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(User utente) {
		this.utente = utente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ruolo, username, utente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		return Objects.equals(ruolo, other.ruolo) && Objects.equals(username, other.username)
				&& Objects.equals(utente, other.utente);
	}



	
	
	
}
